package com.omp.admin.salestat.service;

import java.io.File;
import java.util.List;

import com.omp.admin.salestat.model.SaleStat;
import com.omp.admin.salestat.model.SaleStatSearch;

public interface SaleStatAdmService {
	
	/**
	 * get saleStatMonthly list
	 * @param sub
	 * @return
	 */
	List<SaleStat> selectSaleStatMonthlyMainList(SaleStatSearch sub);
	
	/**
	 * get saleStatMonthly excel
	 * @param sub
	 * @return
	 */
	File selectSaleStatMonthlyMainExcel(SaleStatSearch sub);
	
	/**
	 * get saleStatDaily list
	 * @param sub
	 * @return
	 */
	List<SaleStat> selectSaleStatDailyMainList(SaleStatSearch sub);
	
	/**
	 * get saleStatDaily list excel file
	 * @param sub
	 * @return
	 */
	File exportSaleStatDailyMainList(SaleStatSearch sub);
	
	/**
	 * get saleStatDailyDetail list
	 * @param sub
	 * @return
	 */
	List<SaleStat> selectSaleStatDailyDetailList(SaleStatSearch sub);
	
	/**
	 * get saleStatDailyDetail list excel file
	 * @param sub
	 * @return
	 */
	File exportSaleStatDailyDetailList(SaleStatSearch sub);
	
	/**
	 * get SaleStatRefundMain list
	 * @param sub
	 * @return
	 */
	List<SaleStat> selectSaleStatRefundMainList(SaleStatSearch sub);
	
	/**
	 * get SaleStatRefundMainList list excel file
	 * @param sub
	 * @return
	 */
	File exportSaleStatRefundMainList(SaleStatSearch sub);
	
}
