package com.omp.commons.communicate.mail.impl;

import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;

/**
 * 메일 발송 대행자 기본 구현체용 서비스
 * @author pat
 *
 */
public class DefaultMailSendAgentServiceImpl
	extends AbstractService {
	
	/**
	 * 메일 발송 테이블에 메일 발송 요청 자료를 인써트
	 * @param cem
	 * @return
	 */
	public Long insertMailSend(CommonEmailModel cem) {
		try {
			return (Long)this.commonDAO.insert("Communicate.insertRequestMainSend", cem);
		} catch (Exception e) {
			throw new ServiceException("메일 발송 의뢰 테이블에 데이터를 추가하는데 실패 했습니다.", e);
		}
	}
}
