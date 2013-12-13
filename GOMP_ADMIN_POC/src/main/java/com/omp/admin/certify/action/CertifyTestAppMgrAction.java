package com.omp.admin.certify.action;

import java.io.PrintWriter;
import java.util.List;

import org.json.JSONObject;

import com.omp.admin.adminmgr.auth.model.AdMgr;
import com.omp.admin.certify.model.SaveTestStatus;
import com.omp.admin.certify.model.SearchConditionCertifyTesterApp;
import com.omp.admin.certify.model.SearchResultCertifyTestAppList;
import com.omp.admin.certify.model.SubAppTestResultSave;
import com.omp.admin.certify.service.CertifyAppMgrService;
import com.omp.admin.certify.service.CertifyAppMgrServiceImpl;
import com.omp.admin.common.Constants;
import com.omp.admin.common.util.CommonUtil;
import com.omp.commons.action.BaseAction;
import com.omp.commons.exception.NoticeException;
import com.omp.commons.text.LocalizeMessage;
import com.omp.commons.util.DateUtil;

/**
 * <pre>
 * Certify Test Management Action
 * </pre>
 * 
 * @author shsin
 *
 */
@SuppressWarnings("serial")
public class CertifyTestAppMgrAction extends BaseAction {
	
	private String adminAuthGubun;
	
	private SearchConditionCertifyTesterApp searchCon;
	
	private List<SearchResultCertifyTestAppList> list;
	
	private SaveTestStatus saveTestStatus;
	
	private SubAppTestResultSave subAppTestResultSave;
	
	private String listResultMsg;
	
	private CertifyAppMgrService service;
	
	/**
	 * Certify Test Application Action
	 */
	public CertifyTestAppMgrAction(){
		service = new CertifyAppMgrServiceImpl();
	}
	
	
	/**
	 * prepare Request
	 */
	protected void prepareRequest() throws Exception{
		// Login Info get
		AdMgr adMgr = CommonUtil.getAdMgr(req.getSession());
		
		if(logger.isDebugEnabled()) logger.debug("adMgr======>" + adMgr);
		
		adminAuthGubun = adMgr.getAuthGbn();
		
		if(!Constants.AUTH_GBN_TESTER.equals(adminAuthGubun)){
			throw new NoticeException("권한이 없습니다.");
		}
	}	

	/**
	 * Certification Product Info Search
	 * </pre>
	 * @return String
	 * @throws Exception
	 */
	public String findCertifyTestAppPagingList() throws Exception{
		
		/**
		 * Tester 에게 할당된 Test List 조회
		 */
		
		if (searchCon == null) {
			searchCon = new SearchConditionCertifyTesterApp();
			
			// 최초 화면 로딩 시 검색 기간 7일로 설정
			
			searchCon.setCtAssignDtTo(DateUtil.getSysDate("yyyyMMdd"));
			searchCon.setCtAssignDtFrom(DateUtil.getAddDay(-7, "yyyyMMdd"));
			
			searchCon.setCtEndExDtTo(DateUtil.getSysDate("yyyyMMdd"));
			searchCon.setCtEndExDtFrom(DateUtil.getAddDay(-7, "yyyyMMdd"));
			
			listResultMsg = LocalizeMessage.getLocalizedMessage("jsp.certifymgr.common.msg.init_list_result");
			
			return SUCCESS;
		}
		
		searchCon.setLoginId(com.omp.admin.common.util.CommonUtil.getLoginId(this.req.getSession()));
		
		list = service.findCertifyTestPagingList(searchCon);
		
		setSearchedTotalCount(list);
		
		if(list.size() <= 0) listResultMsg = LocalizeMessage.getLocalizedMessage("jsp.certifymgr.common.msg.list_result");
		
		return SUCCESS;
	}
	
	/**
	 * Test End Check
	 * </pre>
	 * @return String
	 * @throws Exception
	 */
	public void findTestEndCheckByAjax() throws Exception{
		
		/**
		 * Tester 가 Test 종료가 가능한지 체크
		 */
		
		res.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = null;		
		
		JSONObject jsonObject = new JSONObject(); 
		
		try {
			
			String testEndCheck = service.findTestEndCheck(saveTestStatus);
			
			jsonObject.put("responseCode", "SUCC");
			jsonObject.put("responseMessage", testEndCheck);

		}catch(Exception ex){
			this.logger.error("테스터의 미처리 유무 확인 실패.", ex);
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
	public void saveTestStatusByAjax() throws Exception{
		
		/**
		 * Tester 가 검증대상 Application Test 진행 / Test 종료 처리
		 */
		
		res.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = null;		
		
		JSONObject jsonObject = new JSONObject(); 
		
		boolean isSaved = false;
		
		try {
			
			saveTestStatus.setUpdBy(CommonUtil.getLoginId(req.getSession()));
			
			if(Constants.TEST_PROGRESS_STATUS_ING.equals(saveTestStatus.getStatus())){
				
				// 검증 대상 Application Test 진행 처리
				isSaved = service.updateTestIng(saveTestStatus);
				jsonObject.put("responseFlag", "TEST_ING");
				
			}else if(Constants.TEST_PROGRESS_STATUS_COMPLETE.equals(saveTestStatus.getStatus())){
				
				// 검증 대상 Application Test 종료 처리
				isSaved = service.updateTestEnd(saveTestStatus);
			}
			
			jsonObject.put("responseCode", "SUCC");
			jsonObject.put("responseMessage", LocalizeMessage.getLocalizedMessage("정상적으로 처리되었습니다."));

		}catch(Exception ex){
			this.logger.error("테스트 상태변경 실패.", ex);
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
	 * Save Application File Test Complete Info
	 * </pre>
	 * 
	 * @throws Exception
	 */
	public void saveSubTestCompleteByAjax() throws Exception{
		
		/**
		 * 검증 대상 Application 의 Sub Contents 에 대한 Test 종료 처리
		 */
		
		res.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = null;		
		
		JSONObject jsonObject = new JSONObject(); 
		
		boolean isSaved = false;
		
		try {
			
			subAppTestResultSave.setLoginId(CommonUtil.getLoginId(req.getSession()));
			
			isSaved = service.saveSubTestComplete(subAppTestResultSave, getConfig());
			
			jsonObject.put("responseCode", "SUCC");
			jsonObject.put("responseMessage", LocalizeMessage.getLocalizedMessage("정상적으로 처리되었습니다."));

		}catch(Exception ex){
			this.logger.error("부분테스트 완료 처리 실패.", ex);
			jsonObject.put("responseCode", "FAIL");
			jsonObject.put("responseMessage", LocalizeMessage.getLocalizedMessage("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다."));
		}finally{
			
			System.out.println("jsonObject.toString()==========>" + jsonObject.toString());
			
			out = res.getWriter();
			out.write(jsonObject.toString());
			
			if(out != null)out.close();
		}
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
	public SearchConditionCertifyTesterApp getSearchCon() {
		return searchCon;
	}

    /**
     * @param searchCon the searchCon to set
     */
	public void setSearchCon(SearchConditionCertifyTesterApp searchCon) {
		this.searchCon = searchCon;
	}

    /**
     * @return the list
     */
	public List<SearchResultCertifyTestAppList> getList() {
		return list;
	}

    /**
     * @param list the list to set
     */
	public void setList(List<SearchResultCertifyTestAppList> list) {
		this.list = list;
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
     * @return the subAppTestResultSave
     */
	public SubAppTestResultSave getSubAppTestResultSave() {
		return subAppTestResultSave;
	}

    /**
     * @param subAppTestResultSave the subAppTestResultSave to set
     */
	public void setSubAppTestResultSave(SubAppTestResultSave subAppTestResultSave) {
		this.subAppTestResultSave = subAppTestResultSave;
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
