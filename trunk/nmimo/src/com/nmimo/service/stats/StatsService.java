package com.nmimo.service.stats;

import java.sql.SQLException;
import java.util.List;

import com.nmimo.common.info.PageInfo;
import com.nmimo.data.stats.info.StatsInfo;

/**
 * <pre>
 * 통계 Service
 * </pre>
 * @author Leesh
 *
 */
public interface StatsService {

	/**
	 * <pre>
	 * 통계 리스트
	 * </pre>
	 * @param dbParams
	 * @param pageInfo
	 * @return
	 * @throws SQLException
	 */
	public PageInfo findStatsListBySearchCode( StatsInfo dbParams , PageInfo pageInfo) throws SQLException;

	/**
	 * <pre>
	 * 통계 엑셀출력
	 * </pre>
	 * @param dbParams
	 * @return
	 * @throws SQLException
	 */
	public List<StatsInfo> findStatsListExcel(StatsInfo dbParams) throws SQLException;
	
}
