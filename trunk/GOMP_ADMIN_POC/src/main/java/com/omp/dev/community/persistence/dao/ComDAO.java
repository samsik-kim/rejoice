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
package com.omp.dev.community.persistence.dao;

import java.util.List;

import com.ibatis.dao.client.DaoException;
import com.omp.dev.community.model.Ctgr;
import com.omp.dev.community.model.DpCat;

/**
 * ComDAO
 * 
 * @author redaano
 * @version 0.1
 */
public interface ComDAO	{
	/**
	 * CategoryList Search
	 * 
	 * @param highCtgr
	 * @return
	 * @throws DaoException
	 */
	public List<Ctgr> selectCategoryList( String highCtgr ) throws DaoException;

	/**
	 * DevPoC Issue CategoryList Search
	 * Sung Ju-Hun, 2009-10-16
	 * 
	 * @param highCtgr
	 * @return
	 */
	public List<Ctgr> selectIssueCategoryList( String highCtgr ) throws DaoException;

//	/**
//	 * PhoneName List
//	 * 
//	 * @param makeComp
//	 * @return
//	 * @throws DaoException
//	 */
//	public List<PhoneInfo> selectPhoneNameList( String makeComp ) throws DaoException;

	/**
	 * Display Category Level1
	 * 
	 * @param dpCatNo
	 * @return
	 * @throws DaoException
	 */
	public List<DpCat> selectDpCatLvl1List( List<String> dpCatNo ) throws DaoException;

	/**
	 * Display Category
	 * 
	 * @param upDpCatNo
	 * @return
	 * @throws DaoException
	 */
	public List<DpCat> selectDpCatList( String upDpCatNo ) throws DaoException;

	/**
	 * Common Code All Search ( Server Start )
	 * 
	 * @return
	 */
	public Object selectCommCode();
}
