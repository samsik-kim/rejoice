package com.seojeong.data.member.info;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MemberInfo extends CommonInfo implements Serializable{

	/** SEQ */
	private String seq;
	/** 이름 */
	private String memberNm;
	/** 휴대폰 */
	private String mdn;
	/** 전화번호 */
	private String contanct;
	/** 주소 */
	private String addr;
	/** 이메일 */
	private String email;
	/** 당첨횟수 */
	private String winCnt;
	/** 방문횟수 */
	private String vstCnt;
	/** 등록일 */
	private String regDt;
	/** 수정일 */
	private String upDt;
	/** 방문일 */
	private String vstDt;
	/**
	 * @return the seq
	 */
	public String getSeq() {
		return seq;
	}
	/**
	 * @param seq the seq to set
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
	/**
	 * @return the memberNm
	 */
	public String getMemberNm() {
		return memberNm;
	}
	/**
	 * @param memberNm the memberNm to set
	 */
	public void setMemberNm(String memberNm) {
		this.memberNm = memberNm;
	}
	/**
	 * @return the mdn
	 */
	public String getMdn() {
		return mdn;
	}
	/**
	 * @param mdn the mdn to set
	 */
	public void setMdn(String mdn) {
		this.mdn = mdn;
	}
	/**
	 * @return the contanct
	 */
	public String getContanct() {
		return contanct;
	}
	/**
	 * @param contanct the contanct to set
	 */
	public void setContanct(String contanct) {
		this.contanct = contanct;
	}
	/**
	 * @return the addr
	 */
	public String getAddr() {
		return addr;
	}
	/**
	 * @param addr the addr to set
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the winCnt
	 */
	public String getWinCnt() {
		return winCnt;
	}
	/**
	 * @param winCnt the winCnt to set
	 */
	public void setWinCnt(String winCnt) {
		this.winCnt = winCnt;
	}
	/**
	 * @return the vstCnt
	 */
	public String getVstCnt() {
		return vstCnt;
	}
	/**
	 * @param vstCnt the vstCnt to set
	 */
	public void setVstCnt(String vstCnt) {
		this.vstCnt = vstCnt;
	}
	/**
	 * @return the regDt
	 */
	public String getRegDt() {
		return regDt;
	}
	/**
	 * @param regDt the regDt to set
	 */
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	/**
	 * @return the upDt
	 */
	public String getUpDt() {
		return upDt;
	}
	/**
	 * @param upDt the upDt to set
	 */
	public void setUpDt(String upDt) {
		this.upDt = upDt;
	}
	/**
	 * @return the vstDt
	 */
	public String getVstDt() {
		return vstDt;
	}
	/**
	 * @param vstDt the vstDt to set
	 */
	public void setVstDt(String vstDt) {
		this.vstDt = vstDt;
	}
	
		
}
