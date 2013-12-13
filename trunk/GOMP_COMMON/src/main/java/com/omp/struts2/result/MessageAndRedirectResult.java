package com.omp.struts2.result;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.util.TextParseUtil;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * 간단한 메세지 출력후 이동하는 result type
 * 메세지 출력후 이동 처리는 지정된 page에서 전달되는 두개의 값 ${to} 와 ${message} 를 활용하여  수행 해야 한다. 
 * @author pat
 */
@SuppressWarnings("serial")
public class MessageAndRedirectResult implements Result {

	private String	to;
	private String	message;
	private String	page;
	
	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	@Override
	public void execute(ActionInvocation invocation) throws Exception {
		ActionContext		actx;
		HttpServletRequest	req;
		HttpServletResponse	res;
		RequestDispatcher	rd;
		ValueStack			vs;
		
		vs	= invocation.getStack();
		if (StringUtils.isEmpty(this.to)) {
			throw new IllegalAccessException("property 'to' required.");
		}
		this.to			= TextParseUtil.translateVariables(this.to, vs);
		if (StringUtils.isEmpty(this.message)) {
			throw new IllegalAccessException("property 'message' required.");
		}
		this.message	= TextParseUtil.translateVariables(this.message, vs);
		if (StringUtils.isEmpty(this.page)) {
			throw new IllegalAccessException("property 'page' required.");
		}
		this.page		= TextParseUtil.translateVariables(this.page, vs);
		actx	= invocation.getInvocationContext();
		req		= (HttpServletRequest)actx.get(ServletActionContext.HTTP_REQUEST);
		res		= (HttpServletResponse)actx.get(ServletActionContext.HTTP_RESPONSE);
		if (this.to.indexOf("http://") != 0 && this.to.indexOf("https://") != 0) {
			this.to	= req.getContextPath() + this.to;
		}
		req.setAttribute("message", this.message);
		req.setAttribute("to", this.to);
		rd		= req.getRequestDispatcher(this.page);
		rd.forward(req, res);
	}
}
