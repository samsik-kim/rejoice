package com.omp.admin.certify.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * <pre>
 * Certify Application Sub Contents Infomation
 * </pre>
 * 
 * 
 * @author shsin
 *
 */
@SuppressWarnings("serial")
public class CertifyAppSubInfomation implements Serializable{

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
	 * VM Version Min
	 */
	private String vmVerMin;
	
	/**
	 * VM Version Max
	 */
	private String vmVerMax;
	
	/**
	 * VM Version Target
	 */
	private String vmVerTarget;
	
	/**
	 * Package Name
	 */
	private String pkgNm;
	
	/**
	 * Version Code
	 */
	private String versionCode;
	
	/**
	 * Version Name
	 */
	private String versionName;
	
	/**
	 * Apk File path
	 */
	private String runFilePos;
	
	/**
	 * Apk File Name
	 */
	private String runFileNm;
	
	/**
	 * Apk File size
	 */
	private String runFileSize;
	
	/**
	 * Certification Result File
	 */
	private String appCtResultFile;
	
	/**
	 * Certification Result File name
	 */
	private String appCtResultFileNm;
	
	/**
	 * Certification Result Comment
	 */
	private String appCtCmt;
	
	
	/**
	 * Certification updated By
	 */
	private String updBy;
	
	/**
	 * Certification updated date 
	 */
	private String updDttm;	
	
	/**
	 * Test Certify Status
	 */
	private String testerCtStat;
	
	/**
	 * VM Type
	 */
	private String vmType;
	

	/**
	 * Certification progress status
	 */	
	private String verifyPrgrYn;	
	
	/**
	 * Certification Phone List
	 */
	private List<CertifySupportPhone> supportPhoneList;
	
	/**
	 * Certification Report added File list
	 */
	private List<CertifyReportAddFile> reportAddFileList;
	
	
	//////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * @return the runFileSize By Kilobyte
	 */
	public long getRunFileSizeByKilobyte(){
		
		long fileSize = 0L;
		
		try{
			fileSize = Long.parseLong(runFileSize);
			
			fileSize = fileSize/1024L;
			
		}catch(Exception ex){
			// Skip
		}
		
		return fileSize;
	}
	
	
    /**
     * @return the appCtCmtByBr
     */
	public String getAppCtCmtByBr() {
		
		String str = "";
		try{
			str = appCtCmt.replaceAll("\n\r", "<BR>");
			str = appCtCmt.replaceAll("\n", "<BR>");
		}catch(Exception ex){
			// Skip
		}
		
		return str;
	}
	
	/**
	 * Support PhoneList Size
	 * @return int
	 */
	public int getSupportPhoneListSize(){
		
		int iSize = 0;
		
		try{
			iSize = supportPhoneList.size();
		}catch(Exception ex){
			// Skip
		}
		
		return iSize;
	}
	
	/**
	 * toString
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
	}	
	
	//////////////////////////////////////////////////////////////////////////////////	
	
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
     * @return the verifyReqVer
     */
	public String getVerifyReqVer() {
		return verifyReqVer;
	}
    /**
     * @param verifyReqVer the verifyReqVer to set
     */
	public void setVerifyReqVer(String verifyReqVer) {
		this.verifyReqVer = verifyReqVer;
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
     * @return the agrmntStat
     */
	public String getAgrmntStat() {
		return agrmntStat;
	}
    /**
     * @param agrmntStat the agrmntStat to set
     */
	public void setAgrmntStat(String agrmntStat) {
		this.agrmntStat = agrmntStat;
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
     * @return the runFilePos
     */
	public String getRunFilePos() {
		return runFilePos;
	}
    /**
     * @param runFilePos the runFilePos to set
     */
	public void setRunFilePos(String runFilePos) {
		this.runFilePos = runFilePos;
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
     * @return the appCtResultFile
     */
	public String getAppCtResultFile() {
		return appCtResultFile;
	}
    /**
     * @param appCtResultFile the appCtResultFile to set
     */
	public void setAppCtResultFile(String appCtResultFile) {
		this.appCtResultFile = appCtResultFile;
	}
    /**
     * @return the appCtResultFileNm
     */
	public String getAppCtResultFileNm() {
		return appCtResultFileNm;
	}
    /**
     * @param appCtResultFileNm the appCtResultFileNm to set
     */
	public void setAppCtResultFileNm(String appCtResultFileNm) {
		this.appCtResultFileNm = appCtResultFileNm;
	}
    /**
     * @return the appCtCmt
     */
	public String getAppCtCmt() {
		return appCtCmt;
	}
    /**
     * @param appCtCmt the appCtCmt to set
     */
	public void setAppCtCmt(String appCtCmt) {
		this.appCtCmt = appCtCmt;
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
     * @return the supportPhoneList
     */	
	public List<CertifySupportPhone> getSupportPhoneList() {
		return supportPhoneList;
	}
    /**
     * @param supportPhoneList the supportPhoneList to set
     */	
	public void setSupportPhoneList(List<CertifySupportPhone> supportPhoneList) {
		this.supportPhoneList = supportPhoneList;
	}
    /**
     * @return the reportAddFileList
     */	
	public List<CertifyReportAddFile> getReportAddFileList() {
		return reportAddFileList;
	}
    /**
     * @param reportAddFileList the reportAddFileList to set
     */	
	public void setReportAddFileList(List<CertifyReportAddFile> reportAddFileList) {
		this.reportAddFileList = reportAddFileList;
	}

    /**
     * @return the testerCtStat
     */	
	public String getTesterCtStat() {
		return testerCtStat;
	}

    /**
     * @param testerCtStat the testerCtStat to set
     */	
	public void setTesterCtStat(String testerCtStat) {
		this.testerCtStat = testerCtStat;
	}


    /**
     * @return the vmType
     */
	public String getVmType() {
		return vmType;
	}
    /**
     * @param vmType the vmType to set
     */
	public void setVmType(String vmType) {
		this.vmType = vmType;
	}
	
    /**
     * @return the verifyPrgrYn
     */
	public String getVerifyPrgrYn() {
		return verifyPrgrYn;
	}
    /**
     * @param verifyPrgrYn the verifyPrgrYn to set
     */
	public void setVerifyPrgrYn(String verifyPrgrYn) {
		this.verifyPrgrYn = verifyPrgrYn;
	}	
}
