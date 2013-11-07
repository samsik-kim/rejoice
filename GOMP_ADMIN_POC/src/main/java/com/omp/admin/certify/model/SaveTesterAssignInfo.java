package com.omp.admin.certify.model;

import java.io.Serializable;

/**
 * <pre>
 * Tester Assign Info
 * </pre>
 * 
 * @author shsin
 *
 */
@SuppressWarnings("serial")
public class SaveTesterAssignInfo implements Serializable{

	/**
	 * Application CID
	 */		
	private String cid;
	
	/**
	 * Certification Requested Version
	 */		
	private String verifyReqVer;
	
	/**
	 * Tester Id
	 */
	private String testerId;
	
	/**
	 * Certification expected end date
	 */	
	private String ctEndExDt;	
	
	/**
	 * Test Memo
	 */
	private String testMemo;	
	
	/**
	 * Login ID
	 */
	private String loginId;	
	
	/**
	 * Phone List
	 */
	private CertifySupportPhone[] phoneInfo;
	
	private String[] ctCtgSeq;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
     * Prepare dtos.
     * 
     * @param length the length
     */
    private void prepareDtos(int length){
        if(this.phoneInfo == null){
            this.phoneInfo = new CertifySupportPhone[length];
            for(int i=0; i<length; i++){
                this.phoneInfo[i] = new CertifySupportPhone();
            }
        }
    }	
    
    
    /**
	 * <pre>
	 * support Phone Seq
	 * </pre>
	 *
	 * @param phoneModelCd String[]
	 */
	public void setScid(String[] params) {
		this.prepareDtos(params.length);
		for(int i=0; i<params.length; i++){
            this.phoneInfo[i].setScid(params[i]);
        }
	}       
    
    /**
	 * <pre>
	 * support Phone Seq
	 * </pre>
	 *
	 * @param phoneModelCd String[]
	 */
	public void setSprtPhoneSeq(String[] params) {
		this.prepareDtos(params.length);
		for(int i=0; i<params.length; i++){
            this.phoneInfo[i].setSprtPhoneSeq(params[i]);
        }
	}    
	
    /**
	 * <pre>
	 * Phone Model Cd
	 * </pre>
	 *
	 * @param phoneModelCd String[]
	 */
	public void setTargetYn(String[] params) {
		this.prepareDtos(params.length);
		for(int i=0; i<params.length; i++){
			
			if(params[i] == null || params[i].length() <= 0){
				this.phoneInfo[i].setTargetYn("N");
			}else{
				this.phoneInfo[i].setTargetYn(params[i]);
			}
            
        }
	}
	
    /**
     * @return the ctEndExDt
     */
	public String getCtEndExDt() {
		
		String str = "";
		
		try{
			str = ctEndExDt.replaceAll("-", "");
		}catch(Exception ex){
			// Skip
		}
		
		return str;
	}	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
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
     * @return the loginId
     */	
	public String getLoginId() {
		return loginId;
	}
    /**
     * @param loginId the loginId to set
     */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
    /**
     * @return the phoneInfo
     */		
	public CertifySupportPhone[] getPhoneInfo() {
		return phoneInfo;
	}
    /**
     * @param phoneInfo the phoneInfo to set
     */	
	public void setPhoneInfo(CertifySupportPhone[] phoneInfo) {
		this.phoneInfo = phoneInfo;
	}

	public String[] getCtCtgSeq() {
		return ctCtgSeq;
	}

	public void setCtCtgSeq(String[] ctCtgSeq) {
		this.ctCtgSeq = ctCtgSeq;
	}
}
