package com.omp.admin.community.postscript.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.omp.admin.common.Constants;
import com.omp.admin.community.postscript.model.DpBadnoti;
import com.omp.admin.community.postscript.model.DpProdNoti;
import com.omp.admin.community.postscript.model.UsMember;
import com.omp.admin.community.postscript.model.UsMemberPunish;
import com.omp.commons.communicate.CommunicatorFactory;
import com.omp.commons.communicate.mail.MailSendAgent;
import com.omp.commons.communicate.mail.SendMailModel;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.StringUtil;
import com.omp.commons.util.config.ConfigProperties;

public class PostscriptService extends AbstractService {

	private GLogger log = new GLogger(PostscriptService.class);

	@SuppressWarnings("unchecked")
	public List<DpProdNoti> selectDpProdNotiPagingList(DpProdNoti dpProdNoti) throws ServiceException {
		return this.commonDAO.queryForPageList("Community.selectDpProdNotiPagingList", dpProdNoti);
	}

	@SuppressWarnings("unchecked")
	public List<DpProdNoti> selectDpProdNotiList(DpProdNoti dpProdNoti) throws ServiceException {
		List<DpProdNoti> retDpProdNotiList = null;
		try {
			retDpProdNotiList = this.commonDAO.queryForList("Community.selectDpProdNotiList", dpProdNoti);
		} catch (SQLException e) {
			throw new ServiceException("사용후기 리스트를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return retDpProdNotiList;
	}

	public DpProdNoti selectDpProdNoti(DpProdNoti dpProdNoti) throws ServiceException {
		DpProdNoti retDpProdNoti = null;
		try {
			retDpProdNoti = (DpProdNoti) this.commonDAO.queryForObject("Community.selectDpProdNoti", dpProdNoti);
		} catch (SQLException e) {
			throw new ServiceException("사용후기 내용을 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return retDpProdNoti;
	}

	public DpBadnoti selectDpBadnoti(DpBadnoti dpBadnoti) throws ServiceException {
		DpBadnoti retDpBadnoti = null;
		try {
			retDpBadnoti = (DpBadnoti) this.commonDAO.queryForObject("Community.selectDpBadnoti", dpBadnoti);
		} catch (SQLException e) {
			throw new ServiceException("사용후기 징계 내용을 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return retDpBadnoti;
	}

	@SuppressWarnings("unchecked")
	public void insertBadnoti(DpBadnoti dpBadnoti, SendMailModel sendMailModel) throws ServiceException {

		ConfigProperties conf = new ConfigProperties();

		try {

			String sMailTitle1st = conf.getString("omp.admin.punish.mail.telmpate.id.del.prodnoti.title");
			String sMailTitle2nd = conf.getString("omp.admin.punish.mail.telmpate.id.ins.punish.title");
			if ("".equals(StringUtil.nvlStr(sMailTitle1st)))
				throw new ServiceException("게시물삭제 메일 템플릿 정보가 없습니다.");
			if ("".equals(StringUtil.nvlStr(sMailTitle2nd)))
				throw new ServiceException("이용제한안내 메일 템플릿 정보가 없습니다.");

			daoManager.startTransaction();

			List<DpBadnoti> dpBadnotiList = this.commonDAO.queryForList("Community.selectDpBadnotiList", dpBadnoti);

			if (dpBadnotiList == null) {
				if (log.isInfoEnabled())
					log.info("dpBadnotiList is null : {0}", new Object[] { dpBadnoti.getMbrNo() });
				return;
			}

			String sPnshStrtDttm = DateUtil.getCurrentDate() + DateUtil.getCurrentTime();
			if (log.isDebugEnabled())
				log.debug("sPnshStrtDttm : " + sPnshStrtDttm);

			DpProdNoti dpProdNoti = new DpProdNoti();
			dpProdNoti.setNotiNo(dpBadnoti.getNotiNo());

			UsMemberPunish usMemberPunish = new UsMemberPunish();
			usMemberPunish.setMbrNo(dpBadnoti.getMbrNo());
			usMemberPunish.setMbrPnshSeq(1);
			usMemberPunish.setPnshNotiCnt(1);
			usMemberPunish.setHistYn("N");
			usMemberPunish.setPnshClsCd(dpBadnoti.getPnshClsCd());
			usMemberPunish.setPnshStrtDttm(sPnshStrtDttm);
			usMemberPunish.setPnshEndDttm(sPnshStrtDttm);
			usMemberPunish.setRegId(dpBadnoti.getRegId());
			usMemberPunish.setUpdId(dpBadnoti.getRegId());

			HashMap<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("mbrNo", dpBadnoti.getMbrNo());
			paramMap.put("mbrStatCd", Constants.MEM_STATUS_STOP);
			paramMap.put("updId", dpBadnoti.getUpdId());

			// 회원 상태 확인 후 정상 회원인 경우에만 발송.
			UsMember usMember = new UsMember();
			usMember.setMbrNo(dpBadnoti.getMbrNo());
			usMember = (UsMember) this.commonDAO.queryForObject("Community.selectUsMember", usMember);
			if (usMember == null) {
				log.warn("MEMBER NOT FOUND : {0}", new Object[] { dpBadnoti.getMbrNo() });
				sendMailModel.setToAddr(""); // 수신자 주소
				sendMailModel.setRefDataId("" + dpBadnoti.getNotiNo());
			} else {
				if (log.isDebugEnabled()) {
					log.debug("toAddr : " + usMember.getEmailAddr());
				}
				sendMailModel.setToAddr(usMember.getEmailAddr()); // 수신자 주소
				sendMailModel.setToName(usMember.getMbrNm());
				sendMailModel.setRefDataId("" + dpBadnoti.getNotiNo());
			}
   
			SendMailModel notiSendMailModel = null;
			Map<String, Object> mailContentMap;
			Map<String, Object> mailContentNotiMap;

			// DEV WEB BASE URL
			String sDevBaseUrl = (String) sendMailModel.getContentData();

			this.commonDAO.update("Community.updateDpProdNoti", dpProdNoti);

			int nCnt = dpBadnotiList.size();
			if (nCnt < 1) {
				this.commonDAO.insert("Community.insertDpBadnoti", dpBadnoti);
				this.commonDAO.insert("Community.insertUsMemberPunish", usMemberPunish);

				if (usMember != null) {

					// 메일 발송 : 1차 경고 - 게재중단 안내
					mailContentMap = new HashMap<String, Object>();
					mailContentMap.put("devBaseUrl", sDevBaseUrl);
					mailContentMap.put("mailSendDt", DateUtil.getDateToStr(new Date(), "yyyy. MM. dd"));
					//mailContentMap.put("mbrNm", usMember.getMbrNm());
					mailContentMap.put("mbrNm", usMember.getMbrId());

					sendMailModel.setRefAppId("Postscript.insertBadnoti.del.prodnoti");
					sendMailModel.setTemplateId(dpBadnoti.getMailTemplate1st());
					sendMailModel.setSubject(sMailTitle1st); // 제목
					sendMailModel.setContentData(mailContentMap); // 데이터
					log.info("MAIL del Prodnoti : {0}, {1}, {2}", new Object[] { sendMailModel.getRefDataId(), sendMailModel.getToAddr(),
							usMemberPunish.getMbrNo() });
				}

			} else if (nCnt == 1) {
				UsMemberPunish retUsMemberPunish = (UsMemberPunish) this.commonDAO.queryForObject("Community.selectUsMemberPunish",
						usMemberPunish);
				usMemberPunish.setMbrPnshSeq(retUsMemberPunish.getMbrPnshSeq());
				usMemberPunish.setPnshNotiCnt(retUsMemberPunish.getPnshNotiCnt() + 1);

				this.commonDAO.insert("Community.insertDpBadnoti", dpBadnoti);
				this.commonDAO.update("Community.updateUsMemberPunish", usMemberPunish);

				if (usMember != null) {

					// 메일 발송 : 2차 경고 - 게재중단 안내
					mailContentMap = new HashMap<String, Object>();
					mailContentMap.put("devBaseUrl", sDevBaseUrl);
					mailContentMap.put("mailSendDt", DateUtil.getDateToStr(new Date(), "yyyy. MM. dd"));
					//mailContentMap.put("mbrNm", usMember.getMbrNm());
					mailContentMap.put("mbrNm", usMember.getMbrId());
					
					sendMailModel.setRefAppId("Postscript.insertBadnoti.del.prodnoti");
					sendMailModel.setTemplateId(dpBadnoti.getMailTemplate1st());
					sendMailModel.setSubject(sMailTitle1st); // 제목
					sendMailModel.setContentData(mailContentMap); // 데이터
					log.info("MAIL del Prodnoti : {0}, {1}, {2}", new Object[] { sendMailModel.getRefDataId(), sendMailModel.getToAddr(),
							usMemberPunish.getMbrId() });
				}

			} else if (nCnt > 1) {
				UsMemberPunish retUsMemberPunish = (UsMemberPunish) this.commonDAO.queryForObject("Community.selectUsMemberPunish",
						usMemberPunish);
				usMemberPunish.setMbrPnshSeq(retUsMemberPunish.getMbrPnshSeq());
				usMemberPunish.setPnshNotiCnt(retUsMemberPunish.getPnshNotiCnt() + 1);

				usMemberPunish.setPnshStrtDttm(sPnshStrtDttm);
				usMemberPunish.setPnshEndDttm("99991231235959");

				this.commonDAO.insert("Community.insertDpBadnoti", dpBadnoti);
				this.commonDAO.update("Community.updateUsMemberPunish", usMemberPunish);
				this.commonDAO.update("Community.updateUsMember", paramMap);

				if (log.isInfoEnabled())
					log.info("REGISTER PUNISH MEMBER - MBR_NO : {0}", new Object[] { usMemberPunish.getMbrNo() });

				if (usMember != null) {
					// 메일 발송 : 3차 경고 - 징계회원 안내
					mailContentMap = new HashMap<String, Object>();
					mailContentMap.put("devBaseUrl", sDevBaseUrl);
					mailContentMap.put("mailSendDt", DateUtil.getDateToStr(new Date(), "yyyy. MM. dd"));
					//mailContentMap.put("mbrNm", usMember.getMbrNm());
					mailContentMap.put("mbrNm", usMember.getMbrId());
					
					sendMailModel.setRefAppId("Postscript.insertBadnoti.del.prodnoti");
					sendMailModel.setTemplateId(dpBadnoti.getMailTemplate1st());
					sendMailModel.setSubject(sMailTitle1st); // 제목
					sendMailModel.setContentData(mailContentMap); // 데이터
					log.info("MAIL del Prodnoti : {0}, {1}, {2}", new Object[] { StringUtil.nvlStr(sendMailModel.getRefDataId()),
							StringUtil.nvlStr(sendMailModel.getToAddr()), StringUtil.nvlStr(usMemberPunish.getMbrNo()) });

					mailContentNotiMap = new HashMap<String, Object>();
					mailContentNotiMap.put("devBaseUrl", sDevBaseUrl);
					mailContentNotiMap.put("mailSendDt", DateUtil.getDateToStr(new Date(), "yyyy. MM. dd"));
					mailContentNotiMap.put("punishRegYYYY", DateUtil.getDateToStr(new Date(), "yyyy"));
					mailContentNotiMap.put("punishRegMM", DateUtil.getDateToStr(new Date(), "MM"));
					mailContentNotiMap.put("punishRegDD", DateUtil.getDateToStr(new Date(), "dd"));
					mailContentNotiMap.put("mbrNm", sendMailModel.getToName());

					notiSendMailModel = new SendMailModel();
					notiSendMailModel.setTemplateId(dpBadnoti.getMailTemplate2nd()); // 템플릿 아이디
					notiSendMailModel.setRefAppId("Postscript.insertBadnoti.ins.punish"); // 연관 업무 아이디
					notiSendMailModel.setRefDataId(sendMailModel.getRefDataId()); // 연관 데이터의 아이디
					notiSendMailModel.setToAddr(sendMailModel.getToAddr()); // 수신자 주소
					notiSendMailModel.setToName(sendMailModel.getToName()); // 수신자 주소
					notiSendMailModel.setFromAddr(sendMailModel.getFromAddr()); // 발신자 주소
					notiSendMailModel.setFromName(sendMailModel.getFromName());
					notiSendMailModel.setReturnAddr(sendMailModel.getReturnAddr()); // 반송 수신자 주소
					notiSendMailModel.setSubject(sMailTitle1st); // 제목
					notiSendMailModel.setContentData(mailContentNotiMap); // 데이터
					log.info("MAIL ins Punish : {0}, {1}, {2}", new Object[] { StringUtil.nvlStr(notiSendMailModel.getRefDataId()),
							StringUtil.nvlStr(notiSendMailModel.getToAddr()), StringUtil.nvlStr(usMemberPunish.getMbrNo()) });
				}

			}

			MailSendAgent mailSendAgent;

			if (usMember != null) {

				if (sendMailModel != null && !"".equals(StringUtil.nvlStr(sendMailModel.getToAddr()))) {
					mailSendAgent = CommunicatorFactory.getMailSendAgent();
					mailSendAgent.requestMailSend(sendMailModel);
					if (notiSendMailModel != null)
						mailSendAgent.requestMailSend(notiSendMailModel);
				} else {
					log.warn("FAIL SEND MAIL : NONE EMAIL ADDRESS - {0}, {1}",
							new Object[] { dpBadnoti.getMbrNo(), sendMailModel.getRefAppId() });
				}

				//				if (notiSendMailModel != null && !"".equals(StringUtil.nvlStr(notiSendMailModel.getToAddr()))) {
				//					mailSendAgent = CommunicatorFactory.getMailSendAgent();
				//					mailSendAgent.requestMailSend(notiSendMailModel);
				//				} else {
				//					log.warn("FAIL SEND MAIL. : NONE EMAIL ADDRESS - {0}, {1}",
				//							new Object[] { dpBadnoti.getMbrNo(), sendMailModel.getRefAppId() });
				//				}
			}

			daoManager.commitTransaction();

		} catch (SQLException e) {
			throw new ServiceException("사용후기 징계 내용을 저장하는 동안 에러가 발생 하였습니다.", e);
		} catch (Exception ex) {
			throw new ServiceException("사용후기 징계 내용을 저장하는 동안 에러가 발생 하였습니다..", ex);
		} finally {
			daoManager.endTransaction();
		}

	}

	public void updateProdNotiDelYn(String selectedNotiNo, DpProdNoti dpProdNoti) throws ServiceException {

		try {

			daoManager.startTransaction();

			if (selectedNotiNo.indexOf(',') > 0) {

				String[] arrSelectedNotiNo = selectedNotiNo.split(",");
				for (int i = 0; i < arrSelectedNotiNo.length; i++) {

					dpProdNoti.setNotiNo(Long.parseLong(arrSelectedNotiNo[i]));

					if (log.isDebugEnabled())
						log.debug(dpProdNoti.toString());

					this.commonDAO.update("Community.updateProdNotiDelYn", dpProdNoti);
				}

			} else {

				if (!"".equals(selectedNotiNo)) {
					dpProdNoti.setNotiNo(Long.parseLong(selectedNotiNo));
				}

				if (log.isDebugEnabled())
					log.debug(dpProdNoti.toString());

				this.commonDAO.update("Community.updateProdNotiDelYn", dpProdNoti);
			}

			daoManager.commitTransaction();

		} catch (SQLException e) {
			throw new ServiceException("사용후기 징계 내용을 변경하는 동안 에러가 발생 하였습니다.", e);
		} catch (Exception ex) {
			throw new ServiceException("사용후기 징계 내용을 변경하는 동안 에러가 발생 하였습니다.", ex);
		} finally {
			daoManager.endTransaction();
		}
	}

}
