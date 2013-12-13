package com.omp.dev.contents.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.json.JSONObject;

import com.omp.commons.action.BaseAction;
import com.omp.commons.commcode.CacheCommCode;
import com.omp.commons.commcode.model.CommCode;
import com.omp.commons.exception.NoticeException;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.product.service.ARMManagerService;
import com.omp.commons.product.service.ARMManagerServiceImpl;
import com.omp.commons.util.CommonUtil;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.FileUtil;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.SessionUtil;
import com.omp.dev.common.Constants;
import com.omp.dev.contents.model.ContentSprtPhone;
import com.omp.dev.contents.model.ContentUpdate;
import com.omp.dev.contents.model.Contents;
import com.omp.dev.contents.model.Provision;
import com.omp.dev.contents.model.SubContents;
import com.omp.dev.contents.service.ContentDetailInfoService;
import com.omp.dev.contents.service.ContentDetailInfoServiceImpl;
import com.omp.dev.contents.service.ContentDevBinaryService;
import com.omp.dev.contents.service.ContentDevBinaryServiceImpl;
import com.omp.dev.user.model.Session;

/**
 * Content Develop Info
 * 
 * @author snoopy
 *
 */
@SuppressWarnings("serial")
public class ContentDevBinaryAction extends BaseAction {

	private static GLogger logger = new GLogger(ContentDevBinaryAction.class);
	
	private    Contents 				content;
	private		SubContents				subContent;
	private 	ContentUpdate			contentUpdate;
	private 	ContentDevBinaryService contentDevBinaryService;
	private 	ContentDetailInfoService contentDetailInfoService;
	
	private		Map<String, Object> 	resultMap 			 = new HashMap<String, Object>();

	private		List<Contents>			resultVerifyCmntList;
	private		List<ContentUpdate>		resultContentUpdate;
	private 	int						subContentsCnt;
	private		InputStream 			inputStream;
	private		String 					redirectUrl;
	private		String 					tabGbn;
	private		String					modifySubContentId;			// 수정/삭제 하려는 서브 컨텐츠 Id
	private		String					modifySubContentIndex;		// 수정/삭제 하려는 서브 컨텐츠 Index
	
	public ContentDevBinaryAction() {
		content						= new Contents();
		subContent					= new SubContents();
		contentUpdate 				= new ContentUpdate();
		contentDetailInfoService	= new ContentDetailInfoServiceImpl();
		contentDevBinaryService		= new ContentDevBinaryServiceImpl();
	}
	
	@Override
	protected void prepareRequest() throws Exception {
		this.setTabGbn("DEVELOP");
		super.prepareRequest();
	}
	
	/**
	 * Content Develop Info View
	 * 
	 * @return
	 */
	public String contentDevInfoView() {
	
		List<Provision>		resultProvisionItemsList   = new ArrayList<Provision>();
		String forward 		= "android";
		
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
			
			// [목록] setting
			String searchType = content.getSearchType();
			String searchValue = content.getSearchValue();
			String saleSearchType = content.getSaleSearchType();
			int pageNo = content.getPage().getNo();
			
			// 컨텐츠 정보 조회
			content = contentDetailInfoService.getContentDetailInfo(content.getCid());					// select Content Base Info

			// 상품을 등록한 사용자와 Login한 상용자가 다를 경우  Exception 처리 한다.
			if(!memberInfo.getMbrNo().equals(content.getDevUserId())) {	
				throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
			}
			
			// 서브 컨텐츠 개수 조회
			subContentsCnt = Integer.parseInt(contentDevBinaryService.getSubContentsCount(content.getCid()));
			
			if (logger.isDebugEnabled()) logger.debug("* SubContents Count : {0}" , new Object[] {subContentsCnt});
			
			// 서브 컨텐츠 정보 조회
			resultMap = contentDevBinaryService.getSubContentsWithProvisionItemByCid(content.getCid());
			resultProvisionItemsList = contentDevBinaryService.getSubContentsProvisionItemList(content.getCid());
			
			// 대상단말 폰 리스트
			resultMap.put("targetPhoneList", contentDevBinaryService.getContentTargetPhoneList(content.getCid(), ""));
			
			// 업데이트 이력 리스트
			resultContentUpdate = contentDevBinaryService.getContentUpdateList(content.getCid());
			
			resultMap.put("subContentsCnt", subContentsCnt);
			resultMap.put("cid", content.getCid());
			resultMap.put("provisionItemsList", resultProvisionItemsList);
			resultMap.put("lcdSizeChkBox", CacheCommCode.getCommCode(Constants.PHONE_LCD_SIZE));		// LCD SIZE
			
			// [목록] setting
			content.setSearchType(searchType);
			content.setSearchValue(searchValue);
			content.setSaleSearchType(saleSearchType);
			content.getPage().setNo(pageNo); 
			
			// JSP switch
			forward = CacheCommCode.getCommCodeByFullCode(content.getVmType()).getAddField1();
			
		} else {
			return LOGIN;
		}
		
		// 검증진행상태가(검증요청, 검증중이면 화면 컨트롤을 못하도록 view화면으로 이동)
		if(this.content.getVerifyPrgrYn().equals(Constants.CODE_VERIFY_REQ) 
					|| this.content.getVerifyPrgrYn().equals(Constants.CODE_VERIFY_ING)) {
			forward += "view";
		}

		return forward;
	}
	
	/**
	 * 개발정보 수정
	 * 
	 * @return
	 */
	public String modifyContentDevInfo() {
	
		if (SessionUtil.existMemberSession(this.req)) {

			if (content == null || content.getCid() == null) {
				throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
			}
			
			if (logger.isDebugEnabled()) {
				logger.debug("Selected CID 				: {0}", new Object[] {content.getCid()});
				logger.debug("Selected DRM YN 			: {0}", new Object[] {content.getDrmYn()});
				logger.debug("Selected VERIFY SCNR NAME : {0}", new Object[] {content.getVerifyScnrUploadFileName()});
				logger.debug("Selected VERIFY SCNR YN  	: {0}", new Object[] {content.getVerifyScnrUploadDelYn()});
			}
			
			Contents oldContent = contentDetailInfoService.getContentDetailInfo(content.getCid());					// select Content Base Info
			Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
			
			// 상품을 등록한 사용자와 Login한 상용자가 다를 경우  Exception 처리 한다.
			if(!oldContent.getDevUserId().equals(memberInfo.getMbrNo())) {	
				throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
			}
			
			// 검증대기, 검증중 상태가 아닐 떄 수정 가능
			if(!Constants.CODE_VERIFY_REQ.equals(oldContent.getVerifyPrgrYn()) 
					&& !Constants.CODE_VERIFY_ING.equals(oldContent.getVerifyPrgrYn())) {
			
				// DRM_OPT_SET
				Properties prop = (Properties) this.req.getSession().getServletContext().getAttribute("CONF");
	
				if (Constants.YES.equals(content.getDrmYn())) {
					// Application 등록 infinite 
					content.setDrmSetOpt(Constants.CONTENT_DRM_INFINITE);
					content.setDrmSetVal(prop.getProperty("omp.dev.arm.infinite.value"));
				} else {
					content.setDrmSetOpt("");
					content.setDrmSetVal("");
				}
				
				content.setUpdBy(memberInfo.getMbrNo());
				content.setVmType(oldContent.getVmType());
				content.setProdPrc(oldContent.getProdPrc());
				content.setAdjRate(oldContent.getAdjRate());
				content.setAdjRateSkt(oldContent.getAdjRateSkt());
				content.setVerifyReqVer(oldContent.getVerifyReqVer());
				
				String resultValue = contentDevBinaryService.modifyContentDevInfo(content);
				
				if ("fileSizeError".equals(resultValue)) return "fileSizeError";
				else if ("fileExtentionError".equals(resultValue)) return "fileExtentionError";
			}
			
		} else {
			return LOGIN;
		}

		return SUCCESS;
	}
	
	/**
	 * androidMamifest.xml Upload
	 * 
	 * @return
	 */
	public String ajaxReadManifestXML() {
	
		//String resultName = SUCCESS;

		if (SessionUtil.existMemberSession(this.req)) {
			
			if (subContent.getRunFile().getRunUpload() == null) {
				throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
			}
			
			if (logger.isDebugEnabled()) {
				logger.debug("* Selected CID 		: {0}", new Object[] {subContent.getCid()});
				logger.debug("* Selected LCD Size : {0}", new Object[] {subContent.getProvisionItem()});
				logger.debug("* Selected RunFile  : {0}", new Object[] {subContent.getRunFile().getRunUpload()});
				logger.debug("* Selected DelYn  : {0}", new Object[] {subContent.getDelYn()});
				logger.debug("* Selected modifySubContentIndex  : {0}", new Object[] {modifySubContentIndex});
			}
			
			content = contentDetailInfoService.getContentDetailInfo(subContent.getCid());					// select Content Base Info
			Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
			
			// 상품을 등록한 사용자와 Login한 상용자가 다를 경우  Exception 처리 한다.
			if(!content.getDevUserId().equals(memberInfo.getMbrNo())) {	
				throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
			}
			
			Properties prop = (Properties) this.req.getSession().getServletContext().getAttribute("CONF");
			resultMap = contentDevBinaryService.ajaxReadManifestXML(content.getVmType(), subContent, prop);

			//if ("fileSizeError".equals(resultMap.get("uploadRunFileResult"))) return "fileSizeError";
			//else if ("fileExtentionError".equals(resultMap.get("uploadRunFileResult"))) return "fileExtentionError";
			//resultName = (String) resultMap.get("errorMessage");
			//resultName = resultName == null ? SUCCESS : resultName;
			
		} 
		
		if("Y".equals(subContent.getDelYn())) {
			modifySubContentIndex = modifySubContentIndex == null || "".equals(modifySubContentIndex) ? "0" : modifySubContentIndex;
			resultMap.put("subContentsCnt", Integer.parseInt(modifySubContentIndex));		// binary 개수 or Form ID
		} 
		
		return SUCCESS;
	}

	/**
	 * get SprtPhone List
	 * 
	 * @return
	 */
	public String ajaxSprtPhoneList() {
	
		if (SessionUtil.existMemberSession(this.req)) {

			if (subContent == null) {
				throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
			}
			
			if (subContent.getRunFile() == null) {
				throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
			}
			
			if (logger.isDebugEnabled()) {
				logger.debug("* Selected CIC  : {0}", new Object[] {subContent.getCid()});
				logger.debug("* Selected SCID : {0}", new Object[] {subContent.getScid()});
				logger.debug("* Selected RunFile  : {0}", new Object[] {subContent.getRunFile().getRunUpload()});
				logger.debug("* Selected DelYn  : {0}", new Object[] {subContent.getDelYn()});
			}
			
			Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
			content = contentDetailInfoService.getContentDetailInfo(subContent.getCid());	
			
			// 상품을 등록한 사용자와 Login한 상용자가 다를 경우  Exception 처리 한다.
			if(!content.getDevUserId().equals(memberInfo.getMbrNo())) {	
				throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
			}
			
			// 물리파일이 삭제 되었을 때
			if("Y".equals(subContent.getDelYn())) {	
				throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
			}
			
			resultMap.put("subContentsCnt", contentDevBinaryService.getSubContentsCount(content.getCid()));
			resultMap.put("sprtPhoneList", contentDevBinaryService.getContentSprtPhoneSearchList(content, subContent, this.conf.getStaticProperties()));
			
			List<ContentSprtPhone> targetPhoneList =  new ArrayList<ContentSprtPhone>();
			
			if(subContent.getScid() != null && !"".equals(subContent.getScid())) {
					targetPhoneList =	contentDevBinaryService.getContentTargetPhoneList(content.getCid(), subContent.getScid());
			}
		
			resultMap.put("targetPhoneList", targetPhoneList);
		}
		
		return SUCCESS;
	}
	
	/**
	 * 단말 검색
	 */
	public void ajaxCheckSprtPhone() {
		
		this.res.setContentType("text/plain; charset=UTF-8");
		
		PrintWriter writer = null;
		JSONObject jsonObject = new JSONObject(); 
		
		try {
			if (SessionUtil.existMemberSession(this.req)) {

				if (subContent == null) {
					throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
				}

				if (logger.isDebugEnabled()) {
					logger.debug("* Selected CID 			: {0}", new Object[] {subContent.getCid()});
					logger.debug("* Selected SCID 			: {0}", new Object[] {subContent.getScid()});
					logger.debug("* Selected Phone Model 	: {0}", new Object[] {subContent.getSprtPhoneModel()});
				}
				
				Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
				content = contentDetailInfoService.getContentDetailInfo(subContent.getCid());	
				
				// 상품을 등록한 사용자와 Login한 상용자가 다를 경우  Exception 처리 한다.
				if(!content.getDevUserId().equals(memberInfo.getMbrNo())) {	
					throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
				}
				
				int sprtPhoneModelCnt = contentDevBinaryService.getRegisteredContentSprtPhone(subContent);
				
				if (logger.isDebugEnabled()) logger.debug("* Selected Phone Model sprtPhoneModelCnt: {0}", new Object[] {sprtPhoneModelCnt});
				
				jsonObject.put("sprtPhoneModelCnt", sprtPhoneModelCnt);
				
			}
		
		} catch(Exception e) {
			logger.error("요청 처리가 실패했습니다. 재시도해 주시기 바랍니다.", e);
		} finally {
			try {
				
				writer = this.res.getWriter();
				writer.write(jsonObject.toString());
				
			} catch (IOException e) {
				logger.error("ajax CheckSprt Phone Fail.", e);
			}
			
			if(writer != null) { writer.close(); }	
			
		}
	
	}
	
	/**
	 * sub content 수정
	 * 
	 * @return
	 * @throws IOException
	 */
	public String ajaxModifySubContent() {

		if (SessionUtil.existMemberSession(this.req)) {

			if (subContent == null || subContent.getCid() == null) {
				throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
			}
			
			if (logger.isDebugEnabled()) {
				logger.debug("* Selected CID 			: {0}", new Object[] {subContent.getCid()});
				logger.debug("* Selected SCID 			: {0}", new Object[] {subContent.getScid()});
				logger.debug("* Selected LCD Size 		: {0}", new Object[] {subContent.getProvisionItem()});
				logger.debug("* Selected Phone Model 	: {0}", new Object[] {subContent.getSprtPhoneModel()});
				logger.debug("* Selected RunFile Path	: {0}", new Object[] {subContent.getRunFile().getRunFilePos()});
				logger.debug("* Selected RunFile Name	: {0}", new Object[] {subContent.getRunFile().getRunUploadFileName()});
				logger.debug("* Selected SubContent DelYn	: {0}", new Object[] {subContent.getDelYn()});
			}
			
			if (subContent.getProvisionItem() == null || subContent.getSprtPhoneModel() == null ) {
				return ERROR;
			} else {
			
				List<Provision>		resultProvisionItemsList   = new ArrayList<Provision>();
				Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
				content = contentDetailInfoService.getContentDetailInfo(subContent.getCid());	
					
				// 상품을 등록한 사용자와 Login한 상용자가 다를 경우  Exception 처리 한다.
				if(!content.getDevUserId().equals(memberInfo.getMbrNo())) {	
					throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
				}
				
				// 검증대기, 검증중 상태가 아닐 떄 수정 가능
				if(!Constants.CODE_VERIFY_REQ.equals(content.getVerifyPrgrYn()) 
						&& !Constants.CODE_VERIFY_ING.equals(content.getVerifyPrgrYn())) {
					
				
					if (subContent.getScid() != null && !"".equals(subContent.getScid())) {
						
						if(!"Y".equals(subContent.getDelYn())) {
							// SubContents 수정
							contentDevBinaryService.updateSubContent(content, subContent, this.conf.getStaticProperties());	
						}
						
					} else {
						// SubContents 생성
						contentDevBinaryService.insertSubContent(content, subContent, this.conf.getStaticProperties());		
					}
					
					// 서브 컨텐츠 개수 조회
					subContentsCnt = Integer.parseInt(contentDevBinaryService.getSubContentsCount(content.getCid()));
					
					if (logger.isDebugEnabled()) logger.debug("* SubContents Count : []", new Object[] {subContentsCnt});
					
					// 서브 컨텐츠 정보 조회
					resultMap = contentDevBinaryService.getSubContentsWithProvisionItemByCid(content.getCid());
					resultProvisionItemsList = contentDevBinaryService.getSubContentsProvisionItemList(content.getCid());
					
					// 대상단말 폰 리스트
					resultMap.put("targetPhoneList", contentDevBinaryService.getContentTargetPhoneList(content.getCid(), ""));
					
					// 업데이트 이력 리스트
					resultContentUpdate = contentDevBinaryService.getContentUpdateList(content.getCid());
					
					resultMap.put("subContentsCnt", subContentsCnt);
					resultMap.put("cid", content.getCid());
					resultMap.put("provisionItemsList", resultProvisionItemsList);
					resultMap.put("lcdSizeChkBox", CacheCommCode.getCommCode(Constants.PHONE_LCD_SIZE));		// LCD SIZE
				}
				
			}
		}

		return SUCCESS;
	}
	
	/**
	 * sub Contents modify/delete cancel
	 * @return
	 * @throws IOException
	 */
	public String ajaxModifySubContentCancel() throws IOException {
		

		if (SessionUtil.existMemberSession(this.req)) {

			if (subContent == null || subContent.getCid() == null) {
				throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
			}
			
			if (subContent.getScid() == null || "".equals(subContent.getScid())) {
				throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
			}
			
			if (logger.isDebugEnabled()) {
				logger.debug("* Selected CID 			: {0}", new Object[] {subContent.getCid()});
				logger.debug("* Selected SCID 			: {0}", new Object[] {subContent.getScid()});
				logger.debug("* Selected modifySubContentIndex  : {0}", new Object[] {modifySubContentIndex});
			}

			if (modifySubContentIndex != null && !"".equals(modifySubContentIndex)) {
				
				List<Provision>		resultProvisionItems   = new ArrayList<Provision>();
				SubContents		subContentResult   = new SubContents();
				Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
				content = contentDetailInfoService.getContentDetailInfo(subContent.getCid());	
					
				// 상품을 등록한 사용자와 Login한 상용자가 다를 경우  Exception 처리 한다.
				if(!memberInfo.getMbrNo().equals(content.getDevUserId())) {	
					throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
				}
	
				// Form ID
				subContentsCnt = Integer.parseInt(modifySubContentIndex);

				// 서브 컨텐츠 정보 조회
				subContentResult = contentDevBinaryService.getSubContentsWithProvisionItemByScid(subContent.getScid());
				resultProvisionItems = contentDevBinaryService.getSubContentProvisionItem(subContent.getScid());
				
				// 대상단말 폰 리스트
				resultMap.put("targetPhoneList", contentDevBinaryService.getContentTargetPhoneList(content.getCid(), subContent.getScid()));

				resultMap.put("subContentsCnt", subContentsCnt);
				resultMap.put("cid", content.getCid());
				resultMap.put("subContent", subContentResult);
				resultMap.put("provisionItemsList", resultProvisionItems);
				resultMap.put("lcdSizeChkBox", CacheCommCode.getCommCode(Constants.PHONE_LCD_SIZE));		// LCD SIZE
			
			} else {
				return ERROR;
			}
			
		}

		return SUCCESS;
	}
	
	/**
	 * remove RunFile
	 * 
	 * @return
	 * @throws IOException 
	 */
	public String ajaxRemoveRunFile() throws IOException {
	
		if (SessionUtil.existMemberSession(this.req)) {

			Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
			content = contentDetailInfoService.getContentDetailInfo(subContent.getCid());	
				
			// 상품을 등록한 사용자와 Login한 상용자가 다를 경우  Exception 처리 한다.
			if(!content.getDevUserId().equals(memberInfo.getMbrNo())) {	
				throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
			}
			
			if (logger.isDebugEnabled()) {
				logger.debug("* Selected CID 			: {0}", new Object[] {subContent.getCid()});
				logger.debug("* Selected SCID 			: {0}", new Object[] {subContent.getScid()});
				logger.debug("* Selected LCD Size 		: {0}", new Object[] {subContent.getProvisionItem()});
				logger.debug("* Selected Phone Model 	: {0}", new Object[] {subContent.getSprtPhoneModel()});
				logger.debug("* Selected RunFile Path	: {0}", new Object[] {subContent.getRunFile().getRunFilePos()});
				logger.debug("* Selected RunFile Name	: {0}", new Object[] {subContent.getRunFile().getRunUploadFileName()});
				logger.debug("* Selected SubContent DelYn	: {0}", new Object[] {subContent.getDelYn()});
			}
			
			// 검증대기, 검증중 상태가 아닐 떄 수정 가능
			if(!Constants.CODE_VERIFY_REQ.equals(content.getVerifyPrgrYn()) 
					&& !Constants.CODE_VERIFY_ING.equals(content.getVerifyPrgrYn())) {
				
				if (subContent.getScid() == null || "".equals(subContent.getScid())) {	// Form Delete
					resultMap.put("subContentsCnt", contentDevBinaryService.getSubContentsCount(subContent.getCid()));
					content.setCid(subContent.getCid());
					
				} else {							// SubContent Delete/RunFile Delete
					
				
					// sub content 삭제
					contentDevBinaryService.deleteSubContentsCount(subContent.getScid());
					
					// sub content 갯수 조회
					String subContentsCnt = contentDevBinaryService.getSubContentsCount(subContent.getCid());
					
					resultMap.put("subContentsCnt", subContentsCnt);
					
					content.setCid(subContent.getCid());
				}
				
				List<Provision>		resultProvisionItemsList   = new ArrayList<Provision>();
				
				// 서브 컨텐츠 개수 조회
				subContentsCnt = Integer.parseInt(contentDevBinaryService.getSubContentsCount(content.getCid()));
				
				if (logger.isDebugEnabled()) logger.debug("SubContents Count : " + subContentsCnt);
				
				// 서브 컨텐츠 정보 조회
				resultMap = contentDevBinaryService.getSubContentsWithProvisionItemByCid(content.getCid());
				resultProvisionItemsList = contentDevBinaryService.getSubContentsProvisionItemList(content.getCid());
				
				// 대상단말 폰 리스트
				resultMap.put("targetPhoneList", contentDevBinaryService.getContentTargetPhoneList(content.getCid(), ""));
				
				// 업데이트 이력 리스트
				resultContentUpdate = contentDevBinaryService.getContentUpdateList(content.getCid());
				
				resultMap.put("subContentsCnt", subContentsCnt);
				resultMap.put("cid", content.getCid());
				resultMap.put("provisionItemsList", resultProvisionItemsList);
				resultMap.put("lcdSizeChkBox", CacheCommCode.getCommCode(Constants.PHONE_LCD_SIZE));		// LCD SIZE
				
			}
			
		}

		return SUCCESS;
	}
	
	/**
	 * append RunFile
	 * : runfile input form append
	 * 
	 * @return
	 * @throws IOException 
	 */
	public String ajaxAppendRunFile() throws IOException {
		
		if (SessionUtil.existMemberSession(this.req)) {
			
			if (subContent == null) {
				throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
			}
			
			if (logger.isDebugEnabled()) {
				logger.debug("* Selected CID  : {0}", new Object[] {subContent.getCid()});
				logger.debug("* Selected SCID : {0}", new Object[] {subContent.getScid()});
			}
			
			// Form Id
			String  strSubContentsCnt = contentDevBinaryService.getSubContentsCount(subContent.getCid());
			//int subContentsCnt =  strSubContentsCnt != null ? Integer.parseInt(strSubContentsCnt) + 1 : 0;
			int subContentsCnt =  strSubContentsCnt != null ? Integer.parseInt(strSubContentsCnt) : 0;
			
			resultMap.put("subContentsCnt", subContentsCnt);
		}
		return SUCCESS;
	}
	
	/**
	 * 서브 컨텐츠 갯수 조회
	 */
	public void ajaxGetSubContentsCount() {
		
		this.res.setContentType("text/plain; charset=UTF-8");
		
		PrintWriter writer = null;
		JSONObject jsonObject = new JSONObject(); 
		
		try {
			
			if (SessionUtil.existMemberSession(this.req)) {

				if (content.getCid() == null || "".equals(content.getCid())) {
					throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
				}

				if (logger.isDebugEnabled()) {
					logger.debug("* Selected CID 			: {0}", new Object[] {content.getCid()});
				}
				
				Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
				content = contentDetailInfoService.getContentDetailInfo(content.getCid());	
				
				// 상품을 등록한 사용자와 Login한 상용자가 다를 경우  Exception 처리 한다.
				if(!content.getDevUserId().equals(memberInfo.getMbrNo())) {	
					throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
				}
				
				// 서브 컨텐츠 개수 조회
				subContentsCnt = Integer.parseInt(contentDevBinaryService.getSubContentsCount(content.getCid()));
				
				if (logger.isDebugEnabled()) logger.debug("* Selected Content SubContentsCnt: {0}", new Object[] {subContentsCnt});
				
				jsonObject.put("subContentsCnt", subContentsCnt);
				
			}
		
		} catch(Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재시도해 주시기 바랍니다.", e);
		} finally {
			try {
				
				writer = this.res.getWriter();
				writer.write(jsonObject.toString());
				
			} catch (IOException e) {
				logger.error("ajax Get SubContent Count Fail.", e);
			}
			
			if(writer != null) { writer.close(); }	
			
		}
	}
	
	/**
	 * Verify Request Comment List
	 * : 검증 내역 리스트
	 * 
	 * @return
	 */
	public String verifyCommentList() {
		
		if (SessionUtil.existMemberSession(this.req)) {
			
			if (content == null || content.getCid() == null) {
				throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
			}
			
			if (logger.isDebugEnabled()) {
				logger.debug("* Selected CID : {0}", new Object[] {content.getCid()});
			}
			
			List<CommCode> commCodeList = CacheCommCode.getCommCode(Constants.VERIFY_COMMENT_CD_GRP);
			resultVerifyCmntList = contentDevBinaryService.getVerifyCommentList(content.getCid());
			
			// 검증 내역 리스트가 존재할 경우
			if(resultVerifyCmntList != null && resultVerifyCmntList.size() != 0 
					&& resultVerifyCmntList.get(0).getVerifyCommentCd() != null) {
				
				String[][] resultVerifyCd      = new String[resultVerifyCmntList.size()][];
				StringBuffer[] resultVerifyNm  = new StringBuffer[resultVerifyCmntList.size()];
				
				for(int i = 0; i < resultVerifyCmntList.size(); i++) {
					
					resultVerifyCd[i] = resultVerifyCmntList.get(i).getVerifyCommentCd().split(";");
				}
				
				for(int i = 0; i < resultVerifyCd.length; i++) {
					
					resultVerifyNm[i] = new StringBuffer();
					
					for(int j = 0; j < resultVerifyCd[i].length; j++) {

						for(int k = 0; k < commCodeList.size(); k++) {
							
							if(commCodeList.get(k).getDtlFullCd().equals(resultVerifyCd[i][j])) {
						
								if(Constants.VERIFY_COMMENT_CD_MANIFEST_ETC.equals(resultVerifyCd[i][j])) {
									resultVerifyNm[i].append(resultVerifyCmntList.get(i).getVerifyEtcCmt());
								} else {
									resultVerifyNm[i].append(commCodeList.get(k).getCdNm());
								}
								
								if (j < resultVerifyCd[i].length - 1) {
									resultVerifyNm[i].append(", ");
								}
							
							}
						}
					}
					
					resultVerifyCmntList.get(i).setVerifyCommentNm(resultVerifyNm[i].toString());
				}
				
			}

		}
		return SUCCESS;
	}
	
	/**
	 * 업데이트 내역  입력
	 * 
	 * @return
	 */
	public String ajaxUpdateManagementWrite() {
	
		if (SessionUtil.existMemberSession(this.req)) {
			
			if (contentUpdate == null) {
				throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
			}
			
			// 컨텐츠 정보 조회
			content = contentDetailInfoService.getContentDetailInfo(contentUpdate.getCid());	

			// 로그인한 사용자의 Session을 가져 온다.
			Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
		
			if (logger.isDebugEnabled()) {
				logger.debug("* CID   		: {0}", new Object[] {content.getCid()});
				logger.debug("* 업데이트 내용   : {0}", new Object[] {contentUpdate.getUpdateText()});
			}
			
			contentUpdate.setCid(content.getCid());
			contentUpdate.setUpdateText(CommonUtil.removeSpecial(contentUpdate.getUpdateText()));
			contentUpdate.setInsBy(memberInfo.getMbrNo());
			contentUpdate.setUpdBy(memberInfo.getMbrNo());
			
			// 업데이트 내역 insert
			contentDevBinaryService.insertUpdateHistory(contentUpdate);
			
			resultContentUpdate = contentDevBinaryService.getContentUpdateList(content.getCid());
		}

		return SUCCESS;
		
	}
	
	/**
	 * 업데이트 내역 수정
	 * 
	 * @return
	 */
	public String ajaxUpdateManagementUpdate() {
		
		if (SessionUtil.existMemberSession(this.req)) {
			
			if (contentUpdate == null) {
				throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
			}
			
			// 컨텐츠 정보 조회
			content = contentDetailInfoService.getContentDetailInfo(contentUpdate.getCid());	
	
			// 로그인한 사용자의 Session을 가져 온다.
			Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
		
			if(logger.isDebugEnabled()) {
				logger.debug("* CID   		  : {0}", new Object[] {content.getCid()});
				logger.debug("* 업데이트 SEQ   : {0}", new Object[] {contentUpdate.getUpdateSeq()});
				logger.debug("* 업데이트 내용   : {0}", new Object[] {contentUpdate.getUpdateText()});
			}
			
			contentUpdate.setCid(content.getCid());
			contentUpdate.setUpdateText(CommonUtil.removeSpecial(contentUpdate.getUpdateText()));
			contentUpdate.setInsBy(memberInfo.getMbrNo());
			contentUpdate.setUpdBy(memberInfo.getMbrNo());
			
			// 업데이트 내역 update
			contentDevBinaryService.updateUpdateHistory(contentUpdate);
			
			// 업데이트 내역 리스트
			resultContentUpdate = contentDevBinaryService.getContentUpdateList(content.getCid());
		}

		return SUCCESS;
		
	}
	
	/**
	 * 업데이트 내역 삭제
	 * 
	 * @return
	 */
	public String ajaxUpdateManagementDelete() {
		
		if (SessionUtil.existMemberSession(this.req)) {
			
			if (contentUpdate == null) {
				throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
			}
			
			// 컨텐츠 정보 조회
			content = contentDetailInfoService.getContentDetailInfo(contentUpdate.getCid());	
			
			if(logger.isDebugEnabled()) {
				logger.debug("* CID   		: {0}", new Object[] {content.getCid()});
				logger.debug("* 업데이트 SEQ   : {0}", new Object[] {contentUpdate.getUpdateSeq()});
				logger.debug("* 업데이트 내용   : {0}", new Object[] {contentUpdate.getUpdateText()});
			}
			
			contentUpdate.setCid(content.getCid());
			
			// 업데이트 내역 delete
			contentDevBinaryService.deleteUpdateHistory(contentUpdate);
			
			// 업데이트 내역 리스트
			resultContentUpdate = contentDevBinaryService.getContentUpdateList(content.getCid());
		}
	
		return SUCCESS;
		
	}
	
	/**
	 * 등록된 Test 폰 여부 조회 For Application DRM 다운로드 
	 */
	public void licenseForDeveloper() {

		this.res.setContentType("text/plain; charset=UTF-8");
		
		PrintWriter writer = null;
		JSONObject jsonObject = new JSONObject();
		
		try{
			
			jsonObject.put("resultCode", "error");

			if (SessionUtil.existMemberSession(this.req)) {
				
				if (this.content == null) throw new NoticeException("licenseForDeveloper cid is null");
				
				logger.info("[getLicenseForDeveloper] 상품 CID : {0}", new Object[] {content.getCid()});
				
				// 로그인한 사용자의 Session을 가져 온다.
				Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
				
				// 등록된 테스트 폰 조회
				String device = contentDevBinaryService.getOtaDeveloperPhoneList(memberInfo.getMbrNo());		// 컨텐츠 개발 회원의 휴대폰번호 리스트
				
				// 등록된 테스트 폰이 존재하지 않을 경우 error, 존재할경우 success
				if(device == null || "0".equals(device)){
					jsonObject.put("resultCode", "error");
				} else {
					jsonObject.put("resultCode", "success");
				}
				
			}
			
		} catch(Exception e){
			throw new ServiceException("ARM Check Developer Test Phone Error", e);
		} finally {
			
			try {
				
				writer = this.res.getWriter();
				writer.write(jsonObject.toString());
				
			} catch(Exception jse) {
				logger.error("license download Fail.", jse);
			}
			
			if(writer != null) { writer.close(); }
		}
		
	}
	
	/**
	 * Application DRM 다운로드 
	 * 
	 * @return
	 * @throws IOException
	 */
	public String licenseForDeveloperDownload() throws IOException {
		
		BufferedWriter wt = null;
		ARMManagerService service = new ARMManagerServiceImpl();
		
		try{
			
			if (this.content == null) {
				throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
			}
			
			if (logger.isDebugEnabled()) {
				logger.debug("[License For Developer Down] 상품 CID : {0}", new Object[] {content.getCid()});
			}
			
			// 로그인한 사용자의 Session을 가져 온다.
			Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
			
			// 컨텐츠 정보 조회
			content = contentDetailInfoService.getContentDetailInfo(content.getCid());	
			
			// ARM 연동 - 개발자용 라이센스 발급
			String strXmlData = service.connectARMReqVerificationLicense(memberInfo.getMbrNo(), content.getAid());
			
			StringBuffer filePath = new StringBuffer();
			StringBuffer fileName = new StringBuffer();
			String saveFileName = "";
			
			fileName.append(DateUtil.getGeneralTimeStampString());
			fileName.append("_");
			fileName.append(this.conf.getString("omp.common.arm.licenseFileName", "licenseForDeveloperTW.gomp"));
			saveFileName = this.conf.getString("omp.common.arm.licenseFileName", "licenseForDeveloperTW.gomp");
			
			filePath.append(this.conf.getString("omp.common.path.temp.base"));
			filePath.append(File.separator + "product" + File.separator + "amr" + File.separator);
			filePath.append(fileName.toString());

			File file = new File(filePath.toString());

			if (file.isDirectory()) {
				FileUtil.write( file, strXmlData );
			} else {
				file.getParentFile().mkdirs();
				FileUtil.write( file, strXmlData );
			}
			
			// 개발자용 라이센스 다운로드
			this.setDownloadFile(file, "binary/octet-stream", saveFileName);

//			wt = new BufferedWriter(new FileWriter(file));				// 파일쓰기를 위한 객체 생성
//			wt.write(strXmlData);										// 파일에 아래 문자들을 출력
//			inputStream = new FileInputStream(file);					// 파일 핸들 반환
			
		} catch(Exception e){
			throw new ServiceException("요청 처리가 실패했습니다. 재시도해 주시기 바랍니다.", e);
		} finally{
			if(wt != null) { wt.close(); }
		}
	
		return SUCCESS;
	}

	/**
	 * @return the content
	 */
	public Contents getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(Contents content) {
		this.content = content;
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
	 * @return the resultContentUpdate
	 */
	public List<ContentUpdate> getResultContentUpdate() {
		return resultContentUpdate;
	}

	/**
	 * @param resultContentUpdate the resultContentUpdate to set
	 */
	public void setResultContentUpdate(List<ContentUpdate> resultContentUpdate) {
		this.resultContentUpdate = resultContentUpdate;
	}

	/**
	 * @return the resultVerifyCmntList
	 */
	public List<Contents> getResultVerifyCmntList() {
		return resultVerifyCmntList;
	}

	/**
	 * @param resultVerifyCmntList the resultVerifyCmntList to set
	 */
	public void setResultVerifyCmntList(List<Contents> resultVerifyCmntList) {
		this.resultVerifyCmntList = resultVerifyCmntList;
	}

	/**
	 * @return the resultMap
	 */
	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	/**
	 * @param resultMap the resultMap to set
	 */
	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	/**
	 * @return the contentUpdate
	 */
	public ContentUpdate getContentUpdate() {
		return contentUpdate;
	}

	/**
	 * @return the redirectUrl
	 */
	public String getRedirectUrl() {
		return redirectUrl;
	}

	/**
	 * @param redirectUrl the redirectUrl to set
	 */
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
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
	 * @param contentUpdate the contentUpdate to set
	 */
	public void setContentUpdate(ContentUpdate contentUpdate) {
		this.contentUpdate = contentUpdate;
	}

	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	/**
	 * @return the modifySubContentId
	 */
	public String getModifySubContentId() {
		return modifySubContentId;
	}

	/**
	 * @param modifySubContentId the modifySubContentId to set
	 */
	public void setModifySubContentId(String modifySubContentId) {
		this.modifySubContentId = modifySubContentId;
	}

	/**
	 * @return the modifySubContentIndex
	 */
	public String getModifySubContentIndex() {
		return modifySubContentIndex;
	}

	/**
	 * @param modifySubContentIndex the modifySubContentIndex to set
	 */
	public void setModifySubContentIndex(String modifySubContentIndex) {
		this.modifySubContentIndex = modifySubContentIndex;
	}
	
	
}
