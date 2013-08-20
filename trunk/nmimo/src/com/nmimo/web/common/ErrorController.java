package com.nmimo.web.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nmimo.common.configuration.ConfigurationService;
import com.nmimo.common.util.StringUtils;

@Controller
public class ErrorController {
	/** LOG4J */
	Log logger = LogFactory.getLog(getClass());
	
	/** Message 관련 */
	@Autowired
	private MessageSourceAccessor message;

	@Autowired
	ConfigurationService config;
	
	/**
	 * 에러 페이지
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/common/error.do")
	public String error(HttpServletRequest request)throws Exception{
		
		String errorCode = "";
		
	try{
			if(logger.isDebugEnabled()){
				logger.debug(">>> UserLoginController error()... <<<");
			}
			errorCode = StringUtils.nvlStr(request.getParameter("code"),"500");
			String exName = StringUtils.nvlStr(request.getParameter("exName"));
			if(!"".equals(exName)){
				errorCode = "none";
			}
			String requestURI = StringUtils.nvlStr(request.getParameter("requestURI"));
			String throwable = StringUtils.nvlStr(request.getParameter("throwable"));
	
			
			String s_errorMessage="http.error.message."+errorCode;
			String s_errorDesc="http.error.desc."+errorCode;
			String s_errorSolution="http.error.solution."+errorCode;
			
			
			String errorMessage = "";
			String errorDesc = "";
			String errorSolution = "";
			
			errorMessage = StringUtils.nvlStr(message.getMessage(s_errorMessage));
			errorDesc = StringUtils.nvlStr(message.getMessage(s_errorDesc));
			errorSolution = StringUtils.nvlStr(message.getMessage(s_errorSolution));
	
			
			
			request.setAttribute("errorCode", errorCode);
			request.setAttribute("errorMessage", errorMessage);
			request.setAttribute("errorDesc", errorDesc);
			request.setAttribute("errorSolution", errorSolution);
			request.setAttribute("throwable", throwable);
			request.setAttribute("exName", exName);
			
	
		}catch(Exception ex){
			logger.error(ex);
		}
		
		if("500".equals(errorCode)){
			return "common/error/error";
		}else{
			return "common/error/errorNoDecorator";
		}
	}
	/**
	 * 에러 팝업 페이지
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/common/errorPopup.do")
	public String errorPopup(HttpServletRequest request)throws Exception{
		if(logger.isDebugEnabled()){
			logger.debug(">>> UserLoginController error()... <<<");
		}
		
		String errorCode = "";
		
	try{
			
			errorCode = StringUtils.nvlStr(request.getParameter("code"),"500");
			String exName = StringUtils.nvlStr(request.getParameter("exName"));
			String requestURI = StringUtils.nvlStr(request.getParameter("requestURI"));
			String throwable = StringUtils.nvlStr(request.getParameter("throwable"));
	
			
			String s_errorMessage="http.error.message."+errorCode;
			String s_errorDesc="http.error.desc."+errorCode;
			String s_errorSolution="http.error.solution."+errorCode;
			
			String errorMessage = "";
			String errorDesc = "";
			String errorSolution = "";
			
			errorMessage = StringUtils.nvlStr(message.getMessage(s_errorMessage));
			errorDesc = StringUtils.nvlStr(message.getMessage(s_errorDesc));
			errorSolution = StringUtils.nvlStr(message.getMessage(s_errorSolution));
	
			
			
			request.setAttribute("errorCode", errorCode);
			request.setAttribute("errorMessage", errorMessage);
			request.setAttribute("errorDesc", errorDesc);
			request.setAttribute("errorSolution", errorSolution);
			request.setAttribute("throwable", throwable);
			request.setAttribute("exName", exName);
			
		}catch(Exception ex){
			logger.error(ex);
		}
		
		if("500".equals(errorCode)){
			return "common/error/errorPopup";
		}else{
			return "common/error/errorNoDecoratorPopup";
		}
	}
	

}
