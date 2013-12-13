package com.omp.commons.communicate.mail;

/**
 * 유효하지 않은 메일 첨부 파일일때
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class InvalidMailAttachementFileException
	extends InvalidMailRequestException {

	public InvalidMailAttachementFileException() {
		super();
	}

	public InvalidMailAttachementFileException(String msgSrc, Object[] msgArgs,
			Throwable cause) {
		super(msgSrc, msgArgs, cause);
	}

	public InvalidMailAttachementFileException(String msgSrc, Object[] msgArgs) {
		super(msgSrc, msgArgs);
	}

	public InvalidMailAttachementFileException(String msgSrc, Throwable cause) {
		super(msgSrc, cause);
	}

	public InvalidMailAttachementFileException(String msgSrc) {
		super(msgSrc);
	}

	public InvalidMailAttachementFileException(Throwable cause) {
		super(cause);
	}

}
