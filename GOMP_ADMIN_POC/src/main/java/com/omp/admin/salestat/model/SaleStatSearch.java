package com.omp.admin.salestat.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;

public class SaleStatSearch  implements Pagenateable {
	
	// search Condition
	private String startDate;
	private String endDate;
	private String startDateDB;
	private String endDateDB;
	private String searchToday;
	private String searchWeek;
	private String searchMonth;
	
	private String saleDt; 
	private String searchType;
	private String searchText;
	
	private String fromYm;
	private String toYm;
	private String saleYm;
	
	
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

	public String getSearchMonth() {
		return searchMonth;
	}

	public void setSearchMonth(String searchMonth) {
		this.searchMonth = searchMonth;
	}
	
	public String getSearchWeek() {
		return searchWeek;
	}

	public void setSearchWeek(String searchWeek) {
		this.searchWeek = searchWeek;
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
	public String getSaleDt() {
		return saleDt;
	}

	public void setSaleDt(String saleDt) {
		this.saleDt = saleDt;
	}

	public String getFromYm() {
		return fromYm;
	}

	public void setFromYm(String fromYm) {
		this.fromYm = fromYm;
	}

	public String getToYm() {
		return toYm;
	}

	public void setToYm(String toYm) {
		this.toYm = toYm;
	}

	public String getSaleYm() {
		return saleYm;
	}

	public void setSaleYm(String saleYm) {
		this.saleYm = saleYm;
	}
	
}
