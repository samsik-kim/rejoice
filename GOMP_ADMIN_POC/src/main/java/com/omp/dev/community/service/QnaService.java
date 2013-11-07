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
package com.omp.dev.community.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.omp.dev.community.model.Ctgr;
import com.omp.dev.community.model.Qna;

/**
 * QnaService
 * 
 * @author redaano
 * @version 0.1
 */
public interface QnaService {

	/**
	 * Qna Insert
	 * @param req 
	 * 
	 * @param qna
	 * @return
	 */
	public void addQna(HttpServletRequest req, Qna qna);
	
	/**
	 * CategoryList Search
	 * 
	 * @param String
	 * @return
	 */
	public List<Ctgr> getCategoryList();

}
