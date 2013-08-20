package com.nmimo.common.info;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("serial")
public class FileReaderInfo implements Serializable  {

	private String sendPhoneNum;
	private String sendPhoneModel;
	private String sendUserInfo1 ="";
	private String sendUserInfo2 ="";
	private String sendUserInfo3 ="";
	private String sendUserInfo4 ="";
	private String sendUserInfo5 ="";
	private String sendUserInfo6 ="";
	private String sendUserInfo7 ="";
	private String sendUserInfo8 ="";
	private String sendUserInfo9 ="";
	private String sendUserInfo10 ="";
	private String fileRealName;
	private String fileOrgName;
	
	private int tCnt=0;
	private int sCnt=0;
	private int fCnt=0;
	
	private MultipartFile fileForm1;
	private MultipartFile fileForm2;
	private MultipartFile fileForm3;
	private MultipartFile fileForm4;
	private MultipartFile fileForm5;
	/**
	 * @return the sendPhoneNum
	 */
	public String getSendPhoneNum() {
		return sendPhoneNum;
	}
	/**
	 * @param sendPhoneNum the sendPhoneNum to set
	 */
	public void setSendPhoneNum(String sendPhoneNum) {
		this.sendPhoneNum = sendPhoneNum;
	}
	/**
	 * @return the sendPhoneModel
	 */
	public String getSendPhoneModel() {
		return sendPhoneModel;
	}
	/**
	 * @param sendPhoneModel the sendPhoneModel to set
	 */
	public void setSendPhoneModel(String sendPhoneModel) {
		this.sendPhoneModel = sendPhoneModel;
	}
	/**
	 * @return the sendUserInfo1
	 */
	public String getSendUserInfo1() {
		return sendUserInfo1;
	}
	/**
	 * @param sendUserInfo1 the sendUserInfo1 to set
	 */
	public void setSendUserInfo1(String sendUserInfo1) {
		this.sendUserInfo1 = sendUserInfo1;
	}
	/**
	 * @return the sendUserInfo2
	 */
	public String getSendUserInfo2() {
		return sendUserInfo2;
	}
	/**
	 * @param sendUserInfo2 the sendUserInfo2 to set
	 */
	public void setSendUserInfo2(String sendUserInfo2) {
		this.sendUserInfo2 = sendUserInfo2;
	}
	/**
	 * @return the sendUserInfo3
	 */
	public String getSendUserInfo3() {
		return sendUserInfo3;
	}
	/**
	 * @param sendUserInfo3 the sendUserInfo3 to set
	 */
	public void setSendUserInfo3(String sendUserInfo3) {
		this.sendUserInfo3 = sendUserInfo3;
	}
	/**
	 * @return the sendUserInfo4
	 */
	public String getSendUserInfo4() {
		return sendUserInfo4;
	}
	/**
	 * @param sendUserInfo4 the sendUserInfo4 to set
	 */
	public void setSendUserInfo4(String sendUserInfo4) {
		this.sendUserInfo4 = sendUserInfo4;
	}
	/**
	 * @return the sendUserInfo5
	 */
	public String getSendUserInfo5() {
		return sendUserInfo5;
	}
	/**
	 * @param sendUserInfo5 the sendUserInfo5 to set
	 */
	public void setSendUserInfo5(String sendUserInfo5) {
		this.sendUserInfo5 = sendUserInfo5;
	}
	/**
	 * @return the sendUserInfo6
	 */
	public String getSendUserInfo6() {
		return sendUserInfo6;
	}
	/**
	 * @param sendUserInfo6 the sendUserInfo6 to set
	 */
	public void setSendUserInfo6(String sendUserInfo6) {
		this.sendUserInfo6 = sendUserInfo6;
	}
	/**
	 * @return the sendUserInfo7
	 */
	public String getSendUserInfo7() {
		return sendUserInfo7;
	}
	/**
	 * @param sendUserInfo7 the sendUserInfo7 to set
	 */
	public void setSendUserInfo7(String sendUserInfo7) {
		this.sendUserInfo7 = sendUserInfo7;
	}
	/**
	 * @return the sendUserInfo8
	 */
	public String getSendUserInfo8() {
		return sendUserInfo8;
	}
	/**
	 * @param sendUserInfo8 the sendUserInfo8 to set
	 */
	public void setSendUserInfo8(String sendUserInfo8) {
		this.sendUserInfo8 = sendUserInfo8;
	}
	/**
	 * @return the sendUserInfo9
	 */
	public String getSendUserInfo9() {
		return sendUserInfo9;
	}
	/**
	 * @param sendUserInfo9 the sendUserInfo9 to set
	 */
	public void setSendUserInfo9(String sendUserInfo9) {
		this.sendUserInfo9 = sendUserInfo9;
	}
	/**
	 * @return the sendUserInfo10
	 */
	public String getSendUserInfo10() {
		return sendUserInfo10;
	}
	/**
	 * @param sendUserInfo10 the sendUserInfo10 to set
	 */
	public void setSendUserInfo10(String sendUserInfo10) {
		this.sendUserInfo10 = sendUserInfo10;
	}
	/**
	 * @return the fileRealName
	 */
	public String getFileRealName() {
		return fileRealName;
	}
	/**
	 * @param fileRealName the fileRealName to set
	 */
	public void setFileRealName(String fileRealName) {
		this.fileRealName = fileRealName;
	}
	/**
	 * @return the fileOrgName
	 */
	public String getFileOrgName() {
		return fileOrgName;
	}
	/**
	 * @param fileOrgName the fileOrgName to set
	 */
	public void setFileOrgName(String fileOrgName) {
		this.fileOrgName = fileOrgName;
	}
	/**
	 * @return the tCnt
	 */
	public int gettCnt() {
		return tCnt;
	}
	/**
	 * @param tCnt the tCnt to set
	 */
	public void settCnt(int tCnt) {
		this.tCnt = tCnt;
	}
	/**
	 * @return the sCnt
	 */
	public int getsCnt() {
		return sCnt;
	}
	/**
	 * @param sCnt the sCnt to set
	 */
	public void setsCnt(int sCnt) {
		this.sCnt = sCnt;
	}
	/**
	 * @return the fCnt
	 */
	public int getfCnt() {
		return fCnt;
	}
	/**
	 * @param fCnt the fCnt to set
	 */
	public void setfCnt(int fCnt) {
		this.fCnt = fCnt;
	}
	/**
	 * @return the fileForm1
	 */
	public MultipartFile getFileForm1() {
		return fileForm1;
	}
	/**
	 * @param fileForm1 the fileForm1 to set
	 */
	public void setFileForm1(MultipartFile fileForm1) {
		this.fileForm1 = fileForm1;
	}
	/**
	 * @return the fileForm2
	 */
	public MultipartFile getFileForm2() {
		return fileForm2;
	}
	/**
	 * @param fileForm2 the fileForm2 to set
	 */
	public void setFileForm2(MultipartFile fileForm2) {
		this.fileForm2 = fileForm2;
	}
	/**
	 * @return the fileForm3
	 */
	public MultipartFile getFileForm3() {
		return fileForm3;
	}
	/**
	 * @param fileForm3 the fileForm3 to set
	 */
	public void setFileForm3(MultipartFile fileForm3) {
		this.fileForm3 = fileForm3;
	}
	/**
	 * @return the fileForm4
	 */
	public MultipartFile getFileForm4() {
		return fileForm4;
	}
	/**
	 * @param fileForm4 the fileForm4 to set
	 */
	public void setFileForm4(MultipartFile fileForm4) {
		this.fileForm4 = fileForm4;
	}
	/**
	 * @return the fileForm5
	 */
	public MultipartFile getFileForm5() {
		return fileForm5;
	}
	/**
	 * @param fileForm5 the fileForm5 to set
	 */
	public void setFileForm5(MultipartFile fileForm5) {
		this.fileForm5 = fileForm5;
	}
	


	

}
