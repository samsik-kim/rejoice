package com.omp.dev.common.persistence.dao;

import java.util.List;

public interface ZipCodeDAO {

	public abstract List<?> selectZipCode(String keyword);
	
	/**
	 * PC Suite의 유효한 버전 조회.(다운로드 URL용)
	 * 딱히 둘데가 없어서 zipCodeDao에 넣었음.
	 * @return
	 */
	public String getPcSuiteVer();

}