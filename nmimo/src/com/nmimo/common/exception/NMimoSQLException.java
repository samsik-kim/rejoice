package com.nmimo.common.exception;

import com.nmimo.common.ServiceConstants;

@SuppressWarnings("serial")
public class NMimoSQLException extends NMimoAbstractException {
	
	/**
	 * 예외 객체를 받아서 처리하는 생성자
	 */
	public NMimoSQLException(Throwable throwable) {
		super(throwable);
		
	}
	
	/**
	 * 예외 메세지를 받아서 처리하는 생성자
	 */
	public NMimoSQLException(String errorMessage) {		
		super(errorMessage);
	}
	
	/**
	 * 에러 코드를 반환
	 */
	public String getErrorCode() {
		return ServiceConstants.SQL_ERROR;
	}
}