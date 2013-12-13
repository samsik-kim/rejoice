package com.omp.dev.contents.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;

import com.omp.commons.action.BaseAction;
import com.omp.commons.exception.NoticeException;
import com.omp.commons.text.LocalizeMessage;
import com.omp.commons.util.CommonUtil;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.SessionUtil;
import com.omp.commons.model.PagenateList;
import com.omp.commons.product.service.ContentsHistoryService;
import com.omp.commons.product.service.ContentsHistoryServiceImpl;
import com.omp.dev.common.Constants;
import com.omp.dev.common.util.ReturnUrlGenerateUtil;
import com.omp.dev.contents.model.Contents;
import com.omp.dev.contents.model.ContentsVerify;
import com.omp.dev.contents.model.SaleStatHist;
import com.omp.dev.contents.model.SubContents;
import com.omp.dev.contents.service.ContentDetailInfoService;
import com.omp.dev.contents.service.ContentDetailInfoServiceImpl;
import com.omp.dev.contents.service.ContentManagementService;
import com.omp.dev.contents.service.ContentManagementServiceImpl;
import com.omp.dev.contents.service.ContentsVerifyService;
import com.omp.dev.contents.service.ContentsVerifyServiceImpl;
import com.omp.dev.user.model.Session;

/**
 * Contents Management Status
 * 
 * @author snoopy
 *
 */
@SuppressWarnings("serial")
public class ContentManagementAction extends BaseAction {

	private static GLogger logger = new GLogger(ContentManagementAction.class);
	
	private    Contents 					content;
	private    SubContents 					subContent;
	private 	ContentManagementService   	contentsManagementService;
	private 	ContentDetailInfoService 	contentDetailInfoService;
	private		ContentsHistoryService		contentsHistoryService;
	
	private    Map<String, Object>			resultContentsMap;
	private  	Map<String, Object>			contentImageMap;
	private  	Map<String, Object>			contentTagMap;
	private 	List<Contents>				contentsStatusList;
	private 	List<SaleStatHist>			SaleStatHistList;
	private 	int							contentsStatusListCount;
	private		String 						tabGbn;
	
	public ContentManagementAction() {
		content						= new Contents();
		contentsManagementService	= new ContentManagementServiceImpl();
		contentDetailInfoService	= new ContentDetailInfoServiceImpl();
		contentsHistoryService		= new ContentsHistoryServiceImpl();
	}
	
	/**
	 * Content Management SubMain
	 * : 상품 등록/관리 서브메인
	 * 
	 * @return
	 */
	public String contentsSubMain() {
	
		if(resultContentsMap == null) resultContentsMap = new HashMap<String, Object>();

		if (SessionUtil.existMemberSession(this.req)) {
			
			Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
			
			if(logger.isDebugEnabled()) {
    			logger.debug("* Session MBR_NO 	: {0}", new Object[] {memberInfo.getMbrNo()});
    		}
			
			content.setDevUserId(memberInfo.getMbrNo());		// Member Info
			content.setImgGbn(Constants.IMG_72_72);				// List Image Size
	
			// Newly registered Contents (Recent 7Days, Count : 3, 신규 등록된 상품 )
			content.setSaleStat(Constants.CONTENT_SALE_STAT_NA);
			resultContentsMap.put("newRegistContents", 	contentsManagementService.getContentStatusList7Days(content));
			resultContentsMap.put("newRegistCount", 	contentsManagementService.getContentStatusCount(content));
			
			// Sale Pending Contents (Recent 7Days, Count : 3, 판매 대기 중 상품)
			content.setSaleStat(Constants.CONTENT_SALE_STAT_WAIT);			
			resultContentsMap.put("saleWaitContents", 	contentsManagementService.getContentStatusList7Days(content));
			resultContentsMap.put("saleWaitCount", 		contentsManagementService.getContentStatusCount(content));
			
			// sales Contents (Recent 7Days, Count : 3, 판매 중 상품)
			content.setSaleStat(Constants.CONTENT_SALE_STAT_ING);			
			resultContentsMap.put("salesContents", 		contentsManagementService.getContentStatusCount(content));

			// Verification in progress Contents (Count : 3, 검증 진행 중 상품)
			content.setSaleStat("");		
			content.setVerifyPrgrYn(Constants.CODE_VERIFY_ING); 
			resultContentsMap.put("verifyContents", contentsManagementService.getContentVerifyStatusList7Days(content));
			resultContentsMap.put("verifyCount", 	contentsManagementService.getContentStatusCount(content));
			
			// Verification results (Recent 7Days, Count : 3, 검증 결과)
			content.setSaleStat("");	
			content.setVerifyPrgrYn(Constants.CODE_VERIFY_END); 
			resultContentsMap.put("verifyResults", contentsManagementService.getContentVerifyStatusList7Days(content));

		} 
		
		return SUCCESS;
	}
	
	/**
	 * Content Management Status List
	 * : 상품 현황 리스트
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String contentsStatusList() {
		
		if (SessionUtil.existMemberSession(this.req)) {
			Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);

			if(logger.isDebugEnabled()) {
    			logger.debug("* Session MBR_NO 	: {0}", new Object[] {memberInfo.getMbrNo()});
    		}
			
			content.getPage().setRows(20);
			content.setDevUserId(memberInfo.getMbrNo());										// Member Info
			content.setImgGbn(Constants.IMG_72_72);												// List Image Size
			content.setProdNm(CommonUtil.removeSpecial(content.getSearchValue()));				// Security handling

			contentsStatusList = contentsManagementService.getContentStatusList(content);		// Contents List
			contentsStatusListCount = ((PagenateList)contentsStatusList).getTotalCount().intValue();

		} 
		
		return SUCCESS;
	}
	
	/**	
	 * Create Content Page
	 * : 상품 등록 페이지
	 * 
	 * @return
	 */
	public String registContentWrite() {
		

		if (SessionUtil.existMemberSession(this.req)) {
			
			Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
			
			if(logger.isDebugEnabled()) {
    			logger.debug("* Session MBR_NO 	: {0}", new Object[] {memberInfo.getMbrNo()});
    		}
			
			if (content.getVmType() == null 
					|| Constants.CONTENT_PLATFORM_ANDROID.equals(content.getVmType())){
				content.setVmType(Constants.CONTENT_PLATFORM_ANDROID);
			}
			
		} 
		
		return SUCCESS;
	}

	/**
	 * Create New Content 
	 * : 상품 등록
	 * 
	 * @return
	 * @throws JSONException
	 * @throws IOException
	 */
	public String ajaxRegistConfirm() {

		try	{
			
			if (SessionUtil.existMemberSession(this.req)) {
					
				if (content == null || content.getProdNm() == null) {
					throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
				}
	
				Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
				Properties prop = this.conf.getStaticProperties();
	
				content.setDevUserId(memberInfo.getMbrNo());
				content.setInsBy(memberInfo.getMbrNo());
				content.setUpdBy(memberInfo.getMbrNo());
	
				content = contentsManagementService.setContent(content, prop);
				
				if(logger.isDebugEnabled()) {
					logger.debug("* Session MBR_NO 				 : {0}", new Object[] {memberInfo.getMbrNo()});
					logger.debug("* 상품 신규 등록 완료   [NEW CID :   {0}]", new Object[] {content.getCid()});
				}
				
				if (content.getCid() != null) {
					
					// History Table Insert
					contentsHistoryService.insertContsHis(content.getCid());
					contentsHistoryService.insertSaleStatHis(content.getCid(), content.getSaleStat(), content.getInsBy(), false);
				}
				
			} else {
				throw new NoticeException("Session이 존재하지 않습니다.");
			}
			
		} catch (Exception se) {
			logger.error("상품 등록에 실패하였습니다. 재시도 해주시기 바랍니다. ", se);
			return ERROR;
		} 

		return SUCCESS;
	}
	
	/**
	 * Delete Content
	 * : 미승인 상품 삭제
	 * 
	 */
	public void ajaxContentDelete() {
		
	
		this.res.setContentType("text/html; charset=UTF-8");
		
		PrintWriter writer 		= null;
		JSONObject jsonObject 	= new JSONObject();
		
		String resultCode = "FAIL";
		
        try {
        	
        	if (SessionUtil.existMemberSession(this.req)) {

	        	if (content == null || content.getCid() == null) {
					//throw new NoticeException("Can't acquire Contents null.");
					throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
				}
				
        		Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
        		
        		if(logger.isDebugEnabled()) {
	        		logger.debug("* CID 			: {0}", new Object[] {content.getCid()});
	    			logger.debug("* Session MBR_NO 	: {0}", new Object[] {memberInfo.getMbrNo()});
        		}
        		
	        	content = contentDetailInfoService.getContentDetailInfo(content.getCid());	
	        	
	        	// 상품을 등록한 사용자와 Login한 상용자가 다를 경우  Exception 처리 한다.
				if(content == null || !memberInfo.getMbrNo().equals(content.getDevUserId())) {	
					throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
				}
				
				// 판매 상태 : 미승인, 검증 상태 :  미검증 일 때 상품 삭제 가능
				if (Constants.CONTENT_SALE_STAT_NA.equals(content.getSaleStat()) 
						&& Constants.CODE_VERIFY_INIT.equals(content.getVerifyPrgrYn())) {
					content.setUpdBy(memberInfo.getMbrNo());								// 수정자의 MbrNo를 등록 합니다.
					contentsManagementService.updateContentDelete(content);					// 상품 삭제 여부를 업데이트 한다.

					resultCode = "SUCCESS";
							
				} else {
					resultCode = "FAIL";
				}
				
				// History Table Insert
				contentsHistoryService.insertContsHis(content.getCid());
				
        	} else {
    			throw new NoticeException("Session이 존재하지 않습니다.");
    		}
        	
		} catch (Exception e) {
			resultCode = "FAIL";
			logger.error("상품 삭제가 실패하였습니다. 재시도 해주시기 바랍니다", e);
		} finally {
			
			try {
				
				if (logger.isDebugEnabled()) 
						logger.debug("* result Code 	: {0}", new Object[] {resultCode});
				
				jsonObject.put("resultCode", resultCode);
			
				writer = this.res.getWriter();
				writer.write(jsonObject.toString());
				
			} catch (Exception jse) {
				logger.error("Content Delete Fail" , jse);
			}
			
			if(writer != null) { writer.close(); }
		}
	
	}

	/**
	 * Verify Request Comment Popup Page
	 * : 검증요청 팝업 페이지 호출
	 * 
	 * @return
	 */
	public String ajaxVerifyReqComnt() {
		
		if (SessionUtil.existMemberSession(this.req)) {
			
			// Contents null일 경우 Throw 처리 한다.
			if (content == null || content.getCid() == null) {
				throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
			}
			
			Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
			
			if(logger.isDebugEnabled()) {
				logger.debug("* CID 				: {0}", new Object[] {content.getCid()});
				logger.debug("* Session MBR_NO 		: {0}", new Object[] {memberInfo.getMbrNo()});
				logger.debug("* TAB GBN 			: {0}", new Object[] {this.getTabGbn()});
			}
			
			content = contentDetailInfoService.getContentBaseInfo(content.getCid());		// Content Base Info
	
			// 상품을 등록한 사용자와 Login한 상용자가 다를 경우  Exception 처리 한다.
			if(content == null || !memberInfo.getMbrNo().equals(content.getDevUserId())) {	
				throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
			}

			// 최초검증인지 재검증인지 구분
			boolean isNotFirstVerificationFlag = false;	// true : 재검증, false : 최초검증
			isNotFirstVerificationFlag = contentsManagementService.getVerificationFlag(content);		
			
			if (!isNotFirstVerificationFlag) {
				return "firstVerifyRequest";	// 최초 검증
			} else {
				return "reVerifyRequest";		// 재검증
			}
			
		} else {
			throw new NoticeException("Session이 존재하지 않습니다.");
		}
		
	}
	
	/**
	 *  Verify Request
	 *  : 검증 요청
	 */
	public void ajaxVerifyReq() {
		
		this.res.setContentType("text/html; charset=UTF-8");
		
		PrintWriter writer = null;
		JSONObject jsonObject = new JSONObject(); 
		
		String checkNullValue = "SUCCESS";
		String resultMessage = "";
		
		try {
			
			if (SessionUtil.existMemberSession(this.req)) {

				this.setStep("CheckValidateParamCid");
				if (content == null || content.getCid() == null) {
					throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
				}
				
				Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
				
				if(logger.isDebugEnabled()) {
					logger.debug("* CID 				: {0}", new Object[] {content.getCid()});
					logger.debug("* Session MBR_NO 		: {0}", new Object[] {memberInfo.getMbrNo()});
					logger.debug("* Verify CD 			: {0}", new Object[] {content.getVerifyCommentCd()} );
					logger.debug("* Verify Cmnt 		: {0}", new Object[] {content.getVerifyEtcCmt()});
					logger.debug("* TAB GBN 			: {0}", new Object[] {this.getTabGbn()});
				}
				
				this.setPrimaryKey("ACTOR", memberInfo.getMbrNo(), "CID", content.getCid(), "JOB_TYPE", "CERTIFY_REQ");
				
				this.setStep("CallServiceContentBaseInfo");
				Contents oldContent = contentDetailInfoService.getContentDetailInfo(content.getCid());	
				
				this.setStep("CheckValidateDevUser");
				// 상품을 등록한 사용자와 Login한 상용자가 다를 경우  Exception 처리 한다.
				if(oldContent == null || !memberInfo.getMbrNo().equals(oldContent.getDevUserId())) {	
					throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
				}
				
				// 검증 요청 필수값 Check
				this.setStep("CallServiceRequiredValueCheck");
				checkNullValue = contentsManagementService.getRequiredValueCheck(oldContent);
				
				this.setStep("ResultContentValidate");
				if ("SUCCESS".equals(checkNullValue)) {
				
					// 검증 요청
					this.setStep("CallServiceContentVerifyRequest");
					String result = contentsManagementService.updateContentVerifyRequest(content);
					result = result ==  null || "".equals(result) ? "0" : result;
					
					if(logger.isDebugEnabled()) {
						logger.debug(" * Verify Req Result : {0}", new Object[] {result});
					}

					this.setStep("ResultContentVerifyRequest");
					if(Integer.parseInt(result) > 0) {
						
						// E-mail 이메일발송
						String returlUrl = ReturnUrlGenerateUtil.rtnUrl(this.req);
						String toAddr = memberInfo.getEmailAddr();
						
						this.setStep("CallServiceVerifyReqContentInfo");
						Contents newContent = contentsManagementService.getVerifyReqContentInfo(oldContent.getCid());
						
						this.setStep("CallServiceSendEmail");
						contentsManagementService.sendContentSaleStatMail(newContent, returlUrl, memberInfo.getMbrId(),toAddr);
	
						// History Table Insert
						this.setStep("CallServiceContentHistory");
						contentsHistoryService.insertContsHis(content.getCid());
						
					}
				} 
				
				if("ErrorProdNm".equals(checkNullValue)) 				resultMessage = "상품명을 입력하세요";
				else if("ErrorExposureDevNm".equals(checkNullValue)) 	resultMessage = "판매자명을 입력하세요";
				else if("ErrorProdPrc".equals(checkNullValue)) 			resultMessage = "상품 가격을 입력하세요";
				else if("ErrorProdCtgr".equals(checkNullValue)) 		resultMessage = "분류 정보를 입력하세요";
				else if("ErrorGameDelibGrd".equals(checkNullValue)) 	resultMessage = "이용등급을 선택하세요";	
				else if ("ErrorTagNm".equals(checkNullValue)) 			resultMessage = "태그 정보를 입력하세요.";
				else if ("ErrorProdDescSmmr".equals(checkNullValue)) 	resultMessage = "요약설명을 입력하세요.";
				else if ("ErrorProdDescDtl".equals(checkNullValue)) 	resultMessage = "상세설명을 입력하세요.";
				else if ("ErrorIconImg1".equals(checkNullValue)) 		resultMessage = "대표아이콘을 등록하세요.";
				else if ("getPreviewImg1".equals(checkNullValue) || "getPreviewImg2".equals(checkNullValue) 
						|| "getPreviewImg3".equals(checkNullValue) || "getPreviewImg4".equals(checkNullValue)) 
																		resultMessage = "미리보기이미지를 입력하세요.";
				else if ("NoneSubContents".equals(checkNullValue)) 		resultMessage = "바이너리 파일을 등록하세요.";
				
			} else {
				throw new NoticeException("Session이 존재하지 않습니다.");
			}
		
		} catch(Exception e) {
			resultMessage = "요청 처리가 실패했습니다. 재시도해 주시기 바랍니다.";
		} finally {
			try {	
				
				if (logger.isDebugEnabled()) 
						logger.debug("* check Null Value 	: {0}", new Object[] {checkNullValue});
			
				jsonObject.put("resultMessage", LocalizeMessage.getLocalizedMessage(resultMessage));
				
				writer = this.res.getWriter();
				writer.write(jsonObject.toString());
				
			} catch (Exception jse) {
				logger.error("ajax Verify Request Fail.", jse);
			}
			
			if(writer != null) { writer.close(); }	
			
		}
	
	}
	
	/**
	 *  Verify Cancel
	 *  : 검증 취소
	 */
	public void ajaxVerifyCancel() {
		
		this.res.setContentType("text/html; charset=UTF-8");
			
		PrintWriter writer 		= null;
		JSONObject jsonObject 	= new JSONObject(); 
		String resultCode 		= "FAIL";
		
		try {
			
			if (SessionUtil.existMemberSession(this.req)) {

				if (content == null || content.getCid() == null) {
					throw new NoticeException("Can't acquire contents null");
				}

				Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);	

				if(logger.isDebugEnabled()) {
					logger.debug("* CID 				: {0}", new Object[] {content.getCid()});
					logger.debug("* Session MBR_NO 		: {0}", new Object[] {memberInfo.getMbrNo()});
					logger.debug("* TAB GBN 			: {0}", new Object[] {this.getTabGbn()});
				}
				
				content = contentDetailInfoService.getContentDetailInfo(content.getCid());	
				
				// 상품을 등록한 사용자와 Login한 상용자가 다를 경우  Exception 처리 한다.
				if(content == null || !memberInfo.getMbrNo().equals(content.getDevUserId())) {	
					throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
				}
				
				// 검증 취소 요청
				ContentsVerifyService ctVerifyService  = new ContentsVerifyServiceImpl();
				
				ContentsVerify ctVerify = new ContentsVerify();
				boolean resultVerifyCancel = false;
				
				ctVerify.setInsBy(memberInfo.getMbrNo());
				ctVerify.setCid(content.getCid());
				
				resultVerifyCancel = ctVerifyService.updateContentVerifyCancel(ctVerify);
				
				if (resultVerifyCancel) {
					
					resultCode = "SUCCESS";
					
					// History Table Insert
					contentsHistoryService.insertContsHis(content.getCid());
				}
	
			} else {
				throw new NoticeException("Session이 존재하지 않습니다.");
			}
		
		} catch(Exception e) {
			resultCode = "FAIL";
			logger.error("요청 처리가 실패했습니다. 재시도해 주시기 바랍니다.", e);
		} finally {
			try {
				
				if(logger.isDebugEnabled()) {
					logger.debug("* result Code 	: {0}", new Object[] {resultCode});
				}
				
				jsonObject.put("resultCode", resultCode);
				
				writer = this.res.getWriter();
				writer.write(jsonObject.toString());
				
			} catch (Exception jse) {
				logger.error("Verify CancelRequest Fail" , jse);
			}
			
			if(writer != null) { writer.close(); }	
			
		}
	
	}

	
	/**
	 * Chagne Content Sale State
	 * : 판매상태 변경
	 * 
	 * @return
	 */
	public String ajaxChangeSaleStatus() {

		this.res.setContentType("text/html; charset=UTF-8");
		
		PrintWriter writer		= null;
		JSONObject jsonObject 	= new JSONObject(); 
		String resultCode		= "FAIL";

		
		if (SessionUtil.existMemberSession(this.req)) {
			
			try {
				
				if (content == null || content.getCid() == null) {
					throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
				}
	
				Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);	
				
				if(logger.isDebugEnabled()) {
					logger.debug("* CID 				: {0}", new Object[] {content.getCid()});
					logger.debug("* Session MBR_NO 		: {0}", new Object[] {memberInfo.getMbrNo()});
					logger.debug("* TAB GBN 			: {0}", new Object[] {this.getTabGbn()});
				}
				
				Contents oldContent = contentDetailInfoService.getContentDetailInfo(content.getCid());	
				
				// 상품을 등록한 사용자와 Login한 상용자가 다를 경우  Exception 처리 한다.
				if(oldContent == null || !memberInfo.getMbrNo().equals(oldContent.getDevUserId())) {	
					throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
				}
				
				// Content Sale Stat Change Service Call
				boolean result = false;
	
				result = contentsManagementService.updateChangeSaleStatusContent(oldContent, content);
				
				Contents newContent = contentDetailInfoService.getContentDetailInfo(content.getCid());	
				
				if(logger.isDebugEnabled()) {
					logger.debug(" * Change Sale Result : {0}", new Object[] {result});
				}
				
				if (result) {
					
					// E-mail 이메일발송
					String returlUrl = ReturnUrlGenerateUtil.rtnUrl(this.req);
					String toAddr = memberInfo.getEmailAddr();
					String toName = memberInfo.getMbrId();
					
					contentsManagementService.sendContentSaleStatMail(oldContent, newContent, returlUrl, toName, toAddr);				
					
					// History Table Insert
					contentsHistoryService.insertContsHis(newContent.getCid());

					// PD Sale Stat history
					contentsHistoryService.insertSaleStatHis(newContent.getCid(), newContent.getSaleStat(), newContent.getInsBy(), false);
					
					resultCode = "SUCCESS";
					
				}else {
					resultCode = "FAIL";
				}

			} catch(Exception e) {
				resultCode = "FAIL";
				logger.error("요청 처리가 실패했습니다. 재시도해 주시기 바랍니다.", e);
			} finally {
				
				try {
					
					if (logger.isDebugEnabled()) 
						logger.debug("* result Code 	: {0}", new Object[] {resultCode});
					
					jsonObject.put("resultCode", resultCode);
					
					writer = this.res.getWriter();
					writer.write(jsonObject.toString());
					
				} catch (Exception jse) {
					logger.error("Content Sale Stat Change Fail" , jse);
				}
				
				if(writer != null) { writer.close(); }	
				
			}
			
		}
		
		return SUCCESS;
	}

	/**
	 * Content Sale Changed History List
	 * : 상세내역보기
	 * 
	 * @return
	 */
	public String popContentSaleStatList () {
		
		if (SessionUtil.existMemberSession(this.req)) {
			// Contents null일 경우 Throw 처리 한다.
			if (content == null || content.getCid() == null) {
				throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
			}
			
			Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
			
			if(logger.isDebugEnabled()) {
				logger.debug("* CID 			: {0}", new Object[] {content.getCid()});
				logger.debug("* Session MBR_NO 	: {0}", new Object[] {memberInfo.getMbrNo()});
			}
			
			content = contentDetailInfoService.getContentBaseInfo(content.getCid());		// Content Base Info
	
			// 상품을 등록한 사용자와 Login한 상용자가 다를 경우  Exception 처리 한다.
			if(!memberInfo.getMbrNo().equals(content.getDevUserId())) {	
				throw new NoticeException("[ContentDetailInfoAction].popContentSaleStatList : Can't access Other user Product");
			}
			
			if(logger.isDebugEnabled()) {
				// Content Sale Changed History List :  CID
				logger.debug("상태변경 내역 조회 CID : {0}", new Object[] {content.getCid()});
			}
	
			SaleStatHistList = contentDetailInfoService.getContentSaleStatList(content.getCid());
		
		} else {
			throw new NoticeException("session이 존재하지 않습니다.");
		}
			
		return SUCCESS;
	}
	
	/**
	 * Content Preview Popup
	 * : 상품 미리보기
	 * 
	 * @return
	 */
	public String popItemPreview() {
		
		if (SessionUtil.existMemberSession(this.req)) {
			
			// Contents null일 경우 Throw 처리 한다.
			if (content == null || content.getCid() == null) {
				throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
			}
			
			Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
			
			logger.info("* CID 				: {0}", new Object[] {content.getCid()});
			logger.info("* Session MBR_NO 	: {0}", new Object[] {memberInfo.getMbrNo()});
			logger.info("* Session MBR_NM 	: {0}", new Object[] {memberInfo.getMbrNm()});
		
			content = contentDetailInfoService.getContentDetailInfo(content.getCid());		// Content Detail Info
			
			// 상품을 등록한 사용자와 Login한 상용자가 다를 경우  Exception 처리 한다.
			if(!memberInfo.getMbrNo().equals(content.getDevUserId())) {	
				throw new NoticeException("[ContentDetailInfoAction].popItemPreview : Can't access Other user Product");
			}
			
			contentImageMap = contentDetailInfoService.getListContentImage(content.getCid(), this.conf.getStaticProperties());			// select Content Images
			contentTagMap = contentDetailInfoService.getContentTagNameList(content.getCid());// select Content Tag Info
			
		}
		
		return SUCCESS;
	}
	
	/**
	 * Check Contents Session and Status
	 * : Session 및 Contents의 상태를 체크 한다.
	 * 
	 */
	public void ajaxStatusCheck() {
	
		this.res.setContentType("text/plain; charset=UTF-8");
		
		PrintWriter writer 		= null;
		JSONObject jsonObject 	= new JSONObject();  
		String resultCode		= "SUCCESS";
		
        try {
        	
        	// Contents null일 경우 Throw 처리 한다.
			if(content == null || content.getCid() == null) {			
				resultCode = "FAIL";
				throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
			}
    		
			// Session이 종료 됐을 경우 NS Code를 Jsp로 Put 한다.
			Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
			
    		if (memberInfo == null) resultCode = "NS";							
    		else {
	    		// 상품 정보 조회
	    		Contents oldContent = this.contentDetailInfoService.getContentDetailInfo(content.getCid());
	    		
	    		if(oldContent == null) {
	    			resultCode = "NC";
	    		} else {
	    		
		    		// 상품을 등록한 사용자와 Login한 상용자가 다를 경우  Exception 처리 한다.
					if(!memberInfo.getMbrNo().equals(oldContent.getDevUserId())) {
						throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
					}
					
		    		// 검증 대기, 검증중일 경우 상품 정보 수정 불가
		        	if (Constants.CODE_VERIFY_REQ.equals(oldContent.getVerifyPrgrYn()) 
		        			|| Constants.CODE_VERIFY_ING.equals(oldContent.getVerifyPrgrYn())) 
		        		resultCode = "VIEW";
		    	}
    		}
    
        	if(logger.isDebugEnabled()) {
        		logger.debug("* Content Ajax Check Stat CID : {0}", new Object[] {content.getCid()});
				logger.debug("* Session MBR_NO 				: {0}", new Object[] {memberInfo.getMbrNo()});
				logger.debug("* Content Check & Stat Result : {0}", new Object[] {resultCode});
				logger.debug("* TAB GBN 					: {0}", new Object[] {this.getTabGbn()});
			}
        	
		} catch (Exception e) {
			resultCode = "FAIL";
			logger.error("요청 처리가 실패했습니다. 재시도해 주시기 바랍니다.", e);
		} finally {
			
			try {
				
				if(logger.isDebugEnabled()) {
					logger.debug("* result Code 	: {0}", new Object[] {resultCode});
				}
				
				jsonObject.put("resultCode", resultCode);
				
				writer = this.res.getWriter();
				writer.write(jsonObject.toString());
				
			} catch (Exception jse) {
				logger.error("Check Contents Session and Status Fail" , jse);
			}
			
			if(writer != null) { writer.close(); }
		}
	}
	
	
	/**
	 * @return the resultMap
	 */
	public Map<String, Object> getResultContentsMap() {
		return resultContentsMap;
	}

	/**
	 * @param resultMap the resultMap to set
	 */
	public void setResultContentsMap(Map<String, Object> resultContentsMap) {
		this.resultContentsMap = resultContentsMap;
	}

	/**
	 * @return the contentsStatusList
	 */
	public List<Contents> getContentsStatusList() {
		return contentsStatusList;
	}

	/**
	 * @param contentsStatusList the contentsStatusList to set
	 */
	public void setContentsStatusList(List<Contents> contentsStatusList) {
		this.contentsStatusList = contentsStatusList;
	}

	/**
	 * @return the content
	 */
	public Contents getContent() {
		return content;
	}

	/**
	 * @param content the contents to set
	 */
	public void setContent(Contents content) {
		this.content = content;
	}

	/**
	 * @return the contentsStatusListCount
	 */
	public int getContentsStatusListCount() {
		return contentsStatusListCount;
	}

	/**
	 * @param contentsStatusListCount the contentsStatusListCount to set
	 */
	public void setContentsStatusListCount(int contentsStatusListCount) {
		this.contentsStatusListCount = contentsStatusListCount;
	}

	/**
	 * @return the tabGbn
	 */
	public String getTabGbn() {
		return tabGbn;
	}

	/**
	 * @param tabGbn the tabGbn to set
	 */
	public void setTabGbn(String tabGbn) {
		this.tabGbn = tabGbn;
	}

	/**
	 * @return the subContent
	 */
	public SubContents getSubContent() {
		return subContent;
	}

	/**
	 * @param subContent the subContent to set
	 */
	public void setSubContent(SubContents subContent) {
		this.subContent = subContent;
	}


	/**
	 * @return the saleStatHistList
	 */
	public List<SaleStatHist> getSaleStatHistList() {
		return SaleStatHistList;
	}

	/**
	 * @param saleStatHistList the saleStatHistList to set
	 */
	public void setSaleStatHistList(List<SaleStatHist> saleStatHistList) {
		SaleStatHistList = saleStatHistList;
	}
	/**
	 * @return the contentImageMap
	 */
	public Map<String, Object> getContentImageMap() {
		return contentImageMap;
	}

	/**
	 * @param contentImageMap the contentImageMap to set
	 */
	public void setContentImageMap(Map<String, Object> contentImageMap) {
		this.contentImageMap = contentImageMap;
	}

	/**
	 * @return the contentTagMap
	 */
	public Map<String, Object> getContentTagMap() {
		return contentTagMap;
	}

	/**
	 * @param contentTagMap the contentTagMap to set
	 */
	public void setContentTagMap(Map<String, Object> contentTagMap) {
		this.contentTagMap = contentTagMap;
	}
}
