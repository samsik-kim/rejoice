package com.seojeong.data.member.dao.impl;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import tframe.core.dao.IBatisAbstractDao;

import com.seojeong.data.member.dao.MemberDAO;
import com.seojeong.data.member.info.MemberInfo;

@Repository
public class MemberDAOImpl extends IBatisAbstractDao implements MemberDAO{
	
	private static final String NAME_SPACE = "member";

	@Override
	public int selectEmp() throws SQLException {
		return (Integer)getSqlMapClient().queryForObject("test.sql.selectEmp");
	}

	/* 
	 * <p>
	 *	회원 정보 등록
	 * </p>
	 * @see com.seojeong.data.member.dao.MemberDAO#insertMember(com.seojeong.data.member.info.MemberInfo)
	 */
	@Override
	public void insertMember(MemberInfo info) throws SQLException {
		getSqlMapClient().insert(NAME_SPACE + ".insert", info);
	}

	/* <p>
	 * 	회원 정보 업데이트
	 * </p>
	 * @see com.seojeong.data.member.dao.MemberDAO#updateMember(com.seojeong.data.member.info.MemberInfo)
	 */
	@Override
	public int updateMember(MemberInfo info) throws SQLException {
		return getSqlMapClient().update(NAME_SPACE + ".update", info);
	}
	
}
