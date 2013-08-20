package com.nmimo.data.notice.info;

import java.io.Serializable;

@SuppressWarnings("serial")
public class NoticeInfo implements Serializable{

//	private MultipartFile file1;
	
	
	private String ntfSeq;		
	private String userNm;			
	private String cretrId;			
	private String titleNm;			
	private String ntfSbst;			
	private String atcFileNm;			
	private String retvTmscnt;			
	private String cretDt;			
	private String amdDt;			
	private String popupYn;			
	private String popupFnsDate;
	
	/** 페이징 시작 번호 */
	private int startNum;
	/** 페이징 끝 번호 */
	private int endNum;
	/** */
	private int pageGroupSize = 10;
	/** 총글수 */
	private int totalCount;
	/** 검색 값 */
	private String searchStr;
	/** 검색 구분 */
	private String searchType;
	/**
	 * @return the ntfSeq
	 */
	public String getNtfSeq() {
		return ntfSeq;
	}
	/**
	 * @param ntfSeq the ntfSeq to set
	 */
	public void setNtfSeq(String ntfSeq) {
		this.ntfSeq = ntfSeq;
	}
	/**
	 * @return the userNm
	 */
	public String getUserNm() {
		return userNm;
	}
	/**
	 * @param userNm the userNm to set
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	/**
	 * @return the cretrId
	 */
	public String getCretrId() {
		return cretrId;
	}
	/**
	 * @param cretrId the cretrId to set
	 */
	public void setCretrId(String cretrId) {
		this.cretrId = cretrId;
	}
	/**
	 * @return the titleNm
	 */
	public String getTitleNm() {
		return titleNm;
	}
	/**
	 * @param titleNm the titleNm to set
	 */
	public void setTitleNm(String titleNm) {
		this.titleNm = titleNm;
	}
	/**
	 * @return the ntfSbst
	 */
	public String getNtfSbst() {
		return ntfSbst;
	}
	/**
	 * @param ntfSbst the ntfSbst to set
	 */
	public void setNtfSbst(String ntfSbst) {
		this.ntfSbst = ntfSbst;
	}
	/**
	 * @return the atcFileNm
	 */
	public String getAtcFileNm() {
		return atcFileNm;
	}
	/**
	 * @param atcFileNm the atcFileNm to set
	 */
	public void setAtcFileNm(String atcFileNm) {
		this.atcFileNm = atcFileNm;
	}
	/**
	 * @return the retvTmscnt
	 */
	public String getRetvTmscnt() {
		return retvTmscnt;
	}
	/**
	 * @param retvTmscnt the retvTmscnt to set
	 */
	public void setRetvTmscnt(String retvTmscnt) {
		this.retvTmscnt = retvTmscnt;
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
	 * @return the popupYn
	 */
	public String getPopupYn() {
		return popupYn;
	}
	/**
	 * @param popupYn the popupYn to set
	 */
	public void setPopupYn(String popupYn) {
		this.popupYn = popupYn;
	}
	/**
	 * @return the popupFnsDate
	 */
	public String getPopupFnsDate() {
		return popupFnsDate;
	}
	/**
	 * @param popupFnsDate the popupFnsDate to set
	 */
	public void setPopupFnsDate(String popupFnsDate) {
		this.popupFnsDate = popupFnsDate;
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
	/**
	 * @return the searchStr
	 */
	public String getSearchStr() {
		return searchStr;
	}
	/**
	 * @param searchStr the searchStr to set
	 */
	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}
	/**
	 * @return the searchType
	 */
	public String getSearchType() {
		return searchType;
	}
	/**
	 * @param searchType the searchType to set
	 */
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
}