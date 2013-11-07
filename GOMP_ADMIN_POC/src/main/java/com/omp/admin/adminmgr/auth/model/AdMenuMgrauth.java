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
public class AdMenuMgrauth implements Serializable {

	private String mgrId = null;
	private String menuId = null;
	private String menuNm = null;
	private String upMenuId = null;
	private int menuPrior = 0;
	private int menuDepth = 0;
	private String pageUrl = null;

	private String topMenuId = null;

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

	public String getMenuNm() {
		return menuNm;
	}

	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}

	public String getUpMenuId() {
		return upMenuId;
	}

	public void setUpMenuId(String upMenuId) {
		this.upMenuId = upMenuId;
	}

	public int getMenuPrior() {
		return menuPrior;
	}

	public void setMenuPrior(int menuPrior) {
		this.menuPrior = menuPrior;
	}

	public int getMenuDepth() {
		return menuDepth;
	}

	public void setMenuDepth(int menuDepth) {
		this.menuDepth = menuDepth;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getTopMenuId() {
		return topMenuId;
	}

	public void setTopMenuId(String topMenuId) {
		this.topMenuId = topMenuId;
	}

}
