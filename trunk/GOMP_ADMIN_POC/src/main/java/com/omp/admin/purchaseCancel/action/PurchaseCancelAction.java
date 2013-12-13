/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 * CHJIN      2011. 3. 10.    Description
 *
 */
package com.omp.admin.purchaseCancel.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.omp.admin.purchaseCancel.model.PurchaseCancel;
import com.omp.admin.purchaseCancel.service.PurchaseCancelService;
import com.omp.admin.purchaseCancel.service.PurchaseCancelServiceImpl;
import com.omp.commons.action.BaseAction;
import com.omp.commons.util.GLogger;

/**
 * TODO 클래스 설명을 입력해 주세요.
 * <p/>
 * 상세한 설명을 입력해 주세요.
 *
 * @author  윤경철
 * @version 0.1
 */
public class PurchaseCancelAction extends BaseAction{
	private static final GLogger log = new GLogger(PurchaseCancelAction.class);
    private PurchaseCancelService purchaseCancelService;
    private PurchaseCancel purchaseCancel;
    
    private String userId;
    private String prchsId;
    private String prchsDtm;
    private String mbrNo;
    private String refundAmt = "";
    private String[] reqPrchsId = null;
    
    public PurchaseCancelAction(){
        purchaseCancelService = new PurchaseCancelServiceImpl();
        purchaseCancel    = new PurchaseCancel();
    }
    
    /**
     * 
     * TODO 구매취소
     * <P/>
     * 구매취소요청을 받는 Action
     *
     * @return
     */
    public void purchaseCancel(){
        
    	this.setStep("RequestSetting");
    	
        HttpServletRequest request = this.req;
        String PRODID[] = null;
        
        log.info("취소요청 시작되었음");
        log.info("구매취소일자 조회  : {0}", new Object[] { prchsDtm});
        log.info("사용자ID  : {0}", new Object[] {userId});
        
        purchaseCancel.setPrchsDtm(prchsDtm);
        purchaseCancel.setUserId(userId);
        purchaseCancel.setPrchsId(prchsId);// 한준호 추가
        
        this.setPrimaryKey("ACTOR", userId);
        this.setPrimaryKey("P_ID", prchsId);
        this.setStep("CallServicePurchaseCancel");
        
        boolean result = purchaseCancelService.purchaseCancel(purchaseCancel);
        
        JSONObject jsonObject = new JSONObject();
        HttpServletResponse response = this.res;
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter writer = null;
		String resultString = "";
		
		this.setStep("ReturnResult");
		
        if(result == true){
            purchaseCancel.setResult("00");
            resultString = "SUCCESS";
            //return "CANCELDONE";
        } else {
        	purchaseCancel.setResult("01");
        	resultString = "FAIL";
        	//return "CANCELDONE";
        }
        
        try {
			jsonObject.put("data", resultString);
			writer = response.getWriter();
			writer.write(jsonObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally { if(writer != null) { writer.close(); } }
    }
    
    /**
     * 
     * TODO 복수건 구매취소
     * <P/>
     * 복수건 구매취소를 위한 Action
     *
     * @return
     */
    public void purchaseCancelMulti(){
        
        HttpServletRequest request = this.req;
        String PRODID[] = null;
        
        log.info("Multi 취소요청 시작되었음");
        
        if(log.isDebugEnabled()) {
	        for(String reqPrchsId : this.getReqPrchsId()) {
	        	log.debug("Purchase Cancel Param ReqPrchsId  : " +  reqPrchsId.toString());
	        }
        }
        
        purchaseCancel.setType("MULTI");
        purchaseCancel.setReqPrchsIdMulti(this.getReqPrchsId());
        
        boolean result = purchaseCancelService.purchaseCancelMulti(purchaseCancel);
        
        JSONObject jsonObject = new JSONObject();
        HttpServletResponse response = this.res;
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter writer = null;
		String resultString = "";
        
        if(result == true){
            purchaseCancel.setResult("05");
            resultString = "SUCCESS";
        } else {
        	purchaseCancel.setResult("06");
            resultString = "FAIL";
        }
        
        try {
			jsonObject.put("data", resultString);
			writer = response.getWriter();
			writer.write(jsonObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally { if(writer != null) { writer.close(); } }
    }
    
    /**
     * 
     * TODO Whoopy Cash 환불
     * <P/>
     * Whoopy Cash 환불 Action
     *
     * @return
     */
    public String purchaseRefundCash(){
        
    	this.setStep("RequestSetting");
        HttpServletRequest request = this.req;
        String PRODID[] = null;
        
        log.info("Cahs 환불 요청 시작되었음");
        this.setMbrNo(request.getParameter("mbrNo").toString());
        this.setRefundAmt(request.getParameter("refundAmt").toString());
        log.info("Member No  : {0}", new Object[] {this.getMbrNo()});
        log.info("Refund Amt  : {0}", new Object[] {this.getRefundAmt()});
        
        purchaseCancel.setMbrNo(this.getMbrNo());
        purchaseCancel.setRefundAmt(this.getRefundAmt());
        
        this.setStep("CallServicePurchaseRefund");
        boolean result = purchaseCancelService.purchaseRefund(purchaseCancel);
        
        if(result == true){
            purchaseCancel.setResult("03");
            return "CANCELDONE";
        }   
        purchaseCancel.setResult("04");
        return "CANCELDONE";
    }
    
    /*
     * getter/setter
     */    
    public PurchaseCancel getPurchaseCancel() {return purchaseCancel;}    
    public void setPurchaseCancel(PurchaseCancel purchaseCancel) {this.purchaseCancel = purchaseCancel;}

    public String getUserId() {
        return userId;
    }

    public void setUserId( String userId ) {
        this.userId = userId;
    }

    public String getPrchsId() {
		return prchsId;
	}

	public void setPrchsId(String prchsId) {
		this.prchsId = prchsId;
	}

	public String getPrchsDtm() {
        return prchsDtm;
    }

    public void setPrchsDtm( String prchsDtm ) {
        this.prchsDtm = prchsDtm;
    }

    public String getMbrNo() {
        return mbrNo;
    }

    public void setMbrNo( String mbrNo ) {
        this.mbrNo = mbrNo;
    }

    public String getRefundAmt() {
        return refundAmt;
    }

    public void setRefundAmt( String refundAmt ) {
        this.refundAmt = refundAmt;
    }

    public String[] getReqPrchsId() {
        return reqPrchsId;
    }

    public void setReqPrchsId( String[] reqPrchsId ) {
        this.reqPrchsId = reqPrchsId;
    }
    
    

}

