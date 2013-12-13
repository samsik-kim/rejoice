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
package com.omp.commons.product.model.phone.ack2;

/**
 * 폰 맵핑 연동용 처리 여부 통지 XML 표현 모델
 * @author pat
 *
 */
public class Header {
	public ResultInfo result_info;

	public Header() {
		result_info = new ResultInfo();
	}

}
