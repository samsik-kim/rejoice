package com.omp.admin.member.membermgr.action;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.omp.admin.common.Constants;
import com.omp.admin.common.util.CommonUtil;
import com.omp.admin.community.postscript.model.DpProdNoti;
import com.omp.admin.community.postscript.model.UsMemberPunish;
import com.omp.admin.member.membermgr.service.PunishMemberService;
import com.omp.commons.action.BaseAction;
import com.omp.commons.communicate.mail.SendMailModel;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.model.PagenateList;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.StringUtil;

@SuppressWarnings("serial")
public class PunishMemberAction extends BaseAction {

	private GLogger log = new GLogger(PunishMemberAction.class);

	private PunishMemberService punishMemberService;

	private UsMemberPunish usMemberPunish = null;
	private List<UsMemberPunish> usMemberPunishList = null;
	private List<DpProdNoti> dpProdNotiList = null;

	private String srchFlag = "";
	private String userMemYn;

	private long srchCnt = 0;

	public PunishMemberAction() {
		punishMemberService = new PunishMemberService();
	}

	@SuppressWarnings("rawtypes")
	public String selectUsMemberPunishList() {

		if (usMemberPunish == null) {
			usMemberPunish = new UsMemberPunish();
		}

		srchFlag = ("".equals(StringUtil.nvlStr(this.req.getParameter("srchFlag")))) ? srchFlag : this.req.getParameter("srchFlag");
		if ("".equals(StringUtil.nvlStr(srchFlag))) {
			return SUCCESS;
		}

		if ("".equals(StringUtil.nvlStr(usMemberPunish.getStartDate()))) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DATE, -7);
			// calendar.getTime();
			usMemberPunish.setStartDate(DateUtil.getDateToStr(calendar.getTime(), "yyyyMMdd") + "000000");
			usMemberPunish.setEndDate(DateUtil.getDateToStr(DateUtil.addMonth(new Date(), 0), "yyyyMMdd") + "235959");
		} else {
			usMemberPunish.setStartDate(usMemberPunish.getStartDate().replaceAll("-", "") + "000000");
			usMemberPunish.setEndDate(usMemberPunish.getEndDate().replaceAll("-", "") + "235959");
		}

		usMemberPunishList = punishMemberService.selectUsMemberPunishPagingList(usMemberPunish);
		srchCnt = ((PagenateList) usMemberPunishList).getTotalCount();

		if (log.isDebugEnabled())
			log.debug("usMemberPunishList.size() : " + usMemberPunishList.size());

		return SUCCESS;
	}

	public String selectUsMemberPunish() {

		if (usMemberPunish == null) {
			usMemberPunish = new UsMemberPunish();
		}

		usMemberPunish.setMbrNo(StringUtil.nvlStr(this.req.getParameter("mbrNo")));
		usMemberPunish.setMbrId(StringUtil.nvlStr(this.req.getParameter("mbrId")));

		return SUCCESS;
	}

	public String updateMemberPunish() {

		if (usMemberPunish == null) {
			usMemberPunish = new UsMemberPunish();
		}

		usMemberPunish.getMbrNo();

		//Setting EMail
		String sMailTemplate = this.conf.getString("omp.admin.punish.mail.telmpate.id.del.punish");
		if ("".equals(StringUtil.nvlStr(sMailTemplate)))
			throw new ServiceException("이용제한해제안내 메일 템플릿 정보가 없습니다.");

		String sMailTitle = this.conf.getString("omp.admin.punish.mail.telmpate.id.del.punish.title");
		if ("".equals(StringUtil.nvlStr(sMailTitle)))
			throw new ServiceException("이용제한해제안내 메일 템플릿 제목 정보가 없습니다.");

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
		sendMailModel.setTemplateId(sMailTemplate); // 템플릿 아이디
		//sendMailModel.setRefAppId(sMailRefAppId); // 연관 업무 아이디
		//sendMailModel.setRefDataId("" + dpBadnoti.getNotiNo()); // 연관 데이터의 아이디
		//sendMailModel.setToAddr(toAddr); // 수신자 주소
		sendMailModel.setFromAddr(sMailFromAddr); // 발신자 주소
		sendMailModel.setFromName(sMailFromName);
		sendMailModel.setReturnAddr(sMailReturnAddr); // 반송 수신자 주소
		sendMailModel.setSubject(sMailTitle); // 제목
		sendMailModel.setContentData(sDevBaseUrl); // 데이터

		usMemberPunish.setUpdId(CommonUtil.getLoginId(this.req.getSession()));

		punishMemberService.updateMemberPunish(usMemberPunish, sendMailModel);

		if (log.isInfoEnabled())
			log.info("CLOSE PUNISH MEMBER - MBR_NO : {0}", new Object[] { usMemberPunish.getMbrNo() });

		return SUCCESS;
	}

	@SuppressWarnings("rawtypes")
	public String selectBadProdNotiList() {

		if (usMemberPunish == null) {
			usMemberPunish = new UsMemberPunish();
		}

		DpProdNoti dpProdNoti = new DpProdNoti();
		dpProdNoti.setMbrNo(usMemberPunish.getMbrNo());

		dpProdNotiList = punishMemberService.selectBadProdNotiPagingList(dpProdNoti);
		srchCnt = ((PagenateList) dpProdNotiList).getTotalCount();

		return SUCCESS;
	}

	@SuppressWarnings("rawtypes")
	public String selectUsMemberPunishMbrNoList() {

		if (usMemberPunish == null) {
			usMemberPunish = new UsMemberPunish();
		}

		String sSearchValue = usMemberPunish.getSearchValue();
		String sStartDate = usMemberPunish.getStartDate();
		String sEndDate = usMemberPunish.getEndDate();

		usMemberPunishList = punishMemberService.selectUsMemberPunishMbrNoPagingList(usMemberPunish);
		srchCnt = ((PagenateList) usMemberPunishList).getTotalCount();

		usMemberPunish = punishMemberService.selectUsMemberPunishMbrNo(usMemberPunish);
		usMemberPunish.setSearchValue(sSearchValue);
		usMemberPunish.setStartDate(sStartDate);
		usMemberPunish.setEndDate(sEndDate);

		return SUCCESS;
	}

	public UsMemberPunish getUsMemberPunish() {
		return usMemberPunish;
	}

	public void setUsMemberPunish(UsMemberPunish usMemberPunish) {
		this.usMemberPunish = usMemberPunish;
	}

	public List<UsMemberPunish> getUsMemberPunishList() {
		return usMemberPunishList;
	}

	public void setUsMemberPunishList(List<UsMemberPunish> usMemberPunishList) {
		this.usMemberPunishList = usMemberPunishList;
	}

	public String getSrchFlag() {
		return srchFlag;
	}

	public void setSrchFlag(String srchFlag) {
		this.srchFlag = srchFlag;
	}

	public long getSrchCnt() {
		return srchCnt;
	}

	public void setSrchCnt(long srchCnt) {
		this.srchCnt = srchCnt;
	}

	public List<DpProdNoti> getDpProdNotiList() {
		return dpProdNotiList;
	}

	public void setDpProdNotiList(List<DpProdNoti> dpProdNotiList) {
		this.dpProdNotiList = dpProdNotiList;
	}

	/**
	 * @return the userMemYn
	 */
	public String getUserMemYn() {
		return userMemYn;
	}

	/**
	 * @param userMemYn the userMemYn to set
	 */
	public void setUserMemYn(String userMemYn) {
		if(userMemYn == null) userMemYn = "N";
		
		this.userMemYn = userMemYn;
	}

}
