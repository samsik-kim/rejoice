package com.nmimo.data.stats.dao;

import java.sql.SQLException;
import java.util.List;

import com.nmimo.common.info.PageInfo;
import com.nmimo.data.stats.info.StatsInfo;

/**
 * <pre>
 * 통계 DAO 
 * </pre>
 * @file StatsDAO.java
 * @since 2013. 4. 18.
 * @author Leesh
 */
public interface StatsDAO {



	/**
	 * <pre>
	 * 통계 리스트
	 * </pre>
	 * @param dbParams
	 * @return
	 * @throws SQLException
	 */
	public List<PageInfo> findStatsListBySearchCode(StatsInfo dbParams) throws SQLException;
	
	/**
	 * <pre>
	 * 통계 리스트 TotalCount
	 * </pre>
	 * @param dbParams
	 * @return
	 * @throws SQLException
	 */
	public int findStatsListTotalCountInfo(StatsInfo dbParams) throws SQLException;
	
	
	/**
	 * <pre>
	 * 통계 리스트 엑셀출력
	 * </pre>
	 * @param dbParams
	 * @return
	 * @throws SQLException
	 */
	public List<StatsInfo> findStatsListExcel(StatsInfo dbParams) throws SQLException;
	
}
