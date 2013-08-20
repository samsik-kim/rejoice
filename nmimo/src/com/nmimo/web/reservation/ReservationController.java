package com.nmimo.web.reservation;

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
import com.nmimo.data.reservation.info.ReservationInfo;
import com.nmimo.data.review.info.BfacRegBasInfo;
import com.nmimo.data.review.info.ReviewInfo;
import com.nmimo.service.reservation.ReservationService;

/**
 * <pre>
 * 사전예약 Controller
 * </pre>
 * @file ReservationController.java
 * @since 2013. 5. 2.
 * @author Leesh
 */
@Controller
public class ReservationController {
	
	/** LOG4J */
	Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	ReservationService reservationService;
	
	/**
	 * <pre>
	 * 사전예약 List  
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/reservation/list.do")
	public ModelAndView reservationList(HttpServletRequest request, @ModelAttribute ReservationInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[Reservation] Controller -> reservationList ");
		}

		return new ModelAndView("reservation/list");
	}
	
	
	/**
	 * <pre>
	 * 검토	>> 사전예약 >> InnerList
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */                      
	@RequestMapping("/reservation/ajaxListInner.do")
	public ModelAndView reviewReservationListInner(HttpServletRequest request, @ModelAttribute ReservationInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[Reservation] Controller -> reservationListInner ");
		}

		int currentPage = Integer.parseInt(StringUtils.nvlStr(request.getParameter("pageIndex"), "1")); //현재 페이지 위치
		int pageUnit = 10; // 페이지를 보여줄 개수
		int pageSize = 10; // 한 페이지에 보여줄 게시물 수 
		
		PageInfo pageInfo = new PageInfo(request, currentPage, pageUnit, pageSize);
		ModelAndView mav = new ModelAndView();

		pageInfo = reservationService.selectBfacRegList(pageInfo, dbParams);
		
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("reservation/inc/listInner");
		
		return mav;
	}
	
	
	/**
	 * <pre>
	 * 사전예약 >> 사전예약신청
	 * </pre>
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/reservation/registerForm.do")
	public ModelAndView reservationRegisterForm(HttpServletRequest request){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("reservation/registerForm");
		
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * 사전예약 >> 공통 >> 예약날짜선택
	 * </pre>
	 * @param request
	 * @return
	 */
	@RequestMapping("/reservation/popup/calendarPop.do")
	public ModelAndView reservationCalendarPop(HttpServletRequest request){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/common/popup/calendarPop");
		
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * 사전예약 상세
	 * </pre>
	 * @param request
	 * @return
	 */
	@RequestMapping("/reservation/detail.do")
	public ModelAndView reservationDetail(HttpServletRequest request, @ModelAttribute ReservationInfo dbParams){
		
		ModelAndView modelAndView = new ModelAndView();
		dbParams.setBfacRegSeq(request.getParameter("postKey"));
		modelAndView.setViewName("reservation/detail");
		
		return modelAndView;
	}
}
