package com.omp.servlet.jsp.taglib;

import java.io.File;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.PropertyFilter;

/**
 * JSON 식으로 출력하는 태그<br>
 * 프로퍼티 일람<br>
 * <ul>
 * <li>value : 출력 대상 값을 지정합니다. EL식 사용 가능</li>
 * <li>태그바디 : value의 값이 지정 되지 않았을때 value를 지정하는 효과를 냅니다.</li>
 * </ul>
 * @author pat
 */
@SuppressWarnings("serial")
public class JSONOutTag
	extends BodyTagSupport {
	
	private static final JsonConfig	DEFAULT_JSON_CONF	= new JsonConfig(); 
	private Object					value;
	
	static {
		DEFAULT_JSON_CONF.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		DEFAULT_JSON_CONF.setJsonPropertyFilter(new ExtendPropertyFilter());
	}
	
	/**
	 * 기본 JSON 설정에서 사용될 프로퍼티 필터
	 * @author pat
	 *
	 */
	private static class ExtendPropertyFilter
		implements PropertyFilter {
		
		private ExtendPropertyFilter() {
		}
		
		public boolean apply(Object source, String name, Object value) {
			if (source instanceof File) {
				return !("canonicalPath".equals(name) || "absolutePath".equals(name)
					|| "path".equals(name));
			}
			return false;
		}
	}

	/**
	 * 출력대상을 설정합니다.
	 * @param value
	 */
	public void setValue(Object value) {
		this.value	= value;
	}
	
	/**
	 * 태그의 기능을 구현하기 위해 오버라이드 되었습니다.
	 */
	public int doEndTag()
		throws JspException {
		try {
			Object		obj;
			String		vstr;
			JspWriter	jout;
			
			if (this.value == null) {
				BodyContent	bct;
				
				bct	= this.getBodyContent();
				if (bct != null) {
					this.value	= bct.getString();
				}
			}
			if (this.value == null) {
				obj	= null;
			}  else {
				obj	= this.value;
			}
			
			jout	= this.pageContext.getOut();
			if (obj == null) {
				jout.print("null");
			} else {
				if (obj.getClass().isArray()
					|| obj instanceof List) {
					jout.println(this.getEncodedText(
						JSONArray.fromObject(obj, DEFAULT_JSON_CONF).toString()));
				} else {
					jout.println(this.getEncodedText(
						JSONObject.fromObject(obj, DEFAULT_JSON_CONF).toString()));
					
				}
			}
		} catch (Exception e) {
			throw new JspException("JSON out Tag render fail.", e);
		} finally {
			this.release();
		}
		return EVAL_PAGE;
	}
	
	/**
	 * JSON 결과를 인코딩 할 필요가 있을때 오버라이드 하십시오.
	 * @param src
	 * @return
	 */
	protected String getEncodedText(String src) {
		return src;
	}
	
    /**
     * 태그의 기능 구현을 위해 오버라이딩 되었습니다.
     */
    public void release() {
    	this.value	= null;
    }
}
