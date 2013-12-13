/*
 * COPYRIGHT(c) SK telecom 2011
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author | Date        | Description
 * ---------------------------------------
 * bcpark | 2011. 3. 17. | Description
 *
 */
package com.omp.commons.product.model.contents;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 배포 연동용 XML 표현 모델
 * @author pat
 *
 */
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlRootElement(name = "message")
public class ContentsDomain {
	public Header header;
	public ServiceData service_data;
	public Body body;

	public ContentsDomain() {
		header = new Header();
		service_data = new ServiceData();
		// body = new Body();
	}
}
