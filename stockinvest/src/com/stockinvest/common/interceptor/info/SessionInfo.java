package com.stockinvest.common.interceptor.info;

import java.io.Serializable;

import org.springframework.stereotype.Service;

@SuppressWarnings("serial")
@Service
public class SessionInfo implements Serializable{
	
	/** 회원 번호 */
	private String memNo;
	/** 회원 ID */
	private String memId;
	/** 회원 구분 코드 */
	private String memClCd;
	/** 회원 상태 코드 */
	private String memStatCd;
	/** 회원 가입 일자 */
	private String memJoinDy;
	/** 회원 탈퇴 일자 */
	private String memScsnDy;
	/** 회원 명 */
	private String memNm;
	/**
	 * @return the memNo
	 */
	public String getMemNo() {
		return memNo;
	}
	/**
	 * @param memNo the memNo to set
	 */
	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}
	/**
	 * @return the memId
	 */
	public String getMemId() {
		return memId;
	}
	/**
	 * @param memId the memId to set
	 */
	public void setMemId(String memId) {
		this.memId = memId;
	}
	/**
	 * @return the memClCd
	 */
	public String getMemClCd() {
		return memClCd;
	}
	/**
	 * @param memClCd the memClCd to set
	 */
	public void setMemClCd(String memClCd) {
		this.memClCd = memClCd;
	}
	/**
	 * @return the memStatCd
	 */
	public String getMemStatCd() {
		return memStatCd;
	}
	/**
	 * @param memStatCd the memStatCd to set
	 */
	public void setMemStatCd(String memStatCd) {
		this.memStatCd = memStatCd;
	}
	/**
	 * @return the memJoinDy
	 */
	public String getMemJoinDy() {
		return memJoinDy;
	}
	/**
	 * @param memJoinDy the memJoinDy to set
	 */
	public void setMemJoinDy(String memJoinDy) {
		this.memJoinDy = memJoinDy;
	}
	/**
	 * @return the memScsnDy
	 */
	public String getMemScsnDy() {
		return memScsnDy;
	}
	/**
	 * @param memScsnDy the memScsnDy to set
	 */
	public void setMemScsnDy(String memScsnDy) {
		this.memScsnDy = memScsnDy;
	}
	/**
	 * @return the memNm
	 */
	public String getMemNm() {
		return memNm;
	}
	/**
	 * @param memNm the memNm to set
	 */
	public void setMemNm(String memNm) {
		this.memNm = memNm;
	}
	

}
