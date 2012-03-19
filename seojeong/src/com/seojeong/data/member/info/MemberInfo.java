package com.seojeong.data.member.info;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MemberInfo implements Serializable{

	private String MEMBER_SEQ;
	private String MEMBER_NM;
	private String CONTANCT1;
	private String CONTANCT2;
	private String ADDR;
	private String EMAIL;
	private String VST_CNT;
	private String REG_DT;
	private String UP_DT;
	private String WIN_CNT;
	/**
	 * @return the mEMBER_SEQ
	 */
	public String getMEMBER_SEQ() {
		return MEMBER_SEQ;
	}
	/**
	 * @param mEMBER_SEQ the mEMBER_SEQ to set
	 */
	public void setMEMBER_SEQ(String mEMBER_SEQ) {
		MEMBER_SEQ = mEMBER_SEQ;
	}
	/**
	 * @return the mEMBER_NM
	 */
	public String getMEMBER_NM() {
		return MEMBER_NM;
	}
	/**
	 * @param mEMBER_NM the mEMBER_NM to set
	 */
	public void setMEMBER_NM(String mEMBER_NM) {
		MEMBER_NM = mEMBER_NM;
	}
	/**
	 * @return the cONTANCT1
	 */
	public String getCONTANCT1() {
		return CONTANCT1;
	}
	/**
	 * @param cONTANCT1 the cONTANCT1 to set
	 */
	public void setCONTANCT1(String cONTANCT1) {
		CONTANCT1 = cONTANCT1;
	}
	/**
	 * @return the cONTANCT2
	 */
	public String getCONTANCT2() {
		return CONTANCT2;
	}
	/**
	 * @param cONTANCT2 the cONTANCT2 to set
	 */
	public void setCONTANCT2(String cONTANCT2) {
		CONTANCT2 = cONTANCT2;
	}
	/**
	 * @return the aDDR
	 */
	public String getADDR() {
		return ADDR;
	}
	/**
	 * @param aDDR the aDDR to set
	 */
	public void setADDR(String aDDR) {
		ADDR = aDDR;
	}
	/**
	 * @return the eMAIL
	 */
	public String getEMAIL() {
		return EMAIL;
	}
	/**
	 * @param eMAIL the eMAIL to set
	 */
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	/**
	 * @return the vST_CNT
	 */
	public String getVST_CNT() {
		return VST_CNT;
	}
	/**
	 * @param vST_CNT the vST_CNT to set
	 */
	public void setVST_CNT(String vST_CNT) {
		VST_CNT = vST_CNT;
	}
	/**
	 * @return the rEG_DT
	 */
	public String getREG_DT() {
		return REG_DT;
	}
	/**
	 * @param rEG_DT the rEG_DT to set
	 */
	public void setREG_DT(String rEG_DT) {
		REG_DT = rEG_DT;
	}
	/**
	 * @return the uP_DT
	 */
	public String getUP_DT() {
		return UP_DT;
	}
	/**
	 * @param uP_DT the uP_DT to set
	 */
	public void setUP_DT(String uP_DT) {
		UP_DT = uP_DT;
	}
	/**
	 * @return the wIN_CNT
	 */
	public String getWIN_CNT() {
		return WIN_CNT;
	}
	/**
	 * @param wIN_CNT the wIN_CNT to set
	 */
	public void setWIN_CNT(String wIN_CNT) {
		WIN_CNT = wIN_CNT;
	}
	
}
