package com.omp.admin.cash.model;

import com.omp.admin.member.membermgr.model.PageModel;

public class ChrgCash  extends PageModel{
	
	private String chrgCashId;
	private String chrgCashNm;
	private int prchsAmt;
	private String bonusRatio;
	private int cashAmt;
	private String cashType;
	private String useYn;
	private String regId;
	private String regDt;
	private String updtId;
	private String updtDt;
	private String prebonusRatio;
	private String occrNo;
	
	private String startDate;
	private String endDate;
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getOccrNo() {
		return occrNo;
	}
	public void setOccrNo(String occrNo) {
		this.occrNo = occrNo;
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
	public int getPrchsAmt() {
		return prchsAmt;
	}
	public void setPrchsAmt(int prchsAmt) {
		this.prchsAmt = prchsAmt;
	}
	public String getBonusRatio() {
		return bonusRatio;
	}
	public void setBonusRatio(String bonusRatio) {
		this.bonusRatio = bonusRatio;
	}
	public int getCashAmt() {
		return cashAmt;
	}
	public void setCashAmt(int cashAmt) {
		this.cashAmt = cashAmt;
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
	public String getPrebonusRatio() {
		return prebonusRatio;
	}
	public void setPrebonusRatio(String prebonusRatio) {
		this.prebonusRatio = prebonusRatio;
	}
	
}
