package com.stockinvest.web.controller.stockinvest;

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

import com.stockinvest.data.stockinvest.info.CodeInfo;
import com.stockinvest.service.stockinvest.CodeService;

/**
 * 종목코드 관리
 * @author kakashi
 *
 */
@Controller
public class CodeController {

	@Autowired
	CodeService service;
	
	/**
	 * @param request
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping("/code/codeList.do")
	public ModelAndView codeList(HttpServletRequest request, @ModelAttribute CodeInfo info)throws Exception{
		ModelAndView mav = new ModelAndView();
		int currentPage = Integer.parseInt(StringUtils.nvlStr(request.getParameter("currentPage"), "1"));
		int pageUnit = 10; // 페이지를 보여줄 갯수
		int pageSize = 20; // 한페이지에 보여줄 게시물수
		PageInfo pageInfo = new PageInfo(request, currentPage, pageUnit, pageSize);
		pageInfo = service.selectCodeList(pageInfo, info);
		mav.addObject("pageInfo", pageInfo);
		
		mav.addObject("info", info);
		mav.setViewName("code/list");
		return mav;
	}	
	
	@RequestMapping("/code/codeListExcel.do")
	 public ModelAndView excelExportForm(HttpServletRequest req) throws Exception {
	  CodeInfo info = new CodeInfo();
	  info.setStDt(req.getParameter("stDt"));
	  info.setEnDt(req.getParameter("enDt"));
	  List list = service.selectCodeListExcel(info);
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
	
	@RequestMapping("/code/insertCodeForm.do")
	public String insertForm(){
		return "code/write";
	}
	
	/**
	 * @param request
	 * @param info
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/code/codeDetail.do")
	public ModelAndView codeDetailForm(HttpServletRequest request, @ModelAttribute CodeInfo info)throws Exception{
		ModelAndView mav = new ModelAndView("code/view");
		info.setCurrentPage(StringUtils.nvlStr(request.getParameter("currentPage"), "1"));
		if(info.getSeqNo() != null){
			info = service.selectCodeInfo(info);
		}
		mav.addObject("info", info);
		return mav;
	}	
	
	@RequestMapping("/code/codeInsert.do")
	public ModelAndView insertCode(HttpServletRequest request, @ModelAttribute("setCodeInfo") CodeInfo info)throws Exception{
		ModelAndView mav = new ModelAndView("redirect:/code/codeList.do");
		info = setCode(info);
		service.insertCodeInfo(info);
		return mav;
	}
	
	@RequestMapping("/code/codeUpdate.do")
	public ModelAndView updateCode(HttpServletRequest request, @ModelAttribute("setCodeInfo") CodeInfo info)throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		JSONObject jsonObject = new JSONObject();
		int result = service.updateCodeInfo(info);
		info.setResultCode(result > 0 ? "SUCCESS" : "FAIL");
		jsonObject.put("result", info.getResultCode());
		mav.addObject("jsonObject", jsonObject);
		return mav;
	}	
	
	@RequestMapping("/code/codeDelete.do")
	public ModelAndView deleteCode(HttpServletRequest request, @ModelAttribute("setCodeInfo") CodeInfo info)throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		JSONObject jsonObject = new JSONObject();
		int result = service.deleteCodeInfo(info);
		info.setResultCode(result > 0 ? "SUCCESS" : "FAIL");
		jsonObject.put("result", info.getResultCode());
		mav.addObject("jsonObject", jsonObject);
		return mav;
	}
	
	public CodeInfo setCode(CodeInfo info) {
		info.setJuju(StringUtils.nvlStr(info.getJuju(),""));
		info.setTel(StringUtils.nvlStr(info.getTel(),""));
		info.setTel1(StringUtils.nvlStr(info.getTel1(),""));
		info.setTel2(StringUtils.nvlStr(info.getTel2(),""));
		info.setTel3(StringUtils.nvlStr(info.getTel3(),""));
		info.setHoldShare(StringUtils.nvlStr(info.getHoldShare(),""));
		info.setInfoTel(StringUtils.nvlStr(info.getInfoTel(),""));
		info.setInfoTel1(StringUtils.nvlStr(info.getInfoTel1(),""));
		info.setInfoTel2(StringUtils.nvlStr(info.getInfoTel2(),""));
		info.setInfoTel3(StringUtils.nvlStr(info.getInfoTel3(),""));
		return info;
	}
	
}
