package com.omp.dev.purchase.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.omp.commons.exception.ServiceException;
import com.omp.commons.payment.ApprovalLimitOverflowException;
import com.omp.commons.payment.InvalidPaymentInfoException;
import com.omp.commons.payment.PaymentConnectFailException;
import com.omp.commons.payment.PaymentDenyException;
import com.omp.commons.payment.PaymentFailException;
import com.omp.commons.payment.PaymentProcessor;
import com.omp.commons.payment.creditcard.CreditCardPayment;
import com.omp.commons.service.AbstractService;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.config.ConfigProperties;
import com.omp.dev.purchase.model.Purchase;

public class PurchaseServiceImpl extends AbstractService implements PurchaseService {

	//결제 로그 생성
	static ConfigProperties prop = new ConfigProperties();  
	SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMddHHmmss");

	private static final GLogger log = new GLogger(PurchaseServiceImpl.class);

	//가입비 구매 의뢰
	public Purchase purchase(Purchase purchase) throws ServiceException {
		//구매 정보 성공 및 정보 리턴
		if(purchase.getUsPayYn().equals("Y")){
			//가입비 결재
			//1.가입비 체크(중복결제 재체크)
			int checkUsPay=0;
			try {
				checkUsPay = (Integer)super.commonDAO.queryForObject("Purchase.preUsPay", purchase);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if( checkUsPay > 0){
				//기가입 내역이 있음
				purchase.setRtnCd("12");
				purchase.setRtnMsg("이미 가입비 구매 내역이 있습니다.");
				return purchase; //중복구매 방지

			} else {

				//2. 구매아이디 생성	
				String prchsid = null;
				try {
					prchsid = (String) super.commonDAO.queryForObject("Purchase.getSeqPrchsId");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				purchase.setPrchsId(prchsid);

				//카드결제를 위한 정보 셋팅 
				purchase.setPrc(prop.getString("omp.dev.member.pay.prc"));		//price
				purchase.setProdId("0000000000");								//가입비 결제 상품코드 0000000000
				purchase.setProdNm(prop.getString("omp.dev.member.pay.name")); 	//연회비 결제
				purchase.setPrchsCls("OR000401"); 								//웹(연회비 결제)

				//TID 채번
				String TID_PREFIX = "NCCCCARDPAY";
				String tid;
				try {
					tid = (String) super.commonDAO.queryForObject("Purchase.getTidNo",TID_PREFIX);
				} catch (SQLException e) {
					purchase.setRtnCd("33");
					purchase.setRtnMsg("tid 생성 에러");
					return purchase; 
				}
				purchase.setTid(tid);

				boolean cardrtn = false;

				//3.카드 결제
				Purchase cardAppPurchase = new Purchase();

				cardAppPurchase = cardApproval(purchase);

				if (cardAppPurchase.getRtnCd().equals("0000")) {
					//카드결제 성공
					purchase.setRtnCd("0000");
					purchase.setRtnMsg("신용카드 결제 성공");			
					purchase.setTid(cardAppPurchase.getTid());			
					purchase.setMoid(cardAppPurchase.getMoid());
					purchase.setAppldate(cardAppPurchase.getAppldate());
					purchase.setAppltime(cardAppPurchase.getAppltime());
					purchase.setApplnum(cardAppPurchase.getApplnum());				//승인번호
					purchase.setTotprice(cardAppPurchase.getTotprice());			//승인금액
					purchase.setCardResultCode(cardAppPurchase.getCardResultCode());
					purchase.setCardInterest(cardAppPurchase.getCardInterest());	//일시불
					purchase.setPayCls("OR000601");  								//신용카드
					cardrtn = true;
				}else {
					purchase.setRtnCd(cardAppPurchase.getRtnCd());
					purchase.setRtnMsg("카드결제 실패 : " + cardAppPurchase.getRtnMsg());
					return purchase; 
				}

				if (cardrtn) {
					//결제 성공
					purchase.setPrchsDt(purchase.getAppldate());
					purchase.setPrchsTm(purchase.getAppltime());
					purchase.setPrchsStat("OR000301");							//구매
					purchase.setPrchsAmt(Integer.valueOf(purchase.getPrc()));	//연회비 금액

					boolean result = false;
					daoManager.startTransaction();

					//4-1.구매 정보 insert
					Purchase purchaseInfo = new Purchase();

					purchaseInfo.setPrchsId(purchase.getPrchsId());
					purchaseInfo.setMbrNo(purchase.getMbrNo());
					purchaseInfo.setGdid(purchase.getGdid());
					purchaseInfo.setPrchsDt(purchase.getAppldate());
					purchaseInfo.setPrchsTm(purchase.getAppltime());
					purchaseInfo.setPrchsStat("OR000301");					//구매
					purchaseInfo.setPrchsAmt(purchase.getPrcAmt());
					purchaseInfo.setPrchsCls(purchase.getPrchsCls());		//구매경로
					purchaseInfo.setPayCls(purchase.getPayCls()); 
					purchaseInfo.setPrchsCnclDtm("");
					purchaseInfo.setDcAmt(purchase.getDcAmt());				//할인금액

					try {
						super.commonDAO.insert("Purchase.insertPurchaseInfo",purchaseInfo);
					} catch (SQLException e) {
						purchase.setRtnCd("4");
						purchase.setRtnMsg("구매 정보 insert 실패");
						return purchase; 
					}
					
					//4-2.상품 구매 정보 insert
					Purchase purchaseExpInfo = new Purchase();

					purchaseExpInfo.setPrchsProdNum("1");
					purchaseExpInfo.setPrchsId(purchase.getPrchsId());
					purchaseExpInfo.setProdId(purchase.getProdId());
					purchaseExpInfo.setPrcAmt(purchase.getPrcAmt());
					purchaseExpInfo.setDwnldStat("N");
					purchaseExpInfo.setDwnldExpDt("99991231235959");
					purchaseExpInfo.setDelYn("N");

					try {
						super.commonDAO.insert("Purchase.insertExpPurchaseInfo",purchaseExpInfo);
					} catch (SQLException e) {
						purchase.setRtnCd("5");
						purchase.setRtnMsg("상품 구매 정보 insert 실패");
						return purchase; 
					}
				
					//4-4.결제결과내역관리 insert (결제 수단에 따른 (카드))
					Purchase purchaseAppfConf = new Purchase();
					purchaseAppfConf.setTid(purchase.getTid());
					purchaseAppfConf.setPrchsId(purchase.getPrchsId());
					purchaseAppfConf.setMoid(purchase.getMoid());
					purchaseAppfConf.setAppldate(purchase.getAppldate());
					purchaseAppfConf.setAppltime(purchase.getAppltime());
					purchaseAppfConf.setApplnum(purchase.getApplnum());
					purchaseAppfConf.setPayCls(purchase.getPayCls());
					purchaseAppfConf.setTotprice(purchase.getTotprice());
					purchaseAppfConf.setCardNum(purchase.getCardNum());
					purchaseAppfConf.setCardInterest(purchase.getCardInterest());
					purchaseAppfConf.setCardQuota(purchase.getCardQuota());
					purchaseAppfConf.setPayHpNo("");

					try {
						this.commonDAO.insert("Purchase.insertAppfConf",purchaseAppfConf);
					} catch (SQLException e) {
						purchase.setRtnCd("6");
						purchase.setRtnMsg("구매 수단별 결제 내역 insert 실패");
						return purchase; 
					}

					String rtYearterm = getSysDate("yyyyMM").toString(); 
					purchase.setRtYearterm(rtYearterm);

					String rtprefix_rtno = null;
					try {
						rtprefix_rtno = (String) super.commonDAO.queryForObject("Purchase.selectReceipt", rtYearterm);
					} catch (SQLException e) {
						purchase.setRtnCd("66");
						purchase.setRtnMsg("rtprefix_rtno select error");
						return purchase; 
					}
					purchase.setRtprefix_rtno(rtprefix_rtno);

					//5.영수증 발급 정보 update 
					result = updateReceipt(purchase);

					//6.영수증 관리 정보 카운트 update
					result = updateReceiptInfo(purchase);

					//7.유료개발자결제내역 insert
					result = createpayHistInfo(purchase);

					if (!result) {
						purchase.setRtnCd("7");
						purchase.setRtnMsg("가입비 결제 실패");
						return purchase;
					}

					daoManager.commitTransaction();
				}
				
				purchase.setRtnCd("0000");
				purchase.setRtnMsg("카드결제성공");
			}
		} else {
			purchase.setRtnCd("8");
			purchase.setRtnMsg("check UsPayYn value");
		}
		return purchase; 
	}

	public Purchase cardApproval(Purchase purchase) throws ServiceException {

		Purchase rtnpurchase = new Purchase();
		String orderId = purchase.getTid();

		CreditCardPayment cardPayment = new CreditCardPayment();
		cardPayment.setOrderId(orderId);		
		cardPayment.setRequestAmount(new BigDecimal(purchase.getPrc()));
		cardPayment.setCardNo(purchase.getCardNum());
		cardPayment.setAuthCode(purchase.getCardext());
		cardPayment.setExpireYear(purchase.getCardexpy());
		cardPayment.setExpireMonth(purchase.getCardexpm());
		cardPayment.setInstallmentMonth(1);					// 일시불

		try{
			cardPayment = PaymentProcessor.requestCreditCardApproval(cardPayment);

			String approvalDate = SDF.format(cardPayment.getApprovalDate()); 

			rtnpurchase.setRtnCd("0000");
			rtnpurchase.setRtnMsg("신용카드 결제 성공");
			rtnpurchase.setPayCls("OR000601");
			rtnpurchase.setTid(cardPayment.getOrderId());			
			rtnpurchase.setMoid(cardPayment.getOrderId());
			rtnpurchase.setAppldate(approvalDate.substring(0, 8));
			rtnpurchase.setAppltime(approvalDate.substring(8));
			rtnpurchase.setApplnum(cardPayment.getApprovalId());					//승인번호
			rtnpurchase.setTotprice(cardPayment.getApprovalAmount().toString());	//승인금액
			rtnpurchase.setCardNum(cardPayment.getCardNo());						//카드번호
			rtnpurchase.setCardInterest("0");
			rtnpurchase.setCardQuota("1");
			rtnpurchase.setCardexpy(cardPayment.getExpireYear());

			return rtnpurchase;

		}catch(PaymentConnectFailException e){
			if(e.isApprovaled()){
				rtnpurchase.setRtnCd("0111");
				rtnpurchase.setRtnMsg("이미 승인된 거래 입니다." + e);
			}else{
				rtnpurchase.setRtnCd("0122");
				rtnpurchase.setRtnMsg("결재 대행서버 접속 실패했습니다" + e);
			}	
			return rtnpurchase;
		}catch(PaymentFailException e){
			rtnpurchase.setRtnCd("0211");
			rtnpurchase.setRtnMsg("결재 처리 시스템 오류입니다" + e);
			return rtnpurchase;
		}catch(ApprovalLimitOverflowException e){
			rtnpurchase.setRtnCd("03"+e.getVenderCode()); // getVenderCode()
			rtnpurchase.setRtnMsg("결재 처리 시스템 오류입니다" + e);
			return rtnpurchase;
		}catch(InvalidPaymentInfoException e){
			rtnpurchase.setRtnCd("04"+e.getVenderCode()); // getVenderCode()
			rtnpurchase.setRtnMsg(e.getVenderMessage());
			return rtnpurchase;
		}catch(PaymentDenyException e){
			rtnpurchase.setRtnCd("05"+e.getVenderCode()); // getVenderCode()
			rtnpurchase.setRtnMsg(e.getVenderMessage());
			return rtnpurchase;
		}catch(UnsupportedOperationException e){
			rtnpurchase.setRtnCd("0611"); // getVenderCode()
			rtnpurchase.setRtnMsg("이 결재 방법을 지원하지 않습니다." + e);
			return rtnpurchase;
		}
	}

	public boolean updateReceipt(Purchase purchase) throws ServiceException {

		try {
			this.commonDAO.update("Purchase.updateReceipt",purchase);
			return true;
		}catch  (Exception e){
			log.debug("영수증 발급 정보 생성 에러입니다." + e);
			return false;
		}
	}

	public boolean updateReceiptInfo(Purchase purchase) throws ServiceException {
		try {
			this.commonDAO.update("Purchase.updateReceiptInfo",purchase);
			return true;
		}catch  (Exception e){
			log.debug("영수증 발급 정보 관리 에러입니다." + e);
			return false;
		}
	}

	public boolean createpayHistInfo(Purchase purchase) throws ServiceException {
		boolean rtn=false;
		try {
			this.commonDAO.insert("Purchase.insertpayHistInfo",purchase);
			rtn = true;
		}catch  (Exception e){
			log.debug("회원 유료결제 정보 관리 에러입니다." + e);
			rtn = false;
		}
		return rtn;
	}

	
	private static String getSysDate(String pattern) { 
		String result = "";

		Calendar cal = Calendar.getInstance();

		result = getFormatString(pattern, cal);
		return result;
	}

	private static String getFormatString(String pattern, Calendar cal)		{
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String dateString = formatter.format(cal.getTime());
		return dateString;
	}

}
