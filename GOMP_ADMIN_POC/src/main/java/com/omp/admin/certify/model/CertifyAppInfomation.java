package com.omp.admin.certify.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * <pre>
 * Certify Application Infomation
 * </pre>
 * 
 * 
 * @author shsin
 *
 */
@SuppressWarnings("serial")
public class CertifyAppInfomation implements Serializable{
	
	/**
	 * Application CID
	 */	
	private String cid;
	
	/**
	 * Application AID
	 */	
	private String aid;
	
	/**
	 * Certification Requested Version
	 */	
	private String verifyReqVer;
	
	/**
	 * Certification progress status
	 */	
	private String verifyPrgrYn;
	
	/**
	 * Certification Agreement progress status
	 */
	private String agrmntStat;
	
	/**
	 * Certification Tester progress status
	 */
	private String testerCtStat;
	
	/**
	 * Certification ended date
	 */	
	private String ctEndDt;
	
	/**
	 * Certification test ended date
	 */		
	private String testEndDt;
	
	/**
	 * OS Platform
	 */
	private String vmType;
	
	/**
	 * Application Product Name
	 */	
	private String prodNm;
	
	/**
	 * deliberate grade
	 */
	private String gameDelibGrd;
	
	/**
	 * Certification Scenario file path
	 */
	private String verifyScnrFile;
	
	/**
	 * Certification Scenario file name
	 */
	private String verifyScnrFileNm;
	
	/**
	 * DRM apply Y/N Flag
	 */
	private String drmYn;
	
	/**
	 * DRM Set Option
	 */
	private String drmSetOpt;
	
	/**
	 * DRM Set Value
	 */
	private String drmSetVal;
	
	/**
	 * Verify Comment Code
	 */
	private String verifyCommentCd;
	
	/**
	 * Verify Etc Comment
	 */
	private String verifyEtcCmt;
	
	/**
	 * Tester Id
	 */
	private String testerId;
	
	/**
	 * Tester Name
	 */	
	private String testerNm;
	
	/**
	 * Certification assigned date
	 */	
	private String ctAssignDt;
	
	/**
	 * Certification expected end date
	 */	
	private String ctEndExDt;
	
	/**
	 * Test Memo
	 */
	private String testMemo;
	
	/**
	 * Test Reject Comment
	 */
	private String testRejectCmt;
	
	/**
	 * Direct Pass Y/N
	 */
	private String directPassYn;
	
	/**
	 * Certification inserted By
	 */
	private String insBy;
	
	/**
	 * Certification inserted date
	 */	
	private String insDttm;
	
	/**
	 * Certification updated By
	 */
	private String updBy;
	
	/**
	 * Certification updated date 
	 */
	private String updDttm;
	
	/**
	 * Re-Certification Request Y/N
	 */
	private String reCertifyReqYn;
	
	/**
	 * Product Certification Level
	 */
	private String signOption;
	
	/**
	 * Developer Grade Code
	 */
	private String mbrGrCd;
	
	/**
	 * Product Developer Id
	 */
	private String mbrId;
	
	
	/**
	 * Test Case list
	 */
	private List<CerfifyTargetTestCase> testCaseList;
	
	
	//////////////////////////////////////////////////////////////////////////////////
	
	
    /**
     * @return the verifyEtcCmtByBr
     */
	public String getVerifyEtcCmtByBr() {
		
		String str = "";
		try{
			str = verifyEtcCmt.replaceAll("\n\r", "<BR>");
			str = verifyEtcCmt.replaceAll("\n", "<BR>");
		}catch(Exception ex){
			// Skip
		}
		
		return str;
	}	
	
    /**
     * @return the testMemoByBr
     */
	public String getTestMemoByBr() {
		
		String str = "";
		try{
			str = testMemo.replaceAll("\n\r", "<BR>");
			str = testMemo.replaceAll("\n", "<BR>");
		}catch(Exception ex){
			// Skip
		}
		
		return str;
	}	
	
    /**
     * @return the testRejectCmtByBr
     */
	public String getTestRejectCmtByBr() {
		
		String str = "";
		try{
			str = testRejectCmt.replaceAll("\n\r", "<BR>");
			str = testRejectCmt.replaceAll("\n", "<BR>");
		}catch(Exception ex){
			// Skip
		}
		
		return str;
	}		
	
	/**
	 * toString
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
	}	
	
	//////////////////////////////////////////////////////////////////////////////////	

    /**
     * @return the cid
     */
	public String getCid() {
		return cid;
	}
    /**
     * @param cid the cid to set
     */
	public void setCid(String cid) {
		this.cid = cid;
	}
    /**
     * @return the cid
     */
	public String getAid() {
		return aid;
	}
    /**
     * @param aid the aid to set
     */
	public void setAid(String aid) {
		this.aid = aid;
	}
    /**
     * @return the verifyReqVer
     */
	public String getVerifyReqVer() {
		return verifyReqVer;
	}
    /**
     * @param verifyReqVer the verifyReqVer to set
     */
	public void setVerifyReqVer(String verifyReqVer) {
		this.verifyReqVer = verifyReqVer;
	}
    /**
     * @return the verifyPrgrYn
     */
	public String getVerifyPrgrYn() {
		return verifyPrgrYn;
	}
    /**
     * @param verifyPrgrYn the verifyPrgrYn to set
     */
	public void setVerifyPrgrYn(String verifyPrgrYn) {
		this.verifyPrgrYn = verifyPrgrYn;
	}
    /**
     * @return the agrmntStat
     */
	public String getAgrmntStat() {
		return agrmntStat;
	}
    /**
     * @param agrmntStat the agrmntStat to set
     */
	public void setAgrmntStat(String agrmntStat) {
		this.agrmntStat = agrmntStat;
	}
    /**
     * @return the testerCtStat
     */
	public String getTesterCtStat() {
		return testerCtStat;
	}
    /**
     * @param testerCtStat the testerCtStat to set
     */
	public void setTesterCtStat(String testerCtStat) {
		this.testerCtStat = testerCtStat;
	}
    /**
     * @return the ctEndDt
     */
	public String getCtEndDt() {
		return ctEndDt;
	}
    /**
     * @param ctEndDt the ctEndDt to set
     */
	public void setCtEndDt(String ctEndDt) {
		this.ctEndDt = ctEndDt;
	}
    /**
     * @return the testEndDt
     */
	public String getTestEndDt() {
		return testEndDt;
	}
    /**
     * @param testEndDt the testEndDt to set
     */
	public void setTestEndDt(String testEndDt) {
		this.testEndDt = testEndDt;
	}
    /**
     * @return the vmType
     */
	public String getVmType() {
		return vmType;
	}
    /**
     * @param vmType the vmType to set
     */
	public void setVmType(String vmType) {
		this.vmType = vmType;
	}
    /**
     * @return the prodNm
     */
	public String getProdNm() {
		return prodNm;
	}
    /**
     * @param prodNm the prodNm to set
     */
	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
	}
    /**
     * @return the gameDelibGrd
     */
	public String getGameDelibGrd() {
		return gameDelibGrd;
	}
    /**
     * @param gameDelibGrd the gameDelibGrd to set
     */
	public void setGameDelibGrd(String gameDelibGrd) {
		this.gameDelibGrd = gameDelibGrd;
	}
    /**
     * @return the verifyScnrFile
     */
	public String getVerifyScnrFile() {
		return verifyScnrFile;
	}
    /**
     * @param verifyScnrFile the verifyScnrFile to set
     */
	public void setVerifyScnrFile(String verifyScnrFile) {
		this.verifyScnrFile = verifyScnrFile;
	}
    /**
     * @return the verifyScnrFileNm
     */
	public String getVerifyScnrFileNm() {
		return verifyScnrFileNm;
	}
    /**
     * @param verifyScnrFileNm the verifyScnrFileNm to set
     */
	public void setVerifyScnrFileNm(String verifyScnrFileNm) {
		this.verifyScnrFileNm = verifyScnrFileNm;
	}
    /**
     * @return the drmYn
     */
	public String getDrmYn() {
		return drmYn;
	}
    /**
     * @param drmYn the drmYn to set
     */
	public void setDrmYn(String drmYn) {
		this.drmYn = drmYn;
	}
    /**
     * @return the drmSetOpt
     */
	public String getDrmSetOpt() {
		return drmSetOpt;
	}
    /**
     * @param drmSetOpt the drmSetOpt to set
     */
	public void setDrmSetOpt(String drmSetOpt) {
		this.drmSetOpt = drmSetOpt;
	}
    /**
     * @return the drmSetVal
     */
	public String getDrmSetVal() {
		return drmSetVal;
	}
    /**
     * @param drmSetVal the drmSetVal to set
     */
	public void setDrmSetVal(String drmSetVal) {
		this.drmSetVal = drmSetVal;
	}
    /**
     * @return the verifyCommentCd
     */
	public String getVerifyCommentCd() {
		return verifyCommentCd;
	}
    /**
     * @param verifyCommmentCd the verifyCommmentCd to set
     */
	public void setVerifyCommentCd(String verifyCommentCd) {
		this.verifyCommentCd = verifyCommentCd;
	}
    /**
     * @return the verifyEtcCmt
     */
	public String getVerifyEtcCmt() {
		return verifyEtcCmt;
	}
    /**
     * @param verifyEtcCmt the verifyEtcCmt to set
     */
	public void setVerifyEtcCmt(String verifyEtcCmt) {
		this.verifyEtcCmt = verifyEtcCmt;
	}
	
	/**
     * @return the testerId
     */
    public String getTesterId() {
		return testerId;
	}
    /**
     * @param testerId the testerId to set
     */
	public void setTesterId(String testerId) {
		this.testerId = testerId;
	}

	/**
     * @return the testerNm
     */
	public String getTesterNm() {
		return testerNm;
	}
    /**
     * @param testerNm the testerNm to set
     */
	public void setTesterNm(String testerNm) {
		this.testerNm = testerNm;
	}
    /**
     * @return the ctAssignDt
     */
	public String getCtAssignDt() {
		return ctAssignDt;
	}
    /**
     * @param ctAssignDt the ctAssignDt to set
     */
	public void setCtAssignDt(String ctAssignDt) {
		this.ctAssignDt = ctAssignDt;
	}
    /**
     * @return the ctEndExDt
     */
	public String getCtEndExDt() {
		return ctEndExDt;
	}
    /**
     * @param ctEndExDt the ctEndExDt to set
     */
	public void setCtEndExDt(String ctEndExDt) {
		this.ctEndExDt = ctEndExDt;
	}
    /**
     * @return the testMemo
     */
	public String getTestMemo() {
		return testMemo;
	}
    /**
     * @param testMemo the testMemo to set
     */
	public void setTestMemo(String testMemo) {
		this.testMemo = testMemo;
	}
    /**
     * @return the testRejectCmt
     */
	public String getTestRejectCmt() {
		return testRejectCmt;
	}
    /**
     * @param testRejectCmt the testRejectCmt to set
     */
	public void setTestRejectCmt(String testRejectCmt) {
		this.testRejectCmt = testRejectCmt;
	}
    /**
     * @return the directPassYn
     */
	public String getDirectPassYn() {
		return directPassYn;
	}
    /**
     * @param directPassYn the directPassYn to set
     */
	public void setDirectPassYn(String directPassYn) {
		this.directPassYn = directPassYn;
	}
    /**
     * @return the insBy
     */
	public String getInsBy() {
		return insBy;
	}
    /**
     * @param insBy the insBy to set
     */
	public void setInsBy(String insBy) {
		this.insBy = insBy;
	}
    /**
     * @return the insDttm
     */
	public String getInsDttm() {
		return insDttm;
	}
    /**
     * @param insDttm the insDttm to set
     */
	public void setInsDttm(String insDttm) {
		this.insDttm = insDttm;
	}
	
	/**
     * @return the updBy
     */		
	public String getUpdBy() {
		return updBy;
	}
    /**
     * @param updBy the updBy to set
     */		
	public void setUpdBy(String updBy) {
		this.updBy = updBy;
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
	
	
	
	public String getReCertifyReqYn() {
		return reCertifyReqYn;
	}

	public void setReCertifyReqYn(String reCertifyReqYn) {
		this.reCertifyReqYn = reCertifyReqYn;
	}

	/**
     * @return the testCaseList
     */	
	public List<CerfifyTargetTestCase> getTestCaseList() {
		return testCaseList;
	}
	
    /**
     * @param testCaseList the testCaseList to set
     */	
	public void setTestCaseList(List<CerfifyTargetTestCase> testCaseList) {
		this.testCaseList = testCaseList;
	}
	/**
     * @return the signOption
     */	
	public String getSignOption() {
		return signOption;
	}
    /**
     * @param signOption the signOption to set
     */
	public void setSignOption(String signOption) {
		this.signOption = signOption;
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
	
	
	

}
