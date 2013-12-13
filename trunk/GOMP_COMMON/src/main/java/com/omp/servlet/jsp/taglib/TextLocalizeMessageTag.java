package com.omp.servlet.jsp.taglib;

/**
 * 지역화 메세지를 아무런 인코딩을 하지 않고 출력합니다.
 * @author pat
 * @see AbstractLocalizeMessageTag
 */
@SuppressWarnings("serial")
public class TextLocalizeMessageTag
	extends AbstractLocalizeMessageTag {

	/**
	 * AbstractLocalizeMessageTag 구현 메소드
	 * 아무런 인코딩 없이 받은 문자열을 반환합니다.
	 */
	protected String getEncodeString(String src)
		throws Exception {
		return src;
	}

}
