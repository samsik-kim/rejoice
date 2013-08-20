package com.nmimo.data.review.info;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BanrInfo implements Serializable{
	
	/** 페이징 시작 번호 */
	private int startNum;
	/** 페이징 끝 번호 */
	private int endNum;
	/** */
	private int pageGroupSize = 10;
	/** 총글수 */
	private int totalCount;
	
	/** 배너본문관리 일련번호 */
	private String banrBdyAdmSeq;
	/** 전화구분값 */
	private String telDivVal;
	/** 배너내용 */
	private String banrSbst;
	/** 배너파일명*/
	private String banrFileNm;
	/** 내용우선순위 값 */
	private String sbstPrirtVal;
	/** 배너 사용 유무 */
	private String banrUseYn;
	/** 생성일시 */
	private String cretDt;
	/** 수정일시 */
	private String amdDt;
	/**
	 * @return the banrBdyAdmSeq
	 */
	public String getBanrBdyAdmSeq() {
		return banrBdyAdmSeq;
	}
	/**
	 * @param banrBdyAdmSeq the banrBdyAdmSeq to set
	 */
	public void setBanrBdyAdmSeq(String banrBdyAdmSeq) {
		this.banrBdyAdmSeq = banrBdyAdmSeq;
	}
	/**
	 * @return the telDivVal
	 */
	public String getTelDivVal() {
		return telDivVal;
	}
	/**
	 * @param telDivVal the telDivVal to set
	 */
	public void setTelDivVal(String telDivVal) {
		this.telDivVal = telDivVal;
	}
	/**
	 * @return the banrSbst
	 */
	public String getBanrSbst() {
		return banrSbst;
	}
	/**
	 * @param banrSbst the banrSbst to set
	 */
	public void setBanrSbst(String banrSbst) {
		this.banrSbst = banrSbst;
	}
	/**
	 * @return the banrFileNm
	 */
	public String getBanrFileNm() {
		return banrFileNm;
	}
	/**
	 * @param banrFileNm the banrFileNm to set
	 */
	public void setBanrFileNm(String banrFileNm) {
		this.banrFileNm = banrFileNm;
	}
	/**
	 * @return the sbstPrirtVal
	 */
	public String getSbstPrirtVal() {
		return sbstPrirtVal;
	}
	/**
	 * @param sbstPrirtVal the sbstPrirtVal to set
	 */
	public void setSbstPrirtVal(String sbstPrirtVal) {
		this.sbstPrirtVal = sbstPrirtVal;
	}
	/**
	 * @return the banrUseYn
	 */
	public String getBanrUseYn() {
		return banrUseYn;
	}
	/**
	 * @param banrUseYn the banrUseYn to set
	 */
	public void setBanrUseYn(String banrUseYn) {
		this.banrUseYn = banrUseYn;
	}
	/**
	 * @return the cretDt
	 */
	public String getCretDt() {
		return cretDt;
	}
	/**
	 * @param cretDt the cretDt to set
	 */
	public void setCretDt(String cretDt) {
		this.cretDt = cretDt;
	}
	/**
	 * @return the amdDt
	 */
	public String getAmdDt() {
		return amdDt;
	}
	/**
	 * @param amdDt the amdDt to set
	 */
	public void setAmdDt(String amdDt) {
		this.amdDt = amdDt;
	}
	/**
	 * @return the startNum
	 */
	public int getStartNum() {
		return startNum;
	}
	/**
	 * @param startNum the startNum to set
	 */
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	/**
	 * @return the endNum
	 */
	public int getEndNum() {
		return endNum;
	}
	/**
	 * @param endNum the endNum to set
	 */
	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}
	/**
	 * @return the pageGroupSize
	 */
	public int getPageGroupSize() {
		return pageGroupSize;
	}
	/**
	 * @param pageGroupSize the pageGroupSize to set
	 */
	public void setPageGroupSize(int pageGroupSize) {
		this.pageGroupSize = pageGroupSize;
	}
	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}