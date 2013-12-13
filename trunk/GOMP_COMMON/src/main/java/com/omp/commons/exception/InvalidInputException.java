package com.omp.commons.exception;

/**
 * 사용자의 입력 실수를 통지하는 익셉션
 * @author pat
 * 
 */
@SuppressWarnings("serial")
public class InvalidInputException extends BaseException {

	public InvalidInputException(String msgSrc, Object[] msgArgs,
			Throwable cause) {
		super(msgSrc, msgArgs, cause);
	}

	public InvalidInputException(String msgSrc, Object[] msgArgs) {
		super(msgSrc, msgArgs);
	}

	public InvalidInputException(String msgSrc, Throwable cause) {
		super(msgSrc, cause);
	}

	public InvalidInputException(String msgSrc) {
		super(msgSrc);
	}

	public InvalidInputException(Throwable cause) {
		super(cause);
	}
}
