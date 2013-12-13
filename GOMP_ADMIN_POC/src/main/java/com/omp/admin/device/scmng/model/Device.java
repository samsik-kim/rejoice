package com.omp.admin.device.scmng.model;

import com.omp.commons.model.DataValueObject;

@SuppressWarnings("serial")
public class Device
	extends DataValueObject {
    private String phoneModelCd;
    private String gdcd;
    private String mgmtPhoneModelNm;
    private String modelNm;
    private String makeComp;
    private String vmVer;
    private String lcdSize;
    private String vmType;
    private String networkCd;
    private String listImgPos;
    private String dtlImgPos;
    private String svcCd;
    private String delYn;
    private String regId;
    private String regDttm;
    private String updId;
    private String updDttm;

    public void setPhoneModelCd(String phoneModelCd) {
        this.phoneModelCd = phoneModelCd;
    }

    public String getPhoneModelCd() {
        return this.phoneModelCd;
    }

    public void setGdcd(String gdcd) {
        this.gdcd = gdcd;
    }

    public String getGdcd() {
        return this.gdcd;
    }

    public void setMgmtPhoneModelNm(String mgmtPhoneModelNm) {
        this.mgmtPhoneModelNm = mgmtPhoneModelNm;
    }

    public String getMgmtPhoneModelNm() {
        return this.mgmtPhoneModelNm;
    }

    public void setModelNm(String modelNm) {
        this.modelNm = modelNm;
    }

    public String getModelNm() {
        return this.modelNm;
    }

    public void setMakeComp(String makeComp) {
        this.makeComp = makeComp;
    }

    public String getMakeComp() {
        return this.makeComp;
    }

    public void setVmVer(String vmVer) {
        this.vmVer = vmVer;
    }

    public String getVmVer() {
        return this.vmVer;
    }

    public void setLcdSize(String lcdSize) {
        this.lcdSize = lcdSize;
    }

    public String getLcdSize() {
        return this.lcdSize;
    }

    public void setVmType(String vmType) {
        this.vmType = vmType;
    }

    public String getVmType() {
        return this.vmType;
    }

    public void setNetworkCd(String networkCd) {
        this.networkCd = networkCd;
    }

    public String getNetworkCd() {
        return this.networkCd;
    }

    public void setListImgPos(String listImgPos) {
        this.listImgPos = listImgPos;
    }

    public String getListImgPos() {
        return this.listImgPos;
    }

    public void setDtlImgPos(String dtlImgPos) {
        this.dtlImgPos = dtlImgPos;
    }

    public String getDtlImgPos() {
        return this.dtlImgPos;
    }

    public void setSvcCd(String svcCd) {
        this.svcCd = svcCd;
    }

    public String getSvcCd() {
        return this.svcCd;
    }

    public void setDelYn(String delYn) {
        this.delYn = delYn;
    }

    public String getDelYn() {
        return this.delYn;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getRegId() {
        return this.regId;
    }

    public void setRegDttm(String regDttm) {
        this.regDttm = regDttm;
    }

    public String getRegDttm() {
        return this.regDttm;
    }

    public void setUpdId(String updId) {
        this.updId = updId;
    }

    public String getUpdId() {
        return this.updId;
    }

    public void setUpdDttm(String updDttm) {
        this.updDttm = updDttm;
    }

    public String getUpdDttm() {
        return this.updDttm;
    }
	
}
