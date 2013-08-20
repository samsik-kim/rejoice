package com.nmimo.web.crm;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nmimo.common.ServiceConstants;
import com.nmimo.common.info.PageInfo;
import com.nmimo.common.info.SessionInfo;
import com.nmimo.common.session.SessionHandler;
import com.nmimo.common.util.StringUtils;
import com.nmimo.data.crm.info.CrmInfo;
import com.nmimo.data.mypage.info.MyWorkInfo;
import com.nmimo.data.user.info.UserInfo;
import com.nmimo.service.crm.CrmService;
import com.nmimo.service.user.UserService;

@Controller
public class CrmContorller {

	/** LOG4J */
	Log logger = LogFactory.getLog(getClass());
	
	/** Message 관련 */
	@Autowired
	MessageSourceAccessor message;
	
	@Autowired
	CrmService crmService; 
	
	@Autowired
	UserService userService;
	
	/**
	 * <pre>
	 * 외부연동 PSSO연동
	 * </pre>
	 * @param request
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value="/crm/campaign.do")
	public ModelAndView crmCampaign(HttpServletRequest request) throws SQLException{
		
		return new ModelAndView("/crm/result");
	}
	
	/**
	 * <pre>
	 * 외부연동 PSSO연동
	 * </pre>
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/crm/pssoChk.do")
	public ModelAndView etcCrmPssoChk(HttpServletRequest request,HttpServletResponse response)throws Exception {
		
		return new ModelAndView("/crm/result");
	}
	
	
	/**
	 * <pre>
	 * 외부연동 PSSO연동
	 * </pre>
	 * @param request
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value="/crm/result.do")
	public ModelAndView crmResult(HttpServletRequest request) throws SQLException{
		
		return new ModelAndView("/crm/result");
	}
	
	/**
	 * <pre>
	 * 외부연동 CRM 메인
	 * </pre>
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/crm/list.do")
	public ModelAndView etcCrmList(HttpServletRequest request,HttpServletResponse response)throws Exception {
		
		if (logger.isDebugEnabled()) {
			logger.debug("[CRM] Controller -> crmList");
		}
		
		return new ModelAndView("/crm/crmList"); 
		
	}
	
	
	/**
	 * <pre>
	 * 외부연동 CRM InnerList
	 * </pre>
	 * @param request
	 * @param dbParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/crm/ajaxListInner.do")
	public ModelAndView etcCrmListInner(HttpServletRequest request, @ModelAttribute CrmInfo dbParams)throws Exception{

		if (logger.isDebugEnabled()) {
			logger.debug("[CRM] Controller -> crmListInner ");
		}

		int currentPage = Integer.parseInt(StringUtils.nvlStr(request.getParameter("pageIndex"), "1")); //현재 페이지 위치
		int pageUnit = 10; // 페이지를 보여줄 개수
		int pageSize = 10; // 한 페이지에 보여줄 게시물 수 
		
		PageInfo pageInfo = new PageInfo(request, currentPage, pageUnit, pageSize);
		ModelAndView mav = new ModelAndView();

//		pageInfo = statsService.findStatsListBySearchCode(dbParams , pageInfo);
		
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("crm/inc/crmListInner");
		
		return mav;
	}
	
	/**
	 * <pre>
	 * 외부연동 CRM List 상세보기
	 * </pre>
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/crm/listDetail.do")
	public ModelAndView etcCrmListDetail(HttpServletRequest request)throws Exception{
		
		return new ModelAndView("crm/crmListDetail");
	}
	
	
	/**
	 * <pre>
	 * 외부연동 CRM 메세지등록 폼
	 * </pre>
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/crm/regMsgForm.do")
	public ModelAndView etcCrmRegMsgForm(HttpServletRequest request)throws Exception{
		
		return new ModelAndView("crm/crmRegMsgForm");
	}	
	
	
	/**
	 * <pre>
	 * 외부연동 CRM 작업신청현황 팝업 (파라미터 전송)
	 * </pre>
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/crm/requestJob.do")
	public ModelAndView etcCrmRequestJob(HttpServletRequest request) throws Exception{
		
		return new ModelAndView("crm/crmRequestJob");
	}
	
	
	/**
	 * <pre>
	 * 외부연동 CRM 작업신청현황 View 팝업
	 * </pre>
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/crm/requestJobViewPop.do")
	public ModelAndView etcCrmRequestJobViewPop(HttpServletRequest request) throws Exception{
		
		ModelAndView mav = new ModelAndView("crm/result");

		//트랜잭션ID
		String jobId = StringUtils.nvlStr(request.getParameter("jobId"));

		//트랜잭션ID 정보로 로컬DB조회
		if(!"".equals(jobId)){
			
			CrmInfo dbParams = new CrmInfo();
			CrmInfo crmInfo = new CrmInfo();
			
			dbParams.setUserId(jobId);
			crmInfo=null;
			
			//@TODO trId DB조회수정
//			etcInfo = crmService.selectEtcUserInfo(dbParams);
			
			//결과값 존재시 팝업허용
			if(crmInfo != null){
				mav.setViewName("crm/crmRequestJobViewPop");
			}else{
				mav.addObject(ServiceConstants.Common.RESULT_CODE,ServiceConstants.Crm.RESULT_CODE_FAIL);
				mav.addObject(ServiceConstants.Common.RESULT_MSG,message.getMessage(ServiceConstants.Crm.RESULT_MSG_FAIL_401));
			}
		}else{
			mav.addObject(ServiceConstants.Common.RESULT_CODE,ServiceConstants.Crm.RESULT_CODE_FAIL);
			mav.addObject(ServiceConstants.Common.RESULT_MSG,message.getMessage(ServiceConstants.Crm.RESULT_MSG_FAIL_400));
		}
		
		return mav;
	}
	
}
