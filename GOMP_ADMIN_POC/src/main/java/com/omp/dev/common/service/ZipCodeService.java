package com.omp.dev.common.service;

import java.util.List;

public interface ZipCodeService{

	List<?> getZipCodeList(String keyword);
	
	/**
	 * PC Suite의 유효한 버전 조회.(다운로드 URL용)
	 * 딱히 둘데가 없어서 zipCode에 넣었음.
	 * @return
	 */
	public String getPcSuiteVer();
	
}
