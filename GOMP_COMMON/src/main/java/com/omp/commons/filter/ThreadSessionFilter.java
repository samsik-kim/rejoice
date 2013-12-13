package com.omp.commons.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.omp.commons.util.ThreadSession;

/**
 * 하나의 HTTP 요청이 종료 되는 시점에 쓰레스 세션을 정리하는 필터
 * @author pat
 *
 */
public class ThreadSessionFilter
	implements Filter {

	@Override
	public void destroy() {
		ThreadSession.claearAll();
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain fc) throws IOException, ServletException {
		ThreadSession	tses;
		
		tses	= ThreadSession.getSession();
		tses.setActionStep("BeforeInit");
		tses.setServiceStep(null);
		req.setAttribute("ThreadSession", ThreadSession.getSession());
		try {
			fc.doFilter(req, res);
		} finally {
			ThreadSession.doExpire();
		}
	}

	@Override
	public void init(FilterConfig flcfg) throws ServletException {
	}
}
