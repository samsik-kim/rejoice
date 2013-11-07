package com.omp.admin.contents.best.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.omp.admin.common.util.CommonUtil;
import com.omp.admin.contents.adminrec.model.AdminRecommend;
import com.omp.admin.contents.adminrec.model.DpCategoryList;
import com.omp.admin.contents.best.model.BestParam;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;

public class ChargeServiceImpl extends AbstractService implements ChargeService {

	@Override
	public List<AdminRecommend> selectChargeBestList(BestParam param) {
		// TODO Auto-generated method stub
		List<AdminRecommend> result = null;

		try {
			
//			int chk = (Integer) super.commonDAO.queryForObject("Contents.insertBestPayProdChk", param);
//			if(chk == 0){
//				//super.commonDAO.insert("Contents.insertBestPayProd", param);
//				insertDailyContentsBest();
//			}
//			insertDailyContentsBest();
			this.setStep("SelectChargeBestList");
			result = super.commonDAO.queryForPageList("Contents.selectChargeBestList", param);
		} catch (Exception e) {
			throw new ServiceException("유료BEST 리스트를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return result;
	}


	@Override
	public void updateChargeBestList(BestParam param,
			String[] upProdId, String[] expoYn, String[] expoPrior) {
		// TODO Auto-generated method stub
		try {
			super.daoManager.startTransaction();
			this.setStep("UpdateChargeBestList");
			for (int i = 0; i < upProdId.length; i++) {
				if (log.isDebugEnabled())
					log.debug("upProdId[" + i + "] : " + upProdId[i] + ", expoYn[" + i + "] : " + upProdId[i] + ", expoPrior[" + i + "] : " + expoPrior[i]);
				
				param.setCheckProdId(upProdId[i]);
				param.setExpoYn(expoYn[i]);
				param.setExpoPrior(expoPrior[i]);
				
				super.commonDAO.update("Contents.updateChargeBestList", param);
			}

			super.daoManager.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		} finally {
			super.daoManager.endTransaction();
		}
	}

	@Override
	public void deleteChargeBestList(String selectedProdId,
			BestParam param) {
		// TODO Auto-generated method stub
		try {
			super.daoManager.startTransaction();
			
			if (selectedProdId.indexOf(',') > 0) {
				this.setStep("DeleteChargeBestLists");
				String[] arrSelectedProdId = selectedProdId.split(",");
				for (int i = 0; i < arrSelectedProdId.length; i++) {

					param.setCheckProdId(arrSelectedProdId[i]);
					
					super.commonDAO.insert("Contents.deleteChargeBestList", param);
				}
			} else {

				if (!"".equals(selectedProdId)) {
					param.setCheckProdId(selectedProdId);
				}
				this.setStep("DeleteChargeBestList");
				super.commonDAO.insert("Contents.deleteChargeBestList", param);
			}

			super.daoManager.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		} finally {
			super.daoManager.endTransaction();
		}
	}


	@Override
	public List<DpCategoryList> getDpCategoryList(BestParam param) {
		// TODO Auto-generated method stub
		List<DpCategoryList> result = null;

		try {
			this.setStep("GetDpCategoryList");
			result = super.commonDAO.queryForList("Contents.selectAdminCategoryList2", param);
		} catch (Exception e) {
			throw new ServiceException("Best 카테고리 리스트를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return result;
	}


	@Override
	public void insertChargeBestDefault(BestParam param) {
		// TODO Auto-generated method stub
		try {
			super.daoManager.startTransaction();
			
			//기존데이터삭제
			super.commonDAO.insert("Contents.deleteChargeBestDefaultPay", param);
			
			//전일판매된 상품갯수
 			int payChkCnt = (Integer) super.commonDAO.queryForObject("Contents.selectChargeBestDefaultPayChkCnt", param);
			
			if(payChkCnt != 0){
				this.setStep("InsertChargeBestDefaultPay");
				//판매된상품 저장
				super.commonDAO.insert("Contents.insertChargeBestDefaultPay", param);
			}
			
			int payTotCnt = 150 - payChkCnt;
			
			if(payTotCnt > 0){
				
				param.setPayChkCnt(payChkCnt+"");
				param.setPayTotCnt(payTotCnt+"");
				
				//판매상품이 150개가 안될때 나머지는 신규상품 저장
				super.commonDAO.insert("Contents.insertChargeBestDefaultPay2", param);
			}

			super.daoManager.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		} finally {
			super.daoManager.endTransaction();
		}
	}

	/**
	 * Contents Best Daily Interface 
	 * @param runDate
	 * @throws Exception
	 */
	public void insertDailyContentsBest() throws Exception {

		List<DpCategoryList> result = null;
		BestParam param = new BestParam();
		
		
		try{
			
			//카테고리 리스트를 가져온다.
			param.setDpUpCatNo("DP01");
			result = getDpCategoryList(param);
			
			if(result != null){

				for (int i = 0; i < result.size(); i++) {

					param.setDpCatNo(result.get(i).getDpCatNo());
					param.setRegId("System");
					param.setUpdId("System");
					
					//유료베스트
					super.daoManager.startTransaction();
					
					//기존데이터삭제
					super.commonDAO.insert("Contents.deleteChargeBestDefaultPay", param);
					
					//전일판매된 상품갯수
		 			int payChkCnt = (Integer) super.commonDAO.queryForObject("Contents.selectChargeBestDefaultPayChkCnt", param);
					
					if(payChkCnt != 0){
						this.setStep("InsertChargeBestDefaultPay");
						//판매된상품 저장
						super.commonDAO.insert("Contents.insertChargeBestDefaultPay", param);
					}
					
					int payTotCnt = 150 - payChkCnt;
					
					if(payTotCnt > 0){
						
						param.setPayChkCnt(payChkCnt+"");
						param.setPayTotCnt(payTotCnt+"");
						
						//판매상품이 150개가 안될때 나머지는 신규상품 저장
						super.commonDAO.insert("Contents.insertChargeBestDefaultPay2", param);
					}

					super.daoManager.commitTransaction();
					
					
					//무료베스트
					super.daoManager.startTransaction();
					
					//기존데이터삭제
					super.commonDAO.insert("Contents.deleteFreeBestDefaultPay", param);
					
					//전일판매된 상품갯수
		 			payChkCnt = (Integer) super.commonDAO.queryForObject("Contents.selectFreeBestDefaultPayChkCnt", param);
					
					if(payChkCnt != 0){
						this.setStep("InsertFreeBestDefaultFree");
						//판매된상품 저장
						super.commonDAO.insert("Contents.insertFreeBestDefaultPay", param);
					}
					
					payTotCnt = 150 - payChkCnt;
					
					if(payTotCnt > 0){
						
						param.setPayChkCnt(payChkCnt+"");
						param.setPayTotCnt(payTotCnt+"");
						
						//판매상품이 150개가 안될때 나머지는 신규상품 저장
						super.commonDAO.insert("Contents.insertFreeBestDefaultPay2", param);
					}

					super.daoManager.commitTransaction();
				}
			}
			
			
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		} finally {
			
		}  

	}
}