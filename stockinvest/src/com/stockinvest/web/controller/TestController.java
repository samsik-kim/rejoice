package com.stockinvest.web.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tframe.common.configuration.ConfigurationService;

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
		System.out.println("#################");
		System.out.println(content);
		System.out.println("#################");
		return mav;
	}
	
	/**
	 * 파일 업로드 (이미지, 플래쉬)
	 * @param request
	 * @return 파일 업로드 수행 결과
	 */
	@RequestMapping("/display/banner/saveBannerFile.do")
	public ModelAndView saveBannerFile(HttpServletRequest request) throws Exception {		
		
		String savePath = config.getString("upload.img.display.bnr.path");
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
}
