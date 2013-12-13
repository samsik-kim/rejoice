package com.omp.servlet.jsp.taglib;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * jsp 페이지에서 사용할 리소스 번들을 로드하는 태그<br>
 * 프로퍼티 일람<br>
 * <ul>
 * <li>basename : 로드할 리소스 번들의 베이스 네임을 지정합니다.<br>
 * <li>locale : EL가능. 로드할 리소스 번들의 로케일을 지정합니다.
 * 생략하면 시스템 기본 로케일을 사용 합니다.<br>
 * <li>var : 바인딩 이름을 지정합니다. 생략하면 상수 SetBundleTag.DEFAULT_VAR_NAME이 사용됩니다.<br>
 * </ul>
 * 태그 바디의 내용은 출력되지 않습니다.
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class LoadBundleTag
	extends BodyTagSupport {
	
	private String	basename;
	private Locale	locale;
	private String	var;
	
	/**
	 * 로드할 리소스 번들의 베이스 네임을 지정합니다.
	 * @param basename
	 */
	public void setBasename(String basename) {
		this.basename	= basename;
	}
	
	
	public void setLocale(Locale locale) {
		this.locale	= locale;
	}
	
	/**
	 * 바인딩할 이름을 지정합니다.
	 * @param var
	 */
	public void setVar(String var) {
		this.var	= var;
	}

	/**
	 * 태그 기능 구현을 위해 오버라이딩 되었습니다.
	 */
	public int doEndTag() 
		throws JspException {
		
		try {
			Locale				loc;
			ResourceBundle		rb;
			
			if (this.basename == null) {
				throw new JspException("basename must not null.");
			}
			if (this.locale == null) {
				loc	= Locale.getDefault();
			} else {
				loc	= this.locale;
			}
				
			rb	= ResourceBundle.getBundle(this.basename, loc);
			
			if (this.var != null) {
				this.pageContext.getRequest().setAttribute(this.var, rb);
			} else {
				this.pageContext.getRequest().setAttribute(SetBundleTag.DEFAULT_VAR_NAME, rb);
			}
			return EVAL_PAGE;
		} catch (JspException e) {
			throw e;
		} catch (Exception e) {
			throw new JspException("LoadBundle Tag proc fail.", e);
		} finally {
			this.release();
		}
	}
	
	public void release() {
		this.basename	= null;
		this.locale		= null;
		this.var		= null;
	}
}
