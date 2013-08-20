package com.nmimo.web.review;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nmimo.common.configuration.ConfigurationService;
import com.nmimo.common.info.PageInfo;
import com.nmimo.common.util.StringUtils;
import com.nmimo.data.review.info.BanrInfo;
import com.nmimo.data.review.info.BfacRegBasInfo;
import com.nmimo.data.review.info.ReviewInfo;
import com.nmimo.service.review.ReviewService;

/**
 * <pre>
 * 검토 Controller
 * </pre>
 * @file ReviewController.java
 * @since 2013. 6. 27.
 * @author Administrator
 */
@Controller
public class ReviewController {

	
	/** LOG4J */
	Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	private ConfigurationService config;
	
	/**
	 * <pre>
	 * 검토	>>	대기List  
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/review/standbyList.do")
	public ModelAndView reviewStandbyList(HttpServletRequest request, @ModelAttribute ReviewInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[Review] Controller -> reviewStandbyList ");
		}

		return new ModelAndView("review/standbyList");
	}

	/**
	 * <pre>
	 * 검토	>>	대기 InnerList
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/review/ajaxStandbyListInner.do")
	public ModelAndView reviewStandbyListInner(HttpServletRequest request, @ModelAttribute ReviewInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[Review] Controller -> reviewStandbyListInner ");
		}

		int currentPage = Integer.parseInt(StringUtils.nvlStr(request.getParameter("pageIndex"), "1")); //현재 페이지 위치
		int pageUnit = 10; // 페이지를 보여줄 개수
		int pageSize = 10; // 한 페이지에 보여줄 게시물 수 
		
		PageInfo pageInfo = new PageInfo(request, currentPage, pageUnit, pageSize);
		ModelAndView mav = new ModelAndView();

		//대기
		dbParams.setMsgCharVal(config.getString("mypage.msg.div.wait"));

		pageInfo = reviewService.selectWorkList(pageInfo, dbParams);
		
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("review/inc/standbyListInner");
		
		return mav;
	}
	
	
	
	/**
	 * <pre>
	 * 검토	>>	대기List >> 상세
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/review/standbyListDetail.do")
	public ModelAndView reviewStandbyListDetail(HttpServletRequest request, @ModelAttribute ReviewInfo dbParams)throws Exception{
		
		ModelAndView mav = new ModelAndView();
		dbParams.setWrkId(request.getParameter("postKey"));
		mav.setViewName("review/standbyListDetail");
		mav.addObject("viewInfo", reviewService.selectWorkDetail(dbParams));
		
		return mav;
	}	
	
	
	/**
	 * <pre>
	 * 검토	>>	대기List >> 수정
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/review/standbyListDetailModify.do")
	public ModelAndView reviewStandbyListDetailModify(HttpServletRequest request, @ModelAttribute ReviewInfo dbParams)throws Exception{
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("review/standbyListDetailModify");
		
		return mav;
	}
	
	
	/**
	 * <pre>
	 * 검토	>>	대기List >> 상세 >> 반려
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/review/popup/standbyListDetailReturnJobPop.do")
	public ModelAndView reviewStandbyListDetailReturnJobPop(HttpServletRequest request, @ModelAttribute ReviewInfo dbParams)throws Exception{
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("common/popup/returnJobPop");
		
		return mav;
	}	
	

	/**
	 * <pre>
	 * 검토 >> 공통 >> 미리보기
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/review/popup/previewPop.do")
	public ModelAndView reviewPreviewPop(HttpServletRequest request, @ModelAttribute ReviewInfo dbParams)throws Exception{
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("common/popup/previewPop");
		
		return mav;
	}	

	
	/**
	 * <pre>
	 * 검토 >> 공통 >> 시험발송
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/review/popup/testSenderPop.do")
	public ModelAndView reviewTestSenderPop(HttpServletRequest request, @ModelAttribute ReviewInfo dbParams)throws Exception{
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("common/popup/testSenderPop");
		
		return mav;
	}	
	
	
	/**
	 * <pre>
	 * 검토	>>	반려 List  
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/review/returnList.do")
	public ModelAndView reviewReturnList(HttpServletRequest request, @ModelAttribute ReviewInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[Review] Controller -> reviewReturnList ");
		}

		return new ModelAndView("review/returnList");
	}

	/**
	 * <pre>
	 * 검토	>>	반려 InnerList
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/review/ajaxReturnListInner.do")
	public ModelAndView reviewReturnListInner(HttpServletRequest request, @ModelAttribute ReviewInfo dbParams)throws Exception{
		
		if (logger.isDebugEnabled()) {
			logger.debug("[Review] Controller -> reviewReturnListInner ");
		}

		int currentPage = Integer.parseInt(StringUtils.nvlStr(request.getParameter("pageIndex"), "1")); //현재 페이지 위치
		int pageUnit = 10; // 페이지를 보여줄 개수
		int pageSize = 10; // 한 페이지에 보여줄 게시물 수 
		
		PageInfo pageInfo = new PageInfo(request, currentPage, pageUnit, pageSize);
		ModelAndView mav = new ModelAndView();

		//대기
		dbParams.setMsgCharVal(config.getString("mypage.msg.div.companion"));

		pageInfo = reviewService.selectWorkList(pageInfo, dbParams);
		
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("review/inc/returnListInner");
		
		return mav;
	}	
	
	
	
	/**
	 * <pre>
	 * 검토	>>	반려List >> 상세
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/review/returnListDetail.do")
	public ModelAndView reviewReturnListDetail(HttpServletRequest request, @ModelAttribute ReviewInfo dbParams)throws Exception{
		
		ModelAndView mav = new ModelAndView();
		dbParams.setWrkId(request.getParameter("postKey"));
		mav.setViewName("review/returnListDetail");
		mav.addObject("viewInfo", reviewService.selectWorkDetail(dbParams));
		
		return mav;
	}	
	
	

	
	/**
	 * <pre>
	 * 검토	>>	배너 List  
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/review/bannerList.do")
	public ModelAndView reviewBannerList(HttpServletRequest request, @ModelAttribute ReviewInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[Review] Controller -> reviewBannerList ");
		}

		return new ModelAndView("review/bannerList");
	}

	/**
	 * <pre>
	 * 검토	>>	배너 InnerList
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/review/ajaxBannerListInner.do")
	public ModelAndView reviewBannerListInner(HttpServletRequest request, @ModelAttribute BanrInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[Review] Controller -> reviewBannerListInner ");
		}

		int currentPage = Integer.parseInt(StringUtils.nvlStr(request.getParameter("pageIndex"), "1")); //현재 페이지 위치
		int pageUnit = 10; // 페이지를 보여줄 개수
		int pageSize = 10; // 한 페이지에 보여줄 게시물 수 
		
		PageInfo pageInfo = new PageInfo(request, currentPage, pageUnit, pageSize);
		ModelAndView mav = new ModelAndView();

		pageInfo = reviewService.selectBanrList(pageInfo, dbParams);
		
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("review/inc/bannerListInner");
		
		return mav;
	}
	
	
	/**
	 * <pre>
	 * 검토	>> 배너 >> 작성  
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/review/bannerRegForm.do")
	public ModelAndView reviewBannerRegForm(HttpServletRequest request, @ModelAttribute BanrInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[Review] Controller -> reviewBannerRegForm ");
		}

		return new ModelAndView("review/bannerRegForm");
	}
	
	/**
	 * <pre>
	 * 배너 상세
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/review/bannerDetail.do")
	public ModelAndView reviewBannerDetail(HttpServletRequest request, @ModelAttribute BanrInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[Review] Controller -> reviewBannerRegForm ");
		}
		
		ModelAndView mav = new ModelAndView("review/bannerDetail");
		
		dbParams.setBanrBdyAdmSeq(request.getParameter("postKey"));
		mav.addObject("info", reviewService.selectBanrDetail(Integer.parseInt(dbParams.getBanrBdyAdmSeq())));
		
		return mav;
	}
	
	
	/**
	 * <pre>
	 * 검토	>>	사전예약 List  
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/review/reservationList.do")
	public ModelAndView reviewReservationList(HttpServletRequest request, @ModelAttribute ReviewInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[Review] Controller -> reviewReservationList ");
		}

		return new ModelAndView("review/reservationList");
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
	@RequestMapping("/review/ajaxReservationListInner.do")
	public ModelAndView reviewReservationListInner(HttpServletRequest request, @ModelAttribute BfacRegBasInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[Review] Controller -> reviewReservationListInner ");
		}

		int currentPage = Integer.parseInt(StringUtils.nvlStr(request.getParameter("pageIndex"), "1")); //현재 페이지 위치
		int pageUnit = 10; // 페이지를 보여줄 개수
		int pageSize = 10; // 한 페이지에 보여줄 게시물 수 
		
		PageInfo pageInfo = new PageInfo(request, currentPage, pageUnit, pageSize);
		ModelAndView mav = new ModelAndView();

		pageInfo = reviewService.selectBfacRegList(pageInfo, dbParams);
		
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("review/inc/reservationListInner");
		
		return mav;
	}
	
	/**
	 * <pre>
	 * 검토 >> 사전예약 >> 신청
	 * </pre>
	 * @param request
	 * @return
	 */
	@RequestMapping("/review/reservationRegisterForm.do")
	public ModelAndView reviewRegisterForm(HttpServletRequest request){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("review/reservationRegisterForm");
		
		return modelAndView;
	}	
	
	
	/**
	 * <pre>
	 * 검토 >> 사전예약 >> 공통 >> 예약날짜선택
	 * </pre>
	 * @param request
	 * @return
	 */
	@RequestMapping("/review/popup/calendarPop.do")
	public ModelAndView reviewCalendarPop(HttpServletRequest request){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/common/popup/calendarPop");
		
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * 검토 >> 사전예약 >> 상세
	 * </pre>
	 * @param request
	 * @return
	 */              
	@RequestMapping("/review/reservationDetail.do")
	public ModelAndView reviewReservationDetail(HttpServletRequest request, @ModelAttribute BfacRegBasInfo dbParams){
		
		ModelAndView modelAndView = new ModelAndView();
		dbParams.setBfacRegSeq(request.getParameter("postKey"));
		modelAndView.addObject("info", reviewService.selectBfacDetail(Integer.parseInt(dbParams.getBfacRegSeq())));
		modelAndView.setViewName("review/reservationDetail");
		
		return modelAndView;
	}
	

	/**
	 * <pre>
	 * 검토	>>	완료 List  
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/review/completeList.do")
	public ModelAndView reviewCompleteList(HttpServletRequest request, @ModelAttribute ReviewInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[Review] Controller -> reviewCompleteList ");
		}

		return new ModelAndView("review/completeList");
	}

	
	/**
	 * <pre>
	 * 검토	>>	완료 InnerList
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/review/ajaxCompleteListInner.do")
	public ModelAndView reviewCompleteListInner(HttpServletRequest request, @ModelAttribute ReviewInfo dbParams)throws Exception{
		
		if (logger.isDebugEnabled()) {
			logger.debug("[Review] Controller -> reviewCompleteListInner ");
		}

		int currentPage = Integer.parseInt(StringUtils.nvlStr(request.getParameter("pageIndex"), "1")); //현재 페이지 위치
		int pageUnit = 10; // 페이지를 보여줄 개수
		int pageSize = 10; // 한 페이지에 보여줄 게시물 수 
		
		PageInfo pageInfo = new PageInfo(request, currentPage, pageUnit, pageSize);
		ModelAndView mav = new ModelAndView();

		//완료
		dbParams.setMsgCharVal(config.getString("mypage.msg.div.complete"));

		pageInfo = reviewService.selectWorkList(pageInfo, dbParams);
		
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("review/inc/completeListInner");
		
		return mav;
	}	
	
	

	/**
	 * <pre>
	 * 검토	>>	완료List >> 상세(공지/홍보)
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/review/completeListDetail.do")
	public ModelAndView reviewCompleteListDetail(HttpServletRequest request, @ModelAttribute ReviewInfo dbParams)throws Exception{
		
		ModelAndView mav = new ModelAndView();
		dbParams.setWrkId(request.getParameter("postKey"));
		mav.setViewName("review/completeListDetail");
		mav.addObject("viewInfo", reviewService.selectWorkDetail(dbParams));
		
		return mav;
	}	
	
	
	/**
	 * <pre>
	 * 검토	>>	완료List >> 상세(자동안내)
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/review/completeListAutoDetail.do")
	public ModelAndView reviewCompleteListAutoDetail(HttpServletRequest request, @ModelAttribute ReviewInfo dbParams)throws Exception{
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("review/completeListAutoDetail");
		
		return mav;
	}
	
	
	/**
	 * <pre>
	 * 검토	>>	캠페인 List  
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/review/ecammsList.do")
	public ModelAndView reviewEcammsList(HttpServletRequest request, @ModelAttribute ReviewInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[Review] Controller -> reviewEcammsList ");
		}

		return new ModelAndView("review/ecammsList");
	}

	
	/**
	 * <pre>
	 * 검토	>>	캠페인 InnerList
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/review/ajaxEcammsListInner.do")
	public ModelAndView reviewEcammsListInner(HttpServletRequest request, @ModelAttribute ReviewInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[Review] Controller -> reviewEcammsListInner ");
		}

		int currentPage = Integer.parseInt(StringUtils.nvlStr(request.getParameter("pageIndex"), "1")); //현재 페이지 위치
		int pageUnit = 10; // 페이지를 보여줄 개수
		int pageSize = 10; // 한 페이지에 보여줄 게시물 수 
		
		PageInfo pageInfo = new PageInfo(request, currentPage, pageUnit, pageSize);
		ModelAndView mav = new ModelAndView();

//		pageInfo = reservationService.findStatsListBySearchCode(dbParams , pageInfo);
		
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("review/inc/ecammsListInner");
		
		return mav;
	}		
}
