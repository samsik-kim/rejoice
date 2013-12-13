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

/**
 * 상품 판매상태 변경 도메인
 * 
 * @author bcpark
 * @version 0.1
 */
public class ContentsSaleHistory {
	private String cid;
	private String chngDttm;
	private String saleStat;
	private String insBy;
	private String changeId;
	private String adminChngYn;

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getChngDttm() {
		return chngDttm;
	}

	public void setChngDttm(String chngDttm) {
		this.chngDttm = chngDttm;
	}

	public String getSaleStat() {
		return saleStat;
	}

	public void setSaleStat(String saleStat) {
		this.saleStat = saleStat;
	}

	public String getInsBy() {
		return insBy;
	}

	public void setInsBy(String insBy) {
		this.insBy = insBy;
	}

	public String getChangeId() {
		return changeId;
	}

	public void setChangeId(String changeId) {
		this.changeId = changeId;
	}

	public String getAdminChngYn() {
		return adminChngYn;
	}

	public void setAdminChngYn(String adminChngYn) {
		this.adminChngYn = adminChngYn;
	}

}
