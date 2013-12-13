package com.omp.admin.member.membermgr.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.omp.admin.common.Constants;
import com.omp.admin.community.postscript.model.DpProdNoti;
import com.omp.admin.community.postscript.model.UsMember;
import com.omp.admin.community.postscript.model.UsMemberPunish;
import com.omp.admin.community.postscript.service.PostscriptService;
import com.omp.commons.communicate.CommunicatorFactory;
import com.omp.commons.communicate.mail.MailSendAgent;
import com.omp.commons.communicate.mail.SendMailModel;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.GLogger;

public class PunishMemberService extends AbstractService {

	private GLogger log = new GLogger(PostscriptService.class);

	@SuppressWarnings("unchecked")
	public List<UsMemberPunish> selectUsMemberPunishPagingList(UsMemberPunish usMemberPunish) {
		return this.commonDAO.queryForPageList("Community.selectUsMemberPunishPagingList", usMemberPunish);
	}

	@SuppressWarnings("unchecked")
	public List<UsMemberPunish> selectUsMemberPunishMbrNoPagingList(UsMemberPunish usMemberPunish) {
		return this.commonDAO.queryForPageList("Community.selectUsMemberPunishMbrNoPagingList", usMemberPunish);
	}

	@SuppressWarnings("unchecked")
	public List<UsMemberPunish> selectUsMemberPunishList(UsMemberPunish usMemberPunish) {
		List<UsMemberPunish> retUsMemberPunishList = null;
		try {
			retUsMemberPunishList = this.commonDAO.queryForList("Community.selectUsMemberPunishList", usMemberPunish);
		} catch (SQLException e) {
			throw new ServiceException("징계회원 리스트를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return retUsMemberPunishList;
	}

	public UsMemberPunish selectUsMemberPunish(UsMemberPunish usMemberPunish) {
		UsMemberPunish retUsMemberPunish = null;
		try {
			retUsMemberPunish = (UsMemberPunish) this.commonDAO.queryForObject("Community.selectUsMemberPunish", usMemberPunish);
		} catch (SQLException e) {
			throw new ServiceException("징계회원 내용을 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return retUsMemberPunish;
	}

	public UsMemberPunish selectUsMemberPunishMbrNo(UsMemberPunish usMemberPunish) {
		UsMemberPunish retUsMemberPunish = null;
		try {
			retUsMemberPunish = (UsMemberPunish) this.commonDAO.queryForObject("Community.selectUsMemberPunishMbrNo", usMemberPunish);
		} catch (SQLException e) {
			throw new ServiceException("징계회원 MBR_NO를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return retUsMemberPunish;
	}

	public int updateMemberPunish(UsMemberPunish usMemberPunish, SendMailModel sendMailModel) {

		int nCnt = 0;

		try {

			daoManager.startTransaction();

			HashMap<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("mbrNo", usMemberPunish.getMbrNo());
			paramMap.put("mbrStatCd", Constants.MEM_STATUS_NORMAL);
			paramMap.put("updId", usMemberPunish.getUpdId());

			String sPnshEndDttm = DateUtil.getCurrentDate() + DateUtil.getCurrentTime();
			if (log.isDebugEnabled())
				log.debug("sPnshEndDttm : " + sPnshEndDttm);

			UsMemberPunish retUsMemberPunish = (UsMemberPunish) this.commonDAO.queryForObject("Community.selectUsMemberPunish",
					usMemberPunish);

			usMemberPunish.setHistYn("Y");
			usMemberPunish.setPnshNotiCnt(retUsMemberPunish.getPnshNotiCnt());
			usMemberPunish.setPnshStrtDttm(retUsMemberPunish.getPnshStrtDttm());
			usMemberPunish.setPnshEndDttm(sPnshEndDttm);
			usMemberPunish.setMbrPnshSeq(retUsMemberPunish.getMbrPnshSeq());
			
			this.commonDAO.update("Community.updateUsMemberPunish", usMemberPunish);
			this.commonDAO.update("Community.updateUsMember", paramMap);

			// 회원 상태 확인 후 정상 회원인 경우에만 발송.
			UsMember usMember = new UsMember();
			usMember.setMbrNo(usMemberPunish.getMbrNo());
			usMember = (UsMember) this.commonDAO.queryForObject("Community.selectUsMember", usMember);
			if (usMember == null) {
				log.warn("MEMBER NOT FOUND : {0}", new Object[] { usMemberPunish.getMbrNo() });
				sendMailModel.setToAddr(""); // 수신자 주소
				sendMailModel.setRefDataId(usMemberPunish.getMbrNo());
			} else {
				if (log.isDebugEnabled()) {
					log.debug("toAddr : " + usMember.getEmailAddr());
				}
				sendMailModel.setToAddr(usMember.getEmailAddr()); // 수신자 주소
				sendMailModel.setToName(usMember.getMbrNm());
				sendMailModel.setRefDataId(usMember.getMbrNo());
				//sendMailModel.se
			}

			Map<String, Object> mailContentMap;

			// DEV WEB BASE URL
			String sDevBaseUrl = (String) sendMailModel.getContentData();

			mailContentMap = new HashMap<String, Object>();
			mailContentMap.put("devBaseUrl", sDevBaseUrl);
			mailContentMap.put("mailSendDt", DateUtil.getDateToStr(new Date(), "yyyy. MM. dd"));
			mailContentMap.put("pnshCloseDscr", usMemberPunish.getPnshCloseDscr());
			//mailContentMap.put("mbrNm", sendMailModel.getToName()); // MAIL TITLE
			mailContentMap.put("mbrNm", usMemberPunish.getMbrId()); // MAIL TITLE
  
			sendMailModel.setRefAppId("PunishMember.updateMemberPunish.del.punish");
			sendMailModel.setContentData(mailContentMap); // 데이터
			log.info("MAIL del Prodnoti : {0}, {1}, {2}", new Object[] { sendMailModel.getRefDataId(), sendMailModel.getToAddr(),
					usMemberPunish.getMbrNo() });

			MailSendAgent mailSendAgent;

			mailSendAgent = CommunicatorFactory.getMailSendAgent();
			mailSendAgent.requestMailSend(sendMailModel);

			daoManager.commitTransaction();

		} catch (Exception ex) {
			throw new ServiceException("징계회원 정보를 변경하는 동안 에러가 발생 하였습니다.", ex);
		} finally {
			daoManager.endTransaction();
		}

		return nCnt;
	}

	@SuppressWarnings("unchecked")
	public List<DpProdNoti> selectBadProdNotiPagingList(DpProdNoti dpProdNoti) {
		return this.commonDAO.queryForPageList("Community.selectBadProdNotiPagingList", dpProdNoti);
	}

}
