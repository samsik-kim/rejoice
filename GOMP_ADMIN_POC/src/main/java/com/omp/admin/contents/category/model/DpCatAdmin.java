/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 * redaano    2009. 4. 7.    Description
 *
 */
package com.omp.admin.contents.category.model;

import java.io.File;
import java.io.Serializable;

/**
 * 카테고리관리- Model
 * 
 * @author redaano
 * @version 0.1
 */
@SuppressWarnings("serial")
public class DpCatAdmin implements Serializable {

	private String dpCatNo = null;
	private String dpCatNm = null;
	private String dpCatDesc = null;
	private int dpCatPrior = 0;
	private int dpCatDepth = 0;
	private String subCatYn = null;
	private String useYn = null;
	private String upDpCatNo = null;
	private String bodyFilePos = null;
	private String bodyFileNm = null;
	private long bodyFileSize = 0;
	private String regId = null;
	private String regDt = null;
	private String updId = null;
	private String updDt = null;
	private String targetUrl = null;

	private int catProdCnt = 0;

	private File bodyImageUpload;
	private String bodyImageUploadFileName;
	private String bodyImageUploadContentType;

	private File modifyBodyImageUpload;
	private String modifyBodyImageUploadFileName;
	private String modifyBodyImageUploadContentType;

	public String getDpCatNo() {
		return dpCatNo;
	}

	public void setDpCatNo(String dpCatNo) {
		this.dpCatNo = dpCatNo;
	}

	public String getDpCatNm() {
		return dpCatNm;
	}

	public void setDpCatNm(String dpCatNm) {
		this.dpCatNm = dpCatNm;
	}

	public String getDpCatDesc() {
		return dpCatDesc;
	}

	public void setDpCatDesc(String dpCatDesc) {
		this.dpCatDesc = dpCatDesc;
	}

	public int getDpCatPrior() {
		return dpCatPrior;
	}

	public void setDpCatPrior(int dpCatPrior) {
		this.dpCatPrior = dpCatPrior;
	}

	public int getDpCatDepth() {
		return dpCatDepth;
	}

	public void setDpCatDepth(int dpCatDepth) {
		this.dpCatDepth = dpCatDepth;
	}

	public String getSubCatYn() {
		return subCatYn;
	}

	public void setSubCatYn(String subCatYn) {
		this.subCatYn = subCatYn;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getUpDpCatNo() {
		return upDpCatNo;
	}

	public void setUpDpCatNo(String upDpCatNo) {
		this.upDpCatNo = upDpCatNo;
	}

	public String getBodyFilePos() {
		return bodyFilePos;
	}

	public void setBodyFilePos(String bodyFilePos) {
		this.bodyFilePos = bodyFilePos;
	}

	public String getBodyFileNm() {
		return bodyFileNm;
	}

	public void setBodyFileNm(String bodyFileNm) {
		this.bodyFileNm = bodyFileNm;
	}

	public long getBodyFileSize() {
		return bodyFileSize;
	}

	public void setBodyFileSize(long bodyFileSize) {
		this.bodyFileSize = bodyFileSize;
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

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public int getCatProdCnt() {
		return catProdCnt;
	}

	public void setCatProdCnt(int catProdCnt) {
		this.catProdCnt = catProdCnt;
	}

	public File getBodyImageUpload() {
		return bodyImageUpload;
	}

	public void setBodyImageUpload(File bodyImageUpload) {
		this.bodyImageUpload = bodyImageUpload;
	}

	public String getBodyImageUploadFileName() {
		return bodyImageUploadFileName;
	}

	public void setBodyImageUploadFileName(String bodyImageUploadFileName) {
		this.bodyImageUploadFileName = bodyImageUploadFileName;
	}

	public String getBodyImageUploadContentType() {
		return bodyImageUploadContentType;
	}

	public void setBodyImageUploadContentType(String bodyImageUploadContentType) {
		this.bodyImageUploadContentType = bodyImageUploadContentType;
	}

	public File getModifyBodyImageUpload() {
		return modifyBodyImageUpload;
	}

	public void setModifyBodyImageUpload(File modifyBodyImageUpload) {
		this.modifyBodyImageUpload = modifyBodyImageUpload;
	}

	public String getModifyBodyImageUploadFileName() {
		return modifyBodyImageUploadFileName;
	}

	public void setModifyBodyImageUploadFileName(String modifyBodyImageUploadFileName) {
		this.modifyBodyImageUploadFileName = modifyBodyImageUploadFileName;
	}

	public String getModifyBodyImageUploadContentType() {
		return modifyBodyImageUploadContentType;
	}

	public void setModifyBodyImageUploadContentType(String modifyBodyImageUploadContentType) {
		this.modifyBodyImageUploadContentType = modifyBodyImageUploadContentType;
	}

}
