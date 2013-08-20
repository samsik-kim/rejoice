package com.nmimo.common.info;

import java.io.Serializable;

/**
 * <pre>
 * PSSO연동 Return CookieInfo 
 * </pre>
 * @file PSSOCookieInfo.java
 * @since 2013. 5. 14.
 * @author Administrator
 */
@SuppressWarnings("serial")
public class PSSOCookieInfo implements Serializable{
	
	private String PSSO_OTP_YN;
	private String PSSO_keyid;
	private String PSSO_UserName;
	private String PSSO_UserID;
	private String PSSO_enckey;
	
	/**
	 * 
	 */
	public PSSOCookieInfo() {
		super();
	}


	/**
	 * @return the pSSO_OTP_YN
	 */
	public String getPSSO_OTP_YN() {
		return PSSO_OTP_YN;
	}


	/**
	 * @param pSSO_OTP_YN the pSSO_OTP_YN to set
	 */
	public void setPSSO_OTP_YN(String pSSO_OTP_YN) {
		PSSO_OTP_YN = pSSO_OTP_YN;
	}


	/**
	 * @return the pSSO_keyid
	 */
	public String getPSSO_keyid() {
		return PSSO_keyid;
	}


	/**
	 * @param pSSO_keyid the pSSO_keyid to set
	 */
	public void setPSSO_keyid(String pSSO_keyid) {
		PSSO_keyid = pSSO_keyid;
	}


	/**
	 * @return the pSSO_UserName
	 */
	public String getPSSO_UserName() {
		return PSSO_UserName;
	}


	/**
	 * @param pSSO_UserName the pSSO_UserName to set
	 */
	public void setPSSO_UserName(String pSSO_UserName) {
		PSSO_UserName = pSSO_UserName;
	}


	/**
	 * @return the pSSO_UserID
	 */
	public String getPSSO_UserID() {
		return PSSO_UserID;
	}


	/**
	 * @param pSSO_UserID the pSSO_UserID to set
	 */
	public void setPSSO_UserID(String pSSO_UserID) {
		PSSO_UserID = pSSO_UserID;
	}


	/**
	 * @return the pSSO_enckey
	 */
	public String getPSSO_enckey() {
		return PSSO_enckey;
	}


	/**
	 * @param pSSO_enckey the pSSO_enckey to set
	 */
	public void setPSSO_enckey(String pSSO_enckey) {
		PSSO_enckey = pSSO_enckey;
	}
	
	
}
