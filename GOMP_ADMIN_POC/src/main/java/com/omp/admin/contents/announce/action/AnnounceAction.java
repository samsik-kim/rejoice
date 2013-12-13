/* 
 * COPYRIGHT(c) SK telecom 2008
 * This software is the proprietary information of SK telecom.
 */
package com.omp.admin.contents.announce.action;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.omp.admin.adminmgr.auth.model.AdMgr;
import com.omp.admin.common.Constants;
import com.omp.admin.common.SessionManager;
import com.omp.admin.common.util.CommonUtil;
import com.omp.admin.contents.announce.model.DpAnoc;
import com.omp.admin.contents.announce.service.AnnounceService;
import com.omp.commons.action.BaseAction;
import com.omp.commons.text.LocalizeMessage;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.StringUtil;

/**
 * <pre>
 * @Description SC Category
 * @author 
 * @since 2009.04.09
 * </pre>
 */
@SuppressWarnings("serial")
public class AnnounceAction extends BaseAction {

	private static final GLogger log = new GLogger(AnnounceAction.class);

	private AnnounceService announceService = null;

	private List<DpAnoc> dpAnocList;
	private DpAnoc dpAnoc;

	private String selectedAnocSeq;

	private Map<String, Object> result;

	private String srchFlag = "";

	public AnnounceAction() {
		announceService = new AnnounceService();
	}

	private Map<String, Object> makeSearchCondition(Map<String, Object> result) {

		if (result == null) {
			result = new HashMap<String, Object>();
		}

		Map<String, String> radioMap = new LinkedHashMap<String, String>();

		radioMap.put("", LocalizeMessage.getLocalizedMessage("전체"));
		radioMap.put(Constants.CODE_ANOC_INSPECT, LocalizeMessage.getLocalizedMessage("점검"));
		radioMap.put(Constants.CODE_ANOC_NOTICE, LocalizeMessage.getLocalizedMessage("알림"));

		result.put("radioMap", radioMap);

		return result;
	}

	public String selectAnnounceList() {

		if (dpAnoc == null) {
			dpAnoc = new DpAnoc();
		}

		result = makeSearchCondition(result);

		srchFlag = ("".equals(StringUtil.nvlStr(this.req.getParameter("srchFlag")))) ? srchFlag : this.req.getParameter("srchFlag");
		if ("".equals(StringUtil.nvlStr(srchFlag))) {
			return SUCCESS;
		}

		String sStartDate = ("".equals(StringUtil.nvlStr(this.req.getParameter("startDate")))) ? dpAnoc.getStartDate() : this.req
				.getParameter("startDate");
		String sEndDate = ("".equals(StringUtil.nvlStr(this.req.getParameter("endDate")))) ? dpAnoc.getEndDate() : this.req
				.getParameter("endDate");
		dpAnoc.setStartDate(sStartDate);
		dpAnoc.setEndDate(sEndDate);

		if ("".equals(StringUtil.nvlStr(dpAnoc.getStartDate()))) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DATE, -7);
			// calendar.getTime();
			dpAnoc.setAnocStrtDttm(DateUtil.getDateToStr(calendar.getTime(), "yyyyMMdd") + "000000");
			dpAnoc.setAnocEndDttm(DateUtil.getDateToStr(DateUtil.addMonth(new Date(), 0), "yyyyMMdd") + "235959");
		} else {
			dpAnoc.setAnocStrtDttm(dpAnoc.getStartDate().replaceAll("-", "") + "000000");
			dpAnoc.setAnocEndDttm(dpAnoc.getEndDate().replaceAll("-", "") + "235959");
		}
		if ("".equals(dpAnoc.getSrchAnocCd())) {
			dpAnoc.setAnocCd(null);
		} else {
			dpAnoc.setAnocCd(dpAnoc.getSrchAnocCd());
		}
		dpAnoc.setDelYn("N");

		log.debug("##### dpAnoc.getAnocCd() : " + dpAnoc.getAnocCd());
		log.debug("##### dpAnoc.getAnocStrtDttm() : " + dpAnoc.getAnocStrtDttm());
		log.debug("##### dpAnoc.getAnocEndDttm() : " + dpAnoc.getAnocEndDttm());

		dpAnocList = announceService.selectAnnounceList(dpAnoc);

		return SUCCESS;
	}

	public String selectAnnounce() {

		if (dpAnoc == null) {
			dpAnoc = new DpAnoc(0);
		}

		result = makeSearchCondition(result);

		String sAnocSeq = ("".equals(StringUtil.nvlStr(this.req.getParameter("anocSeq")))) ? "" + dpAnoc.getAnocSeq() : this.req
				.getParameter("anocSeq");
		sAnocSeq = ("".equals(sAnocSeq)) ? "0" : sAnocSeq;
		dpAnoc.setAnocSeq(Integer.parseInt(sAnocSeq));

		String sStartDate = dpAnoc.getStartDate();
		String sEndDate = dpAnoc.getEndDate();
		int nPageNo = dpAnoc.getPage().getNo();

		if (log.isDebugEnabled())
			log.debug("##### dpAnoc.getAnocSeq() : " + dpAnoc.getAnocSeq());

		if (dpAnoc.getAnocSeq() != 0) { // UPDATE

			if (log.isDebugEnabled())
				log.debug("selectAnnounce() ### " + dpAnoc.getAnocSeq() + " ###");

			dpAnoc = announceService.selectAnnounce(dpAnoc);
			dpAnoc.setAnocStrtDd(dpAnoc.getAnocStrtDttm().substring(0, 8));
			dpAnoc.setAnocStrtHh(dpAnoc.getAnocStrtDttm().substring(8, 10));
			dpAnoc.setAnocStrtMm(dpAnoc.getAnocStrtDttm().substring(10, 12));
			dpAnoc.setAnocEndDd(dpAnoc.getAnocEndDttm().substring(0, 8));
			dpAnoc.setAnocEndHh(dpAnoc.getAnocEndDttm().substring(8, 10));
			dpAnoc.setAnocEndMm(dpAnoc.getAnocEndDttm().substring(10, 12));

		} else {

			if (log.isDebugEnabled())
				log.debug("change Announce INSERT MODE");

			dpAnoc.setAnocEndDttm(DateUtil.getDateStr(new Date(), "yyyyMMdd") + "235959");
			log.debug("dpAnoc.getAnocEndDttm() : " + dpAnoc.getAnocEndDttm());

			AdMgr adMgr = SessionManager.getSessionAdMgr(this.req);
			dpAnoc.setRegId(adMgr.getMgrId());

		}

		dpAnoc.setStartDate(sStartDate);
		dpAnoc.setEndDate(sEndDate);
		dpAnoc.getPage().setNo(nPageNo);

		return SUCCESS;
	}

	public String insertAnnounce() {

		if (dpAnoc == null) {
			dpAnoc = new DpAnoc();
		}

		dpAnoc.setAnocStrtDttm(dpAnoc.getAnocStrtDd() + dpAnoc.getAnocStrtHh() + dpAnoc.getAnocStrtMm() + "00");
		dpAnoc.setAnocEndDttm(dpAnoc.getAnocEndDd() + dpAnoc.getAnocEndHh() + dpAnoc.getAnocEndMm() + "00");
		dpAnoc.setAnocStrtDttm(dpAnoc.getAnocStrtDttm().replaceAll("-", ""));
		dpAnoc.setAnocEndDttm(dpAnoc.getAnocEndDttm().replaceAll("-", ""));

		if (log.isDebugEnabled()) {
			log.debug("getAnocSeq() : " + dpAnoc.getAnocSeq());
			//log.debug("getAnocTitl() : " + dpAnoc.getAnocTitl());
			//log.debug("getAnocCont() : " + dpAnoc.getAnocCont());
			//log.debug("getAnocStrtDttm() : " + dpAnoc.getAnocStrtDttm());
			//log.debug("getAnocEndDttm() : " + dpAnoc.getAnocEndDttm());
			log.debug("getAnocCd() : " + dpAnoc.getAnocCd());
		}

		if (dpAnoc.getAnocSeq() == 0) {

			dpAnoc.setRegId(CommonUtil.getLoginId(this.req.getSession()));
			announceService.insertAnnounce(dpAnoc);

		} else {

			dpAnoc.setUpdId(CommonUtil.getLoginId(this.req.getSession()));
			announceService.updateAnnounce(dpAnoc);

		}

		return SUCCESS;
	}

	public String updateAnnounce() {

		if (dpAnoc == null) {
			dpAnoc = new DpAnoc();
		}

		dpAnoc.setAnocSeq(dpAnoc.getAnocSeq());
		dpAnoc.setAnocStrtDttm(dpAnoc.getAnocStrtDd() + dpAnoc.getAnocStrtHh() + dpAnoc.getAnocStrtMm() + "00");
		dpAnoc.setAnocEndDttm(dpAnoc.getAnocEndDd() + dpAnoc.getAnocEndHh() + dpAnoc.getAnocEndMm() + "00");
		dpAnoc.setAnocStrtDttm(dpAnoc.getAnocStrtDttm().replaceAll("-", ""));
		dpAnoc.setAnocEndDttm(dpAnoc.getAnocEndDttm().replaceAll("-", ""));

		if (log.isDebugEnabled()) {
			log.debug("getAnocSeq() : " + dpAnoc.getAnocSeq());
			log.debug("getAnocTitl() : " + dpAnoc.getAnocTitl());
			log.debug("getAnocCont() : " + dpAnoc.getAnocCont());
			log.debug("getAnocStrtDttm() : " + dpAnoc.getAnocStrtDttm());
			log.debug("getAnocEndDttm() : " + dpAnoc.getAnocEndDttm());
			log.debug("getAnocCd() : " + dpAnoc.getAnocCd());
		}

		dpAnoc.setUpdId(CommonUtil.getLoginId(this.req.getSession()));

		announceService.updateAnnounce(dpAnoc);

		return SUCCESS;
	}

	public String deleteAnnounce() {

		if (dpAnoc == null) {
			dpAnoc = new DpAnoc();
		}

		announceService.deleteAnnounce(dpAnoc, selectedAnocSeq);

		return SUCCESS;
	}

	public static String DeCode(String param) {

		StringBuffer sb = new StringBuffer();
		int pos = 0;
		boolean flg = true;

		if (param != null) {
			if (param.length() > 1) {
				while (flg) {
					String sLen = param.substring(pos, ++pos);
					int nLen = 0;

					try {
						nLen = Integer.parseInt(sLen);
					} catch (Exception e) {
						nLen = 0;
					}

					String code = "";
					if ((pos + nLen) > param.length())
						code = param.substring(pos);
					else
						code = param.substring(pos, (pos + nLen));

					pos += nLen;

					sb.append(((char) Integer.parseInt(code)));

					if (pos >= param.length())
						flg = false;
				}
			}
		} else {
			param = "";
		}

		return sb.toString();
	}

	public List<DpAnoc> getDpAnocList() {
		return dpAnocList;
	}

	public void setDpAnocList(List<DpAnoc> dpAnocList) {
		this.dpAnocList = dpAnocList;
	}

	public DpAnoc getDpAnoc() {
		return dpAnoc;
	}

	public void setDpAnoc(DpAnoc dpAnoc) {
		this.dpAnoc = dpAnoc;
	}

	public String getSelectedAnocSeq() {
		return selectedAnocSeq;
	}

	public void setSelectedAnocSeq(String selectedAnocSeq) {
		this.selectedAnocSeq = selectedAnocSeq;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public String getSrchFlag() {
		return srchFlag;
	}

	public void setSrchFlag(String srchFlag) {
		this.srchFlag = srchFlag;
	}

}
