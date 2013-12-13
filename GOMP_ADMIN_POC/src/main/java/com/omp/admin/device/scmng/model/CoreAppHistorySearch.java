package com.omp.admin.device.scmng.model;

import com.omp.commons.model.SearchCondition;

/**
 * 
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class CoreAppHistorySearch
	extends SearchCondition {
	
	private String phoneModelCd;
	private String appType;
	
	public String getPhoneModelCd() {
		return phoneModelCd;
	}
	public void setPhoneModelCd(String phoneModelCd) {
		this.phoneModelCd = phoneModelCd;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
}
