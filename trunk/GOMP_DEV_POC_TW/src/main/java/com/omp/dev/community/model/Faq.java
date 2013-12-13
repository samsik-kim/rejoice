/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 * redaano    2009. 4. 2.    Description
 *
 */
package com.omp.dev.community.model;

/**
 * FAQ Model
 * 
 * @author redaano
 * @version 0.1
 */
public class Faq {
	private String faqId = ""; // FAQ_ID
	private String ctgrCd = ""; // Category_Code
	private String title = ""; // Title
	private String dscr = ""; // Content
	private String hit = ""; // Hits
	private String delYn = ""; // Delete_Check
	private String delDttm = ""; // Delete_Date
	private String bestYn = ""; // Best_Check
	private String bestDttm = ""; // Best_Date
	private String openYn = ""; // Open_Check
	private String sort = ""; // Sort
	private String insDttm = ""; // Insert_Date
	private String insBy = ""; // Insert_Person
	private String updDttm = ""; // Update_Date
	private String updBy = ""; // Updater

	private String attFile = ""; // File Route
	private String attFileNm = ""; // File Name
	
	private String imgYn;
	private String img_ofnm;
	private String img_path;
	private String downYn;
	private String down_ofnm;
	private String down_path;

	public String getFaqId() {
		return faqId;
	}

	public void setFaqId(String faqId) {
		this.faqId = faqId;
	}

	public String getCtgrCd() {
		return ctgrCd;
	}

	public void setCtgrCd(String ctgrCd) {
		this.ctgrCd = ctgrCd;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDscr() {
		return dscr;
	}

	public void setDscr(String dscr) {
		this.dscr = dscr;
	}

	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public String getDelDttm() {
		return delDttm;
	}

	public void setDelDttm(String delDttm) {
		this.delDttm = delDttm;
	}

	public String getBestYn() {
		return bestYn;
	}

	public void setBestYn(String bestYn) {
		this.bestYn = bestYn;
	}

	public String getBestDttm() {
		return bestDttm;
	}

	public void setBestDttm(String bestDttm) {
		this.bestDttm = bestDttm;
	}

	public String getOpenYn() {
		return openYn;
	}

	public void setOpenYn(String openYn) {
		this.openYn = openYn;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
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

	public String getAttFile()	{
		return attFile;
	}

	public void setAttFile( String attFile )	{
		this.attFile	= attFile;
	}

	public String getAttFileNm()	{
		return attFileNm;
	}

	public void setAttFileNm( String attFileNm )	{
		this.attFileNm	= attFileNm;
	}

	/**
	 * @return the imgYn
	 */
	public String getImgYn() {
		return imgYn;
	}

	/**
	 * @param imgYn the imgYn to set
	 */
	public void setImgYn(String imgYn) {
		this.imgYn = imgYn;
	}

	/**
	 * @return the img_ofnm
	 */
	public String getImg_ofnm() {
		return img_ofnm;
	}

	/**
	 * @param img_ofnm the img_ofnm to set
	 */
	public void setImg_ofnm(String img_ofnm) {
		this.img_ofnm = img_ofnm;
	}

	/**
	 * @return the img_path
	 */
	public String getImg_path() {
		return img_path;
	}

	/**
	 * @param img_path the img_path to set
	 */
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}

	/**
	 * @return the downYn
	 */
	public String getDownYn() {
		return downYn;
	}

	/**
	 * @param downYn the downYn to set
	 */
	public void setDownYn(String downYn) {
		this.downYn = downYn;
	}

	/**
	 * @return the down_ofnm
	 */
	public String getDown_ofnm() {
		return down_ofnm;
	}

	/**
	 * @param down_ofnm the down_ofnm to set
	 */
	public void setDown_ofnm(String down_ofnm) {
		this.down_ofnm = down_ofnm;
	}

	/**
	 * @return the down_path
	 */
	public String getDown_path() {
		return down_path;
	}

	/**
	 * @param down_path the down_path to set
	 */
	public void setDown_path(String down_path) {
		this.down_path = down_path;
	}
}