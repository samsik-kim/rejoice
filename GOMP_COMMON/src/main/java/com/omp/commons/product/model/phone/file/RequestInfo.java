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
package com.omp.commons.product.model.phone.file;

/**
 * 폰빌 맵핑 정보 XML 파일 출력용 모델
 * @author pat
 *
 */
public class RequestInfo {
	private String tx_id;
	private String module;
	private String type;

	public String getTx_id() {
		return tx_id == null ? "" : tx_id;
	}

	public void setTx_id(String tx_id) {
		this.tx_id = tx_id;
	}

	public String getModule() {
		return module == null ? "" : module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getType() {
		return type == null ? "" : type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
