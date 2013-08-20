package com.nmimo.web.stats;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nmimo.common.info.PageInfo;
import com.nmimo.common.util.DateUtils;
import com.nmimo.common.util.ExcelExportView;
import com.nmimo.common.util.StringUtils;
import com.nmimo.data.stats.info.StatsInfo;
import com.nmimo.service.stats.StatsService;

/**
 * <pre>
 * 통계 
 * </pre>
 * @file StatsController.java
 * @since 2013. 4. 15.
 * @author Leesh
 */
@Controller
public class StatsController {

	/** LOG4J */
	Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	StatsService statsService;
	
	/**
	 * <pre>
	 * 통계 List
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/stats/list.do")
	public ModelAndView statsList(HttpServletRequest request, @ModelAttribute StatsInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[Stats] Controller -> statsList ");
		}

		return new ModelAndView("stats/list");
	}

	/**
	 * <pre>
	 * 통계 InnerList
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */				 
	@RequestMapping("/stats/ajaxlistInner.do")
	public ModelAndView statsListInner(HttpServletRequest request, @ModelAttribute StatsInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[Stats] Controller -> statsListInner ");
		}

		int currentPage = Integer.parseInt(StringUtils.nvlStr(request.getParameter("pageIndex"), "1")); //현재 페이지 위치
		int pageUnit = 10; // 페이지를 보여줄 개수
		int pageSize = 10; // 한 페이지에 보여줄 게시물 수 
		
		PageInfo pageInfo = new PageInfo(request, currentPage, pageUnit, pageSize);
		ModelAndView mav = new ModelAndView();

//		pageInfo = statsService.findStatsListBySearchCode(dbParams , pageInfo);
		
		//TODO 사용자예외처리 Test
//		if(pageInfo.getDataList().size() > 0){
//			throw new MimoHandleableException("MimoHandleableException 예외처리 sample");
//		}

		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("stats/inc/listInner");
		
		return mav;
	}
	
	
	/**
	 * <pre>
	 * 통계 >> 발송내역 상세
	 * </pre>
	 * @param reqeust
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/stats/popup/listInfoDetailPop.do")
	public ModelAndView listInfoDetailPop(HttpServletRequest reqeust, @ModelAttribute StatsInfo dbParams)throws Exception{
		
		if (logger.isDebugEnabled()) {
			logger.debug("[Stats] Controller -> listInfoDetailPop ");
		}
		
		return new ModelAndView("stats/popup/listInfoDetailPop");
	}
	
	
	/**
	 * <pre>
	 * 통계 >> 발송정보 상세
	 * </pre>
	 * @param reqeust
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/stats/popup/sendInfoDetailPop.do")
	public ModelAndView sendInfoDetailPop(HttpServletRequest reqeust, @ModelAttribute StatsInfo dbParams)throws Exception{
		
		if (logger.isDebugEnabled()) {
			logger.debug("[Stats] Controller -> sendInfoDetailPop ");
		}
		
		return new ModelAndView("stats/popup/sendInfoDetailPop");
	}
	
	/**
	 * <pre>
	 * 통계 >> List 엑셀저장
	 * </pre>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/stats/popup/excelSavePop.do")
	public String statsExcelSaveForm() throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[Stats] Controller -> statsExcelSaveForm ");
		}
		
		return "stats/popup/excelSavePop";
	}
	
	
	/**
	 * <pre>
	 * 통계 >> 엑셀저장
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/stats/popup/excelSave.do")
	public ModelAndView statsExcelSave(HttpServletRequest request, @ModelAttribute StatsInfo dbParams)throws Exception{
		
		if (logger.isDebugEnabled()) {
			logger.debug("[Stats] Controller -> statsExcelSave ");
		}
		
		// 기간조회 초기값
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"));
		
		DateUtils.getToday("yyyy-MM-dd");
		
		String end_date = StringUtils.nvlStr(request.getParameter("end_date"));
		if(end_date.equals("")) {
			end_date = sdf.format(c.getTime());
		}
		String start_date = StringUtils.nvlStr(request.getParameter("start_date"));
		if(start_date.equals("")) {
			c.add(Calendar.DATE, -7);
			start_date = sdf.format(c.getTime());
		}
		/* 엑셀 데이터 설정 */
		Map model = new HashMap();
		String[] header = {"작업명", "메세지구분", "메세지특성", "발송부서", "발송자", "발송일", "총발송", "총성공", "성공률(%)","실패건수","실패률(%)"};
		String[] columns = {"jobnm", "service", "join_mob", "department", "userid", "insertdt", "","","","",""};
		List data = new ArrayList();
	
		HashMap excelItem;

//		List<StatsInfo> excelList = statsService.findStatsListExcel(dbParams);
//
//		Iterator<StatsInfo> iterator = excelList.iterator();
//	
//		while (iterator.hasNext()) {
//			dbParams = (StatsInfo)iterator.next();
//			excelItem = new HashMap();
//			excelItem.put("jobnm", dbParams.getJobNm());
//			excelItem.put("service", dbParams.getService());
//			excelItem.put("join_mob", dbParams.getJobType());
//			excelItem.put("department", dbParams.getDepartment());
//			excelItem.put("userid", dbParams.getUserId());
//			excelItem.put("insertdt", dbParams.getInsertDt());
//			excelItem.put("insertdt", dbParams.getInsertDt());
//			data.add(excelItem);
//		}
	
		/* 엑셀 출력 */
		String temp = "MIMO통계_" + sdf.format(c.getTime()) + ".xls";
		String fileName = new String(temp.getBytes("UTF-8"), "ISO-8859-1");
		String sheetName = "sheetName";
		model.put(ExcelExportView.HEADER, header);
		model.put(ExcelExportView.DATA_LIST, data);
		model.put(ExcelExportView.COLUMN, columns);
		model.put(ExcelExportView.FILE_NAME, fileName);
		model.put(ExcelExportView.SHEET_NAME, sheetName);
		
		return new ModelAndView("excelExportView", "excelExportView", model);
	}
	
	/**
	 * <pre>
	 * 통계 >> 전송정보 상세 >> 엑셀저장
	 * </pre>
	 * @param reqeust
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/stats/popup/excelSaveCallNumberPop.do")
	public ModelAndView statsExcelSaveCallNumberPop(HttpServletRequest reqeust, @ModelAttribute StatsInfo dbParams)throws Exception{
		
		if (logger.isDebugEnabled()) {
			logger.debug("[Stats] Controller -> statsExcelSaveCallNumberPop ");
		}
		
		return new ModelAndView("stats/popup/excelSaveCallNumberPop");
	}
	
	
	
}
