package com.omp.dev.common.persistence.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.ibatis.dao.client.DaoManager;
import com.omp.commons.persistence.dao.CommonDAOImpl;
import com.omp.commons.util.GLogger;

public class ZipCodeDAOImpl extends CommonDAOImpl implements ZipCodeDAO { 
    @SuppressWarnings("unused")
	private final static GLogger log = new GLogger(ZipCodeDAOImpl.class);
    
    public ZipCodeDAOImpl(DaoManager daoManager) {
        super(daoManager);
    }

	public List<?> selectZipCode(String keyword) {
        return queryForList("Common.selectZipCode", keyword);
	}

	public String getPcSuiteVer() {
		return (String) queryForObject("Common.getPcSuiteVer");
	}
}
