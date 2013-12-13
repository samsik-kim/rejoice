package com.omp.dev.user.action;

import javax.servlet.http.HttpServletRequest;

import com.omp.commons.action.BaseAction;
import com.omp.commons.util.SessionUtil;
import com.omp.dev.user.model.Session;

@SuppressWarnings("serial")
public class LogoutAction extends BaseAction{

	
	public String logout() throws Exception{
		
		HttpServletRequest request = this.req;
		Session session = null;
		if (SessionUtil.existMemberSession(request)) {
			session = (Session) SessionUtil.getMemberSession(request);

			SessionUtil.removeMemberSession(request);
		}
		return SUCCESS;
	}
}
