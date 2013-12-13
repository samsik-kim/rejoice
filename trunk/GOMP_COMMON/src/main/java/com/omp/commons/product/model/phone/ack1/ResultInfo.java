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

/**
 * 폰 맵핑 연동용 수신 여부 통지 XML 표현 모델
 * @author pat
 *
 */
public class ResultInfo {
	private String file_name;
	private String file_path;
	private String code;
	private String msg;

	public String getFile_name() {
		return file_name == null ? "" : file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_path() {
		return file_path == null ? "" : file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public final String getCode() {
		return code == null ? "" : code;
	}

	public final void setCode(String code) {
		this.code = code;
	}

	public final String getMsg() {
		return msg == null ? "" : msg;
	}

	public final void setMsg(String msg) {
		this.msg = msg;
	}

}
