package com.omp.admin.giftcard.model;

import java.io.File;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;

public class GiftCardSub implements Pagenateable {
	
	//search Condition
	private String startDate;
	private String endDate;
	private String startDateDB;
	private String endDateDB;
	private String searchToday;
	private String searchWeek;
	private String searchMonth;
	private String searchType;
	private String searchText;
	
	private String issueType;
	private String resultType;
	public String vToday;
	private String firstCheck;
	
	private String resAuthConfirm;
	private String password;
	
	private File comp_file;
	private String cardNm;
	private String presentCashAmt;
	
	private String cardCnt;
	private String groupSeq;
	private String issuedGiftCardCnt;
	private String issueYn;
	private String regstiedListCnt;
	
	public String getRegstiedListCnt() {
		return regstiedListCnt;
	}

	public void setRegstiedListCnt(String regstiedListCnt) {
		this.regstiedListCnt = regstiedListCnt;
	}
	
	public String getIssueYn() {
		return issueYn;
	}

	public void setIssueYn(String issueYn) {
		this.issueYn = issueYn;
	}

	public String getIssuedGiftCardCnt() {
		return issuedGiftCardCnt;
	}

	public void setIssuedGiftCardCnt(String issuedGiftCardCnt) {
		this.issuedGiftCardCnt = issuedGiftCardCnt;
	}

	public String getGroupSeq() {
		return groupSeq;
	}

	public void setGroupSeq(String groupSeq) {
		this.groupSeq = groupSeq;
	}

	public void setCardCnt(String cardCnt) {
		this.cardCnt = cardCnt;
	}

	public String getCardCnt() {
		return cardCnt;
	}

	public String getCardNm() {
		return cardNm;
	}

	public void setCardNm(String cardNm) {
		this.cardNm = cardNm;
	}

	public String getPresentCashAmt() {
		return presentCashAmt;
	}

	public void setPresentCashAmt(String presentCashAmt) {
		this.presentCashAmt = presentCashAmt;
	}

	public File getComp_file() {
		return comp_file;
	}

	public void setComp_file(File comp_file) {
		this.comp_file = comp_file;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getResAuthConfirm() {
		return resAuthConfirm;
	}

	public void setResAuthConfirm(String resAuthConfirm) {
		this.resAuthConfirm = resAuthConfirm;
	}

	public String getFirstCheck() {
		return firstCheck;
	}

	public void setFirstCheck(String firstCheck) {
		this.firstCheck = firstCheck;
	}

	public String getvToday() {
		return vToday;
	}

	public void setvToday(String vToday) {
		this.vToday = vToday;
	}

	public void setPage(PagenateInfoModel page) {
		this.page = page;
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

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

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	
}
