package com.stockinvest.data.stockinvest.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import tframe.core.dao.IBatisAbstractDao;

import com.stockinvest.data.board.info.BoardManageInfo;
import com.stockinvest.data.stockinvest.dao.StockinvestDAO;
import com.stockinvest.data.stockinvest.info.MemberInfo;

@Repository
public class StockinvestDAOImpl extends IBatisAbstractDao implements StockinvestDAO{
	
	private static final String NAME_SPACE = "stockinvest";

	@Override
	public int selectEmp() throws SQLException {
		return (Integer)getSqlMapClient().queryForObject("stockinvest.selectEmp");
	}

	/**
	 * 관리자 정보 가져오기
	 */
	@Override
	public MemberInfo selectAdminInfo() throws SQLException {
		return (MemberInfo)getSqlMapClient().queryForObject(NAME_SPACE + ".selectMemberInfo"); 
	}

	/**
	 * 비밀번호 변경
	 */
	@Override
	public int updatePassword(MemberInfo info) throws SQLException {
		return getSqlMapClient().update(NAME_SPACE + ".update", info);
	}

	/**
	 * leftMenu 게시판관리 목록
	 */
	@Override
	public List<BoardManageInfo> selectBoardManageList() throws SQLException {
		return getSqlMapClient().queryForList(NAME_SPACE + ".selectBoardManageList");
	} 
	
	/**
	 * 게시판 관리 게시판명 입력
	 * @throws SQLException
	 */
	@Override
	public void insertBoardManageInfo(BoardManageInfo info) throws SQLException {
		
		String getMaxBbsCd = (String)getSqlMapClient().queryForObject(NAME_SPACE + ".selectMaxBbsCd");
		info.setBbsCd(getMaxBbsCd);
		getSqlMapClient().insert(NAME_SPACE + ".insertBoardManage", info);		
	}
	
	@Override
	public int deleteBoardManageInfo(BoardManageInfo info) throws SQLException {
		return getSqlMapClient().update(NAME_SPACE + ".deleteBoardManage", info);
	}	
	
	@Override
	public int updateBoardManageInfo(BoardManageInfo info) throws SQLException {
		return getSqlMapClient().update(NAME_SPACE + ".updateBoardManage", info);
	}	
	
	
}
