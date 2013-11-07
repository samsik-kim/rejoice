package com.omp.admin.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.omp.admin.adminmgr.auth.model.AdMgr;
import com.omp.admin.adminmgr.auth.model.AdSession;

public class SessionManager {


	//	private static boolean setSession(HttpServletRequest req, String sessionKey, Object obj) {
	//
	//		if (obj != null) {
	//
	//			HttpSession session = req.getSession(true);
	//			session.setMaxInactiveInterval(60 * 30); // 세션시간(초)
	//			session.setAttribute(sessionKey, obj);
	//
	//			return true;
	//		}
	//
	//		return false;
	//	}

	private static Object getSession(HttpServletRequest req, String sessionKey) {

		if (isValidSession(req)) {
			return req.getSession(false).getAttribute(sessionKey);
		}

		return null;
	}

	public static boolean isValidSession(HttpServletRequest req) {
		return (req != null && req.getSession(false) != null);
	}

	public static boolean removeSession(HttpServletRequest req, HttpServletResponse res) {
		if (isValidSession(req)) {
			req.getSession(false).invalidate();
			return true;
		}
		return false;
	}

	public static void removeCookie(HttpServletResponse res, String coName, String domain) {
		Cookie cookie = new Cookie(coName, null);
		cookie.setDomain(domain);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		res.addCookie(cookie);
	}

	public static HttpSession getHttpSession(HttpServletRequest req) {
		if (isValidSession(req)) {
			return req.getSession(false);
		}
		return null;
	}

	public static AdMgr getSessionAdMgr(HttpServletRequest req) {
		AdSession adminAuthSession = (AdSession) getSession(req, Constants.ADMIN_AUTH_SESSION_KEY);
		return (AdMgr) adminAuthSession.getAdMgr();
	}
}
