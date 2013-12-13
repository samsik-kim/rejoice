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

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.omp.admin.adminmgr.auth.model.AdMenuMgrauth;
import com.omp.admin.adminmgr.auth.model.AdMgr;
import com.omp.admin.adminmgr.auth.model.AdSession;
import com.omp.admin.adminmgr.auth.service.AdminMemberService;
import com.omp.admin.common.Constants;
import com.omp.admin.common.util.CommonUtil;
import com.omp.commons.action.BaseAction;
import com.omp.commons.model.PagenateList;
import com.omp.commons.text.LocalizeMessage;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.StringUtil;

/**
 * AdminMemberAction
 * <p/>
 * AdminMemberAction
 * 
 * @author
 * @version 0.1
 */
@SuppressWarnings("serial")
public class AdminMemberAction extends BaseAction {

	private static final GLogger log = new GLogger(AdminMemberAction.class);

	AdminMemberService adminMemberService = null;

	private AdMgr adMgr = null;
	private List<AdMgr> adMgrList = null;

	private AdMenuMgrauth adMenuMgrauth = null;

	HashMap<String, List<AdMenuMgrauth>> authMenuMap = null;

	private Map<String, Object> result;

	private long srchCnt = 0;
	private String chkMgrId = null;

	private String selectedAccount = "";
	private String checkedMenuId = "";

	private String defaultTopMenuCode = "";
	private String defaultLeftMenuCode = "";

	public AdminMemberAction() {
		adminMemberService = new AdminMemberService();
	}

	private Map<String, Object> makeSearchCondition(Map<String, Object> result) {

		if (result == null) {
			result = new HashMap<String, Object>();
		}

		Map<String, String> selectMap = new LinkedHashMap<String, String>();
		selectMap.put("MGRNM", LocalizeMessage.getLocalizedMessage("이름"));
		selectMap.put("COMPNM", LocalizeMessage.getLocalizedMessage("회사명"));
		selectMap.put("MGRID", LocalizeMessage.getLocalizedMessage("아이디"));
		//selectMap.put("MGRNM", "可以姓名");
		//selectMap.put("COMPNM", "公司名稱");
		//selectMap.put("MGRID", "搜尋帳號");

		result.put("selectMap", selectMap);

		return result;
	}

	@SuppressWarnings("rawtypes")
	public String selectAdMgrList() {

		if (adMgr == null) {
			adMgr = new AdMgr();
		}

		this.setStep("makeSearchCondition");
		result = makeSearchCondition(result);

		// Clear/Reset Condition
		adMgr.setMgrNm(null);
		adMgr.setCompNm(null);
		adMgr.setMgrId(null);

		String sSearchType = ("".equals(StringUtil.nvlStr(this.req.getParameter("searchType")))) ? adMgr.getSearchType() : this.req
				.getParameter("searchType");
		String sSearchValue = ("".equals(StringUtil.nvlStr(this.req.getParameter("searchValue")))) ? adMgr.getSearchValue() : this.req
				.getParameter("searchValue");
		String sPageNo = ("".equals(StringUtil.nvlStr(this.req.getParameter("pageNo")))) ? "" + adMgr.getPage().getNo() : this.req
				.getParameter("pageNo");
		sPageNo = StringUtil.nvlStr(sPageNo, "1");

		adMgr.setSearchType(CommonUtil.removeSpecial(sSearchType));
		adMgr.setSearchValue(CommonUtil.removeSpecial(sSearchValue));
		adMgr.getPage().setNo(Integer.parseInt(sPageNo));

		if (!"".equals(StringUtil.nvlStr(adMgr.getSearchValue()))) {
			if ("MGRNM".equals(StringUtil.nvlStr(adMgr.getSearchType()))) {
				adMgr.setMgrNm(adMgr.getSearchValue());
			} else if ("COMPNM".equals(StringUtil.nvlStr(adMgr.getSearchType()))) {
				adMgr.setCompNm(adMgr.getSearchValue());
			} else if ("MGRID".equals(StringUtil.nvlStr(adMgr.getSearchType()))) {
				adMgr.setMgrId(adMgr.getSearchValue());
			}
		}

		if (log.isDebugEnabled()) {
			log.debug("MGRNM  : " + adMgr.getMgrNm());
			log.debug("COMPNM : " + adMgr.getCompNm());
			log.debug("MGRID  : " + adMgr.getMgrId());
		}

		this.setStep("CallServiceSelectAdMgrList");
		adMgrList = adminMemberService.selectAdMgrList(adMgr);
		srchCnt = ((PagenateList) adMgrList).getTotalCount();

		return SUCCESS;
	}

	public String selectMyInfo() {

		if (adMgr == null) {
			adMgr = new AdMgr();
		}

		this.setStep("CheckSearchCondition");
		String sSearchType = adMgr.getSearchType();
		String sSearchValue = adMgr.getSearchValue();
		int nPageNo = adMgr.getPage().getNo();

		AdSession adSession = (AdSession) this.req.getSession(true).getAttribute(Constants.ADMIN_AUTH_SESSION_KEY);
		if (adSession != null)
			adMgr = adSession.getAdMgr();

		this.setStep("CallServiceSelectAdMgr");
		adMgr = adminMemberService.selectAdMgr(adMgr);

		if (adMgr != null) {
			adMgr.setSearchType(sSearchType);
			adMgr.setSearchValue(sSearchValue);
			adMgr.getPage().setNo(nPageNo);
		}

		return SUCCESS;
	}

	public String selectAdMgr() {

		if (adMgr == null) {
			adMgr = new AdMgr();
		}

		this.setStep("CheckSearchCondition");
		String sSearchType = StringUtil.nvlStr(adMgr.getSearchType(), "");
		String sSearchValue = StringUtil.nvlStr(adMgr.getSearchValue(), "");
		int nPageNo = adMgr.getPage().getNo();

		chkMgrId = ("".equals(StringUtil.nvlStr(this.req.getParameter("mgrId")))) ? adMgr.getMgrId() : this.req.getParameter("mgrId");
		adMgr.setMgrId(chkMgrId);

		this.setStep("CallServiceSelectAdMgr");
		if (!"".equals(StringUtil.nvlStr(adMgr.getMgrId())))
			adMgr = adminMemberService.selectAdMgr(adMgr);

		if (adMgr != null) {
			adMgr.setSearchType(sSearchType);
			adMgr.setSearchValue(sSearchValue);
			adMgr.getPage().setNo(nPageNo);
		}

		return SUCCESS;
	}

	public String insertAdMgr() {

		if (adMgr == null) {
			adMgr = new AdMgr();
		}

		adMgr.setRegId(CommonUtil.getLoginId(this.req.getSession()));

		if (log.isDebugEnabled())
			log.debug(adMgr.toString());

		this.setStep("CallServiceInsertAdMgr");
		adminMemberService.insertAdMgr(adMgr);

		return SUCCESS;
	}

	public String updateAdMgr() {

		if (adMgr == null) {
			adMgr = new AdMgr();
		}

		adMgr.setUpdId(CommonUtil.getLoginId(this.req.getSession()));

		if (log.isDebugEnabled())
			log.debug(adMgr.toString());

		this.setStep("CallServiceUpdateAdMgr");
		adminMemberService.updateAdMgr(adMgr);

		return SUCCESS;
	}

	public String deleteAdMgr() {

		if (adMgr == null) {
			adMgr = new AdMgr();
		}

		selectedAccount = StringUtil.nvlStr(this.req.getParameter("selectedAccount"));
		if (selectedAccount.indexOf(',') > 0) {

			this.setStep("SplitAccount");
			String[] arrSelectedAccount = selectedAccount.split(",");
			for (int i = 0; i < arrSelectedAccount.length; i++) {

				if ("admin".equalsIgnoreCase(arrSelectedAccount[i])) {
					log.warn("DISABLE DELETE admin, {0}", new Object[] { arrSelectedAccount[i] });
					continue;
				}

				adMgr.setMgrId(arrSelectedAccount[i]);

				if (log.isDebugEnabled())
					log.debug("DELETE getMgrId() : {0}", new Object[] { adMgr.getMgrId() });

				this.setStep("CallServiceDeleteAdMgr");
				adminMemberService.deleteAdMgr(adMgr);

			}

		} else {

			this.setStep("deleteAdMgrAdmin");
			if ("admin".equalsIgnoreCase(selectedAccount)) {
				log.warn("NOT DELETE admin, {0}", new Object[] { selectedAccount });
				return SUCCESS;
			}

			adMgr.setMgrId(selectedAccount);

			if (log.isDebugEnabled())
				log.debug("DELETE getMgrId() : " + adMgr.getMgrId());

			adminMemberService.deleteAdMgr(adMgr);

		}

		return SUCCESS;
	}

	public String selectAdMenuList() {

		if (adMenuMgrauth == null) {
			adMenuMgrauth = new AdMenuMgrauth();
		}

		selectedAccount = this.req.getParameter("selectedAccount");
		if (selectedAccount.indexOf(',') <= 0) {
			adMenuMgrauth.setMgrId(selectedAccount);
		}

		// HashMap ALL MENU
		this.setStep("CallServiceSelectAdMenuList");
		authMenuMap = adminMemberService.selectAdMenuList(adMenuMgrauth);

		return SUCCESS;
	}

	public String updateAdMgrAuthList() {

		if (adMenuMgrauth == null) {
			adMenuMgrauth = new AdMenuMgrauth();
		}

		this.selectedAccount = this.req.getParameter("selectedAccount");
		this.checkedMenuId = this.req.getParameter("checkMenuId");

		// log.debug("###selectedAccount  = " + selectedAccount);
		// log.debug("###checkedMenuId = " + checkedMenuId);

		this.setStep("CallServiceUpdateAdMgrAuthList");
		adminMemberService.updateAdMgrAuthList(selectedAccount, checkedMenuId, CommonUtil.getLoginId(this.req.getSession()));

		return SUCCESS;
	}

	public AdMgr getAdMgr() {
		return adMgr;
	}

	public void setAdMgr(AdMgr adMgr) {
		this.adMgr = adMgr;
	}

	public List<AdMgr> getAdMgrList() {
		return adMgrList;
	}

	public void setAdMgrList(List<AdMgr> adMgrList) {
		this.adMgrList = adMgrList;
	}

	public AdMenuMgrauth getAdMenuMgrauth() {
		return adMenuMgrauth;
	}

	public void setAdMenuMgrauth(AdMenuMgrauth adMenuMgrauth) {
		this.adMenuMgrauth = adMenuMgrauth;
	}

	public HashMap<String, List<AdMenuMgrauth>> getAuthMenuMap() {
		return authMenuMap;
	}

	public void setAuthMenuMap(HashMap<String, List<AdMenuMgrauth>> authMenuMap) {
		this.authMenuMap = authMenuMap;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public long getSrchCnt() {
		return srchCnt;
	}

	public void setSrchCnt(long srchCnt) {
		this.srchCnt = srchCnt;
	}

	public String getChkMgrId() {
		return chkMgrId;
	}

	public void setChkMgrId(String chkMgrId) {
		this.chkMgrId = chkMgrId;
	}

	public String getSelectedAccount() {
		return selectedAccount;
	}

	public void setSelectedAccount(String selectedAccount) {
		this.selectedAccount = selectedAccount;
	}

	public String getCheckedMenuId() {
		return checkedMenuId;
	}

	public void setCheckedMenuId(String checkedMenuId) {
		this.checkedMenuId = checkedMenuId;
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

}
