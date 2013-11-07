/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 * bcpark    2011. 3. 14.    Description
 *
 */
package com.omp.admin.product.model;

/**
 * 상품 이미지 도메인
 * 
 * @author bcpark
 * @version 0.1
 */
public class ContentsImg {
	private String cid;
	private String imgGbn;
	private String filePos;
	private String fileNm;
	private String fileSize;

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getImgGbn() {
		return imgGbn;
	}

	public void setImgGbn(String imgGbn) {
		this.imgGbn = imgGbn;
	}

	public String getFilePos() {
		return filePos;
	}

	public void setFilePos(String filePos) {
		this.filePos = filePos;
	}

	public String getFileNm() {
		return fileNm;
	}

	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

}