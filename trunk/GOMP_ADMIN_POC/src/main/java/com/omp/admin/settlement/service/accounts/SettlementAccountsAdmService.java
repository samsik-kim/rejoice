package com.omp.admin.settlement.service.accounts;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import com.omp.admin.settlement.model.Accounts;


public interface SettlementAccountsAdmService {

	List<Accounts> monthAccountsList(Accounts accounts);
	File monthAccountsExcelList(Accounts accounts);
	HashMap accountsStateInfo(Accounts accounts);
	Accounts monthAccountsBundleEnd(Accounts accounts);
	List<Accounts> receiptProcessList(Accounts accounts);
	Accounts receiptProcessInfo(Accounts accounts);
	File receiptProcessLoNaExcelList(Accounts accounts);
	Accounts receiptLoNaCount(Accounts accounts);
	Accounts editStartReceiptProcessInfo(Accounts accounts);
	void editEndReceiptProcessInfo(Accounts accounts);
	void updateReceiptProcessBundleEnd(Accounts accounts);
	Accounts editStartAdjustmentMoneyInfo(Accounts accounts);
	void editEndAdjustmentMoneyInfo(Accounts accounts);
	
	
}
