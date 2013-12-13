package com.omp.dev.member.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MemberDoc implements Serializable {

	private String transNo;			// 전환 번호
	private String docHmCd;			// 서류 코드
	private String mbrNo;			// 회원 번호
	private String filePath;		// 파일 경로
	private String fileNm;			// 파일명
	private long fileSize;			// 파일 사이즈
	private String lstValid;		// 최종 유효여부
	private String regId;			// 등록자
	
	/**
	 * @return the transNo
	 */
	public String getTransNo() {
		return transNo;
	}
	/**
	 * @param transNo the transNo to set
	 */
	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}
	/**
	 * @return the docHmCd
	 */
	public String getDocHmCd() {
		return docHmCd;
	}
	/**
	 * @param docHmCd the docHmCd to set
	 */
	public void setDocHmCd(String docHmCd) {
		this.docHmCd = docHmCd;
	}
	/**
	 * @return the mbrNo
	 */
	public String getMbrNo() {
		return mbrNo;
	}
	/**
	 * @param mbrNo the mbrNo to set
	 */
	public void setMbrNo(String mbrNo) {
		this.mbrNo = mbrNo;
	}
	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * @return the fileNm
	 */
	public String getFileNm() {
		return fileNm;
	}
	/**
	 * @param fileNm the fileNm to set
	 */
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}
	/**
	 * @return the fileSize
	 */
	public long getFileSize() {
		return fileSize;
	}
	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	/**
	 * @return the lstValid
	 */
	public String getLstValid() {
		return lstValid;
	}
	/**
	 * @param lstValid the lstValid to set
	 */
	public void setLstValid(String lstValid) {
		this.lstValid = lstValid;
	}
	/**
	 * @return the regId
	 */
	public String getRegId() {
		return regId;
	}
	/**
	 * @param regId the regId to set
	 */
	public void setRegId(String regId) {
		this.regId = regId;
	}
}
