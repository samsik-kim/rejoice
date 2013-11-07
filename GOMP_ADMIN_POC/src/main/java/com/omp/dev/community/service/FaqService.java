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
package com.omp.dev.community.service;

import java.util.List;

import com.omp.commons.exception.ServiceException;
import com.omp.dev.community.model.Faq;

/**
 * FAQ Service
 * 
 * @author redaano
 * @version 0.1
 */
public interface FaqService {

	/**
	 * FAQ List Search
	 * 
	 * @param faq
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getFaqList(Faq faq) throws ServiceException;
	
	/**
	 * Faq Hit Number Update
	 * @param faq
	 */
	public void updateFaqHit(Faq faq) throws ServiceException;

}
