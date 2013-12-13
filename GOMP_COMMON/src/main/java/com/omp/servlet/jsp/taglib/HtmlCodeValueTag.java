package com.omp.servlet.jsp.taglib;

import pat.neocore.util.MiscEncoder;

/**
 * 특정 코그 드룹의 지정된 코드가 의미하는 값을 HTML로 출력하는 태그입니다.
 * @author pat
 */
@SuppressWarnings("serial")
public class HtmlCodeValueTag
	extends AbstractCodeValueTag {

	/**
	 * AbstractCodeValueTag 구현 메소드
	 */
	protected String getEncodeString(String src)
		throws Exception {
		return MiscEncoder.encode4Html(src);
	}

}
