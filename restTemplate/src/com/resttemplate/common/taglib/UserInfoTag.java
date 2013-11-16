package com.resttemplate.common.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.resttemplate.sample.bean.TestBean;

@SuppressWarnings("serial")
public class UserInfoTag extends TagSupport {
	
    private final Log logger = LogFactory.getLog(getClass());
	
	private String var;


	public UserInfoTag() {
        init();
    }

    private void init() {
        var = "";
    }    

    public void release() {
        super.release();
        init();
    }

    /**
     * <pre>
     * {@inheritDoc}
     * </pre>
     *
     * @see org.springframework.web.servlet.tags.RequestContextAwareTag#doStartTagInternal()
     *
     * @return
     * @throws Exception
     *
     */
	public int doStartTag() throws JspException {
    	HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		HttpSession session = request.getSession();
		TestBean sessionInfo = (TestBean) session.getAttribute("testBean");
//		SessionInfo sessionInfo = (SessionInfo) sh.getSessionInfo(request,ServiceConstants.Member.SESSION_LOGIN_INFO_KEY);
//		try{
//			if(logger.isDebugEnabled()){
//				logger.debug("[Member Debug] UserInfoTag sessionInfo.getUserId()	:"+sessionInfo.getUserId()); 
//				logger.debug("[Member Debug] UserInfoTag sessionInfo.getLoginYn()	:"+sessionInfo.getLoginYn()); 
//				logger.debug("[Member Debug] UserInfoTag sessionInfo.getUserNm()	:"+sessionInfo.getUserNm()); 
//			}
//		}catch(Exception e){
//			if(logger.isDebugEnabled()){
//				logger.debug("[Member Debug] message:"+e.getMessage());
//			}
//		}
//		SessionInfo sessionInfo2 = (SessionInfo)SessionHandler.getInstance().getLoginInfo(request);
//		try{
//			if(logger.isDebugEnabled()){
//				logger.debug("[Member Debug] UserInfoTag sessionInfo2.getUserId()	:"+sessionInfo2.getUserId());               
//				logger.debug("[Member Debug] UserInfoTag sessionInfo2.getLoginYn()	:"+sessionInfo2.getLoginYn()); 
//				logger.debug("[Member Debug] UserInfoTag sessionInfo.getUserNm()	:"+sessionInfo.getUserNm()); 
//			}
//		}catch(Exception e){
//			if(logger.isDebugEnabled()){
//				logger.debug("[Member Debug] message:"+e.getMessage());
//			}
//		}
//
//		if(SessionHandler.getInstance().isLoginIfo(request)){
			try {
				 pageContext.setAttribute(var, sessionInfo);
			} catch(RuntimeException e) {
				logger.error("codeListTag error for <var='" + var + "'> : " + e);
			}
			
//		}
		return SKIP_BODY;
    }

	/**
	 * @return the var
	 */
	public String getVar() {
		return var;
	}

	/**
	 * @param var the var to set
	 */
	public void setVar(String var) {
		this.var = var;
	}

}