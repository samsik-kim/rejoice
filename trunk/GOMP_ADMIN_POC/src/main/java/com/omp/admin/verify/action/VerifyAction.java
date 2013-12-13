package com.omp.admin.verify.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.omp.admin.adminmgr.auth.model.AdMgr;
import com.omp.admin.adminmgr.auth.model.AdSession;
import com.omp.admin.common.Constants;
import com.omp.admin.common.util.CommonUtil;
import com.omp.admin.verify.model.Verify;
import com.omp.admin.verify.service.VerifyService;
import com.omp.admin.verify.service.VerifyServiceImpl;
import com.omp.commons.action.BaseAction;
import com.omp.commons.exception.NoticeException;
import com.omp.commons.model.PagenateList;
import com.omp.commons.text.LocalizeMessage;
import com.omp.commons.util.FileUtil;

@SuppressWarnings("serial")
public class VerifyAction extends BaseAction{
	private static final SimpleDateFormat SS = new SimpleDateFormat("yyyyMMddHHmmssss");
	
	private Verify 			verify = null;
	private VerifyService 	verifyService = null;
	private List<Verify>		categoryNameList = null;
	private String		secondDepthCategory = "";	
	private List<Verify> verifyList = null;
	private int searchCnt = 0;
	private int ctgrCnt = 0;
	private String tempCtgr = "";
	private String ctCtgrSeq = "";
	private String titleNm = "";
	private String expl = "";
	private String usYN = "";
	private File bodyUpload = null;
	private String bodyUploadFileName = "";
	private String adminAuthGubun;

	/**
	 * Constructor
	 */
    public VerifyAction() {
    	
    	this.logger.debug("VerifyAction()");
    	
    	verify = new Verify();
    	if(this.verifyService == null) {
    		this.verifyService = new VerifyServiceImpl();
    	}
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
     * TEST CASE SEARCH LIST.
     * @param qna
     * @return
     */
	@SuppressWarnings("rawtypes")
	public String verifyCase() throws Throwable {
		
		// 검증/할당 승인자 인 경우만 서비스 가능하도록 체크한다.
		isApproverCheck();

		if(verify == null){
			verify = new Verify();
		}
//		if(!verify.getCategory().equals("")){
//			this.tempCtgr = verify.getCategory();
//		}else{
//			verify.setCategory(tempCtgr);
//		}
		this.categoryNameList = this.verifyService.selectCategoryNameAll();
        this.verifyList = this.verifyService.verifyCase(verify);
		this.searchCnt = ((PagenateList)verifyList).getTotalCount().intValue();
		this.ctgrCnt = categoryNameList.size();
		
        return "success";
	}
	
	/**
     * CATEGORY SEARCH LIST.
     * @param qna
     * @return
     */
	public String popupCategory() throws Throwable {
		
		// 검증/할당 승인자 인 경우만 서비스 가능하도록 체크한다.
		isApproverCheck();
		
		if(!verify.getCategory().equals("")){
			this.tempCtgr = verify.getCategory();
		}else{
			verify.setCategory(tempCtgr);
		}
		this.categoryNameList = this.verifyService.categorySaveSelect();
		
		if(categoryNameList != null) {
			this.ctgrCnt = categoryNameList.size();
		} else {
			this.ctgrCnt = 0;
		}
		
//		this.logger.debug("숫자==>"+ctgrCnt);
//		this.logger.debug("숫자==>"+categoryNameList.get(0).getTitleNm());
        return "success";
	}
	
	public void saveCategory() {
		
		// 검증/할당 승인자 인 경우만 서비스 가능하도록 체크한다.
		isApproverCheck();
		
		HttpServletRequest request = this.req;
		AdSession adSession = (AdSession) request.getSession(true).getAttribute(Constants.ADMIN_AUTH_SESSION_KEY);
		AdMgr adMgr = adSession.getAdMgr();
		if(logger.isDebugEnabled()) {
			this.logger.debug("설명===>"+expl);
			this.logger.debug("항목명===>"+titleNm);
			this.logger.debug("사용여부===>"+usYN);
			this.logger.debug("PK===>"+ctCtgrSeq);
		}
		verify.setExplain(expl);
		verify.setTitleNm(titleNm);
		verify.setUseYn(usYN);
		verify.setInsId(adMgr.getMgrId());
		verify.setCtCtgSeq(ctCtgrSeq);
		this.verifyService.saveCategory(verify);
	}
	
	/**
     * CASE SEARCH LIST.
     * @param qna
     * @return
     */
	public String popupCase() throws Throwable {
		
		// 검증/할당 승인자 인 경우만 서비스 가능하도록 체크한다.
		isApproverCheck();
		
		this.categoryNameList = this.verifyService.selectCategoryNameAll();
		
		if(categoryNameList != null) {
			this.ctgrCnt = categoryNameList.size();
		} else {
			this.ctgrCnt = 0;
		}
		
//		this.logger.debug("숫자==>"+ctgrCnt);
//		this.logger.debug("숫자==>"+categoryNameList.get(0).getTitleNm());
        return "success";
	}
	
	public void saveCase() throws Throwable {
		
		// 검증/할당 승인자 인 경우만 서비스 가능하도록 체크한다.
		isApproverCheck();
		
		String file_full_path = null;
		HttpServletRequest request = this.req;
		
		if(bodyUpload != null) {
			file_full_path = caseAttachFileUpload();
			
			if(this.logger.isDebugEnabled()) { this.logger.debug("파일주소==>"+file_full_path); }
			if(this.logger.isDebugEnabled()) { this.logger.debug("File Name : " + bodyUploadFileName); }
			
			if(StringUtils.isNotEmpty(file_full_path) && StringUtils.isNotEmpty(bodyUploadFileName)) {
				verify.setStepFile(file_full_path);
				verify.setStepFileNm(bodyUploadFileName);
			} else {
				throw new NoticeException("(절차서)를 등록해주세요.");
			}
			
			AdSession adSession = (AdSession) request.getSession(true).getAttribute(Constants.ADMIN_AUTH_SESSION_KEY);
			AdMgr adMgr = adSession.getAdMgr();
			verify.setInsId(adMgr.getMgrId());
			verify.setVmType(verify.getPlatCtgr());
			verify.setUseYn(usYN);
			this.verifyService.insertCase(verify);
		} else {
			throw new NoticeException("(절차서)를 등록해주세요.");
		}			
		//return "success";
	}
	
	public String caseAttachFileUpload() throws Exception {
		
		// 검증/할당 승인자 인 경우만 서비스 가능하도록 체크한다.
		isApproverCheck();
		
		if(logger.isDebugEnabled()) {
			logger.debug("#case 절차서 파일 업로드"); 
		}
		
		File storePath;
		String tempPath = "/" + "testcase_"+SS.format(new Date());
		storePath = new File(this.conf.getString("omp.admin.path.share.testcase"), tempPath);
		try {
			FileUtil.move(bodyUpload, storePath, true);
		} catch (IOException e) {
		}
//		String file_full_path = null;
//		ConfigProperties prop	= new ConfigProperties();
//			
//		if (bodyUpload != null) {
//			logger.debug("#bodyUpload#"+bodyUpload);
//			if (bodyUpload.exists()) {
//				logger.debug("#bodyUploadFileName#"+bodyUploadFileName);
//				logger.debug("#prop.getString123#"+prop.getString("omp.admin.path.share.testcase"));
//				file_full_path = FileUploadUtil.uploadFile(bodyUpload, prop.getString("omp.admin.path.share.testcase"), bodyUploadFileName);
//				logger.debug("#file_full_path#"+file_full_path);
//			}
//		}
		return tempPath;
	}
	
	public Verify getVerify() {
		return verify;
	}

	public void setVerify(Verify verify) {
		this.verify = verify;
	}

	public VerifyService getVerifyService() {
		return verifyService;
	}

	public void setVerifyService(VerifyService verifyService) {
		this.verifyService = verifyService;
	}

	public String getSecondDepthCategory() {
		return secondDepthCategory;
	}

	public void setSecondDepthCategory(String secondDepthCategory) {
		this.secondDepthCategory = secondDepthCategory;
	}

	public List<Verify> getVerifyList() {
		return verifyList;
	}

	public void setVerifyList(List<Verify> verifyList) {
		this.verifyList = verifyList;
	}

	public int getSearchCnt() {
		return searchCnt;
	}

	public void setSearchCnt(int searchCnt) {
		this.searchCnt = searchCnt;
	}

	public int getCtgrCnt() {
		return ctgrCnt;
	}

	public void setCtgrCnt(int ctgrCnt) {
		this.ctgrCnt = ctgrCnt;
	}

	public String getCtgrCd() {
		return ctCtgrSeq;
	}

	public void setCtgrCd(String ctCtgrSeq) {
		this.ctCtgrSeq = ctCtgrSeq;
	}

	public String getCtgrNm() {
		return titleNm;
	}

	public void setCtgrNm(String titleNm) {
		this.titleNm = titleNm;
	}
	
	public String getCtCtgrSeq() {
		return ctCtgrSeq;
	}

	public void setCtCtgrSeq(String ctCtgrSeq) {
		this.ctCtgrSeq = ctCtgrSeq;
	}

	public String getTitleNm() {
		return titleNm;
	}

	public void setTitleNm(String titleNm) {
		this.titleNm = titleNm;
	}

	public String getExpl() {
		return expl;
	}

	public void setExpl(String expl) {
		this.expl = expl;
	}

	public String getUsYN() {
		return usYN;
	}

	public void setUsYN(String usYN) {
		this.usYN = usYN;
	}

	public void setCategoryNameList(List<Verify> categoryNameList) {
		this.categoryNameList = categoryNameList;
	}
	
	public List<Verify> getCategoryNameList() {
		return categoryNameList;
	}
	
	public String getTempCtgr() {
		return tempCtgr;
	}

	public void setTempCtgr(String tempCtgr) {
		this.tempCtgr = tempCtgr;
	}

	public File getBodyUpload() {
		return bodyUpload;
	}

	public void setBodyUpload(File bodyUpload) {
		this.bodyUpload = bodyUpload;
	}

	public String getBodyUploadFileName() {
		return bodyUploadFileName;
	}

	public void setBodyUploadFileName(String bodyUploadFileName) {
		this.bodyUploadFileName = bodyUploadFileName;
	}

}
