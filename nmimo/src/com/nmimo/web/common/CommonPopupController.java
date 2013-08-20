package com.nmimo.web.common;

import java.io.File;
import java.io.IOException;
import java.net.BindException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nmimo.common.ServiceConstants;
import com.nmimo.common.configuration.ConfigurationService;
import com.nmimo.common.info.FileReaderInfo;
import com.nmimo.common.util.FileReaderUtil;
import com.nmimo.common.util.FileUpload;
import com.nmimo.common.util.RequestMap;
import com.nmimo.common.util.StringUtils;
import com.nmimo.data.message.info.MessageInfo;


@Controller
public class CommonPopupController {

	/** LOG4J */
	Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private ConfigurationService config;

	
	/**
	 * <pre>
	 * 달력 팝업
	 * </pre>
	 * @param request
	 * @return
	 */
	@RequestMapping("/common/popup/calendarPop.do")
	public ModelAndView calendarPop(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("common/popup/calendarPop");
		
		return mav;
	}
	

	
	/**
	 * <pre>
	 * 달력 팝업
	 * </pre>
	 * @param request
	 * @return
	 */
	@RequestMapping("/common/popup/newCalendarPop.do")
	public ModelAndView newCalendarPop(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("common/popup/newCalendarPop");
		
		RequestMap.getMapping(request);
		
		return mav;
	}
	
	
	/**
	 * <pre>
	 * 발송 승인자
	 * </pre>
	 * @param request
	 * @return
	 */
	@RequestMapping("/common/popup/searchApproverPop.do")
	public ModelAndView approverPop(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();	
		mav.setViewName("common/popup/searchApproverPop");
		
		return mav;
	}
	
	/**
	 * <pre>
	 * 보관함 팝업
	 * </pre>
	 * @param request
	 * @return
	 */
	@RequestMapping("/common/popup/myLibraryPop.do")
	public ModelAndView myLibraryPop(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/common/popup/myLibraryPop");
		
		return mav;
	}
	
	/**
	 * <pre>
	 * DOC 파일 업로드
	 * </pre>
	 * @param request
	 * @return
	 */
	@RequestMapping("/common/popup/docFileUploadPop.do")
	public ModelAndView fileUploadPop(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/common/popup/docFileUploadPop");
		
		return mav;
	}
	
	
	/**
	 * <pre>
	 * DOC 파일업로드 Action
	 * </pre>
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws WriteException 
	 * @throws BiffException 
	 * @throws RowsExceededException 
	 * @throws JSONException 
	 */
	@RequestMapping("/common/popup/ajaxDocFileUploadAction.do")
	public ModelAndView docfileUploadAction(HttpServletRequest request,HttpServletResponse response, Object command, BindException errors) throws RowsExceededException, BiffException, WriteException, IOException, Exception{
		
		if (logger.isDebugEnabled()) {
			logger.debug("[CommonPopupController - ajaxDocFileUploadAction]");
		}

		JSONObject jsonObject = new JSONObject();
		ModelAndView mav = new ModelAndView("jsonView");
		FileUpload uploader = new FileUpload();
		HashMap<String, String> resultMap =  new HashMap();
		
		ArrayList<HashMap> hashMaps = uploader.upload(request, config.getString("upload.nas.web.path")+config.getString("msg.doc.tmp.path"),config.getString("upload.nas.web.path")+config.getString("msg.doc.new.path"),"D");
		
		for(int i=0 ; i < hashMaps.size() ; i++) {
			
			HashMap<String,String> map = hashMaps.get(i);
			
			//파일 업로드 성공시
			if("S".equals(map.get("resultCode"))){

				//파일 중복제거 처리	
				resultMap = FileReaderUtil.getData(map.get("fileFullPath"),map.get("newPathDir"));
				
				if("S" == resultMap.get("resultCode")){
					//폰번호 중복검사 처리후  Set
					jsonObject.put("fileOrgName", map.get("fileOrgName"));
					jsonObject.put("fileRealName", resultMap.get("fileRealName"));
					jsonObject.put("tCnt", Integer.parseInt(resultMap.get("tCnt")));
					jsonObject.put("sCnt", Integer.parseInt(resultMap.get("sCnt")));
					jsonObject.put("fCnt", Integer.parseInt(resultMap.get("fCnt")));
					jsonObject.put("resultCode", "S");
					jsonObject.put("resultMsg", "[정상] 정상적으로 승인요청 되었습니다. ");
				}else{
					jsonObject.put("resultCode", "F");
					jsonObject.put("resultMsg", "[ERROR] 파일 업로드중 오류가 발생했습니다.");
				}
			}else{
				jsonObject.put("resultCode", "F");
				jsonObject.put("resultMsg", "[ERROR] 파일 업로드중 오류가 발생했습니다.");
			}
		}
		mav.addObject("jsonObject", jsonObject);
		return mav;
	}
	

	/**
	 * <pre>
	 * DOC 파일업로드 Result 화면
	 * </pre>
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/common/popup/docFileUploadResultPop.do")
	public ModelAndView docFileUploadResultPop(HttpServletRequest request)throws Exception{
		
		ModelAndView mav = new ModelAndView();
		FileReaderInfo fileInfo = new FileReaderInfo();
		
		RequestMap.getMapping(request);

		fileInfo.setFileOrgName(StringUtils.nvlStr(request.getParameter("fileOrgName")));
		fileInfo.setFileRealName(StringUtils.nvlStr(request.getParameter("fileRealName")));
		fileInfo.settCnt(Integer.parseInt(StringUtils.nvlStr(request.getParameter("tCnt"))));
		fileInfo.setsCnt(Integer.parseInt(StringUtils.nvlStr(request.getParameter("sCnt"))));
		fileInfo.setfCnt(Integer.parseInt(StringUtils.nvlStr(request.getParameter("fCnt"))));
		
		mav.addObject("fileInfo",fileInfo);
		mav.setViewName("/common/popup/docFileUploadResultPop");
		
		return mav;
	}
	
	
	/**
	 * <pre>
	 * FileDownload 실행
	 * </pre>
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/common/popup/fileDownload.do")
	public ModelAndView fileDownload(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView mav = new ModelAndView("fileDownload");

		String fileName = StringUtils.nvlStr(request.getParameter("fileName"), "");
		String tmp1="";
		String tmp2="";
		String datePath="";
		
		//파일업로드 경로 ( Default 경로/YYYY/MM )
		if(!"".equals(fileName)){

			String str[] = fileName.split("_");
			if(str.length > 0){
				tmp1 = str[1].substring(0,4);
				tmp2 = str[1].substring(4,6);
				datePath = tmp1+File.separator+tmp2+File.separator;
				String filePath = config.getString("upload.file.path.doc")+datePath;

				//파일 객체에 다운받을 파일의 경로와 파일의 이름을 넣어서 생성
				File downFile = new File(filePath,fileName);
				mav.addObject("downloadFile", downFile);
				mav.addObject("fileName", fileName);
				mav.addObject("charset", ServiceConstants.Common.CHARSET_UTF_8);
			}
		}
		
		return mav;
	}
	
	
	/**
	 * <pre>
	 * 메세지등록 >> 이미지등록 Form
	 * </pre>
	 * @param request
	 * @return
	 */
	@RequestMapping("/common/popup/imgFileUploadPop.do")
	public ModelAndView imgFileUploadPop(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/common/popup/imgFileUploadPop");
		
		return mav;
	}

	/**
	 * <pre>
	 * 메세지등록 >> 동영상등록 Form
	 * </pre>
	 * @param request
	 * @return
	 */
	@RequestMapping("/common/popup/movFileUploadPop.do")
	public ModelAndView movFileUploadPop(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/common/popup/movFileUploadPop");
		
		return mav;
	}

	/**
	 * <pre>
	 * 메세지등록 >> 이미지등록 Action 
	 * </pre>
	 * @param request
	 * @return
	 * @throws JSONException 
	 */
	@RequestMapping("/common/popup/ajaxMultiFileUploadResult.do")
	public ModelAndView multiFileUploadResult(HttpServletRequest request) throws JSONException{

		JSONObject jsonObject = new JSONObject();
		ModelAndView mav = new ModelAndView("jsonView");
		FileUpload uploader = new FileUpload();
		
		ArrayList<HashMap> hashMaps = uploader.upload(request, config.getString("upload.bodymessage.path"),"","M");

		for(int i=0 ; i < hashMaps.size() ; i++) {
			HashMap<String,String> map = hashMaps.get(i);
			
			if("S".equals(map.get("resultCode"))){
				jsonObject.put("resultCode", "S");
				jsonObject.put("resultMsg", "정상적으로 업로드 되었습니다.");
				jsonObject.put("fileRealName", map.get("fileRealName"));
			}else{
				jsonObject.put("resultCode", "F");
				jsonObject.put("resultMsg", "[ERROR] 재등록 해주시기 바랍니다.");
			}
		}
		
	    mav.addObject("jsonObject", jsonObject);
		
		return mav;
	}
	
	
	/**
	 * <pre>
	 * Message 미리보기 팝업
	 * </pre>
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("common/popup/previewPop.do")
	public ModelAndView previewPop(HttpServletRequest request, @ModelAttribute MessageInfo dbParams)throws Exception{
	
		ModelAndView mav = new ModelAndView();
		mav.setViewName("common/popup/previewPop");
		
		RequestMap.getMapping(request);
	
		
		return mav;
	} 
	
	
	/**
	 * <pre>
	 * Message 파일다운
	 * </pre> 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("common/msgFiledown.do")
	public ModelAndView msgFiledown(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView("fileDownload");
		String fileName = StringUtils.nvlStr(request.getParameter("tgtrFileNm"), "");
		
		if(!"".equals(fileName)){
			String fileYear=fileName.substring(0,4);
			String fileMonth=fileName.substring(4,6);
			String fileDay=fileName.substring(6,8);
			
			//파일의 전체경로 지정
			String filePath = config.getString("upload.nas.share.path")+fileYear+File.separator+fileMonth+File.separator+fileDay+File.separator;
			
			//파일 객체에 다운받을 파일의 경로와 파일의 이름을 넣어서 생성
			File downFile = new File(filePath,fileName);
			mav.addObject("downloadFile", downFile);
			mav.addObject("fileName", fileName);
			mav.addObject("charset", ServiceConstants.Common.CHARSET_UTF_8);
		}
		
		return mav;
	}	
}