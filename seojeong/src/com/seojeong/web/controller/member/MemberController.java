package com.seojeong.web.controller.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.seojeong.data.member.info.MemberInfo;
import com.seojeong.service.member.MemberService;

@Controller
public class MemberController {

	
	@Autowired
	MemberService service;
	
	@RequestMapping("/member/main.do")
	public String membermain(){
		return "member/index";
	}
	
	/**
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/member/list.do")
	public ModelAndView memberList(HttpServletRequest request)throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.addObject("cnt", service.selectEmp());
		mav.setViewName("member/memberList");
		return mav;
	}
	
	/**
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/member/insert.do")
	public ModelAndView memberInsert(HttpServletRequest request, @ModelAttribute("setMemberInfo") MemberInfo info)throws Exception{
		service.insertMember(info);
		return null;
	}
	
	
	/**
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/member/update.do")
	public ModelAndView memberUpdate(HttpServletRequest request, @ModelAttribute("setMemberInfo") MemberInfo info)throws Exception{
		service.updateMember(info);
		return null;
	}
}
