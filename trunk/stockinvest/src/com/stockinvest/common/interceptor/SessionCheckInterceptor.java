/**
 * 로그인 이후의 요청 부터 
 * 모든 요청 에 대해
 * Header에 들어가야 하는 공통 내역을 검증한다.
 */
package com.stockinvest.common.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.activemq.command.SessionInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import tframe.common.configuration.ConfigurationService;
import tframe.web.session.SessionHandler;

public class SessionCheckInterceptor extends HandlerInterceptorAdapter {

	/** LOG4J 설정을 위한 Log 객체 */
	Log logger = LogFactory.getLog(getClass());

	/** 환경변수 처리를 하기 위한 ConfigurationService 객체 */
	@Resource(name="configurationService")
	ConfigurationService config;
	
	/** 메세지 처리를 하기 위한 MessageSourceAccessor 객체 */
	@Resource(name="messageSourceAccessor")
	protected MessageSourceAccessor message;

	private JSONObject jsonObject;
	private Boolean isSucess=true;
	/*
	 * <pre>
	 * Controller 실행 요청전에 수행
	 * </pre>
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug(" >>> LoginInterceptor - preHandle(Controller 실행 요청전에 수행) <<<");
		}
		SessionInfo member = (SessionInfo)SessionHandler.getInstance().getLoginInfo(request);
		HttpSession session = request.getSession(true);

		if(logger.isDebugEnabled()){
			logger.debug("[Member Debug] LoginInterceptor.preHandle : session.isNew():"+session.isNew());
		}
		
		String contextPath = request.getContextPath();
		
		String uriinfo = request.getRequestURI();
		if (session.getAttribute("RETURI") != null) {
			//	session.removeAttribute("RETURI");
			}
		if(contextPath!=null && uriinfo!=null && uriinfo.length()>contextPath.length()){
			if(contextPath.equals(uriinfo.substring(0,contextPath.length()))){
				uriinfo = uriinfo.substring(contextPath.length(), uriinfo.length());
			}
		}

		int k = 0;
		String andstring = "";
		String paramdata = "";
		
		for (java.util.Enumeration hparam = request.getParameterNames(); hparam
			.hasMoreElements(); k++) {
			String paramname = (String) hparam.nextElement();
			String[] paramvalue = request.getParameterValues(paramname);
			if (k > 0) {
				andstring = "&";
			} else {
				andstring = "?";
			}
			
			for (int i = 0; i < paramvalue.length; i++) {
					if (i > 0) {
							andstring = "&";
					}
					paramdata += andstring + paramname + "=" + paramvalue[i];
			}
		}
		
		String returi = uriinfo + paramdata;
//		if(logger.isDebugEnabled()){
//			logger.debug("[Member Debug] returi:"+returi);
//			logger.debug("[Member Debug] CipherAES.encrypt(returi)"+CipherAES.encrypt(returi));
//		}
//		returi=CipherAES.encrypt(returi);
//		/* Session 유무 확인 */
//		if(member == null || !"Y".equals(member.getLoginYn())){
//			if(logger.isDebugEnabled()){
//				logger.debug("===========================================================");
//				logger.debug("Login Check Interceptor - Login Fail(session == " + member + ")");
//				logger.debug("===========================================================");
//			}			
//			
//			if(returi.indexOf("Iframe.do")>=0){
//				session.setAttribute("RETURIIFRAME", returi);
//	        	response.sendRedirect(contextPath+config.getString("url.dnc.loginIframe"));
//				return false;
//	        }else if (!isAjaxRequest(request)) {// Ajax 호출이 아닐 경우
//
//				/* Return URL */
//				session.setAttribute("RETURI", returi);
//				response.sendRedirect(contextPath+config.getString("url.dnc.login"));
//				return false;
//	        }else{
//	        	jsonObject = new JSONObject();
//    			jsonObject.put("resultCode", "fail");
//    			jsonObject.put("resultMessage", message.getMessage("error.security.login.timeout"));
//    			this.setJsonObject(jsonObject);
//    			this.setIsSucess(false);
//	        }
//
//		}
		
//		Boolean loginSession = SessionHandler.getInstance().isLoginIfo(request);
//		/* Session의 LOGIN_SESSION 유무 확인 */
//		if(!loginSession && !"Y".equals(member.getLoginYn())){
//			if(logger.isDebugEnabled()){
//				logger.debug("==========================================================");
//				logger.debug("Login Check Interceptor - Login Fail(LOGIN_SESSION == " + loginSession + ")");
//				logger.debug("==========================================================");
//			}
//			if(returi.indexOf("Iframe.do")>=0){
//				session.setAttribute("RETURIIFRAME", returi);
//	        	response.sendRedirect(contextPath+config.getString("url.dnc.loginIframe"));
//				return false;
//	        }else if (!isAjaxRequest(request)) {// Ajax 호출이 아닐 경우
//
//				/* Return URL */
//				session.setAttribute("RETURI", returi);
//				response.sendRedirect(contextPath+config.getString("url.dnc.login"));
//				return false;
//	        }else{
//	        	jsonObject = new JSONObject();
//    			jsonObject.put("resultCode", "fail");
//    			jsonObject.put("resultMessage", message.getMessage("error.security.login.timeout"));
//    			this.setJsonObject(jsonObject);
//    			this.setIsSucess(false);
//	        }
//		}
		return true; 
	}
	
	/*
	 * <pre>
	 * view(jsp)로 forward되기 전에 수행
	 * </pre>
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug(" >>> LoginInterceptor - postHandle(view(jsp)로 forward되기 전에 수행) <<<");
			logger.debug("[Member Debug] \n-----------------------------------------------");
			logger.debug("[Member Debug] isAjaxRequest(request): "+isAjaxRequest(request));
			logger.debug("[Member Debug] this.getIsSucess(): "+this.getIsSucess());
			logger.debug("[Member Debug] -----------------------------------------------\n");
		}
		
		
		if (isAjaxRequest(request) && !this.getIsSucess()) {// Ajax 호출일 경우
			modelAndView.addObject("jsonObject",this.getJsonObject());
        }   
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
	
	/*
	 * <pre>
	 * Controller 실행 끝난뒤 수행
	 * </pre>
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug(" >>> LoginInterceptor - afterCompletion(Controller 실행 끝난뒤 수행) <<<");
		}
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

    /**
     * @param request the request object
     * @return true if this request is an ajax request.  This is determined by a configured
     *         name/value pair that is applied to the request header
     */
    protected boolean isAjaxRequest(HttpServletRequest request) {
		//XMLHttpRequest
		if("XMLHttpRequest".equalsIgnoreCase(request.getHeader("x-requested-with")) || "Ajax".equalsIgnoreCase(request.getHeader("x-requested-with"))){
			return true;
		}
        return false;
    }

	/**
	 * @return the jsonObject
	 */
	public JSONObject getJsonObject() {
		return jsonObject;
	}

	/**
	 * @param jsonObject the jsonObject to set
	 */
	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	/**
	 * @return the isSucess
	 */
	public Boolean getIsSucess() {
		return isSucess;
	}

	/**
	 * @param isSucess the isSucess to set
	 */
	public void setIsSucess(Boolean isSucess) {
		this.isSucess = isSucess;
	}
}
