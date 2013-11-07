/*
 * COPYRIGHT(c) SK telecom 2011
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author | Date        | Description
 * ---------------------------------------
 * bcpark | 2011. 3. 18. | Description
 *
 */
package com.omp.admin.product.model;

/**
 * 서브상품 LCD 및 지원 단말 도메인
 * 
 * @author bcpark
 * @version 0.1
 */
public class ContentsSprtPhone {
	private String scid;
	private String phoneModelCd;
	private String mgmtPhoneModelNm;
	private String modelNm;
	private String vmVer;
	private String vmVerNm;
	private String lcdSize;
	private String lcdSizeNm;
	private String verifyReqVer;

	public String getScid() {
		return scid;
	}

	public void setScid(String scid) {
		this.scid = scid;
	}

	public String getPhoneModelCd() {
		return phoneModelCd;
	}

	public void setPhoneModelCd(String phoneModelCd) {
		this.phoneModelCd = phoneModelCd;
	}

	public String getMgmtPhoneModelNm() {
		return mgmtPhoneModelNm;
	}

	public void setMgmtPhoneModelNm(String mgmtPhoneModelNm) {
		this.mgmtPhoneModelNm = mgmtPhoneModelNm;
	}

	public String getModelNm() {
		return modelNm;
	}

	public void setModelNm(String modelNm) {
		this.modelNm = modelNm;
	}

	public String getVmVer() {
		return vmVer;
	}

	public void setVmVer(String vmVer) {
		this.vmVer = vmVer;
	}

	public String getVmVerNm() {
		return vmVerNm;
	}

	public void setVmVerNm(String vmVerNm) {
		this.vmVerNm = vmVerNm;
	}

	public String getLcdSize() {
		return lcdSize;
	}

	public void setLcdSize(String lcdSize) {
		this.lcdSize = lcdSize;
	}

	public String getLcdSizeNm() {
		return lcdSizeNm;
	}

	public void setLcdSizeNm(String lcdSizeNm) {
		this.lcdSizeNm = lcdSizeNm;
	}

	public String getVerifyReqVer() {
		return verifyReqVer;
	}

	public void setVerifyReqVer(String verifyReqVer) {
		this.verifyReqVer = verifyReqVer;
	}

}
