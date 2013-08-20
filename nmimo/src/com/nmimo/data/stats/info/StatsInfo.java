package com.nmimo.data.stats.info;

import java.io.Serializable;

import com.nmimo.common.util.StringUtils;

/**
 * <pre>
 * 통계Info 정보
 * </pre>
 * @author Leesh
 *
 */
@SuppressWarnings("serial")
public class StatsInfo implements Serializable {
	
	//Paging처리 공통
	private int currentPage;
	private int pageSize;
	private int totalCount;
	
	private int seq;
	private int sendCnt;
	private int rejCnt;
	private int dupliCnt;
	private int realCnt;
	private int successCnt;
	private int failCnt;
	private int readCnt;
	private int lderrCnt;
	
	private String jobId;
	private String jobNm;
	private String userId;
	private String userNm;
	private String department;
	private String service;
	private String jobType;
	private String jobKind1;
	private String jobKind2;
	private String received;
	private String insertDt;
	private String reserveDt;
	private String final_send_status;
	private String searchCode;
	private String searchCodeVal;
	


	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}
	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
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
	 * @return the seq
	 */
	public int getSeq() {
		return seq;
	}
	/**
	 * @param seq the seq to set
	 */
	public void setSeq(int seq) {
		this.seq = seq;
	}
	/**
	 * @return the sendCnt
	 */
	public int getSendCnt() {
		return sendCnt;
	}
	/**
	 * @param sendCnt the sendCnt to set
	 */
	public void setSendCnt(int sendCnt) {
		this.sendCnt = sendCnt;
	}
	/**
	 * @return the rejCnt
	 */
	public int getRejCnt() {
		return rejCnt;
	}
	/**
	 * @param rejCnt the rejCnt to set
	 */
	public void setRejCnt(int rejCnt) {
		this.rejCnt = rejCnt;
	}
	/**
	 * @return the dupliCnt
	 */
	public int getDupliCnt() {
		return dupliCnt;
	}
	/**
	 * @param dupliCnt the dupliCnt to set
	 */
	public void setDupliCnt(int dupliCnt) {
		this.dupliCnt = dupliCnt;
	}
	/**
	 * @return the realCnt
	 */
	public int getRealCnt() {
		return realCnt;
	}
	/**
	 * @param realCnt the realCnt to set
	 */
	public void setRealCnt(int realCnt) {
		this.realCnt = realCnt;
	}
	/**
	 * @return the successCnt
	 */
	public int getSuccessCnt() {
		return successCnt;
	}
	/**
	 * @param successCnt the successCnt to set
	 */
	public void setSuccessCnt(int successCnt) {
		this.successCnt = successCnt;
	}
	/**
	 * @return the failCnt
	 */
	public int getFailCnt() {
		return failCnt;
	}
	/**
	 * @param failCnt the failCnt to set
	 */
	public void setFailCnt(int failCnt) {
		this.failCnt = failCnt;
	}
	/**
	 * @return the readCnt
	 */
	public int getReadCnt() {
		return readCnt;
	}
	/**
	 * @param readCnt the readCnt to set
	 */
	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}
	/**
	 * @return the lderrCnt
	 */
	public int getLderrCnt() {
		return lderrCnt;
	}
	/**
	 * @param lderrCnt the lderrCnt to set
	 */
	public void setLderrCnt(int lderrCnt) {
		this.lderrCnt = lderrCnt;
	}
	/**
	 * @return the jobId
	 */
	public String getJobId() {
		return jobId;
	}
	/**
	 * @param jobId the jobId to set
	 */
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	/**
	 * @return the jobNm
	 */
	public String getJobNm() {
		return StringUtils.nvlStr(jobNm);
	}
	/**
	 * @param jobNm the jobNm to set
	 */
	public void setJobNm(String jobNm) {
		this.jobNm = jobNm;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return StringUtils.nvlStr(userId);
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the userNm
	 */
	public String getUserNm() {
		return StringUtils.nvlStr(userNm);
	}
	/**
	 * @param userNm the userNm to set
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	/**
	 * @return the department
	 */
	public String getDepartment() {
		return StringUtils.nvlStr(department);
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	/**
	 * @return the service
	 */
	public String getService() {
		return StringUtils.nvlStr(service);
	}
	/**
	 * @param service the service to set
	 */
	public void setService(String service) {
		this.service = service;
	}
	/**
	 * @return the jobType
	 */
	public String getJobType() {
		return StringUtils.nvlStr(jobType);
	}
	/**
	 * @param jobType the jobType to set
	 */
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	/**
	 * @return the jobKind1
	 */
	public String getJobKind1() {
		return StringUtils.nvlStr(jobKind1);
	}
	/**
	 * @param jobKind1 the jobKind1 to set
	 */
	public void setJobKind1(String jobKind1) {
		this.jobKind1 = jobKind1;
	}
	/**
	 * @return the jobKind2
	 */
	public String getJobKind2() {
		return StringUtils.nvlStr(jobKind2);
	}
	/**
	 * @param jobKind2 the jobKind2 to set
	 */
	public void setJobKind2(String jobKind2) {
		this.jobKind2 = jobKind2;
	}
	/**
	 * @return the received
	 */
	public String getReceived() {
		return StringUtils.nvlStr(received);
	}
	/**
	 * @param received the received to set
	 */
	public void setReceived(String received) {
		this.received = received;
	}
	/**
	 * @return the insertDt
	 */
	public String getInsertDt() {
		return StringUtils.nvlStr(insertDt);
	}
	/**
	 * @param insertDt the insertDt to set
	 */
	public void setInsertDt(String insertDt) {
		this.insertDt = insertDt;
	}
	/**
	 * @return the reserveDt
	 */
	public String getReserveDt() {
		return StringUtils.nvlStr(reserveDt);
	}
	/**
	 * @param reserveDt the reserveDt to set
	 */
	public void setReserveDt(String reserveDt) {
		this.reserveDt = reserveDt;
	}
	/**
	 * @return the final_send_status
	 */
	public String getFinal_send_status() {
		return StringUtils.nvlStr(final_send_status);
	}
	/**
	 * @param final_send_status the final_send_status to set
	 */
	public void setFinal_send_status(String final_send_status) {
		this.final_send_status = final_send_status;
	}
	
	/**
	 * @return the searchCode
	 */
	public String getSearchCode() {
		return StringUtils.nvlStr(searchCode);
	}
	/**
	 * @param searchCode the searchCode to set
	 */
	public void setSearchCode(String searchCode) {
		this.searchCode = searchCode;
	}
	/**
	 * @return the searchCodeVal
	 */
	public String getSearchCodeVal() {
		return StringUtils.nvlStr(searchCodeVal);
	}
	/**
	 * @param searchCodeVal the searchCodeVal to set
	 */
	public void setSearchCodeVal(String searchCodeVal) {
		this.searchCodeVal = searchCodeVal;
	}
	
	
	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		sb.setLength(0);
		
		sb.append("seq="+getSeq());		
		sb.append("&").append("sendCnt="+getSendCnt());
		sb.append("&").append("rejCnt="+getRejCnt());
		sb.append("&").append("dupliCnt="+getDupliCnt());
		sb.append("&").append("realCnt="+getRealCnt());
		sb.append("&").append("successCnt="+getSuccessCnt());
		sb.append("&").append("failCnt="+getFailCnt());
		sb.append("&").append("readCnt="+getReadCnt());
		sb.append("&").append("lderrCnt="+getLderrCnt());
		sb.append("&").append("jobId="+getJobId());
		sb.append("&").append("jobNm="+getJobNm());
		sb.append("&").append("userId="+getUserId());
		sb.append("&").append("userNm="+getUserNm());
		sb.append("&").append("department="+getDepartment());
		sb.append("&").append("service="+getService());
		sb.append("&").append("jobType="+getJobType());
		sb.append("&").append("jobKind1="+getJobKind1());
		sb.append("&").append("jobKind2="+getJobKind2());
		sb.append("&").append("received="+getReceived());
		sb.append("&").append("insertDt="+getInsertDt());
		sb.append("&").append("reserveDt="+getReserveDt());
		sb.append("&").append("final_send_status="+getFinal_send_status());
		sb.append("&").append("searchCode="+getSearchCode());
		sb.append("&").append("searchCodeVal="+getSearchCodeVal());
		
		return sb.toString();
	}
}
