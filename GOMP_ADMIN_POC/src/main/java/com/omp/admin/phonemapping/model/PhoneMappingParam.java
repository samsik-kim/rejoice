/*
 * COPYRIGHT(c) SK telecom 2011
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author | Date        | Description
 * ---------------------------------------
 * bcpark | 2011. 4. 7. | Description
 *
 */
package com.omp.admin.phonemapping.model;

import java.io.File;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 단말 Mapping 관리 화면에서 사용하는 파라미터 Domain
 * 
 * @author bcpark
 * @version 0.1
 */
public class PhoneMappingParam {
	private String type;
	private String tmpExcel;
	private File aidFile;
	private String aid;
	private String cmd;

	private String baseDevice;
	private String addDevice;
	private String delDevice;
	private String searchDevice;

	private String adminId;
	private String insIp;

	private String cid;
	private String scid;
	private String verifyReqVer;

	// upload excel file
	private String aidFileContentType;
	private String aidFileFileName;

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getBaseDevice() {
		return baseDevice;
	}

	public void setBaseDevice(String baseDevice) {
		this.baseDevice = baseDevice;
	}

	public String getAddDevice() {
		return addDevice;
	}

	public void setAddDevice(String addDevice) {
		this.addDevice = addDevice;
	}

	public String getDelDevice() {
		return delDevice;
	}

	public void setDelDevice(String delDevice) {
		this.delDevice = delDevice;
	}

	public String getSearchDevice() {
		return searchDevice;
	}

	public void setSearchDevice(String searchDevice) {
		this.searchDevice = searchDevice;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTmpExcel() {
		return tmpExcel;
	}

	public void setTmpExcel(String tmpExcel) {
		this.tmpExcel = tmpExcel;
	}

	public File getAidFile() {
		return aidFile;
	}

	public void setAidFile(File aidFile) {
		this.aidFile = aidFile;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getInsIp() {
		return insIp;
	}

	public void setInsIp(String insIp) {
		this.insIp = insIp;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getVerifyReqVer() {
		return verifyReqVer;
	}

	public void setVerifyReqVer(String verifyReqVer) {
		this.verifyReqVer = verifyReqVer;
	}

	public String getScid() {
		return scid;
	}

	public void setScid(String scid) {
		this.scid = scid;
	}

	public String getAidFileContentType() {
		return aidFileContentType;
	}

	public void setAidFileContentType(String aidFileContentType) {
		this.aidFileContentType = aidFileContentType;
	}

	public String getAidFileFileName() {
		return aidFileFileName;
	}

	public void setAidFileFileName(String aidFileFileName) {
		this.aidFileFileName = aidFileFileName;
	}

}
