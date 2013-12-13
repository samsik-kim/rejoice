package com.omp.dev.contents.model;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ContentImage {
	private 	String		cid;		// 컨텐츠_코드
	private 	String		imgGbn;		// 이미지_구분
	private 	String		filePos;	// 원본_파일
	private 	String 		fileRealPos;// 원본_실제_파일
	private 	String		fileNm;		// 원본_파일_명
	private 	long		fileSize;	// 원본_파일_크기
	private 	String 		insDttm;	// 등록_일시
	private 	String 		insBy;		// 등록_자
	private 	String 		updDttm;	// 수정_일시
	private 	String 		updBy;		// 수정_자
	
	// Image File
	private 	File 		upload;
	private 	String 		uploadFileName;
	private 	String 		uploadFileFullPath;
	private 	String 		uploadContentType;

	private 	InputStream inputStream;
	private 	String 		contentDisposition;
	private 	long 		contentLength;
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
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

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getUploadFileFullPath() {
		return uploadFileFullPath;
	}
	public void setUploadFileFullPath(String uploadFileFullPath) {
		this.uploadFileFullPath = uploadFileFullPath;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getImgGbn() {
		return imgGbn;
	}
	public void setImgGbn(String imgGbn) {
		this.imgGbn = imgGbn;
	}

	public String getInsDttm() {
		return insDttm;
	}
	public void setInsDttm(String insDttm) {
		this.insDttm = insDttm;
	}
	public String getInsBy() {
		return insBy;
	}
	public void setInsBy(String insBy) {
		this.insBy = insBy;
	}
	public String getUpdDttm() {
		return updDttm;
	}
	public void setUpdDttm(String updDttm) {
		this.updDttm = updDttm;
	}
	public String getUpdBy() {
		return updBy;
	}
	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public String getContentDisposition() {
		return contentDisposition;
	}
	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}
	public long getContentLength() {
		return contentLength;
	}
	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}
	public String getFileRealPos() {
		return fileRealPos;
	}
	public void setFileRealPos(String fileRealPos) {
		this.fileRealPos = fileRealPos;
	}
	
}
