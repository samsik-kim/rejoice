package com.omp.admin.certify.action;

import java.util.ArrayList;
import java.util.List;

import com.omp.admin.certify.model.CerfifyTargetTestCase;
import com.omp.admin.certify.model.CertifyAppHistoryList;
import com.omp.admin.certify.model.CertifyAppSimpleInfomation;
import com.omp.admin.certify.model.CertifySupportPhone;
import com.omp.admin.certify.model.CertifyTestCase;
import com.omp.admin.certify.model.SearchConditionCertifyAppDetail;
import com.omp.admin.certify.model.SearchConditionCertifyAppHistory;
import com.omp.admin.certify.service.CertifyCommonService;
import com.omp.admin.certify.service.CertifyCommonServiceImpl;
import com.omp.commons.action.BaseAction;

/**
 * <pre>
 * Certify Common Action
 * </pre>
 * 
 * @author shsin
 *
 */
@SuppressWarnings("serial")
public class CertifyCommonAction extends BaseAction {
	
	private SearchConditionCertifyAppHistory searchCon;
	
	private List<CertifyAppHistoryList> list;
	
	private SearchConditionCertifyAppDetail searchDetailCon;
	
	private CertifyAppSimpleInfomation appInfo;
	
	private List<CertifySupportPhone> targetPhoneList;
	
	private List<CertifySupportPhone> sourcePhoneList;
	
	private CerfifyTargetTestCase searchTestCaseCon;
	
	private List<CertifyTestCase> testCaseList;
	
	private CertifyCommonService service;
	
	/**
	 * Certify Common Action
	 */
	public CertifyCommonAction(){
		service = new CertifyCommonServiceImpl();
	}
	
	
	/**
	 * Certification History List
	 * </pre>
	 * @return String
	 * @throws Exception
	 */
	public String findCertifyHistoryAppPagingList() throws Exception{
		
		/**
		 * 검증 대상 Application 에 대한 검증 History List 조회
		 */
		
		if (searchCon == null) {
			searchCon = new SearchConditionCertifyAppHistory();
		}
		
		list = service.findCertifyHistoryAppPagingList(searchCon);
		
		if(list != null && list.size() > 0){
			searchCon.setVerifyReqVer(list.get(0).getVerifyReqVer());
		}
		
		setSearchedTotalCount(list);
		
		return SUCCESS;
	}
	
	/**
	 * <pre>
	 * Certification Application Detail History
	 * </pre>
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String findCertifyHistoryAppDetail() throws Exception{
		
		/**
		 * 검증 회차에 속한 검증 History 상세 내역 조회
		 */
		
		// 검증 대상 Application 상세 정보 조회
		appInfo = service.findCertifyHistoryAppInfomationView(searchDetailCon);
		
		// 검증 요청 Device List 조회
		sourcePhoneList = service.findCertifyHistoryTargetPhoneList(searchDetailCon);
		
		
		targetPhoneList = null;
		
		if(sourcePhoneList != null && sourcePhoneList.size() > 0){
			
			// 검증 할당된 Device List
			targetPhoneList = new ArrayList<CertifySupportPhone>();
			
			for (CertifySupportPhone certifySupportPhone : sourcePhoneList) {
				
				if("Y".equals(certifySupportPhone.getTargetYn())){
					targetPhoneList.add(certifySupportPhone);
				}
			}
		}
		
		return SUCCESS;
	}
	
	/**
	 * <pre>
	 * Certification Test Case List
	 * </pre>
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findTestCaseList() throws Exception{
		
		/**
		 * 할당된 Test Case List 조회
		 */
		
		testCaseList = service.findCertifyTestCaseList(searchTestCaseCon);
		
		return SUCCESS;
	}

	
	
	
    /**
     * @return the searchCon
     */	
	public SearchConditionCertifyAppHistory getSearchCon() {
		return searchCon;
	}

    /**
     * @param searchCon the searchCon to set
     */	
	public void setSearchCon(SearchConditionCertifyAppHistory searchCon) {
		this.searchCon = searchCon;
	}

    /**
     * @return the list
     */	
	public List<CertifyAppHistoryList> getList() {
		return list;
	}

    /**
     * @param list the list to set
     */	
	public void setList(List<CertifyAppHistoryList> list) {
		this.list = list;
	}

    /**
     * @return the searchDetailCon
     */	
	public SearchConditionCertifyAppDetail getSearchDetailCon() {
		return searchDetailCon;
	}

    /**
     * @param searchDetailCon the searchDetailCon to set
     */	
	public void setSearchDetailCon(SearchConditionCertifyAppDetail searchDetailCon) {
		this.searchDetailCon = searchDetailCon;
	}

    /**
     * @return the appInfo
     */	
	public CertifyAppSimpleInfomation getAppInfo() {
		return appInfo;
	}

    /**
     * @param appInfo the appInfo to set
     */	
	public void setAppInfo(CertifyAppSimpleInfomation appInfo) {
		this.appInfo = appInfo;
	}

    /**
     * @return the targetPhoneList
     */	
	public List<CertifySupportPhone> getTargetPhoneList() {
		return targetPhoneList;
	}

    /**
     * @param targetPhoneList the targetPhoneList to set
     */	
	public void setTargetPhoneList(List<CertifySupportPhone> targetPhoneList) {
		this.targetPhoneList = targetPhoneList;
	}

    /**
     * @return the sourcePhoneList
     */	
	public List<CertifySupportPhone> getSourcePhoneList() {
		return sourcePhoneList;
	}

    /**
     * @param sourcePhoneList the sourcePhoneList to set
     */	
	public void setSourcePhoneList(List<CertifySupportPhone> sourcePhoneList) {
		this.sourcePhoneList = sourcePhoneList;
	}

	/**
     * @return the testCaseList
     */	
	public List<CertifyTestCase> getTestCaseList() {
		return testCaseList;
	}

    /**
     * @param testCaseList the testCaseList to set
     */	
	public void setTestCaseList(List<CertifyTestCase> testCaseList) {
		this.testCaseList = testCaseList;
	}

	/**
     * @return the searchTestCaseCon
     */	
	public CerfifyTargetTestCase getSearchTestCaseCon() {
		return searchTestCaseCon;
	}

    /**
     * @param searchTestCaseCon the searchTestCaseCon to set
     */	
	public void setSearchTestCaseCon(CerfifyTargetTestCase searchTestCaseCon) {
		this.searchTestCaseCon = searchTestCaseCon;
	}
	
	
}
