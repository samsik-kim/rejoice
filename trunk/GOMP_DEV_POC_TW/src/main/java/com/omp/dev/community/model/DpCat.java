/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 * redaano    2009. 5. 8.    Description
 *
 */
package com.omp.dev.community.model;

/**
 * TBL_DP_CAT Model
 * 
 * @author redaano
 * @version 0.1
 */
public class DpCat {
	private String dpCatNo = "";
	private String dpCatNm = "";
	private String dpCatDesc = "";


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