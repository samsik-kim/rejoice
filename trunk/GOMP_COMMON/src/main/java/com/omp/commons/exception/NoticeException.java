package com.omp.commons.exception;

/**
 * 사용자에게 에러가 아닌 가벼운 상황변화와 같은 내용을 통지하는 익세션
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class NoticeException
	extends BaseException {

	public NoticeException(String msgSrc, Object[] msgArgs, Throwable cause) {
		super(msgSrc, msgArgs, cause);
	}

	public NoticeException(String msgSrc, Object[] msgArgs) {
		super(msgSrc, msgArgs);
	}

	public NoticeException(String msgSrc, Throwable cause) {
		super(msgSrc, cause);
	}

	public NoticeException(String msgSrc) {
		super(msgSrc);
	}

	public NoticeException(Throwable cause) {
		super(cause);
	}

}
