package com.stockinvest.data.stockinvest.info;

import java.io.Serializable;
import com.stockinvest.common.info.CommonInfo;

@SuppressWarnings("serial")
public class MemberInfo extends CommonInfo implements Serializable{
	private String seqNo; 	//순번
	private String adminId; //관리자ID
	private String passWd;	//암호
	/**
	 * @return the seqNo
	 */
	public String getSeqNo() {
		return seqNo;
	}
	/**
	 * @param seqNo the seqNo to set
	 */
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	/**
	 * @return the adminId
	 */
	public String getAdminId() {
		return adminId;
	}
	/**
	 * @param adminId the adminId to set
	 */
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	/**
	 * @return the passWd
	 */
	public String getPassWd() {
		return passWd;
	}
	/**
	 * @param passWd the passWd to set
	 */
	public void setPassWd(String passWd) {
		this.passWd = passWd;
	}

	
	
}
