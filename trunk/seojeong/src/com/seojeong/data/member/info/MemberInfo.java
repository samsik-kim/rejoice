package com.seojeong.data.member.info;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MemberInfo implements Serializable{

	// 페이징, 검색 관련
	/** 페이징 시작 번호 */
	private int startNum;
	/** 페이징 끝 번호 */
	private int endNum;
	/** 총 갯수 */
	private int totalCount;
	/** 현재페이지 */
	private String currentPage;
	/** 검색구분 */
	private String searchKey;
	/** 검색어 */
	private String searchValue;

	// 이전글, 다음글 관련
	/** 다음글번호 */
	private String nextSeq;
	/** 이전글번호 */
	private String prevSeq;
	/** 다음글제목 */
	private String nextNm;
	/** 이전글제목 */
	private String prevNm;
	
	private String rnum;
	
	private String MEMBER_SEQ;
	private String MEMBER_NM;
	private String CONTANCT1;
	private String CONTANCT2;
	private String ADDR;
	private String EMAIL;
	private String VST_CNT;
	private String REG_DT;
	private String UP_DT;
	private String WIN_CNT;
	/**
	 * @return the mEMBER_SEQ
	 */
	public String getMEMBER_SEQ() {
		return MEMBER_SEQ;
	}
	/**
	 * @param mEMBER_SEQ the mEMBER_SEQ to set
	 */
	public void setMEMBER_SEQ(String mEMBER_SEQ) {
		MEMBER_SEQ = mEMBER_SEQ;
	}
	/**
	 * @return the mEMBER_NM
	 */
	public String getMEMBER_NM() {
		return MEMBER_NM;
	}
	/**
	 * @param mEMBER_NM the mEMBER_NM to set
	 */
	public void setMEMBER_NM(String mEMBER_NM) {
		MEMBER_NM = mEMBER_NM;
	}
	/**
	 * @return the cONTANCT1
	 */
	public String getCONTANCT1() {
		return CONTANCT1;
	}
	/**
	 * @param cONTANCT1 the cONTANCT1 to set
	 */
	public void setCONTANCT1(String cONTANCT1) {
		CONTANCT1 = cONTANCT1;
	}
	/**
	 * @return the cONTANCT2
	 */
	public String getCONTANCT2() {
		return CONTANCT2;
	}
	/**
	 * @param cONTANCT2 the cONTANCT2 to set
	 */
	public void setCONTANCT2(String cONTANCT2) {
		CONTANCT2 = cONTANCT2;
	}
	/**
	 * @return the aDDR
	 */
	public String getADDR() {
		return ADDR;
	}
	/**
	 * @param aDDR the aDDR to set
	 */
	public void setADDR(String aDDR) {
		ADDR = aDDR;
	}
	/**
	 * @return the eMAIL
	 */
	public String getEMAIL() {
		return EMAIL;
	}
	/**
	 * @param eMAIL the eMAIL to set
	 */
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	/**
	 * @return the vST_CNT
	 */
	public String getVST_CNT() {
		return VST_CNT;
	}
	/**
	 * @param vST_CNT the vST_CNT to set
	 */
	public void setVST_CNT(String vST_CNT) {
		VST_CNT = vST_CNT;
	}
	/**
	 * @return the rEG_DT
	 */
	public String getREG_DT() {
		return REG_DT;
	}
	/**
	 * @param rEG_DT the rEG_DT to set
	 */
	public void setREG_DT(String rEG_DT) {
		REG_DT = rEG_DT;
	}
	/**
	 * @return the uP_DT
	 */
	public String getUP_DT() {
		return UP_DT;
	}
	/**
	 * @param uP_DT the uP_DT to set
	 */
	public void setUP_DT(String uP_DT) {
		UP_DT = uP_DT;
	}
	/**
	 * @return the wIN_CNT
	 */
	public String getWIN_CNT() {
		return WIN_CNT;
	}
	/**
	 * @param wIN_CNT the wIN_CNT to set
	 */
	public void setWIN_CNT(String wIN_CNT) {
		WIN_CNT = wIN_CNT;
	}
	/**
	 * @return the startNum
	 */
	public int getStartNum() {
		return startNum;
	}
	/**
	 * @param startNum the startNum to set
	 */
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	/**
	 * @return the endNum
	 */
	public int getEndNum() {
		return endNum;
	}
	/**
	 * @param endNum the endNum to set
	 */
	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}
	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * @return the currentPage
	 */
	public String getCurrentPage() {
		return currentPage;
	}
	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	/**
	 * @return the searchKey
	 */
	public String getSearchKey() {
		return searchKey;
	}
	/**
	 * @param searchKey the searchKey to set
	 */
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	/**
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}
	/**
	 * @param searchValue the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	/**
	 * @return the nextSeq
	 */
	public String getNextSeq() {
		return nextSeq;
	}
	/**
	 * @param nextSeq the nextSeq to set
	 */
	public void setNextSeq(String nextSeq) {
		this.nextSeq = nextSeq;
	}
	/**
	 * @return the prevSeq
	 */
	public String getPrevSeq() {
		return prevSeq;
	}
	/**
	 * @param prevSeq the prevSeq to set
	 */
	public void setPrevSeq(String prevSeq) {
		this.prevSeq = prevSeq;
	}
	/**
	 * @return the nextNm
	 */
	public String getNextNm() {
		return nextNm;
	}
	/**
	 * @param nextNm the nextNm to set
	 */
	public void setNextNm(String nextNm) {
		this.nextNm = nextNm;
	}
	/**
	 * @return the prevNm
	 */
	public String getPrevNm() {
		return prevNm;
	}
	/**
	 * @param prevNm the prevNm to set
	 */
	public void setPrevNm(String prevNm) {
		this.prevNm = prevNm;
	}
	/**
	 * @return the rnum
	 */
	public String getRnum() {
		return rnum;
	}
	/**
	 * @param rnum the rnum to set
	 */
	public void setRnum(String rnum) {
		this.rnum = rnum;
	}
	
}
