package com.omp.commons.communicate.sms;

import java.util.Date;

import com.omp.commons.model.DataValueObject;

/**
 * SMS 발송 요청 모델
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class SendSmsModel
	extends DataValueObject {

	protected String	refAppId;		// 연관 업무 아이디
	protected String	refDataId;		// 연관 데이터의 아이디
	protected String	toNo;			// 수신자 번호
	protected String	fromNo;			// 발신자 번호
	protected String	message;		// 메세지
	protected Date		reserveDate;	// 예약일시
	public String getRefAppId() {
		return refAppId;
	}
	public void setRefAppId(String refAppId) {
		this.refAppId = refAppId;
	}
	public String getRefDataId() {
		return refDataId;
	}
	public void setRefDataId(String refDataId) {
		this.refDataId = refDataId;
	}
	public String getToNo() {
		return toNo;
	}
	public void setToNo(String toNo) {
		this.toNo = toNo;
	}
	public String getFromNo() {
		return fromNo;
	}
	public void setFromNo(String fromNo) {
		this.fromNo = fromNo;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getReserveDate() {
		return reserveDate;
	}
	public void setReserveDate(Date reserveDate) {
		this.reserveDate = reserveDate;
	}
}
