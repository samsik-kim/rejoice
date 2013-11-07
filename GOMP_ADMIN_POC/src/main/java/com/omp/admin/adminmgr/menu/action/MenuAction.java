/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 *             2009. 4. 24.    Description
 *
 */
package com.omp.admin.adminmgr.menu.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import com.omp.admin.adminmgr.menu.model.AdMenu;
import com.omp.admin.adminmgr.menu.service.MenuService;
import com.omp.admin.common.util.CommonUtil;
import com.omp.commons.action.BaseAction;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.StringUtil;

/**
 * CommonCodeAction
 * <p/>
 * CommonCodeAction
 * 
 * @author
 * @version 0.1
 */
@SuppressWarnings("serial")
public class MenuAction extends BaseAction {

	private static final GLogger log = new GLogger(MenuAction.class);

	private static final String DEFAULT_UP_MENU_ID = "ROOT";

	MenuService menuService = null;

	private AdMenu adMenu = null;
	private List<AdMenu> adMenuList = null;

	private String mode = "";
	private String locateMenuNm = "";
	private String locateMenuId = "";

	private long srchCnt = 0;

	public MenuAction() {
		menuService = new MenuService();
	}

	public String selectAdMenuList() {

		if (adMenu == null) {
			adMenu = new AdMenu();
		}

		this.setStep("SetSearchCondition");
		String sSearchValue = ("".equals(StringUtil.nvlStr(this.req.getParameter("searchValue")))) ? adMenu.getSearchValue() : this.req
				.getParameter("searchValue");

		try {
			if (sSearchValue != null) {
				sSearchValue = StringUtil.nvlStr(sSearchValue).replace("@", "%");
				sSearchValue = URLDecoder.decode(sSearchValue, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			log.error("ERROR URLDecoder.decode() - {0}" + new Object[] { sSearchValue });
			sSearchValue = "";
		}

		adMenu.setSearchValue(CommonUtil.removeSpecial(sSearchValue));

		if (log.isDebugEnabled())
			log.debug("adMenu.getSearchValue() : " + adMenu.getSearchValue());

		if ("".equals(StringUtil.nvlStr(adMenu.getSearchValue()))) {

			if ("".equals(StringUtil.nvlStr(adMenu.getUpMenuId()))) {
				adMenu.setUpMenuId(DEFAULT_UP_MENU_ID);
				locateMenuNm = "HOME";
			} else if (DEFAULT_UP_MENU_ID.equals(StringUtil.nvlStr(adMenu.getUpMenuId()))) {
				adMenu.setUpMenuId(DEFAULT_UP_MENU_ID);
				locateMenuNm = "HOME";
			} else {
				if (adMenu.getMenuDepth() > 0) {
					locateMenuNm = locateMenuNm.substring(0, locateMenuNm.lastIndexOf(" > "));
				} else {
					locateMenuNm = locateMenuNm + " > " + adMenu.getMenuNm();
				}
			}

			locateMenuId = adMenu.getUpMenuId();

			if (log.isDebugEnabled())
				log.debug("adMenu.getUpMenuId() : " + adMenu.getUpMenuId());

		} else {

			locateMenuNm = "UNKNOWN";
			locateMenuId = "";
			adMenu.setUpMenuId("");

		}

		this.setStep("CallServiceSelectAdMenuList");
		adMenuList = menuService.selectAdMenuList(adMenu);

		return "SUCCESS";
	}

	public String updateAdMenuUseYn() {

		if (adMenu == null) {
			adMenu = new AdMenu();
		}

		this.setStep("CallServiceUpdateAdMenuUseYn");
		menuService.updateAdMenuUseYn(adMenu);

		return SUCCESS;
	}

	public String processAdMenu() {

		if (adMenu == null) {
			adMenu = new AdMenu();
		}

		adMenu.setRegId(CommonUtil.getLoginId(this.req.getSession()));
		adMenu.setUpdId(CommonUtil.getLoginId(this.req.getSession()));

		if ("INS".equalsIgnoreCase(StringUtil.nvlStr(mode))) {

			if (log.isDebugEnabled())
				log.debug("INSERT adMenu.getMenuId() : " + adMenu.getMenuId());

			this.setStep("CallServiceInsertAdMenu");
			menuService.insertAdMenu(adMenu);

		} else {

			if (log.isDebugEnabled())
				log.debug("UPDATE adMenu.getMenuId() : " + adMenu.getMenuId());

			this.setStep("CallServiceUpdateAdMenu");
			menuService.updateAdMenu(adMenu);

		}

		String sSearchValue = adMenu.getSearchValue();
		try {
			if (sSearchValue != null) {
				sSearchValue = URLEncoder.encode(adMenu.getSearchValue(), "UTF-8");
				sSearchValue = sSearchValue.replace("%", "@");
			}
		} catch (UnsupportedEncodingException e) {
			log.error("ERROR URLEncoder.encode() - {0}" + new Object[] { sSearchValue });
			sSearchValue = "";
		}
		adMenu.setSearchValue(sSearchValue);

		return SUCCESS;
	}

	public AdMenu getAdMenu() {
		return adMenu;
	}

	public void setAdMenu(AdMenu adMenu) {
		this.adMenu = adMenu;
	}

	public List<AdMenu> getAdMenuList() {
		return adMenuList;
	}

	public void setAdMenuList(List<AdMenu> adMenuList) {
		this.adMenuList = adMenuList;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getLocateMenuNm() {
		return locateMenuNm;
	}

	public void setLocateMenuNm(String locateMenuNm) {
		this.locateMenuNm = locateMenuNm;
	}

	public String getLocateMenuId() {
		return locateMenuId;
	}

	public void setLocateMenuId(String locateMenuId) {
		this.locateMenuId = locateMenuId;
	}

	public long getSrchCnt() {
		return srchCnt;
	}

	public void setSrchCnt(long srchCnt) {
		this.srchCnt = srchCnt;
	}

	//	public static void main(String[] args) throws Exception {
	//		String sString = URLEncoder.encode("당월정산", "UTF-8");
	//		System.out.println(sString);
	//		sString = URLDecoder.decode(sString, "UTF-8");
	//		System.out.println(sString);
	//	}

}
