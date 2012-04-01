package com.stockinvest.service.stockinvest;

import java.sql.SQLException;

import com.stockinvest.data.stockinvest.info.MemberInfo;

public interface StockinvestService {

	public int selectEmp()throws SQLException;
	public MemberInfo selectAdminInfo() throws SQLException;
	public int updatePassword(MemberInfo info) throws SQLException;
}
