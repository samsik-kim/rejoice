/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 * redaano    2009. 4. 6.    Description
 * choi       2010. 6.21.    공통파일다운로드 추가
 *
 */
package com.omp.admin.common.action;

import com.omp.admin.adminmgr.auth.model.AdSession;
import com.omp.admin.common.Constants;
import com.omp.commons.action.FileSupportBaseAction;

/**
 * FileSupportAction
 * 
 * @author redaano
 * @version 0.1
 */
@SuppressWarnings("serial")
public class FileSupportAction extends FileSupportBaseAction {
	public String fileDown() {
		AdSession adminSes = null;
		try {
			adminSes = (AdSession) req.getSession().getAttribute(Constants.ADMIN_AUTH_SESSION_KEY);
		} catch (Exception e) {}
		if (!"webcontent".equals(this.bnsType) && adminSes == null)
			return "login";
		return super.fileDown();
	}

	public String imgView() {
		AdSession adminSes = null;
		try {
			adminSes = (AdSession) req.getSession().getAttribute(Constants.ADMIN_AUTH_SESSION_KEY);
		} catch (Exception e) {}
		if (!"webcontent".equals(this.bnsType) && adminSes == null)
			return "login";
		return super.imgView();
	}

}