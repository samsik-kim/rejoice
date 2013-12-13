package com.omp.dev.member.model;

import com.omp.commons.model.CommonModel;

@SuppressWarnings("serial")
public class MemberWithdrawInfo extends CommonModel {

	private String mbrNo;
	private String mbrCatcd;
	private String devMbrStatCd;
	private String freeDevStartDt;
	private String freeDevEndDt;
	private String freeDevEndReqDt;
	private String freeDevEndReasonCd;
	private String freeDevEndReasonDscr;
	private String payDevStartDt;
	private String payDevEndDt;
	private String payDevEndReqDt;
	private String payDevEndReasonCd;
	private String payDevEndReasonDscr;
	
	public String getMbrNo() {
		return mbrNo;
	}
	public void setMbrNo(String mbrNo) {
		this.mbrNo = mbrNo;
	}
	public String getMbrCatcd() {
		return mbrCatcd;
	}
	public void setMbrCatcd(String mbrCatcd) {
		this.mbrCatcd = mbrCatcd;
	}
	public String getDevMbrStatCd() {
		return devMbrStatCd;
	}
	public void setDevMbrStatCd(String devMbrStatCd) {
		this.devMbrStatCd = devMbrStatCd;
	}
	public String getFreeDevStartDt() {
		return freeDevStartDt;
	}
	public void setFreeDevStartDt(String freeDevStartDt) {
		this.freeDevStartDt = freeDevStartDt;
	}
	public String getFreeDevEndDt() {
		return freeDevEndDt;
	}
	public void setFreeDevEndDt(String freeDevEndDt) {
		this.freeDevEndDt = freeDevEndDt;
	}
	public String getFreeDevEndReqDt() {
		return freeDevEndReqDt;
	}
	public void setFreeDevEndReqDt(String freeDevEndReqDt) {
		this.freeDevEndReqDt = freeDevEndReqDt;
	}
	public String getFreeDevEndReasonCd() {
		return freeDevEndReasonCd;
	}
	public void setFreeDevEndReasonCd(String freeDevEndReasonCd) {
		this.freeDevEndReasonCd = freeDevEndReasonCd;
	}
	public String getFreeDevEndReasonDscr() {
		return freeDevEndReasonDscr;
	}
	public void setFreeDevEndReasonDscr(String freeDevEndReasonDscr) {
		String str = "";
		  
		try{
			str = freeDevEndReasonDscr.replaceAll("\n\r", "<BR>");
			str = freeDevEndReasonDscr.replaceAll("\n", "<BR>");
		}catch(Exception ex){
		   // Skip
		}
		
		this.freeDevEndReasonDscr = str;
	}
	public String getPayDevStartDt() {
		return payDevStartDt;
	}
	public void setPayDevStartDt(String payDevStartDt) {
		this.payDevStartDt = payDevStartDt;
	}
	public String getPayDevEndDt() {
		return payDevEndDt;
	}
	public void setPayDevEndDt(String payDevEndDt) {
		this.payDevEndDt = payDevEndDt;
	}
	public String getPayDevEndReqDt() {
		return payDevEndReqDt;
	}
	public void setPayDevEndReqDt(String payDevEndReqDt) {
		this.payDevEndReqDt = payDevEndReqDt;
	}
	public String getPayDevEndReasonCd() {
		return payDevEndReasonCd;
	}
	public void setPayDevEndReasonCd(String payDevEndReasonCd) {
		this.payDevEndReasonCd = payDevEndReasonCd;
	}
	public String getPayDevEndReasonDscr() {
		return payDevEndReasonDscr;
	}
	public void setPayDevEndReasonDscr(String payDevEndReasonDscr) {
		String str = "";
		  
		try{
			str = payDevEndReasonDscr.replaceAll("\n\r", "<BR>");
			str = payDevEndReasonDscr.replaceAll("\n", "<BR>");
		}catch(Exception ex){
		   // Skip
		}
		
		this.payDevEndReasonDscr = str;
	}
	
}
