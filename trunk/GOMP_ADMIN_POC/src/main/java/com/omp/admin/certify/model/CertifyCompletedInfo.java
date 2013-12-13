package com.omp.admin.certify.model;

import java.io.Serializable;


/**
 * <pre>
 * Certification Completed Info
 * </pre>
 * 
 * @author shsin
 *
 */
@SuppressWarnings("serial")
public class CertifyCompletedInfo implements Serializable{

	
	/**
	 * Re-Certification Request Y/N
	 */
	private String reCertifyReqYn;

	
	/**
	 * Certification Agreement progress status
	 */
	private String agrmntStat;
	
	/**
	 * Current Product Sale Status
	 */
	private String curSaleStat;
	
	
	/**
	 * Direct Pass Y/N
	 */
	private String directPassYn;	
	
	
	/**
	 * Verify Comment Code
	 */
	private String verifyCommentCd;	
	
	/**
     * @return the reCertifyReqYn
     */
	public String getReCertifyReqYn() {
		return reCertifyReqYn;
	}
    /**
     * @param reCertifyReqYn the reCertifyReqYn to set
     */	
	public void setReCertifyReqYn(String reCertifyReqYn) {
		this.reCertifyReqYn = reCertifyReqYn;
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
     * @return the curSaleStat
     */	
	public String getCurSaleStat() {
		return curSaleStat;
	}
    /**
     * @param curSaleStat the curSaleStat to set
     */	
	public void setCurSaleStat(String curSaleStat) {
		this.curSaleStat = curSaleStat;
	}	
	
    /**
     * @return the directPassYn
     */
	public String getDirectPassYn() {
		return directPassYn;
	}
    /**
     * @param directPassYn the directPassYn to set
     */
	public void setDirectPassYn(String directPassYn) {
		this.directPassYn = directPassYn;
	}
	
    /**
     * @return the verifyCommentCd
     */
	public String getVerifyCommentCd() {
		return verifyCommentCd;
	}
    /**
     * @param verifyCommmentCd the verifyCommmentCd to set
     */
	public void setVerifyCommentCd(String verifyCommentCd) {
		this.verifyCommentCd = verifyCommentCd;
	}	
}
