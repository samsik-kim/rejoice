package com.omp.admin.member.membermgr.service;

import java.io.File;
import java.util.List;

import org.json.JSONObject;

import com.omp.admin.member.membermgr.model.PurchaseList;

public interface PurchaseListService {
    /**
     * PurchaseList
     * 
     * @param String, String
     * @return List
     * @throws Exception
     */
    public List<PurchaseList> listPurchaseList(PurchaseList purchaseList);

	public File hpPurcaseCancelListExcel(PurchaseList purchase);

	public PurchaseList getPurchaseInfo(PurchaseList purchase);

	public JSONObject ajaxUpdateUserAddr(PurchaseList purchase);

}
