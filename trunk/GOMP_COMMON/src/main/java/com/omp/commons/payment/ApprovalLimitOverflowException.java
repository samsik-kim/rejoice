package com.omp.commons.payment;


/**
 * 한도 초과로 인한 결재 거부
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class ApprovalLimitOverflowException
	extends PaymentDenyException {

	public ApprovalLimitOverflowException(String msgSrc, String venderCode) {
		super(msgSrc, venderCode);
	}
}
