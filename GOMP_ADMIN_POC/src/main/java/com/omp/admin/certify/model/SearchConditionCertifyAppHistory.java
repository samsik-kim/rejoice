package com.omp.admin.certify.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;

/**
 * <pre>
 * Search Condition Certify Application History
 * </pre>
 * 
 * @author shsin
 *
 */
@SuppressWarnings("serial")
public class SearchConditionCertifyAppHistory implements Serializable, Pagenateable{

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
	
	
	
	//////////////////////////////////////////////////////////////////////////////////
	
	
	// paging
	private PagenateInfoModel page = new PagenateInfoModel(5);

	/**
	 * @return the page
	 */
	@Override
	public PagenateInfoModel getPage() {
		return this.page;
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
}
