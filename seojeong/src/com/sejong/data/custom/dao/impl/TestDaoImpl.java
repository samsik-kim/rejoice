package com.sejong.data.custom.dao.impl;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import tframe.core.dao.IBatisAbstractDao;

import com.seojeong.data.custom.dao.TestDao;

@Repository
public class TestDaoImpl extends IBatisAbstractDao implements TestDao {

	@Override
	public int selectEmp(String empNo) throws SQLException {
		return (Integer) getSqlMapClient().queryForObject("test.sql.selectEmp", empNo);
	}
}
