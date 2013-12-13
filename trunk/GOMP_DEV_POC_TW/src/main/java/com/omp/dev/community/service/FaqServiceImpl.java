package com.omp.dev.community.service;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.persistence.dao.DaoConfig;
import com.omp.commons.service.AbstractService;
import com.omp.commons.util.GLogger;
import com.omp.dev.community.model.Faq;
import com.omp.dev.community.persistence.dao.FaqDAO;

/**
 * FAQ ServiceImpl
 * 
 * @author redaano
 * @version 0.1
 */
public class FaqServiceImpl
	extends AbstractService implements FaqService {
	private static GLogger logger = new GLogger(FaqServiceImpl.class);
	DaoManager manager = null;
	FaqDAO dao = null;


	public FaqServiceImpl() {
		manager = DaoConfig.getDaoManager();
		dao = (FaqDAO)manager.getDao(FaqDAO.class);
	}


	/**
	 * FAQ List Search
	 * 
	 * @param faq
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getFaqList(Faq faq) throws ServiceException {
		List result = null;
		try {
			result = this.commonDAO.queryForList("Community_Faq.selectFaqList", faq);

		} catch(Exception e) {
			throw new ServiceException("getFaqList Error", e);
		}
		return result;
	}

	@Override
	public void updateFaqHit(Faq faq) throws ServiceException {
		try {
			this.commonDAO.update("Community_Faq.updateHitNumber", faq);
		} catch (Exception e) {
			throw new ServiceException("faqHitIncrease", e);
		}
		
	}

}
