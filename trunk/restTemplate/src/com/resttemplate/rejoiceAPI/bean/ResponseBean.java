/**
 * 
 */
package com.resttemplate.rejoiceAPI.bean;

import java.io.Serializable;

/**
 * @comment : RestTemplate 요청의 대한 결과
 * @date    : 2013. 10. 6.
 * @author  : Rejoice
 */
@SuppressWarnings("serial")
public class ResponseBean implements Serializable{

	/**
	 * @comment : 결과 코드 
	 */
	private String code;
	/**
	 * @comment : 결과 메세지
	 */
	private String message;
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}