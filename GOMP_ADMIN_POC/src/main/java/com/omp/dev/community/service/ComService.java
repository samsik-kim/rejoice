/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 * redaano    2009. 4. 15.    Description
 *
 */
package com.omp.dev.community.service;

import java.util.List;

import com.omp.dev.community.model.Ctgr;
import com.omp.dev.community.model.DpCat;

/**
 * ComService
 * 
 * @author redaano
 * @version 0.1
 */
public interface ComService	{
	/**
	 * CategoryList Search
	 * 
	 * @param highCtgr
	 * @return
	 */
	public List<Ctgr> getCategoryList( String highCtgr );

	/**
	 * DevPoC Issue CategoryList Search
	 * Sung Ju-Hun, 2009-10-16
	 * 
	 * @param highCtgr
	 * @return
	 */
	public List<Ctgr> getIssueCategoryList( String highCtgr );

//	/**
//	 * PhoneName List
//	 * 
//	 * @param makeComp
//	 * @return
//	 */
//	public List<PhoneInfo> getPhoneNameList( String makeComp );

	/**
	 * Display Category Level1
	 * 
	 * @param dpCatNo
	 * @return
	 */
	public List<DpCat> getDpCatLvl1List( List<String> dpCatNo );

	/**
	 * Display Category
	 * 
	 * @param upDpCatNo
	 * @return
	 */
	public List<DpCat> getDpCatList( String upDpCatNo );

	/**
	 * Common Code All Search ( Server Start )
	 * 
	 * @return
	 */
	public Object getCommCodeMap();
}
