package com.stockinvest.data.board.info;

import java.io.Serializable;

import com.stockinvest.common.info.CommonInfo;

@SuppressWarnings("serial")
public class BoardInfo extends CommonInfo implements Serializable{
	private String seqNo;		//순번
	private String listNum;
	private String ref;
	private String reLevel;
	private String parNo;
	private String name;
	private String codeNum;
	private String codeName;
	private String subject;
	private String content;
	private String file1;
	private String file2;
	private String file3;
	private String passWd;
	private String crtDate;
	private String udtDate;
	private String delYn;
	private String vcounter;
	private String bbsCd;
	private String stDt;
	private String enDt;
	
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
	 * @return the listNum
	 */
	public String getListNum() {
		return listNum;
	}
	/**
	 * @param listNum the listNum to set
	 */
	public void setListNum(String listNum) {
		this.listNum = listNum;
	}
	/**
	 * @return the ref
	 */
	public String getRef() {
		return ref;
	}
	/**
	 * @param ref the ref to set
	 */
	public void setRef(String ref) {
		this.ref = ref;
	}
	/**
	 * @return the reLevel
	 */
	public String getReLevel() {
		return reLevel;
	}
	/**
	 * @param reLevel the reLevel to set
	 */
	public void setReLevel(String reLevel) {
		this.reLevel = reLevel;
	}
	/**
	 * @return the parNo
	 */
	public String getParNo() {
		return parNo;
	}
	/**
	 * @param parNo the parNo to set
	 */
	public void setParNo(String parNo) {
		this.parNo = parNo;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the codeNum
	 */
	public String getCodeNum() {
		return codeNum;
	}
	/**
	 * @param codeNum the codeNum to set
	 */
	public void setCodeNum(String codeNum) {
		this.codeNum = codeNum;
	}
	/**
	 * @return the codeName
	 */
	public String getCodeName() {
		return codeName;
	}
	/**
	 * @param codeName the codeName to set
	 */
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the file1
	 */
	public String getFile1() {
		return file1;
	}
	/**
	 * @param file1 the file1 to set
	 */
	public void setFile1(String file1) {
		this.file1 = file1;
	}
	/**
	 * @return the file2
	 */
	public String getFile2() {
		return file2;
	}
	/**
	 * @param file2 the file2 to set
	 */
	public void setFile2(String file2) {
		this.file2 = file2;
	}
	/**
	 * @return the file3
	 */
	public String getFile3() {
		return file3;
	}
	/**
	 * @param file3 the file3 to set
	 */
	public void setFile3(String file3) {
		this.file3 = file3;
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
	/**
	 * @return the crtDate
	 */
	public String getCrtDate() {
		return crtDate;
	}
	/**
	 * @param crtDate the crtDate to set
	 */
	public void setCrtDate(String crtDate) {
		this.crtDate = crtDate;
	}
	/**
	 * @return the udtDate
	 */
	public String getUdtDate() {
		return udtDate;
	}
	/**
	 * @param udtDate the udtDate to set
	 */
	public void setUdtDate(String udtDate) {
		this.udtDate = udtDate;
	}
	/**
	 * @return the delYn
	 */
	public String getDelYn() {
		return delYn;
	}
	/**
	 * @param delYn the delYn to set
	 */
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	/**
	 * @return the vcounter
	 */
	public String getVcounter() {
		return vcounter;
	}
	/**
	 * @param vcounter the vcounter to set
	 */
	public void setVcounter(String vcounter) {
		this.vcounter = vcounter;
	}
	/**
	 * @return the bbsCd
	 */
	public String getBbsCd() {
		return bbsCd;
	}
	/**
	 * @param bbsCd the bbsCd to set
	 */
	public void setBbsCd(String bbsCd) {
		this.bbsCd = bbsCd;
	}
	/**
	 * @return the stDt
	 */
	public String getStDt() {
		return stDt;
	}
	/**
	 * @param stDt the stDt to set
	 */
	public void setStDt(String stDt) {
		this.stDt = stDt;
	}
	/**
	 * @return the enDt
	 */
	public String getEnDt() {
		return enDt;
	}
	/**
	 * @param enDt the enDt to set
	 */
	public void setEnDt(String enDt) {
		this.enDt = enDt;
	}
}
