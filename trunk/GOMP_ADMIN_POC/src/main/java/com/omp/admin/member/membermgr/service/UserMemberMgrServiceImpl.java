package com.omp.admin.member.membermgr.service;

import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import com.omp.admin.common.Constants;
import com.omp.admin.member.membermgr.model.PhoneInfoHist;
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
import com.omp.commons.util.RandomString;
import com.omp.commons.util.config.ConfigProperties;

public class UserMemberMgrServiceImpl extends AbstractService implements UserMemberMgrService {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserMemberMgr> findUserMemberMgrList(UserMemberMgr userMember) throws ServiceException {
		try{
			return super.commonDAO.queryForPageList("userMemberMgr.findUserMemberMgrList", userMember);
		} catch (NoticeException e) {
			throw e;
		} catch(ServiceException e) {
			throw e;
		}catch(Exception e){
            throw new ServiceException("findUserMemberMgrList Error.", e);
		}
	}

	@Override
	public UserMemberMgr findUserMemberDetail(String mbrno) throws ServiceException {
		try{
			return (UserMemberMgr)super.commonDAO.queryForObject("userMemberMgr.findUserMemberDetail", mbrno);
		} catch (NoticeException e) {
			throw e;
		} catch(ServiceException e) {
			throw e;
		}catch(Exception e){
            throw new ServiceException("findUserMemberDetail Error.", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HashMap<String, String>> phoneInfoList(String mbrno) throws ServiceException {
		try{
			return (List<HashMap<String, String>>)super.commonDAO.queryForList("userMemberMgr.phoneInfoList", mbrno);
		} catch (NoticeException e) {
			throw e;
		} catch(ServiceException e) {
			throw e;
		}catch(Exception e){
            throw new ServiceException("phoneInfoList Error.", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserMemberMgr> loginInfoHistory(UserMemberMgr userMember) throws ServiceException {
		 try{
			 return super.commonDAO.queryForPageList("userMemberMgr.loginInfoHistory", userMember);
	 	} catch (NoticeException e) {
			throw e;
		} catch(ServiceException e) {
			throw e;
		}catch(Exception e){
            throw new ServiceException("loginInfoHistory Error.", e);
		}
	}

	@Override
	public JSONObject userPhoneInfoDelete(UserMemberMgr userMember) throws ServiceException {
		JSONObject json = new JSONObject();
		
		 try{
			PhoneInfoHist  phonehist = (PhoneInfoHist)super.commonDAO.queryForObject("userMemberMgr.phoneInfo", userMember);
			 
			String hphistseq = (String)super.commonDAO.queryForObject("userMemberMgr.seqHpHistSeq");
			 
			phonehist.setHphistseq(hphistseq);
			phonehist.setHiscd(Constants.PHONE_HIS_CD_ADMIN_DIRECT_DELETE);
			 
			super.daoManager.startTransaction();
			 
			super.commonDAO.delete("userMemberMgr.userPhoneInfoDelete", userMember);
			
			super.commonDAO.insert("userMemberMgr.insertPhoneInfoHistory", phonehist);
			
			json.put("result", "SUCCESS");
			
			super.daoManager.commitTransaction();
		} catch (NoticeException e) {
				throw e;
		} catch(ServiceException e) {
				throw e;
		}catch(Exception e){
            throw new ServiceException("userPhoneInfoDelete Error.", e);
		}finally{
			super.daoManager.endTransaction();
		}
			
		return json;
	}

	@Override
	public JSONObject ajaxEmailSendExcute(UserMemberMgr userMember) throws ServiceException {
		MemberEmailModel contentData;
		MailSendAgent msa;
		SendMailModel mail;
		JSONObject json = new JSONObject();
		ConfigProperties	conf	= new ConfigProperties();
		
		try{
			super.daoManager.startTransaction();
			 
			String tempEmail = userMember.getTempEmailAddr();
			String password = RandomString.getString(4, RandomString.TYPE_NUMBER);
			 
			contentData = new MemberEmailModel();
			contentData.setMbrId(userMember.getMbrid());
			contentData.setTempPw(password);
			 
			// Password Encode
			userMember.setMbrpw(CipherAES.encryption(password));
			
			// Difference Email Address Update
			if(!userMember.getEmailaddr().equals(tempEmail)){
				userMember.setEmailaddr(tempEmail);
			}
			
			super.commonDAO.update("userMemberMgr.updateEmailAddr", userMember);
			
			// Email Send Process Start	--->
			mail = new SendMailModel();
			mail.setTemplateId("/SCI/US_001.html");
			mail.setRefAppId("UserMemberMgrAction.ajaxEmailSendExcute_001");
			mail.setRefDataId(userMember.getMbrno());
			mail.setSubject(LocalizeMessage.getLocalizedMessage("[Whoopy] {0}님 요청하신 임시비밀번호를 안내해 드립니다.", new Object[] {userMember.getMbrid()}));
            mail.setToAddr(userMember.getEmailaddr());
            mail.setContentData(contentData);
			mail.setFromAddr(conf.getString("omp.admin.punish.mail.from.addr"));
			mail.setFromName(conf.getString("omp.admin.punish.mail.from.name"));
			mail.setReturnAddr(conf.getString("omp.admin.punish.mail.return.addr"));
            
            msa = CommunicatorFactory.getMailSendAgent();
            
            msa.requestMailSend(mail);
			// <--- Email Send Process End
			
			json.put("result", "SUCCESS");
			
			super.daoManager.commitTransaction();
		} catch (NoticeException e) {
			throw e;
		} catch(ServiceException e) {
			throw e;
		}catch(Exception e){
            throw new ServiceException("ajaxEmailSendExcute Error.", e);
		}finally{
			super.daoManager.endTransaction();
		}
			
		return json;
	}

}
