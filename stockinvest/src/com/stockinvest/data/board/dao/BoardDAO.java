package com.stockinvest.data.board.dao;

import java.sql.SQLException;
import java.util.List;

import com.stockinvest.data.board.info.BoardInfo;
import com.stockinvest.data.board.info.BoardManageInfo;

public interface BoardDAO {
	public int selectBoardListCount(BoardInfo info)throws SQLException;
	public List<BoardInfo> selectBoardList(BoardInfo info)throws SQLException;
	public List selectBoardListExcel(BoardInfo info) throws SQLException;
	public BoardInfo selectBoardInfo(BoardInfo info) throws SQLException;
	public int updateBoardInfo(BoardInfo info) throws  SQLException;
	public int deleteBoardInfo(BoardInfo info) throws SQLException;
	public void insertBoardInfo(BoardInfo info) throws SQLException;
}
