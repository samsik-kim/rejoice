package com.omp.servlet.jsp.taglib;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import pat.neocore.util.MiscEncoder;

import com.omp.commons.commcode.model.CommCode;
import com.omp.commons.text.LocalizeMessage;

/**
 * 특정 코드 그룹의 내용을 체크박스로 나열시키는 태그<br>
 * 프로퍼티 일람<br>
 * <ul>
 * <li> name : 체크박스에 지정할 for element name을 지정합니다.</li>
 * <li> value : EL가능. 체크박스에 체크되어 있얼야 할 값을 지정합니다.
 * 지정 가능한 타입은 객체의 배열, 혹은 인터페이스 java.util.Iterable을
 * 구현하고 있는객체, 또는 단일값으로 사용될 객체 입니다.
 * 배열 혹은 인터페이스 java.util.Iterable을 구현하고 있는 객체를 지정할 경우
 * 해당 객체가 가지고 있는 모든 값이 체크 됩니다.</li>
 * <li> extra : 체크박스 태그 출력시 추가로 출력할 태그 속성을 지정합니다.</li>
 * <li> direction : 체크박스 표시 방향을 지정합니다. v 를 지정하면 세로로 출력됩니다.
 * 생략시 가로로 출력됩니다.</li>
 * </ul>
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class CodeListCheckBoxRenderTag
	extends AbstractCodeListRenderTag {
	
	/**
	 * 체크박스 출력시 지정할 폼 요소 이름
	 */
	private String	name;
	/**
	 * 체크 되어 있어야 할 값
	 */
	private Object	value;
	/**
	 * 태그 추가 속성 문자열
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
	 * 체크박스 출력시 지정할 폼 요소 이름을 지정합니다.
	 * @param n
	 */
	public void setName(String n) {
		this.name	= n;
	}
	
	/**
	 * 체크 되어 있어야 할 값을 지정합니다.
	 * @param v
	 */
	public void setValue(Object v) {
		this.value	= v;
	}

	/**
	 * 체크박스 태그의 추가 속성 부분을 지정합니다.
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
	 * AbstractCodeListResolveTag의 구현 메소드
	 */
	protected void doWork(List<CommCode> codeList)
		throws Exception {
		Iterator<CommCode>	its;
		JspWriter			jout;
		Object				vobj;
		Set<String>			vset;
		String				codeId;
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
		vset		= new HashSet<String>();
		if (this.value != null) {
			vobj	= this.value;
			if (vobj != null) {
				if (vobj instanceof Iterable<?>) {
					Iterator<?>	it2;
					
					it2	= ((Iterable<?>)vobj).iterator();
					while (it2.hasNext()) {
						Object	ao;
						
						ao	= it2.next();
						if (ao != null) {
							codeId	= ao.toString();
							vset.add(codeId);
						}
					}
				} else if (vobj.getClass().isArray()) {
					int	len;
					
					len	= Array.getLength(vobj);
					for (int i=0; i<len; i++) {
						Object	ao;
						
						ao	= Array.get(vobj, i);
						if (ao != null) {
							codeId	= ao.toString();
							vset.add(codeId);
						}
					}
				} else {
					codeId	= vobj.toString(); 
					vset.add(codeId);
				}
			}
		}
		its		= codeList.iterator();
		jout	= this.pageContext.getOut();
		isFirst	= true;
		while (its.hasNext()) {
			CommCode	cd;
			
			cd		= its.next();
			codeId	= this.getCode(cd);
			if (isFirst) {
				isFirst	= false;
			} else {
				jout.print(this.divide);
			}
			jout.print("<input type=\"checkBox\" name=\"");
			jout.print(MiscEncoder.encode4TagAttribute(this.name));
			jout.print("\" value=\"");
			jout.print(MiscEncoder.encode4TagAttribute(codeId));
			jout.print("\" ");
			if (this.value != null && vset.contains(codeId)) {
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
	 * 이 태그의 기능을 위해 오버라이드 되었습니다.
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
