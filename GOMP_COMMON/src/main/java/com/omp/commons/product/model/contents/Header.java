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
public class Header {
	private String tx_id;
	private String service_id;

	public String getTx_id() {
		return tx_id == null ? "" : tx_id;
	}

	public void setTx_id(String tx_id) {
		this.tx_id = tx_id;
	}

	public String getService_id() {
		return service_id == null ? "" : service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

}
