package com.seojeong.web.controller.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tframe.common.util.StringUtils;
import tframe.web.page.PageInfo;

import com.seojeong.data.member.info.MemberInfo;
import com.seojeong.service.member.MemberService;

@Controller
public class MemberController {

	
	@Autowired
	MemberService service;
	
	@RequestMapping("/member/main.do")
	public String membermain(){
		return "member/main";
	}
	
	/**
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/member/list.do")
	public ModelAndView memberList(HttpServletRequest request, @ModelAttribute MemberInfo info)throws Exception{
		ModelAndView mav = new ModelAndView();
		int currentPage = Integer.parseInt(StringUtils.nvlStr(request.getParameter("currentPage"), "1"));
		int pageUnit = 10; // 페이지를 보여줄 갯수
		int pageSize = 20; // 한페이지에 보여줄 게시물수
		PageInfo pageInfo = new PageInfo(request, currentPage, pageUnit, pageSize);
		pageInfo = service.selectMemberList(pageInfo, info);
		mav.addObject("pageInfo", pageInfo);
		
		mav.addObject("info", info);
		mav.setViewName("member/list");
		return mav;
	}
	
	/**
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/member/insert.do")
	public ModelAndView memberInsert(HttpServletRequest request, @ModelAttribute("setMemberInfo") MemberInfo info)throws Exception{
		ModelAndView mav = new ModelAndView("member/insert");
		//service.insertMember(info);
		
		return mav;
	}
	
	
	/**
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/member/update.do")
	public ModelAndView memberUpdate(HttpServletRequest request, @ModelAttribute("setMemberInfo") MemberInfo info)throws Exception{
		ModelAndView mav = new ModelAndView("member/update");
		//service.updateMember(info);
		return mav;
	}
}
