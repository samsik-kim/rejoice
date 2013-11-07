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
package com.omp.admin.contents.announce.model;

import com.omp.commons.model.CommonModel;
import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;

/**
 * 카테고리관리- Model
 * 
 * @author redaano
 * @version 0.1
 */
@SuppressWarnings("serial")
public class DpAnoc extends CommonModel implements Pagenateable {

	public DpAnoc() {}

	public DpAnoc(int anocSeq) {
		this.anocSeq = anocSeq;
	}

	private int anocSeq = 0;
	private String anocCd = null;
	private String anocTitl = null;
	private String anocCont = null;
	private String anocStrtDttm = null;
	private String anocEndDttm = null;
	private int readCnt = 0;
	private String delYn = null;
	private String regId = null;
	private String regDttm = null;
	private String updId = null;
	private String updDttm = null;

	private String anocStrtDd = null;
	private String anocStrtHh = null;
	private String anocStrtMm = null;
	private String anocEndDd = null;
	private String anocEndHh = null;
	private String anocEndMm = null;

	private String regNm = null;

	private String srchAnocCd = null;

	private PagenateInfoModel page = new PagenateInfoModel(10);
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

	public int getAnocSeq() {
		return anocSeq;
	}

	public void setAnocSeq(int anocSeq) {
		this.anocSeq = anocSeq;
	}

	public String getAnocCd() {
		return anocCd;
	}

	public void setAnocCd(String anocCd) {
		this.anocCd = anocCd;
	}

	public String getAnocTitl() {
		return anocTitl;
	}

	public void setAnocTitl(String anocTitl) {
		this.anocTitl = anocTitl;
	}

	public String getAnocCont() {
		return anocCont;
	}

	public void setAnocCont(String anocCont) {
		this.anocCont = anocCont;
	}

	public String getAnocStrtDttm() {
		return anocStrtDttm;
	}

	public void setAnocStrtDttm(String anocStrtDttm) {
		this.anocStrtDttm = anocStrtDttm;
	}

	public String getAnocEndDttm() {
		return anocEndDttm;
	}

	public void setAnocEndDttm(String anocEndDttm) {
		this.anocEndDttm = anocEndDttm;
	}

	public int getReadCnt() {
		return readCnt;
	}

	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
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

	public String getAnocStrtDd() {
		return anocStrtDd;
	}

	public void setAnocStrtDd(String anocStrtDd) {
		this.anocStrtDd = anocStrtDd;
	}

	public String getAnocStrtHh() {
		return anocStrtHh;
	}

	public void setAnocStrtHh(String anocStrtHh) {
		this.anocStrtHh = anocStrtHh;
	}

	public String getAnocStrtMm() {
		return anocStrtMm;
	}

	public void setAnocStrtMm(String anocStrtMm) {
		this.anocStrtMm = anocStrtMm;
	}

	public String getAnocEndDd() {
		return anocEndDd;
	}

	public void setAnocEndDd(String anocEndDd) {
		this.anocEndDd = anocEndDd;
	}

	public String getAnocEndHh() {
		return anocEndHh;
	}

	public void setAnocEndHh(String anocEndHh) {
		this.anocEndHh = anocEndHh;
	}

	public String getAnocEndMm() {
		return anocEndMm;
	}

	public void setAnocEndMm(String anocEndMm) {
		this.anocEndMm = anocEndMm;
	}

	public String getRegNm() {
		return regNm;
	}

	public void setRegNm(String regNm) {
		this.regNm = regNm;
	}

	public String getSrchAnocCd() {
		return srchAnocCd;
	}

	public void setSrchAnocCd(String srchAnocCd) {
		this.srchAnocCd = srchAnocCd;
	}

}
