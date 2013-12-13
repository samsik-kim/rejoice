package com.omp.admin.device.scmng.model;

import java.io.File;

import com.omp.commons.model.DataValueObject;

/**
 * 테이블 TBL_DP_COREAPP 과 입력에 필요한 부가 정보를 가지는 모델
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class CoreApp
	extends DataValueObject {
	
    private Long   coreappId;
    private Long   coreappGroupId;
    private String ver;
    private String appPath;
    private String prodDesc;
    private String status;
    private String regDttm;
    private String regId;
    private String updDttm;
    private String updId;
    private String appType;
    private String groupName;
    private File   appFile;
    private String appFileContentType;
    private String appFileFileName;
    
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Long getCoreappId() {
		return coreappId;
	}
	public void setCoreappId(Long coreappId) {
		this.coreappId = coreappId;
	}
	public Long getCoreappGroupId() {
		return coreappGroupId;
	}
	public void setCoreappGroupId(Long coreappGroupId) {
		this.coreappGroupId = coreappGroupId;
	}
	public String getVer() {
		return ver;
	}
	public void setVer(String ver) {
		this.ver = ver;
	}
	public String getAppPath() {
		return appPath;
	}
	public void setAppPath(String appPath) {
		this.appPath = appPath;
	}
	public String getProdDesc() {
		return prodDesc;
	}
	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getUpdDttm() {
		return updDttm;
	}
	public void setUpdDttm(String updDttm) {
		this.updDttm = updDttm;
	}
	public String getUpdId() {
		return updId;
	}
	public void setUpdId(String updId) {
		this.updId = updId;
	}
	public File getAppFile() {
		return appFile;
	}
	public void setAppFile(File appFile) {
		this.appFile = appFile;
	}
	public String getAppFileContentType() {
		return appFileContentType;
	}
	public void setAppFileContentType(String appFileContentType) {
		this.appFileContentType = appFileContentType;
	}
	public String getAppFileFileName() {
		return appFileFileName;
	}
	public void setAppFileFileName(String appFileFileName) {
		this.appFileFileName = appFileFileName;
	}
}
