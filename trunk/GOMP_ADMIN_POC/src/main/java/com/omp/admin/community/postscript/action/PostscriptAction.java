package com.omp.admin.community.postscript.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.omp.admin.adminmgr.auth.model.AdMgr;
import com.omp.admin.adminmgr.auth.model.AdSession;
import com.omp.admin.common.Constants;
import com.omp.admin.common.util.CommonUtil;
import com.omp.admin.community.postscript.model.DpBadnoti;
import com.omp.admin.community.postscript.model.DpProdNoti;
import com.omp.admin.community.postscript.service.PostscriptService;
import com.omp.commons.action.BaseAction;
import com.omp.commons.commcode.CacheCommCode;
import com.omp.commons.commcode.model.CommCode;
import com.omp.commons.communicate.mail.SendMailModel;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.text.LocalizeMessage;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.StringUtil;

/**
 * 사용후기 액션 클래스.
 * @author soohee
 * 
 */
@SuppressWarnings("serial")
public class PostscriptAction extends BaseAction {

	private GLogger log = new GLogger(PostscriptAction.class);

	private PostscriptService postscriptService;

	private DpProdNoti dpProdNoti = null;
	private List<DpProdNoti> dpProdNotiList = null;

	private DpBadnoti dpBadnoti = null;
	private List<DpBadnoti> dpBadnotiList = null;

	private Map<String, Object> result;

	private List<CommCode> commCodeList;

	private String selectedNotiNo = null;

	private String srchFlag = "";

	public PostscriptAction() {
		postscriptService = new PostscriptService();
	}

	/**
	 * 사용후기 검색
	 * @return
	 */
	public String selectDpProdNotiList() {

		if (dpProdNoti == null) {
			dpProdNoti = new DpProdNoti();
		}

		result = makeProdNotiCriteria(result);

		srchFlag = ("".equals(StringUtil.nvlStr(this.req.getParameter("srchFlag")))) ? srchFlag : this.req.getParameter("srchFlag");
		if ("".equals(StringUtil.nvlStr(srchFlag))) {
			return SUCCESS;
		}

		String sSrchBadnotiYn = ("".equals(StringUtil.nvlStr(this.req.getParameter("srchBadnotiYn")))) ? dpProdNoti.getSrchBadnotiYn()
				: this.req.getParameter("srchBadnotiYn");
		String sSrchDelYn = ("".equals(StringUtil.nvlStr(this.req.getParameter("srchDelYn")))) ? dpProdNoti.getSrchDelYn() : this.req
				.getParameter("srchDelYn");
		String sStartDate = ("".equals(StringUtil.nvlStr(this.req.getParameter("startDate")))) ? dpProdNoti.getStartDate() : this.req
				.getParameter("startDate");
		String sEndDate = ("".equals(StringUtil.nvlStr(this.req.getParameter("endDate")))) ? dpProdNoti.getEndDate() : this.req
				.getParameter("endDate");
		String sSearchType = ("".equals(StringUtil.nvlStr(this.req.getParameter("searchType")))) ? dpProdNoti.getSearchType() : this.req
				.getParameter("searchType");
		String sSearchValue = ("".equals(StringUtil.nvlStr(this.req.getParameter("searchValue")))) ? dpProdNoti.getSearchValue() : this.req
				.getParameter("searchValue");
		String sPageNo = ("".equals(StringUtil.nvlStr(this.req.getParameter("pageNo")))) ? "" + dpProdNoti.getPage().getNo() : this.req
				.getParameter("pageNo");
		sPageNo = StringUtil.nvlStr(sPageNo, "1");

		// IE6
		try {
			if (sSearchValue != null) {
				sSearchValue = StringUtil.nvlStr(sSearchValue).replace("@", "%");
				sSearchValue = URLDecoder.decode(sSearchValue, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			log.error("ERROR URLDecoder.decode() - {0}" + new Object[] { sSearchValue });
			sSearchValue = "";
		}

		dpProdNoti.setSrchBadnotiYn(sSrchBadnotiYn);
		dpProdNoti.setSrchDelYn(sSrchDelYn);
		dpProdNoti.setStartDate(sStartDate);
		dpProdNoti.setEndDate(sEndDate);
		dpProdNoti.setSearchType(CommonUtil.removeSpecial(sSearchType));
		dpProdNoti.setSearchValue(CommonUtil.removeSpecial(sSearchValue));
		dpProdNoti.getPage().setNo(Integer.parseInt(sPageNo));

		if ("".equals(StringUtil.nvlStr(dpProdNoti.getStartDate()))) {
			dpProdNoti.setStartDate(DateUtil.getDateToStr(DateUtil.addMonth(new Date(), 0), "yyyyMMdd") + "000000");
			dpProdNoti.setEndDate(DateUtil.getDateToStr(DateUtil.addMonth(new Date(), 0), "yyyyMMdd") + "235959");
		} else {
			dpProdNoti.setStartDate(dpProdNoti.getStartDate().replaceAll("-", "") + "000000");
			dpProdNoti.setEndDate(dpProdNoti.getEndDate().replaceAll("-", "") + "235959");
		}
		dpProdNoti.setBadnotiYn(dpProdNoti.getSrchBadnotiYn());
		dpProdNoti.setDelYn(dpProdNoti.getSrchDelYn());
		dpProdNoti.setSearchType(CommonUtil.removeSpecial(dpProdNoti.getSearchType()));
		dpProdNoti.setSearchValue(CommonUtil.removeSpecial(dpProdNoti.getSearchValue()));

		//if (log.isDebugEnabled())
		//	log.debug(dpProdNoti.toString());

		dpProdNotiList = postscriptService.selectDpProdNotiPagingList(dpProdNoti);

		//if (log.isDebugEnabled())
		//	log.debug("dpProdNotiList.size() : " + dpProdNotiList.size());

		return SUCCESS;
	}

	private Map<String, Object> makeProdNotiCriteria(Map<String, Object> result) {

		if (result == null) {
			result = new HashMap<String, Object>();
		}

		// badnotiYn Y - 惡性. N - 正常
		Map<String, String> radioMap1 = new LinkedHashMap<String, String>();
		radioMap1.put("", LocalizeMessage.getLocalizedMessage("전체"));
		radioMap1.put(Constants.NO, LocalizeMessage.getLocalizedMessage("정상"));
		radioMap1.put(Constants.YES, LocalizeMessage.getLocalizedMessage("불량"));
		//radioMap1.put("", "全部");
		//radioMap1.put(Constants.NO, "正常");
		//radioMap1.put(Constants.YES, "惡性");

		// delYn Y - 隱藏, N - 公開
		Map<String, String> radioMap2 = new LinkedHashMap<String, String>();
		radioMap2.put("", LocalizeMessage.getLocalizedMessage("전체"));
		radioMap2.put(Constants.NO, LocalizeMessage.getLocalizedMessage("노출"));
		radioMap2.put(Constants.YES, LocalizeMessage.getLocalizedMessage("숨김"));
		//radioMap2.put("", "全部 ");
		//radioMap2.put(Constants.NO, "公開");
		//radioMap2.put(Constants.YES, "隱藏");

		Map<String, String> selectMap = new LinkedHashMap<String, String>();
		selectMap.put("byMbrId", LocalizeMessage.getLocalizedMessage("아이디"));
		selectMap.put("btProdNm", LocalizeMessage.getLocalizedMessage("상품명"));
		selectMap.put("byNotiDscr", LocalizeMessage.getLocalizedMessage("사용후기"));
		//selectMap.put("byMbrId", "帳號");
		//selectMap.put("btProdNm", "產品名稱");
		//selectMap.put("byNotiDscr", "產品評論");

		result.put("radioMap1", radioMap1);
		result.put("radioMap2", radioMap2);
		result.put("selectMap", selectMap);

		return result;
	}

	/**
	 * 사용후기 조회
	 * @return
	 */
	public String selectDpBadnoti() {

		if (dpBadnoti == null) {
			dpBadnoti = new DpBadnoti();
		}

		selectedNotiNo = StringUtil.nvlStr(this.req.getParameter("selectedNotiNo"));
		dpBadnoti.setNotiNo(Long.parseLong(StringUtil.nvlStr(this.req.getParameter("notiNo"), "0")));
		dpBadnoti.setMbrNo(StringUtil.nvlStr(this.req.getParameter("mbrNo")));

		String sBadnotiYn = StringUtil.nvlStr(this.req.getParameter("badnotiYn"));

		if ("Y".equalsIgnoreCase(sBadnotiYn)) {

			dpBadnoti = postscriptService.selectDpBadnoti(dpBadnoti);

		} else {

			commCodeList = CacheCommCode.getCommCode(Constants.CODE_BADNOTI_RPT);

			AdSession adSession = (AdSession) this.req.getSession().getAttribute(Constants.ADMIN_AUTH_SESSION_KEY);
			if (adSession != null) {
				AdMgr adMgr = adSession.getAdMgr();

				dpBadnoti.setRegId(adMgr.getMgrId());
				dpBadnoti.setRegNm(adMgr.getMgrNm());
			}
			dpBadnoti.setRegDttm(DateUtil.getDateToStr(new Date(), "yyyyMMddHHmmss"));

		}

		return SUCCESS;
	}

	/**
	 * 사용후기 신고 등록
	 * @return
	 */
	public String insertBadnoti() {

		if (dpBadnoti == null) {
			dpBadnoti = new DpBadnoti();
		}

		if (log.isDebugEnabled())
			log.debug("selectedNotiNo : " + selectedNotiNo);

		//Setting EMail
		String sMailTemplate1st = this.conf.getString("omp.admin.punish.mail.telmpate.id.del.prodnoti");
		String sMailTemplate2nd = this.conf.getString("omp.admin.punish.mail.telmpate.id.ins.punish");
		//String sMailTemplate3rd = this.conf.getString("omp.admin.punish.mail.telmpate.id.del.punish");
		if ("".equals(StringUtil.nvlStr(sMailTemplate1st)))
			throw new ServiceException("게시물삭제 메일 템플릿 정보가 없습니다.");
		if ("".equals(StringUtil.nvlStr(sMailTemplate2nd)))
			throw new ServiceException("이용제한안내 메일 템플릿 정보가 없습니다.");
		//if ("".equals(StringUtil.nvlStr(sMailTemplate3rd)))
		//	throw new ServiceException("이용제한해제안내 메일 템플릿 정보가 없습니다.");

		String sMailFromAddr = this.conf.getString("omp.admin.punish.mail.from.addr");
		String sMailFromName = StringUtil.nvlStr(this.conf.getString("omp.admin.punish.mail.from.name"), "Whoopy Admin");
		String sMailReturnAddr = this.conf.getString("omp.admin.punish.mail.return.addr");
		if ("".equals(StringUtil.nvlStr(sMailFromAddr)))
			throw new ServiceException("징계회원 메일 발송의 발신자 주소 정보가 없습니다.");
		if ("".equals(StringUtil.nvlStr(sMailReturnAddr)))
			throw new ServiceException("징계회원 메일 발송의 반송 수신자 주소 정보가 없습니다.");

		String sDevBaseUrl = this.conf.getString("omp.common.url-prefix.http.dev") + this.conf.getString("omp.common.path.context.dev")
				+ Constants.DEV_MAIN_URL;

		SendMailModel sendMailModel = new SendMailModel();
		//sendMailModel.setTemplateId(sMailTemplate1st); // 템플릿 아이디
		//sendMailModel.setRefAppId(sMailRefAppId); // 연관 업무 아이디
		//sendMailModel.setRefDataId("" + dpBadnoti.getNotiNo()); // 연관 데이터의 아이디
		//sendMailModel.setToAddr(toAddr); // 수신자 주소
		sendMailModel.setFromAddr(sMailFromAddr); // 발신자 주소
		sendMailModel.setFromName(sMailFromName);
		sendMailModel.setReturnAddr(sMailReturnAddr); // 반송 수신자 주소
		//sendMailModel.setSubject(subject); // 제목
		sendMailModel.setContentData(sDevBaseUrl); // 데이터

		dpBadnoti.setMailTemplate1st(sMailTemplate1st);
		dpBadnoti.setMailTemplate2nd(sMailTemplate2nd);
		//dpBadnoti.setMailTemplate3rd(sMailTemplate3rd);

		dpBadnoti.setRegId(CommonUtil.getLoginId(this.req.getSession()));
		dpBadnoti.setUpdId(CommonUtil.getLoginId(this.req.getSession()));

		if (selectedNotiNo.indexOf(',') > 0) {

			String[] arrSelectedNotiNo = selectedNotiNo.split(",");
			for (int i = 0; i < arrSelectedNotiNo.length; i++) {

				dpBadnoti.setNotiNo(Long.parseLong(arrSelectedNotiNo[i]));

				DpProdNoti rtnDpProdNoti = new DpProdNoti();
				rtnDpProdNoti.setNotiNo(Long.parseLong(arrSelectedNotiNo[i]));
				rtnDpProdNoti = postscriptService.selectDpProdNoti(rtnDpProdNoti);
				dpBadnoti.setMbrNo(rtnDpProdNoti.getMbrNo());

				if (log.isDebugEnabled())
					log.debug(dpBadnoti.toString());

				postscriptService.insertBadnoti(dpBadnoti, sendMailModel);
			}

		} else {

			if (!"".equals(selectedNotiNo)) {
				dpBadnoti.setNotiNo(Long.parseLong(selectedNotiNo));
			}

			//DpBadnoti rtnDpBadnoti = new DpBadnoti();
			//rtnDpBadnoti = postscriptService.selectDpBadnoti(dpBadnoti);
			//dpBadnoti.setMbrNo(rtnDpBadnoti.getMbrNo());

			DpProdNoti rtnDpProdNoti = new DpProdNoti();
			rtnDpProdNoti.setNotiNo(dpBadnoti.getNotiNo());
			rtnDpProdNoti = postscriptService.selectDpProdNoti(rtnDpProdNoti);
			if (rtnDpProdNoti == null) {
				throw new ServiceException("사용후기 내용을 가져오는 동안 에러가 발생 하였습니다.");
			}

			dpBadnoti.setMbrNo(rtnDpProdNoti.getMbrNo());

			if (log.isDebugEnabled())
				log.debug(dpBadnoti.toString());

			postscriptService.insertBadnoti(dpBadnoti, sendMailModel);
		}

		// IE6
		String sSearchValue = dpProdNoti.getSearchValue();
		try {
			if (sSearchValue != null) {
				sSearchValue = URLEncoder.encode(dpProdNoti.getSearchValue(), "UTF-8");
				sSearchValue = sSearchValue.replace("%", "@");
			}
		} catch (UnsupportedEncodingException e) {
			log.error("ERROR URLEncoder.encode() - {0}" + new Object[] { sSearchValue });
			sSearchValue = "";
		}
		if (log.isDebugEnabled())
			log.debug("dpProdNoti.getSearchValue() : " + sSearchValue);

		dpProdNoti.setSearchValue(sSearchValue);

		return SUCCESS;
	}

	/**
	 * 사용후기 삭제 처리
	 * @return
	 */
	public String updateProdNotiDelYn() {

		if (dpProdNoti == null) {
			dpProdNoti = new DpProdNoti();
		}

		if (log.isDebugEnabled())
			log.debug("selectedNotiNo : " + selectedNotiNo);
		if (log.isDebugEnabled())
			log.debug("sDelYn : " + dpProdNoti.getDelYn());

		dpProdNoti.setRegId(CommonUtil.getLoginId(this.req.getSession()));

		postscriptService.updateProdNotiDelYn(selectedNotiNo, dpProdNoti);

		// IE6
		String sSearchValue = dpProdNoti.getSearchValue();
		try {
			if (sSearchValue != null) {
				sSearchValue = URLEncoder.encode(dpProdNoti.getSearchValue(), "UTF-8");
				sSearchValue = sSearchValue.replace("%", "@");
			}
		} catch (UnsupportedEncodingException e) {
			log.error("ERROR URLEncoder.encode() - {0}" + new Object[] { sSearchValue });
			sSearchValue = "";
		}
		if (log.isDebugEnabled())
			log.debug("dpProdNoti.getSearchValue() : " + sSearchValue);

		dpProdNoti.setSearchValue(sSearchValue);

		return SUCCESS;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public DpProdNoti getDpProdNoti() {
		return dpProdNoti;
	}

	public void setDpProdNoti(DpProdNoti dpProdNoti) {
		this.dpProdNoti = dpProdNoti;
	}

	public List<DpProdNoti> getDpProdNotiList() {
		return dpProdNotiList;
	}

	public void setDpProdNotiList(List<DpProdNoti> dpProdNotiList) {
		this.dpProdNotiList = dpProdNotiList;
	}

	public DpBadnoti getDpBadnoti() {
		return dpBadnoti;
	}

	public void setDpBadnoti(DpBadnoti dpBadnoti) {
		this.dpBadnoti = dpBadnoti;
	}

	public List<DpBadnoti> getDpBadnotiList() {
		return dpBadnotiList;
	}

	public void setDpBadnotiList(List<DpBadnoti> dpBadnotiList) {
		this.dpBadnotiList = dpBadnotiList;
	}

	public List<CommCode> getCommCodeList() {
		return commCodeList;
	}

	public void setCommCodeList(List<CommCode> commCodeList) {
		this.commCodeList = commCodeList;
	}

	public String getSelectedNotiNo() {
		return selectedNotiNo;
	}

	public void setSelectedNotiNo(String selectedNotiNo) {
		this.selectedNotiNo = selectedNotiNo;
	}

	public String getSrchFlag() {
		return srchFlag;
	}

	public void setSrchFlag(String srchFlag) {
		this.srchFlag = srchFlag;
	}

}
