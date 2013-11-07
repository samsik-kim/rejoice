/*
 * COPYRIGHT(c) SK telecom 2011
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author | Date        | Description
 * ---------------------------------------
 * bcpark | 2011. 3. 30. | Description
 *
 */
package com.omp.admin.phonemapping.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.omp.admin.phonemapping.model.PhoneMappingParam;
import com.omp.admin.phonemapping.model.PhoneRemMgr;
import com.omp.admin.phonemapping.model.PhoneRemScid;
import com.omp.admin.phonemapping.model.SearchParam;
import com.omp.admin.product.model.Contents;
import com.omp.commons.product.model.phone.ack2.PhoneSecondAck;
import com.omp.commons.product.service.DistributeException;

/**
 * TODO class description </p> class detail description
 * 
 * @author Administrator
 * @version 0.1
 */
public interface PhoneMappingService {

	/**
	 * @param phone_model_cd
	 * @return
	 */
	File getContentsExcelByTargetDevice(String phone_model_cd);

	/**
	 * @param searchDevice
	 * @return
	 */
	Map<String, String> isValidDevice(String searchDevice);

	/**
	 * @param param
	 * @return
	 */
	boolean deviceAddMapping(PhoneMappingParam param) throws DistributeException;

	/**
	 * @param param
	 */
	boolean deviceDelMapping(PhoneMappingParam param) throws DistributeException;

	/**
	 * @param phoneSecondAck
	 */
	void executePhoneSecondAck(PhoneSecondAck phoneSecondAck);

	/**
	 * @param param
	 * @return
	 * @throws DistributeException
	 */
	Map<String, String> deviceAddMappingExcel(PhoneMappingParam param) throws DistributeException;

	/**
	 * @param param
	 * @return
	 * @throws DistributeException
	 */
	Map<String, String> deviceDelMappingExcel(PhoneMappingParam param) throws DistributeException;

	/**
	 * @param searchParam
	 * @return
	 */
	List<PhoneRemMgr> selectPhoneRemMgrList(SearchParam searchParam);

	/**
	 * @param txId
	 * @return
	 */
	PhoneRemMgr getPhoneRemMgr(String txId);

	/**
	 * @param searchParam
	 * @return
	 */
	List<PhoneRemScid> selectPhoneRemScidList(SearchParam searchParam);

	/**
	 * @param searchParam
	 * @return
	 */
	File getDetailExcel(SearchParam searchParam);
	
	/**
	 * @param searchParam
	 * @return
	 */
	List<Contents> getDPContentInfoList(SearchParam searchParam);

}
