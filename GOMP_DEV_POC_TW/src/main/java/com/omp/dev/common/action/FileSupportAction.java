/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 * redaano    2009. 4. 6.    Description
 *
 */
package com.omp.dev.common.action;

import com.omp.commons.action.FileSupportBaseAction;

/**
 * FileSupportAction
 * 
 * @author redaano
 * @version 0.1
 */
@SuppressWarnings("serial")
public class FileSupportAction extends FileSupportBaseAction {

	/*
	 * (non-Javadoc)
	 * @see com.omp.commons.action.FileSupportBaseAction#fileDown()
	 */
	@Override
	public String fileDown() {
		// Interceptor에서 자동으로 세션 체크 함
		return super.fileDown();
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.commons.action.FileSupportBaseAction#imgView()
	 */
	@Override
	public String imgView() {
		// Interceptor에서 자동으로 세션 체크 함
		return super.imgView();
	}

	/**
	 * BBS File Down : Do Not Session Check
	 * 
	 * @return
	 */
	public String bbsFileDown() {
		// Interceptor에서 세션 체크 예외처리 되어 있음
		return super.fileDown();
	}

}
