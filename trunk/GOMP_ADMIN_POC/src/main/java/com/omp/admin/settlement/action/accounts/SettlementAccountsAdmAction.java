/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 *
 */
package com.omp.admin.settlement.action.accounts;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.omp.admin.adminmgr.auth.model.AdMgr;
import com.omp.admin.adminmgr.auth.model.AdSession;
import com.omp.admin.adminmgr.code.model.CommCd;
import com.omp.admin.common.Constants;
import com.omp.admin.settlement.model.Accounts;
import com.omp.admin.settlement.service.accounts.SettlementAccountsAdmService;
import com.omp.admin.settlement.service.accounts.SettlementAccountsAdmServiceImpl;
import com.omp.commons.action.BaseAction;
import com.omp.commons.exception.NoticeException;
import com.omp.commons.model.PagenateList;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.StringUtil;




/**
 * SettlementAdm Action
 *
 * @version 0.1
 */
@SuppressWarnings("serial")
public class SettlementAccountsAdmAction extends BaseAction {
	
	// service 
	private SettlementAccountsAdmService service = null;
	
	// param 
	private Accounts accounts = null;
	
	// param 
	private Accounts accountsS = null;
	
	// param 
	private Accounts accountsSS = null;
	
	
	// ExchangeRate list
	private List<Accounts> accountsList = null;
	
	
	private String chkResult = "";

	// contents list totalCount
	private long totalCount;
	
	
	
	



	/**
	 * 
	 */
	public SettlementAccountsAdmAction() {
		service = new SettlementAccountsAdmServiceImpl();
	}

	


	
	
	/**
	 * <b>Action</b>
	 * Settlement Management : 당월정산현황 리스트 month accounts list
	 * 
	 * @return
	 */
	public String monthAccountsList() {
		if (accountsS == null) { //초기접근시 바로 리턴한다.
			accountsS = new Accounts();
			return SUCCESS;
			
		}
			accountsList = service.monthAccountsList(accountsS);
			if (accountsList == null){
				totalCount = 0;
			}else{
				totalCount = ((PagenateList) accountsList).getTotalCount();
			}
			
		
		return SUCCESS;
	}
	
	
	/**
	 * <b>Action</b>
	 * Settlement Management : 당월정산현황 엑셀 다운로드
	 * 
	 * @return
	 */
	public String monthAccountsExcelList() {
		if (accountsS == null) {//초기접근시 바로 리턴해준다.
			accountsS = new Accounts();
			return SUCCESS;
		}
		
		
		this.setDownloadFile(service.monthAccountsExcelList(accountsS), "application/vnd.ms-excel; charset=UTF-8", "REMITTANCE_ACCOUNT_LIST-"+""+".xls");
		
		return SUCCESS;
	}
	
	
	/**
	 * 정산마감 패스워드 확인
	 * @return
	 */
	public String popConfirmAccounts() {

		if (accountsS == null) {
			accountsS = new Accounts();
		}

		if ("".equals(StringUtil.nvlStr(accountsS.getAdMgrPswdNo()))) {
			return NONE;
		}

		AdMgr adMgr = new AdMgr();
		String adMgrPswdNo = "";

		AdSession adSession = (AdSession) this.req.getSession(true).getAttribute(Constants.ADMIN_AUTH_SESSION_KEY);
		if (adSession != null) {
			adMgr = adSession.getAdMgr();
			adMgrPswdNo = adMgr.getPswdNo();
		} else {
			chkResult = ERROR;
			return ERROR;
		}

		if (!adMgrPswdNo.equals(StringUtil.nvlStr(accountsS.getAdMgrPswdNo()))) {
			chkResult = ERROR;
			return ERROR;
		}

		chkResult = SUCCESS;
		return SUCCESS;
	}
	
	
	/**
	 * 정산마감 일괄처리
	 * @param accounts
	 * @return
	 */
	public String monthAccountsBundleEnd() {
		
		if (accountsS == null) {//초기접근시 바로 리턴해준다.
			accountsS = new Accounts();
			return SUCCESS;
		}
		
		String loginId = com.omp.admin.common.util.CommonUtil.getLoginId(this.req.getSession());
		accountsS.setUpdBy(loginId);
		accounts = service.monthAccountsBundleEnd(accountsS);
		
		//처리후 리턴페이지는 당월정산현황 이므로 검색조건이  정산상태코드(판매마감인 상태)로 셋팅
		//그리고 검색조건을 초기상태로 변환
		accountsS.setAggtStatCd("PD004101");
		accountsS.setAdjStatCd("");
		accountsS.setMbrNo("");
		accountsS.setMbrId("");
		accountsS.setSearchType("");
		accountsS.setSearchCont("");
		
		return SUCCESS;
		
	}
	
	/**
	 * <b>Action</b>
	 * Settlement Management : 영수증 처리 리스트
	 * 
	 * @return
	 */
	public String receiptProcessList() {
		if (accountsS == null) { //초기접근시 바로 리턴한다.
			accountsS = new Accounts();
			return SUCCESS;
			
		}
		
		if (accounts == null) { 
			accounts = new Accounts();
		}
			HashMap map;
			if(!"".equals(accountsS.getSearchTimeBlock())){// 특정판매월을 검색시도시, 영수증 마감처리버튼을 위해서 현재상태와 영수증 파일존재여부 확인한다.
				map = service.accountsStateInfo(accountsS);
				if(map==null){
					accountsS.setWholeAdjStatCd("");
				}else {
					accountsS.setWholeAdjStatCd((String)map.get("ADJSTATCD"));
				}
				//local , national 영수증 존재 확인
				String prePath;
				String findMonth;
				String localFileName;
				String nationalFileName;
				String localFullPath;
				String nationalFullPath;
				
				prePath = conf.getString("omp.common.path.share.payment.transfer");
				findMonth = DateUtil.getYYYYMM(DateUtil.addMonth(DateUtil.getDateYYYYMM(accountsS.getSaleYm()), 1));
				localFileName = "local_"+findMonth+".txt" ;
				nationalFileName = "national_"+findMonth+".txt" ;
				localFullPath = prePath + "/" +localFileName ;
				nationalFullPath = prePath + "/" + nationalFileName ;
				
				
				//System.out.println("==================  localFullPath : " + localFullPath);
				//System.out.println("==================  nationalFullPath : " + nationalFullPath);
				
				File localFile = new File(localFullPath);
				File nationalFile = new File(nationalFullPath);
				if (localFile.canRead()) {
					accounts.setLocalRemittanceFileName(localFileName);
				}
				if (nationalFile.canRead()) {
					accounts.setNationalRemittanceFileName(nationalFileName);
				}
				//local , national 영수증 존재 확인
				
			}else{
				accountsS.setWholeAdjStatCd("");
			}
		
			accountsList = service.receiptProcessList(accountsS);
			totalCount = ((PagenateList) accountsList).getTotalCount();
			
		return SUCCESS;
	}
	
	/**
	 * <b>Action</b>
	 * Settlement Management : Local, National 영수증내역 Excel 다운로드
	 * 
	 * @return
	 */
	public String receiptProcessLoNaExcelList() {
		if (accountsS == null) {//초기접근시 바로 리턴해준다.
			accountsS = new Accounts();
			return SUCCESS;
		}
		
		
		this.setDownloadFile(service.receiptProcessLoNaExcelList(accountsS), "application/vnd.ms-excel; charset=UTF-8", "RECEIPT_EXCEL-"+""+".xls");
		
		return SUCCESS;
	}
	
	/**
	 * <b>Action</b>
	 * Settlement Management : 영수증 처리 상세 정보 조회
	 * 
	 * @return
	 */
	public String receiptProcessInfo() {
		if (accountsS == null) { //초기접근시 바로 리턴한다.
			accountsS = new Accounts();
			return SUCCESS;
			
		}
			accounts = service.receiptProcessInfo(accountsSS);
		
		return SUCCESS;
	}
	
	
	/**
	 * <b>Action</b>
	 * Settlement Management : 영수증 처리 수정  초기 조회
	 * 
	 * @return
	 */
	public String editStartReceiptProcessInfo() {
		if (accountsSS == null) { //초기접근시 바로 리턴한다.
			accountsS = new Accounts();
			return SUCCESS;
			
		}
			accounts = service.editStartReceiptProcessInfo(accountsSS);
		
		return SUCCESS;
	}
	
	
	/**
	 * <b>Action</b>
	 * Settlement Management : 영수증 처리 수정 완료
	 * 
	 * @return
	 */
	public String editEndReceiptProcessInfo() {
		if (accountsS == null) { //초기접근시 바로 리턴한다.
			accountsS = new Accounts();
			return SUCCESS;
			
		}
		String loginId = com.omp.admin.common.util.CommonUtil.getLoginId(this.req.getSession());
		accountsSS.setUpdBy(loginId);
		accountsSS.setInsBy(loginId);
		
		service.editEndReceiptProcessInfo(accountsSS);
		
		return SUCCESS;
	}
	
	
	/**
	 * <b>Action</b>
	 * Settlement Management : 영수증(조정액) 처리 상세 정보 조회
	 * 
	 * @return
	 */
	public String adjustmentMoneyInfo() {
		if (accountsS == null) { //초기접근시 바로 리턴한다.
			accountsS = new Accounts();
			return SUCCESS;
			
		}
			accounts = service.receiptProcessInfo(accountsSS);
		
		return SUCCESS;
	}
	
	
	
	/**
	 * <b>Action</b>
	 * Settlement Management : 영수증(조정액) 처리 수정  초기 조회
	 * 
	 * @return
	 */
	public String editStartAdjustmentMoneyInfo() {
		if (accountsSS == null) { //초기접근시 바로 리턴한다.
			accountsS = new Accounts();
			return SUCCESS;
			
		}
			accounts = service.editStartAdjustmentMoneyInfo(accountsSS);
		
		return SUCCESS;
	}
	
	
	/**
	 * <b>Action</b>
	 * Settlement Management : 영수증(조정액) 처리 수정 완료
	 * 
	 * @return
	 */
	public String editEndAdjustmentMoneyInfo() {
		if (accountsS == null) { //초기접근시 바로 리턴한다.
			accountsS = new Accounts();
			return SUCCESS;
			
		}
		String loginId = com.omp.admin.common.util.CommonUtil.getLoginId(this.req.getSession());
		accountsSS.setUpdBy(loginId);
		
		service.editEndAdjustmentMoneyInfo(accountsSS);
		
		return SUCCESS;
	}
	
	
	
	
	/**
	 * 조정액 수정팝업페이지
	 * @return
	 */
	public String popUpAdjAmt() {
		if (accountsS == null) { //초기접근시 바로 리턴한다.
			accountsS = new Accounts();
			return SUCCESS;
			
		}
		
		return SUCCESS;
	}
	
	/**
	 * 조정액 view 팝업페이지
	 * @return
	 */
	public String popUpAdjAmtView() {
		if (accountsS == null) { //초기접근시 바로 리턴한다.
			accountsS = new Accounts();
			return SUCCESS;
			
		}
		
		return SUCCESS;
	}
	
	
	/**
	 * 영수증처리마감 패스워드 확인
	 * @return
	 */
	public String popConfirmReceipt() {

		if (accountsS == null) {
			accountsS = new Accounts();
		}

		if ("".equals(StringUtil.nvlStr(accountsS.getAdMgrPswdNo()))) {
			return NONE;
		}

		AdMgr adMgr = new AdMgr();
		String adMgrPswdNo = "";

		AdSession adSession = (AdSession) this.req.getSession(true).getAttribute(Constants.ADMIN_AUTH_SESSION_KEY);
		if (adSession != null) {
			adMgr = adSession.getAdMgr();
			adMgrPswdNo = adMgr.getPswdNo();
		} else {
			chkResult = ERROR;
			return ERROR;
		}

		if (!adMgrPswdNo.equals(StringUtil.nvlStr(accountsS.getAdMgrPswdNo()))) {
			chkResult = ERROR;
			return ERROR;
		}

		chkResult = SUCCESS;
		return SUCCESS;
	}
	
	
	/**
	 * <b>Action</b>
	 * Settlement Management : 영수증 처리 마감
	 * 
	 * @return
	 */
	public String updateReceiptProcessBundleEnd() {
		if (accountsS == null) { //초기접근시 바로 리턴한다.
			accountsS = new Accounts();
			return SUCCESS;
			
		}
		String loginId = com.omp.admin.common.util.CommonUtil.getLoginId(this.req.getSession());
		accountsS.setUpdBy(loginId);
		
		service.updateReceiptProcessBundleEnd(accountsS);
		
		return SUCCESS;
	}
	
// ===================== SET/GET =============================================
	
	
	/**
	 * list total count
	 * @return long totalCount
	 */
	public long getTotalCount() {
		return totalCount;
	}






	/**
	 * @return the accounts
	 */
	public Accounts getAccounts() {
		return accounts;
	}






	/**
	 * @param accounts the accounts to set
	 */
	public void setAccounts(Accounts accounts) {
		this.accounts = accounts;
	}






	/**
	 * @return the accountsS
	 */
	public Accounts getAccountsS() {
		return accountsS;
	}






	/**
	 * @param accountsS the accountsS to set
	 */
	public void setAccountsS(Accounts accountsS) {
		this.accountsS = accountsS;
	}






	/**
	 * @return the accountsList
	 */
	public List<Accounts> getAccountsList() {
		return accountsList;
	}






	/**
	 * @param accountsList the accountsList to set
	 */
	public void setAccountsList(List<Accounts> accountsList) {
		this.accountsList = accountsList;
	}

    




	/**
	 * @return the accountsSS
	 */
	public Accounts getAccountsSS() {
		return accountsSS;
	}






	/**
	 * @param accountsSS the accountsSS to set
	 */
	public void setAccountsSS(Accounts accountsSS) {
		this.accountsSS = accountsSS;
	}






	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	
	public String getChkResult() {
		return chkResult;
	}

	public void setChkResult(String chkResult) {
		this.chkResult = chkResult;
	}
	

		
		
}