package com.omp.servlet.jsp.taglib;

import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.omp.commons.text.LocalizeMessage;

/**
 * jsp 페이지에서 사용할 리소스 번들을 지정하는 태그<br>
 * 프로퍼티 일람<br>
 * <ul>
 * <li>value : EL표현 가능. 사용할 리소스 번들을 EL 식이나 어트리뷰트 명으로 지정합니다.<br>
 * <li>var : 바인딩 이름을 지정합니다. 생략하면 상수 DEFAULT_VAR_NAME이 사용됩니다.<br>
 * </ul>
 * 태그 바디의 내용은 출력되지 않습니다.
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class SetBundleTag
	extends BodyTagSupport {
	
	/**
	 * 지정된 리소스 번들이 request에 바인딩 될때 사용되는 기본 속성키
	 */
	public static final String	DEFAULT_VAR_NAME	= "_imath_jsp_resource_bundle";
	
	private Object	value;
	private String	var;
	
	/**
	 * 사용 대상 리소스 번들<br>
	 * EL식 혹은 어트리뷰트 명을 지정할 수 있습니다.
	 * @param value
	 */
	public void setValue(Object value) {
		this.value	= value;
	}
	
	public void setVar(String var) {
		this.var	= var;
	}

	/**
	 * 태그 기능 구현을 위해 오버라이딩 되었습니다.
	 */
	public int doEndTag() 
		throws JspException {
		
		try {
			Object				obj;
			ResourceBundle		rb;
			
			if (this.value == null) {
				throw new JspException(LocalizeMessage.getLocalizedMessage("프로퍼티 value는 null일 수 없습니다."));
			}
			obj	= this.value;
			if (obj instanceof String) {
				try {
					rb	= (ResourceBundle)this.pageContext.findAttribute((String)obj);
				} catch (ClassCastException e) {
					throw new JspException(LocalizeMessage.getLocalizedMessage("프로퍼티 value는 String 이거나  ResourceBundle이야 합니다."), e);
				}
				if (rb == null) {
					throw new JspException(LocalizeMessage.getLocalizedMessage("프로퍼티 value에 지정된 속성명 '{0}' 에 해당하는 ResourceBundle을 찾을 수 없습니다."
						, new Object[] {obj}));
				}
			} else if (obj instanceof ResourceBundle) {
				rb	= (ResourceBundle)obj;
			} else {
				throw new JspException(LocalizeMessage.getLocalizedMessage("프로퍼티 value는 String 이거나  ResourceBundle이야 합니다."));
			}
			
			if (this.var != null) {
				this.pageContext.getRequest().setAttribute(this.var, rb);
			} else {
				this.pageContext.getRequest().setAttribute(DEFAULT_VAR_NAME, rb);
			}
			return EVAL_PAGE;
		} catch (JspException e) {
			throw e;
		} catch (Exception e) {
			throw new JspException("Ubigent Tag proc fail.", e);
		} finally {
			this.release();
		}
	}
	
	public void release() {
		this.value	= null;
		this.var	= null;
	}
}
