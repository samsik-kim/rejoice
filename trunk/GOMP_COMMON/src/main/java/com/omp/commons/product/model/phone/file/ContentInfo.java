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
public class ContentInfo {
	private String cid;
	private String scid;

	public String getCid() {
		return cid == null ? "" : cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getScid() {
		return scid == null ? "" : scid;
	}

	public void setScid(String scid) {
		this.scid = scid;
	}

}
