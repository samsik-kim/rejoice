package com.omp.dev.member.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.xwork.StringUtils;
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
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.SessionUtil;
import com.omp.commons.util.config.ConfigProperties;
import com.omp.dev.common.Constants;
import com.omp.dev.common.util.ReturnUrlGenerateUtil;
import com.omp.dev.contents.service.ContentManagementService;
import com.omp.dev.contents.service.ContentManagementServiceImpl;
import com.omp.dev.member.model.Bank;
import com.omp.dev.member.model.Member;
import com.omp.dev.member.model.MemberTransHist;
import com.omp.dev.member.model.MemberWithdrawInfo;
import com.omp.dev.member.service.MyPageService;
import com.omp.dev.member.service.MyPageServiceImpl;
import com.omp.dev.purchase.model.Purchase;
import com.omp.dev.user.model.Session;

@SuppressWarnings("serial")
public class MyPageAction extends BaseAction {

	/**
	 * Logger
	 */
	protected GLogger logger = new GLogger(this.getClass());
	
	private MyPageService myPageService = null;
	private ContentManagementService contentManagementService = null;
	
	private Member profileInfo = null;					// 기본정보
	private MemberTransHist calculateInfo = null;		// 정산 정보
	private MemberTransHist transferInfo = null;		// 정산 정보
	private MemberWithdrawInfo withdrawInfo = null;		//탈퇴 이력 정보
	private Bank bank = null;							//
	private List<Bank> bankList;						//
	private Purchase purchase = new Purchase();
	private String usrPw = "";		// variable for mypageCertificationAjax
	private String param = null;
	private MailSendAgent msa;
	private SendMailModel mail;
	private MemberEmailModel model;
	private String isTransMenuValid = "FALSE";	// 회원 전환 메뉴 유효성
	private String isProfile		= "";	// 회원 기본 & 정산 정보 화면 
	private String redirectUrl		= "";
	private String forwardAction 	= "";
	
	public MyPageAction() {
		this.myPageService = new MyPageServiceImpl();
		this.contentManagementService = new ContentManagementServiceImpl();
	}

	public String popupSearchBankList()throws Exception{
		
		return SUCCESS;
	}
	
	public void ajaxpopupSearchCodeList()throws Exception{
		logger.debug("<MyPageAction - ajaxpopupSearchCodeList>");
		String result = "";

		bankList = myPageService.getBankList(bank.getCdNm());

		result = bankList.size() > 0 ? "SUCCESS" : "FAIL";
		JSONArray jsonArray = JSONArray.fromObject(bankList);
		JSONObject jsonObject = new JSONObject();
		HttpServletResponse response = this.res;
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter writer = null;
		
		try {
			
			jsonObject.put("result", result);
			jsonObject.put("jsonList", jsonArray);
			writer = response.getWriter();
			writer.write(jsonObject.toString());
		} catch (Exception e) {
			throw new ServiceException("<MypageAction ajaxpopupSearchCodeList>", e);
		} finally { if(writer != null) { writer.close(); } }
	}
	
	public String mypageEmail()throws Exception{
		Session session = (Session) SessionUtil.getMemberSession(this.req);
		profileInfo = myPageService.getMemberProfile(session.getMbrNo());
		
		// LNB 회원 전환 유효성
		this.isTransMenuValid = this.myPageService.isPersonalMember(session.getMbrNo());
		return SUCCESS;
	}
	
	public String mypageTransResult()throws Exception{
		Session session = (Session) SessionUtil.getMemberSession(this.req);
		
		// LNB 회원 전환 유효성
		this.isTransMenuValid = this.myPageService.isPersonalMember(session.getMbrNo());
		
		// 접속 계정과 인증 계정 일치 여부 확인
		if(!session.getMbrNo().equals(param)) {
			throw new NoticeException("Ivalid User");
		}
		
		profileInfo = myPageService.getMemberProfile(session.getMbrNo());
		logger.debug("MBR-NO: " + session.getMbrNo());
		
		// 전환 요청 상태 체크
		if(profileInfo.getDevMbrStatCd().equals(Constants.MEM_DEV_STATUS_TURN_MOTION) 
				&& profileInfo.getMbrCatCd().equals(Constants.MEM_TYPE_DEV_NOPAY)) {
			this.myPageService.transferBusinessMember(this.req, session.getMbrNo());
			
		}
		return SUCCESS;
	}
	
	public String mypageEmailAuthResult()throws Exception{
		Session session = (Session) SessionUtil.getMemberSession(this.req);
		// LNB 회원 전환 유효성
		this.isTransMenuValid = this.myPageService.isPersonalMember(session.getMbrNo());
		
		if(!SessionUtil.existMemberSession(this.req)) {		// session out
			logger.debug("SESS_OUT");
		}else {
			
			profileInfo = new Member();
			profileInfo.setMbrNo(session.getMbrNo());
			profileInfo.setEmailAddr(this.param);
			profileInfo.setMbrId(session.getMbrId());
			//updateProfile
			myPageService.updateMemberEmail(profileInfo);
		}
		
		return SUCCESS;
	}
	
	public void ajaxSendEmail()throws Exception{
		HttpServletResponse res = this.res;
		res.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = null;
		String result = "";
		Session mbr_sess = (Session) SessionUtil.getMemberSession(this.req);
		String email = StringUtils.defaultIfEmpty(this.req.getParameter("emailAddr"), "");
		ConfigProperties	conf	= new ConfigProperties();
		
		// LNB 회원 전환 유효성
		this.isTransMenuValid = this.myPageService.isPersonalMember(mbr_sess.getMbrNo());

		try {
			if(!SessionUtil.existMemberSession(this.req)) {		// session out
				result = "SESS_OUT";
			}else {
				String mbrNo = mbr_sess.getMbrNo();
				
				HashMap map = new HashMap();
				map.put("mbrNo", mbrNo);
				map.put("changEamil", email);
				map.put("usrPw", CipherAES.encryption(this.usrPw));
				result = this.myPageService.certificationPassword(map);
				if("SUCCESS".equals(result)){
					//TODO Send Email
					String url = ReturnUrlGenerateUtil.rtnUrl(this.req, Constants.AUTH_EMAIL_CHANGE, email, mbr_sess.getMbrNo());
					//logger.debug("Returl :: " + url);
					try {
						String templateId = "/DEV/US_004.html";
						// 1. MemberEmailModel
						model = new MemberEmailModel();
						model.setMbrId(mbr_sess.getMbrId());
						model.setMainUrl(ReturnUrlGenerateUtil.rtnUrl(this.req));
						model.setReturnUrl(url);
						// 2. SendMailModel
						mail = new SendMailModel();
						mail.setTemplateId(templateId);
						mail.setRefAppId("MemberAction.registFinish");
						mail.setSubject(LocalizeMessage.getLocalizedMessage("[Whoopy 개발자센터] 이메일을 변경하시려면 인증해 주세요."));
						mail.setRefDataId(mbrNo);
						mail.setToAddr(email);
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
				}
			}
			JSONObject jsonObject = new JSONObject();

			jsonObject.put("result", result);
			jsonObject.put("sendEmail", email);
			
			out = res.getWriter();
			out.write(jsonObject.toString());
		} catch(Exception e) {
			throw new ServiceException("<MypageAction ajaxSendEmail>", e);
		} finally {
			if(out != null) {
				out.close();
			}
		}
	}
	
	/**
	 * MyPage Intro
	 * @return
	 * @throws Exception
	 */
	public String mypageIntro() throws Exception {
		Session session = (Session) SessionUtil.getMemberSession(this.req);
		// LNB 회원 전환 유효성
		
		this.isTransMenuValid = this.myPageService.isPersonalMember(session.getMbrNo());
		// 다음 화면 디폴트 설정
		if(StringUtils.defaultString(this.forwardAction).equals("")) {
			this.forwardAction = "PROFILE";
		}
		return SUCCESS;
	}
	
	/**
	 * Ajax for password certification
	 * @return
	 * @throws Exception
	 */
	public void mypageCertificationAjax() throws Exception {
		//logger.info("satrt mypageCertificationAjax");
		HttpServletResponse res = this.res;
		res.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = null;
		String result = "";
		Session mbr_sess = (Session) SessionUtil.getMemberSession(this.req);
		try {

			JSONObject jsonObject = new JSONObject();
			
			if(mbr_sess == null) {		// session out
				result = "SESS_OUT";
			}
			else {
				String mbrNo = mbr_sess.getMbrNo();
				
				HashMap paramMap = new HashMap();
				paramMap.put("mbrNo", mbrNo);
				paramMap.put("menuCat", this.param);		// 화면 메뉴 정보
				paramMap.put("usrPw", CipherAES.encryption(this.usrPw));
				
				result = this.myPageService.certificationPassword(paramMap);
				
				HashMap map = this.myPageService.isValidPrcStat(paramMap);
				
				// 회원 전환 중일 경우
				if("FALSE".equals(map.get("PRC_VALID"))) {
					result = "BLOCK";
				}
				if("SUCCESS".equals(result)){
					this.isProfile = CipherAES.encryption("TRUE");
				}
				//jsonObject.put("stat", map.get("CD_NM"));
				jsonObject.put("stat", LocalizeMessage.getLocalizedMessage(("jsp.member.mypage.msg.com04"), new Object[] {map.get("CD_NM")}));
			}

			jsonObject.put("result", result);
			jsonObject.put("isProfile", this.isProfile);
			out = res.getWriter();
			out.write(jsonObject.toString());
		} catch(Exception e) {
			throw new ServiceException("<MypageAction mypageCertificationAjax>", e);
		} finally {
			if(out != null) {
				out.close();
			}
		}
	}
	
	public void mypageCheckValidPrcStatAjax() throws Exception {
		HttpServletResponse res = this.res;
		res.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = null;
		String result = "";
		Session mbr_sess = (Session) SessionUtil.getMemberSession(this.req);
		try {

			JSONObject jsonObject = new JSONObject();
			
			if(mbr_sess == null) {		// session out
				result = "SESS_OUT";
				jsonObject.put("result", result);
			}
			else {
				String mbrNo = mbr_sess.getMbrNo();
				HashMap paramMap = new HashMap();
				paramMap.put("mbrNo", mbrNo);
				paramMap.put("menuCat", this.param);	// 화면 메뉴 정보
								
				HashMap map = this.myPageService.isValidPrcStat(paramMap);
				jsonObject.put("result", map.get("PRC_VALID"));
				jsonObject.put("stat", LocalizeMessage.getLocalizedMessage(("jsp.member.mypage.msg.com04"), new Object[] {map.get("CD_NM")}));
			}

			out = res.getWriter();
			out.write(jsonObject.toString());
		} catch(Exception e) {
			throw new ServiceException("<MypageAction mypageCheckValidPrcStatAjax>", e);
		} finally {
			if(out != null) {
				out.close();
			}
		}
	}
	
	/**
	 * show user profile
	 * @return
	 * @throws Exception
	 */
	public String mypageProfileView() throws Exception {
		Session mbr_sess = (Session) SessionUtil.getMemberSession(this.req);
		String result = "";
		try {
			logger.debug("isProfile ==> " + this.isProfile);
			
			if(CipherAES.encryption("TRUE").equals(this.isProfile)){
				result = "VIEW";
			}else{
				result = "INTRO";
			}
			// LNB 회원 전환 유효성
			this.isTransMenuValid = this.myPageService.isPersonalMember(mbr_sess.getMbrNo());
			String mbrNo = mbr_sess.getMbrNo();
			
			this.profileInfo =  this.myPageService.getMemberProfile(mbrNo);
			//logger.info(profileInfo.getMbrClsCd());
			
		} catch(Exception e) {
			throw new ServiceException("<MypageAction mypageProfileView>", e);
		}
		return result;
	}
	
	/**
	 * 2011-04-21
	 * @throws Exception
	 */
	public void ajaxMypageProfileModify() throws Exception {
		HttpServletResponse res = this.res;
		res.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = null;
		Session mbr_sess = (Session) SessionUtil.getMemberSession(this.req);
		String result = null;		// 수정후 화면
		JSONObject jsonObject = new JSONObject();
		try {

			String mbrNo = mbr_sess.getMbrNo();
			this.profileInfo.setMbrNo(mbrNo);
			this.myPageService.updateProfile(this.profileInfo);
			if(!StringUtils.defaultString(this.forwardAction).equals("")) {
				result = this.forwardAction;
			}
			jsonObject.put("result", "SUCCESS");
			out = res.getWriter();
			out.write(jsonObject.toString());
		} catch(Exception e) {
			jsonObject.put("result", "FAIL");
			throw new ServiceException("<MypageAction ajaxMypageProfileModify>", e);
		} finally {
			if(out != null) {
				out.close();
			}
		}
	}
	
	
	/**
	 * show user calculate infomation
	 * @return
	 * @throws Exception
	 */
	public String mypageCalculateInfoView() throws Exception {
		Session mbr_sess = (Session) SessionUtil.getMemberSession(this.req);
		String result = "INPUT";
		try {
			// LNB 회원 전환 유효성
			this.isTransMenuValid = this.myPageService.isPersonalMember(mbr_sess.getMbrNo());
			String mbrNo = mbr_sess.getMbrNo();
			this.calculateInfo = this.myPageService.getCalculateInfo(mbrNo);
			if(CipherAES.encryption("TRUE").equals(this.isProfile)){
				result = calculateInfo.getDispStat();
			}else{
				result = "INTRO";
			}
		} catch(Exception e) {
			throw new ServiceException("<MypageAction mypageCalculateInfoView>", e);
		}
		return result;
	}
	
	//ajaxMypageCalculateInfoInsert
	public void ajaxMypageCalculateInfoInsert() throws Exception {
		HttpServletResponse res = this.res;
		res.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = null;
		
		Session mbr_sess = (Session) SessionUtil.getMemberSession(this.req);
		Properties prop = (Properties) this.req.getSession().getServletContext().getAttribute("CONF");
		String result = "CALCULATE";
		ConfigProperties	conf	= new ConfigProperties();
		try {
			JSONObject jsonObject = new JSONObject();
			String mbrNo = mbr_sess.getMbrNo();
			this.calculateInfo.setMbrNo(mbrNo);
			this.calculateInfo.setPrcStatCd(Constants.MEM_DEV_STATUS_ACCOUNTS_WAIT);
			
			this.myPageService.insertCalculateInfo(this.calculateInfo, prop);
			
			if(!StringUtils.defaultString(this.forwardAction).equals("")) {
				result = this.forwardAction;
				
				try {
					String templateId = "/DEV/US_002.html";
					// 1. MemberEmailModel
					model = new MemberEmailModel();
					model.setMainUrl(ReturnUrlGenerateUtil.rtnUrl(this.req));
					model.setMbrId(mbr_sess.getMbrId());
					model.setReqDt(DateUtil.getSysDate());
					// 2. SendMailModel
					mail = new SendMailModel();
					mail.setTemplateId(templateId);
					mail.setRefAppId("MemberAction.registFinish");
					mail.setSubject(LocalizeMessage.getLocalizedMessage("[Whoopy 개발자센터] 정산정보 승인대기 안내입니다."));
					mail.setRefDataId(mbrNo);
					mail.setToAddr(mbr_sess.getEmailAddr());
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
			}
			jsonObject.put("result", "SUCCESS");
			
			out = res.getWriter();
			out.write(jsonObject.toString());
		} catch(Exception e) {
			throw new ServiceException("<MypageAction ajaxMypageCalculateInfoInsert>", e);
		} finally {
		
			if(out != null) {
				out.close();
			}
		}
	}
	
	/**
	 * transfer membership guide view
	 * @return
	 */
	public String mypageMemberTransGuideView() throws Exception {
		Session mbr_sess = (Session) SessionUtil.getMemberSession(this.req);
		// LNB 회원 전환 유효성
		this.isTransMenuValid = this.myPageService.isPersonalMember(mbr_sess.getMbrNo());
		
		this.profileInfo = this.myPageService.getMemberProfile(mbr_sess.getMbrNo());
		
		// 전환 신청중 (신경 결과 화면)
		if(profileInfo.getDevMbrStatCd().equals(Constants.MEM_DEV_STATUS_TURN_MOTION)) {
			HashMap map = new HashMap();
			map.put("mbrNo", mbr_sess.getMbrNo());
			map.put("prcStatCd", Constants.MEM_DEV_STATUS_TURN_MOTION);
			this.transferInfo = this.myPageService.getLastTransHistStat(map);
			
			return "APPLY";
		}
		return SUCCESS;
	}
	
	/**
	 * transfer membership company info input view
	 * @return
	 */
	public String mypageMemberTransCompView() throws Exception {
		Session mbr_sess = (Session) SessionUtil.getMemberSession(this.req);
		// LNB 회원 전환 유효성
		this.isTransMenuValid = this.myPageService.isPersonalMember(mbr_sess.getMbrNo());
		return SUCCESS;
	}
	
	/**
	 * transfer membership info view
	 * @return
	 */
	public String mypageMemberTransInfoView() throws Exception {
		Session session = (Session) SessionUtil.getMemberSession(this.req);
		Properties prop = (Properties) this.req.getSession().getServletContext().getAttribute("CONF");
		String result = "INFO";
		// LNB 회원 전환 유효성
		if(transferInfo != null){
			this.isTransMenuValid = this.myPageService.isPersonalMember(session.getMbrNo());
			profileInfo = myPageService.getMemberProfile(session.getMbrNo());
			result = "INFO";
		}else{
			result = "MAIN";
			this.redirectUrl = prop.getProperty("omp.common.url-prefix.http.dev") + this.req.getContextPath(); 
		}
		return result;
	}
	
	/**
	 * 회원전환 신청 - 무/유
	 * transfer membership info input
	 * @return
	 * @throws Exception
	 */
	public String mypageMemberTransInfoInput() throws Exception {
		Session mbr_sess = (Session) SessionUtil.getMemberSession(this.req);
		// LNB 회원 전환 유효성
		this.isTransMenuValid = this.myPageService.isPersonalMember(mbr_sess.getMbrNo());
		Properties prop = (Properties) this.req.getSession().getServletContext().getAttribute("CONF");
		String mbrNo = mbr_sess.getMbrNo();
		this.transferInfo.setMbrNo(mbrNo);
		this.transferInfo.setPrcStatCd(Constants.MEM_DEV_STATUS_TURN_MOTION);
		
		this.profileInfo = this.myPageService.insertTransferInfo(this.req, this.transferInfo, prop);
		return SUCCESS;
	}
	
	public String mypageMemberTransFinsh()throws Exception{
		return SUCCESS;
	}
	
	/**
	 * Mypage Member Withdraw Page
	 * @return
	 * @throws Exception
	 */
	public String mypageMemberWithdraw() throws Exception {
		
		Session session = (Session)SessionUtil.getMemberSession(this.req);
		
		// LNB 회원 전환 유효성
		this.isTransMenuValid = this.myPageService.isPersonalMember(session.getMbrNo());
		
		if(profileInfo == null){
			profileInfo = new Member();
		}
		
		profileInfo.setMbrId(session.getMbrId());
		
		return SUCCESS;
	}
	
	/**
	 * Ajax Withdraw Excute
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void ajaxWithdrawExcute() throws Exception {
		
		Session session = (Session)SessionUtil.getMemberSession(this.req);
		
		// LNB 회원 전환 유효성
		this.isTransMenuValid = this.myPageService.isPersonalMember(session.getMbrNo());
		
		this.res.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = null;
		
		String result = "";
		
		try {
			HashMap map = new HashMap();
			map.put("mbrNo", session.getMbrNo());
			map.put("usrPw", CipherAES.encryption(this.usrPw));
			
			result = this.myPageService.certificationPassword(map);
			if("SUCCESS".equals(result)){
				//등록 상품 조회
				int contentCnt = contentManagementService.getCountSaleContentByMbrNo(session.getMbrNo());
				//Pw Success
				result = "100";
				//Not 
				if(contentCnt > 0){
					result = "200";
				}
			}else{
				result = "300";
			}
			JSONObject jsonObject = new JSONObject();

			jsonObject.put("result", result);
			out = this.res.getWriter();
			System.out.println(jsonObject.toString());
			out.write(jsonObject.toString());
		} catch(Exception e) {
			throw new ServiceException("<MypageAction ajaxWithdrawExcute>", e);
		}finally{
			if(out != null){
				out.close();
			}
		}
	}
	
	/**
	 * Mypage Member Withdraw Reason Register
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String mypageWithdrawReason() throws Exception {
		Session session = (Session)SessionUtil.getMemberSession(this.req);
		Properties prop = (Properties) this.req.getSession().getServletContext().getAttribute("CONF");
		
		String result = "";
		
		if(this.usrPw != null && !this.usrPw.equals("")){
			HashMap map = new HashMap();
			map.put("mbrNo", session.getMbrNo());
			map.put("usrPw", CipherAES.encryption(this.usrPw));
			
			result = this.myPageService.certificationPassword(map);
			
			if("SUCCESS".equals(result)){
				// LNB 회원 전환 유효성
				this.isTransMenuValid = this.myPageService.isPersonalMember(session.getMbrNo());
				
				try {
					this.profileInfo =  this.myPageService.getMemberProfile(session.getMbrNo());
					
					result = "success";
				} catch(Exception e) {
					throw new ServiceException("<MypageAction mypageWithdrawReason>", e);
				}
			}else{
				result = "fail";
				this.redirectUrl = prop.getProperty("omp.common.url-prefix.http.dev") + this.req.getContextPath(); 
			}
		}else{
			result = "fail";
			this.redirectUrl = prop.getProperty("omp.common.url-prefix.http.dev") + this.req.getContextPath(); 
		}
		
		return result;
	}
	
	/**
	 * Mypage Member Withdraw Excute
	 * @return
	 * @throws Exception
	 */
	public String mypageWithdrawExcute() throws Exception{
		if(withdrawInfo == null){
			withdrawInfo = new MemberWithdrawInfo();
		}

		SessionUtil.removeMemberSession(this.req);
		
		this.myPageService.mypageWithdrawExcute(this.req, withdrawInfo);
		
		return SUCCESS;
	}

	/**
	 * @return the usrPw
	 */
	public String getUsrPw() {
		return usrPw;
	}

	/**
	 * @param usrPw the usrPw to set
	 */
	public void setUsrPw(String usrPw) {
		this.usrPw = usrPw;
	}

	/**
	 * @return the profileInfo
	 */
	public Member getProfileInfo() {
		return profileInfo;
	}

	/**
	 * @param profileInfo the profileInfo to set
	 */
	public void setProfileInfo(Member profileInfo) {
		this.profileInfo = profileInfo;
	}

	/**
	 * @return the forwardAction
	 */
	public String getForwardAction() {
		return forwardAction;
	}

	/**
	 * @param forwardAction the forwardAction to set
	 */
	public void setForwardAction(String forwardAction) {
		this.forwardAction = forwardAction;
	}

	/**
	 * @return the calculateInfo
	 */
	public MemberTransHist getCalculateInfo() {
		return calculateInfo;
	}

	/**
	 * @param calculateInfo the calculateInfo to set
	 */
	public void setCalculateInfo(MemberTransHist calculateInfo) {
		this.calculateInfo = calculateInfo;
	}
	
	public MemberWithdrawInfo getWithdrawInfo() {
		return withdrawInfo;
	}

	public void setWithdrawInfo(MemberWithdrawInfo withdrawInfo) {
		this.withdrawInfo = withdrawInfo;
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
	 * @return the transferInfo
	 */
	public MemberTransHist getTransferInfo() {
		return transferInfo;
	}

	/**
	 * @param transferInfo the transferInfo to set
	 */
	public void setTransferInfo(MemberTransHist transferInfo) {
		this.transferInfo = transferInfo;
	}

	/**
	 * @return the isTransMenuValid
	 */
	public String getIsTransMenuValid() {
		return isTransMenuValid;
	}

	/**
	 * @param isTransMenuValid the isTransMenuValid to set
	 */
	public void setIsTransMenuValid(String isTransMenuValid) {
		this.isTransMenuValid = isTransMenuValid;
	}

	/**
	 * @return the bank
	 */
	public Bank getBank() {
		return bank;
	}

	/**
	 * @param bank the bank to set
	 */
	public void setBank(Bank bank) {
		this.bank = bank;
	}

	/**
	 * @return the purchase
	 */
	public Purchase getPurchase() {
		return purchase;
	}

	/**
	 * @param purchase the purchase to set
	 */
	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}


	/**
	 * @return the isProfile
	 */
	public String getIsProfile() {
		return isProfile;
	}


	/**
	 * @param isProfile the isProfile to set
	 */
	public void setIsProfile(String isProfile) {
		this.isProfile = isProfile;
	}

	/**
	 * @return the redirectUrl
	 */
	public String getRedirectUrl() {
		return redirectUrl;
	}

	/**
	 * @param redirectUrl the redirectUrl to set
	 */
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	
}
