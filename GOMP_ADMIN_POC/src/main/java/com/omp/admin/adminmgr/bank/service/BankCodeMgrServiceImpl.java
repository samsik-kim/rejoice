package com.omp.admin.adminmgr.bank.service;

import java.sql.SQLException;
import java.util.List;

import com.omp.admin.adminmgr.bank.model.BankCodeModel;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;

public class BankCodeMgrServiceImpl extends AbstractService implements BankCodeMgrService {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BankCodeModel> commBankList(BankCodeModel bankModel) {
		return this.commonDAO.queryForPageList("BankMgr.commBankList", bankModel);
	}

	@Override
	public void insertBankInfo(BankCodeModel bankModel) {
		try {
			this.daoManager.startTransaction();
			
			this.commonDAO.insert("BankMgr.insertBankInfo", bankModel);
			
			this.daoManager.commitTransaction();
		} catch (SQLException e) {
			throw new ServiceException("Bank Code Register Error.", e);
		} finally {
			this.daoManager.endTransaction();
		}
	}

	@Override
	public void updateBankInfo(BankCodeModel bankModel) {
		try {
			this.daoManager.startTransaction();
			
			this.commonDAO.update("BankMgr.updateBankInfo", bankModel);
			
			this.daoManager.commitTransaction();
		} catch (SQLException e) {
			throw new ServiceException("Bank Code Modify Error.", e);
		} finally {
			this.daoManager.endTransaction();
		}
	}

	@Override
	public BankCodeModel getBankInfo(BankCodeModel bankModel) {
		try {
			return (BankCodeModel)this.commonDAO.queryForObject("BankMgr.getBankInfo", bankModel);
		} catch (SQLException e) {
			throw new ServiceException("Bank Code Detail Info Error.", e);
		}
	}

	@Override
	public BankCodeModel ajaxDuplCheck(BankCodeModel bankModel) {
		BankCodeModel bankCodeModel = new BankCodeModel();
		try {
			String result = (String)this.commonDAO.queryForObject("BankMgr.duplBankCount", bankModel);
			
			if(result.equals("1")){
				bankCodeModel = (BankCodeModel)this.commonDAO.queryForObject("BankMgr.ajaxDuplCheck", bankModel);
			}else{
				bankCodeModel.setBANK_CD("Y");
				bankCodeModel.setCERTI_CD("Y");
			}
			
		} catch (SQLException e) {
			throw new ServiceException("Bank Code Duplication Find Error.", e);
		}
		
		return bankCodeModel;
	}

	@Override
	public void deleteBankInfo(BankCodeModel bankModel) {
		try {
			this.daoManager.startTransaction();
			
			for(int i = 0; i < bankModel.getCHECK_BANK_CD().length; i++){
				this.commonDAO.delete("BankMgr.deleteBankInfo", bankModel.getCHECK_BANK_CD()[i].toString());
			}
			
			this.daoManager.commitTransaction();
		} catch (SQLException e) {
			throw new ServiceException("Delete Bank Code Info Error.", e);
		} finally {
			this.daoManager.endTransaction();
		}
	}

}
