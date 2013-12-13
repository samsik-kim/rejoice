package com.omp.admin.device.scmng.model;

@SuppressWarnings("serial")
public class CoreAppGroupDevice
	extends Device {

	private boolean	joined;
	private Long	coreappGroupId;
	private String	groupName;
	
	public boolean isJoined() {
		return joined;
	}
	public void setJoined(boolean joined) {
		this.joined = joined;
	}
	public Long getCoreappGroupId() {
		return coreappGroupId;
	}
	public void setCoreappGroupId(Long coreappGroupId) {
		this.coreappGroupId = coreappGroupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
