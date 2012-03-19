package com.stockinvest.data.stockinvest.dao.impl;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import tframe.core.dao.IBatisAbstractDao;

import com.stockinvest.data.stockinvest.dao.StockinvestDAO;

@Repository
public class StockinvestDAOImpl extends IBatisAbstractDao implements StockinvestDAO{
	
//	private static final String NAME_SPACE = "stockinvest";

	@Override
	public int selectEmp() throws SQLException {
		return (Integer)getSqlMapClient().queryForObject("stockinvest.selectEmp");
	}
}
