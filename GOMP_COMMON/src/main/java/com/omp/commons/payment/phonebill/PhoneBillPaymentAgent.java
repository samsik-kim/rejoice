package com.omp.commons.payment.phonebill;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 신용카드 결재 대행자
 * @author pat
 *
 */
public interface PhoneBillPaymentAgent
	extends Remote, Serializable {
	
	/**
	 * 폰빌 인증코드 발송 요청
	 * @param payment 결재정보
	 * @return 발송 처리된 결과
	 * @throws RemoteException
	 */
	public PhoneBillPayment requestSendAuthCode(PhoneBillPayment payment)
		throws RemoteException;
	
	/**
	 * 폰빌 결재 요청
	 * @param payment 결재정보
	 * @return 결재 처리된 결과
	 * @throws RemoteException
	 */
	public PhoneBillPayment requestApproval(PhoneBillPayment payment)
		throws RemoteException;
	
	/**
	 * 폰빌 결재 취소
	 * @param payment 결재정보
	 * @return 취소 거래번호 (벤더가 제공할 경우)
	 * @throws RemoteException
	 */
	public PhoneBillPayment requestCancel(PhoneBillPayment payment)
		throws RemoteException;
}
