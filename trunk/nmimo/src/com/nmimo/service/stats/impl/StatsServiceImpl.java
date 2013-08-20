package com.nmimo.service.stats.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nmimo.common.info.PageInfo;
import com.nmimo.data.stats.dao.StatsDAO;
import com.nmimo.data.stats.info.StatsInfo;
import com.nmimo.service.stats.StatsService;

/**
 * <pre>
 * 통계 ServiceImpl
 * </pre>
 * @file StatsServiceImpl.java
 * @since 2013. 4. 18.
 * @author Leesh
 */
@Service
public class StatsServiceImpl implements StatsService {

	@Autowired
	StatsDAO statsDAO;

	/**
	 * <pre>
	 * 통계 리스트
	 * </pre>
	 * @param page
	 * @param dbParams
	 * @return
	 * @throws SQLException
	 */
	public PageInfo findStatsListBySearchCode(StatsInfo dbParams , PageInfo pageInfo) throws SQLException{
		
		//통계 리스트 TotalCount 가져오기
		pageInfo.setTotalCount(statsDAO.findStatsListTotalCountInfo(dbParams));
		
		if(pageInfo.getTotalCount() > 0) {
			
			dbParams.setCurrentPage(pageInfo.getCurrentPage());
			dbParams.setPageSize(pageInfo.getPageSize());
			
			pageInfo.setDataList(statsDAO.findStatsListBySearchCode(dbParams));
		}
		return pageInfo;
	}
	
	
	/**
	 * <pre>
	 *
	 * </pre>
	 * @param dbParams
	 * @return
	 * @throws SQLException
	 */
	public List<StatsInfo> findStatsListExcel(StatsInfo dbParams) throws SQLException{

		return statsDAO.findStatsListExcel(dbParams);   
	} 
	
}
