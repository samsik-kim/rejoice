package com.omp.dev.common.util;

import javax.servlet.http.HttpServletRequest;

import com.omp.commons.util.CipherAES;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.config.ConfigProperties;
import com.omp.dev.common.Constants;

public class ReturnUrlGenerateUtil {
	
	private static final GLogger logger = new GLogger(ReturnUrlGenerateUtil.class);
	private static ConfigProperties conf = new ConfigProperties();
	
	/**
	 * Default Return Url Main 
	 * @param req
	 * @return
	 */
	public static String rtnUrl(HttpServletRequest req){
		return conf.getString("omp.common.url-prefix.http.dev") + req.getContextPath() + Constants.DEV_MAIN_URL;
	}

	
	/**
	 * Return Url Generate
	 * @param req
	 * @param type
	 * @param param
	 * @return
	 */
	public static String rtnUrl(HttpServletRequest req, String type, String param){
		String rtnUrl = conf.getString("omp.common.url-prefix.https.dev");
		String encodeStr = "";
		if(type.equals(Constants.AUTH_MEM_CHANGE)){
			encodeStr = "cmd=" + type + "&param=" + param;
			
			try {
				encodeStr = CipherAES.encryption(encodeStr);
			} catch (Exception e) {
				logger.error(">>>>> Parameter Encrypt Error <<<<<", e);
			}
			
			rtnUrl += req.getContextPath() + Constants.RETURN_AUTH_MYPAGE + encodeStr;
		}else if(type.equals(Constants.AUTH_EMAIL_JOIN)){
			encodeStr = "cmd=" + type + "&param=" + param;
			
			try {
				encodeStr = CipherAES.encryption(encodeStr);
			} catch (Exception e) {
				logger.error(">>>>> Parameter Encrypt Error <<<<<", e);
			}
			
			rtnUrl += req.getContextPath() + Constants.RETURN_AUTH_MEMBER + encodeStr;
		}
		
		return rtnUrl;
	}
	
	/**
	 * @param req
	 * @param type
	 * @param email
	 * @param mbrNo
	 * @return
	 */
	public static String rtnUrl(HttpServletRequest req, String type, String email, String mbrNo){
		String rtnUrl = conf.getString("omp.common.url-prefix.https.dev");
		String encodeStr = "";
		
		encodeStr = "cmd=" + type + "&param=" + email + "&mbrNo=" + mbrNo;
		
		try {
			encodeStr = CipherAES.encryption(encodeStr);
		} catch (Exception e) {
			logger.error(">>>>> Parameter Encrypt Error <<<<<", e);
		}
		
		rtnUrl += req.getContextPath() + Constants.RETURN_AUTH_MYPAGE + encodeStr;
		
		return rtnUrl;
	}
}
