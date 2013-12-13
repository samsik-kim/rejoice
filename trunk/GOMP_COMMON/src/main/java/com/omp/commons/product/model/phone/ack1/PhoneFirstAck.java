/*
 * COPYRIGHT(c) SK telecom 2011
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author | Date        | Description
 * ---------------------------------------
 * bcpark | 2011. 4. 5. | Description
 *
 */
package com.omp.commons.product.model.phone.ack1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 폰 맵핑 연동용 수신 여부 통지 XML 표현 모델
 * @author pat
 *
 */
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlRootElement(name = "message")
public class PhoneFirstAck {
	public Header header;
	private String body;

	public PhoneFirstAck() {
		header = new Header();
	}

	public final String getBody() {
		return body == null ? "" : body;
	}

	public final void setBody(String body) {
		this.body = body;
	}

}
