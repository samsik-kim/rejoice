package com.stockinvest.service.stockinvest.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stockinvest.data.board.info.BoardInfo;
import com.stockinvest.data.board.info.BoardManageInfo;
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
	
	@Override
	public List<BoardManageInfo> selectBoardManageList() throws SQLException {
		return dao.selectBoardManageList();
	}	
	
	@Override
	public int updateBoardManageInfo(BoardManageInfo info) throws SQLException {
		return dao.updateBoardManageInfo(info);
	}

	@Override
	public int deleteBoardManageInfo(BoardManageInfo info) throws SQLException {
		return dao.deleteBoardManageInfo(info);
	}

	@Override
	public void insertBoardManageInfo(BoardManageInfo info) throws SQLException {
		dao.insertBoardManageInfo(info);
	}
}
