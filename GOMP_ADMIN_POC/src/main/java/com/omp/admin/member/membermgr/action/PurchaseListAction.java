package com.omp.admin.member.membermgr.action;

import java.io.PrintWriter;
import java.util.List;


import org.json.JSONObject;

import com.omp.admin.common.Constants;
import com.omp.admin.member.membermgr.model.PurchaseList;
import com.omp.admin.member.membermgr.model.SearchConditionInfo;
import com.omp.admin.member.membermgr.service.PurchaseListService;
import com.omp.admin.member.membermgr.service.PurchaseListServiceImpl;
import com.omp.commons.action.BaseAction;
import com.omp.commons.util.DateUtil;

@SuppressWarnings("serial")
public class PurchaseListAction extends BaseAction {
	
	private PurchaseList purchase;
    
	private SearchConditionInfo sc;
    
	private List<PurchaseList> resultList    = null;
    
    private String resultMsg = "";
    
    private PurchaseListService service;
    
    private String searchYn;

	public PurchaseListAction() {
        service = new PurchaseListServiceImpl();
    }

    /**
     * get Purchase List 
     * @return java.lang.String
     * @throws Exception
     */
	public String listPurchaseList() throws Exception {
    	if(purchase == null) {
    		purchase = new PurchaseList();
    	}
    	
    	if(sc != null){
    		purchase.setPayCls(sc.getPayCls());
    		purchase.setPrchsStat(sc.getPrchsStat());
    		purchase.setStartDate(sc.getPurchaseStartDt());
    		purchase.setEndDate(sc.getPurchaseEndDt());
    		purchase.setSearchType(sc.getSearchType());
    		purchase.setSearchValue(sc.getSearchNm());
    		purchase.getPage().setNo(sc.getCurrentPageNo());
    		
    		resultList = service.listPurchaseList(purchase);
    		
    		searchYn = "Y";
    	}else{
    		sc = new SearchConditionInfo();
    		
    		searchYn = "N";
    	}
    	
    	return SUCCESS;
    }
	
	/**
	 * PopUp Purchase List
	 * @return
	 * @throws Exception
	 */
	public String popPurchaseList() throws Exception {
    	if(purchase == null) {
    		purchase = new PurchaseList();
    	}
    	
    	if(sc != null){
    		purchase.setPayCls(sc.getPayCls());
    		purchase.setPrchsStat(sc.getPrchsStat());
    		purchase.setStartDate(sc.getPurchaseStartDt());
    		purchase.setEndDate(sc.getPurchaseEndDt());
    		purchase.setSearchType(sc.getSearchType());
    		purchase.setSearchValue(sc.getSearchNm());
    		purchase.getPage().setNo(sc.getCurrentPageNo());
    		
    		resultList = service.listPurchaseList(purchase);
    		
    		searchYn = "Y";
    	}else{
    		sc = new SearchConditionInfo();
    		
    		searchYn = "N";
    	}
    	
    	return SUCCESS;
    }
	
	/**
	 * Cell Phone Purchase Cancel List Excel Download
	 * @return
	 * @throws Exception
	 */
	public String hpPurcaseCancelListExcel() throws Exception {
    	if(purchase == null) {
    		purchase = new PurchaseList();
    	}
    	
    	purchase.setPayCls(Constants.PURCHASE_CELLPHONE);
    	purchase.setPrchsStat(Constants.PURCHASE_CANCEL);
    	
    	if(sc == null){
    		sc = new SearchConditionInfo();
    		
    		//Default Date : 7day
    		purchase.setStartDate(DateUtil.getAddDay("-7"));
    		purchase.setEndDate(DateUtil.getSysDate());
    	}else{
    		purchase.setStartDate(sc.getPurchaseStartDt());
    		purchase.setEndDate(sc.getPurchaseEndDt());
    	}
    	
		this.setDownloadFile(service.hpPurcaseCancelListExcel(purchase), "application/vnd.ms-excel; charset=UTF-8", "下載取消購買記錄_" + DateUtil.getSysDate() + DateUtil.getCurrentTime24() + ".xls");
    	
    	return SUCCESS;
    }
	
	/**
	 * Popup Purchase Cancel
	 * @return
	 * @throws Exception
	 */
	public String popPurchaseCancel() throws Exception {
    	if(purchase == null) {
    		purchase = new PurchaseList();
    	}
    	String popupYn = purchase.getPopupYn();
    	
    	purchase = this.service.getPurchaseInfo(purchase);
    	
    	purchase.setPopupYn(popupYn);
    	
    	return SUCCESS;
    }
	
	/**
	 * Ajax Update User Addr
	 * @return
	 * @throws Exception
	 */
	public void ajaxUpdateUserAddr() throws Exception {
    	if(purchase == null) {
    		purchase = new PurchaseList();
    	}
		super.res.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = null;
    	
    	try {
			JSONObject jsonObject = new JSONObject();

			jsonObject = this.service.ajaxUpdateUserAddr(purchase);

			out = res.getWriter();
			
			out.write(jsonObject.toString());
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
    }
	
	/**
	 * Popup Purchase Cancel View
	 * @return
	 * @throws Exception
	 */
	public String popCancelView() throws Exception {
    	if(purchase == null) {
    		purchase = new PurchaseList();
    	}
    	
    	purchase = this.service.getPurchaseInfo(purchase);
    	
    	return SUCCESS;
    }
    
    public List<PurchaseList> getResultList() {
		return resultList;
	}

	public void setResultList(List<PurchaseList> resultList) {
		this.resultList = resultList;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	/**
	 * @return the purchase
	 */
	public PurchaseList getPurchase() {
		return purchase;
	}

	/**
	 * @param purchase the purchase to set
	 */
	public void setPurchase(PurchaseList purchase) {
		this.purchase = purchase;
	}

	/**
	 * @return the sc
	 */
	public SearchConditionInfo getSc() {
		return sc;
	}

	/**
	 * @param sc the sc to set
	 */
	public void setSc(SearchConditionInfo sc) {
		this.sc = sc;
	}

	/**
	 * @return the searchYn
	 */
	public String getSearchYn() {
		return searchYn;
	}

	/**
	 * @param searchYn the searchYn to set
	 */
	public void setSearchYn(String searchYn) {
		this.searchYn = searchYn;
	}
}
