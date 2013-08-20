package com.nmimo.web.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfigController {

	
	@RequestMapping(value="/config/preferences.do")
	public ModelAndView preferences(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("config/preferences");
		return modelAndView;
	}
}
