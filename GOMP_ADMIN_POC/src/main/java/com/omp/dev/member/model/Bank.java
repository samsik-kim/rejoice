package com.omp.dev.member.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Bank implements Serializable{
	
	private String dtlFullCd;
	private String addField1;
	private String cdNm;
	
	/**
	 * @return the dtlFullCd
	 */
	public String getDtlFullCd() {
		return dtlFullCd;
	}
	/**
	 * @param dtlFullCd the dtlFullCd to set
	 */
	public void setDtlFullCd(String dtlFullCd) {
		this.dtlFullCd = dtlFullCd;
	}
	/**
	 * @return the cdNm
	 */
	public String getCdNm() {
		return cdNm;
	}
	/**
	 * @param cdNm the cdNm to set
	 */
	public void setCdNm(String cdNm) {
		this.cdNm = cdNm;
	}
	/**
	 * @return the addField1
	 */
	public String getAddField1() {
		return addField1;
	}
	/**
	 * @param addField1 the addField1 to set
	 */
	public void setAddField1(String addField1) {
		this.addField1 = addField1;
	}
	
}
