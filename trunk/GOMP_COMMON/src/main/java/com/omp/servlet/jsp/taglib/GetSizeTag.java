package com.omp.servlet.jsp.taglib;

import java.lang.reflect.Array;
import java.util.Collection;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.omp.commons.text.LocalizeMessage;

/**
 * List나 배열의 크기를 얻어 속성으로 설정 하는 태그<br>
 * 프로퍼티 일람<br>
 * <ul>
 * <li>item : 크기를 얻을 대상. EL 지정 가능</li>
 * <li>var : 얻어진 크기를 설정할 속성명. 생략 불가. Integer객체로 설정됨</li>
 * <li>scope : 설정 범위. application, session, page, request 중 하나. 생략시 request</li>
 * </ul>
 * @author pat
 */
@SuppressWarnings("serial")
public class GetSizeTag
	extends BodyTagSupport {
	private Object	item;
	private String	var;
	private String	scope;
	
	public void setItem(Object item) {
		this.item	= item;
	}
	
	public void setVar(String var) {
		this.var	= var;
	}
	
	public void setScope(String scope) {
		this.scope	= scope;
	}

	@SuppressWarnings("rawtypes")
	public int doEndTag()
		throws JspException {
		
		try {
			Object				obj;
			Integer				size;
			
			obj	= this.item;
			if (obj	== null) {
				size	= null;
			} else {
				if (obj instanceof Collection) {
					size	= Integer.valueOf(((Collection)obj).size());
				} else if (obj.getClass().isArray()) {
					size	= Integer.valueOf(Array.getLength(obj));
				} else {
					size	= Integer.valueOf(1);
				}
			}
			if (size != null) {
				if ("application".equals(this.scope)) {
					this.pageContext.getServletContext().setAttribute(this.var, size);
				} else if ("session".equals(this.scope)) {
					this.pageContext.getSession().setAttribute(this.var, size);
				} else if ("page".equals(this.scope)) {
					this.pageContext.setAttribute(this.var, size);
				} else if ("request".equals(this.scope) || this.scope == null) {
					this.pageContext.getRequest().setAttribute(this.var, size);
				} else {
					throw new JspException(LocalizeMessage.getLocalizedMessage("알수 없는 scope '{0}'가 지정 되었습니다.", new Object[] {this.scope}));
				}
			} else {
				this.pageContext.getRequest().removeAttribute(this.var);
			}
		} catch (JspException e) {
			throw e;
		} catch (Exception e) {
			throw new JspException("Tag render fail.", e);
		} finally {
			this.release();
		}
		
		return EVAL_PAGE;
	}
	
	public void release() {
		this.item	= null;
		this.var	= null;
		this.scope	= null;
	}
}
