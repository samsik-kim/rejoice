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
	
	/**
	 * @param request
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping("/stockinvest/codeList.do")
	public ModelAndView codeList(HttpServletRequest request, @ModelAttribute CodeInfo info)throws Exception{
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
	
	@RequestMapping("/stockinvest/insertCodeForm.do")
	public String insertForm(){
		return "code_cate/write";
	}
	
	/**
	 * @param request
	 * @param info
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/stockinvest/codeDetail.do")
	public ModelAndView codeDetailForm(HttpServletRequest request, @ModelAttribute("setCodeInfo") CodeInfo info)throws Exception{
		ModelAndView mav = new ModelAndView("code_cate/view");
		info.setCurrentPage(StringUtils.nvlStr(request.getParameter("currentPage"), "1"));
		if(info.getSeqNo() != null){
			info = service.selectCodeInfo(info);
		}
		mav.addObject("info", info);
		return mav;
	}	
	
	@RequestMapping("/stockinvest/codeInsert.do")
	public ModelAndView insertCode(HttpServletRequest request, @ModelAttribute("setCodeInfo") CodeInfo info)throws Exception{
		ModelAndView mav = new ModelAndView("redirect:/stockinvest/codeList.do");
		info = setCode(info);
		service.insertCodeInfo(info);
		return mav;
	}
	
	@RequestMapping("/stockinvest/codeUpdate.do")
	public ModelAndView updateCode(HttpServletRequest request, @ModelAttribute("setCodeInfo") CodeInfo info)throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		JSONObject jsonObject = new JSONObject();
		int result = service.updateCodeInfo(info);
		info.setResultCode(result > 0 ? "SUCCESS" : "FAIL");
		jsonObject.put("result", info.getResultCode());
		mav.addObject("jsonObject", jsonObject);
		return mav;
	}	
	
	@RequestMapping("/stockinvest/codeDelete.do")
	public ModelAndView deleteCode(HttpServletRequest request, @ModelAttribute("setCodeInfo") CodeInfo info)throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		JSONObject jsonObject = new JSONObject();
		int result = service.deleteCodeInfo(info);
		info.setResultCode(result > 0 ? "SUCCESS" : "FAIL");
		jsonObject.put("result", info.getResultCode());
		mav.addObject("jsonObject", jsonObject);
		return mav;
	}
	
	public CodeInfo setCode(CodeInfo info) {
		info.setJuju(StringUtils.nvlStr(info.getJuju(),""));
		info.setTel(StringUtils.nvlStr(info.getTel(),""));
		info.setTel1(StringUtils.nvlStr(info.getTel1(),""));
		info.setTel2(StringUtils.nvlStr(info.getTel2(),""));
		info.setTel3(StringUtils.nvlStr(info.getTel3(),""));
		info.setHoldShare(StringUtils.nvlStr(info.getHoldShare(),""));
		info.setInfoTel(StringUtils.nvlStr(info.getInfoTel(),""));
		info.setInfoTel1(StringUtils.nvlStr(info.getInfoTel1(),""));
		info.setInfoTel2(StringUtils.nvlStr(info.getInfoTel2(),""));
		info.setInfoTel3(StringUtils.nvlStr(info.getInfoTel3(),""));
		return info;
	}
	
}
