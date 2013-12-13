package com.omp.admin.certify.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import com.omp.admin.adminmgr.auth.model.AdMgr;
import com.omp.admin.certify.model.CerfifyTestCategory;
import com.omp.admin.certify.model.CertifyAppInfomation;
import com.omp.admin.certify.model.CertifyAppSubContsKeyList;
import com.omp.admin.certify.model.CertifyAppSubInfomation;
import com.omp.admin.certify.model.CertifyCompletedInfo;
import com.omp.admin.certify.model.CertifyReportAddFile;
import com.omp.admin.certify.model.CertifySupportPhone;
import com.omp.admin.certify.model.CertifyTestCase;
import com.omp.admin.certify.model.SaveTestStatus;
import com.omp.admin.certify.model.SaveTesterAssignInfo;
import com.omp.admin.certify.model.SearchConPhoneInfo;
import com.omp.admin.certify.model.SearchConditionCertifyApp;
import com.omp.admin.certify.model.SearchConditionCertifyAppDetail;
import com.omp.admin.certify.model.SearchConditionCertifyTesterApp;
import com.omp.admin.certify.model.SearchResultCertifyAppList;
import com.omp.admin.certify.model.SearchResultCertifyTestAppList;
import com.omp.admin.certify.model.SearchResultPhoneInfoList;
import com.omp.admin.certify.model.SubAppTestResultSave;
import com.omp.admin.common.Constants;
import com.omp.commons.commcode.CacheCommCode;
import com.omp.commons.commcode.model.CommCode;
import com.omp.commons.communicate.CommunicatorFactory;
import com.omp.commons.communicate.mail.MailSendAgent;
import com.omp.commons.communicate.mail.SendMailModel;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.model.ContentMailModel;
import com.omp.commons.model.PagenateList;
import com.omp.commons.product.model.ProductPhysicalFileCreationInfo;
import com.omp.commons.product.service.ContentsHistoryService;
import com.omp.commons.product.service.ContentsHistoryServiceImpl;
import com.omp.commons.product.service.DisplayDistributeService;
import com.omp.commons.product.service.DisplayDistributeServiceImpl;
import com.omp.commons.product.service.DownloadDistributeService;
import com.omp.commons.product.service.DownloadDistributeServiceImpl;
import com.omp.commons.service.AbstractService;
import com.omp.commons.text.LocalizeMessage;
import com.omp.commons.util.ProductFileUtil;
import com.omp.commons.util.config.ConfigProperties;


/**
 * 
 * <pre>
 * Certify Application Management Service
 * </pre>
 * 
 * 
 * @author shsin
 *
 */
public class CertifyAppMgrServiceImpl extends AbstractService implements CertifyAppMgrService {
	
	private ContentsHistoryService  contentsHistoryService = null;
	
	private CertifyCompletedInfo certifyCompletedInfo = null;

	/**
	 * <pre>
	 * Application Paging List
	 * </pre>
	 * 
	 * @param SearchConditionCertifyApp
	 * @return PagenateList<SearchResultCertifyAppList>
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PagenateList<SearchResultCertifyAppList> findCertifyAppPagingList(SearchConditionCertifyApp param) throws ServiceException {
		
		PagenateList<SearchResultCertifyAppList> list = null;
		
		try {
			// 검증 대상 Application List 조회
			list = super.commonDAO.queryForPageList("certifyMgr.selectCertifyAppPagingList", param);
		
		} catch (Exception e) {
			throw new ServiceException("검증대상 리스트 조회 실패.", e);
		}
		
		return list;
	}
	
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
	@SuppressWarnings("unchecked")
	public List<CertifyAppSubContsKeyList> findCertifySubContsKeyList(SearchConditionCertifyAppDetail param) throws ServiceException {
		List<CertifyAppSubContsKeyList> list = null;
		
		try{
			// 검증 대상 Sub Contents 리스트 조회(Key List 조회)
			list = (List<CertifyAppSubContsKeyList>)super.commonDAO.queryForList("certifyMgr.selectCertifySubContsKeyList", param);
		} catch (Exception e) {
			throw new ServiceException("검증 대상 Sub Contents Key 리스트 조회 실패.", e);
		}		
		
		return list;		
	}
	
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
	public CertifyAppInfomation findCertifyAppInfomationView(SearchConditionCertifyAppDetail param) throws ServiceException {
		
		CertifyAppInfomation info = null;
		
		try{
			// 검증 대상 Application 상세 정보 조회
			info = (CertifyAppInfomation)super.commonDAO.queryForObject("certifyMgr.selectCertifyAppInfomationView", param);
		} catch (Exception e) {
			throw new ServiceException("검증 대상 Application 상세 정보 조회 실패.", e);
		}		
		
		return info;
	}
	
	
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
	public CertifyAppSubInfomation findCertifyAppSubInfomationView(SearchConditionCertifyAppDetail param) throws ServiceException {
		
		CertifyAppSubInfomation info;
		String directPassFlag = "";
		
		try{
			// 검증 대상 상품이 즉시통과 된 상품인지 테스터 할당되서 승인된 상품인지 파악한다.
			directPassFlag = (String) super.commonDAO.queryForObject("certifyMgr.selectCertifyAppDirectPassYN", param);
			
			if(directPassFlag != null && "Y".equals(directPassFlag)) {
				param.setDirectPassFlag(directPassFlag);
			}
			
			// 검증 대상 Sub Contents 상세 정보 조회
			info = (CertifyAppSubInfomation)super.commonDAO.queryForObject("certifyMgr.selectCertifyAppSubInfomationView", param);
		} catch (Exception e) {
			throw new ServiceException("검증 대상 Sub Contents 상세 정보 조회 실패.", e);
		}				
		
		return info;
	}
	
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
	@SuppressWarnings("unchecked")
	public List<CertifyAppSubInfomation> findCertifyAppSubInfomationList(SearchConditionCertifyAppDetail param) throws ServiceException {
		List<CertifyAppSubInfomation> list = null;
		
		try{
			// 검증 대상 Application 에 속한 Sub Contents List 조회
			list = (List<CertifyAppSubInfomation>)super.commonDAO.queryForList("certifyMgr.selectCertifyAppSubInfomationList", param);
		} catch (Exception e) {
			throw new ServiceException("검증대상 서브 컨탠츠 리스트 조회 실패.", e);
		}		
		
		return list;		
	}
	
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
	@SuppressWarnings("unchecked")
	public List<CertifyReportAddFile> findCertifyRefortAddFileInfomationList(SearchConditionCertifyAppDetail param) throws ServiceException {
		
		List<CertifyReportAddFile> list = null;
		
		try{
			// 검증 결과로 추가적으로 등록한 기타첨부파일 리스트 조회
			list = (List<CertifyReportAddFile>)super.commonDAO.queryForList("certifyMgr.selectCertifyRefortAddFileInfomationList", param);
		} catch (Exception e) {
			throw new ServiceException("검증결과 추가 등록 첨부파일 리스크 조회 실패.", e);
		}		
		
		return list;
	}
	
	
	/**
	 * <pre>
	 * Certification Tester List
	 * </pre>
	 * 
	 * @return List<AdMgr>
	 * @throws ServiceException
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<AdMgr> findCertifyTesterList() throws ServiceException {
		List<AdMgr> list = null;
		
		try{
			// Tester List 조회
			list = (List<AdMgr>)super.commonDAO.queryForList("certifyMgr.selectCertifyTesterList");
		} catch (Exception e) {
			throw new ServiceException("테스터 목록 조회 실패.", e);
		}		
		
		return list;		
	}
	
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
	public boolean saveTesterAssign(SaveTesterAssignInfo param) throws ServiceException {
		
		boolean isSaved = false;
		
		int saveResult = -1;
		
		try{
			
			/**
			 * Test 할당 처리
			 * 1. TBL_CT_SPRT_PHONE
			 *    => 검증 대상 Device 지정을 위한 업데이트
			 * 
			 * 2. TBL_CT_CONTS
			 *    => Tester, 검증중 등 정보 업데이트
			 *    
			 * 3. TBL_PD_CONTS
			 *    => 검증중 상태로 업데이트
			 *    
			 * 4. TBL_CT_TARGET_TEST_CTG
			 *    => Test Category 에 속한 Test CASE List 들로 업데이트
			 */
			
			
			CertifyAppInfomation certifyAppInfomation = new CertifyAppInfomation();
			
			certifyAppInfomation.setTesterId(param.getTesterId());
			certifyAppInfomation.setCtEndExDt(param.getCtEndExDt());
			certifyAppInfomation.setTestMemo(param.getTestMemo());
			certifyAppInfomation.setUpdBy(param.getLoginId());
			certifyAppInfomation.setCid(param.getCid());
			certifyAppInfomation.setVerifyReqVer(param.getVerifyReqVer());
			
			CertifySupportPhone[] certifySupportPhones = param.getPhoneInfo();
			
			String[] ctCtgSeqs = param.getCtCtgSeq();
			
			
			super.daoManager.startTransaction();
			
			if(certifySupportPhones == null || certifySupportPhones.length <= 0) {
				throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.");
			}
			
			for (CertifySupportPhone certifySupportPhone : certifySupportPhones) {
				
				certifySupportPhone.setUpdBy(param.getLoginId());
				certifySupportPhone.setVerifyReqVer(param.getVerifyReqVer());
				
				saveResult = super.commonDAO.update("certifyMgr.updateCertifyTargetPhone", certifySupportPhone);
				
				if(saveResult <= 0){
					throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.");
				}				
			}
		
			saveResult = super.commonDAO.update("certifyMgr.updateTesterAssign", certifyAppInfomation);
			
			if(saveResult <= 0){
				throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.");
			}
			
			saveResult = super.commonDAO.update("certifyMgr.updateCertifyIng", certifyAppInfomation);
			
			if(saveResult <= 0){
				throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.");
			}

			CerfifyTestCategory info = new CerfifyTestCategory();
			
			info.setInsBy(param.getLoginId());
			info.setVerifyReqVer(param.getVerifyReqVer());				
			info.setCid(param.getCid());
			info.setCtCtgSeqs(ctCtgSeqs);
			
			super.commonDAO.insert("certifyMgr.insertCertifyTargetTestCtg", info);
			
			super.daoManager.commitTransaction();
			
			contentsHistoryService = new ContentsHistoryServiceImpl();
			
			// History Table Insert   <-- TBL_PD_CONTS 테이블 변경될 떄마다
		    contentsHistoryService.insertContsHis(certifyAppInfomation.getCid());
			
			isSaved = true;
			
			return isSaved;
			
		} catch (ServiceException se){
			throw se;
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		} finally{
			super.daoManager.endTransaction();
		}
	}
	
	
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
	@SuppressWarnings("unchecked")
	public List<CertifyTestCase> findCertifyTestCaseInfomationList(SearchConditionCertifyAppDetail param) throws ServiceException {
		
		List<CertifyTestCase> list = null;
		
		try{
			// Test Category 할당을 위한 Test Category List 조회
			list = (List<CertifyTestCase>)super.commonDAO.queryForList("certifyMgr.selectCertifyTestCaseInfomationList", param);
		} catch (Exception e) {
			throw new ServiceException("테스트 카테고리 목록 조회 실패.", e);
		}		
		
		return list;
	}

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
	public boolean saveSubTestComplete(SubAppTestResultSave param, Properties confProps) throws ServiceException{
		
		boolean isSaved = false;
		
		int saveResult = -1;
		
		ProductPhysicalFileCreationInfo productPhysicalFileCreationInfo = null;
		
		try{
			
			/**
			 * Tester 의 서브 컨텐츠 Test 완료 처리
			 * 1. TBL_CT_SUB_CONTS : 검증 내역 업데이트
			 * 2. TBL_CT_ADD_FILE : 검증 추가 파일 업데이트
			 */
			
			
			super.daoManager.startTransaction();
			
			CommCode commCode= CacheCommCode.getCommCodeByFullCode(param.getVmType());

			String appCtResultPhysicalPath = "";
			
			// App 검증 결과서 파일 업로드
			if(param.getAppCtResult() != null){
				
				productPhysicalFileCreationInfo = new ProductPhysicalFileCreationInfo();
				productPhysicalFileCreationInfo.setAddPrefixName("");
				productPhysicalFileCreationInfo.setConfProps(confProps);
				productPhysicalFileCreationInfo.setLimitSize(10485760L);
				productPhysicalFileCreationInfo.setSourceFileName(param.getAppCtResultFileName());
				productPhysicalFileCreationInfo.setUploadedFile(param.getAppCtResult());
				productPhysicalFileCreationInfo.setUploadJobType(ProductFileUtil.UPLOAD_JOB_TYPE_CERTIFY_REPORT_SUB_DOC);
				productPhysicalFileCreationInfo.setCid(param.getCid());
				productPhysicalFileCreationInfo.setScid(param.getScid());
				productPhysicalFileCreationInfo.setVmTypeName(commCode.getCdNm());
			
				appCtResultPhysicalPath = ProductFileUtil.changePhsicalFilePath(productPhysicalFileCreationInfo);
			}else{
				
			}
			
			CertifyAppSubInfomation certifyAppSubInfomation = new CertifyAppSubInfomation();
			
			certifyAppSubInfomation.setAppCtResultFile(appCtResultPhysicalPath);
			certifyAppSubInfomation.setAppCtResultFileNm(param.getAppCtResultFileName());
			certifyAppSubInfomation.setAppCtCmt(param.getAppCtCmt());
			certifyAppSubInfomation.setUpdBy(param.getLoginId());
			certifyAppSubInfomation.setAgrmntStat(param.getAgrmntStat());
			certifyAppSubInfomation.setScid(param.getScid());
			certifyAppSubInfomation.setVerifyReqVer(param.getVerifyReqVer());
			
			saveResult = super.commonDAO.update("certifyMgr.updateCertifySubContentsResultReport", certifyAppSubInfomation);
			
			if(saveResult <= 0){
				throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.");
			}	
			
			CertifyReportAddFile certifyReportAddFile = null;

			certifyReportAddFile = new CertifyReportAddFile();
			
			certifyReportAddFile.setScid(param.getScid());
			certifyReportAddFile.setVerifyReqVer(param.getVerifyReqVer());
			
			super.commonDAO.update("certifyMgr.deleteCertifySubContentsResultEtcReport", certifyReportAddFile);
			
			// 기타1 첨부 파일 업로드
			if(param.getAddFile1() != null){

				productPhysicalFileCreationInfo = new ProductPhysicalFileCreationInfo();
				productPhysicalFileCreationInfo.setAddPrefixName("1_");
				productPhysicalFileCreationInfo.setConfProps(confProps);
				productPhysicalFileCreationInfo.setLimitSize(10485760L);
				productPhysicalFileCreationInfo.setSourceFileName(param.getAddFile1FileName());
				productPhysicalFileCreationInfo.setUploadedFile(param.getAddFile1());
				productPhysicalFileCreationInfo.setUploadJobType(ProductFileUtil.UPLOAD_JOB_TYPE_CERTIFY_REPORT_SUB_ETC_DOC);	
				productPhysicalFileCreationInfo.setCid(param.getCid());
				productPhysicalFileCreationInfo.setScid(param.getScid());
				productPhysicalFileCreationInfo.setVmTypeName(commCode.getCdNm());			
				
				
				String addFile1PhysicalPath = ProductFileUtil.changePhsicalFilePath(productPhysicalFileCreationInfo);
				
				certifyReportAddFile = new CertifyReportAddFile();
				
				certifyReportAddFile.setInsBy(param.getLoginId());
				certifyReportAddFile.setScid(param.getScid());
				certifyReportAddFile.setVerifyReqVer(param.getVerifyReqVer());
				certifyReportAddFile.setAddFile(addFile1PhysicalPath);
				certifyReportAddFile.setAddFileNm(param.getAddFile1FileName());
				
				super.commonDAO.insert("certifyMgr.insertCertifySubContentsResultEtcReport", certifyReportAddFile);
			}
			
			// 기타2 첨부 파일 업로드
			if(param.getAddFile2() != null){
				
				productPhysicalFileCreationInfo = new ProductPhysicalFileCreationInfo();
				productPhysicalFileCreationInfo.setAddPrefixName("2_");
				productPhysicalFileCreationInfo.setConfProps(confProps);
				productPhysicalFileCreationInfo.setLimitSize(10485760L);
				productPhysicalFileCreationInfo.setSourceFileName(param.getAddFile2FileName());
				productPhysicalFileCreationInfo.setUploadedFile(param.getAddFile2());
				productPhysicalFileCreationInfo.setUploadJobType(ProductFileUtil.UPLOAD_JOB_TYPE_CERTIFY_REPORT_SUB_ETC_DOC);	
				productPhysicalFileCreationInfo.setCid(param.getCid());
				productPhysicalFileCreationInfo.setScid(param.getScid());
				productPhysicalFileCreationInfo.setVmTypeName(commCode.getCdNm());			
				
				
				String addFile2PhysicalPath = ProductFileUtil.changePhsicalFilePath(productPhysicalFileCreationInfo);
				
				certifyReportAddFile = new CertifyReportAddFile();
				
				certifyReportAddFile.setInsBy(param.getLoginId());
				certifyReportAddFile.setScid(param.getScid());
				certifyReportAddFile.setVerifyReqVer(param.getVerifyReqVer());
				certifyReportAddFile.setAddFile(addFile2PhysicalPath);
				certifyReportAddFile.setAddFileNm(param.getAddFile2FileName());
				
				super.commonDAO.insert("certifyMgr.insertCertifySubContentsResultEtcReport", certifyReportAddFile);
			}
			
			// 기타3 첨부 파일 업로드
			if(param.getAddFile3() != null){
				
				productPhysicalFileCreationInfo = new ProductPhysicalFileCreationInfo();
				productPhysicalFileCreationInfo.setAddPrefixName("3_");
				productPhysicalFileCreationInfo.setConfProps(confProps);
				productPhysicalFileCreationInfo.setLimitSize(10485760L);
				productPhysicalFileCreationInfo.setSourceFileName(param.getAddFile3FileName());
				productPhysicalFileCreationInfo.setUploadedFile(param.getAddFile3());
				productPhysicalFileCreationInfo.setUploadJobType(ProductFileUtil.UPLOAD_JOB_TYPE_CERTIFY_REPORT_SUB_ETC_DOC);
				productPhysicalFileCreationInfo.setCid(param.getCid());
				productPhysicalFileCreationInfo.setScid(param.getScid());
				productPhysicalFileCreationInfo.setVmTypeName(commCode.getCdNm());						
				
				String addFile3PhysicalPath = ProductFileUtil.changePhsicalFilePath(productPhysicalFileCreationInfo);		
				
				certifyReportAddFile = new CertifyReportAddFile();
				
				certifyReportAddFile.setInsBy(param.getLoginId());
				certifyReportAddFile.setScid(param.getScid());
				certifyReportAddFile.setVerifyReqVer(param.getVerifyReqVer());
				certifyReportAddFile.setAddFile(addFile3PhysicalPath);
				certifyReportAddFile.setAddFileNm(param.getAddFile3FileName());
				
				super.commonDAO.insert("certifyMgr.insertCertifySubContentsResultEtcReport", certifyReportAddFile);
			}
			
			// 기타4 첨부 파일 업로드
			if(param.getAddFile4() != null){
				
				productPhysicalFileCreationInfo = new ProductPhysicalFileCreationInfo();
				productPhysicalFileCreationInfo.setAddPrefixName("4_");
				productPhysicalFileCreationInfo.setConfProps(confProps);
				productPhysicalFileCreationInfo.setLimitSize(10485760L);
				productPhysicalFileCreationInfo.setSourceFileName(param.getAddFile4FileName());
				productPhysicalFileCreationInfo.setUploadedFile(param.getAddFile4());
				productPhysicalFileCreationInfo.setUploadJobType(ProductFileUtil.UPLOAD_JOB_TYPE_CERTIFY_REPORT_SUB_ETC_DOC);	
				productPhysicalFileCreationInfo.setCid(param.getCid());
				productPhysicalFileCreationInfo.setScid(param.getScid());
				productPhysicalFileCreationInfo.setVmTypeName(commCode.getCdNm());					

				String addFile4PhysicalPath = ProductFileUtil.changePhsicalFilePath(productPhysicalFileCreationInfo);				
				
				certifyReportAddFile = new CertifyReportAddFile();
				
				certifyReportAddFile.setInsBy(param.getLoginId());
				certifyReportAddFile.setScid(param.getScid());
				certifyReportAddFile.setVerifyReqVer(param.getVerifyReqVer());
				certifyReportAddFile.setAddFile(addFile4PhysicalPath);
				certifyReportAddFile.setAddFileNm(param.getAddFile4FileName());
				
				super.commonDAO.insert("certifyMgr.insertCertifySubContentsResultEtcReport", certifyReportAddFile);
			}			
			
			super.daoManager.commitTransaction();
			
			isSaved = true;
			
			return isSaved;
			
		} catch (ServiceException se){
			throw se;
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		} finally{
			super.daoManager.endTransaction();
		}
		
		
	}

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
	@SuppressWarnings("unchecked")
	public PagenateList<SearchResultCertifyTestAppList> findCertifyTestPagingList(SearchConditionCertifyTesterApp param) throws ServiceException {
		
		PagenateList<SearchResultCertifyTestAppList> list = null;
		
		try {
			// Tester 에게 할당된 검증 대상 Application List 조회
			list = super.commonDAO.queryForPageList("certifyMgr.selectCertifyTestPagingList", param);
		
		} catch (Exception e) {
			throw new ServiceException("테스터 할당 검증 대상 목록 조회 실패.", e);
		}
		
		return list;
	}	
	

	


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
	@SuppressWarnings("unchecked")
	public List<SearchResultPhoneInfoList> findPhoneList(SearchConPhoneInfo param) throws ServiceException {

		List<SearchResultPhoneInfoList> list = null;
		
		try{
			// LCD Size 별 지원 단말 리스트 조회
			list = (List<SearchResultPhoneInfoList>)super.commonDAO.queryForList("certifyMgr.selectPhoneList", param);
		} catch (Exception e) {
			throw new ServiceException("지원 단말 리스트 조회 실패.", e);
		}		
		
		return list;		
	}
	
	
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
	public boolean updateTestIng(SaveTestStatus param) throws ServiceException {
		
		boolean isSaved = false;
		
		int saveResult = -1;
		
		try{
			super.daoManager.startTransaction();

			// Tester 의 Test 진행 
			saveResult = super.commonDAO.update("certifyMgr.updateTestIng", param);
			
			if(saveResult <= 0){
				throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.");
			}				
			
			super.daoManager.commitTransaction();
			
			isSaved = true;
			
			return isSaved;
			
		} catch (ServiceException se){
			throw se;
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		} finally{
			super.daoManager.endTransaction();
		}
	}	
	
	
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
	public String findTestEndCheck(SaveTestStatus saveTestStatus) throws ServiceException {

		String testEndCheck = "N";
		
		try{
			
			
			/**
			 * Tester 의 Test 종료가 가능한지 체크
			 * => TBL_CT_SUB_CONTS 에 대해서 승인/반려 처리 하지 않은 내역이 있는지 체크 
			 */
			
			testEndCheck = (String)super.commonDAO.queryForObject("certifyMgr.selectTestEndCheck", saveTestStatus);
		} catch (Exception e) {
			throw new ServiceException("테스터의 미처리 유무 확인 실패.", e);
		}		
		
		return testEndCheck;		
	}	
	
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
	public boolean updateTestEnd(SaveTestStatus param) throws ServiceException {
		
		boolean isSaved = false;
		
		int saveResult = -1;
		
		try{
			super.daoManager.startTransaction();

			// Tester 의 Test 종료
			saveResult = super.commonDAO.update("certifyMgr.updateTestEnd", param);
			
			if(saveResult <= 0){
				throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.");
			}				
			
			super.daoManager.commitTransaction();
			
			isSaved = true;
			
			return isSaved;
			
		} catch (ServiceException se){
			throw se;
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		} finally{
			super.daoManager.endTransaction();
		}
	}	
	
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
	public boolean updateTestReject(SaveTestStatus param) throws ServiceException {
		
		boolean isSaved = false;
		
		int saveResult = -1;
		
		try{
			super.daoManager.startTransaction();
			
			// 검증결과 반려 처리
			saveResult = super.commonDAO.update("certifyMgr.updateTestReject", param);
			
			if(saveResult <= 0){
				throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.");
			}				
			
			super.daoManager.commitTransaction();
			
			isSaved = true;
			
			return isSaved;
			
		} catch (ServiceException se){
			throw se;
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		} finally{
			super.daoManager.endTransaction();
		}
	}	
	
	
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
	public boolean saveCertifyPass(SaveTestStatus param, HttpServletRequest req) throws ServiceException{
		
		boolean isSaved = false;
		
		int saveResult = -1;
		
		try{
			super.daoManager.startTransaction();
			
			/**
			 * 검증 통과 처리
			 * 1. TBL_CT_CONTS
			 *    => 검증 완료 처리
			 * 2. TBL_CT_SUB_CONTS
			 *    => 승인 처리
			 */
			
			
			saveResult = super.commonDAO.update("certifyMgr.updateCertifyPassByConts", param);
			
			if(saveResult <= 0){
				throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.");
			}
			
			
			saveResult = super.commonDAO.update("certifyMgr.updateCertifyPassBySubConts", param);
			
			// 검증상품이 최초 승인일경우 최초승인일자 Update
			updateProductFirstAgrmntDt(param);
			
			if(saveResult <= 0){
				throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.");
			}							
			
			// 상품 업데이트, DP 배포, DD 배포
			updateProductInfoByCertifyComplete(param);
			
			// 검증결과 메일 발송
			certificationResultMailSend(param, req);
			
			super.daoManager.commitTransaction();
			
			// 상품 History insert or update
			contentsHistoryComplete(param);
			
			isSaved = true;
			
			return isSaved;
			
		} catch (ServiceException se){
			throw se;
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		} finally{
			super.daoManager.endTransaction();
		}
	}	
	
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
	public boolean updateCertifyAgreement(SaveTestStatus param, HttpServletRequest req) throws ServiceException {
		
		boolean isSaved = false;
		
		int saveResult = -1;
		
		try{
			
			super.daoManager.startTransaction();
			
			// 검증처리 결과 승인 처리
			saveResult = super.commonDAO.update("certifyMgr.updateCertifyAgreement", param);
			
			// 검증상품이 최초 승인일경우 최초승인일자 Update
			updateProductFirstAgrmntDt(param);
			
			if(saveResult <= 0){
				throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.");
			}				
			
			// 상품 업데이트, DP 배포, DD 배포
			updateProductInfoByCertifyComplete(param);
			
			// 검증결과 메일 발송
			certificationResultMailSend(param, req);
			
			super.daoManager.commitTransaction();
			
			// 상품 History insert or update
			contentsHistoryComplete(param);
			
			isSaved = true;
			
			return isSaved;
			
		} catch (ServiceException se){
			throw se;
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		} finally{
			super.daoManager.endTransaction();
		}
	}
	
	/**
	 * <pre>
	 * Update Product FirstAgrmntDt
	 * </pre>
	 * 
	 * @param param
	 * @throws ServiceException
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes" })
	private void updateProductFirstAgrmntDt(SaveTestStatus param) 
		throws ServiceException, Exception {
		
		int updateResult = -1;
		
		try {
			// 검증상품이 최초승인 상품인지 조회
			HashMap result = (HashMap) super.commonDAO.queryForObject("certifyMgr.selectFirstAgrmntCount", param);
			
			if(log.isDebugEnabled()) { 
				log.debug("count :: " + String.valueOf(result.get("COUNT"))); 
				log.debug("AGRMNTSTAT :: " + (String)result.get("AGRMNTSTAT"));
			}
			
			// 최초승인 상품일시에 상품 최초승인일자 Update
			if("1".equals(String.valueOf(result.get("COUNT"))) && Constants.AGREEMENT_STATUS_AGREE.equals((String)result.get("AGRMNTSTAT"))) {
				updateResult = super.commonDAO.update("certifyMgr.updateProductFirstAgrmntDt", param);
				
				if(updateResult <= 0) {
					throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.");
				}
				
				contentsHistoryService = new ContentsHistoryServiceImpl();
				
				// History Table Insert   <-- TBL_PD_CONTS 테이블 변경될 떄마다
			     contentsHistoryService.insertContsHis(param.getCid());
			}
		} catch (ServiceException se){
			throw se;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}
	}
	
	/**
	 * <pre>
	 * Certification Result Mail Send 
	 * </pre>
	 * 
	 * @param SaveTestStatus param
	 * @param HttpServletRequest req
	 */
	private void certificationResultMailSend(SaveTestStatus param, HttpServletRequest req)
		throws ServiceException {
		
		/**
		 * 상품 검증결과 메일발송
		 * 
		 * 1. 메일발송 기본 데이터 조회
		 *   (개발자ID, 상품명, 검증요청일, 검증완료일, 승인상태)
		 * 2. ContentsMailModel에 개발자 사이트 메인 URL Set
		 * 3. SendMailModel Set
		 * 4. 메일발송 
		 */
		
		try {
			SendMailModel mail;
			MailSendAgent msa;
			ContentMailModel contentMailModel = (ContentMailModel) super.commonDAO.queryForObject("crtifyMgr.selectCtResultMailSendData", param);
//			ConfigProperties conf = new ConfigProperties();
//			String rtnUrl = conf.getString("omp.common.url-prefix.http.dev") + req.getContextPath() + "/main/main.omp";
			
//			contentMailModel.setMainUrl(rtnUrl);
			ConfigProperties	conf	= new ConfigProperties();
			
			mail = new SendMailModel();
			mail.setTemplateId("/DEV/PD_001.html");
			mail.setRefAppId("certifyAppMgrAction.saveAgreeStatusByAjax");
			mail.setSubject(LocalizeMessage.getLocalizedMessage("[Whoopy 개발자센터] 회원님께서 요청하신 상품검증 결과 안내입니다."));
			mail.setRefDataId(param.getCid());
			mail.setToAddr(contentMailModel.getDevEmailAddr());
			mail.setContentData(contentMailModel);
			mail.setFromAddr(conf.getString("omp.admin.punish.mail.from.addr"));
			mail.setFromName(conf.getString("omp.admin.punish.mail.from.name"));
			mail.setReturnAddr(conf.getString("omp.admin.punish.mail.return.addr"));
			msa = CommunicatorFactory.getMailSendAgent();
			
			msa.requestMailSend(mail);
			
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}
	}

	/**
	 * <pre>
	 * Update Product Info By Certification Completed
	 * </pre>
	 * 
	 * @param SaveTestStatus
	 * @throws SQLException
	 */
	private void updateProductInfoByCertifyComplete(SaveTestStatus param)
			throws ServiceException, Exception {
		
		
		int saveResult = -1;
		
		/**
		 * TBL_CT 검증완료 처리 후 상품 상태 정보 조회 및 검증 완료 정보 조회
		 */
		
		certifyCompletedInfo = (CertifyCompletedInfo)super.commonDAO.queryForObject("certifyMgr.selectCertifyAgreementInfo", param);
		
		if(certifyCompletedInfo == null){
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.");
		}
		
		
		/**
		 * 
		 * 전제 조건 : TBL_CT 검증완료 처리 후에 처리
		 * 
		 * 검증 승인 상태가 승인 일 경우만
		 *
		 * 1. TBL_PD_SALE_STAT_HIS : 판매상태 업데이트
		 *    => 최초검증 일 경우만 업데이트
		 *    
		 * 2. DD, DP Full 배포
		 *    => 최초검증 : DD Full 배포
		 *    => 판매중 상태에서의 재 검증 : DD Full, DP Full 배포 
		 *
		 * 3. TBL_PD_CONTS : 검증 완료 및 승인 처리
		 *    =>  최초검증 일 경우 : 판매상태는 판매대기
		 *        그외의 경우 판매상태의 변경은 없음.
		 *    
		 * 4. TBL_PD_CONTS_UPDATE : 검증완료 처리   
		 *    => 재검증 요청인 경우에 대해서만
		 *       단 재검증 사유가 단말삭제, 단말추가인 경우에 대해서는 Skip
		 *    
		 *    
		 */
		
		if(Constants.AGREEMENT_STATUS_AGREE.equals(certifyCompletedInfo.getAgrmntStat())){
			
			saveResult = super.commonDAO.update("certifyMgr.updateProductByCertifyAgreement", param);
			
			if(saveResult <= 0){
				throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.");
			}
			
			if(Constants.CONTENT_SALE_STAT_ING.equals(certifyCompletedInfo.getCurSaleStat())){
				
				// Download distribution
				DownloadDistributeService downloadDistributeService = new DownloadDistributeServiceImpl();
				
				try{
					downloadDistributeService.ddDeployContents(param.getCid(), false);
				}catch(Exception ex){
					throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", ex);
				}				
				
				if("Y".equals(certifyCompletedInfo.getReCertifyReqYn())){
				
					// Display distribution
					DisplayDistributeService displayDistributeService = new DisplayDistributeServiceImpl();
					
					try{
						displayDistributeService.dpDeployContents(param.getCid(), false);
					}catch(Exception ex){
						throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", ex);
					}
				}
			}else{
				// Download distribution
				DownloadDistributeService downloadDistributeService = new DownloadDistributeServiceImpl();
				
				try{
					downloadDistributeService.ddDeployContents(param.getCid(), false);
				}catch(Exception ex){
					throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", ex);
				}				
			}
		} else {
			saveResult = super.commonDAO.update("certifyMgr.updateProductByCertifyAgreementReject", param);
			
			if(saveResult <= 0){
				throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.");
			}
		}
	}
	
	/**
	 * Contents History insert or update
	 * 
	 * @param param
	 * @throws ServiceException
	 * @throws Exception
	 */
	private void contentsHistoryComplete(SaveTestStatus param)
		throws ServiceException, Exception {

		try {
			if(Constants.AGREEMENT_STATUS_AGREE.equals(certifyCompletedInfo.getAgrmntStat())){
				int saveResult = -1;
			    
			    contentsHistoryService = new ContentsHistoryServiceImpl();
				
				// History Table Insert   <-- TBL_PD_CONTS 테이블 변경될 떄마다
			    contentsHistoryService.insertContsHis(param.getCid()); 
				
				if(Constants.CONTENT_SALE_STAT_NA.equals(certifyCompletedInfo.getCurSaleStat())){
					
					// PD Sale Stat history  <-- TBL_PD_CONTS 의 SALE_STAT 의 값이 변경될 때마다
				    contentsHistoryService.insertSaleStatHis(param.getCid(), Constants.CONTENT_SALE_STAT_WAIT, param.getUpdBy(), true);
		
					saveResult = super.commonDAO.update("certifyMgr.updateProudctUpdateHistory", param);
					
					if(saveResult <= 0){
						// Skip
					}
				}
				
				if(Constants.CONTENT_SALE_STAT_ING.equals(certifyCompletedInfo.getCurSaleStat())) {
					
					// PD Sale Stat history  <-- TBL_PD_CONTS 의 SALE_STAT 의 값이 변경될 때마다
				    contentsHistoryService.insertSaleStatHis(param.getCid(), Constants.CONTENT_SALE_STAT_ING, param.getUpdBy(), true);
				    
				}
				
				if("Y".equals(certifyCompletedInfo.getReCertifyReqYn())){
					if(Constants.VERIFY_COMMENT_CD_DEVICE_ADD.equals(certifyCompletedInfo.getVerifyCommentCd())
							|| Constants.VERIFY_COMMENT_CD_DEVICE_DEL.equals(certifyCompletedInfo.getVerifyCommentCd())){
						// Skip
					}else{
						super.commonDAO.insert("certifyMgr.insertProudctSaleStatusHistory", param);
					}
				}
			}
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}
	}
}
