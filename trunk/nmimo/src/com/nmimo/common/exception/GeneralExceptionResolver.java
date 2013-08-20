package com.nmimo.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.nmimo.common.ServiceConstants;
import com.nmimo.common.configuration.ConfigurationService;
import com.nmimo.common.info.SessionInfo;
import com.nmimo.common.session.SessionHandler;
import com.nmimo.common.util.CommonUtils;
import com.nmimo.common.util.StringUtils;

public class GeneralExceptionResolver extends SimpleMappingExceptionResolver {
	/** LOG4J 설정을 위한 Log 객체 */
	protected final Log logger = LogFactory.getLog(getClass());
	
	/** 에러로그 출력 여부 ConfigurationService 객체 */
	@Autowired
	ConfigurationService config;	
	
	/** 메세지 처리를 하기 위한 MessageSourceAccessor 객체 */
	@Autowired
	private MessageSourceAccessor message;	
	
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    	ModelAndView modelAndView = super.resolveException(request, response, handler, ex);
    	
    	StringBuilder resultErrorMsg = new StringBuilder();
    	
    	String returnUri = CommonUtils.returnUri(request);
    	
    	logger.debug("RETURNURI : " + returnUri);
    	
        logger.debug("========== [ 에러 발생 ] ============");
        logger.error(handler, ex);
        
        String statusYn[] = config.getString("error.log.html.printYn").split("#");
        
        if("Y".equals(statusYn[0])){
        
        	request.setAttribute("errorPrintYn",config.getString("error.log.html.printYn"));
        	String handlerMsg = handler.toString();
        	
        	request.setAttribute("returnUri", returnUri);
        	
        	
	        // 화면에 메시지를 출력하기 위한 처리--------------------------------------------------------------------------------------------
        	String message = ex.getMessage();
        	
	        request.setAttribute("message", message);
	        resultErrorMsg.append("[[ message : "+message+"]]<br />");
	        
	        if(ex.getCause()!=null){
	        	String causeMessage = ex.getCause().getMessage();
	            request.setAttribute("causeMessage", causeMessage);
	            resultErrorMsg.append("[[ causeMessage : "+ causeMessage+"]]<br />");
	        }
	        
	        // 화면에 StackTrace를 출력하기 위한 처리--------------------------------------------------------------------------------------
	        StackTraceElement[] stackTraces = ex.getStackTrace();
	        StringBuilder sb = new StringBuilder();
	
	        for (int i = 0; i < stackTraces.length; i++) {
	            sb.append(stackTraces[i].toString() + "\n");
	        }
	
	        Throwable throwable = ex.getCause();
	
	        if (throwable != null) {
	            sb.append("##### 원인 : " + throwable.getClass().getName() + "#####\n");
	            sb.append("\n");
	            stackTraces = throwable.getStackTrace();
	            for (int j = 0; j < stackTraces.length; j++) {
	                sb.append(stackTraces[j].toString() + "\n");
	            }
	        }
	
	        request.setAttribute("stacktrace", sb.toString());
	        resultErrorMsg.append("[[ causeMessage : "+ sb.toString()+"]]");
        }
        String returnURI = StringUtils.nvlStr(request.getRequestURI());
        if(isAjaxRequestConfirm(request)){
        	
        	String startTag = "<div>";
        	String endTag = "</div>";
        	
        	if(!"".equals(resultErrorMsg)){
        		startTag = "<div style='width:100%;height:100px;word-break:break-all;word-wrap:break-word;overflow-y:auto;'>";
        	}
        	
        	String errMsg = message.getMessage("result.msg.error")+startTag+resultErrorMsg.toString()+endTag;
        	
    		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
    		
			jsonObject.put("resultCode", ServiceConstants.Common.RST_CODE_ERROR);
			jsonObject.put("resultMessage", errMsg);
			modelAndView.addObject("jsonObject", jsonObject);
    		
    		setDefaultErrorView("jsonSFView");
        		
        }else if(returnURI.indexOf("Ppuop.do")>=0){
        	setDefaultErrorView("forward:/common/errorPopup.do");
        }else{
        	setDefaultErrorView("forward:/common/error.do");
        }

        return modelAndView;
    }
   
    /**
     * <pre>
     * 요청 상태가 AJAX 여부를 체크한다.
     * </pre>
     * @param request HttpServletRequest 객체
     * @return 요청 상태가 AJAX 방식일 경우 TRUE를 리턴하고 그외의 경우에는 FALSE를 리턴한다.
     */
    private boolean isAjaxRequestConfirm(HttpServletRequest request) {
    	
    	logger.debug("GeneralExceptionResolver:::x-requested-with:"+request.getHeader("x-requested-with"));
		if("XMLHttpRequest".equalsIgnoreCase(request.getHeader("x-requested-with")) || "Ajax".equalsIgnoreCase(request.getHeader("x-requested-with"))){
			return true;
		}
        return false;
    }    
}
