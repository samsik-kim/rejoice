package com.stockinvest.data.admin.dao.impl;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import tframe.core.dao.IBatisAbstractDao;

import com.stockinvest.data.admin.dao.AdminDAO;
import com.stockinvest.data.stockinvest.info.MemberInfo;

@Repository
public class AdminDAOImpl extends IBatisAbstractDao implements AdminDAO{

	private static final String NAME_SPACE = "admin";

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
