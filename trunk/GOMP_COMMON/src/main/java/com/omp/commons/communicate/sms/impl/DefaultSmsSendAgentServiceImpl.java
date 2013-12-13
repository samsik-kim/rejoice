package com.omp.commons.communicate.sms.impl;

import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;

/**
 * SMS 발송 대행자 기본 구현체에서 사용하는 서비스
 * @author pat
 *
 */
public class DefaultSmsSendAgentServiceImpl
	extends AbstractService {
	
	public Long insertSmsSend(CommonSmsModel csm)
		throws ServiceException {
		try {
			return (Long)this.commonDAO.insert("Communicate.insertRequestSmsSend", csm);
		} catch (Exception e) {
			throw new ServiceException("SMS 발송 의뢰 테이블에 데이터를 추가하는데 실패 했습니다.", e);
		}
	}

}
