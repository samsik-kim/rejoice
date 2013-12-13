package com.omp.admin.device.scmng.model;

/**
 * 코어 어플 지원 단말 상세정보 모델
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class CoreAppSupportDeviceInfo
	extends Device {
    private String coreappId;
    private String verStatus;
    private String scTestVer;
    private String scProductVer;
    private String armTestVer;
    private String armProductVer;


    public void setCoreappId(String coreappId) {
        this.coreappId = coreappId;
    }

    public String getCoreappId() {
        return this.coreappId;
    }

    public void setVerStatus(String verStatus) {
        this.verStatus = verStatus;
    }

    public String getVerStatus() {
        return this.verStatus;
    }

	public String getScTestVer() {
		return scTestVer;
	}

	public void setScTestVer(String scTestVer) {
		this.scTestVer = scTestVer;
	}

	public String getScProductVer() {
		return scProductVer;
	}

	public void setScProductVer(String scProductVer) {
		this.scProductVer = scProductVer;
	}

	public String getArmTestVer() {
		return armTestVer;
	}

	public void setArmTestVer(String armTestVer) {
		this.armTestVer = armTestVer;
	}

	public String getArmProductVer() {
		return armProductVer;
	}

	public void setArmProductVer(String armProductVer) {
		this.armProductVer = armProductVer;
	}
	
}
