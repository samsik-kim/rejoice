package com.omp.commons.payment;

/**
 * 결재 처리 접속 실패
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class PaymentConnectFailException
	extends PaymentFailException {

	public PaymentConnectFailException() {
	}

	public PaymentConnectFailException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public PaymentConnectFailException(String arg0) {
		super(arg0);
	}

	public PaymentConnectFailException(Throwable arg0) {
		super(arg0);
	}

}
