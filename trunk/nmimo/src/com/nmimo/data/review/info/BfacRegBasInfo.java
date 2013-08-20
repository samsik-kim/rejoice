package com.nmimo.data.review.info;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BfacRegBasInfo implements Serializable{
	
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
	
	/** 사전등록일련번호 */
	private String bfacRegSeq;
	/** 사용자 명 */
	private String userNm;
	/** 사용자부서명 */
	private String userDeptNm;
	/** 작업명 */
	private String wrkNm;
	/** 발송예약일자 */
	private String dspRsrvDate;
	/** 생성일시 */
	private String cretDt;
	/** 발송내용 */
	private String dspSbst;
	/** 수신대상내용 */
	private String rcvTgtSbst;
	/** 승인상태값 */
	private String apvSttusVal;
	/** 예약번호 */
	private String rsrvNo;
	/** 사용여부 */
	private String useYn;
	/** 반려사유내용 */
	private String rcessWhySbst;
	/** 메세지유형값 */
	private String msgTypeVal;
	/** 발송건수 */
	private String dspCascnt;			
	/** 생성자아이디 */
	private String cretrId;
	/**
	 * @return the bfacRegSeq
	 */
	public String getBfacRegSeq() {
		return bfacRegSeq;
	}
	/**
	 * @param bfacRegSeq the bfacRegSeq to set
	 */
	public void setBfacRegSeq(String bfacRegSeq) {
		this.bfacRegSeq = bfacRegSeq;
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
	 * @return the userDeptNm
	 */
	public String getUserDeptNm() {
		return userDeptNm;
	}
	/**
	 * @param userDeptNm the userDeptNm to set
	 */
	public void setUserDeptNm(String userDeptNm) {
		this.userDeptNm = userDeptNm;
	}
	/**
	 * @return the wrkNm
	 */
	public String getWrkNm() {
		return wrkNm;
	}
	/**
	 * @param wrkNm the wrkNm to set
	 */
	public void setWrkNm(String wrkNm) {
		this.wrkNm = wrkNm;
	}
	/**
	 * @return the dspRsrvDate
	 */
	public String getDspRsrvDate() {
		return dspRsrvDate;
	}
	/**
	 * @param dspRsrvDate the dspRsrvDate to set
	 */
	public void setDspRsrvDate(String dspRsrvDate) {
		this.dspRsrvDate = dspRsrvDate;
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
	 * @return the dspSbst
	 */
	public String getDspSbst() {
		return dspSbst;
	}
	/**
	 * @param dspSbst the dspSbst to set
	 */
	public void setDspSbst(String dspSbst) {
		this.dspSbst = dspSbst;
	}
	/**
	 * @return the rcvTgtSbst
	 */
	public String getRcvTgtSbst() {
		return rcvTgtSbst;
	}
	/**
	 * @param rcvTgtSbst the rcvTgtSbst to set
	 */
	public void setRcvTgtSbst(String rcvTgtSbst) {
		this.rcvTgtSbst = rcvTgtSbst;
	}
	/**
	 * @return the apvSttusVal
	 */
	public String getApvSttusVal() {
		return apvSttusVal;
	}
	/**
	 * @param apvSttusVal the apvSttusVal to set
	 */
	public void setApvSttusVal(String apvSttusVal) {
		this.apvSttusVal = apvSttusVal;
	}
	/**
	 * @return the rsrvNo
	 */
	public String getRsrvNo() {
		return rsrvNo;
	}
	/**
	 * @param rsrvNo the rsrvNo to set
	 */
	public void setRsrvNo(String rsrvNo) {
		this.rsrvNo = rsrvNo;
	}
	/**
	 * @return the useYn
	 */
	public String getUseYn() {
		return useYn;
	}
	/**
	 * @param useYn the useYn to set
	 */
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	/**
	 * @return the rcessWhySbst
	 */
	public String getRcessWhySbst() {
		return rcessWhySbst;
	}
	/**
	 * @param rcessWhySbst the rcessWhySbst to set
	 */
	public void setRcessWhySbst(String rcessWhySbst) {
		this.rcessWhySbst = rcessWhySbst;
	}
	/**
	 * @return the msgTypeVal
	 */
	public String getMsgTypeVal() {
		return msgTypeVal;
	}
	/**
	 * @param msgTypeVal the msgTypeVal to set
	 */
	public void setMsgTypeVal(String msgTypeVal) {
		this.msgTypeVal = msgTypeVal;
	}
	/**
	 * @return the dspCascnt
	 */
	public String getDspCascnt() {
		return dspCascnt;
	}
	/**
	 * @param dspCascnt the dspCascnt to set
	 */
	public void setDspCascnt(String dspCascnt) {
		this.dspCascnt = dspCascnt;
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