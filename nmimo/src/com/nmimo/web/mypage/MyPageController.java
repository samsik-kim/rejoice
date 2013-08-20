package com.nmimo.web.mypage;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nmimo.common.info.PageInfo;
import com.nmimo.common.info.SessionInfo;
import com.nmimo.common.session.SessionHandler;
import com.nmimo.common.util.StringUtils;
import com.nmimo.data.mypage.info.MyPageInfo;
import com.nmimo.data.mypage.info.MyWorkInfo;
import com.nmimo.data.user.info.UserBasInfo;
import com.nmimo.service.mypage.MyPageService;
import com.nmimo.service.user.UserService;


/**
 * <pre>
 * 나의작업
 * </pre>
 * @file MyPageController.java
 * @since 2013. 4. 15.
 * @author Leesh
 */
@Controller
public class MyPageController {

	/** LOG4J */
	Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	MyPageService myPageService;
	
	@Autowired
	UserService userService;
	
	/**
	 * <pre>
	 *  나의작업 >> 나의 보관함 List
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myPage/messageList.do")
	public ModelAndView myPageMessageList(HttpServletRequest request, @ModelAttribute MyPageInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[MyPage] Controller -> myPageMessageList ");
		}

		return new ModelAndView("mypage/myPageMessageList");
	}

	/**
	 * <pre>
	 * 나의작업 >> 나의 보관함 InnerList
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myPage/ajaxMessageListInner.do")
	public ModelAndView myPageMessageListInner(HttpServletRequest request, @ModelAttribute MyPageInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[MyPage] Controller -> myPageMessageListInner ");
		}

		int currentPage = Integer.parseInt(StringUtils.nvlStr(request.getParameter("pageIndex"), "1")); //현재 페이지 위치
		int pageUnit = 10; // 페이지를 보여줄 개수
		int pageSize = 10; // 한 페이지에 보여줄 게시물 수 
		
		PageInfo pageInfo = new PageInfo(request, currentPage, pageUnit, pageSize);
		ModelAndView mav = new ModelAndView();

//		pageInfo = reservationService.findStatsListBySearchCode(dbParams , pageInfo);
		
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("mypage/inc/myPageMessageListInner");
									
		return mav;
	}
	
	/**
	 * <pre>
	 * 나의작업 >> 나의 보관함List 상세보기
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myPage/messageListDetail.do")
	public ModelAndView myWorkCompleteListDetail(HttpServletRequest request, @ModelAttribute MyWorkInfo dbParams)throws Exception{
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mypage/myPageMessageListDetail");
		
		return mav;
	}	
	
	
	/**
	 * <pre>
	 * 나의 작업 >> 나의 정보관리
	 * </pre>
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myPage/info.do")
	public ModelAndView myPageInfo(HttpServletRequest request)throws Exception{

		ModelAndView mav = new ModelAndView();	
		UserBasInfo info = new UserBasInfo();
		SessionInfo member = (SessionInfo)SessionHandler.getInstance().getLoginInfo(request);
		info.setUserId(member.getUserId());
		mav.addObject("viewInfo", userService.selectUserDetail(info));
		mav.setViewName("mypage/myPageInfo");
		return mav;
	}

	
	/**
	 * <pre>
	 * 나의 작업 >> 나의 정보관리 수정폼
	 * </pre>
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myPage/infoModify.do")
	public ModelAndView myPageInfoModify(HttpServletRequest request)throws Exception{

		ModelAndView mav = new ModelAndView();
		UserBasInfo info = new UserBasInfo();
		SessionInfo member = (SessionInfo)SessionHandler.getInstance().getLoginInfo(request);
		info.setUserId(member.getUserId());
		mav.addObject("viewInfo", userService.selectUserDetail(info));
		mav.setViewName("mypage/myPageInfoModify");
		
		return mav;
	}
	
	/*
	 * MyPage Common PopUp URL
	 */
	
	/**
	 * <pre>
	 * 시험발송
	 * </pre>
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/myPage/popup/testSenderPop.do")
	public ModelAndView testSenderPop(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();	
		mav.setViewName("mypage/popup/testSenderPop");
		return mav;
	}
	
}