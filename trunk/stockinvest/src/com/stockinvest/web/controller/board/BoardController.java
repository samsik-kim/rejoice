package com.stockinvest.web.controller.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tframe.common.configuration.ConfigurationService;
import tframe.common.util.DateUtils;
import tframe.common.util.StringUtils;
import tframe.web.mvc.support.views.ExcelExportView;
import tframe.web.page.PageInfo;

import com.stockinvest.common.util.FileUpload;
import com.stockinvest.data.board.info.BoardInfo;
import com.stockinvest.data.board.info.BoardManageInfo;
import com.stockinvest.service.board.BoardService;

/**
 * 게시판 관리
 * @author kakashi
 *
 */
@Controller
public class BoardController {
	
	Log logger = LogFactory.getLog(getClass());
	
	@Resource(name="configurationService")
	ConfigurationService config;
	
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
		if (info.getBbsCd()!= null) {
			pageInfo = service.selectBoardList(pageInfo, info);
		}
		mav.addObject("pageInfo", pageInfo);
		
		mav.addObject("info", info);
		mav.setViewName("board/list");
		return mav;
	}	
	
	@RequestMapping("/board/boardListExcel.do")
	 public ModelAndView excelBoardExportForm(HttpServletRequest req) throws Exception {
	  BoardInfo info = new BoardInfo();
	  info.setStDt(req.getParameter("stDt"));
	  info.setEnDt(req.getParameter("enDt"));
	  info.setBbsCd(req.getParameter("bbsCd"));
	  List list = service.selectBoardListExcel(info);
	  Map model = new HashMap();
	  String[] header = {"순번","작성일", "제목" , "종목명" , "종목코드" };
	  //출력할 Column 목록 
      
	  String[] columnList = {"SEQNO","CRTDATE","SUBJECT","CODENAME","CODENUM"};
	  model.put(ExcelExportView.HEADER, header); //헤더정보
	  model.put(ExcelExportView.DATA_LIST, list); // 목록
	  model.put(ExcelExportView.COLUMN, columnList); // 컬럼
	  model.put(ExcelExportView.FILE_NAME, DateUtils.getToday("yyyy-MM-dd.HH:mm")+".xls"); //파일정보
	  model.put(ExcelExportView.SHEET_NAME, "SHEET TEST 1"); //Excel 시트 명
	  return new ModelAndView("excelExportView","excelExportView",model);
	 }	
	
	@RequestMapping("/board/insertBoardForm.do")
	public ModelAndView insertBoardForm(HttpServletRequest request, @ModelAttribute BoardInfo info)throws Exception{
		ModelAndView mav = new ModelAndView("board/write");
		mav.addObject("info", info);
		return mav;
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
	public ModelAndView insertBoard(HttpServletRequest request, @ModelAttribute("setBoardInfo") BoardInfo info)throws Exception{
		
		String savePath = config.getString("uploadTempDir");
		String allowExt = "swf,gif,jpg,png,jpeg";
		
		ArrayList<HashMap> fileData = new FileUpload().upload(request, savePath, allowExt);

		// 파일 업로드 관련 변수
		String resultCode = ""; // 00 성공
		String resultMessage = ""; // 
		String inputName = "";// input 태그 이름
		String fileRealName = ""; // 저장된 파일명
		String fileOrgName = ""; // 원본 파일명
		String fileSize = ""; // 파일용량

		for (int i = 0; i < fileData.size(); i++) {
			HashMap<String, String> row = fileData.get(i);
			resultCode = row.get("resultCode"); // 00 성공
			resultMessage = row.get("resultMessage");
			inputName = row.get("inputName"); // input 태그 이름
			fileRealName = row.get("fileRealName"); // 저장된 파일명
			fileOrgName = row.get("fileOrgName"); // 원본 파일명
			fileSize = row.get("fileSize"); // 파일용량

			logger.debug("==========================================================");
			logger.debug(resultCode);
			logger.debug(resultMessage);
			logger.debug(inputName);
			logger.debug(fileRealName);
			logger.debug(fileOrgName);
			logger.debug(fileSize);
			logger.debug("==========================================================");
		}		
		
		
		String redirectUrl = "redirect:/board/boardList.do?bbsCd="+info.getBbsCd();
		ModelAndView mav = new ModelAndView(redirectUrl);
		info.setCodeName("이타치테스트중");
		info.setContent(request.getParameter("CONTENT"));
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
		ModelAndView mav = new ModelAndView("redirect:/board/boardList.do");
		info.setDelArr(info.getDelVal().split(","));
		service.deleteBoardInfo(info);
		return mav;
	}
	
	public BoardInfo setSearchInfo(BoardInfo info,BoardInfo searchBoardInfo) {
		info.setCodeName("TEST중");
		return info;
	}
	
	/**
	 * @param request
	 * @param info
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/board/ajaxBoardListinner.do")
	public ModelAndView boardManageListinner(HttpServletRequest request, @ModelAttribute BoardManageInfo info)throws Exception{
		ModelAndView mav = new ModelAndView();
		List<BoardManageInfo> list = service.selectBoardManageList();
		mav.addObject("boardManageList", list);
		mav.setViewName("board/manageListInner");
		return mav;
	}	
	
}
