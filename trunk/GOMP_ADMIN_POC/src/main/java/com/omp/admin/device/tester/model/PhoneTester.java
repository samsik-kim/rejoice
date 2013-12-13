/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 * redaano    2009. 4. 7.    Description
 *
 */
package com.omp.admin.device.tester.model;

import com.omp.commons.model.CommonModel;
import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;

/**
 * PHONE_INFO Model
 * 
 * @author redaano
 * @version 0.1
 */
@SuppressWarnings("serial")
public class PhoneTester extends CommonModel implements Pagenateable {

	private String testerId = null;
	private String dscr = null;
	private String regId = null;
	private String regDttm = null;
	private String updId = null;
	private String updDttm = null;

	private String regNm = null;
	private String updNm = null;

	private PagenateInfoModel page = new PagenateInfoModel(20);
	private Long totalCount;

	public PagenateInfoModel getPage() {
		return this.page;
	}

	public Long getTotalCount() {
		return this.totalCount;
	}

	public Long setTotalCount(Long totalCount) {
		return this.totalCount = totalCount;
	}

	public String getTesterId() {
		return testerId;
	}

	public void setTesterId(String testerId) {
		this.testerId = testerId;
	}

	public String getDscr() {
		return dscr;
	}

	public void setDscr(String dscr) {
		this.dscr = dscr;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getRegDttm() {
		return regDttm;
	}

	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}

	public String getUpdId() {
		return updId;
	}

	public void setUpdId(String updId) {
		this.updId = updId;
	}

	public String getUpdDttm() {
		return updDttm;
	}

	public void setUpdDttm(String updDttm) {
		this.updDttm = updDttm;
	}

	public String getRegNm() {
		return regNm;
	}

	public void setRegNm(String regNm) {
		this.regNm = regNm;
	}

	public String getUpdNm() {
		return updNm;
	}

	public void setUpdNm(String updNm) {
		this.updNm = updNm;
	}

	public String toString() {
		String LF = System.getProperty("line.separator");
		StringBuffer sb = new StringBuffer();
		sb.append(this.getClass().toString()).append(LF);
		sb.append(" testerId='").append(testerId).append("'").append(LF);
		sb.append(",dscr='").append(dscr).append("'").append(LF);
		sb.append(",regId='").append(regId).append("'").append(LF);
		sb.append(",updId='").append(updId).append("'").append(LF);
		sb.append("}");
		return sb.toString();
	}

}
