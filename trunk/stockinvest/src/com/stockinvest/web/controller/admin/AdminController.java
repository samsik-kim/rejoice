package com.stockinvest.web.controller.admin;

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
import com.stockinvest.data.code.info.CodeInfo;
import com.stockinvest.data.stockinvest.info.MemberInfo;
import com.stockinvest.service.admin.AdminService;

@Controller
public class AdminController {

	@Autowired
	AdminService service;
	
	@RequestMapping("/admin/adminDetail.do")
	public ModelAndView adminDetailForm(HttpServletRequest request, @ModelAttribute("setMemberInfo") MemberInfo info)throws Exception{
		ModelAndView mav = new ModelAndView("admin/view");
		info = service.selectAdminInfo();
		mav.addObject("info", info);
		return mav;
	}
	
	@RequestMapping("/admin/adminUpdate.do")
	public ModelAndView updateCode(HttpServletRequest request, @ModelAttribute("setMemberInfo") MemberInfo info)throws Exception{
		ModelAndView mav = new ModelAndView("redirect:/admin/adminDetail.do");
		service.updatePassword(info);
		return mav;
	}		
}
