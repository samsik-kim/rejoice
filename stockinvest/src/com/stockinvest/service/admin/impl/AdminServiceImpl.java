package com.stockinvest.service.admin.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stockinvest.data.admin.dao.AdminDAO;
import com.stockinvest.data.stockinvest.info.MemberInfo;
import com.stockinvest.service.admin.AdminService;

@Service
@Transactional(readOnly=true, rollbackFor = Exception.class)
public class AdminServiceImpl implements AdminService{

	@Autowired
	AdminDAO dao;
	
	@Override
	public MemberInfo selectAdminInfo() throws SQLException {
		return dao.selectAdminInfo();
	}

	@Override
	public int updatePassword(MemberInfo info) throws SQLException {
		return dao.updatePassword(info);
	}	
}
