package com.omp.servlet.jsp.taglib;

import com.omp.commons.commcode.model.CommCode;

/**
 * 코드 리스트로 랜더링 하는 커스텀 태그의 추상체.
 * @author pat
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractCodeListRenderTag
	extends AbstractCodeListResolveTag {
	
	protected static final int CODE_TYPE_NORMAL	= 0;
	protected static final int CODE_TYPE_FULL	= 1;
	
	protected int			codeType;
	
	/**
	 * 렌더링 할때 사용할 코드 타입 설정
	 * @param type
	 */
	public void setCodeType(String type) {
		if ("normal".equals(type)) {
			this.codeType 	= CODE_TYPE_NORMAL;
		} else if ("full".equals(type)) {
			this.codeType 	= CODE_TYPE_FULL;
		} else {
			throw new RuntimeException("unknown code type '" + type + "', code type must on of (normal|full).");
		}
	}
	
	/**
	 * 설정된 코드 타입에 따른 코드값 얻기
	 * @param code
	 * @return
	 */
	protected String getCode(CommCode code) {
		return this.codeType == CODE_TYPE_NORMAL ? code.getDtlCd() : code.getDtlFullCd();
	}
	
	/**
	 * 이 태그의 기능을 위해 오버라이딩 되었습니다.
	 */
	public void release() {
		super.release();
		this.codeType		= CODE_TYPE_FULL;
	}
}
