package com.omp.admin.cash.model;

import com.omp.commons.model.TotalCountCarriable;

public class Cash implements TotalCountCarriable {
	
	//cashlist
	private String cashId;
	private String mbrNo;
	private String occrStat;
	private String opType;
    private String occrAmt;
    private String occrDt;
    private String extnSchdPeriod;
    private String mbrNm;
    private String mbrId;
    private String relateMbrId;
    
    //chargeList
    private String prchsDt;
    private String prchsAmt;
    private String cashAmt;
    
    //chargeDetailList
    private String mbrIdNm;
    private String atmAmt;
    private String adminAmt;
    private String applyCancel;
    
    //cancelList
    private String cancelCnt;
    
    //refund
    private String cashTotAmt;
    
    //cacelDetailList
    private String hpNo;
    private String payCls;
    
    //ChrgList
    private String chrgCashId; 
    private String chrgCashNm; 
    private String cashType;
    private String useYn; 
    private String regId; 
    private String regDt; 
    private String updtId;
    private String updtDt;
    private String bonusRatio;
    private String prebonusRatio;
    private String occrNo;
    
	public String getCashId() {
		return cashId;
	}

	public void setCashId(String cashId) {
		this.cashId = cashId;
	}

	public String getMbrNo() {
		return mbrNo;
	}

	public void setMbrNo(String mbrNo) {
		this.mbrNo = mbrNo;
	}

	public String getOccrStat() {
		return occrStat;
	}

	public void setOccrStat(String occrStat) {
		this.occrStat = occrStat;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public String getOccrAmt() {
		return occrAmt;
	}

	public void setOccrAmt(String occrAmt) {
		this.occrAmt = occrAmt;
	}

	public String getOccrDt() {
		return occrDt;
	}

	public void setOccrDt(String occrDt) {
		this.occrDt = occrDt;
	}

	public String getExtnSchdPeriod() {
		return extnSchdPeriod;
	}

	public void setExtnSchdPeriod(String extnSchdPeriod) {
		this.extnSchdPeriod = extnSchdPeriod;
	}

	public String getMbrNm() {
		return mbrNm;
	}

	public void setMbrNm(String mbrNm) {
		this.mbrNm = mbrNm;
	}

	public String getMbrId() {
		return mbrId;
	}

	public void setMbrId(String mbrId) {
		this.mbrId = mbrId;
	}

	public String getRelateMbrId() {
		return relateMbrId;
	}

	public void setRelateMbrId(String relateMbrId) {
		this.relateMbrId = relateMbrId;
	}
    
	public String getPrchsDt() {
		return prchsDt;
	}

	public void setPrchsDt(String prchsDt) {
		this.prchsDt = prchsDt;
	}

	public String getPrchsAmt() {
		return prchsAmt;
	}

	public void setPrchsAmt(String prchsAmt) {
		this.prchsAmt = prchsAmt;
	}

	public String getCashAmt() {
		return cashAmt;
	}

	public void setCashAmt(String cashAmt) {
		this.cashAmt = cashAmt;
	}

	public String getMbrIdNm() {
		return mbrIdNm;
	}

	public void setMbrIdNm(String mbrIdNm) {
		this.mbrIdNm = mbrIdNm;
	}

	public String getAdminAmt() {
		return adminAmt;
	}

	public void setAdminAmt(String adminAmt) {
		this.adminAmt = adminAmt;
	}

	public String getApplyCancel() {
		return applyCancel;
	}

	public void setApplyCancel(String applyCancel) {
		this.applyCancel = applyCancel;
	}

	
	public String getAtmAmt() {
		return atmAmt;
	}

	public void setAtmAmt(String atmAmt) {
		this.atmAmt = atmAmt;
	}
	
	public String getCancelCnt() {
		return cancelCnt;
	}

	public void setCancelCnt(String cancelCnt) {
		this.cancelCnt = cancelCnt;
	}
	
	public String getCashTotAmt() {
		return cashTotAmt;
	}

	public void setCashTotAmt(String cashTotAmt) {
		this.cashTotAmt = cashTotAmt;
	}

	public String getHpNo() {
		return hpNo;
	}

	public void setHpNo(String hpNo) {
		this.hpNo = hpNo;
	}

	public String getPayCls() {
		return payCls;
	}

	public void setPayCls(String payCls) {
		this.payCls = payCls;
	}
	
	public String getChrgCashId() {
		return chrgCashId;
	}

	public void setChrgCashId(String chrgCashId) {
		this.chrgCashId = chrgCashId;
	}

	public String getChrgCashNm() {
		return chrgCashNm;
	}

	public void setChrgCashNm(String chrgCashNm) {
		this.chrgCashNm = chrgCashNm;
	}

	public String getCashType() {
		return cashType;
	}

	public void setCashType(String cashType) {
		this.cashType = cashType;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getUpdtId() {
		return updtId;
	}

	public void setUpdtId(String updtId) {
		this.updtId = updtId;
	}

	public String getUpdtDt() {
		return updtDt;
	}

	public void setUpdtDt(String updtDt) {
		this.updtDt = updtDt;
	}

	public String getBonusRatio() {
		return bonusRatio;
	}

	public void setBonusRatio(String bonusRatio) {
		this.bonusRatio = bonusRatio;
	}

	public String getPrebonusRatio() {
		return prebonusRatio;
	}

	public void setPrebonusRatio(String prebonusRatio) {
		this.prebonusRatio = prebonusRatio;
	}

	public String getOccrNo() {
		return occrNo;
	}

	public void setOccrNo(String occrNo) {
		this.occrNo = occrNo;
	}




	/*
	 * PAGING REFER
	 */
	private int rnum;
	// paging
	private Long total_count;
	
	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	
	public Long getTotal_count() {
		return total_count;
	}

	public void setTotal_count(Long total_count) {
		this.total_count = total_count;
	}
	
	// baseInfo 

	@Override
	public Long getTotalCount() {
		return this.total_count;
	}

	@Override
	public void setTotalCount(Long totalCount) {
		this.total_count = totalCount;
	}
	
}
