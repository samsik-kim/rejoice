package com.omp.admin.search.model;

import com.omp.commons.model.CommonModel;
import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;
import com.omp.commons.model.TotalCountCarriable;

public class Search extends CommonModel implements Pagenateable, TotalCountCarriable{
	private PagenateInfoModel	page		= new PagenateInfoModel();
	private Long    			totalCount;
	private String 				rnum;
	private String 				searchIdx="";
	private String 				searchNm="";
	private String 				expoPrior="";
	private String 				insId="";
	
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

	public String getSearchIdx() {
		return searchIdx;
	}

	public void setSearchIdx(String searchIdx) {
		this.searchIdx = searchIdx;
	}

	public String getSearchNm() {
		return searchNm;
	}

	public void setSearchNm(String searchNm) {
		this.searchNm = searchNm;
	}

	public String getExpoPrior() {
		return expoPrior;
	}

	public void setExpoPrior(String expoPrior) {
		this.expoPrior = expoPrior;
	}

	public String getInsId() {
		return insId;
	}

	public void setInsId(String insId) {
		this.insId = insId;
	}

}
