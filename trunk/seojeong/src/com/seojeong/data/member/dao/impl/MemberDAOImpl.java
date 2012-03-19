package com.seojeong.data.member.dao.impl;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import tframe.core.dao.IBatisAbstractDao;

import com.seojeong.data.member.dao.MemberDAO;

@Repository
public class MemberDAOImpl extends IBatisAbstractDao implements MemberDAO{

	@Override
	public int selectEmp() throws SQLException {
		return (Integer)getSqlMapClient().queryForObject("test.sql.selectEmp");
	}
}
