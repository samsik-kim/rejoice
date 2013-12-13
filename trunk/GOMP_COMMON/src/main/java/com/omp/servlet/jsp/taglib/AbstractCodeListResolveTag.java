package com.omp.servlet.jsp.taglib;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.omp.commons.commcode.CacheCommCode;
import com.omp.commons.commcode.model.CommCode;

/**
 * 코드 그룹의 코드 목록을 사용하는 태그의 추상 클래스<br>
 * 프로퍼티 일람<br>
 * <ul>
 * <li>group : EL가능. 대상 코드 그룹의 아이디. 필수<li>
 * </ul>
 * 태그 바디의 내용은 출력되지 않습니다.
 * @author pat
 */
@SuppressWarnings("serial")
public abstract class AbstractCodeListResolveTag
	extends BodyTagSupport {
	
	/**
	 * 코드 그룹
	 */
	protected String	cgnm;
	/**
	 * 유효 코드 필터 패턴
	 */
	protected String	filterPattern;
	/**
	 * 제외 코드 필터
	 */
	protected String	excludeFilterPattern;
	
	/**
	 * 코드 그룹을 지정합니다.
	 * @param grp
	 */
	public void setGroup(String grp) {
		this.cgnm	= grp;
	}
	
	public AbstractCodeListResolveTag() {
		this.release();
	}
	
	/**
	 * 유효 코드 필터를 설정합니다.
	 * 필터에 맞는 코드들만 리스트 됩니다.
	 * @param filterPattern 와일드 카드 *나 ?를 사용한 패턴
	 */
	public void setFilter(String filterPattern) {
		this.filterPattern	= filterPattern;
	}
	
	/**
	 * 제외 코드 필터를 설정합니다.
	 * 필터에 맞는 코드는 리스트에서 제외 됩니다.
	 * @param filterPattern 와일드 카드 *나 ?를 사용한 패턴
	 */
	public void setExcludeFilter(String filterPattern) {
		this.excludeFilterPattern	= filterPattern;
	}

	
	/**
	 * 태그의 기능을 위해 오버라이드 되었습니다.
	 */
	public int doEndTag()
		throws JspException {
		
		try {
			String	groupId;
			List<CommCode>	codeList;
			
			groupId	= this.cgnm;
			if (groupId == null) {
				throw new JspException("code group must not null.");
			}
			groupId	= groupId.trim();
			codeList	= CacheCommCode.getCommCode(groupId);
			if (codeList != null) {
				if (this.filterPattern == null && this.excludeFilterPattern == null) {
					this.doWork(codeList);
				} else {
					this.doWork(CacheCommCode.getFilteredList(codeList, this.filterPattern, this.excludeFilterPattern) );
				}
			} else {
				this.pageContext.getOut().println("!!unknown code group " + groupId + "!!");
			}
		} catch (JspException e) {
			throw e;
		} catch (Exception e) {
			throw new JspException("Common Code Tag render fail.",  e);
		} finally {
			this.release();
		}

		return EVAL_PAGE;
	}
	
	/**
	 * 이 태그의 기능을 위해 오버라이드 되었습니다.
	 */
	public void release() {
		this.id						= null;
		this.cgnm					= null;
		this.filterPattern			= null;
		this.excludeFilterPattern	= null;
	}
	
	/**
	 * 조회된 코드그룹의 코드 목록으로 실제 태그의 기능을 수행하도록
	 * 하는 코드를 구현하십시오.
	 * @param codeList 조회된 코드의 그룹. 원소는 pat.neocore.util.code.Code
	 * 입니다.
	 * @throws Exception
	 * @see pat.neocore.util.code.Code
	 */
	protected abstract void doWork(List<CommCode> codeList)
		throws Exception;
	

}
