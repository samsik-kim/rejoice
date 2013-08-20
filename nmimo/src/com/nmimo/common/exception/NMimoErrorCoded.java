package com.nmimo.common.exception;

import org.springframework.core.ErrorCoded;

public interface NMimoErrorCoded extends ErrorCoded {
	
	/**
	 * 예외 메세지 파일에 변수 형태로 값을 넘길 데이타들을 반환.
	 */
	public abstract Object[] getErrorMessageElement() ;
}