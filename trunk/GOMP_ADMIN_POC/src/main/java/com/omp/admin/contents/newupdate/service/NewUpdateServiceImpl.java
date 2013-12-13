package com.omp.admin.contents.newupdate.service;

import java.util.List;

import com.omp.admin.contents.adminrec.model.AdminRecommend;
import com.omp.admin.contents.adminrec.model.AdminRecommendParam;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;

public class NewUpdateServiceImpl extends AbstractService implements NewUpdateService {

	@Override
	public List<AdminRecommend> selectNewUpdateList(AdminRecommendParam param) {
		// TODO Auto-generated method stub
		List<AdminRecommend> result = null;

		try {
			this.setStep("SelectNewUpdateList");
			result = super.commonDAO.queryForPageList("Contents.selectNewUpdateList", param);
		} catch (Exception e) {
			throw new ServiceException("신규관리 리스트를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return result;
	}


	@Override
	public void updateNewUpdateList(AdminRecommendParam param,
			String[] upProdId, String[] expoYn, String[] expoPrior) {
		// TODO Auto-generated method stub
		try {
			super.daoManager.startTransaction();
			this.setStep("UpdateNewUpdateList");
			for (int i = 0; i < upProdId.length; i++) {
				if (log.isDebugEnabled())
					log.debug("upProdId[" + i + "] : " + upProdId[i] + ", expoYn[" + i + "] : " + upProdId[i] + ", expoPrior[" + i + "] : " + expoPrior[i]);
				
				param.setCheckProdId(upProdId[i]);
				param.setExpoYn(expoYn[i]);
				param.setExpoPrior(expoPrior[i]);
				
				super.commonDAO.update("Contents.updateNewUpdateList", param);
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
	public void deleteNewUpdateList(String selectedProdId,
			AdminRecommendParam param) {
		// TODO Auto-generated method stub
		try {
			super.daoManager.startTransaction();
			
			if (selectedProdId.indexOf(',') > 0) {
				this.setStep("DeleteNewUpdateLists");
				String[] arrSelectedProdId = selectedProdId.split(",");
				for (int i = 0; i < arrSelectedProdId.length; i++) {

					param.setCheckProdId(arrSelectedProdId[i]);
					
					super.commonDAO.insert("Contents.deleteNewUpdateList", param);
				}
			} else {

				if (!"".equals(selectedProdId)) {
					param.setCheckProdId(selectedProdId);
				}
				this.setStep("DeleteNewUpdateList");
				super.commonDAO.insert("Contents.deleteNewUpdateList", param);
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