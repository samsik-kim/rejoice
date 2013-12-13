package com.omp.admin.certify.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SearchResultPhoneInfoList implements Serializable{
	
	/**
	 * Phone Model Code
	 */
	private String phoneModelCd;
	
	/**
	 * Model Name
	 */
	private String modelNm;
	
	/**
	 * Management Phone Model Name
	 */
	private String mgmtPhoneModelNm;

	/**
     * @return the phoneModelCd
     */		
	public String getPhoneModelCd() {
		return phoneModelCd;
	}
    /**
     * @param phoneModelCd the phoneModelCd to set
     */	
	public void setPhoneModelCd(String phoneModelCd) {
		this.phoneModelCd = phoneModelCd;
	}
	/**
     * @return the modelNm
     */		
    public String getModelNm() {
		return modelNm;
	}
    /**
     * @param modelNm the modelNm to set
     */	    
	public void setModelNm(String modelNm) {
		this.modelNm = modelNm;
	}
	/**
     * @return the mgmtPhoneModelNm
     */		
	public String getMgmtPhoneModelNm() {
		return mgmtPhoneModelNm;
	}
    /**
     * @param mgmtPhoneModelNm the mgmtPhoneModelNm to set
     */	
	public void setMgmtPhoneModelNm(String mgmtPhoneModelNm) {
		this.mgmtPhoneModelNm = mgmtPhoneModelNm;
	}
	
	
}
