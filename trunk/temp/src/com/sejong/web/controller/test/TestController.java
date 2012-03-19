package com.sejong.web.controller.test;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sejong.service.custom.TestService;

@Controller
public class TestController {
	
	/** Provision service */
	@Autowired
	private TestService service;
	
	
	@RequestMapping("/test/test.do")
	public String test(){
		return "sejong/custom/index";
	}
	
	@RequestMapping("/test/selectEmp.do")
	public ModelAndView selectEmp(HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("idp/result");
		
		mav.addObject("cnt", service.selectEmp(""));
		
		return mav;
	}
}
