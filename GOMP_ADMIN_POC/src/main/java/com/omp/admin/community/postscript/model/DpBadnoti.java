package com.omp.admin.community.postscript.model;

import java.io.Serializable;
import java.util.Date;

import com.omp.commons.model.CommonModel;
import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;

@SuppressWarnings("serial")
public class DpBadnoti extends CommonModel implements Pagenateable, Serializable {

	private long badnotiNo;
	private long notiNo;
	private String badnotiRptCd;
	private String rptDscr;
	private String mbrNo;
	private String pnshClsCd;
	private String oldPnshClsCd;
	private String regId;
	private Date regDts;
	private String updId;
	private Date updDt;

	private String badnotiRptNm;
	private String regNm;
	private String regDttm;
	private String updNm;
	private String updDttm;

	private String notiDscr;
	private String notiRegDttm;

	private String mailTemplate1st;
	private String mailTemplate2nd;
	private String mailTemplate3rd;

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

	public long getBadnotiNo() {
		return badnotiNo;
	}

	public void setBadnotiNo(long badnotiNo) {
		this.badnotiNo = badnotiNo;
	}

	public long getNotiNo() {
		return notiNo;
	}

	public void setNotiNo(long notiNo) {
		this.notiNo = notiNo;
	}

	public String getMbrNo() {
		return mbrNo;
	}

	public void setMbrNo(String mbrNo) {
		this.mbrNo = mbrNo;
	}

	public String getBadnotiRptCd() {
		return badnotiRptCd;
	}

	public void setBadnotiRptCd(String badnotiRptCd) {
		this.badnotiRptCd = badnotiRptCd;
	}

	public String getRptDscr() {
		return rptDscr;
	}

	public void setRptDscr(String rptDscr) {
		this.rptDscr = rptDscr;
	}

	public String getPnshClsCd() {
		return pnshClsCd;
	}

	public void setPnshClsCd(String pnshClsCd) {
		this.pnshClsCd = pnshClsCd;
	}

	public String getOldPnshClsCd() {
		return oldPnshClsCd;
	}

	public void setOldPnshClsCd(String oldPnshClsCd) {
		this.oldPnshClsCd = oldPnshClsCd;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public Date getRegDts() {
		return regDts;
	}

	public void setRegDts(Date regDts) {
		this.regDts = regDts;
	}

	public String getUpdId() {
		return updId;
	}

	public void setUpdId(String updId) {
		this.updId = updId;
	}

	public Date getUpdDt() {
		return updDt;
	}

	public void setUpdDt(Date updDt) {
		this.updDt = updDt;
	}

	public String getBadnotiRptNm() {
		return badnotiRptNm;
	}

	public void setBadnotiRptNm(String badnotiRptNm) {
		this.badnotiRptNm = badnotiRptNm;
	}

	public String getUpdNm() {
		return updNm;
	}

	public void setUpdNm(String updNm) {
		this.updNm = updNm;
	}

	public String getRegNm() {
		return regNm;
	}

	public void setRegNm(String regNm) {
		this.regNm = regNm;
	}

	public String getRegDttm() {
		return regDttm;
	}

	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}

	public String getUpdDttm() {
		return updDttm;
	}

	public void setUpdDttm(String updDttm) {
		this.updDttm = updDttm;
	}

	public String getNotiDscr() {
		return notiDscr;
	}

	public void setNotiDscr(String notiDscr) {
		this.notiDscr = notiDscr;
	}

	public String getNotiRegDttm() {
		return notiRegDttm;
	}

	public void setNotiRegDttm(String notiRegDttm) {
		this.notiRegDttm = notiRegDttm;
	}

	public String getMailTemplate1st() {
		return mailTemplate1st;
	}

	public void setMailTemplate1st(String mailTemplate1st) {
		this.mailTemplate1st = mailTemplate1st;
	}

	public String getMailTemplate2nd() {
		return mailTemplate2nd;
	}

	public void setMailTemplate2nd(String mailTemplate2nd) {
		this.mailTemplate2nd = mailTemplate2nd;
	}

	public String getMailTemplate3rd() {
		return mailTemplate3rd;
	}

	public void setMailTemplate3rd(String mailTemplate3rd) {
		this.mailTemplate3rd = mailTemplate3rd;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getClass().toString());
		sb.append(" badnotiNo=").append(badnotiNo);
		sb.append(",notiNo=").append(notiNo);
		sb.append(",mbrNo='").append(mbrNo).append("'");
		sb.append(",badnotiRptCd='").append(badnotiRptCd).append("'");
		sb.append(",rptDscr='").append(rptDscr).append("'");
		sb.append(",pnshClsCd='").append(pnshClsCd).append("'");
		sb.append(",oldPnshClsCd='").append(oldPnshClsCd).append("'");
		sb.append(",regId='").append(regId).append("'");
		sb.append(",regDts='").append(regDts).append("'");
		sb.append(",updId='").append(updId).append("'");
		sb.append(",updDt='").append(updDt).append("'");
		sb.append(",regNm='").append(regNm).append("'");
		sb.append("}");
		return sb.toString();
	}

}
