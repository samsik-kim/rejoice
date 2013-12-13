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

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;

import org.apache.commons.lang.StringUtils;

/**
 * 배포 연동용 XML 표현 모델
 * @author pat
 *
 */
public class DeploySubContent {
	private String type = "";

	private String tx_id;
	private String sub_content_id;
	private String sub_content_name;
	private String sub_content_version;
	private String os_type;
	private String content_type;
	private String content_id;
	private String file_name;
	private String file_path;
	private String file_size;
	private List<String> device_list_array;
	private String device_list;
	private String create_id;
	private String create_time;
	private String update_id;
	private String update_time;
	private String cud_type;

	private String sub_meta_name_01;
	private String sub_meta_value_01;
	private String sub_meta_name_02;
	private String sub_meta_value_02;
	private String sub_meta_name_03;
	private String sub_meta_value_03;
	private String sub_meta_name_04;
	private String sub_meta_value_04;
	private String sub_meta_name_05;
	private String sub_meta_value_05;
	private String sub_meta_name_06;
	private String sub_meta_value_06;
	private String sub_meta_name_07;
	private String sub_meta_value_07;
	private String sub_meta_name_08;
	private String sub_meta_value_08;
	private String sub_meta_name_09;
	private String sub_meta_value_09;
	private String sub_meta_name_10;
	private List<String> sub_meta_value_10_array;
	private String sub_meta_value_10;
	private String sub_meta_name_11;
	private String sub_meta_value_11;
	private String sub_meta_name_12;
	private String sub_meta_value_12;
	private String sub_meta_name_13;
	private String sub_meta_value_13;
	private String sub_meta_name_14;
	private String sub_meta_value_14;
	private String sub_meta_name_15;
	private String sub_meta_value_15;
	private String sub_meta_name_16;
	private String sub_meta_value_16;
	private String sub_meta_name_17;
	private String sub_meta_value_17;
	private String sub_meta_name_18;
	private String sub_meta_value_18;
	private String sub_meta_name_19;
	private String sub_meta_value_19;
	private String sub_meta_name_20;
	private String sub_meta_value_20;
	private String sub_meta_name_21;
	private String sub_meta_value_21;
	private String sub_meta_name_22;
	private String sub_meta_value_22;
	private String sub_meta_name_23;
	private String sub_meta_value_23;
	private String sub_meta_name_24;
	private String sub_meta_value_24;
	private String sub_meta_name_25;
	private String sub_meta_value_25;
	private String sub_meta_name_26;
	private String sub_meta_value_26;
	private String sub_meta_name_27;
	private String sub_meta_value_27;
	private String sub_meta_name_28;
	private String sub_meta_value_28;
	private String sub_meta_name_29;
	private String sub_meta_value_29;
	private String sub_meta_name_30;
	private String sub_meta_value_30;

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

	public String getSub_content_id() {
		return sub_content_id == null ? "" : sub_content_id;
	}

	public void setSub_content_id(String sub_content_id) {
		this.sub_content_id = sub_content_id;
	}

	public String getSub_content_name() {
		return sub_content_name == null ? "<![CDATA[]]>" : "<![CDATA[" + sub_content_name + "]]>";
	}

	public void setSub_content_name(String sub_content_name) {
		this.sub_content_name = sub_content_name;
	}

	public String getSub_content_version() {
		return sub_content_version == null ? "" : sub_content_version;
	}

	public void setSub_content_version(String sub_content_version) {
		this.sub_content_version = sub_content_version;
	}

	public final String getOs_type() {
		return os_type == null ? "" : os_type;
	}

	public final void setOs_type(String os_type) {
		this.os_type = os_type;
	}

	public String getContent_type() {
		return content_type == null ? "" : content_type;
	}

	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}

	public String getContent_id() {
		return content_id == null ? "" : content_id;
	}

	public void setContent_id(String content_id) {
		this.content_id = content_id;
	}

	public String getFile_name() {
		if (file_path == null)
			return "";
		else {
			return file_path.substring(file_path.lastIndexOf("/") + 1);
		}
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_path() {
		if (file_path == null)
			return "";
		else {
			return file_path.substring(0, file_path.lastIndexOf("/") + 1);
		}
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getFile_size() {
		return file_size == null ? "" : file_size;
	}

	public void setFile_size(String file_size) {
		this.file_size = file_size;
	}

	public final void setDevice_list_array(List<String> device_list_array) {
		this.device_list_array = device_list_array;
	}

	public String getDevice_list() {
		if (device_list_array != null && device_list_array.size() > 0)
			return StringUtils.join(device_list_array, ";");
		else
			return "";
	}

	public final void setDevice_list(String device_list) {
		this.device_list = device_list;
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

	public final String getSub_meta_name_01() {
		return sub_meta_name_01 == null ? "" : sub_meta_name_01;
	}

	public final void setSub_meta_name_01(String sub_meta_name_01) {
		this.sub_meta_name_01 = sub_meta_name_01;
	}

	public final String getSub_meta_value_01() {
		return sub_meta_value_01 == null ? "" : sub_meta_value_01;
	}

	public final void setSub_meta_value_01(String sub_meta_value_01) {
		this.sub_meta_value_01 = sub_meta_value_01;
	}

	public final String getSub_meta_name_02() {
		return sub_meta_name_02 == null ? "" : sub_meta_name_02;
	}

	public final void setSub_meta_name_02(String sub_meta_name_02) {
		this.sub_meta_name_02 = sub_meta_name_02;
	}

	public final String getSub_meta_value_02() {
		return sub_meta_value_02 == null ? "" : sub_meta_value_02;
	}

	public final void setSub_meta_value_02(String sub_meta_value_02) {
		this.sub_meta_value_02 = sub_meta_value_02;
	}

	public final String getSub_meta_name_03() {
		return sub_meta_name_03 == null ? "" : sub_meta_name_03;
	}

	public final void setSub_meta_name_03(String sub_meta_name_03) {
		this.sub_meta_name_03 = sub_meta_name_03;
	}

	public final String getSub_meta_value_03() {
		return sub_meta_value_03 == null ? "" : sub_meta_value_03;
	}

	public final void setSub_meta_value_03(String sub_meta_value_03) {
		this.sub_meta_value_03 = sub_meta_value_03;
	}

	public final String getSub_meta_name_04() {
		return sub_meta_name_04 == null ? "" : sub_meta_name_04;
	}

	public final void setSub_meta_name_04(String sub_meta_name_04) {
		this.sub_meta_name_04 = sub_meta_name_04;
	}

	public final String getSub_meta_value_04() {
		return sub_meta_value_04 == null ? "" : sub_meta_value_04;
	}

	public final void setSub_meta_value_04(String sub_meta_value_04) {
		this.sub_meta_value_04 = sub_meta_value_04;
	}

	public final String getSub_meta_name_05() {
		return sub_meta_name_05 == null ? "" : sub_meta_name_05;
	}

	public final void setSub_meta_name_05(String sub_meta_name_05) {
		this.sub_meta_name_05 = sub_meta_name_05;
	}

	public final String getSub_meta_value_05() {
		return sub_meta_value_05 == null ? "" : sub_meta_value_05;
	}

	public final void setSub_meta_value_05(String sub_meta_value_05) {
		this.sub_meta_value_05 = sub_meta_value_05;
	}

	public final String getSub_meta_name_06() {
		return sub_meta_name_06 == null ? "" : sub_meta_name_06;
	}

	public final void setSub_meta_name_06(String sub_meta_name_06) {
		this.sub_meta_name_06 = sub_meta_name_06;
	}

	public final String getSub_meta_value_06() {
		return sub_meta_value_06 == null ? "" : sub_meta_value_06;
	}

	public final void setSub_meta_value_06(String sub_meta_value_06) {
		this.sub_meta_value_06 = sub_meta_value_06;
	}

	public final String getSub_meta_name_07() {
		return sub_meta_name_07 == null ? "" : sub_meta_name_07;
	}

	public final void setSub_meta_name_07(String sub_meta_name_07) {
		this.sub_meta_name_07 = sub_meta_name_07;
	}

	public final String getSub_meta_value_07() {
		return sub_meta_value_07 == null ? "" : sub_meta_value_07;
	}

	public final void setSub_meta_value_07(String sub_meta_value_07) {
		this.sub_meta_value_07 = sub_meta_value_07;
	}

	public final String getSub_meta_name_08() {
		return sub_meta_name_08 == null ? "" : sub_meta_name_08;
	}

	public final void setSub_meta_name_08(String sub_meta_name_08) {
		this.sub_meta_name_08 = sub_meta_name_08;
	}

	public final String getSub_meta_value_08() {
		return sub_meta_value_08 == null ? "" : sub_meta_value_08;
	}

	public final void setSub_meta_value_08(String sub_meta_value_08) {
		this.sub_meta_value_08 = sub_meta_value_08;
	}

	public final String getSub_meta_name_09() {
		return sub_meta_name_09 == null ? "" : sub_meta_name_09;
	}

	public final void setSub_meta_name_09(String sub_meta_name_09) {
		this.sub_meta_name_09 = sub_meta_name_09;
	}

	public final String getSub_meta_value_09() {
		return sub_meta_value_09 == null ? "" : sub_meta_value_09;
	}

	public final void setSub_meta_value_09(String sub_meta_value_09) {
		this.sub_meta_value_09 = sub_meta_value_09;
	}

	public final String getSub_meta_name_10() {
		return sub_meta_name_10 == null ? "" : sub_meta_name_10;
	}

	public final void setSub_meta_name_10(String sub_meta_name_10) {
		this.sub_meta_name_10 = sub_meta_name_10;
	}

	public final void setSub_meta_value_10_array(List<String> sub_meta_value_10_array) {
		this.sub_meta_value_10_array = sub_meta_value_10_array;
	}

	public final String getSub_meta_value_10() {
		if (sub_meta_value_10_array != null && sub_meta_value_10_array.size() > 0)
			return StringUtils.join(sub_meta_value_10_array, ";").replaceAll("X", "_").replaceAll("x", "_");
		else
			return "";
	}

	public final void setSub_meta_value_10(String sub_meta_value_10) {
		this.sub_meta_value_10 = sub_meta_value_10;
	}

	public final String getSub_meta_name_11() {
		return sub_meta_name_11 == null ? "" : sub_meta_name_11;
	}

	public final void setSub_meta_name_11(String sub_meta_name_11) {
		this.sub_meta_name_11 = sub_meta_name_11;
	}

	public final String getSub_meta_value_11() {
		return sub_meta_value_11 == null ? "" : sub_meta_value_11;
	}

	public final void setSub_meta_value_11(String sub_meta_value_11) {
		this.sub_meta_value_11 = sub_meta_value_11;
	}

	public final String getSub_meta_name_12() {
		return sub_meta_name_12 == null ? "" : sub_meta_name_12;
	}

	public final void setSub_meta_name_12(String sub_meta_name_12) {
		this.sub_meta_name_12 = sub_meta_name_12;
	}

	public final String getSub_meta_value_12() {
		return sub_meta_value_12 == null ? "" : sub_meta_value_12;
	}

	public final void setSub_meta_value_12(String sub_meta_value_12) {
		this.sub_meta_value_12 = sub_meta_value_12;
	}

	public final String getSub_meta_name_13() {
		return sub_meta_name_13 == null ? "" : sub_meta_name_13;
	}

	public final void setSub_meta_name_13(String sub_meta_name_13) {
		this.sub_meta_name_13 = sub_meta_name_13;
	}

	public final String getSub_meta_value_13() {
		return sub_meta_value_13 == null ? "" : sub_meta_value_13;
	}

	public final void setSub_meta_value_13(String sub_meta_value_13) {
		this.sub_meta_value_13 = sub_meta_value_13;
	}

	public final String getSub_meta_name_14() {
		return sub_meta_name_14 == null ? "" : sub_meta_name_14;
	}

	public final void setSub_meta_name_14(String sub_meta_name_14) {
		this.sub_meta_name_14 = sub_meta_name_14;
	}

	public final String getSub_meta_value_14() {
		return sub_meta_value_14 == null ? "" : sub_meta_value_14;
	}

	public final void setSub_meta_value_14(String sub_meta_value_14) {
		this.sub_meta_value_14 = sub_meta_value_14;
	}

	public final String getSub_meta_name_15() {
		return sub_meta_name_15 == null ? "" : sub_meta_name_15;
	}

	public final void setSub_meta_name_15(String sub_meta_name_15) {
		this.sub_meta_name_15 = sub_meta_name_15;
	}

	public final String getSub_meta_value_15() {
		return sub_meta_value_15 == null ? "" : sub_meta_value_15;
	}

	public final void setSub_meta_value_15(String sub_meta_value_15) {
		this.sub_meta_value_15 = sub_meta_value_15;
	}

	public final String getSub_meta_name_16() {
		return sub_meta_name_16 == null ? "" : sub_meta_name_16;
	}

	public final void setSub_meta_name_16(String sub_meta_name_16) {
		this.sub_meta_name_16 = sub_meta_name_16;
	}

	public final String getSub_meta_value_16() {
		return sub_meta_value_16 == null ? "" : sub_meta_value_16;
	}

	public final void setSub_meta_value_16(String sub_meta_value_16) {
		this.sub_meta_value_16 = sub_meta_value_16;
	}

	public final String getSub_meta_name_17() {
		return sub_meta_name_17 == null ? "" : sub_meta_name_17;
	}

	public final void setSub_meta_name_17(String sub_meta_name_17) {
		this.sub_meta_name_17 = sub_meta_name_17;
	}

	public final String getSub_meta_value_17() {
		return sub_meta_value_17 == null ? "" : sub_meta_value_17;
	}

	public final void setSub_meta_value_17(String sub_meta_value_17) {
		this.sub_meta_value_17 = sub_meta_value_17;
	}

	public final String getSub_meta_name_18() {
		return sub_meta_name_18 == null ? "" : sub_meta_name_18;
	}

	public final void setSub_meta_name_18(String sub_meta_name_18) {
		this.sub_meta_name_18 = sub_meta_name_18;
	}

	public final String getSub_meta_value_18() {
		return sub_meta_value_18 == null ? "" : sub_meta_value_18;
	}

	public final void setSub_meta_value_18(String sub_meta_value_18) {
		this.sub_meta_value_18 = sub_meta_value_18;
	}

	public final String getSub_meta_name_19() {
		return sub_meta_name_19 == null ? "" : sub_meta_name_19;
	}

	public final void setSub_meta_name_19(String sub_meta_name_19) {
		this.sub_meta_name_19 = sub_meta_name_19;
	}

	public final String getSub_meta_value_19() {
		return sub_meta_value_19 == null ? "" : sub_meta_value_19;
	}

	public final void setSub_meta_value_19(String sub_meta_value_19) {
		this.sub_meta_value_19 = sub_meta_value_19;
	}

	public final String getSub_meta_name_20() {
		return sub_meta_name_20 == null ? "" : sub_meta_name_20;
	}

	public final void setSub_meta_name_20(String sub_meta_name_20) {
		this.sub_meta_name_20 = sub_meta_name_20;
	}

	public final String getSub_meta_value_20() {
		return sub_meta_value_20 == null ? "" : sub_meta_value_20;
	}

	public final void setSub_meta_value_20(String sub_meta_value_20) {
		this.sub_meta_value_20 = sub_meta_value_20;
	}

	public final String getSub_meta_name_21() {
		return sub_meta_name_21 == null ? "" : sub_meta_name_21;
	}

	public final void setSub_meta_name_21(String sub_meta_name_21) {
		this.sub_meta_name_21 = sub_meta_name_21;
	}

	public final String getSub_meta_value_21() {
		return sub_meta_value_21 == null ? "" : sub_meta_value_21;
	}

	public final void setSub_meta_value_21(String sub_meta_value_21) {
		this.sub_meta_value_21 = sub_meta_value_21;
	}

	public final String getSub_meta_name_22() {
		return sub_meta_name_22 == null ? "" : sub_meta_name_22;
	}

	public final void setSub_meta_name_22(String sub_meta_name_22) {
		this.sub_meta_name_22 = sub_meta_name_22;
	}

	public final String getSub_meta_value_22() {
		return sub_meta_value_22 == null ? "" : sub_meta_value_22;
	}

	public final void setSub_meta_value_22(String sub_meta_value_22) {
		this.sub_meta_value_22 = sub_meta_value_22;
	}

	public final String getSub_meta_name_23() {
		return sub_meta_name_23 == null ? "" : sub_meta_name_23;
	}

	public final void setSub_meta_name_23(String sub_meta_name_23) {
		this.sub_meta_name_23 = sub_meta_name_23;
	}

	public final String getSub_meta_value_23() {
		return sub_meta_value_23 == null ? "" : sub_meta_value_23;
	}

	public final void setSub_meta_value_23(String sub_meta_value_23) {
		this.sub_meta_value_23 = sub_meta_value_23;
	}

	public final String getSub_meta_name_24() {
		return sub_meta_name_24 == null ? "" : sub_meta_name_24;
	}

	public final void setSub_meta_name_24(String sub_meta_name_24) {
		this.sub_meta_name_24 = sub_meta_name_24;
	}

	public final String getSub_meta_value_24() {
		return sub_meta_value_24 == null ? "" : sub_meta_value_24;
	}

	public final void setSub_meta_value_24(String sub_meta_value_24) {
		this.sub_meta_value_24 = sub_meta_value_24;
	}

	public final String getSub_meta_name_25() {
		return sub_meta_name_25 == null ? "" : sub_meta_name_25;
	}

	public final void setSub_meta_name_25(String sub_meta_name_25) {
		this.sub_meta_name_25 = sub_meta_name_25;
	}

	public final String getSub_meta_value_25() {
		return sub_meta_value_25 == null ? "" : sub_meta_value_25;
	}

	public final void setSub_meta_value_25(String sub_meta_value_25) {
		this.sub_meta_value_25 = sub_meta_value_25;
	}

	public final String getSub_meta_name_26() {
		return sub_meta_name_26 == null ? "" : sub_meta_name_26;
	}

	public final void setSub_meta_name_26(String sub_meta_name_26) {
		this.sub_meta_name_26 = sub_meta_name_26;
	}

	public final String getSub_meta_value_26() {
		return sub_meta_value_26 == null ? "" : sub_meta_value_26;
	}

	public final void setSub_meta_value_26(String sub_meta_value_26) {
		this.sub_meta_value_26 = sub_meta_value_26;
	}

	public final String getSub_meta_name_27() {
		return sub_meta_name_27 == null ? "" : sub_meta_name_27;
	}

	public final void setSub_meta_name_27(String sub_meta_name_27) {
		this.sub_meta_name_27 = sub_meta_name_27;
	}

	public final String getSub_meta_value_27() {
		return sub_meta_value_27 == null ? "" : sub_meta_value_27;
	}

	public final void setSub_meta_value_27(String sub_meta_value_27) {
		this.sub_meta_value_27 = sub_meta_value_27;
	}

	public final String getSub_meta_name_28() {
		return sub_meta_name_28 == null ? "" : sub_meta_name_28;
	}

	public final void setSub_meta_name_28(String sub_meta_name_28) {
		this.sub_meta_name_28 = sub_meta_name_28;
	}

	public final String getSub_meta_value_28() {
		return sub_meta_value_28 == null ? "" : sub_meta_value_28;
	}

	public final void setSub_meta_value_28(String sub_meta_value_28) {
		this.sub_meta_value_28 = sub_meta_value_28;
	}

	public final String getSub_meta_name_29() {
		return sub_meta_name_29 == null ? "" : sub_meta_name_29;
	}

	public final void setSub_meta_name_29(String sub_meta_name_29) {
		this.sub_meta_name_29 = sub_meta_name_29;
	}

	public final String getSub_meta_value_29() {
		return sub_meta_value_29 == null ? "" : sub_meta_value_29;
	}

	public final void setSub_meta_value_29(String sub_meta_value_29) {
		this.sub_meta_value_29 = sub_meta_value_29;
	}

	public final String getSub_meta_name_30() {
		return sub_meta_name_30 == null ? "" : sub_meta_name_30;
	}

	public final void setSub_meta_name_30(String sub_meta_name_30) {
		this.sub_meta_name_30 = sub_meta_name_30;
	}

	public final String getSub_meta_value_30() {
		return sub_meta_value_30 == null ? "" : sub_meta_value_30;
	}

	public final void setSub_meta_value_30(String sub_meta_value_30) {
		this.sub_meta_value_30 = sub_meta_value_30;
	}

}
