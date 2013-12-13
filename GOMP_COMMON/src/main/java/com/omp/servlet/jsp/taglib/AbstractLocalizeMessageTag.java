package com.omp.servlet.jsp.taglib;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.omp.commons.text.LocalizeMessage;

/**
 * 지역화 메세지 출력 태그의 공통 부분을 구현한 추상 클래스<br>
 * 프로퍼티 일람<br>
 * <ul>
 * <li>value : EL가능. 출력할 지역화 메세지 ID</li>
 * <li>id : 사용할 리소스 번들 아이디(바인드이름),
 * 생략시 SetBundleTag.DEFAULT_VAR_NAME가 사용됩니다.</li>
 * </ul>
 * 메세지 인수는 부속 태그 LocalizeMessageArgumentTage를 통해 지정
 * 할 수 있습니다.<br>
 * 태그 바디의 내용은 출력되지 않습니다.
 * @author pat
 * @see LocalizeMessageArgumentTage
 */
@SuppressWarnings("serial")
public abstract class AbstractLocalizeMessageTag	
	extends BodyTagSupport {
	
	/**
	 * 출력할 메세지 아이디. EL 가능
	 */
	protected Object		value;
	/**
	 * 사용할 리소스 번들의 바인드명
	 */
	protected String		id;
	/**
	 * 메세지 인수
	 */
	protected List<Object>	msgArgs;
	
	
	/**
	 * 생성자
	 */
	protected AbstractLocalizeMessageTag() {
		this.msgArgs	= new ArrayList<Object>();
	}
		
	/**
	 * 사용할 리소스 번들을 바인드명으로 설정합니다.
	 */
	public void setId(String id) {
		this.id	= id;
	}
	
	/**
	 * 메세지 아이디를 설정합니다. EL가능
	 * @param value
	 */
	public void setValue(Object value) {
		this.value	= value;
	}
	
	/**
	 * 메세지 인수를 추가합니다.
	 * 부속 태그인 LocalizeMessageArgumentTage에서 허출됩니다.
	 * @param msgArg
	 * @see LocalizeMessageArgumentTag
	 */
	public void addMessageArgument(Object msgArg) {
		this.msgArgs.add(msgArg);
	}
	
	/**
	 * 이 태그의 기능을 위해 오버라이드 되었습니다.
	 */
	public int doEndTag()
		throws JspException {
		try {
			String			vstr;
			JspWriter		jout;
			ResourceBundle	rb;
			
			rb	= (ResourceBundle)this.pageContext.findAttribute(
				(this.id == null ? SetBundleTag.DEFAULT_VAR_NAME : this.id));
			// ThreadSession에 설정된 기본값을 가지도록 오류 처리 안함
//			if (rb == null) {
//				throw new JspException(
//					"ResourceBundle required.");
//			}
			
			if (this.value == null) {
				vstr	= "";
			}  else {
				Object				vobj;
				LocalizeMessage		lm;
				
				vobj	= this.value;
				if (vobj == null) {
					vstr	= "";
				} else if (vobj instanceof LocalizeMessage) {
					vstr	= ((LocalizeMessage)vobj).getLocalizedMessage(rb);
				} else {
					lm		= new LocalizeMessage(vobj.toString(), this.msgArgs.toArray());
					vstr	= lm.getLocalizedMessage(rb);
				}
			}

			jout	= this.pageContext.getOut();
			jout.print(this.getEncodeString(vstr));
		} catch (JspException e) {
			throw e;
		} catch (Exception e) {
			throw new JspException("LocalizeMessage Tag render fail.", e);
		} finally {
			this.release();
		}
		return EVAL_PAGE;
	}

	/**
	 * 이태그의 기능을 위해 오버라이드 되었습니다.
	 */
    public void release() {
    	this.id		= null;
    	this.value	= null;
    	this.msgArgs.clear();
    }
	
	
    /**
     * 구성된 메세지 택스트를 사용처에 맞게 인코딩 하는
     * 코드를 구현하십시오.
     * @param src 출력할 메세지 택스트
     * @return 인코딩될 메세지 택스트
     * @throws Exception
     */
	protected abstract String getEncodeString(String src)
		throws Exception;

}