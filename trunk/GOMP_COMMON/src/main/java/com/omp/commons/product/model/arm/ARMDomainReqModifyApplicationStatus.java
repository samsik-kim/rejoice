package com.omp.commons.product.model.arm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * ARM XML 통신용 XML 표현 객체
 * @author pat
 *
 */
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlRootElement(name = "reqModifyApplicationStatus")
public class ARMDomainReqModifyApplicationStatus {

	private String cid;
	private String appid;
	private String pid;
	
	private String userid;
	private String appname;
	private String appversion;
	private String status;
	
	public ARMItem constraint;
	
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
	 * @return the appid
	 */
	public String getAppid() {
		return appid == null? "" : appid;
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
		return pid == null? "" : pid;
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
		return userid;
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
		return appname;
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
		return appversion;
	}

	/**
	 * @param appversion the appversion to set
	 */
	public void setAppversion(String appversion) {
		this.appversion = appversion;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status == null? "" : status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
