package com.omp.dev.contents.service;

import java.util.List;

import com.omp.commons.exception.ServiceException;
import com.omp.dev.contents.model.TestPhoneSet;

public interface TestPhoneSetService {
	/**
	 * Test Phone List
	 * 
	 * @param mbrNo
	 * @return
	 * @throws ServiceException
	 */
	public List<TestPhoneSet> getTestPhoneList(String mbrNo);
	
	/**
	 * Test Phone Delete
	 * 
	 * @param TestPhoneSet
	 * @return
	 * @throws ServiceException
	 */
	public void delTestPhone(TestPhoneSet testPhSet);
	
	/**
	 * Test Phone Insert
	 * 
	 * @param String[],String[],String
	 * @return
	 * @throws ServiceException
	 */
	public void insertTestPhone(String[] seqArry,String[] macArry,String mbrNo);
}
