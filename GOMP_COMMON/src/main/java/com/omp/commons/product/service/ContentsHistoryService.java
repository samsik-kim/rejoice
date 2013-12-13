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
 * Product History Service
 * 
 * @author Administrator
 * @version 0.1
 */
public interface ContentsHistoryService {
	/**
	 * Contents History Insert<br/>
	 * <ul>
	 * <li>TBL_PD_CONTS To TBL_PD_CONTS_HIS</li>
	 * </ul>
	 * @param cid
	 * @return
	 */
	public boolean insertContsHis(String cid);

	/**
	 * Contents Image History Insert<br/>
	 * <ul>
	 * <li>TBL_PD_CONTS_IMG To TBL_PD_CONTS_IMG_HIS</li>
	 * </ul>
	 * @param cid
	 * @return
	 */
	public boolean insertContsImgHis(String cid);

	/**
	 * Sub Contents History Insert<br/>
	 * <ul>
	 * <li>TBL_PD_SUB_CONTS To TBL_PD_SUB_CONTS_HIS</li>
	 * </ul>
	 * @param scid
	 * @return
	 */
	public boolean insertSubContsHis(String scid);

	/**
	 * Sub Contents Provision Item History Insert<br/>
	 * <ul>
	 * <li>TBL_PD_PROVISION_ITEM To TBL_PD_PROVISION_ITEM_HIS</li>
	 * </ul>
	 * @param scid
	 * @return
	 */
	public boolean insertProvisionItemHis(String scid);

	/**
	 * Sub Contents Support Phone History Insert<br/>
	 * <ul>
	 * <li>TBL_PD_SPRT_PHONE To TBL_PD_SPRT_PHONE_HIS</li>
	 * </ul>
	 * @param scid
	 * @return
	 */
	public boolean insertSprtPhoneHis(String scid);

	/**
	 * Main Contentents History Insert<br/>
	 * <ul>
	 * <li>TBL_PD_CONTS To TBL_PD_CONTS_HIS</li>
	 * <li>TBL_PD_SUB_CONTS To TBL_PD_SUB_CONTS_HIS</li>
	 * </ul>
	 * @param cid
	 * @return
	 */
	public boolean insertMainContentsHis(String cid);

	/**
	 * Sub Contents All History Insert<br/>
	 * <ul>
	 * <li>TBL_PD_CONTS_IMG To TBL_PD_CONTS_IMG_HIS</li>
	 * <li>TBL_PD_PROVISION_ITEM To TBL_PD_PROVISION_ITEM_HIS</li>
	 * <li>TBL_PD_SPRT_PHONE To TBL_PD_SPRT_PHONE_HIS</li>
	 * </ul>
	 * @param scid
	 * @return
	 */
	public boolean insertSubContsAllHis(String scid);

	/**
	 * Contents Sale Stat Change History Insert<br/>
	 * <ul>
	 * <li>TBL_PD_SALE_STAT_HIS</li>
	 * </ul>
	 * @param cid
	 * @param sale_stat
	 * @return
	 */
	public boolean insertSaleStatHis(String cid, String sale_stat, String ins_by, boolean isAdmin);

}
