package com.stockinvest.service.admin;

import java.sql.SQLException;

import com.stockinvest.data.stockinvest.info.MemberInfo;

public interface AdminService {
	public MemberInfo selectAdminInfo() throws SQLException;
	public int updatePassword(MemberInfo info) throws SQLException;
}
