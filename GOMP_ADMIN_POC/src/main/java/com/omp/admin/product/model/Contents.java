/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 * bcpark    2011. 3. 7.    Description
 *
 */
package com.omp.admin.product.model;

import com.omp.commons.model.TotalCountCarriable;

/**
 * 상품관리 Model
 * 
 * @author bcpark
 * @version 0.1
 */
public class Contents implements TotalCountCarriable {
	// list
	private String cid;
	private String prodNm;
	private String aid;
	private String devUserId;
	private String prodPrc;
	private String saleStat;
	private String saleStatNm;
	private String ctgrCd2;
	private String ctgrNm2;
	private String verifyPrgrYn;
	private String verifyPrgrNm;
	private String usMbrId;
	private String statusImgPos;
	private String statusImgNm;
	private String mbrNo;
	private String mbrId;
	private String prodGbn;
	private String prodGbnNm;
	private String verifyReqVer;
	private String verifyPrgrYnNm;
	private String agrmntStat;
	private String agrmntStatNm;
	private String vmType;
	private String vmTypeNm;

	// baseInfo additional
	private String corpProdNo;
	private String firstInsDt;
	private String mbrNm;
	private String firstAgrmntDt;
	private String saleStartDt;
	private String adjRate;
	private String adjRateSkt;
	private String signOption;

	// productInfo additional
	private String gameDelibGrd;
	private String gameDelibGrdNm;
	private String promotionUrl;
	private String prodDescSmmr;
	private String prodDescDtl;
	private String updDttm;
	private String exposureDevNm;

	// devInfo additional
	private String drmYn;
	private String verifyScnrFile;
	private String verifyScnrFileNm;
	
	private String dwldCount;

	private int rnum;
	// paging
	private Long total_count;

	// baseInfo 

	@Override
	public Long getTotalCount() {
		return this.total_count;
	}

	@Override
	public void setTotalCount(Long totalCount) {
		this.total_count = totalCount;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getProdNm() {
		return prodNm;
	}

	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getDevUserId() {
		return devUserId;
	}

	public void setDevUserId(String devUserId) {
		this.devUserId = devUserId;
	}

	public String getProdPrc() {
		return prodPrc;
	}

	public void setProdPrc(String prodPrc) {
		this.prodPrc = prodPrc;
	}

	public String getSaleStat() {
		return saleStat;
	}

	public void setSaleStat(String saleStat) {
		this.saleStat = saleStat;
	}

	public String getSaleStatNm() {
		return saleStatNm;
	}

	public void setSaleStatNm(String saleStatNm) {
		this.saleStatNm = saleStatNm;
	}

	public String getCtgrCd2() {
		return ctgrCd2;
	}

	public void setCtgrCd2(String ctgrCd2) {
		this.ctgrCd2 = ctgrCd2;
	}

	public String getCtgrNm2() {
		return ctgrNm2;
	}

	public void setCtgrNm2(String ctgrNm2) {
		this.ctgrNm2 = ctgrNm2;
	}

	public String getVerifyPrgrYn() {
		return verifyPrgrYn;
	}

	public void setVerifyPrgrYn(String verifyPrgrYn) {
		this.verifyPrgrYn = verifyPrgrYn;
	}

	public String getVerifyPrgrNm() {
		return verifyPrgrNm;
	}

	public void setVerifyPrgrNm(String verifyPrgrNm) {
		this.verifyPrgrNm = verifyPrgrNm;
	}

	public String getUsMbrId() {
		return usMbrId;
	}

	public void setUsMbrId(String usMbrId) {
		this.usMbrId = usMbrId;
	}

	public String getStatusImgPos() {
		return statusImgPos;
	}

	public void setStatusImgPos(String statusImgPos) {
		this.statusImgPos = statusImgPos;
	}

	public String getStatusImgNm() {
		return statusImgNm;
	}

	public void setStatusImgNm(String statusImgNm) {
		this.statusImgNm = statusImgNm;
	}

	public String getMbrId() {
		return mbrId;
	}

	public void setMbrId(String mbrId) {
		this.mbrId = mbrId;
	}

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public String getProdGbn() {
		return prodGbn;
	}

	public void setProdGbn(String prodGbn) {
		this.prodGbn = prodGbn;
	}

	public String getProdGbnNm() {
		return prodGbnNm;
	}

	public void setProdGbnNm(String prodGbnNm) {
		this.prodGbnNm = prodGbnNm;
	}

	public String getVerifyReqVer() {
		return verifyReqVer;
	}

	public void setVerifyReqVer(String verifyReqVer) {
		this.verifyReqVer = verifyReqVer;
	}

	public String getVerifyPrgrYnNm() {
		return verifyPrgrYnNm;
	}

	public void setVerifyPrgrYnNm(String verifyPrgrYnNm) {
		this.verifyPrgrYnNm = verifyPrgrYnNm;
	}

	public String getAgrmntStat() {
		return agrmntStat;
	}

	public void setAgrmntStat(String agrmntStat) {
		this.agrmntStat = agrmntStat;
	}

	public String getAgrmntStatNm() {
		return agrmntStatNm;
	}

	public void setAgrmntStatNm(String agrmntStatNm) {
		this.agrmntStatNm = agrmntStatNm;
	}

	public String getVmType() {
		return vmType;
	}

	public void setVmType(String vmType) {
		this.vmType = vmType;
	}

	public String getVmTypeNm() {
		return vmTypeNm;
	}

	public void setVmTypeNm(String vmTypeNm) {
		this.vmTypeNm = vmTypeNm;
	}

	public String getFirstInsDt() {
		return firstInsDt;
	}

	public void setFirstInsDt(String firstInsDt) {
		this.firstInsDt = firstInsDt;
	}

	public String getMbrNm() {
		return mbrNm;
	}

	public void setMbrNm(String mbrNm) {
		this.mbrNm = mbrNm;
	}

	public String getFirstAgrmntDt() {
		return firstAgrmntDt;
	}

	public void setFirstAgrmntDt(String firstAgrmntDt) {
		this.firstAgrmntDt = firstAgrmntDt;
	}

	public String getSaleStartDt() {
		return saleStartDt;
	}

	public void setSaleStartDt(String saleStartDt) {
		this.saleStartDt = saleStartDt;
	}

	public String getAdjRate() {
		return adjRate;
	}

	public void setAdjRate(String adjRate) {
		this.adjRate = adjRate;
	}

	public String getAdjRateSkt() {
		return adjRateSkt;
	}

	public void setAdjRateSkt(String adjRateSkt) {
		this.adjRateSkt = adjRateSkt;
	}

	public String getMbrNo() {
		return mbrNo;
	}

	public void setMbrNo(String mbrNo) {
		this.mbrNo = mbrNo;
	}

	public String getGameDelibGrd() {
		return gameDelibGrd;
	}

	public void setGameDelibGrd(String gameDelibGrd) {
		this.gameDelibGrd = gameDelibGrd;
	}

	public String getGameDelibGrdNm() {
		return gameDelibGrdNm;
	}

	public void setGameDelibGrdNm(String gameDelibGrdNm) {
		this.gameDelibGrdNm = gameDelibGrdNm;
	}

	public String getPromotionUrl() {
		return promotionUrl;
	}

	public void setPromotionUrl(String promotionUrl) {
		this.promotionUrl = promotionUrl;
	}

	public String getProdDescSmmr() {
		return prodDescSmmr;
	}

	public void setProdDescSmmr(String prodDescSmmr) {
		this.prodDescSmmr = prodDescSmmr;
	}

	public String getProdDescDtl() {
		return prodDescDtl;
	}

	public void setProdDescDtl(String prodDescDtl) {
		this.prodDescDtl = prodDescDtl;
	}

	public String getUpdDttm() {
		return updDttm;
	}

	public void setUpdDttm(String updDttm) {
		this.updDttm = updDttm;
	}

	public String getExposureDevNm() {
		return exposureDevNm;
	}

	public void setExposureDevNm(String exposureDevNm) {
		this.exposureDevNm = exposureDevNm;
	}

	public String getSignOption() {
		return signOption;
	}

	public void setSignOption(String signOption) {
		this.signOption = signOption;
	}

	public String getDrmYn() {
		return drmYn;
	}

	public void setDrmYn(String drmYn) {
		this.drmYn = drmYn;
	}

	public String getVerifyScnrFile() {
		return verifyScnrFile;
	}

	public void setVerifyScnrFile(String verifyScnrFile) {
		this.verifyScnrFile = verifyScnrFile;
	}

	public String getVerifyScnrFileNm() {
		return verifyScnrFileNm;
	}

	public void setVerifyScnrFileNm(String verifyScnrFileNm) {
		this.verifyScnrFileNm = verifyScnrFileNm;
	}

	public Long getTotal_count() {
		return total_count;
	}

	public void setTotal_count(Long total_count) {
		this.total_count = total_count;
	}

	public String getCorpProdNo() {
		return corpProdNo;
	}

	public void setCorpProdNo(String corpProdNo) {
		this.corpProdNo = corpProdNo;
	}

	public String getDwldCount() {
		return dwldCount;
	}

	public void setDwldCount(String dwldCount) {
		this.dwldCount = dwldCount;
	}

}