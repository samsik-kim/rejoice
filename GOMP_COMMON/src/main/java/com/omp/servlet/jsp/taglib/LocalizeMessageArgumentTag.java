package com.omp.servlet.jsp.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

/**
 * AbstractLocalizeMessageTag를 구현한 지역화 메세지 출력태그에
 * 사용될 메세지 인수를 지정하는 부속 태그 입니다.<br>
 * 프로퍼티 일람<br>
 * <ul>
 * <li>value : EL 사용 가능. 메세지의 인수를 지정합니다.</li>
 * <li>태그바디 : value가 지정 되지 않았을때 value의 값으로 이용 됩니다.</li>
 * </ul>
 * 태그 바디의 내용은 출력되지 않습니다.
 * @author pat
 * @see AbstractLocalizeMessageTag
 */
@SuppressWarnings("serial")
public class LocalizeMessageArgumentTag
	extends BodyTagSupport {
	
	/**
	 * 메세지 인수
	 */
	private Object	value;
	
	/**
	 * 메세지 인수를 지정합니다.
	 * @param value
	 */
	public void setValue(Object value) {
		this.value	= value;
	}

	/**
	 * 태그의 기능을 위해 오버라이드 되었습니다.
	 */
	public int doEndTag()
		throws JspException {
		try {
			Tag		ptag;
			Object	vobj;
			
			if (this.value == null) {
				BodyContent	bct;
				
				bct	= this.getBodyContent();
				if (bct != null) {
					this.value	= bct.getString();
				}
			}
			if (this.value == null) {
				vobj	= null;
			} else {
				vobj	= this.value;
			}
			ptag	= this;
			while (ptag != null
				&& !(ptag instanceof AbstractLocalizeMessageTag)) {
				ptag	= ptag.getParent();
			}
			if (ptag == null) {
				throw new JspException("this tag must in LocalizeMessage tags.");
			}
			((AbstractLocalizeMessageTag)ptag).addMessageArgument(vobj);
			this.release();
		} catch (JspException e) {
			throw e;
		} catch (Exception e) {
			throw new JspException("LocalizeMessage argument Tag render fail.", e);
		}
		return EVAL_PAGE;
	}

	/**
	 * 태그의 기능을 위해 오버라이드 되었습니다.
	 */
    public void release() {
    	this.value	= null;
    }
}