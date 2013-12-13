package com.omp.dev.main.model;

import com.omp.commons.model.CommonModel;

@SuppressWarnings("serial")
public class MainDownloadBest extends CommonModel {
	
	private String dpCatNo;			// 전시 카테고리 번호
	private String prodID;			// 상품 ID
	private String prodNm;			// 상품명
	private String wkStartDt;		// 주간 시작일
	private String wkEndDt;		// 주간 종료일
	private String expoYn;			// 노출여부
	private String expoPrior;		// 다운로드 우선 순위
	private String dwldCnt;			// 다운로드 수
	private String regDt;				// 등록일자
	private String type;				// Free & Pay type
	
	public String getDpCatNo() {
		return dpCatNo;
	}
	public void setDpCatNo(String dpCatNo) {
		this.dpCatNo = dpCatNo;
	}
	public String getProdID() {
		return prodID;
	}
	public void setProdID(String prodID) {
		this.prodID = prodID;
	}
	public String getProdNm() {
		return prodNm;
	}
	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
	}
	public String getWkStartDt() {
		return wkStartDt;
	}
	public void setWkStartDt(String wkStartDt) {
		this.wkStartDt = wkStartDt;
	}
	public String getWkEndDt() {
		return wkEndDt;
	}
	public void setWkEndDt(String wkEndDt) {
		this.wkEndDt = wkEndDt;
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
	public String getDwldCnt() {
		return dwldCnt;
	}
	public void setDwldCnt(String dwldCnt) {
		this.dwldCnt = dwldCnt;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
