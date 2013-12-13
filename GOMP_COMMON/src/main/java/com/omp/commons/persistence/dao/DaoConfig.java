package com.omp.commons.persistence.dao;

import java.io.Reader;
import java.util.Properties;

import com.ibatis.common.resources.Resources;
import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.DaoManagerBuilder;

/**
 * DAO 환경을 표현하는 객체
 * @author pat
 *
 */
public class DaoConfig {
 
  private static final String resource = "ibatis_config/dao.xml";
  private static DaoManager daoManager;
  
  public static DaoManager getDaoManager() {
	  if (daoManager == null) {
		  throw new RuntimeException("DaoConfig is not initialized.");
	  }
	  return daoManager;
  }

  public static DaoManager newDaoManager(Properties props) {
    try {
    	Reader reader = Resources.getResourceAsReader(resource);
		return DaoManagerBuilder.buildDaoManager(reader, props);
    } catch (Exception e) {
      throw new RuntimeException("Could not initialize DaoConfig.", e);
    }
  }
  
  public static void initialize(Properties props) {
	  daoManager	= newDaoManager(props);
  }
}