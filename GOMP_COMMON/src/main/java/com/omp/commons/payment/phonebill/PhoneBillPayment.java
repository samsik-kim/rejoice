package com.omp.commons.payment.phonebill;

import com.omp.commons.payment.PaymentModel;

/**
 * 폰빌 결제용 지불모델
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class PhoneBillPayment
	extends PaymentModel {

	// 결재 세션 아이디
	private String	sessionId;
	// 상품코드
	private String	productCode;
	// 상품명
	private String	productName;
	// 번호
	private String	phoneNo;
	// pin번호
	private String	pin;
	// 통신회사
	private String	carrier;
	// 접속 사용자 IP
	private String	connectedIp;
	// 사용자 이메일
	private String	userEmail;
	
	public String getConnectedIp() {
		return connectedIp;
	}
	public void setConnectedIp(String connectedIp) {
		this.connectedIp = connectedIp;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
}
