package com.omp.commons.model;

import com.omp.commons.util.CommonUtil;

/**
 * 모델들의 공통요소들을 가지는 모델
 */
@SuppressWarnings("serial")
public class CommonModel
	extends DataValueObject {

	/** 검색 관련 */
	// 검색종류
	private String searchType;
	// 검색어
	private String searchValue;
	// 검색시작일
	private String startDate;
	private String startDate2;
	// 검색종료일
	private String endDate;
	private String endDate2;

	private String ordering;

	private String paramStr;

	/** 사이드 메뉴 */
	private String topCode;
	private String leftCode;

	public String getStartDate2() {
		return startDate2;
	}

	public void setStartDate2(String startDate2) {
		startDate2 = CommonUtil.removeSpecial(startDate2);
		startDate2 = startDate2.replaceAll("/", "");
		startDate2 = startDate2.replaceAll("-", "");
		this.startDate2 = startDate2;
	}

	public String getEndDate2() {
		return endDate2;
	}

	public void setEndDate2(String endDate2) {
		endDate2 = CommonUtil.removeSpecial(endDate2);
		endDate2 = endDate2.replaceAll("/", "");
		endDate2 = endDate2.replaceAll("-", "");
		this.endDate2 = endDate2;
	}

	public String getTopCode() {
		return topCode;
	}

	public void setTopCode(String topCode) {
		this.topCode = topCode;
	}

	public String getLeftCode() {
		return leftCode;
	}

	public void setLeftCode(String leftCode) {
		this.leftCode = leftCode;
	}

	public String getParamStr() {
		return paramStr;
	}

	public void setParamStr(String paramStr) {
		this.paramStr = paramStr;
	}

	public String getOrdering() {
		return ordering;
	}

	public void setOrdering(String ordering) {
		this.ordering = ordering;
	}


	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		searchType = CommonUtil.removeSpecial(searchType);
		this.searchType = searchType;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		searchValue = CommonUtil.removeSpecial(searchValue);
		this.searchValue = searchValue;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		startDate = CommonUtil.removeSpecial(startDate);
		startDate = startDate.replaceAll("/", "");
		startDate = startDate.replaceAll("-", "");
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		endDate = CommonUtil.removeSpecial(endDate);
		endDate = endDate.replaceAll("/", "");
		endDate = endDate.replaceAll("-", "");
		this.endDate = endDate;
	}
}