package com.stockinvest.service.stockinvest.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stockinvest.data.stockinvest.dao.StockinvestDAO;
import com.stockinvest.data.stockinvest.info.MemberInfo;
import com.stockinvest.service.stockinvest.StockinvestService;

@Service
@Transactional(readOnly=true, rollbackFor = Exception.class)
public class StockinvestServiceImpl implements StockinvestService {

	
	@Autowired
	StockinvestDAO dao;
	
	@Override
	public int selectEmp() throws SQLException {
		return dao.selectEmp();
	}

	@Override
	public MemberInfo selectAdminInfo() throws SQLException {
		return dao.selectAdminInfo();
	}

	@Override
	public int updatePassword(MemberInfo info) throws SQLException {
		return dao.updatePassword(info);
	}

}
