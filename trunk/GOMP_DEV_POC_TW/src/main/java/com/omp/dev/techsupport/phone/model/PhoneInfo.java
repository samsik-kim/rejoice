package com.omp.dev.techsupport.phone.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;
import com.omp.commons.model.TotalCountCarriable;

public class PhoneInfo implements Pagenateable, TotalCountCarriable {

	private String searchType;
	private String searchText;
	private String searchSelect;
	
	private String phoneModelCd;
	private String mgmtPhoneModelNm;
	private String modelNm;
	private String makeComp;
	private String vmType;
	private String vmVer;
	private String lcdSize;
	private String networkCd;
	private String listImgPos;
	
	private PagenateInfoModel page = new PagenateInfoModel();
	private Long totalCount;
	
	@Override
	public Long getTotalCount() {
		return this.totalCount;
	}

	@Override
	public Long setTotalCount(Long totalCount) {
		return this.totalCount	= totalCount;
	}
	
	@Override
    public PagenateInfoModel getPage() {
        return this.page;
    }

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getSearchSelect() {
		return searchSelect;
	}

	public void setSearchSelect(String searchSelect) {
		this.searchSelect = searchSelect;
	}

	public String getPhoneModelCd() {
		return phoneModelCd;
	}

	public void setPhoneModelCd(String phoneModelCd) {
		this.phoneModelCd = phoneModelCd;
	}

	public String getMgmtPhoneModelNm() {
		return mgmtPhoneModelNm;
	}

	public void setMgmtPhoneModelNm(String mgmtPhoneModelNm) {
		this.mgmtPhoneModelNm = mgmtPhoneModelNm;
	}

	public String getModelNm() {
		return modelNm;
	}

	public void setModelNm(String modelNm) {
		this.modelNm = modelNm;
	}

	public String getMakeComp() {
		return makeComp;
	}

	public void setMakeComp(String makeComp) {
		this.makeComp = makeComp;
	}

	public String getVmType() {
		return vmType;
	}

	public void setVmType(String vmType) {
		this.vmType = vmType;
	}

	public String getVmVer() {
		return vmVer;
	}

	public void setVmVer(String vmVer) {
		this.vmVer = vmVer;
	}

	public String getLcdSize() {
		return lcdSize;
	}

	public void setLcdSize(String lcdSize) {
		this.lcdSize = lcdSize;
	}

	public String getNetworkCd() {
		return networkCd;
	}

	public void setNetworkCd(String networkCd) {
		this.networkCd = networkCd;
	}

	public String getListImgPos() {
		return listImgPos;
	}

	public void setListImgPos(String listImgPos) {
		this.listImgPos = listImgPos;
	}

	@Override
    public String toString() { 
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); 
    } 
}
