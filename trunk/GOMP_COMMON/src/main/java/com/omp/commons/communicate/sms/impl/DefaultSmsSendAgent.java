package com.omp.commons.communicate.sms.impl;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;

import com.omp.commons.communicate.mail.MailRequestFailException;
import com.omp.commons.communicate.sms.AbstractSmsSendAgent;
import com.omp.commons.communicate.sms.InvalidSmsRequestException;
import com.omp.commons.communicate.sms.SendSmsModel;
import com.omp.commons.communicate.sms.SmsRequestFailException;
import com.omp.commons.util.config.ConfigProperties;

/**
 * SMS 발송 대행자 기본 구현체
 * @author pat
 *
 */
public class DefaultSmsSendAgent
	extends AbstractSmsSendAgent {
	
	private DefaultSmsSendAgentServiceImpl	service;
	private String	messageCharset;
	private int		messageBytesLimit;

	@Override
	public void init(ConfigProperties conf) {
		String	work;
		
		//validate
		work	= conf.getString("omp.common.module.smsSendAgent.messageCharset");
		if (StringUtils.isEmpty(work)) {
			throw new RuntimeException("must set config 'omp.common.module.smsSendAgent.messageCharset' for sms charset.");
		}
		this.messageCharset	= work;
		work	= conf.getString("omp.common.module.smsSendAgent.messageLimit");
		if (StringUtils.isEmpty(work)) {
			throw new RuntimeException("must set config 'omp.common.module.smsSendAgent.messageLimit' for sms message length limit(bytes).");
		}
		try {
			this.messageBytesLimit	= Integer.parseInt(work);
		} catch (NumberFormatException e) {
			throw new RuntimeException("assigned sms message length '" + work + "' is not valid integer.", e);
		}
		
		// 서비스 설정
		this.service	= new DefaultSmsSendAgentServiceImpl();
	}

	@Override
	protected long registRequestSmsSend(SendSmsModel ssm)
		throws InvalidSmsRequestException, SmsRequestFailException {
		int				messageLength;
		CommonSmsModel	csm;
		
		
		try {
			messageLength	= ssm.getMessage().getBytes(this.messageCharset).length;
		} catch (UnsupportedEncodingException e) {
			throw new InvalidSmsRequestException("SMS 메세지 인코딩 케릭터셋이 {0}은 시스템에서 지원하지 않는 케릭터 셋입니다."
				, new Object[] {this.messageCharset});
		}
		if (messageLength > this.messageBytesLimit) {
			throw new InvalidSmsRequestException("SMS 메세지의 허용 크기인 {0}bytes는 넘었습니다. (전달된 메세지 크기 {1}bytes)"
				, new Object[] {this.messageBytesLimit, messageLength});
		}
		
		
		try {
			csm	= new CommonSmsModel();
			csm.copyFrom(ssm);
			csm.setUpdId("system");
			return this.service.insertSmsSend(csm);
		} catch (Exception e) {
			throw new MailRequestFailException("SMS 발송 요청 등록 실패.", e);
		}
	}
}