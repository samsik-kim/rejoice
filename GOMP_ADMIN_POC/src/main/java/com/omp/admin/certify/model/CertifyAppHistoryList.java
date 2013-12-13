package com.omp.admin.certify.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.omp.commons.model.TotalCountCarriable;

/**
 * 
 * <pre>
 * Certify Application History List
 * </pre>
 * 
 * 
 * @author shsin
 *
 */
@SuppressWarnings("serial")
public class CertifyAppHistoryList implements Serializable, TotalCountCarriable {
	
	/**
	 * Application CID
	 */	
	private String cid;
	
	/**
	 * Sub Contents SCID
	 */
	private String scid;
	
	/**
	 * Certification Requested Version
	 */		
	private String verifyReqVer;
	

	/**
	 * Certification progress status
	 */	
	private String verifyPrgrYn;	
	
	/**
	 * Certification Agreement progress status
	 */
	private String agrmntStat;
	

	/**
	 * Certification ended date
	 */	
	private String ctEndDt;
	
	/**
	 * Sub Content History List
	 */
	private List<CertifyAppSubSimpleInfomation> subContentsHistoryList; 
	
	
	
	
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
    /**
     * @return the subContentsHistoryList
     */	
	public List<CertifyAppSubSimpleInfomation> getSubContentsHistoryList() {
		return subContentsHistoryList;
	}
    /**
     * @param subContentsHistoryList the subContentsHistoryList to set
     */	
	public void setSubContentsHistoryList(
			List<CertifyAppSubSimpleInfomation> subContentsHistoryList) {
		this.subContentsHistoryList = subContentsHistoryList;
	}

	
	
}
