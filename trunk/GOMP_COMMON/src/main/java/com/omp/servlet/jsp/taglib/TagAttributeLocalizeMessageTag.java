package com.omp.servlet.jsp.taglib;

import pat.neocore.util.MiscEncoder;

/**
 * 따옴표로 둘러싸인 태그 속성 표현식 내에 지역화 메세지의 내용을 표시합니다.
 * 자세한 기능은 AbstractLocalizeMessageTag를 참조 하십시오.
 * @author pat
 * @see AbstractLocalizeMessageTag
 */
@SuppressWarnings("serial")
public class TagAttributeLocalizeMessageTag
	extends AbstractLocalizeMessageTag {

	/**
	 * AbstractLocalizeMessageTag 구현 메소드
	 * 따옴표로 둘러싸인 태그 속성 표현식 내에
	 * 올바르게 표현될수 있도록 인코딩 합니다.
	 */
	protected String getEncodeString(String src)
		throws Exception {
		return MiscEncoder.encode4TagAttribute(src);
	}

}
