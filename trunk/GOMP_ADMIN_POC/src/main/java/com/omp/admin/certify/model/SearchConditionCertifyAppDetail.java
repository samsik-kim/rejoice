package com.omp.admin.certify.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * <pre>
 * Search Condition Certify Application Detail
 * </pre>
 * 
 * 
 * @author shsin
 *
 */
@SuppressWarnings("serial")
public class SearchConditionCertifyAppDetail implements Serializable{

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
	 * Support Phone Seq
	 */
	private String sprtPhoneSeq;	
	
	/**
	 * Application DirectPass Flag
	 */
	private String directPassFlag;
	
	
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
     * @return the sprtPhoneSeq
     */		
	public String getSprtPhoneSeq() {
		return sprtPhoneSeq;
	}
    /**
     * @param sprtPhoneSeq the sprtPhoneSeq to set
     */		
	public void setSprtPhoneSeq(String sprtPhoneSeq) {
		this.sprtPhoneSeq = sprtPhoneSeq;
	}

	/**
	 * @return the directPassFlag
	 */
	public String getDirectPassFlag() {
		return directPassFlag;
	}

	/**
	 * @param directPassFlag the directPassFlag to set 
	 */
	public void setDirectPassFlag(String directPassFlag) {
		this.directPassFlag = directPassFlag;
	}	
}
