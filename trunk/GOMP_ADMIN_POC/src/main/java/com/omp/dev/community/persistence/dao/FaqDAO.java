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

import com.ibatis.dao.client.DaoException;
import com.omp.dev.community.model.Faq;

/**
 * FAQ DAO
 * 
 * @author redaano
 * @version 0.1
 */
public interface FaqDAO {

	/**
	 * FAQ List Search
	 * 
	 * @param faq
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	public List selectFaqList(Faq faq) throws DaoException;

}