package com.nmimo.common.configuration.exception;

import com.nmimo.common.ServiceConstants;
import com.nmimo.common.exception.NMimoAbstractException;

@SuppressWarnings("serial")
public class ConfigFileNotFoundException extends  NMimoAbstractException {

	/**
	 * 
	 * <pre>
	 * 상위 클래스에 예외메세지를 전달한다.
	 * </pre>
	 * @param element message.properties 파일에 있는 예외메세지에 변수로 들어갈 문자열
	 */
	public ConfigFileNotFoundException(String errorMessageParameter) {
		super(errorMessageParameter);
	}
	
	/**
	 * Configuration 예외 발생시 message 파일에서 예외메세지를 가져오기 위한 키값 을 정의 
	 */
	public String getErrorCode() {
		return ServiceConstants.ConfigFileNotFound;
	}
}