package com.stockinvest.web.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tframe.common.configuration.ConfigurationService;
import tframe.common.util.StringUtils;

import com.stockinvest.common.util.FileUpload;

@Controller
public class TestController {

	Log logger = LogFactory.getLog(getClass());
	
	@Resource(name="configurationService")
	ConfigurationService config;
	
	@RequestMapping("/test/test.do")
	public String testForm(){
		return "board/test";
	}
	
	@RequestMapping("/test/edit.do")
	public ModelAndView testEdit(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		
		String content = request.getParameter("CONTENT");
		String dir = config.getString("uploadTempDir");
		System.out.println("#################");
		System.out.println(content);
		System.out.println("#################");
		System.out.println(dir);
		return mav;
	}
	
	/**
	 * 파일 업로드 (이미지, 플래쉬)
	 * @param request
	 * @return 파일 업로드 수행 결과
	 */
	@RequestMapping("/display/banner/saveBannerFile.do")
	public ModelAndView saveBannerFile(HttpServletRequest request) throws Exception {		
		
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

		ModelAndView mav = new ModelAndView("display/banner/bannerFileUploadResultPage");
		
		if ("00".equals(resultCode)) {
			mav.addObject("result_code", "SUCCESS");
			mav.addObject("fileName", fileRealName);
			mav.addObject("uploadFileNo", request.getParameter("uploadFileNo"));
		} else {
			mav.addObject("result_code", "FAIL");
			mav.addObject("result_msg", resultMessage);
			mav.addObject("uploadFileNo", request.getParameter("uploadFileNo"));
		}
		
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
	@RequestMapping("/display/fileDownload.do")
	public ModelAndView fileDown(HttpServletRequest request, HttpServletResponse response){
		String fileType = StringUtils.nvlStr(request.getParameter("fileType"), "xls");
		String fileName = StringUtils.nvlStr(request.getParameter("fileName"), "");
		//파일의 경로 지정
		String filePath = config.getString("upload.img.dir"); 
		filePath += config.getString("upload.file.display.event." + fileType + ".path") + "/";	
		
		ModelAndView mav =new ModelAndView();
		if(!"".equals(fileName)){	
			//파일 객체에 다운받을 파일의 경로와 파일의 이름을 넣어서 생성
			File downFile = new File(filePath,fileName);	
			mav.setViewName("fileDownload");	
			mav.addObject("downloadFile", downFile);
			mav.addObject("fileName", fileName);	
			
			if(logger.isDebugEnabled()){
				logger.debug(">>>File DownLoad :  " + fileName + " : " + downFile.getPath() );
			}	
		}
		/*
		 * JSP Script
		 */
//		//파일 다운로드
//		function fileDown(fileName){
//			location.href = PageVariable.URL.FILEDOWNLOAD + "?fileType=xls&fileName=" + fileName;
//		}	
		return mav;
	}
}
