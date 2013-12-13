package com.omp.commons.communicate.mail;

/**
 * 메일 템플릿 존재하지 않음
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class MailTemplateNotFoundException
	extends InvalidMailRequestException {

	public MailTemplateNotFoundException() {
		super();
	}

	public MailTemplateNotFoundException(String msgSrc, Object[] msgArgs,
			Throwable cause) {
		super(msgSrc, msgArgs, cause);
	}

	public MailTemplateNotFoundException(String msgSrc, Object[] msgArgs) {
		super(msgSrc, msgArgs);
	}

	public MailTemplateNotFoundException(String msgSrc, Throwable cause) {
		super(msgSrc, cause);
	}

	public MailTemplateNotFoundException(String msgSrc) {
		super(msgSrc);
	}

	public MailTemplateNotFoundException(Throwable cause) {
		super(cause);
	}

	
}
