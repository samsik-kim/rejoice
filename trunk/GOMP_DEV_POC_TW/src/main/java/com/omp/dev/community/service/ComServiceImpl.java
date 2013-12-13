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

import org.apache.log4j.Logger;

import com.ibatis.dao.client.DaoException;
import com.ibatis.dao.client.DaoManager;
import com.omp.dev.community.model.Ctgr;
import com.omp.dev.community.model.DpCat;
import com.omp.dev.community.persistence.dao.ComDAO;
import com.omp.commons.exception.InfraException;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.persistence.dao.DaoConfig;

/**
 * ComServiceImpl
 * 
 * @author redaano
 * @version 0.1
 */
public class ComServiceImpl implements ComService	{
	private static Logger logger	= Logger.getLogger( ComServiceImpl.class );
	DaoManager manager	= null;
	ComDAO dao	= null;

	public ComServiceImpl()	{
		manager	= DaoConfig.getDaoManager();
		dao	= (ComDAO)manager.getDao( ComDAO.class );
	}

	/**
	 * CategoryList Search
	 * 
	 * @param highCtgr
	 * @return
	 */
	public List<Ctgr> getCategoryList( String highCtgr )	{
		List<Ctgr> result	= null;

		try	{
			result	= dao.selectCategoryList( highCtgr );
		}	catch( Exception e )	{
			throw new ServiceException( "카테고리 목록 조회 실패.", e);
		}

		return	result;
	}

	/**
	 * DevPoC Issue CategoryList Search
	 * Sung Ju-Hun, 2009-10-16
	 * 
	 * @param highCtgr
	 * @return
	 */
	public List<Ctgr> getIssueCategoryList( String highCtgr )	{
		List<Ctgr> result	= null;

		try	{
			result	= dao.selectIssueCategoryList( highCtgr );
		}	catch( Exception e )	{
			throw new ServiceException( "이슈 카테고리 목록 조회 실패.", e);
		}

		return	result;
	}


	/**
	 * Display Category Level1
	 * 
	 * @param dpCatNo
	 * @return
	 */
	public List<DpCat> getDpCatLvl1List( List<String> dpCatNo )	{
		List<DpCat> result	= null;

		try	{
			result	= dao.selectDpCatLvl1List( dpCatNo );
		}	catch( Exception e )	{
			throw new ServiceException( "전시 1레벨 카테고리 목록 조회 실패.", e);
		}

		return	result;
	}

	/**
	 * Display Category
	 * 
	 * @param upDpCatNo
	 * @return
	 */
	public List<DpCat> getDpCatList( String upDpCatNo )	{
		List<DpCat> result	= null;

		try	{
			result	= dao.selectDpCatList( upDpCatNo );
		}	catch( Exception e )	{
			throw new ServiceException( "전시 카테고리 목록 조회 실패.", e);
		}

		return result;
	}

	/**
	 * Common Code All Search ( Server Start )
	 * 
	 * @return
	 */
	public Object getCommCodeMap()	{
		return	dao.selectCommCode();
	}
}
