package com.omp.commons.product.model.arm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * ARM XML 통신용 XML 표현 객체
 * @author pat
 *
 */
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlRootElement(name = "resRegisterApplicationInfo")
public class ARMDomainResRegisterApplicationInfo {
	
	private String resultcode;
	private String message;

	/**
	 * @return the resultcode
	 */
	@XmlAttribute
	public String getResultcode() {
		return resultcode == null ? "" : resultcode;
	}

	/**
	 * @param resultcode the resultcode to set
	 */
	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}


	/**
	 * @return the message
	 */
	public String getMessage() {
		return message == null ? "" : message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
