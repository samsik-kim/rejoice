package com.omp.servlet.jsp.taglib;

import pat.neocore.util.MiscEncoder;

/**
 * 특정 코드그룹의 지정된 코드가 의미하는 값을
 * 태그 속성 안의 자바스트립트 스트링으로 올바르게 표시되도록 인코딩하여
 * 출력하는 태그 입니다.
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class TagAttributeStringCodeValueTag
	extends AbstractCodeValueTag {

	@Override
	protected String getEncodeString(String src)
		throws Exception {
		return MiscEncoder.encode4TagAttribute(
			MiscEncoder.encodeEscapeString(src));
	}

}
