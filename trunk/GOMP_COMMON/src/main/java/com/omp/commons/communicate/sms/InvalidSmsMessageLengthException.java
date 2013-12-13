package com.omp.commons.communicate.sms;


/**
 * SMS 메세지 길이 오류
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class InvalidSmsMessageLengthException
	extends	InvalidSmsRequestException {

	public InvalidSmsMessageLengthException() {
		super();
	}

	public InvalidSmsMessageLengthException(String msgSrc, Object[] msgArgs,
			Throwable cause) {
		super(msgSrc, msgArgs, cause);
	}

	public InvalidSmsMessageLengthException(String msgSrc, Object[] msgArgs) {
		super(msgSrc, msgArgs);
	}

	public InvalidSmsMessageLengthException(String msgSrc, Throwable cause) {
		super(msgSrc, cause);
	}

	public InvalidSmsMessageLengthException(String msgSrc) {
		super(msgSrc);
	}

	public InvalidSmsMessageLengthException(Throwable cause) {
		super(cause);
	}

}
