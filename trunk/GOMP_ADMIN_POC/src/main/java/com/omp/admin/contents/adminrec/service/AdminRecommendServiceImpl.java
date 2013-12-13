package com.omp.admin.contents.adminrec.service;

import java.util.List;

import com.omp.admin.contents.adminrec.model.AdminRecommend;
import com.omp.admin.contents.adminrec.model.AdminRecommendParam;
import com.omp.admin.contents.adminrec.model.DpCategoryList;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;

public class AdminRecommendServiceImpl extends AbstractService implements AdminRecommendService {

	@Override
	public List<DpCategoryList> getDpCategoryList(AdminRecommendParam param) {
		// TODO Auto-generated method stub
		List<DpCategoryList> result = null;

		try {
			this.setStep("GetDpCategoryList");
			result = super.commonDAO.queryForList("Contents.selectAdminCategoryList", param);
		} catch (Exception e) {
			throw new ServiceException("추천 카테고리 리스트를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return result;
	}

	@Override
	public List<AdminRecommend> selectAdminRecommendList(AdminRecommendParam param) {
		// TODO Auto-generated method stub
		List<AdminRecommend> result = null;

		try {
			this.setStep("SelectAdminRecommendList");
			result = super.commonDAO.queryForPageList("Contents.selectAdminRecommendList", param);
		} catch (Exception e) {
			throw new ServiceException("추천 리스트를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return result;
	}

	@Override
	public List<AdminRecommend> popAdminProdList(AdminRecommendParam param) {
		// TODO Auto-generated method stub
		List<AdminRecommend> result = null;

		try {
			this.setStep("PopAdminProdList");
			result = super.commonDAO.queryForPageList("Contents.popAdminProdList", param);
		} catch (Exception e) {
			throw new ServiceException("상품리스트를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return result;
	}

	@Override
	public AdminRecommend popAdminProdDetail(String prodId) {
		// TODO Auto-generated method stub
		AdminRecommend result = null;

		try {
			this.setStep("PopAdminProdDetail");
			result = (AdminRecommend) super.commonDAO.queryForObject("Contents.popAdminProdDetail", prodId);
		} catch (Exception e) {
			throw new ServiceException("상품정보를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return result;
	}

	@Override
	public void insertAdminRecommendProd(String selectedProdId,
			AdminRecommendParam param) {
		// TODO Auto-generated method stub

		try {
			super.daoManager.startTransaction();
			
			if (selectedProdId.indexOf(',') > 0) {

				String[] arrSelectedProdId = selectedProdId.split(",");
				this.setStep("InsertAdminRecommendProds");
				for (int i = 0; i < arrSelectedProdId.length; i++) {

					param.setInProdId(arrSelectedProdId[i]);
					
					int chk = (Integer) super.commonDAO.queryForObject("Contents.insertAdminRecommendProdChk", param);

					if(chk == 0){
						if (log.isDebugEnabled())
							log.debug(param.toString());
	
						super.commonDAO.insert("Contents.insertAdminRecommendProd", param);
					}
				}
			} else {

				if (!"".equals(selectedProdId)) {
					param.setInProdId(selectedProdId);
				}
				this.setStep("InsertAdminRecommendProd");
				int chk = (Integer) super.commonDAO.queryForObject("Contents.insertAdminRecommendProdChk", param);

				if(chk == 0){
					if (log.isDebugEnabled())
						log.debug(param.toString());

					super.commonDAO.insert("Contents.insertAdminRecommendProd", param);
				}
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
	public void updateAdminRecommendList(AdminRecommendParam param,
			String[] upProdId, String[] expoYn, String[] expoPrior) {
		// TODO Auto-generated method stub
		try {
			super.daoManager.startTransaction();
			this.setStep("UpdateAdminRecommendList");
			for (int i = 0; i < upProdId.length; i++) {
				if (log.isDebugEnabled())
					log.debug("upProdId[" + i + "] : " + upProdId[i] + ", expoYn[" + i + "] : " + upProdId[i] + ", expoPrior[" + i + "] : " + expoPrior[i]);
				
				param.setCheckProdId(upProdId[i]);
				param.setExpoYn(expoYn[i]);
				param.setExpoPrior(expoPrior[i]);
				
				super.commonDAO.update("Contents.updateAdminRecommendList", param);
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
	public void deleteAdminRecommendList(String selectedProdId,
			AdminRecommendParam param) {
		// TODO Auto-generated method stub
		try {
			super.daoManager.startTransaction();
			
			if (selectedProdId.indexOf(',') > 0) {
				this.setStep("DeleteAdminRecommendLists");
				String[] arrSelectedProdId = selectedProdId.split(",");
				for (int i = 0; i < arrSelectedProdId.length; i++) {

					param.setCheckProdId(arrSelectedProdId[i]);
					
					super.commonDAO.insert("Contents.deleteAdminRecommendList", param);
				}
			} else {

				if (!"".equals(selectedProdId)) {
					param.setCheckProdId(selectedProdId);
				}
				this.setStep("DeleteAdminRecommendList");
				super.commonDAO.insert("Contents.deleteAdminRecommendList", param);
			}

			super.daoManager.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		} finally {
			super.daoManager.endTransaction();
		}
	}


}