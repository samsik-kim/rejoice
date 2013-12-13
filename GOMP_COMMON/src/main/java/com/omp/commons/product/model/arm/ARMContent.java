package com.omp.commons.product.model.arm;

/**
 * ARM 연동용  상품정보 모델
 * @author pat
 *
 */
public class ARMContent {

	private String cid;
	private String appid;
	private String pid;
	
	private String userid;
	private String appname;
	private String appversion;
	private String constraint;
	private String status;
	
	private String drmYn;
	private String drmSetOpt;
	private String drmSetVal;


	/**
	 * @return the cid
	 */
	public String getCid() {
		return cid == null ? "" : cid;
	}

	/**
	 * @param cid the cid to set
	 */
	public void setCid(String cid) {
		this.cid = cid;
	}

	/**
	 * @return the appid
	 */
	public String getAppid() {
		return appid == null ? "" : appid;
	}

	/**
	 * @param appid the appid to set
	 */
	public void setAppid(String appid) {
		this.appid = appid;
	}

	/**
	 * @return the pid
	 */
	public String getPid() {
		return pid == null ? "" : pid;
	}

	/**
	 * @param pid the pid to set
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}

	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid == null ? "" : userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * @return the appname
	 */
	public String getAppname() {
		return appname == null ? "" : appname;
	}

	/**
	 * @param appname the appname to set
	 */
	public void setAppname(String appname) {
		this.appname = appname;
	}

	/**
	 * @return the appversion
	 */
	public String getAppversion() {
		return appversion == null ? "" : appversion;
	}

	/**
	 * @param appversion the appversion to set
	 */
	public void setAppversion(String appversion) {
		this.appversion = appversion;
	}

	/**
	 * @return the constraint
	 */
	public String getConstraint() {
		return constraint == null ? "" : constraint;
	}

	/**
	 * @param constraint the constraint to set
	 */
	public void setConstraint(String constraint) {
		this.constraint = constraint;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status == null ? "" : status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the drmYn
	 */
	public String getDrmYn() {
		return drmYn == null ? "" : drmYn;
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
		return drmSetOpt == null ? "" : drmSetOpt;
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
		return drmSetVal == null ? "" : drmSetVal;
	}

	/**
	 * @param drmSetVal the drmSetVal to set
	 */
	public void setDrmSetVal(String drmSetVal) {
		this.drmSetVal = drmSetVal;
	}
	
	
}
