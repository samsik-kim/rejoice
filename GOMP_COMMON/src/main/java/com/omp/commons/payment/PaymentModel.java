package com.omp.commons.payment;

import java.math.BigDecimal;
import java.util.Date;

import com.omp.commons.model.DataValueObject;

/**
 * 결재 처리 모델들의 공통 요소를 가지는 슈퍼모델
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class PaymentModel
	extends DataValueObject {
	
	// 주문자 아이디
	protected String customerId;
	// 주문 아이디
	protected String orderId;
	// 주문 시각
	protected Date orderDate;
	// 거래 아이디
	protected String transactionId;
	// 승인 아이디
	protected String approvalId;
	// 인증코드
	protected String authCode;
	// 요청금액
	protected BigDecimal requestAmount;
	// 승인금액
	protected BigDecimal approvalAmount;
	// 승인일자
	private Date   approvalDate;
	
	public PaymentModel() {
		this.orderDate	= new Date();
	}
	
	public Date getApprovalDate() {
		return approvalDate;
	}
	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getApprovalId() {
		return approvalId;
	}
	public void setApprovalId(String approvalId) {
		this.approvalId = approvalId;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public BigDecimal getRequestAmount() {
		return requestAmount;
	}
	public void setRequestAmount(BigDecimal requestAmount) {
		this.requestAmount = requestAmount;
	}
	public BigDecimal getApprovalAmount() {
		return approvalAmount;
	}
	public void setApprovalAmount(BigDecimal approvalAmount) {
		this.approvalAmount = approvalAmount;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}
