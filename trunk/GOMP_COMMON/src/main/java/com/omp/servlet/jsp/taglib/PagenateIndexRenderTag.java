package com.omp.servlet.jsp.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.PagenateList;

import pat.web.util.pageindex.BasicPageIndexRenderer;
import pat.web.util.pageindex.PageIndexRenderer;

/**
 * 페이지 나눔 리스트 출력시 함께 출력할 페이지 인덱스를 출력하는 태그
 * 프로퍼티 일람<br>
 * <ul>
 * <li>template : 출력할 탬플릿을 지정합니다. 생략하면 기본 형식이 사용됩니다.<br>
 * 탬플릿은 pat.web.util.pageindex.PageIndexRenderer 객체를 참조하는 EL 이어야 합니다.
 * <li>total : 전체 자료수를 EL로 지정합니다. 필수값입니다.</li>
 * <li>pageInfo : 페이지 나눔 정보를 가진 com.ubigent.core.model.PagenateInfoModel 객체를 EL로 지정합니다. 필수값입니다.</li>
 * <li>funcName : 페이지 번호를 클릭했을때 호출될 자바 스크립트 펑션 이름을 지정 합니다. 생략하면 goPage가 사용됩니다.</li>
 * </ul>
 * @author pat
 */
@SuppressWarnings({"serial", "rawtypes"})
public class PagenateIndexRenderTag
	extends BodyTagSupport {
	public static final	String	DEFAULT_TEMPLATE_ID	= "_gomp_default_pageindex_template";
	private static final PagenateInfoModel	EMPTY_PAGE	= new PagenateInfoModel();
	private PageIndexRenderer	template;
	private PagenateList		item;
	private String				funcName;
	
	public void setTemplate(PageIndexRenderer template) {
		this.template	= template;
	}
	
	public void setItem(PagenateList item) {
		this.item = item;
	}

	public void setFuncName(String funcName) {
		this.funcName	= funcName;
	}
	
	public PagenateIndexRenderTag() {
		this.release();
	}

	/**
	 * 이 태그의 기능을 위해 오버라이드 되었습니다.
	 */
	public int doEndTag()
		throws JspException {
		
		try {
			PageIndexRenderer	pir;
			Long				total;
			PagenateInfoModel	page;
			
			// 렌더러 얻기
			if (this.template == null) {
				pir	= this.getDefaultPageIndexRenderer();
			} else {
				pir	= this.template;
			}
			
			// 페이지 나눔 정보 얻기
			if (this.item == null) {
				page	= EMPTY_PAGE;
				total	= 0L;
			} else {
				page	= this.item.getPage();
				total	= this.item.getTotalCount();
			}
			
			// 출력
			pir.doWritePageIndex(this.pageContext.getOut(), total.intValue()
				, page.getNo().intValue(), page.getRows().intValue(), this.funcName);
			
			return EVAL_PAGE;
		} catch (Exception e) {
			throw new JspException("Page index Tag render fail.", e);
		} finally {
			this.release();
		}
	}
	
	/**
	 * 기본 형식의 페이지 렌더러를 반환합니다.
	 * @return
	 */
	private PageIndexRenderer getDefaultPageIndexRenderer() {
		PageIndexRenderer	rv;
		
		rv	= (PageIndexRenderer)this.pageContext.findAttribute(
			DEFAULT_TEMPLATE_ID);
		if (rv == null) {
			rv	= new SimplePageIndexRenderer();
			this.pageContext.getServletContext().setAttribute(
				DEFAULT_TEMPLATE_ID, rv);
		}
		return rv;
	}
	
	public void release() {
		this.template	= null;
		this.item		= null;
		this.funcName	= "goPage";
	}
}
