package com.stockinvest.common.info;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CommonInfo implements Serializable{
	
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
	
	/** 결과 코드 */
	private String resultCode;
	/** 결과 메세지 */
	private String resultMessage;

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

	/**
	 * @return the resultCode
	 */
	public String getResultCode() {
		return resultCode;
	}

	/**
	 * @param resultCode the resultCode to set
	 */
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	/**
	 * @return the resultMessage
	 */
	public String getResultMessage() {
		return resultMessage;
	}

	/**
	 * @param resultMessage the resultMessage to set
	 */
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	
}
