/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 *             2009. 5. 9.    Description
 *
 */
package com.omp.admin.adminmgr.auth.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * AdSession
 * <p/>
 * Admin Session
 * 
 * @author
 * @version 0.1
 */
@SuppressWarnings("serial")
public class AdSession implements Serializable {

	private AdMgr adMgr = null;
	private HashMap<String, List<AdMenuMgrauth>> menuListMap = null;

	public AdMgr getAdMgr() {
		return adMgr;
	}

	public void setAdMgr(AdMgr adMgr) {
		this.adMgr = adMgr;
	}

	public HashMap<String, List<AdMenuMgrauth>> getMenuListMap() {
		return menuListMap;
	}

	public void setMenuListMap(HashMap<String, List<AdMenuMgrauth>> menuListMap) {
		this.menuListMap = menuListMap;
	}

}
