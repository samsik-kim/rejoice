package com.omp.dev.member.model;

import java.io.File;
import java.io.Serializable;

@SuppressWarnings("serial")
public class MemberTransHist implements Serializable {

	// 화면 제어
	private String mbrClsCd;			// 회원 구분 코드
	private String dispStat;			// 정산정보 화면
	
	// 전환 이력
	private String transNo;				// 전환 신청번호
	private String mbrNo;				// 회원 번호
	private String city;				// 도시
	private String zipCd;				// 우편 번호
	private String homeAddrDtl;			// 상세 주소
	private String webSiteUrl;			// Web Site Url
	private String opNm;				// 담당자명
	private String opEmail;				// 담당자 이메일
	private String opTelNo;				// 담당자 전화
	private String opHpNo;				// 담당자 휴대폰
	private String acctNo;				// 계좌번호
	private String bankNm;				// 은행명
	private String bankCd;				// 은행 코드
	private String banckBranchNm;		// 지점명
	private String banckBranchCd;		// 지점 코드
	private String currentcyUnit;		// 화폐선택
	private String bankGlCd;			// Swift code/IBAN
	private String bankGlType;				//Swift Code / ABA No
	private String acctNm;				// 예금주
	private String acctCertYn;			// 계좌인증 여부
	private String compNm;				// 상호명
	private String psRegNo;				// 등록 번호
	private String bizCatCd;			// 사업자 종류
	private String traceInfo;			// 전환 Trace
	private String prcStatCd;			// 처리상태 코드
	private String rejReason;			// 거절 사유
	
	// 등록 서류
	private String identityDocNm;			// 신상증명서 사본명
	private String acctDocNm;				// 통장 사본명
	private File identityDoc;				// 신상증명서 사본
	private String identityDocContentType;	// 신상증명서 업로드 파일 타입
	private String identityDocFileName;		// 신상증명서 업로드 파일명
	private File acctDoc;					// 통장 사본
	private String acctDocContentType;		// 통장사본 업로드 파일 타입
	private String acctDocFileName;			// 통장사본 업로드 파일명
	
	private String identityDocPath;			// 신상증명서 파일경로
	private String acctDocPath;				// 통장사본 파일경로
	
	/**
	 * @return the mbrClsCd
	 */
	public String getMbrClsCd() {
		return mbrClsCd;
	}
	/**
	 * @param mbrClsCd the mbrClsCd to set
	 */
	public void setMbrClsCd(String mbrClsCd) {
		this.mbrClsCd = mbrClsCd;
	}
	/**
	 * @return the dispStat
	 */
	public String getDispStat() {
		return dispStat;
	}
	/**
	 * @param dispStat the dispStat to set
	 */
	public void setDispStat(String dispStat) {
		this.dispStat = dispStat;
	}
	/**
	 * @return the transNo
	 */
	public String getTransNo() {
		return transNo;
	}
	/**
	 * @param transNo the transNo to set
	 */
	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}
	/**
	 * @return the mbrNo
	 */
	public String getMbrNo() {
		return mbrNo;
	}
	/**
	 * @param mbrNo the mbrNo to set
	 */
	public void setMbrNo(String mbrNo) {
		this.mbrNo = mbrNo;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the zipCd
	 */
	public String getZipCd() {
		return zipCd;
	}
	/**
	 * @param zipCd the zipCd to set
	 */
	public void setZipCd(String zipCd) {
		this.zipCd = zipCd;
	}
	/**
	 * @return the homeAddrDtl
	 */
	public String getHomeAddrDtl() {
		return homeAddrDtl;
	}
	/**
	 * @param homeAddrDtl the homeAddrDtl to set
	 */
	public void setHomeAddrDtl(String homeAddrDtl) {
		this.homeAddrDtl = homeAddrDtl;
	}
	/**
	 * @return the webSiteUrl
	 */
	public String getWebSiteUrl() {
		return webSiteUrl;
	}
	/**
	 * @param webSiteUrl the webSiteUrl to set
	 */
	public void setWebSiteUrl(String webSiteUrl) {
		this.webSiteUrl = webSiteUrl;
	}
	/**
	 * @return the opNm
	 */
	public String getOpNm() {
		return opNm;
	}
	/**
	 * @param opNm the opNm to set
	 */
	public void setOpNm(String opNm) {
		this.opNm = opNm;
	}
	/**
	 * @return the opEmail
	 */
	public String getOpEmail() {
		return opEmail;
	}
	/**
	 * @param opEmail the opEmail to set
	 */
	public void setOpEmail(String opEmail) {
		this.opEmail = opEmail;
	}
	/**
	 * @return the opTelNo
	 */
	public String getOpTelNo() {
		return opTelNo;
	}
	/**
	 * @param opTelNo the opTelNo to set
	 */
	public void setOpTelNo(String opTelNo) {
		this.opTelNo = opTelNo;
	}
	/**
	 * @return the opHpNo
	 */
	public String getOpHpNo() {
		return opHpNo;
	}
	/**
	 * @param opHpNo the opHpNo to set
	 */
	public void setOpHpNo(String opHpNo) {
		this.opHpNo = opHpNo;
	}
	/**
	 * @return the acctNo
	 */
	public String getAcctNo() {
		return acctNo;
	}
	/**
	 * @param acctNo the acctNo to set
	 */
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	/**
	 * @return the bankNm
	 */
	public String getBankNm() {
		return bankNm;
	}
	/**
	 * @param bankNm the bankNm to set
	 */
	public void setBankNm(String bankNm) {
		this.bankNm = bankNm;
	}
	/**
	 * @return the bankCd
	 */
	public String getBankCd() {
		return bankCd;
	}
	/**
	 * @param bankCd the bankCd to set
	 */
	public void setBankCd(String bankCd) {
		this.bankCd = bankCd;
	}
	/**
	 * @return the banckBranchNm
	 */
	public String getBanckBranchNm() {
		return banckBranchNm;
	}
	/**
	 * @param banckBranchNm the banckBranchNm to set
	 */
	public void setBanckBranchNm(String banckBranchNm) {
		this.banckBranchNm = banckBranchNm;
	}
	/**
	 * @return the banckBranchCd
	 */
	public String getBanckBranchCd() {
		return banckBranchCd;
	}
	/**
	 * @param banckBranchCd the banckBranchCd to set
	 */
	public void setBanckBranchCd(String banckBranchCd) {
		this.banckBranchCd = banckBranchCd;
	}
	/**
	 * @return the currentcyUnit
	 */
	public String getCurrentcyUnit() {
		return currentcyUnit;
	}
	/**
	 * @param currentcyUnit the currentcyUnit to set
	 */
	public void setCurrentcyUnit(String currentcyUnit) {
		this.currentcyUnit = currentcyUnit;
	}
	/**
	 * @return the bankGlCd
	 */
	public String getBankGlCd() {
		return bankGlCd;
	}
	/**
	 * @param bankGlCd the bankGlCd to set
	 */
	public void setBankGlCd(String bankGlCd) {
		this.bankGlCd = bankGlCd;
	}
	/**
	 * @return the acctNm
	 */
	public String getAcctNm() {
		return acctNm;
	}
	/**
	 * @param acctNm the acctNm to set
	 */
	public void setAcctNm(String acctNm) {
		this.acctNm = acctNm;
	}
	/**
	 * @return the acctCertYn
	 */
	public String getAcctCertYn() {
		return acctCertYn;
	}
	/**
	 * @param acctCertYn the acctCertYn to set
	 */
	public void setAcctCertYn(String acctCertYn) {
		this.acctCertYn = acctCertYn;
	}
	/**
	 * @return the compNm
	 */
	public String getCompNm() {
		return compNm;
	}
	/**
	 * @param compNm the compNm to set
	 */
	public void setCompNm(String compNm) {
		this.compNm = compNm;
	}
	/**
	 * @return the psRegNo
	 */
	public String getPsRegNo() {
		return psRegNo;
	}
	/**
	 * @param psRegNo the psRegNo to set
	 */
	public void setPsRegNo(String psRegNo) {
		this.psRegNo = psRegNo;
	}
	/**
	 * @return the bizCatCd
	 */
	public String getBizCatCd() {
		return bizCatCd;
	}
	/**
	 * @param bizCatCd the bizCatCd to set
	 */
	public void setBizCatCd(String bizCatCd) {
		this.bizCatCd = bizCatCd;
	}
	/**
	 * @return the traceInfo
	 */
	public String getTraceInfo() {
		return traceInfo;
	}
	/**
	 * @param traceInfo the traceInfo to set
	 */
	public void setTraceInfo(String traceInfo) {
		this.traceInfo = traceInfo;
	}
	/**
	 * @return the prcStatCd
	 */
	public String getPrcStatCd() {
		return prcStatCd;
	}
	/**
	 * @param prcStatCd the prcStatCd to set
	 */
	public void setPrcStatCd(String prcStatCd) {
		this.prcStatCd = prcStatCd;
	}
	/**
	 * @return the rejReason
	 */
	public String getRejReason() {
		return rejReason;
	}
	/**
	 * @param rejReason the rejReason to set
	 */
	public void setRejReason(String rejReason) {
		this.rejReason = rejReason;
	}
	/**
	 * @return the identityDocNm
	 */
	public String getIdentityDocNm() {
		return identityDocNm;
	}
	/**
	 * @param identityDocNm the identityDocNm to set
	 */
	public void setIdentityDocNm(String identityDocNm) {
		this.identityDocNm = identityDocNm;
	}
	/**
	 * @return the acctDocNm
	 */
	public String getAcctDocNm() {
		return acctDocNm;
	}
	/**
	 * @param acctDocNm the acctDocNm to set
	 */
	public void setAcctDocNm(String acctDocNm) {
		this.acctDocNm = acctDocNm;
	}
	/**
	 * @return the identityDoc
	 */
	public File getIdentityDoc() {
		return identityDoc;
	}
	/**
	 * @param identityDoc the identityDoc to set
	 */
	public void setIdentityDoc(File identityDoc) {
		this.identityDoc = identityDoc;
	}
	/**
	 * @return the identityDocContentType
	 */
	public String getIdentityDocContentType() {
		return identityDocContentType;
	}
	/**
	 * @param identityDocContentType the identityDocContentType to set
	 */
	public void setIdentityDocContentType(String identityDocContentType) {
		this.identityDocContentType = identityDocContentType;
	}
	/**
	 * @return the identityDocFileName
	 */
	public String getIdentityDocFileName() {
		return identityDocFileName;
	}
	/**
	 * @param identityDocFileName the identityDocFileName to set
	 */
	public void setIdentityDocFileName(String identityDocFileName) {
		this.identityDocFileName = identityDocFileName;
	}
	/**
	 * @return the acctDoc
	 */
	public File getAcctDoc() {
		return acctDoc;
	}
	/**
	 * @param acctDoc the acctDoc to set
	 */
	public void setAcctDoc(File acctDoc) {
		this.acctDoc = acctDoc;
	}
	/**
	 * @return the acctDocContentType
	 */
	public String getAcctDocContentType() {
		return acctDocContentType;
	}
	/**
	 * @param acctDocContentType the acctDocContentType to set
	 */
	public void setAcctDocContentType(String acctDocContentType) {
		this.acctDocContentType = acctDocContentType;
	}
	/**
	 * @return the acctDocFileName
	 */
	public String getAcctDocFileName() {
		return acctDocFileName;
	}
	/**
	 * @param acctDocFileName the acctDocFileName to set
	 */
	public void setAcctDocFileName(String acctDocFileName) {
		this.acctDocFileName = acctDocFileName;
	}
	/**
	 * @return the bankGlType
	 */
	public String getBankGlType() {
		return bankGlType;
	}
	/**
	 * @param bankGlType the bankGlType to set
	 */
	public void setBankGlType(String bankGlType) {
		this.bankGlType = bankGlType;
	}
	/**
	 * @return the identityDocPath
	 */
	public String getIdentityDocPath() {
		return identityDocPath;
	}
	/**
	 * @param identityDocPath the identityDocPath to set
	 */
	public void setIdentityDocPath(String identityDocPath) {
		this.identityDocPath = identityDocPath;
	}
	/**
	 * @return the acctDocPath
	 */
	public String getAcctDocPath() {
		return acctDocPath;
	}
	/**
	 * @param acctDocPath the acctDocPath to set
	 */
	public void setAcctDocPath(String acctDocPath) {
		this.acctDocPath = acctDocPath;
	}
	
}
