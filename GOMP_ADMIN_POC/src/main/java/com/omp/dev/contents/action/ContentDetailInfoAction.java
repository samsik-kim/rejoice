package com.omp.dev.contents.action;


import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.omp.commons.action.BaseAction;
import com.omp.commons.exception.NoticeException;
import com.omp.commons.product.service.ContentsHistoryService;
import com.omp.commons.product.service.ContentsHistoryServiceImpl;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.SessionUtil;
import com.omp.dev.common.Constants;
import com.omp.dev.contents.model.Contents;
import com.omp.dev.contents.model.Category;
import com.omp.dev.contents.service.ContentDetailInfoService;
import com.omp.dev.contents.service.ContentDetailInfoServiceImpl;
import com.omp.dev.main.service.MainService;
import com.omp.dev.main.service.MainServiceImpl;
import com.omp.dev.member.model.Member;
import com.omp.dev.user.model.Session;


/**
 * Content Base Info
 * 
 * @author snoopy
 *
 */
@SuppressWarnings("serial")
public class ContentDetailInfoAction extends BaseAction {

	private static GLogger logger = new GLogger(ContentDetailInfoAction.class);
	
	private    Contents 					content;
	
	private 	List<Category>				contentCategoryList;

	private  	Map<String, Object>			contentImageMap;
	private  	Map<String, Object>			contentTagMap;
	
	private		ContentDetailInfoService	contentDetailInfoService;
	private		ContentsHistoryService		contentsHistoryService;
	
	private		String 						returnMessage;
	private		String 						redirectUrl;
	private		String 						tabGbn;
	
	public ContentDetailInfoAction() {
		content						= new Contents();
		contentDetailInfoService	= new ContentDetailInfoServiceImpl();
		contentsHistoryService		= new ContentsHistoryServiceImpl();
	}
	
	@Override
	protected void prepareRequest() throws Exception {
		this.setTabGbn("DETAIL");
		super.prepareRequest();
	}

	/**
	 * Content Detail Info View
	 * : 상품 상세정보 조회
	 * 
	 * @return
	 */
	public String contentDetailInfoView() {
	
		if (SessionUtil.existMemberSession(this.req)) {
			
			// Contents null일 경우 Throw 처리 한다.
			if (content == null || content.getCid() == null) {
				throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
			}

			Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
			
			if(logger.isDebugEnabled()) {
				logger.debug("* CID 			: {0}", new Object[] {content.getCid()});
				logger.debug("* Session MBR_NO 	: {0}", new Object[] {memberInfo.getMbrNo()});
				logger.debug("* Sale Stat 		: {0}", new Object[] {content.getSaleStat()});
				logger.debug("* Search Type 	: {0}", new Object[] {content.getSearchType()});
				logger.debug("* Search Type2 	: {0}", new Object[] {content.getSaleSearchType()});
				logger.debug("* Search Value 	: {0}", new Object[] {content.getSearchValue()});
				logger.debug("* Page No		 	: {0}", new Object[] {content.getPage().getNo()});
			}
			
			// [목록] setting
			String searchType = content.getSearchType();
			String searchValue = content.getSearchValue();
			String saleSearchType = content.getSaleSearchType();
			int pageNo = content.getPage().getNo();
			
			// 상품 상세 정보 조회
			content = contentDetailInfoService.getContentDetailInfo(content.getCid());					// select Content Base Info
				
			// 상품을 등록한 사용자와 Login한 상용자가 다를 경우  Exception 처리 한다.
			if(content == null || !memberInfo.getMbrNo().equals(content.getDevUserId())) {	
				throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
			}
			
			contentImageMap = contentDetailInfoService.getListContentImage(content.getCid(), this.conf.getStaticProperties());		// select Content Images
			contentTagMap 	= contentDetailInfoService.getContentTagNameList(content.getCid());										// select Content Tag Info
			
			// 회원 정산 정보 조회 (US000205 : 무료개발자, US000206: 유료개발자)
			MainService memberService 	= new MainServiceImpl();
			Member member	   			= new Member();
			member						= memberService.getMemberProfile(memberInfo.getMbrNo());
			
			if(logger.isDebugEnabled()) {
				logger.debug("* Member Pay Info 	: {0}", new Object[] {member.getMbrCatCd()});
			}
			
			content.setPayMemberInfo(member.getMbrCatCd());
			content.setSearchType(searchType);
			content.setSearchValue(searchValue);
			content.setSaleSearchType(saleSearchType);
			content.getPage().setNo(pageNo);
			
			// 미 등록된 Thumbnail Image Generating Process
			//this.detailService.processImageGenerating(this.content);	

		}

		// When in standby mode or in verifyed that shows details View Jsp.
		if(content.getVerifyPrgrYn() != null) {
			if(Constants.CODE_VERIFY_REQ.equals(content.getVerifyPrgrYn()) 
					|| Constants.CODE_VERIFY_ING.equals(content.getVerifyPrgrYn())) {
				return SUCCESS;
			}
		}
		
		// When in sale restric that shows details View Jsp. 판매금지에서도 정책서에 가능하도록 되있음.
//		if(content.getSaleStat() != null) {
//			if(Constants.CONTENT_SALE_STAT_RESTRIC.equals(content.getSaleStat())) {
//				return SUCCESS;
//			}
//		}
	
		return INPUT;
	}
	
	/**
	 * Content Detail Info Modify
	 * : 상품 상세 정보 수정
	 * 
	 * @return
	 */
	public void ajaxModifyContentDetailInfo () {

		String result = "UPDATE_FAIL";
		
		this.res.setContentType("text/html; charset=UTF-8");
		
		PrintWriter writer = null;
		JSONObject jsonObject = new JSONObject(); 
	
		try {
		
			if (SessionUtil.existMemberSession(this.req)) {
				
				// Contents null일 경우 Throw 처리 한다.
				if (content == null || content.getCid() == null) {
					throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
				}
		
				Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
				
				if (logger.isDebugEnabled()) {
					logger.debug("* CID 			: {0}", new Object[] {content.getCid()});
					logger.debug("* Session MBR_NO 	: {0}", new Object[] {memberInfo.getMbrNo()});
				}
				
				Contents oldContent = contentDetailInfoService.getContentBaseInfo(content.getCid());		// Content Base Info
			
				// 상품을 등록한 사용자와 Login한 상용자가 다를 경우  Exception 처리 한다.
				if(!memberInfo.getMbrNo().equals((oldContent.getDevUserId()))) {	
					throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
				}
		
				// 검증 대기, 검증 중 상태가 아닐 때 저장 가능.
				if (!Constants.CODE_VERIFY_ING.equals(oldContent.getVerifyPrgrYn()) 
									&& !Constants.CODE_VERIFY_REQ.equals(oldContent.getVerifyPrgrYn())) {
					
					// 무료 회원은 유료 상품 등록 불가
					MainService memberService 	= new MainServiceImpl();
					Member member	   			= new Member();
					member						= memberService.getMemberProfile(memberInfo.getMbrNo());
					content.setPayMemberInfo(member.getMbrCatCd());
					
					if(Constants.MEM_TYPE_DEV_NOPAY.equals(member.getMbrCatCd())) {
						if (Integer.parseInt(content.getProdPrc()) > 0) {
							result = "NONE_PAY_MEMBER";
						}
					}
					
					//content.setPid(oldContent.getPid());										
					content.setSaleStat(oldContent.getSaleStat());	
					content.setUpdBy(oldContent.getDevUserId());
					content.setUpdBy(memberInfo.getMbrNo());
					content.setVmType(oldContent.getVmType());
					
					// 상세 정보 수정
					result = contentDetailInfoService.modifyContentDetail(content, oldContent);

					// History Table Insert
					if ("SUCCESS".equals(result)) {
						contentsHistoryService.insertMainContentsHis(content.getCid());
						contentsHistoryService.insertContsImgHis(content.getCid());
					}
					
				}
				
			} else {
				throw new NoticeException("Session이 존재하지 않습니다.");
			}
	
		} catch (Exception e) {
			logger.error("상품 상세정보 수정에 실패하였습니다. 재시도해주시기 바랍니다." , e);
		} finally {
			
			try {
				
				if (logger.isDebugEnabled()) {
					logger.debug("* result 			: {0}", new Object[] {result});
					logger.debug("* redirectUrl 	: {0}", new Object[] {this.getRedirectUrl()});
				}

				jsonObject.put("resultMessage", result);
				jsonObject.put("redirectUrl", this.getRedirectUrl());

				this.setReturnMessage(result);
				
				writer = this.res.getWriter();
				writer.write(jsonObject.toString());
				
			} catch (Exception e) {
				logger.error("Content Detail Info Modify Fail", e);
			}
			
			if(writer != null) { writer.close(); }	
			
		}

	}
	
	/**
	 * Content Category Popup
	 * : 카테고리 Popup
	 * 
	 * @return
	 */
	public String ajaxSelectCtgrList() {
		
		String contentCtgr;
		
		// Contents null일 경우 Throw 처리 한다.
		if (content == null || content.getCid() == null) {
			throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
		}
		
		contentCategoryList = contentDetailInfoService.getCategoryList();
		contentCtgr 		= contentDetailInfoService.getContentCategory(content.getCid());
		
		content.setCtgrCd(contentCtgr);
		
		return SUCCESS;
	}

	/**
	 * Content Image Preview Popup
	 * : 변환된 이미지 미리보기 Popup
	 * 
	 * @return
	 */
	public String popImagePreview() {
		
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
			
			content = contentDetailInfoService.getContentDetailInfo(content.getCid());		// Content Detail Info
			
			// 상품을 등록한 사용자와 Login한 상용자가 다를 경우  Exception 처리 한다.
			if(!memberInfo.getMbrNo().equals(content.getDevUserId())) {	
				throw new NoticeException("요청하신 상품 정보가 올바르지 않습니다.");
			}
			
			// select Content Images
			contentImageMap = contentDetailInfoService.getListContentImage(content.getCid(), this.conf.getStaticProperties());			
			
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
	 * @return the contentCategory
	 */
	public List<Category> getContentCategoryList() {
		return contentCategoryList;
	}

	/**
	 * @param contentCategory the contentCategory to set
	 */
	public void setContentCategoryList(List<Category> contentCategoryList) {
		this.contentCategoryList = contentCategoryList;
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

	/**
	 * @return the tabGbn
	 */
	public String getReturnMessage() {
		return returnMessage;
	}

	/**
	 * @param tabGbn the tabGbn to set
	 */
	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
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
	
}
