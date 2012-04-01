package com.stockinvest.data.stockinvest.dao;

import java.sql.SQLException;

import com.stockinvest.data.stockinvest.info.MemberInfo;

public interface StockinvestDAO {
	
	/** Test Select EMP */
	public int selectEmp()throws SQLException ;
	public MemberInfo selectAdminInfo()throws SQLException;
	public int updatePassword(MemberInfo info) throws SQLException;
}
