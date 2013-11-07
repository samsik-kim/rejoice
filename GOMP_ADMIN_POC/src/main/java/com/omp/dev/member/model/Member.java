package com.omp.dev.member.model;

import com.omp.commons.model.CommonModel;

/**
 * @author P007974
 *
 */
@SuppressWarnings("serial")
public class Member extends CommonModel {

	private String mbrNo;					//회원번호
	private String mbrClsCd;				//회원구분코드
	private String mbrCatCd;				//회원분류코드 
	private String devMbrStatCd;			//개발자회원상태코드
	private String mbrStatCd;				//회원상태코드
	private String mbrGrCd;					//회원등급코드
	private String mbrStartDt;				//회원가입일자
	private String mbrEndDt;				//회원탈퇴일자
	private String mbrApprDt;				//회원승인일자
	private String mbrId;					//회원ID
	private String mbrPw;					//비밀번호
	private String emailCertYn;				//이메일인증여부
	private String psRegNo;					//등록번호
	private String mbrNm;					//성명
	private String telNo;					//전화번호
	private String hpNo;					//휴대폰번호
	private String birthDt;					//생년월일
	private String naCd;					//국가코드
	private String sex;						//성별
	private String city;					//도시
	private String zipCd;					//우편번호
	private String homeAddrDtl;				//상세주소
	private String emailAddr;				//이메일주소
	private String newsYn;					//뉴스레터수신여부
	private String webSiteUrl;				//홈페이지주소
	private String opNm;					//담당자명
	private String opEmailAddr;				//담당자이메일주소
	private String opTelNo;					//담당자전화번호
	private String opHpNo;					//담당자휴대폰번호
	private String acctNo;					//계좌번호
	private String backNm;					//은행명
	private String backCd;					//은행코드
	private String backBranchNm;			//은행지점명
	private String backBranchCd;			//은행지점코드
	private String currencyUnit;			//통화단위
	private String bankGlCd;				//Swift Code
	private String bankGlType;				//Swift Code / ABA No
	private String acctNm;					//예금주명
	private String acctCertYn;				//계좡인증여부
	private String compNm;					//상호명
	private String bizCatCd;				//사업자종류코드
	private String regDttm;					//등록일시
	private String updId;					//수정자
	private String updDttm;					//수정일시
	private String payTransDt;				//유료전환일자
	private String mbrStatRegDt;			//개발자회원상태 등록일자
	private String virAcctNo;				//가상계좌
	private String lastTransRegDt;			//전환 신청일
	private String resultMsg;				//resultMag
	
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
	 * @return the mbrCatCd
	 */
	public String getMbrCatCd() {
		return mbrCatCd;
	}
	/**
	 * @param mbrCatCd the mbrCatCd to set
	 */
	public void setMbrCatCd(String mbrCatCd) {
		this.mbrCatCd = mbrCatCd;
	}
	/**
	 * @return the devMbrStatCd
	 */
	public String getDevMbrStatCd() {
		return devMbrStatCd;
	}
	/**
	 * @param devMbrStatCd the devMbrStatCd to set
	 */
	public void setDevMbrStatCd(String devMbrStatCd) {
		this.devMbrStatCd = devMbrStatCd;
	}
	/**
	 * @return the mbrStatCd
	 */
	public String getMbrStatCd() {
		return mbrStatCd;
	}
	/**
	 * @param mbrStatCd the mbrStatCd to set
	 */
	public void setMbrStatCd(String mbrStatCd) {
		this.mbrStatCd = mbrStatCd;
	}
	/**
	 * @return the mbrGrCd
	 */
	public String getMbrGrCd() {
		return mbrGrCd;
	}
	/**
	 * @param mbrGrCd the mbrGrCd to set
	 */
	public void setMbrGrCd(String mbrGrCd) {
		this.mbrGrCd = mbrGrCd;
	}
	/**
	 * @return the mbrStartDt
	 */
	public String getMbrStartDt() {
		return mbrStartDt;
	}
	/**
	 * @param mbrStartDt the mbrStartDt to set
	 */
	public void setMbrStartDt(String mbrStartDt) {
		this.mbrStartDt = mbrStartDt;
	}
	/**
	 * @return the mbrEndDt
	 */
	public String getMbrEndDt() {
		return mbrEndDt;
	}
	/**
	 * @param mbrEndDt the mbrEndDt to set
	 */
	public void setMbrEndDt(String mbrEndDt) {
		this.mbrEndDt = mbrEndDt;
	}
	/**
	 * @return the mbrApprDt
	 */
	public String getMbrApprDt() {
		return mbrApprDt;
	}
	/**
	 * @param mbrApprDt the mbrApprDt to set
	 */
	public void setMbrApprDt(String mbrApprDt) {
		this.mbrApprDt = mbrApprDt;
	}
	/**
	 * @return the mbrId
	 */
	public String getMbrId() {
		return mbrId;
	}
	/**
	 * @param mbrId the mbrId to set
	 */
	public void setMbrId(String mbrId) {
		this.mbrId = mbrId;
	}
	/**
	 * @return the mbrPw
	 */
	public String getMbrPw() {
		return mbrPw;
	}
	/**
	 * @param mbrPw the mbrPw to set
	 */
	public void setMbrPw(String mbrPw) {
		this.mbrPw = mbrPw;
	}
	/**
	 * @return the emailCertYn
	 */
	public String getEmailCertYn() {
		return emailCertYn;
	}
	/**
	 * @param emailCertYn the emailCertYn to set
	 */
	public void setEmailCertYn(String emailCertYn) {
		this.emailCertYn = emailCertYn;
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
	 * @return the mbrNm
	 */
	public String getMbrNm() {
		return mbrNm;
	}
	/**
	 * @param mbrNm the mbrNm to set
	 */
	public void setMbrNm(String mbrNm) {
		this.mbrNm = mbrNm;
	}
	/**
	 * @return the birthDt
	 */
	public String getBirthDt() {
		return birthDt;
	}
	/**
	 * @return the telNo
	 */
	public String getTelNo() {
		return telNo;
	}
	/**
	 * @param telNo the telNo to set
	 */
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	/**
	 * @return the hpNo
	 */
	public String getHpNo() {
		return hpNo;
	}
	/**
	 * @param hpNo the hpNo to set
	 */
	public void setHpNo(String hpNo) {
		this.hpNo = hpNo;
	}
	/**
	 * @param birthDt the birthDt to set
	 */
	public void setBirthDt(String birthDt) {
		this.birthDt = birthDt;
	}
	/**
	 * @return the naCd
	 */
	public String getNaCd() {
		return naCd;
	}
	/**
	 * @param naCd the naCd to set
	 */
	public void setNaCd(String naCd) {
		this.naCd = naCd;
	}
	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
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
	 * @return the emailAddr
	 */
	public String getEmailAddr() {
		return emailAddr;
	}
	/**
	 * @param emailAddr the emailAddr to set
	 */
	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}
	/**
	 * @return the newsYn
	 */
	public String getNewsYn() {
		return newsYn;
	}
	/**
	 * @param newsYn the newsYn to set
	 */
	public void setNewsYn(String newsYn) {
		this.newsYn = newsYn;
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
	 * @return the opEmailAddr
	 */
	public String getOpEmailAddr() {
		return opEmailAddr;
	}
	/**
	 * @param opEmailAddr the opEmailAddr to set
	 */
	public void setOpEmailAddr(String opEmailAddr) {
		this.opEmailAddr = opEmailAddr;
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
	 * @return the backNm
	 */
	public String getBackNm() {
		return backNm;
	}
	/**
	 * @param backNm the backNm to set
	 */
	public void setBackNm(String backNm) {
		this.backNm = backNm;
	}
	/**
	 * @return the backCd
	 */
	public String getBackCd() {
		return backCd;
	}
	/**
	 * @param backCd the backCd to set
	 */
	public void setBackCd(String backCd) {
		this.backCd = backCd;
	}
	/**
	 * @return the backBranchNm
	 */
	public String getBackBranchNm() {
		return backBranchNm;
	}
	/**
	 * @param backBranchNm the backBranchNm to set
	 */
	public void setBackBranchNm(String backBranchNm) {
		this.backBranchNm = backBranchNm;
	}
	/**
	 * @return the backBranchCd
	 */
	public String getBackBranchCd() {
		return backBranchCd;
	}
	/**
	 * @param backBranchCd the backBranchCd to set
	 */
	public void setBackBranchCd(String backBranchCd) {
		this.backBranchCd = backBranchCd;
	}
	/**
	 * @return the currencyUnit
	 */
	public String getCurrencyUnit() {
		return currencyUnit;
	}
	/**
	 * @param currencyUnit the currencyUnit to set
	 */
	public void setCurrencyUnit(String currencyUnit) {
		this.currencyUnit = currencyUnit;
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
	 * @return the regDttm
	 */
	public String getRegDttm() {
		return regDttm;
	}
	/**
	 * @param regDttm the regDttm to set
	 */
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	/**
	 * @return the updId
	 */
	public String getUpdId() {
		return updId;
	}
	/**
	 * @param updId the updId to set
	 */
	public void setUpdId(String updId) {
		this.updId = updId;
	}
	/**
	 * @return the updDttm
	 */
	public String getUpdDttm() {
		return updDttm;
	}
	/**
	 * @param updDttm the updDttm to set
	 */
	public void setUpdDttm(String updDttm) {
		this.updDttm = updDttm;
	}
	/**
	 * @return the resultMsg
	 */
	public String getResultMsg() {
		return resultMsg;
	}
	/**
	 * @param resultMsg the resultMsg to set
	 */
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	/**
	 * @return the payTransDt
	 */
	public String getPayTransDt() {
		return payTransDt;
	}
	/**
	 * @param payTransDt the payTransDt to set
	 */
	public void setPayTransDt(String payTransDt) {
		this.payTransDt = payTransDt;
	}
	/**
	 * @return the mbrStatRegDt
	 */
	public String getMbrStatRegDt() {
		return mbrStatRegDt;
	}
	/**
	 * @param mbrStatRegDt the mbrStatRegDt to set
	 */
	public void setMbrStatRegDt(String mbrStatRegDt) {
		this.mbrStatRegDt = mbrStatRegDt;
	}
	/**
	 * @return the virAcctNo
	 */
	public String getVirAcctNo() {
		return virAcctNo;
	}
	/**
	 * @param virAcctNo the virAcctNo to set
	 */
	public void setVirAcctNo(String virAcctNo) {
		this.virAcctNo = virAcctNo;
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
	 * @return the lastTransRegDt
	 */
	public String getLastTransRegDt() {
		return lastTransRegDt;
	}
	/**
	 * @param lastTransRegDt the lastTransRegDt to set
	 */
	public void setLastTransRegDt(String lastTransRegDt) {
		this.lastTransRegDt = lastTransRegDt;
	}
	
}
