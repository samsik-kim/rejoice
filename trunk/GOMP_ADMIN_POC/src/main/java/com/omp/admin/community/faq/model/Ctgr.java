package com.omp.admin.community.faq.model;

import com.omp.commons.model.CommonModel;

/**
 * 공지사항 정보 정의 클래스.
 * 테이블 필드 외의 다른 정보는 타 객체를 만들어서 사용합니다.
 * @author soohee
 * 
 */
@SuppressWarnings("serial")
public class Ctgr extends CommonModel {

	private String ctgrCd;
	private String ctgrNm;
	private String highCtgr;
	private String ctgrLevelCd;
	private String openYn;
	private String displayOrder;
	private String delYn;
	private String insDttm;
	private String insBy;
	private String updDttm;
	private String updBy;
	private String delDttm;

	private String gubun;
	private int cntFaq;

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

	public String getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
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

	public String getDelDttm() {
		return delDttm;
	}

	public void setDelDttm(String delDttm) {
		this.delDttm = delDttm;
	}

	public String getGubun() {
		return gubun;
	}

	public void setGubun(String gubun) {
		this.gubun = gubun;
	}

	public int getCntFaq() {
		return cntFaq;
	}

	public void setCntFaq(int cntFaq) {
		this.cntFaq = cntFaq;
	}

}
