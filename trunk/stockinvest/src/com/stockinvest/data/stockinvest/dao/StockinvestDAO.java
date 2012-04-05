package com.stockinvest.data.stockinvest.dao;

import java.sql.SQLException;
import java.util.List;

import com.stockinvest.data.board.info.BoardManageInfo;
import com.stockinvest.data.stockinvest.info.MemberInfo;

public interface StockinvestDAO {
	
	/** Test Select EMP */
	public int selectEmp()throws SQLException ;
	public MemberInfo selectAdminInfo()throws SQLException;
	public int updatePassword(MemberInfo info) throws SQLException;
	public List<BoardManageInfo> selectBoardManageList() throws SQLException;	
}
