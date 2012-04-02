package com.stockinvest.web.controller.stockinvest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tframe.web.page.PageInfo;
import tframe.common.util.StringUtils;

import com.stockinvest.data.stockinvest.info.MemberInfo;
import com.stockinvest.service.stockinvest.StockinvestService;

@Controller
public class StockinvestController {

	
	@Autowired
	StockinvestService service;
	
	@RequestMapping("/stockinvest/main.do")
	public String membermain(){
		return "stockinvest/login";
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
