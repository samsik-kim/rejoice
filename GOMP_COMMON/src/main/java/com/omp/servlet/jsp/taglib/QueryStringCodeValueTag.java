package com.omp.servlet.jsp.taglib;

import java.net.URLEncoder;


/**
 * URL에 사용될 쿼리 스트링으로 코드값을 출력합니다.
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class QueryStringCodeValueTag
	extends AbstractCodeValueTag {

	private String	charSet;

	/**
	 * 케릭터 셋을 지정합니다.
	 * @param charSet
	 */
	public void setCharSet(String charSet) {
		this.charSet	= charSet;
	}

	
	@Override
	protected String getEncodeString(String src) throws Exception {
		return URLEncoder.encode(src, this.charSet);
	}

	/**
	 * 이 메소드의 기능을 위해 오버라이드 되었습니다.
	 */
	public void release() {
		super.release();
		this.charSet	= null;
	}
}
