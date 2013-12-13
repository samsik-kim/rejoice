package com.omp.admin.member.membermgr.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.omp.commons.model.CommonModel;
import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;
import com.omp.commons.model.TotalCountCarriable;

/**
 * Member Management - UserMember withdraw Management
 * 
 * @author HanJH
 * @version 0.1
 */
@SuppressWarnings("serial")
public class MemberContract extends CommonModel implements Pagenateable, TotalCountCarriable {
	// search
	private String searchType1;
	private String searchType2;
	private String searchType3;
	private String searchType4;

	// TBL_US_MEMBER Table Column
	private String mbrNo;          
	private String mbrClsCd;       
	private String mbrCatCd;       
	private String devMbrStatCd;
	private String mbrStatRegDt;
	private String mbrStatCd;      
	private String mbrGrCd;        
	private String mbrStartDt;     
	private String mbrEndDt;       
	private String mbrApprDt;      
	private String mbrId;          
	private String mbrPw;          
	private String emailCertYn;    
	private String mbrNm;          
	private String devTelNo;       
	private String devHpNo;        
	private String birthDt;        
	private String naCd;           
	private String sex;            
	private String city;           
	private String zipCd;          
	private String homeAddrDtl;    
	private String emailAddr;      
	private String newsYn;         
	private String webSiteUrl;     
	private String opNm;           
	private String opEmailAddr;    
	private String opTelNo;        
	private String opHpNo;         
	private String acctNo;         
	private String backNm;         
	private String backCd;         
	private String backBranchNm;   
	private String backBranchCd;   
	private String currencyUnit;   
	private String backGlCd;       
	private String acctNm;         
	private String acctCertYn;     
	private String compNm;         
	private String psRegNo;        
	private String bizCatCd;       
	private String regDttm;     
	private String regDisposalDttm;
	private String updId;          
	private String updDttm;
	private String appReqDt;
	
	private String devEndReqDt;
	
	private String payTransDt;
	private String devEndReasonDscr;
	
	private String rnum;
	
	private PagenateInfoModel	page		= new PagenateInfoModel(10);
    private Long				totalCount;
    
    @Override
	public PagenateInfoModel getPage() {
		return this.page;
	}

	@Override
	public Long getTotalCount() {
		return this.totalCount;
	}

	@Override
	public void setTotalCount(Long totalCount) {
		this.totalCount	= totalCount;
	}

	public String getSearchType1() {
		return searchType1;
	}

	public void setSearchType1(String searchType1) {
		this.searchType1 = searchType1;
	}

	public String getSearchType2() {
		return searchType2;
	}

	public void setSearchType2(String searchType2) {
		this.searchType2 = searchType2;
	}

	public String getSearchType3() {
		return searchType3;
	}

	public void setSearchType3(String searchType3) {
		this.searchType3 = searchType3;
	}

	public String getSearchType4() {
		return searchType4;
	}

	public void setSearchType4(String searchType4) {
		this.searchType4 = searchType4;
	}

	public String getMbrNo() {
		return mbrNo;
	}

	public void setMbrNo(String mbrNo) {
		this.mbrNo = mbrNo;
	}

	public String getMbrClsCd() {
		return mbrClsCd;
	}

	public void setMbrClsCd(String mbrClsCd) {
		this.mbrClsCd = mbrClsCd;
	}

	public String getMbrCatCd() {
		return mbrCatCd;
	}

	public void setMbrCatCd(String mbrCatCd) {
		this.mbrCatCd = mbrCatCd;
	}

	public String getDevMbrStatCd() {
		return devMbrStatCd;
	}

	public void setDevMbrStatCd(String devMbrStatCd) {
		this.devMbrStatCd = devMbrStatCd;
	}

	public String getMbrStatRegDt() {
		return mbrStatRegDt;
	}

	public void setMbrStatRegDt(String mbrStatRegDt) {
		this.mbrStatRegDt = mbrStatRegDt;
	}

	public String getMbrStatCd() {
		return mbrStatCd;
	}

	public void setMbrStatCd(String mbrStatCd) {
		this.mbrStatCd = mbrStatCd;
	}

	public String getMbrGrCd() {
		return mbrGrCd;
	}

	public void setMbrGrCd(String mbrGrCd) {
		this.mbrGrCd = mbrGrCd;
	}

	public String getMbrStartDt() {
		return mbrStartDt;
	}

	public void setMbrStartDt(String mbrStartDt) {
		this.mbrStartDt = mbrStartDt;
	}

	public String getMbrEndDt() {
		return mbrEndDt;
	}

	public void setMbrEndDt(String mbrEndDt) {
		this.mbrEndDt = mbrEndDt;
	}

	public String getMbrApprDt() {
		return mbrApprDt;
	}

	public void setMbrApprDt(String mbrApprDt) {
		this.mbrApprDt = mbrApprDt;
	}

	public String getMbrId() {
		return mbrId;
	}

	public void setMbrId(String mbrId) {
		this.mbrId = mbrId;
	}

	public String getMbrPw() {
		return mbrPw;
	}

	public void setMbrPw(String mbrPw) {
		this.mbrPw = mbrPw;
	}

	public String getEmailCertYn() {
		return emailCertYn;
	}

	public void setEmailCertYn(String emailCertYn) {
		this.emailCertYn = emailCertYn;
	}

	public String getMbrNm() {
		return mbrNm;
	}

	public void setMbrNm(String mbrNm) {
		this.mbrNm = mbrNm;
	}

	public String getDevTelNo() {
		return devTelNo;
	}

	public void setDevTelNo(String devTelNo) {
		this.devTelNo = devTelNo;
	}

	public String getDevHpNo() {
		return devHpNo;
	}

	public void setDevHpNo(String devHpNo) {
		this.devHpNo = devHpNo;
	}

	public String getBirthDt() {
		return birthDt;
	}

	public void setBirthDt(String birthDt) {
		this.birthDt = birthDt;
	}

	public String getNaCd() {
		return naCd;
	}

	public void setNaCd(String naCd) {
		this.naCd = naCd;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCd() {
		return zipCd;
	}

	public void setZipCd(String zipCd) {
		this.zipCd = zipCd;
	}

	public String getHomeAddrDtl() {
		return homeAddrDtl;
	}

	public void setHomeAddrDtl(String homeAddrDtl) {
		this.homeAddrDtl = homeAddrDtl;
	}

	public String getEmailAddr() {
		return emailAddr;
	}

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	public String getNewsYn() {
		return newsYn;
	}

	public void setNewsYn(String newsYn) {
		this.newsYn = newsYn;
	}

	public String getWebSiteUrl() {
		return webSiteUrl;
	}

	public void setWebSiteUrl(String webSiteUrl) {
		this.webSiteUrl = webSiteUrl;
	}

	public String getOpNm() {
		return opNm;
	}

	public void setOpNm(String opNm) {
		this.opNm = opNm;
	}

	public String getOpEmailAddr() {
		return opEmailAddr;
	}

	public void setOpEmailAddr(String opEmailAddr) {
		this.opEmailAddr = opEmailAddr;
	}

	public String getOpTelNo() {
		return opTelNo;
	}

	public void setOpTelNo(String opTelNo) {
		this.opTelNo = opTelNo;
	}

	public String getOpHpNo() {
		return opHpNo;
	}

	public void setOpHpNo(String opHpNo) {
		this.opHpNo = opHpNo;
	}

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public String getBackNm() {
		return backNm;
	}

	public void setBackNm(String backNm) {
		this.backNm = backNm;
	}

	public String getBackCd() {
		return backCd;
	}

	public void setBackCd(String backCd) {
		this.backCd = backCd;
	}

	public String getBackBranchNm() {
		return backBranchNm;
	}

	public void setBackBranchNm(String backBranchNm) {
		this.backBranchNm = backBranchNm;
	}

	public String getBackBranchCd() {
		return backBranchCd;
	}

	public void setBackBranchCd(String backBranchCd) {
		this.backBranchCd = backBranchCd;
	}

	public String getCurrencyUnit() {
		return currencyUnit;
	}

	public void setCurrencyUnit(String currencyUnit) {
		this.currencyUnit = currencyUnit;
	}

	public String getBackGlCd() {
		return backGlCd;
	}

	public void setBackGlCd(String backGlCd) {
		this.backGlCd = backGlCd;
	}

	public String getAcctNm() {
		return acctNm;
	}

	public void setAcctNm(String acctNm) {
		this.acctNm = acctNm;
	}

	public String getAcctCertYn() {
		return acctCertYn;
	}

	public void setAcctCertYn(String acctCertYn) {
		this.acctCertYn = acctCertYn;
	}

	public String getCompNm() {
		return compNm;
	}

	public void setCompNm(String compNm) {
		this.compNm = compNm;
	}

	public String getPsRegNo() {
		return psRegNo;
	}

	public void setPsRegNo(String psRegNo) {
		this.psRegNo = psRegNo;
	}

	public String getBizCatCd() {
		return bizCatCd;
	}

	public void setBizCatCd(String bizCatCd) {
		this.bizCatCd = bizCatCd;
	}

	public String getRegDttm() {
		return regDttm;
	}

	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}

	public String getRegDisposalDttm() {
		return regDisposalDttm;
	}

	public void setRegDisposalDttm(String regDisposalDttm) {
		this.regDisposalDttm = regDisposalDttm;
	}

	public String getUpdId() {
		return updId;
	}

	public void setUpdId(String updId) {
		this.updId = updId;
	}

	public String getUpdDttm() {
		return updDttm;
	}

	public void setUpdDttm(String updDttm) {
		this.updDttm = updDttm;
	}

	public String getDevEndReqDt() {
		return devEndReqDt;
	}

	public void setDevEndReqDt(String devEndReqDt) {
		this.devEndReqDt = devEndReqDt;
	}

	public String getPayTransDt() {
		return payTransDt;
	}

	public void setPayTransDt(String payTransDt) {
		this.payTransDt = payTransDt;
	}

	public String getDevEndReasonDscr() {
		return devEndReasonDscr;
	}

	public void setDevEndReasonDscr(String devEndReasonDscr) {
		this.devEndReasonDscr = devEndReasonDscr;
	}

	public String getAppReqDt() {
		return appReqDt;
	}

	public void setAppReqDt(String appReqDt) {
		this.appReqDt = appReqDt;
	}

	public String getRnum() {
		return rnum;
	}

	public void setRnum(String rnum) {
		this.rnum = rnum;
	}

	@Override
    public String toString() { 
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); 
    } 

}
