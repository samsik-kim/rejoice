package com.omp.admin.certify.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.omp.commons.model.CommonModel;
import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;

/**
 * <pre>
 * Search Condition Certify Tester Application
 * </pre>
 * 
 * @author shsin
 *
 */
@SuppressWarnings("serial")
public class SearchConditionCertifyTesterApp extends CommonModel implements Serializable, Pagenateable{

	/**
	 * Platform
	 */
	private String vmType;
	
	/**
	 * <pre>
	 * Certification assigned date(From)
	 * </pre>
	 */
	private String ctAssignDtFrom;
	
	/**
	 * <pre>
	 * Certification assigned date(To)
	 * </pre>
	 */
	private String ctAssignDtTo;
	
	/**
	 * <pre>
	 * Certification ended date(From)
	 * </pre>
	 */
	private String ctEndExDtFrom;
	
	
	/**
	 * <pre>
	 * Certification ended date(To)
	 * </pre>
	 */
	private String ctEndExDtTo;		
	
	
	/**
	 * Login ID
	 */
	private String loginId;
	
	
	
	//////////////////////////////////////////////////////////////////////////////////
	
	
	// paging
	private PagenateInfoModel page = new PagenateInfoModel(20);

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
     * @return the ctAssignDtFrom
     */
	public String getCtAssignDtFrom() {
		
		try{ctAssignDtFrom = ctAssignDtFrom.replaceAll("-", "");}catch(Exception e){}
		
		return ctAssignDtFrom;
	}

    /**
     * @param ctAssignDtFrom the ctAssignDtFrom to set
     */
	public void setCtAssignDtFrom(String ctAssignDtFrom) {
		
		try{ctAssignDtFrom = ctAssignDtFrom.replaceAll("-", "");}catch(Exception e){}
		
		this.ctAssignDtFrom = ctAssignDtFrom;
	}
	
	
    /**
     * @return the ctAssignDtTo
     */
	public String getCtAssignDtTo() {
		return ctAssignDtTo;
	}

    /**
     * @param ctAssignDtTo the ctAssignDtTo to set
     */
	public void setCtAssignDtTo(String ctAssignDtTo) {
		
		try{ctAssignDtTo = ctAssignDtTo.replaceAll("-", "");}catch(Exception e){}
		
		this.ctAssignDtTo = ctAssignDtTo;
	}
    /**
     * @return the ctEndExDtFrom
     */
	public String getCtEndExDtFrom() {
		return ctEndExDtFrom;
	}
    /**
     * @param ctEndExDtFrom the ctEndExDtFrom to set
     */
	public void setCtEndExDtFrom(String ctEndExDtFrom) {
		
		try{ctEndExDtFrom = ctEndExDtFrom.replaceAll("-", "");}catch(Exception e){}
		
		this.ctEndExDtFrom = ctEndExDtFrom;
	}
    /**
     * @return the ctEndExDtTo
     */
	public String getCtEndExDtTo() {
		return ctEndExDtTo;
	}
    /**
     * @param ctEndExDtTo the ctEndExDtTo to set
     */
	public void setCtEndExDtTo(String ctEndExDtTo) {
		
		try{ctEndExDtTo = ctEndExDtTo.replaceAll("-", "");}catch(Exception e){}
		
		this.ctEndExDtTo = ctEndExDtTo;
	}	

    /**
     * @return the loginId
     */	
	public String getLoginId() {
		return loginId;
	}
    /**
     * @param loginId the loginId to set
     */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
}
