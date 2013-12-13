package com.omp.dev.settlement.model;

import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;
import com.omp.commons.model.TotalCountCarriable;

public class DailySale  implements Pagenateable, TotalCountCarriable {
	
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
	private String mbrNo;
	private String startDate;
	private String endDate;
	private String cardSaleAmt;//카드결제 판매 금액
	private String phoneSaleAmt;// 폰결제  판매금액
	private String cashSaleAmt; //캐쉬결제 판매금액
	private String saleYN;// 판매취소구분
	private String saleYNName;// 판매취소구분명
	private String totalSaleCount; //총구매건수
	private String totalCancelCount; //총구매취소건수
	private String prdSaleType; //유료무료 상품체크
	private String prdSaleTypeName; //유료무료 상품체크명
	private String prodId; //상품코드
	private String searchDay;
	private long tSaleAmt;
	private long tSaleCancelAmt;
	private long tSubtotalAmt; //소계금액
	private long tDwnlCnt;
	private long tFreeDwnlCnt;
	private long tChargedDwnlCnt;
	private long tCancelDwnlCnt;
	private long tCardDwnlCnt;
	private long tPhoneDwnlCnt;
	private long tCashDwnlCnt;
	private long tPayCnt;
	private long tFreeCnt;         
	private long tCancelCnt;
	private long tCancelAmt;
	private long tCardAmt;
	private long tPhoneAmt;
	private long tCashAmt;
	private long tSubAmt;
	private long tProdprc;
	private long tPayAmt;
	private long tCardPay;
	private long tPhonePay;
	private long tCashPay;
	private String tCardSaleAmt;//카드결제 총판매 금액
	private String tPhoneSaleAmt;// 폰결제 총 판매금액
	private String tCashSaleAmt; //캐쉬결제 총판매금액
	private String tTotalSaleCount; //총구매건수
	private String tTotalCancelCount; //총구매취소건수
	private String aid;
	
	
	
	
	
	
	
	
	/**
	 * @return the aid
	 */
	public String getAid() {
		return aid;
	}

	/**
	 * @param aid the aid to set
	 */
	public void setAid(String aid) {
		this.aid = aid;
	}

	/**
	 * @return the tCardSaleAmt
	 */
	public String gettCardSaleAmt() {
		return tCardSaleAmt;
	}

	/**
	 * @param tCardSaleAmt the tCardSaleAmt to set
	 */
	public void settCardSaleAmt(String tCardSaleAmt) {
		this.tCardSaleAmt = tCardSaleAmt;
	}

	/**
	 * @return the tPhoneSaleAmt
	 */
	public String gettPhoneSaleAmt() {
		return tPhoneSaleAmt;
	}

	/**
	 * @param tPhoneSaleAmt the tPhoneSaleAmt to set
	 */
	public void settPhoneSaleAmt(String tPhoneSaleAmt) {
		this.tPhoneSaleAmt = tPhoneSaleAmt;
	}

	/**
	 * @return the tCashSaleAmt
	 */
	public String gettCashSaleAmt() {
		return tCashSaleAmt;
	}

	/**
	 * @param tCashSaleAmt the tCashSaleAmt to set
	 */
	public void settCashSaleAmt(String tCashSaleAmt) {
		this.tCashSaleAmt = tCashSaleAmt;
	}

	/**
	 * @return the tTotalSaleCount
	 */
	public String gettTotalSaleCount() {
		return tTotalSaleCount;
	}

	/**
	 * @param tTotalSaleCount the tTotalSaleCount to set
	 */
	public void settTotalSaleCount(String tTotalSaleCount) {
		this.tTotalSaleCount = tTotalSaleCount;
	}

	/**
	 * @return the tTotalCancelCount
	 */
	public String gettTotalCancelCount() {
		return tTotalCancelCount;
	}

	/**
	 * @param tTotalCancelCount the tTotalCancelCount to set
	 */
	public void settTotalCancelCount(String tTotalCancelCount) {
		this.tTotalCancelCount = tTotalCancelCount;
	}

	/**
	 * @return the tSaleAmt
	 */
	public long gettSaleAmt() {
		return tSaleAmt;
	}

	/**
	 * @param tSaleAmt the tSaleAmt to set
	 */
	public void settSaleAmt(long tSaleAmt) {
		this.tSaleAmt = tSaleAmt;
	}

	/**
	 * @return the tSaleCancelAmt
	 */
	public long gettSaleCancelAmt() {
		return tSaleCancelAmt;
	}

	/**
	 * @param tSaleCancelAmt the tSaleCancelAmt to set
	 */
	public void settSaleCancelAmt(long tSaleCancelAmt) {
		this.tSaleCancelAmt = tSaleCancelAmt;
	}

	/**
	 * @return the tSubtotalAmt
	 */
	public long gettSubtotalAmt() {
		return tSubtotalAmt;
	}

	/**
	 * @param tSubtotalAmt the tSubtotalAmt to set
	 */
	public void settSubtotalAmt(long tSubtotalAmt) {
		this.tSubtotalAmt = tSubtotalAmt;
	}

	/**
	 * @return the tDwnlCnt
	 */
	public long gettDwnlCnt() {
		return tDwnlCnt;
	}

	/**
	 * @param tDwnlCnt the tDwnlCnt to set
	 */
	public void settDwnlCnt(long tDwnlCnt) {
		this.tDwnlCnt = tDwnlCnt;
	}

	/**
	 * @return the tFreeDwnlCnt
	 */
	public long gettFreeDwnlCnt() {
		return tFreeDwnlCnt;
	}

	/**
	 * @param tFreeDwnlCnt the tFreeDwnlCnt to set
	 */
	public void settFreeDwnlCnt(long tFreeDwnlCnt) {
		this.tFreeDwnlCnt = tFreeDwnlCnt;
	}

	/**
	 * @return the tChargedDwnlCnt
	 */
	public long gettChargedDwnlCnt() {
		return tChargedDwnlCnt;
	}

	/**
	 * @param tChargedDwnlCnt the tChargedDwnlCnt to set
	 */
	public void settChargedDwnlCnt(long tChargedDwnlCnt) {
		this.tChargedDwnlCnt = tChargedDwnlCnt;
	}

	/**
	 * @return the tCancelDwnlCnt
	 */
	public long gettCancelDwnlCnt() {
		return tCancelDwnlCnt;
	}

	/**
	 * @param tCancelDwnlCnt the tCancelDwnlCnt to set
	 */
	public void settCancelDwnlCnt(long tCancelDwnlCnt) {
		this.tCancelDwnlCnt = tCancelDwnlCnt;
	}

	/**
	 * @return the tCardDwnlCnt
	 */
	public long gettCardDwnlCnt() {
		return tCardDwnlCnt;
	}

	/**
	 * @param tCardDwnlCnt the tCardDwnlCnt to set
	 */
	public void settCardDwnlCnt(long tCardDwnlCnt) {
		this.tCardDwnlCnt = tCardDwnlCnt;
	}

	/**
	 * @return the tPhoneDwnlCnt
	 */
	public long gettPhoneDwnlCnt() {
		return tPhoneDwnlCnt;
	}

	/**
	 * @param tPhoneDwnlCnt the tPhoneDwnlCnt to set
	 */
	public void settPhoneDwnlCnt(long tPhoneDwnlCnt) {
		this.tPhoneDwnlCnt = tPhoneDwnlCnt;
	}

	/**
	 * @return the tCashDwnlCnt
	 */
	public long gettCashDwnlCnt() {
		return tCashDwnlCnt;
	}

	/**
	 * @param tCashDwnlCnt the tCashDwnlCnt to set
	 */
	public void settCashDwnlCnt(long tCashDwnlCnt) {
		this.tCashDwnlCnt = tCashDwnlCnt;
	}

	/**
	 * @return the tPayCnt
	 */
	public long gettPayCnt() {
		return tPayCnt;
	}

	/**
	 * @param tPayCnt the tPayCnt to set
	 */
	public void settPayCnt(long tPayCnt) {
		this.tPayCnt = tPayCnt;
	}

	/**
	 * @return the tFreeCnt
	 */
	public long gettFreeCnt() {
		return tFreeCnt;
	}

	/**
	 * @param tFreeCnt the tFreeCnt to set
	 */
	public void settFreeCnt(long tFreeCnt) {
		this.tFreeCnt = tFreeCnt;
	}

	/**
	 * @return the tCancelCnt
	 */
	public long gettCancelCnt() {
		return tCancelCnt;
	}

	/**
	 * @param tCancelCnt the tCancelCnt to set
	 */
	public void settCancelCnt(long tCancelCnt) {
		this.tCancelCnt = tCancelCnt;
	}

	/**
	 * @return the tCancelAmt
	 */
	public long gettCancelAmt() {
		return tCancelAmt;
	}

	/**
	 * @param tCancelAmt the tCancelAmt to set
	 */
	public void settCancelAmt(long tCancelAmt) {
		this.tCancelAmt = tCancelAmt;
	}

	/**
	 * @return the tCardAmt
	 */
	public long gettCardAmt() {
		return tCardAmt;
	}

	/**
	 * @param tCardAmt the tCardAmt to set
	 */
	public void settCardAmt(long tCardAmt) {
		this.tCardAmt = tCardAmt;
	}

	/**
	 * @return the tPhoneAmt
	 */
	public long gettPhoneAmt() {
		return tPhoneAmt;
	}

	/**
	 * @param tPhoneAmt the tPhoneAmt to set
	 */
	public void settPhoneAmt(long tPhoneAmt) {
		this.tPhoneAmt = tPhoneAmt;
	}

	/**
	 * @return the tCashAmt
	 */
	public long gettCashAmt() {
		return tCashAmt;
	}

	/**
	 * @param tCashAmt the tCashAmt to set
	 */
	public void settCashAmt(long tCashAmt) {
		this.tCashAmt = tCashAmt;
	}

	/**
	 * @return the tSubAmt
	 */
	public long gettSubAmt() {
		return tSubAmt;
	}

	/**
	 * @param tSubAmt the tSubAmt to set
	 */
	public void settSubAmt(long tSubAmt) {
		this.tSubAmt = tSubAmt;
	}

	/**
	 * @return the tProdprc
	 */
	public long gettProdprc() {
		return tProdprc;
	}

	/**
	 * @param tProdprc the tProdprc to set
	 */
	public void settProdprc(long tProdprc) {
		this.tProdprc = tProdprc;
	}

	/**
	 * @return the tPayAmt
	 */
	public long gettPayAmt() {
		return tPayAmt;
	}

	/**
	 * @param tPayAmt the tPayAmt to set
	 */
	public void settPayAmt(long tPayAmt) {
		this.tPayAmt = tPayAmt;
	}

	/**
	 * @return the tCardPay
	 */
	public long gettCardPay() {
		return tCardPay;
	}

	/**
	 * @param tCardPay the tCardPay to set
	 */
	public void settCardPay(long tCardPay) {
		this.tCardPay = tCardPay;
	}

	/**
	 * @return the tPhonePay
	 */
	public long gettPhonePay() {
		return tPhonePay;
	}

	/**
	 * @param tPhonePay the tPhonePay to set
	 */
	public void settPhonePay(long tPhonePay) {
		this.tPhonePay = tPhonePay;
	}

	/**
	 * @return the tCashPay
	 */
	public long gettCashPay() {
		return tCashPay;
	}

	/**
	 * @param tCashPay the tCashPay to set
	 */
	public void settCashPay(long tCashPay) {
		this.tCashPay = tCashPay;
	}

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
	 * @return the saleYNName
	 */
	public String getSaleYNName() {
		return saleYNName;
	}

	/**
	 * @param saleYNName the saleYNName to set
	 */
	public void setSaleYNName(String saleYNName) {
		this.saleYNName = saleYNName;
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
	 * @return the totalSaleCount
	 */
	public String getTotalSaleCount() {
		return totalSaleCount;
	}

	/**
	 * @param totalSaleCount the totalSaleCount to set
	 */
	public void setTotalSaleCount(String totalSaleCount) {
		this.totalSaleCount = totalSaleCount;
	}

	/**
	 * @return the totalCancelCount
	 */
	public String getTotalCancelCount() {
		return totalCancelCount;
	}

	/**
	 * @param totalCancelCount the totalCancelCount to set
	 */
	public void setTotalCancelCount(String totalCancelCount) {
		this.totalCancelCount = totalCancelCount;
	}

	/**
	 * @return the saleYN
	 */
	public String getSaleYN() {
		return saleYN;
	}

	/**
	 * @param saleYN the saleYN to set
	 */
	public void setSaleYN(String saleYN) {
		this.saleYN = saleYN;
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
	 * @return the subtotalAmt
	 */
	public long getSubtotalAmt() {
		return subtotalAmt;
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
	public void setTotalCount(Long totalCount) {
		this.total_count = totalCount;
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
