package com.omp.admin.certify.model;

import java.io.File;
import java.io.Serializable;

@SuppressWarnings("serial")
public class SubAppTestResultSave implements Serializable{

	/**
	 * Application CID
	 */		
	private String cid;
	
	/**
	 * Certification Requested Version
	 */		
	private String verifyReqVer;
	
	/**
	 * Sub Contents SCID
	 */
	private String scid;	
	
	/**
	 * Certification Agreement progress status
	 */
	private String agrmntStat;	
	
	
	/**
	 * Certification Result File name
	 */
	private File appCtResult;	
	
	/**
	 * Certification Result File name Content Type
	 */
	private String appCtResultContentType;
	
	/**
	 * Certification Result File name
	 */
	private String appCtResultFileName;	
	
	/**
	 * Certification Add File Path
	 */
	private File addFile1;
	
	/**
	 * Certification Add File Content Type
	 */
	private String addFile1ContentType;
	
	/**
	 * Certification Add File Path
	 */
	private String addFile1FileName;	
	
	/**
	 * Certification Add File Path
	 */
	private File addFile2; 	
	
	/**
	 * Certification Add File Content Type
	 */
	private String addFile2ContentType;	

	
	/**
	 * Certification Add File Path
	 */
	private String addFile2FileName; 		
	
	/**
	 * Certification Add File Path
	 */
	private File addFile3;
	
	
	/**
	 * Certification Add File Content Type
	 */
	private String addFile3ContentType;	
	
	/**
	 * Certification Add File Path
	 */
	private String addFile3FileName;	
	
	/**
	 * Certification Add File Path
	 */
	private File addFile4; 
	
	
	/**
	 * Certification Add File Content Type
	 */
	private String addFile4ContentType;	
	
	/**
	 * Certification Add File Path
	 */
	private String addFile4FileName; 	
	
	/**
	 * Certification Result Comment
	 */
	private String appCtCmt;	
	
	/**
	 * 
	 */
	private String vmType;
	
	
	/**
	 * Login Id
	 */
	private String loginId;


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


	public String getAgrmntStat() {
		return agrmntStat;
	}


	public void setAgrmntStat(String agrmntStat) {
		this.agrmntStat = agrmntStat;
	}


	public File getAppCtResult() {
		return appCtResult;
	}


	public void setAppCtResult(File appCtResult) {
		this.appCtResult = appCtResult;
	}


	public String getAppCtResultContentType() {
		return appCtResultContentType;
	}


	public void setAppCtResultContentType(String appCtResultContentType) {
		this.appCtResultContentType = appCtResultContentType;
	}


	public String getAppCtResultFileName() {
		return appCtResultFileName;
	}


	public void setAppCtResultFileName(String appCtResultFileName) {
		this.appCtResultFileName = appCtResultFileName;
	}


	public File getAddFile1() {
		return addFile1;
	}


	public void setAddFile1(File addFile1) {
		this.addFile1 = addFile1;
	}


	public String getAddFile1ContentType() {
		return addFile1ContentType;
	}


	public void setAddFile1ContentType(String addFile1ContentType) {
		this.addFile1ContentType = addFile1ContentType;
	}


	public String getAddFile1FileName() {
		return addFile1FileName;
	}


	public void setAddFile1FileName(String addFile1FileName) {
		this.addFile1FileName = addFile1FileName;
	}


	public File getAddFile2() {
		return addFile2;
	}


	public void setAddFile2(File addFile2) {
		this.addFile2 = addFile2;
	}


	public String getAddFile2ContentType() {
		return addFile2ContentType;
	}


	public void setAddFile2ContentType(String addFile2ContentType) {
		this.addFile2ContentType = addFile2ContentType;
	}


	public String getAddFile2FileName() {
		return addFile2FileName;
	}


	public void setAddFile2FileName(String addFile2FileName) {
		this.addFile2FileName = addFile2FileName;
	}


	public File getAddFile3() {
		return addFile3;
	}


	public void setAddFile3(File addFile3) {
		this.addFile3 = addFile3;
	}


	public String getAddFile3ContentType() {
		return addFile3ContentType;
	}


	public void setAddFile3ContentType(String addFile3ContentType) {
		this.addFile3ContentType = addFile3ContentType;
	}


	public String getAddFile3FileName() {
		return addFile3FileName;
	}


	public void setAddFile3FileName(String addFile3FileName) {
		this.addFile3FileName = addFile3FileName;
	}


	public File getAddFile4() {
		return addFile4;
	}


	public void setAddFile4(File addFile4) {
		this.addFile4 = addFile4;
	}


	public String getAddFile4ContentType() {
		return addFile4ContentType;
	}


	public void setAddFile4ContentType(String addFile4ContentType) {
		this.addFile4ContentType = addFile4ContentType;
	}


	public String getAddFile4FileName() {
		return addFile4FileName;
	}


	public void setAddFile4FileName(String addFile4FileName) {
		this.addFile4FileName = addFile4FileName;
	}


	public String getAppCtCmt() {
		return appCtCmt;
	}


	public void setAppCtCmt(String appCtCmt) {
		this.appCtCmt = appCtCmt;
	}


	public String getLoginId() {
		return loginId;
	}


	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}


	public String getVmType() {
		return vmType;
	}


	public void setVmType(String vmType) {
		this.vmType = vmType;
	}



	
}
