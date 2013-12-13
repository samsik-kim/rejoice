package com.omp.dev.main.action;

import java.io.PrintWriter;
import java.util.List;

import net.sf.json.JSONArray;
import org.json.JSONObject;

import com.omp.commons.action.BaseAction;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.SessionUtil;
import com.omp.dev.common.Constants;
import com.omp.dev.community.model.Notice;
import com.omp.dev.contents.model.Contents;
import com.omp.dev.contents.service.ContentManagementService;
import com.omp.dev.contents.service.ContentManagementServiceImpl;
import com.omp.dev.main.model.MainDownloadBest;
import com.omp.dev.main.service.MainService;
import com.omp.dev.main.service.MainServiceImpl;
import com.omp.dev.member.model.Member;
import com.omp.dev.settlement.model.DailySale;
import com.omp.dev.settlement.service.dailysale.SettlementDevDailySaleService;
import com.omp.dev.settlement.service.dailysale.SettlementDevDailySaleServiceImpl;
import com.omp.dev.user.model.Session;

@SuppressWarnings("serial")
public class MainAction extends BaseAction {
	
	private static GLogger logger = new GLogger(MainAction.class);

	private Member member;
	
	private List<MainDownloadBest> downBestList;
	
	private List<Notice> noticeList;
	
	private MainService mainService;
	
	// service 
	private SettlementDevDailySaleService service = null;
	
	private 	ContentManagementService   contentManagementService;
	
	private String type;
	private String newProcuctCnt;
	private String authDoneProductCnt;
	private String beforeSaleCnt = "0";
	private String beforeSaleSum = "0";
	
	public MainAction(){
		mainService = new MainServiceImpl();
		contentManagementService	= new ContentManagementServiceImpl();
		service = new SettlementDevDailySaleServiceImpl();
	}
	
	/**
	 * 메인 처리
	 * @return
	 * @throws Exception
	 */
	public String main() throws Exception{
		logger.debug("[MainAction()][main()][DevPoC MainAction...]");
		Session session = (Session) SessionUtil.getMemberSession(this.req);
		if(SessionUtil.existMemberSession(this.req)){
			member = new Member();
			
			member = mainService.getMemberProfile(session.getMbrNo());
			
			Contents contents = new Contents();

			// 신규 등록된 상품
			contents.setDevUserId(session.getMbrNo());		// Member Mbr_No
			contents.setSaleStat(Constants.CONTENT_SALE_STAT_NA);
			
			this.newProcuctCnt = contentManagementService.getContentStatusCount7Days(contents);
			
			// 검증 완료 
			contents.setDevUserId(session.getMbrNo());		// Member Mbr_No
			contents.setSaleStat("");
			contents.setVerifyPrgrYn(Constants.CODE_VERIFY_END);
			
			this.authDoneProductCnt = contentManagementService.getContentVerifyStatusCount7Days(contents);
			
			//전일 판매 현황
			DailySale dailySaleS = new DailySale();
			dailySaleS.setMbrNo(session.getMbrNo());
			dailySaleS.setStartDate(DateUtil.getDayAfterWithOutSlash(DateUtil.getSysDate(), -1));
			dailySaleS.setEndDate(DateUtil.getDayAfterWithOutSlash(DateUtil.getSysDate(), -1));
			
			DailySale dailySale = service.dailySaleDetailCount(dailySaleS);
			
			if(dailySale != null){
				this.beforeSaleCnt = dailySale.getTotalSaleCount();
				this.beforeSaleSum = String.valueOf(dailySale.getSaleAmt());
			}
			
		}
		
		if(this.type == null || "".equals(this.type)){
			this.type = "pay";
		}
		
		// Main Download Best 5
		this.getMainDownloadBest(this.type);
		
		// Main Notice List
		noticeList = mainService.selectMainNoticeList(Constants.CTGR_NTC_DEV);
		
		return SUCCESS;
	}
	
	public void ajaxDownloadBest() throws Exception{
		this.res.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = null;
		
		try {
			JSONObject jsonObject = new JSONObject();
			
			this.getMainDownloadBest(this.type);
			
			JSONArray jsonArray = JSONArray.fromObject(this.downBestList);
			
			jsonObject.put("downBestList", jsonArray);
			
			out = this.res.getWriter();
			out.write(jsonObject.toString());
		} catch(Exception e) {
			throw new ServiceException("MainAction ajaxDownloadBest", e);
		} finally {
			if(out != null) out.close();
		}
	}
	
	/**
	 * Download Best 5 List
	 * @param type
	 * @return
	 */
	private List<MainDownloadBest> getMainDownloadBest(String type){
		
		if(type.equals("pay")){
			this.downBestList = mainService.getMainPayDownloadBest();
		}else if(type.equals("free")){
			this.downBestList = mainService.getMainFreeDownloadBest();
		}
		
		return this.downBestList;
	}
	
	/**
	 * @return the member
	 */
	public Member getMember() {
		return member;
	}
	/**
	 * @param member the member to set
	 */
	public void setMember(Member member) {
		this.member = member;
	}

	/**
	 * @return the downBestList
	 */
	public List<MainDownloadBest> getDownBestList() {
		return downBestList;
	}

	/**
	 * @param downBestList the downBestList to set
	 */
	public void setDownBestList(List<MainDownloadBest> downBestList) {
		this.downBestList = downBestList;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the newProcuctCnt
	 */
	public String getNewProcuctCnt() {
		return newProcuctCnt;
	}

	/**
	 * @param newProcuctCnt the newProcuctCnt to set
	 */
	public void setNewProcuctCnt(String newProcuctCnt) {
		this.newProcuctCnt = newProcuctCnt;
	}

	/**
	 * @return the authDoneProductCnt
	 */
	public String getAuthDoneProductCnt() {
		return authDoneProductCnt;
	}

	/**
	 * @param authDoneProductCnt the authDoneProductCnt to set
	 */
	public void setAuthDoneProductCnt(String authDoneProductCnt) {
		this.authDoneProductCnt = authDoneProductCnt;
	}

	/**
	 * @return the beforeSaleCnt
	 */
	public String getBeforeSaleCnt() {
		return beforeSaleCnt;
	}

	/**
	 * @param beforeSaleCnt the beforeSaleCnt to set
	 */
	public void setBeforeSaleCnt(String beforeSaleCnt) {
		this.beforeSaleCnt = beforeSaleCnt;
	}

	/**
	 * @return the beforeSaleSum
	 */
	public String getBeforeSaleSum() {
		return beforeSaleSum;
	}

	/**
	 * @param beforeSaleSum the beforeSaleSum to set
	 */
	public void setBeforeSaleSum(String beforeSaleSum) {
		this.beforeSaleSum = beforeSaleSum;
	}

	/**
	 * @return the noticeList
	 */
	public List<Notice> getNoticeList() {
		return noticeList;
	}

	/**
	 * @param noticeList the noticeList to set
	 */
	public void setNoticeList(List<Notice> noticeList) {
		this.noticeList = noticeList;
	}
	
}
