package com.omp.admin.community.postscript.model;

import com.omp.commons.model.CommonModel;
import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;

@SuppressWarnings("serial")
public class UsMemberPunish extends CommonModel implements Pagenateable {

	private String mbrNo;
	private int mbrPnshSeq;
	private String histYn;
	private int pnshNotiCnt;
	private String pnshStrtDttm;
	private String pnshEndDttm;
	private String pnshClsCd;
	private String pnshCloseDscr;
	private String regId;
	private String regDttm;
	private String updId;
	private String updDttm;

	private String mbrId;

	private PagenateInfoModel page = new PagenateInfoModel(20);
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

	public String getMbrNo() {
		return mbrNo;
	}

	public void setMbrNo(String mbrNo) {
		this.mbrNo = mbrNo;
	}

	public int getMbrPnshSeq() {
		return mbrPnshSeq;
	}

	public void setMbrPnshSeq(int mbrPnshSeq) {
		this.mbrPnshSeq = mbrPnshSeq;
	}

	public String getHistYn() {
		return histYn;
	}

	public void setHistYn(String histYn) {
		this.histYn = histYn;
	}

	public int getPnshNotiCnt() {
		return pnshNotiCnt;
	}

	public void setPnshNotiCnt(int pnshNotiCnt) {
		this.pnshNotiCnt = pnshNotiCnt;
	}

	public String getPnshStrtDttm() {
		return pnshStrtDttm;
	}

	public void setPnshStrtDttm(String pnshStrtDttm) {
		this.pnshStrtDttm = pnshStrtDttm;
	}

	public String getPnshEndDttm() {
		return pnshEndDttm;
	}

	public void setPnshEndDttm(String pnshEndDttm) {
		this.pnshEndDttm = pnshEndDttm;
	}

	public String getPnshClsCd() {
		return pnshClsCd;
	}

	public void setPnshClsCd(String pnshClsCd) {
		this.pnshClsCd = pnshClsCd;
	}

	public String getPnshCloseDscr() {
		return pnshCloseDscr;
	}

	public void setPnshCloseDscr(String pnshCloseDscr) {
		this.pnshCloseDscr = pnshCloseDscr;
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

	public String getUpdId() {
		return updId;
	}

	public void setUpdId(String updId) {
		this.updId = updId;
	}

	public String getUpdDttm() {
		return updDttm;
	}

	public void setUpdDttm(String updDttm) {
		this.updDttm = updDttm;
	}

	public String getMbrId() {
		return mbrId;
	}

	public void setMbrId(String mbrId) {
		this.mbrId = mbrId;
	}

}
