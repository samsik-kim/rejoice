package com.omp.admin.device.scmng.model;

import com.omp.commons.model.DataValueObject;

/**
 * 테이블 TBL_DP_COREAPP_HP 의 내용을 가지는
 * 코어어플 지원단말 모델
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class CoreAppSupport
	extends DataValueObject {

    private String coreappId;
    private String phoneModelCd;
    private String verStatus;

    public void setCoreappId(String coreappId) {
        this.coreappId = coreappId;
    }

    public String getCoreappId() {
        return this.coreappId;
    }

    public void setPhoneModelCd(String phoneModelCd) {
        this.phoneModelCd = phoneModelCd;
    }

    public String getPhoneModelCd() {
        return this.phoneModelCd;
    }

    public void setVerStatus(String verStatus) {
        this.verStatus = verStatus;
    }

    public String getVerStatus() {
        return this.verStatus;
    }	
}
