package com.omp.dev.contents.model;

import com.omp.commons.model.CommonModel;

public class TestPhoneSet extends CommonModel {

	private String mbrNo;
	private String tmpSeq;
	private String macAddr;
	private String regId;
	private String regDttm;
	
	public String getMbrNo() {
		return mbrNo;
	}
	public void setMbrNo(String mbrNo) {
		this.mbrNo = mbrNo;
	}
	public String getTmpSeq() {
		return tmpSeq;
	}
	public void setTmpSeq(String tmpSeq) {
		this.tmpSeq = tmpSeq;
	}
	public String getMacAddr() {
		return macAddr;
	}
	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
}
