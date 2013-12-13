/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 * redaano    2009. 5. 24.    Description
 *
 */
package com.omp.dev.community.model;

public class Qna {
    private String queNo = ""; // QNA Question Number
    private String qnaTpCd = ""; // QNA Type Code
    private String qnaClsCd = ""; // QNA Classify Code
    private String queId = ""; // Questioner ID
    private String queNm = ""; // Questioner Name
    private String emailAddr = ""; // Email Address
    private String queTitle = ""; // Title
    private String queDscr = ""; // Content
    private String prcStatCd = ""; // Treat State Code
    private String delYn = ""; // Delete Check
    private String regDt = ""; // Insert Date
    private String delDt = ""; // Delete Date
    private String prcOpId = ""; // Action person in charge ID
    private String prcDscr = ""; // Action Content
    private String prcCompDt = ""; // Action Complete Date
    private String prodId = ""; // ProductID
    private String devMbrNo = ""; // Developer Member Number

    private String qnaTpNm = "";
    private String prcStatNm = "";
    private String prodNm = ""; //Product Name
    private String hpNo = "";	//Cell Phone
    private String hpModel = "";	//Cell Phone Model Name
    
    private String mbrNo;


    public String getHpNo() {
		return hpNo;
	}


	public void setHpNo(String hpNo) {
		this.hpNo = hpNo;
	}


	public String getHpModel() {
		return hpModel;
	}


	public void setHpModel(String hpModel) {
		this.hpModel = hpModel;
	}


	public String getQueNo() {
        return queNo;
    }


    public void setQueNo(String queNo) {
        this.queNo = queNo;
    }


    public String getQnaTpCd() {
        return qnaTpCd;
    }


    public void setQnaTpCd(String qnaTpCd) {
        this.qnaTpCd = qnaTpCd;
    }


    public String getQnaClsCd() {
        return qnaClsCd;
    }


    public void setQnaClsCd(String qnaClsCd) {
        this.qnaClsCd = qnaClsCd;
    }


    public String getQueId() {
        return queId;
    }


    public void setQueId(String queId) {
        this.queId = queId;
    }


    public String getQueNm() {
        return queNm;
    }


    public void setQueNm(String queNm) {
        this.queNm = queNm;
    }


    public String getEmailAddr() {
        return emailAddr;
    }


    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }


    public String getQueTitle() {
        return queTitle;
    }


    public void setQueTitle(String queTitle) {
        this.queTitle = queTitle;
    }


    public String getQueDscr() {
        return queDscr;
    }


    public void setQueDscr(String queDscr) {
        this.queDscr = queDscr;
    }


    public String getPrcStatCd() {
        return prcStatCd;
    }


    public void setPrcStatCd(String prcStatCd) {
        this.prcStatCd = prcStatCd;
    }


    public String getDelYn() {
        return delYn;
    }


    public void setDelYn(String delYn) {
        this.delYn = delYn;
    }


    public String getRegDt() {
        return regDt;
    }


    public void setRegDt(String regDt) {
        this.regDt = regDt;
    }


    public String getDelDt() {
        return delDt;
    }


    public void setDelDt(String delDt) {
        this.delDt = delDt;
    }


    public String getPrcOpId() {
        return prcOpId;
    }


    public void setPrcOpId(String prcOpId) {
        this.prcOpId = prcOpId;
    }


    public String getPrcDscr() {
        return prcDscr;
    }


    public void setPrcDscr(String prcDscr) {
        this.prcDscr = prcDscr;
    }


    public String getPrcCompDt() {
        return prcCompDt;
    }


    public void setPrcCompDt(String prcCompDt) {
        this.prcCompDt = prcCompDt;
    }


    public String getProdId() {
        return prodId;
    }


    public void setProdId(String prodId) {
        this.prodId = prodId;
    }


    public String getDevMbrNo() {
        return devMbrNo;
    }


    public void setDevMbrNo(String devMbrNo) {
        this.devMbrNo = devMbrNo;
    }


    public String getQnaTpNm() {
        return qnaTpNm;
    }


    public void setQnaTpNm(String qnaTpNm) {
        this.qnaTpNm = qnaTpNm;
    }


    public String getPrcStatNm() {
        return prcStatNm;
    }


    public void setPrcStatNm(String prcStatNm) {
        this.prcStatNm = prcStatNm;
    }


    public String getProdNm() {
        return prodNm;
    }


    public void setProdNm(String prodNm) {
        this.prodNm = prodNm;
    }


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

}