package com.omp.commons.communicate.sms;

import com.omp.commons.exception.LocalizeMessageException;

/**
 * 유효하지 않은 SMS 발송 요청
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class InvalidSmsRequestException
	extends LocalizeMessageException {

	public InvalidSmsRequestException() {
		super();
	}

	public InvalidSmsRequestException(String msgSrc, Object[] msgArgs,
			Throwable cause) {
		super(msgSrc, msgArgs, cause);
	}

	public InvalidSmsRequestException(String msgSrc, Object[] msgArgs) {
		super(msgSrc, msgArgs);
	}

	public InvalidSmsRequestException(String msgSrc, Throwable cause) {
		super(msgSrc, cause);
	}

	public InvalidSmsRequestException(String msgSrc) {
		super(msgSrc);
	}

	public InvalidSmsRequestException(Throwable cause) {
		super(cause);
	}
}
