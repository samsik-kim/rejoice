package com.omp.commons.communicate.mail.impl;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.PropertyFilter;

import com.omp.commons.communicate.mail.SendMailModel;

/**
 * 메일 발송 기본 구현체에서 사용하는 메일 발송 데이터 모델
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class CommonEmailModel
	extends SendMailModel {

	private static final JsonConfig	DEFAULT_JSON_CONF	= new JsonConfig(); 
	static {
		DEFAULT_JSON_CONF.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		DEFAULT_JSON_CONF.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (source instanceof File) {
					return !("canonicalPath".equals(name) || "absolutePath".equals(name)
						|| "path".equals(name));
				}
				return false;
			}
		});
	}
	
	private static final SimpleDateFormat	SDF	= new SimpleDateFormat("yyyyMMddHHmmss");
	
	private Long mailId;
    private String status;
    private String sendDttm;
    private String sendResult;
    private String regDttm;
    private String regId;
    private String updDttm;
    private String updId;
    private String contentDataExpr;
    
	public Long getMailId() {
		return mailId;
	}
	public void setMailId(Long mailId) {
		this.mailId = mailId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReserveDttm() {
		if (this.reserveDate == null) {
			return null;
		} else {
			return SDF.format(this.reserveDate);
		}
	}
	public void setReserveDttm(String reserveDttm) {
		if (reserveDttm == null) {
			this.reserveDate	= null;
		} else {
			try {
				this.reserveDate	= SDF.parse(reserveDttm);
			} catch (ParseException e) {
				throw new RuntimeException("invalid date string.");
			}
		}
	}
	public String getSendDttm() {
		return sendDttm;
	}
	public void setSendDttm(String sendDttm) {
		this.sendDttm = sendDttm;
	}
	public String getSendResult() {
		return sendResult;
	}
	public void setSendResult(String sendResult) {
		this.sendResult = sendResult;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getUpdDttm() {
		return updDttm;
	}
	public void setUpdDttm(String updDttm) {
		this.updDttm = updDttm;
	}
	public String getUpdId() {
		return updId;
	}
	public void setUpdId(String updId) {
		this.updId = updId;
	}
	public void setContentData(Object data) {
		super.setContentData(data);
		if (data == null) {
			this.contentDataExpr = null;
		} else {
			if (data instanceof String) {
				Map<String, Object>	dataMap;
				
				dataMap	= new HashMap<String, Object>();
				dataMap.put("data", data);
				
				this.contentDataExpr = JSONObject.fromObject(dataMap, DEFAULT_JSON_CONF).toString();
			} else {
				this.contentDataExpr = JSONObject.fromObject(data, DEFAULT_JSON_CONF).toString();
			}
		}
	}
	public void setContentDataExpr(String dataExpr) {
		this.contentDataExpr	= dataExpr;
		if (dataExpr == null) {
			this.contentData	= null;
		} else {
			this.contentData	= JSONObject.toBean(JSONObject.fromObject(dataExpr), HashMap.class);
		}
	}
	public String getContentDataExpr() {
		return this.contentDataExpr;
	}
}
