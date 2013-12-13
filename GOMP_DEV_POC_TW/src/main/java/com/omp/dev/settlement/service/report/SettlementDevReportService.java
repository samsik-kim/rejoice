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
	Report selectSettlementContInfo(Report sub);
	void foreignSettlementRequest(Report sub);
		
}
