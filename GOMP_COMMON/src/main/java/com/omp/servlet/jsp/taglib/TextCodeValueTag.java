package com.omp.servlet.jsp.taglib;

/**
 * 특정 코드그룹의 지정된 코드 값을 출력하는 태그 입니다.
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class TextCodeValueTag
	extends AbstractCodeValueTag {

	/**
	 * AbstractCodeValueTag 구현 메소드
	 */
	protected String getEncodeString(String src)
		throws Exception {
		return src;
	}

}
