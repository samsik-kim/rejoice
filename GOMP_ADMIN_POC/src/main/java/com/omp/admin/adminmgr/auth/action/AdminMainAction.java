/*
 * COPYRIGHT(c) SK telecom 2011
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 *            2009. 5. 4.    Description
 *
 */
package com.omp.admin.adminmgr.auth.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.StopWatch;

import com.omp.admin.adminmgr.auth.model.AdMenuItem;
import com.omp.admin.adminmgr.auth.model.AdMenuMgrauth;
import com.omp.admin.adminmgr.auth.model.AdMgr;
import com.omp.admin.adminmgr.auth.model.AdSession;
import com.omp.admin.adminmgr.auth.service.AdminMainService;
import com.omp.admin.common.Constants;
import com.omp.commons.action.BaseAction;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.StringUtil;

/**
 * AdminMainAction
 * <p/>
 * AdminMainAction
 * 
 * @author
 * @version 0.1
 */
@SuppressWarnings("serial")
public class AdminMainAction extends BaseAction {

	private static final GLogger log = new GLogger(AdminMainAction.class);

	private static final String URL_LOGIN_MAIN = "/adminpoc/adminMgr/loginMain.omp";

	AdminMainService adminMainService = null;

	private AdMgr adMgr = null;

	private String defaultTopMenuCode = "";
	private String defaultLeftMenuCode = "";

	private String loginSuccessMoveUrl = "";

	private List<AdMenuItem> listAdMenuItem;

	private String topCode = "";
	private String leftCode = "";

	public AdminMainAction() {
		adminMainService = new AdminMainService();
	}

	public String adminLogin() {
		HttpServletRequest request = this.req;
		request.getSession().removeAttribute(Constants.ADMIN_AUTH_SESSION_KEY);
		return SUCCESS;
	}

	public String adminLogOut() {
		HttpServletRequest request = this.req;
		request.getSession().removeAttribute(Constants.ADMIN_AUTH_SESSION_KEY);
		return SUCCESS;
	}

	public String adminLoginExcute() {

		if (adMgr == null) {
			adMgr = new AdMgr();
		}

		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		this.setStep("CallServiceAdminLogin");
		adMgr = adminMainService.adminLogin(adMgr);

		String returnStr = "";

		if (adMgr == null) {
			log.debug("### adMgr is null");
			returnStr = "loginFail";
			return returnStr;
		}

		log.debug("### ID=" + adMgr.getMgrId());
		log.debug("### PASS=" + adMgr.getPswdNo().substring(0, 1) + "*******");

		// HashMap ALL MENU
		this.setStep("CallServiceAdminAuthLeftMenuListAllMap");
		HashMap<String, List<AdMenuMgrauth>> authMenuMap = adminMainService.adminAuthLeftMenuListAllMap(adMgr.getMgrId());

		if (authMenuMap != null && authMenuMap.size() > 0) {

			this.setStep("MENUdepth2List");
			List<AdMenuMgrauth> depth2List = (List<AdMenuMgrauth>) authMenuMap.get("ROOT");
			if (depth2List.size() > 0) {

				AdMenuMgrauth authMgr2 = (AdMenuMgrauth) depth2List.get(0);
				defaultTopMenuCode = authMgr2.getMenuId();

				this.setStep("MENUdepth3List");
				List<AdMenuMgrauth> depth3list = (List<AdMenuMgrauth>) authMenuMap.get(defaultTopMenuCode);
				if (depth3list.size() > 0) {

					AdMenuMgrauth authMgr3 = (AdMenuMgrauth) depth3list.get(0);
					String menuCode3 = authMgr3.getMenuId();

					this.setStep("MENUdepth4List");
					List<AdMenuMgrauth> depth4list = (List<AdMenuMgrauth>) authMenuMap.get(menuCode3);
					if (depth4list.size() > 0) {

						AdMenuMgrauth authMgr4 = (AdMenuMgrauth) depth4list.get(0);

						loginSuccessMoveUrl = authMgr4.getPageUrl();
						defaultLeftMenuCode = authMgr4.getMenuId();
					}
				}
			}
		}

		returnStr = "loginSuccess";

		this.setStep("SetLoginSuccessMoveUrl");
		if (loginSuccessMoveUrl != null && loginSuccessMoveUrl.length() > 0) {
			if (loginSuccessMoveUrl.indexOf("?") > 0) {
				StringBuffer url = new StringBuffer();
				url.append(loginSuccessMoveUrl).append("&topCode=").append(defaultTopMenuCode);
				url.append("&leftCode=").append(defaultLeftMenuCode);
				loginSuccessMoveUrl = url.toString();
			} else {
				StringBuffer url = new StringBuffer();
				url.append(loginSuccessMoveUrl).append("?topCode=").append(defaultTopMenuCode);
				url.append("&leftCode=").append(defaultLeftMenuCode);
				loginSuccessMoveUrl = url.toString();
			}
		}

		// Redirect Main Page
		loginSuccessMoveUrl = URL_LOGIN_MAIN + "?topCode=" + defaultTopMenuCode + "&leftCode=" + defaultLeftMenuCode;
		log.debug("####loginSuccessMoveUrl==>" + loginSuccessMoveUrl);

		this.setStep("SetAdSession");
		AdSession adSession = new AdSession();
		adSession.setAdMgr(adMgr);
		adSession.setMenuListMap(authMenuMap);

		HttpServletRequest request = this.req;
		request.getSession(true).setAttribute(Constants.ADMIN_AUTH_SESSION_KEY, adSession);

		stopWatch.stop();

		if (log.isInfoEnabled())
			log.info("Process Time : {0}", new Object[] { stopWatch.getTime() });

		return returnStr;
	}

	public String adminMain() {

		listAdMenuItem = new ArrayList<AdMenuItem>();

		this.setStep("CheckAdSession");
		AdSession admSession = (AdSession) this.req.getSession().getAttribute(Constants.ADMIN_AUTH_SESSION_KEY);

		if (admSession == null) {
			log.warn("admSession is NULL !!!");
			return SUCCESS;
		}

		String topCode = "";
		String leftCode = "";

		// iterator 바인딩 편의를 위해 map이 아닌 계층형 구조로 변환 
		this.setStep("hashMapMenuList");
		HashMap<String, List<AdMenuMgrauth>> hashMapMenuList = admSession.getMenuListMap();

		this.setStep("hashMapMenuListDepth2List");
		List<AdMenuMgrauth> depth2List = (List<AdMenuMgrauth>) hashMapMenuList.get("ROOT");
		if (depth2List == null) {
			return SUCCESS;
		}

		for (int k = 0; k < depth2List.size(); k++) {

			AdMenuMgrauth authMgr2 = (AdMenuMgrauth) depth2List.get(k);
			AdMenuItem menuItem = new AdMenuItem();
			menuItem.setMenuId(authMgr2.getMenuId());
			menuItem.setMenuNm(authMgr2.getMenuNm());
			menuItem.setMenuDepth(authMgr2.getMenuDepth());
			menuItem.setMenuPrior(authMgr2.getMenuPrior());
			menuItem.setMgrId(authMgr2.getMgrId());
			menuItem.setPageUrl(authMgr2.getPageUrl());

			this.setStep("hashMapMenuListDepth3List");
			List<AdMenuMgrauth> depth3list = (List<AdMenuMgrauth>) hashMapMenuList.get(authMgr2.getMenuId());
			String localTopCode = authMgr2.getMenuId();
			for (int i = 0; i < depth3list.size(); i++) {

				AdMenuMgrauth authMgr3 = (AdMenuMgrauth) depth3list.get(i);
				AdMenuItem menuItem2 = new AdMenuItem();
				menuItem2.setMenuId(authMgr3.getMenuId());
				menuItem2.setMenuNm(authMgr3.getMenuNm());
				menuItem2.setMenuDepth(authMgr3.getMenuDepth());
				menuItem2.setMenuPrior(authMgr3.getMenuPrior());
				menuItem2.setMgrId(authMgr3.getMgrId());
				menuItem2.setPageUrl(authMgr3.getPageUrl());

				List<AdMenuMgrauth> depth4list = (List<AdMenuMgrauth>) hashMapMenuList.get(authMgr3.getMenuId());
				for (int j = 0; j < depth4list.size(); j++) {

					this.setStep("hashMapMenuListDepth4List");
					AdMenuMgrauth authMgr4 = (AdMenuMgrauth) depth4list.get(j);
					AdMenuItem menuItem3 = new AdMenuItem();
					menuItem3.setMenuId(authMgr4.getMenuId());
					menuItem3.setMenuNm(authMgr4.getMenuNm());
					menuItem3.setMenuDepth(authMgr4.getMenuDepth());
					menuItem3.setMenuPrior(authMgr4.getMenuPrior());
					menuItem3.setMgrId(authMgr4.getMgrId());
					menuItem3.setPageUrl(authMgr4.getPageUrl());
					menuItem2.getSubMenuItem().add(menuItem3);

					String localLeftCode = authMgr4.getMenuId();
					// select local top, left code setting
					menuItem3.setPageUrl(menuItem3.getPageUrl() + (menuItem3.getPageUrl().indexOf("?") > 0 ? "&" : "?") + "topCode="
							+ localTopCode + "&leftCode=" + localLeftCode);

					if (StringUtil.isEmpty(leftCode))
						leftCode = authMgr4.getMenuId();
				}

				menuItem.getSubMenuItem().add(menuItem2);
				if (StringUtil.isEmpty(topCode))
					topCode = authMgr3.getMenuId();
			}
			listAdMenuItem.add(menuItem);
		}

		return SUCCESS;
	}

	public AdMgr getAdMgr() {
		return adMgr;
	}

	public void setAdMgr(AdMgr adMgr) {
		this.adMgr = adMgr;
	}

	public String getLoginSuccessMoveUrl() {
		return loginSuccessMoveUrl;
	}

	public void setLoginSuccessMoveUrl(String loginSuccessMoveUrl) {
		this.loginSuccessMoveUrl = loginSuccessMoveUrl;
	}

	public String getDefaultTopMenuCode() {
		return defaultTopMenuCode;
	}

	public void setDefaultTopMenuCode(String defaultTopMenuCode) {
		this.defaultTopMenuCode = defaultTopMenuCode;
	}

	public String getDefaultLeftMenuCode() {
		return defaultLeftMenuCode;
	}

	public void setDefaultLeftMenuCode(String defaultLeftMenuCode) {
		this.defaultLeftMenuCode = defaultLeftMenuCode;
	}

	public List<AdMenuItem> getListAdMenuItem() {
		return listAdMenuItem;
	}

	public void setListAdMenuItem(List<AdMenuItem> listAdMenuItem) {
		this.listAdMenuItem = listAdMenuItem;
	}

	public String getTopCode() {
		return topCode;
	}

	public void setTopCode(String topCode) {
		this.topCode = topCode;
	}

	public String getLeftCode() {
		return leftCode;
	}

	public void setLeftCode(String leftCode) {
		this.leftCode = leftCode;
	}

}
