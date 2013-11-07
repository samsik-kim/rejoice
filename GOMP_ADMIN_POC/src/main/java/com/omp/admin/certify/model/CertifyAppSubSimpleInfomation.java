package com.omp.admin.certify.model;

import java.io.Serializable;

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
public class CertifyAppSubSimpleInfomation implements Serializable{

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
}
