package com.seojeong.web.controller.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.seojeong.service.member.MemberService;

@Controller
public class MemberController {

	
	@Autowired
	MemberService service;
	
	@RequestMapping("/member/main.do")
	public String membermain(){
		return "member/index";
	}
	
	@RequestMapping("/member/list.do")
	public ModelAndView memberList(HttpServletRequest request)throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.addObject("cnt", service.selectEmp());
		mav.setViewName("member/memberList");
		return mav;
	}
}
