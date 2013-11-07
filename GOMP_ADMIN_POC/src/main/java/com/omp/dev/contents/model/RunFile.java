package com.omp.dev.contents.model;

import java.io.File;
import java.io.InputStream;

public class RunFile {
	
	// 실행파일 정보
    private 	File   		runUpload;  							// 실행_파일 (물리)
	private	 	String 		runUploadFileName; 						// 첨부파일명
	private 	String 		runUploadContentType; 					// 첨부파일 컨텐츠타입
	private 	String 		runUploadDelYn; 							// 삭제여부
    private 	String 		runFilePos;    							// 실행_파일_위치
    private 	String 		runFileNm;    							// 실행_파일_명  
    private 	String 		runFileSize;    							// 실행_파일_크기
    private 	InputStream runInputStream;
	private 	String 		runContentDisposition;
	private 	long   		runContentLength;
	/**
	 * @return the runUpload
	 */
	public File getRunUpload() {
		return runUpload;
	}
	/**
	 * @param runUpload the runUpload to set
	 */
	public void setRunUpload(File runUpload) {
		this.runUpload = runUpload;
	}
	/**
	 * @return the runUploadFileName
	 */
	public String getRunUploadFileName() {
		return runUploadFileName;
	}
	/**
	 * @param runUploadFileName the runUploadFileName to set
	 */
	public void setRunUploadFileName(String runUploadFileName) {
		this.runUploadFileName = runUploadFileName;
	}
	/**
	 * @return the runUploadContentType
	 */
	public String getRunUploadContentType() {
		return runUploadContentType;
	}
	/**
	 * @param runUploadContentType the runUploadContentType to set
	 */
	public void setRunUploadContentType(String runUploadContentType) {
		this.runUploadContentType = runUploadContentType;
	}
	/**
	 * @return the runUploadDelYn
	 */
	public String getRunUploadDelYn() {
		return runUploadDelYn;
	}
	/**
	 * @param runUploadDelYn the runUploadDelYn to set
	 */
	public void setRunUploadDelYn(String runUploadDelYn) {
		this.runUploadDelYn = runUploadDelYn;
	}
	/**
	 * @return the runFilePos
	 */
	public String getRunFilePos() {
		return runFilePos;
	}
	/**
	 * @param runFilePos the runFilePos to set
	 */
	public void setRunFilePos(String runFilePos) {
		this.runFilePos = runFilePos;
	}
	/**
	 * @return the runFileNm
	 */
	public String getRunFileNm() {
		return runFileNm;
	}
	/**
	 * @param runFileNm the runFileNm to set
	 */
	public void setRunFileNm(String runFileNm) {
		this.runFileNm = runFileNm;
	}
	/**
	 * @return the runFileSize
	 */
	public String getRunFileSize() {
		return runFileSize;
	}
	/**
	 * @param runFileSize the runFileSize to set
	 */
	public void setRunFileSize(String runFileSize) {
		this.runFileSize = runFileSize;
	}
	/**
	 * @return the runInputStream
	 */
	public InputStream getRunInputStream() {
		return runInputStream;
	}
	/**
	 * @param runInputStream the runInputStream to set
	 */
	public void setRunInputStream(InputStream runInputStream) {
		this.runInputStream = runInputStream;
	}
	/**
	 * @return the runContentDisposition
	 */
	public String getRunContentDisposition() {
		return runContentDisposition;
	}
	/**
	 * @param runContentDisposition the runContentDisposition to set
	 */
	public void setRunContentDisposition(String runContentDisposition) {
		this.runContentDisposition = runContentDisposition;
	}
	/**
	 * @return the runContentLength
	 */
	public long getRunContentLength() {
		return runContentLength;
	}
	/**
	 * @param runContentLength the runContentLength to set
	 */
	public void setRunContentLength(long runContentLength) {
		this.runContentLength = runContentLength;
	}

	
	
}
