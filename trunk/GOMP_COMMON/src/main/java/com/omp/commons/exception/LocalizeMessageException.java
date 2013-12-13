package com.omp.commons.exception;

import java.util.ResourceBundle;

import com.omp.commons.text.LocalizeMessage;
import com.omp.commons.util.ThreadSession;

/**
 * 로컬라이징 할수 있는 메세지 객체를 이용하여 표현하는 익셉션입니다.
 * @author pat
 */
@SuppressWarnings("serial")
public class LocalizeMessageException
	extends RuntimeException {
	
	private LocalizeMessage		msg;
	private String				lmsg;
	
	/**
	 * 생성자
	 */
	public LocalizeMessageException() {
		this(null, null, null);
	}
	
	/**
	 * 생성자
	 * @param cause 원인 예외
	 */
	public LocalizeMessageException(Throwable cause) {
		this(null, null, cause);
	}
	
	/**
	 * 생성자
	 * @param msgSrc 로컬라이징 대상 메세지 소스
	 */
	public LocalizeMessageException(String msgSrc) {
		this(msgSrc, null, null);
	}
	
	/**
	 * 생성자
	 * @param msgSrc 로컬라이징 대상 메세지 소스
	 * @param cause 원인 예외
	 */
	public LocalizeMessageException(String msgSrc, Throwable cause) {
		this(msgSrc, null, cause);
	}
	
	/**
	 * 생성자
	 * @param msgSrc 로컬라이징 대상 메세지 소스
	 * @param msgArgs 메세지 인수
	 */
	public LocalizeMessageException(String msgSrc, Object[] msgArgs) {
		this(msgSrc, msgArgs, null);
	}
	
	
	/**
	 * 생성자
	 * @param msgSrc 로컬라이징 대상 메세지
	 * @param msgArgs 메세지 인수
	 * @param cause 원인 예외
	 */
	public LocalizeMessageException(String msgSrc, Object[] msgArgs, Throwable cause) {
		this(new LocalizeMessage(msgSrc, msgArgs), cause);
	}

	/**
	 * 생성자
	 * @param msg 로컬라이즈 메세지 객체
	 * @param cause 원인 예외
	 */
	private LocalizeMessageException(LocalizeMessage msg, Throwable cause) {
		super(msg.getMessageSource(), cause);
		this.msg	= msg;
	}

	/**
	 * 로컬라이즈 메세지 객체를 반환합니다.
	 * @return
	 */
	public LocalizeMessage	getLocalizeMessage() {
		return this.msg;
	}
	
	/**
	 * 로컬라이즈 메세지를 리턴 하도록 오버하이딩 되었습니다.
	 * LocalizeMessage에 지정된 기본 리소스번들을 사용하지 않으려면 setMessageResourceBundle을 이용하여 지정 할 수 있습니다.
	 * @return
	 * @see #setMessageResourceBundle
	 */
	@Override
	public String getLocalizedMessage() {
		if (this.lmsg == null) {
			this.setMessageResourceBundle(LocalizeMessage.getResourceBundle(ThreadSession.getSession().getServiceLocale()));
		}
		return (this.lmsg == null ? super.getMessage() : this.lmsg); 
	}
	
	/**
	 * 이 익센션이 가지는 로컬라이즈 메세지의 로컬라이징 대상 리소스 번들을
	 * 지정합니다.
	 * @param bundle
	 */
	public void setMessageResourceBundle(ResourceBundle bundle) {
		this.lmsg	= this.msg.getLocalizedMessage(bundle);
	}
}