package com.omp.admin.adminmgr.bank.action;

import java.io.PrintWriter;
import java.util.List;

import org.json.JSONObject;

import com.omp.admin.adminmgr.auth.model.AdSession;
import com.omp.admin.adminmgr.bank.model.BankCodeModel;
import com.omp.admin.adminmgr.bank.service.BankCodeMgrService;
import com.omp.admin.adminmgr.bank.service.BankCodeMgrServiceImpl;
import com.omp.admin.common.Constants;
import com.omp.commons.action.BaseAction;
import com.omp.commons.util.GLogger;

@SuppressWarnings("serial")
public class BankCodeMgrAction extends BaseAction {

	private final static GLogger log = new GLogger(BankCodeMgrAction.class);
	
	private BankCodeModel bankModel;
	
	private List<BankCodeModel> bankList;
	
	private BankCodeMgrService bankService;
	
	private String useYn;
	private String searchType;
	private String searchValue;
	
	public BankCodeMgrAction(){
		bankService = new BankCodeMgrServiceImpl();
	}
	
	/**
	 * Common Bank Code Management List
	 * @return
	 */
	public String commBankCdList(){
		if(bankModel == null){
			bankModel = new BankCodeModel();
		}
		
		bankModel.setUSE_YN(this.useYn);
		bankModel.setSearchType(this.searchType);
		bankModel.setSearchValue(this.searchValue);
		
		bankList = this.bankService.commBankList(bankModel);
		
		return SUCCESS;
	}
	
	/**
	 * Common Bank Code Info Register Page
	 * @return
	 */
	public String regBankInfo(){
		if(bankModel == null){
			bankModel = new BankCodeModel();
		}
		
		return SUCCESS;
	}
	
	/**
	 * Common Bank Code Duplicate Check
	 * @return
	 */
	public void ajaxDuplCheck(){
		if(bankModel == null){
			bankModel = new BankCodeModel();
		}
		
		this.res.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = null;

		JSONObject json = new JSONObject();
		try{
			bankModel = this.bankService.ajaxDuplCheck(bankModel);
			
			json.put("BANK_CD", bankModel.getBANK_CD());
			json.put("CERTI_CD", bankModel.getCERTI_CD());
			
			out = this.res.getWriter();
			
			out.write(json.toString());
		}catch(Exception e){
			e.getStackTrace();
		}finally{
			if(out != null) out.close();
		}
	}
	
	/**
	 * Common Bank Code Info Insert
	 * @return
	 */
	public String insertBankInfo(){
		if(bankModel == null){
			bankModel = new BankCodeModel();
		}
		AdSession adSession = (AdSession) this.req.getSession(true).getAttribute(Constants.ADMIN_AUTH_SESSION_KEY);
		
		bankModel.setREG_ID(adSession.getAdMgr().getMgrId());
		
		this.bankService.insertBankInfo(bankModel);
		
		return SUCCESS;
	}
	
	/**
	 * Common Bank Code Info Edit Page
	 * @return
	 */
	public String modifyBankInfo(){
		if(bankModel == null){
			bankModel = new BankCodeModel();
		}
		int currentPage = bankModel.getPage().getNo();
		
		bankModel = this.bankService.getBankInfo(bankModel);
		
		bankModel.getPage().setNo(currentPage);
		
		return SUCCESS;
	}
	
	/**
	 * Common Bank Code Info Update
	 * @return
	 */
	public String updateBankInfo(){
		if(bankModel == null){
			bankModel = new BankCodeModel();
		}
		
		AdSession adSession = (AdSession) this.req.getSession(true).getAttribute(Constants.ADMIN_AUTH_SESSION_KEY);
		
		bankModel.setUPD_ID(adSession.getAdMgr().getMgrId());
		
		this.bankService.updateBankInfo(bankModel);
		
		return SUCCESS;
	}
	
	/**
	 * Common Bank Code Info Delete
	 * @return
	 */
	public String deleteBankInfo(){
		if(bankModel == null){
			bankModel = new BankCodeModel();
		}
		
		this.bankService.deleteBankInfo(bankModel);
		
		return SUCCESS;
	}

	/**
	 * @return the bankModel
	 */
	public BankCodeModel getBankModel() {
		return bankModel;
	}

	/**
	 * @param bankModel the bankModel to set
	 */
	public void setBankModel(BankCodeModel bankModel) {
		this.bankModel = bankModel;
	}

	/**
	 * @return the bankList
	 */
	public List<BankCodeModel> getBankList() {
		return bankList;
	}

	/**
	 * @param bankList the bankList to set
	 */
	public void setBankList(List<BankCodeModel> bankList) {
		this.bankList = bankList;
	}

	/**
	 * @return the useYn
	 */
	public String getUseYn() {
		return useYn;
	}

	/**
	 * @param useYn the useYn to set
	 */
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	/**
	 * @return the searchType
	 */
	public String getSearchType() {
		return searchType;
	}

	/**
	 * @param searchType the searchType to set
	 */
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	/**
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * @param searchValue the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

}
