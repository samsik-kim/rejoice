package com.omp.servlet.jsp.taglib;

/**
 * 출력 대상 값을 수정 없이 그대로 출력합니다.
 * 자세한 기능은 AbstractOutTag 참조 하십시오.
 * @author pat
 * @see AbstractOutTag
 */
@SuppressWarnings("serial")
public class TextOutTag
	extends AbstractOutTag {

	/**
	 * AbstractOutTag 구현 메소드
	 * 출력 대상 값의 가공 없이 그대로 반환합니다.
	 */
	protected String getEncodeString(String src)
		throws Exception {
		return src;
	}

}
