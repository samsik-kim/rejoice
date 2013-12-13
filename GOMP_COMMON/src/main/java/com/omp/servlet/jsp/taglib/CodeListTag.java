package com.omp.servlet.jsp.taglib;

import java.util.List;

import com.omp.commons.commcode.model.CommCode;

/**
 * 특정 코드 그룹의 코드 내용을 List로 바인드 하는 태그<br>
 * 프로퍼티 일람<br>
 * <ul>
 * <li> var : 바인드 할 바인드명을 지정합니다.</li>
 * </ul>
 * 바인드 되는 List의 원소는 다음의 프로퍼티들을 가진 객체입니다.
 * <ul>
 * <li> grpCd : 코드그룹</li>
 * <li> dtlCd : 코드</li>
 * <li> dtlFullCd : 상세코드</li>
 * <li> cdNm : 코드명</li>
 * <li> addField1 : 추가필드1</li>
 * <li> addField2 : 추가필드2</li>
 * <li> useYN : 사용여부</li>
 * <li> description : 설명</li>
 * <li> displayOrder : 나열순위</li>
 * </ul>
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class CodeListTag
	extends AbstractCodeListResolveTag {
	
	/**
	 * 코드 그룹의 내용인 List가 바인드될 이름
	 */
	private String	var;
	
	/**
	 * 코드 그룹의 내용인 List가 바인드될 이름을 지정합니다.
	 * @param var
	 */
	public void setVar(String var) {
		this.var	= var;
	}

	/**
	 * AbstractCodeListResolveTag 구현 메소드
	 */
	protected void doWork(List<CommCode> codeList)
		throws Exception {
		this.pageContext.getRequest().setAttribute(this.var, codeList);
	}

}
