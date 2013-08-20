package com.nmimo.data.user.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.nmimo.data.user.dao.UserDAO;
import com.nmimo.data.user.info.UserBasInfo;
import com.nmimo.data.user.info.UserInfo;

/**
 * <pre>
 *
 * </pre>
 * @file UserDAOImpl.java
 * @since 2013. 8. 16.
 * @author Administrator
 */
@Repository
public class UserDAOImpl implements UserDAO {

	
	@Autowired
	 private SqlMapClientTemplate sqlMapClientTemplate;

	private static final String NAME_SPACE = "user";
	
	
	/**
	 * <pre>
	 *
	 * </pre>
	 * @param info
	 * @return
	 */
	@Override
	public int selectTotalUserBas(UserBasInfo info)throws SQLException{
		return (Integer) sqlMapClientTemplate.queryForObject(NAME_SPACE + ".selectTotalUser", info);
	}

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param info
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserBasInfo> selectUserBasList(UserBasInfo info)throws SQLException{
		return sqlMapClientTemplate.queryForList(NAME_SPACE + ".selectUserBasList", info);
	}

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param info
	 * @return
	 */
	@Override
	public UserBasInfo selectUserDetail(UserBasInfo info)throws SQLException{
		return (UserBasInfo) sqlMapClientTemplate.queryForObject(NAME_SPACE + ".selectUserDetail", info);
	}
	
	/**
	 * <pre>
	 *
	 * </pre>
	 * @param info
	 * @return
	 */
	@Override
	public UserBasInfo selectIdmsUserDetail(UserBasInfo info)throws SQLException{
		return (UserBasInfo) sqlMapClientTemplate.queryForObject(NAME_SPACE + ".selectIdmsUserDetail", info);
	}
	

	/**
	 * <pre>
	 * TEST
	 * </pre>
	 * @return
	 * @throws SQLException 
	 */
	public String selectUserIdTest() throws SQLException {
		return (String)sqlMapClientTemplate.getSqlMapClient().queryForObject( NAME_SPACE + ".test");
	}
	
	/**
	 * <pre>
	 * 로그인 처리
	 * </pre>
	 * @param dbParams
	 * @return
	 * @throws SQLException
	 */
	public UserBasInfo selectUserLoginChk(UserBasInfo dbParams) throws SQLException {
		return (UserBasInfo)sqlMapClientTemplate.queryForObject(NAME_SPACE + ".selectUserLoginChk", dbParams);
	}
	
	/**
	 * <pre>
	 * 로그인 AES키값 추출
	 * </pre>
	 * @param dbParams
	 * @return
	 * @throws SQLException
	 */
	public String selectUserLoginAesKey(UserInfo dbParams) throws SQLException {
//		return (String)sqlMapClientTemplate.getSqlMapClient().queryForObject("test",dbParams);
		
//		return (UserInfo)getSqlMapClient().queryForObject(NAME_SPACE + ".selectTest", dbParams);
		return null;
	}
	
	
	/**
	 * <pre>
	 * 로그인 > 사용자 신청 > 등록
	 * </pre>
	 * @param dbParams
	 * @return
	 * @throws SQLException
	 */
	public int insertRegisterUserInfo(UserBasInfo dbParams) throws SQLException {
		return sqlMapClientTemplate.update(NAME_SPACE + ".insertRegisterUserInfo", dbParams);
	}

	
	/**
	 * <pre>
	 * 로그인 > 사용자 신청 > 사용자ID 중복체크
	 * </pre>
	 * @param dbParams
	 * @return
	 * @throws SQLException
	 */
	public int selectUserRegChk(UserBasInfo dbParams) throws SQLException {
		return (Integer) sqlMapClientTemplate.queryForObject(NAME_SPACE + ".selectUserRegChk", dbParams);
	}
	

	/**
	 * <pre>
	 * 사용자관리 >> 사용자정보 >> 사용자정보수정 >> 비밀번호 초기화  
	 * </pre>
	 * @param dbParams
	 * @return
	 * @throws SQLException
	 */
	public int updateUserPwdRest(UserBasInfo dbParams) throws SQLException {
		return sqlMapClientTemplate.update(NAME_SPACE + ".updateUserPwdRest", dbParams);
	}

	
	/**
	 * <pre>
	 * 사용자관리 >> 사용자정보 >> 사용자정보수정 >> 발송승인자 검색
	 * </pre>
	 * @param dbParams
	 * @return
	 * @throws SQLException
	 */
	public UserBasInfo selectSearchApprover(UserBasInfo dbParams) throws SQLException {
		return (UserBasInfo)sqlMapClientTemplate.queryForObject(NAME_SPACE + ".selectSearchApprover", dbParams);
	}
	
	/**
	 * <pre>
	 * 사용자관리 >> 사용자정보 >> 사용자정보수정 >> 수정 처리 
	 * </pre>
	 * @param dbParams
	 * @return
	 * @throws SQLException
	 */
	public int updateUserInfo(UserBasInfo dbParams) throws SQLException {
		return sqlMapClientTemplate.update(NAME_SPACE + ".updateUserInfo", dbParams);
	}
	
	/**
	 * <pre>
	 * 사용자관리 >> 사용자정보 >> 사용자정보수정 >> 해지 처리
	 * </pre>
	 * @param dbParams
	 * @return
	 * @throws SQLException
	 */
	public int deleteUserInfo(UserBasInfo dbParams) throws SQLException {
		return sqlMapClientTemplate.update(NAME_SPACE + ".deleteUserInfo", dbParams);
	}
	
	
	
}
