package com.nmimo.service.user;

import java.sql.SQLException;

import com.nmimo.common.info.PageInfo;
import com.nmimo.data.user.info.UserBasInfo;
import com.nmimo.data.user.info.UserInfo;

/**
 * <pre>
 * User Service
 * </pre>
 * @file UserService.java
 * @since 2013. 5. 22.
 * @author Administrator
 */
public interface UserService {

	public PageInfo selectUserList(PageInfo pageInfo, UserBasInfo info)throws SQLException;
	
	public UserBasInfo selectUserDetail(UserBasInfo info)throws SQLException;
	
	public UserBasInfo selectIdmsUserDetail(UserBasInfo info)throws SQLException;
	
	
	
	/**
	 * <pre>
	 * Test
	 * </pre>
	 * @return
	 * @throws SQLException
	 */
	public String selectUserIdTest() throws SQLException;
	
	/**
	 * <pre>
	 * 로그인검증
	 * </pre>
	 * @param dbParams
	 * @return
	 * @throws SQLException
	 */
	public UserBasInfo selectUserLoginChk(UserBasInfo dbParams) throws SQLException;
	
	public int insertRegisterUserInfo(UserBasInfo dbParams) throws SQLException;
	
	public int selectUserRegChk(UserBasInfo dbParams) throws SQLException;

	public int updateUserPwdRest(UserBasInfo dbParams) throws SQLException;
	
	public UserBasInfo selectSearchApprover(UserBasInfo dbParams) throws SQLException;
	
	public int updateUserInfo(UserBasInfo dbParams) throws SQLException;
	
	public int deleteUserInfo(UserBasInfo dbParams) throws SQLException;
	
}
