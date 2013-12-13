/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 * 윤경철            2009. 5. 6.    Description
 *
 */
package com.omp.admin.purchaseCancel.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibatis.dao.client.DaoManager;
import com.omp.admin.cash.service.CashServiceImpl;
import com.omp.admin.purchaseCancel.model.PurchaseCancel;
import com.omp.admin.purchaseCancel.persistence.dao.PurchaseCancelDAO;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.payment.ApprovalLimitOverflowException;
import com.omp.commons.payment.InvalidPaymentInfoException;
import com.omp.commons.payment.PaymentConnectFailException;
import com.omp.commons.payment.PaymentDenyException;
import com.omp.commons.payment.PaymentFailException;
import com.omp.commons.payment.PaymentProcessor;
import com.omp.commons.payment.creditcard.CreditCardPayment;
import com.omp.commons.payment.phonebill.PhoneBillPayment;
import com.omp.commons.persistence.dao.DaoConfig;
import com.omp.commons.service.AbstractService;
import com.omp.commons.text.LocalizeMessage;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.config.ConfigProperties;

/**
 * TODO 결제취소 Service 구현.
 * <p/>
 * 결제취소 Service 구현
 *
 * @author  윤경철
 * @version 0.1
 */
public class PurchaseCancelServiceImpl extends AbstractService implements PurchaseCancelService {

	private static final GLogger log = new GLogger(PurchaseCancelServiceImpl.class);
	private static final Log paymentLog = LogFactory.getLog("payment");
	
	//결제취소 로그 생성
	ConfigProperties prop = new ConfigProperties();  
	private String date = getSysDate("yyyyMMddHHmmssSSS");
	
	SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMddHHmmss");
	
	private PurchaseCancelDAO purchaseCancelDAO;
	DaoManager daoMgr = null;

	public PurchaseCancelServiceImpl(){
		daoMgr = DaoConfig.getDaoManager();
		this.purchaseCancelDAO = (PurchaseCancelDAO) daoMgr.getDao(PurchaseCancelDAO.class);
	}

	public boolean purchaseCancel(PurchaseCancel purchaseCancel) throws ServiceException {
		
		this.setStep("cancelFlag");
		
		boolean cancelFlag = true; //취소성공여부 Flag
		String cancelDtm = "";     //취소일자
		String resultCash = "";    //Cash 취소결과
		List list = null;
		
		this.setStep("SearchTid");
		String result = purchaseCancelDAO.searchTid(purchaseCancel);	// 구매ID 조회

		if(result == "NOK") {//거래번호가 존재하지 않는다면
			purchaseCancel.setResultMsg(LocalizeMessage.getLocalizedMessage("거래내역 존재하지 않습니다. 구매내역 확인"));  //결과메세지
			return false;
		}
		
		this.setStep("getPurchaseCancelList");
		list = purchaseCancel.getPurchaseCancelList();
		
		// 결제방법별 취소요청을 한다.
		for(int i=0; i<list.size(); i++){
			if(cancelFlag == true) {
				purchaseCancel.setTid(((PurchaseCancel)list.get(i)).getTid());
				purchaseCancel.setPrchsId(((PurchaseCancel)list.get(i)).getPrchsId());
				purchaseCancel.setPayMethod(((PurchaseCancel)list.get(i)).getPayMethod());
				purchaseCancel.setPrchsCls(((PurchaseCancel)list.get(i)).getPrchsCls());
				purchaseCancel.setHandsetNo(((PurchaseCancel)list.get(i)).getHandsetNo());
				purchaseCancel.setPayHandsetNo(((PurchaseCancel)list.get(i)).getPayHandsetNo());
				purchaseCancel.setProdId(((PurchaseCancel)list.get(i)).getProdId());
				purchaseCancel.setMoId(((PurchaseCancel)list.get(i)).getMoId());
				purchaseCancel.setTotPrice(((PurchaseCancel)list.get(i)).getTotPrice());
				purchaseCancel.setApplDate(((PurchaseCancel)list.get(i)).getApplDate());
				purchaseCancel.setApplTime(((PurchaseCancel)list.get(i)).getApplTime());

				if(purchaseCancel.getPayMethod().equals("OR000601")) { 
					//신용카드 결제취소
					this.setStep("cardCancel");
					try {
						cancelFlag = cardCancel(purchaseCancel);
					} catch(Exception e){
						e.printStackTrace();
					}
					
				} else if(purchaseCancel.getPayMethod().equals("OR000602")) { 
					//핸드폰 결제취소
					this.setStep("phoneCancel");
					purchaseCancel.setCnclDt(getSysDate("yyyyMMdd"));
					purchaseCancel.setCnclTm(getSysDate("HHmmss"));
					cancelFlag = true;
				}  else if(purchaseCancel.getPayMethod().equals("OR000607")) {  // 캐쉬 결제취소
					//캐쉬 구매 취소요청
					this.setStep("myCashCancel");
					resultCash = myCashCancel(purchaseCancel, "CASH");
					if("00".equals(resultCash)) {
						purchaseCancel.setResult("0000");
						purchaseCancel.setResultMsg("Cash Cancle Success");
					} else  {
						purchaseCancel.setResult("001");
						purchaseCancel.setResultMsg("Cash Cancle Fail");
					} 
					
					purchaseCancel.setCnclDt(getSysDate("yyyyMMdd"));
					purchaseCancel.setCnclTm(getSysDate("HHmmss"));
					
					cancelFlag = true; //rollback 기능 구현시 적용
					log.info("CASH=====================");
				} else if(purchaseCancel.getPayMethod().equals("OR000608")) {  // 포인트 결제취소
					//포인트 구매취소요청
					this.setStep("myPointCancel");
					resultCash = myCashCancel(purchaseCancel, "POINT");
					if("00".equals(resultCash)) {
						purchaseCancel.setResult("0000");
						purchaseCancel.setResultMsg("Point Cancel Success");
					} else  {
						purchaseCancel.setResult("001");
						purchaseCancel.setResultMsg("Point Cancel Fail");
					}
					
					purchaseCancel.setCnclDt(getSysDate("yyyyMMdd"));
					purchaseCancel.setCnclTm(getSysDate("HHmmss"));
					
					cancelFlag = true; //rollback 기능 구현시 적용
					log.info("POINT=====================");
				} else if(purchaseCancel.getPayMethod().equals("OR000698")) {  // 테스트 결제취소
					this.setStep("TestCancel");
					purchaseCancel.setResult("0000");
					purchaseCancel.setResultMsg("testMDN Cancle Success");
					
					purchaseCancel.setCnclDt(getSysDate("yyyyMMdd"));
					purchaseCancel.setCnclTm(getSysDate("HHmmss"));
					cancelFlag = true;
				} 
				
				//결제취소 로그 생성
				log.info("Purchase Cancel DATE : {0}", new Object[] {cancelDtm});
				logMsg(purchaseCancel.getResult(), purchaseCancel.getResultMsg(), "purchaseCacel", purchaseCancel.getHandsetNo(), purchaseCancel.getUserId(), cancelDtm, purchaseCancel.getProdId(), purchaseCancel.getTid());
				
			}
		}

		//구매취소내역 생성
		if(cancelFlag == true) {
			
			//구매취소내역 생성[구매취소일자, 구매상태]
			result = purchaseCancelDAO.createCancel(purchaseCancel);
			
			if(result == "NOK") {
				log.info("결제취소내역 생성에 실패하였습니다.");
				return false;
			}
			
		} else {
			if("".equals(purchaseCancel.getResultMsg())) purchaseCancel.setResultMsg(LocalizeMessage.getLocalizedMessage("정상 취소작업이 이루어지지 않았습니다."));
		}

		return cancelFlag;
	}

	public boolean purchaseCancelMulti(PurchaseCancel purchaseCancel) throws ServiceException {
		// TODO Auto-generated method stub
		String purchaseIdArray[] = null;
		String prePurchaseId = "";
		boolean cancelResult = false;
		//purchaseIdArray = purchaseCancel.getReqPrchsId().split(",");
		purchaseIdArray = purchaseCancel.getReqPrchsIdMulti();

		for(int i=0; i < purchaseIdArray.length;i++){
			log.info("[PurchaseCancel Multi Cancel] Purchase List : {0}", new Object[] {purchaseIdArray[i]});
			if(purchaseIdArray.length == 0) {
				purchaseCancel.setResultMsg(LocalizeMessage.getLocalizedMessage("취소요청 대상이 존재하지 않습니다. 구매내역 확인 후 재요청 하시기 바랍니다."));
				return false;
			}
			else if(i > 0 && prePurchaseId.equals(purchaseIdArray[i])){
				log.info("[Purchase Cancel] PurchaseId Pair");
			}
			else if("".equals(purchaseIdArray[i])){
				log.info("[Purchase Cancel] PurchaseId NULL");
			}
			else {
				purchaseCancel.setPrchsId(purchaseIdArray[i]);
				cancelResult = purchaseCancel(purchaseCancel);
				if(cancelResult == true) purchaseCancel.setSucCnt(purchaseCancel.getSucCnt()+1);
				else purchaseCancel.setFailCnt(purchaseCancel.getFailCnt()+1);
				try {
					Thread.sleep(100);
				} catch(Exception e){
					log.info("Multi Cancel Sleep Do Fail");
					e.printStackTrace();
				}
			}
			prePurchaseId = purchaseIdArray[i]; //복수구매 중복 구매ID 전달 시 생략기능
		}
		purchaseCancel.setTotalCnt(purchaseCancel.getSucCnt()+purchaseCancel.getFailCnt());
		log.info("Purhcase Cancel Success Count = {0}", new Object[] {purchaseCancel.getSucCnt()});
		log.info("Purhcase Cancel Fail Count = {0}", new Object[] {purchaseCancel.getFailCnt()});
		log.info("Purhcase Cancel Total Count = {0}", new Object[] {purchaseCancel.getTotalCnt()});

		return true;
	}

	public boolean cardCancel(PurchaseCancel purchaseCancel) throws ServiceException {
		
		try {
			CreditCardPayment cardPayment = new CreditCardPayment();
			cardPayment.setOrderId(purchaseCancel.getTid());
			
			cardPayment = PaymentProcessor.requestCreditCardApprovalCancel(cardPayment);

			String approvalDate = SDF.format(cardPayment.getApprovalDate()); 
			
			purchaseCancel.setResult("0000");
			purchaseCancel.setResultMsg("");  //결과메세지
			purchaseCancel.setCnclDt(approvalDate.substring(0, 8));    		//취소날짜
			purchaseCancel.setCnclTm(approvalDate.substring(8, 14));    		//취소시각
			
			return true;
			
		}catch(PaymentConnectFailException e){
			if(e.isApprovaled()){
				purchaseCancel.setResult("1111");
				purchaseCancel.setResultMsg(LocalizeMessage.getLocalizedMessage("이미 승인된 거래 입니다.") + e);
			}else{
				purchaseCancel.setResult("1111");
				purchaseCancel.setResultMsg(LocalizeMessage.getLocalizedMessage("결재 대행서버 접속 실패했습니다") + e);
			}	
			return false;
		}catch(PaymentFailException e){
			purchaseCancel.setResult("1111");
			purchaseCancel.setResultMsg(LocalizeMessage.getLocalizedMessage("결재 처리 시스템 오류입니다") + e);
			return false;
		}catch(ApprovalLimitOverflowException e){
			purchaseCancel.setResult(e.getVenderCode());
			purchaseCancel.setResultMsg(e.getVenderMessage());
			return false;
		}catch(InvalidPaymentInfoException e){
			purchaseCancel.setResult(e.getVenderCode());
			purchaseCancel.setResultMsg(e.getVenderMessage());
			return false;
		}catch(PaymentDenyException e){
			purchaseCancel.setResult(e.getVenderCode());
			purchaseCancel.setResultMsg(e.getVenderMessage());
			return false;
		}catch(UnsupportedOperationException e){
			purchaseCancel.setResult("1111");
			purchaseCancel.setResultMsg(LocalizeMessage.getLocalizedMessage("이 결재 방법을 지원하지 않습니다.") + e);
			return false;
		}
	}

	public boolean phoneCancel(PurchaseCancel purchaseCancel ) throws ServiceException {
		
		try {
			PhoneBillPayment phonepayment = new PhoneBillPayment();
			
			phonepayment.setOrderId(purchaseCancel.getTid());
			
			phonepayment = PaymentProcessor.requestPhoneBillApprovalCancel(phonepayment);
			
			String approvalDate = SDF.format(phonepayment.getApprovalDate()); 
			
			purchaseCancel.setResult("0000");
			purchaseCancel.setResultMsg("");  								//결과메세지
			purchaseCancel.setCnclDt(approvalDate.substring(0, 8));    		//취소날짜
			purchaseCancel.setCnclTm(approvalDate.substring(8, 14));    		//취소시각
			
			return true;
		}catch(PaymentConnectFailException e){
			purchaseCancel.setResult("1111");
			purchaseCancel.setResultMsg(LocalizeMessage.getLocalizedMessage("결재 대행서버 접속 실패했습니다") + e);
			return false;
		}catch(PaymentFailException e){
			purchaseCancel.setResult("1111");
			purchaseCancel.setResultMsg(LocalizeMessage.getLocalizedMessage("결재 처리 시스템 오류입니다") + e);
			return false;
		}catch(ApprovalLimitOverflowException e){
			purchaseCancel.setResult("1111");
			purchaseCancel.setResultMsg(LocalizeMessage.getLocalizedMessage("승인한도 초과되었습니다.") + e);
			return false;
		}catch(InvalidPaymentInfoException e){
			purchaseCancel.setResult("1111");
			purchaseCancel.setResultMsg(LocalizeMessage.getLocalizedMessage("카드정보 오류입니다.") + e);
			return false;
		}catch(PaymentDenyException e){
			purchaseCancel.setResult("1111");
			purchaseCancel.setResultMsg(LocalizeMessage.getLocalizedMessage("기타 승인거부입니다.") + e);
			return false;
		}catch(UnsupportedOperationException e){
			//대만 다날 결제 취소를 제공하질 않는다. 취소는 오프라인 취소
			purchaseCancel.setResult("0000");    //결과코드
			purchaseCancel.setResultMsg(LocalizeMessage.getLocalizedMessage("이 결재 방법을 지원하지 않습니다.") + e);
			purchaseCancel.setCnclDt(date.substring(0, 8));   					
			purchaseCancel.setCnclTm(date.substring(8, 14));
			return true;
		}
	}

	/**
	 * 
	 * TODO My Cash Refund
	 * <P/>
	 * Whoopy Cash 환불처리를 한다
	 *
	 * @return
	 * @throws Exception
	 */
	public boolean purchaseRefund(PurchaseCancel purchaseCancel) throws ServiceException {
		// TODO Auto-generated method stub
		boolean cancelFlag = true; //취소성공여부 Flag
		String cancelDtm = getSysDate("yyyyMMddHHmmss");     //취소일자
		String resultCash = "";    //Cash 취소결과
		String logMsg = "";

		//Whoopy Cash 잔여량 조회
		int myCashRemain = myCashSearch(purchaseCancel.getMbrNo());
		if(myCashRemain <= 0) { 
			purchaseCancel.setResultMsg(LocalizeMessage.getLocalizedMessage("환불 가능한 Whoopy Cash 잔여량이  없습니다."));
			return false;
		} else if (myCashRemain < Integer.parseInt(purchaseCancel.getRefundAmt())){
			purchaseCancel.setResultMsg(LocalizeMessage.getLocalizedMessage("요청하신 환불 가능한 Whoopy Cash 잔여량이 부족합니다."));
			return false;
		} 

		//구매취소내역 생성
		purchaseCancel.setCnclDt(cancelDtm);
		purchaseCancel.setPayMethod("OR000607"); 		//환불일 경우 결제방법과 상관없이 캐쉬결제 취소로 처리한다.
		purchaseCancel.setPrchsId("REFUND"+cancelDtm);  //특정대상에 대한 환불이 아니기 때문에 임의의 주문번호 생성

		//Whoopy Cash 환불요청
		String refundResult = cashRefund(purchaseCancel);

		if("00".equals(refundResult)) {
			cancelFlag = true;
			purchaseCancel.setResult("0000");
			purchaseCancel.setResultMsg("Whoopy Cash Refund Success");
		} else {
			cancelFlag = false;
			purchaseCancel.setResult("001");
			purchaseCancel.setResultMsg("Whoopy Cash Refund Fail");
		}

		log.info("Purchase Cancel DATE : {0}", new Object[] {cancelDtm});
		
		logMsg(purchaseCancel.getResult(), purchaseCancel.getResultMsg(), "cashCacel", purchaseCancel.getHandsetNo(), purchaseCancel.getUserId(), cancelDtm, purchaseCancel.getProdId(), purchaseCancel.getTid());

		return cancelFlag;
	}

	/**
	 * 
	 * TODO My Cash Cancel
	 * <P/>
	 * Cash 사용취소를 한다
	 *
	 * @return
	 * @throws Exception
	 */
	public String myCashCancel(PurchaseCancel purchaseCancel, String area) throws ServiceException {
		Map reqMap = new HashMap();
		Map resMap = new HashMap();
		try {
			reqMap.put("MBR_NO", purchaseCancel.getMbrNo());
			reqMap.put("PRCHS_ID", purchaseCancel.getPrchsId());
			reqMap.put("AMT", purchaseCancel.getTotPrice());
			reqMap.put("TYPE", area);  //POINT OR CASH

			//Cash 사용취소로 인해 구매취소로 인해 캐시 발생(충전요청)
			CashServiceImpl service = new CashServiceImpl();
			resMap = service.cancel(reqMap);
			log.info("Cash/Point Use Cancel(Recharge) Result : {0}", new Object[] {resMap.get("ERROR_CODE").toString()});
		} catch (Exception e){
			e.printStackTrace();
		}
		return resMap.get("ERROR_CODE").toString();
	}

	/**
	 * 
	 * TODO Whoopy Cash Remain Search
	 * <P/>
	 * Cash 사용 가능 잔여량을 조회
	 *
	 * @return
	 * @throws Exception
	 */
	public int myCashSearch(String mbrNo) throws ServiceException {

		Map reqMap = new HashMap();
		Map resMap = new HashMap();
		
		reqMap.put("MBR_NO", mbrNo);

		//이용가능금액 호출
		CashServiceImpl service = new CashServiceImpl();
		log.debug("mbrNo : " + mbrNo );
		int availCashAmt = service.getTotalAvailAmtCash(reqMap);
		log.debug("availCashAmt : " + availCashAmt );
		log.info("Cash Total Avail Amount Result : {0}", new Object[] {availCashAmt});

		return availCashAmt;
	}

	/**
	 * 
	 * TODO Cash Charge Cancel Reserve
	 * <P/>
	 * Cash 충전취소 예약을 한다
	 *
	 * @return
	 * @throws Exception
	 */
	public Map cashCancelReserve(String mbrNo, String applyCashAmt) throws ServiceException {

		Map reqMap = new HashMap();
		Map resMap = new HashMap();
		reqMap.put("MBR_NO", mbrNo);
		reqMap.put("PRC_AMT", applyCashAmt);

		//Cash 사용취소(충전요청)
		CashServiceImpl service = new CashServiceImpl();
		resMap = service.reserveChargeCancel(reqMap);
		if(resMap == null){
			log.info("===================Cash Charge Cancel Reserve Before Use PARAMETER ERROR===================");
			resMap.put("CASH_ID", "");
			resMap.put("CASH_AMT", 0);
		}
		log.info("Cash Charge Cancel Reserve Result : {0}", new Object[] {resMap.get("ERROR_CODE")});

		return resMap;
	}

	/**
	 * 
	 * TODO Cash Reserve Cancel
	 * <P/>
	 * Cash 충전취소 예약 취소를 한다
	 *
	 * @return
	 * @throws Exception
	 */
	public String cashReserveCancel(PurchaseCancel purchaseCancel, String area) throws ServiceException {
		Map reqMap = new HashMap();
		Map resMap = new HashMap();
		try {
			reqMap.put("CASH_ID", purchaseCancel.getCashId());
			reqMap.put("TYPE", area);  //POINT OR CASH

			//Cash 사용취소(충전요청)
			CashServiceImpl service = new CashServiceImpl();
			resMap = service.cancelReserve(reqMap);
			log.info("Cash Cancel Reserve Cancel Result : {0}", new Object[] {resMap.get("ERROR_CODE").toString()});
		} catch (Exception e){
			e.printStackTrace();
		}
		return resMap.get("ERROR_CODE").toString();
	}

	/**
	 * 
	 * TODO Cash Reserve Cancel
	 * <P/>
	 * Cash 충전취소 요청을 한다
	 *
	 * @return
	 * @throws Exception
	 */
	public String cashChargeCancel(PurchaseCancel purchaseCancel) throws ServiceException {
		Map reqMap = new HashMap();
		Map resMap = new HashMap();
		reqMap.put("MBR_NO", purchaseCancel.getMbrNo());
		reqMap.put("PRCHS_ID", purchaseCancel.getPrchsId());
		reqMap.put("CASH_ID", purchaseCancel.getCashId());

		CashServiceImpl service = new CashServiceImpl();
		resMap = service.useChargeCancel(reqMap);
		log.info("Cash Charge Cancel Result : {0}", new Object[] {resMap.get("ERROR_CODE").toString()});

		return resMap.get("ERROR_CODE").toString();
	}

	/**
	 * 
	 * TODO Cash Refund Request
	 * <P/>
	 * Cash 환불 요청을 한다
	 *
	 * @return
	 * @throws Exception
	 */
	public String cashRefund(PurchaseCancel purchaseCancel) throws ServiceException {
		Map reqMap = new HashMap();
		Map resMap = new HashMap();
		try {
			reqMap.put("MBR_NO", purchaseCancel.getMbrNo());
			reqMap.put("PRCHS_ID", purchaseCancel.getPrchsId());
			reqMap.put("PRC_AMT", purchaseCancel.getRefundAmt());

			//Cash 사용취소(충전요청)
			CashServiceImpl service = new CashServiceImpl();
			resMap = service.refund(reqMap);
			log.info("Cash refund Result : {0}", new Object[] {resMap.get("ERROR_CODE").toString()});
		} catch (Exception e){
			e.printStackTrace();
		}
		return resMap.get("ERROR_CODE").toString();
	}


	private static void writeLog(String direct, String msg)
	{

		BufferedWriter file = null;

		String  path  = direct + "/pg_if_cancel"+getSysDate("yyyyMMdd");

		makeDirectory(direct);

		try
		{
			file = new BufferedWriter(new FileWriter(path, true));
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}

		try
		{
			try
			{
				file.write(getSysDate("yyyy-MM-dd HH:mm:ss\t"));
				file.write(msg, 0, msg.length());
				file.newLine();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				file.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	private static String getSysDate( String pattern )
	{
		String result = "";

		Calendar cal = Calendar.getInstance();

		result  = getFormatString(pattern,  cal);
		return result;
	}

	private static String getFormatString(String pattern, Calendar cal)
	{
		java.text.SimpleDateFormat formatter =  new java.text.SimpleDateFormat(pattern);
		String dateString = formatter.format(cal.getTime());
		return dateString;
	}

	private static boolean makeDirectory(String path)
	{
		boolean isSuccess = false;

		File directory = new File(path);

		if(directory.exists() == false)
		{
			directory.mkdirs();
			isSuccess = true;
		} else isSuccess = true;

		return isSuccess;
	}
	
	private static void logMsg(String resultCd, String resultMsg, String payMethod, String phoneNumber, String userid, String canceldt,String prodid, String tid)	{
		String logMsg = resultCd + "^" + resultMsg + "^" + payMethod + "^" + phoneNumber + "^" + userid + "^" + canceldt + "^" + prodid + "^" + tid ;
		paymentLog.info(logMsg);
	}

}
