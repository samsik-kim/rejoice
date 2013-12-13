package com.omp.commons.communicate.mail;

import com.omp.commons.util.config.ConfigProperties;

/**
 * 메일 발송 대행자의 인터페이스
 * @author pat
 *
 */
public interface MailSendAgent {
	
	/**
	 * 에이전트 초기화 작업
	 * @param conf
	 */
	public void init(ConfigProperties conf);
	
	/**
	 * 메일 발송 요청
	 * @param mail
	 * @return 등록된 발송 요청 아이디
	 * @throws InvalidMailRequestException 요청 입력 오류시
	 * @throws MailRequestFailException 요청 처리 실패시
	 */
	public long requestMailSend(SendMailModel mail)
		throws InvalidMailRequestException, MailRequestFailException;

}