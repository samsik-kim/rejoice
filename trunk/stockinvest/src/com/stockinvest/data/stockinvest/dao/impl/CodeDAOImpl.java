package com.stockinvest.data.stockinvest.dao.impl;

import java.sql.SQLException;
import java.util.List;

import tframe.common.exception.TFrameSQLException;
import tframe.core.dao.IBatisAbstractDao;

import com.stockinvest.data.stockinvest.dao.CodeDAO;
import com.stockinvest.data.stockinvest.info.CodeInfo;

public class CodeDAOImpl extends IBatisAbstractDao implements CodeDAO{
	
	private static final String NAME_SPACE = "code";
	
	@Override
	public int selectCodeListCount(CodeInfo info) throws SQLException {
		int result = 0;
		try {
			result = (Integer)getSqlMapClient().queryForObject(NAME_SPACE + ".selectCodeListCount", info);
		} catch (TFrameSQLException e) {
			throw new TFrameSQLException(e);
		}		
		return result;
	}
	
	@SuppressWarnings("unchecked")	
	@Override
	public List<CodeInfo> selectCodeList(CodeInfo info) throws SQLException {
		return getSqlMapClient().queryForList(NAME_SPACE + ".selectCodeList",info);
	}
}