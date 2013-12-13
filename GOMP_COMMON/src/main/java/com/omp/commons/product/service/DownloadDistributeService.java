/*
 * COPYRIGHT(c) SK telecom 2011
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author | Date        | Description
 * ---------------------------------------
 * bcpark | 2011. 3. 25. | Description
 *
 */
package com.omp.commons.product.service;

import com.omp.commons.product.model.phone.file.PhoneDomain;

/**
 * 다운로드 배포 서비스 인터페이스
 * 
 * @author Administrator
 * @version 0.1
 */
public interface DownloadDistributeService {

	/**
	 * Deploy Contents To DownloadServer<br/>
	 * <ol>
	 * <li>DEPLOY_PRODUCT_CONTENT</li>
	 * <li>DEPLOY_MAIN_CONTENT</li>
	 * <li>DEPLOY_SUB_CONTENT</li>
	 * </ol>
	 * If isMainConts is true then Deploy Product,Main Contents only<br/>
	 * Find verify_req_ver AT TBL_PD_CONTS (verify_prgr_yn is finally confirm status-PD005002)<br/>
	 * 
	 * @param cid product cid
	 * @param isMainConts
	 * @return
	 * @throws DistributeException
	 */
	public boolean ddDeployContents(String cid, boolean isMainConts) throws DistributeException;

	/**
	 * Object to XML String(Use JAXB)
	 * 
	 * @param obj
	 * @param objClass
	 * @return xml String
	 * @throws DistributeException
	 */
	@SuppressWarnings("rawtypes")
	String objToXmlString(Object obj, Class objClass) throws DistributeException;

	/**
	 * @param objClass
	 * @param xmlString
	 * @return
	 * @throws DistributeException
	 */
	Object xmlStringToObj(Class objClass, String xmlString) throws DistributeException;

	/**
	 * @param obj
	 * @param objClass
	 * @param fullPath
	 * @param fileName
	 * @throws DistributeException
	 */
	void objToXmlFile(Object obj, Class objClass, String fullPath, String fileName) throws DistributeException;

	/**
	 * phone mapping DD Distribute
	 * 
	 * @param phoneDomain
	 * @return
	 * @throws DistributeException
	 */
	boolean ddPhoneMapping(PhoneDomain phoneDomain) throws DistributeException;

}
