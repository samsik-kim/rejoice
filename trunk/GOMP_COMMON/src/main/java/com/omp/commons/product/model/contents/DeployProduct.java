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

import javax.xml.bind.annotation.XmlAttribute;

/**
 * 배포 연동용 XML 표현 모델
 * @author pat
 *
 */
public class DeployProduct {
	private String type = "";

	private String tx_id;
	private String product_id;
	private String product_name;
	private String content_id;
	private String create_id;
	private String create_time;
	private String update_id;
	private String update_time;
	private String cud_type;

	@XmlAttribute
	public String getType() {
		return type;
	}

	public final void setType(String type) {
		this.type = type;
	}

	public String getTx_id() {
		return tx_id == null ? "" : tx_id;
	}

	public void setTx_id(String tx_id) {
		this.tx_id = tx_id;
	}

	public String getProduct_id() {
		return product_id == null ? "" : product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name == null ? "<![CDATA[]]>" : "<![CDATA[" + product_name + "]]>";
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getContent_id() {
		return content_id == null ? "" : content_id;
	}

	public void setContent_id(String content_id) {
		this.content_id = content_id;
	}

	public String getCreate_id() {
		return create_id == null ? "" : create_id;
	}

	public void setCreate_id(String create_id) {
		this.create_id = create_id;
	}

	public String getCreate_time() {
		return create_time == null ? "" : create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getUpdate_id() {
		return update_id == null ? "" : update_id;
	}

	public void setUpdate_id(String update_id) {
		this.update_id = update_id;
	}

	public String getUpdate_time() {
		return update_time == null ? "" : update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public String getCud_type() {
		return cud_type == null ? "" : cud_type;
	}

	public void setCud_type(String cud_type) {
		this.cud_type = cud_type;
	}

}
