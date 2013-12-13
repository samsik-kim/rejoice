package com.omp.dev.purchase.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nccc.evpos.ApiClient;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.config.ConfigProperties;
import com.omp.dev.purchase.model.Purchase;


public class PurchaseServiceImpl extends AbstractService implements PurchaseService {

	//결제 로그 생성
	static ConfigProperties prop = new ConfigProperties();  
	private String date = getSysDate("yyyyMMddHHmmssSSS");

	private static final GLogger log = new GLogger(PurchaseServiceImpl.class);
	private static final Log paymentLog = LogFactory.getLog("payment");

	//구매 의뢰
	public Purchase purchase(Purchase purchase) throws ServiceException {
		//구매 정보 성공 및 정보 리턴
		boolean isCashUse = false;
		Purchase rtnpurchase = new Purchase();

		if(purchase.getUsPayYn().equals("N")){ //상품구매

			//1.기구매 체크(중복결제 재체크)
			Purchase obj=null;
			try {
				obj = (Purchase)super.commonDAO.queryForObject("Purchase.prePurchase", purchase);
			} catch (SQLException e) {
				rtnpurchase.setRtnCd("1");
				rtnpurchase.setRtnMsg("구매아이디 중복여부 확인 실패");
				return rtnpurchase; 
			}

			if(obj != null){
				//기구매 내역이 있음
				rtnpurchase.setRtnCd("2");
				rtnpurchase.setRtnMsg("기 구매 내역이 있습니다.");
				return rtnpurchase; 									//중복구매 방지
			}

			//2. 구매아이디 생성
			String prchsid = null;
			try {
				prchsid = (String) super.commonDAO.queryForObject("Purchase.getSeqPrchsId");
				purchase.setPrchsId(prchsid);
			} catch (SQLException e) {
				rtnpurchase.setRtnCd("3");
				rtnpurchase.setRtnMsg("구매아이디 생성 실패");
				return rtnpurchase; 
			}

			if( purchase.getPayCls().equals("OR000699")){ //3.무료 

				try {				

					daoManager.startTransaction();

					// 3-1.구매 정보 insert
					Purchase purchaseInfo = new Purchase();

					purchaseInfo.setPrchsId(purchase.getPrchsId());
					purchaseInfo.setMbrNo(purchase.getMbrNo());
					purchaseInfo.setGdid(purchase.getGdid());
					purchaseInfo.setPrchsDt(date.substring(0, 8));
					purchaseInfo.setPrchsTm(date.substring(8, 14));
					purchaseInfo.setPrchsStat("OR000301");					//구매
					purchaseInfo.setPrchsAmt('0');
					purchaseInfo.setPrchsCls(purchase.getPrchsCls());		//구매경로
					purchaseInfo.setPayCls(purchase.getPayCls()); 
					purchaseInfo.setPrchsCnclDtm("");
					purchaseInfo.setDcAmt(purchase.getDcAmt());				//할인금액

					try {
						super.commonDAO.insert("Purchase.insertPurchaseInfo",purchaseInfo);
					} catch (SQLException e) {
						rtnpurchase.setRtnCd("4");
						rtnpurchase.setRtnMsg("구매 정보 insert 실패");
						return rtnpurchase; 
					}

					//3-2.상품 구매 정보 insert
					Purchase purchaseExpInfo = new Purchase();

					purchaseExpInfo.setPrchsId(purchase.getPrchsId());
					purchaseExpInfo.setProdId(purchase.getProdId());
					purchaseExpInfo.setCorpProdNo(purchase.getCorpProdNo());
					purchaseExpInfo.setPrcAmt(0);
					purchaseExpInfo.setDwnldStat("N");
					purchaseExpInfo.setDwnldExpDt("99991231235959");
					purchaseExpInfo.setDelYn("N");

					try {
						super.commonDAO.insert("Purchase.insertExpPurchaseInfo",purchaseExpInfo);
					} catch (SQLException e) {
						rtnpurchase.setRtnCd("5");
						rtnpurchase.setRtnMsg("상품 구매 정보 insert 실패");
						return rtnpurchase; 
					}

					// 3-3.상품 구매  cnt증가
					Purchase prodPrchsCnt = new Purchase();

					prodPrchsCnt.setProdId(purchase.getProdId());

					try {
						super.commonDAO.update("Purchase.mergeProdPrchsCnt",prodPrchsCnt);
					} catch (SQLException e) {
						rtnpurchase.setRtnCd("6");
						rtnpurchase.setRtnMsg("상품 구매  cnt증가 insert 실패");
						return rtnpurchase; 
					}

					daoManager.commitTransaction();

					rtnpurchase.setRtnCd("0000");
					rtnpurchase.setRtnMsg("결제 성공");
					return rtnpurchase;

				} catch (Exception e) {
					throw new ServiceException("무료 결제 정보 생성하는 동안 에러가 발생 하였습니다.", e);
				} finally {
					daoManager.endTransaction();
				}

			} else { //4.유료
				// Cash Use
				daoManager.startTransaction();

				if(purchase.getCashAplyYn().equals("Y")){
					isCashUse = true;
				}

				boolean isUsePoint = false;
				// 4-1. Cash and Point Reserve
				if(isCashUse){

					// Cash & Point Reserve
					Purchase cashUsePurchase = new Purchase();

					cashUsePurchase = reserveCashPoint(purchase);

					if(cashUsePurchase.getRtnCd().equals("0000")) {
						purchase.setReserveCashAmt(cashUsePurchase.getReserveCashAmt());
						purchase.setReservePointAmt(cashUsePurchase.getReservePointAmt());
					}else {
						rtnpurchase.setRtnCd("7");
						rtnpurchase.setRtnMsg("캐쉬예약 실패");
						return rtnpurchase; 
					}
				}

				// Case Card & Phone
				if(purchase.getPayCls().equals("OR000601") || purchase.getPayCls().equals("OR000602")) {

					boolean rtnpaymethod = false;

					if (purchase.getPayCls().equals("OR000601")) { //credit

						//TID 채번
						String TID_PREFIX = "NCCCCARDPAY";
						String tid=null;
						try {
							tid = (String) super.commonDAO.queryForObject("Purchase.getTidNo", TID_PREFIX);
						} catch (SQLException e) {
							purchase.setRtnCd("33");
							purchase.setRtnMsg("tid 생성 에러" + e);
							return purchase; 
						}

						purchase.setTid(tid);

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
							purchase.setApplnum(cardAppPurchase.getApplnum());		//승인번호
							purchase.setTotprice(cardAppPurchase.getTotprice());	//승인금액
							purchase.setCardResultCode(cardAppPurchase.getCardResultCode());
							purchase.setCardInterest(cardAppPurchase.getCardInterest());	//일시불
							purchase.setPayCls("OR000601");  						//신용카드

							rtnpaymethod = true;
						}else {
							purchase.setRtnCd("44");
							purchase.setRtnMsg("카드결제 실패");
							return purchase; 
						}

					}else {
						//phonebill
						//TID 채번
						String TID_PREFIX = "DANALPHONEPAY";
						String tid = null;
						try {
							tid = (String) super.commonDAO.queryForObject("Purchase.getTidNo",TID_PREFIX);
						} catch (SQLException e) {
							purchase.setRtnCd("55");
							purchase.setRtnMsg("tid 생성 에러");
							return purchase; 
						}
						purchase.setTid(tid);

						Purchase phoneAppPurchase = new Purchase();
						phoneAppPurchase = phoneApproval(purchase);

						if(phoneAppPurchase.getRtnCd().equals("0000")) {
							purchase.setRtnCd("0000");
							purchase.setRtnMsg("폰빌 결제 성공");
							purchase.setTid(phoneAppPurchase.getTid());			
							purchase.setMoid(rtnpurchase.getMoid());
							purchase.setAppldate(rtnpurchase.getAppldate());
							purchase.setAppltime(rtnpurchase.getAppltime());
							purchase.setTotprice(rtnpurchase.getTotprice());			//승인금액
							purchase.setPayHpNo(purchase.getPayHpNo());
							purchase.setPayCls("OR000602");  					//폰빌

							rtnpaymethod = true;
						}else {
							purchase.setRtnCd("55");
							purchase.setRtnMsg("폰빌결제 실패");
							return purchase;
						}
					}

					if (rtnpaymethod) {

						boolean result = false;

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
							rtnpurchase.setRtnCd("4");
							rtnpurchase.setRtnMsg("구매 정보 insert 실패");
							return rtnpurchase; 
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
							rtnpurchase.setRtnCd("5");
							rtnpurchase.setRtnMsg("상품 구매 정보 insert 실패");
							return rtnpurchase; 
						}

						// 4-3.상품 구매  cnt증가
						Purchase prodPrchsCnt = new Purchase();
						prodPrchsCnt.setProdId(purchase.getProdId());
						try {
							super.commonDAO.update("Purchase.mergeProdPrchsCnt",prodPrchsCnt);
						} catch (SQLException e1) {
							rtnpurchase.setRtnCd("9");
							rtnpurchase.setRtnMsg("상품 구매  cnt증가 실패");
							return rtnpurchase; 
						}

						//4-4.결제결과내역관리 insert (결제 수단에 따른 (카드,폰빌)
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
						purchaseAppfConf.setPayHpNo(purchase.getPayHpNo());

						try {
							this.commonDAO.insert("Purchase.insertAppfConf",purchaseAppfConf);
						} catch (SQLException e) {
							rtnpurchase.setRtnCd("6");
							rtnpurchase.setRtnMsg("구매 수단별 결제 내역 insert 실패");
							return rtnpurchase; 
						}

						String rtYearterm = getSysDate("yyyyMM").toString(); 
						purchase.setRtYearterm(rtYearterm);

						String rtprefix_rtno = null;
						try {
							rtprefix_rtno = (String) super.commonDAO.queryForObject("Purchase.selectReceipt", rtYearterm);
						} catch (SQLException e) {
							rtnpurchase.setRtnCd("66");
							rtnpurchase.setRtnMsg("rtprefix_rtno select error");
							return purchase; 
						}
						purchase.setRtprefix_rtno(rtprefix_rtno);

						if (purchase.getPayCls().equals("OR000601")) {//신용카드인경우만 영수증발급

							//4-2.영수증 발급 정보 update 
							result = updateReceipt(purchase);

							//6.영수증 관리 정보 카운트 update
							result = updateReceiptInfo(purchase);

						}	
						if(isCashUse){ //캐시 사용시
							if(purchase.getReserveCashAmt() != 0){

								// 6. Appoval Information save(Cash & Point)
								Purchase cashAppPurchase = new Purchase();
								try {
									cashAppPurchase = useCash(purchase);
								} catch (Exception e1) {
									rtnpurchase.setRtnCd("66");
									rtnpurchase.setRtnMsg("캐시 결제 내역 insert 실패" + e1);
									return rtnpurchase; 
								}
								if (cashAppPurchase.getRtnCd().equals("0000")){
									purchase.setAvailCashAmt(cashAppPurchase.getAvailCashAmt());
								}else {
									rtnpurchase.setRtnCd("66");
									rtnpurchase.setRtnMsg("캐시 사용 에러");
									return purchase; 
								}

								String tid = null;
								try {
									tid = (String) super.commonDAO.queryForObject("Purchase.getTidNo","TSTORE");
								} catch (SQLException e) {
									rtnpurchase.setRtnCd("33");
									rtnpurchase.setRtnMsg("tid 생성 에러");
									return purchase; 
								}

								//4-4.결제결과내역관리 insert (결제 수단에 따른 (캐쉬)
								Purchase purchaseAppfConfCash = new Purchase();
								purchaseAppfConfCash.setTid(tid);
								purchaseAppfConfCash.setPrchsId(purchase.getPrchsId());
								purchaseAppfConfCash.setMoid(tid);
								purchaseAppfConfCash.setAppldate(date.substring(0,8));
								purchaseAppfConfCash.setAppltime(date.substring(8,14));
								purchaseAppfConfCash.setApplnum("");
								purchaseAppfConfCash.setPayCls("OR000607");
								purchaseAppfConfCash.setTotprice(String.valueOf(purchase.getReserveCashAmt()));
								purchaseAppfConfCash.setCardNum("");
								purchaseAppfConfCash.setCardInterest("");
								purchaseAppfConfCash.setCardQuota("");
								purchaseAppfConfCash.setPayHpNo("");

								try {
									this.commonDAO.insert("Purchase.insertAppfConf",purchaseAppfConfCash);
								} catch (SQLException e) {
									rtnpurchase.setRtnCd("6");
									rtnpurchase.setRtnMsg("구매 수단별 결제 내역 insert 실패");
									return rtnpurchase; 
								}
							}
							if(isUsePoint){

								Purchase pointAppPurchase = new Purchase();
								try {
									pointAppPurchase = usePoint(purchase);
								} catch (Exception e1) {
									rtnpurchase.setRtnCd("9");
									rtnpurchase.setRtnMsg("구매 수단별 결제 내역 insert 실패" + e1);
									return rtnpurchase; 
								}
								if (pointAppPurchase.getRtnCd().equals("0000")){
									purchase.setAvailPointAmt(pointAppPurchase.getAvailPointAmt());
								}else {
									rtnpurchase.setRtnCd("66");
									rtnpurchase.setRtnMsg("포인트 사용 에러");
									return purchase; 
								}

								// 6. Appoval Information save(Cash & Point)
								String tid= null;
								try {
									tid = (String) super.commonDAO.queryForObject("Purchase.getTidNo","TSTORE");
								} catch (SQLException e) {
									purchase.setRtnCd("33");
									purchase.setRtnMsg("tid 생성 에러");
									return purchase; 
								}

								//4-4.결제결과내역관리 insert (결제 수단에 따른 (캐쉬)
								Purchase purchaseAppfConfPoint = new Purchase();
								purchaseAppfConfPoint.setTid(tid);
								purchaseAppfConfPoint.setPrchsId(purchase.getPrchsId());
								purchaseAppfConfPoint.setMoid(tid);
								purchaseAppfConfPoint.setAppldate(date.substring(0,8));
								purchaseAppfConfPoint.setAppltime(date.substring(8,14));
								purchaseAppfConfPoint.setApplnum("");
								purchaseAppfConfPoint.setPayCls("OR000608");
								purchaseAppfConfPoint.setTotprice(String.valueOf(purchase.getReservePointAmt()));
								purchaseAppfConfPoint.setCardNum("");
								purchaseAppfConfPoint.setCardInterest("");
								purchaseAppfConfPoint.setCardQuota("");
								purchaseAppfConfPoint.setPayHpNo("");

								try {
									this.commonDAO.insert("Purchase.insertAppfConf",purchaseAppfConfPoint);
								} catch (SQLException e) {
									rtnpurchase.setRtnCd("6");
									rtnpurchase.setRtnMsg("구매 수단별 결제 내역 insert 실패");
									return rtnpurchase; 
								}
							}	

						}
						purchase.setRtnCd("0000");
						purchase.setRtnMsg("결제 성공");
						daoManager.commitTransaction();
						return purchase;
					}
				}
				daoManager.commitTransaction();
				return purchase;
			}	
		} else {
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
				purchase.setProdNm(prop.getString("omp.dev.member.pay.name")); 	//연회비 결제
				purchase.setPrchsCls("OR000401"); 							//웹(연회비 결제)

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
					purchase.setRtnCd("44");
					purchase.setRtnMsg("카드결제 실패");
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
						rtnpurchase.setRtnCd("4");
						rtnpurchase.setRtnMsg("구매 정보 insert 실패");
						return rtnpurchase; 
					}
					
//					//4-4.결제결과내역관리 insert (결제 수단에 따른 (카드))
//					Purchase purchaseAppfConf = new Purchase();
//					purchaseAppfConf.setTid(purchase.getTid());
//					purchaseAppfConf.setPrchsId(purchase.getPrchsId());
//					purchaseAppfConf.setMoid(purchase.getMoid());
//					purchaseAppfConf.setAppldate(purchase.getAppldate());
//					purchaseAppfConf.setAppltime(purchase.getAppltime());
//					purchaseAppfConf.setApplnum(purchase.getApplnum());
//					purchaseAppfConf.setPayCls(purchase.getPayCls());
//					purchaseAppfConf.setTotprice(purchase.getTotprice());
//					purchaseAppfConf.setCardNum(purchase.getCardNum());
//					purchaseAppfConf.setCardInterest(purchase.getCardInterest());
//					purchaseAppfConf.setCardQuota(purchase.getCardQuota());
//					purchaseAppfConf.setPayHpNo("");
//
//					try {
//						this.commonDAO.insert("Purchase.insertAppfConf",purchaseAppfConf);
//					} catch (SQLException e) {
//						rtnpurchase.setRtnCd("6");
//						rtnpurchase.setRtnMsg("구매 수단별 결제 내역 insert 실패");
//						return rtnpurchase; 
//					}

					String rtYearterm = getSysDate("yyyyMM").toString(); 
					purchase.setRtYearterm(rtYearterm);

					String rtprefix_rtno = null;
					try {
						rtprefix_rtno = (String) super.commonDAO.queryForObject("Purchase.selectReceipt", rtYearterm);
					} catch (SQLException e) {
						rtnpurchase.setRtnCd("66");
						rtnpurchase.setRtnMsg("rtprefix_rtno select error");
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
						rtnpurchase.setRtnCd("7");
						rtnpurchase.setRtnMsg("가입비 결제 실패");
						return rtnpurchase;
					}

					daoManager.commitTransaction();
				}
			}
		}
		return purchase;
	}

	public Purchase cardApproval(Purchase purchase) throws ServiceException {

		Purchase rtnpurchase = new Purchase();

		try {
			String orderId = purchase.getTid();

			String merchantid = prop.getString("omp.purchase.card.merchantid");					
			
			String expiredate = purchase.getCardexpy().substring(2, 4) +  purchase.getCardexpm();
			String cardnum = purchase.getCardNum();
			String cardext = purchase.getCardext();
			String cardInterest= "0";
			String cardquota = "";
			//String cardInterest= purchase.getCardInterest();
			//String cardquota = purchase.getCardQuota();
			//String prc= purchase.getPrc();
			//testing 
			////////////////////////////////////////////////////////////////////////////////////////////////////
			//카드승인 api 제공전까지 성공만 보내기 시작
			////////////////////////////////////////////////////////////////////////////////////////////////////
			String prc = "99";
			////////////////////////////////////////////////////////////////////////////////////////////////////
			//카드승인 api 제공전까지 성공만 보내기 끝
			////////////////////////////////////////////////////////////////////////////////////////////////////

			// 1. 결제 API 호출 시작
			ApiClient apiClient=new ApiClient();

			apiClient.setMERCHANTID(merchantid);								//merchantid.
			apiClient.setORDERID(orderId);  							 		 //EC 系統交易序號對應商店指派的「交 易訂單編號		
			apiClient.setTERMINALID(prop.getString("omp.purchase.card.terminalid"));	 //端末機代號(TERMINALID):(3D)
			apiClient.setPAN(cardnum);  				 						//交易卡號(카드번호)
			apiClient.setEXPIREDATE(expiredate);				 				//유효기간(YYMM)
			apiClient.setEXTENNO(cardext);			 							//Visa、MasterCard及JCB卡片背面三   碼稱為CVV2或CVC2，AE卡的卡片    左上方或右上方的四碼稱為4DBC(註 3)
			apiClient.setTRANSCODE("00");										//00-授權,01取消
			apiClient.setTRANSMODE("0");					 					//0-一般、1-分期、2-紅利
			if (cardInterest.equals("0"))	apiClient.setINSTALLMENT("1");		//할부개월수					 						
			else apiClient.setINSTALLMENT(cardquota);				
			apiClient.setTRANSAMT(prc);											//交易金額(금액)	
			apiClient.setURL(prop.getString("omp.purchase.card.domainName"),prop.getString("omp.purchase.card.requestURL"));

			int rtnCode=0;
			try {
				rtnCode = apiClient.postAuth();  // 執行交易(Internet用戶)
			} catch (Exception e){
				e.printStackTrace();
			}

			log.debug("결과코드 : " + apiClient.getRESPONSECODE());
			log.debug("결과메시지 : " + apiClient.getRESPONSEMSG());

			String msg6 = "";
			if (rtnCode > 0){ 
				rtnpurchase.setRtnCd("0000");
				rtnpurchase.setRtnMsg("신용카드 결제 성공");			
				rtnpurchase.setTid(apiClient.getORDERID());			
				rtnpurchase.setMoid(apiClient.getORDERID());
				rtnpurchase.setAppldate(apiClient.getTRANSDATE());
				rtnpurchase.setAppltime(apiClient.getTRANSTIME());
				rtnpurchase.setApplnum(apiClient.getAPPROVECODE());		//승인번호
				rtnpurchase.setTotprice(apiClient.getTRANSAMT());			//승인금액
				rtnpurchase.setCardNum(apiClient.getPAN());				//카드번호
				rtnpurchase.setCardInterest(cardInterest);
				rtnpurchase.setCardQuota(apiClient.getINSTALLMENT());
				rtnpurchase.setCardexpy(apiClient.getEXPIREDATE());
				rtnpurchase.setCardResultCode(apiClient.getRESPONSECODE());
				rtnpurchase.setPayCls("OR000601");  						//신용카드
			} else {
				rtnpurchase.setRtnCd("22");
				msg6 = apiClient.getRESPONSEMSG();
				if (msg6.equals("900")) rtnpurchase.setRtnMsg("XML格式錯誤(資料傳送失敗");
				else if (msg6.equals("901")) rtnpurchase.setRtnMsg("特店代號格式錯誤");
				else if (msg6.equals("902")) rtnpurchase.setRtnMsg("端末機代號格式錯誤");
				else if (msg6.equals("903")) rtnpurchase.setRtnMsg("訂單編號格式錯誤");
				else if (msg6.equals("904")) rtnpurchase.setRtnMsg("交易金額格式錯誤");
				else if (msg6.equals("905")) rtnpurchase.setRtnMsg("回應網址格式錯誤");
				else if (msg6.equals("906")) rtnpurchase.setRtnMsg("分期交易,請輸入分期期數");
				else if (msg6.equals("907")) rtnpurchase.setRtnMsg("交易模式輸入錯誤!!");
				else if (msg6.equals("908")) rtnpurchase.setRtnMsg("特店代號：不存在");
				else if (msg6.equals("909")) rtnpurchase.setRtnMsg("此特店代號已解約");
				else if (msg6.equals("910")) rtnpurchase.setRtnMsg("貴店網址[URL]與本中心註冊網址不同,請?明後再使用，謝謝");
				else if (msg6.equals("911")) rtnpurchase.setRtnMsg("訂單編號重複");
				else if (msg6.equals("912")) rtnpurchase.setRtnMsg("端末機代號：不存在");
				else if (msg6.equals("913")) rtnpurchase.setRtnMsg("端末機代號已停用");
				else if (msg6.equals("914")) rtnpurchase.setRtnMsg("資料庫AUTH_RECORD新增失敗");
				else if (msg6.equals("915")) rtnpurchase.setRtnMsg("資料庫AUTH_RECORD 讀取失敗");
				else if (msg6.equals("916")) rtnpurchase.setRtnMsg("連接授權主機失敗");
				else if (msg6.equals("917")) rtnpurchase.setRtnMsg("不支援此呼叫方法");
				else if (msg6.equals("918")) rtnpurchase.setRtnMsg("交易逾時[Session資料不一致]");
				else if (msg6.equals("919")) rtnpurchase.setRtnMsg("交易逾時[Session=null]");
				else if (msg6.equals("920")) rtnpurchase.setRtnMsg("卡號錯誤");
				else if (msg6.equals("921")) rtnpurchase.setRtnMsg("連接SSL Socket Server失敗");
				else if (msg6.equals("922")) rtnpurchase.setRtnMsg("3D 交易認證錯誤");
				else if (msg6.equals("923")) rtnpurchase.setRtnMsg("卡片效期格式錯誤");
				else if (msg6.equals("925")) rtnpurchase.setRtnMsg("端末機不支援API功能");
				else if (msg6.equals("926")) rtnpurchase.setRtnMsg("端末機不支援此功能");
				else if (msg6.equals("927")) rtnpurchase.setRtnMsg("交易代碼輸入錯誤!!");
				else if (msg6.equals("928")) rtnpurchase.setRtnMsg("非有效訂單編號，無法取消授權");
				else if (msg6.equals("929")) rtnpurchase.setRtnMsg("端末機尚未建檔");
				else if (msg6.equals("931")) rtnpurchase.setRtnMsg("交易失敗");
				else if (msg6.equals("933")) rtnpurchase.setRtnMsg("訂單編號不存在");
				else if (msg6.equals("934")) rtnpurchase.setRtnMsg("發卡行3D認證數字簽章驗章失敗");
				else if (msg6.equals("935")) rtnpurchase.setRtnMsg("發?行3D認證主機回覆認證錯誤");
				else if (msg6.equals("936")) rtnpurchase.setRtnMsg("超過特店月限額 ");
				else rtnpurchase.setRtnMsg("기타오류 : 관리자 문의요함");
			}
			
			logMsg(apiClient.getRESPONSECODE(), apiClient.getRESPONSEMSG(), "card", apiClient.getTRANSDATE(), apiClient.getTRANSTIME(), purchase.getHpNo(), purchase.getProdNm(), apiClient.getORDERID());
			
			rtnpurchase.setRtnCd("0000");
			rtnpurchase.setRtnMsg("신용카드 결제 성공");	
			return rtnpurchase;		
			
		}catch  (Exception e){
			rtnpurchase.setRtnCd("1");
			rtnpurchase.setRtnMsg("신용카드 결제 실패");
			log.debug("카드 승인에러입니다." + e);
			return rtnpurchase;
		}
	}

	public Purchase phoneApproval(Purchase purchase) throws ServiceException {

		Purchase rtnpurchase = new Purchase();

		try {
			String orderId = purchase.getTid();

			//			String output = "";
			//			String serverInfo = null;
			//			String otp = null;
			//
			//			String ItemCode = "12S1hI0001";
			//			String ItemName = purchase.getProdNm();
			//			String ItemType = "1";
			//			String Amount = purchase.getPrc();
			//
			//			String DstAddr = req.get("phoneNumber").toString();
			//			String Carrier = req.get("telecom").toString();
			//			String Iden = req.get("personalid").toString();
			//
			//			int ErrorFlag = 0;
			//			String Output = "";
			//			String configDir = "/app/omp/purchase/if_inipay/key/";
			//
			//			Calendar cal = Calendar.getInstance();
			//			String ndt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss S").format(cal.getTime());
			//
			//			String Input = "ItemType=Amount;ItemCount=1;";
			//			Input = Input + "ItemInfo=" + ItemType + "|" + Amount + "|1|" + ItemCode + "|" + ItemName + ";";
			//			Input = Input + "DstAddr=" + DstAddr + ";Iden=" + Iden + ";Carrier=" + Carrier;
			//
			//			String Result = "";
			//			String ErrorMsg = "";
			//			String CAP = "";
			//			String TID = "";
			//			String TotalAmount = "";
			//			String Bill_Date = "";
			//			try
			//			{
			//				for (int i = 1; i < 2; i++) {
			//					Output = new TeleditClient(configDir).otbill(Input);
			//					System.out.println(i + " : " + "[" + ndt + "] " + Output + "<br>");
			//				}
			//
			//				Result = PacketStringHelper.getString(Output, "Result", "\n");
			//				ErrorMsg = PacketStringHelper.getString(Output, "ErrMsg", "\n");
			//				CAP = PacketStringHelper.getString(Output, "CAP", "\n");
			//				TID = PacketStringHelper.getString(Output, "TID", "\n");
			//				TotalAmount = PacketStringHelper.getString(Output, "TotalAmount", "\n");
			//				Bill_Date = PacketStringHelper.getString(Output, "Date", "\n");
			//			}
			//			catch (Exception localException) {
			//			}
			//
			//			log.info("//////////////////[Danal 휴대폰 결제가 완료되었습니다.]/////////////////////<br>");
			//			log.info("Result : " + Result);
			//			log.info("TID : " + TID);
			//			log.info("TotalAmount : " + TotalAmount);
			//			log.info("CAP : " + CAP);
			//			log.info("Bill Date : " + Bill_Date);
			//			log.info("ErrorMessage : " + ErrorMsg);
			//			log.info("/////////////////////////////////////////////////////////////");
			//
			//			//결제 결과 정보를 담는다.
			//			res.put("resultMsg", ErrorMsg);
			//			res.put("paymethod", "phone");
			//			res.put("tid", TID);
			//			res.put("authdate", Bill_Date.substring(0, 8));
			//			res.put("authtime", Bill_Date.substring(8, 14));
			//
			//			if ("0".equals(Result)) res.put("resultCode", "0000");  //결제성공
			//			else res.put("resultCode", Result);
			//
			//			logMsg(Result, ErrorMsg, "phone", res.get("authdate").toString(), res.get("authtime").toString(), req.get("phoneNumber").toString(), req.get("content_name").toString(), res.get("tid").toString());

			////////////////////////////////////////////////////////////////////////////////////////////////////
			//핸드폰 api 제공전까지 성공만 보내기 시작
			////////////////////////////////////////////////////////////////////////////////////////////////////
			rtnpurchase.setRtnCd("0000");
			rtnpurchase.setRtnMsg("폰빌 결제 성공");
			rtnpurchase.setTid(orderId);			
			rtnpurchase.setMoid(orderId);
			rtnpurchase.setAppldate(date.substring(0, 8));
			rtnpurchase.setAppltime(date.substring(8, 14));
			rtnpurchase.setTotprice(purchase.getPrc());			//승인금액
			rtnpurchase.setPayHpNo(purchase.getPhoneNumber());
			rtnpurchase.setPayCls("OR000602");  					//폰빌
			return rtnpurchase;
			////////////////////////////////////////////////////////////////////////////////////////////////////
			//핸드폰 api 제공전까지 성공만 보내기 끝
			////////////////////////////////////////////////////////////////////////////////////////////////////	
		}catch  (Exception e){
			rtnpurchase.setRtnCd("22");
			rtnpurchase.setRtnMsg("폰빌 결제 실패");
			log.debug("폰빌 승인에러입니다." + e);
			return rtnpurchase;
		}
	}


	private Purchase useCash(Purchase purchase) throws Exception {

		Purchase rtnpurchase  = new Purchase();

		Map<String, Object> reserveCashMap = (Map<String, Object>)this.commonDAO.queryForObject("Purchase.getReserveToUseOdCashHst", purchase);

		int totAvailAmt = purchase.getCashAvailAmt();   //cash 
		int restAmt = 0; //차감 후 잔여금액	

		if( "0".equals(reserveCashMap.get("RESERVE_CASH_CNT").toString()) ) {
			// 예약 데이타 없슴
			rtnpurchase.setRtnCd("1");
			rtnpurchase.setRtnMsg("예약된 데이터가 없음 실패");
			return rtnpurchase;
		} else {

			this.commonDAO.update("Purchase.reserveToUseOdCashHst",purchase);

			// 예약금액 : 사용해야할 금액
			int prcAmt = Integer.parseInt((String)this.commonDAO.queryForObject("Purchase.getCashIdOccrAmt", purchase));	
			totAvailAmt = totAvailAmt - prcAmt;
			if(totAvailAmt < 0)	totAvailAmt = 0;

			// 사용가능한 금액 list
			List<Object> mbrCashAvailList = this.commonDAO.queryForList("Purchase.getMbrNoCashAvailAmts", purchase);

			String cashId = null;
			int availAmt = 0;
			Map<String, Object> tmpMap = null;

			for(int i = 0; i < mbrCashAvailList.size(); i++){
				tmpMap = (Map)mbrCashAvailList.get(i);

				cashId = tmpMap.get("CASH_POINT_ID").toString();
				availAmt = Integer.parseInt(tmpMap.get("AVAIL_AMT").toString());
				restAmt = prcAmt - availAmt;

				purchase.setOccrNo(purchase.getCashId());

				if(restAmt > 0){
					purchase.setUpCashId(cashId);
					purchase.setUpAvailAmt(0);

					this.commonDAO.update("Purchase.minusOdCashHst", purchase);

					purchase.setBfAvailAmt(availAmt);
					purchase.setAfAvailAmt(0);

					this.commonDAO.insert("Purchase.insertOdCashHstDtl", purchase);

				}else if(restAmt == 0){
					purchase.setUpCashId(cashId);
					purchase.setUpAvailAmt(0);

					this.commonDAO.update("Purchase.minusOdCashHst", purchase);

					purchase.setBfAvailAmt(availAmt);
					purchase.setAfAvailAmt(0);

					this.commonDAO.insert("Purchase.insertOdCashHstDtl", purchase);
					break;

				}else if(restAmt < 0){
					purchase.setUpCashId(cashId);
					purchase.setUpAvailAmt(Math.abs(restAmt));

					this.commonDAO.update("Purchase.minusOdCashHst", purchase);

					purchase.setBfAvailAmt(availAmt);
					purchase.setAfAvailAmt(Math.abs(restAmt));

					this.commonDAO.insert("Purchase.insertOdCashHstDtl", purchase);
					break;
				}
				prcAmt = restAmt;
			}
			rtnpurchase.setAvailCashAmt(Integer.valueOf(totAvailAmt));
			rtnpurchase.setRtnCd("0000");
			rtnpurchase.setRtnMsg("케시 사용 성공");
			return rtnpurchase;
		}
	}

	private Purchase usePoint(Purchase purchase) throws Exception {

		Purchase rtnpurchase  = new Purchase();

		Map<String, Object> reservePointMap = (Map<String, Object>)this.commonDAO.queryForObject("Purchase.getReserveToUseOdPointHst", purchase);

		int totAvailAmt =  purchase.getPointAvailAmt();   //point

		if( "0".equals(reservePointMap.get("RESERVE_POINT_CNT").toString()) ) {
			// 예약 데이타 없슴
			rtnpurchase.setRtnCd("1");
			rtnpurchase.setRtnMsg("예약된 데이터가 없음 실패");
			return rtnpurchase;

		} else {

			this.commonDAO.update("Purchase.reserveToUseOdPointHst",purchase);

			// 예약금액 : 사용해야할 금액
			int prcAmt = Integer.parseInt((String)this.commonDAO.queryForObject("Purchase.getPointIdOccrAmt", purchase));	
			totAvailAmt = totAvailAmt - prcAmt;
			if(totAvailAmt < 0)	totAvailAmt = 0;

			// 사용가능한 금액 list
			List<Object> mbrPointAvailList = this.commonDAO.queryForList("getMbrNoPointAvailAmts", purchase);

			int restAmt = 0; //차감 후 잔여금액	
			String pointId = null;
			int availAmt = 0;
			Map<String, Object> tmpMap = null;

			for(int i = 0; i < mbrPointAvailList.size(); i++){
				tmpMap = (Map)mbrPointAvailList.get(i);

				pointId = tmpMap.get("CASH_POINT_ID").toString();
				availAmt = Integer.parseInt(tmpMap.get("AVAIL_AMT").toString());
				restAmt = prcAmt - availAmt;
				purchase.setOccrNo(purchase.getPointId());

				if(restAmt > 0){
					purchase.setUpCashId(pointId);
					purchase.setUpAvailAmt(0);

					this.commonDAO.update("Purchase.minusOdPointHst", purchase);

					purchase.setBfAvailAmt(availAmt);
					purchase.setAfAvailAmt(0);

					this.commonDAO.insert("Purchase.insertOdPointHstDtl", purchase);

				}else if(restAmt == 0){
					purchase.setUpCashId(pointId);
					purchase.setUpAvailAmt(0);

					this.commonDAO.update("Purchase.minusOdPointHst", purchase);

					purchase.setBfAvailAmt(availAmt);
					purchase.setAfAvailAmt(0);

					this.commonDAO.insert("Purchase.insertOdPointHstDtl", purchase);				
					break;

				}else if(restAmt < 0){
					purchase.setUpCashId(pointId);
					purchase.setUpAvailAmt(Math.abs(restAmt));

					this.commonDAO.update("Purchase.minusOdPointHst", purchase);

					purchase.setBfAvailAmt(availAmt);
					purchase.setAfAvailAmt(Math.abs(restAmt));

					this.commonDAO.insert("Purchase.insertOdPointHstDtl", purchase);

					break;
				}
				prcAmt = restAmt;
			}
			rtnpurchase.setAvailPointAmt(Integer.valueOf(totAvailAmt));
			rtnpurchase.setRtnCd("0000");
			rtnpurchase.setRtnMsg("포인트 사용 성공");
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

	private Purchase reserveCashPoint(Purchase purchase) throws ServiceException {

		Purchase rtnpurchase = new Purchase();

		//1.총 케시금액과 tsotrycash금액 및 포인트 금액을 구한다.
		Map<String, Object> totAvailAmt = null;
		try {
			totAvailAmt = (Map<String, Object>)this.commonDAO.queryForObject("Purchase.getTotAvailAmt", purchase);
		} catch (SQLException e2) {
			rtnpurchase.setRtnCd("1");
			rtnpurchase.setRtnMsg("에러" + e2);
			return rtnpurchase;
		}

		int cashAvailAmt = Integer.parseInt(totAvailAmt.get("CASH_TOT_AMT").toString()); //사용할 수 있는 CASH
		int pointAvailAmt = Integer.parseInt(totAvailAmt.get("POINT_TOT_AMT").toString()); //사용할 수 있는 POINT

		purchase.setCashAvailAmt(cashAvailAmt);
		purchase.setPointAvailAmt(pointAvailAmt);
		purchase.setTotTcashAvailAmt(cashAvailAmt+pointAvailAmt);

		int restAmt = 0; // POINT 사용 후 남은 잔여 금액  	
		int cashPrcAmt = Integer.parseInt(purchase.getCashAmt());

		if(cashPrcAmt > pointAvailAmt + cashAvailAmt){
			rtnpurchase.setRtnCd("2");
			rtnpurchase.setRtnMsg("사용할 수 있는 캐시가 부족합니다.");
			return rtnpurchase;
		}else{    		
			if(cashAvailAmt != 0){
				//INSERT CASH
				purchase.setAvailAmt(0);

				if(cashPrcAmt>=cashAvailAmt){
					String cashId="";

					try {
						cashId = (String)this.commonDAO.queryForObject("Purchase.getSeqReserveCash");
					} catch (SQLException e1) {
						rtnpurchase.setRtnCd("3");
						rtnpurchase.setRtnMsg("캐시아이디 채번 에러입니다."+ e1) ;
						return rtnpurchase;
					}

					purchase.setCashId(cashId);
					purchase.setAmt(cashAvailAmt);

					try {
						this.commonDAO.insert("Purchase.reserveCash", purchase);
					} catch (SQLException e) {
						rtnpurchase.setRtnCd("4");
						rtnpurchase.setRtnMsg("캐시 예약 에러입니다."+ e) ;
						return rtnpurchase;
					}
					rtnpurchase.setReserveCashAmt(cashAvailAmt);

				}else{
					String cashId="";
					try {
						cashId = (String)this.commonDAO.queryForObject("Purchase.getSeqReserveCash");
					} catch (SQLException e1) {
						rtnpurchase.setRtnCd("3");
						rtnpurchase.setRtnMsg("캐시아이디 채번 에러입니다."+ e1) ;
						return rtnpurchase;
					}

					purchase.setCashId(cashId);
					purchase.setAmt(cashPrcAmt);

					try {
						this.commonDAO.insert("Purchase.reserveCash", purchase);
					} catch (SQLException e) {
						rtnpurchase.setRtnCd("4");
						rtnpurchase.setRtnMsg("캐시 예약 에러입니다."+ e) ;
						return rtnpurchase;
					}
					rtnpurchase.setReserveCashAmt(cashPrcAmt);
				}

				restAmt = cashAvailAmt - cashPrcAmt;

				//INSERT POINT(restAmt 절대값)
				if(restAmt<0){       			

					String pointId="";

					try {
						pointId = (String)this.commonDAO.queryForObject("Purchase.getSeqReservePoint");
					} catch (SQLException e1) {
						rtnpurchase.setRtnCd("5");
						rtnpurchase.setRtnMsg("포인트아이디 채번 에러입니다."+ e1) ;
						return rtnpurchase;
					}

					purchase.setPointId(pointId);
					purchase.setAmt(restAmt);

					try {
						this.commonDAO.insert("Purchase.reservePoint", purchase);
					} catch (SQLException e) {
						rtnpurchase.setRtnCd("6");
						rtnpurchase.setRtnMsg("포인트 예약 에러입니다."+ e) ;
						return rtnpurchase;	
					}
					rtnpurchase.setReservePointAmt(Math.abs(restAmt));

				}
				//POINT 예약
			}else{ 
				String pointId = "";
				try {
					pointId = (String)this.commonDAO.queryForObject("Purchase.getSeqReservePoint");

				} catch (SQLException e1) {
					rtnpurchase.setRtnCd("5");
					rtnpurchase.setRtnMsg("포인트아이디 채번 에러입니다."+ e1) ;
					return rtnpurchase;
				}
				purchase.setPointId(pointId);
				purchase.setAmt(cashPrcAmt);
				try {
					this.commonDAO.insert("Purchase.reservePoint", purchase);
				} catch (SQLException e) {
					rtnpurchase.setRtnCd("6");
					rtnpurchase.setRtnMsg("포인트 예약 에러입니다."+ e) ;
					return rtnpurchase;	
				}
				rtnpurchase.setReservePointAmt(cashPrcAmt);
			}    		
		}
		rtnpurchase.setRtnCd("0000");
		return rtnpurchase;
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

	private static void logMsg(String resultCd, String resultMsg, String payMethod, String authdate, String authtime, String phoneNumber, String prodNm, String tid)	{
		String logMsg = resultCd + "^" + resultMsg + "^" + payMethod + "^" + authdate + "^" + authtime + "^" + phoneNumber + "^" + prodNm + "^" + tid;
		paymentLog.info(logMsg);
	}

}
