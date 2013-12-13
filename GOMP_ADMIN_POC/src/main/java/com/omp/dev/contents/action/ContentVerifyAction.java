package com.omp.dev.contents.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.omp.commons.Constants;
import com.omp.commons.action.BaseAction;
import com.omp.commons.commcode.CacheCommCode;
import com.omp.commons.commcode.model.CommCode;
import com.omp.commons.exception.NoticeException;
import com.omp.commons.text.LocalizeMessage;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.SessionUtil;
import com.omp.dev.contents.model.ContentsVerify;
import com.omp.dev.contents.model.SubContentsVerify;
import com.omp.dev.contents.model.verify.ContentVerifyDetail;
import com.omp.dev.contents.model.verify.SubContentVerify;
import com.omp.dev.contents.service.ContentsVerifyService;
import com.omp.dev.contents.service.ContentsVerifyServiceImpl;
import com.omp.dev.user.model.Session;

/**
 * ContentVerifyAction Class - Developer POC Contents Verify Present
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class ContentVerifyAction extends BaseAction{
	private GLogger logger = new GLogger(ContentVerifyAction.class);
	
	private ContentsVerify ctVerify;
	private ContentVerifyDetail contentVerifyDetail;
	private ContentsVerifyService ctVerifyService;
	private List<ContentsVerify> resultList;
	private List<SubContentsVerify> subContentsVerifyList;
	private List<SubContentVerify> addFileList;
	private List<List> addFile;
	private List<CommCode> agrmntCodeList;
	private List<CommCode> verifyPrgrYnCodeList;
	private int searchCnt = 0;
	private String searchDay = "";
	private String succMsg = "";
	
	public ContentVerifyAction(){
		ctVerifyService = new ContentsVerifyServiceImpl();
	}
	
	/**
	 * Contents Verify List
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getVerifyList() {
		
		if (SessionUtil.existMemberSession(this.req)) {
			Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
			
			// default Date setting
			if(ctVerify == null) {
				ctVerify = new ContentsVerify();
				
				String[] returnDate = getDateSetting(-1, "M", "yyyyMMdd");
				
				ctVerify.setStartDate(returnDate[0]);
				ctVerify.setEndDate(returnDate[1]);
				ctVerify.setSearchAgrmntStat(new String[] {Constants.AGREEMENT_STATUS_INIT, Constants.AGREEMENT_STATUS_AGREE, Constants.AGREEMENT_STATUS_REJECT});
				ctVerify.setSearchVerifyPrgrYn(new String[] {Constants.CODE_VERIFY_REQ, Constants.CODE_VERIFY_ING, Constants.CODE_VERIFY_CANCEL, Constants.CODE_VERIFY_END});
			}
			
			if("subMain".equals(StringUtils.defaultString(this.req.getParameter("referer")))) {
				String[] returnDate = getDateSetting(-1, "M", "yyyyMMdd");
				
				ctVerify.setStartDate(returnDate[0]);
				ctVerify.setEndDate(returnDate[1]);
			}
			
			ctVerify.setInsBy(memberInfo.getMbrNo());
			
			agrmntCodeList = CacheCommCode.getCommCode("PD0050");
			verifyPrgrYnCodeList = CacheCommCode.getCommCode("PD0053");
			verifyPrgrYnCodeList = CacheCommCode.getFilteredList(verifyPrgrYnCodeList, "ph", null);

			resultList = ctVerifyService.getContentsVerifyList(ctVerify);
		}
		
		return SUCCESS;
	}
	
	/**
	 * Content Verify Detail View
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getContentVerifyDetailView() {
		
		if(ctVerify == null) {
			ctVerify = new ContentsVerify();
		}
		
		logger.info("CID  ::  {0}", new Object[] {ctVerify.getCid()});
		logger.info("VerifyReqVer  ::  {0}", new Object[] {ctVerify.getVerifyReqVer()});
		
		if (SessionUtil.existMemberSession(req)) {
			Session memberInfo = (Session) SessionUtil.getMemberSession(req);
			
			ctVerify.setInsBy(memberInfo.getMbrNo());
			
			addFile = new ArrayList<List>();
			
			contentVerifyDetail = ctVerifyService.getContentVerifyDetailViewHead(ctVerify);
			
			this.req.setAttribute("referer", this.req.getHeader("referer"));
		}
		
		return SUCCESS;
	}
	
	public String ditailVerifyStateAjax() {
		
		if(ctVerify == null) {
			ctVerify = new ContentsVerify();
		}
		
		logger.info("CID  ::  {0}", new Object[] {ctVerify.getCid()});
		logger.info("VerifyReqVer  ::  {0}", new Object[] {ctVerify.getVerifyReqVer()});
		
		if (SessionUtil.existMemberSession(req)) {
			Session memberInfo = (Session) SessionUtil.getMemberSession(req);
			
			ctVerify.setInsBy(memberInfo.getMbrNo());
			
			/**
			 * 상품 상세정보
			 * 1. 플랫폼, ApplicationId, 분류, 대상단말, 등급, 등록일, 검증요청일, 검증완료(예정)일, 검증상태, 검증결과 값을 가져온다.
			 * 
			 * 2. Sub Contents Apk파일, Apk파일 상세 정보와 App.검증결과서를 가져온다.
			 * 
			 * (2번에서 가져온 SCID, VERIFY_REQ_VER을 조건으로 3, 4번을 가져온다.)
			 * 3. 기타 첨부파일을 가져온다.
			 * 
			 * 4. 해당 상품 대상단말 수를 가져온다.
			 * 
			 * */
			
			addFile = new ArrayList<List>();
			
			contentVerifyDetail = ctVerifyService.getSubContentsVerifyDetailState(ctVerify);
			
			subContentsVerifyList = ctVerifyService.getContentVerifyDetailViewBody(ctVerify);
			
			String[] scidArray = new String[subContentsVerifyList.size()];
			
			int listSize = subContentsVerifyList.size();
			for(int idx = 0; idx < listSize; idx++) {
				ctVerify.setScid(subContentsVerifyList.get(idx).getScid());
				addFileList = ctVerifyService.getContentVerifyDetailAddFile(ctVerify);
				addFile.add(addFileList);
				
				scidArray[idx] = subContentsVerifyList.get(idx).getScid();
				
				ctVerify.setScidArray(scidArray);
			}
			
			this.req.setAttribute("referer", this.req.getHeader("referer"));
		}
		
		return SUCCESS;
	}
	
	public String ditailVerifyHistoryAjax() {
		
		if(ctVerify == null) {
			ctVerify = new ContentsVerify();
		}
		
		if(SessionUtil.existMemberSession(req)) {
			Session memberInfo = (Session) SessionUtil.getMemberSession(req);
			
			ctVerify.setInsBy(memberInfo.getMbrNo());
			
			resultList = ctVerifyService.getContentsVerifyHisList(ctVerify);
		}
		
		return SUCCESS;
	}
	
	/**
	 * Content Verify Cancel
	 * 
	 * @throws Exception
	 */
	public String contentVerifyCancel() {
		boolean flag = false;
		String rtnResult = "";
		
		if(ctVerify == null) {
			ctVerify = new ContentsVerify();
		}
		
		logger.info("CID  ::  " + ctVerify.getCid());
		logger.info("VerifyReqVer  ::  " + ctVerify.getVerifyReqVer());
		
		if (SessionUtil.existMemberSession(req)) {
			Session memberInfo = (Session) SessionUtil.getMemberSession(req);
			
			ctVerify.setInsBy(memberInfo.getMbrNo());
			
			flag = ctVerifyService.updateContentVerifyCancel(ctVerify);
			
			if(!flag) { throw new NoticeException("검증대기 상태에서만 취소 가능합니다."); }
			
			rtnResult = getContentVerifyDetailView();
			
			if(SUCCESS.equals(rtnResult)) { succMsg = LocalizeMessage.getLocalizedMessage("jsp.content.common.msg.result.success"); }
			
			this.req.setAttribute("referer", "cancel");
		}
		
		return rtnResult;
	}
	
	/**
	 * default StartDate, andDate Setting
	 * 
	 * @param int before - before month or day 
	 * @param String flag - "M" : month, "D" : day
	 * @param String dateFormat - ex)yyyy-MM-dd
	 * @return String[2] - index 0 : StartDate, index 1 : EndDate  
	 */
	private String[] getDateSetting(int before, String flag, String dateFormat) {
		String[] returnDate = new String[2];
			
		Date nowDate = new Date();
		returnDate[1] = DateUtil.getYYYYMMDD(nowDate);
		SimpleDateFormat dateFomat = new SimpleDateFormat(dateFormat);
		
		if("M".equals(flag)) {
			returnDate[0] = dateFomat.format(DateUtil.addMonth(nowDate, before));
		}
		
		if("D".equals(flag)) {
			returnDate[0] = DateUtil.getAddDay(before, dateFormat);
		}
			
		return returnDate;
	}
	
	public String popupSubContentsVerifyDetailView() throws Exception {
		return ditailVerifyStateAjax();
	}

	public ContentsVerify getCtVerify() {
		return ctVerify;
	}

	public void setCtVerify(ContentsVerify ctVerify) {
		this.ctVerify = ctVerify;
	}

	public ContentVerifyDetail getContentVerifyDetail() {
		return contentVerifyDetail;
	}

	public void setContentVerifyDetail(ContentVerifyDetail contentVerifyDetail) {
		this.contentVerifyDetail = contentVerifyDetail;
	}

	public List<ContentsVerify> getResultList() {
		return resultList;
	}

	public void setResultList(List<ContentsVerify> resultList) {
		this.resultList = resultList;
	}

	public List<SubContentsVerify> getSubContentsVerifyList() {
		return subContentsVerifyList;
	}

	public List<SubContentVerify> getAddFileList() {
		return addFileList;
	}

	public void setAddFileList(List<SubContentVerify> addFileList) {
		this.addFileList = addFileList;
	}

	public List<List> getAddFile() {
		return addFile;
	}

	public void setAddFile(List<List> addFile) {
		this.addFile = addFile;
	}

	public List<CommCode> getAgrmntCodeList() {
		return agrmntCodeList;
	}

	public void setAgrmntCodeList(List<CommCode> agrmntCodeList) {
		this.agrmntCodeList = agrmntCodeList;
	}

	public List<CommCode> getVerifyPrgrYnCodeList() {
		return verifyPrgrYnCodeList;
	}

	public void setVerifyPrgrYnCodeList(List<CommCode> verifyPrgrYnCodeList) {
		this.verifyPrgrYnCodeList = verifyPrgrYnCodeList;
	}

	public void setSubContentsVerifyList(List<SubContentsVerify> subContentsVerifyList) {
		this.subContentsVerifyList = subContentsVerifyList;
	}

	public int getSearchCnt() {
		return searchCnt;
	}

	public void setSearchCnt(int searchCnt) {
		this.searchCnt = searchCnt;
	}

	public String getSearchDay() {
		return searchDay;
	}

	public void setSearchDay(String searchDay) {
		this.searchDay = searchDay;
	}

	public String getSuccMsg() {
		return succMsg;
	}

	public void setSuccMsg(String succMsg) {
		this.succMsg = succMsg;
	}
	
	
}
