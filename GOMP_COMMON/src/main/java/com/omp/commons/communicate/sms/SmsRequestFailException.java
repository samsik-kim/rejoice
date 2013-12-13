package com.omp.commons.communicate.sms;

import com.omp.commons.exception.LocalizeMessageException;

/**
 * SMS 발송 요청 처리 실패
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class SmsRequestFailException
	extends LocalizeMessageException {

	public SmsRequestFailException() {
		super();
	}

	public SmsRequestFailException(String msgSrc, Object[] msgArgs,
			Throwable cause) {
		super(msgSrc, msgArgs, cause);
	}

	public SmsRequestFailException(String msgSrc, Object[] msgArgs) {
		super(msgSrc, msgArgs);
	}

	public SmsRequestFailException(String msgSrc, Throwable cause) {
		super(msgSrc, cause);
	}

	public SmsRequestFailException(String msgSrc) {
		super(msgSrc);
	}

	public SmsRequestFailException(Throwable cause) {
		super(cause);
	}

}
