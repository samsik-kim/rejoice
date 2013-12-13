package com.omp.dev.contents.model;

import java.util.List;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.omp.commons.model.CommonModel;
import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;
import com.omp.commons.model.TotalCountCarriable;

@SuppressWarnings("serial")
public class SubContentsVerify extends CommonModel implements Pagenateable, TotalCountCarriable {
	
	private String cid;
	private String verifyReqVer;
	private String scid;
	private String vmVerMin;
	private String vmVerMax;
	private String vmVerTarget;
	private String pkgNm;
	private String versionCode;
	private String versionName;
	private String runFilePos;
	private String runFileNm;
	private String runFileSize;
	private String appCtResultFile;
	private String appCtResultFileNm;
	private String appCtCmt;	
	private String ctInsDttm;
	private String agrmntStat;
	private String addFile;
	private String addFileNm;
	private List<VerifyItemCd> itemCdList;
	private List<SubContentsVerify> addFileList;
	
	private ContentsVerify contentsVerify;
	
	private PagenateInfoModel	page = new PagenateInfoModel();
    private Long	totalCount;
	
    @Override
	public PagenateInfoModel getPage() {
		return this.page;
	}

	@Override
	public Long getTotalCount() {
		return this.totalCount;
	}

	@Override
	public Long setTotalCount(Long totalCount) {
		return this.totalCount	= totalCount;
	}

	public ContentsVerify getContentsVerify() {
		return contentsVerify;
	}

	public void setContentsVerify(ContentsVerify contentsVerify) {
		this.contentsVerify = contentsVerify;
	}
	
	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getVerifyReqVer() {
		return verifyReqVer;
	}

	public void setVerifyReqVer(String verifyReqVer) {
		this.verifyReqVer = verifyReqVer;
	}

	public String getScid() {
		return scid;
	}

	public void setScid(String scid) {
		this.scid = scid;
	}

	public String getVmVerMin() {
		return vmVerMin;
	}

	public void setVmVerMin(String vmVerMin) {
		this.vmVerMin = vmVerMin;
	}

	public String getVmVerMax() {
		return vmVerMax;
	}

	public void setVmVerMax(String vmVerMax) {
		this.vmVerMax = vmVerMax;
	}

	public String getVmVerTarget() {
		return vmVerTarget;
	}

	public void setVmVerTarget(String vmVerTarget) {
		this.vmVerTarget = vmVerTarget;
	}

	public String getPkgNm() {
		return pkgNm;
	}

	public void setPkgNm(String pkgNm) {
		this.pkgNm = pkgNm;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getRunFilePos() {
		return runFilePos;
	}

	public void setRunFilePos(String runFilePos) {
		this.runFilePos = runFilePos;
	}

	public String getRunFileNm() {
		return runFileNm;
	}

	public void setRunFileNm(String runFileNm) {
		this.runFileNm = runFileNm;
	}

	public String getRunFileSize() {
		return runFileSize;
	}

	public void setRunFileSize(String runFileSize) {
		this.runFileSize = runFileSize;
	}

	public String getAppCtResultFile() {
		return appCtResultFile;
	}

	public void setAppCtResultFile(String appCtResultFile) {
		this.appCtResultFile = appCtResultFile;
	}

	public String getAppCtResultFileNm() {
		return appCtResultFileNm;
	}

	public void setAppCtResultFileNm(String appCtResultFileNm) {
		this.appCtResultFileNm = appCtResultFileNm;
	}

	public String getAppCtCmt() {
		return appCtCmt;
	}

	public void setAppCtCmt(String appCtCmt) {
		this.appCtCmt = appCtCmt;
	}

	public String getAgrmntStat() {
		return agrmntStat;
	}

	public void setAgrmntStat(String agrmntStat) {
		this.agrmntStat = agrmntStat;
	}

	public String getAddFile() {
		return addFile;
	}

	public void setAddFile(String addFile) {
		this.addFile = addFile;
	}

	public String getAddFileNm() {
		return addFileNm;
	}

	public void setAddFileNm(String addFileNm) {
		this.addFileNm = addFileNm;
	}

	public List<VerifyItemCd> getItemCdList() {
		return itemCdList;
	}

	public void setItemCdList(List<VerifyItemCd> itemCdList) {
		this.itemCdList = itemCdList;
	}

	public String getCtInsDttm() {
		return ctInsDttm;
	}

	public void setCtInsDttm(String ctInsDttm) {
		this.ctInsDttm = ctInsDttm;
	}

	public List<SubContentsVerify> getAddFileList() {
		return addFileList;
	}

	public void setAddFileList(List<SubContentsVerify> addFileList) {
		this.addFileList = addFileList;
	}

	@Override
    public String toString() { 
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); 
    } 

}
