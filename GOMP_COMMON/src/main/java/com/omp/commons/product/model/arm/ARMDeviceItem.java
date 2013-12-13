package com.omp.commons.product.model.arm;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * ARM 연동용 디바이스 정보의  아이템 단위 원소 모델
 * @author pat
 *
 */
public class ARMDeviceItem {


	private String type;
	private String value;

	/**
	 * @return the type
	 */
	@XmlAttribute
	public String getType() {
		return type == null ? "" : type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the value
	 */
	@XmlValue
	public String getValue() {
		return value == null ? "" : value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
