package com.omp.commons.payment.creditcard;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 신용카드 결재 대행자
 * @author pat
 *
 */
public interface CreditCardPaymentAgent
	extends Remote, Serializable {

	/**
	 * 신용카드 승인 요청
	 * @param cardPayment 승인 요청 카드 정보
	 * @return 처리 결과 
	 * @see CreditCardPayment
	 */
	public CreditCardPayment requestApproval(CreditCardPayment cardPayment)
		throws RemoteException;
	
	/**
	 * 신용카드 승인 취소 요청
	 * @param cardPayment 취소 대상 카드 승인 정보
	 * @return 취소 승인 내용
	 * @see CreditCardPayment
	 */
	public CreditCardPayment requestCancel(CreditCardPayment cardPayment)
		throws RemoteException;
	
}
