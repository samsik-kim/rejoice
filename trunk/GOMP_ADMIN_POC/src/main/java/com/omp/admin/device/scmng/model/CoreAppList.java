package com.omp.admin.device.scmng.model;

import com.omp.commons.model.TotalCountCarriable;

/**
 * 코어 어플 리스트용 모델
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class CoreAppList
	extends CoreApp
	implements TotalCountCarriable {
	
	private Long	totalCount;
	private String	groupName;

	@Override
	public Long getTotalCount() {
		return this.totalCount;
	}

	@Override
	public void setTotalCount(Long totalCount) {
		this.totalCount	= totalCount;
	}
	
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
