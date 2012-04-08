package com.stockinvest.service.stockinvest;

import java.sql.SQLException;
import java.util.List;

import com.stockinvest.data.board.info.BoardManageInfo;
import com.stockinvest.data.stockinvest.info.MemberInfo;

public interface StockinvestService {

	public int selectEmp()throws SQLException;
	public MemberInfo selectAdminInfo() throws SQLException;
	public int updatePassword(MemberInfo info) throws SQLException;
	public List<BoardManageInfo> selectBoardManageList() throws SQLException;
	public int updateBoardManageInfo(BoardManageInfo info) throws  SQLException;
	public int deleteBoardManageInfo(BoardManageInfo info) throws SQLException;
	public void insertBoardManageInfo(BoardManageInfo info) throws SQLException;		
}
