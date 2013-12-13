package com.omp.dev.common.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.ibatis.dao.client.DaoManager;
import com.omp.commons.persistence.dao.DaoConfig;
import com.omp.commons.service.AbstractService;
import com.omp.commons.util.GLogger;
import com.omp.dev.common.persistence.dao.ZipCodeDAO;

public class ZipCodeServiceImpl
	extends AbstractService implements ZipCodeService{
    @SuppressWarnings("unused")
	private final static GLogger log = new GLogger(ZipCodeServiceImpl.class);

    private ZipCodeDAO zipCodeDAO;
    DaoManager daoMgr = null;

    public ZipCodeServiceImpl(){
        daoMgr = DaoConfig.getDaoManager();
        this.zipCodeDAO = (ZipCodeDAO) daoMgr.getDao(ZipCodeDAO.class);
    }
    
	public List<?> getZipCodeList(String keyword) {
        return zipCodeDAO.selectZipCode(keyword);
	}

	public String getPcSuiteVer() {
		return zipCodeDAO.getPcSuiteVer();
	}
}
