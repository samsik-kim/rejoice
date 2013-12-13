package com.omp.servlet.jsp.taglib;

import pat.neocore.util.MiscEncoder;

/**
 * 따옴표로 둘러싸인 태그 속성 표현식 내에 값을 출력합니다.
 * 자세한 기능은 AbstractOutTag 참조 하십시오.
 * @author pat
 * @see AbstractOutTag
 */
@SuppressWarnings("serial")
public class TagAttributeOutTag
	extends AbstractOutTag {

	/**
	 * AbstractOutTag 구현 메소드
	 * 따옴표로 둘러싸인 태그 속성 표현식 내에 값이
	 * 올바르게 표현될 수 있도록 인코딩 합니다.
	 */
	protected String getEncodeString(String src)
		throws Exception {
		return MiscEncoder.encode4TagAttribute(src);
	}

}
