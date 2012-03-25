package com.seojeong.data.member.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import tframe.common.exception.TFrameSQLException;
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

	/* (non-Javadoc)
	 * @see com.seojeong.data.member.dao.MemberDAO#selectMemberList(com.seojeong.data.member.info.MemberInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MemberInfo> selectMemberList(MemberInfo info) throws SQLException {
		return getSqlMapClient().queryForList(NAME_SPACE + ".selectMemberList", info);
	}

	/* (non-Javadoc)
	 * @see com.seojeong.data.member.dao.MemberDAO#selectMemberListCount(com.seojeong.data.member.info.MemberInfo)
	 */
	@Override
	public int selectMemberListCount(MemberInfo info) throws SQLException {
		int result = 0;
		try {
			result = (Integer)getSqlMapClient().queryForObject(NAME_SPACE + ".selectMemberListCount", info);
		} catch (TFrameSQLException e) {
			throw new TFrameSQLException(e);
		}		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.seojeong.data.member.dao.MemberDAO#selectMdnCheck(java.lang.String)
	 */
	@Override
	public int selectMdnCheck(String mdn) throws SQLException {
		return (Integer) getSqlMapClient().queryForObject(NAME_SPACE+".selectMdnCheck", mdn);
	}

	/* (non-Javadoc)
	 * @see com.seojeong.data.member.dao.MemberDAO#selectMemberInfo(com.seojeong.data.member.info.MemberInfo)
	 */
	@Override
	public MemberInfo selectMemberInfo(MemberInfo info) throws SQLException {
		return (MemberInfo) getSqlMapClient().queryForObject(NAME_SPACE + ".selectMemberInfo", info);
	}
}
