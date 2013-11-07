package com.omp.admin.adminmgr.bank.service;

import java.util.List;

import com.omp.admin.adminmgr.bank.model.BankCodeModel;

public interface BankCodeMgrService {
	
	public List<BankCodeModel> commBankList(BankCodeModel bankModel);
	
	public BankCodeModel getBankInfo(BankCodeModel bankModel);
	
	public void insertBankInfo(BankCodeModel bankModel);
	
	public void updateBankInfo(BankCodeModel bankModel);

	public BankCodeModel ajaxDuplCheck(BankCodeModel bankModel);

	public void deleteBankInfo(BankCodeModel bankModel);

}
