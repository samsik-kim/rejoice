/*
 * COPYRIGHT(c) SK telecom 2011
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author | Date        | Description
 * ---------------------------------------
 * bcpark | 2011. 3. 14. | Category Domain
 *
 */
package com.omp.admin.product.model;

/**
 * 상품 카테고리 도메인
 * 
 * @author Administrator
 * @version 0.1
 */
public class DpCat {
	private String dpCatNo;
	private String dpCatNm;
	private String dpCatDesc;

	public String getDpCatNo() {
		return dpCatNo;
	}

	public void setDpCatNo(String dpCatNo) {
		this.dpCatNo = dpCatNo;
	}

	public String getDpCatNm() {
		return dpCatNm;
	}

	public void setDpCatNm(String dpCatNm) {
		this.dpCatNm = dpCatNm;
	}

	public String getDpCatDesc() {
		return dpCatDesc;
	}

	public void setDpCatDesc(String dpCatDesc) {
		this.dpCatDesc = dpCatDesc;
	}
}