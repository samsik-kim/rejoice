/*
 * COPYRIGHT(c) SK telecom 2011
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author | Date        | Description
 * ---------------------------------------
 * bcpark | 2011. 3. 22. | Description
 *
 */
package com.omp.admin.product.model;

import com.omp.commons.model.TotalCountCarriable;

/**
 * 상품 검증 정보 도메인
 * 
 * @author bcpark
 * @version 0.1
 */
public class ContentsVerify implements TotalCountCarriable {
	private String verifyReqVer;
	private String insDttm;
	private String ctEndDt;
	private String verifyPrgrYn;
	private String agrmntStat;
	private String scid;

	private int rnum;
	// paging
	private Long total_count;

	// baseInfo 

	@Override
	public Long getTotalCount() {
		return this.total_count;
	}

	@Override
	public void setTotalCount(Long totalCount) {
		this.total_count = totalCount;
	}

	public String getVerifyReqVer() {
		return verifyReqVer;
	}

	public void setVerifyReqVer(String verifyReqVer) {
		this.verifyReqVer = verifyReqVer;
	}

	public String getInsDttm() {
		return insDttm;
	}

	public void setInsDttm(String insDttm) {
		this.insDttm = insDttm;
	}

	public String getCtEndDt() {
		return ctEndDt;
	}

	public void setCtEndDt(String ctEndDt) {
		this.ctEndDt = ctEndDt;
	}

	public String getVerifyPrgrYn() {
		return verifyPrgrYn;
	}

	public void setVerifyPrgrYn(String verifyPrgrYn) {
		this.verifyPrgrYn = verifyPrgrYn;
	}

	public String getScid() {
		return scid;
	}

	public void setScid(String scid) {
		this.scid = scid;
	}

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

	public String getAgrmntStat() {
		return agrmntStat;
	}

	public void setAgrmntStat(String agrmntStat) {
		this.agrmntStat = agrmntStat;
	}

}
