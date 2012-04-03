package com.stockinvest.service.board.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tframe.web.page.PageInfo;

import com.stockinvest.data.board.dao.BoardDAO;
import com.stockinvest.data.board.info.BoardInfo;
import com.stockinvest.data.board.info.BoardManageInfo;
import com.stockinvest.service.board.BoardService;

@Service
@Transactional(readOnly=true, rollbackFor = Exception.class)
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDAO dao;
	
	@Override
	public PageInfo selectBoardList(PageInfo pageInfo, BoardInfo info) throws SQLException {
		pageInfo.setTotalCount(dao.selectBoardListCount(info));
		if( pageInfo.getTotalCount() > 0 ) {
			info.setCurrentPage(String.valueOf(pageInfo.getCurrentPage()));
			info.setStartNum(pageInfo.getStartNum());
			info.setEndNum(pageInfo.getEndNum());
			pageInfo.setDataList(dao.selectBoardList(info));			
		}
		return pageInfo;
	}

	@Override
	public BoardInfo selectBoardInfo(BoardInfo info) throws SQLException {
		return dao.selectBoardInfo(info);
	}

	@Override
	public int updateBoardInfo(BoardInfo info) throws SQLException {
		return dao.updateBoardInfo(info);
	}

	@Override
	public int deleteBoardInfo(BoardInfo info) throws SQLException {
		return dao.deleteBoardInfo(info);
	}

	@Override
	public void insertBoardInfo(BoardInfo info) throws SQLException {
		dao.insertBoardInfo(info);
	}

	@Override
	public List selectBoardListExcel(BoardInfo info) throws SQLException {
		return dao.selectBoardListExcel(info);
	}

	@Override
	public List<BoardManageInfo> selectBoardManageList() throws SQLException {
		return dao.selectBoardManageList();
	}	
}
