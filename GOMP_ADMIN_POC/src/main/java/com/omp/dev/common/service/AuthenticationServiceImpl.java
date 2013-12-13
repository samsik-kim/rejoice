package com.omp.dev.common.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import com.omp.commons.service.AbstractService;

public class AuthenticationServiceImpl
	extends AbstractService implements AuthenticationService {

	public String doLogin(HttpSession session) {
		// TODO
		Object userInfo = new Object();
		
		Map loginUsersMap = getLoginUsersMap(session);
		loginUsersMap.put(session.getId(), userInfo);
		
		return null;
	}
	
	private Map<?,?> getLoginUsersMap(HttpSession session) {
		ServletContext servletContext = session.getServletContext(); 
    	Map<?,?> loginUsersMap = (HashMap<?,?>)servletContext.getAttribute("loginUsersMap");
    	if(loginUsersMap == null){
    		loginUsersMap = new HashMap<String,Object>();
    		servletContext.setAttribute("loginUsersMap", loginUsersMap);
    	}
		return loginUsersMap;
	}
	
	public boolean isLogined(HttpSession session) {
		if(session != null){   	
	    	Map<?,?> loginUsersMap = getLoginUsersMap(session);	    	
	    	return loginUsersMap.containsKey(session.getId());			
		}
		return Boolean.FALSE;
	}

}
