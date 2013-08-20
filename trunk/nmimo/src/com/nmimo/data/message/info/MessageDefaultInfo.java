package com.nmimo.data.message.info;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MessageDefaultInfo implements Serializable{

	 private String wName;
	 private String sendInfo;
	 private String etcInfo;
	 private String deptDesc;
	/**
	 * @return the wName
	 */
	public String getwName() {
		return wName;
	}
	/**
	 * @param wName the wName to set
	 */
	public void setwName(String wName) {
		this.wName = wName;
	}
	/**
	 * @return the sendInfo
	 */
	public String getSendInfo() {
		return sendInfo;
	}
	/**
	 * @param sendInfo the sendInfo to set
	 */
	public void setSendInfo(String sendInfo) {
		this.sendInfo = sendInfo;
	}
	/**
	 * @return the etcInfo
	 */
	public String getEtcInfo() {
		return etcInfo;
	}
	/**
	 * @param etcInfo the etcInfo to set
	 */
	public void setEtcInfo(String etcInfo) {
		this.etcInfo = etcInfo;
	}
	/**
	 * @return the deptDesc
	 */
	public String getDeptDesc() {
		return deptDesc;
	}
	/**
	 * @param deptDesc the deptDesc to set
	 */
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
}