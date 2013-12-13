package com.omp.admin.certify.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * <pre>
 * Tester Assign Info
 * </pre>
 * 
 * @author shsin
 *
 */
@SuppressWarnings("serial")
public class SaveTestStatus implements Serializable{

	/**
	 * Application CID
	 */		
	private String cid;
	
	/**
	 * Certification Requested Version
	 */		
	private String verifyReqVer;
	
	/**
	 * Tester Id
	 */
	private String testerId;
	
	/**
	 * Status
	 */
	private String status;
	
	/**
	 * Test Reject Comment
	 */
	private String testRejectCmt;	
	

	/**
	 * Certification updated By
	 */
	private String updBy;
	
	/**
	 * Certification updated date 
	 */
	private String updDttm;	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * toString
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
	}	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
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
     * @return the testerId
     */
    public String getTesterId() {
		return testerId;
	}
    /**
     * @param testerId the testerId to set
     */
	public void setTesterId(String testerId) {
		this.testerId = testerId;
	}
	/**
     * @return the status
     */
	public String getStatus() {
		return status;
	}
    /**
     * @param status the status to set
     */
	public void setStatus(String status) {
		this.status = status;
	}	
	
    /**
     * @return the testRejectCmt
     */
	public String getTestRejectCmt() {
		return testRejectCmt;
	}
    /**
     * @param testRejectCmt the testRejectCmt to set
     */
	public void setTestRejectCmt(String testRejectCmt) {
		this.testRejectCmt = testRejectCmt;
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
