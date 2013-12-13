package com.omp.admin.device.scmng.model;

import com.omp.commons.model.SearchCondition;

@SuppressWarnings("serial")
public class CoreAppGroupDeviceSearch
	extends SearchCondition {
	
	private Long	coreappGroupId;
	private String	appType;
	
	public Long getCoreappGroupId() {
		return coreappGroupId;
	}
	public void setCoreappGroupId(Long coreappGroupId) {
		this.coreappGroupId = coreappGroupId;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}

}
