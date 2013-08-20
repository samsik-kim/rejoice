package com.nmimo.web.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nmimo.common.configuration.ConfigurationService;
import com.nmimo.common.info.PageInfo;
import com.nmimo.common.info.SessionInfo;
import com.nmimo.common.session.SessionHandler;
import com.nmimo.common.util.StringUtils;
import com.nmimo.data.user.info.UserBasInfo;
import com.nmimo.service.user.UserService;

/**
 * <pre>
 * 사용자관리  Controller
 * </pre>
 * @file UserController.java
 * @since 2013. 4. 15.
 * @author Leesh
 */
@Controller
public class UserController {

	/** LOG4J */
	Log logger = LogFactory.getLog(getClass());

	/** Message 관련 */
	@Autowired
	MessageSourceAccessor message;
	
	/** Config 관련 */
	@Autowired
	ConfigurationService config;

	@Autowired
	UserService userService;

	
	/**
	 * <pre>
	 * nMIMO MAIN 
	 * </pre>
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/nmimo/main.do")
	public ModelAndView main(HttpServletRequest request) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug("[UserController - main]");
		}

		ModelAndView mav = new ModelAndView();
		
		//시스템점검 환경설정(기본값:N)
		if ("Y".equals(config.getString("ERROR.SYSTEMCHECK.YN"))) {
			mav.setViewName("common/error/errorSystemCheck");
		}else{

			//CU 일반사용자		main
			//CC1 검토승인자	main
			//CC2 114승인자		main
			//MA 관리자			main	
			//DV 개발자			main
			//CC3 발송승인자	승인>대기
			//AD 통계조회자		통계
			//SC 회원관리승인자	사용자관리>일반사용자
			//KT KBN사용자		KBN관리>전송정보리스트
			
			//Session authority 권한값 체크 후 MAIN페이지 이동
			SessionInfo member = (SessionInfo)SessionHandler.getInstance().getLoginInfo(request);
			String sAuthority = member.getAuthority();
			
			if(config.getString("auth.code.cc3").equals(sAuthority)){
				mav.setViewName("forward:/review/standbyList.do");
			}else if(config.getString("auth.code.ad").equals(sAuthority)){
				mav.setViewName("forward:/stats/list.do");
			}else if(config.getString("auth.code.sc").equals(sAuthority)){
				mav.setViewName("forward:/user/list.do");
			}else if(config.getString("auth.code.kt").equals(sAuthority)){
				mav.setViewName("forward:/kbn/msgSendList.do");
			}else{
				mav.setViewName("user/main");
			}	
		}
		
		return mav;
	}
	
	/**
	 * <pre>
	 * 사용자관리 >> 사용자관리 list
	 * 
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/list.do")
	public ModelAndView userList(HttpServletRequest request, @ModelAttribute UserBasInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[User] Controller -> userList ");
		}

		return new ModelAndView("user/list");
	}

	/**
	 * <pre>
	 * 사용자관리 >> 사용자관리 InnerList
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/ajaxlistInner.do")
	public ModelAndView userListInner(HttpServletRequest request, @ModelAttribute UserBasInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[User] Controller -> userListInner ");
		}

		int currentPage = Integer.parseInt(StringUtils.nvlStr(request.getParameter("pageIndex"), "1")); //현재 페이지 위치
		int pageUnit = 10; // 페이지를 보여줄 개수
		int pageSize = 10; // 한 페이지에 보여줄 게시물 수 
		
		PageInfo pageInfo = new PageInfo(request, currentPage, pageUnit, pageSize);
		ModelAndView mav = new ModelAndView();

		//사용자 구분
		dbParams.setUserAutVal(config.getString("user.aut.user"));
		
		pageInfo = userService.selectUserList(pageInfo, dbParams);
		
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("user/inc/listInner");
		
		return mav;
	}
	
	
	/**
	 * <pre>
	 * 사용자관리  >> 사용자 상세정보 (공통)
	 * </pre>
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/popup/listInfoDetailPop.do")
	public ModelAndView userListInfoDetailPop(HttpServletRequest request, @ModelAttribute UserBasInfo dbParams)throws Exception{

		ModelAndView mav = new ModelAndView();
		
		UserBasInfo userBasInfo = new UserBasInfo();
		userBasInfo= userService.selectUserDetail(dbParams);
		
		//권한값에 따른 권한명 세팅
		String AuthorityName = getUserAuthorityName(userBasInfo.getUserAutVal());
		userBasInfo.setUserAutVal(AuthorityName);
		
		mav.addObject("viewInfo", userBasInfo);
		mav.setViewName("user/popup/listInfoDetailPop");
		return mav;
	}
	

	/**
	 * <pre>
	 * 사용자관리  >> 사용자 상세정보 >> 수정
	 * </pre>
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/popup/listInfoDetailModifyPop.do")
	public ModelAndView userListInfoDetailModifyPop(HttpServletRequest request, @ModelAttribute UserBasInfo dbParams)throws Exception{

		ModelAndView mav = new ModelAndView();	
		
		mav.addObject("viewInfo", userService.selectUserDetail(dbParams));
		mav.setViewName("user/popup/listInfoDetailModifyPop");
		
		return mav;
	}
	
	
	/**
	 * <pre>
	 * 사용자관리 >> 승인자관리 list
	 * 
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/confirmList.do")
	public ModelAndView userConfirmList(HttpServletRequest request, @ModelAttribute UserBasInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[User] Controller -> userConfirmList ");
		}

		return new ModelAndView("user/confirmList");
	}

	/**
	 * <pre>
	 * 사용자관리 >> 승인자관리 InnerList
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/ajaxConfirmListInner.do")
	public ModelAndView userConfirmListInner(HttpServletRequest request, @ModelAttribute UserBasInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[User] Controller -> userConfirmListInner ");
		}

		int currentPage = Integer.parseInt(StringUtils.nvlStr(request.getParameter("pageIndex"), "1")); //현재 페이지 위치
		int pageUnit = 10; // 페이지를 보여줄 개수
		int pageSize = 10; // 한 페이지에 보여줄 게시물 수 
		
		PageInfo pageInfo = new PageInfo(request, currentPage, pageUnit, pageSize);
		ModelAndView mav = new ModelAndView();

		//Temp
		dbParams.setUserAutVal(config.getString("user.aut.approver"));
		
		pageInfo = userService.selectUserList(pageInfo, dbParams);
		
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("user/inc/confirmListInner");
		
		return mav;
	}
	
	
	/**
	 * <pre>
	 * 사용자관리  >> 승인자관리 >> 상세
	 * </pre>
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/popup/comfirmListInfoDetailPop.do")
	public ModelAndView userConfirmListInfoDetailPop(HttpServletRequest request)throws Exception{

		ModelAndView mav = new ModelAndView();	
		mav.setViewName("user/popup/confirmListInfoDetailPop");
		
		return mav;
	}
	

	/**
	 * <pre>
	 * 사용자관리  >> 승인자관리 >> 수정
	 * </pre>
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/popup/confirmListInfoDetailModifyPop.do")
	public ModelAndView userConfirmListInfoDetailModifyPop(HttpServletRequest request)throws Exception{

		ModelAndView mav = new ModelAndView();	
		mav.setViewName("user/popup/listInfoDetailModifyPop");
		
		return mav;
	}	
	
	
	
	/**
	 * <pre>
	 * 사용자관리 >> 기타 사용자관리 list
	 * 
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/etcList.do")
	public ModelAndView userEtcList(HttpServletRequest request, @ModelAttribute UserBasInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[User] Controller -> userEtcList ");
		}

		return new ModelAndView("user/etcList");
	}

	/**
	 * <pre>
	 * 사용자관리 >> 기타 사용자관리 InnerList
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/ajaxEtcListInner.do")
	public ModelAndView userEtcListInner(HttpServletRequest request, @ModelAttribute UserBasInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[User] Controller -> userEtcListInner ");
		}

		int currentPage = Integer.parseInt(StringUtils.nvlStr(request.getParameter("pageIndex"), "1")); //현재 페이지 위치
		int pageUnit = 10; // 페이지를 보여줄 개수
		int pageSize = 10; // 한 페이지에 보여줄 게시물 수 
		
		PageInfo pageInfo = new PageInfo(request, currentPage, pageUnit, pageSize);
		ModelAndView mav = new ModelAndView();

		//Temp
		dbParams.setUserAutVal(config.getString("user.aut.etc"));
		pageInfo = userService.selectUserList(pageInfo, dbParams);
		
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("user/inc/etcListInner");
		
		return mav;
	}
	
	
	/**
	 * <pre>
	 * 사용자관리  >> 기타사용자 관리 >> 상세
	 * </pre>
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/popup/etcListInfoDetailPop.do")
	public ModelAndView userEtcListInfoDetailPop(HttpServletRequest request)throws Exception{

		ModelAndView mav = new ModelAndView();	
		mav.setViewName("user/popup/etcListInfoDetailPop");
		
		return mav;
	}
	

	/**
	 * <pre>
	 * 사용자관리  >> 기타사용자 관리 >> 수정
	 * </pre>
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/popup/etcListInfoDetailModifyPop.do")
	public ModelAndView userEtcListInfoDetailModifyPop(HttpServletRequest request)throws Exception{

		ModelAndView mav = new ModelAndView();	
		mav.setViewName("user/popup/etcListInfoDetailModifyPop");
		
		return mav;
	}		
	
	
	
	/**
	 * <pre>
	 * 사용자관리 >> 나의정보 
	 * 
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/userInfo.do")
	public ModelAndView userInfo(HttpServletRequest request, @ModelAttribute UserBasInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[User] Controller -> userInfo ");
		}
		ModelAndView mav = new ModelAndView("user/userInfo");
		SessionInfo member = (SessionInfo)SessionHandler.getInstance().getLoginInfo(request);
		dbParams.setUserId(member.getUserId());
		mav.addObject("viewInfo", userService.selectUserDetail(dbParams));
		return mav;
	}

	
	/**
	 * <pre>
	 * 사용자관리 >> 나의정보 >> 수정
	 * 
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/userInfoModify.do")
	public ModelAndView userInfoModify(HttpServletRequest request, @ModelAttribute UserBasInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[User] Controller -> userInfo ");
		}
		ModelAndView mav = new ModelAndView("user/userInfoModify");
		SessionInfo member = (SessionInfo)SessionHandler.getInstance().getLoginInfo(request);
		dbParams.setUserId(member.getUserId());
		mav.addObject("viewInfo", userService.selectUserDetail(dbParams));
		return mav;
	}
	
	
	/**
	 * <pre>
	 * 사용자관리 >> 사용자정보 >> 사용자정보수정 >> 비밀번호 초기화
	 * </pre>
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/ajaxListInfoPwdResetAction.do")
	public ModelAndView userListInfoPwdResetAction(HttpServletRequest request, @ModelAttribute UserBasInfo dbParams)throws Exception{

		JSONObject jsonObject = new JSONObject();
		ModelAndView mav = new ModelAndView("jsonView");
		
		int result=0;
		result = userService.updateUserPwdRest(dbParams);
		
		if(result > 0){
			jsonObject.put("resultCode", "S");
			jsonObject.put("resultMsg", "비밀번호가 0000 으로 초기화 되었습니다.");
		}else{
			jsonObject.put("resultCode", "F");
			jsonObject.put("resultMsg", "[ERROR] 처리중 오류가 발생했습니다.");
		}

	    mav.addObject("jsonObject", jsonObject);
		
		return mav;
	}
	
	/**
	 * <pre>
	 * 사용자관리 >> 사용자정보 >> 사용자정보수정 >> 발송승인자검색
	 * </pre>
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/ajaxSearchApprover.do")
	public ModelAndView userSearchApprover(HttpServletRequest request,@ModelAttribute UserBasInfo dbParams)throws Exception{

		JSONObject jsonObject = new JSONObject();
		ModelAndView mav = new ModelAndView("jsonView");
		
		UserBasInfo userBasInfo = new UserBasInfo();
		userBasInfo = userService.selectSearchApprover(dbParams);
		
		if(null==userBasInfo){
			jsonObject.put("resultCode", "F");
			jsonObject.put("resultMsg", "검색된 결과가 없습니다.");
		}else{
			jsonObject.put("resultCode", "S");
			jsonObject.put("userId", userBasInfo.getUserId());
			jsonObject.put("userNm", userBasInfo.getUserNm());
			jsonObject.put("userRlvnDeptNm", userBasInfo.getUserRlvnDeptNm());
		}

	    mav.addObject("jsonObject", jsonObject);
		
		return mav;
	}
	
	
	
	
	/**
	 * <pre>
	 * 사용자관리 >> 사용자정보 >> 사용자정보수정 >> 수정 처리
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/ajaxUpdateUserInfoAction.do")
	public ModelAndView userUpdateUserInfoAction(HttpServletRequest request, @ModelAttribute UserBasInfo dbParams)throws Exception{

		JSONObject jsonObject = new JSONObject();
		ModelAndView mav = new ModelAndView("jsonView");
		
		int result=0;
		result = userService.updateUserInfo(dbParams);
		
		if(result > 0){
			jsonObject.put("resultCode", "S");
			jsonObject.put("resultMsg", "사용자정보 수정이 완료 되었습니다.");
		}else{
			jsonObject.put("resultCode", "F");
			jsonObject.put("resultMsg", "[ERROR] 처리중 오류가 발생했습니다.");
		}

	    mav.addObject("jsonObject", jsonObject);
		
		return mav;
	}
	
	/**
	 * <pre>
	 * 사용자관리 >> 사용자정보 >> 사용자정보수정 >> 해지 처리
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/ajaxDeleteUserInfoAction.do")
	public ModelAndView userDeleteUserInfoAction(HttpServletRequest request, @ModelAttribute UserBasInfo dbParams)throws Exception{

		JSONObject jsonObject = new JSONObject();
		ModelAndView mav = new ModelAndView("jsonView");
		
		//사용자 상태값 (해지)
		dbParams.setUserSttusVal(config.getString("user.sttus.expired"));
		
		int result=0;
		result = userService.deleteUserInfo(dbParams);
		
		if(result > 0){
			jsonObject.put("resultCode", "S");
			jsonObject.put("resultMsg", "사용자 해지 처리가 완료 되었습니다.");
		}else{
			jsonObject.put("resultCode", "F");
			jsonObject.put("resultMsg", "[ERROR] 처리중 오류가 발생했습니다.");
		}

	    mav.addObject("jsonObject", jsonObject);
		
		return mav;
	}
	
	
	/**
	 * <pre>
	 * 권한별 코드값으로 권한명칭 세팅
	 * </pre>
	 * @param userAuth
	 * @return
	 */
	public String getUserAuthorityName(String userAuth){
		
		String userAuthorityName="";

		if(config.getString("auth.code.cu").equals(userAuth)){
			userAuthorityName = config.getString("auth.code.cu.name"); 
		}else if(config.getString("auth.code.cc1").equals(userAuth)){
			userAuthorityName = config.getString("auth.code.cc1.name");
		}else if(config.getString("auth.code.cc2").equals(userAuth)){
			userAuthorityName = config.getString("auth.code.cc2.name");	
		}else if(config.getString("auth.code.cc3").equals(userAuth)){
			userAuthorityName = config.getString("auth.code.cc3.name");
		}else if(config.getString("auth.code.ma").equals(userAuth)){
			userAuthorityName = config.getString("auth.code.ma.name");
		}else if(config.getString("auth.code.dv").equals(userAuth)){
			userAuthorityName = config.getString("auth.code.dv.name");
		}else if(config.getString("auth.code.ad").equals(userAuth)){
			userAuthorityName = config.getString("auth.code.ad.name");
		}else if(config.getString("auth.code.sc").equals(userAuth)){
			userAuthorityName = config.getString("auth.code.sc.name");
		}else if(config.getString("auth.code.kt").equals(userAuth)){
			userAuthorityName = config.getString("auth.code.kt.name");
		}else{
			userAuthorityName="ERROR";
		}
		
		return userAuthorityName; 
	}
	

}