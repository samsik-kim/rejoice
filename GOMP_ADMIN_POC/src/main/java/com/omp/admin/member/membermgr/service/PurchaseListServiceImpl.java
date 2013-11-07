package com.omp.admin.member.membermgr.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.ibatis.dao.client.DaoException;
import com.omp.admin.member.membermgr.model.PurchaseList;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.model.ColumnInfoModel;
import com.omp.commons.service.AbstractService;

public class PurchaseListServiceImpl extends AbstractService implements PurchaseListService {

    @SuppressWarnings("unchecked")
	public List<PurchaseList> listPurchaseList(PurchaseList purchaseList) throws DaoException {
    	List<PurchaseList> resultList = null;
    	
    	try {
    		resultList = this.commonDAO.queryForPageList("member_purchase.listPurchaseList", purchaseList);
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new ServiceException("listPurchaseList", e);
    	}
    	
    	return resultList;
    }

	@Override
	public File hpPurcaseCancelListExcel(PurchaseList purchase) {
		List<ColumnInfoModel> excelList = new ArrayList<ColumnInfoModel>();
		
		try {
			if(purchase.getPopupYn().equals("Y")){	// PopUp Page Excel Download
				excelList.add(new ColumnInfoModel("rnum", "序號"));
				excelList.add(new ColumnInfoModel("prchsDt", "購買日期"));
				excelList.add(new ColumnInfoModel("prodNm", "產品名稱"));
				excelList.add(new ColumnInfoModel("aid", "產品AID"));
				excelList.add(new ColumnInfoModel("devMbrId", "開發商"));
				excelList.add(new ColumnInfoModel("payCls", "付款方式"));
				excelList.add(new ColumnInfoModel("prchsAmt", "付款金額"));
				excelList.add(new ColumnInfoModel("dwnldStat", "下載"));
				excelList.add(new ColumnInfoModel("prchsCnclDtm", "取消日期"));
				excelList.add(new ColumnInfoModel("applNum", "驗證碼"));
				excelList.add(new ColumnInfoModel("oreRtNo", "發票編號"));
				excelList.add(new ColumnInfoModel("rtCbyn", "捐贈與否"));
			} else {
				excelList.add(new ColumnInfoModel("rnum", "序號"));
				excelList.add(new ColumnInfoModel("mbrId", "帳號"));
				excelList.add(new ColumnInfoModel("prchsDt", "購買日期"));
				excelList.add(new ColumnInfoModel("prodNm", "產品名稱"));
				excelList.add(new ColumnInfoModel("aid", "產品AID"));
				excelList.add(new ColumnInfoModel("devMbrId", "開發商"));
				excelList.add(new ColumnInfoModel("payCls", "付款方式"));
				excelList.add(new ColumnInfoModel("prchsAmt", "付款金額"));
				excelList.add(new ColumnInfoModel("prchsCnclDtm", "取消日期"));
				excelList.add(new ColumnInfoModel("dwnldStat", "下載"));
				excelList.add(new ColumnInfoModel("applNum", "驗證碼"));
				excelList.add(new ColumnInfoModel("oreRtNo", "發票編號"));
				excelList.add(new ColumnInfoModel("rtCbyn", "捐贈與否"));
			}
		
			return this.commonDAO.queryForExcel("member_purchase.hpPurcaseCancelListExcel", purchase, excelList);
		} catch (Exception e) {
			throw new ServiceException("Cell Phone Purchase Cancel List Excel Download Error.", e);
		}
	}

	@Override
	public PurchaseList getPurchaseInfo(PurchaseList purchase) {
		try {
			return (PurchaseList) this.commonDAO.queryForObject("member_purchase.getPurchaseInfo", purchase);
		} catch (Exception e) {
			throw new ServiceException("getPurchaseInfo Error.", e);
		}
	}

	@Override
	public JSONObject ajaxUpdateUserAddr(PurchaseList purchase) {
		JSONObject json = new JSONObject();
		try {
			super.daoManager.startTransaction();
			this.commonDAO.update("member_purchase.updateUserAddr", purchase);
			
			json.put("result", "SUCCESS");
			super.daoManager.commitTransaction();
		} catch (Exception e) {
			try {
				json.put("result", "FAIL");
			} catch (JSONException e1) {
				throw new ServiceException("ajaxUpdateUserAddr Error.", e);
			}
			throw new ServiceException("ajaxUpdateUserAddr Error.", e);
		} finally {
			super.daoManager.endTransaction();
		}
		
		return json;
	}
}
