package com.nmimo.data.stats.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.nmimo.common.info.PageInfo;
import com.nmimo.data.stats.dao.StatsDAO;
import com.nmimo.data.stats.info.StatsInfo;

/**
 * <pre>
 * 통계 DAOImpl
 * </pre>
 * @file StatsDAOImpl.java
 * @since 2013. 4. 18.
 * @author Leesh
 */
@Repository
public class StatsDAOImpl implements StatsDAO {

	private static final String NAME_SPACE = "stats";


	/**
	 * <pre>
	 * 통계 리스트
	 * </pre>
	 * @param dbParams
	 * @return
	 * @throws SQLException
	 */
	public List<PageInfo> findStatsListBySearchCode(StatsInfo dbParams) throws SQLException {

//		return getSqlMapClient().queryForList(NAME_SPACE + ".findStatsListBySearchCode", dbParams);
		return null;
	}


	/**
	 * <pre>
	 * 통계 리스트 TotalCount
	 * </pre>
	 * @param dbParams
	 * @return
	 * @throws SQLException
	 */
	public int findStatsListTotalCountInfo(StatsInfo dbParams) throws SQLException {
	
//		return (Integer) getSqlMapClient().queryForObject(NAME_SPACE + ".findStatsListTotalCountInfo", dbParams);
		return 0;
	}
	
	
	/**
	 * <pre>
	 * 통계 리스트 엑셀출력
	 * </pre>
	 * @param dbParams
	 * @return
	 * @throws SQLException
	 */
	public List<StatsInfo> findStatsListExcel(StatsInfo dbParams) throws SQLException {
		
//		return getSqlMapClient().queryForList(NAME_SPACE + ".selectStatsListExcel", dbParams);
		return null;
	} 
}
