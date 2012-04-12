package com.stockinvest.web.controller.board;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		ModelAndView mav = new ModelAndView("board/list");
		mav.addObject("info", info);
		return mav;
	}	
	
	@RequestMapping("/board/ajaxBoardListinner.do")
	public ModelAndView boardlistinner(HttpServletRequest request, @ModelAttribute  BoardInfo info)throws Exception {
		ModelAndView mav = new ModelAndView();
		String getStDt = StringUtils.nvlStr(info.getStDt(),"");
		String getEnDt = StringUtils.nvlStr(info.getEnDt(),"");
		info.setStDt(StringUtils.nvlStr(info.getStDt(),"").replaceAll("-", ""));
		info.setEnDt(StringUtils.nvlStr(info.getEnDt(),"").replaceAll("-", ""));
		int currentPage = Integer.parseInt(StringUtils.nvlStr(request.getParameter("currentPage"), "1"));
		int pageUnit = 10; // 페이지를 보여줄 갯수
		int pageSize = 20; // 한페이지에 보여줄 게시물수
		PageInfo pageInfo = new PageInfo(request, currentPage, pageUnit, pageSize);
		if (info.getBbsCd()!= null) {
			pageInfo = service.selectBoardList(pageInfo, info);
		}
		mav.addObject("pageInfo", pageInfo);
		info.setStDt(getStDt);
		info.setEnDt(getEnDt);
		mav.addObject("info", info);
		mav.setViewName("board/listInner");
		return mav;		
	}
	
	@RequestMapping("/board/boardListExcel.do")
	 public ModelAndView excelBoardExportForm(HttpServletRequest req) throws Exception {
	  BoardInfo info = new BoardInfo();
	  info.setStDt(StringUtils.nvlStr(req.getParameter("stDt"),"").replaceAll("-", ""));
	  info.setEnDt(StringUtils.nvlStr(req.getParameter("enDt"),"").replaceAll("-", ""));
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
		BoardInfo searchInfo = info;
		if(info.getSeqNo() != null){
			info = service.selectBoardInfo(info);
			info = setSearchInfo(info,searchInfo);
		}
		mav.addObject("info", info);
		return mav;
	}	
	
	@RequestMapping("/board/boardInsert.do")
	public ModelAndView insertBoard(HttpServletRequest request, @ModelAttribute("setBoardInfo") BoardInfo info)throws Exception{
		
		String savePath = config.getString("board.dir");
		String allowExt = "swf,gif,jpg,png,jpeg";
		
		ArrayList<HashMap> fileData = new FileUpload().upload(request, savePath, allowExt);

		// 파일 업로드 관련 변수
		String resultCode = ""; // 00 성공
		String resultMessage = ""; // 
		String inputName = "";// input 태그 이름
		String fileRealName = " "; // 저장된 파일명
		String fileOrgName = " "; // 원본 파일명
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
		
		info.setFile2(fileRealName);
		info.setFile3(fileOrgName);		
		String redirectUrl = "redirect:/board/boardList.do?bbsCd="+info.getBbsCd();
		ModelAndView mav = new ModelAndView(redirectUrl);
		info.setContent(request.getParameter("CONTENT"));
		service.insertBoardInfo(info);
		return mav;
	}
	
	@RequestMapping("/board/boardUpdate.do")
	public ModelAndView updateBoard(HttpServletRequest request, @ModelAttribute("setBoardInfo") BoardInfo info)throws Exception{
		String redirectUrl = "redirect:/board/boardList.do?bbsCd="+info.getBbsCd();		
		ModelAndView mav = new ModelAndView(redirectUrl);
		service.updateBoardInfo(info);
		return mav;
	}	
	
	@RequestMapping("/board/boardDelete.do")
	public ModelAndView deleteBoard(HttpServletRequest request, @ModelAttribute("setBoardInfo") BoardInfo info)throws Exception{
		String redirectUrl = "redirect:/board/boardList.do?bbsCd="+info.getBbsCd();
		ModelAndView mav = new ModelAndView(redirectUrl);
		info.setDelArr(info.getDelVal().split(","));
		service.deleteBoardInfo(info);
		return mav;
	}
	
	/** 
	 * <pre>
	 * 파일 다운로드
	 * 다운로드 요청된 파일의 정보를 서비스 객체를 통해 얻어오고,
	 * 얻어온 파일정보 객체를 ModelAndView에 셋팅해서 리턴한다.
	 * </pre>
	 * @param request HttpServletRequest 객체
	 * @param response HttpServletResponse 객체
	 * @return 다운로드 요청된 파일과 다운로드View 정보를 가진 ModelAndView객체
	 */
	@RequestMapping("/board/fileDownload.do")
	public ModelAndView fileDown(HttpServletRequest request, HttpServletResponse response){
		String getFile2 = StringUtils.nvlStr(request.getParameter("file2")); //실제파일명
 		
//		int lastIndex = getFile2.lastIndexOf(".");
		String fileName = getFile2;//getFile2.substring(0,lastIndex); //파일명
//		String fileType = fileInfo[1]; //확장자명
		//String fileType = StringUtils.nvlStr(request.getParameter("fileType"), "xls");
		//String fileName = StringUtils.nvlStr(request.getParameter("fileName"), "");
		//파일의 경로 지정
		String filePath = config.getString("uploadTempDir")+"/"+config.getString("board.dir"); 
		ModelAndView mav =new ModelAndView();
		if(!"".equals(fileName)){	
			//파일 객체에 다운받을 파일의 경로와 파일의 이름을 넣어서 생성
			File downFile = new File(filePath,fileName);
			mav.setViewName("downloadView");	
			mav.addObject("downloadFile", downFile);
			mav.addObject("fileName", fileName);	
			
			if(logger.isDebugEnabled()){
				logger.debug(">>>File DownLoad :  " + fileName + " : " + downFile.getPath() );
			}
					
		}
		
		return mav;
	}
	
	public BoardInfo setSearchInfo(BoardInfo info,BoardInfo searchInfo) {
		info.setCurrentPage(StringUtils.nvlStr(searchInfo.getCurrentPage(),"1"));
		info.setStDt(StringUtils.nvlStr(searchInfo.getStDt(),""));
		info.setEnDt(StringUtils.nvlStr(searchInfo.getEnDt(),""));
		info.setSearchKey(StringUtils.nvlStr(searchInfo.getSearchKey(),""));
		info.setSearchValue(StringUtils.nvlStr(searchInfo.getSearchValue(),""));
		info.setBbsCd(StringUtils.nvlStr(searchInfo.getBbsCd(),""));
		return info;
	}
	
}
