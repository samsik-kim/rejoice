package com.stockinvest.web.controller.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tframe.common.util.DateUtils;
import tframe.common.util.StringUtils;
import tframe.web.mvc.support.views.ExcelExportView;
import tframe.web.page.PageInfo;

import com.stockinvest.data.board.info.BoardInfo;
import com.stockinvest.service.board.BoardService;

/**
 * 게시판 관리
 * @author kakashi
 *
 */
@Controller
public class BoardController {

	@Autowired
	BoardService service;
	
	/**
	 * @param request
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping("/board/boardList.do")
	public ModelAndView boardList(HttpServletRequest request, @ModelAttribute BoardInfo info)throws Exception{
		ModelAndView mav = new ModelAndView();
		int currentPage = Integer.parseInt(StringUtils.nvlStr(request.getParameter("currentPage"), "1"));
		int pageUnit = 10; // 페이지를 보여줄 갯수
		int pageSize = 20; // 한페이지에 보여줄 게시물수
		PageInfo pageInfo = new PageInfo(request, currentPage, pageUnit, pageSize);
		pageInfo = service.selectBoardList(pageInfo, info);
		mav.addObject("pageInfo", pageInfo);
		
		mav.addObject("info", info);
		mav.setViewName("board/list");
		return mav;
	}	
	
	@RequestMapping("/board/boardListExcel.do")
	 public ModelAndView excelExportForm(HttpServletRequest req) throws Exception {
	  BoardInfo info = new BoardInfo();
	  info.setStDt(req.getParameter("stDt"));
	  info.setEnDt(req.getParameter("enDt"));
	  List list = service.selectBoardListExcel(info);
	  Map model = new HashMap();
	  String[] header = {"순번","종목명", "종목코드명" , "지분보유" , "전화번호" ,"정보연락처" , "등록일"};
	  //출력할 Column 목록 
	  String[] columnList = {"SEQNO","CODENAME","CODENUM","HOLDSHARE","TEL","INFOTEL","CRTDATE"};
	  model.put(ExcelExportView.HEADER, header); //헤더정보
	  model.put(ExcelExportView.DATA_LIST, list); // 목록
	  model.put(ExcelExportView.COLUMN, columnList); // 컬럼
	  model.put(ExcelExportView.FILE_NAME, DateUtils.getToday("yyyy-MM-dd.HH:mm")+".xls"); //파일정보
	  model.put(ExcelExportView.SHEET_NAME, "SHEET TEST 1"); //Excel 시트 명
	  return new ModelAndView("excelExportView","excelExportView",model);
	 }	
	
	@RequestMapping("/board/insertBoardForm.do")
	public String insertForm(){
		return "board/write";
	}
	
	/**
	 * @param request
	 * @param info
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/board/boardDetail.do")
	public ModelAndView boardDetailForm(HttpServletRequest request, @ModelAttribute BoardInfo info)throws Exception{
		ModelAndView mav = new ModelAndView("board/view");
		info.setCurrentPage(StringUtils.nvlStr(request.getParameter("currentPage"), "1"));
		if(info.getSeqNo() != null){
			info = service.selectBoardInfo(info);
		}
		mav.addObject("info", info);
		return mav;
	}	
	
	@RequestMapping("/board/boardInsert.do")
	public ModelAndView insertCode(HttpServletRequest request, @ModelAttribute("setBoardInfo") BoardInfo info)throws Exception{
		ModelAndView mav = new ModelAndView("redirect:/board/boardList.do");
		info = setCode(info);
		service.insertBoardInfo(info);
		return mav;
	}
	
	@RequestMapping("/board/boardUpdate.do")
	public ModelAndView updateCode(HttpServletRequest request, @ModelAttribute("setBoardInfo") BoardInfo info)throws Exception{
		ModelAndView mav = new ModelAndView("redirect:/board/boardList.do");
		service.updateBoardInfo(info);
		return mav;
	}	
	
	@RequestMapping("/board/boardDelete.do")
	public ModelAndView deleteCode(HttpServletRequest request, @ModelAttribute("setBoardInfo") BoardInfo info)throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		JSONObject jsonObject = new JSONObject();
		int result = service.deleteBoardInfo(info);
		info.setResultCode(result > 0 ? "SUCCESS" : "FAIL");
		jsonObject.put("result", info.getResultCode());
		mav.addObject("jsonObject", jsonObject);
		return mav;
	}
	
	public BoardInfo setCode(BoardInfo info) {

		return info;
	}
	
}
