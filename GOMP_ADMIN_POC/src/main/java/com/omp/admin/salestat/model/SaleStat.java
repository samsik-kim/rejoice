package com.omp.admin.salestat.model;

import com.omp.commons.model.TotalCountCarriable;

public class SaleStat  implements TotalCountCarriable {
	
	// dailylist
	private String saleDt;
	private long saleAmt;
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
	private String paymethod;
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

	public String getPaymethod() {
		return paymethod;
	}

	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
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
	
}
