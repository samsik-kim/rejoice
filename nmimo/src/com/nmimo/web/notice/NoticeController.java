package com.nmimo.web.notice;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nmimo.common.info.PageInfo;
import com.nmimo.common.util.StringUtils;
import com.nmimo.data.notice.info.NoticeInfo;
import com.nmimo.service.notice.NoticeService;

/**
 * <pre>
 * 공지사항 Controller
 * </pre>
 * @file NoticeController.java
 * @since 2013. 5. 3.
 * @author Leesh
 */
@Controller
public class NoticeController {

	/** LOG4J */
	Log logger = LogFactory.getLog(getClass());

	@Autowired
	NoticeService noticeService;
	
	/**
	 * <pre>
	 * 공지사항 list
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/notice/list.do")
	public ModelAndView noticeList(HttpServletRequest request, @ModelAttribute NoticeInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[Notice] Controller -> noticeList ");
		}

		return new ModelAndView("notice/list");
	}

	/**
	 * <pre>
	 * 공지사항 InnerList
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/notice/ajaxlistInner.do")
	public ModelAndView noticeListInner(HttpServletRequest request, @ModelAttribute NoticeInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[Notice] Controller -> noticeListInner ");
		}

		int currentPage = Integer.parseInt(StringUtils.nvlStr(request.getParameter("pageIndex"), "1")); //현재 페이지 위치
		int pageUnit = 10; // 페이지를 보여줄 개수
		int pageSize = 10; // 한 페이지에 보여줄 게시물 수 
		
		PageInfo pageInfo = new PageInfo(request, currentPage, pageUnit, pageSize);
		ModelAndView mav = new ModelAndView();

		pageInfo = noticeService.selectNoticeList(pageInfo, dbParams);
		
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("notice/inc/listInner");
		
		return mav;
	}
	
	
	/**
	 * <pre>
	 * 공지사항 등록Form
	 * </pre>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/notice/regForm.do")
	public ModelAndView noticeRegister()throws Exception{
		return new ModelAndView("notice/regForm");
	}
	
	
//	/**
//	 * <pre>
//	 * 공지사항 등록
//	 * </pre>
//	 * @param request
//	 * @param response
//	 * @param command
//	 * @param errors
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping("/notice/regAction.do")
//	public ModelAndView noticeRegAction(HttpServletRequest request,
//			HttpServletResponse response, Object command, BindException errors) throws Exception {
//		
//		ModelAndView mav = new ModelAndView();
//		
//		Vector vector;
//		FileUpload uploader = new FileUpload();
////		ArrayList<HashMap> hashMaps = uploader.upload(request, "temp", "d:");
//		
//		//임시 경로 Property 작성후 삭제
//		StringBuffer fullPath = new StringBuffer();
//		
//		for(int i=0 ; i < hashMaps.size() ; i++) {
//			
//			HashMap<String,String> map = hashMaps.get(i);
//			
//			fullPath.setLength(0);
//			fullPath.append("d:\\");
//			fullPath.append("temp\\");
//			fullPath.append(map.get("fileRealName"));
//			
//			logger.debug("파일확장자 체크-> "+ map.get("fileExt"));
			
//			if("txt".equals(map.get("fileExt"))) {
//				
//				
//				vector = TxtReaderUtil.simpleTxtReadPoi(fullPath.toString());
//				ArrayList<SendListParseInfo> txtInfo = SendListParseUtil.getVectorToVo(vector);
//				
//				
//			} else if("xls".equals(map.get("fileExt"))) {
//				vector = ExcelReaderUtil.simpleExcelReadPoi(new File(fullPath.toString()));
//				ArrayList<SendListParseInfo> xlsInfo = SendListParseUtil.getVectorToVo(vector);
//			} else if("xlsx".equals(map.get("fileExt"))) {
//				vector = ExcelReaderUtil.simpleExcelReadPoi(new File(fullPath.toString()));
//				ArrayList<SendListParseInfo> xlsxInfo = SendListParseUtil.getVectorToVo(vector);
//			}
//		}
//		return new ModelAndView("sample/fileUploadForm");
//	}
	
	
	/**
	 * <pre>
	 * 상세
	 * </pre>
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/notice/detail.do")
	public ModelAndView detail(HttpServletRequest request, @ModelAttribute NoticeInfo dbParams){
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("info", noticeService.selectNoticeDetail(Integer.parseInt(dbParams.getNtfSeq())));
		
		modelAndView.setViewName("notice/detail");
		
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * 수정 폼
	 * </pre>
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/notice/editForm.do")
	public ModelAndView editForm(HttpServletRequest request, @ModelAttribute NoticeInfo dbParams){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("info", noticeService.selectNoticeDetail(Integer.parseInt(dbParams.getNtfSeq())));
		modelAndView.setViewName("notice/editForm");
		
		return modelAndView;
	}
}
