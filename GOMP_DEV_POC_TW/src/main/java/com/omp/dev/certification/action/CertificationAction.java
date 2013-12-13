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
import com.omp.dev.user.model.Session;

@SuppressWarnings("serial")
public class CertificationAction extends BaseAction{
	
	
	private String param = null;
	
	/**
	 * Logger
	 */
	protected GLogger logger = new GLogger(this.getClass());
	
	public String cf()throws Exception{
		
		String result = "";
		
		//
		HashMap<String, String> map = getHashMapString();
		Session session = (Session) SessionUtil.getMemberSession(this.req);
		
		//Email Update, Member Type Update
		if(Constants.AUTH_EMAIL_CHANGE.equals(map.get("cmd"))){
			result = Constants.AUTH_EMAIL_CHANGE;
			logger.debug("adasdad" + map.get("param"));
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
				logger.debug("parameters" + i + " :: " + parameters[i]);
				String [] temp = parameters[i].split("=");
				map.put(temp[0], temp[1]);
			}
	
			Iterator<?> ir = map.keySet().iterator();
			while (ir.hasNext()) {
				String key = (String)ir.next();
				String value = (String)map.get(key);
	        	
				logger.debug("Debug - key ::  "+StringUtils.rightPad(key, 40, ' ')+"value ::  "+value);
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
	
}
