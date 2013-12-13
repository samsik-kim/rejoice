package com.omp.admin.certify.action;

import java.io.PrintWriter;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.omp.admin.adminmgr.auth.model.AdMgr;
import com.omp.admin.certify.model.CertifyAppInfomation;
import com.omp.admin.certify.model.CertifyAppSubContsKeyList;
import com.omp.admin.certify.model.CertifyAppSubInfomation;
import com.omp.admin.certify.model.CertifyReportAddFile;
import com.omp.admin.certify.model.CertifyTestCase;
import com.omp.admin.certify.model.SaveTestStatus;
import com.omp.admin.certify.model.SaveTesterAssignInfo;
import com.omp.admin.certify.model.SearchConPhoneInfo;
import com.omp.admin.certify.model.SearchConditionCertifyApp;
import com.omp.admin.certify.model.SearchConditionCertifyAppDetail;
import com.omp.admin.certify.model.SearchResultCertifyAppList;
import com.omp.admin.certify.model.SearchResultPhoneInfoList;
import com.omp.admin.certify.service.CertifyAppMgrService;
import com.omp.admin.certify.service.CertifyAppMgrServiceImpl;
import com.omp.admin.common.Constants;
import com.omp.admin.common.util.CommonUtil;
import com.omp.commons.action.BaseAction;
import com.omp.commons.exception.NoticeException;
import com.omp.commons.model.PagenateList;
import com.omp.commons.text.LocalizeMessage;
import com.omp.commons.util.DateUtil;

/**
 * <pre>
 * Certify Management Action
 * </pre>
 * 
 * @author shsin
 *
 */
@SuppressWarnings("serial")
public class CertifyAppMgrAction extends BaseAction {
	
	private String isMofifyYn;
	
	private String adminAuthGubun;
	
	private SearchConditionCertifyApp searchCon;
	
	private PagenateList<SearchResultCertifyAppList> list;
	
	private SearchConPhoneInfo searchConPhoneInfo;
	
	private List<SearchResultPhoneInfoList> phoneList;
	
	private CertifyAppInfomation appInfo;
	
	private SearchConditionCertifyAppDetail searchConAppDetail;
	
	private List<CertifyAppSubContsKeyList> subKeyList;
	
	private CertifyAppSubInfomation subInfo;
	
	private List<CertifyAppSubInfomation> subInfoList;
	
	private List<CertifyReportAddFile> reportAddFileList;
	
	private List<AdMgr> adMgrList;
	
	private List<CertifyTestCase> testCtgList;
	
	private SaveTesterAssignInfo saveAssign;
	
	private SaveTestStatus saveTestStatus;
	
	private String listResultMsg;
	
	
	private CertifyAppMgrService service;
	
	/**
	 * Certify Application Management Action
	 */
	public CertifyAppMgrAction(){
		service = new CertifyAppMgrServiceImpl();
	}
	
	
	/**
	 * prepare Request
	 */
	protected void prepareRequest() throws Exception{
		// Login Info get
		AdMgr adMgr = CommonUtil.getAdMgr(req.getSession());
		
		if(logger.isDebugEnabled()) logger.debug("adMgr======>" + adMgr);
		
		// Admin 권한 구분 : 검증/할당 승인자, Tester, 운영자
		// => 검증센터 : 검증/할당 승인자, Tester 만 서비스
		adminAuthGubun = adMgr.getAuthGbn();
	}
	
	private void isApproverCheck() {
		if(!Constants.AUTH_GBN_APPROVER.equals(adminAuthGubun)){
			throw new NoticeException("권한이 없습니다.");
		}
	}	
	

	/**
	 * <pre>
	 * Certification Product Info Search
	 * </pre>
	 * @return String
	 * @throws Exception
	 */
	public String findCertifyAppPagingList() {
		
		// 검증/할당 승인자 인 경우만 서비스 가능하도록 체크한다.
		isApproverCheck();
		
		/**
		 * 검증 대상 Application List 조회
		 */
		
		if (searchCon == null) {
			searchCon = new SearchConditionCertifyApp();
			
			// 최초 화면 로딩 시 검색 기간 7일로 설정
			
			searchCon.setCertifyPrgrYnAll("ALL");
			
			searchCon.setCertifyRequestDtTo(DateUtil.getSysDate("yyyyMMdd"));
			searchCon.setCertifyRequestDtFrom(DateUtil.getAddDay(-7, "yyyyMMdd"));
			
			searchCon.setCtAssignDtTo(DateUtil.getSysDate("yyyyMMdd"));
			searchCon.setCtAssignDtFrom(DateUtil.getAddDay(-7, "yyyyMMdd"));
			
			searchCon.setCtEndDtTo(DateUtil.getSysDate("yyyyMMdd"));
			searchCon.setCtEndDtFrom(DateUtil.getAddDay(-7, "yyyyMMdd"));
			
			listResultMsg = LocalizeMessage.getLocalizedMessage("jsp.certifymgr.common.msg.init_list_result");
			
			return SUCCESS;
		}
		
		list = service.findCertifyAppPagingList(searchCon);
		
		if(logger.isDebugEnabled()) logger.debug("list.getTotalCount()======>" + list.getTotalCount());
		
		setSearchedTotalCount(list);
		
		if(list.size() <= 0) listResultMsg = LocalizeMessage.getLocalizedMessage("jsp.certifymgr.common.msg.list_result");
		
		return SUCCESS;
	}
	
	/**
	 * <pre>
	 * Phone List By Ajax
	 * </pre>
	 * 
	 * @return void
	 * @throws Exception
	 */
	public void findPhoneListByAjax() throws Exception{
		
		/**
		 * Device 제조사 에 속한 Device List 조회
		 */
		
		res.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = null;		
		
		JSONObject jsonObject = new JSONObject(); 
		
		try {
			
			// Device List 조회
			phoneList = service.findPhoneList(searchConPhoneInfo);
			
			if(phoneList != null && phoneList.size() > 0){
			
				JSONArray jsonArray = new JSONArray();

				for (SearchResultPhoneInfoList searchResultPhoneInfoList : phoneList) {
					JSONObject obj = new JSONObject(); 
					obj.put("modelNm", searchResultPhoneInfoList.getModelNm());
					
					jsonArray.put(obj);
				}
				
				jsonObject.put("phoneList", jsonArray);
			}

		}catch(Exception ex){
			jsonObject.put("ERROR", "ERROR");
		}finally{
			out = res.getWriter();
			out.write(jsonObject.toString());
			
			if(out != null)out.close();
		}
	}
	
	/**
	 * <pre>
	 * Certification Application Detail Info
	 * </pre>
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String findCertifyAppDetail() throws Exception{
		
		/**
		 * 검증 대상 Application 상세 조회
		 */
		
		appInfo = service.findCertifyAppInfomationView(searchConAppDetail);
		
		// App file Tab 출력을 위한 Sub Contents Key List
		subKeyList = service.findCertifySubContsKeyList(searchConAppDetail);

		return SUCCESS;
	}
	
	
	/**
	 * <pre>
	 * Certification Application Sub Detail Info
	 * </pre>
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String findCertifyAppSubDetail() throws Exception{
		
		/**
		 * 검증대상 Application 에 속한 특정 Sub Contents 상세 정보 조회
		 */
		
		
		subInfo = service.findCertifyAppSubInfomationView(searchConAppDetail);
		
		// 기타첨부파일 List
		reportAddFileList = service.findCertifyRefortAddFileInfomationList(searchConAppDetail);
		
		// App file Tab 출력을 위한 Sub Contents Key List
		subKeyList = service.findCertifySubContsKeyList(searchConAppDetail);
		
		return SUCCESS;
	}	
	
	
	/**
	 * <pre>
	 * Certification Application Info To Assign
	 * </pre>
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String findCertifyAppInfoToAssign() throws Exception{
		
		/**
		 * 검증대상 Application 을 Tester 에게 할당하기위한 화면 Form
		 * => 검증/할당 승인자 에게만 서비스
		 */
		
		// 검증/할당 승인자 인 경우만 서비스 가능하도록 체크한다.
		isApproverCheck();	
		
		/**
		 * 검증 대상 Application 정보
		 */
		appInfo = service.findCertifyAppInfomationView(searchConAppDetail);
		
		/**
		 * 검증 대상 Application 에 속한 Sub Contents List
		 */
		subInfoList = service.findCertifyAppSubInfomationList(searchConAppDetail);
		
		/**
		 * Tester List
		 */
		adMgrList = service.findCertifyTesterList();
		
		/**
		 * Test Category List
		 */
		testCtgList = service.findCertifyTestCaseInfomationList(searchConAppDetail);
		
		return SUCCESS;
	}

	/**
	 * <pre>
	 * Save Tester Assign Info
	 * </pre>
	 * 
	 * @throws Exception
	 */
	public void saveTesterAssignByAjax() throws Exception{
		
		
		/**
		 * 검증 대상 Application 에 대해서 Tester 및 Test Case 할당
		 */
		
		// 검증/할당 승인자 인 경우만 서비스 가능하도록 체크한다.
		isApproverCheck();
		
		res.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = null;		
		
		JSONObject jsonObject = new JSONObject(); 
		
		boolean isSaved = false;
		
		try {
			
			saveAssign.setLoginId(CommonUtil.getLoginId(req.getSession()));
			
			isSaved = service.saveTesterAssign(saveAssign);
			
			jsonObject.put("responseCode", "SUCC");
			jsonObject.put("responseMessage", LocalizeMessage.getLocalizedMessage("정상적으로 처리되었습니다."));

		}catch(Exception ex){
			this.logger.error("테스터를 위한 검증상품 할당 실패.", ex);
			jsonObject.put("responseCode", "FAIL");
			jsonObject.put("responseMessage", LocalizeMessage.getLocalizedMessage("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다."));
		}finally{
			out = res.getWriter();
			out.write(jsonObject.toString());
			
			if(out != null)out.close();
		}
	}
	
	
	/**
	 * <pre>
	 * Save Tester Assign Info
	 * </pre>
	 * 
	 * @throws Exception
	 */
	public void saveCertifyPassByAjax() throws Exception{
		
		/**
		 * 검증 Pass
		 * => 검증할당 없이 바로 검증완료 처리.
		 */
		
		// 검증/할당 승인자 인 경우만 서비스 가능하도록 체크한다.
		isApproverCheck();		
		
		res.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = null;		
		
		JSONObject jsonObject = new JSONObject(); 
		
		boolean isSaved = false;
		
		try {
			
			saveTestStatus.setUpdBy(CommonUtil.getLoginId(req.getSession()));
			
			isSaved = service.saveCertifyPass(saveTestStatus, req);
			
			jsonObject.put("responseCode", "SUCC");
			jsonObject.put("responseMessage", LocalizeMessage.getLocalizedMessage("정상적으로 처리되었습니다."));

		}catch(Exception ex){
			this.logger.error("검증 PASS처리 실패.", ex);
			jsonObject.put("responseCode", "FAIL");
			jsonObject.put("responseMessage", LocalizeMessage.getLocalizedMessage("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다."));
		}finally{
			out = res.getWriter();
			out.write(jsonObject.toString());
			
			if(out != null)out.close();
		}
	}	
	
	/**
	 * <pre>
	 * Save Test Status Info
	 * </pre>
	 * 
	 * @throws Exception
	 */
	public void saveAgreeStatusByAjax() throws Exception{
		
		/**
		 * 검증 승인 처리
		 * 1. 검증 승인
		 *    => 검증 완료 처리
		 * 2. 검증 반려
		 *    => Tester 에게 다시 Test 요청
		 */
		
		// 검증/할당 승인자 인 경우만 서비스 가능하도록 체크한다.
		isApproverCheck();		
		
		res.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = null;		
		
		JSONObject jsonObject = new JSONObject(); 
		
		boolean isSaved = false;
		
		try {
			
			saveTestStatus.setUpdBy(CommonUtil.getLoginId(req.getSession()));
			
			if(Constants.CODE_VERIFY_TEST_REJECT.equals(saveTestStatus.getStatus())){
				
				// 검증 반려 : Tester 에게 다시 Test 요청
				isSaved = service.updateTestReject(saveTestStatus);
				
			}else if(Constants.CODE_VERIFY_END.equals(saveTestStatus.getStatus())){
				
				// 검증 승인 : 검증 완료
				isSaved = service.updateCertifyAgreement(saveTestStatus, this.req);
			}

			jsonObject.put("responseCode", "SUCC");
			jsonObject.put("responseMessage", LocalizeMessage.getLocalizedMessage("정상적으로 처리되었습니다."));

		}catch(Exception ex){
			this.logger.error("검증 승인/반려 변경 실패.", ex);
			jsonObject.put("responseCode", "FAIL");
			jsonObject.put("responseMessage", LocalizeMessage.getLocalizedMessage("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다."));
		}finally{
			out = res.getWriter();
			out.write(jsonObject.toString());
			
			if(out != null)out.close();
		}
	}		

	
	/**
	 * <pre>
	 * Certification Application Detail Info
	 * </pre>
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String testRejectReason() throws Exception{
		
		/**
		 * 검증 반려 처리하기 위한 화면 Form
		 */
		
		// 검증/할당 승인자 인 경우만 서비스 가능하도록 체크한다.
		isApproverCheck();		
		
		return SUCCESS;
	}	

	
    /**
     * @return the adminAuthGubun
     */		
	public String getAdminAuthGubun() {
		return adminAuthGubun;
	}

    /**
     * @param adminAuthGubun the adminAuthGubun to set
     */
	public void setAdminAuthGubun(String adminAuthGubun) {
		this.adminAuthGubun = adminAuthGubun;
	}

    /**
     * @return the searchCon
     */	
	public SearchConditionCertifyApp getSearchCon() {
		return searchCon;
	}

    /**
     * @param searchCon the searchCon to set
     */
	public void setSearchCon(SearchConditionCertifyApp searchCon) {
		this.searchCon = searchCon;
	}

    /**
     * @return the list
     */	
	public PagenateList<SearchResultCertifyAppList> getList() {
		return list;
	}

    /**
     * @param list the list to set
     */
	public void setList(PagenateList<SearchResultCertifyAppList> list) {
		this.list = list;
	}

    /**
     * @return the phoneList
     */	
	public List<SearchResultPhoneInfoList> getPhoneList() {
		return phoneList;
	}

    /**
     * @param phoneList the phoneList to set
     */
	public void setPhoneList(List<SearchResultPhoneInfoList> phoneList) {
		this.phoneList = phoneList;
	}

    /**
     * @return the searchConPhoneInfo
     */	
	public SearchConPhoneInfo getSearchConPhoneInfo() {
		return searchConPhoneInfo;
	}

    /**
     * @param searchConPhoneInfo the searchConPhoneInfo to set
     */
	public void setSearchConPhoneInfo(SearchConPhoneInfo searchConPhoneInfo) {
		this.searchConPhoneInfo = searchConPhoneInfo;
	}

    /**
     * @return the appInfo
     */	
	public CertifyAppInfomation getAppInfo() {
		return appInfo;
	}

    /**
     * @param appInfo the appInfo to set
     */
	public void setAppInfo(CertifyAppInfomation appInfo) {
		this.appInfo = appInfo;
	}

    /**
     * @return the searchConAppDetail
     */	
	public SearchConditionCertifyAppDetail getSearchConAppDetail() {
		return searchConAppDetail;
	}

    /**
     * @param searchConAppDetail the searchConAppDetail to set
     */
	public void setSearchConAppDetail(
			SearchConditionCertifyAppDetail searchConAppDetail) {
		this.searchConAppDetail = searchConAppDetail;
	}

    /**
     * @return the subKeyList
     */	
	public List<CertifyAppSubContsKeyList> getSubKeyList() {
		return subKeyList;
	}

    /**
     * @param subInfo the subInfo to set
     */
	public void setSubKeyList(List<CertifyAppSubContsKeyList> subKeyList) {
		this.subKeyList = subKeyList;
	}

    /**
     * @return the subInfo
     */	
	public CertifyAppSubInfomation getSubInfo() {
		return subInfo;
	}

    /**
     * @param subInfoList the subInfoList to set
     */
	public void setSubInfo(CertifyAppSubInfomation subInfo) {
		this.subInfo = subInfo;
	}

    /**
     * @return the subInfoList
     */	
	public List<CertifyAppSubInfomation> getSubInfoList() {
		return subInfoList;
	}

    /**
     * @param adMgrList the adMgrList to set
     */
	public void setSubInfoList(List<CertifyAppSubInfomation> subInfoList) {
		this.subInfoList = subInfoList;
	}

    /**
     * @return the adMgrList
     */	
	public List<AdMgr> getAdMgrList() {
		return adMgrList;
	}

    /**
     * @param testCtgList the testCtgList to set
     */
	public void setAdMgrList(List<AdMgr> adMgrList) {
		this.adMgrList = adMgrList;
	}

    /**
     * @return the testCtgList
     */	
	public List<CertifyTestCase> getTestCtgList() {
		return testCtgList;
	}

    /**
     * @param testCtgList the testCtgList to set
     */
	public void setTestCtgList(List<CertifyTestCase> testCtgList) {
		this.testCtgList = testCtgList;
	}

    /**
     * @return the saveAssign
     */	
	public SaveTesterAssignInfo getSaveAssign() {
		return saveAssign;
	}

    /**
     * @param saveAssign the saveAssign to set
     */
	public void setSaveAssign(SaveTesterAssignInfo saveAssign) {
		this.saveAssign = saveAssign;
	}

    /**
     * @return the isMofifyYn
     */	
	public String getIsMofifyYn() {
		return isMofifyYn;
	}

    /**
     * @param isMofifyYn the isMofifyYn to set
     */
	public void setIsMofifyYn(String isMofifyYn) {
		this.isMofifyYn = isMofifyYn;
	}

    /**
     * @return the reportAddFileList
     */	
	public List<CertifyReportAddFile> getReportAddFileList() {
		return reportAddFileList;
	}

    /**
     * @param reportAddFileList the reportAddFileList to set
     */
	public void setReportAddFileList(List<CertifyReportAddFile> reportAddFileList) {
		this.reportAddFileList = reportAddFileList;
	}

    /**
     * @return the saveTestStatus
     */	
	public SaveTestStatus getSaveTestStatus() {
		return saveTestStatus;
	}

    /**
     * @param saveTestStatus the saveTestStatus to set
     */
	public void setSaveTestStatus(SaveTestStatus saveTestStatus) {
		this.saveTestStatus = saveTestStatus;
	}

	/**
	 * @return the listResultMsg
	 */
	public String getListResultMsg() {
		return listResultMsg;
	}

	/**
	 * @param listResultMsg the listResultMsg to set
	 */
	public void setListResultMsg(String listResultMsg) {
		this.listResultMsg = listResultMsg;
	}
}
