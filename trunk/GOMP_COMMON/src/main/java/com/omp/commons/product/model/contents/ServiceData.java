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
public class ServiceData {
	//@XmlElement(name = "service_time")
	public ServiceTime service_time;
	//@XmlElement(name = "error_data")
	public ErrorData error_data;

	public ServiceData() {
		service_time = new ServiceTime();
		error_data = new ErrorData();
	}
}
