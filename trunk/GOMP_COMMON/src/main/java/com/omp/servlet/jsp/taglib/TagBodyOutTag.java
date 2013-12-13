package com.omp.servlet.jsp.taglib;

import pat.neocore.util.MiscEncoder;

/**
 * &lt;textarea&gt;와 같이 태그로 둘러싸인 태그바디 내에 값을 표시합니다.
 * 자세한 기능은 AbstractOutTag 참조 하십시오.
 * @author pat
 * @see AbstractOutTag
 */
@SuppressWarnings("serial")
public class TagBodyOutTag
	extends AbstractOutTag {

	/**
	 * AbstractOutTag 구현 메소드
	 * &lt;textarea&gt;와 같이 태그로 둘러싸인 태그바디 내에 값이
	 * 올바르게 표현되록 인코딩 합니다.
	 */
	protected String getEncodeString(String src)
		throws Exception {
		return MiscEncoder.encode4TagBody(src);
	}

}
