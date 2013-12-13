/*
 * COPYRIGHT(c) SK telecom 2011
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author | Date        | Description
 * ---------------------------------------
 * bcpark | 2011. 4. 11. | Description
 *
 */
package com.omp.admin.phonemapping.model;

import com.omp.commons.model.TotalCountCarriable;

/**
 * 단말매핑 처리결과 상세화면에서 사용하는 <Br/>
 * 단말 mapping된 상품 domain
 * 
 * @author bcpark
 * @version 0.1
 */
public class PhoneRemScid implements TotalCountCarriable {
	private String txId;
	private String cid;
	private String aid;
	private String scid;
	private String prodNm;
	private String ctgrCd2;
	private String ctgrNm2;
	private String mbrId;
	private String saleStat;
	private String saleStatNm;

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

	@Override
	public Long getTotalCount() {
		return this.total_count;
	}

	@Override
	public void setTotalCount(Long totalCount) {
		this.total_count = totalCount;
	}

	public String getTxId() {
		return txId;
	}

	public void setTxId(String txId) {
		this.txId = txId;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getScid() {
		return scid;
	}

	public void setScid(String scid) {
		this.scid = scid;
	}

	public String getProdNm() {
		return prodNm;
	}

	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
	}

	public String getCtgrCd2() {
		return ctgrCd2;
	}

	public void setCtgrCd2(String ctgrCd2) {
		this.ctgrCd2 = ctgrCd2;
	}

	public String getCtgrNm2() {
		return ctgrNm2;
	}

	public void setCtgrNm2(String ctgrNm2) {
		this.ctgrNm2 = ctgrNm2;
	}

	public String getMbrId() {
		return mbrId;
	}

	public void setMbrId(String mbrId) {
		this.mbrId = mbrId;
	}

	public String getSaleStat() {
		return saleStat;
	}

	public void setSaleStat(String saleStat) {
		this.saleStat = saleStat;
	}

	public String getSaleStatNm() {
		return saleStatNm;
	}

	public void setSaleStatNm(String saleStatNm) {
		this.saleStatNm = saleStatNm;
	}

}
