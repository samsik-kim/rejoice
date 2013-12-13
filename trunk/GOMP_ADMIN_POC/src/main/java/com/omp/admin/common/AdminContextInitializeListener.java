/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 * redaano    2009. 4. 7.    Description
 *
 */
package com.omp.admin.common;

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
public class AdminContextInitializeListener
	extends AbstractContextInitialzeListener {
	
	@Override
	protected String getContextId() {
		return "admin";
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
			spir.setHeader("<div class='paging_area'><div class='paging'>");
			spir.setFooter("</div></div>");
			spir.setTopLink("<a href='${link}' class='arr' title='go TOP'><img src='" + contextPath + "/images/board/arrL_02.gif'/></a>");
			spir.setBottomLink("<a href='${link}' class='arr' title='go BOTTOM'><img src='" + contextPath + "/images/board/arrR_02.gif'/></a>");
//			spir.setPreviousLink("<a href='${link}' class='arr' title='go Page ${no}'><img src='" + contextPath + "/images/board/arrL_01.gif'/></a>&nbsp;");
//			spir.setNextLink("&nbsp;<a href='${link}' class='arr' title='go Page ${no}'><img src='" + contextPath + "/images/board/arrR_01.gif'/></a>");
//			spir.setDecLink("<a href='${link}' title='go Page ${no}'>-</a>");
//			spir.setIncLink("<a href='${link}' title='go Page ${no}'>+</a>");
			spir.setPreviousLink("");
			spir.setNextLink("");
			spir.setDecLink("<a href='${link}' class='arr' title='go Page ${no}'><img src='" + contextPath + "/images/board/arrL_01.gif'/></a>&nbsp;");
			spir.setIncLink("&nbsp;<a href='${link}' class='arr' title='go Page ${no}'><img src='" + contextPath + "/images/board/arrR_01.gif'/></a>");
			spir.setCurrentPageLink("&nbsp;<b>${no}</b>&nbsp;");
			spir.setOtherPageLink("<a href='${link}' title='go Page ${no}'>${no}</a>");
			spir.setSeparater("|");
			sc.setAttribute(PagenateIndexRenderTag.DEFAULT_TEMPLATE_ID, spir);
		} catch (Exception e) {
			throw new RuntimeException("Page Index Render Template fail.", e);
		}
		
	}

	@Override
	protected void destoryExtra(ServletContextEvent sce) throws Exception {
	}
}
