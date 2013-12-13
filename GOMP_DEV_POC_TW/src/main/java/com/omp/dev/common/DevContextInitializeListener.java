/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 * redaano    2009. 4. 2.    Description
 * kbeui      2011. 2.16.
 *
 */
package com.omp.dev.common;

import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import com.omp.commons.persistence.dao.DaoConfig;
import com.omp.servlet.AbstractContextInitialzeListener;
import com.omp.servlet.jsp.taglib.PagenateIndexRenderTag;
import com.omp.servlet.jsp.taglib.SimplePageIndexRenderer;

/**
 * 
 * @author pat
 *
 */
public class DevContextInitializeListener 
	extends AbstractContextInitialzeListener {

	@Override
	protected String getContextId() {
		return "dev";
	}

	@Override
	protected void initExtra(ServletContextEvent sce) throws Exception {
		ServletContext	sc	= sce.getServletContext();
		String contextPath	= sc.getContextPath();
		
		// Constants 설정
		System.out.println("### Constants Map regist to ApplicationContext.");
		try {
			this.setConstants2ApplicationContext(sce, "CONST", new Constants());
		} catch (Exception e) {
			throw new RuntimeException("Constants Map regist fail.", e);
		}
		
		// Page Index Render Template 설정
		System.out.println("### Page Index Render Template Initializing.");
		try {
			SimplePageIndexRenderer	spir;
			
			spir	= new SimplePageIndexRenderer();
			spir.setHeader("<div class='paging'>");
			spir.setFooter("</div>");
			spir.setTopLink("&nbsp;<a href='${link}' class='pre_end' title='go TOP'><img src='" + contextPath + "/images/common/btn_ppre.gif'/></a>");
			spir.setBottomLink("&nbsp;<a href='${link}' class='next_end' title='go BOTTOM'><img src='" + contextPath + "/images/common/btn_nnext.gif'/></a>");
			spir.setPreviousLink("&nbsp;<a href='${link}' class='pre' title='go Page ${no}'><img src='" + contextPath + "/images/common/btn_pre.gif'/></a>&nbsp;");
			spir.setNextLink("&nbsp;<a href='${link}' class='next' title='go Page ${no}'><img src='" + contextPath + "/images/common/btn_next.gif'/></a>");
			spir.setDecLink("<a href='${link}' title='go Page ${no}'>-</a>");
			spir.setIncLink("<a href='${link}' title='go Page ${no}'>+</a>");
			spir.setCurrentPageLink("&nbsp;<span class='active'>${no}</span>&nbsp;");
			spir.setOtherPageLink("&nbsp;<a href='${link}' title='go Page ${no}'>${no}</a>");
			spir.setSeparater("&nbsp;<span>|</span>");
			sc.setAttribute(PagenateIndexRenderTag.DEFAULT_TEMPLATE_ID, spir);
		} catch (Exception e) {
			throw new RuntimeException("Page Index Render Template fail.", e);
		}
		
	}

	@Override
	protected void destoryExtra(ServletContextEvent sce) throws Exception {
	}
}