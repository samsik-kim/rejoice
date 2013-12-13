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
		totalCount = ((PagenateList) reportList).getTotalCount();
		
		return SUCCESS;
	}
	
	/**
	 * 송금요청
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
		reportS.setProcessMessage("송금신청이 처리되었습니다. \\n Invoice나 Receipt를 작성하여 \\n 보내주셔야 송금이 정상적으로 이뤄집니다.");
		
		return SUCCESS;
	}
	
	/**
	 * 외국인 송금요청
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
		reportS.setProcessMessage("송금신청이 처리되었습니다. \\n Invoice나 Receipt를 작성하여 \\n 보내주셔야 송금이 정상적으로 이뤄집니다.");
		
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