package com.omp.admin.verify.model;

import com.omp.commons.model.CommonModel;
import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;
import com.omp.commons.model.TotalCountCarriable;

@SuppressWarnings("serial")
public class Verify extends CommonModel implements Pagenateable, TotalCountCarriable{

	private PagenateInfoModel	page		= new PagenateInfoModel();
	private Long    			totalCount;
	private String 				rnum;
	private String testCaseSeq = "";   	
	private String vmType = "";   	
	private String ctCtgSeq = "";   	
	private String ctCtgNm = "";
	private String titleNm = "";   	
	private String explain = "";   	
	private String stepFile = "";   	
	private String stepFileNm = ""; 
	private String platCtgr = ""; 
	private String useYn = ""; 
	private String keyword = ""; 
	private String category = ""; 
	private String insId = ""; 

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
	
	public String getTestCaseSeq() {
		return testCaseSeq;
	}

	public void setTestCaseSeq(String testCaseSeq) {
		this.testCaseSeq = testCaseSeq;
	}

	public String getVmType() {
		return vmType;
	}

	public void setVmType(String vmType) {
		this.vmType = vmType;
	}

	public String getCtCtgSeq() {
		return ctCtgSeq;
	}

	public void setCtCtgSeq(String ctCtgSeq) {
		this.ctCtgSeq = ctCtgSeq;
	}

	public String getTitleNm() {
		return titleNm;
	}

	public void setTitleNm(String titleNm) {
		this.titleNm = titleNm.trim();
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain.trim();
	}

	public String getStepFile() {
		return stepFile;
	}

	public void setStepFile(String stepFile) {
		this.stepFile = stepFile;
	}

	public String getStepFileNm() {
		return stepFileNm;
	}

	public void setStepFileNm(String stepFileNm) {
		this.stepFileNm = stepFileNm;
	}

	public String getPlatCtgr() {
		return platCtgr;
	}

	public void setPlatCtgr(String platCtgr) {
		this.platCtgr = platCtgr;
	}
	
	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	public String getCtCtgNm() {
		return ctCtgNm;
	}

	public void setCtCtgNm(String ctCtgNm) {
		this.ctCtgNm = ctCtgNm;
	}
	
	public String getInsId() {
		return insId;
	}

	public void setInsId(String insId) {
		this.insId = insId;
	}
}
