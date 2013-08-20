package com.nmimo.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.nmimo.common.configuration.ConfigurationService;
import com.nmimo.common.info.SessionInfo;
import com.nmimo.common.session.SessionHandler;
import com.nmimo.common.util.CommonUtils;
import com.nmimo.common.util.StringUtils;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	/** LOG4J */
	Log logger = LogFactory.getLog(getClass());
	
	/** Config 관련 */
	@Autowired
	ConfigurationService config;
	
	/** Message 관련 */
	@Autowired
	MessageSourceAccessor message;
	
	
	private JSONObject jsonObject;
	private Boolean isSucess=true;
	
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
		if(logger.isDebugEnabled()){
			logger.debug(" >>> LoginInterceptor - afterCompletion(Controller 실행 끝난뒤 수행) <<<");
		}
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
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
		if(logger.isDebugEnabled()){
			logger.debug(" >>> LoginInterceptor - postHandle(view(jsp)로 forward되기 전에 수행) <<<");
			logger.debug("[Member Debug] \n-----------------------------------------------");
			logger.debug("[Member Debug] isAjaxRequest(request): "+isAjaxRequest(request));
			logger.debug("[Member Debug] this.getIsSucess(): "+this.getIsSucess());
			logger.debug("[Member Debug] -----------------------------------------------\n");
		}
		
		
		//GNB 탑메뉴 이동시 menuId값 저장
		String menuId = StringUtils.nvlStr(request.getParameter("menuId"));
		String subMenuId = StringUtils.nvlStr(request.getParameter("subMenuId"));
		
		if("".equals(menuId) && "".equals(subMenuId)){
			request.setAttribute("menuId", "mynav0");
			request.setAttribute("subMenuId", "mycon00");
		}else{
			request.setAttribute("menuId", "mynav"+menuId);
			request.setAttribute("subMenuId", "mycon"+subMenuId);
		}
		
		if (isAjaxRequest(request) && !this.getIsSucess()) {// Ajax 호출일 경우
			modelAndView.addObject("jsonObject",this.getJsonObject());
        }   
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
	 * @return
	 * @throws Exception
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
		
		if (session.getAttribute("RETURI") != null) {
			session.removeAttribute("RETURI");
		}
		
		String contextPath = request.getContextPath();
		
		String returi = CommonUtils.returnUri(request);
		if(logger.isDebugEnabled()){
			logger.debug("[Member Debug] returi:"+returi);
//			logger.debug("[Member Debug] CipherAES.encrypt(returi)"+CipherAES.encrypt(returi));
		}

		
		/* Session 유무 확인 */
		if(member == null || !"Y".equals(member.getLoginYn())){
			if(logger.isDebugEnabled()){
				logger.debug("===========================================================");
				logger.debug("Login Check Interceptor - Login Fail(session == " + member + ")");
				logger.debug("===========================================================");
			}			
			
			/* Return URL */
			session.setAttribute("RETURI", returi);
			response.sendRedirect(contextPath + config.getString("url.mimo.login"));
			return false;
		}
		
		Boolean loginSession = SessionHandler.getInstance().isLoginIfo(request);
		
		/* Session의 LOGIN_SESSION 유무 확인 */
		if(!loginSession && !"Y".equals(member.getLoginYn())){
			if(logger.isDebugEnabled()){
				logger.debug("==========================================================");
				logger.debug("Login Check Interceptor - Login Fail(LOGIN_SESSION == " + loginSession + ")");
				logger.debug("==========================================================");
			}

			/* Return URL */
			session.setAttribute("RETURI", returi);
			response.sendRedirect(contextPath + config.getString("url.mimo.login"));
			return false;
		}
		return true;
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