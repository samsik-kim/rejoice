package com.stockinvest.data.stockinvest.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import tframe.common.exception.TFrameSQLException;
import tframe.core.dao.IBatisAbstractDao;

import com.stockinvest.data.stockinvest.dao.CodeDAO;
import com.stockinvest.data.stockinvest.info.CodeInfo;

@Repository
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
	
	@Override
	public CodeInfo selectCodeInfo(CodeInfo info) throws SQLException {
		return (CodeInfo)getSqlMapClient().queryForObject(NAME_SPACE + ".selectCodeInfo",info);
	}

	@Override
	public int updateCodeInfo(CodeInfo info) throws SQLException {
		return getSqlMapClient().update(NAME_SPACE + ".update", info);
	}

	@Override
	public int deleteCodeInfo(CodeInfo info) throws SQLException {
		return getSqlMapClient().update(NAME_SPACE + ".delete", info);
	}

	@Override
	public void insertCodeInfo(CodeInfo info) throws SQLException {
		getSqlMapClient().insert(NAME_SPACE + ".insert", info);
	}
 
}
