package com.omp.admin.device.scmng.model;

@SuppressWarnings("serial")
public class CoreAppDevice
	extends CoreApp {

    private String phoneModelCd;
    private String verStatus;
    
	public String getPhoneModelCd() {
		return phoneModelCd;
	}
	public void setPhoneModelCd(String phoneModelCd) {
		this.phoneModelCd = phoneModelCd;
	}
	public String getVerStatus() {
		return verStatus;
	}
	public void setVerStatus(String verStatus) {
		this.verStatus = verStatus;
	}
	
}
