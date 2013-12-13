package com.omp.admin.certify.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import com.omp.admin.adminmgr.auth.model.AdMgr;
import com.omp.admin.certify.model.CertifyAppInfomation;
import com.omp.admin.certify.model.CertifyAppSubContsKeyList;
import com.omp.admin.certify.model.CertifyAppSubInfomation;
import com.omp.admin.certify.model.CertifyReportAddFile;
import com.omp.admin.certify.model.CertifyTestCase;
import com.omp.admin.certify.model.SaveTestStatus;
import com.omp.admin.certify.model.SearchConPhoneInfo;
import com.omp.admin.certify.model.SearchConditionCertifyApp;
import com.omp.admin.certify.model.SearchConditionCertifyAppDetail;
import com.omp.admin.certify.model.SearchConditionCertifyTesterApp;
import com.omp.admin.certify.model.SearchResultCertifyAppList;
import com.omp.admin.certify.model.SearchResultCertifyTestAppList;
import com.omp.admin.certify.model.SearchResultPhoneInfoList;
import com.omp.admin.certify.model.SaveTesterAssignInfo;
import com.omp.admin.certify.model.SubAppTestResultSave;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.model.PagenateList;

/**
 * 
 * <pre>
 * Certify Application Management Service Interface
 * </pre>
 * 
 * 
 * @author shsin
 *
 */
public interface CertifyAppMgrService {

	/**
	 * <pre>
	 * Application Paging List
	 * </pre>
	 * 
	 * @param SearchConditionCertifyApp
	 * @return PagenateList<SearchResultCertifyAppList>
	 * @throws ServiceException
	 */
	public PagenateList<SearchResultCertifyAppList> findCertifyAppPagingList(SearchConditionCertifyApp param) throws ServiceException;
	
	
	/**
	 * <pre>
	 * Certification Application Sub Contents Key List
	 * </pre>
	 * 
	 * @param SearchConditionCertifyAppDetail
	 * @return List<CertifyAppSubContsKeyList>
	 * @throws ServiceException
	 * @throws SQLException
	 */
	public List<CertifyAppSubContsKeyList> findCertifySubContsKeyList(SearchConditionCertifyAppDetail param) throws ServiceException;	
	
	/**
	 * <pre>
	 * Certification Application Infomation View
	 * </pre>
	 * 
	 * @param SearchConditionCertifyAppDetail
	 * @return CertifyAppInfomation
	 * @throws ServiceException
	 * @throws SQLException
	 */
	public CertifyAppInfomation findCertifyAppInfomationView(SearchConditionCertifyAppDetail param) throws ServiceException;
	
	
	/**
	 * <pre>
	 * Certification Application Sub Infomation View
	 * </pre>
	 * 
	 * @param SearchConditionCertifyAppDetail
	 * @return CertifyAppSubInfomation
	 * @throws ServiceException
	 * @throws SQLException
	 */
	public CertifyAppSubInfomation findCertifyAppSubInfomationView(SearchConditionCertifyAppDetail param) throws ServiceException;
	
	
	/**
	 * <pre>
	 * Certification Application Sub Infomation List
	 * </pre>
	 * 
	 * @param SearchConditionCertifyAppDetail
	 * @return List<CertifyAppSubInfomation>
	 * @throws ServiceException
	 * @throws SQLException
	 */
	public List<CertifyAppSubInfomation> findCertifyAppSubInfomationList(SearchConditionCertifyAppDetail param) throws ServiceException;	
	
	/**
	 * <pre>
	 * Certification Report Add File Infomation List
	 * </pre>
	 * 
	 * @param SearchConditionCertifyAppDetail
	 * @return List<CertifyReportAddFile>
	 * @throws ServiceException
	 * @throws SQLException
	 */
	public List<CertifyReportAddFile> findCertifyRefortAddFileInfomationList(SearchConditionCertifyAppDetail param) throws ServiceException;
	
	/**
	 * <pre>
	 * Certification Tester List
	 * </pre>
	 * 
	 * @return List<AdMgr>
	 * @throws ServiceException
	 * @throws SQLException
	 */
	public List<AdMgr> findCertifyTesterList() throws ServiceException;	
	
	/**
	 * <pre>
	 * Certification Tester Assign Info Update
	 * </pre>
	 * 
	 * @param SaveTesterAssignInfo
	 * @return boolean
	 * @throws ServiceException
	 * @throws SQLException
	 */
	public boolean saveTesterAssign(SaveTesterAssignInfo param) throws ServiceException;
	
	/**
	 * <pre>
	 * Certification Test Case List
	 * </pre>
	 * 
	 * @param SearchConditionCertifyAppDetail
	 * @return List<CertifyTestCase>
	 * @throws ServiceException
	 * @throws SQLException
	 */
	public List<CertifyTestCase> findCertifyTestCaseInfomationList(SearchConditionCertifyAppDetail param) throws ServiceException;

	
	/**
	 * <pre>
	 * Test Commplete
	 * </pre>
	 * 
	 * @param SubAppTestResultSave
	 * @param Properties
	 * @return boolean
	 * @throws ServiceException
	 */
	public boolean saveSubTestComplete(SubAppTestResultSave param, Properties confProps) throws ServiceException;
	
	/**
	 * <pre>
	 * Certifiaction Tester List
	 * </pre>
	 * 
	 * @param SearchConditionCertifyTesterApp
	 * @return PagenateList<SearchResultCertifyAppList>
	 * @throws ServiceException
	 * @throws SQLException
	 */
	public PagenateList<SearchResultCertifyTestAppList> findCertifyTestPagingList(SearchConditionCertifyTesterApp param) throws ServiceException;	
	
	
	/**
	 * <pre>
	 * Phone List
	 * </pre>
	 * 
	 * @param SearchConPhoneInfo
	 * @return List<SearchResultPhoneInfoList>
	 * @throws ServiceException
	 * @throws SQLException
	 */
	public List<SearchResultPhoneInfoList> findPhoneList(SearchConPhoneInfo param) throws ServiceException;
	
	
	/**
	 * <pre>
	 * Certification Test Ing Update
	 * </pre>
	 * 
	 * @param SaveTestStatus
	 * @return boolean
	 * @throws ServiceException
	 * @throws SQLException
	 */
	public boolean updateTestIng(SaveTestStatus param) throws ServiceException;
	
	
	/**
	 * <pre>
	 * Test End Check
	 * 
	 * return value : "N"(Test end impossible), "Y"(Test end possible)
	 * 
	 * </pre>
	 * 
	 * @param SaveTestStatus
	 * @return String
	 * @throws ServiceException
	 * @throws SQLException
	 */
	public String findTestEndCheck(SaveTestStatus saveTestStatus) throws ServiceException;	
	
	
	/**
	 * <pre>
	 * Certification Test End Update
	 * </pre>
	 * 
	 * @param SaveTestStatus
	 * @return boolean
	 * @throws ServiceException
	 * @throws SQLException
	 */
	public boolean updateTestEnd(SaveTestStatus param) throws ServiceException;	
	

	/**
	 * <pre>
	 * Certification Test Reject Update
	 * </pre>
	 * 
	 * @param SaveTestStatus
	 * @return boolean
	 * @throws ServiceException
	 * @throws SQLException
	 */
	public boolean updateTestReject(SaveTestStatus param) throws ServiceException;
	
	
	/**
	 * <pre>
	 * Certification Pass
	 * </pre>
	 * 
	 * @param SaveTestStatus
	 * @return boolean
	 * @throws ServiceException
	 * @throws SQLException
	 */
	public boolean saveCertifyPass(SaveTestStatus param, HttpServletRequest req) throws ServiceException;	
	
	/**
	 * <pre>
	 * Certification Agreement Update
	 * </pre>
	 * 
	 * @param SaveTestStatus
	 * @return boolean
	 * @throws ServiceException
	 * @throws SQLException
	 */
	public boolean updateCertifyAgreement(SaveTestStatus param, HttpServletRequest req) throws ServiceException;	
}

