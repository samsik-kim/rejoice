package com.omp.commons.communicate.mail;

import com.omp.commons.exception.LocalizeMessageException;

/**
 * 잘못된 메일 전송 요청시
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class InvalidMailRequestException
	extends LocalizeMessageException {

	public InvalidMailRequestException() {
		super();
	}

	public InvalidMailRequestException(String msgSrc, Object[] msgArgs,
			Throwable cause) {
		super(msgSrc, msgArgs, cause);
	}

	public InvalidMailRequestException(String msgSrc, Object[] msgArgs) {
		super(msgSrc, msgArgs);
	}

	public InvalidMailRequestException(String msgSrc, Throwable cause) {
		super(msgSrc, cause);
	}

	public InvalidMailRequestException(String msgSrc) {
		super(msgSrc);
	}

	public InvalidMailRequestException(Throwable cause) {
		super(cause);
	}

}
