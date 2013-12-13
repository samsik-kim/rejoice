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
public class DeployMainContent {
	private String type = "";

	private String tx_id;
	private String content_id;
	private String content_name;
	private String main_content_version;
	private String os_type;
	private String content_type;
	private String description;
	private String create_id;
	private String create_time;
	private String update_id;
	private String update_time;
	private String cud_type;

	private String meta_name_01;
	private String meta_value_01;
	private String meta_name_02;
	private String meta_value_02;
	private String meta_name_03;
	private String meta_value_03;
	private String meta_name_04;
	private String meta_value_04;
	private String meta_name_05;
	private String meta_value_05;
	private String meta_name_06;
	private String meta_value_06;
	private String meta_name_07;
	private String meta_value_07;
	private String meta_name_08;
	private String meta_value_08;
	private String meta_name_09;
	private String meta_value_09;
	private String meta_name_10;
	private String meta_value_10;

	@XmlAttribute
	public String getType() {
		return type == null ? "" : type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTx_id() {
		return tx_id == null ? "" : tx_id;
	}

	public void setTx_id(String tx_id) {
		this.tx_id = tx_id;
	}

	public String getContent_id() {
		return content_id == null ? "" : content_id;
	}

	public void setContent_id(String content_id) {
		this.content_id = content_id;
	}

	public String getContent_name() {
		return content_name == null ? "<![CDATA[]]>" : "<![CDATA[" + content_name + "]]>";
	}

	public void setContent_name(String content_name) {
		this.content_name = content_name;
	}

	public String getMain_content_version() {
		return main_content_version == null ? "" : main_content_version;
	}

	public void setMain_content_version(String main_content_version) {
		this.main_content_version = main_content_version;
	}

	public String getOs_type() {
		return os_type == null ? "" : os_type;
	}

	public void setOs_type(String os_type) {
		this.os_type = os_type;
	}

	public String getContent_type() {
		return content_type == null ? "" : content_type;
	}

	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}

	public String getDescription() {
		return description == null ? "<![CDATA[]]>" : "<![CDATA[" + description + "]]>";
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getMeta_name_01() {
		return meta_name_01 == null ? "" : meta_name_01;
	}

	public void setMeta_name_01(String meta_name_01) {
		this.meta_name_01 = meta_name_01;
	}

	public String getMeta_value_01() {
		return meta_value_01 == null ? "" : meta_value_01;
	}

	public void setMeta_value_01(String meta_value_01) {
		this.meta_value_01 = meta_value_01;
	}

	public String getMeta_name_02() {
		return meta_name_02 == null ? "" : meta_name_02;
	}

	public void setMeta_name_02(String meta_name_02) {
		this.meta_name_02 = meta_name_02;
	}

	public String getMeta_value_02() {
		return meta_value_02 == null ? "" : meta_value_02;
	}

	public void setMeta_value_02(String meta_value_02) {
		this.meta_value_02 = meta_value_02;
	}

	public String getMeta_name_03() {
		return meta_name_03 == null ? "" : meta_name_03;
	}

	public void setMeta_name_03(String meta_name_03) {
		this.meta_name_03 = meta_name_03;
	}

	public String getMeta_value_03() {
		return meta_value_03 == null ? "" : meta_value_03;
	}

	public void setMeta_value_03(String meta_value_03) {
		this.meta_value_03 = meta_value_03;
	}

	public String getMeta_name_04() {
		return meta_name_04 == null ? "" : meta_name_04;
	}

	public void setMeta_name_04(String meta_name_04) {
		this.meta_name_04 = meta_name_04;
	}

	public String getMeta_value_04() {
		return meta_value_04 == null ? "" : meta_value_04;
	}

	public void setMeta_value_04(String meta_value_04) {
		this.meta_value_04 = meta_value_04;
	}

	public String getMeta_name_05() {
		return meta_name_05 == null ? "" : meta_name_05;
	}

	public void setMeta_name_05(String meta_name_05) {
		this.meta_name_05 = meta_name_05;
	}

	public String getMeta_value_05() {
		return meta_value_05 == null ? "" : meta_value_05;
	}

	public void setMeta_value_05(String meta_value_05) {
		this.meta_value_05 = meta_value_05;
	}

	public String getMeta_name_06() {
		return meta_name_06 == null ? "" : meta_name_06;
	}

	public void setMeta_name_06(String meta_name_06) {
		this.meta_name_06 = meta_name_06;
	}

	public String getMeta_value_06() {
		return meta_value_06 == null ? "" : meta_value_06;
	}

	public void setMeta_value_06(String meta_value_06) {
		this.meta_value_06 = meta_value_06;
	}

	public String getMeta_name_07() {
		return meta_name_07 == null ? "" : meta_name_07;
	}

	public void setMeta_name_07(String meta_name_07) {
		this.meta_name_07 = meta_name_07;
	}

	public String getMeta_value_07() {
		return meta_value_07 == null ? "" : meta_value_07;
	}

	public void setMeta_value_07(String meta_value_07) {
		this.meta_value_07 = meta_value_07;
	}

	public String getMeta_name_08() {
		return meta_name_08 == null ? "" : meta_name_08;
	}

	public void setMeta_name_08(String meta_name_08) {
		this.meta_name_08 = meta_name_08;
	}

	public String getMeta_value_08() {
		return meta_value_08 == null ? "" : meta_value_08;
	}

	public void setMeta_value_08(String meta_value_08) {
		this.meta_value_08 = meta_value_08;
	}

	public String getMeta_name_09() {
		return meta_name_09 == null ? "" : meta_name_09;
	}

	public void setMeta_name_09(String meta_name_09) {
		this.meta_name_09 = meta_name_09;
	}

	public String getMeta_value_09() {
		return meta_value_09 == null ? "" : meta_value_09;
	}

	public void setMeta_value_09(String meta_value_09) {
		this.meta_value_09 = meta_value_09;
	}

	public String getMeta_name_10() {
		return meta_name_10 == null ? "" : meta_name_10;
	}

	public void setMeta_name_10(String meta_name_10) {
		this.meta_name_10 = meta_name_10;
	}

	public String getMeta_value_10() {
		return meta_value_10 == null ? "" : meta_value_10;
	}

	public void setMeta_value_10(String meta_value_10) {
		this.meta_value_10 = meta_value_10;
	}

}
