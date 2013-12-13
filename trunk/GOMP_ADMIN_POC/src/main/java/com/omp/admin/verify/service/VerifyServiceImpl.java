package com.omp.admin.verify.service;

import java.sql.SQLException;
import java.util.List;
import com.omp.admin.verify.model.Verify;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;
import com.omp.commons.util.GLogger;

public class VerifyServiceImpl extends AbstractService implements VerifyService{
	
	public List<Verify> verifyCase(Verify verify){
		List<Verify> verifyList = null;
		try{
				
			verifyList = this.commonDAO.queryForPageList("verify.selectVerify", verify);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException("verifyCase Error.", e);
		}
		return verifyList;
	}
	
	public List selectCategoryName(String vmType){
		List verifyList = null;
		try{
			verifyList = this.commonDAO.queryForList("verify.selectCategoryName",vmType);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException("selectCategoryName Error.", e);
		}
		return verifyList;
	}
	
	public List<Verify> selectCategoryNameAll(){
		List<Verify> verifyList = null;
		try{
			verifyList = this.commonDAO.queryForList("verify.selectCategoryNameAll");
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException("selectCategoryNameAll Error.", e);
		}
		return verifyList;
	}
	
	@Override
	public List<Verify> categorySaveSelect() {
		List<Verify> verifyList = null;
			try {
				verifyList = this.commonDAO.queryForList("verify.categorySaveSelect");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return verifyList;
	}

	public void saveCategory(Verify verify){
		daoManager.startTransaction();
		try{
			this.commonDAO.insert("verify.saveCategory",verify);
			daoManager.commitTransaction();
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException("saveCategory Error.", e);
		}finally{
			daoManager.endTransaction();
		}
	}
	
	public void insertCase(Verify verify){
		daoManager.startTransaction();
		try{
			this.commonDAO.insert("verify.insertCase",verify);
			daoManager.commitTransaction();
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException("insertCase Error.", e);
		}finally{
			daoManager.endTransaction();
		}
	}
}
