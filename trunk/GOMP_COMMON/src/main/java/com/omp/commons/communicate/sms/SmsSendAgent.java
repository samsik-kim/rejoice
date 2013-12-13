package com.omp.commons.communicate.sms;

import com.omp.commons.util.config.ConfigProperties;

/**
 * SMS 발송 대행자의 인터페이스
 * @author pat
 *
 */
public interface SmsSendAgent {

	/**
	 * 에이전트 초기화 작업
	 * @param conf
	 */
	public void init(ConfigProperties conf);
	
	/**
	 * sms 발송 요청
	 * @param ssm sms
	 * @return 등록된 요청 아이디
	 * @throws InvalidSmsRequestException 잘못된 내용이 요청 되었을때.
	 * @throws SmsRequestFailException sms 발송 요청 처리 실패.
	 */
	public long requestSmsSend(SendSmsModel ssm)
		throws InvalidSmsRequestException, SmsRequestFailException;
}
