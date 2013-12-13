package com.omp.admin.certify.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * <pre>
 * Cerfify Target Test Case
 * </pre>
 * 
 * 
 * @author shsin
 *
 */
@SuppressWarnings("serial")
public class CerfifyTargetTestCase implements Serializable{

	/**
	 * Application CID
	 */		
	private String cid;
	
	/**
	 * Certification Requested Version
	 */		
	private String verifyReqVer;
	
	/**
	 * Test Case SEQ
	 */
	private String testCaseSeq;
	
	/**
	 * Test Case title name
	 */
	private String testCaseTitleNm;
	
	
	/**
	 * Certification inserted By
	 */
	private String insBy;
	
	/**
	 * Certification inserted date
	 */	
	private String insDttm;
	
	
	/**
	 * Certification Category Seq
	 */
	private String ctCtgSeq;
	
	
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
     * @return the testCaseSeq
     */
	public String getTestCaseSeq() {
		return testCaseSeq;
	}
    /**
     * @param testCaseSeq the testCaseSeq to set
     */
	public void setTestCaseSeq(String testCaseSeq) {
		this.testCaseSeq = testCaseSeq;
	}
    /**
     * @return the testCaseTitleNm
     */
	public String getTestCaseTitleNm() {
		return testCaseTitleNm;
	}
    /**
     * @param testCaseTitleNm the testCaseTitleNm to set
     */
	public void setTestCaseTitleNm(String testCaseTitleNm) {
		this.testCaseTitleNm = testCaseTitleNm;
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
     * @return the ctCtgSeq
     */
	public String getCtCtgSeq() {
		return ctCtgSeq;
	}
    /**
     * @param ctCtgSeq the ctCtgSeq to set
     */
	public void setCtCtgSeq(String ctCtgSeq) {
		this.ctCtgSeq = ctCtgSeq;
	}	
	
	
	
}
