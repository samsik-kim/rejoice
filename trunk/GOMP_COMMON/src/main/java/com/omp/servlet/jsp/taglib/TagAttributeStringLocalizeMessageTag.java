package com.omp.servlet.jsp.taglib;

import pat.neocore.util.MiscEncoder;

/**
 * 따옴표로 둘러싸인 태그 속성 표현식 내에 스크립트 스트링 지역화 메세지의 내용을 표시합니다.
 * 자세한 기능은 AbstractLocalizeMessageTag를 참조 하십시오.
 * @author pat
 * @see AbstractLocalizeMessageTag
 */
@SuppressWarnings("serial")
public class TagAttributeStringLocalizeMessageTag
	extends	AbstractLocalizeMessageTag {

	@Override
	protected String getEncodeString(String src) throws Exception {
		return MiscEncoder.encode4TagAttribute(
			MiscEncoder.encodeEscapeString(src));
	}

}
