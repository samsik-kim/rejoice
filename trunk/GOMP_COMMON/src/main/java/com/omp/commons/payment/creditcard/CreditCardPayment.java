package com.omp.commons.payment.creditcard;

import com.omp.commons.payment.PaymentModel;

/**
 * 신용카드 결제용 지불 모델
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class CreditCardPayment
	extends PaymentModel {
	
	private String cardNo;
	private String cardCo;
	private String expireYear;
	private String expireMonth;
	private int    installmentMonth;
	
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCardCo() {
		return cardCo;
	}
	public void setCardCo(String cardCo) {
		this.cardCo = cardCo;
	}
	public String getExpireYear() {
		return expireYear;
	}
	public void setExpireYear(String expireYear) {
		this.expireYear = expireYear;
	}
	public String getExpireMonth() {
		return expireMonth;
	}
	public void setExpireMonth(String expireMonth) {
		this.expireMonth = expireMonth;
	}
	public int getInstallmentMonth() {
		return installmentMonth;
	}
	public void setInstallmentMonth(int installmentMonth) {
		this.installmentMonth = installmentMonth;
	}
}
