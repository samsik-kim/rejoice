package com.omp.dev.certification.action;

import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.omp.commons.action.BaseAction;
import com.omp.commons.util.CipherAES;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.SessionUtil;
import com.omp.dev.common.Constants;
import com.omp.dev.member.service.MyPageService;
import com.omp.dev.member.service.MyPageServiceImpl;
import com.omp.dev.user.model.Session;

@SuppressWarnings("serial")
public class CertificationAction extends BaseAction{
	
	
	private String param = null;
	private String mbrNo = null;
	private String redirectUrl = null;
	private MyPageService myPageService = null;
	/**
	 * Logger
	 */
	protected GLogger logger = new GLogger(this.getClass());
	
	public CertificationAction(){
		this.myPageService = new MyPageServiceImpl();
	}
	
	/**
	 * Mypage - Email Update, Member Type Update
	 * @return
	 * @throws Exception
	 */
	public String cf()throws Exception{
		
		String result = "";
		//
		HashMap<String, String> map = getHashMapString();
		Session session = (Session) SessionUtil.getMemberSession(this.req);
		
		//Email Update, Member Type Update
		if(Constants.AUTH_EMAIL_CHANGE.equals(map.get("cmd"))){
			if(map.get("mbrNo").equals(session.getMbrNo())){
				int ck = myPageService.getEmailCheck(map.get("param"));
				if(ck > 0){
					this.redirectUrl = this.conf.getString("omp.common.url-prefix.http.dev") + this.req.getContextPath() + "/main/main.omp";
					result = "duplicate";
				}else{
					result = Constants.AUTH_EMAIL_CHANGE;
					this.mbrNo = map.get("mbrNo");
				}
			}else{
				result = "sessOut";
				this.redirectUrl = this.req.getRequestURL() + "?" + req.getQueryString();
				SessionUtil.removeMemberSession(req);
			}
		}else if(Constants.AUTH_MEM_CHANGE.equals(map.get("cmd"))){
			result = Constants.AUTH_MEM_CHANGE;
		}
		this.param = map.get("param");
		
		return result;
	}
	
	/**
	 * Member Join CertificationAction
	 * @return
	 * @throws Exception
	 */
	public String q()throws Exception{
		HttpServletRequest request = this.req;
		Session session = null;
		if (SessionUtil.existMemberSession(request)) {
			session = (Session) SessionUtil.getMemberSession(request);

			SessionUtil.removeMemberSession(request);
		}
		//DebugLog
		HashMap<String, String> map = getHashMapString();
		
		this.param = CipherAES.encryption(map.get("param"));
		
		return SUCCESS;
	}

	
	/**
	 * @return
	 */
	public HashMap<String, String> getHashMapString(){
		
		String job = StringUtils.defaultString(req.getParameter("j"));
		HashMap<String, String> map = new HashMap<String, String>();
		String[] parameters;
		
		try {
			parameters = CipherAES.decryption(job).split("&");
			
			for (int i = 0; i < parameters.length; i++) {
				logger.info("parameters" + i + " :: " + parameters[i]);
				String [] temp = parameters[i].split("=");
				map.put(temp[0], temp[1]);
			}
	
			Iterator<?> ir = map.keySet().iterator();
			while (ir.hasNext()) {
				String key = (String)ir.next();
				String value = (String)map.get(key);
	        	
				logger.info("Debug - key ::  "+StringUtils.rightPad(key, 40, ' ')+"value ::  "+value);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
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
