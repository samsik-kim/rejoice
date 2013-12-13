package com.omp.servlet.jsp.taglib;

import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import pat.neocore.util.MiscEncoder;

import com.omp.commons.commcode.model.CommCode;
import com.omp.commons.text.LocalizeMessage;

/**
 * 튿정 코드 그룹의 코드 목록을 라이오 버튼 태그로 출력하는 커스텀 태그<br>
 * 프로퍼티 일람<br>
 * <ul>
 * <li> name : 출력 태그의 폼 요소명을 지정합니다.<li>
 * <li> value : EL가능. 선택 되어 있어야할 값을 지정합니다.<li>
 * <li> extra : 태그 출력시 함께 출력할 추가 속성을 지정합니다.</li>
 * <li> direction : 출력 방향을 지정합니다. v이면 세로로 생략하면
 * 가로로 출력합니다.</li>
 * </ul>
 * 태그 바디의 내용은 출력하지 않습니다.
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class CodeListRadioButtonRenderTag
	extends AbstractCodeListRenderTag {
	
	/**
	 * 출력 태그의 폼 요소명
	 */
	private String	name;
	/**
	 * 선택 되어 있어야 할 값.
	 */
	private String	value;
	/**
	 * 태그 출력시 함께 나타나야할 추가속성
	 */
	private String	extra;
	/**
	 * 출력 간격 사이 표현
	 */
	private String	divide;
	/**
	 * 체크박스와 코드 사이 표현
	 */
	private String	split;
	
	/**
	 * 출력 태그의 폼 요소명을 지정합니다.
	 * @param n
	 */
	public void setName(String n) {
		this.name	= n;
	}
	
	/**
	 * 선택 되어 있어야 할 값을 지정합니다.
	 * @param v
	 */
	public void setValue(String v) {
		this.value	= v;
	}
	
	/**
	 * 태그와 함께 출력할 추가 요소를 지정합니다.
	 * @param extra
	 */
	public void setExtra(String extra) {
		this.extra	= extra;
	}
	
	/**
	 * 코드와 코드 사이에 들어갈 구분 문자열을 지정합니다.
	 * 코드가 두개 이상일때 두 코드의 사이에만 출력됩니다.
	 * @param divide
	 */
	public void setDivide(String divide) {
		this.divide	= divide;
	}
	
	/**
	 * 체크박스와 코드 사이에 들어가 구분 문자열을 지정합니다.
	 * @param split
	 */
	public void setSplit(String split) {
		this.split	= split;
	}
	
	/**
	 * AbstractCodeListResolveTag 구현 메소드
	 */
	protected void doWork(List<CommCode> codeList)
		throws Exception {
		String				oldv;
		Iterator<CommCode>	its;
		JspWriter			jout;
		int					sp;
		boolean				isFirst;
		
		
		if (this.name == null) {
			throw new JspException(LocalizeMessage.getLocalizedMessage("프로퍼티 name은 null일 수 없습니다."));
		}
		if (this.split == null) {
			this.split	= "";
		}
		if (this.divide == null) {
			this.divide	= "";
		}
		oldv		= null;
		if (this.value != null) {
			sp			= this.value.lastIndexOf(":");
			if (sp != -1) {
				// 예전 제품의 코드 처리 방법을 위한 처리
				oldv	= this.value.substring(sp+1);
			}
			
		}
		its		= codeList.iterator();
		jout	= this.pageContext.getOut();
		isFirst	= true;
		while (its.hasNext()) {
			CommCode	cd;
			String		codeId;
			
			cd		= its.next();
			codeId	= this.getCode(cd);
			if (isFirst) {
				isFirst	= false;
			} else {
				jout.print(this.divide);
			}
			jout.print("<input type=\"radio\" name=\"");
			jout.print(MiscEncoder.encode4TagAttribute(this.name));
			jout.print("\" value=\"");
			jout.print(MiscEncoder.encode4TagAttribute(codeId));
			jout.print("\" ");
			if ((this.value != null && this.value.equals(codeId))
				|| (oldv != null && oldv.equals(codeId))) {
				jout.print("checked ");
			}
			if (this.extra != null) {
				jout.print(this.extra);
			}
			jout.print(">");
			jout.print(this.split);
			jout.print(MiscEncoder.encode4Html(cd.getCdNm()));
		}
	}
	
	/**
	 * 이 태그의 기능을 위해 오버라이딩 되었습니다.
	 */
	public void release() {
		super.release();
		this.name	= null;
		this.value	= null;
		this.extra	= null;
		this.divide	= null;
		this.split	= null;
	}
}
