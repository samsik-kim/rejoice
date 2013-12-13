package com.omp.commons.exception;


/**
 * 익셉션의 슈퍼 객체
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class BaseException extends LocalizeMessageException {

	public BaseException(String msgSrc, Object[] msgArgs, Throwable cause) {
		super(msgSrc, msgArgs, cause);
	}

	public BaseException(String msgSrc, Object[] msgArgs) {
		super(msgSrc, msgArgs);
	}

	public BaseException(String msgSrc, Throwable cause) {
		super(msgSrc, cause);
	}

	public BaseException(String msgSrc) {
		super(msgSrc);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}
	
	
//	private static GLogger logger = new GLogger(BaseException.class);
//	private String domain;
//	private String code;
//	private String messageCode;
//	private Object[] args;
	
//	public String getMessage(String code) {
//		String message="메세지 코드["+code+"]가 messages.properties에 정의가 되지 않았습니다. ";
//		try {
//		    message = Message.get(code);
//			
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		return message;
//	}
//	
//
//
//	public String getMessage(String code, Object[] args) {
//		String message="메세지 코드["+code+"]가 messages.properties에 정의가 되지 않았습니다. ";
//		try {
//            /** @TODO		    
//		    // 메세지처리가들어가야함// message = WCommon.getMessage(code,args,"",Locale.getDefault());
//		     * 
//		     */
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		return message;
//	}
	
	
//	public BaseException(String domain, String code) {
//		super("errors." + domain + "." + code);
//		this.domain = domain;
//		this.code = code;
//		this.messageCode = "errors." + domain + "." + code;
//		logger.error(getMessage(messageCode));
//	}
//	
//	public BaseException(String domain, String code,Throwable causeThrowable) {
//		super("errors." + domain + "." + code,causeThrowable);
//		this.domain = domain;
//		this.code = code;
//		this.messageCode = "errors." + domain + "." + code;
//		logger.error(StringUtil.cutString(getMessage(messageCode) + "\n" + StackTraceUtil.getStackTrace(causeThrowable), 2000));
//	}
//	
//	public BaseException(String domain, String code, Object[] args, Throwable causeThrowable) {
//		super("errors." + domain + "." + code,causeThrowable);
//		this.domain = domain;
//		this.code = code;
//		this.args = args;
//		this.messageCode = "errors." + domain + "." + code;
//		logger.error(StringUtil.cutString(getMessage(messageCode) + "\n" + StackTraceUtil.getStackTrace(causeThrowable), 2000));
//	}
//	
//	public BaseException(String domain, String code, Object[] args) {
//		super("errors." + domain + "." + code);
//		this.domain = domain;
//		this.code = code;
//		this.args = args;
//		this.messageCode = "errors." + domain + "." + code;
//		logger.error(getMessage(messageCode, args));
//	}
//		
//	
//	public BaseException(String messageKey) {
//		super(messageKey);
//		messageCode=messageKey;
//		logger.error(getMessage(messageCode));
//	}	
//	
//	public BaseException(String messageKey,Throwable causeThrowable) {
//		super(messageKey ,causeThrowable);
//		messageCode=messageKey;
//		logger.error(StringUtil.cutString(getMessage(messageCode) + "\n" + StackTraceUtil.getStackTrace(causeThrowable), 2000));
//	}	
//
//	public String getAdminCode() {
//		return messageCode + ".admin";
//	}
//
//	public String getUserCode() {
//		return messageCode + ".user";
//	}
//
//	public String getMessageCode() {
//		return messageCode;
//	}
//
//	public void setMessageCode(String messageCode) {
//		this.messageCode = messageCode;
//	}
//	
//	public String getAdminMessage() {
//		if(args != null) {
//			return getMessage(getAdminCode(), args);
//		}else return getMessage(getAdminCode());
//	}
//
//	public String getUserMessage() {
//		if(args != null)
//		{
//			return getMessage(getUserCode(), args);
//		}else
//			return getMessage(getUserCode());
//	}
//	
//	public String getDefaultMessage() {
//		if(args != null){
//			return getMessage(messageCode, args);
//		}
//		else
//			return getMessage(messageCode);
//	}
//
//
//
//	public Object[] getArgs() {
//		return args;
//	}
//
//
//
//	public void setArgs(Object[] args) {
//		this.args = args;
//	}
		
}
