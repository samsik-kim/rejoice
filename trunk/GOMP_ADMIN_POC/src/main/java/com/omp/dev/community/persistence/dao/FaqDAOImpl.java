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
package com.omp.dev.community.persistence.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.ibatis.dao.client.DaoException;
import com.ibatis.dao.client.DaoManager;
import com.omp.commons.persistence.dao.CommonDAOImpl;
import com.omp.commons.util.GLogger;
import com.omp.dev.community.model.Faq;

/**
 * FAQ DAOImpl
 * 
 * @author redaano
 * @version 0.1
 */
public class FaqDAOImpl extends CommonDAOImpl implements FaqDAO {
	@SuppressWarnings("unused")
	private static GLogger logger = new GLogger(FaqDAOImpl.class);


	public FaqDAOImpl(DaoManager daoManager) {
		super(daoManager);
	}


	/**
	 * FAQ List Search
	 * 
	 * @param faq
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	public List selectFaqList(Faq faq) throws DaoException {
		return queryForList("Community_Faq.selectFaqList", faq);
	}

}
