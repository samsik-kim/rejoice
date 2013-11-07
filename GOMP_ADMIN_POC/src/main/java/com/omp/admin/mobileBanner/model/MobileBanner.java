package com.omp.admin.mobileBanner.model;

import com.omp.commons.model.CommonModel;
import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;
import com.omp.commons.model.TotalCountCarriable;

public class MobileBanner extends CommonModel implements Pagenateable, TotalCountCarriable {
	private PagenateInfoModel	page		= new PagenateInfoModel();
	private Long    			totalCount;
	private String 				rnum;
	private String 				bnrNo="";
	private String 				title="";
	private String 				bannerType="";
	private String 				bnrEndDt="";
	private String 				bnrStartDt="";
	private String 				prodId="";
	private String 				horImgSize;
	private String 				imgNm="";
	private String 				imgOrgNm="";
	private String 				imgPos="";
	private String 				imgSize="";
	private int 				openOrder=0;
	private String 				openYn="";
	private String 				state="";
	private String 				keyWord="";
	private String 				regDttm="";
	private String 				insId="";
	private String 				startH="";
	private String 				endH="";
	private String 				productType="";
	
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

	public String getBnrNo() {
		return bnrNo;
	}

	public void setBnrNo(String bnrNo) {
		this.bnrNo = bnrNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBannerType() {
		return bannerType;
	}

	public void setBannerType(String bannerType) {
		this.bannerType = bannerType;
	}

	public String getBnrEndDt() {
		return bnrEndDt;
	}

	public void setBnrEndDt(String bnrEndDt) {
		this.bnrEndDt = bnrEndDt;
	}

	public String getBnrStartDt() {
		return bnrStartDt;
	}

	public void setBnrStartDt(String bnrStartDt) {
		this.bnrStartDt = bnrStartDt;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getHorImgSize() {
		return horImgSize;
	}

	public void setHorImgSize(String horImgSize) {
		this.horImgSize = horImgSize;
	}

	public String getImgNm() {
		return imgNm;
	}

	public void setImgNm(String imgNm) {
		this.imgNm = imgNm;
	}

	public String getImgOrgNm() {
		return imgOrgNm;
	}

	public void setImgOrgNm(String imgOrgNm) {
		this.imgOrgNm = imgOrgNm;
	}

	public String getImgPos() {
		return imgPos;
	}

	public void setImgPos(String imgPos) {
		this.imgPos = imgPos;
	}

	public String getImgSize() {
		return imgSize;
	}

	public void setImgSize(String imgSize) {
		this.imgSize = imgSize;
	}

	public String getOpenYn() {
		return openYn;
	}

	public void setOpenYn(String openYn) {
		this.openYn = openYn;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getRegDttm() {
		return regDttm;
	}

	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}

	public String getInsId() {
		return insId;
	}

	public void setInsId(String insId) {
		this.insId = insId;
	}

	public String getStartH() {
		return startH;
	}

	public void setStartH(String startH) {
		this.startH = startH;
	}

	public String getEndH() {
		return endH;
	}

	public void setEndH(String endH) {
		this.endH = endH;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public int getOpenOrder() {
		return openOrder;
	}

	public void setOpenOrder(int openOrder) {
		this.openOrder = openOrder;
	}
	
}
