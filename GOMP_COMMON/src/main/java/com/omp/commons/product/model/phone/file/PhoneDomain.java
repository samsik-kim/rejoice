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
package com.omp.commons.product.model.phone.file;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 폰빌 맵핑 정보 XML 파일 출력용 모델
 * @author pat
 *
 */
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlRootElement(name = "message")
public class PhoneDomain {
	public Header header;
	@XmlElementWrapper(name = "body")
	public List<ContentInfo> content_info;

	public PhoneDomain() {
		header = new Header();

		// TODO  : 외부에서 주입되어야 한다.
		// content_info = new ArrayList();
		// content_info.add(new ContentInfo());
		// content_info.add(new ContentInfo());
	}
}