package com.stockinvest.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

	@RequestMapping("/test/test.do")
	public String testForm(){
		return "board/test";
	}
	
	@RequestMapping("/test/edit.do")
	public ModelAndView testEdit(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		
		String content = request.getParameter("CONTENT");
		System.out.println("#################");
		System.out.println(content);
		System.out.println("#################");
		return mav;
	}
}
