/*
 * COPYRIGHT(c) SK telecom 2010
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 * Choi 	  2010.6.18.    Description
 *
 */
package com.omp.admin.common.model;

/**
 * 파일다운로드 Model
 * 
 * @author Choi
 * @version 0.1
 */
public class FileSupport {
	
	// 파일정보
	private String fid;
	private String fnm;
	private String ofnm;
	private String fsz;
	private String furl;
	private String istemp;
	private String regdate;
	private String ftype;
	
	// 파일그룹정보
	private String gid;
	private String seq;
	private String useYn;
	
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getFnm() {
		return fnm;
	}
	public void setFnm(String fnm) {
		this.fnm = fnm;
	}
	public String getOfnm() {
		return ofnm;
	}
	public void setOfnm(String ofnm) {
		this.ofnm = ofnm;
	}
	public String getFsz() {
		return fsz;
	}
	public void setFsz(String fsz) {
		this.fsz = fsz;
	}
	public String getFurl() {
		return furl;
	}
	public void setFurl(String furl) {
		this.furl = furl;
	}
	public String getIstemp() {
		return istemp;
	}
	public void setIstemp(String istemp) {
		this.istemp = istemp;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getFtype() {
		return ftype;
	}
	public void setFtype(String ftype) {
		this.ftype = ftype;
	}
}