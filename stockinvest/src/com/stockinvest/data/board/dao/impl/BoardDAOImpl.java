package com.stockinvest.data.board.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import tframe.common.exception.TFrameSQLException;
import tframe.core.dao.IBatisAbstractDao;

import com.stockinvest.data.board.dao.BoardDAO;
import com.stockinvest.data.board.info.BoardInfo;

@Repository
public class BoardDAOImpl extends IBatisAbstractDao implements BoardDAO{
	
	private static final String NAME_SPACE = "board";
	
	@Override
	public int selectBoardListCount(BoardInfo info) throws SQLException {
		int result = 0;
		try {
			result = (Integer)getSqlMapClient().queryForObject(NAME_SPACE + ".selectBoardListCount", info);
		} catch (TFrameSQLException e) {
			throw new TFrameSQLException(e);
		}		
		return result;
	}
	
	@SuppressWarnings("unchecked")	
	@Override
	public List<BoardInfo> selectBoardList(BoardInfo info) throws SQLException {
		return getSqlMapClient().queryForList(NAME_SPACE + ".selectBoardList",info);
	}
	
	@Override
	public BoardInfo selectBoardInfo(BoardInfo info) throws SQLException {
		return (BoardInfo)getSqlMapClient().queryForObject(NAME_SPACE + ".selectBoardInfo",info);
	}

	@Override
	public int updateBoardInfo(BoardInfo info) throws SQLException {
		return getSqlMapClient().update(NAME_SPACE + ".update", info);
	}

	@Override
	public int deleteBoardInfo(BoardInfo info) throws SQLException {
		return getSqlMapClient().update(NAME_SPACE + ".delete", info);
	}

	@Override
	public void insertBoardInfo(BoardInfo info) throws SQLException {
		getSqlMapClient().insert(NAME_SPACE + ".insert", info);
	}

	@Override
	public List selectBoardListExcel(BoardInfo info) throws SQLException {
		return getSqlMapClient().queryForList(NAME_SPACE + ".selectCodeListExcel",info);
	}
 
}
