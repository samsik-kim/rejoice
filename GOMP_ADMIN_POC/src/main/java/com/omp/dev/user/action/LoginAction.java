package com.omp.dev.user.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

import com.omp.commons.action.BaseAction;
import com.omp.commons.communicate.CommunicatorFactory;
import com.omp.commons.communicate.mail.MailSendAgent;
import com.omp.commons.communicate.mail.SendMailModel;
import com.omp.commons.exception.NoticeException;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.model.MemberEmailModel;
import com.omp.commons.text.LocalizeMessage;
import com.omp.commons.util.CipherAES;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.SessionUtil;
import com.omp.dev.common.Constants;
import com.omp.dev.common.util.ReturnUrlGenerateUtil;
import com.omp.dev.user.model.Session;
import com.omp.dev.user.service.UserService;
import com.omp.dev.user.service.UserServiceImpl;

@SuppressWarnings("serial")
public class LoginAction extends BaseAction{
	
	/**
	 * Logger
	 */
	protected GLogger logger = new GLogger(this.getClass());
	
	private String resultId;
	private String errMsg;
	private String userId;
	private String twIdNm;
	private String compNm;
	private String passportNm;
	private String email;
	private String twIdPsRegNo;
	private String compPsRegNo;
	private String passportPsRegNo;
	private String mbrNo;
	private String redirectUrl;
	
	private UserService userService;
	
	private MailSendAgent msa;
	private SendMailModel mail;
	private MemberEmailModel model;
	private Session session;
	
	public LoginAction(){
		userService = new UserServiceImpl();
	}
	
	/**
	 * LoginMain
	 * @return
	 * @throws Exception
	 */
	public String loginMain() throws Exception{
		logger.debug("<LoginAction  loginMain>");
		return SUCCESS;
	}
	
	/**
	 * 
	 *  1. 2011 - 04 - 12 (Form Submit() - Login 수정)
	 * @return
	 * @throws Exception
	 */
	public String login()throws Exception{
		String result = "loginResult";
		session = new Session();

		session.setMbrId(StringUtils.defaultString(this.req.getParameter("user_id"), ""));
		String pw = StringUtils.defaultString(this.req.getParameter("user_passwd"), "");
		session.setMbrPw(CipherAES.encryption(pw));
		session.setRemoteAddr(this.req.getRemoteAddr());
		
		this.setStep("CallServiceGetSession");
		session = userService.getSession(session);
		logger.debug("Login result :: " + session.getResultCode());
		//logger.debug("URL ==> " + req.getSession(true).getAttribute(Constants.RETURN_URL_KEY));
		//session.setReturnUrl(this.req.getRequestURI());
		this.setStep("CheckValidateInput");
		if(session.getResultCode() == 100){
			SessionUtil.setMemberSession(this.req, session);
			logger.debug("100 - Login SUCCESS");
			result = "loginResult";
		}else if(session.getResultCode() == 200){
			//아이디 없음
			logger.debug("200 - ID Null");
			result = "fail";
		}else if(session.getResultCode() == 300){
			//비밀번호 불일치
			logger.debug("300 - PW Fail");
			result = "fail";
		}else if(session.getResultCode() == 400){
			logger.debug("400 - Not Login Stat");
			logger.debug("DevStat ==> " + session.getDevMbrStatCd());
			result = "fail";
			//Email ReAuth
			if(Constants.MEM_DEV_STATUS_REG_MOTION.equals(session.getDevMbrStatCd())){
				logger.debug("404 - DevStat ==> Join Ready");
				result = "reSendEmail";
			}
		}else if(session.getResultCode() == 500){
			
			result = "loginFailTempPw";
		}
		this.setPrimaryKey("ACTOR", session.getMbrNo(), "JOB_TYPE", "LOGIN");
		session.setResultMsg(result);
		return SUCCESS;
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	public String ajaxSendTempPassword()throws Exception{
		//
		String email = StringUtils.defaultString(this.req.getParameter("emailAddr"));
		String mbrId = StringUtils.defaultString(this.req.getParameter("mbrId"));
		String tempPw = StringUtils.defaultString(this.req.getParameter("tempPw"));
		String mbrNo = StringUtils.defaultString(this.req.getParameter("mbrNo"));
		try {
			//비밀번호 15회 오류 - 이메일 발송
			//1. MemberEmailModel Set
			model = new MemberEmailModel();
			model.setMbrId(mbrId);
			model.setTempPw(tempPw);
			model.setSendDt(1);
			model.setMainUrl(ReturnUrlGenerateUtil.rtnUrl(this.req));
			//2. SendMailModel Set
			mail = new SendMailModel();
			mail.setTemplateId("/DEV/US_010.html");
			mail.setRefAppId("LoginAction.ajaxSendTempPassword");
			mail.setSubject(LocalizeMessage.getLocalizedMessage("[Whoopy 개발자센터] 임시비밀번호 발급 안내입니다."));
			mail.setRefDataId(mbrNo);
			mail.setToAddr(email);
			mail.setContentData(model);
			mail.setFromAddr(conf.getString("omp.dev.email.default.from"));
			mail.setFromName(conf.getString("omp.dev.email.default.fromName"));
			mail.setReturnAddr(conf.getString("omp.dev.email.default.return"));
			msa = CommunicatorFactory.getMailSendAgent();
			//3. Send Mail
			msa.requestMailSend(mail);
		} catch (Exception e) {
			throw new ServiceException("메일 전송 정상적으로 발송되지 않았습니다.", e);
		}
		return SUCCESS;
	}
	
	/**
	 * Login
	 * @return
	 * @throws Exception
	 */
	public String loginResult() throws Exception{
		logger.debug("<LoginAction loginResult>");
		return SUCCESS;
	}
	
	/**
	 * Pop Layer
	 * @return
	 * @throws Exception
	 */
	public String ajaxReEmailCertification() throws Exception{
		HttpServletRequest request = this.req;
		
		email = StringUtils.defaultString(request.getParameter("email"), "");
		
		return SUCCESS;
	}
	
	public void reEmailCertification() throws Exception{
		HttpServletRequest request = this.req;
		HttpServletResponse response = this.res;
		JSONObject jsonObject = null;
		PrintWriter writer = null;
		String mailSendResult = null; // 배열 index[0] : 아이디
		
		try {
			String radioFlag = request.getParameter("radio");
			
			Session session = new Session();
			
			session.setEmailAddr(StringUtils.defaultString(request.getParameter("emailAddr"), ""));
			session.setMbrNo(StringUtils.defaultString(request.getParameter("mbrNoC"), ""));		
			String returnUrl = ReturnUrlGenerateUtil.rtnUrl(req, Constants.AUTH_EMAIL_JOIN, session.getMbrNo());
			mailSendResult = userService.reEmailCertification(radioFlag, session);
			try {
				//1. MemberEmailModel Set
				model = new MemberEmailModel();
				model.setMbrId(mailSendResult);
				model.setReturnUrl(returnUrl);
				//2. SendMailModel Set
				mail = new SendMailModel();
				mail.setTemplateId("/DEV/US_001.html");
				mail.setRefAppId("userAction.reEmailCertification");
				mail.setSubject(LocalizeMessage.getLocalizedMessage("[Whoopy 개발자센터] 이메일 재인증 입니다."));
				mail.setRefDataId(session.getMbrNo());
				mail.setToAddr(session.getEmailAddr());
				mail.setContentData(model);
				mail.setFromAddr(conf.getString("omp.dev.email.default.from"));
				mail.setFromName(conf.getString("omp.dev.email.default.fromName"));
				mail.setReturnAddr(conf.getString("omp.dev.email.default.return"));
				msa = CommunicatorFactory.getMailSendAgent();
				//3. Send Mail
				msa.requestMailSend(mail);
			} catch (Exception e) {
				throw new ServiceException("메일 전송 정상적으로 발송되지 않았습니다.", e);
			}
		
			jsonObject = new JSONObject();
			jsonObject.put("data", "success");
			
			writer = response.getWriter();
			writer.write(jsonObject.toString());
		} catch(Exception e) {
			throw new ServiceException("<Login reEmailCertification>", e); 
		} finally { if(writer != null) { writer.close(); } }
		
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	public String findId() throws Exception {
		return SUCCESS;
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	public String findPw() throws Exception {
		return SUCCESS;
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	public String findIdResult() throws Exception {
		String flag = "";
		String mbrClsCd = "";
		Session session = null;
		
		if (twIdNm != null) {
			flag = "name";
			mbrClsCd = Constants.MEM_CLS_PERSON;
			session = userService.getDevMemberById(twIdNm, twIdPsRegNo, mbrClsCd, flag);
		}
		if (compNm != null) {
			flag = "compNm";
			mbrClsCd = Constants.MEM_CLS_BUSINESS;
			session = userService.getDevMemberById(compNm, compPsRegNo, mbrClsCd, flag);
		}
		if (passportNm != null) {
			flag = "name";
			mbrClsCd = Constants.MEM_CLS_FOREIGNER;
			session = userService.getDevMemberById(passportNm, passportPsRegNo, mbrClsCd, flag);
		}
		if (email != null) {
			flag = "email";
			session = userService.getDevMemberById(email, flag);
		}
		
		if(logger.isDebugEnabled()) logger.debug("session :: " + session);
		
		if(session != null && StringUtils.isNotEmpty(email)) {
			//TODO [SEND - EMAIL] (S)
			try {
				//1. MemberEmailModel Set
				model = new MemberEmailModel();
				model.setMbrId(session.getMbrId());
				model.setMainUrl(ReturnUrlGenerateUtil.rtnUrl(this.req));
				//2. SendMailModel Set
				mail = new SendMailModel();
				mail.setTemplateId("/DEV/US_011.html");
				mail.setRefAppId("userAction.findIdResult");
				mail.setSubject(LocalizeMessage.getLocalizedMessage("[Whoopy 개발자센터] 회원님께서 요청하신 아이디입니다."));
				mail.setRefDataId(session.getMbrNo());
				mail.setToAddr(session.getEmailAddr());
				mail.setContentData(model);
				mail.setFromAddr(conf.getString("omp.dev.email.default.from"));
				mail.setFromName(conf.getString("omp.dev.email.default.fromName"));
				mail.setReturnAddr(conf.getString("omp.dev.email.default.return"));
				msa = CommunicatorFactory.getMailSendAgent();
				//3. Send Mail
				msa.requestMailSend(mail);
				errMsg = LocalizeMessage.getLocalizedMessage("입력한 이메일로 아이디를 발송하였습니다.");
			} catch (Exception e) {
				throw new ServiceException("메일 전송 정상적으로 발송되지 않았습니다.", e);
			}
			
			if (email != null) {
				this.redirectUrl = this.conf.getString("omp.common.url-prefix.http.dev") + this.req.getContextPath() + "/main/main.omp";
				return "successE";
			}
			
			return INPUT;
		} else if(session == null) {
			errMsg = (LocalizeMessage.getLocalizedMessage("가입되지 않은 정보입니다."));
			return INPUT;
		}
		
		resultId = session.getMbrId();
		return SUCCESS;
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	public String findPwResult() throws Exception {
		
		if(StringUtils.isEmpty(userId)){
			throw new NoticeException("아이디를 입력해주세요.");
		}
		Session session = userService.getDevMemberByPw(userId); 
		
		if (session == null) {
			errMsg = LocalizeMessage.getLocalizedMessage("존재하지 않는 아이디 입니다.");
			return "inputP";
		} else {
			email = session.getEmailAddr();
			
			//1. MemberEmailModel Set
			model = new MemberEmailModel();
			model.setTempPw(session.getMbrPw());
			model.setMbrId(session.getMbrId());
			model.setMainUrl(ReturnUrlGenerateUtil.rtnUrl(this.req));
			//2. SendMailModel Set
			mail = new SendMailModel();
			mail.setTemplateId("/DEV/US_012.html");
			mail.setRefAppId("userAction.findPwResult");
			mail.setSubject(LocalizeMessage.getLocalizedMessage("[Whoopy 개발자센터] 회원님께서 요청하신 비밀번호입니다."));
			mail.setRefDataId(session.getMbrNo());
			mail.setToAddr(session.getEmailAddr());
			mail.setContentData(model);
			mail.setFromAddr(conf.getString("omp.dev.email.default.from"));
			mail.setFromName(conf.getString("omp.dev.email.default.fromName"));
			mail.setReturnAddr(conf.getString("omp.dev.email.default.return"));
			msa = CommunicatorFactory.getMailSendAgent();
			//3. Send Mail
			msa.requestMailSend(mail);
		}
		
		return SUCCESS;
	}
	
	public String getResultId() {
		return resultId;
	}

	public void setResultId(String resultId) {
		this.resultId = resultId;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTwIdNm() {
		return twIdNm;
	}

	public void setTwIdNm(String twIdNm) {
		this.twIdNm = twIdNm;
	}

	public String getCompNm() {
		return compNm;
	}

	public void setCompNm(String compNm) {
		this.compNm = compNm;
	}

	public String getPassportNm() {
		return passportNm;
	}

	public void setPassportNm(String passportNm) {
		this.passportNm = passportNm;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTwIdPsRegNo() {
		return twIdPsRegNo;
	}

	public void setTwIdPsRegNo(String twIdPsRegNo) {
		this.twIdPsRegNo = twIdPsRegNo.toUpperCase();
	}

	public String getCompPsRegNo() {
		return compPsRegNo;
	}

	public void setCompPsRegNo(String compPsRegNo) {
		this.compPsRegNo = compPsRegNo;
	}

	public String getPassportPsRegNo() {
		return passportPsRegNo;
	}

	public void setPassportPsRegNo(String passportPsRegNo) {
		this.passportPsRegNo = passportPsRegNo;
	}

	/**
	 * @return the mbrNo
	 */
	public String getMbrNo() {
		return mbrNo;
	}

	/**
	 * @param mbrNo the mbrNo to set
	 */
	public void setMbrNo(String mbrNo) {
		this.mbrNo = mbrNo;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	/**
	 * @return the session
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * @param session the session to set
	 */
	public void setSession(Session session) {
		this.session = session;
	}
	
}

