package com.omp.dev.member.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.log4j.Logger;

import com.ibatis.dao.client.DaoManager;
import com.omp.commons.communicate.CommunicatorFactory;
import com.omp.commons.communicate.mail.MailSendAgent;
import com.omp.commons.communicate.mail.SendMailModel;
import com.omp.commons.exception.NoticeException;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.model.MemberEmailModel;
import com.omp.commons.persistence.dao.DaoConfig;
import com.omp.commons.service.AbstractService;
import com.omp.commons.text.LocalizeMessage;
import com.omp.commons.util.CipherAES;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.FileUtil;
import com.omp.commons.util.SessionUtil;
import com.omp.commons.util.config.ConfigProperties;
import com.omp.dev.common.Constants;
import com.omp.dev.common.util.ReturnUrlGenerateUtil;
import com.omp.dev.member.model.Bank;
import com.omp.dev.member.model.Member;
import com.omp.dev.member.model.MemberDoc;
import com.omp.dev.member.model.MemberTransHist;
import com.omp.dev.member.model.MemberWithdrawInfo;
import com.omp.dev.purchase.model.Purchase;
import com.omp.dev.purchase.service.PurchaseServiceImpl;
import com.omp.dev.user.model.Session;

public class MyPageServiceImpl extends AbstractService implements MyPageService {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
	
	private MailSendAgent msa;
	private SendMailModel mail;
	private MemberEmailModel model;
	
	DaoManager manager = null;
	
	public MyPageServiceImpl() {
		manager = DaoConfig.getDaoManager();
	}
	
	@Override
	public String certificationPassword(HashMap map) throws Exception {
		String result = "";
		int resultCnt = 0;
		try {
			result = (String) this.commonDAO.queryForObject("MyPage.selectCertificationPassword", map);
			if("SUCCESS".equals(result)){
				resultCnt = (Integer) this.commonDAO.queryForObject("member.emailCheck", map.get("changEamil"));
				result = resultCnt > 0 ? "useEamil" : "SUCCESS";
			}
		} catch (NoticeException e) {
			throw e;
		} catch(ServiceException e) {
			throw e;
		} catch(Exception e) {
			throw new ServiceException("<MyPageServiceImpl - certificationPassword>", e);
		}
		return result;
	}

	@Override
	public Member getMemberProfile(String mbrNo) throws Exception {
		Member member = null;
		try {
			member = (Member) this.commonDAO.queryForObject("MyPage.selectMemberProfile", mbrNo);
			if(Constants.MEM_CLS_PERSON.equals(member.getMbrClsCd())){
				member.setPsRegNo(CipherAES.decryption(member.getPsRegNo()));
			}
			member.setMbrPw(CipherAES.decryption(member.getMbrPw()));
		} catch (NoticeException e) {
			throw e;
		} catch(ServiceException e) {
			throw e;
		} catch(Exception e) {
			throw new ServiceException("회원 프로파일 조회 실패.", e);
		}
		return member;
	}

	public void updateProfile(Member profileInfo) throws Exception {
		try {
			profileInfo.setMbrPw(CipherAES.encryption(profileInfo.getMbrPw()));
			this.commonDAO.update("MyPage.updateMemberProfile", profileInfo);
		} catch (NoticeException e) {
			throw e;
		} catch(ServiceException e) {
			throw e;
		} catch(Exception e) {
			throw new ServiceException("<MyPageServiceImpl - updateProfile>", e);
		}
		return;
	}
	
	public void updateMemberEmail(Member member) throws Exception {
		try {
			this.commonDAO.update("MyPage.updateMemberInfo", member);
		} catch (NoticeException e) {
			throw e;
		} catch(ServiceException e) {
			throw e;
		} catch(Exception e) {
			throw new ServiceException("<MyPageServiceImpl - updateMemberEmail>", e);
		}
		return;
		
	}

	public MemberTransHist getCalculateInfo(String mbrNo) throws Exception {
		MemberTransHist calculateInfo = null;
		try {
			// 회원 구분 코드 획득
			Member member = (Member) this.commonDAO.queryForObject("MyPage.selectMemberProfile", mbrNo);
			
			// 현재 정산정보 상태 확인
			HashMap resultMap = (HashMap) this.commonDAO.queryForObject("MyPage.selectCurrentCalculateStat", mbrNo);
			
			// 최종 이력이 거절일 경우 유효한 이력을 설정
			if(resultMap != null && StringUtils.defaultString((String)resultMap.get("DISP_STAT")).equals("REJECT")) {
				resultMap = (HashMap) this.commonDAO.queryForObject("MyPage.selectLastValidTransNo", mbrNo);
			}
			
			if(resultMap == null) {
				calculateInfo = new MemberTransHist();
				calculateInfo.setMbrClsCd(member.getMbrClsCd());
				calculateInfo.setDispStat("INPUT");
			}
			else {
				HashMap map = new HashMap();
				map.put("mbrNo", mbrNo);
				map.put("transNo", resultMap.get("TRANS_NO"));
				
				// 정산정보 설정
				calculateInfo = (MemberTransHist) this.commonDAO.queryForObject("MyPage.selectCurrentCalculateInfo", map);
				calculateInfo.setTransNo((String) resultMap.get("TRANS_NO"));
				calculateInfo.setDispStat((String) resultMap.get("DISP_STAT"));
				calculateInfo.setMbrClsCd(member.getMbrClsCd());
				calculateInfo.setAcctNo(CipherAES.decryption(calculateInfo.getAcctNo()));
			}
		} catch (NoticeException e) {
			throw e;
		} catch(ServiceException e) {
			throw e;
		} catch(Exception e) {
			throw new ServiceException("<MyPageServiceImpl - getCalculateInfo>", e);
		}
		return calculateInfo;
	}
	
	public void insertCalculateInfo(MemberTransHist calculateInfo, Properties prop) throws Exception {
		long size;
		try {
			this.manager.startTransaction();
			// 전환 Trace 설정
			Member member = (Member) this.commonDAO.queryForObject("MyPage.selectMemberProfile", calculateInfo.getMbrNo());
			String transTrace = member.getMbrClsCd() + ":" + member.getMbrCatCd() + ":" + member.getMbrClsCd() + ":" + Constants.MEM_TYPE_DEV_PAY;
			calculateInfo.setTraceInfo(transTrace);
			
			// 전환 이력 SEQ 설정
			String tranNo = (String) this.commonDAO.queryForObject("MyPage.selectTransSEQ");
			calculateInfo.setTransNo(tranNo);
			
			// 전환 이력 입력
			calculateInfo.setAcctNo(CipherAES.encryption(calculateInfo.getAcctNo()));
			this.commonDAO.insert("MyPage.insertUsTransHistByModel", calculateInfo);
			String currDttm = sdf.format(new Date());			
			
			MemberDoc identDoc = new MemberDoc();
			// 서류 종류 설정
			if(Constants.MEM_CLS_PERSON.equals(member.getMbrClsCd())) {
				identDoc.setDocHmCd(Constants.DOC_HM_02);
			}
			else if(Constants.MEM_CLS_BUSINESS.equals(member.getMbrClsCd())) {
				identDoc.setDocHmCd(Constants.DOC_HM_01);
			}
			else if(Constants.MEM_CLS_FOREIGNER.equals(member.getMbrClsCd())) {
				identDoc.setDocHmCd(Constants.DOC_HM_03);
			}
			if(calculateInfo.getIdentityDoc() != null && calculateInfo.getIdentityDoc().exists()) {
				// 파일 업로드
				size = calculateInfo.getIdentityDoc().length();
				String oriFileName = calculateInfo.getIdentityDocFileName();
				String extension = oriFileName.substring(oriFileName.lastIndexOf("."), oriFileName.length());
				String fileUploadPath = File.separator + currDttm.substring(0, 8);
				String fileName = calculateInfo.getMbrNo() + "_" + currDttm + "_" + identDoc.getDocHmCd() + extension;
				String upFilePath = fileUploadPath + File.separator+ fileName;
				FileUtil.move(calculateInfo.getIdentityDoc().getPath(), prop.getProperty("omp.common.path.share.member") + upFilePath, true);
				// 파일 정보 설정
				identDoc.setTransNo(tranNo);
				identDoc.setFilePath(upFilePath);
				identDoc.setFileNm(calculateInfo.getIdentityDocFileName());
				identDoc.setFileSize(size);
				identDoc.setMbrNo(calculateInfo.getMbrNo());
				//logger.debug("File Size :: " + size);
				this.commonDAO.insert("MyPage.insertMemberDoc", identDoc);
			}
			else {
				// 기존 최종 유효 등록 서류 등록
				identDoc.setTransNo(tranNo);
				identDoc.setMbrNo(calculateInfo.getMbrNo());
				this.commonDAO.insert("MyPage.insertMemberDocByRecent", identDoc);
			}
			
			MemberDoc acctDoc = new MemberDoc();
			
			if(calculateInfo.getAcctDoc() != null && calculateInfo.getAcctDoc().exists()) {
				size = calculateInfo.getAcctDoc().length();
				// 파일 업로드
				String oriFileName = calculateInfo.getAcctDocFileName();
				String extension = oriFileName.substring(oriFileName.lastIndexOf("."), oriFileName.length());
				String fileUploadPath = File.separator + currDttm.substring(0, 8);
				String fileName = calculateInfo.getMbrNo() + "_" + currDttm + "_" + Constants.DOC_HM_04 + extension;
				String upFilePath = fileUploadPath + File.separator+ fileName;
				
				FileUtil.move(calculateInfo.getAcctDoc().getPath(), prop.getProperty("omp.common.path.share.member") + upFilePath, true);
				
				// 파일 정보 설정
				acctDoc.setTransNo(tranNo);
				acctDoc.setFilePath(upFilePath);
				acctDoc.setFileNm(calculateInfo.getAcctDocFileName());
				acctDoc.setFileSize(size);
				acctDoc.setMbrNo(calculateInfo.getMbrNo());
				
				// 서류 종류 설정
				acctDoc.setDocHmCd(Constants.DOC_HM_04);
				
				this.commonDAO.insert("MyPage.insertMemberDoc", acctDoc);
			}
			else {
				// 기존 최종 유효 등록 서류 등록
				acctDoc.setDocHmCd(Constants.DOC_HM_04);
				acctDoc.setTransNo(tranNo);
				acctDoc.setMbrNo(calculateInfo.getMbrNo());
				this.commonDAO.insert("MyPage.insertMemberDocByRecent", acctDoc);
			}
			
			HashMap map = new HashMap();
			map.put("mbrNo", calculateInfo.getMbrNo());
			map.put("devMbrStatCd", Constants.MEM_DEV_STATUS_ACCOUNTS_WAIT);
			
			this.commonDAO.update("MyPage.updateMemberStat", map);
			/*profileInfo.setMbrPw(CipherAES.encrypt(profileInfo.getMbrPw()));
			this.commonDAO.update("MyPage.updateMemberProfile", profileInfo);*/
			this.manager.commitTransaction();
		} catch (NoticeException e) {
			throw e;
		} catch(ServiceException e) {
			throw e;
		} catch(Exception e) {
			throw new ServiceException("<MyPageServiceImpl - insertCalculateInfo>", e);
		} finally {
			this.manager.endTransaction();
		}
		return;
	}
	
	public Purchase transferPaidMember(Purchase purchase) throws Exception {
		MemberTransHist calculateInfo = new MemberTransHist();
		PurchaseServiceImpl purchaseServiceImpl = new PurchaseServiceImpl();
		String mbrNo = "";
		String workStat = "";
		mbrNo = purchase.getMbrNo();
		try {
			this.manager.startTransaction();
			
			//TODO 연회비 결제 프로세스 추가
			// 1. 연회비 결제 서비스 호출
			// 2. 결제 결과에 따른 결제내역 DB 등록
			purchase = purchaseServiceImpl.purchase(purchase);
			logger.debug("결제후 purchase.getRtnCd() ==>  " + purchase.getRtnCd());
			
			if("0000".equals(purchase.getRtnCd())){
				workStat = (String) this.commonDAO.queryForObject("MyPage.selectPayHist", mbrNo);
				if("SUCCESS".equals(workStat)){
					// 전환 Trace 설정
					calculateInfo.setMbrNo(mbrNo);
					Member member = (Member) this.commonDAO.queryForObject("MyPage.selectMemberProfile", calculateInfo.getMbrNo());
					String transTrace = member.getMbrClsCd() + ":" + member.getMbrCatCd() + ":" + member.getMbrClsCd() + ":" + Constants.MEM_TYPE_DEV_PAY;
					calculateInfo.setTraceInfo(transTrace);
					
					// 전환 이력 설정
					String tranNo = (String) this.commonDAO.queryForObject("MyPage.selectTransSEQ");
					calculateInfo.setTransNo(tranNo);
					calculateInfo.setPrcStatCd(Constants.MEM_DEV_STATUS_MEMBERSHIP_FINISH);
					
					// 등록서류 재 등록 및 설정
					this.commonDAO.insert("MyPage.insertMemberDocBySelect", calculateInfo);
					this.commonDAO.update("MyPage.updateMemberDocLastValid", calculateInfo);
					// 전환 이력 등록
					this.commonDAO.insert("MyPage.insertCalculateInfoBySelect", calculateInfo);
					
					// 회원 유형 이력
					this.commonDAO.update("MyPage.updateMemberPaySubHist", mbrNo);
					
					// 회원 정보 수정
					HashMap map = new HashMap();
					map.put("mbrNo", calculateInfo.getMbrNo());
					map.put("devMbrStatCd", Constants.MEM_DEV_STATUS_MEMBERSHIP_FINISH);
					map.put("mbrCatCd", Constants.MEM_TYPE_DEV_PAY);
					this.commonDAO.update("MyPage.updateMemberStat", map);
				}
			}
			this.manager.commitTransaction();
		} catch (NoticeException e) {
			throw e;
		} catch(ServiceException e) {
			throw e;
		} catch(Exception e) {
			throw new ServiceException("<MyPageServiceImpl - transferPaidMember>", e);
		} finally {
			this.manager.endTransaction();
		}
		return purchase;
	}
	
	public Member insertTransferInfo(HttpServletRequest req, MemberTransHist transferInfo, Properties prop) throws Exception {
		Member member = null;
		long size;
		try {
			ConfigProperties	conf	= new ConfigProperties();
			
			this.manager.startTransaction();

			// 전환 Trace 설정
			member = (Member) this.commonDAO.queryForObject("MyPage.selectMemberProfile", transferInfo.getMbrNo());
			String transTrace = member.getMbrClsCd() + ":" + member.getMbrCatCd() + ":" + Constants.MEM_CLS_BUSINESS + ":" + member.getMbrCatCd();
			transferInfo.setTraceInfo(transTrace);
			
			// 전환 이력 SEQ 설정
			String tranNo = (String) this.commonDAO.queryForObject("MyPage.selectTransSEQ");
			transferInfo.setTransNo(tranNo);
			
			// 전환 이력 입력
			transferInfo.setAcctNo(CipherAES.encryption(transferInfo.getAcctNo()));
			this.commonDAO.insert("MyPage.insertUsTransHistByModel", transferInfo);
			
			String currDttm = sdf.format(new Date());
			
			MemberDoc identDoc = new MemberDoc();
			
			if(transferInfo.getIdentityDoc() != null && transferInfo.getIdentityDoc().exists()) {
				size = transferInfo.getIdentityDoc().length();
				// 파일 업로드
				String oriFileName = transferInfo.getIdentityDocFileName();
				String extension = oriFileName.substring(oriFileName.lastIndexOf("."), oriFileName.length());
				String fileUploadPath = File.separator + currDttm.substring(0, 8);
				String fileName = transferInfo.getMbrNo() + "_" + currDttm + "_" + Constants.DOC_HM_01 + extension;
				String upFilePath = fileUploadPath + File.separator+ fileName;
				
				FileUtil.move(transferInfo.getIdentityDoc().getPath(), prop.getProperty("omp.common.path.share.member") + upFilePath, true);
				
				// 파일 정보 설정
				identDoc.setTransNo(tranNo);
				identDoc.setFilePath(upFilePath);
				identDoc.setFileNm(transferInfo.getIdentityDocFileName());
				identDoc.setFileSize(size);
				identDoc.setMbrNo(transferInfo.getMbrNo());
				
				// 서류 종류 설정
				identDoc.setDocHmCd(Constants.DOC_HM_01);
				
				this.commonDAO.insert("MyPage.insertMemberDoc", identDoc);
			}
			
			MemberDoc acctDoc = new MemberDoc();
			
			if(transferInfo.getAcctDoc() != null && transferInfo.getAcctDoc().exists()) {
				size = transferInfo.getAcctDoc().length();
				// 파일 업로드
				String oriFileName = transferInfo.getAcctDocFileName();
				String extension = oriFileName.substring(oriFileName.lastIndexOf("."), oriFileName.length());
				String fileUploadPath = File.separator + currDttm.substring(0, 8);
				String fileName = transferInfo.getMbrNo() + "_" + currDttm + "_" + Constants.DOC_HM_04 + extension;
				String upFilePath = fileUploadPath + File.separator+ fileName;
				
				FileUtil.move(transferInfo.getAcctDoc().getPath(), prop.getProperty("omp.common.path.share.member") + upFilePath, true);
				
				// 파일 정보 설정
				acctDoc.setTransNo(tranNo);
				acctDoc.setFilePath(upFilePath);
				acctDoc.setFileNm(transferInfo.getAcctDocFileName());
				acctDoc.setFileSize(size);
				acctDoc.setMbrNo(transferInfo.getMbrNo());
				
				// 서류 종류 설정
				acctDoc.setDocHmCd(Constants.DOC_HM_04);
				
				this.commonDAO.insert("MyPage.insertMemberDoc", acctDoc);
			}
			
			HashMap map = new HashMap();
			map.put("mbrNo", transferInfo.getMbrNo());
			map.put("devMbrStatCd", Constants.MEM_DEV_STATUS_TURN_MOTION);
			
			this.commonDAO.update("MyPage.updateMemberStat", map);
			
			//email 발송
			if(member.getMbrCatCd().equals(Constants.MEM_TYPE_DEV_NOPAY)) {
				// 승인 인증 email 발송
				//String uri = prop.getProperty("omp.common.url-prefix.https.dev") + "/cert/cf.omp?j=" + CipherAES.encryption("cmd=" + Constants.AUTH_MEM_CHANGE + "&param=" + transferInfo.getMbrNo());
				String url = ReturnUrlGenerateUtil.rtnUrl(req, Constants.AUTH_MEM_CHANGE, member.getMbrNo());
				logger.debug("Certification URL : " + url);
				try {
					String templateId = "/DEV/US_005.html";
					// 1. MemberEmailModel
					model = new MemberEmailModel();
					model.setMbrId(member.getMbrId());
					model.setReturnUrl(url);
					model.setMainUrl(ReturnUrlGenerateUtil.rtnUrl(req));
					// 2. SendMailModel
					mail = new SendMailModel();
					mail.setTemplateId(templateId);
					mail.setRefAppId("MyPageServiceImpl.insertTransferInfo_05");
					mail.setSubject(LocalizeMessage.getLocalizedMessage("[Whoopy 개발자센터] 회원전환을 위해 이메일 인증을 해주세요."));
					mail.setRefDataId(member.getMbrNo());
					mail.setToAddr(member.getEmailAddr());
					mail.setContentData(model);
					mail.setFromAddr(conf.getString("omp.dev.email.default.from"));
					mail.setFromName(conf.getString("omp.dev.email.default.fromName"));
					mail.setReturnAddr(conf.getString("omp.dev.email.default.return"));
					msa = CommunicatorFactory.getMailSendAgent();
					//[SEND - EMAIL]
					msa.requestMailSend(mail);
				} catch (Exception e) {
					throw new ServiceException("메일 전송 정상적으로 발송되지 않았습니다.", e);
				}
			}else if(member.getMbrCatCd().equals(Constants.MEM_TYPE_DEV_PAY)) {
				// 전환 대기  email 발송
				try {
					String templateId = "/DEV/US_007.html";
					// 1. MemberEmailModel
					model = new MemberEmailModel();
					model.setMbrId(member.getMbrId());
					model.setReqDt(DateUtil.getSysDate());
					model.setMainUrl(ReturnUrlGenerateUtil.rtnUrl(req));
					// 2. SendMailModel
					mail = new SendMailModel();
					mail.setTemplateId(templateId);
					mail.setRefAppId("MyPageServiceImpl.insertTransferInfo_07");
					mail.setSubject(LocalizeMessage.getLocalizedMessage("[Whoopy 개발자센터] 회원님의 회원전환신청이 접수되었습니다."));
					mail.setRefDataId(member.getMbrNo());
					mail.setToAddr(member.getEmailAddr());
					mail.setFromAddr(conf.getString("omp.dev.email.default.from"));
					mail.setFromName(conf.getString("omp.dev.email.default.fromName"));
					mail.setReturnAddr(conf.getString("omp.dev.email.default.return"));
					mail.setContentData(model);
					msa = CommunicatorFactory.getMailSendAgent();
					//[SEND - EMAIL]
					msa.requestMailSend(mail);
				} catch (Exception e) {
					throw new ServiceException("메일 전송 정상적으로 발송되지 않았습니다.", e);
				}
			}
			
			this.manager.commitTransaction();
		} catch (NoticeException e) {
			throw e;
		} catch(ServiceException e) {
			throw e;
		} catch(Exception e) {
			throw new ServiceException("<MyPageServiceImpl - insertTransferInfo>", e);
		} finally {
			this.manager.endTransaction();
		}
		return member;
	}
	
	/**
	 * 무료 개인 -> 무료 회사 전환 처리(이메일 인증)
	 */
	public void transferBusinessMember(HttpServletRequest req, String mbrNo) throws Exception {
		MemberTransHist transferInfo = new MemberTransHist();
		Session session = new Session();
		Member member = null;
		try {
			ConfigProperties	conf	= new ConfigProperties();
			
			this.manager.startTransaction();
			session = (Session) SessionUtil.getMemberSession(req);
			member = (Member) this.commonDAO.queryForObject("MyPage.selectMemberProfile", mbrNo);
			// 전환 Trace 설정
			String transTrace = Constants.MEM_CLS_PERSON + ":" + Constants.MEM_TYPE_DEV_NOPAY + ":" + Constants.MEM_CLS_BUSINESS + ":" + Constants.MEM_TYPE_DEV_NOPAY;
			transferInfo.setTraceInfo(transTrace);
			transferInfo.setMbrNo(mbrNo);
			transferInfo.setPrcStatCd(Constants.MEM_DEV_STATUS_TURN_FINISH);
			
			logger.debug("devStatCd :: " + transferInfo.getPrcStatCd());
			// 전환 이력 SEQ 설정
			String tranNo = (String) this.commonDAO.queryForObject("MyPage.selectTransSEQ");
			transferInfo.setTransNo(tranNo);
			
			// 전환 이력 입력
			this.commonDAO.insert("MyPage.insertTransHistTransComplete", transferInfo);
			
			// 회원 마스터 업데이트			
			this.commonDAO.update("MyPage.updateMemberTransComplete", transferInfo);

			// 전환 승인 요청 완료 email 발송
			try {
				String templateId = "/DEV/US_006.html";
				
				// 1. MemberEmailModel
				model = new MemberEmailModel();
				model.setMbrId(session.getMbrId());
				model.setReqDt(member.getLastTransRegDt());
				model.setEndDt(DateUtil.getSysDate());
				model.setMainUrl(ReturnUrlGenerateUtil.rtnUrl(req));
				// 2. SendMailModel
				mail = new SendMailModel();
				mail.setTemplateId(templateId);
				mail.setRefAppId("MyPageServiceImpl.transferBusinessMember");
				mail.setSubject(LocalizeMessage.getLocalizedMessage("[Whoopy 개발자센터] 회원님께서 신청하신 회원전환이 완료되었습니다."));
				mail.setRefDataId(session.getMbrNo());
				mail.setToAddr(session.getEmailAddr());
				mail.setContentData(model);
				mail.setFromAddr(conf.getString("omp.dev.email.default.from"));
				mail.setFromName(conf.getString("omp.dev.email.default.fromName"));
				mail.setReturnAddr(conf.getString("omp.dev.email.default.return"));
				msa = CommunicatorFactory.getMailSendAgent();
				//[SEND - EMAIL]
				msa.requestMailSend(mail);
			} catch (Exception e) {
				throw new ServiceException("<MyPageServiceImpl - transferBusinessMember>", e);
			}
			
			this.manager.commitTransaction();
		} catch (NoticeException e) {
			throw e;
		} catch(ServiceException e) {
			throw e;
		} catch(Exception e) {
			throw new ServiceException("<MyPageServiceImpl - transferBusinessMember>", e);
		}finally {
			this.manager.endTransaction();
		}
		return;
	}
	
	@Override
	public void mypageWithdrawExcute(HttpServletRequest req, MemberWithdrawInfo withdrawInfo) throws Exception {
		try{
			ConfigProperties	conf	= new ConfigProperties();
			
			this.manager.startTransaction();
			
			HashMap<String, String> param = new HashMap<String, String>();
			param.put("mbrNo", withdrawInfo.getMbrNo());
			param.put("devMbrStatCd", withdrawInfo.getDevMbrStatCd());
			param.put("mbrStatCd", Constants.MEM_STATUS_LEAVE);
			
			this.commonDAO.update("MyPage.updateMemberStat", param);
			
			Member member = (Member) this.commonDAO.queryForObject("MyPage.selectMemberProfile", withdrawInfo.getMbrNo());
			
			// Trans Hist SEQ
			String tranNo = (String) this.commonDAO.queryForObject("MyPage.selectTransSEQ");
			
			// Trance Info
			String traceInfo = member.getMbrClsCd() + ":" + member.getMbrCatCd() + ":" + member.getMbrClsCd() + ":" + member.getMbrCatCd();
			
			MemberTransHist transHistInfo = new MemberTransHist();
			transHistInfo.setTransNo(tranNo);
			transHistInfo.setMbrNo(member.getMbrNo());
			transHistInfo.setPrcStatCd(member.getDevMbrStatCd());
			transHistInfo.setTraceInfo(traceInfo);
			
			this.commonDAO.insert("MyPage.insertCalculateInfoBySelect", transHistInfo);
			
			withdrawInfo.setMbrCatcd(member.getMbrCatCd());
			
			this.commonDAO.update("MyPage.updateMemberSubHist", withdrawInfo);
			
			try {
				// 1. MemberEmailModel Set
				model = new MemberEmailModel();
				model.setMainUrl(ReturnUrlGenerateUtil.rtnUrl(req));
				model.setMbrId(member.getMbrId());
				model.setReqDt(DateUtil.getSysDate());
				
				// 2. SendMailModel Set
				mail = new SendMailModel();
				mail.setTemplateId("/DEV/US_008.html");
				mail.setSubject(LocalizeMessage.getLocalizedMessage("[Whoopy 개발자센터] 회원님의 회원탈퇴 신청이 접수되었습니다."));
				mail.setRefAppId("MyPageAction.mypageWithdrawExcute");
				mail.setRefDataId(withdrawInfo.getMbrNo());
				mail.setToAddr(member.getEmailAddr());
				mail.setContentData(model);
				mail.setFromAddr(conf.getString("omp.dev.email.default.from"));
				mail.setFromName(conf.getString("omp.dev.email.default.fromName"));
				mail.setReturnAddr(conf.getString("omp.dev.email.default.return"));
				msa = CommunicatorFactory.getMailSendAgent();
				
				msa.requestMailSend(mail);
				
			} catch (Exception e) {
				throw new ServiceException("<MyPageServiceImpl - mypageWithdrawExcute>", e);
			}
			
			this.manager.commitTransaction();
		} catch (NoticeException e) {
			throw e;
		} catch(ServiceException e) {
			throw e;
		} catch(Exception e) {
			throw new ServiceException("<MyPageServiceImpl - mypageWithdrawExcute>", e);
		}finally{
			this.manager.endTransaction();
		}
	}
	
	@Override
	public List<Bank> getBankList(String bankNm) throws Exception {
		return (List)this.commonDAO.queryForList("MyPage.selectCommCdList", bankNm);
	}

	public String isPersonalMember(String mbrNo) throws Exception {
		return (String) this.commonDAO.queryForObject("MyPage.selectIsPersonalMember", mbrNo);
	}
	
	public MemberTransHist getLastTransHistStat(HashMap map) throws Exception {
		return (MemberTransHist) this.commonDAO.queryForObject("MyPage.selectLastTransHist", map);
	}
	
	public HashMap isValidPrcStat(HashMap map) throws Exception {
		return (HashMap) this.commonDAO.queryForObject("MyPage.selectIsValidPrcStat", map);
	}

	/* (non-Javadoc)
	 * @see com.omp.dev.member.service.MyPageService#getEmailCheck(java.lang.String)
	 */
	@Override
	public Integer getEmailCheck(String email) throws Exception {

		return (Integer)this.commonDAO.queryForObject("Mypage.emailCheck", email);
	}
	
}
