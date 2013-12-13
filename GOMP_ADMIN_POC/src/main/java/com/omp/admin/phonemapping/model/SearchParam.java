/*
 * COPYRIGHT(c) SK telecom 2011
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author | Date        | Description
 * ---------------------------------------
 * bcpark | 2011. 4. 13. | Description
 *
 */
package com.omp.admin.phonemapping.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;

/**
 * 단말 Mapping 처리결과 화면에서 사용하는 파마리터 Domain
 * 
 * @author bcpark
 * @version 0.1
 */
public class SearchParam implements Pagenateable {
	private String txId;

	private String targetPhoneModelCd;
	private String standardPhoneModelCd;
	private String mappingType;
	private String mappingStat;
	private String searchType;
	private String searchText;
	private String subSearchType;
	private String subSearchText;
	private String searchToday;
	private String searchWeek;
	private String searchMonth;

	private String startDate;
	private String endDate;
	private String startDateDB;
	private String endDateDB;

	private String masterNo;

	// paging
	private PagenateInfoModel page = new PagenateInfoModel(20);

	@Override
	public PagenateInfoModel getPage() {
		return this.page;
	}

	// toString
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getTxId() {
		return txId;
	}

	public void setTxId(String txId) {
		this.txId = txId;
	}

	public String getTargetPhoneModelCd() {
		return targetPhoneModelCd;
	}

	public void setTargetPhoneModelCd(String targetPhoneModelCd) {
		this.targetPhoneModelCd = targetPhoneModelCd;
	}

	public String getStandardPhoneModelCd() {
		return standardPhoneModelCd;
	}

	public void setStandardPhoneModelCd(String standardPhoneModelCd) {
		this.standardPhoneModelCd = standardPhoneModelCd;
	}

	public String getMappingType() {
		return mappingType;
	}

	public void setMappingType(String mappingType) {
		this.mappingType = mappingType;
	}

	public String getMappingStat() {
		return mappingStat;
	}

	public void setMappingStat(String mappingStat) {
		this.mappingStat = mappingStat;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getSearchToday() {
		return searchToday;
	}

	public void setSearchToday(String searchToday) {
		this.searchToday = searchToday;
	}

	public String getSearchWeek() {
		return searchWeek;
	}

	public void setSearchWeek(String searchWeek) {
		this.searchWeek = searchWeek;
	}

	public String getSearchMonth() {
		return searchMonth;
	}

	public void setSearchMonth(String searchMonth) {
		this.searchMonth = searchMonth;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStartDateDB() {
		return startDateDB;
	}

	public void setStartDateDB(String startDateDB) {
		this.startDateDB = startDateDB;
	}

	public String getEndDateDB() {
		return endDateDB;
	}

	public void setEndDateDB(String endDateDB) {
		this.endDateDB = endDateDB;
	}

	public void setPage(PagenateInfoModel page) {
		this.page = page;
	}

	public String getMasterNo() {
		return masterNo;
	}

	public void setMasterNo(String masterNo) {
		this.masterNo = masterNo;
	}

	public String getSubSearchType() {
		return subSearchType;
	}

	public void setSubSearchType(String subSearchType) {
		this.subSearchType = subSearchType;
	}

	public String getSubSearchText() {
		return subSearchText;
	}

	public void setSubSearchText(String subSearchText) {
		this.subSearchText = subSearchText;
	}

}
