package com.nmimo.data.user.info;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserBasInfo implements Serializable{
	
	/** 페이징 시작 번호 */
	private int startNum;
	/** 페이징 끝 번호 */
	private int endNum;
	/** */
	private int pageGroupSize = 10;
	/** 총글수 */
	private int totalCount;
	/** 검색 값 */
	private String searchStr;
	/** 검색 구분 */
	private String searchType;
	
	/** 사용자아이디 */
	private String userId;
	/** 사용자비밀번호 */
	private String userPwd;
	/** 사용자명 */
	private String userNm;
	/** 사용자해당부서명 */
	private String userRlvnDeptNm;
	/** 기본승인자 아이디 */
	private String basApvrId;
	/** 기본승인자명 */
	private String basApvrNm;
	/** 일반전화번호 */
	private String genlTelNo;
	/** 이동전화번호 */
	private String mphonNo;
	/** 이메일주소 */
	private String emailAdr;
	/** IP주소 */
	private String ipadr;
	/** 생성일시 */
	private String cretDt;
	/** 승인일시 */
	private String apvDt;
	/** 수정일시 */
	private String amdDt;
	/** 사용가능시작일시 */
	private String usePosblStDt;
	/** 사용가능종료일시 */
	private String usePosblFnsDt;
	/** 사용자권한값 */
	private String userAutVal;
	/** 사용자상태값 */
	private String userSttusVal;
	/** 비밀번호변경일시 */
	private String pwdChgDt;
	/** 시험전송1번호 */
	private String tstTrm1No;
	/** 시험전송2번호 */
	private String tstTrm2No;
	/** 시험전송3번호 */
	private String tstTrm3No;
	/** 시험전송4번호 */
	private String tstTrm4No;
	/** 비밀번호변경여부 */
	private String pwdChgYn;
	/** 비밀번호AES 암호화용키값 */
	private String pwdAesEcodUsgKeyVal;

	/** Login 처리 및 CRM연동 */
	/** 권한 */
	private String authority;
	/** 권한명 */
	private String authorityName;
	/** CRM연동 파라미터 */
	private String jobId;	
	
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the userPwd
	 */
	public String getUserPwd() {
		return userPwd;
	}
	/**
	 * @param userPwd the userPwd to set
	 */
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	/**
	 * @return the userNm
	 */
	public String getUserNm() {
		return userNm;
	}
	/**
	 * @param userNm the userNm to set
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	/**
	 * @return the userRlvnDeptNm
	 */
	public String getUserRlvnDeptNm() {
		return userRlvnDeptNm;
	}
	/**
	 * @param userRlvnDeptNm the userRlvnDeptNm to set
	 */
	public void setUserRlvnDeptNm(String userRlvnDeptNm) {
		this.userRlvnDeptNm = userRlvnDeptNm;
	}
	/**
	 * @return the basApvrId
	 */
	public String getBasApvrId() {
		return basApvrId;
	}
	/**
	 * @param basApvrId the basApvrId to set
	 */
	public void setBasApvrId(String basApvrId) {
		this.basApvrId = basApvrId;
	}
	/**
	 * @return the basApvrNm
	 */
	public String getBasApvrNm() {
		return basApvrNm;
	}
	/**
	 * @param basApvrNm the basApvrNm to set
	 */
	public void setBasApvrNm(String basApvrNm) {
		this.basApvrNm = basApvrNm;
	}
	/**
	 * @return the genlTelNo
	 */
	public String getGenlTelNo() {
		return genlTelNo;
	}
	/**
	 * @param genlTelNo the genlTelNo to set
	 */
	public void setGenlTelNo(String genlTelNo) {
		this.genlTelNo = genlTelNo;
	}
	/**
	 * @return the mphonNo
	 */
	public String getMphonNo() {
		return mphonNo;
	}
	/**
	 * @param mphonNo the mphonNo to set
	 */
	public void setMphonNo(String mphonNo) {
		this.mphonNo = mphonNo;
	}
	/**
	 * @return the emailAdr
	 */
	public String getEmailAdr() {
		return emailAdr;
	}
	/**
	 * @param emailAdr the emailAdr to set
	 */
	public void setEmailAdr(String emailAdr) {
		this.emailAdr = emailAdr;
	}
	/**
	 * @return the ipadr
	 */
	public String getIpadr() {
		return ipadr;
	}
	/**
	 * @param ipadr the ipadr to set
	 */
	public void setIpadr(String ipadr) {
		this.ipadr = ipadr;
	}
	/**
	 * @return the cretDt
	 */
	public String getCretDt() {
		return cretDt;
	}
	/**
	 * @param cretDt the cretDt to set
	 */
	public void setCretDt(String cretDt) {
		this.cretDt = cretDt;
	}
	/**
	 * @return the apvDt
	 */
	public String getApvDt() {
		return apvDt;
	}
	/**
	 * @param apvDt the apvDt to set
	 */
	public void setApvDt(String apvDt) {
		this.apvDt = apvDt;
	}
	/**
	 * @return the amdDt
	 */
	public String getAmdDt() {
		return amdDt;
	}
	/**
	 * @param amdDt the amdDt to set
	 */
	public void setAmdDt(String amdDt) {
		this.amdDt = amdDt;
	}
	/**
	 * @return the usePosblStDt
	 */
	public String getUsePosblStDt() {
		return usePosblStDt;
	}
	/**
	 * @param usePosblStDt the usePosblStDt to set
	 */
	public void setUsePosblStDt(String usePosblStDt) {
		this.usePosblStDt = usePosblStDt;
	}
	/**
	 * @return the usePosblFnsDt
	 */
	public String getUsePosblFnsDt() {
		return usePosblFnsDt;
	}
	/**
	 * @param usePosblFnsDt the usePosblFnsDt to set
	 */
	public void setUsePosblFnsDt(String usePosblFnsDt) {
		this.usePosblFnsDt = usePosblFnsDt;
	}
	/**
	 * @return the userAutVal
	 */
	public String getUserAutVal() {
		return userAutVal;
	}
	/**
	 * @param userAutVal the userAutVal to set
	 */
	public void setUserAutVal(String userAutVal) {
		this.userAutVal = userAutVal;
	}
	/**
	 * @return the userSttusVal
	 */
	public String getUserSttusVal() {
		return userSttusVal;
	}
	/**
	 * @param userSttusVal the userSttusVal to set
	 */
	public void setUserSttusVal(String userSttusVal) {
		this.userSttusVal = userSttusVal;
	}
	/**
	 * @return the pwdChgDt
	 */
	public String getPwdChgDt() {
		return pwdChgDt;
	}
	/**
	 * @param pwdChgDt the pwdChgDt to set
	 */
	public void setPwdChgDt(String pwdChgDt) {
		this.pwdChgDt = pwdChgDt;
	}
	/**
	 * @return the tstTrm1No
	 */
	public String getTstTrm1No() {
		return tstTrm1No;
	}
	/**
	 * @param tstTrm1No the tstTrm1No to set
	 */
	public void setTstTrm1No(String tstTrm1No) {
		this.tstTrm1No = tstTrm1No;
	}
	/**
	 * @return the tstTrm2No
	 */
	public String getTstTrm2No() {
		return tstTrm2No;
	}
	/**
	 * @param tstTrm2No the tstTrm2No to set
	 */
	public void setTstTrm2No(String tstTrm2No) {
		this.tstTrm2No = tstTrm2No;
	}
	/**
	 * @return the tstTrm3No
	 */
	public String getTstTrm3No() {
		return tstTrm3No;
	}
	/**
	 * @param tstTrm3No the tstTrm3No to set
	 */
	public void setTstTrm3No(String tstTrm3No) {
		this.tstTrm3No = tstTrm3No;
	}
	/**
	 * @return the tstTrm4No
	 */
	public String getTstTrm4No() {
		return tstTrm4No;
	}
	/**
	 * @param tstTrm4No the tstTrm4No to set
	 */
	public void setTstTrm4No(String tstTrm4No) {
		this.tstTrm4No = tstTrm4No;
	}
	/**
	 * @return the pwdChgYn
	 */
	public String getPwdChgYn() {
		return pwdChgYn;
	}
	/**
	 * @param pwdChgYn the pwdChgYn to set
	 */
	public void setPwdChgYn(String pwdChgYn) {
		this.pwdChgYn = pwdChgYn;
	}
	/**
	 * @return the pwdAesEcodUsgKeyVal
	 */
	public String getPwdAesEcodUsgKeyVal() {
		return pwdAesEcodUsgKeyVal;
	}
	/**
	 * @param pwdAesEcodUsgKeyVal the pwdAesEcodUsgKeyVal to set
	 */
	public void setPwdAesEcodUsgKeyVal(String pwdAesEcodUsgKeyVal) {
		this.pwdAesEcodUsgKeyVal = pwdAesEcodUsgKeyVal;
	}
	/**
	 * @return the startNum
	 */
	public int getStartNum() {
		return startNum;
	}
	/**
	 * @param startNum the startNum to set
	 */
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	/**
	 * @return the endNum
	 */
	public int getEndNum() {
		return endNum;
	}
	/**
	 * @param endNum the endNum to set
	 */
	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}
	/**
	 * @return the pageGroupSize
	 */
	public int getPageGroupSize() {
		return pageGroupSize;
	}
	/**
	 * @param pageGroupSize the pageGroupSize to set
	 */
	public void setPageGroupSize(int pageGroupSize) {
		this.pageGroupSize = pageGroupSize;
	}
	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * @return the searchStr
	 */
	public String getSearchStr() {
		return searchStr;
	}
	/**
	 * @param searchStr the searchStr to set
	 */
	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}
	/**
	 * @return the searchType
	 */
	public String getSearchType() {
		return searchType;
	}
	/**
	 * @param searchType the searchType to set
	 */
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	/**
	 * @return the authority
	 */
	public String getAuthority() {
		return authority;
	}
	/**
	 * @param authority the authority to set
	 */
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	/**
	 * @return the authorityName
	 */
	public String getAuthorityName() {
		return authorityName;
	}
	/**
	 * @param authorityName the authorityName to set
	 */
	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}
	/**
	 * @return the jobId
	 */
	public String getJobId() {
		return jobId;
	}
	/**
	 * @param jobId the jobId to set
	 */
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	
	
}