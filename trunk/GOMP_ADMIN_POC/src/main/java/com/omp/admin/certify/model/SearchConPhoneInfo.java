package com.omp.admin.certify.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SearchConPhoneInfo implements Serializable{
	
	/**
	 * maked Company
	 */
	private String makeComp;

    /**
     * @return the makeComp
     */	
	public String getMakeComp() {
		return makeComp;
	}
    /**
     * @param makeComp the makeComp to set
     */
	public void setMakeComp(String makeComp) {
		this.makeComp = makeComp;
	}
}
