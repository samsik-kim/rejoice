/*
 * COPYRIGHT(c) SK telecom 2011
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author | Date        | Description
 * ---------------------------------------
 * bcpark | 2011. 3. 29. | Description
 *
 */
package com.omp.commons.product.model.contents;

/**
 * 배포 연동용 XML 표현 모델
 * @author pat
 *
 */
public class ErrorData {
	private String error_code;
	private String error_msg;

	public String getError_code() {
		return error_code == null ? "" : error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	public String getError_msg() {
		return error_msg == null ? "" : error_msg;
	}

	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}

}
