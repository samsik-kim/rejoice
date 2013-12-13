package com.omp.servlet.jsp.taglib;

import pat.neocore.util.MiscEncoder;

/**
 * &lt;textarea&gt;와 같이 태그로 둘러싸인 태그바디 내에 지역화 메세지의 내용을 표시합니다.
 * 자세한 기능은 AbstractLocalizeMessageTag를 참조 하십시오.
 * @author pat
 * @see AbstractLocalizeMessageTag
 */
@SuppressWarnings("serial")
public class TagBodyLocalizeMessageTag
	extends AbstractLocalizeMessageTag {

	/**
	 * AbstractLocalizeMessageTag 구현 메소드
	 * &lt;textarea&gt;와 같이 태그로 둘러싸인 태그바디 내에
	 * 올바르게 표현될 수 있도록 인코딩 합니다.
	 */
	protected String getEncodeString(String src)
		throws Exception {
		return MiscEncoder.encode4TagBody(src);
	}

}
