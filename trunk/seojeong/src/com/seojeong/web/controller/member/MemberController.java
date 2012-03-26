package com.seojeong.web.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tframe.common.util.StringUtils;
import tframe.web.page.PageInfo;

import com.seojeong.data.member.info.MemberInfo;
import com.seojeong.service.member.MemberService;

/**
 * @author Administrator
 *
 */
@Controller
public class MemberController {

	
	@Autowired
	MemberService service;
	
	@RequestMapping("/member/main.do")
	public String membermain(){
		return "member/main";
	}
	
	@RequestMapping("/member/insertForm.do")
	public String insertForm(){
		return "member/insert";
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
	 * @param info
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/member/insert.do")
	public ModelAndView memberInsert(HttpServletRequest request, @ModelAttribute("setMemberInfo") MemberInfo info)throws Exception{
		ModelAndView mav = new ModelAndView("redirect:/member/list.do");
		service.insertMember(info);
		return mav;
	}

	/**
	 * @param request
	 * @param info
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/member/updateForm.do")
	public ModelAndView memberUpdateForm(HttpServletRequest request, @ModelAttribute("setMemberInfo") MemberInfo info)throws Exception{
		ModelAndView mav = new ModelAndView("member/update");
		info.setCurrentPage(StringUtils.nvlStr(request.getParameter("currentPage"), "1"));
		if(info.getMdn() != null || info.getSeq() != null){
			info = service.selectMemberInfo(info);
		}
		mav.addObject("info", info);
		return mav;
	}

	/**
	 * @param request
	 * @param info
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/member/update.do")
	public ModelAndView memberUpdate(HttpServletRequest request, @ModelAttribute("setMemberInfo") MemberInfo info)throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		JSONObject jsonObject = new JSONObject();
		int result = service.updateMember(info);
		info.setResultCode(result > 0 ? "SUCCESS" : "FAIL");
		jsonObject.put("result", info.getResultCode());
		mav.addObject("jsonObject", jsonObject);
		return mav;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/member/mdnCheck.do")
	public ModelAndView findBestProdJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String mdn = StringUtils.nvlStr(request.getParameter("mdn"));
		ModelAndView mav = new ModelAndView("jsonView");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", service.selectMdnCheck(mdn));
		mav.addObject("jsonObject", jsonObject);
		return mav;
	}
}
