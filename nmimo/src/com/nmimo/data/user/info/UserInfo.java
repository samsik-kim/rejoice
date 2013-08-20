package com.nmimo.data.user.info;

import java.io.Serializable;

import com.nmimo.common.util.StringUtils;

/**
 * <pre>
 * User 정보
 * </pre>
 * @file UserInfo.java
 * @since 2013. 5. 30.
 * @author Administrator
 */
@SuppressWarnings("serial")
public class UserInfo implements Serializable{

	private String userId;
	private String userPwd;
	private String userNm;
	private String userRlvnDeptNm;
	private String basApvrId;
	private String authority;
	private String authorityName;
	private String jobId;
	
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return StringUtils.nvlStr(userId);
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
		return StringUtils.nvlStr(userPwd);
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
		return StringUtils.nvlStr(userNm);
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
		return StringUtils.nvlStr(userRlvnDeptNm);
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
		return StringUtils.nvlStr(basApvrId);
	}
	/**
	 * @param basApvrId the basApvrId to set
	 */
	public void setBasApvrId(String basApvrId) {
		this.basApvrId = basApvrId;
	}
	/**
	 * @return the authority
	 */
	public String getAuthority() {
		return StringUtils.nvlStr(authority);
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
		return StringUtils.nvlStr(authorityName);
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