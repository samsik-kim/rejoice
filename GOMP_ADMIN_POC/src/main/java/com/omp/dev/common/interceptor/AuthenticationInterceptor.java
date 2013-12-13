package com.omp.dev.common.interceptor;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.omp.commons.util.GLogger;
import com.omp.commons.util.SessionUtil;
import com.omp.commons.util.config.ConfigProperties;
import com.omp.dev.common.Constants;
import com.opensymphony.module.sitemesh.mapper.PathMapper;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.TextParseUtil;

@SuppressWarnings("serial")
public class AuthenticationInterceptor extends AbstractInterceptor{
	private static GLogger logger = new GLogger(AuthenticationInterceptor.class);
	private static ConfigProperties conf = new ConfigProperties();
    @SuppressWarnings("unchecked")
	protected Set excludesPattern;
	protected String redirectPage = "/login/loginMain.omp";
	protected String returnAction;
	private PathMapper pathMapper = null;

	public AuthenticationInterceptor(){
		super();
		pathMapper = new PathMapper();
	}
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
	    
	    HttpServletRequest request = ServletActionContext.getRequest();        
		logger.debug("<AuthenticationInterceptior> intercept ...");
		
		if(isAvailableActionWithoutLogin()){
			logger.debug("<AuthenticationInterceptior> isAvailableActionWithoutLogin() = true");
			request.getSession(true).setAttribute(Constants.RETURN_ACTION, conf.getString("omp.common.url-prefix.http.dev") + request.getContextPath() + returnAction);
            return invocation.invoke();
		}else{
			if(SessionUtil.existMemberSession(request)){
                logger.debug("<AuthenticationInterceptior> member session exist.");
                return invocation.invoke();
            }else{
                logger.debug("<AuthenticationInterceptior> member session NOT exist.");
                saveReturnURL(request);
                doRedirect();
                return null;
            }
		}
	}

	private void doRedirect() throws IOException {
		StringBuffer sb = new StringBuffer(ServletActionContext.getRequest().getContextPath());
		sb.append((!(redirectPage.startsWith("/")) && !(redirectPage.startsWith("\\"))) ? "/" : "");
		sb.append(redirectPage); 
		
		String redirectUri =  conf.getString("omp.common.url-prefix.http.dev") + sb.toString();
		if( logger.isDebugEnabled() ) logger.debug("Redirect Uri : " + redirectUri);
		ServletActionContext.getResponse().sendRedirect(redirectUri);
	}

	private boolean isAvailableActionWithoutLogin() {
		String requestedAction = getRequestedAction(ServletActionContext.getRequest());
		if( logger.isDebugEnabled() ) {
			logger.debug("Requested Action : " + requestedAction);
			logger.debug("pathMapper.get("+requestedAction+") : " + pathMapper.get(requestedAction));
		}
		if(requestedAction.equals("/login/loginMain.omp")){
			returnAction = "/login/loginMain.omp";
		}else if(requestedAction.equals("/main/main.omp")){
			returnAction = "/main/main.omp";
			if(!SessionUtil.existMemberSession(ServletActionContext.getRequest())){
				ServletActionContext.getRequest().getSession(true).setAttribute(Constants.RETURN_URL_KEY, "");
			}
		}
		
        return pathMapper.get(requestedAction) != null;
		
	}
 
	private String getRequestedAction(HttpServletRequest request){
		String contextPath = request.getContextPath();
		String requestedUri = request.getRequestURI();
		
		if( "/".equals(contextPath) ){
			return requestedUri;
		}else{
			return requestedUri.substring(contextPath.length());
		}
	}

	@SuppressWarnings("unchecked")
	public void setExcludesPattern(String excludesPattern) {
        this.excludesPattern = TextParseUtil.commaDelimitedStringToSet(excludesPattern);
				
		for(Iterator<String> it = this.excludesPattern.iterator(); it.hasNext();){
			String pattern = it.next();
			pathMapper.put(pattern, pattern);
		}
	}
    
	public void setRedirectPage(String redirectPage) {
		if(redirectPage != null && !"".equals(redirectPage)) this.redirectPage = redirectPage;
	}
	
	
	/**
	 * 요청이 이루어진 URL을 리턴 URL로 세션에 저장한다.
	 * @param request
	 */
	private void saveReturnURL(HttpServletRequest request)
    {
        
        StringBuffer returnURL = new StringBuffer();
        
        if(request.getRequestURI().indexOf("/mypage") > -1){
        	returnURL.append(request.getRequestURI());
        }else{
        	returnURL.append(conf.getString("omp.common.url-prefix.http.dev") + request.getRequestURI());
        }
        
        // redirect 되기 이전의 parameter값을 설정함.
        boolean findQuest = false;
        String paramKey = null;
        String paramValue = null;
        Enumeration enumeration = request.getParameterNames();

        while ((enumeration !=null) && enumeration.hasMoreElements() )
        {
            paramKey = (String)enumeration.nextElement();
            paramValue = (String)request.getParameter(paramKey);
    
                if (findQuest){
                    returnURL.append("&");
                }else{
                    returnURL.append("?");
                    findQuest = true;
                }
                returnURL.append(paramKey).append("=").append(paramValue);
        }
        request.getSession(true).setAttribute(Constants.RETURN_URL_KEY, returnURL.toString());
        
        if( logger.isDebugEnabled() ) {
            logger.debug("<AuthenticationInterceptor> [saveReturnURL] Session ["+Constants.RETURN_URL_KEY+"="+returnURL.toString()+"] was saved.");
        }      
    }   
}
