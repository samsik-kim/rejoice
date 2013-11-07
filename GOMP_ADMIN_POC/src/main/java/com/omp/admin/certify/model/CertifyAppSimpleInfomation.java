package com.omp.admin.certify.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * <pre>
 * Certify Application Simple Infomation
 * </pre>
 * 
 * 
 * @author shsin
 *
 */
@SuppressWarnings("serial")
public class CertifyAppSimpleInfomation implements Serializable{
	
	/**
	 * Application CID
	 */	
	private String cid;
	
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
	 * Tester Name
	 */	
	private String testerNm;
	

	/**
	 * Test Case list
	 */
	private List<CerfifyTargetTestCase> testCaseList;
	
	
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

}
