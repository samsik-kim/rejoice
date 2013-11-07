/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 *
 */
package com.omp.dev.settlement.action.report;

import java.util.List;

import com.omp.commons.action.BaseAction;
import com.omp.commons.model.PagenateList;
import com.omp.commons.util.SessionUtil;
import com.omp.dev.settlement.model.Report;
import com.omp.dev.settlement.service.report.SettlementDevReportService;
import com.omp.dev.settlement.service.report.SettlementDevReportServiceImpl;
import com.omp.dev.user.model.Session;




/**
 * SettlementAdm Action
 *
 * @version 0.1
 */
@SuppressWarnings("serial")
public class SettlementDevReportAction extends BaseAction {
	
	// service 
	private SettlementDevReportService service = null;
	
	// param 
	private Report report = null;
	
	// param 
	private Report reportS = null;
	
	
	// DailySale list
	private List<Report> reportList = null;
	
	

	// contents list totalCount
	private long totalCount;
	
	
	
	



	/**
	 * 
	 */
	public SettlementDevReportAction() {
		service = new SettlementDevReportServiceImpl();
	}

	


	
	/**
	 * <b>Action</b>
	 * Settlement Management : 
	 * 
	 * @return
	 */
	public String settlementReport() {
		if (reportS == null) {
			reportS = new Report();
			
		}
		
		Session session = (Session) SessionUtil.getMemberSession(this.req);
		reportS.setMbrNo(session.getMbrNo());
		
		reportList = service.settlementReport(reportS);
		report = service.bankInfo(reportS);
		report.setRmtReqYN(service.rmtReqYN()); //송금신청가능여부
		totalCount = ((PagenateList) reportList).getTotalCount();
		
		return SUCCESS;
	}
	
	/**
	 * 정산 송금요청
	 * @return
	 */
	public String settlementRequest() {
		if (reportS == null) {
			reportS = new Report();
			
		}
		
		Session session = (Session) SessionUtil.getMemberSession(this.req);
		reportS.setMbrNo(session.getMbrNo());
		reportS.setUpdBy(session.getMbrId());
		service.settlementRequest(reportS);
		reportS.setProcessMessage("請款完畢。請寄送發票或收據至公司地址, 以順利匯款。");
		
		return SUCCESS;
	}
	
	/**
	 * 조정 송금요청
	 * @return
	 */
	public String adjustMoneyRequest() {
		if (reportS == null) {
			reportS = new Report();
			
		}
		
		Session session = (Session) SessionUtil.getMemberSession(this.req);
		reportS.setMbrNo(session.getMbrNo());
		reportS.setUpdBy(session.getMbrId());
		service.adjustMoneyRequest(reportS);
		reportS.setProcessMessage("請款完畢。請寄送發票或收據至公司地址, 以順利匯款。");
		
		return SUCCESS;
	}
	
	/**
	 * 외국인 정산 송금요청
	 * @return
	 */
	public String foreignSettlementRequest() {
		if (reportS == null) {
			reportS = new Report();
			
		}
		
		Session session = (Session) SessionUtil.getMemberSession(this.req);
		reportS.setMbrNo(session.getMbrNo());
		reportS.setUpdBy(session.getMbrId());
		service.foreignSettlementRequest(reportS);
		reportS.setProcessMessage("請款完畢。請寄送發票或收據至公司地址, 以順利匯款。");
		
		return SUCCESS;
	}
	
	
	
	/**
	 * 이월처리 내용조회
	 * 
	 * @return
	 */
	public String popUpSettlementTransferInfo() {
		if (reportS == null) {
			reportS = new Report();
			
		}
		
		Session session = (Session) SessionUtil.getMemberSession(this.req);
		reportS.setMbrNo(session.getMbrNo());
		reportS.setAdjYn("N");
		report = service.selectSettlementContInfo(reportS);
		return SUCCESS;
	}
	
	
	/**
	 * 송금포기 내용조회
	 * 
	 * @return
	 */
	public String popUpSettlementGiveUpInfo() {
		if (reportS == null) {
			reportS = new Report();
			
		}
		
		Session session = (Session) SessionUtil.getMemberSession(this.req);
		reportS.setMbrNo(session.getMbrNo());
		reportS.setAdjYn("N");
		report = service.selectSettlementMoneyInfo(reportS);
		return SUCCESS;
	}
	
	
	/**
	 * 조정액 사유 내용조회
	 * 
	 * @return
	 */
	public String popUpSettlementAdjustmentInfo() {
		if (reportS == null) {
			reportS = new Report();
			
		}
		
		Session session = (Session) SessionUtil.getMemberSession(this.req);
		reportS.setMbrNo(session.getMbrNo());
		reportS.setAdjYn("Y");
		report = service.selectSettlementContInfo(reportS);
		return SUCCESS;
	}
	
		
	
	
// ===================== SET/GET =============================================
	
	
	/**
	 * list total count
	 * @return long totalCount
	 */
	public long getTotalCount() {
		return totalCount;
	}



	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}





	/**
	 * @return the report
	 */
	public Report getReport() {
		return report;
	}





	/**
	 * @param report the report to set
	 */
	public void setReport(Report report) {
		this.report = report;
	}





	/**
	 * @return the reportS
	 */
	public Report getReportS() {
		return reportS;
	}





	/**
	 * @param reportS the reportS to set
	 */
	public void setReportS(Report reportS) {
		this.reportS = reportS;
	}





	/**
	 * @return the reportList
	 */
	public List<Report> getReportList() {
		return reportList;
	}





	/**
	 * @param reportList the reportList to set
	 */
	public void setReportList(List<Report> reportList) {
		this.reportList = reportList;
	}
	
	
		
		
}