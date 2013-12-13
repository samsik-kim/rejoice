package com.omp.admin.contents.adminrec.model;

import com.omp.commons.model.TotalCountCarriable;

/**
 * @author Administrator
 *
 */
public class AdminRecommend implements TotalCountCarriable {

	private String rnum;
	private String catNo;
	private String catNm;
	private String filePos;
	private String prodId;
	private String prodNm;
	private String prodGrdCd;
	private String mbrNo;
	private String mbrId;
	private String mbrNm;
	private String dwldCnt;
	private String expoYnNm;
	private String expoYn;
	private String expoPrior;
	private String aid;
	
	private String prodAmt;
	private String regDt;
	private String regDt2;
	
	private String prodDesc;
	private String fileSize;
	private String updDt;
	private String updDt2;
	private String platClsCd;
	
	// paging
	private Long    totalCount;


	public String getRnum() {
		return rnum;
	}
	public void setRnum(String rnum) {
		this.rnum = rnum;
	}
	public String getCatNo() {
		return catNo;
	}
	public void setCatNo(String catNo) {
		this.catNo = catNo;
	}
	public String getCatNm() {
		return catNm;
	}
	public void setCatNm(String catNm) {
		this.catNm = catNm;
	}
	public String getFilePos() {
		return filePos;
	}
	public void setFilePos(String filePos) {
		this.filePos = filePos;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getProdNm() {
		return prodNm;
	}
	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
	}
	public String getProdGrdCd() {
		return prodGrdCd;
	}
	public void setProdGrdCd(String prodGrdCd) {
		this.prodGrdCd = prodGrdCd;
	}
	public String getMbrNo() {
		return mbrNo;
	}
	public void setMbrNo(String mbrNo) {
		this.mbrNo = mbrNo;
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
	public String getDwldCnt() {
		return dwldCnt;
	}
	public void setDwldCnt(String dwldCnt) {
		this.dwldCnt = dwldCnt;
	}
	public String getExpoYnNm() {
		return expoYnNm;
	}
	public void setExpoYnNm(String expoYnNm) {
		this.expoYnNm = expoYnNm;
	}
	public String getExpoYn() {
		return expoYn;
	}
	public void setExpoYn(String expoYn) {
		this.expoYn = expoYn;
	}
	public String getExpoPrior() {
		return expoPrior;
	}
	public void setExpoPrior(String expoPrior) {
		this.expoPrior = expoPrior;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	
	public String getProdAmt() {
		return prodAmt;
	}
	public void setProdAmt(String prodAmt) {
		this.prodAmt = prodAmt;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getRegDt2() {
		return regDt2;
	}
	public void setRegDt2(String regDt2) {
		this.regDt2 = regDt2;
	}
	public String getProdDesc() {
		return prodDesc;
	}
	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getUpdDt() {
		return updDt;
	}
	public void setUpdDt(String updDt) {
		this.updDt = updDt;
	}
	public String getUpdDt2() {
		return updDt2;
	}
	public void setUpdDt2(String updDt2) {
		this.updDt2 = updDt2;
	}
	public String getPlatClsCd() {
		return platClsCd;
	}
	public void setPlatClsCd(String platClsCd) {
		this.platClsCd = platClsCd;
	}
	
	@Override
	public Long getTotalCount() {
		return this.totalCount;
	}
	@Override
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
}
