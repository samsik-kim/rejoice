package com.omp.admin.device.scmng.model;

import com.omp.commons.model.SearchCondition;

/**
 * 코어 어플 검색용 모델
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class CoreAppSearch
	extends SearchCondition {
	
	private String[]	appTypes;
	private String[]	status;
	private Long		groupId;
	
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public String[] getAppTypes() {
		return appTypes;
	}
	public void setAppTypes(String[] appTypes) {
		this.appTypes = appTypes;
	}
	public String[] getStatus() {
		return status;
	}
	public void setStatus(String[] status) {
		this.status = status;
	}
	
}