package com.omp.admin.community.postscript.model;

import java.io.Serializable;

import com.omp.commons.model.CommonModel;

@SuppressWarnings("serial")
public class UsMember extends CommonModel implements Serializable {

	private String mbrNo;
	private String mbrClsCd;
	private String mbrCatCd;
	private String mbrStatCd;
	private String mbrId;
	private String mbrNm;
	private String emailAddr;

	public String getMbrNo() {
		return mbrNo;
	}

	public void setMbrNo(String mbrNo) {
		this.mbrNo = mbrNo;
	}

	public String getMbrClsCd() {
		return mbrClsCd;
	}

	public void setMbrClsCd(String mbrClsCd) {
		this.mbrClsCd = mbrClsCd;
	}

	public String getMbrCatCd() {
		return mbrCatCd;
	}

	public void setMbrCatCd(String mbrCatCd) {
		this.mbrCatCd = mbrCatCd;
	}

	public String getMbrStatCd() {
		return mbrStatCd;
	}

	public void setMbrStatCd(String mbrStatCd) {
		this.mbrStatCd = mbrStatCd;
	}

	public String getMbrId() {
		return mbrId;
	}

	public void setMbrId(String mbrId) {
		this.mbrId = mbrId;
	}

	public String getMbrNm() {
		return mbrNm;
	}

	public void setMbrNm(String mbrNm) {
		this.mbrNm = mbrNm;
	}

	public String getEmailAddr() {
		return emailAddr;
	}

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getClass().toString());
		sb.append(" mbrNo='").append(mbrNo).append("'");
		sb.append(",mbrClsCd='").append(mbrClsCd).append("'");
		sb.append(",mbrCatCd='").append(mbrCatCd).append("'");
		sb.append(",mbrStatCd='").append(mbrStatCd).append("'");
		sb.append(",mbrId='").append(mbrId).append("'");
		sb.append(",mbrNm='").append(mbrNm).append("'");
		sb.append(",emailAddr='").append(emailAddr).append("'");
		sb.append("}");
		return sb.toString();
	}

}
