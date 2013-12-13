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

/**
 * 전시 배포 서비스 인터페이스
 * 
 * @author Administrator
 * @version 0.1
 */
public interface DisplayDistributeService {

	/**
	 * Deploy Contents To DP(DISPLAY) TABLE<br/>
	 * If isMainConts is true then Deploy Main Contents only[TBL_DP_PORD, TBL_DP_TAG_INFO, TBL_DP_CAT_PROD, TBL_DP_PROD_IMG]<br/>
	 * Find verify_req_ver AT TBL_PD_CONTS (verify_prgr_yn is finally confirm : PD005002)<br/>
	 * 
	 * @param cid product cid
	 * @return
	 * @throws DistributeException
	 */
	public boolean dpDeployContents(String cid, boolean isMainConts) throws DistributeException;

	/**
	 * @param cid
	 * @return
	 * @throws DistributeException
	 */
	boolean stopSaleContents(String cid) throws DistributeException;

	/**
	 * @param cid
	 * @return
	 * @throws DistributeException
	 */
	boolean restricSaleContents(String cid) throws DistributeException;

}
