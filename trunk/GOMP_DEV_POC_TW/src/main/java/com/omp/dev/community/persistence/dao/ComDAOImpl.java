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

import org.apache.log4j.Logger;

import com.ibatis.dao.client.DaoException;
import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.template.SqlMapDaoTemplate;
import com.omp.dev.community.model.Ctgr;
import com.omp.dev.community.model.DpCat;

/**
 * ComDAOImpl
 * 
 * @author redaano
 * @version 0.1
 */
public class ComDAOImpl extends SqlMapDaoTemplate implements ComDAO	{
	@SuppressWarnings( "unused" )
	private static Logger logger	= Logger.getLogger( ComDAOImpl.class );

	public ComDAOImpl( DaoManager daoManager )	{
		super( daoManager );
	}

	/**
	 * CategoryList Search
	 * 
	 * @param highCtgr
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings( "unchecked" )
	public List<Ctgr> selectCategoryList( String highCtgr ) throws DaoException	{
		return	queryForList( "Common.selectCategoryList", highCtgr );
	}

	/**
	 * DevPoC Issue CategoryList Search
	 * Sung Ju-Hun, 2009-10-16
	 * 
	 * @param highCtgr
	 * @return
	 */
	@SuppressWarnings( "unchecked" )
	public List<Ctgr> selectIssueCategoryList( String highCtgr ) throws DaoException	{
		return	queryForList( "Common.selectIssueCategoryList", highCtgr );
	}

//	/**
//	 * PhoneName List
//	 * 
//	 * @param makeComp
//	 * @return
//	 * @throws DaoException
//	 */
//	@SuppressWarnings( "unchecked" )
//	public List<PhoneInfo> selectPhoneNameList( String makeComp ) throws DaoException	{
//		return	queryForList( "Common.selectPhoneNameList", makeComp );
//	}

	/**
	 * Display Category Level1
	 * 
	 * @param dpCatNo
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings( "unchecked" )
	public List<DpCat> selectDpCatLvl1List( List<String> dpCatNo ) throws DaoException	{
		return	queryForList( "Common.selectDpCatLvl1List", dpCatNo );
	}

	/**
	 * Display Category
	 * 
	 * @param upDpCatNo
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings( "unchecked" )
	public List<DpCat> selectDpCatList( String upDpCatNo ) throws DaoException	{
		return	queryForList( "Common.selectDpCatList", upDpCatNo );
	}

	/**
	 * Common Code All Search ( Server Start )
	 * 
	 * @return
	 */
	public Object selectCommCode()	{
		return	this.queryForMap( "commcode.selectCommCode", null, "CD" );
	}
}
