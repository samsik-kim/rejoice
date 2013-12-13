package com.omp.admin.community.notice.model;

import java.io.File;

import com.omp.commons.model.CommonModel;
import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;

/**
 * 공지사항 정보 정의 클래스.
 * 테이블 필드 외의 다른 정보는 타 객체를 만들어서 사용합니다.
 * @author soohee
 * 
 */
@SuppressWarnings("serial")
public class Notice extends CommonModel implements Pagenateable {

	private long noticeId;
	private String ctgrCd;
	private String title;
	private String dscr;
	private long hit;
	private String mainOpenYn;
	private String mainOpenDttm;
	private String openYn;
	private String delYn;
	//private long gid;
	private String gid;
	private String insDttm;
	private String insBy;
	private String updDttm;
	private String updBy;

	private String insNm = null;
	private String updNm = null;

	private String selectedNoticeId = null;

	private String searchOpenYn = null;

	private File attFileImg;
	private String attFileImgFileName;
	private String attFileImgContentType = null; // 첨부파일 컨텐츠타입
	private File attFileEtc;
	private String attFileEtcFileName;
	private String attFileEtcContentType = null; // 첨부파일 컨텐츠타입

	private String uploadImgLimit = ""; //첨부파일 사이즈제한
	private String uploadImgExt = ""; //첨부가능확장자
	private String uploadEtcLimit = ""; //첨부파일 사이즈제한
	private String uploadEtcExt = ""; //첨부가능확장자

	private String asisImgFid;
	private String asisEtcFid;
	private String tobeImgFid;
	private String tobeEtcFid;

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

	public long getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(long noticeId) {
		this.noticeId = noticeId;
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

	public String getMainOpenYn() {
		return mainOpenYn;
	}

	public void setMainOpenYn(String mainOpenYn) {
		this.mainOpenYn = mainOpenYn;
	}

	public String getMainOpenDttm() {
		return mainOpenDttm;
	}

	public void setMainOpenDttm(String mainOpenDttm) {
		this.mainOpenDttm = mainOpenDttm;
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

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
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

	public String getSelectedNoticeId() {
		return selectedNoticeId;
	}

	public void setSelectedNoticeId(String selectedNoticeId) {
		this.selectedNoticeId = selectedNoticeId;
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

	public String getAttFileImgContentType() {
		return attFileImgContentType;
	}

	public void setAttFileImgContentType(String attFileImgContentType) {
		this.attFileImgContentType = attFileImgContentType;
	}

	public File getAttFileEtc() {
		return attFileEtc;
	}

	public void setAttFileEtc(File attFileEtc) {
		this.attFileEtc = attFileEtc;
	}

	public String getAttFileEtcFileName() {
		return attFileEtcFileName;
	}

	public void setAttFileEtcFileName(String attFileEtcFileName) {
		this.attFileEtcFileName = attFileEtcFileName;
	}

	public String getAttFileEtcContentType() {
		return attFileEtcContentType;
	}

	public void setAttFileEtcContentType(String attFileEtcContentType) {
		this.attFileEtcContentType = attFileEtcContentType;
	}

	public String getUploadImgLimit() {
		return uploadImgLimit;
	}

	public void setUploadImgLimit(String uploadImgLimit) {
		this.uploadImgLimit = uploadImgLimit;
	}

	public String getUploadImgExt() {
		return uploadImgExt;
	}

	public void setUploadImgExt(String uploadImgExt) {
		this.uploadImgExt = uploadImgExt;
	}

	public String getUploadEtcLimit() {
		return uploadEtcLimit;
	}

	public void setUploadEtcLimit(String uploadEtcLimit) {
		this.uploadEtcLimit = uploadEtcLimit;
	}

	public String getUploadEtcExt() {
		return uploadEtcExt;
	}

	public void setUploadEtcExt(String uploadEtcExt) {
		this.uploadEtcExt = uploadEtcExt;
	}

	public String getAsisImgFid() {
		return asisImgFid;
	}

	public void setAsisImgFid(String asisImgFid) {
		this.asisImgFid = asisImgFid;
	}

	public String getAsisEtcFid() {
		return asisEtcFid;
	}

	public void setAsisEtcFid(String asisEtcFid) {
		this.asisEtcFid = asisEtcFid;
	}

	public String getTobeImgFid() {
		return tobeImgFid;
	}

	public void setTobeImgFid(String tobeImgFid) {
		this.tobeImgFid = tobeImgFid;
	}

	public String getTobeEtcFid() {
		return tobeEtcFid;
	}

	public void setTobeEtcFid(String tobeEtcFid) {
		this.tobeEtcFid = tobeEtcFid;
	}

}
