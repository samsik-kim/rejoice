package com.omp.admin.verify.service;

import java.util.List;

import com.omp.admin.mobileBanner.model.MobileBanner;
import com.omp.admin.verify.model.VerifyLicense;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.product.service.ARMManagerService;
import com.omp.commons.product.service.ARMManagerServiceImpl;
import com.omp.commons.service.AbstractService;
import com.omp.commons.util.DateUtil;

public class VerifyLicenseServiceImpl extends AbstractService implements VerifyLicenseService{
	public void insertLicense(VerifyLicense verifyLicense){
		try{
			daoManager.startTransaction();
			commonDAO.insert("verifyLicense.insertVerifyLicense", verifyLicense);
			daoManager.commitTransaction();
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}finally{
			daoManager.endTransaction();
		}
	}

	public List<VerifyLicense> licenseList(VerifyLicense verifyLicense){
		List<VerifyLicense> licenseList = null;
		try{
			licenseList = this.commonDAO.queryForPageList("verifyLicense.licenseList", verifyLicense);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException("licenseList Error.", e);
		}
		return licenseList;
	}
	
	public VerifyLicense detailLicense(String seqOta){
		VerifyLicense verifyLicense = new VerifyLicense();
		try{
			verifyLicense = (VerifyLicense)this.commonDAO.queryForObject("verifyLicense.detailLicense",seqOta);
		}catch(Exception e){
			e.printStackTrace();
            throw new ServiceException("detailLicense Error.", e);
		}
		return verifyLicense;
	}
	public String getCodeName(String cd){
		VerifyLicense verifyLicense = new VerifyLicense();
		try{
			verifyLicense = (VerifyLicense)this.commonDAO.queryForObject("verifyLicense.getCodeName",cd);
		}catch(Exception e){
			e.printStackTrace();
            throw new ServiceException("getCodeName Error.", e);
		}
		return verifyLicense.getCdNm();
	}
}
