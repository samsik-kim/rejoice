package com.omp.commons.service;

import com.ibatis.dao.client.DaoManager;
import com.omp.commons.persistence.dao.CommonDAO;
import com.omp.commons.persistence.dao.DaoConfig;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.ThreadSession;

/**
 * 추상 서비스
 * @author pat
 *
 */
public abstract class AbstractService {
	
	protected GLogger		log;
	protected DaoManager	daoManager;
	protected CommonDAO		commonDAO;
	
	
	public AbstractService() {
		this.daoManager	= DaoConfig.getDaoManager();
		this.commonDAO 	= (CommonDAO)this.daoManager.getDao(CommonDAO.class);
		this.log		= new GLogger(this.getClass());
	}
	
	protected void setStep(String step) {
		ThreadSession.getSession().setServiceStep(step);
	}
}
