package com.stockinvest.web.controller.stockinvest;

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
}
