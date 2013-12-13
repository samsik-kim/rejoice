package com.omp.servlet.jsp.taglib;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.jsp.JspWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pat.neocore.util.MiscEncoder;

import com.omp.commons.commcode.model.CommCode;

/**
 * 특정 코드의 내용을 &lt;select&gt; 부속 태그인 &lt;option&gt;태그로 출력하는 커스텀 태그<br>
 * 프로퍼티 일람<br>
 * <ul>
 * <li> value : EL 가능. 선택 되어 있어야 할 값을 지정합니다.
 * 지정 가능한 타입은 객체의 배열, 혹은 인터페이스 java.util.Iterable을
 * 구현하고 있는객체, 또는 단일값으로 사용될 객체 입니다.
 * 배열 혹은 인터페이스 java.util.Iterable을 구현하고 있는 객체를 지정할 경우
 * 해당 객체가 가지고 있는 모든 값이 selected 됩니다.</li>
 * <li> extra : 태그 출력시 같이 출력할 추가 속성을 지정합니다.</li> 
 * </ul>
 * 태그 바디의 내용은 출력되지 않습니다.
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class CodeListOptionRenderTag
	extends AbstractCodeListRenderTag {
	
	private Log	logger	= LogFactory.getLog(this.getClass());
	
	/**
	 * 선택 되어 있어야 할 값
	 */
	private Object	value;
	/**
	 * 태그 출력시 함께 출력할 추가 속성
	 */
	private String	extra;
	
	/**
	 * 선택 되어 있어야 할 값을 지정합니다.
	 * @param v
	 */
	public void setValue(Object v) {
		this.value	= v;
	}
	
	/**
	 * 태그와 함께 출력한 추가 속성을 지정합니다.
	 * @param extra
	 */
	public void setExtra(String extra) {
		this.extra	= extra;
	}

	/**
	 * AbstractCodeListResolveTag 구현 메소드
	 */
	protected void doWork(List<CommCode> codeList)
		throws Exception {
		Iterator<CommCode>	its;
		JspWriter			jout;
		Object				vobj;
		Set<String>			vset;
		String				codeId;

		vset	= new HashSet<String>();
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
		
		if (codeList == null) {
			this.logger.warn("code group '" + this.cgnm + "' is empty or not available.");
		} else {
			its	= codeList.iterator();
			jout	= this.pageContext.getOut();
			while (its.hasNext()) {
				CommCode	cd;
				
				cd		= its.next();
				codeId	= this.getCode(cd); 
				jout.print("<option value=\"");
				jout.print(MiscEncoder.encode4TagAttribute(codeId));
				jout.print("\" ");
				if (this.value != null && vset.contains(codeId)) {
					jout.print("selected ");
				}
				if (this.extra != null) {
					jout.print(this.extra);
				}
				jout.print(">");
				jout.print(MiscEncoder.encode4Html(cd.getCdNm()));
				jout.print("</option>");
			}
		}
	}
	
	/**
	 * 이 태그의 기능을 위해 오버라이드 되었습니다.
	 */
	public void release() {
		super.release();
		this.value	= null;
		this.extra	= null;
	}
}
