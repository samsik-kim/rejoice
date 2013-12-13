package com.omp.servlet.jsp.taglib;

import pat.neocore.util.MiscEncoder;

/**
 * 특정 코드그룹의 지정된 코드가 의미하는 값을
 * 태그 몸체 안에 올바르게 표현될 수 있도록
 * 인코딩 하여 출력하는 태그입니다.
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class TagBodyCodeValueTag
	extends AbstractCodeValueTag {

	/**
	 * AbstractCodeValueTag 구현 메소드
	 */
	protected String getEncodeString(String src)
		throws Exception {
		return MiscEncoder.encode4TagBody(src);
	}

}
