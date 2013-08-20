package com.nmimo.common.exception;

public abstract class NMimoAbstractException extends RuntimeException implements NMimoErrorCoded {

	private static final long serialVersionUID = 1L;
	protected String errorMessageParameter;

	/**
	 * 
	 * <pre>
	 * NMimoAbstractException 디폴트 생성자
	 * </pre>
	 */
	public NMimoAbstractException(){		
		super();
	}
	
	/**
	 * 
	 * <pre>
	 * NMimoAbstractException 생성자
	 * </pre>
	 * @param throwable 예외 최상위 객체
	 */
	public NMimoAbstractException(Throwable throwable){
		super(throwable);
	}
	
	/**
	 * 
	 * <pre>
	 * NMimoAbstractException 문자열 변수를 받아 처리하는 생성자
	 * </pre>
	 * @param errorMessage 에러 메세지 변수
	 */
	public NMimoAbstractException(String errorMessage) {
		super(errorMessage);		
		this.errorMessageParameter = errorMessage;
	}
		
	/**
	 * [프로젝트]/error.message.properties 에
	 * 정의 되어 있는 메세지 문자열에 넘길 파라미터를 정의하여 반환
	 */
	public Object[] getErrorMessageElement() {
		return new Object[]{errorMessageParameter};
	}
}
