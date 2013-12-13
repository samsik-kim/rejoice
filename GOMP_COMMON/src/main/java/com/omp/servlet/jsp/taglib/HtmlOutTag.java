package com.omp.servlet.jsp.taglib;

import pat.neocore.util.MiscEncoder;

/**
 * HTML페이지에 값을 표시합니다.
 * 자세한 기능은 AbstractOutTag 참조 하십시오.
 * @author pat
 * @see AbstractOutTag
 */
@SuppressWarnings("serial")
public class HtmlOutTag
	extends AbstractOutTag {

	/**
	 * AbstractOutTag 구현 메소드
	 * HTML에 원본 문자열이 그대로 보여 지도록 인코딩 합니다.
	 */
	protected String getEncodeString(String src)
		throws Exception {
		return MiscEncoder.encode4Html(src);
	}

}
