package com.omp.commons.payment;

import java.rmi.Naming;
import java.rmi.RemoteException;

import org.apache.commons.lang.StringUtils;

import com.omp.commons.payment.creditcard.CreditCardPayment;
import com.omp.commons.payment.creditcard.CreditCardPaymentAgent;
import com.omp.commons.payment.phonebill.PhoneBillPayment;
import com.omp.commons.payment.phonebill.PhoneBillPaymentAgent;
import com.omp.commons.util.CommonUtil;
import com.omp.commons.util.config.ConfigProperties;

/**
 * 결재관련 작업 처리자
 * @author pat
 *
 */
public class PaymentProcessor {
	
	/**
	 * 신용카드 승인 요청
	 * @param cardPayment 승인 처리할 카드 결재 정보<br>
	 * 필수 속성값: orderId, cardNo, expireYear, expireMonth, authCode(CVC같은), installmentMonth (일시불은1), requestAmount 
	 * @return 승인처리된 내용
	 * @throws PaymentConnectFailException 결재 대행서버 접속 실패시
	 * @throws PaymentFailException 결재 처리 시스템 오류
	 * @throws ApprovalLimitOverflowException 승인한도 초과
	 * @throws InvalidPaymentInfoException 카드정보 오류
	 * @throws PaymentDenyException 기타 승인거부
	 * @throws UnsupportedOperationException 이 결재 방법을 지원하지 않을때
	 */
	public static CreditCardPayment requestCreditCardApproval(CreditCardPayment cardPayment)
		throws PaymentConnectFailException, PaymentFailException, ApprovalLimitOverflowException
			, InvalidPaymentInfoException, PaymentDenyException, UnsupportedOperationException {
		try {
			return getCreditCardPaymentAgent().requestApproval(cardPayment);
		} catch (RemoteException e) {
			Throwable	cause;
			
			cause	= CommonUtil.getRemoteCause(e);
			if (cause instanceof PaymentDenyException) {
				throw (PaymentDenyException)cause;
			}
			throw new PaymentFailException("신용카드 결재 처리 실패.", e);
		}
	}
	
	/**
	 * 신용카드 승인 취소 요청
	 * @param cardPayment 기 승인된 카드 결재 정보<br>
	 * 필수 속성값: orderId, cardNo, expireYear, expireMonth, authCode(CVC같은), installmentMonth (일시불은1), requestAmount, transactionId
	 * @return 승인취소내용
	 * @throws PaymentConnectFailException 결재 대행서버 접속 실패시
	 * @throws PaymentFailException 결재 처리 시스템 오류
	 * @throws ApprovalLimitOverflowException 승인한도 초과
	 * @throws InvalidPaymentInfoException 카드정보 오류
	 * @throws PaymentDenyException 기타 승인거부
	 * @throws UnsupportedOperationException 이 결재 방법을 지원하지 않을때
	 */
	public static CreditCardPayment requestCreditCardApprovalCancel(CreditCardPayment cardPayment)
		throws PaymentConnectFailException, PaymentFailException, ApprovalLimitOverflowException
			, InvalidPaymentInfoException, PaymentDenyException, UnsupportedOperationException {
		try {
			return getCreditCardPaymentAgent().requestCancel(cardPayment);
		} catch (RemoteException e) {
			Throwable	cause;
			
			cause	= CommonUtil.getRemoteCause(e);
			if (cause instanceof PaymentDenyException) {
				throw (PaymentDenyException)cause;
			}
			throw new PaymentFailException("신용카드 결재취소 처리 실패.", e);
		}
	}
	
	/**
	 * 폰빌 결재 인증 코드 전송 요청
	 * @param payment 결재 정보<br>
	 * 필수 속성값: orderId, requestAmount, customerId, phoneNo, pin, carrier, connectedIp, userEmail
	 * @return 처리된 내용
	 * @throws PaymentConnectFailException 결재 대행서버 접속 실패시
	 * @throws PaymentFailException 결재 처리 시스템 오류
	 * @throws ApprovalLimitOverflowException 승인한도 초과
	 * @throws InvalidPaymentInfoException 카드정보 오류
	 * @throws PaymentDenyException 기타 승인거부
	 */
	public static PhoneBillPayment requestPhoneBillSendAuthCode(PhoneBillPayment payment)
		throws PaymentConnectFailException, PaymentFailException, ApprovalLimitOverflowException
		, InvalidPaymentInfoException, PaymentDenyException, UnsupportedOperationException {
		try {
			return getPhoneBillPaymentAgent().requestSendAuthCode(payment);
		} catch (RemoteException e) {
			Throwable	cause;
			
			cause	= CommonUtil.getRemoteCause(e);
			if (cause instanceof PaymentDenyException) {
				throw (PaymentDenyException)cause;
			}
			throw new PaymentFailException("폰빌 인증코드 발송 처리 실패.", e);
		}
	}

	
	/**
	 * 폰빌 결재 요청
	 * @param payment 결재 정보<br>
	 * 필수 속성값: transactionId(인증코드 전송시점에 받은 내용), sessionId(인증코드 전송시점에 받은 내용), authCode(사용자가 입력한 OTP)
	 * @return 처리된 내용
	 * @throws PaymentConnectFailException 결재 대행서버 접속 실패시
	 * @throws PaymentFailException 결재 처리 시스템 오류
	 * @throws ApprovalLimitOverflowException 승인한도 초과
	 * @throws InvalidPaymentInfoException 카드정보 오류
	 * @throws PaymentDenyException 기타 승인거부
	 * @throws UnsupportedOperationException 이 결재 방법을 지원하지 않을때
	 */
	public static PhoneBillPayment requestPhoneBillApproval(PhoneBillPayment payment)
		throws PaymentConnectFailException, PaymentFailException, ApprovalLimitOverflowException
		, InvalidPaymentInfoException, PaymentDenyException, UnsupportedOperationException {
		try {
			return getPhoneBillPaymentAgent().requestApproval(payment);
		} catch (RemoteException e) {
			Throwable	cause;
			
			cause	= CommonUtil.getRemoteCause(e);
			if (cause instanceof PaymentDenyException) {
				throw (PaymentDenyException)cause;
			}
			throw new PaymentFailException("폰빌 결재 처리 실패.", e);
		}
	}
	

	/**
	 * 폰빌 결재 취소 요청
	 * @param payment 결재 정보<br>
	 * 필수 속성값: transactionId(인증코드 전송시점에 받은 내용), sessionId(인증코드 전송시점에 받은 내용), authCode(사용자가 입력한 OTP)
	 * @return 취소 거래내용
	 * @throws PaymentConnectFailException 결재 대행서버 접속 실패시
	 * @throws PaymentFailException 결재 처리 시스템 오류
	 * @throws ApprovalLimitOverflowException 승인한도 초과
	 * @throws InvalidPaymentInfoException 카드정보 오류
	 * @throws PaymentDenyException 기타 승인거부
	 * @throws UnsupportedOperationException 이 결재 방법을 지원하지 않을때
	 */
	public static PhoneBillPayment requestPhoneBillApprovalCancel(PhoneBillPayment payment)
		throws PaymentConnectFailException, PaymentFailException, ApprovalLimitOverflowException
		, InvalidPaymentInfoException, PaymentDenyException, UnsupportedOperationException {
		try {
			return getPhoneBillPaymentAgent().requestCancel(payment);
		} catch (RemoteException e) {
			Throwable	cause;
			
			cause	= CommonUtil.getRemoteCause(e);
			if (cause instanceof PaymentDenyException) {
				throw (PaymentDenyException)cause;
			}
			throw new PaymentFailException("폰빌 결재취소 처리 실패.", e);
		}
	}
	
	/**
	 * 신용카드 결재 대행 서버 rmi 얻기
	 * @return
	 */
	private static CreditCardPaymentAgent getCreditCardPaymentAgent()
		throws PaymentConnectFailException, PaymentFailException {
		ConfigProperties	conf;
		String				agentName;
		CreditCardPaymentAgent		rv;
		
		
		conf		= new ConfigProperties();
		agentName	= conf.getString("omp.common.module.paymentAgent.credit_card.nameUrl");
		if (StringUtils.isEmpty(agentName)) {
			throw new PaymentFailException("신용카드 결재 대행 서버 위치가 환경설정 속성 'omp.common.module.paymentAgent.credit_card.nameUrl'에 지정되어 있지 않습니다.");
		}
		try {
			rv	= (CreditCardPaymentAgent)Naming.lookup(agentName);
		} catch (Exception e) {
			throw new PaymentConnectFailException("결재 대행 서버 접속 실패.", e);
		}
		return rv;
	}
	
	/**
	 * 신용카드 결재 대행 서버 rmi 얻기
	 * @return
	 */
	private static PhoneBillPaymentAgent getPhoneBillPaymentAgent()
		throws PaymentConnectFailException, PaymentFailException {
		ConfigProperties		conf;
		String					agentName;
		PhoneBillPaymentAgent	rv;
		
		
		conf	= new ConfigProperties();
		agentName	= conf.getString("omp.common.module.paymentAgent.phone_bill.nameUrl");
		if (StringUtils.isEmpty(agentName)) {
			throw new PaymentFailException("폰빌 결재 대행 서버 위치가 환경설정 속성 'omp.common.module.paymentAgent.phone_bill.nameUrl'에 지정되어 있지 않습니다.");
		}
		
		try {
			rv	= (PhoneBillPaymentAgent)Naming.lookup(agentName);
		} catch (Exception e) {
			throw new PaymentConnectFailException("결재 대행 서버 접속 실패.", e);
		}
		return rv;
	}
}
