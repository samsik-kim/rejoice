package com.nmimo.service.user.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nmimo.common.info.PageInfo;
import com.nmimo.common.util.UserUtil;
import com.nmimo.data.user.dao.UserDAO;
import com.nmimo.data.user.info.UserBasInfo;
import com.nmimo.data.user.info.UserInfo;
import com.nmimo.service.user.UserService;

/**
 * <pre>
 * User ServiceImpl
 * </pre>
 * @file UserServiceImpl.java
 * @since 2013. 5. 22.
 * @author Administrator
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;
	
	/**
	 * <pre>
	 *
	 * </pre>
	 * @param info
	 * @return
	 */
	@Override
	public PageInfo selectUserList(PageInfo pageInfo, UserBasInfo info)throws SQLException{
		int totalCnt = userDAO.selectTotalUserBas(info);
		info.setTotalCount(totalCnt);
		List<UserBasInfo> list = userDAO.selectUserBasList(info);
		// 게시물 목록 사이즈 조회 및 설정
		if (list != null && list.size() > 0) {
			// 총 갯수 설정
			pageInfo.setTotalCount(totalCnt);
			// 데이타 리스트 설정
			pageInfo.setDataList(list);
		} 
		return pageInfo;
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
		return userDAO.selectUserDetail(info);
	}


	/**
	 * <pre>
	 *
	 * </pre>
	 * @param info
	 * @return
	 */
	@Override
	public UserBasInfo 	selectIdmsUserDetail(UserBasInfo info)throws SQLException{
		return userDAO.	selectIdmsUserDetail(info);
	}


	/**
	 * <pre>
	 * Test
	 * </pre>
	 * @return
	 * @throws SQLException 
	 */
	public String selectUserIdTest() throws SQLException {
		return userDAO.selectUserIdTest();
	}
	
	/**
	 * <pre>
	 * 로그인 처리
	 * </pre>
	 * @param dbParams
	 * @return
	 * @throws SQLException
	 */
	public UserBasInfo selectUserLoginChk(UserBasInfo dbParams) throws SQLException{
		
		UserBasInfo userBasInfo = new UserBasInfo();
		
		//로그인 AES키값 추출
//		String aesKey = userDAO.selectUserLoginAesKey(dbParams);
//		String aesKey ="1234";
//		
//		if(!"".equals(aesKey)){
//			String pwd = UserUtil.encryptPw(dbParams.getUserPwd(), aesKey);
//			dbParams.setUserPwd(pwd);
//			
//			userInfo = userDAO.selectUserLoginChk(dbParams);
//		}

		userBasInfo = userDAO.selectUserLoginChk(dbParams);
		
		return userBasInfo;
		 
	}
	
	
	public int insertRegisterUserInfo(UserBasInfo dbParams)throws SQLException{
		return userDAO.insertRegisterUserInfo(dbParams);
	}

	public int selectUserRegChk(UserBasInfo dbParams)throws SQLException{
		return userDAO.selectUserRegChk(dbParams);
	}
	
	public int updateUserPwdRest(UserBasInfo dbParams)throws SQLException{
		return userDAO.	updateUserPwdRest(dbParams);
	}
	
	public UserBasInfo 	selectSearchApprover(UserBasInfo info)throws SQLException{
		return userDAO.	selectSearchApprover(info);
	}
	
	public int updateUserInfo(UserBasInfo dbParams)throws SQLException{
		return userDAO.	updateUserInfo(dbParams);
	}
	
	public int deleteUserInfo(UserBasInfo dbParams)throws SQLException{
		return userDAO.	deleteUserInfo(dbParams);
	}
	
}