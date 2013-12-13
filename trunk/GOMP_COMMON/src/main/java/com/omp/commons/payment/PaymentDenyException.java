package com.omp.commons.payment;

import com.omp.commons.exception.NoticeException;

/**
 * 결재 거절 되었음을 알리는 예외
 * @author pat
 */
@SuppressWarnings("serial")
public class PaymentDenyException
	extends NoticeException {
	
	protected String venderCode;
	protected String venderMessage;

	public PaymentDenyException(String msgSrc, String venderCode) {
		super("{0} (vender code {1})", new Object[] {msgSrc, venderCode});
		this.venderMessage	= msgSrc;
		this.venderCode		= venderCode;
	}

	/**
	 * 카드사에서 응답한 오류 코드
	 * @param venderCode
	 */
	public String getVenderCode() {
		return this.venderCode;
	}
	
	/**
	 * 카드사에서 응답한 오류 메세지
	 * @return
	 */
	public String getVenderMessage() {
		return this.venderMessage;
	}
}
