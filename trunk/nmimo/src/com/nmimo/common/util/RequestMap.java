package com.nmimo.common.util;

import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RequestMap {
	
	/** LOG4J */
	static Log logger = LogFactory.getLog(RequestMap.class);
	
	/**
	 * <pre>
	 * 요청 받은 Request 값을 HashMap 으로 반환
	 * </pre>
	 *
	 * @param request HttpServletRequest
	 * @return HashMap<String, String>
	 */
	public static HashMap<String, String> getMapping(HttpServletRequest request) {
		Enumeration<?> em = request.getParameterNames();
        HashMap<String, String> hm = new HashMap<String, String>();
        
        try {
        	logger.debug("___________________________________________________________________________");
        	logger.debug(DateUtils.getToday("GGG yyyy MMMMM dd EEE  HH:mm:ss"));
        	
        	System.err.println();
	        while(em.hasMoreElements()){
	        	
	        	String key = (String)em.nextElement();
	        	String tValue = StringUtils.nvlStr((String)request.getParameter(key));
	        	String value = URLDecoder.decode(tValue, "UTF-8");
	        	
	        	logger.debug("Map key ::  "+StringUtils.RPAD(key, 40, ' ')+"value ::  "+value);
				hm.put(key, value);
	        }
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return hm;
	}
	
	public static void debugHashMap(HashMap<String, String> map){
		Iterator<?> ir = map.keySet().iterator();
		logger.debug("___________________________________________________________________________");
		logger.debug(DateUtils.getToday("GGG yyyy MMMMM dd EEE  HH:mm:ss"));
		while (ir.hasNext()) {
			String key = (String)ir.next();
			String value = (String)map.get(key);
			logger.debug("Debug Map key :: "+StringUtils.RPAD((String)ir.next(), 50, ' ')+"value ::  "+value);
		}
	}
}