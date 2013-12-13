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

/**
 * 배포 연동용 XML 표현 모델
 * @author pat
 *
 */
public class ServiceTime {
	private String request_time;
	private String response_time;

	public String getRequest_time() {
		return request_time == null ? "" : request_time;
	}

	public void setRequest_time(String request_time) {
		this.request_time = request_time;
	}

	public String getResponse_time() {
		return response_time == null ? "" : response_time;
	}

	public void setResponse_time(String response_time) {
		this.response_time = response_time;
	}

}
