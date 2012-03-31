package com.stockinvest.web.controller.stockinvest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tframe.common.util.StringUtils;
import tframe.web.page.PageInfo;

import com.stockinvest.data.stockinvest.info.CodeInfo;
import com.stockinvest.service.stockinvest.CodeService;

/**
 * 종목코드 관리
 * @author kakashi
 *
 */
@Controller
public class CodeController {

	@Autowired
	CodeService service;
	
	@RequestMapping("/stockinvest/main.do")
	public String membermain(){
		return "stockinvest/index";
	}
	
	@RequestMapping("/stockinvest/codeList.do")
	public ModelAndView testDB(HttpServletRequest request, @ModelAttribute CodeInfo info)throws Exception{
		ModelAndView mav = new ModelAndView();
		int currentPage = Integer.parseInt(StringUtils.nvlStr(request.getParameter("currentPage"), "1"));
		int pageUnit = 10; // 페이지를 보여줄 갯수
		int pageSize = 20; // 한페이지에 보여줄 게시물수
		PageInfo pageInfo = new PageInfo(request, currentPage, pageUnit, pageSize);
		pageInfo = service.selectCodeList(pageInfo, info);
		mav.addObject("pageInfo", pageInfo);
		
		mav.addObject("info", info);
		mav.setViewName("code_cate/list");
		return mav;
	}	
}
