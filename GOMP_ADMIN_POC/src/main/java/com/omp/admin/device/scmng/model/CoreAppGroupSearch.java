package com.omp.admin.device.scmng.model;

import com.omp.commons.model.SearchCondition;

/**
 * Shop Cient 바이너리 관리를 위한 단말기 그룹의 검색 조건
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class CoreAppGroupSearch
	extends SearchCondition {
	
	private String[] appTypes;

	public String[] getAppTypes() {
		return appTypes;
	}

	public void setAppTypes(String[] appTypes) {
		this.appTypes = appTypes;
	}
	
	

}
