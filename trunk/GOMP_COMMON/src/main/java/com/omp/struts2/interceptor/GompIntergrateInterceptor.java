package com.omp.struts2.interceptor;


import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.StrutsStatics;

import pat.neocore.util.MiscEncoder;

import com.omp.commons.action.BaseAction;
import com.omp.commons.exception.InvalidInputException;
import com.omp.commons.exception.NoticeException;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.text.LocalizeMessage;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.ThreadSession;
import com.omp.commons.util.config.ConfigProperties;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * 스터럿츠 공통 인터셉터
 * 메세지/익셉션/로그 관련 공통 처리 부분을 구현하고 있습니다.
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class GompIntergrateInterceptor
	extends AbstractInterceptor {
	
	public class GompRequestStatus
		implements Serializable {
		
		private boolean isAjaxCall;

		public boolean isAjaxCall() {
			return isAjaxCall;
		}

		public void setAjaxCall(boolean isAjaxCall) {
			this.isAjaxCall = isAjaxCall;
		}
	}
	
	/**
	 * 요청 인덱스
	 */
	private static 	long				reqidx	= 0;
	/**
	 * 요청 주소와 로그의 매핑
	 */
	private static	Map<String, String>	reqOnmMap	= new HashMap<String, String>();
	static {
		reqOnmMap.put("/devpoc/main/main.omp", "omp.onm.dev.main");
		reqOnmMap.put("/devpoc/login/login.omp", "omp.onm.dev.developer");
		reqOnmMap.put("/devpoc/cert/q.omp", "omp.onm.dev.developer");
		reqOnmMap.put("/devpoc/content/contentDetailInfoView.omp", "omp.onm.dev.developer");
		reqOnmMap.put("/devpoc/content/contentDetailInfoView.omp", "omp.onm.dev.payment");
		reqOnmMap.put("/devpoc/content/ajaxVerifyReq.omp", "omp.onm.dev.application");
		reqOnmMap.put("/adminpoc/purchasecancel/purchasecancel.omp", "omp.onm.dev.purchasecancel");
		reqOnmMap.put("/adminpoc/phonemapping/downloadPhoneRemappingAck.omp", "omp.onm.admin.devicemappingack");
	}
	
	/**
	 * 메세지 익셉션일때 처리할 리절트
	 */
	private String	messageResult;

	/**
	 * 서비스 실패 일때 처리 할 리절트
	 */
	private String	serviceFaultResult;
	
	/**
	 * 요청 아이치 전치사
	 */
	private String	reqIdPrefix;
	
	

	
	public GompIntergrateInterceptor() {
		ConfigProperties	conf;
		
		conf	= new ConfigProperties();
		this.reqIdPrefix	= conf.getString("omp.common.id.unique") + "_" + Long.toHexString(System.currentTimeMillis());
	}
	
	public String getMessageResult() {
		return messageResult;
	}

	public void setMessageResult(String messageResult) {
		this.messageResult = messageResult;
	}
	
	public String getServiceFaultResult() {
		return serviceFaultResult;
	}

	public void setServiceFaultResult(String serviceFaultResult) {
		this.serviceFaultResult = serviceFaultResult;
	}

	@Override
	public String intercept(ActionInvocation invocation)
		throws Exception {
		GLogger				logger;
		Object				action;
		ActionProxy			actionProxy;
		String[]			methodName;
		String				rv;
		ActionContext		actx;
		HttpServletRequest	req;
		HttpServletResponse	res;
		ValueStack			vs;
		GompRequestStatus	grstat;
		long				startTm;
		ThreadSession		tses;
		long				rcnt;
		String				reqId;
		String				omcRcode;
		long				workingTime;
		String				onmLogId;
		Log					onmLogger;
		
		startTm		= System.currentTimeMillis();
		action		= invocation.getAction();
		actionProxy	= invocation.getProxy();
		actx		= invocation.getInvocationContext();
		req			= (HttpServletRequest)actx.get(StrutsStatics.HTTP_REQUEST);
		res			= (HttpServletResponse)actx.get(StrutsStatics.HTTP_RESPONSE);
		methodName	= new String[] {actionProxy.getMethod()};
		grstat		= new GompRequestStatus();
		grstat.setAjaxCall(methodName[0].indexOf("ajax") == 0);
		vs			= invocation.getStack();
		logger		= action instanceof BaseAction ? ((BaseAction)action).getLogger() : new GLogger(action.getClass());
		rv			= null;
		tses		= ThreadSession.getSession();
		synchronized (GompIntergrateInterceptor.class) {
			rcnt	= ++reqidx; 
		}
		reqId		= this.reqIdPrefix + "_" + Long.toHexString(rcnt);
		onmLogId	= reqOnmMap.get(req.getRequestURI());
		if (onmLogId != null) {
			onmLogger	= LogFactory.getLog(onmLogId);
		} else {
			onmLogger	= null;
		}
		try {
			tses.setActionStep("PrepareInvoked");
			if (logger.isInfoEnabled()) {
				String			reqUrl;
				String			reqHeaderDump;
				String			reqParamDump;
				StringBuffer	sb;
				String			qs;
				Enumeration<?>	en;
				boolean			flag;
				
				sb	= new StringBuffer();
				sb.append(req.getRequestURL());
				qs	= req.getQueryString();
				if (qs != null) {
					sb.append("?").append(qs);
				}
				reqUrl	= sb.toString();
				sb.setLength(0);
				en		= req.getHeaderNames();
				flag	= false;
				while (en.hasMoreElements()) {
					String	hnm;
					
					hnm	= (String)en.nextElement();
					if (flag) {
						sb.append("\n");
					} else {
						flag	= true;
					}
					sb.append(hnm).append(" : ").append(req.getHeader(hnm));
				}
				reqHeaderDump	= sb.toString();
				sb.setLength(0);
				en		= req.getParameterNames();
				flag	= false;
				while (en.hasMoreElements()) {
					String		pnm;
					String[]	pvs;
					
					if (flag) {
						sb.append("\n");
					} else {
						flag	= true;
					}
					pnm	= (String)en.nextElement();
					sb.append(pnm).append(" : ");
					pvs	= req.getParameterValues(pnm);
					for (int i=0; i<pvs.length; i++) {
						if (i > 0) {
							sb.append(", ");
						}
						sb.append("\"").append(MiscEncoder.encodeEscapeString(pvs[i])).append("\"");
					}
				}
				reqParamDump	= sb.toString();
				logger.info("{0} requested.\n"
					+ "Request Id : {1}\n"
					+ "[Request Header] -------------------\n"
					+ "{2}\n"
					+ "[Request Parameter] -------------------\n"
					+ "{3}", new Object[] {reqUrl, reqId, reqHeaderDump, reqParamDump});
			}
			
			if (logger.isDebugEnabled()) {
				logger.debug("START Method {0}()", methodName);
			}
			tses.setActionStep("Start");
			rv			= invocation.invoke();
			tses.setActionStep("Done");
			omcRcode	= "00";
		} catch (NoticeException e) {
			rv	= this.messageResult;
			vs.push(grstat);
			vs.push(e.getLocalizeMessage());
			omcRcode	= "00";
		} catch (InvalidInputException e) {
			rv	= this.messageResult;
			vs.push(grstat);
			vs.push(new LocalizeMessage("입력된 값에 오류가 있습니다.\n{0}", new Object[] {e.getLocalizeMessage()}));
			omcRcode	= "00";
		} catch (ServiceException e) {
			logger.error("Service Exception throwed", e);
			rv	= this.serviceFaultResult;
			vs.push(grstat);
			vs.push(e.getLocalizeMessage());
			omcRcode	= "99";
			res.setStatus(500);
		} catch (Throwable t) {
			Throwable	cause;
			boolean		prfail;

			if (t.getClass().getName().indexOf("ClientAbortException") != -1) {
				// 사용자 전송 취소는 무시
				return rv;
			}
			cause	= t;
			prfail	= false;
			do {
				if (cause instanceof JspException) {
					prfail	= true;
					break;
				}
			} while ((cause = cause.getCause()) != null); 
			vs.push(grstat);
			if (prfail) {
				logger.error("Result page rendering fail", t);
				rv	= this.serviceFaultResult;
				vs.push(new LocalizeMessage("수행은 완료되었으나 결과 페이지 구성을 실패 하였습니다."));
			} else {
				logger.error("Uncached Exception throwed", t);
				rv	= this.serviceFaultResult;
				vs.push(new LocalizeMessage("처리되지 않은 오류가 발생 하였습니다."));
			}
			omcRcode	= "99";
			res.setStatus(500);
		} finally {
			workingTime	= System.currentTimeMillis() - startTm;
			if (logger.isInfoEnabled()) {
				logger.info("PROCESSED Method {0}() - work time {1} msecs.", new Object[] {methodName[0], workingTime});
			}
			res.setHeader("Cache-control", "no-cache, no-store");
			res.setHeader("pragma", "no-cache");
			res.setHeader("Expires", "-1");
		}
		if (onmLogger != null) {
			StringBuffer	sb;
			String			step;
			String			serviceStep;
			String			pkey;
			
			serviceStep	= tses.getServiceStep();  
			step		= serviceStep == null ? tses.getActionStep() : tses.getActionStep() + "/" + tses.getServiceStep();
			pkey		= StringUtils.defaultString(tses.getPrimaryKey(), "");
			sb			= new StringBuffer();
			sb.append("^").append(workingTime).append("^").append(omcRcode).append("^").append(req.getRemoteAddr())
				.append("^").append(req.getRequestURL()).append("^").append(reqId).append("^").append(step)
				.append("^").append(pkey);
			
			onmLogger.info(sb.toString());
		}
		
		return rv;
	}
	
}