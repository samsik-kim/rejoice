package com.stockinvest.data.admin.dao;

import java.sql.SQLException;

import com.stockinvest.data.stockinvest.info.MemberInfo;

public interface AdminDAO {
	public MemberInfo selectAdminInfo()throws SQLException;
	public int updatePassword(MemberInfo info) throws SQLException;
}
