package com.omp.servlet.jsp.taglib;

import java.util.StringTokenizer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.omp.commons.text.LocalizeMessage;

import pat.neocore.util.StringPattern;
import pat.web.util.WebUtil;

/**
 * 지정된 객체의 모든 프로퍼티와 값을 출력하는 태그 입니다.<br>
 * 프로퍼티 일람<br>
 * <ul>
 * <li> value : EL 가능. 출력 대상 객체를 지정합니다.</li>
 * <li> outType : 출력형식을 지정합니다. qs는 URL뒤에 붙일 QueryString형식으로,
 * hidden은 &lt;form&gt; 안에 표현한 hidden형식의 &lt;input&gt;로 출력합니다.
 * 생략시 qs로 지정됩니다.</li>
 * <li> prefix : 프로퍼티 출력시 프로퍼티명에 붙일 전치사를 지정합니다.</li>
 * <li> charSet : 출력 타입 qs에서만 사용되는 값으로, 프로퍼티의 값을 URLEncode 할때
 * 사용할 케릭터 셋 이름을 지정합니다. 생략하면 JSP페이지의 ContentType에 지정된
 * 케릭터 셋을 따릅니다.</li>
 * <li> skipReadonly : yes 혹은 true를 지정하면 읽기만 가능한 속성은 생략합니다.
 * 기본값은 true 입니다.</li> 
 * <li> excludePattern : 출력시 제외할 속성명의 패턴을 지정합니다. 여러 패턴을 지정할 때에는
 * 구분자 세미콜론을 이용하여 붙여 지정합니다.</li> 
 * </ul>
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class PrintBeanTag
	extends BodyTagSupport{
	
	/**
	 * 출력할 객체
	 */
	private Object value;
	/**
	 * 출력 형식
	 */
	protected String outType;
	/**
	 * 출력시 프로퍼티 명 앞에 붙일 전치사
	 */
	private String prefix;
	/**
	 * qs 출력시 URLEncode할때 사용할 케릭터 셋
	 */
	private String charSet;
	/**
	 * 읽기전용 속성 건너뛰기 여부 
	 */
	private String skipReadonly;
	/**
	 * 제외속성 패턴들
	 */
	private StringPattern[] excludePatterns;
	
	
	public PrintBeanTag() {
		this.release();
	}
	
	/**
	 * 제외속성 패턴 식을 지정합니다.
	 * 스트링 패턴 식이며, 여러 패턴을 지정 할 때에는 구분자 세미콜론(;)을 사용합니다.
	 * @param ptnexp
	 */
	public void setExcludePattern(String ptnexp) {
		StringTokenizer	stk;
		
		stk	= new StringTokenizer(ptnexp, ";");
		this.excludePatterns	= new StringPattern[stk.countTokens()];
		for (int i=0; i<this.excludePatterns.length; i++) {
			this.excludePatterns[i]	= new StringPattern(stk.nextToken());
		}
	}
	
	/**
	 * 출력할 객체를 지정합니다.
	 * @param value
	 */
	public void setValue(Object value) {
		this.value	= value;
	}
	
	/**
	 * 출력 형식을 지정합니다.
	 * @param ot "qs" - URL 쿼리 스트링, "hidden" &lt;input type="hidden"&gt;형식
	 */
	public void setOutType(String ot) {
		this.outType	= ot;
	}
	
	/**
	 * 출력시 프로퍼티 명 앞에 붙일 전치사를 지정합니다.
	 * @param prefix
	 */
	public void setPrefix(String prefix) {
		this.prefix		= prefix;
	}
	
	/**
	 * qs 타입 출력시 URLEncode할때 사용할 케릭터 셋을 지정합니다.
	 * @param cs
	 */
	public void setCharSet(String cs) {
		this.charSet	= cs;
	}
	
	/**
	 * 읽기만 가능한 속성은 건너뛸것인지 지정합니다.
	 * @param sr yes/no 혹은 treu/false
	 */
	public void setSkipReadonly(String sr) {
		this.skipReadonly	= sr;
	}
	
	/**
	 * 이 태그의 기능을 위해 오버라이드 되었습니다.
	 */
	public int doEndTag()
		throws JspException {
		try {
			Object				vobj;
			
			if (this.value == null) {
				throw new JspException(LocalizeMessage.getLocalizedMessage("프로퍼티 value는 반드시 지정 되어야 합니다."));
			}
			if (this.prefix == null) {
				this.prefix	= "";
			}
			if (this.charSet == null) {
				this.charSet	= this.pageContext.getResponse().getCharacterEncoding();
			}
			if (this.outType == null) {
				this.outType	= "qs";
			}
			
			vobj	= this.value;
			this.pageContext.getOut().print(this.getOutString(vobj));
		} catch (JspException e) {
			throw e;
		} catch (Exception e) {
			throw new JspException("Tag render fail.", e);
		} finally {
			this.release();
		}
		return EVAL_PAGE;
	}
	
	/**
	 * 이 태그의 기능을 위해 오버라이드 되었습니다.
	 */
	public void release() {
		this.value				= null;
		this.outType			= null;
		this.prefix				= null;
		this.charSet			= null;
		this.excludePatterns	= null;
		this.skipReadonly		= "yes";
	}
	
	/**
	 * 출력 형식에 따른 출력 문자열을 반환합니다.
	 * @param bean 출력 대상 객체
	 * @return
	 * @throws Exception
	 */
	protected String getOutString(Object bean)
		throws Exception {
		String	rv;
		boolean	isSkipReadonly;
		
		
		isSkipReadonly	= "yes".equalsIgnoreCase(this.skipReadonly)
			|| "true".equalsIgnoreCase(this.skipReadonly); 
		if (bean == null) {
			rv	= "";
		} else if ("qs".equals(this.outType)) {
			rv	= WebUtil.makeQueryString(this.prefix, bean, this.charSet
				, isSkipReadonly, this.excludePatterns);
		} else if ("hidden".equals(this.outType)) {
			rv	= WebUtil.makeHiddenInputTags(this.prefix, bean, isSkipReadonly
				, this.excludePatterns);
		} else {
			throw new IllegalArgumentException(
				LocalizeMessage.getLocalizedMessage("출력 타입 {0}은 지원하지 안습니다.", new Object[] {this.outType}));
		}
		return rv;
	}
}