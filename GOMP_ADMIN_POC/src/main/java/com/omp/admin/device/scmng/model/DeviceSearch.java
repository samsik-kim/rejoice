package com.omp.admin.device.scmng.model;

import com.omp.commons.model.SearchCondition;

@SuppressWarnings("serial")
public class DeviceSearch
	extends SearchCondition {
	
	private boolean includeDeletedDevice;
	private String makeComp;
    private String modelNm;
    private String[] svcCd;
    private String[] verStatus;    
    private String vmType;
    private String vmVer;

    public boolean isIncludeDeletedDevice() {
		return includeDeletedDevice;
	}

	public void setIncludeDeletedDevice(boolean includeDeletedDevice) {
		this.includeDeletedDevice = includeDeletedDevice;
	}

    public String[] getVerStatus() {
		return verStatus;
	}

	public void setVerStatus(String[] verStatus) {
		this.verStatus = verStatus;
	}

	public void setMakeComp(String makeComp) {
        this.makeComp = makeComp;
    }

    public String getMakeComp() {
        return this.makeComp;
    }

    public void setModelNm(String modelNm) {
        this.modelNm = modelNm;
    }

    public String getModelNm() {
        return this.modelNm;
    }

    public void setSvcCd(String[] svcCd) {
        this.svcCd = svcCd;
    }

    public String[] getSvcCd() {
        return this.svcCd;
    }

    public void setVmType(String vmType) {
        this.vmType = vmType;
    }

    public String getVmType() {
        return this.vmType;
    }

    public void setVmVer(String vmVer) {
        this.vmVer = vmVer;
    }

    public String getVmVer() {
        return this.vmVer;
    }
	
}
