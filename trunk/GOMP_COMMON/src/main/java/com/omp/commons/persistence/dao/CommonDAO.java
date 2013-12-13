package com.omp.commons.persistence.dao;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.omp.commons.model.ColumnInfoModel;
import com.omp.commons.model.PagenateList;
import com.omp.commons.model.Pagenateable;

/**
 * 공통 DAO 인테페이스
 * @author pat
 *
 */
public interface CommonDAO extends com.ibatis.sqlmap.client.SqlMapExecutor {

	/**
	 * 페이징 쿼리 처리
	 */
	@SuppressWarnings("rawtypes")
	public PagenateList queryForPageList(String id, Pagenateable param);

	/**
	 * 쿼리 결과를 excel로 출력
	 * @param id
	 * @param param
	 * @param colNameMap
	 * @return
	 */
	public File queryForExcel(String id, Object param, List<ColumnInfoModel> colNameList) throws IOException;

	/**
	 * 쿼리 결과를 Notice(검색조건등)과 함께 excel로 출력
	 * @param id
	 * @param param
	 * @param colNameList
	 * @param notice
	 * @return
	 * @throws IOException
	 */
	public File queryForExcel(String id, Object param, List<ColumnInfoModel> colNameList, String notice) throws IOException;
	
	
	/**
	 * 사용하는 데이터 소스를 닫습니다.
	 * 시스템 정지시에만 호출하세요.
	 * @throws SQLException
	 */
	public void closeDataSource()
		throws SQLException;
}
