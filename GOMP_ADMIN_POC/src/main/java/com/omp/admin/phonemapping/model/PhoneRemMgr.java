/*
 * COPYRIGHT(c) SK telecom 2011
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author | Date        | Description
 * ---------------------------------------
 * bcpark | 2011. 4. 11. | Description
 *
 */
package com.omp.admin.phonemapping.model;

import com.omp.commons.model.TotalCountCarriable;

/**
 * 단말Mapping처리결과에서 사용하는 단말매핑관리 Domain
 * 
 * @author bcpark
 * @version 0.1
 */
public class PhoneRemMgr implements TotalCountCarriable {
	private String txId;
	private String mappingType;
	private String mappingTypeNm;
	private String targetPhoneModelCd;
	private String standardPhoneModelCd;
	private String insDttm;
	private String insBy;
	private String insIp;
	private String mappingStat;
	private String mappingStatNm;
	private String updDttm;
	private String updBy;

	private int rnum;
	// paging
	private Long total_count;

	@Override
	public String toString() {
		return mappingTypeNm + ", " + targetPhoneModelCd + ", " + mappingStatNm;
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

	@Override
	public Long getTotalCount() {
		return this.total_count;
	}

	@Override
	public void setTotalCount(Long totalCount) {
		this.total_count = totalCount;
	}

	public String getTxId() {
		return txId;
	}

	public void setTxId(String txId) {
		this.txId = txId;
	}

	public String getMappingType() {
		return mappingType;
	}

	public void setMappingType(String mappingType) {
		this.mappingType = mappingType;
	}

	public String getMappingTypeNm() {
		return mappingTypeNm;
	}

	public void setMappingTypeNm(String mappingTypeNm) {
		this.mappingTypeNm = mappingTypeNm;
	}

	public String getTargetPhoneModelCd() {
		return targetPhoneModelCd;
	}

	public void setTargetPhoneModelCd(String targetPhoneModelCd) {
		this.targetPhoneModelCd = targetPhoneModelCd;
	}

	public String getStandardPhoneModelCd() {
		return standardPhoneModelCd;
	}

	public void setStandardPhoneModelCd(String standardPhoneModelCd) {
		this.standardPhoneModelCd = standardPhoneModelCd;
	}

	public String getInsDttm() {
		return insDttm;
	}

	public void setInsDttm(String insDttm) {
		this.insDttm = insDttm;
	}

	public String getInsBy() {
		return insBy;
	}

	public void setInsBy(String insBy) {
		this.insBy = insBy;
	}

	public String getInsIp() {
		return insIp;
	}

	public void setInsIp(String insIp) {
		this.insIp = insIp;
	}

	public String getMappingStat() {
		return mappingStat;
	}

	public void setMappingStat(String mappingStat) {
		this.mappingStat = mappingStat;
	}

	public String getMappingStatNm() {
		return mappingStatNm;
	}

	public void setMappingStatNm(String mappingStatNm) {
		this.mappingStatNm = mappingStatNm;
	}

	public String getUpdDttm() {
		return updDttm;
	}

	public void setUpdDttm(String updDttm) {
		this.updDttm = updDttm;
	}

	public String getUpdBy() {
		return updBy;
	}

	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}

}
