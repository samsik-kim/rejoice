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
package com.omp.admin.purchaseCancel.model;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO 클래스 설명을 입력해 주세요.
 * <p/>
 * 상세한 설명을 입력해 주세요.
 *
 * @author  윤경철
 * @version 0.1
 */
public class PurchaseCancel {
    private String userId = "";    //사용자ID 
    private String mbrNo = "";     //회원번호 
    private String handsetNo = ""; //핸드폰번호
    private String payHandsetNo = ""; //구매핸드폰번호
    private String prchsDtm = "";  //구매일시
    private String payMethod = ""; //결제방법
    private String prchsCls = "";  //결제경로
    private String cnclRsn = "";   //취소사유
    private String prodId = "";    //상품ID
    private String tid = "";       //거래번호
    private String result = "";    //결제결과코드
    private String resultMsg = ""; //결제결과메세지
    private String cnclDt = "";    //취소일자
    private String cnclTm = "";    //취소시간
    private String prchsId = "";   //구매ID
    private String moId = "";	  //상점주문번호
    private String totPrice = "";  //결제결과금액
    private String chnnelId = "";  //채널ID
    private String prcAmt = "";    //상품금액
    private String cnclArea = "";
    private String remainCash = ""; //잔여Cash
    private String cashId = "";     //Cash ID
    private String cashAmt = "";    //캐쉬 예약 금액
    private String refundAmt = "";  //환불캐쉬금액
    private String reqPrchsId = ""; //구매취소요청 구매ID
    private String[] reqPrchsIdMulti; //구매취소요청 구매ID multi
    private int sucCnt = 0;         //취소 성공건수
    private int failCnt = 0;        //취소 실패건수
    private int totalCnt = 0;       //취소요청 총 건수
    private String type = "ONE";    //단/복수 구매취소Type
    private String applDate ="";
    private String applTime = "";
    
    private String systemDivCncl = "028V";
    private String dcmfPid = "9000511306";
    private String chargePivot = "1";
    
    private List purchaseCancelList = new ArrayList<PurchaseCancel>();   //복합결제리스트
    
    public String getUserId   () {return userId;}
    public void setUserId    (String userId) {this.userId    = userId;}

    public String getMbrNo() {
        return mbrNo;
    }
    public void setMbrNo( String mbrNo ) {
        this.mbrNo = mbrNo;
    }
    public String getHandsetNo() {return handsetNo;}
    public void setHandsetNo (String handsetNo) {this.handsetNo = handsetNo;}

    public String getPayHandsetNo() {return payHandsetNo;}
    public void setPayHandsetNo (String payHandsetNo) {this.payHandsetNo = payHandsetNo;}

    public String getPrchsDtm () {return prchsDtm;}
    public void setPrchsDtm  (String prchsDtm) {this.prchsDtm  = prchsDtm;}

    public String getPayMethod() {return payMethod;}
    public void setPayMethod (String payMethod) {this.payMethod = payMethod;}

    
    public String getPrchsCls() {
        return prchsCls;
    }
    public void setPrchsCls( String prchsCls ) {
        this.prchsCls = prchsCls;
    }
    
    public String getCnclRsn  () {return cnclRsn;}
    public void setCnclRsn   (String cnclRsn) {this.cnclRsn   = cnclRsn;}
    
    public String getProdId  () {return prodId;}
    public void setProdId   (String prodId) {this.prodId   = prodId;}
    
    public String getTid      () {return tid;}
    public void setTid       (String tid) {this.tid       = tid;}

    public String getResult   () {return result;}
    public void setResult    (String result) {this.result    = result;}

    public String getResultMsg() {return resultMsg;}
    public void setResultMsg (String resultMsg) {this.resultMsg = resultMsg;}

    public String getCnclDt   () {return cnclDt;}
    public void setCnclDt    (String cnclDt) {this.cnclDt    = cnclDt;}

    public String getCnclTm   () {return cnclTm;}
    public void setCnclTm    (String cnclTm) {this.cnclTm    = cnclTm;}

    public String getPrchsId   () {return prchsId;}
    public void setPrchsId    (String prchsId) {this.prchsId    = prchsId;}
    
    public String getMoId   () {return moId;}
    public void setMoId    (String moId) {this.moId    = moId;}

    public String getTotPrice   () {return totPrice;}
    public void setTotPrice    (String totPrice) {this.totPrice    = totPrice;}
    
    public String getChnnelId() {
        return chnnelId;
    }
    public void setChnnelId( String chnnelId ) {
        this.chnnelId = chnnelId;
    }
    
    public String getCnclArea() {
        return cnclArea;
    }
    public void setCnclArea( String cnclArea ) {
        this.cnclArea = cnclArea;
    }
    public List getPurchaseCancelList() {
        return purchaseCancelList;
    }
    public void setPurchaseCancelList( PurchaseCancel purchaseCancel ) {
        this.purchaseCancelList.add(purchaseCancel);
    }
    public void purchaseCancelListRemove() {
        this.purchaseCancelList.removeAll(getPurchaseCancelList());//.add(purchaseCancel);
    }
    public String getSystemDivCncl() {
        return systemDivCncl;
    }
    public void setSystemDivCncl( String systemDivCncl ) {
        this.systemDivCncl = systemDivCncl;
    }
    public String getDcmfPid() {
        return dcmfPid;
    }
    public void setDcmfPid( String dcmfPid ) {
        this.dcmfPid = dcmfPid;
    }
    public String getPrcAmt() {
        return prcAmt;
    }
    public void setPrcAmt( String prcAmt ) {
        this.prcAmt = prcAmt;
    }
    public String getRemainCash() {
        return remainCash;
    }
    public void setRemainCash( String remainCash ) {
        this.remainCash = remainCash;
    }
    public String getCashId() {
        return cashId;
    }
    public void setCashId( String cashId ) {
        this.cashId = cashId;
    }
    public String getCashAmt() {
        return cashAmt;
    }
    public void setCashAmt( String cashAmt ) {
        this.cashAmt = cashAmt;
    }
    public String getRefundAmt() {
        return refundAmt;
    }
    public void setRefundAmt( String refundAmt ) {
        this.refundAmt = refundAmt;
    }
    public String getChargePivot() {
        return chargePivot;
    }
    public void setChargePivot( String chargePivot ) {
        this.chargePivot = chargePivot;
    }
    public String getReqPrchsId() {
        return reqPrchsId;
    }
    public void setReqPrchsId( String reqPrchsId ) {
        this.reqPrchsId = reqPrchsId;
    }
    public String[] getReqPrchsIdMulti() {
		return reqPrchsIdMulti;
	}
	public void setReqPrchsIdMulti(String[] reqPrchsIdMulti) {
		this.reqPrchsIdMulti = reqPrchsIdMulti;
	}
	public int getSucCnt() {
        return sucCnt;
    }
    public void setSucCnt( int sucCnt ) {
        this.sucCnt = sucCnt;
    }
    public int getFailCnt() {
        return failCnt;
    }
    public void setFailCnt( int failCnt ) {
        this.failCnt = failCnt;
    }
    public int getTotalCnt() {
        return totalCnt;
    }
    public void setTotalCnt( int totalCnt ) {
        this.totalCnt = totalCnt;
    }
    public String getType() {
        return type;
    }
    public void setType( String type ) {
        this.type = type;
    }
    public String getApplDate() {
        return applDate;
    }
    public void setApplDate( String applDate ) {
        this.applDate = applDate;
    }
    public String getApplTime() {
        return applTime;
    }
    public void setApplTime( String applTime ) {
        this.applTime = applTime;
    }
    
}
