package com.omp.admin.community.postscript.model;

import java.io.Serializable;

import com.omp.commons.model.CommonModel;
import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;

@SuppressWarnings("serial")
public class DpProdNoti extends CommonModel implements Pagenateable, Serializable {

	private long notiNo;
	private String notiTpCd;
	private String mbrNo;
	private String prodId;
	private String notiDscr;
	private String badnotiYn;
	private String delYn;
	private String regId;
	private String regDts;
	private String updDts;
	private String delDts;

	private String mbrNm;
	private String mbrId;
	private String prodNm;

	private String srchBadnotiYn;
	private String srchDelYn;

	private PagenateInfoModel page = new PagenateInfoModel(10);
	private Long totalCount;

	public PagenateInfoModel getPage() {
		return this.page;
	}

	public Long getTotalCount() {
		return this.totalCount;
	}

	public Long setTotalCount(Long totalCount) {
		return this.totalCount = totalCount;
	}

	public long getNotiNo() {
		return notiNo;
	}

	public void setNotiNo(long notiNo) {
		this.notiNo = notiNo;
	}

	public String getNotiTpCd() {
		return notiTpCd;
	}

	public void setNotiTpCd(String notiTpCd) {
		this.notiTpCd = notiTpCd;
	}

	public String getMbrNo() {
		return mbrNo;
	}

	public void setMbrNo(String mbrNo) {
		this.mbrNo = mbrNo;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getNotiDscr() {
		return notiDscr;
	}

	public void setNotiDscr(String notiDscr) {
		this.notiDscr = notiDscr;
	}

	public String getBadnotiYn() {
		return badnotiYn;
	}

	public void setBadnotiYn(String badnotiYn) {
		this.badnotiYn = badnotiYn;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getRegDts() {
		return regDts;
	}

	public void setRegDts(String regDts) {
		this.regDts = regDts;
	}

	public String getUpdDts() {
		return updDts;
	}

	public void setUpdDts(String updDts) {
		this.updDts = updDts;
	}

	public String getDelDts() {
		return delDts;
	}

	public void setDelDts(String delDts) {
		this.delDts = delDts;
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

	public String getProdNm() {
		return prodNm;
	}

	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
	}

	public String getSrchBadnotiYn() {
		return srchBadnotiYn;
	}

	public void setSrchBadnotiYn(String srchBadnotiYn) {
		this.srchBadnotiYn = srchBadnotiYn;
	}

	public String getSrchDelYn() {
		return srchDelYn;
	}

	public void setSrchDelYn(String srchDelYn) {
		this.srchDelYn = srchDelYn;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getClass().toString());
		sb.append(" notiNo=").append(notiNo);
		sb.append(",notiTpCd='").append(notiTpCd).append("'");
		sb.append(",mbrNo='").append(mbrNo).append("'");
		sb.append(",prodId='").append(prodId).append("'");
		sb.append(",notiDscr='").append(notiDscr).append("'");
		sb.append(",badnotiYn='").append(badnotiYn).append("'");
		sb.append(",delYn='").append(delYn).append("'");
		sb.append(",regId='").append(regId).append("'");
		sb.append(",regDts='").append(regDts).append("'");
		sb.append(",updDts='").append(updDts).append("'");
		sb.append(",delDts='").append(delDts).append("'");
		sb.append(",mbrNm='").append(mbrNm).append("'");
		sb.append(",mbrId='").append(mbrId).append("'");
		sb.append(",prodNm='").append(prodNm).append("'");
		sb.append("}");
		return sb.toString();
	}

}
