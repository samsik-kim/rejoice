package com.omp.dev.settlement.model;

import java.util.ArrayList;

import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;
import com.omp.commons.model.TotalCountCarriable;

public class ProductSale  implements Pagenateable, TotalCountCarriable {
	
	private PagenateInfoModel	page		= new PagenateInfoModel();
	// dailylist
	private String saleDt;
	private long saleAmt;
	private long saleCancelAmt;
	private long subtotalAmt; //소계금액
	private long dwnlCnt;
	private long freeDwnlCnt;
	private long chargedDwnlCnt;
	private long cancelDwnlCnt;
	private long cardDwnlCnt;
	private long phoneDwnlCnt;
	private long cashDwnlCnt;
	private String exportYn;
	
	//dailyDetaillist
	private String memNameId;
	private String prdName;
	private String itemProdNm;
	
	//monthlylist
	private String saleYm;
	//private long  saleAmt;
	private long  payCnt;
	private long  freeCnt;         
	private long  cancelCnt;
	private long  cancelAmt;
	private long  cardAmt;
	private long  phoneAmt;
	private long  cashAmt;
	private long  subAmt;
	
	private String dttm;
	private String saleDttm;
	private String userMemNameId;
	private long prodprc;
	private long payAmt;
	private long cardPay;
	private long phonePay;
	private long cashPay;
	

	
	// search Condition
	private String startDate;
	private String endDate;
	private String startDateDB;
	private String endDateDB;
	private String searchToday;
	private String searchWeek;
	private String searchMonth;
	
	//private String saleDt; 
	private String searchType;
	private String searchText;
	
	private String fromYm;
	private String toYm;
	//private String saleYm;
	private String prdSaleType; //유료무료 상품체크
	private String prdSaleTypeName; //유료무료 상품체크 이름
	private String prodId; //상품코드
	private String cardSaleAmt;//카드결제 판매 금액
	private String phoneSaleAmt;// 폰결제  판매금액
	private String cashSaleAmt; //캐쉬결제 판매금액
	private String mbrNo;
	private ArrayList prdType;// 검색: 상품구분
	private String strPrdType;//검색: 상품구분
	private String searchDay;
	
	
	
	
	
	
	
	/**
	 * @return the searchDay
	 */
	public String getSearchDay() {
		return searchDay;
	}

	/**
	 * @param searchDay the searchDay to set
	 */
	public void setSearchDay(String searchDay) {
		this.searchDay = searchDay;
	}

	/**
	 * @return the prdSaleTypeName
	 */
	public String getPrdSaleTypeName() {
		return prdSaleTypeName;
	}

	/**
	 * @param prdSaleTypeName the prdSaleTypeName to set
	 */
	public void setPrdSaleTypeName(String prdSaleTypeName) {
		this.prdSaleTypeName = prdSaleTypeName;
	}

	/**
	 * @return the strPrdType
	 */
	public String getStrPrdType() {
		return strPrdType;
	}

	/**
	 * @param strPrdType the strPrdType to set
	 */
	public void setStrPrdType(String strPrdType) {
		this.strPrdType = strPrdType;
	}

	/**
	 * @return the prdType
	 */
	public ArrayList getPrdType() {
		return prdType;
	}

	/**
	 * @param prdType the prdType to set
	 */
	public void setPrdType(ArrayList prdType) {
		this.prdType = prdType;
	}

	/**
	 * @return the mbrNo
	 */
	public String getMbrNo() {
		return mbrNo;
	}

	/**
	 * @param mbrNo the mbrNo to set
	 */
	public void setMbrNo(String mbrNo) {
		this.mbrNo = mbrNo;
	}

	/**
	 * @return the cardSaleAmt
	 */
	public String getCardSaleAmt() {
		return cardSaleAmt;
	}

	/**
	 * @param cardSaleAmt the cardSaleAmt to set
	 */
	public void setCardSaleAmt(String cardSaleAmt) {
		this.cardSaleAmt = cardSaleAmt;
	}

	/**
	 * @return the phoneSaleAmt
	 */
	public String getPhoneSaleAmt() {
		return phoneSaleAmt;
	}

	/**
	 * @param phoneSaleAmt the phoneSaleAmt to set
	 */
	public void setPhoneSaleAmt(String phoneSaleAmt) {
		this.phoneSaleAmt = phoneSaleAmt;
	}

	/**
	 * @return the cashSaleAmt
	 */
	public String getCashSaleAmt() {
		return cashSaleAmt;
	}

	/**
	 * @param cashSaleAmt the cashSaleAmt to set
	 */
	public void setCashSaleAmt(String cashSaleAmt) {
		this.cashSaleAmt = cashSaleAmt;
	}

	/**
	 * @return the prodId
	 */
	public String getProdId() {
		return prodId;
	}

	/**
	 * @param prodId the prodId to set
	 */
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	/**
	 * @return the prdSaleType
	 */
	public String getPrdSaleType() {
		return prdSaleType;
	}

	/**
	 * @param prdSaleType the prdSaleType to set
	 */
	public void setPrdSaleType(String prdSaleType) {
		this.prdSaleType = prdSaleType;
	}

	/**
	 * @return the subtotalAmt
	 */
	public long getSubtotalAmt() {
		return subtotalAmt;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the startDateDB
	 */
	public String getStartDateDB() {
		return startDateDB;
	}

	/**
	 * @param startDateDB the startDateDB to set
	 */
	public void setStartDateDB(String startDateDB) {
		this.startDateDB = startDateDB;
	}

	/**
	 * @return the endDateDB
	 */
	public String getEndDateDB() {
		return endDateDB;
	}

	/**
	 * @param endDateDB the endDateDB to set
	 */
	public void setEndDateDB(String endDateDB) {
		this.endDateDB = endDateDB;
	}

	/**
	 * @return the searchToday
	 */
	public String getSearchToday() {
		return searchToday;
	}

	/**
	 * @param searchToday the searchToday to set
	 */
	public void setSearchToday(String searchToday) {
		this.searchToday = searchToday;
	}

	/**
	 * @return the searchWeek
	 */
	public String getSearchWeek() {
		return searchWeek;
	}

	/**
	 * @param searchWeek the searchWeek to set
	 */
	public void setSearchWeek(String searchWeek) {
		this.searchWeek = searchWeek;
	}

	/**
	 * @return the searchMonth
	 */
	public String getSearchMonth() {
		return searchMonth;
	}

	/**
	 * @param searchMonth the searchMonth to set
	 */
	public void setSearchMonth(String searchMonth) {
		this.searchMonth = searchMonth;
	}

	/**
	 * @return the searchType
	 */
	public String getSearchType() {
		return searchType;
	}

	/**
	 * @param searchType the searchType to set
	 */
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	/**
	 * @return the searchText
	 */
	public String getSearchText() {
		return searchText;
	}

	/**
	 * @param searchText the searchText to set
	 */
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	/**
	 * @return the fromYm
	 */
	public String getFromYm() {
		return fromYm;
	}

	/**
	 * @param fromYm the fromYm to set
	 */
	public void setFromYm(String fromYm) {
		this.fromYm = fromYm;
	}

	/**
	 * @return the toYm
	 */
	public String getToYm() {
		return toYm;
	}

	/**
	 * @param toYm the toYm to set
	 */
	public void setToYm(String toYm) {
		this.toYm = toYm;
	}

	/**
	 * @param subtotalAmt the subtotalAmt to set
	 */
	public void setSubtotalAmt(long subtotalAmt) {
		this.subtotalAmt = subtotalAmt;
	}

	/**
	 * @return the saleCancelAmt
	 */
	public long getSaleCancelAmt() {
		return saleCancelAmt;
	}

	/**
	 * @param saleCancelAmt the saleCancelAmt to set
	 */
	public void setSaleCancelAmt(long saleCancelAmt) {
		this.saleCancelAmt = saleCancelAmt;
	}

	public String getDttm() {
		return dttm;
	}

	public void setDttm(String dttm) {
		this.dttm = dttm;
	}

	public String getSaleDttm() {
		return saleDttm;
	}

	public void setSaleDttm(String saleDttm) {
		this.saleDttm = saleDttm;
	}

	public String getUserMemNameId() {
		return userMemNameId;
	}

	public void setUserMemNameId(String userMemNameId) {
		this.userMemNameId = userMemNameId;
	}

	public long getProdprc() {
		return prodprc;
	}

	public void setProdprc(long prodprc) {
		this.prodprc = prodprc;
	}

	public long getPayAmt() {
		return payAmt;
	}

	public void setPayAmt(long payAmt) {
		this.payAmt = payAmt;
	}

	public long getCardPay() {
		return cardPay;
	}

	public void setCardPay(long cardPay) {
		this.cardPay = cardPay;
	}

	public long getPhonePay() {
		return phonePay;
	}

	public void setPhonePay(long phonePay) {
		this.phonePay = phonePay;
	}

	public long getCashPay() {
		return cashPay;
	}

	public void setCashPay(long cashPay) {
		this.cashPay = cashPay;
	}

	public String getMemNameId() {
		return memNameId;
	}

	public void setMemNameId(String memNameId) {
		this.memNameId = memNameId;
	}

	public String getPrdName() {
		return prdName;
	}

	public void setPrdName(String prdName) {
		this.prdName = prdName;
	}

	public String getItemProdNm() {
		return itemProdNm;
	}

	public void setItemProdNm(String itemProdNm) {
		this.itemProdNm = itemProdNm;
	}

	public String getSaleDt() {
		return saleDt;
	}

	public void setSaleDt(String saleDt) {
		this.saleDt = saleDt;
	}

	public long getSaleAmt() {
		return saleAmt;
	}

	public void setSaleAmt(long saleAmt) {
		this.saleAmt = saleAmt;
	}

	public long getDwnlCnt() {
		return dwnlCnt;
	}

	public void setDwnlCnt(long dwnlCnt) {
		this.dwnlCnt = dwnlCnt;
	}

	public String getExportYn() {
		return exportYn;
	}

	public void setExportYn(String exportYn) {
		this.exportYn = exportYn;
	}
	
	public long getFreeDwnlCnt() {
		return freeDwnlCnt;
	}

	public void setFreeDwnlCnt(long freeDwnlCnt) {
		this.freeDwnlCnt = freeDwnlCnt;
	}

	public long getChargedDwnlCnt() {
		return chargedDwnlCnt;
	}

	public void setChargedDwnlCnt(long chargedDwnlCnt) {
		this.chargedDwnlCnt = chargedDwnlCnt;
	}

	public long getCancelDwnlCnt() {
		return cancelDwnlCnt;
	}

	public void setCancelDwnlCnt(long cancelDwnlCnt) {
		this.cancelDwnlCnt = cancelDwnlCnt;
	}

	public long getCardDwnlCnt() {
		return cardDwnlCnt;
	}

	public void setCardDwnlCnt(long cardDwnlCnt) {
		this.cardDwnlCnt = cardDwnlCnt;
	}

	public long getPhoneDwnlCnt() {
		return phoneDwnlCnt;
	}

	public void setPhoneDwnlCnt(long phoneDwnlCnt) {
		this.phoneDwnlCnt = phoneDwnlCnt;
	}

	public long getCashDwnlCnt() {
		return cashDwnlCnt;
	}

	public void setCashDwnlCnt(long cashDwnlCnt) {
		this.cashDwnlCnt = cashDwnlCnt;
	}
	
	public String getSaleYm() {
		return saleYm;
	}

	public void setSaleYm(String saleYm) {
		this.saleYm = saleYm;
	}

	public long getPayCnt() {
		return payCnt;
	}

	public void setPayCnt(long payCnt) {
		this.payCnt = payCnt;
	}

	public long getFreeCnt() {
		return freeCnt;
	}

	public void setFreeCnt(long freeCnt) {
		this.freeCnt = freeCnt;
	}

	public long getCancelCnt() {
		return cancelCnt;
	}

	public void setCancelCnt(long cancelCnt) {
		this.cancelCnt = cancelCnt;
	}

	public long getCancelAmt() {
		return cancelAmt;
	}

	public void setCancelAmt(long cancelAmt) {
		this.cancelAmt = cancelAmt;
	}

	public long getCardAmt() {
		return cardAmt;
	}

	public void setCardAmt(long cardAmt) {
		this.cardAmt = cardAmt;
	}

	public long getPhoneAmt() {
		return phoneAmt;
	}

	public void setPhoneAmt(long phoneAmt) {
		this.phoneAmt = phoneAmt;
	}

	public long getCashAmt() {
		return cashAmt;
	}

	public void setCashAmt(long cashAmt) {
		this.cashAmt = cashAmt;
	}

	public long getSubAmt() {
		return subAmt;
	}

	public void setSubAmt(long subAmt) {
		this.subAmt = subAmt;
	}




	/*
	 * PAGING REFER
	 */
	private int rnum;
	// paging
	private Long total_count;
	
	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	
	public Long getTotal_count() {
		return total_count;
	}

	public void setTotal_count(Long total_count) {
		this.total_count = total_count;
	}
	
	// baseInfo 

	@Override
	public Long getTotalCount() {
		return this.total_count;
	}

	@Override
	public Long setTotalCount(Long totalCount) {
		return this.total_count = totalCount;
	}

	/**
	 * @return the page
	 */
	public PagenateInfoModel getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(PagenateInfoModel page) {
		this.page = page;
	}
	
}
