package com.omp.commons.payment;


import com.omp.commons.exception.LocalizeMessageException;

/**
 * 결재관련작업 실패 
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class PaymentFailException
	extends LocalizeMessageException {
	
	protected boolean approvaled;

	public PaymentFailException() {
		super();
	}

	public PaymentFailException(String msgSrc, Object[] msgArgs, Throwable cause) {
		super(msgSrc, msgArgs, cause);
	}

	public PaymentFailException(String msgSrc, Object[] msgArgs) {
		super(msgSrc, msgArgs);
	}

	public PaymentFailException(String msgSrc, Throwable cause) {
		super(msgSrc, cause);
	}

	public PaymentFailException(String msgSrc) {
		super(msgSrc);
	}

	public PaymentFailException(Throwable cause) {
		super(cause);
	}

	public PaymentFailException(boolean approvaled) {
		super();
		this.approvaled	= approvaled;
	}

	public PaymentFailException(String msgSrc, Object[] msgArgs, Throwable cause, boolean approvaled) {
		super(msgSrc, msgArgs, cause);
		this.approvaled	= approvaled;
	}

	public PaymentFailException(String msgSrc, Object[] msgArgs, boolean approvaled) {
		super(msgSrc, msgArgs);
		this.approvaled	= approvaled;
	}

	public PaymentFailException(String msgSrc, Throwable cause, boolean approvaled) {
		super(msgSrc, cause);
		this.approvaled	= approvaled;
	}

	public PaymentFailException(String msgSrc, boolean approvaled) {
		super(msgSrc);
		this.approvaled	= approvaled;
	}

	public PaymentFailException(Throwable cause, boolean approvaled) {
		super(cause);
		this.approvaled	= approvaled;
	}
	
	/**
	 * 이미 승인 처리 되었는지 여부
	 * @return true라면 승인 처리되고 난 이후에 발생한 오류이므로, 오류 처리시 이미 완료된 승인에 대한 처리가 함께 이루어 져야 합니다.
	 */
	public boolean isApprovaled() {
		return approvaled;
	}
}
