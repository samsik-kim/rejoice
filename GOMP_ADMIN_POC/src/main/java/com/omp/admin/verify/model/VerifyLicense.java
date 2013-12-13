package com.omp.admin.verify.model;

import com.omp.commons.model.CommonModel;
import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;
import com.omp.commons.model.TotalCountCarriable;

public class VerifyLicense extends CommonModel implements Pagenateable, TotalCountCarriable{
	private PagenateInfoModel	page		= new PagenateInfoModel();
	private Long    			totalCount;
	private String 				rnum;
	private String 				macAddr = "";
	private String 				validFrdt = "";
	private String 				validTodt = "";
	private String 				issueDttm = "";
	private String 				insId = "";
	private String 				regDttm = "";
	private String 				keyWord = "";
	private String 				keyType = "";
	private String 				seqOta = "";
	private String 				cdNm = "";
	
	@Override
	public Long getTotalCount() {
		return this.totalCount;
	}

	@Override
	public void setTotalCount(Long totalCount) {
		this.totalCount	= totalCount;
	}
	
	@Override
    public PagenateInfoModel getPage() {
        return this.page;
    }
	
	public String getRnum() {
		return rnum;
	}

	public void setRnum(String rnum) {
		this.rnum = rnum;
	}

	public String getMacAddr() {
		return macAddr;
	}

	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}

	public String getValidFrdt() {
		return validFrdt;
	}

	public void setValidFrdt(String validFrdt) {
		this.validFrdt = validFrdt;
	}

	public String getValidTodt() {
		return validTodt;
	}

	public void setValidTodt(String validTodt) {
		this.validTodt = validTodt;
	}

	public String getIssueDttm() {
		return issueDttm;
	}

	public void setIssueDttm(String issueDttm) {
		this.issueDttm = issueDttm;
	}

	public String getInsId() {
		return insId;
	}

	public void setInsId(String insId) {
		this.insId = insId;
	}

	public String getRegDttm() {
		return regDttm;
	}

	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	public String getSeqOta() {
		return seqOta;
	}

	public void setSeqOta(String seqOta) {
		this.seqOta = seqOta;
	}

	public String getCdNm() {
		return cdNm;
	}

	public void setCdNm(String cdNm) {
		this.cdNm = cdNm;
	}

}
