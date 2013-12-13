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

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.omp.commons.Constants;
import com.omp.commons.exception.InvalidInputException;
import com.omp.commons.exception.NoticeException;
import com.omp.commons.service.AbstractService;
import com.omp.commons.util.ThreadSession;
import com.omp.commons.util.config.ConfigProperties;

/**
 * 전시 배포 서비스 구현체
 * 
 * @author Administrator
 * @version 0.1
 */
public class DisplayDistributeServiceImpl extends AbstractService implements DisplayDistributeService {
	// 배포 전용 Logger (지역화 메세지를 타지 않음)
	final Log dLogger = LogFactory.getLog(DisplayDistributeServiceImpl.class);
	
	private Log	onmDeployLog	= LogFactory.getLog("omp.onm.admin.appdisp");

	/*
	 * (non-Javadoc)
	 * @see com.omp.commons.product.service.DisplayDistributeService#dpDeployMainContents (java.lang.String, boolean)
	 */
	@Override
	public boolean dpDeployContents(String cid, boolean isMainConts) throws DistributeException {
		long	startTm;
		String	rcode;
		
		// OMC 키값 : cid
		startTm	= System.currentTimeMillis();
		
		this.setStep("StartDisplayDeploy");
		
		rcode	= "00";
		try {
			if (dLogger.isInfoEnabled()) {
				dLogger.info("==== START Display Deploy ====");
			}
	
			String verify_req_ver = "";
			if (StringUtils.isEmpty(cid)) {
				dLogger.error("Display Deploy FAIL cid is Empty");
				throw new DistributeException("디스플레이 배포 실패 : 컨텐츠 정보가 없습니다.");
			} else {
	
				this.setStep("GetVerifyReqVer");
				Object reqVerObj = null;
				// ============ 1. Find verifyReqVer ============
				try {
					reqVerObj = commonDAO.queryForObject("DisplayDeploy.findRightVerifyReqVer", cid);
				} catch (SQLException e) {
					throw new DistributeException("디스플레이 배포 실패 : SQLException", e);
				}
				if (reqVerObj == null || StringUtils.isEmpty((String) reqVerObj)) {
					throw new DistributeException("디스플레이 배포 실패 : 올바른 검증버전이 없습니다.");
				}
				verify_req_ver = (String) reqVerObj;
				ConfigProperties conf = new ConfigProperties();
	
				try {
					Map<String, String> param = new HashMap<String, String>();
					param.put("cid", cid);
					param.put("verify_req_ver", verify_req_ver);
					param.put("isMainConts", "" + isMainConts);
	
					// daoManager.startTransaction();
					// ============ 2. Deploy Main Contents ============
					// 2.1 TBL_DP_PROD
					this.setStep("DepolyTBL_DP_PROD");
					String prod_id = (String) commonDAO.queryForObject("DisplayDeploy.checkDpProd", param);
					if (StringUtils.isBlank(prod_id)) {
						// DP Insert
						commonDAO.insert("DisplayDeploy.insertDpProd", param);
					} else {
						// DP Update
						commonDAO.update("DisplayDeploy.updateDpProd", param);
					}
					// 2.2 TBL_DP_CAT_PROD
					this.setStep("DepolyTBL_DP_CAT_PROD");
					commonDAO.delete("DisplayDeploy.deleteDpCatProd", param);
					commonDAO.insert("DisplayDeploy.insertDpCatProd", param);
	
					// 2.3 TBL_DP_TAG_INFO
					this.setStep("DepolyTBL_DP_TAG_INFO");
					commonDAO.delete("DisplayDeploy.deleteDpTagInfo", param);
					commonDAO.insert("DisplayDeploy.insertDpTagInfo", param);
	
					// 2.4 TBL_DP_PROD_IMG
					this.setStep("DepolyTBL_DP_PROD_IMG");
					commonDAO.delete("DisplayDeploy.deleteDpProdImg", param);
					commonDAO.insert("DisplayDeploy.insertDpProdImg", param);
	
					// 2.5 TBL_DP_NEWFREE (카테고리 및 pay_yn 업데이트, 업데이트일자는 FULL 배포인 경우만 업데이트한다.)
					this.setStep("DepolyTBL_DP_NEWFREE");
					String beforeCategory = (String) commonDAO.queryForObject("DisplayDeploy.checkNewfree", param);
					if (StringUtils.isBlank(beforeCategory))
						commonDAO.insert("DisplayDeploy.insertNewfree", param);
					else {
						param.put("beforeCategory", beforeCategory);
						commonDAO.update("DisplayDeploy.updateNewfree", param);
					}
					// 2.6 TBL_DP_NEWFREE (sc MAIN : DP01)
					this.setStep("DepolyTBL_DP_NEWFREE (sc MAIN : DP01)");
					beforeCategory = (String) commonDAO.queryForObject("DisplayDeploy.checkNewfreeMain", param);
					if (StringUtils.isBlank(beforeCategory))
						commonDAO.insert("DisplayDeploy.insertNewfreeMain", param);
					else {
						param.put("beforeCategory", beforeCategory);
						commonDAO.update("DisplayDeploy.updateNewfreeMain", param);
					}
	
					// ============ 3. Deploy Sub Contents(FULL 배포) ============
					if (!isMainConts) {
						this.setStep("DepolyTBL_DP_SPRT_HP, TBL_DP_SUB_CONTS");
						// 3.1 TBL_DP_SPRT_HP, TBL_DP_SUB_CONTS
						commonDAO.delete("DisplayDeploy.deleteDpSprtHp", param);
						commonDAO.delete("DisplayDeploy.deleteDpSubConts", param);
	
						commonDAO.insert("DisplayDeploy.insertDpSubConts", param);
						commonDAO.insert("DisplayDeploy.insertDpSprtHp", param);
	
						// 3.2 APP_UPDATE (해당 상품을 다운로드 받은 사람들이 업데이트 받을 수 있도록 함)
						String updApplyDivisionCd = (String) commonDAO.queryForObject("DisplayDeploy.selectPdUpdApplyDivisionCd", param);
						// 업데이트 standby 인 경우만,
						if (Constants.CONTENT_UPGRADE_STAT_STANDBY.equals(updApplyDivisionCd)) {
							this.setStep("DepolyAPP_UPDATE");
							commonDAO.update("DisplayDeploy.updateAppUpdate", param);
							param.put("upd_apply_division_cd", Constants.CONTENT_UPGRADE_STAT_COMPLETE);
							commonDAO.update("DisplayDeploy.updatePdUpdApplyDivisionCd", param);
						}
	
						// FULL 배포시에 아래 테이블의 값이 있는체 체크하고, 값이 없으면 INSERT
						// 앞단 로직과 상관없이(어떻게 변경되더라도) FULL 배포시 아래 테이블에 대한 검사가 빠지면 안됨.
	
						// 3.3 TBL_DP_PROD_PRCHS
						this.setStep("DepolyTBL_DP_PROD_PRCHS");
						prod_id = (String) commonDAO.queryForObject("DisplayDeploy.checkProdPrchs", param);
						if (StringUtils.isBlank(prod_id))
							commonDAO.insert("DisplayDeploy.insertProdPrchs", param);
	
						// 3.4 TBL_DP_PROD_DWLD
						this.setStep("DepolyTBL_DP_PROD_DWLD");
						prod_id = (String) commonDAO.queryForObject("DisplayDeploy.checkProdDwld", param);
						if (StringUtils.isBlank(prod_id))
							commonDAO.insert("DisplayDeploy.insertProdDwld", param);
	
						// 3.5 TBL_DP_PROD_AVG
						this.setStep("DepolyTBL_DP_PROD_AVG");
						prod_id = (String) commonDAO.queryForObject("DisplayDeploy.checkProdAvg", param);
						if (StringUtils.isBlank(prod_id))
							commonDAO.insert("DisplayDeploy.insertProdAvg", param);
	
						// 3.6 정산율 정보 (TBL_PROD_SETTL)
						this.setStep("DepolyTBL_PROD_SETTL");
						prod_id = (String) commonDAO.queryForObject("DisplayDeploy.checkProdSettl", param);
						if (StringUtils.isBlank(prod_id))
							commonDAO.insert("DisplayDeploy.insertProdSettl", param);
					}
					// daoManager.commitTransaction();
					if (dLogger.isInfoEnabled()) {
						dLogger.info("==== Display Deploy  SUCCESS ====");
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new DistributeException("디스플레이 배포 실패 : Exception", e);
				}
				//finally {
				//	daoManager.endTransaction();
				//}
	
				return true;
			}
		} catch (DistributeException e) {
			rcode	= "99";
			throw e;
		} finally {
			StringBuffer 	sb;
			
			sb	= new StringBuffer();
			sb.append("^").append(System.currentTimeMillis() - startTm)
				.append("^").append(rcode)
				.append("^").append(ThreadSession.getSession().getServiceStep())
				.append("^").append("CID=").append(cid);
			onmDeployLog.info(sb.toString());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.commons.product.service.DisplayDistributeService#stopSaleContents(java.lang.String)
	 */
	@Override
	public boolean stopSaleContents(String cid) throws DistributeException {
		boolean suc = false;

		if (StringUtils.isEmpty(cid)) {
			dLogger.error("stopSaleContents FAIL cid is Empty");
			throw new DistributeException("stopSaleContents FAIL : cid is empty");
		} else {
			try {
				Map<String, String> param = new HashMap<String, String>();
				param.put("cid", cid);
				param.put("saleStat", Constants.CONTENT_SALE_STAT_STOP);
				param.put("dpYn", "N");

				// daoManager.startTransaction();
				int updCnt = commonDAO.update("DisplayDeploy.updateDpProdSaleStat", param);
				commonDAO.update("DisplayDeploy.updateDpCatProdDpYn", param);
				if (updCnt > 0) {
					suc = true;
					if (dLogger.isInfoEnabled()) {
						dLogger.info("stopSaleContents SUCCESS");
					}
					// daoManager.commitTransaction();
				} else {
					suc = false;
					if (dLogger.isInfoEnabled()) {
						dLogger.info("stopSaleContents FALSE");
					}
				}
			} catch (Exception e) {
				suc = false;
				e.printStackTrace();
				throw new DistributeException("상품 상태 판매중지 변경 실패 : Exception", e);
			}
			//finally {
			//	daoManager.endTransaction();
			//}
			return suc;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.commons.product.service.DisplayDistributeService#stopSaleContents(java.lang.String)
	 */
	@Override
	public boolean restricSaleContents(String cid) throws DistributeException {
		boolean suc = false;

		if (StringUtils.isEmpty(cid)) {
			dLogger.error("stopSaleContents FAIL cid is Empty");
			throw new DistributeException("stopSaleContents FAIL : cid is empty");
		} else {
			try {
				Map<String, String> param = new HashMap<String, String>();
				param.put("cid", cid);
				param.put("saleStat", Constants.CONTENT_SALE_STAT_RESTRIC);
				param.put("dpYn", "N");

				// daoManager.startTransaction();
				int updCnt = commonDAO.update("DisplayDeploy.updateDpProdSaleStat", param);
				commonDAO.update("DisplayDeploy.updateDpCatProdDpYn", param);
				if (updCnt > 0) {
					suc = true;
					if (dLogger.isInfoEnabled()) {
						dLogger.info("stopSaleContents SUCCESS");
					}
					// daoManager.commitTransaction();
				} else {
					suc = false;
					if (dLogger.isInfoEnabled()) {
						dLogger.info("stopSaleContents FALSE");
					}
				}
			} catch (Exception e) {
				suc = false;
				throw new DistributeException("상품 상태 판매금지 변경 실패 : Exception", e);
			}
			//finally {
			//	daoManager.endTransaction();
			//}
			return suc;
		}
	}
}
