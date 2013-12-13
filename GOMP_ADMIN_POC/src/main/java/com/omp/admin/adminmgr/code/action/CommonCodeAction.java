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
package com.omp.admin.adminmgr.code.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.omp.admin.adminmgr.auth.model.AdMgr;
import com.omp.admin.adminmgr.auth.model.AdSession;
import com.omp.admin.adminmgr.code.model.CommCd;
import com.omp.admin.adminmgr.code.service.CommonCodeService;
import com.omp.admin.common.Constants;
import com.omp.admin.common.util.CommonUtil;
import com.omp.commons.action.BaseAction;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.model.PagenateList;
import com.omp.commons.ssc.SyncSignal;
import com.omp.commons.text.LocalizeMessage;
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
public class CommonCodeAction extends BaseAction {

	private static final GLogger log = new GLogger(CommonCodeAction.class);

	CommonCodeService commonCodeService = null;

	private CommCd commCd = null;
	private List<CommCd> commCdList = null;

	private String mode = "";

	private long srchCnt = 0;

	private Map<String, Object> result;

	private String chkResult = "";

	public CommonCodeAction() {
		commonCodeService = new CommonCodeService();
	}
  
	private Map<String, Object> makeCommCdCriteria(Map<String, Object> result) {

		if (result == null) {
			result = new HashMap<String, Object>();
		}

		// badnotiYn Y - BAD. N - NORMAL
		Map<String, String> radioMap = new LinkedHashMap<String, String>();
		radioMap.put("", LocalizeMessage.getLocalizedMessage("전체"));
		radioMap.put(Constants.YES, "Y");
		radioMap.put(Constants.NO, "N");
		//radioMap.put("", "全部");
		//radioMap.put(Constants.YES, "Y");
		//radioMap.put(Constants.NO, "N");

		Map<String, String> selectMap = new LinkedHashMap<String, String>();
		selectMap.put("CD", LocalizeMessage.getLocalizedMessage("코드"));
		selectMap.put("CDNM", LocalizeMessage.getLocalizedMessage("코드명"));
		//selectMap.put("CD", "編碼");
		//selectMap.put("CDNM", "編碼名稱");

		result.put("radioMap", radioMap);
		result.put("selectMap", selectMap);

		return result;
	}

	@SuppressWarnings("rawtypes")
	public String selectCommCdList() {

		if (commCd == null) {
			commCd = new CommCd();
		}

		this.setStep("makeCommCdCriteria");
		result = makeCommCdCriteria(result);

		String sSrchUseYn = ("".equals(StringUtil.nvlStr(this.req.getParameter("srchUseYn")))) ? commCd.getSrchUseYn() : this.req
				.getParameter("srchUseYn");
		String sSrchGrpType = ("".equals(StringUtil.nvlStr(this.req.getParameter("srchGrpType")))) ? commCd.getSrchGrpType() : this.req
				.getParameter("srchGrpType");
		String sSrchGrpValue = ("".equals(StringUtil.nvlStr(this.req.getParameter("srchGrpValue")))) ? commCd.getSrchGrpValue() : this.req
				.getParameter("srchGrpValue");
		String sSrchDtlType = ("".equals(StringUtil.nvlStr(this.req.getParameter("srchDtlType")))) ? commCd.getSrchDtlType() : this.req
				.getParameter("srchDtlType");
		String sSrchDtlValue = ("".equals(StringUtil.nvlStr(this.req.getParameter("srchDtlValue")))) ? commCd.getSrchDtlValue() : this.req
				.getParameter("srchDtlValue");
		String sPageNo = ("".equals(StringUtil.nvlStr(this.req.getParameter("pageNo")))) ? "" + commCd.getPage().getNo() : this.req
				.getParameter("pageNo");
		sPageNo = StringUtil.nvlStr(sPageNo, "1");
		String sSelectedGrpCd = ("".equals(StringUtil.nvlStr(this.req.getParameter("selectedGrpCd")))) ? commCd.getSelectedGrpCd()
				: this.req.getParameter("selectedGrpCd");
		String sGubun = ("".equals(StringUtil.nvlStr(this.req.getParameter("gubun")))) ? commCd.getGubun() : this.req.getParameter("gubun");

		// IE6
		try {
			if (sSrchGrpValue != null) {
				sSrchGrpValue = StringUtil.nvlStr(sSrchGrpValue).replace("@", "%");
				sSrchGrpValue = URLDecoder.decode(sSrchGrpValue, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			log.error("ERROR URLDecoder.decode() - {0}" + new Object[] { sSrchGrpValue });
			sSrchGrpValue = "";
		}
		// IE6
		try {
			if (sSrchDtlValue != null) {
				sSrchDtlValue = StringUtil.nvlStr(sSrchDtlValue).replace("@", "%");
				sSrchDtlValue = URLDecoder.decode(sSrchDtlValue, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			log.error("ERROR URLDecoder.decode() - {0}" + new Object[] { sSrchDtlValue });
			sSrchDtlValue = "";
		}

		commCd.setSrchUseYn(CommonUtil.removeSpecial(sSrchUseYn));
		commCd.setSrchGrpType(CommonUtil.removeSpecial(sSrchGrpType));
		commCd.setSrchGrpValue(CommonUtil.removeSpecial(sSrchGrpValue));
		commCd.setSrchDtlType(CommonUtil.removeSpecial(sSrchDtlType));
		commCd.setSrchDtlValue(CommonUtil.removeSpecial(sSrchDtlValue));
		commCd.getPage().setNo(Integer.parseInt(sPageNo));
		commCd.setSelectedGrpCd(CommonUtil.removeSpecial(sSelectedGrpCd));
		commCd.setGubun(sGubun);

		commCd.setSrchGrpValue(CommonUtil.removeSpecial(commCd.getSrchGrpValue()));
		commCd.setSrchDtlValue(CommonUtil.removeSpecial(commCd.getSrchDtlValue()));

		if ("".equals(StringUtil.nvlStr(commCd.getGubun()))) {
			commCd.setGubun("GROUP");
		}

		if (log.isDebugEnabled()) {
			log.debug("commCd.getSrchUseYn() : " + commCd.getSrchUseYn());
			log.debug("commCd.getSrchGrpType() : " + commCd.getSrchGrpType());
			log.debug("commCd.getSrchGrpValue() : " + commCd.getSrchGrpValue());
			log.debug("commCd.getSrchDtlType() : " + commCd.getSrchDtlType());
			log.debug("commCd.getSrchDtlValue() : " + commCd.getSrchDtlValue());
			log.debug("commCd.getSelectedGrpCd() : " + commCd.getSelectedGrpCd());
			log.debug("commCd.getGubun() : " + commCd.getGubun());
		}

		if (!"".equals(StringUtil.nvlStr(commCd.getSelectedGrpCd()))) {
			commCd.setGrpCd(commCd.getSelectedGrpCd());

			if (log.isDebugEnabled()) {
				log.debug("commCd.getGrpCd() : " + commCd.getGrpCd());
			}
		}

		this.setStep("CallServiceSelectCommCdList");
		commCdList = commonCodeService.selectCommCdList(commCd);
		srchCnt = ((PagenateList) commCdList).getTotalCount();

		return SUCCESS;
	}

	public String selectCommCd() {

		if (commCd == null) {
			commCd = new CommCd();
		}

		this.setStep("SetSearchCondition");
		String sSrchUseYn = commCd.getUseYn();
		String sSrchGrpType = commCd.getSrchGrpType();
		String sSrchGrpValue = commCd.getSrchGrpValue();
		String sSrchDtlType = commCd.getSrchDtlType();
		String sSrchDtlValue = commCd.getSrchDtlValue();
		int nPageNo = commCd.getPage().getNo();

		if (!"".equals(commCd.getSelectedGrpCd())) {

			commCd.setGrpCd(commCd.getSelectedGrpCd());
			commCd.setDtlCd(commCd.getSelectedDtlCd());

			if (log.isDebugEnabled()) {
				log.debug("commCd.getGrpCd() : " + commCd.getGrpCd());
				log.debug("commCd.getDtlCd() : " + commCd.getDtlCd());
			}
		}

		if (!"".equals(StringUtil.nvlStr(commCd.getDtlCd()))) {
			this.setStep("CallServiceSelectCommCd");
			commCd = commonCodeService.selectCommCd(commCd);
		}

		if (commCd != null) {
			commCd.setSrchUseYn(sSrchUseYn);
			commCd.setSrchGrpType(sSrchGrpType);
			commCd.setSrchGrpValue(sSrchGrpValue);
			commCd.setSrchDtlType(sSrchDtlType);
			commCd.setSrchDtlValue(sSrchDtlValue);
			commCd.getPage().setNo(nPageNo);
		}

		return SUCCESS;
	}

	public String processCommCd() {

		if (commCd == null) {
			commCd = new CommCd();
		}

		if ("".equals(StringUtil.nvlStr(commCd.getDtlFullCd()))) {

			if (log.isDebugEnabled())
				log.debug("INSERT commCd.getDtlFullCd() : " + commCd.getGrpCd() + " + " + commCd.getDtlCd());

			commCd.setDtlFullCd(commCd.getGrpCd() + commCd.getDtlCd());
			
			if (!"".equals(commCd.getDtlFullCd())) {
				int retCnt = commonCodeService.selectGroupInfoCnt(commCd);
				
				if (retCnt > 0) {
					//System.out.print("###################### retCnt : " + retCnt );				
	   				return NONE;
				}
			}	
			
			this.setStep("CallServiceInsertCommCd");
			commonCodeService.insertCommCd(commCd);

		} else {

			if (log.isDebugEnabled())
				log.debug("UPDATE commCd.getDtlFullCd() : " + commCd.getDtlFullCd());

			this.setStep("CallServiceUpdateCommCd");
			commonCodeService.updateCommCd(commCd);

		}

		// IE6
		String sSrchGrpValue = commCd.getSrchGrpValue();
		try {
			if (sSrchGrpValue != null) {
				sSrchGrpValue = URLEncoder.encode(commCd.getSrchGrpValue(), "UTF-8");
				sSrchGrpValue = sSrchGrpValue.replace("%", "@");
			}
		} catch (UnsupportedEncodingException e) {
			log.error("ERROR URLEncoder.encode() - {0}" + new Object[] { sSrchGrpValue });
			sSrchGrpValue = "";
		}
		commCd.setSrchGrpValue(sSrchGrpValue);
		// IE6
		String sSrchDtlValue = commCd.getSrchDtlValue();
		try {
			if (sSrchDtlValue != null) {
				sSrchDtlValue = URLEncoder.encode(commCd.getSrchDtlValue(), "UTF-8");
				sSrchDtlValue = sSrchDtlValue.replace("%", "@");
			}
		} catch (UnsupportedEncodingException e) {
			log.error("ERROR URLEncoder.encode() - {0}" + new Object[] { sSrchDtlValue });
			sSrchDtlValue = "";
		}
		commCd.setSrchDtlValue(sSrchDtlValue);

		return SUCCESS;
	}
	
	
	public String processCommDetailCd() {

		if (commCd == null) {
			commCd = new CommCd();
		}

		if ("DETAIL".equals(StringUtil.nvlStr(commCd.getGubun() ))) {

			if (log.isDebugEnabled())
				log.debug("INSERT commCd.getDtlFullCd() : " + commCd.getGrpCd() + " + " + commCd.getDtlCd());

			commCd.setDtlFullCd(commCd.getGrpCd() + commCd.getDtlCd());
			
			if (!"".equals(commCd.getDtlFullCd())) {
				int retCnt = commonCodeService.selectGroupInfoCnt(commCd);
				
				if (retCnt > 0) {
					//System.out.print("###################### retCnt : " + retCnt );				
	   				return NONE;
				}
			}

			this.setStep("CallServiceInsertCommCd");
			commonCodeService.insertCommCd(commCd);
		} else {

			if (log.isDebugEnabled())
				log.debug("UPDATE commCd.getDtlFullCd() : " + commCd.getDtlFullCd());

			this.setStep("CallServiceUpdateCommCd");
			commonCodeService.updateCommCd(commCd);
		}

		// IE6
		String sSrchGrpValue = commCd.getSrchGrpValue();
		try {
			if (sSrchGrpValue != null) {
				sSrchGrpValue = URLEncoder.encode(commCd.getSrchGrpValue(), "UTF-8");
				sSrchGrpValue = sSrchGrpValue.replace("%", "@");
			}
		} catch (UnsupportedEncodingException e) {
			log.error("ERROR URLEncoder.encode() - {0}" + new Object[] { sSrchGrpValue });
			sSrchGrpValue = "";
		}
		commCd.setSrchGrpValue(sSrchGrpValue);
		// IE6
		String sSrchDtlValue = commCd.getSrchDtlValue();
		try {
			if (sSrchDtlValue != null) {
				sSrchDtlValue = URLEncoder.encode(commCd.getSrchDtlValue(), "UTF-8");
				sSrchDtlValue = sSrchDtlValue.replace("%", "@");
			}
		} catch (UnsupportedEncodingException e) {
			log.error("ERROR URLEncoder.encode() - {0}" + new Object[] { sSrchDtlValue });
			sSrchDtlValue = "";
		}
		commCd.setSrchDtlValue(sSrchDtlValue);

		return SUCCESS;
	}	

	public String updateCommCdUseYn() {

		if (commCd == null) {
			commCd = new CommCd();
		}

		commCd.setUseYn("N");
		this.setStep("CallServiceUpdateCommCdUseYn");
		commonCodeService.updateCommCdUseYn(commCd);

		// IE6
		String sSrchGrpValue = commCd.getSrchGrpValue();
		try {
			if (sSrchGrpValue != null) {
				sSrchGrpValue = URLEncoder.encode(commCd.getSrchGrpValue(), "UTF-8");
				sSrchGrpValue = sSrchGrpValue.replace("%", "@");
			}
		} catch (UnsupportedEncodingException e) {
			log.error("ERROR URLEncoder.encode() - {0}" + new Object[] { sSrchGrpValue });
			sSrchGrpValue = "";
		}
		commCd.setSrchGrpValue(sSrchGrpValue);
		// IE6
		String sSrchDtlValue = commCd.getSrchDtlValue();
		try {
			if (sSrchDtlValue != null) {
				sSrchDtlValue = URLEncoder.encode(commCd.getSrchDtlValue(), "UTF-8");
				sSrchDtlValue = sSrchDtlValue.replace("%", "@");
			}
		} catch (UnsupportedEncodingException e) {
			log.error("ERROR URLEncoder.encode() - {0}" + new Object[] { sSrchDtlValue });
			sSrchDtlValue = "";
		}
		commCd.setSrchDtlValue(sSrchDtlValue);

		return SUCCESS;
	}

	public String deleteCommCd() {

		if (commCd == null) {
			commCd = new CommCd();
		}

		this.setStep("CallServiceDeleteCommCd");
		commonCodeService.deleteCommCd(commCd);

		// IE6
		String sSrchGrpValue = commCd.getSrchGrpValue();
		try {
			if (sSrchGrpValue != null) {
				sSrchGrpValue = URLEncoder.encode(commCd.getSrchGrpValue(), "UTF-8");
				sSrchGrpValue = sSrchGrpValue.replace("%", "@");
			}
		} catch (UnsupportedEncodingException e) {
			log.error("ERROR URLEncoder.encode() - {0}" + new Object[] { sSrchGrpValue });
			sSrchGrpValue = "";
		}
		commCd.setSrchGrpValue(sSrchGrpValue);
		// IE6
		String sSrchDtlValue = commCd.getSrchDtlValue();
		try {
			if (sSrchDtlValue != null) {
				sSrchDtlValue = URLEncoder.encode(commCd.getSrchDtlValue(), "UTF-8");
				sSrchDtlValue = sSrchDtlValue.replace("%", "@");
			}
		} catch (UnsupportedEncodingException e) {
			log.error("ERROR URLEncoder.encode() - {0}" + new Object[] { sSrchDtlValue });
			sSrchDtlValue = "";
		}
		commCd.setSrchDtlValue(sSrchDtlValue);

		return SUCCESS;
	}

	public String initCacheCode() {

		try {

			// INIT CODE CACHE - WHO
			log.info("INIT CODE CACHE : " + CommonUtil.getLoginId(this.req.getSession()));

			this.setStep("CallServiceInitCacheCode");
			this.ssc.doCast(new SyncSignal(null, "code", "reload"));

		} catch (IOException e) {
			throw new ServiceException("공통코드를 새롭게 캐쉬하는 동안 에러가 발생 하였습니다.", e);
		}

		return SUCCESS;
	}

	public String confirmCacheCode() {

		if (commCd == null) {
			commCd = new CommCd();
		}

		if ("".equals(StringUtil.nvlStr(commCd.getAdMgrPswdNo()))) {
			return NONE;
		}

		AdMgr adMgr = new AdMgr();
		String adMgrPswdNo = "";

		this.setStep("CallServiceConfirmCacheCode");
		AdSession adSession = (AdSession) this.req.getSession(true).getAttribute(Constants.ADMIN_AUTH_SESSION_KEY);
		if (adSession != null) {
			adMgr = adSession.getAdMgr();
			adMgrPswdNo = adMgr.getPswdNo();
		} else {
			chkResult = ERROR;
			return ERROR;
		}

		if (!adMgrPswdNo.equals(StringUtil.nvlStr(commCd.getAdMgrPswdNo()))) {
			chkResult = ERROR;
			return ERROR;
		}

		chkResult = SUCCESS;
		return SUCCESS;
	}

	public CommCd getCommCd() {
		return commCd;
	}

	public void setCommCd(CommCd commCd) {
		this.commCd = commCd;
	}

	public List<CommCd> getCommCdList() {
		return commCdList;
	}

	public void setCommCdList(List<CommCd> commCdList) {
		this.commCdList = commCdList;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public long getSrchCnt() {
		return srchCnt;
	}

	public void setSrchCnt(long srchCnt) {
		this.srchCnt = srchCnt;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public String getChkResult() {
		return chkResult;
	}

	public void setChkResult(String chkResult) {
		this.chkResult = chkResult;
	}

}
