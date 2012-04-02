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
	
	@RequestMapping("/stockinvest/loginForm.do")
	public String main(){
		return "stockinvest/login";
	}

	@RequestMapping("/stockinvest/loginCheck.do")
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
		}
		
		ModelAndView mav = new ModelAndView("jsonView");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", returnValue);
		mav.addObject("jsonObject", jsonObject);
		return mav;
	}
	
	@RequestMapping("/stockinvest/logOut.do")
	public ModelAndView logOut(HttpServletRequest request)throws Exception{
		ModelAndView mav = new ModelAndView("redirect:/stockinvest/index.jsp");
		return mav;
	}
	
	
	
	@RequestMapping("/stockinvest/testDBConnect.do")
	public ModelAndView testDB(HttpServletRequest request, @ModelAttribute MemberInfo info)throws Exception{
		ModelAndView mav = new ModelAndView();
		int currentPage = Integer.parseInt(StringUtils.nvlStr(request.getParameter("currentPage"), "1"));
		int pageUnit = 10; // 페이지를 보여줄 갯수
		int pageSize = 20; // 한페이지에 보여줄 게시물수
		PageInfo pageInfo = new PageInfo(request, currentPage, pageUnit, pageSize);
//		pageInfo = service.selectMemberList(pageInfo, info);
//		mav.addObject("pageInfo", pageInfo);
		int adminCount = service.selectEmp();
		mav.addObject("info", info);
		mav.setViewName("stockinvest/list");
		return mav;		
	}
	
	@RequestMapping("/stockinvest/adminDetail.do")
	public ModelAndView codeDetailForm(HttpServletRequest request, @ModelAttribute("setMemberInfo") MemberInfo info)throws Exception{
		ModelAndView mav = new ModelAndView("admin/view");
		info.setCurrentPage(StringUtils.nvlStr(request.getParameter("currentPage"), "1"));
		if(info.getSeqNo() != null){
			info = service.selectAdminInfo();
		}
		mav.addObject("info", info);
		return mav;
	}
	


	
}
