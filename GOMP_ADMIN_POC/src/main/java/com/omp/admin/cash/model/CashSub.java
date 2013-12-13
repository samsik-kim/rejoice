package com.omp.admin.cash.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;

public class CashSub implements Pagenateable {
	
	//search Condition
	private String startDate;
	private String endDate;
	private String startDateDB;
	private String endDateDB;
	private String searchToday;
	private String searchWeek;
	private String searchMonth;
	private String search3Month;
	private String search6Month;
	private String search12Month;
	
	private String searchType;
	private String searchText;
	
	//cashList
	private String[] opTypes;
	private String opType;
	
	
	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	//chargeDetail
	private String prchsDt;
	
	//refund 
	private String firstCheck;
	
	//cashBonus
	private String [] chrgCashId;
	private String [] bonusRatio;
	private String [] prebonusRatio;
	
	//cash 차감
	private String mbrNo;
	private String mbrId;
	private String cashTotAmt;
	private String refundAmt;
	
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

	public String[] getOpTypes() {
		return opTypes;
	}

	public void setOpTypes(String[] opTypes) {
		this.opTypes = opTypes;
	}

	public String getPrchsDt() {
		return prchsDt;
	}

	public void setPrchsDt(String prchsDt) {
		this.prchsDt = prchsDt;
	}

	public String getFirstCheck() {
		return firstCheck;
	}

	public void setFirstCheck(String firstCheck) {
		this.firstCheck = firstCheck;
	}

	public String[] getChrgCashId() {
		return chrgCashId;
	}

	public void setChrgCashId(String[] chrgCashId) {
		this.chrgCashId = chrgCashId;
	}

	public String[] getBonusRatio() {
		return bonusRatio;
	}

	public void setBonusRatio(String[] bonusRatio) {
		this.bonusRatio = bonusRatio;
	}

	public String[] getPrebonusRatio() {
		return prebonusRatio;
	}

	public void setPrebonusRatio(String[] prebonusRatio) {
		this.prebonusRatio = prebonusRatio;
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

	public String getCashTotAmt() {
		return cashTotAmt;
	}

	public void setCashTotAmt(String cashTotAmt) {
		this.cashTotAmt = cashTotAmt;
	}

	public String getRefundAmt() {
		return refundAmt;
	}

	public void setRefundAmt(String refundAmt) {
		this.refundAmt = refundAmt;
	}

	public String getSearch3Month() {
		return search3Month;
	}

	public void setSearch3Month(String search3Month) {
		this.search3Month = search3Month;
	}

	public String getSearch6Month() {
		return search6Month;
	}

	public void setSearch6Month(String search6Month) {
		this.search6Month = search6Month;
	}

	public String getSearch12Month() {
		return search12Month;
	}

	public void setSearch12Month(String search12Month) {
		this.search12Month = search12Month;
	}
	
}
