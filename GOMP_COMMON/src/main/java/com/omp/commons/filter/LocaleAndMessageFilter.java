package com.omp.commons.filter;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

import com.omp.commons.text.LocalizeMessage;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.ThreadSession;

/**
 * 서비스 로케일을 확인하고 그 정보를 설정하며, 지역화 메세지를 설정하는 필터
 * @author pat
 *
 */
public class LocaleAndMessageFilter
	implements Filter {
	
	private static final Pattern		PTN_LOCCD	= Pattern.compile("[a-zA-Z\\_\\-]+");
	
	private ServletContext				sctx;
	private Map<String, Locale> 		locMap	= new Hashtable<String, Locale>();
	private Locale						forceLoc;
	private GLogger log = new GLogger(this.getClass());

	@Override
	public void destroy() {
		this.sctx	= null;
		this.locMap.clear();
		this.locMap	= null;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain fc) throws IOException, ServletException {
		HttpServletRequest	hreq;
		HttpServletResponse	hres;
		Locale				loc;
		ThreadSession		tses;
		

		hreq	= (HttpServletRequest)req;
		hres	= (HttpServletResponse)res;
		loc		= this.forceLoc;
		
		// 강제 설정이 없으면 세션 설정 확인
		if (loc == null) {
			HttpSession	hses;
			
			hses	= hreq.getSession(false);
			if (hses != null) {
				loc = (Locale)hses.getAttribute("SERVICE_LOCALE");
			}
		}
		
		// 세션 설정 없으면 브라우저 언어 확인
		if (loc == null) {
			loc = this.getLocaleFromStr(hreq.getHeader("accept-language"));
		}
		
		
		if (this.log.isDebugEnabled()) {
			this.log.debug(new LocalizeMessage("locale set to {0} for {1}", new Object[] {loc, hreq.getRequestURL()}));
		}
		
		tses	= ThreadSession.getSession();
		
		// 세션 쓰레드에 로케일 설정
		tses.setServiceLocale(loc);
		
		// 기본 케릭터셋이 UTF 적용
		res.setCharacterEncoding("UTF-8");
		
		// 세션 쿠키 손실로 인한 c:url 의 url 구성시 ;jsessionid=xxx 붙임 방지
		if (hreq.getSession().isNew()) {
			HttpServletResponse	wres 	= new HttpServletResponseWrapper(hres) {
	
				@Override
				public String encodeURL(String url) {
					return url;
				}
	
				@Override
				public String encodeUrl(String url) {
					return url;
				}
				
			};
			fc.doFilter(req, wres);
		} else {
			fc.doFilter(req, res);
		}
	}

	@Override
	public void init(FilterConfig flcfg) throws ServletException {
		this.sctx		= flcfg.getServletContext();
		
		Properties conf	= (Properties)this.sctx.getAttribute("CONF");
		if (conf != null) {
			String	forceLocStr;
			
			forceLocStr	= conf.getProperty("omp.common.locale.force");
			// 로케일 강제 설정이 있는지 확인
			if (forceLocStr != null) {
				this.forceLoc	= this.getLocaleFromStr(forceLocStr);
			}
		}
	}
	
	/**
	 * 지역코드 문자열로 부터 Locale 객체 획득
	 * @param locStr null이 지정되면 시스템 기본 로케일이 반환
	 * @return
	 */
	private Locale getLocaleFromStr(String locStr) {
		Locale	rv;
		
		if (locStr == null) {
			rv	= Locale.getDefault();
		} else {
			rv	= locMap.get(locStr);
			if (rv == null) {
				Matcher	mch;
				
				mch	= PTN_LOCCD.matcher(locStr);
				if (mch.find()) {
					StringTokenizer	stk;
					
					stk	= new StringTokenizer(mch.group(0), "-_");
					switch(stk.countTokens()) {
						case 1:
							rv	= new Locale(stk.nextToken());
							break;
						case 2:	
							rv	= new Locale(stk.nextToken(), stk.nextToken());
							break;
						default:
						case 3:
							rv	= new Locale(stk.nextToken(), stk.nextToken(), stk.nextToken());
							break;
					}
				}
				locMap.put(locStr, rv);
			}
		}
		return rv;
	}
}
