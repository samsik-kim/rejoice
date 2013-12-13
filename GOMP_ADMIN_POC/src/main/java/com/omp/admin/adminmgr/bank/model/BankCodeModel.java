package com.omp.admin.adminmgr.bank.model;

import java.io.Serializable;

import com.omp.commons.model.CommonModel;
import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;

@SuppressWarnings("serial")
public class BankCodeModel extends CommonModel implements Pagenateable, Serializable {
	
	private String RNUM;						// Row Number
	private String BANK_CD;					// Bank Code
	private String CERTI_CD;					// Certification Code
	private String BANK_NM;					// Bank Name
	private String BANK_BRIEF_NM;		// Bank Brief name
	private String USE_YN;						// Use Y or N
	private String REG_ID;						// Register ID
	private String UPD_ID;						// Update ID
	
	private String[] CHECK_BANK_CD;		// Multi Check Value
	
	private Long totalCount;
	private PagenateInfoModel page = new PagenateInfoModel(20);


	/**
	 * @return the rNUM
	 */
	public String getRNUM() {
		return RNUM;
	}

	/**
	 * @param rNUM the rNUM to set
	 */
	public void setRNUM(String rNUM) {
		RNUM = rNUM;
	}

	/**
	 * @return the bANK_CD
	 */
	public String getBANK_CD() {
		return BANK_CD;
	}

	/**
	 * @param bANK_CD the bANK_CD to set
	 */
	public void setBANK_CD(String bANK_CD) {
		BANK_CD = bANK_CD;
	}

	/**
	 * @return the cERTI_CD
	 */
	public String getCERTI_CD() {
		return CERTI_CD;
	}

	/**
	 * @param cERTI_CD the cERTI_CD to set
	 */
	public void setCERTI_CD(String cERTI_CD) {
		CERTI_CD = cERTI_CD;
	}

	/**
	 * @return the bANK_NM
	 */
	public String getBANK_NM() {
		return BANK_NM;
	}

	/**
	 * @param bANK_NM the bANK_NM to set
	 */
	public void setBANK_NM(String bANK_NM) {
		BANK_NM = bANK_NM;
	}

	/**
	 * @return the bANK_BRIEF_NM
	 */
	public String getBANK_BRIEF_NM() {
		return BANK_BRIEF_NM;
	}

	/**
	 * @param bANK_BRIEF_NM the bANK_BRIEF_NM to set
	 */
	public void setBANK_BRIEF_NM(String bANK_BRIEF_NM) {
		BANK_BRIEF_NM = bANK_BRIEF_NM;
	}

	/**
	 * @return the uSE_YN
	 */
	public String getUSE_YN() {
		return USE_YN;
	}

	/**
	 * @param uSE_YN the uSE_YN to set
	 */
	public void setUSE_YN(String uSE_YN) {
		USE_YN = uSE_YN;
	}

	/**
	 * @return the rEG_ID
	 */
	public String getREG_ID() {
		return REG_ID;
	}

	/**
	 * @param rEG_ID the rEG_ID to set
	 */
	public void setREG_ID(String rEG_ID) {
		REG_ID = rEG_ID;
	}

	/**
	 * @return the uPD_ID
	 */
	public String getUPD_ID() {
		return UPD_ID;
	}

	/**
	 * @param uPD_ID the uPD_ID to set
	 */
	public void setUPD_ID(String uPD_ID) {
		UPD_ID = uPD_ID;
	}

	/**
	 * @return the cHECK_BANK_CD
	 */
	public String[] getCHECK_BANK_CD() {
		return CHECK_BANK_CD;
	}

	/**
	 * @param cHECK_BANK_CD the cHECK_BANK_CD to set
	 */
	public void setCHECK_BANK_CD(String[] cHECK_BANK_CD) {
		CHECK_BANK_CD = cHECK_BANK_CD;
	}

	/**
	 * @return the totalCount
	 */
	public Long getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
	@Override
	public PagenateInfoModel getPage() {
		// TODO Auto-generated method stub
		return this.page;
	}

}
