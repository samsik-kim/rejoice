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
	private long newProcuctCnt = 0;
	private long authDoneProductCnt = 0;
	private long beforeSaleCnt = 0;
	private long beforeSaleSum = 0;
	
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
		String mbrNo = "";
		
		logger.debug("[MainAction()][main()][DevPoC MainAction...]");
		Session session = (Session) SessionUtil.getMemberSession(this.req);
		if(SessionUtil.existMemberSession(this.req)){
			mbrNo = session.getMbrNo();
			
			member = new Member();
			
			this.setStep("CallServiceGetMemberProfile");
			member = mainService.getMemberProfile(mbrNo);
			
			Contents contents = new Contents();

			// 신규 등록된 상품
			contents.setDevUserId(mbrNo);		// Member Mbr_No
			contents.setSaleStat(Constants.CONTENT_SALE_STAT_NA);
			
			this.setStep("CallServiceGetContentStatusCount7Days");
			this.newProcuctCnt = Long.parseLong(contentManagementService.getContentStatusCount7Days(contents));
			
			// 검증 완료 
//			contents.setDevUserId(session.getMbrNo());		// Member Mbr_No
			contents.setSaleStat("");
			contents.setVerifyPrgrYn(Constants.CODE_VERIFY_END);
			
			this.setStep("CallServiceGetContentVerifyStatusCount7Days");
			this.authDoneProductCnt = Long.parseLong(contentManagementService.getContentVerifyStatusCount7Days(contents));
			
			//전일 판매 현황
			DailySale dailySaleS = new DailySale();
			dailySaleS.setMbrNo(mbrNo);
			dailySaleS.setStartDate(DateUtil.getDayAfterWithOutSlash(DateUtil.getSysDate(), -1));
			dailySaleS.setEndDate(DateUtil.getDayAfterWithOutSlash(DateUtil.getSysDate(), -1));
			
			this.setStep("CallServiceDailySaleDetailCount");
			DailySale dailySale = service.dailySaleDetailCount(dailySaleS);
			
			if(dailySale != null){
				this.beforeSaleCnt = Long.parseLong(dailySale.getTotalSaleCount());
				this.beforeSaleSum = dailySale.getSaleAmt();
			}
		}
		
		if(this.type == null || "".equals(this.type)){
			this.type = "pay";
		}
		
		// Main Download Best 5
		this.setStep("CallServiceGetMainDownloadBest");
		this.getMainDownloadBest(this.type);
		
		// Main Notice List
		this.setStep("CallServiceSelectMainNoticeList");
		noticeList = mainService.selectMainNoticeList(Constants.CTGR_NTC_DEV);
		
		this.setPrimaryKey("ACTOR",  mbrNo);
		
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
	
	
	public String aboutUs()throws Exception{
		return SUCCESS;
	}
	
	public String copyright()throws Exception{
		return SUCCESS;
	}
	
	public String terms()throws Exception{
		return SUCCESS;
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
	public long getNewProcuctCnt() {
		return newProcuctCnt;
	}

	/**
	 * @param newProcuctCnt the newProcuctCnt to set
	 */
	public void setNewProcuctCnt(long newProcuctCnt) {
		this.newProcuctCnt = newProcuctCnt;
	}

	/**
	 * @return the authDoneProductCnt
	 */
	public long getAuthDoneProductCnt() {
		return authDoneProductCnt;
	}

	/**
	 * @param authDoneProductCnt the authDoneProductCnt to set
	 */
	public void setAuthDoneProductCnt(long authDoneProductCnt) {
		this.authDoneProductCnt = authDoneProductCnt;
	}

	/**
	 * @return the beforeSaleCnt
	 */
	public long getBeforeSaleCnt() {
		return beforeSaleCnt;
	}

	/**
	 * @param beforeSaleCnt the beforeSaleCnt to set
	 */
	public void setBeforeSaleCnt(long beforeSaleCnt) {
		this.beforeSaleCnt = beforeSaleCnt;
	}

	/**
	 * @return the beforeSaleSum
	 */
	public long getBeforeSaleSum() {
		return beforeSaleSum;
	}

	/**
	 * @param beforeSaleSum the beforeSaleSum to set
	 */
	public void setBeforeSaleSum(long beforeSaleSum) {
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
