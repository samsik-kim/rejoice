package com.omp.servlet.jsp.taglib;

import pat.neocore.util.MiscEncoder;

/**
 * 특정 코드그룹의 지정된 코드가 의미하는 값을 따옴표 "" 안에서
 * 올바르게 표시되도록 인코딩하여 출력하는 태그 입니다.
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class StringCodeValueTag
	extends AbstractCodeValueTag {

	/**
	 * AbstractCodeValueTag 구현 메소드
	 */
	protected String getEncodeString(String src)
		throws Exception {
		return MiscEncoder.encodeEscapeString(src);
	}

}
