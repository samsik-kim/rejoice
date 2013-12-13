package com.omp.commons.product.model.arm;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;


/**
 * ARM 연동용 상품모델의 원소 단위 아이템 모델
 * @author pat
 *
 */
public class ARMItem {
	

	private String value;
	private String kind;

	/**
	 * @return the kind
	 */
	@XmlAttribute
	public String getKind() {
		return kind == null ? "" : kind;
	}
	
	/**
	 * @param kind the kind to set
	 */
	public String setKind(String kind) {
		return this.kind = kind;
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