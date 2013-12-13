package com.omp.commons.communicate.mail;

import java.io.File;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.lang.StringUtils;

/**
 * 메일 전송 요청 대행자의 추상체
 * @author pat
 *
 */
public abstract class AbstractMailSendAgent
	implements MailSendAgent {
	
	private long attachecmentFileSizeLimit	= 1024 * 1024;	// 첨부파일 허용 크기
	
	/**
	 * 메일에 허용할 첨부 파일의 크기를 설정합니다.
	 * 기본 값은 1메가이며, 0이 지정되면 첨부 허용 하지 않습니다.
	 * @param limit
	 */
	protected void setAttachementFileSizeLimit(long limit) {
		this.attachecmentFileSizeLimit	= limit;
	}
	
	@Override
	public long requestMailSend(SendMailModel mail)
		throws InvalidMailRequestException, MailRequestFailException {
		String	work;
		File	attf;
		
		// validation
		if (StringUtils.isEmpty(mail.getTemplateId())) {
			throw new InvalidMailRequestException("템플릿 아이디는 필수 입니다.");
		}
		if (StringUtils.isEmpty(mail.getRefAppId())) {
			throw new InvalidMailRequestException("메일 전송 요청 연관 프로그램 ID는 필수 입니다.");
		}
		if (StringUtils.isEmpty(mail.getRefDataId())) {
			throw new InvalidMailRequestException("메일 전송 요청 연관 데이터 ID는 필수 입니다.");
		}
		work	= mail.getToAddr();
		if (StringUtils.isEmpty(work)) {
			throw new InvalidMailRequestException("메일 전송 수신자 주소는 필수 입니다.");
		}
		try {
			new InternetAddress(work);
		} catch (AddressException e) {
			throw new InvalidMailRequestException("메일 전송 수신자 주소가 올바르지 않습니다.", e);
		}
		work	= mail.getFromAddr();
		if (!StringUtils.isEmpty(work)) {
			try {
				new InternetAddress(work);
			} catch (AddressException e) {
				throw new InvalidMailRequestException("메일 전송 발신자 주소가 올바르지 않습니다.", e);
			}
		}
		work	= mail.getReturnAddr();
		if (!StringUtils.isEmpty(work)) {
			try {
				new InternetAddress(work);
			} catch (AddressException e) {
				throw new InvalidMailRequestException("메일 전송 반송 수신자 주소가 올바르지 않습니다.", e);
			}
		}
		work	= mail.getSubject();
		if (StringUtils.isEmpty(work)) {
			throw new  InvalidMailRequestException("메일 제목은 필수 입니다.");
		}
		attf	= mail.getAttachement();
		if (attf != null) {
			if (this.attachecmentFileSizeLimit < 1) {
				throw new InvalidMailAttachementFileException("메일 첨부 파일 지원하지 않습니다.");
			} else if (!attf.isFile()) {
				throw new InvalidMailAttachementFileException("메일 첨부 파일 {0}가 존재 하지 않거나 파일이 아닙니다."
					, new Object[] {attf});
			} else if (attf.length() > this.attachecmentFileSizeLimit) {
				throw new InvalidMailAttachementFileException("메일 첨부 파일의 크기가 허용 크기인 {0}Bytes보다 큽니다."
					, new Object[] {this.attachecmentFileSizeLimit});
			}
		}
		
		return this.registRequestMailSend(mail);
	}
	
	/**
	 * 실제 발송 요청 등록 구현 메소드
	 * @param mail
	 * @return
	 * @throws InvalidMailRequestException
	 * @throws MailRequestFailException
	 */
	protected abstract long registRequestMailSend(SendMailModel mail)
		throws InvalidMailRequestException, MailRequestFailException;
}
