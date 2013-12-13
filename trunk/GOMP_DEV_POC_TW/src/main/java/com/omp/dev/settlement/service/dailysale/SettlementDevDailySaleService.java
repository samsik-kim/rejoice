package com.omp.dev.settlement.service.dailysale;

import java.io.File;
import java.util.List;

import com.omp.dev.settlement.model.DailySale;

public interface SettlementDevDailySaleService {
	
	
	/**
	 * get saleStatDaily list
	 * @param sub
	 * @return
	 */
	List<DailySale> dailySaleList(DailySale sub);
	List<DailySale> dailySaleDetailList(DailySale sub);
	DailySale dailySaleDetailCount(DailySale sub);
	File dailySaleExcelList(DailySale sub);
	File dailySaleDetailExcelList(DailySale sub);
	
		
}
