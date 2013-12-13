package com.omp.dev.member.action;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

import com.omp.commons.action.BaseAction;
import com.omp.commons.communicate.CommunicatorFactory;
import com.omp.commons.communicate.mail.MailSendAgent;
import com.omp.commons.communicate.mail.SendMailModel;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.model.MemberEmailModel;
import com.omp.commons.text.LocalizeMessage;
import com.omp.commons.util.CipherAES;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.config.ConfigProperties;
import com.omp.dev.common.Constants;
import com.omp.dev.common.util.ReturnUrlGenerateUtil;
import com.omp.dev.member.model.Member;
import com.omp.dev.member.service.MemberService;
import com.omp.dev.member.service.MemberServiceImpl;
import com.omp.tw.commons.util.Discernment;

/**
 * @author P007974
 *
 */
@SuppressWarnings("serial")
public class MemberAction extends BaseAction{
	
	/**
	 * Logger
	 */
	protected GLogger logger = new GLogger(this.getClass());
	
	/*
	 * Member Service
	 */
	private MemberService memberService;
	
	/**
	 * Map
	 */
	private HashMap<String, Object> map = new HashMap<String, Object>();
	
	private String param;
	private String twId;
	private MailSendAgent msa;
	private SendMailModel mail;
	private MemberEmailModel model;
	/**
	 * 
	 */
	private Member member;
	
	public MemberAction(){
		member = new Member();
		memberService = new MemberServiceImpl();
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	public String registMain()throws Exception{
		logger.debug("<MemberAction - registMain>");
		return SUCCESS;
	}
	
	/**
	 * TODO Agreement
	 * @return
	 * @throws Exception
	 */
	public String registAgreement()throws Exception{
		logger.debug("<MemberAction - registAgreement>");
		return SUCCESS;
	}
	
	/**
	 * TODO Regist Check
	 * person, company, foreigner
	 * @throws Exception
	 */
	public void ajaxRegistCheck() throws Exception{
		logger.debug("<MemberAction - ajaxRegistCheck>");
		int resultCode = 0;
		int regNo = 9;
		logger.debug("Nm :: " + member.getMbrNm() + "   No : " + member.getPsRegNo());
		if(StringUtils.isNotEmpty(member.getPsRegNo()) && StringUtils.isNotEmpty(member.getMbrNm())){
			String mbrNm = member.getMbrNm();
			member.setMbrNm("");
			// Person
			if(Constants.MEM_CLS_PERSON.equals(member.getMbrClsCd())){
				logger.debug("Constants.MEM_CLS_PERSON");
				//ROC ID - Check
				if(Discernment.isROCID(member.getPsRegNo())){
					member.setPsRegNo(CipherAES.encryption(member.getPsRegNo()));
					resultCode = memberService.registCheck(member);
					if(resultCode < 1){
						member.setMbrNm(mbrNm);
						resultCode = memberService.registCheck(member);
					}
					//overlap(1) OR Available(0)
					regNo = resultCode > 0 ? 1 : 0;
					logger.debug("Discernment Fail OR SUCCESS");
				}else{
					//Not Use
					regNo = 3;
					logger.debug("Not Use");
				}
			//BUSINESS
			}else if(Constants.MEM_CLS_BUSINESS.equals(member.getMbrClsCd())){
				logger.debug("BUSINESS & FOREIGNER [START]");
				resultCode = memberService.registCheck(member);
				if(resultCode < 1){
					member.setMbrNm(mbrNm);
					resultCode = memberService.registCheck(member);
					logger.debug("BUSINESS & FOREIGNER [SUCCESS]");
				}
				//overlap(1) OR Available(0)
				regNo = resultCode > 0 ? 1 : 0;
				logger.debug("BUSINESS & FOREIGNER Fail OR SUCCESS  - regNo ::" + regNo);
			//FOREIGNER
			}else if(Constants.MEM_CLS_FOREIGNER.equals(member.getMbrClsCd())){
				logger.debug("BUSINESS & FOREIGNER [START]");
				resultCode = memberService.registCheck(member);
				if(resultCode < 1){
					member.setMbrNm(mbrNm);
					resultCode = memberService.registCheck(member);
					logger.debug("BUSINESS & FOREIGNER [SUCCESS]");
				}
				//overlap(1) OR Available(0)
				regNo = resultCode > 0 ? 1 : 0;
				logger.debug("BUSINESS & FOREIGNER Fail OR SUCCESS  - regNo ::" + regNo);
			}
		}
		
		JSONObject jsonObject = new JSONObject();
		HttpServletResponse response = this.res;
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter writer = null;
		try {
			jsonObject.put("data", regNo);
			writer = response.getWriter();
			writer.write(jsonObject.toString());
		} catch (Exception e) {
			throw new ServiceException("<MemberAction ajaxRegistCheck>", e);
		} finally { if(writer != null) { writer.close(); } }
	}
	
	/**
	 * [CHECK - ID]
	 * @return
	 * @throws Exception
	 */
	public String ajaxDuplicateIdCheck() throws Exception{
		if(logger.isDebugEnabled()){
			logger.debug("<MemberAction - popDuplicateIdCheck>");
		}
		int result = 0;

		logger.debug("mbrId :: " + this.req.getParameter("mbrId"));

		result = memberService.duplicateIdCheck(this.req.getParameter("mbrId"));
		map.put("result", result > 0 ? "FAIL" : "SUCCESS");
		map.put("mbrId", this.req.getParameter("mbrId"));
		return SUCCESS;
	}
	
	/**
	 * TODO Email Check
	 * @throws Exception
	 */
	public void ajaxEmailCheck()throws Exception{
		logger.debug("<MemberAction - ajaxEmailCheck>");
		int resultCnt = 0;
		String result = "";
		logger.debug("Email :: " + member.getEmailAddr());
		if(StringUtils.isNotEmpty(member.getEmailAddr())){
			resultCnt = memberService.duplicateEmailCheck(member.getEmailAddr());
			result = resultCnt > 0 ? "FAIL" : "SUCCESS";
		}else{
			result = "FAIL";
		}
		JSONObject jsonObject = new JSONObject();
		HttpServletResponse response = this.res;
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter writer = null;
		
		try {
			jsonObject.put("data", result);
			writer = response.getWriter();
			writer.write(jsonObject.toString());
		} catch (Exception e) {
			throw new ServiceException("<MemberAction ajaxEmailCheck>", e);
		} finally { if(writer != null) { writer.close(); } }
	}
	
	/**
	 * TODO MBR_ID Check
	 * @throws Exception
	 */
	public void ajaxIdCheck()throws Exception{
		logger.debug("<MemberAction - ajaxIdCheck>");
		int resultCnt = 0;
		String resString = "";
		//mbrId
		if(StringUtils.isNotEmpty(this.req.getParameter("mbrId"))){
			resultCnt = memberService.duplicateIdCheck(this.req.getParameter("mbrId"));
			resString = resultCnt > 0 ? "FAIL" : "SUCCESS";
		}else{
			resString = "FAIL";
		}
		JSONObject jsonObject = new JSONObject();
		HttpServletResponse response = this.res;
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter writer = null;
		map.put("result", resString);
		map.put("mbrId", this.req.getParameter("mbrId"));
		try {
			jsonObject.put("data", map);
			
			writer = response.getWriter();
			writer.write(jsonObject.toString());
		} catch (Exception e) {
			throw new ServiceException("<MemberAction ajaxIdCheck>", e);
		} finally { if(writer != null) { writer.close(); } }
	}
	
	/**
	 * TODO INSERT [person, company, foreigner]
	 * @throws Exception
	 */
	public void ajaxJoinMember()throws Exception{
		logger.debug("<MemberAction - insertMember>");
		
		//[PW - CipherAES]
		member.setMbrPw(CipherAES.encryption(member.getMbrPw()));
		// TW ID 암호화
		if(Constants.MEM_CLS_PERSON.equals(member.getMbrClsCd())){
			member.setPsRegNo(CipherAES.encryption(member.getPsRegNo().toUpperCase()));
		}
		
		HashMap<Object, String> map = memberService.insertMember(member);
		
		logger.debug("<MemberAction - ajaxJoinMember> mbrNo : " + member.getMbrNo());
		JSONObject jsonObject = new JSONObject();
		HttpServletResponse response = this.res;
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter writer = null;
		
		try {
			jsonObject.put("data", map);
			writer = response.getWriter();
			writer.write(jsonObject.toString());
		} catch (Exception e) {
			throw new ServiceException("<MemberAction ajaxJoinMember>", e);
		} finally { if(writer != null) { writer.close(); } }
	}
	
	/**
	 * TODO JOIN FORM [person, company, foreigner]
	 * @return
	 * @throws Exception
	 */
	public String registJoinForm() throws Exception{
		logger.debug("<MemberAction - registJoinForm>");
		if(Constants.MEM_CLS_PERSON.equals(member.getMbrClsCd())){
			member.setResultMsg("person");
		}else if(Constants.MEM_CLS_BUSINESS.equals(member.getMbrClsCd())){
			member.setResultMsg("company");
		}else if(Constants.MEM_CLS_FOREIGNER.equals(member.getMbrClsCd())){
			member.setResultMsg("foreigner");
		}
		return member.getResultMsg();
	}
	
	/**
	 * TODO Join Finish
	 * @return
	 * @throws Exception
	 */
	public String registFinish()throws Exception{
		logger.debug("<MemberAction - resgistFinsh>");
		String mbrNo = memberService.getMemberNo(member.getMbrId());
		String url = ReturnUrlGenerateUtil.rtnUrl(this.req, Constants.AUTH_EMAIL_JOIN, mbrNo);
		//인증 URL
		//member.setWebSiteUrl(url);
		//logger.debug("URL :: " + url);
		
		try {
			ConfigProperties	conf	= new ConfigProperties();
			String templateId = "/DEV/US_001.html";
			// Join Finish Email
			// 1. MemberEmailModel Set
			model = new MemberEmailModel();
			model.setReturnUrl(url);
			model.setMbrId(member.getMbrId());
			// 2. SendMailModel Set
			mail = new SendMailModel();
			mail.setTemplateId(templateId);
			mail.setRefAppId("MemberAction.registFinish");
			mail.setSubject(LocalizeMessage.getLocalizedMessage("[Whoopy 개발자센터] 회원가입을 환영합니다."));
			mail.setRefDataId(mbrNo);
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
		
		return SUCCESS;
	}
	
	public String registJoinSendEmail()throws Exception{
		return SUCCESS;
	}
	
	
	/**
	 * Email Certification
	 * @return
	 * @throws Exception
	 */
	public String memberJoinCertification()throws Exception{
		String result = "finsh";
		String mbrNo = CipherAES.decryption(param);
		//[SELECT] EmailCertYn
		this.setStep("CallServiceEmailCert");
		member = memberService.getEmailCert(mbrNo); 
		
		result = Constants.YES.equals(member.getEmailCertYn()) ? "already" : "finsh";
		
		//Email Cert 'Y' update
		if(Constants.NO.equals(member.getEmailCertYn())){
			logger.debug("MEMBER UPDATE - EMAIL_CERT_YN => Y");
			memberService.updateMemberJoin(mbrNo);
			this.setStep("CallServiceMemberJoin");
		}
		this.setPrimaryKey("ACTOR", this.param, "JOB_TYPE", "EMAIL_JOIN");
		return result;
	}
	
	
	/**
	 *  - Person
	 * @return
	 * @throws Exception
	 */
	public String registPerson()throws Exception{
		logger.debug("<MemberAction - registPerson>");
		// TwID Generate Use Only Local
		//this.twId = memberService.selectTwID();
		return SUCCESS;
	}
	
	/**
	 *  - Company
	 * @return
	 * @throws Exception
	 */
	public String registCompany()throws Exception{
		logger.debug("<MemberAction - registCompany>");
		return SUCCESS;
	}
	
	/**
	 *  - Foreigner
	 * @return
	 * @throws Exception
	 */
	public String registForeigner()throws Exception{
		logger.debug("<MemberAction - registForeigner>");
		return SUCCESS;
	}

	/**
	 * @return the member
	 */
	public Member getMember() {
		return member;
	}

	/**
	 * @param member the member to set
	 */
	public void setMember(Member member) {
		this.member = member;
	}

	/**
	 * @return the map
	 */
	public HashMap<String, Object> getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(HashMap<String, Object> map) {
		this.map = map;
	}

	/**
	 * @return the param
	 */
	public String getParam() {
		return param;
	}

	/**
	 * @param param the param to set
	 */
	public void setParam(String param) {
		this.param = param;
	}

	/**
	 * @return the twId
	 */
	public String getTwId() {
		return twId;
	}

	/**
	 * @param twId the twId to set
	 */
	public void setTwId(String twId) {
		this.twId = twId;
	}
	
}
