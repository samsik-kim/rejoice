package com.nmimo.common.exception;

@SuppressWarnings("serial")
public class NMimoExternalException extends RuntimeException {

	/**
	 * 디폴트 생성자
	 */
	public NMimoExternalException() {
		super();
	}
	
	/**
	 * 예외 메세지를 받아서 처리하는 생성자
	 */
	public NMimoExternalException(String message) {
		super(message);
	}
	
	/**
	 * 예외 객체를 받아서 처리하는 생성자
	 */
	public NMimoExternalException(Throwable cause){
		super(cause);
	}
	
	/**
	 * 예외 메세지와 객체를 받아서 처리하는 생성자
	 */
	public NMimoExternalException(String message, Throwable cause) {
		super(message, cause);
	}
}