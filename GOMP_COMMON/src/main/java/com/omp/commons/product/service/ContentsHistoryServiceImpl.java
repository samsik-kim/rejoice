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

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.omp.commons.service.AbstractService;
import com.omp.commons.util.DateUtil;

/**
 * Contents History Implement
 * 
 * @author bcpark
 * @version 0.1
 */
public class ContentsHistoryServiceImpl extends AbstractService implements ContentsHistoryService {

	/*
	 * (non-Javadoc)
	 * @see com.omp.commons.product.service.ContentsHistoryService#insertContsHis (java.lang.String)
	 */
	@Override
	public boolean insertContsHis(String cid) {
		if (log.isDebugEnabled()) {
			log.debug("insertSprtPhoneHis START");
		}
		if (StringUtils.isEmpty(cid)) {
			log.warn("insertContsHis FAIL");
			return false;
		} else {
			boolean suc = false;
			try {
				Map<String, String> param = new HashMap<String, String>();
				param.put("cid", cid);
				param.put("his_dttm", DateUtil.getGeneralTimeStampString());

				daoManager.startTransaction();
				commonDAO.insert("ProductHistory.insertPdContsHis", param);
				daoManager.commitTransaction();
				if (log.isDebugEnabled()) {
					log.debug("insertContsHis SUCCESS");
				}
				suc = true;
			} catch (Exception e) {
				suc = false;
				log.warn("insertContsHis FAIL", e);
			} finally {
				daoManager.endTransaction();
			}
			return suc;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.commons.product.service.ContentsHistoryService#insertContsImgHis (java.lang.String)
	 */
	@Override
	public boolean insertContsImgHis(String cid) {
		if (log.isDebugEnabled()) {
			log.debug("insertContsImgHis START");
		}
		if (StringUtils.isEmpty(cid)) {
			log.warn("insertContsImgHis FAIL");
			return false;
		} else {
			boolean suc = false;
			try {
				Map<String, String> param = new HashMap<String, String>();
				param.put("cid", cid);
				param.put("his_dttm", DateUtil.getGeneralTimeStampString());

				daoManager.startTransaction();
				commonDAO.insert("ProductHistory.insertPdContsImgHis", param);
				daoManager.commitTransaction();
				if (log.isDebugEnabled()) {
					log.debug("insertContsImgHis SUCCESS");
				}
				suc = true;
			} catch (Exception e) {
				suc = false;
				log.warn("insertContsImgHis FAIL", e);
			} finally {
				daoManager.endTransaction();
			}
			return suc;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.commons.product.service.ContentsHistoryService#insertSubContsHis (java.lang.String)
	 */
	@Override
	public boolean insertSubContsHis(String scid) {
		if (log.isDebugEnabled()) {
			log.debug("insertSubContsHis START");
		}
		if (StringUtils.isEmpty(scid)) {
			log.warn("insertSubContsHis FAIL");
			return false;
		} else {
			boolean suc = false;
			try {
				Map<String, String> param = new HashMap<String, String>();
				param.put("scid", scid);
				param.put("his_dttm", DateUtil.getGeneralTimeStampString());

				daoManager.startTransaction();
				commonDAO.insert("ProductHistory.insertPdSubContsHis", param);
				daoManager.commitTransaction();
				if (log.isDebugEnabled()) {
					log.debug("insertSubContsHis SUCCESS");
				}
				suc = true;
			} catch (Exception e) {
				suc = false;
				log.warn("insertSubContsHis FAIL", e);
			} finally {
				daoManager.endTransaction();
			}
			return suc;

		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.commons.product.service.ContentsHistoryService#insertProvisionItemHis (java.lang.String)
	 */
	@Override
	public boolean insertProvisionItemHis(String scid) {
		if (log.isDebugEnabled()) {
			log.debug("insertProvisionItemHis START");
		}
		if (StringUtils.isEmpty(scid)) {
			log.warn("insertProvisionItemHis FAIL");
			return false;
		} else {
			boolean suc = false;
			try {
				Map<String, String> param = new HashMap<String, String>();
				param.put("scid", scid);
				param.put("his_dttm", DateUtil.getGeneralTimeStampString());

				daoManager.startTransaction();
				commonDAO.insert("ProductHistory.insertPdProvisionItemHis", param);
				daoManager.commitTransaction();
				if (log.isDebugEnabled()) {
					log.debug("insertProvisionItemHis SUCCESS");
				}
				suc = true;
			} catch (Exception e) {
				suc = false;
				log.warn("insertProvisionItemHis FAIL", e);
			} finally {
				daoManager.endTransaction();
			}
			return suc;

		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.commons.product.service.ContentsHistoryService#insertSprtPhoneHis (java.lang.String)
	 */
	@Override
	public boolean insertSprtPhoneHis(String scid) {
		if (log.isDebugEnabled()) {
			log.debug("insertSprtPhoneHis START");
		}
		if (StringUtils.isEmpty(scid)) {
			log.warn("insertSprtPhoneHis FAIL");
			return false;
		} else {
			boolean suc = false;
			try {
				Map<String, String> param = new HashMap<String, String>();
				param.put("scid", scid);
				param.put("his_dttm", DateUtil.getGeneralTimeStampString());

				daoManager.startTransaction();
				commonDAO.insert("ProductHistory.insertPdSprtPhoneHis", param);
				daoManager.commitTransaction();
				if (log.isDebugEnabled()) {
					log.debug("insertSprtPhoneHis SUCCESS");
				}
				suc = true;
			} catch (Exception e) {
				suc = false;
				log.warn("insertSprtPhoneHis FAIL", e);
			} finally {
				daoManager.endTransaction();
			}
			return suc;

		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.commons.product.service.ContentsHistoryService#insertMainContentsHis (java.lang.String)
	 */
	@Override
	public boolean insertMainContentsHis(String cid) {
		if (log.isDebugEnabled()) {
			log.debug("insertContsImgHis START");
		}
		if (StringUtils.isEmpty(cid)) {
			log.warn("insertContsImgHis FAIL");
			return false;
		} else {
			boolean suc = false;
			try {
				Map<String, String> param = new HashMap<String, String>();
				param.put("cid", cid);
				param.put("his_dttm", DateUtil.getGeneralTimeStampString());

				daoManager.startTransaction();
				// pd_conts
				commonDAO.insert("ProductHistory.insertPdContsHis", param);
				// pd_conts_img
				commonDAO.insert("ProductHistory.insertPdContsImgHis", param);
				daoManager.commitTransaction();
				if (log.isDebugEnabled()) {
					log.debug("insertContsImgHis SUCCESS");
				}
				suc = true;
			} catch (Exception e) {
				suc = false;
				log.warn("insertContsImgHis FAIL", e);
			} finally {
				daoManager.endTransaction();
			}
			return suc;

		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.commons.product.service.ContentsHistoryService#insertSubContsAllHis (java.lang.String)
	 */
	@Override
	public boolean insertSubContsAllHis(String scid) {
		if (log.isDebugEnabled()) {
			log.debug("insertSubContsAllHis START");
		}
		if (StringUtils.isEmpty(scid)) {
			log.warn("insertSubContsAllHis FAIL");
			return false;
		} else {
			boolean suc = false;
			try {
				Map<String, String> param = new HashMap<String, String>();
				param.put("scid", scid);
				param.put("his_dttm", DateUtil.getGeneralTimeStampString());

				daoManager.startTransaction();
				// pd_sub_conts
				commonDAO.insert("ProductHistory.insertPdSubContsHis", param);
				// pd_provision_item
				commonDAO.insert("ProductHistory.insertPdProvisionItemHis", param);
				// pd_sprt_phone
				commonDAO.insert("ProductHistory.insertPdSprtPhoneHis", param);
				daoManager.commitTransaction();
				if (log.isDebugEnabled()) {
					log.debug("insertSubContsAllHis SUCCESS");
				}
				suc = true;
			} catch (Exception e) {
				suc = false;
				log.warn("insertSubContsAllHis FAIL", e);
			} finally {
				daoManager.endTransaction();
			}
			return suc;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.commons.product.service.ContentsHistoryService#insertSaleStatHis (java.lang.String, java.lang.String)
	 */
	@Override
	public boolean insertSaleStatHis(String cid, String sale_stat, String ins_by, boolean isAdmin) {
		if (log.isDebugEnabled()) {
			log.debug("insertSaleStatHis START");
		}
		if (StringUtils.isEmpty(cid) || StringUtils.isEmpty(sale_stat) || StringUtils.isEmpty(ins_by)) {
			log.warn("insertSaleStatHis FAIL:cid={0}, sale_stat={1}, ins_by={2}", new Object[] {cid, sale_stat, ins_by});
			return false;
		} else {
			boolean suc = false;
			try {
				Map<String, String> param = new HashMap<String, String>();
				param.put("cid", cid);
				param.put("sale_stat", sale_stat);
				param.put("ins_by", ins_by);
				param.put("admin_chng_yn", isAdmin ? "Y" : "N");

				daoManager.startTransaction();
				commonDAO.insert("ProductHistory.insertSaleStatHis", param);
				daoManager.commitTransaction();
				if (log.isDebugEnabled()) {
					log.debug("insertSaleStatHis SUCCESS");
				}
				suc = true;
			} catch (Exception e) {
				suc = false;
				log.warn("insertSubContsAllHis FAIL");
				e.printStackTrace();
			} finally {
				daoManager.endTransaction();
			}
			return suc;
		}
	}

}
