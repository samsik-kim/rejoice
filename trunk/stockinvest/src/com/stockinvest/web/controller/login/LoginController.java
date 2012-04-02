package com.stockinvest.web.controller.login;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tframe.common.util.StringUtils;
import tframe.web.page.PageInfo;
import tframe.web.session.SessionHandler;

import com.stockinvest.common.interceptor.info.SessionInfo;
import com.stockinvest.data.stockinvest.info.MemberInfo;
import com.stockinvest.service.stockinvest.StockinvestService;

@Controller
public class LoginController {

	
	@Autowired
	StockinvestService service;
	
	@RequestMapping("/loginForm.do")
	public String main(){
		return "stockinvest/login";
	}

	@RequestMapping("/loginCheck.do")
	public ModelAndView logIn(HttpServletRequest request)throws Exception{
		String admin_id = StringUtils.nvlStr(request.getParameter("admin_id"));
		String passWd = StringUtils.nvlStr(request.getParameter("password"));
		
		MemberInfo adminInfo = service.selectAdminInfo();
		String returnValue = "FAIL";

		if ( admin_id.equals(adminInfo.getAdminId()) && passWd.equals(adminInfo.getPassWd()) ) {
			returnValue = "SUCCESS";
			
			SessionInfo ss = new SessionInfo();
			ss.setMemId(adminInfo.getAdminId());
			SessionHandler<SessionInfo> sh = new SessionHandler<SessionInfo>();
			sh.setLoginInfo(request, ss); //로그인정보를 SessionHandler에 담음
		}
		
		ModelAndView mav = new ModelAndView("jsonView");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", returnValue);
		mav.addObject("jsonObject", jsonObject);
		return mav;
	}
	
	@RequestMapping("/logOut.do")
	public ModelAndView logOut(HttpServletRequest request)throws Exception{
		SessionHandler.getInstance().invalidateSession(request);
		ModelAndView mav = new ModelAndView("redirect:/loginForm.do");
		return mav;
	}
}
