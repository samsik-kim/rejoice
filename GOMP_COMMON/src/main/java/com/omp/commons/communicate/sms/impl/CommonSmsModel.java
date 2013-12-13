package com.omp.commons.communicate.sms.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.omp.commons.communicate.sms.SendSmsModel;

/**
 * SMS 발송 대행자 기본 구현체에서 사용하는  SMS발송 데이터 모델
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class CommonSmsModel
	extends SendSmsModel {
	
	private static final SimpleDateFormat	SDF	= new SimpleDateFormat("yyyyMMddHHmmss");
	
    private Long smsId;
    private String venderSmsId;
    private String status;
    private String sendDttm;
    private String sendResult;
    private String regDttm;
    private String regId;
    private String updDttm;
    private String updId;
    
	public Long getSmsId() {
		return smsId;
	}
	public void setSmsId(Long smsId) {
		this.smsId = smsId;
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
	public String getVenderSmsId() {
		return venderSmsId;
	}
	public void setVenderSmsId(String venderSmsId) {
		this.venderSmsId = venderSmsId;
	}
}
