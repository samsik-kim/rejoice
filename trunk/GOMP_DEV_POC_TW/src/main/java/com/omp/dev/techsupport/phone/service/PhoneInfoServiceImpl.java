package com.omp.dev.techsupport.phone.service;

import java.util.List;

import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;
import com.omp.dev.techsupport.phone.model.PhoneInfo;

public class PhoneInfoServiceImpl 
	extends AbstractService implements PhoneInfoService {

	@SuppressWarnings("unchecked")
	@Override
	public List<PhoneInfo> getPhoneInfoList(PhoneInfo phoneInfo) {
		List<PhoneInfo> resultList = null;
		
		try {
			resultList = this.commonDAO.queryForPageList("phone.phoneInfoList", phoneInfo);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException("getPhoneInfoList");
		}
		
		return resultList;
	}

}
