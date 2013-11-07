/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 * redaano    2009. 5. 7.    Description
 *
 */
package com.omp.admin.product.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;

/**
 * 상품관리 파라미터 도에인
 * 
 * @author redaano
 * @version 0.1
 */
public class ContentsSub implements Pagenateable {
	private String cid = ""; // cid
	private String verifyReqVer;
	// search Condition
	private String startDate;
	private String endDate;
	private String startDateDB;
	private String endDateDB;
	private String vmType;
	private String saleStat;
	private String verifyPrgrYn;
	private String dpCat1;
	private String dpCat2;
	private String searchType;
	private String searchText;
	private String searchToday;
	private String searchWeek;
	private String searchMonth;

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
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
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

	public String getVmType() {
		return vmType;
	}

	public void setVmType(String vmType) {
		this.vmType = vmType;
	}

	public String getSaleStat() {
		return saleStat;
	}

	public void setSaleStat(String saleStat) {
		this.saleStat = saleStat;
	}

	public String getVerifyPrgrYn() {
		return verifyPrgrYn;
	}

	public void setVerifyPrgrYn(String verifyPrgrYn) {
		this.verifyPrgrYn = verifyPrgrYn;
	}

	public String getDpCat1() {
		return dpCat1;
	}

	public void setDpCat1(String dpCat1) {
		this.dpCat1 = dpCat1;
	}

	public String getDpCat2() {
		return dpCat2;
	}

	public void setDpCat2(String dpCat2) {
		this.dpCat2 = dpCat2;
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

	public String getVerifyReqVer() {
		return verifyReqVer;
	}

	public void setVerifyReqVer(String verifyReqVer) {
		this.verifyReqVer = verifyReqVer;
	}

	public String getMasterNo() {
		return masterNo;
	}

	public void setMasterNo(String masterNo) {
		this.masterNo = masterNo;
	}

	public void setPage(PagenateInfoModel page) {
		this.page = page;
	}

}