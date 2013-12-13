package com.omp.admin.certify.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.omp.commons.model.CommonModel;
import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;


/**
 * <pre>
 * Search Condition
 * </pre>
 * 
 * @author shsin
 *
 */
@SuppressWarnings("serial")
public class SearchConditionCertifyApp extends CommonModel implements Serializable, Pagenateable{

	/**
	 * Platform
	 */
	private String vmType;
	
	/**
	 * Maked Company
	 */
	private String makeComp;
	
	/**
	 * Phone Model Code
	 */
	private String modelNm;
	
	
	/**
	 * Certification progress status(all) 
	 */
	private String certifyPrgrYnAll;
	
	/**
	 * Certification progress status
	 */
	private String[] certifyPrgrYnList;
	
	/**
	 * <pre>
	 * Certification requested Date(From)
	 * : format(YYYYMMDD)
	 * </pre>
	 */
	private String certifyRequestDtFrom;
	
	/**
	 * <pre>
	 * Certification requested Date(To)
	 * : format(YYYYMMDD)
	 * </pre>
	 */
	private String certifyRequestDtTo;	
	
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
	private String ctEndDtFrom;
	
	
	/**
	 * <pre>
	 * Certification ended date(To)
	 * </pre>
	 */
	private String ctEndDtTo;	
	
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
	
    /**
     * @return the page
     */
	public List<String> getCertifyPrgrYnArrayList() {
		List<String> certifyPrgrYnArrayList = null;
		
		if(!"ALL".equals(certifyPrgrYnAll)){
			if(certifyPrgrYnList != null && certifyPrgrYnList.length > 0){
				certifyPrgrYnArrayList = new ArrayList<String>();
				for (String certifyPrgrYn : certifyPrgrYnList) {
						certifyPrgrYnArrayList.add(certifyPrgrYn);
				}
			}
		}

		
		return certifyPrgrYnArrayList;
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
     * @return the page
     */
	public String getMakeComp() {
		return makeComp;
	}

    /**
     * @param makeComp the makeComp to set
     */
	public void setMakeComp(String makeComp) {
		this.makeComp = makeComp;
	}

    /**
     * @return the modelNm
     */
	public String getModelNm() {
		return modelNm;
	}

    /**
     * @param modelNm the modelNm to set
     */
	public void setModelNm(String modelNm) {
		this.modelNm = modelNm;
	}

	
	/**
	 * @return the certifyPrgrYnAll
	 */
    public String getCertifyPrgrYnAll() {
		return certifyPrgrYnAll;
	}

    /**
     * @param certifyPrgrYnAll the certifyPrgrYnAll to set
     */
	public void setCertifyPrgrYnAll(String certifyPrgrYnAll) {
		this.certifyPrgrYnAll = certifyPrgrYnAll;
	}

	/**
     * @return the certifyPrgrYnList
     */
	public String[] getCertifyPrgrYnList() {
		
		return certifyPrgrYnList;
	}

    /**
     * @param certifyPrgrYnList the certifyPrgrYnList to set
     */
	public void setCertifyPrgrYnList(String[] certifyPrgrYnList) {
		this.certifyPrgrYnList = certifyPrgrYnList;
	}

    /**
     * @return the certifyRequestDtFrom
     */
	public String getCertifyRequestDtFrom() {
		
		try{certifyRequestDtFrom = certifyRequestDtFrom.replaceAll("-", "");}catch(Exception e){}
		
		return certifyRequestDtFrom;
	}

    /**
     * @param certifyRequestDtFrom the certifyRequestDtFrom to set
     */
	public void setCertifyRequestDtFrom(String certifyRequestDtFrom) {
		this.certifyRequestDtFrom = certifyRequestDtFrom;
	}
	
	
    /**
     * @return the certifyRequestDtTo
     */
	public String getCertifyRequestDtTo() {
		
		try{certifyRequestDtTo = certifyRequestDtTo.replaceAll("-", "");}catch(Exception e){}
		
		return certifyRequestDtTo;
	}

    /**
     * @param certifyRequestDtTo the certifyRequestDtTo to set
     */
	public void setCertifyRequestDtTo(String certifyRequestDtTo) {
		this.certifyRequestDtTo = certifyRequestDtTo;
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
		this.ctAssignDtFrom = ctAssignDtFrom;
	}
	
	
    /**
     * @return the ctAssignDtTo
     */
	public String getCtAssignDtTo() {
		
		try{ctAssignDtTo = ctAssignDtTo.replaceAll("-", "");}catch(Exception e){}
		
		return ctAssignDtTo;
	}

    /**
     * @param ctAssignDtTo the ctAssignDtTo to set
     */
	public void setCtAssignDtTo(String ctAssignDtTo) {
		this.ctAssignDtTo = ctAssignDtTo;
	}	

    /**
     * @return the ctEndDtFrom
     */
	public String getCtEndDtFrom() {
		
		try{ctEndDtFrom = ctEndDtFrom.replaceAll("-", "");}catch(Exception e){}
		
		return ctEndDtFrom;
	}

    /**
     * @param ctEndDtFrom the ctEndDtFrom to set
     */
	public void setCtEndDtFrom(String ctEndDtFrom) {
		this.ctEndDtFrom = ctEndDtFrom;
	}
	
	
    /**
     * @return the ctEndDtTo
     */
	public String getCtEndDtTo() {
		
		try{ctEndDtTo = ctEndDtTo.replaceAll("-", "");}catch(Exception e){}
		
		return ctEndDtTo;
	}

    /**
     * @param ctEndDtTo the ctEndDtTo to set
     */
	public void setCtEndDtTo(String ctEndDtTo) {
		this.ctEndDtTo = ctEndDtTo;
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
