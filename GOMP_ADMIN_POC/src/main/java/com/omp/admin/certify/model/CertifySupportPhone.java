package com.omp.admin.certify.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * <pre>
 * Certify Support Phone
 * </pre>
 * 
 * 
 * @author shsin
 *
 */
@SuppressWarnings("serial")
public class CertifySupportPhone implements Serializable{

	
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
	 * Phone Model Code
	 */
	private String phoneModelCd;
	
	/**
	 * Management Phone model name
	 */
	private String mgmtPhoneModelNm;
	
	/**
	 * Phone model name
	 */
	private String modelNm;
	
	/**
	 * VM Version
	 */
	private String vmVer;
	/**
	 * LCD Size
	 */
	private String lcdSize;
	/**
	 * Certification Target Phone Y/N
	 */
	private String targetYn;
	
	
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
     * @return the sprtPhoneSeq
     */			
	public String getPhoneModelCd() {
		return phoneModelCd;
	}
    /**
     * @param sprtPhoneSeq the sprtPhoneSeq to set
     */			
	public void setPhoneModelCd(String phoneModelCd) {
		this.phoneModelCd = phoneModelCd;
	}
    /**
     * @return the sprtPhoneSeq
     */			
	public String getMgmtPhoneModelNm() {
		return mgmtPhoneModelNm;
	}
    /**
     * @param sprtPhoneSeq the sprtPhoneSeq to set
     */			
	public void setMgmtPhoneModelNm(String mgmtPhoneModelNm) {
		this.mgmtPhoneModelNm = mgmtPhoneModelNm;
	}
    /**
     * @return the sprtPhoneSeq
     */			
	public String getModelNm() {
		return modelNm;
	}
    /**
     * @param sprtPhoneSeq the sprtPhoneSeq to set
     */			
	public void setModelNm(String modelNm) {
		this.modelNm = modelNm;
	}
    /**
     * @return the sprtPhoneSeq
     */			
	public String getVmVer() {
		return vmVer;
	}
    /**
     * @param sprtPhoneSeq the sprtPhoneSeq to set
     */			
	public void setVmVer(String vmVer) {
		this.vmVer = vmVer;
	}
    /**
     * @return the sprtPhoneSeq
     */			
	public String getLcdSize() {
		return lcdSize;
	}
    /**
     * @param sprtPhoneSeq the sprtPhoneSeq to set
     */			
	public void setLcdSize(String lcdSize) {
		this.lcdSize = lcdSize;
	}
    /**
     * @return the sprtPhoneSeq
     */			
	public String getTargetYn() {
		return targetYn;
	}
    /**
     * @param sprtPhoneSeq the sprtPhoneSeq to set
     */			
	public void setTargetYn(String targetYn) {
		this.targetYn = targetYn;
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
