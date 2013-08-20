package com.nmimo.web.mypage;

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
import com.nmimo.data.mypage.info.MyWorkInfo;
import com.nmimo.service.mypage.MyWorkService;

/**
 * <pre>
 * 나의작업 Controller
 * </pre>
 * @file MyWorkController.java
 * @since 2013. 6. 12.
 * @author Administrator
 */
@Controller
public class MyWorkController {

	/** LOG4J */
	Log logger = LogFactory.getLog(getClass());

	@Autowired
	MyWorkService myWorkService;
	
	@Autowired
	private ConfigurationService config;
	
	/**
	 * <pre>
	 *  나의작업 >> 대기List
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myWork/standbyList.do")
	public ModelAndView myWorkStandbyList(HttpServletRequest request, @ModelAttribute MyWorkInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[MyWork] Controller -> myWorkStandbyList ");
		}

		return new ModelAndView("mypage/myWorkStandbyList");
	}

	/**
	 * <pre>
	 * 나의작업 >> 대기 InnerList
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myWork/ajaxStandbyListInner.do")
	public ModelAndView myWorkStandbyListInner(HttpServletRequest request, @ModelAttribute MyWorkInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[MyWork] Controller -> myWorkStandbyListInner ");
		}

		int currentPage = Integer.parseInt(StringUtils.nvlStr(request.getParameter("pageIndex"), "1")); //현재 페이지 위치
		int pageUnit = 10; // 페이지를 보여줄 개수
		int pageSize = 10; // 한 페이지에 보여줄 게시물 수 
		
		PageInfo pageInfo = new PageInfo(request, currentPage, pageUnit, pageSize);
		ModelAndView mav = new ModelAndView();
		
		//대기
		dbParams.setApvSttusVal(config.getString("mypage.msg.div.wait"));
		
		pageInfo = myWorkService.selectWorkList(pageInfo, dbParams);
		
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("mypage/inc/myWorkStandbyListInner");
									
		return mav;
	}
	
	
	/**
	 * <pre>
	 * 나의작업 >> 대기 상세보기
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myWork/standbyListDetail.do")
	public ModelAndView myWorkStandbyListDetail(HttpServletRequest request, @ModelAttribute MyWorkInfo dbParams)throws Exception{
		
		ModelAndView mav = new ModelAndView();
		dbParams.setWrkId(request.getParameter("postKey"));
		mav.setViewName("mypage/myWorkStandbyListDetail");
		mav.addObject("viewInfo", myWorkService.selectWorkDetail(dbParams));
		
		return mav;
	} 
	
	/**
	 * <pre>
	 *  나의작업 >> 캠페인 요청실패 List
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myWork/ecammsList.do")
	public ModelAndView myWorkEcammsList(HttpServletRequest request, @ModelAttribute MyWorkInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[MyWork] Controller -> myWorkEcammsList ");
		}

		return new ModelAndView("mypage/myWorkEcammsList");
	}

	/**
	 * <pre>
	 * 나의작업 >> 캠페인 요청실패 InnerList
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myWork/ajaxEcammsListInner.do")
	public ModelAndView myWorkEcammsListInner(HttpServletRequest request, @ModelAttribute MyWorkInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[MyWork] Controller -> myWorkEcammsListInner ");
		}

		int currentPage = Integer.parseInt(StringUtils.nvlStr(request.getParameter("pageIndex"), "1")); //현재 페이지 위치
		int pageUnit = 10; // 페이지를 보여줄 개수
		int pageSize = 10; // 한 페이지에 보여줄 게시물 수 
		
		PageInfo pageInfo = new PageInfo(request, currentPage, pageUnit, pageSize);
		ModelAndView mav = new ModelAndView();

//		pageInfo = reservationService.findStatsListBySearchCode(dbParams , pageInfo);
		
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("mypage/inc/myWorkEcammsListInner");
									
		return mav;
	}
	
	
	/**
	 * <pre>
	 *  나의작업 >> 반려 List
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myWork/returnList.do")
	public ModelAndView myWorkReturnList(HttpServletRequest request, @ModelAttribute MyWorkInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[MyWork] Controller -> myWorkReturnList ");
		}
		
		return new ModelAndView("mypage/myWorkReturnList");
	}

	/**
	 * <pre>
	 * 나의작업 >> 반려 InnerList
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myWork/ajaxReturnListInner.do")
	public ModelAndView myWorkReturnListInner(HttpServletRequest request, @ModelAttribute MyWorkInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[MyWork] Controller -> myWorkReturnListInner ");
		}

		int currentPage = Integer.parseInt(StringUtils.nvlStr(request.getParameter("pageIndex"), "1")); //현재 페이지 위치
		int pageUnit = 10; // 페이지를 보여줄 개수
		int pageSize = 10; // 한 페이지에 보여줄 게시물 수 
		
		PageInfo pageInfo = new PageInfo(request, currentPage, pageUnit, pageSize);
		ModelAndView mav = new ModelAndView();
		
		//반려
		dbParams.setApvSttusVal(config.getString("mypage.msg.div.companion"));
		
		pageInfo = myWorkService.selectWorkList(pageInfo, dbParams);
		
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("mypage/inc/myWorkReturnListInner");
									
		return mav;
	}
	
	
	/**
	 * <pre>
	 * 나의작업 >> 반려 상세보기
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myWork/returnListDetail.do")
	public ModelAndView myWorkReturnListDetail(HttpServletRequest request, @ModelAttribute MyWorkInfo dbParams)throws Exception{
		
		ModelAndView mav = new ModelAndView();
		dbParams.setWrkId(request.getParameter("postKey"));
		mav.setViewName("mypage/myWorkReturnListDetail");
		mav.addObject("viewInfo", myWorkService.selectWorkDetail(dbParams));
		
		return mav;
	}
	

	/**
	 * <pre>
	 * 나의작업 >> 반려	>>	파일업로드
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myWork/popup/fileUploadPop.do")
	public ModelAndView myWorkFileUploadPop(HttpServletRequest request, @ModelAttribute MyWorkInfo dbParams)throws Exception{
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mypage/popup/fileUploadPop");
		
		return mav;
	}

	
	
	/**
	 * <pre>
	 *  나의작업 >> 완료 List
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myWork/completeList.do")
	public ModelAndView myWorkCompleteList(HttpServletRequest request, @ModelAttribute MyWorkInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[MyWork] Controller -> myWorkCompleteList ");
		}

		return new ModelAndView("mypage/myWorkCompleteList");
	}

	/**
	 * <pre>
	 * 나의작업 >> 완료 InnerList
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myWork/ajaxCompleteListInner.do")
	public ModelAndView myWorkCompleteListInner(HttpServletRequest request, @ModelAttribute MyWorkInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[MyWork] Controller -> myWorkCompleteListInner ");
		}

		int currentPage = Integer.parseInt(StringUtils.nvlStr(request.getParameter("pageIndex"), "1")); //현재 페이지 위치
		int pageUnit = 10; // 페이지를 보여줄 개수
		int pageSize = 10; // 한 페이지에 보여줄 게시물 수 
		
		PageInfo pageInfo = new PageInfo(request, currentPage, pageUnit, pageSize);
		ModelAndView mav = new ModelAndView();

		//완료
		dbParams.setApvSttusVal(config.getString("mypage.msg.div.complete"));
		
		pageInfo = myWorkService.selectWorkList(pageInfo, dbParams);
		
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("mypage/inc/myWorkCompleteListInner");
									
		return mav;
	}

	
	/**
	 * <pre>
	 * 나의작업 >> 완료 상세보기
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myWork/completeListDetail.do")
	public ModelAndView myWorkCompleteListDetail(HttpServletRequest request, @ModelAttribute MyWorkInfo dbParams)throws Exception{
		
		ModelAndView mav = new ModelAndView();
		dbParams.setWrkId(request.getParameter("postKey"));
		mav.setViewName("mypage/myWorkCompleteListDetail");
		mav.addObject("viewInfo", myWorkService.selectWorkDetail(dbParams));
		return mav;
	}
	
	/**
	 * <pre>
	 * 상세 -> 미리보기
	 * </pre>
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/myPage/popup/previewPop.do")
	public ModelAndView previewPop(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mypage/popup/previewPop");
		
		return mav;
	}
}