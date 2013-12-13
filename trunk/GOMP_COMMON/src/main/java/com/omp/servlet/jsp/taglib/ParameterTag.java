package com.omp.servlet.jsp.taglib;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import com.omp.commons.text.LocalizeMessage;

/**
 * 부모 태그의 프로퍼티 값을 지정하는 부속 태그<br>
 * 프로퍼티 일람<br>
 * <ul>
 * <li> level : 부모 태그의 레벨, 생략시 1<br>
 * <li> name : 설정 대상 프로퍼티명, 필수<br>
 * <li> value : 설정 값, EL 가능<br>
 * <li> 태그바디 : value의 값이 지정 되지 않았을때 value를 지정하는 효과를 냅니다.<br>
 * </ul>
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class ParameterTag
	extends BodyTagSupport {
	
	private int		level	= 1;
	private String	name;
	private Object	value;
	
	public void setParentLevel(int level) {
		this.level	= level;
	}
	
	public void setName(String name) {
		name	= name.trim();
		if (name.length() < 2) {
			throw new IllegalArgumentException(
				LocalizeMessage.getLocalizedMessage("프로퍼티 name은 반드시 2글자 이상 이어야 합니다."));
		}
		this.name	= name;
	}
	
	public void setValue(Object value) {
		this.value	= value;
	}
	
	public int doEndTag()
		throws JspException {
		try {
			String		mname;
			Tag			ptag;
			Class		ptagClass;
			Method[]	ptagMths;
	
			if (this.value == null) {
				BodyContent	bct;
				
				bct	= this.getBodyContent();
				if (bct != null) {
					this.value	= bct.getString();
				}
			}
			ptag	= this;
			for (int i=0; i<this.level; i++) {
				ptag	= ptag.getParent();
				if (ptag == null) {
					throw new JspException(
						LocalizeMessage.getLocalizedMessage("지정된 레벨의 부모 태그가 존재하지 않습니다. (level {0})", new Object[] {this.level}));
				}
			}
			ptagClass	= ptag.getClass();
			ptagMths	= ptagClass.getMethods();
			mname		= "set" + Character.toUpperCase(name.charAt(0))
				+ name.substring(1);
			for (int i=0; i<ptagMths.length; i++) {
				if (ptagMths[i].getName().equals(mname)) {
					Class[]	pts;
					
					pts	= ptagMths[i].getParameterTypes();
					if (pts.length == 1) {
						Object				pv;
						
						pv	= this.value;
						try {
							ptagMths[i].invoke(ptag, pv);
						} catch (InvocationTargetException e) {
							Throwable	t;
							
							t	= e.getTargetException();
							if (t instanceof Exception) {
								throw (Exception)t;
							} else {
								throw (Error)t;
							}
						}
						return EVAL_PAGE;
					}
				}
			}
			throw new JspException(
				LocalizeMessage.getLocalizedMessage("부모 태그({0})에 쓰기 가능한 지정한 이름의 프로퍼티가 존재하지 않습니다."
				+ " (프로퍼티명 {1})", new Object[] {ptag, this.name}));
		} catch (JspException e) {
			throw e;
		} catch (Exception e) {
			throw new JspException("Tag render fail.", e);
		} finally {
			this.release();
		}
	}
	
	public void release() {
		this.level	= 1;
		this.name	= null;
		this.value	= null;
	}

}
