/*
 * COPYRIGHT(c) SK telecom 2011
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author | Date        | Description
 * ---------------------------------------
 * bcpark | 2011. 4. 8. | Description
 *
 */
package com.omp.admin.phonemapping.model;

import com.omp.commons.model.TotalCountCarriable;

/**
 * 단말 조회시 엑셀 다운로드에 사용하는 상품 Domain
 * 
 * @author bcpark
 * @version 0.1
 */
public class PhoneMappingContents implements TotalCountCarriable {
	private String aid;
	private String pid;
	private String cid;
	private String scid;
	private String verifyReqVer;
	private String prodNm;
	private String saleStat;
	private String saleStatNm;
	private String phoneModelCd;

	private Long total_count;

	@Override
	public Long getTotalCount() {
		return this.total_count;
	}

	@Override
	public void setTotalCount(Long totalCount) {
		this.total_count = totalCount;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getScid() {
		return scid;
	}

	public void setScid(String scid) {
		this.scid = scid;
	}

	public String getVerifyReqVer() {
		return verifyReqVer;
	}

	public void setVerifyReqVer(String verifyReqVer) {
		this.verifyReqVer = verifyReqVer;
	}

	public String getProdNm() {
		return prodNm;
	}

	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
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

	public String getPhoneModelCd() {
		return phoneModelCd;
	}

	public void setPhoneModelCd(String phoneModelCd) {
		this.phoneModelCd = phoneModelCd;
	}

}
