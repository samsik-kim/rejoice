package com.nmimo.web.sample;

import java.io.File;
import java.net.BindException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nmimo.common.ServiceConstants;
import com.nmimo.common.configuration.ConfigurationService;
import com.nmimo.common.util.FileReaderUtil;
import com.nmimo.common.util.FileUpload;
import com.nmimo.common.util.RandomString;
import com.nmimo.common.util.StringUtils;
import com.nmimo.service.mypage.MyWorkService;
import com.nmimo.service.user.UserService;




/**
 * <pre>
 *
 * </pre>
 * @file SampleController.java
 * @since 2013. 7. 31.
 * @author Rejoice
 */
@Controller
public class SampleController {

	/** LOG4J */
	Log logger = LogFactory.getLog(getClass());

	@Autowired
	private MessageSourceAccessor message;
	
	@Autowired
	private ConfigurationService config;
	
	@Autowired
	UserService service;
	
	@Autowired
	MyWorkService myWorkService;
	
	@RequestMapping(value="/sampleJs.do")
	public String sampleJs(HttpServletRequest request) throws SQLException{
		Locale locale=request.getLocale(); //받아오는 국가
		message.getMessage("crm.result.msg.400");
//		logger.debug(message.getMessage("crm.result.msg.400", locale));
//		String a= null;
//		try {
//			message.getMessage("crm.result.msg.123123");
//			if("A".equals(message.getMessage("crm.result.msg.123123"))){
//				logger.debug(StringUtils.nvlStr(a));
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			throw new NMimoException("Test NMimoSQLException");
//		}
		
		String str = "test";
		String rStr = RandomString.getString(64);
//		String aes = JKTFCryptoUtil.encryptOnlyData(JKTFCryptoUtil.CIPHER_AES_128, RandomString.getString(64));
		String temp = "";
		
//		KTFCryptoUtil kt = new KTFCryptoUtil();
//		kt.setCipherAlgorithm(4);
//		logger.debug("##################################");
//		logger.debug("rStr ==> " + rStr);
//		logger.debug("AES128 ==> " + kt.encryptData(rStr));
//		temp = kt.encryptData(rStr);
//		kt.setHashAlgorithm(0x0200);
//		logger.debug("hash ==> " + kt.hash(temp+"|"+str));
//		logger.debug("##################################");
		
		StringBuffer sb = new StringBuffer();
		sb.append("X-nMimo-Employee-Account: 12345678");
		sb.append("<CRLF>");
		sb.append("X-nMimo-Job-ID: 20130402000001");
		sb.append("<CRLF>");
		sb.append("X-nMimo-Status-Code: 10000");
		sb.append("<CRLF>");
		
		//TransLog.getInstance().write(getClass().getSimpleName(), "sampleJs", message.getMessage(ServiceConstants.Common.TRANS_MODE_REQ), TransLog.getInstance().sendDataAnalysis(sb.toString()));
		
		//selectTest()
//		logger.debug(service.selectTest());
//		System.out.println(myWorkService.testSelect());
//		service.selectUserLoginChk(null);
		return "sampleJs";
	}
	
	
	/**
	 * <pre>
	 * Sample Ajax
	 * </pre>
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/sample/ajaxSample.do")
	public ModelAndView sampleForm(HttpServletRequest request) throws Exception{
		
		JSONObject jsonObject = new JSONObject();
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		//DEBUG
		logger.debug("[Ajax Sample DATA]");
		logger.debug("abc==> " + request.getParameter("abc"));
		logger.debug("a==> " + request.getParameter("a"));
		logger.debug("b==> " + request.getParameter("b"));
		logger.debug("c==> " + request.getParameter("c"));
		
		jsonObject.put("result", "SS");
	    modelAndView.addObject("jsonObject", jsonObject);
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * Test File Upload -> Excel DATA -> Vector
	 * </pre>
	 * 
	 * @param request
	 * @param response
	 * @param multipartFile
	 * @param command
	 * @param errors
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/sample/fileUploadAction.do")
	public ModelAndView sampleSimpleExcelPOI(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors) throws Exception {
		
		System.err.println("###### 파일업로드 START ######");
		
		Vector vector;
		FileUpload uploader = new FileUpload();
		ArrayList<HashMap> hashMaps = uploader.upload(request, config.getString("upload.file.path.temp"),"D","D");
		
		HashMap<String,String> dataRows = new HashMap();
		
		//임시 경로 Property 작성후 삭제
		StringBuffer fileFullPath = new StringBuffer();
		
		for(int i=0 ; i < hashMaps.size() ; i++) {
			
			HashMap<String,String> map = hashMaps.get(i);
			
			fileFullPath.setLength(0);
			fileFullPath.append(config.getString("upload.file.path.temp"));
			fileFullPath.append(map.get("fileRealName"));
			
			dataRows = FileReaderUtil.getData(fileFullPath.toString(),config.getString("upload.file.path"));	

			if(dataRows.size() > 0){
				System.err.println("Success");
				System.err.println("dataRows => "+dataRows.size());
			}else{
				System.err.println("Fail");
			}

			System.err.println("###### 파일업로드 END ######");
		}
		return new ModelAndView("sampleJs");
	}
	
	@RequestMapping("sample/filedown.do")
	public ModelAndView fileDownload(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView("fileDownload");
		String fileName = StringUtils.nvlStr(request.getParameter("fileName"), "");
		//파일의 경로 지정
		String filePath = config.getString("upload.img.dir");
		//@TODO
		filePath += config.getString("???");

		if(!"".equals(fileName)){
			//파일 객체에 다운받을 파일의 경로와 파일의 이름을 넣어서 생성
			File downFile = new File(filePath,fileName);
			mav.addObject("downloadFile", downFile);
			mav.addObject("fileName", fileName);
			mav.addObject("charset", ServiceConstants.Common.CHARSET_UTF_8);
		}
		return mav;
	}
	
}