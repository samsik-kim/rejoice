package com.omp.dev.phonemodel.service;

import java.sql.SQLException;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;

public class PhoneModelDownService extends AbstractService{
	public String selectModelDown(String modelCd) {
		String result = null;
		try {
			result=(String) this.commonDAO.queryForObject("phModelDown.selectPhoneModel", modelCd);
		} catch (SQLException e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}
		return result;
	}
}
