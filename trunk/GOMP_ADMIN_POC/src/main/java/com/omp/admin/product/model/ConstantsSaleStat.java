/*
 * COPYRIGHT(c) SK telecom 2011
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author | Date        | Description
 * ---------------------------------------
 * bcpark | 2011. 4. 5. | Description
 *
 */
package com.omp.admin.product.model;

/**
 * 상품 판매상태 및 검증상태
 * 
 * @author bcpark
 * @version 0.1
 */
public class ConstantsSaleStat {
	private String cid;
	private String saleStat;
	private String verifyPrgrYn;

	public final String getCid() {
		return cid;
	}

	public final void setCid(String cid) {
		this.cid = cid;
	}

	public final String getSaleStat() {
		return saleStat;
	}

	public final void setSaleStat(String saleStat) {
		this.saleStat = saleStat;
	}

	public final String getVerifyPrgrYn() {
		return verifyPrgrYn;
	}

	public final void setVerifyPrgrYn(String verifyPrgrYn) {
		this.verifyPrgrYn = verifyPrgrYn;
	}

}
