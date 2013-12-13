package com.omp.dev.community.model;

import java.io.File;
import java.io.Serializable;

import com.omp.commons.model.CommonModel;
import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;

@SuppressWarnings("serial")
public class WebFaq extends CommonModel implements Pagenateable, Serializable {

	private long faqId;
	private String ctgrCd;
	private String title;
	private String dscr;
	private long hit;
	private String sort;
	private String bestYn;
	private String bestDttm;
	private String openYn;
	private String delYn;
	private String insDttm;
	private String insBy;
	private String updDttm;
	private String updBy;
	private String delDttm;
	private String attFile;
	private String attFileNm;

	private String highCtgr;
	private String ctgrNm;
	private String insNm;
	private String updNm;
	
	private String gid;				// gid
	private String fnm;				
	private String ofnm;				
	private String fsz;				
	private String furl;				
	private String ftype;	

	private String sortFaqId = null;

	private String selectedFaqId = null;
	private String searchOpenYn = null;

	private File attFileImg;
	private String attFileImgFileName;

	private PagenateInfoModel page = new PagenateInfoModel(10);
	private Long totalCount;
	private String rnum;
	
	public PagenateInfoModel getPage() {
		return this.page;
	}

	public Long getTotalCount() {
		return this.totalCount;
	}

	public Long setTotalCount(Long totalCount) {
		return this.totalCount = totalCount;
	}
	
	public String getRnum() {
		return rnum;
	}

	public void setRnum(String rnum) {
		this.rnum = rnum;
	}

	public long getFaqId() {
		return faqId;
	}

	public void setFaqId(long faqId) {
		this.faqId = faqId;
	}

	public String getCtgrCd() {
		return ctgrCd;
	}

	public void setCtgrCd(String ctgrCd) {
		this.ctgrCd = ctgrCd;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDscr() {
		return dscr;
	}

	public void setDscr(String dscr) {
		this.dscr = dscr;
	}

	public long getHit() {
		return hit;
	}

	public void setHit(long hit) {
		this.hit = hit;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getBestYn() {
		return bestYn;
	}

	public void setBestYn(String bestYn) {
		this.bestYn = bestYn;
	}

	public String getBestDttm() {
		return bestDttm;
	}

	public void setBestDttm(String bestDttm) {
		this.bestDttm = bestDttm;
	}

	public String getOpenYn() {
		return openYn;
	}

	public void setOpenYn(String openYn) {
		this.openYn = openYn;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public String getInsDttm() {
		return insDttm;
	}

	public void setInsDttm(String insDttm) {
		this.insDttm = insDttm;
	}

	public String getInsBy() {
		return insBy;
	}

	public void setInsBy(String insBy) {
		this.insBy = insBy;
	}

	public String getUpdDttm() {
		return updDttm;
	}

	public void setUpdDttm(String updDttm) {
		this.updDttm = updDttm;
	}

	public String getUpdBy() {
		return updBy;
	}

	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}

	public String getDelDttm() {
		return delDttm;
	}

	public void setDelDttm(String delDttm) {
		this.delDttm = delDttm;
	}

	public String getAttFile() {
		return attFile;
	}

	public void setAttFile(String attFile) {
		this.attFile = attFile;
	}

	public String getAttFileNm() {
		return attFileNm;
	}

	public void setAttFileNm(String attFileNm) {
		this.attFileNm = attFileNm;
	}

	public String getHighCtgr() {
		return highCtgr;
	}

	public void setHighCtgr(String highCtgr) {
		this.highCtgr = highCtgr;
	}

	public String getCtgrNm() {
		return ctgrNm;
	}

	public void setCtgrNm(String ctgrNm) {
		this.ctgrNm = ctgrNm;
	}

	public String getInsNm() {
		return insNm;
	}

	public void setInsNm(String insNm) {
		this.insNm = insNm;
	}

	public String getUpdNm() {
		return updNm;
	}

	public void setUpdNm(String updNm) {
		this.updNm = updNm;
	}

	public String getSortFaqId() {
		return sortFaqId;
	}

	public void setSortFaqId(String sortFaqId) {
		this.sortFaqId = sortFaqId;
	}

	public String getSelectedFaqId() {
		return selectedFaqId;
	}

	public void setSelectedFaqId(String selectedFaqId) {
		this.selectedFaqId = selectedFaqId;
	}

	public String getSearchOpenYn() {
		return searchOpenYn;
	}

	public void setSearchOpenYn(String searchOpenYn) {
		this.searchOpenYn = searchOpenYn;
	}

	public File getAttFileImg() {
		return attFileImg;
	}

	public void setAttFileImg(File attFileImg) {
		this.attFileImg = attFileImg;
	}

	public String getAttFileImgFileName() {
		return attFileImgFileName;
	}

	public void setAttFileImgFileName(String attFileImgFileName) {
		this.attFileImgFileName = attFileImgFileName;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getFnm() {
		return fnm;
	}

	public void setFnm(String fnm) {
		this.fnm = fnm;
	}

	public String getOfnm() {
		return ofnm;
	}

	public void setOfnm(String ofnm) {
		this.ofnm = ofnm;
	}

	public String getFsz() {
		return fsz;
	}

	public void setFsz(String fsz) {
		this.fsz = fsz;
	}

	public String getFurl() {
		return furl;
	}

	public void setFurl(String furl) {
		this.furl = furl;
	}

	public String getFtype() {
		return ftype;
	}

	public void setFtype(String ftype) {
		this.ftype = ftype;
	}
	
}
