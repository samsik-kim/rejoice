/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 *            2009. 4. 29.    Description
 *
 */
package com.omp.admin.adminmgr.auth.model;

import java.util.Date;

import com.omp.commons.model.CommonModel;
import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;

/**
 * AdminMain
 * <p/>
 * AdminMain Model
 * 
 * @author
 * @version 0.1
 */
@SuppressWarnings("serial")
public class AdMgr extends CommonModel implements Pagenateable {

	private String mgrId = null;
	private String pswdNo = null;
	private String mgrNm = null;
	private String compNm = null;
	private String deptNm = null;
	private String jobGrdNm = null;
	private String mnoCd = null;
	private String hp1No = null;
	private String hp2No = null;
	private String hp3No = null;
	private String bizTel1No = null;
	private String bizTel2No = null;
	private String bizTel3No = null;
	private String emailAddrId = null;
	private String emailAddr = null;
	private String etcDscr = null;
	private String regId = null;
	private String regDt = null;
	private String updId = null;
	private String updDt = null;
	private Date lstAccDts = null;
	private String authGbn = null;
	private String authGbnNm = null;

	private PagenateInfoModel page = new PagenateInfoModel(10);
	private Long totalCount;

	public PagenateInfoModel getPage() {
		return this.page;
	}

	public Long getTotalCount() {
		return this.totalCount;
	}

	public Long setTotalCount(Long totalCount) {
		return this.totalCount = totalCount;
	}

	public String getMgrId() {
		return mgrId;
	}

	public void setMgrId(String mgrId) {
		this.mgrId = mgrId;
	}

	public String getPswdNo() {
		return pswdNo;
	}

	public void setPswdNo(String pswdNo) {
		this.pswdNo = pswdNo;
	}

	public String getMgrNm() {
		return mgrNm;
	}

	public void setMgrNm(String mgrNm) {
		this.mgrNm = mgrNm;
	}

	public String getCompNm() {
		return compNm;
	}

	public void setCompNm(String compNm) {
		this.compNm = compNm;
	}

	public String getDeptNm() {
		return deptNm;
	}

	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}

	public String getJobGrdNm() {
		return jobGrdNm;
	}

	public void setJobGrdNm(String jobGrdNm) {
		this.jobGrdNm = jobGrdNm;
	}

	public String getMnoCd() {
		return mnoCd;
	}

	public void setMnoCd(String mnoCd) {
		this.mnoCd = mnoCd;
	}

	public String getHp1No() {
		return hp1No;
	}

	public void setHp1No(String hp1No) {
		this.hp1No = hp1No;
	}

	public String getHp2No() {
		return hp2No;
	}

	public void setHp2No(String hp2No) {
		this.hp2No = hp2No;
	}

	public String getHp3No() {
		return hp3No;
	}

	public void setHp3No(String hp3No) {
		this.hp3No = hp3No;
	}

	public String getBizTel1No() {
		return bizTel1No;
	}

	public void setBizTel1No(String bizTel1No) {
		this.bizTel1No = bizTel1No;
	}

	public String getBizTel2No() {
		return bizTel2No;
	}

	public void setBizTel2No(String bizTel2No) {
		this.bizTel2No = bizTel2No;
	}

	public String getBizTel3No() {
		return bizTel3No;
	}

	public void setBizTel3No(String bizTel3No) {
		this.bizTel3No = bizTel3No;
	}

	public String getEmailAddrId() {
		return emailAddrId;
	}

	public void setEmailAddrId(String emailAddrId) {
		this.emailAddrId = emailAddrId;
	}

	public String getEmailAddr() {
		return emailAddr;
	}

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	public String getEtcDscr() {
		return etcDscr;
	}

	public void setEtcDscr(String etcDscr) {
		this.etcDscr = etcDscr;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getUpdId() {
		return updId;
	}

	public void setUpdId(String updId) {
		this.updId = updId;
	}

	public String getUpdDt() {
		return updDt;
	}

	public void setUpdDt(String updDt) {
		this.updDt = updDt;
	}

	public Date getLstAccDts() {
		return lstAccDts;
	}

	public void setLstAccDts(Date lstAccDts) {
		this.lstAccDts = lstAccDts;
	}

	public String getAuthGbn() {
		return authGbn;
	}

	public void setAuthGbn(String authGbn) {
		this.authGbn = authGbn;
	}

	public String getAuthGbnNm() {
		return authGbnNm;
	}

	public void setAuthGbnNm(String authGbnNm) {
		this.authGbnNm = authGbnNm;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getClass().toString());
		sb.append(" mgrId='").append(mgrId).append("'");
		sb.append(",pswdNo='").append(pswdNo).append("'");
		sb.append(",mgrNm='").append(mgrNm).append("'");
		sb.append(",compNm='").append(compNm).append("'");
		sb.append(",deptNm='").append(deptNm).append("'");
		sb.append(",jobGrdNm='").append(jobGrdNm).append("'");
		sb.append(",mnoCd='").append(mnoCd).append("'");
		sb.append(",hp1No='").append(hp1No).append("'");
		sb.append(",hp2No='").append(hp2No).append("'");
		sb.append(",hp3No='").append(hp3No).append("'");
		sb.append(",bizTel1No='").append(bizTel1No).append("'");
		sb.append(",bizTel2No='").append(bizTel2No).append("'");
		sb.append(",bizTel3No='").append(bizTel3No).append("'");
		sb.append(",emailAddrId='").append(emailAddrId).append("'");
		sb.append(",emailAddr='").append(emailAddr).append("'");
		sb.append(",etcDscr='").append(etcDscr).append("'");
		sb.append(",regId='").append(regId).append("'");
		sb.append(",regDt='").append(regDt).append("'");
		sb.append(",updId='").append(updId).append("'");
		sb.append(",updDt='").append(updDt).append("'");
		sb.append(",lstAccDts='").append(lstAccDts).append("'");
		sb.append(",authGbn='").append(authGbn).append("'");
		sb.append(",authGbnNm='").append(authGbnNm).append("'");
		sb.append("}");
		return sb.toString();
	}

}
