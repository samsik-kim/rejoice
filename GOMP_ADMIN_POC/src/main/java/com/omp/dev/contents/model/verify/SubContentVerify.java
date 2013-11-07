package com.omp.dev.contents.model.verify;

import java.util.List;

import com.omp.dev.contents.model.VerifyItemCd;

public class SubContentVerify {
	
	private String runFilePos;	// 실행파일위치(APK파일)
	private String runFileNm;	// 실행파일명 (APK파일)
	private String vmVerMin;	// 최소지원버전
	private String vmVerMax;	// 최대지원버전
	private String vmVerTarget;	// 타겟버전
	private String versionCode; // 버전코드
	private String versionName; // 버전명
	private String pkgNm;		// 패키지명
	private List<VerifyItemCd> lcdSize;	// 지원 LCD SIZE
	private List<List<SubContentsAddFile>> addFile; // 검증결과첨부파일
	private String appCtCmt;	// 검증결과 상세내역
	
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
	public String getPkgNm() {
		return pkgNm;
	}
	public void setPkgNm(String pkgNm) {
		this.pkgNm = pkgNm;
	}
	public List<VerifyItemCd> getLcdSize() {
		return lcdSize;
	}
	public List<List<SubContentsAddFile>> getAddFile() {
		return addFile;
	}
	public void setAddFile(List<List<SubContentsAddFile>> addFile) {
		this.addFile = addFile;
	}
	public String getAppCtCmt() {
		return appCtCmt;
	}
	public void setAppCtCmt(String appCtCmt) {
		this.appCtCmt = appCtCmt;
	}
	
	
}
