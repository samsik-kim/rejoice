package com.omp.dev.contents.model.verify;

import java.util.List;

public class ContentVerifyDetail {
    
    private String cid;				// CID
	private String vmType;			// 플랫폼
	private String prodNm;			// 상품명
	private String prodDescSmmr;	// 상품설명
	private String aid;				// 상품ID
	private String gameDelibGrd;	// 심의등급
	private String insDttm;			// 상품등록일
	private String ctInsDttm;		// 검증요청일
	private String ctEndExDt;		// 검증요청일
	private String ctEndDt;			// 검증완료일
	private String verifyReqVer;	// 검증요청버전
	private String agrmntStat;		// 승인상태
	private String filePos;			// 상품이미지경로
	private String ctgrCd;			// 카테고리
	private String verifyPrgrYn;	// 검증진행상태
	private String sprtPhoneCnt;	// 대상단말 수
	private String updDttm;			// 검증취소일자
	private List<SubContentVerify> subContentsList;	// Sub Contents List
	
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getVmType() {
		return vmType;
	}
	public void setVmType(String vmType) {
		this.vmType = vmType;
	}
	public String getProdNm() {
		return prodNm;
	}
	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
	}
	public String getProdDescSmmr() {
		return prodDescSmmr;
	}
	public void setProdDescSmmr(String prodDescSmmr) {
		this.prodDescSmmr = prodDescSmmr;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getGameDelibGrd() {
		return gameDelibGrd;
	}
	public void setGameDelibGrd(String gameDelibGrd) {
		this.gameDelibGrd = gameDelibGrd;
	}
	public String getInsDttm() {
		return insDttm;
	}
	public void setInsDttm(String insDttm) {
		this.insDttm = insDttm;
	}
	public String getCtInsDttm() {
		return ctInsDttm;
	}
	public void setCtInsDttm(String ctInsDttm) {
		this.ctInsDttm = ctInsDttm;
	}
	public String getCtEndExDt() {
		return ctEndExDt;
	}
	public void setCtEndExDt(String ctEndExDt) {
		this.ctEndExDt = ctEndExDt;
	}
	public String getCtEndDt() {
		return ctEndDt;
	}
	public void setCtEndDt(String ctEndDt) {
		this.ctEndDt = ctEndDt;
	}
	public String getVerifyReqVer() {
		return verifyReqVer;
	}
	public void setVerifyReqVer(String verifyReqVer) {
		this.verifyReqVer = verifyReqVer;
	}
	public String getAgrmntStat() {
		return agrmntStat;
	}
	public void setAgrmntStat(String agrmntStat) {
		this.agrmntStat = agrmntStat;
	}
	public String getFilePos() {
		return filePos;
	}
	public void setFilePos(String filePos) {
		this.filePos = filePos;
	}
	public String getCtgrCd() {
		return ctgrCd;
	}
	public void setCtgrCd(String ctgrCd) {
		this.ctgrCd = ctgrCd;
	}
	public String getVerifyPrgrYn() {
		return verifyPrgrYn;
	}
	public void setVerifyPrgrYn(String verifyPrgrYn) {
		this.verifyPrgrYn = verifyPrgrYn;
	}
	public String getSprtPhoneCnt() {
		return sprtPhoneCnt;
	}
	public void setSprtPhoneCnt(String sprtPhoneCnt) {
		this.sprtPhoneCnt = sprtPhoneCnt;
	}
	public String getUpdDttm() {
		return updDttm;
	}
	public void setUpdDttm(String updDttm) {
		this.updDttm = updDttm;
	}
	public List<SubContentVerify> getSubContentsList() {
		return subContentsList;
	}
	public void setSubContentsList(List<SubContentVerify> subContentsList) {
		this.subContentsList = subContentsList;
	}
	
	
}
