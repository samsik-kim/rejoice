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

import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
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
	private		String 						redirectUrl;
	private		String 						tabGbn;
	
	
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
		
		logger.info("==== Content ContentDevBinaryAction.contentDevInfoView START ====");	
		
		List<Provision>		resultProvisionItemsList   = new ArrayList<Provision>();
		String forward = "android";
		
		if (SessionUtil.existMemberSession(this.req)) {
			
			// Contents null일 경우 Throw 처리 한다.
			if (content==null) {
				throw new NoticeException("[ContentDetailInfoAction].contentDevInfoView : Can't acquire Contents null");
			}
	
			Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
			
			logger.info("* CID 				: {0}", new Object[] {content.getCid()});
			logger.info("* Session MBR_NO 	: {0}", new Object[] {memberInfo.getMbrNo()});
			
			// 컨텐츠 정보 조회
			content = contentDetailInfoService.getContentDetailInfo(content.getCid());					// select Content Base Info
			
			// 상품을 등록한 사용자와 Login한 상용자가 다를 경우  Exception 처리 한다.
			if(!content.getDevUserId().equals(memberInfo.getMbrNo())) {	
				throw new NoticeException("[ContentDetailInfoAction].contentDevInfoView : Can't access Other user Product");
			}
			
			// 서브 컨텐츠 개수 조회
			subContentsCnt = Integer.parseInt(contentDevBinaryService.getSubContentsCount(content.getCid()));
			
			if (logger.isDebugEnabled()) logger.debug("SubContents Count : " + subContentsCnt);
			
			// 서브 컨텐츠 정보 조회
			resultMap = contentDevBinaryService.getSubContentsWithProvisionItem(content.getCid());
			resultProvisionItemsList = contentDevBinaryService.getSubContentsProvisionItem(content.getCid());
			
			// 대상단말 폰 리스트
			resultMap.put("targetPhoneList", contentDevBinaryService.getContentTargetPhoneList(content.getCid(), ""));
			
			// 업데이트 이력 리스트
			resultContentUpdate = contentDevBinaryService.getContentUpdateList(content.getCid());
			
			resultMap.put("subContentsCnt", subContentsCnt);
			resultMap.put("cid", content.getCid());
			resultMap.put("provisionItemsList", resultProvisionItemsList);
			resultMap.put("lcdSizeChkBox", CacheCommCode.getCommCode(Constants.PHONE_LCD_SIZE));		// LCD SIZE
		} else {
			return LOGIN;
		}
		
		// 검증진행상태가(검증요청, 검증중이면 화면 컨트롤을 못하도록 view화면으로 이동)
		if(this.content.getVerifyPrgrYn().equals(Constants.CODE_VERIFY_REQ) 
					|| this.content.getVerifyPrgrYn().equals(Constants.CODE_VERIFY_ING)) {
			forward += "view";
		}
		
		logger.info("==== Content ContentDevBinaryAction.contentDevInfoView END  ====");	
		
		return forward;
	}
	
	public String modifyContentDevInfo() {
		
		logger.info("==== Content ContentDevBinaryAction.modifyContentDevInfo START ====");	

		if (SessionUtil.existMemberSession(this.req)) {

			if (content == null) {
				throw new NoticeException("[ContentDevBinaryoAction].modifyContentDevInfo : Can't acquire contents null");
			}
			
			if (logger.isDebugEnabled()) {
				logger.debug("Selected CID : " + content.getCid() );
				logger.debug("Selected DRM YN : " + content.getDrmYn());
				logger.debug("Selected VERIFY SCNR NAME : " + content.getVerifyScnrUploadFileName());
				logger.debug("Selected VERIFY SCNR YN  : "  + content.getVerifyScnrUploadDelYn());
			}
			
			Contents oldContent = contentDetailInfoService.getContentDetailInfo(content.getCid());					// select Content Base Info
			Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
			
			// 상품을 등록한 사용자와 Login한 상용자가 다를 경우  Exception 처리 한다.
			if(!oldContent.getDevUserId().equals(memberInfo.getMbrNo())) {	
				throw new NoticeException("[ContentDetailInfoAction].contentDevInfoView : Can't access Other user Product");
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

		logger.info("==== Content ContentDevBinaryAction.modifyContentDevInfo END   ====");	

		return SUCCESS;
	}
	
	/**
	 * androidMamifest.xml Upload
	 * 
	 * @return
	 */
	public String ajaxReadManifestXML() {
		
		logger.info("==== Content ContentDevBinaryAction.ajaxReadManifestXML START ====");	
		//String resultName = SUCCESS;

		try {
			if (SessionUtil.existMemberSession(this.req)) {

				if (subContent.getRunFile().getRunUpload() == null) {
					throw new NoticeException("[ContentDevBinaryoAction].ajaxReadManifestXML : Can't acquire SubContents null");
				}
				
				if (logger.isDebugEnabled()) {
					logger.debug("Selected CID : " + subContent.getCid() );
					logger.debug("Selected LCD Size : " + subContent.getProvisionItem() );
					logger.debug("Selected RunFile : " + subContent.getRunFile().getRunUpload() );
				}
				
				content = contentDetailInfoService.getContentDetailInfo(subContent.getCid());					// select Content Base Info
				Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
				
				// 상품을 등록한 사용자와 Login한 상용자가 다를 경우  Exception 처리 한다.
				if(!content.getDevUserId().equals(memberInfo.getMbrNo())) {	
					throw new NoticeException("[ContentDetailInfoAction].contentDevInfoView : Can't access Other user Product");
				}
				
				Properties prop = (Properties) this.req.getSession().getServletContext().getAttribute("CONF");
				resultMap = contentDevBinaryService.ajaxReadManifestXML(content.getVmType(), subContent, prop);

				//if ("fileSizeError".equals(resultMap.get("uploadRunFileResult"))) return "fileSizeError";
				//else if ("fileExtentionError".equals(resultMap.get("uploadRunFileResult"))) return "fileExtentionError";
				//resultName = (String) resultMap.get("errorMessage");
				//resultName = resultName == null ? SUCCESS : resultName;
			} else {
				return LOGIN;
			}
			
		} catch(Exception e) {
			logger.error("ajaxReadManifestXML ()", e);
		} 
		
		logger.info("==== Content ContentDevBinaryAction.ajaxReadManifestXML END   ====");	

		return SUCCESS;
	}

	/**
	 * get SprtPhone List
	 * @return
	 */
	public String ajaxSprtPhoneList() {
	
		logger.info("==== Content ContentDevBinaryAction.ajaxSprtPhoneList START ====");	
		
		if (SessionUtil.existMemberSession(this.req)) {

			if (subContent == null) {
				throw new NoticeException("[ContentDevBinaryoAction].ajaxReadManifestXML : Can't acquire SubContents null");
			}
			
			if (logger.isDebugEnabled()) {
				logger.debug("* Selected CIC : " + subContent.getCid());
				logger.debug("* Selected SCID : " + subContent.getScid() );
			}
			
			Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
			content = contentDetailInfoService.getContentDetailInfo(subContent.getCid());	
			
			// 상품을 등록한 사용자와 Login한 상용자가 다를 경우  Exception 처리 한다.
			if(!content.getDevUserId().equals(memberInfo.getMbrNo())) {	
				throw new NoticeException("[ContentDetailInfoAction].contentDevInfoView : Can't access Other user Product");
			}
			
			resultMap.put("subContentsCnt", contentDevBinaryService.getSubContentsCount(content.getCid()));
			resultMap.put("sprtPhoneList", contentDevBinaryService.getContentSprtPhoneSearchList(content, subContent, this.conf.getStaticProperties()));
			
			List<ContentSprtPhone> targetPhoneList =  new ArrayList<ContentSprtPhone>();
			
			if(subContent.getScid() != null && !"".equals(subContent.getScid())) {
					targetPhoneList =	contentDevBinaryService.getContentTargetPhoneList(content.getCid(), subContent.getScid());
			}
		
			resultMap.put("targetPhoneList", targetPhoneList);
		}
		
		logger.info("==== Content ContentDevBinaryAction.ajaxSprtPhoneList END   ====");
		
		return SUCCESS;
	}
	
	/**
	 * 단말 검색
	 */
	public void ajaxCheckSprtPhone() {
		
		logger.info("==== Content ContentDevBinaryAction.ajaxCheckSprtPhone START ====");	
		
		HttpServletResponse response = this.res;
		PrintWriter writer = null;
		JSONObject jsonObject = new JSONObject(); 
		response.setContentType("text/plain; charset=UTF-8");
		
		try {
			if (SessionUtil.existMemberSession(this.req)) {

				if (subContent == null) {
					throw new NoticeException("[ContentDevBinaryoAction].ajaxCheckSprtPhone : Can't acquire SubContents null");
				}

				if (logger.isDebugEnabled()) {
					logger.debug("* Selected CID : " + subContent.getCid() );
					logger.debug("* Selected SCID : " + subContent.getScid() );
					logger.debug("* Selected Phone Model : " + subContent.getSprtPhoneModel() );
				}
				
				Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
				content = contentDetailInfoService.getContentDetailInfo(subContent.getCid());	
				
				// 상품을 등록한 사용자와 Login한 상용자가 다를 경우  Exception 처리 한다.
				if(!content.getDevUserId().equals(memberInfo.getMbrNo())) {	
					throw new NoticeException("[ContentDetailInfoAction].contentDevInfoView : Can't access Other user Product");
				}
				
				int sprtPhoneModelCnt = contentDevBinaryService.getRegisteredContentSprtPhone(subContent);
				
				logger.debug("* Selected Phone Model sprtPhoneModelCnt: " + sprtPhoneModelCnt );
				
				jsonObject.put("sprtPhoneModelCnt", sprtPhoneModelCnt);
			}
		
		} catch(Exception e) {
			logger.error("ajaxCheckSprtPhone ()", e);
		} finally {
			try {
				writer = response.getWriter();
				writer.write(jsonObject.toString());
			} catch (IOException e) {
				logger.error("ajaxCheckSprtPhone.response.getWriter()", e);
			}
			
			if(writer != null) { writer.close(); }	
			
		}
		
		logger.info("==== Content ContentDevBinaryAction.ajaxCheckSprtPhone END   ====");
	}
	
	public String ajaxModifySubContent() throws IOException {
		
		logger.info("==== Content ContentDevBinaryAction.ajaxModifySubContent START ====");	

		try {
			if (SessionUtil.existMemberSession(this.req)) {

				if (subContent == null || subContent.getCid() == null) {
					throw new NoticeException("[ContentDevBinaryoAction].ajaxModifySubContent : Can't acquire SubContents null");
				}
				
				if (logger.isDebugEnabled()) {
					logger.debug("* Selected CID : " + subContent.getCid() );
					logger.debug("* Selected SCID : " + subContent.getScid() );
					logger.debug("* Selected LCD Size : " + subContent.getProvisionItem() );
					logger.debug("* Selected Phone Model : " + subContent.getSprtPhoneModel() );
					logger.debug("* Selected RunFile Path: " + subContent.getRunFile().getRunFilePos() );
					logger.debug("* Selected RunFile Name: " + subContent.getRunFile().getRunUploadFileName() );
				}
				
				if (subContent.getProvisionItem() == null || subContent.getSprtPhoneModel() == null ) {
					return ERROR;
				} else {
					
					List<Provision>		resultProvisionItemsList   = new ArrayList<Provision>();
					Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
					content = contentDetailInfoService.getContentDetailInfo(subContent.getCid());	
						
					// 상품을 등록한 사용자와 Login한 상용자가 다를 경우  Exception 처리 한다.
					if(!content.getDevUserId().equals(memberInfo.getMbrNo())) {	
						throw new NoticeException("[ContentDdevBinaryAction].ajaxCreateSubContent : Can't access Other user Product");
					}
					
					// 검증대기, 검증중 상태가 아닐 떄 수정 가능
					if(!Constants.CODE_VERIFY_REQ.equals(content.getVerifyPrgrYn()) 
							&& !Constants.CODE_VERIFY_ING.equals(content.getVerifyPrgrYn())) {
						
					
						if (subContent.getScid() != null && !"".equals(subContent.getScid())) {
							// SubContents 수정
							contentDevBinaryService.updateSubContent(content, subContent, this.conf.getStaticProperties());	
						} else {
							// SubContents 생성
							contentDevBinaryService.insertSubContent(content, subContent, this.conf.getStaticProperties());		
						}
						
						// 서브 컨텐츠 개수 조회
						subContentsCnt = Integer.parseInt(contentDevBinaryService.getSubContentsCount(content.getCid()));
						
						if (logger.isDebugEnabled()) logger.debug("SubContents Count : " + subContentsCnt);
						
						// 서브 컨텐츠 정보 조회
						resultMap = contentDevBinaryService.getSubContentsWithProvisionItem(content.getCid());
						resultProvisionItemsList = contentDevBinaryService.getSubContentsProvisionItem(content.getCid());
						
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
		
		} catch(Exception e) {
			logger.error("ajaxModifySubContent ()", e);
		} 
		
		logger.info("==== Content ContentDevBinaryAction.ajaxModifySubContent END   ====");
		
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
				throw new NoticeException("[ContentDdevBinaryAction].ajaxCreateSubContent : Can't access Other user Product");
			}
			
			// 검증대기, 검증중 상태가 아닐 떄 수정 가능
			if(!Constants.CODE_VERIFY_REQ.equals(content.getVerifyPrgrYn()) 
					&& !Constants.CODE_VERIFY_ING.equals(content.getVerifyPrgrYn())) {
				
				if (subContent.getScid() == null || "".equals(subContent.getScid())) {	// Form Delete
					resultMap.put("subContentsCnt", contentDevBinaryService.getSubContentsCount(subContent.getCid()));
					content.setCid(subContent.getCid());
					
				} else {							// SubContent Delete/RunFile Delete
					
				
					contentDevBinaryService.deleteSubContentsCount(subContent.getScid());
					
					String subContentsCnt = contentDevBinaryService.getSubContentsCount(subContent.getCid());
					
					resultMap.put("subContentsCnt", subContentsCnt);
					
					content.setCid(subContent.getCid());
				}
				
				List<Provision>		resultProvisionItemsList   = new ArrayList<Provision>();
				
				// 서브 컨텐츠 개수 조회
				subContentsCnt = Integer.parseInt(contentDevBinaryService.getSubContentsCount(content.getCid()));
				
				if (logger.isDebugEnabled()) logger.debug("SubContents Count : " + subContentsCnt);
				
				// 서브 컨텐츠 정보 조회
				resultMap = contentDevBinaryService.getSubContentsWithProvisionItem(content.getCid());
				resultProvisionItemsList = contentDevBinaryService.getSubContentsProvisionItem(content.getCid());
				
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
	 * 
	 * @return
	 * @throws IOException 
	 */
	public String ajaxAppendRunFile() throws IOException {
		
		if (SessionUtil.existMemberSession(this.req)) {
			
			if (subContent == null) {
				throw new NoticeException("[ContentDevBinaryoAction].ajaxModifySubContent : Can't acquire SubContents null");
			}
			
			if (logger.isDebugEnabled()) {
				logger.debug("* Selected CID : " + subContent.getCid() );
				logger.debug("* Selected SCID : " + subContent.getScid() );
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
	 * Verify Request Comment List
	 * 
	 * @return
	 */
	public String verifyCommentList() {
		
		if (SessionUtil.existMemberSession(this.req)) {
			
			if (content == null) {
				throw new NoticeException("[ContentDevBinaryoAction].verifyCommentList : Can't acquire Contents null");
			}
			
			if (logger.isDebugEnabled()) {
				logger.debug("* Selected CID : " + content.getCid() );
			}
			
			List<CommCode> commCodeList = CacheCommCode.getCommCode(Constants.VERIFY_COMMENT_CD_GRP);
			resultVerifyCmntList = contentDevBinaryService.getVerifyCommentList(content.getCid());
			
			if(resultVerifyCmntList != null && resultVerifyCmntList.size() != 0 
					&& resultVerifyCmntList.get(0).getVerifyCommentCd() != null) {
				
				String[][] resultVerifyCd = new String[resultVerifyCmntList.size()][];
				StringBuffer[] resultVerifyNm = new StringBuffer[resultVerifyCmntList.size()];
				
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
	
	public String ajaxUpdateManagementWrite() {
		logger.info("==== Content ContentDevBinaryAction.ajaxUpdateManagementWrite START   ====");
		if (SessionUtil.existMemberSession(this.req)) {
			
			if (contentUpdate == null) {
				throw new NoticeException("[ContentDevBinaryoAction].ajaxUpdateManagementWrite : Can't acquire Contents null");
			}
			
			// 컨텐츠 정보 조회
			content = contentDetailInfoService.getContentDetailInfo(contentUpdate.getCid());	

			// 로그인한 사용자의 Session을 가져 온다.
			Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
		
			logger.info("CID   : {0}", new Object[] {content.getCid()});
			logger.info("업데이트 내용   : {0}", new Object[] {contentUpdate.getUpdateText()});
	
			contentUpdate.setCid(content.getCid());
			contentUpdate.setUpdateText(CommonUtil.removeSpecial(contentUpdate.getUpdateText()));
			contentUpdate.setInsBy(memberInfo.getMbrNo());
			contentUpdate.setUpdBy(memberInfo.getMbrNo());
			
			contentDevBinaryService.insertUpdateHistory(contentUpdate);
			
			resultContentUpdate = contentDevBinaryService.getContentUpdateList(content.getCid());
		}

		logger.info("==== Content ContentDevBinaryAction.ajaxUpdateManagementWrite END   ====");
		
		return SUCCESS;
		
	}
	
	public String ajaxUpdateManagementUpdate() {
		logger.info("==== Content ContentDevBinaryAction.ajaxUpdateManagementUpdate START   ====");
		if (SessionUtil.existMemberSession(this.req)) {
			
			if (contentUpdate == null) {
				throw new NoticeException("[ContentDevBinaryoAction].ajaxUpdateManagementWrite : Can't acquire Contents null");
			}
			
			// 컨텐츠 정보 조회
			content = contentDetailInfoService.getContentDetailInfo(contentUpdate.getCid());	
	
			// 로그인한 사용자의 Session을 가져 온다.
			Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
		
			logger.info("CID   : {0}", new Object[] {content.getCid()});
			logger.info("업데이트 SEQ   : {0}", new Object[] {contentUpdate.getUpdateSeq()});
			logger.info("업데이트 내용   : {0}", new Object[] {contentUpdate.getUpdateText()});
	
			contentUpdate.setCid(content.getCid());
			contentUpdate.setUpdateText(CommonUtil.removeSpecial(contentUpdate.getUpdateText()));
			contentUpdate.setInsBy(memberInfo.getMbrNo());
			contentUpdate.setUpdBy(memberInfo.getMbrNo());
			
			contentDevBinaryService.updateUpdateHistory(contentUpdate);
			
			resultContentUpdate = contentDevBinaryService.getContentUpdateList(content.getCid());
		}

		logger.info("==== Content ContentDevBinaryAction.ajaxUpdateManagementUpdate END   ====");
		
		return SUCCESS;
		
	}
	
	public String ajaxUpdateManagementDelete() {
		logger.info("==== Content ContentDevBinaryAction.ajaxUpdateManagementDelete START   ====");
		if (SessionUtil.existMemberSession(this.req)) {
			
			if (contentUpdate == null) {
				throw new NoticeException("[ContentDevBinaryoAction].ajaxUpdateManagementDelete : Can't acquire Contents null");
			}
			
			// 컨텐츠 정보 조회
			content = contentDetailInfoService.getContentDetailInfo(contentUpdate.getCid());	
			
			logger.info("CID   : {0}", new Object[] {content.getCid()});
			logger.info("업데이트 SEQ   : {0}", new Object[] {contentUpdate.getUpdateSeq()});
			logger.info("업데이트 내용   : {0}", new Object[] {contentUpdate.getUpdateText()});
	
			contentUpdate.setCid(content.getCid());
			
			contentDevBinaryService.deleteUpdateHistory(contentUpdate);
			
			resultContentUpdate = contentDevBinaryService.getContentUpdateList(content.getCid());
		}

		logger.info("==== Content ContentDevBinaryAction.ajaxUpdateManagementDelete END   ====");
		
		return SUCCESS;
		
	}
	
	public void licenseForDeveloper() {
		
		HttpServletResponse response = this.res;
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter writer = null;
		JSONObject jsonObject = new JSONObject();
		
		try{
			
			jsonObject.put("resultCode", "success");

			if (SessionUtil.existMemberSession(this.req)) {
				
				if (this.content == null) throw new NoticeException("licenseForDeveloper cid is null");
				
				logger.info("[getLicenseForDeveloper] 상품 CID : {0}", new Object[] {content.getCid()});
				
				// 로그인한 사용자의 Session을 가져 온다.
				Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
				
				String device = contentDevBinaryService.getOtaDeveloperPhoneList(memberInfo.getMbrNo());		// 컨텐츠 개발 회원의 휴대폰번호 리스트
				
				if(device == null || "0".equals(device)){
					jsonObject.put("resultCode", "error");
				}
				writer = response.getWriter();
				writer.write(jsonObject.toString());
			}
			
		}catch(Exception e){
			try {
				jsonObject.put("resultCode", "error");
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			logger.info("#ARM Check Developer Test Phone Error : " + e);
			throw new ServiceException("ARM Check Developer Test Phone Error", e);
		} finally {
			if(writer != null) { writer.close(); }
		}
		
	}
	
	public String licenseForDeveloperDownload() throws IOException {
		
		BufferedWriter wt = null;
		ARMManagerService service = new ARMManagerServiceImpl();
		
		try{
			if (this.content == null) throw new NoticeException("licenseForDeveloperDown cid is null"); 
			
			logger.info("[getLicenseForDeveloperDown] 상품 CID : {0}", new Object[] {content.getCid()});
			
			// 로그인한 사용자의 Session을 가져 온다.
			Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
			
			// 컨텐츠 정보 조회
			content = contentDetailInfoService.getContentDetailInfo(content.getCid());	
			
			String strXmlData = service.connectARMReqVerificationLicense(memberInfo.getMbrNo(), content.getAid());
			
			StringBuffer filePath = new StringBuffer();
			StringBuffer fileName = new StringBuffer();
			
			fileName.append(DateUtil.getGeneralTimeStampString());
			fileName.append("_");
			fileName.append(this.conf.getString("omp.common.arm.licenseFileName", "verificationlicense.omp"));
			
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
			
			this.setDownloadFile(file, "binary/octet-stream", fileName.toString());

//			wt = new BufferedWriter(new FileWriter(file));				// 파일쓰기를 위한 객체 생성
//			wt.write(strXmlData);										// 파일에 아래 문자들을 출력
//			inputStream = new FileInputStream(file);					// 파일 핸들 반환
			
		} catch(Exception e){
			e.printStackTrace();
			throw new ServiceException("ARM Download Developer License Error", e);
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
}
