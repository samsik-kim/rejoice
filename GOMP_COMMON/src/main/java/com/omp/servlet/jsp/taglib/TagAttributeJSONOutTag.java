package com.omp.servlet.jsp.taglib;

import pat.neocore.util.MiscEncoder;

/**
 * 태그속성 내에서 JSON식으로 출력하는 태그입니다.
 * 자세한것은 JSONOutTag를 참조 하십시오.
 * @author pat
 */
@SuppressWarnings("serial")
public class TagAttributeJSONOutTag
	extends JSONOutTag {

	/**
	 * 이 태그의 기능을 위해 오버라이드 되었습니다.
	 */
	protected String getEncodedText(String src) {
		return MiscEncoder.encode4TagAttribute(src);
	}
}
