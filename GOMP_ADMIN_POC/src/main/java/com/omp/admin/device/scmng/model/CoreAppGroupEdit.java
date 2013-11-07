package com.omp.admin.device.scmng.model;

@SuppressWarnings("serial")
public class CoreAppGroupEdit
	extends CoreAppGroup {

    private String[] phoneModelCd;

	public String[] getPhoneModelCd() {
		return phoneModelCd;
	}

	public void setPhoneModelCd(String[] phoneModelCd) {
		this.phoneModelCd = phoneModelCd;
	}
}
