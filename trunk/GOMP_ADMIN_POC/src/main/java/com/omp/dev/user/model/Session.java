package com.omp.dev.user.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Session implements Serializable{

	private String mbrNo;			//회원번호
	private String devMbrStatCd;	//개발자분류코드
	private String mbrStatCd;		//회원 상태 코드
	private String mbrId;			//회원아이디
	private String mbrNm;			//회원이름
	private String mbrPw;			//회원비밀번호
	private String emailAddr;		//이메일주소
	private String emailCertYn;		//이메일인증여부
	private String tempPw;			//임시비밀번호
	private String remoteAddr;		//접속 URL 
	private int resultCode;			//Result Code
	private String resultMsg;		//Result Message
	private String returnUrl;

	/**
	 * @return the mbrNo
	 */
	public String getMbrNo() {
		return mbrNo;
	}
	/**
	 * @param mbrNo the mbrNo to set
	 */
	public void setMbrNo(String mbrNo) {
		this.mbrNo = mbrNo;
	}
	/**
	 * @return the mbrId
	 */
	public String getMbrId() {
		return mbrId;
	}
	/**
	 * @param mbrId the mbrId to set
	 */
	public void setMbrId(String mbrId) {
		this.mbrId = mbrId;
	}
	
	/**
	 * @return the mbrStatCd
	 */
	public String getMbrStatCd() {
		return mbrStatCd;
	}
	/**
	 * @param mbrStatCd the mbrStatCd to set
	 */
	public void setMbrStatCd(String mbrStatCd) {
		this.mbrStatCd = mbrStatCd;
	}
	/**
	 * @return the mbrNm
	 */
	public String getMbrNm() {
		return mbrNm;
	}
	/**
	 * @param mbrNm the mbrNm to set
	 */
	public void setMbrNm(String mbrNm) {
		this.mbrNm = mbrNm;
	}
	/**
	 * @return the mbrPw
	 */
	public String getMbrPw() {
		return mbrPw;
	}
	/**
	 * @param mbrPw the mbrPw to set
	 */
	public void setMbrPw(String mbrPw) {
		this.mbrPw = mbrPw;
	}
	/**
	 * @return the resultCode
	 */
	public int getResultCode() {
		return resultCode;
	}
	/**
	 * @param resultCode the resultCode to set
	 */
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	/**
	 * @return the devMbrStatCd
	 */
	public String getDevMbrStatCd() {
		return devMbrStatCd;
	}
	/**
	 * @param devMbrStatCd the devMbrStatCd to set
	 */
	public void setDevMbrStatCd(String devMbrStatCd) {
		this.devMbrStatCd = devMbrStatCd;
	}
	/**
	 * @return the remoteAddr
	 */
	public String getRemoteAddr() {
		return remoteAddr;
	}
	/**
	 * @param remoteAddr the remoteAddr to set
	 */
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
	/**
	 * @return the returnUrl
	 */
	public String getReturnUrl() {
		return returnUrl;
	}
	/**
	 * @param returnUrl the returnUrl to set
	 */
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	/**
	 * @return the emailAddr
	 */
	public String getEmailAddr() {
		return emailAddr;
	}
	/**
	 * @param emailAddr the emailAddr to set
	 */
	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}
	/**
	 * @return the emailCertYn
	 */
	public String getEmailCertYn() {
		return emailCertYn;
	}
	/**
	 * @param emailCertYn the emailCertYn to set
	 */
	public void setEmailCertYn(String emailCertYn) {
		this.emailCertYn = emailCertYn;
	}
	/**
	 * @return the tempPw
	 */
	public String getTempPw() {
		return tempPw;
	}
	/**
	 * @param tempPw the tempPw to set
	 */
	public void setTempPw(String tempPw) {
		this.tempPw = tempPw;
	}
	/**
	 * @return the resultMsg
	 */
	public String getResultMsg() {
		return resultMsg;
	}
	/**
	 * @param resultMsg the resultMsg to set
	 */
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	
}
