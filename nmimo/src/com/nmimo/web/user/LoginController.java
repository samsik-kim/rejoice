package com.nmimo.web.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nmimo.common.ServiceConstants;
import com.nmimo.common.configuration.ConfigurationService;
import com.nmimo.common.info.SessionInfo;
import com.nmimo.common.session.SessionHandler;
import com.nmimo.common.util.StringUtils;
import com.nmimo.data.user.info.UserBasInfo;
import com.nmimo.data.user.info.UserBasInfo;
import com.nmimo.service.user.UserService;

@Controller
public class LoginController {

	/** LOG4J */
	Log logger = LogFactory.getLog(getClass());
	
	/** Config 관련 */
	@Autowired
	ConfigurationService config;
	
	/** Message 관련 */
	@Autowired
	MessageSourceAccessor message;

	@Autowired
	private UserService userService;
	
	/**
	 * 메인 Login Form
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login/loginForm.do")
	public ModelAndView userLoginForm(HttpServletRequest request)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[LoginController - loginForm]");
		}
		
		
//		logger.debug("^^^^^^^^^^^^^^^^^^^^ 암호화 Start ^^^^^^^^^^^^^^^^^^^^");
//		String aes = JKTFCryptoUtil.encryptOnlyData(JKTFCryptoUtil.CIPHER_AES_128, RandomString.getString(64));
//		logger.debug("aes : " + aes);
		
//		String a[] = UserUtil.encryptPw("1234");
//		logger.debug("newPassWord >> "+a[0]);
//		logger.debug("AESkey >>>"+a[1]);
//		logger.debug("^^^^^^^^^^^^^^^^^^^^ 암호화 END ^^^^^^^^^^^^^^^^^^^^");
		
		
//		logger.debug("[LoginController - PSSO연동후 쿠키체크?????????????]");
//		
//		Cookie[] c = request.getCookies();
//		for(int i=0; i<c.length; i++){
//			String name = URLDecoder.decode(c[i].getName(), "UTF-8");
//			String value = URLDecoder.decode(c[i].getValue(), "UTF-8");
//			
//			logger.debug("name =>>>> " +name);
//			logger.debug("value =>>>> " +value);
//		}
//		
//		logger.debug("[LoginController - PSSO연동후 쿠키체크?????????????]");
//		
//	    Locale locale=request.getLocale(); //받아오는 국가
//	    config.getString("crm.result.msg.400");
//	    logger.debug(config.getString("crm.result.msg.400", locale));
//		String testSql = userService.selectTest();
//		RequestMap.getMapping(request);
		
		
		return new ModelAndView("user/loginForm");
	}
	

	
//	/**
//	 * <pre>
//	 * 로그인 로직처리(DB TEST용)
//	 * </pre>
//	 * @param request
//	 * @param response
//	 * @param dbParams
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping("/login/login.do")
//	public ModelAndView userLogin(HttpServletRequest request,HttpServletResponse response,@ModelAttribute UserBasInfo dbParams)throws Exception{
//		
//		ModelAndView mav = new ModelAndView();
//		
//		String userId="";
//		userId = userService.selectUserIdTest();
//		
//		SessionInfo sessionInfo = new SessionInfo();
//		sessionInfo.setUserId(userId);
//		sessionInfo.setLoginYn(ServiceConstants.Member.SESSION_LOGIN_Y);
//		SessionHandler<SessionInfo> handler = new SessionHandler<SessionInfo>();
//		handler.setLoginInfo(request, sessionInfo);
//
//		mav.setViewName("/user/main");
//		
//		return mav;
//	}
	
	
	/**
	 * <pre>
	 * 로그인 로직처리
	 * </pre>
	 * @param request
	 * @param response
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login/login.do")
	public ModelAndView userLogin(HttpServletRequest request,HttpServletResponse response,@ModelAttribute UserBasInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[LoginController - login]");
		}
		
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = new SessionInfo();

		String userId = StringUtils.nvlStr(request.getParameter("userId"));
		String userPwd = StringUtils.nvlStr(request.getParameter("userPwd"));
		
		String return_url = StringUtils.nvlStr(request.getParameter("return_url"));
//		String resp_url = "http://" + StringUtils.defaultString(request.getServerName());
		String resp_url = "";
		resp_url = resp_url + StringUtils.nvlStr(request.getContextPath());
		if("".equals(return_url)){
			return_url = StringUtils.nvlStr((String)SessionHandler.getInstance().getSessionInfo(request, "RETURI"),"");
			if(!"".equals(return_url) && !"null".equals(return_url)){
				return_url = resp_url+return_url;
			}else{
				return_url = resp_url+ StringUtils.nvlStr(config.getString("url.mimo.main"));
			}
		}
			
		// DB호출 로그인검증
		if(!"".equals(userId) && !"".equals(userPwd)){

			UserBasInfo userBasInfo = new UserBasInfo();

			//TODO
			//ID & PWD 체크 
//			userBasInfo = userService.selectUserLoginChk(dbParams);
			userBasInfo.setUserAutVal("MA");
			userBasInfo.setUserId("admin");
			userBasInfo.setUserNm("관리자");
			userBasInfo.setUserRlvnDeptNm("nMIMO개발팀");
			userBasInfo.setBasApvrId("admin3");

			//User정보 확인 후 세션생성
			if(userBasInfo != null){
				userBasInfo.setAuthority(userBasInfo.getUserAutVal());
				userBasInfo.setAuthorityName(this.getUserAuthorityName(userBasInfo.getAuthority()));

				sessionInfo.setUserId(userBasInfo.getUserId());
				sessionInfo.setUserNm(userBasInfo.getUserNm());
				sessionInfo.setUserRlvnDeptNm(userBasInfo.getUserRlvnDeptNm());
				sessionInfo.setBasApvrId(userBasInfo.getBasApvrId());
				sessionInfo.setAuthority(userBasInfo.getAuthority());
				sessionInfo.setAuthorityName(userBasInfo.getAuthorityName());
				sessionInfo.setLoginYn(ServiceConstants.Member.SESSION_LOGIN_Y);

				SessionHandler<SessionInfo> handler = new SessionHandler<SessionInfo>();
				handler.setLoginInfo(request, sessionInfo);
				mav.setViewName("redirect:"+return_url);
			}else{
				sessionInfo.setLoginYn(ServiceConstants.Member.SESSION_LOGIN_N);
				return_url = config.getString("url.mimo.main");
				mav.setViewName("redirect:"+return_url);
			}
		}else{
			sessionInfo.setLoginYn(ServiceConstants.Member.SESSION_LOGIN_N);
			resp_url = resp_url + config.getString("url.mimo.login");
			resp_url = resp_url + "";
			mav.setViewName("redirect:"+resp_url);
		}
				
		return mav;
	}

	/**
	 * <pre>
	 * 로그아웃 처리
	 * </pre>
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/logout.do")
	public ModelAndView userLogout(HttpServletRequest request)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[LoginController - logout]");
		}
		
		int k = 0;
		String params="";
		for (java.util.Enumeration hparam = request.getParameterNames(); hparam
			.hasMoreElements(); k++) {
			String paramname = (String) hparam.nextElement();
			String[] paramvalue = request.getParameterValues(paramname);
			String sParam = "";
			if("result".equals(paramname)){
				params+=paramname+"=";
				for (int i = 0; i < paramvalue.length; i++) {
						if (i == 0) {
							params += paramname+"=";
						}
				}
			}
		}

		String return_url = StringUtils.nvlStr(request.getParameter("return_url"));
		if("".equals(return_url)){
			return_url = StringUtils.nvlStr((String)SessionHandler.getInstance().getSessionInfo(request, "RETURI"));
			if(!"".equals(return_url) && !"null".equals(return_url)){
				return_url = return_url;
			}else{
				return_url = config.getString("url.mimo.login");
			}
		}

		SessionHandler.getInstance().removeSessionInfo(request, ServiceConstants.Member.SESSION_LOGIN_INFO_KEY);
		SessionHandler.getInstance().invalidateSession(request);
		
		HttpSession sesseion = request.getSession(true);
		
		sesseion.removeAttribute(ServiceConstants.Member.SESSION_LOGIN_INFO_KEY);
		sesseion.invalidate();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:"+return_url);
		
		return mav;
	}
	
	
	/**
	 * 로그인 PassWord Change Form
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login/pwdChangeForm.do")
	public ModelAndView userPwdChangeForm(HttpServletRequest request)throws Exception{
		
		return new ModelAndView("user/pwdChangeForm");
	}
	
	/**
	 * <pre>
	 * 로그인 사용자등록
	 * </pre>
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login/popup/registerPop.do")
	public ModelAndView userRegisterPop(HttpServletRequest request)throws Exception{
		
		return new ModelAndView("user/popup/registerPop");
	}

	/**
	 * <pre>
	 * 로그인 이용안내
	 * </pre>
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login/popup/guidePop.do")
	public ModelAndView userGuidePop(HttpServletRequest request)throws Exception{
		
		return new ModelAndView("user/popup/guidePop");
	}
	
	/**
	 * <pre>
	 * 로그인 화면 > 사용자 신청 > 사용자ID 검색
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login/ajaxSearchUserId.do")
	public ModelAndView searchUserId(HttpServletRequest request, @ModelAttribute UserBasInfo dbParams)throws Exception{
		
		JSONObject jsonObject = new JSONObject();
		ModelAndView mav = new ModelAndView("jsonView");
		
		//가입여부 체크
		int regChk;
		regChk = userService.selectUserRegChk(dbParams);
		
		if(regChk > 0){
			jsonObject.put("resultCode", "F");
			jsonObject.put("resultMsg", "[사용불가] 이미 등록된 아이디 입니다.");
		}else{
			UserBasInfo userBasInfo = new UserBasInfo();
			userBasInfo = userService.selectIdmsUserDetail(dbParams);
			
			if(userBasInfo != null){
				jsonObject.put("userRlvnDeptNm",userBasInfo.getUserRlvnDeptNm());
				jsonObject.put("userNm",userBasInfo.getUserNm());
				jsonObject.put("genlTelNo",userBasInfo.getGenlTelNo());
				jsonObject.put("mphonNo",userBasInfo.getMphonNo());
				jsonObject.put("emailAdr",userBasInfo.getEmailAdr());
				jsonObject.put("resultCode", "S");
				
			}else{
				jsonObject.put("resultCode", "F");
				jsonObject.put("resultMsg", "[사용불가] 존재하지 않는 아이디 입니다.");
			}
		}
			
		mav.addObject("jsonObject", jsonObject);
		
		return mav;
	}
	
	
	
	/**
	 * <pre>
	 * 로그인 화면 > 사용자 신청 > 등록 
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login/ajaxRegisterUserInfo.do")
	public ModelAndView registerUserInfo(HttpServletRequest request, @ModelAttribute UserBasInfo dbParams)throws Exception{
		
		JSONObject jsonObject = new JSONObject();
		ModelAndView mav = new ModelAndView("jsonView");

		//사용자상태 (요청)
		dbParams.setUserSttusVal("Y");
		
		int result=0;
		result = userService.insertRegisterUserInfo(dbParams);
		
		if(result > 0){
			jsonObject.put("resultCode", "S");
			jsonObject.put("resultMsg", "사용자 등록이 완료 되었습니다. \n담당자 승인처리 후 로그인 가능합니다.");
			
		}else{
			jsonObject.put("resultCode", "F");
			jsonObject.put("resultMsg", "[ERROR] 처리중 오류가 발생 했습니다.");
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
