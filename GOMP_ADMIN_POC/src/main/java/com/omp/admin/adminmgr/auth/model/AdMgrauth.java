/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 *             2009. 5. 6.    Description
 *
 */
package com.omp.admin.adminmgr.auth.model;

import java.io.Serializable;

/**
 * AdMenuMgrauth
 * <p/>
 * AdMenuMgrauth Model
 * 
 * @author
 * @version 0.1
 */
@SuppressWarnings("serial")
public class AdMgrauth implements Serializable {

	private String mgrId = null;
	private String menuId = null;
	private String regId = null;
	private String regDt = null;

	public String getMgrId() {
		return mgrId;
	}

	public void setMgrId(String mgrId) {
		this.mgrId = mgrId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

}
