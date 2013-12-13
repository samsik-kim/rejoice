package com.omp.servlet.jsp.taglib;

import pat.neocore.util.MiscEncoder;

/**
 * 따옴표로 둘러싸인 태그 속성 표현식 내에 스크립트 스트링 값을 출력합니다.
 * 자세한 기능은 AbstractOutTag 참조 하십시오.
 * @author pat
 * @see AbstractOutTag
 */
@SuppressWarnings("serial")
public class TagAttributeStringOutTag
	extends AbstractOutTag {

	@Override
	protected String getEncodeString(String src)
		throws Exception {
		return MiscEncoder.encode4TagAttribute(
			MiscEncoder.encodeEscapeString(src));
	}

}
