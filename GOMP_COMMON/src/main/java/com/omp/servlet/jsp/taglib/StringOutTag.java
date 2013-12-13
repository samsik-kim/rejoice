package com.omp.servlet.jsp.taglib;

import pat.neocore.util.MiscEncoder;

/**
 * 따옴표로 둘러싸인 String 표현식 내에 값을 출력합니다.
 * 자세한 기능은 AbstractOutTag 참조 하십시오.
 * @author pat
 * @see AbstractOutTag
 */
@SuppressWarnings("serial")
public class StringOutTag
	extends AbstractOutTag {

	/**
	 * AbstractOutTag 구현 메소드
	 * 따옴표로 둘러싸인 String 표현식 내에
	 * 올바르게 표시되도록 출력합니다.
	 */
	protected String getEncodeString(String src)
		throws Exception {
		return MiscEncoder.encodeEscapeString(src);
	}

}
