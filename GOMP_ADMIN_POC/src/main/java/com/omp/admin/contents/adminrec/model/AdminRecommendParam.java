/*
 * COPYRIGHT(c) SK telecom 2011
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author | Date         | Description
 * ---------------------------------------
 * sw.joo | 2011. 3. 23. | Admin Recommend Parameter Domain
 *
 */
package com.omp.admin.contents.adminrec.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;

/**
 * Category Domain
 * 
 * @author Administrator
 * @version 0.1
 */
public class AdminRecommendParam implements Pagenateable {
	private String dpUpCatNo;
	private String dpCatNo;
	private String scrGbn;
	
	private String dpProdId;
	private String dpProdAid;
	
	private String dpSelectDate;
	private String dpSelectDateDB;
	private String timeType;
	
	//리스트수정
	private String expoYn;
	private String expoPrior;
	private String checkProdId;
	
	//상품조회
	private String dpProdNm;
	private String aid;
	
	//상품등록
	private String regId;
	private String updId;
	private String inProdId;
	
	// paging
	private PagenateInfoModel page = new PagenateInfoModel(20);

	@Override
	public PagenateInfoModel getPage() {
		return this.page;
	}

	// toString
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
	}
	
	
	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	public String getDpSelectDateDB() {
		return dpSelectDateDB;
	}

	public void setDpSelectDateDB(String dpSelectDateDB) {
		this.dpSelectDateDB = dpSelectDateDB;
	}

	public String getExpoYn() {
		return expoYn;
	}

	public void setExpoYn(String expoYn) {
		this.expoYn = expoYn;
	}

	public String getExpoPrior() {
		return expoPrior;
	}

	public void setExpoPrior(String expoPrior) {
		this.expoPrior = expoPrior;
	}

	public String getDpUpCatNo() {
		return dpUpCatNo;
	}
	public void setDpUpCatNo(String dpUpCatNo) {
		this.dpUpCatNo = dpUpCatNo;
	}
	public String getScrGbn() {
		return scrGbn;
	}
	public void setScrGbn(String scrGbn) {
		this.scrGbn = scrGbn;
	}
	public String getDpCatNo() {
		return dpCatNo;
	}
	public void setDpCatNo(String dpCatNo) {
		this.dpCatNo = dpCatNo;
	}
	public String getDpProdId() {
		return dpProdId;
	}
	public void setDpProdId(String dpProdId) {
		this.dpProdId = dpProdId;
	}
	public String getDpProdAid() {
		return dpProdAid;
	}
	public void setDpProdAid(String dpProdAid) {
		this.dpProdAid = dpProdAid;
	}
	public String getDpSelectDate() {
		return dpSelectDate;
	}
	public void setDpSelectDate(String dpSelectDate) {
		this.dpSelectDate = dpSelectDate;
	}

	public String getDpProdNm() {
		return dpProdNm;
	}

	public void setDpProdNm(String dpProdNm) {
		this.dpProdNm = dpProdNm;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getUpdId() {
		return updId;
	}

	public void setUpdId(String updId) {
		this.updId = updId;
	}

	public String getInProdId() {
		return inProdId;
	}

	public void setInProdId(String inProdId) {
		this.inProdId = inProdId;
	}

	public String getCheckProdId() {
		return checkProdId;
	}

	public void setCheckProdId(String checkProdId) {
		this.checkProdId = checkProdId;
	}
	
	
}