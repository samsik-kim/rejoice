package com.omp.admin.device.scmng.model;

import com.omp.commons.model.TotalCountCarriable;

@SuppressWarnings("serial")
public class CoreAppGroupList
	extends CoreAppGroup
	implements TotalCountCarriable {
	
	private Long 	totalCount;
    private Integer	deviceCount;
    private Integer	coreappCount;

	public Integer getDeviceCount() {
		return deviceCount;
	}

	public Integer getCoreappCount() {
		return coreappCount;
	}

	public void setCoreappCount(Integer coreappCount) {
		this.coreappCount = coreappCount;
	}

	public void setDeviceCount(Integer deviceCount) {
		this.deviceCount = deviceCount;
	}

	@Override
	public Long getTotalCount() {
		return totalCount;
	}

	@Override
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

}
