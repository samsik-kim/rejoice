package com.omp.admin.certify.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.omp.commons.model.TotalCountCarriable;

/**
 * <pre>
 * Search Result Certify Test Application List
 * </pre>
 * 
 * @author shsin
 *
 */
@SuppressWarnings("serial")
public class SearchResultCertifyTestAppList implements Serializable, TotalCountCarriable {

	/**
	 * 순번
	 */
	private String rnum;
	
	/**
	 * Application CID
	 */
	private String cid;
	
	/**
	 * Application AID
	 */
	private String aid;
	
	
	/**
	 * Platform
	 */
	private String vmType;
	
	
	/**
	 * Application Product Name
	 */
	private String prodNm;
	

	/**
	 * Certification Requested Version
	 */
	private String verifyReqVer; 
	
	/**
	 * Tester Certification Status
	 */
	private String testerCtStat;
	
	
	/**
	 * Certification assigned date
	 */
	private String ctAssignDt;
	
	
	
	/**
	 * Certification expected end date
	 */
	private String ctEndExDt;
	
	//////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * total count
	 */
	private Long total_count;	
	
	//////////////////////////////////////////////////////////////////////////////////

    /**
     * @return the total_count
     */
	@Override
	public Long getTotalCount() {
		return this.total_count;
	}

    /**
     * @param total_count the total_count to set
     */
	@Override
	public void setTotalCount(Long total_count) {
		this.total_count	= total_count;
	}	
	
	

    /**
     * @return the ctAssignDt By MMDD
     */	
	public String getCtAssignDtByMmDd() {
		return ctAssignDt;
	}
	
    /**
     * @return the ctEndExDt By MMDD
     */	
	public String getCtEndExDtByMmDd() {
		return ctEndExDt;
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
     * @return the rnum
     */	
    public String getRnum() {
		return rnum;
	}
	/**
     * @param rnum the rnum to set
     */	
	public void setRnum(String rnum) {
		this.rnum = rnum;
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
     * @return the aid
     */	
	public String getAid() {
		return aid;
	}

    /**
     * @param aid the aid to set
     */
	public void setAid(String aid) {
		this.aid = aid;
	}
	
    /**
     * @return the page
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
     * @return the prodNm
     */	
	public String getProdNm() {
		return prodNm;
	}

    /**
     * @param prodNm the prodNm to set
     */
	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
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
     * @return the ctAssignDt
     */	
	public String getCtAssignDt() {
		return ctAssignDt;
	}

    /**
     * @param ctAssignDt the ctAssignDt to set
     */
	public void setCtAssignDt(String ctAssignDt) {
		this.ctAssignDt = ctAssignDt;
	}

    /**
     * @return the ctEndExDt
     */	
	public String getCtEndExDt() {
		return ctEndExDt;
	}

    /**
     * @param ctEndExDt the ctEndExDt to set
     */
	public void setCtEndExDt(String ctEndExDt) {
		this.ctEndExDt = ctEndExDt;
	}
}
