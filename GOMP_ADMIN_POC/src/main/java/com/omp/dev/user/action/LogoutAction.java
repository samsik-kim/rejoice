package com.omp.dev.user.action;

import com.omp.commons.action.BaseAction;
import com.omp.commons.util.SessionUtil;

@SuppressWarnings("serial")
public class LogoutAction extends BaseAction{

	public String logout() throws Exception{
		
		if (SessionUtil.existMemberSession(this.req)) {
			SessionUtil.removeMemberSession(this.req);
		}
		return SUCCESS;
	}
}
