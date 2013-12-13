package com.omp.commons.payment;


/**
 * 잘못된 결재 정보로 인한 결재 거부
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class InvalidPaymentInfoException
	extends PaymentDenyException {

	public InvalidPaymentInfoException(String msgSrc, String venderCode) {
		super(msgSrc, venderCode);
	}

}
