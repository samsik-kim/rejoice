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
public class ResultInfo {
	private String tx_id;
	private String code;
	private String msg;

	public final String getTx_id() {
		return tx_id == null ? "" : tx_id;
	}

	public final void setTx_id(String tx_id) {
		this.tx_id = tx_id;
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
