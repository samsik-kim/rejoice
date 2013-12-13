package com.omp.dev.settlement.service.report;

import java.util.List;

import com.omp.dev.settlement.model.Report;

public interface SettlementDevReportService {
	
	
	/**
	 * get saleStatDaily list
	 * @param sub
	 * @return
	 */
	List<Report> settlementReport(Report sub);
	Report bankInfo(Report sub);
	void settlementRequest(Report sub);
	void adjustMoneyRequest(Report sub);
	Report selectSettlementContInfo(Report sub);
	Report selectSettlementMoneyInfo(Report sub);
	void foreignSettlementRequest(Report sub);
	String rmtReqYN();
		
}
