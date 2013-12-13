/*
 * $Id: LoginUtil.java,v 1.13 2008/01/09 05:54:15 hahajjang03 Exp $
 *
 * Copyright 2001-2004 by QnSolv Corp., All rights reserved.
 *
 * This software is the confidential and proprietary information of QnSolv Corp.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with QnSolv.
 */
package com.omp.commons.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 세션을 관리를 담당하는 객체
 * 
 * @author 양영열
 *
 */
public class SessionUtil
{
	private static GLogger logger = new GLogger(SessionUtil.class);
	public static final String MEMBER_SESSION_KEY = "MEMBER_SESSION";
	
	
	/**
	 * 회원 세션의 존재 여부를 파악한다.
	 * @param request
	 * @return boolean
	 */
	public static boolean existMemberSession(HttpServletRequest request)
	{
		if (request.getSession(false) == null
			|| (request.getSession().getAttribute(MEMBER_SESSION_KEY) == null))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	/**
	 * 회원정보를 세션에 저장한다.
	 * @param request
	 * @param obj
	 */
	public static void setMemberSession(HttpServletRequest request, Object obj)
	{
	    HttpSession ss = request.getSession(true);
	    ss.setAttribute(MEMBER_SESSION_KEY, obj);
	    ss.setMaxInactiveInterval(1800);
	}
	
	/**
	 * 회원 세션을 삭제한다.
	 * @param request
	 */
	public static void removeMemberSession(HttpServletRequest request)
	{
		HttpSession ss = request.getSession(false);
		if (ss != null)
		{
			ss.removeAttribute(MEMBER_SESSION_KEY);
		}
	}
	
	/**
	 * 회원세션에서 회원정보를 가져온다.
	 * @param request
	 * @return
	 */
	public static Object getMemberSession(HttpServletRequest request)
	{
		Object obj = request.getSession(true).getAttribute(MEMBER_SESSION_KEY);
		if (obj == null)
		{
			logger.debug("LoginUtil : Member information not found.");
			return null;
		}

		return obj;
	}
	
		
	/**
	 * 전달된 세션키로 세션의 정보 유무를 파악한다.
	 * @param request
	 * @param sessionKey 세션키
	 * @return
	 */
	public static boolean existAnySession(HttpServletRequest request, String sessionKey)
    {
        if (request.getSession(false) == null
            || (request.getSession().getAttribute(sessionKey) == null))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
	/**
	 * 전달된 세션키로 해당 정보를 세션에 저장한다.
	 * @param request
	 * @param sessionKey
	 * @param obj
	 */
    public static void setAnySession(HttpServletRequest request, String sessionKey, Object obj)
    {
        request.getSession(true).setAttribute(sessionKey, obj);     
    }
    
    /**
     * 전달된 세션키에 해당하는 정보를 세션에서 삭제한다.
     * @param request
     * @param sessionKey
     */
    public static void removeAnySession(HttpServletRequest request, String sessionKey)
    {
        HttpSession ss = request.getSession(false);
        if (ss != null)
        {
            ss.removeAttribute(sessionKey);
        }
    }
    
    /**
     * 전달된 세션키에 해당하는 정보를 세션에서 조회한다.
     * @param request
     * @param sessionKey
     * @return
     */
    public static Object getAnySession(HttpServletRequest request, String sessionKey)
    {
        Object obj = request.getSession(true).getAttribute(sessionKey);
        if (obj == null)
        {
            logger.debug("LoginUtil : " + sessionKey +" info not found.");
            return null;
        }
        
        return obj;
    }
   
}