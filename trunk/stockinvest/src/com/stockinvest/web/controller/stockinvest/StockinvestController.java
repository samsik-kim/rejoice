package com.stockinvest.web.controller.stockinvest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stockinvest.service.stockinvest.StockinvestService;

@Controller
public class StockinvestController {

	
	@Autowired
	StockinvestService service;
	
	@RequestMapping("/stockinvest/main.do")
	public String membermain(){
		return "stockinvest/index";
	}
}
