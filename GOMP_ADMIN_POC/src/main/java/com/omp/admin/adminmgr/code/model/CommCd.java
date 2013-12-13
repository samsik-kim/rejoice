/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 *             2009. 4. 29.    Description
 *
 */
package com.omp.admin.adminmgr.code.model;

import java.io.Serializable;

import com.omp.commons.model.CommonModel;
import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;

/**
 * CommCd
 * <p/>
 * CommCd Model
 * 
 * @author
 * @version 0.1
 */
@SuppressWarnings("serial")
public class CommCd extends CommonModel implements Pagenateable, Serializable {

	private String grpCd;
	private String dtlCd;
	private String dtlFullCd;
	private String cdNm;
	private String addField1;
	private String addField2;
	private String useYn;
	private String description;
	private String displayOrder;
	private String tags;

	private int cntDtlCd = 0;
	private String selectedGrpCd;
	private String selectedDtlCd;

	private String srchUseYn;
	private String srchGrpType;
	private String srchGrpValue;
	private String srchDtlType;
	private String srchDtlValue;

	private String adMgrPswdNo;

	private String gubun;

	private PagenateInfoModel page = new PagenateInfoModel(10);
	private Long totalCount;

	public PagenateInfoModel getPage() {
		return this.page;
	}

	public Long getTotalCount() {
		return this.totalCount;
	}

	public Long setTotalCount(Long totalCount) {
		return this.totalCount = totalCount;
	}

	public String getGrpCd() {
		return grpCd;
	}

	public void setGrpCd(String grpCd) {
		this.grpCd = grpCd;
	}

	public String getDtlCd() {
		return dtlCd;
	}

	public void setDtlCd(String dtlCd) {
		this.dtlCd = dtlCd;
	}

	public String getDtlFullCd() {
		return dtlFullCd;
	}

	public void setDtlFullCd(String dtlFullCd) {
		this.dtlFullCd = dtlFullCd;
	}

	public String getCdNm() {
		return cdNm;
	}

	public void setCdNm(String cdNm) {
		this.cdNm = cdNm;
	}

	public String getAddField1() {
		return addField1;
	}

	public void setAddField1(String addField1) {
		this.addField1 = addField1;
	}

	public String getAddField2() {
		return addField2;
	}

	public void setAddField2(String addField2) {
		this.addField2 = addField2;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public int getCntDtlCd() {
		return cntDtlCd;
	}

	public void setCntDtlCd(int cntDtlCd) {
		this.cntDtlCd = cntDtlCd;
	}

	public String getSelectedGrpCd() {
		return selectedGrpCd;
	}

	public void setSelectedGrpCd(String selectedGrpCd) {
		this.selectedGrpCd = selectedGrpCd;
	}

	public String getSelectedDtlCd() {
		return selectedDtlCd;
	}

	public void setSelectedDtlCd(String selectedDtlCd) {
		this.selectedDtlCd = selectedDtlCd;
	}

	public String getSrchUseYn() {
		return srchUseYn;
	}

	public void setSrchUseYn(String srchUseYn) {
		this.srchUseYn = srchUseYn;
	}

	public String getSrchGrpType() {
		return srchGrpType;
	}

	public void setSrchGrpType(String srchGrpType) {
		this.srchGrpType = srchGrpType;
	}

	public String getSrchGrpValue() {
		return srchGrpValue;
	}

	public void setSrchGrpValue(String srchGrpValue) {
		this.srchGrpValue = srchGrpValue;
	}

	public String getSrchDtlType() {
		return srchDtlType;
	}

	public void setSrchDtlType(String srchDtlType) {
		this.srchDtlType = srchDtlType;
	}

	public String getSrchDtlValue() {
		return srchDtlValue;
	}

	public void setSrchDtlValue(String srchDtlValue) {
		this.srchDtlValue = srchDtlValue;
	}

	public String getAdMgrPswdNo() {
		return adMgrPswdNo;
	}

	public void setAdMgrPswdNo(String adMgrPswdNo) {
		this.adMgrPswdNo = adMgrPswdNo;
	}

	public String getGubun() {
		return gubun;
	}

	public void setGubun(String gubun) {
		this.gubun = gubun;
	}

}
