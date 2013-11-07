/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 * redaano    2009. 5. 24.    Description
 *
 */
package com.omp.dev.community.persistence.dao;

import com.ibatis.dao.client.DaoException;
import com.omp.dev.community.model.Qna;

/**
 * QnaDAO
 * 
 * @author redaano
 * @version 0.1
 */
public interface QnaDAO {

	/**
	 * Qna Insert
	 * 
	 * @param qna
	 * @return
	 * @throws DaoException
	 */
	public void insertQna(Qna qna) throws DaoException;

}
