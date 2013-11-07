package com.omp.admin.member.membermgr.service;

import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import com.omp.admin.member.membermgr.model.UserMemberMgr;
import com.omp.commons.exception.ServiceException;

public interface UserMemberMgrService {
	
	public List<UserMemberMgr> findUserMemberMgrList(UserMemberMgr userMember) throws ServiceException;

	public UserMemberMgr findUserMemberDetail(String mbrno) throws ServiceException;

	public List<HashMap<String, String>> phoneInfoList(String mbrno) throws ServiceException;

	public List<UserMemberMgr> loginInfoHistory(UserMemberMgr userMember) throws ServiceException;

	public JSONObject userPhoneInfoDelete(UserMemberMgr userMember) throws ServiceException;

	public JSONObject ajaxEmailSendExcute(UserMemberMgr userMember) throws ServiceException;
}
