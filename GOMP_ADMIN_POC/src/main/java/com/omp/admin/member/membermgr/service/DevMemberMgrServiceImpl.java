package com.omp.admin.member.membermgr.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

import com.omp.admin.adminmgr.auth.model.AdSession;
import com.omp.admin.common.Constants;
import com.omp.admin.member.membermgr.model.UserMemberMgr;
import com.omp.commons.communicate.CommunicatorFactory;
import com.omp.commons.communicate.mail.MailSendAgent;
import com.omp.commons.communicate.mail.SendMailModel;
import com.omp.commons.exception.NoticeException;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.model.MemberEmailModel;
import com.omp.commons.service.AbstractService;
import com.omp.commons.text.LocalizeMessage;
import com.omp.commons.util.CipherAES;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.config.ConfigProperties;

public class DevMemberMgrServiceImpl extends AbstractService implements DevMemberMgrService {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserMemberMgr> findDevMemberMgrList(UserMemberMgr devMember) throws ServiceException {
		try{
			return super.commonDAO.queryForPageList("devMemberMgr.findDevMemberMgrList", devMember);
		} catch (NoticeException e) {
			throw e;
		} catch(ServiceException e) {
			throw e;
		}catch(Exception e){
            throw new ServiceException("findDevMemberMgrList Error.", e);
		}
	}

	@Override
	public UserMemberMgr findDevMemberDetail(String mbrno) throws ServiceException {
		UserMemberMgr userInfo = new UserMemberMgr();
		try{
			userInfo = (UserMemberMgr)super.commonDAO.queryForObject("devMemberMgr.findDevMemberDetail", mbrno);
			
			if(userInfo != null && StringUtils.isNotEmpty(userInfo.getAcctno())){
				userInfo.setAcctno(CipherAES.decryption(userInfo.getAcctno()));
			}
		} catch (NoticeException e) {
			throw e;
		} catch(ServiceException e) {
			throw e;
		}catch(Exception e){
            throw new ServiceException("findFreeMemberDetail Error.", e);
		}
		return userInfo;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public JSONObject ajaxStateUpdateExcute(UserMemberMgr devMember,  HttpServletRequest req) throws ServiceException {
		String transno = "";
		String transinfo = "";
		String rejreason = devMember.getRejreason();
		String date = DateUtil.getGeneralTimeStampString();
		
		String before_Cls_Cd = devMember.getMbrclscd();
		String before_Cat_Cd = devMember.getMbrcatcd();
		String before_Dev_Stat_Cd = devMember.getDevmbrstatcd();
		String after_Dev_Stat_Cd = devMember.getUptdevmbrstatcd();
		
		UserMemberMgr transInfo = null;
		
		List<HashMap> docList = null;
		
		MemberEmailModel contentData = new MemberEmailModel();
		SendMailModel mail = new SendMailModel();
		MailSendAgent msa;
		
		JSONObject json = new JSONObject();
		ConfigProperties	conf	= new ConfigProperties();
		
		try{
			AdSession adSession = (AdSession) req.getSession(true).getAttribute(Constants.ADMIN_AUTH_SESSION_KEY);
			
			// Email Info Set
			contentData.setMbrId(devMember.getMbrid());
			
			// Trans Hist Info
			transInfo = (UserMemberMgr)super.commonDAO.queryForObject("devMemberMgr.selectTransHistInfo", devMember);
			
			if(devMember.getType().equals("grade")){
				// Dev Member Grade Update ------------------------------------------>
				super.commonDAO.update("devMemberMgr.updateGrade", devMember);
				
				json.put("result", "SUCCESS");
				// <------------------------------------------ End Dev Member Grade Update
			}else if(devMember.getType().equals("state")){
				if(devMember.getMbrcatcd().equals(Constants.MEM_TYPE_DEV_NOPAY)){
					
					// ********** Free Dev Member State Update ------------------------------------------>
					super.daoManager.startTransaction();
					
					// Update Memeber
					if(after_Dev_Stat_Cd.equals(Constants.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_FINISH)){
						transInfo.setRegid(adSession.getAdMgr().getMgrId());
						transInfo.setUptdevmbrstatcd(after_Dev_Stat_Cd);
						transInfo.setMbrcatcd(Constants.MEM_TYPE_DEV_PAY);
						transInfo.setMbrstatregdt(date);
						transInfo.setPayTransDt(date);
						transInfo.setAcctcertyn("Y");
						transInfo.setUptDt(date);
					
						// Calculate Info Approve
						super.commonDAO.update("devMemberMgr.updateCalculateInfoApprState", transInfo);
						
						mail.setTemplateId("/DEV/US_016.html");
						mail.setRefAppId("DevMemberMgrAction.ajaxStateUpdateExcute_016");
						mail.setSubject(LocalizeMessage.getLocalizedMessage("[Whoopy 개발자센터] 정산정보 승인완료 안내입니다."));
					}else if(after_Dev_Stat_Cd.equals(Constants.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_REJECT)){
						devMember.setRegid(adSession.getAdMgr().getMgrId());
						devMember.setMbrstatregdt(date);
						devMember.setUptDt(date);
						
						// Reject
						super.commonDAO.update("devMemberMgr.updateTransStateReject_Withdraw", devMember);
						
						// Reject Email Info Set
						contentData.setReqDt(transInfo.getRegdts());
						contentData.setRejDt(DateUtil.getSysDate());
						contentData.setRejDesc(rejreason);
						
						mail.setTemplateId("/DEV/US_017.html");
						mail.setRefAppId("DevMemberMgrAction.ajaxStateUpdateExcute_017");
						mail.setSubject(LocalizeMessage.getLocalizedMessage("[Whoopy 개발자센터] 정산정보 승인거절 안내입니다."));
					}
					
					// Member Old Doc List
					devMember.setDevmbrstatcd(before_Dev_Stat_Cd);
					docList = super.commonDAO.queryForList("devMemberMgr.selectMemberDoc", devMember);

					UserMemberMgr member = (UserMemberMgr)super.commonDAO.queryForObject("devMemberMgr.findDevMemberDetail", devMember.getMbrno());
					
					// Trans Info
					transinfo = before_Cls_Cd + ":" + before_Cat_Cd + ":" + member.getMbrclscd() + ":" + Constants.MEM_TYPE_DEV_PAY;
						
					// Sequence Trans No
					transno = (String) super.commonDAO.queryForObject("devMemberMgr.seqTransNo");
					
					if(after_Dev_Stat_Cd.equals(Constants.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_FINISH)){
						member.setTransno(transno);
						member.setRegid(adSession.getAdMgr().getMgrId());
						member.setTransinfo(transinfo);
						member.setRegdts(date);

						// Insert Trans Hist
						super.commonDAO.insert("devMemberMgr.insertTransHist", member);	
						
						// Member Old Doc Update( Valid 'Y' -> 'N' )
						super.commonDAO.update("devMemberMgr.updateMemberDoc", member);
					}else if(after_Dev_Stat_Cd.equals(Constants.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_REJECT)){	// Reject
						transInfo.setRegid(adSession.getAdMgr().getMgrId());
						transInfo.setDevmbrstatcd(member.getDevmbrstatcd());
						transInfo.setTransno(transno);
						transInfo.setTransinfo(transinfo);
						transInfo.setRejreason(rejreason);
						transInfo.setRegdts(date);
						
						// Insert Trans Hist
						super.commonDAO.insert("devMemberMgr.insertTransHist", transInfo);	
					}
					
					//  Insert Member Doc 
					HashMap map = new HashMap();
					for(int i = 0; i < docList.size(); i++){
						map = (HashMap)docList.get(i);
						map.put("regid", adSession.getAdMgr().getMgrId());
							
						if(after_Dev_Stat_Cd.equals(Constants.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_FINISH)){
							map.put("transno", member.getTransno());
							map.put("lstvalidyn", "Y");
						}else if(after_Dev_Stat_Cd.equals(Constants.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_REJECT)){
							map.put("transno", transInfo.getTransno());
							map.put("lstvalidyn", "N");
						}
							
						super.commonDAO.insert("devMemberMgr.insertMemberDoc", map);
					}
					
					// Result Email Send Start -->
					mail.setRefDataId(member.getMbrno());
		            mail.setToAddr(member.getEmailaddr());
		            mail.setContentData(contentData);
					mail.setFromAddr(conf.getString("omp.admin.punish.mail.from.addr"));
					mail.setFromName(conf.getString("omp.admin.punish.mail.from.name"));
					mail.setReturnAddr(conf.getString("omp.admin.punish.mail.return.addr"));
		            
		            msa = CommunicatorFactory.getMailSendAgent();
		            
		            msa.requestMailSend(mail);
					// <-- Result Email Send End
					
					json.put("result", "SUCCESS");

					super.daoManager.commitTransaction();
					
					// <------------------------------------------ Free Dev Member State Update End **********
				}else{
					
					// ********** Pay Dev Member State Update ------------------------------------------>
					super.daoManager.startTransaction();
					
					transInfo.setRegid(adSession.getAdMgr().getMgrId());
					transInfo.setUptdevmbrstatcd(after_Dev_Stat_Cd);
					
					// Update Memeber Dev State
					if(after_Dev_Stat_Cd.equals(Constants.MEM_DEV_STATUS_TURN_FINISH)){
						transInfo.setMbrclscd(Constants.MEM_CLS_BUSINESS);
						transInfo.setMbrstatregdt(date);
						transInfo.setUptDt(date);
						//transInfo.setAcctcertyn("Y");
						
						// Trans Approve Approve
						super.commonDAO.update("devMemberMgr.updateTransApprState", transInfo);
						
						contentData.setReqDt(transInfo.getRegdts());
						contentData.setEndDt(DateUtil.getSysDate());
						
						mail.setTemplateId("/DEV/US_013.html");
						mail.setRefAppId("DevMemberMgrAction.ajaxStateUpdateExcute_013");
						mail.setSubject(LocalizeMessage.getLocalizedMessage("[Whoopy 개발자센터] 회원님께서 신청하신 회원전환이 완료되었습니다."));
					}else if(after_Dev_Stat_Cd.equals(Constants.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_FINISH)){
						transInfo.setMbrstatregdt(date);
						transInfo.setAcctcertyn("Y");
						transInfo.setUptDt(date);
						
						// Calculate Info Approve
						super.commonDAO.update("devMemberMgr.updateCalculateInfoApprState", transInfo);
						
						mail.setTemplateId("/DEV/US_016.html");
						mail.setRefAppId("DevMemberMgrAction.ajaxStateUpdateExcute_016");
						mail.setSubject(LocalizeMessage.getLocalizedMessage("[Whoopy 개발자센터] 정산정보 승인완료 안내입니다."));
					}else{		//Reject
						devMember.setMbrstatregdt(date);
						devMember.setUptDt(date);
						
						super.commonDAO.update("devMemberMgr.updateTransStateReject_Withdraw", devMember);
						
						contentData.setReqDt(transInfo.getRegdts());
						contentData.setRejDt(DateUtil.getSysDate());
						contentData.setRejDesc(rejreason);

						if(after_Dev_Stat_Cd.equals(Constants.MEM_DEV_STATUS_TURN_REJECT)){
							mail.setTemplateId("/DEV/US_014.html");
							mail.setRefAppId("DevMemberMgrAction.ajaxStateUpdateExcute_014");
							mail.setSubject(LocalizeMessage.getLocalizedMessage("[Whoopy 개발자센터] 회원님의 회원전환 신청이 거절되었습니다."));
						}else if(after_Dev_Stat_Cd.equals(Constants.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_REJECT)){
							mail.setTemplateId("/DEV/US_017.html");
							mail.setRefAppId("DevMemberMgrAction.ajaxStateUpdateExcute_017");
							mail.setSubject(LocalizeMessage.getLocalizedMessage("[Whoopy 개발자센터] 정산정보 승인거절 안내입니다."));
						}
					}
					
					// Member Old Doc List
					devMember.setDevmbrstatcd(before_Dev_Stat_Cd);
					docList = super.commonDAO.queryForList("devMemberMgr.selectMemberDoc", devMember);

					UserMemberMgr member = (UserMemberMgr)super.commonDAO.queryForObject("devMemberMgr.findDevMemberDetail", devMember.getMbrno());
					
					// Trans Info
					if(after_Dev_Stat_Cd.equals(Constants.MEM_DEV_STATUS_TURN_REJECT)){
						transinfo = before_Cls_Cd + ":" + before_Cat_Cd + ":" + Constants.MEM_CLS_BUSINESS + ":" + member.getMbrcatcd();
					}else{
						transinfo = before_Cls_Cd + ":" + before_Cat_Cd + ":" + member.getMbrclscd() + ":" + member.getMbrcatcd();
					}
						
					// if Reject
//					if(after_Dev_Stat_Cd.equals(Constants.MEM_DEV_STATUS_TURN_REJECT) || 
//							after_Dev_Stat_Cd.equals(Constants.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_REJECT)){
//						member.setRejreason(rejreason);
//					}
					
					// Sequence Trans No
					transno = (String) super.commonDAO.queryForObject("devMemberMgr.seqTransNo");
					
					if(after_Dev_Stat_Cd.equals(Constants.MEM_DEV_STATUS_TURN_FINISH) ||	
							after_Dev_Stat_Cd.equals(Constants.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_FINISH)){
						member.setTransno(transno);
						member.setRegid(adSession.getAdMgr().getMgrId());
						member.setTransinfo(transinfo);
						member.setRegdts(date);

						// Insert Trans Hist
						super.commonDAO.insert("devMemberMgr.insertTransHist", member);	
						
						// Member Old Doc Update( Valid 'Y' -> 'N')
						super.commonDAO.update("devMemberMgr.updateMemberDoc", member);
					}else if(after_Dev_Stat_Cd.equals(Constants.MEM_DEV_STATUS_TURN_REJECT) || 
						after_Dev_Stat_Cd.equals(Constants.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_REJECT)){
						// Reject
						transInfo.setRegid(adSession.getAdMgr().getMgrId());
						transInfo.setDevmbrstatcd(member.getDevmbrstatcd());
						transInfo.setTransno(transno);
						transInfo.setTransinfo(transinfo);
						//transInfo.setAcctcertyn("N");
						transInfo.setRejreason(rejreason);
						transInfo.setRegdts(date);
						
						// Insert Trans Hist
						super.commonDAO.insert("devMemberMgr.insertTransHist", transInfo);	
					}
					
					//  Insert Member Doc 
					HashMap map = new HashMap();
					for(int i = 0; i < docList.size(); i++){
						map = (HashMap)docList.get(i);
						map.put("regid", adSession.getAdMgr().getMgrId());
						
						if(after_Dev_Stat_Cd.equals(Constants.MEM_DEV_STATUS_TURN_FINISH) || 
								after_Dev_Stat_Cd.equals(Constants.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_FINISH)){
							map.put("transno", member.getTransno());
							map.put("lstvalidyn", "Y");
						}else if(after_Dev_Stat_Cd.equals(Constants.MEM_DEV_STATUS_TURN_REJECT) ||
								after_Dev_Stat_Cd.equals(Constants.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_REJECT)){
							map.put("transno", transInfo.getTransno());
							map.put("lstvalidyn", "N");
						}
							
						super.commonDAO.insert("devMemberMgr.insertMemberDoc", map);
					}
					
					// Result Email Send Start -->
					mail.setRefDataId(member.getMbrno());
		            mail.setToAddr(member.getEmailaddr());
		            mail.setContentData(contentData);
					mail.setFromAddr(conf.getString("omp.admin.punish.mail.from.addr"));
					mail.setFromName(conf.getString("omp.admin.punish.mail.from.name"));
					mail.setReturnAddr(conf.getString("omp.admin.punish.mail.return.addr"));
		            
		            msa = CommunicatorFactory.getMailSendAgent();
		            
		            msa.requestMailSend(mail);
					// <-- Result Email Send End
					
					json.put("result", "SUCCESS");
					
					super.daoManager.commitTransaction();
					
					// <------------------------------------------ Pay Dev Member State Update End **********
				}
			}else  if(devMember.getType().equals("withdraw")){
				
				// ********** Pay & Free Dev Member Withdraw ------------------------------------------>
				super.daoManager.startTransaction();
				
				// Update Memeber
				devMember.setRegid(adSession.getAdMgr().getMgrId());
				devMember.setMbrstatregdt(date);
				devMember.setUptDt(date);
				
				super.commonDAO.update("devMemberMgr.updateTransStateReject_Withdraw", devMember);
				
				UserMemberMgr member = (UserMemberMgr)super.commonDAO.queryForObject("devMemberMgr.findDevMemberDetail", devMember.getMbrno());
				
				transinfo = before_Cls_Cd + ":" + before_Cat_Cd + ":" + member.getMbrclscd() + ":" + member.getMbrcatcd();
					
				// Sequence Trans No
				transno = (String) super.commonDAO.queryForObject("devMemberMgr.seqTransNo");
				
				member.setTransno(transno);
				member.setRegid(adSession.getAdMgr().getMgrId());
				member.setTransinfo(transinfo);
					
				// Insert Trans Hist
				super.commonDAO.insert("devMemberMgr.insertTransHist", member);	
				
				// Update Member Sub Hist
				super.commonDAO.update("devMemberMgr.updateMemberSubHist", member);	
				
				// Insert Member Term Hist
				super.commonDAO.insert("devMemberMgr.insertMemberTermHist", member);	
				
				// Result Email Send Start -->
				contentData.setMbrId(member.getMbrid());
				contentData.setReqDt(transInfo.getRegdts());
				
				mail.setTemplateId("/DEV/US_018.html");
				mail.setRefAppId("DevMemberMgrAction.ajaxStateUpdateExcute_018");
				mail.setSubject(LocalizeMessage.getLocalizedMessage("[Whoopy 개발자센터] 개발자 사이트에서 탈퇴되었습니다."));
				mail.setRefDataId(member.getMbrno());
	            mail.setToAddr(member.getEmailaddr());
	            mail.setContentData(contentData);
				mail.setFromAddr(conf.getString("omp.admin.punish.mail.from.addr"));
				mail.setFromName(conf.getString("omp.admin.punish.mail.from.name"));
				mail.setReturnAddr(conf.getString("omp.admin.punish.mail.return.addr"));
	            
	            msa = CommunicatorFactory.getMailSendAgent();
	            
	            msa.requestMailSend(mail);
				// <-- Result Email Send End
				
				json.put("result", "SUCCESS");

				super.daoManager.commitTransaction();
				
				// <------------------------------------------ Pay & Free Dev Member Withdraw End **********
			}
		} catch (NoticeException e) {
			throw e;
		} catch(ServiceException e) {
			throw e;
		}catch(Exception e){
            throw new ServiceException("ajaxStateUpdateExcute Error.", e);
		}finally{
			super.daoManager.endTransaction();
		}
		
		return json;
	}

	@Override
	public UserMemberMgr transApplicationFormView(UserMemberMgr devMember) throws ServiceException {
		UserMemberMgr userInfo = new UserMemberMgr();
		try{
			userInfo = (UserMemberMgr)super.commonDAO.queryForObject("devMemberMgr.transApplicationFormView", devMember);
			
			if(userInfo != null && StringUtils.isNotEmpty(userInfo.getAcctno())){
				userInfo.setAcctno(CipherAES.decryption(userInfo.getAcctno()));
			}
		} catch (NoticeException e) {
			throw e;
		} catch(ServiceException e) {
			throw e;
		}catch(Exception e){
            throw new ServiceException("transApplicationFormView Error.", e);
		}
		
		return userInfo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HashMap<String, String>> transApplicationFormViewMemDoc(UserMemberMgr devMember) throws ServiceException {
		try {
			return super.commonDAO.queryForList("devMemberMgr.transApplicationFormViewMemDoc", devMember);
		} catch (NoticeException e) {
			throw e;
		} catch(ServiceException e) {
			throw e;
		}catch(Exception e){
            throw new ServiceException("transApplicationFormViewMemDoc Error.", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserMemberMgr> popTransHistoryView(UserMemberMgr devMember) throws ServiceException {
		try{
			return super.commonDAO.queryForPageList("devMemberMgr.popTransHistoryView", devMember);
		} catch (NoticeException e) {
			throw e;
		} catch(ServiceException e) {
			throw e;
		}catch(Exception e){
            throw new ServiceException("popTransHistoryView Error.", e);
		}
	}

	@Override
	public String popRejectView(UserMemberMgr devMember) throws ServiceException {
		try {
			return (String)super.commonDAO.queryForObject("devMemberMgr.popRejectReasonView", devMember);
		} catch (NoticeException e) {
			throw e;
		} catch(ServiceException e) {
			throw e;
		}catch(Exception e){
            throw new ServiceException("popRejectView Error.", e);
		}
	}

	@Override
	public UserMemberMgr popWithdrawView(UserMemberMgr devMember) throws ServiceException {
		try {
			return (UserMemberMgr)super.commonDAO.queryForObject("devMemberMgr.popWithdrawReasonView", devMember);
		} catch (NoticeException e) {
			throw e;
		} catch(ServiceException e) {
			throw e;
		}catch(Exception e){
            throw new ServiceException("popWithdrawView Error.", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HashMap<String, String>> testPhoneInfoList(String mbrno) throws ServiceException {
		try{
			return super.commonDAO.queryForList("devMemberMgr.testPhoneInfoList", mbrno);
		} catch (NoticeException e) {
			throw e;
		} catch(ServiceException e) {
			throw e;
		}catch(Exception e){
            throw new ServiceException("testPhoneInfoList Error.", e);
		}
	}

}
