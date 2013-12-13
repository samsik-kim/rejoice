package com.omp.commons.communicate.mail;

import com.omp.commons.exception.LocalizeMessageException;

/**
 * 메일 전송 요청 과정중 오류 발생시
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class MailRequestFailException
	extends LocalizeMessageException {

	public MailRequestFailException() {
		super();
	}

	public MailRequestFailException(String msgSrc, Object[] msgArgs,
			Throwable cause) {
		super(msgSrc, msgArgs, cause);
	}

	public MailRequestFailException(String msgSrc, Object[] msgArgs) {
		super(msgSrc, msgArgs);
	}

	public MailRequestFailException(String msgSrc, Throwable cause) {
		super(msgSrc, cause);
	}

	public MailRequestFailException(String msgSrc) {
		super(msgSrc);
	}

	public MailRequestFailException(Throwable cause) {
		super(cause);
	}

}
