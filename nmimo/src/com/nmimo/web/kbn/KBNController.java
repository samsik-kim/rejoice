package com.nmimo.web.kbn;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nmimo.common.info.PageInfo;
import com.nmimo.common.util.StringUtils;
import com.nmimo.data.kbn.info.KBNInfo;
import com.nmimo.service.kbn.KBNService;

/**
 * <pre>
 * KBN관리 Controller
 * </pre>
 * @file KBNController.java
 * @since 2013. 5. 3.
 * @author Leesh
 */
@Controller
public class KBNController {

	/** LOG4J */
	Log logger = LogFactory.getLog(getClass());

	@Autowired
	KBNService kbnService;
	
	/**
	 * <pre>
	 *  KBN관리	>>	SMS/MMS작업요청List 
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/kbn/msgRequestList.do")
	public ModelAndView kbnMsgRequestList(HttpServletRequest request, @ModelAttribute KBNInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[KBN] Controller -> kbnMsgRequestList ");
		}

		return new ModelAndView("kbn/msgRequestList");
	}

	/**
	 * <pre>
	 * 나의작업 >> SMS/MMS작업요청 InnerList
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/kbn/ajaxMsgRequestListInner.do")
	public ModelAndView kbnMsgRequestListInner(HttpServletRequest request, @ModelAttribute KBNInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[KBN] Controller -> kbnMsgRequestListInner ");
		}

		int currentPage = Integer.parseInt(StringUtils.nvlStr(request.getParameter("pageIndex"), "1")); //현재 페이지 위치
		int pageUnit = 10; // 페이지를 보여줄 개수
		int pageSize = 10; // 한 페이지에 보여줄 게시물 수 
		
		PageInfo pageInfo = new PageInfo(request, currentPage, pageUnit, pageSize);
		ModelAndView mav = new ModelAndView();

//		pageInfo = reservationService.findStatsListBySearchCode(dbParams , pageInfo);
		
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("kbn/inc/msgRequestListInner");
									
		return mav;
	}
	
	/**
	 * <pre>
	 *  KBN관리	>>	SMS/MMS작업요청 
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/kbn/msgRequestForm.do")
	public ModelAndView kbnMsgRequestForm(HttpServletRequest request, @ModelAttribute KBNInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[KBN] Controller -> kbnMsgRequestForm ");
		}

		return new ModelAndView("kbn/msgRequestForm");
	}
	
	
	
	/**
	 * <pre>
	 * KBN관리	>>	전송정보List
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/kbn/msgSendList.do")
	public ModelAndView kbnMsgSendList(HttpServletRequest request, @ModelAttribute KBNInfo dbParams)throws Exception{
		
		if (logger.isDebugEnabled()) {
			logger.debug("[KBN] Controller -> kbnMsgSendList ");
		}

		return new ModelAndView("kbn/msgSendList");
	}
	
	
	/**
	 * <pre>
	 * KBN관리 >> 전송정보 InnerList
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/kbn/ajaxMsgSendListInner.do")
	public ModelAndView kbnMsgSendListInner(HttpServletRequest request, @ModelAttribute KBNInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[KBN] Controller -> kbnMsgSendListInner ");
		}

		int currentPage = Integer.parseInt(StringUtils.nvlStr(request.getParameter("pageIndex"), "1")); //현재 페이지 위치
		int pageUnit = 10; // 페이지를 보여줄 개수
		int pageSize = 10; // 한 페이지에 보여줄 게시물 수 
		
		PageInfo pageInfo = new PageInfo(request, currentPage, pageUnit, pageSize);
		ModelAndView mav = new ModelAndView();

//		pageInfo = reservationService.findStatsListBySearchCode(dbParams , pageInfo);
		
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("kbn/inc/msgSendListInner");
									
		return mav;
	}
	

	/**
	 * <pre>
	 * KBN관리	>>	나의정보
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/kbn/myPageInfo.do")
	public ModelAndView kbnMyPageInfo(HttpServletRequest request, @ModelAttribute KBNInfo dbParams)throws Exception{
		
		if (logger.isDebugEnabled()) {
			logger.debug("[KBN] Controller -> kbnMyPageInfo ");
		}

		return new ModelAndView("kbn/myPageInfo");
	}
	
}
