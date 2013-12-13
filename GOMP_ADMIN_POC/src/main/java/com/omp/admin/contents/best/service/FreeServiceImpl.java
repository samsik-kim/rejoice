package com.omp.admin.contents.best.service;

import java.util.List;

import com.omp.admin.contents.adminrec.model.AdminRecommend;
import com.omp.admin.contents.best.model.BestParam;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;

public class FreeServiceImpl extends AbstractService implements FreeService {

	@Override
	public List<AdminRecommend> selectFreeBestList(BestParam param) {
		// TODO Auto-generated method stub
		List<AdminRecommend> result = null;

		try {
			
//			int chk = (Integer) super.commonDAO.queryForObject("Contents.insertBestFreeProdChk", param);
//			if(chk == 0){
//				super.commonDAO.insert("Contents.insertBestFreeProd", param);
//			}
			
			this.setStep("SelectFreeBestList");
			result = super.commonDAO.queryForPageList("Contents.selectFreeBestList", param);
		} catch (Exception e) {
			throw new ServiceException("무료BEST 리스트를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return result;
	}


	@Override
	public void updateFreeBestList(BestParam param,
			String[] upProdId, String[] expoYn, String[] expoPrior) {
		// TODO Auto-generated method stub
		super.daoManager.startTransaction();
		try {
			this.setStep("UpdateFreeBestList");
			for (int i = 0; i < upProdId.length; i++) {
				if (log.isDebugEnabled())
					log.debug("upProdId[" + i + "] : " + upProdId[i] + ", expoYn[" + i + "] : " + upProdId[i] + ", expoPrior[" + i + "] : " + expoPrior[i]);
				
				param.setCheckProdId(upProdId[i]);
				param.setExpoYn(expoYn[i]);
				param.setExpoPrior(expoPrior[i]);
				
				super.commonDAO.update("Contents.updateFreeBestList", param);
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
	public void deleteFreeBestList(String selectedProdId,
			BestParam param) {
		// TODO Auto-generated method stub
		super.daoManager.startTransaction();
		try {
			if (selectedProdId.indexOf(',') > 0) {
				this.setStep("DeleteFreeBestLists");
				String[] arrSelectedProdId = selectedProdId.split(",");
				for (int i = 0; i < arrSelectedProdId.length; i++) {

					param.setCheckProdId(arrSelectedProdId[i]);
					
					super.commonDAO.insert("Contents.deleteFreeBestList", param);
				}
			} else {

				if (!"".equals(selectedProdId)) {
					param.setCheckProdId(selectedProdId);
				}
				this.setStep("DeleteFreeBestList");
				super.commonDAO.insert("Contents.deleteFreeBestList", param);
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
	public void insertFreeBestDefault(BestParam param) {
		// TODO Auto-generated method stub
		try {
			super.daoManager.startTransaction();
			
			//기존데이터삭제
			super.commonDAO.insert("Contents.deleteFreeBestDefaultPay", param);
			
			//전일판매된 상품갯수
 			int payChkCnt = (Integer) super.commonDAO.queryForObject("Contents.selectFreeBestDefaultPayChkCnt", param);
			
			if(payChkCnt != 0){
				this.setStep("InsertFreeBestDefaultPay");
				//판매된상품 저장
				super.commonDAO.insert("Contents.insertFreeBestDefaultPay", param);
			}
			
			int payTotCnt = 150 - payChkCnt;
			
			if(payTotCnt > 0){
				
				param.setPayChkCnt(payChkCnt+"");
				param.setPayTotCnt(payTotCnt+"");
				
				//판매상품이 150개가 안될때 나머지는 신규상품 저장
				super.commonDAO.insert("Contents.insertFreeBestDefaultPay2", param);
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