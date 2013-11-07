package com.omp.admin.certify.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * <pre>
 * Certify Report Add File
 * </pre>
 * 
 * 
 * @author shsin
 *
 */
@SuppressWarnings("serial")
public class CertifyReportAddFile implements Serializable{

	/**
	 * Certification Requested Version
	 */		
	private String verifyReqVer;
	
	/**
	 * Sub Contents SCID
	 */
	private String scid;
	
	/**
	 * Certification Add File Seq
	 */
	private String addFileSeq;
	
	
	/**
	 * Certification Add File Path
	 */
	private String addFile;
	
	/**
	 * Certification Add File Name
	 */
	private String addFileNm;
	
	
	/**
	 * Certification inserted By
	 */
	private String insBy;
	
	/**
	 * Certification inserted date
	 */	
	private String insDttm;
	
	/**
	 * Certification updated By
	 */
	private String updBy;
	
	/**
	 * Certification updated date 
	 */
	private String updDttm;	
	
	
	//////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * toString
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
	}	
	
	//////////////////////////////////////////////////////////////////////////////////	

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
     * @return the addFileSeq
     */		
	public String getAddFileSeq() {
		return addFileSeq;
	}
    /**
     * @param addFileSeq the addFileSeq to set
     */		
	public void setAddFileSeq(String addFileSeq) {
		this.addFileSeq = addFileSeq;
	}
    /**
     * @return the addFile
     */		
	public String getAddFile() {
		return addFile;
	}
    /**
     * @param addFile the addFile to set
     */		
	public void setAddFile(String addFile) {
		this.addFile = addFile;
	}
    /**
     * @return the addFileNm
     */		
	public String getAddFileNm() {
		return addFileNm;
	}
    /**
     * @param addFileNm the addFileNm to set
     */		
	public void setAddFileNm(String addFileNm) {
		this.addFileNm = addFileNm;
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
}
