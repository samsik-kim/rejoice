package com.stockinvest.web.controller.stockinvest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tframe.common.util.StringUtils;
import tframe.web.page.PageInfo;
import tframe.web.session.SessionHandler;

import com.stockinvest.common.interceptor.info.SessionInfo;
import com.stockinvest.common.util.FileUpload;
import com.stockinvest.data.board.info.BoardInfo;
import com.stockinvest.data.board.info.BoardManageInfo;
import com.stockinvest.data.stockinvest.info.MemberInfo;
import com.stockinvest.service.stockinvest.StockinvestService;

@Controller
public class StockinvestController {

	
	@Autowired
	StockinvestService service;

	@RequestMapping("/stockinvest/main.do")
	public String main(){
		return "stockinvest/main";
	}	

	/**
	 * @param request
	 * @param info
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/stockinvest/ajaxBoardListinner.do")
	public ModelAndView boardManageListinner(HttpServletRequest request, @ModelAttribute BoardManageInfo info)throws Exception{
		ModelAndView mav = new ModelAndView();
		List<BoardManageInfo> list = service.selectBoardManageList();
		mav.addObject("boardManageList", list);
		mav.setViewName("stockinvest/manageListInner");
		return mav;
	}		
	
	@RequestMapping("/board/boardManageList.do")
	public ModelAndView boardList(HttpServletRequest request, @ModelAttribute BoardManageInfo info)throws Exception{
		ModelAndView mav = new ModelAndView();

		List<BoardManageInfo> manageList = service.selectBoardManageList();
		mav.addObject("boardManageList", manageList);
		mav.setViewName("board/manageList");
		return mav;
	}	

	@RequestMapping("/board/boardManageInsert.do")
	public ModelAndView insertManageBoard(HttpServletRequest request, @ModelAttribute("setBoardManageInfo") BoardManageInfo info)throws Exception{
		String redirectUrl = "redirect:/board/boardManageList.do";
		ModelAndView mav = new ModelAndView(redirectUrl);
		service.insertBoardManageInfo(info);
		return mav;
	}
	
	@RequestMapping("/board/boardManageUpdate.do")
	public ModelAndView updateManageBoard(HttpServletRequest request, @ModelAttribute("setBoardManageInfo") BoardManageInfo info)throws Exception{
		ModelAndView mav = new ModelAndView("redirect:/board/boardManageList.do");
		service.updateBoardManageInfo(info);
		return mav;
	}	
	
	@RequestMapping("/board/boardManageDelete.do")
	public ModelAndView deleteCode(HttpServletRequest request, @ModelAttribute("setBoardManageInfo") BoardManageInfo info)throws Exception{
		ModelAndView mav = new ModelAndView("redirect:/board/boardManageList.do");
		service.deleteBoardManageInfo(info);
		return mav;
	}	
	
}
