/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 * redaano    2009. 5. 14.    Description
 *
 */
package com.omp.dev.community.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * TBL_CTGR Model
 * 
 * @author redaano
 * @version 0.1
 */
public class Ctgr {
	private String ctgrCd = ""; // 카테고리_코드
	private String ctgrNm = ""; // 카테고리_명
	private String highCtgr = ""; // 상위_카테고리
	private String ctgrLevelCd = ""; // 카테고리_레벨_코드
	private String openYn = ""; // 공개_여부
	private String delYn = ""; // 삭제_여부
	private String delDttm = ""; // 삭제_일시
	private String insDttm = ""; // 등록_일시
	private String insBy = ""; // 등록_자
	private String updDttm = ""; // 수정_일시
	private String updBy = ""; // 수정_자


	public String getCtgrCd() {
		return ctgrCd;
	}


	public void setCtgrCd(String ctgrCd) {
		this.ctgrCd = ctgrCd;
	}


	public String getCtgrNm() {
		return ctgrNm;
	}


	public void setCtgrNm(String ctgrNm) {
		this.ctgrNm = ctgrNm;
	}


	public String getHighCtgr() {
		return highCtgr;
	}


	public void setHighCtgr(String highCtgr) {
		this.highCtgr = highCtgr;
	}


	public String getCtgrLevelCd() {
		return ctgrLevelCd;
	}


	public void setCtgrLevelCd(String ctgrLevelCd) {
		this.ctgrLevelCd = ctgrLevelCd;
	}


	public String getOpenYn() {
		return openYn;
	}


	public void setOpenYn(String openYn) {
		this.openYn = openYn;
	}


	public String getDelYn() {
		return delYn;
	}


	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}


	public String getDelDttm() {
		return delDttm;
	}


	public void setDelDttm(String delDttm) {
		this.delDttm = delDttm;
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
	
	@Override
    public String toString() { 
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); 
    } 

}