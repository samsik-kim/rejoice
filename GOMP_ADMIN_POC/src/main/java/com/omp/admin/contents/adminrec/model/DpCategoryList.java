/*
 * COPYRIGHT(c) SK telecom 2011
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author | Date         | Description
 * ---------------------------------------
 * sw.joo | 2011. 3. 23. | Contents Domain
 *
 */
package com.omp.admin.contents.adminrec.model;

/**
 * Category Domain
 * 
 * @author Administrator
 * @version 0.1
 */
public class DpCategoryList {
	private String dpCatNo;
	private String dpCatNm;
	private String dpCatDesc;
	private String dpCatPrior;

	public String getDpCatPrior() {
		return dpCatPrior;
	}

	public void setDpCatPrior(String dpCatPrior) {
		this.dpCatPrior = dpCatPrior;
	}

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