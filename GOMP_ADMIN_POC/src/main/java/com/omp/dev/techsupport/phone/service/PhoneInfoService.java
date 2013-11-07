package com.omp.dev.techsupport.phone.service;

import java.util.List;

import com.omp.dev.techsupport.phone.model.PhoneInfo;

/**
 * Phone Info Service Interface
 * @author Administrator
 *
 */
public interface PhoneInfoService {

	/**
	 * Get Phone Info List
	 * @param phoneInfo
	 * @return
	 */
	public List<PhoneInfo> getPhoneInfoList(PhoneInfo phoneInfo);
}
