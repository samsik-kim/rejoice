/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 * redaano    2009. 4. 7.    Description
 *
 */
package com.omp.admin.device.servicedevice.model;

import java.io.File;

import com.omp.commons.model.CommonModel;
import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;

/**
 * PHONE_INFO Model
 * 
 * @author redaano
 * @version 0.1
 */
@SuppressWarnings("serial")
public class PhoneInfo extends CommonModel implements Pagenateable {

	private String phoneModelCd = null;
	private String gdcd = null;
	private String mgmtPhoneModelNm = null;
	private String modelNm = null;
	private String makeComp = null;
	private String vmVer = null;
	private String lcdSize = null;
	private String vmType = null;
	private String networkCd = null;
	private String listImgPos = null;
	private String dtlImgPos = null;
	private String svcCd = null;
	private String delYn = null;
	private String regId = null;
	private String regDttm = null;
	private String updId = null;
	private String updDttm = null;

	private String mgmtPhoneModleNm = null;
	private String makeCompNm = null;
	private String vmVerNm = null;
	private String vmTypeNm = null;
	private String lcdSizeNm = null;
	private String svcCdNm = null;

	private String regNm = null;
	private String updNm = null;

	private String searchLcdSize = null;
	private String searchVmVer = null;
	private String searchSvcCd = null;

	private String[] arrNetworkCd = null;
	private String selectedNetworkCd = null;

	private File listImg;
	private String listImgFileName;

	private File dtlImg;
	private String dtlImgFileName;

	private PagenateInfoModel page = new PagenateInfoModel(10);
	private Long totalCount;

	public PagenateInfoModel getPage() {
		return this.page;
	}

	public void setPage(PagenateInfoModel page) {
		this.page = page;
	}

	public Long getTotalCount() {
		return this.totalCount;
	}

	public Long setTotalCount(Long totalCount) {
		return this.totalCount = totalCount;
	}

	public String getPhoneModelCd() {
		return phoneModelCd;
	}

	public void setPhoneModelCd(String phoneModelCd) {
		this.phoneModelCd = phoneModelCd;
	}

	public String getGdcd() {
		return gdcd;
	}

	public void setGdcd(String gdcd) {
		this.gdcd = gdcd;
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

	public String getVmType() {
		return vmType;
	}

	public void setVmType(String vmType) {
		this.vmType = vmType;
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

	public String getDtlImgPos() {
		return dtlImgPos;
	}

	public void setDtlImgPos(String dtlImgPos) {
		this.dtlImgPos = dtlImgPos;
	}

	public String getSvcCd() {
		return svcCd;
	}

	public void setSvcCd(String svcCd) {
		this.svcCd = svcCd;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getRegDttm() {
		return regDttm;
	}

	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}

	public String getUpdId() {
		return updId;
	}

	public void setUpdId(String updId) {
		this.updId = updId;
	}

	public String getUpdDttm() {
		return updDttm;
	}

	public void setUpdDttm(String updDttm) {
		this.updDttm = updDttm;
	}

	public String getRegNm() {
		return regNm;
	}

	public void setRegNm(String regNm) {
		this.regNm = regNm;
	}

	public String getUpdNm() {
		return updNm;
	}

	public void setUpdNm(String updNm) {
		this.updNm = updNm;
	}

	public String getMgmtPhoneModleNm() {
		return mgmtPhoneModleNm;
	}

	public void setMgmtPhoneModleNm(String mgmtPhoneModleNm) {
		this.mgmtPhoneModleNm = mgmtPhoneModleNm;
	}

	public String getMakeCompNm() {
		return makeCompNm;
	}

	public void setMakeCompNm(String makeCompNm) {
		this.makeCompNm = makeCompNm;
	}

	public String getVmVerNm() {
		return vmVerNm;
	}

	public void setVmVerNm(String vmVerNm) {
		this.vmVerNm = vmVerNm;
	}

	public String getVmTypeNm() {
		return vmTypeNm;
	}

	public void setVmTypeNm(String vmTypeNm) {
		this.vmTypeNm = vmTypeNm;
	}

	public String getLcdSizeNm() {
		return lcdSizeNm;
	}

	public void setLcdSizeNm(String lcdSizeNm) {
		this.lcdSizeNm = lcdSizeNm;
	}

	public String getSvcCdNm() {
		return svcCdNm;
	}

	public void setSvcCdNm(String svcCdNm) {
		this.svcCdNm = svcCdNm;
	}

	public String getSearchLcdSize() {
		return searchLcdSize;
	}

	public void setSearchLcdSize(String searchLcdSize) {
		this.searchLcdSize = searchLcdSize;
	}

	public String getSearchVmVer() {
		return searchVmVer;
	}

	public void setSearchVmVer(String searchVmVer) {
		this.searchVmVer = searchVmVer;
	}

	public String getSearchSvcCd() {
		return searchSvcCd;
	}

	public void setSearchSvcCd(String searchSvcCd) {
		this.searchSvcCd = searchSvcCd;
	}

	public String[] getArrNetworkCd() {
		return arrNetworkCd;
	}

	public void setArrNetworkCd(String[] arrNetworkCd) {
		this.arrNetworkCd = arrNetworkCd;
	}

	public String getSelectedNetworkCd() {
		return selectedNetworkCd;
	}

	public void setSelectedNetworkCd(String selectedNetworkCd) {
		this.selectedNetworkCd = selectedNetworkCd;
	}

	public File getListImg() {
		return listImg;
	}

	public void setListImg(File listImg) {
		this.listImg = listImg;
	}

	public File getDtlImg() {
		return dtlImg;
	}

	public void setDtlImg(File dtlImg) {
		this.dtlImg = dtlImg;
	}

	public String getListImgFileName() {
		return listImgFileName;
	}

	public void setListImgFileName(String listImgFileName) {
		this.listImgFileName = listImgFileName;
	}

	public String getDtlImgFileName() {
		return dtlImgFileName;
	}

	public void setDtlImgFileName(String dtlImgFileName) {
		this.dtlImgFileName = dtlImgFileName;
	}

	public String toString() {
		String LF = System.getProperty("line.separator");
		StringBuffer sb = new StringBuffer();
		sb.append(this.getClass().toString()).append(LF);
		sb.append(" phoneModelCd='").append(phoneModelCd).append("'").append(LF);
		sb.append(",gdcd='").append(gdcd).append("'").append(LF);
		sb.append(",mgmtPhoneModelNm='").append(mgmtPhoneModelNm).append("'").append(LF);
		sb.append(",modelNm='").append(modelNm).append("'").append(LF);
		sb.append(",makeComp='").append(makeComp).append("'").append(LF);
		sb.append(",vmVer='").append(vmVer).append("'").append(LF);
		sb.append(",lcdSize='").append(lcdSize).append("'").append(LF);
		sb.append(",vmType='").append(vmType).append("'").append(LF);
		sb.append(",networkCd='").append(networkCd).append("'").append(LF);
		sb.append(",listImgPos='").append(listImgPos).append("'").append(LF);
		sb.append(",dtlImgPos='").append(dtlImgPos).append("'").append(LF);
		sb.append(",svcCd='").append(svcCd).append("'").append(LF);
		sb.append(",delYn='").append(delYn).append("'").append(LF);
		sb.append(",regId='").append(regId).append("'").append(LF);
		sb.append(",updId='").append(updId).append("'").append(LF);
		sb.append(",searchLcdSize='").append(searchLcdSize).append("'").append(LF);
		sb.append(",searchSvcCd='").append(searchSvcCd).append("'").append(LF);
		sb.append(",searchVmVer='").append(searchVmVer).append("'").append(LF);
		sb.append(",searchType='").append(super.getSearchType()).append("'").append(LF);
		sb.append(",searchValue='").append(super.getSearchValue()).append("'").append(LF);
		sb.append("}");
		return sb.toString();
	}

}
