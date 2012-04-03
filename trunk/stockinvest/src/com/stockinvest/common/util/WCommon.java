package com.stockinvest.common.util;

import java.util.Locale;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * <pre>WCommon :Context loding 시 수행되는 초기값 처리 </pre>
 *
 * @author 한언주
 */
public class WCommon {
	private static WCommon wc = new WCommon();
	
	private WCommon() {

	}
    public static final Logger LOG  = Logger.getLogger(WCommon.class);

    private static WebApplicationContext webApplicationContext;
    private static ApplicationContext applicationContext;
    private MessageSource messageSource;
    
    public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource megsrc) {
		this.messageSource = megsrc;
	}

	/**
     * �� Ŭ������ ���� �ʱ�ȭ �մϴ�.
     * @param sctx ServletContext
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public static void initialize(final ServletContext sctx)
        throws Exception {
    	webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(sctx);
    	
    	//messageSource setting
    	wc.setMessageSource((MessageSource)webApplicationContext.getBean("messageSource"));

    }

    /**
     *
     * @throws Exception
     */
    public static void distory(ServletContext sctx)
        throws Exception {
    	
    }
    
    /**
     * 
     * get Object Instance
     * @param beanName
     */
    public static Object getBean(String beanName) { 
    	if(webApplicationContext == null)
    	{
    		if(applicationContext != null)
    		{
    			return applicationContext.getBean(beanName);
    		}
    		else
    		{
    			return null;
    		}
    	}
    	return webApplicationContext.getBean(beanName);
    }    
    
    /**
     * 
     * @param code
     * @param args
     * @param locale
     * @return
     */
    public static String getMessage(String code, Object[] args, Locale locale) {
    	return wc.getMessageSource().getMessage(code, args, locale);
    }

    /**
     * 
     * @param code
     * @param args
     * @param defaultMessage
     * @param locale
     * @return
     */
    public static String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
    	return wc.getMessageSource().getMessage(code, args, defaultMessage, locale);
    }
    

	public static WebApplicationContext getWebApplicationContext() {
		return webApplicationContext;
	}

	public static void setWebApplicationContext(
			WebApplicationContext webApplicationContext) {
		WCommon.webApplicationContext = webApplicationContext;
	}      
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static void setApplicationContext(ApplicationContext applicationContext) {
		WCommon.applicationContext = applicationContext;
	}

	/**
	 * @see resourc= "/WEB-INF
	 */
	public static String getRealPath(String resource) {
		return webApplicationContext.getServletContext().getRealPath(resource);
	}

	public static WCommon getInstance()
	{
		return wc;
	}
}
