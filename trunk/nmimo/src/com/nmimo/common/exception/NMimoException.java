package com.nmimo.common.exception;

@SuppressWarnings("serial")
public class NMimoException extends NMimoAbstractException {
	
	/**
	 * 에러 메세지 변수값을 받아 처리하는 생성자
	 */
	public NMimoException(String errorMessage) {
		super(errorMessage);
	}
	
	/**
	 * 에러 코드를 반환
	 */
	public String getErrorCode() {
		return null;
	}	
}