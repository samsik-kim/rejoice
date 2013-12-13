package com.omp.servlet.jsp.taglib;

import pat.neocore.util.MiscEncoder;

/**
 * HTML페이지에 지역화 메세지의 내용을 표시합니다.
 * 자세한 기능은 AbstractLocalizeMessageTag를 참조 하십시오.
 * @author pat
 * @see AbstractLocalizeMessageTag
 */
@SuppressWarnings("serial")
public class HtmlLocalizeMessageTag
	extends AbstractLocalizeMessageTag {

	/**
	 * AbstractLocalizeMessageTag의 구현 메소드
	 * HTML에 꼭같은 모양으로 표시 될 수 있도록 인코딩 합니다.
	 */
	protected String getEncodeString(String src)
		throws Exception {
		return MiscEncoder.encode4Html(src);
	}

}
