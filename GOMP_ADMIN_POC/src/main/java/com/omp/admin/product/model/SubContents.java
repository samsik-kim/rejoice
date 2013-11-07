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

import java.util.List;
import java.util.Map;

/**
 * 상품 SCID 도메인
 * 
 * @author bcpark
 * @version 0.1
 */
public class SubContents {
	private String scid;
	private String cid;
	private String vmVerMin;
	private String vmVerMax;
	private String vmVerTarget;
	private String pkgNm;
	private String versionCode;
	private String versionName;
	private String runFilePos;
	private String runFileNm;
	private String runFileSize;
	private String verifyReqVer;

	private List<ContentsSprtPhone> contentsSprtPhoneList;

	// verify added
	private String appCtResultFile;
	private String appCtResultFileNm;
	private String appCtCmt;
	private List<String> lcdSizeList;
	private List<Map<String, String>> addFileList;
	private String insDttm;
	private String ctEndDt;

	public String getScid() {
		return scid;
	}

	public void setScid(String scid) {
		this.scid = scid;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getVmVerMin() {
		return vmVerMin;
	}

	public void setVmVerMin(String vmVerMin) {
		this.vmVerMin = vmVerMin;
	}

	public String getVmVerMax() {
		return vmVerMax;
	}

	public void setVmVerMax(String vmVerMax) {
		this.vmVerMax = vmVerMax;
	}

	public String getVmVerTarget() {
		return vmVerTarget;
	}

	public void setVmVerTarget(String vmVerTarget) {
		this.vmVerTarget = vmVerTarget;
	}

	public String getPkgNm() {
		return pkgNm;
	}

	public void setPkgNm(String pkgNm) {
		this.pkgNm = pkgNm;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getRunFilePos() {
		return runFilePos;
	}

	public void setRunFilePos(String runFilePos) {
		this.runFilePos = runFilePos;
	}

	public String getRunFileNm() {
		return runFileNm;
	}

	public void setRunFileNm(String runFileNm) {
		this.runFileNm = runFileNm;
	}

	public String getRunFileSize() {
		return runFileSize;
	}

	public void setRunFileSize(String runFileSize) {
		this.runFileSize = runFileSize;
	}

	public List<ContentsSprtPhone> getContentsSprtPhoneList() {
		return contentsSprtPhoneList;
	}

	public void setContentsSprtPhoneList(List<ContentsSprtPhone> contentsSprtPhoneList) {
		this.contentsSprtPhoneList = contentsSprtPhoneList;
	}

	public String getVerifyReqVer() {
		return verifyReqVer;
	}

	public void setVerifyReqVer(String verifyReqVer) {
		this.verifyReqVer = verifyReqVer;
	}

	public String getAppCtResultFile() {
		return appCtResultFile;
	}

	public void setAppCtResultFile(String appCtResultFile) {
		this.appCtResultFile = appCtResultFile;
	}

	public String getAppCtResultFileNm() {
		return appCtResultFileNm;
	}

	public void setAppCtResultFileNm(String appCtResultFileNm) {
		this.appCtResultFileNm = appCtResultFileNm;
	}

	public String getAppCtCmt() {
		return appCtCmt;
	}

	public void setAppCtCmt(String appCtCmt) {
		this.appCtCmt = appCtCmt;
	}

	public List<String> getLcdSizeList() {
		return lcdSizeList;
	}

	public void setLcdSizeList(List<String> lcdSizeList) {
		this.lcdSizeList = lcdSizeList;
	}

	public List<Map<String, String>> getAddFileList() {
		return addFileList;
	}

	public String getInsDttm() {
		return insDttm;
	}

	public void setInsDttm(String insDttm) {
		this.insDttm = insDttm;
	}

	public String getCtEndDt() {
		return ctEndDt;
	}

	public void setCtEndDt(String ctEndDt) {
		this.ctEndDt = ctEndDt;
	}

	public void setAddFileList(List<Map<String, String>> addFileList) {
		this.addFileList = addFileList;
	}

}
