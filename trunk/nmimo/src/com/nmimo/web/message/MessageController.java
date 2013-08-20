package com.nmimo.web.message;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nmimo.common.configuration.ConfigurationService;
import com.nmimo.common.exception.NMimoException;
import com.nmimo.common.util.BodyCreateUtil;
import com.nmimo.common.util.RequestMap;
import com.nmimo.common.util.StringUtils;
import com.nmimo.data.message.info.MessageInfo;
import com.nmimo.service.message.MessageService;
import com.nmimo.service.user.UserService;

/**
 * <pre>
 * 발송등록
 * </pre>
 * @file MessageController.java
 * @since 2013. 4. 15.
 * @author Leesh
 */
/**
 * <pre>
 *
 * </pre>
 * @file MessageController.java
 * @since 2013. 8. 8.
 * @author Administrator
 */
@Controller
public class MessageController {

	/** LOG4J */
	Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private ConfigurationService config;

	@Autowired
	MessageService messageService;
	
	
	/**
	 * <pre>
	 * 발송등록 >> 공지/홍보 >> 등록
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/message/msgRegForm.do")
	public ModelAndView messageRegForm(HttpServletRequest request, @ModelAttribute MessageInfo dbParams)throws Exception{
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("message/msgRegForm");

		System.err.println("^^^^^^^ msgRegForm Action START ^^^^^^^");
		RequestMap.getMapping(request);
		System.err.println("^^^^^^^ msgRegForm Action END ^^^^^^^");
		
		return mav;
	} 
	
	
	/**
	 * <pre>
	 * 발송등록 >> 자동안내 >> 등록
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/message/autoMsgRegForm.do")
	public ModelAndView messageAutoRegForm(HttpServletRequest request, @ModelAttribute MessageInfo dbParams)throws Exception{
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("message/autoMsgRegForm");
		
		return mav;
	} 
	
	
	/**
	 * <pre>
	 * 발송등록 >> 파일업로드 >> 도움말
	 * </pre>
	 * @param request
	 * @return
	 */
	@RequestMapping("/message/popup/helpFileuploadPop.do")
	public ModelAndView messageHelpFileuploadPop(HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("message/popup/helpFileUploadPop");
									   
		return mav;
				
	}
	

	/**
	 * <pre>
	 * 메세지등록 승인요청 (body생성 및 Socket연동)  
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/message/ajaxCreateBodyHtml.do")
	public ModelAndView messageCreateBodyHtml(HttpServletRequest request, @ModelAttribute MessageInfo dbParams)throws Exception{

		JSONObject jsonObject = new JSONObject();
		ModelAndView mav = new ModelAndView("jsonView");
		RequestMap.getMapping(request);
		
		//4대 검토 확인사항(checkBox)처리
		String[] reviewMtrVals = request.getParameterValues("reviewMtrVal");
		String tmpReviewMtrVal=""; 
		for ( int i = 0; i < reviewMtrVals.length;i++ ) {
			tmpReviewMtrVal+=reviewMtrVals[i]+",";
		 }
		dbParams.setReviewMtrVal(tmpReviewMtrVal);
		dbParams.setMsgSbst(dbParams.getMsgSbstHTML());
		
		// 구분자 '&'으로 Multi파일명 배열처리
		if(!"".equals(dbParams.getArrMultiFileName())){
			String arrMultiFileName = dbParams.getArrMultiFileName();
			String[] arrMulti = arrMultiFileName.split("&");
			dbParams.setArrMulti(arrMulti);
		}
		
		try {
			
			//DB Insert처리
			int result=0;
			result = messageService.insertMsgInfo(dbParams);
			
			//Insert 결과(정상)
			if(result > 0){
				
				//파일생성 (HTM, INFO, Multi File업로드)
				HashMap<String,String> resultMap = new HashMap<String,String>();
				resultMap = BodyCreateUtil.getBodyHTM(dbParams,config.getString("upload.nas.share.path"),config.getString("upload.bodymessage.path"));
				
				if("S".equals(resultMap.get("SUCCESS"))){
					//TODO
					//소켓연동 Engine 구현해야함

					jsonObject.put("resultCode", "S");
					jsonObject.put("resultMsg", "[정상] 정상적으로 승인요청 되었습니다. ");
				}else{
					jsonObject.put("resultCode", "F");
					jsonObject.put("resultMsg", "[ERROR] 처리중 오류가 발생했습니다.(HTM생성)");
				}
				
			}else{
				jsonObject.put("resultCode", "F");
				jsonObject.put("resultMsg", "[ERROR] 처리중 오류가 발생했습니다. (DB처리)");
			}
		} catch (Exception e) {
			logger.error("ERROR ajaxCreateBodyHtml");
			
			jsonObject.put("resultCode", "F");
			jsonObject.put("resultMsg", "[ERROR] 처리중 오류가 발생했습니다. (catch처리)");
		}
		
	    mav.addObject("jsonObject", jsonObject);
		
		return mav;
	}
	
	
	/**
	 * <pre>
	 * 자동 메세지등록 승인요청 (body생성 및 Socket연동)
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/message/ajaxCreateBodyHtmlAuto.do")
	public ModelAndView messageCreateBodyHtmlAuto(HttpServletRequest request, @ModelAttribute MessageInfo dbParams)throws Exception{

		JSONObject jsonObject = new JSONObject();
		ModelAndView mav = new ModelAndView("jsonView");
		RequestMap.getMapping(request);
		
		dbParams.setMsgSbst(dbParams.getMsgSbstHTML());
		
		// 구분자 '&'으로 Multi파일명 배열처리
		if(!"".equals(dbParams.getArrMultiFileName())){
			String arrMultiFileName = dbParams.getArrMultiFileName();
			String[] arrMulti = arrMultiFileName.split("&");
			dbParams.setArrMulti(arrMulti);
		}
		
		
		
		try {
			
			//DB Insert처리
			int result=0;
			result = messageService.insertAutoMsgInfo(dbParams);
			
			//Insert 결과(정상)
			if(result > 0){
				
				//파일생성 (HTM, INFO, Multi File업로드)
				HashMap<String,String> resultMap = new HashMap<String,String>();
				resultMap = BodyCreateUtil.getBodyHTM(dbParams,config.getString("upload.nas.share.path"),config.getString("upload.bodymessage.path"));
				
				if("S".equals(resultMap.get("SUCCESS"))){
					jsonObject.put("resultCode", "S");
					jsonObject.put("resultMsg", "[정상] 정상적으로 승인요청 되었습니다. ");
				}else{
					jsonObject.put("resultCode", "F");
					jsonObject.put("resultMsg", "[ERROR] 처리중 오류가 발생했습니다.(HTM생성)");
				}
				
			}else{
				jsonObject.put("resultCode", "F");
				jsonObject.put("resultMsg", "[ERROR] 처리중 오류가 발생했습니다. (DB처리)");
			}
		} catch (Exception e) {
			logger.error("ERROR ajaxCreateBodyHtml");
			
			jsonObject.put("resultCode", "F");
			jsonObject.put("resultMsg", "[ERROR] 처리중 오류가 발생했습니다. (catch처리)");
		}
		
	    mav.addObject("jsonObject", jsonObject);
		
		return mav;
	}
	
	
	
	/**
	 * <pre>
	 * 임시저장 처리
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/message/ajaxTempSaveBodyHtml.do")
	public ModelAndView messageTempsaveBodyHtml(HttpServletRequest request, @ModelAttribute MessageInfo dbParams)throws Exception{
		
		JSONObject jsonObject = new JSONObject();
		ModelAndView mav = new ModelAndView("jsonObject");
		
		RequestMap.getMapping(request);
		
		//4대 검토 확인사항(checkBox)처리
		String[] reviewMtrVals = request.getParameterValues("reviewMtrVal");
		String tmpReviewMtrVal=""; 
		for ( int i = 0; i < reviewMtrVals.length;i++ ) {
			tmpReviewMtrVal+=reviewMtrVals[i]+",";
		 }
		dbParams.setReviewMtrVal(tmpReviewMtrVal);
		
		// 구분자 '&'으로 Multi파일명 배열처리
		if(!"".equals(dbParams.getArrMultiFileName())){
			String arrMultiFileName = dbParams.getArrMultiFileName();
			String[] arrMulti = arrMultiFileName.split("&");
			dbParams.setArrMulti(arrMulti);
		}
		
		try {

			//TODO
			//소켓연동 Engine 구현해야함

			HashMap<String,String> resultMap = new HashMap<String, String>();
			
			if("S".equals(resultMap.get("SUCCESS"))){

				jsonObject.put("resultCode", "S");
				jsonObject.put("resultMsg", "[정상] 정상적으로 승인요청 되었습니다. ");
			}else{
				jsonObject.put("resultCode", "F");
				jsonObject.put("resultMsg", "[ERROR] 처리중 오류가 발생했습니다.");
			}
			
		} catch (Exception e) {
			logger.error("ERROR ajaxCreateBodyHtml");
			
			jsonObject.put("resultCode", "F");
			jsonObject.put("resultMsg", "[ERROR] 처리중 오류가 발생했습니다.");
			//throw new NMimoException("ERROR ajaxCreateBodyHtml");
		}
		
		mav.addObject("jsonObject",jsonObject);
		return mav;
	}
	
	
	
	/**
	 * <pre>
	 * 사전예약번호 입력시 DB조회
	 * </pre>
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/message/ajaxReservationChkResult.do")
	public ModelAndView messageReservationChkResult(HttpServletRequest request)throws Exception{
		
		JSONObject jsonObject = new JSONObject();
		ModelAndView mav = new ModelAndView("jsonView");
		
		//TODO
		//DB조회 (사전예약번호 Data 존재여부 : TABLE명 BFAC_REG_BAS)
		String tmpNum = StringUtils.nvlStr(request.getParameter("bfac_reg_seq"));
		String result="";
		if("1".equals(tmpNum) || "".equals(tmpNum.trim())){
			result = "F";	
		}else{
			result = "S";
		}
		
		if("F".equals(result)){
			jsonObject.put("resultCode", "F");
			jsonObject.put("resultMsg", "[사용불가] 존재하지 않는 사전예약번호 입니다. ");
		}else{
			jsonObject.put("resultCode", "S");
			jsonObject.put("resultMsg", "[정상] 승인번호가 확인 되었습니다. ");
		}
		
	    mav.addObject("jsonObject", jsonObject);
		
		return mav;
	} 
	
	
	
	/**
	 * <pre>
	 * 배너리스트 호출 ( MMS 선택시 > 배너 삽입 > 사용 선택시)
	 * </pre>
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/message/ajaxBannerResult.do")
	public ModelAndView messageBannerResult(HttpServletRequest request)throws Exception{
		
		JSONObject jsonObject = new JSONObject();
		ModelAndView mav = new ModelAndView("jsonView");

		//DB조회 (bannerList Data 존재여부)
		String result = "F";
		
		if("F".equals(result)){
			jsonObject.put("resultCode", "F");
			jsonObject.put("resultMsg", "[사용불가] 등록된 배너정보가 없습니다. ");
		}else{
			jsonObject.put("resultCode", "S");
			jsonObject.put("resultMsg", "배너정보 확인 ");
		}
		
	    mav.addObject("jsonObject", jsonObject);
		
		return mav;
	}
	
	
	
	
	/**
	 * <pre>
	 * sample view
	 * </pre>
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/message/msgDetail.do")
	public ModelAndView msgDetail (HttpServletRequest request)throws Exception{
		
		ModelAndView mav = new ModelAndView();
		MessageInfo viewInfo = new MessageInfo();
		
		mav.addObject("viewInfo",viewInfo);
		mav.setViewName("/message/msgDetail");

		return mav;
	}
	
	/**
	 * <pre>
	 * sample autoView
	 * </pre>
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/message/autoMsgDetail.do")
	public ModelAndView autoMsgDetail(HttpServletRequest request)throws Exception{
		
		ModelAndView mav = new ModelAndView();
		MessageInfo viewInfo = new MessageInfo();
		
		mav.addObject("viewInfo",viewInfo);
		mav.setViewName("/message/autoMsgDetail");

		return mav;
	}
	
	
}
