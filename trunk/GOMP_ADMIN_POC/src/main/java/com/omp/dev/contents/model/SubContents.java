package com.omp.dev.contents.model;

import java.io.File;
import java.io.InputStream;

public class SubContents {

	private 	String		cid;
	private		String		scid;
	private 	String		vmVerMin;
	private		String		vmVerMax;
	private		String 		vmVerTarget;
	private		String		minSDKVersion;
	private		String 		maxSDKVersion;
	private		String 		targetSDKVersion;
	private		String		privateSignKey;
	private		String		pkgNm;
	private		String		versionCode;
	private		String		versionName;
	private		String		insBy;
	private		String 		insDttm;
	private		String		updBy;
	private		String 		updDttm;
	
	private		RunFile		runFile;
	private		Provision	provision;
	
	private		String		runFileNm;
	private		String		runFilePos;
	private		String		runFileSize;
	

	private		String		provisionItem;
	private		String		sprtPhoneModel;

	private		String 		uploadRunFileResult;
	private 	String 		delYn;
	
	public SubContents(){
		runFile = new RunFile();
		provision = new Provision();
	}
	
	/**
	 * @return the cid
	 */
	public String getCid() {
		return cid;
	}
	/**
	 * @param cid the cid to set
	 */
	public void setCid(String cid) {
		this.cid = cid;
	}
	/**
	 * @return the scid
	 */
	public String getScid() {
		return scid;
	}
	/**
	 * @param scid the scid to set
	 */
	public void setScid(String scid) {
		this.scid = scid;
	}
	/**
	 * @return the vmVerMin
	 */
	public String getVmVerMin() {
		return vmVerMin;
	}
	/**
	 * @param vmVerMin the vmVerMin to set
	 */
	public void setVmVerMin(String vmVerMin) {
		this.vmVerMin = vmVerMin;
	}
	/**
	 * @return the vmVerMax
	 */
	public String getVmVerMax() {
		return vmVerMax;
	}
	/**
	 * @param vmVerMax the vmVerMax to set
	 */
	public void setVmVerMax(String vmVerMax) {
		this.vmVerMax = vmVerMax;
	}
	/**
	 * @return the vmVerTarget
	 */
	public String getVmVerTarget() {
		return vmVerTarget;
	}
	/**
	 * @param vmVerTarget the vmVerTarget to set
	 */
	public void setVmVerTarget(String vmVerTarget) {
		this.vmVerTarget = vmVerTarget;
	}
	/**
	 * @return the pkgNm
	 */
	public String getPkgNm() {
		return pkgNm;
	}
	/**
	 * @param pkgNm the pkgNm to set
	 */
	public void setPkgNm(String pkgNm) {
		this.pkgNm = pkgNm;
	}
	/**
	 * @return the versionCode
	 */
	public String getVersionCode() {
		return versionCode;
	}
	/**
	 * @param versionCode the versionCode to set
	 */
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	/**
	 * @return the versionName
	 */
	public String getVersionName() {
		return versionName;
	}
	/**
	 * @param versionName the versionName to set
	 */
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	/**
	 * @return the insBy
	 */
	public String getInsBy() {
		return insBy;
	}
	/**
	 * @param insBy the insBy to set
	 */
	public void setInsBy(String insBy) {
		this.insBy = insBy;
	}
	/**
	 * @return the insDttm
	 */
	public String getInsDttm() {
		return insDttm;
	}
	/**
	 * @param insDttm the insDttm to set
	 */
	public void setInsDttm(String insDttm) {
		this.insDttm = insDttm;
	}
	/**
	 * @return the updBy
	 */
	public String getUpdBy() {
		return updBy;
	}
	/**
	 * @param updBy the updBy to set
	 */
	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}
	/**
	 * @return the updDttm
	 */
	public String getUpdDttm() {
		return updDttm;
	}
	/**
	 * @param updDttm the updDttm to set
	 */
	public void setUpdDttm(String updDttm) {
		this.updDttm = updDttm;
	}
	/**
	 * @return the runFile
	 */
	public RunFile getRunFile() {
		return runFile;
	}
	/**
	 * @param runFile the runFile to set
	 */
	public void setRunFile(RunFile runFile) {
		this.runFile = runFile;
	}

	/**
	 * @return the provision
	 */
	public Provision getProvision() {
		return provision;
	}

	/**
	 * @param provision the provision to set
	 */
	public void setProvision(Provision provision) {
		this.provision = provision;
	}

	/**
	 * @return the provisionItem
	 */
	public String getProvisionItem() {
		return provisionItem;
	}

	/**
	 * @param provisionItem the provisionItem to set
	 */
	public void setProvisionItem(String provisionItem) {
		this.provisionItem = provisionItem;
	}

	/**
	 * @return the runFileNm
	 */
	public String getRunFileNm() {
		return runFileNm;
	}

	/**
	 * @param runFileNm the runFileNm to set
	 */
	public void setRunFileNm(String runFileNm) {
		this.runFileNm = runFileNm;
	}

	/**
	 * @return the runFilePos
	 */
	public String getRunFilePos() {
		return runFilePos;
	}

	
	/**
	 * @return the runFileSize
	 */
	public String getRunFileSize() {
		return runFileSize;
	}

	/**
	 * @param runFileSize the runFileSize to set
	 */
	public void setRunFileSize(String runFileSize) {
		this.runFileSize = runFileSize;
	}

	/**
	 * @param runFilePos the runFilePos to set
	 */
	public void setRunFilePos(String runFilePos) {
		this.runFilePos = runFilePos;
	}

	/**
	 * @return the sprtPhoneModel
	 */
	public String getSprtPhoneModel() {
		return sprtPhoneModel;
	}

	/**
	 * @param sprtPhoneModel the sprtPhoneModel to set
	 */
	public void setSprtPhoneModel(String sprtPhoneModel) {
		this.sprtPhoneModel = sprtPhoneModel;
	}

	/**
	 * @return the uploadRunFileResult
	 */
	public String getUploadRunFileResult() {
		return uploadRunFileResult;
	}

	/**
	 * @param uploadRunFileResult the uploadRunFileResult to set
	 */
	public void setUploadRunFileResult(String uploadRunFileResult) {
		this.uploadRunFileResult = uploadRunFileResult;
	}

	/**
	 * @return the minSDKVersion
	 */
	public String getMinSDKVersion() {
		return minSDKVersion;
	}

	/**
	 * @param minSDKVersion the minSDKVersion to set
	 */
	public void setMinSDKVersion(String minSDKVersion) {
		this.minSDKVersion = minSDKVersion;
	}

	/**
	 * @return the maxSDKVersion
	 */
	public String getMaxSDKVersion() {
		return maxSDKVersion;
	}

	/**
	 * @param maxSDKVersion the maxSDKVersion to set
	 */
	public void setMaxSDKVersion(String maxSDKVersion) {
		this.maxSDKVersion = maxSDKVersion;
	}

	/**
	 * @return the targetSDKVersion
	 */
	public String getTargetSDKVersion() {
		return targetSDKVersion;
	}

	/**
	 * @param targetSDKVersion the targetSDKVersion to set
	 */
	public void setTargetSDKVersion(String targetSDKVersion) {
		this.targetSDKVersion = targetSDKVersion;
	}

	/**
	 * @return the privateSignKey
	 */
	public String getPrivateSignKey() {
		return privateSignKey;
	}

	/**
	 * @param privateSignKey the privateSignKey to set
	 */
	public void setPrivateSignKey(String privateSignKey) {
		this.privateSignKey = privateSignKey;
	}

	/**
	 * @return the delYn
	 */
	public String getDelYn() {
		return delYn;
	}

	/**
	 * @param delYn the delYn to set
	 */
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	
}
