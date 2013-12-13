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
import com.ibatis.dao.client.DaoManager;
import com.omp.commons.persistence.dao.CommonDAOImpl;
import com.omp.commons.util.GLogger;
import com.omp.dev.community.model.Qna;

/**
 * QnaDAOImpl
 * 
 * @author redaano
 * @version 0.1
 */
public class QnaDAOImpl extends CommonDAOImpl implements QnaDAO {
	static GLogger logger = new GLogger(QnaDAOImpl.class);


	public QnaDAOImpl(DaoManager daoManager) {
		super(daoManager);
	}

	/**
	 * Qna Insert
	 * 
	 * @param qna
	 * @return
	 * @throws DaoException
	 */
	public void insertQna(Qna qna) throws DaoException {
		insert("Community_Qna.insertQna", qna);
	}
}
