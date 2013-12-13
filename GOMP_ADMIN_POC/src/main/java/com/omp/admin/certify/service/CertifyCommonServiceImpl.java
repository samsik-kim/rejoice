package com.omp.admin.certify.service;

import java.sql.SQLException;
import java.util.List;

import com.omp.admin.certify.model.CerfifyTargetTestCase;
import com.omp.admin.certify.model.CertifyAppHistoryList;
import com.omp.admin.certify.model.CertifyAppSimpleInfomation;
import com.omp.admin.certify.model.CertifyAppSubSimpleInfomation;
import com.omp.admin.certify.model.CertifySupportPhone;
import com.omp.admin.certify.model.CertifyTestCase;
import com.omp.admin.certify.model.SearchConditionCertifyAppDetail;
import com.omp.admin.certify.model.SearchConditionCertifyAppHistory;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.model.PagenateList;
import com.omp.commons.service.AbstractService;


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
public class CertifyCommonServiceImpl extends AbstractService implements CertifyCommonService {

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
	@SuppressWarnings("unchecked")
	public PagenateList<CertifyAppHistoryList> findCertifyHistoryAppPagingList(SearchConditionCertifyAppHistory param) throws ServiceException {
		
		PagenateList<CertifyAppHistoryList> list = null;
		
		try {
			// 검증 대상  Application 의 검증 History List 조회
			list = (PagenateList<CertifyAppHistoryList>)super.commonDAO.queryForPageList("certifyMgr.selectCertifyHistoryAppPagingList", param);
			
			for (CertifyAppHistoryList certifyAppHistoryList : list) {
				
				param.setVerifyReqVer(certifyAppHistoryList.getVerifyReqVer());
				
				List<CertifyAppSubSimpleInfomation> binaryList = (List<CertifyAppSubSimpleInfomation>)super.commonDAO.queryForList("certifyMgr.selectCertifyHistoryAppBinaryList", param);
				
				certifyAppHistoryList.setSubContentsHistoryList(binaryList);
				
			}
		
		} catch (Exception e) {
			throw new ServiceException("검증 이력 조회 실패.", e);
		}
		
		return list;
	}
	
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
	public CertifyAppSimpleInfomation findCertifyHistoryAppInfomationView(SearchConditionCertifyAppDetail param) throws ServiceException {
		
		CertifyAppSimpleInfomation info = null;
		
		try{
			// 검증 대상  Application 의 검증 History 상세 정보 조회
			info = (CertifyAppSimpleInfomation)super.commonDAO.queryForObject("certifyMgr.selectCertifyHistoryAppInfomationView", param);
		} catch (Exception e) {
			throw new ServiceException("검증 이력 상세 조회 실패.", e);
		}				
		
		return info;
	}
	
	
	
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
	@SuppressWarnings("unchecked")
	public List<CertifySupportPhone> findCertifyHistoryTargetPhoneList(SearchConditionCertifyAppDetail param) throws ServiceException {
		
		List<CertifySupportPhone> list = null;
		
		try{
			// 검증 대상  Application 에 속한 대상 Device List 조회
			list = (List<CertifySupportPhone>)super.commonDAO.queryForList("certifyMgr.selectCertifyHistoryTargetPhoneList", param);
		} catch (Exception e) {
			throw new ServiceException("검증 대상 지원 단말기 목록 조회 실패.", e);
		}		
		
		return list;
	}
	
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
	@SuppressWarnings("unchecked")
	public List<CertifyTestCase> findCertifyTestCaseList(CerfifyTargetTestCase param) throws ServiceException {
		
		List<CertifyTestCase> list = null;
		
		try{
			// Test Category 에 해당하는 Test Case List 조회
			list = (List<CertifyTestCase>)super.commonDAO.queryForList("certifyMgr.selectCertifyTestCaseList", param);
		} catch (Exception e) {
			throw new ServiceException("테스트 케이스 리스트 조회 실패.", e);
		}		
		
		return list;
	}	
	
}
