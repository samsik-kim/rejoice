package com.stockinvest.data.stockinvest.dao.impl;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import tframe.core.dao.IBatisAbstractDao;

import com.stockinvest.data.stockinvest.dao.StockinvestDAO;
import com.stockinvest.data.stockinvest.info.MemberInfo;

@Repository
public class StockinvestDAOImpl extends IBatisAbstractDao implements StockinvestDAO{
	
	private static final String NAME_SPACE = "stockinvest";

	@Override
	public int selectEmp() throws SQLException {
		return (Integer)getSqlMapClient().queryForObject("stockinvest.selectEmp");
	}

	/**
	 * 관리자 정보 가져오기
	 */
	@Override
	public MemberInfo selectAdminInfo() throws SQLException {
		return (MemberInfo)getSqlMapClient().queryForObject(NAME_SPACE + ".selectMemberInfo"); 
	}

	/**
	 * 비밀번호 변경
	 */
	@Override
	public int updatePassword(MemberInfo info) throws SQLException {
		return getSqlMapClient().update(NAME_SPACE + ".update", info);
	}
}
