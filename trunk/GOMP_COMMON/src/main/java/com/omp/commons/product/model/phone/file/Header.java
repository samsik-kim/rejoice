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

/**
 * 폰빌 맵핑 정보 XML 파일 출력용 모델
 * @author pat
 *
 */
public class Header {
	public RequestInfo request_info;

	public Header() {
		request_info = new RequestInfo();
	}
}
