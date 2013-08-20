package com.nmimo.common.interceptor;

import java.net.URLDecoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.nmimo.common.ServiceConstants;
import com.nmimo.common.ServiceConstants.Member;
import com.nmimo.common.configuration.ConfigurationService;
import com.nmimo.common.info.PSSOCookieInfo;
import com.nmimo.common.info.SessionInfo;
import com.nmimo.common.session.SessionHandler;
import com.nmimo.common.util.StringUtils;
import com.nmimo.data.user.info.UserInfo;
import com.nmimo.service.user.UserService;

public class CrmLoginInterceptor extends HandlerInterceptorAdapter{

	/** LOG4J */
	Log logger = LogFactory.getLog(getClass());
	
	/** Message 관련 */
	@Autowired
	MessageSourceAccessor message;
	
	@Autowired
	private ConfigurationService config;
	
	/** USER 정보 */
	@Autowired
	UserService userService;
	
	/**
	 * <pre>
	 * 기타연동 PreHandle
	 * </pre>
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		if(logger.isDebugEnabled()){
			logger.debug(" >>> CrmLoginInterceptor - preHandle(Controller 실행 요청전에 수행) <<<");
		}
				
		SessionInfo sessionInfo = new SessionInfo();
		String uriInfo = request.getRequestURI();
		RequestDispatcher rd = request.getRequestDispatcher(ServiceConstants.Crm.RESULT_URL);
		
		//CRM제공 URL주소
		if(uriInfo.equals("/crm/campaign.do")){

			if(logger.isDebugEnabled()){
				logger.debug(" >>> CrmLoginInterceptor - 1./crm/campaign.do URL요청 <<<");
			}
			
			String userId = StringUtils.nvlStr(request.getParameter("userId"));
			String jobId = StringUtils.nvlStr(request.getParameter("jobId"));

			//필수 파라미터 유무확인(userId)
			if(!"".equals(userId)){
				
				UserInfo dbParams = new UserInfo();
				UserInfo userInfo = new UserInfo();
				
				dbParams.setUserId(userId);

				//TODO DB설정후 적용
				//userId 정보로 로컬DB조회 
				//userInfo = userService.selectUserLoginChk(dbParams);
				userInfo.setUserId("admin");
				userInfo.setUserNm("관리자");
				userInfo.setAuthority("MA");
				
				if(userInfo != null){

					sessionInfo.setUserId(userInfo.getUserId());
					sessionInfo.setAuthority(userInfo.getAuthority());
					sessionInfo.setJobId(jobId);
					
					SessionHandler<SessionInfo> sh = new SessionHandler<SessionInfo>();
					sh.setSessionInfo(request, "TEMP_SESSION", sessionInfo);
					
					//PSSO Cookie 체크
					String hostUrl;
					StringBuffer str = new StringBuffer();
					str.append(config.getString("PSSO.LOGIN.COOKIE.HOST.URL")).append("url=");
					str.append(config.getString("PSSO.LOGIN.COOKIE.RETURN.URL")).append("&").append("clientkey=");
					str.append(config.getString("PSSO.CILENTKEY")).append("&").append("site=");
					str.append(config.getString("PSSO.LOGIN.SITE"));
					hostUrl = str.toString();

					
					
					response.sendRedirect(hostUrl);
				}else{
					
					if(logger.isDebugEnabled()){
						logger.debug(" >>> CrmLoginInterceptor - 1.IDMS정보 조회 오류 <<<");
					}
					
					//DB조회 오류시 리턴 & 메세지 입력
					request.setAttribute(ServiceConstants.Common.RESULT_CODE, ServiceConstants.Crm.RESULT_CODE_FAIL);
					request.setAttribute(ServiceConstants.Common.RESULT_MSG, config.getString(ServiceConstants.Crm.RESULT_MSG_FAIL_401));
					rd.forward(request, response);
				}

			}else{

				if(logger.isDebugEnabled()){
					logger.debug(" >>> CrmLoginInterceptor - 1.필수파라미터 오류 <<<");
				}
				
				//필수 파라미터 미존재시 리턴 & 메세지 입력
				request.setAttribute(ServiceConstants.Common.RESULT_CODE, ServiceConstants.Crm.RESULT_CODE_FAIL);
				request.setAttribute(ServiceConstants.Common.RESULT_MSG, config.getString(ServiceConstants.Crm.RESULT_MSG_FAIL_400));
				rd.forward(request, response);
			}
			
			return false;

		}else if(uriInfo.equals("/crm/pssoChk.do")){	
			
			if(logger.isDebugEnabled()){
				logger.debug(" >>> CrmLoginInterceptor - 2./crm/pssoChk.do URL요청 <<<");
				
				logger.debug(" >>> CrmLoginInterceptor - #################################################### 쿠키체크 START ######################################### <<<");
				Cookie[] a = request.getCookies();
				
				logger.debug("쿠키COUNT >> "+a.length);
				
				for(int i=0; i<a.length; i++){
					String name2 = URLDecoder.decode(a[i].getName(), "UTF-8");
					String value2 = URLDecoder.decode(a[i].getValue(), "UTF-8");
					
					logger.debug("name =>"+name2);
					logger.debug("value =>"+value2);
				}
				
				logger.debug(" >>> CrmLoginInterceptor - #################################################### 쿠키체크 END ######################################### <<<");
			}
			
			if(request.getParameter("SSO")!=null &&	Member.PSSO_LOGIN_FAIL.equals(request.getParameter("SSO"))){

				if(logger.isDebugEnabled()){
					logger.debug(" >>> CrmLoginInterceptor - 2.PSSO쿠키체크 오류 <<<");
				}
				
				//TempSession삭제
				SessionHandler.getInstance().removeSessionInfo(request, "TEMP_SESSION");
				SessionHandler.getInstance().invalidateSession(request);
				HttpSession tempSesseion = request.getSession(true);
				tempSesseion.removeAttribute("TEMP_SESSION");
				tempSesseion.invalidate();
				
				//PSSO쿠키 체크 실패 및 메세지입력 
				request.setAttribute(ServiceConstants.Common.RESULT_CODE, ServiceConstants.Crm.RESULT_CODE_FAIL);
				request.setAttribute(ServiceConstants.Common.RESULT_MSG, config.getString(ServiceConstants.Crm.RESULT_MSG_FAIL_402));
				rd.forward(request, response);
				
				return false;
			}

			SessionInfo user = (SessionInfo)SessionHandler.getInstance().getSessionInfo(request, "TEMP_SESSION");
			
			//Cookie 값을 PSSO객체에 저장
			PSSOCookieInfo pssoCookieInfo = new PSSOCookieInfo();
			
			Cookie[] c = request.getCookies();
			for(int i=0; i<c.length; i++){
				String name = URLDecoder.decode(c[i].getName(), "UTF-8");
				String value = URLDecoder.decode(c[i].getValue(), "UTF-8");

				if(name.equals("PSSO_OTP_YN")){
					pssoCookieInfo.setPSSO_OTP_YN(value); 
				}else if(name.equals("PSSO_keyid")){
					pssoCookieInfo.setPSSO_keyid(value); 
				}else if(name.equals("PSSO_UserName")){
					pssoCookieInfo.setPSSO_UserName(value); 
				}else if(name.equals("PSSO_UserID")){
					pssoCookieInfo.setPSSO_UserID(value); 
				}else if(name.equals("PSSO_enckey")){
					pssoCookieInfo.setPSSO_enckey(value); 
				}   
			}
			
			//session 생성후 PSSO연동 처리값 입력
			sessionInfo.setUserId(user.getUserId());
			sessionInfo.setUserNm(user.getUserNm());
			sessionInfo.setAuthority(user.getAuthority());
			sessionInfo.setJobId(user.getJobId());
			sessionInfo.setLoginYn(ServiceConstants.Member.SESSION_LOGIN_Y);
			sessionInfo.setPSSOLoginYn(ServiceConstants.Member.PSSO_LOGIN_Y);

			SessionHandler<SessionInfo> sh = new SessionHandler<SessionInfo>();
			sh.setSessionInfo(request, "CRM_SESSION", sessionInfo);
			
			request.setAttribute(ServiceConstants.Common.RESULT_CODE, ServiceConstants.Crm.RESULT_CODE_SUCCESS);
			
			//JobId값이 존재하는경우 ( 메세지View ) , 없는경우 ( List 페이지 ) 
			if("".equals(sessionInfo.getJobId())){
				request.setAttribute(ServiceConstants.Common.RETURN_URL, ServiceConstants.Crm.RESULT_LIST_URL);				
			}else{
				request.setAttribute(ServiceConstants.Common.RETURN_URL, ServiceConstants.Crm.RESULT_VIEW_URL);
			}

			rd.forward(request, response);
			
			return false;
			
		}else{	
			
			//로그인여부 Session 체크
			SessionInfo member = (SessionInfo)SessionHandler.getInstance().getSessionInfo(request, "CRM_SESSION");

			if(member == null || !"Y".equals(member.getLoginYn()) || !"Y".equals(member.getPSSOLoginYn())){
				
				if(logger.isDebugEnabled()){
					logger.debug(" >>> CrmLoginInterceptor - 3.PSSO쿠키체크 오류 <<<");
				}
				
				request.setAttribute(ServiceConstants.Common.RESULT_CODE, ServiceConstants.Crm.RESULT_CODE_FAIL);
				request.setAttribute(ServiceConstants.Common.RESULT_MSG, config.getString(ServiceConstants.Crm.RESULT_MSG_FAIL_402));
				rd.forward(request, response);
				
				return false;
			}
		}
		
		return super.preHandle(request, response, handler);
	}

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @throws Exception
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @throws Exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
}