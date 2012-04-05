package com.stockinvest.service.board;

import java.sql.SQLException;
import java.util.List;

import tframe.web.page.PageInfo;

import com.stockinvest.data.board.info.BoardInfo;
import com.stockinvest.data.board.info.BoardManageInfo;

public interface BoardService {
	public PageInfo selectBoardList(PageInfo pageInfo, BoardInfo info) throws SQLException;
	public List selectBoardListExcel(BoardInfo info) throws SQLException;
	public BoardInfo selectBoardInfo(BoardInfo info) throws SQLException;
	public int updateBoardInfo(BoardInfo info) throws  SQLException;
	public int deleteBoardInfo(BoardInfo info) throws  SQLException;
	public void insertBoardInfo(BoardInfo info) throws SQLException;
}