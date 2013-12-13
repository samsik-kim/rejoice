package com.omp.admin.certify.model;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.omp.commons.model.TotalCountCarriable;

/**
 * <pre>
 * Search Result Certify Application List
 * </pre>
 * 
 * @author shsin
 *
 */
@SuppressWarnings("serial")
public class SearchResultCertifyAppList implements Serializable, TotalCountCarriable {

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
	 * Certification requested Date
	 */
	private String insDttm;
	
	/**
	 * Certification Requested Version
	 */
	private String verifyReqVer; 
	
	/**
	 * Certification progress status
	 */
	private String verifyPrgrYn;
	
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
	
	/**
	 * Tester Name
	 */
	private String testerNm;
	
	/**
	 * <pre>
	 * Certification ended date
	 * </pre>
	 */
	private String ctEndDt;
	
	
	
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
	 * @return String
	 */
	public String getProdNmByAbbreviate(){
		
		String str = "";
		
		try{
			str = StringUtils.abbreviate(this.prodNm, 15);
		}catch(Exception ex){
			str = this.prodNm;
		}
		
		return str;
	}
	
	
    /**
     * @return the insDttm By MMDD
     */	
	public String getInsDttmByMmDd() {		
		return insDttm;
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
     * @return the ctEndDt By MMDD
     */	
	public String getCtEndDtByMmDd() {
		return ctEndDt;
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

    /**
     * @return the testerNm
     */	
	public String getTesterNm() {
		return testerNm;
	}

    /**
     * @param testerNm the testerNm to set
     */
	public void setTesterNm(String testerNm) {
		this.testerNm = testerNm;
	}

    /**
     * @return the ctEndDt
     */	
	public String getCtEndDt() {
		return ctEndDt;
	}

    /**
     * @param ctEndDt the ctEndDt to set
     */
	public void setCtEndDt(String ctEndDt) {
		this.ctEndDt = ctEndDt;
	}
	
}
