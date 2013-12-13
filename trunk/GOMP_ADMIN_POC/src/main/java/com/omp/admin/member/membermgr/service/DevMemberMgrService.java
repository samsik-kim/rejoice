package com.omp.admin.member.membermgr.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.omp.admin.member.membermgr.model.UserMemberMgr;
import com.omp.commons.exception.ServiceException;

public interface DevMemberMgrService {
	
	public List<UserMemberMgr> findDevMemberMgrList(UserMemberMgr userMember) throws ServiceException;

	public UserMemberMgr findDevMemberDetail(String mbrno) throws ServiceException;

	public JSONObject ajaxStateUpdateExcute(UserMemberMgr devMember, HttpServletRequest req) throws ServiceException;

	public UserMemberMgr transApplicationFormView(UserMemberMgr devMember) throws ServiceException;

	public List<HashMap<String, String>> transApplicationFormViewMemDoc(UserMemberMgr devMember) throws ServiceException;

	public List<UserMemberMgr> popTransHistoryView(UserMemberMgr devMember) throws ServiceException;

	public String popRejectView(UserMemberMgr devMember) throws ServiceException;

	public UserMemberMgr popWithdrawView(UserMemberMgr devMember) throws ServiceException;

	public List<HashMap<String, String>> testPhoneInfoList(String mbrno) throws ServiceException;

}
