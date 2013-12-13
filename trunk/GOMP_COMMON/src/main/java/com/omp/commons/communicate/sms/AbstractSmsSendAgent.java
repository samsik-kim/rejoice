package com.omp.commons.communicate.sms;

import org.apache.commons.lang.StringUtils;

/**
 * SMS 발송 요청 대행자의 추상체
 * @author pat
 *
 */
public abstract class AbstractSmsSendAgent
	implements SmsSendAgent {


	@Override
	public long requestSmsSend(SendSmsModel ssm)
		throws InvalidSmsRequestException, SmsRequestFailException {
		String	work;
		
		// validation
		if (StringUtils.isEmpty(ssm.getRefAppId())) {
			throw new InvalidSmsRequestException("SMS 전송 요청 연관 프로그램 ID는 필수 입니다.");
		}
		if (StringUtils.isEmpty(ssm.getRefDataId())) {
			throw new InvalidSmsRequestException("SMS 전송 요청 연관 데이터 ID는 필수 입니다.");
		}
		work	= ssm.getToNo();
		if (StringUtils.isEmpty(work)) {
			throw new InvalidSmsRequestException("SMS 수신자 번호는 필수 입니다.");
		}
		if (StringUtils.isEmpty(ssm.getMessage())) {
			throw new  InvalidSmsRequestException("메세지 내용은 필수 입니다.");
		}
		
		return this.registRequestSmsSend(ssm);
	}
	
	
	/**
	 * 실제 발송 요청 등록 구현 메소드
	 * @param ssm
	 * @return
	 * @throws InvalidSmsRequestException
	 * @throws SmsRequestFailException
	 */
	protected abstract long registRequestSmsSend(SendSmsModel ssm)
		throws InvalidSmsRequestException, SmsRequestFailException;
	
}
