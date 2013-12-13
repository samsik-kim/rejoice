package com.omp.admin.device.scmng.model;

import com.omp.commons.model.DataValueObject;

@SuppressWarnings("serial")
public class CoreAppGroup
	extends DataValueObject {
	
    private Long 	coreappGroupId;
    private String 	appType;
    private String	groupName;
    private String 	groupDesc;
    private String 	regDttm;
    private String 	regId;
    private String 	updDttm;
    private String 	updId;

	public void setCoreappGroupId(Long coreappGroupId) {
        this.coreappGroupId = coreappGroupId;
    }

    public Long getCoreappGroupId() {
        return this.coreappGroupId;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getAppType() {
        return this.appType;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public String getGroupDesc() {
        return this.groupDesc;
    }

    public void setRegDttm(String regDttm) {
        this.regDttm = regDttm;
    }

    public String getRegDttm() {
        return this.regDttm;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getRegId() {
        return this.regId;
    }

    public void setUpdDttm(String updDttm) {
        this.updDttm = updDttm;
    }

    public String getUpdDttm() {
        return this.updDttm;
    }

    public void setUpdId(String updId) {
        this.updId = updId;
    }

    public String getUpdId() {
        return this.updId;
    }
}
