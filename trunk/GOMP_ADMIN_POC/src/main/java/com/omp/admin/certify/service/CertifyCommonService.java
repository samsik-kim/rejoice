package com.omp.admin.certify.service;

import java.sql.SQLException;
import java.util.List;

import com.omp.admin.certify.model.CerfifyTargetTestCase;
import com.omp.admin.certify.model.CertifyAppHistoryList;
import com.omp.admin.certify.model.CertifyAppSimpleInfomation;
import com.omp.admin.certify.model.CertifySupportPhone;
import com.omp.admin.certify.model.CertifyTestCase;
import com.omp.admin.certify.model.SearchConditionCertifyAppDetail;
import com.omp.admin.certify.model.SearchConditionCertifyAppHistory;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.model.PagenateList;

public interface CertifyCommonService {

	/**
	 * <pre>
	 * Certifiaction Application History List
	 * </pre>
	 * 
	 * @param SearchConditionCertifyAppHistory
	 * @return PagenateList<CertifyAppHistoryList>
	 * @throws ServiceException
	 * @throws SQLException
	 */
	public PagenateList<CertifyAppHistoryList> findCertifyHistoryAppPagingList(SearchConditionCertifyAppHistory param) throws ServiceException;

	
	/**
	 * <pre>
	 * Certification Application History View
	 * </pre>
	 * 
	 * @param SearchConditionCertifyAppDetail 
	 * @return CertifyAppSimpleInfomation
	 * @throws ServiceException
	 * @throws SQLException
	 */
	public CertifyAppSimpleInfomation findCertifyHistoryAppInfomationView(SearchConditionCertifyAppDetail param) throws ServiceException;

	
	
	/**
	 * <pre>
	 * Certifiaction Target Phone History List
	 * </pre>
	 * 
	 * @param SearchConditionCertifyAppDetail
	 * @return List<CertifyAppHistoryList>
	 * @throws ServiceException
	 * @throws SQLException
	 */
	public List<CertifySupportPhone> findCertifyHistoryTargetPhoneList(SearchConditionCertifyAppDetail param) throws ServiceException;

	
	/**
	 * <pre>
	 * Certifiaction Test Case List
	 * </pre>
	 * 
	 * @param CerfifyTargetTestCase
	 * @return List<CertifyTestCase>
	 * @throws ServiceException
	 * @throws SQLException
	 */
	public List<CertifyTestCase> findCertifyTestCaseList(CerfifyTargetTestCase param) throws ServiceException;	
}
