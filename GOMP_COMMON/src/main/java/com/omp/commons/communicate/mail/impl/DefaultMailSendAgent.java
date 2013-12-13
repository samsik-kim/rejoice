package com.omp.commons.communicate.mail.impl;

import java.io.File;

import org.apache.commons.lang.StringUtils;

import com.omp.commons.communicate.mail.AbstractMailSendAgent;
import com.omp.commons.communicate.mail.InvalidMailRequestException;
import com.omp.commons.communicate.mail.MailRequestFailException;
import com.omp.commons.communicate.mail.MailTemplateNotFoundException;
import com.omp.commons.communicate.mail.SendMailModel;
import com.omp.commons.util.config.ConfigProperties;

/**
 * 메일 발송 대행자 기본 구현체
 * @author pat
 *
 */
public class DefaultMailSendAgent
	extends AbstractMailSendAgent {
	
	private File	templateDir;							
	private DefaultMailSendAgentServiceImpl service;
	
	@Override
	public void init(ConfigProperties conf) {
		String	work;
		
		// 탬플릿 디렉토리 설정
		work	= conf.getString("omp.common.module.mailSendAgent.templateDir");
		if (StringUtils.isEmpty(work)) {
			throw new RuntimeException("must set config 'omp.common.module.mailSendAgent.templateDir' for mail template.");
		}
		this.templateDir	= new File(work);
		if (!this.templateDir.isDirectory()) {
			throw new RuntimeException("assigned mail template dir '" + this.templateDir + "' is not exists or not directory.");
		}
		
		// 첨부파일 제한 크기 설정
		work	= conf.getString("omp.common.module.mailSendAgent.attachementFileSizeLimit");
		if (work != null) {
			this.setAttachementFileSizeLimit(Long.parseLong(work));
		}
		
		// 서비스 설정
		this.service	= new DefaultMailSendAgentServiceImpl();
	}

	@Override
	protected long registRequestMailSend(SendMailModel mail)
		throws InvalidMailRequestException, MailRequestFailException {
		String 				work;
		CommonEmailModel	cem;
		
		work	= mail.getSubject();
		if (work.length() > 300) {
			throw new InvalidMailRequestException("메일 제목은 300자를 넘을 수 없습니다.");
		}
		work	= mail.getTemplateId();
		if (!new File(this.templateDir, work).exists()) {
			throw new MailTemplateNotFoundException("메일 탬플릿 {0}은 존재하지 않습니다.", new Object[] {work});
		}
		try {
			cem	= new CommonEmailModel();
			cem.copyFrom(mail);
			cem.setUpdId("system");
			return this.service.insertMailSend(cem);
		} catch (Exception e) {
			throw new MailRequestFailException("메일 발송 요청 등록 실패.", e);
		}
	}
}
