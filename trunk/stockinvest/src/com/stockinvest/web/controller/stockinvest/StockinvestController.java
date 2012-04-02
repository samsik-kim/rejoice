package com.stockinvest.web.controller.stockinvest;

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
public class StockinvestController {

	
	@Autowired
	StockinvestService service;

	@RequestMapping("/stockinvest/main.do")
	public String main(){
		return "stockinvest/main";
	}	
	
	@RequestMapping("/stockinvest/adminDetail.do")
	public ModelAndView adminDetailForm(HttpServletRequest request, @ModelAttribute("setMemberInfo") MemberInfo info)throws Exception{
		ModelAndView mav = new ModelAndView("admin/view");
		info = service.selectAdminInfo();
		mav.addObject("info", info);
		return mav;
	}
	
}
