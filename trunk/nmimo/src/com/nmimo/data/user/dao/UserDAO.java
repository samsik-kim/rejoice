package com.nmimo.data.user.dao;

import java.sql.SQLException;
import java.util.List;

import com.nmimo.data.user.info.UserBasInfo;
import com.nmimo.data.user.info.UserInfo;

/**
 * <pre>
 * User DAO
 * </pre>
 * @file UserDAO.java
 * @since 2013. 5. 22.
 * @author Administrator
 */
public interface UserDAO {

	public int selectTotalUserBas(UserBasInfo info)throws SQLException;
	
	public List<UserBasInfo> selectUserBasList(UserBasInfo info)throws SQLException;
	
	public UserBasInfo selectUserDetail(UserBasInfo info)throws SQLException;
	
	public UserBasInfo selectIdmsUserDetail(UserBasInfo info)throws SQLException;

	public String selectUserIdTest()throws SQLException;
	
	/**
	 * <pre>
	 * 로그인검증
	 * </pre>
	 * @param dbParams
	 * @return
	 * @throws SQLException
	 */
	public UserBasInfo selectUserLoginChk(UserBasInfo dbParams) throws SQLException;
	
	/**
	 * <pre>
	 * 로그인 AES키값 추출
	 * </pre>
	 * @param dbParams
	 * @return
	 * @throws SQLException
	 */
	public String selectUserLoginAesKey(UserInfo dbParams) throws SQLException;
	
	public int insertRegisterUserInfo(UserBasInfo dbParams) throws SQLException;
	
	public int selectUserRegChk(UserBasInfo dbParams) throws SQLException;
	
	public int updateUserPwdRest(UserBasInfo dbParams) throws SQLException;
	
	public UserBasInfo selectSearchApprover(UserBasInfo info) throws SQLException;
	
	public int updateUserInfo(UserBasInfo dbParams) throws SQLException;
	
	public int deleteUserInfo(UserBasInfo dbParams) throws SQLException;
	
}
