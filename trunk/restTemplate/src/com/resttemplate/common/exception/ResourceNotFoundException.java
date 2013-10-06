/**
 * 
 */
package com.resttemplate.common.exception;

/**
 * @comment : 
 * @date    : 2013. 10. 6.
 * @author  : Rejoice
 */
@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException{
	
	String code;
	String message;
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