package com.omp.admin.cash.service;

import java.io.File;
import java.util.List;
import java.util.Map;
import com.omp.admin.cash.model.Cash;
import com.omp.admin.cash.model.CashSub;

public interface CashService {
	
	/**
	 * get CashList 
	 * @param sub
	 * @return
	 */
	List<Cash> getCashList(CashSub sub);
	
	/**
	 * get getCashChargeList 
	 * @param sub
	 * @return
	 */
	List<Cash> getCashChargeList(CashSub sub);
	
	/**
	 * get getCashChargeList excel file
	 * @param sub
	 * @return
	 */
	File getCashChargeListExcel(CashSub sub);
	
	/**
	 * get getCashChargeDetailList 
	 * @param sub
	 * @return
	 */
	List<Cash> getCashChargeListDetail(CashSub sub);
	
	/**
	 * get getCashChargeList excel file
	 * @param sub
	 * @return
	 */
	File getCashChargeListDetailExcel(CashSub sub);
	
	/**
	 * getCashCancelList 
	 * @param sub
	 * @return
	 */
	List<Cash> getCashCancelList(CashSub sub);
	
	/**
	 * get getCashCancelListExcel excel file
	 * @param sub
	 * @return
	 */
	File getCashCancelListExcel(CashSub sub);
	
	/**
	 * getCashRefundUser 
	 * @param sub
	 * @return
	 */
	List<Cash> getCashRefundUser(CashSub sub);
	
	/**
	 * 총캐시가용금액 조회
	 * @param paramMap
	 * @return
	 */
	public int getTotalAvailAmtCash(Map paramMap);
	
	/**
	 * 환불 - 캐시
	 * @param paramMap
	 * @return
	 */
	public Map refund(Map paramMap);
	
	/**
	 * 	예약 - 충전취소(캐시)
	 * @param paramMap
	 * @return
	 */
	public Map reserveChargeCancel(Map paramMap);
	
	/**
	 * 취소
	 * @param paramMap
	 * @return
	 */
	public Map cancel(Map paramMap);
	
	/**
	 * 사용 - 충전취소(캐시)
	 * @param paramMap
	 * @return
	 */
	public Map useChargeCancel(Map paramMap);
	
	/**
	 * 예약 취소
	 * @param paramMap
	 * @return
	 */
	public Map cancelReserve(Map paramMap);
	
	/**
	 * getCashCancelListDetail 
	 * @param sub
	 * @return
	 */
	List<Cash> getCashCancelListDetail(CashSub sub);
	
	/**
	 * get getCashCancelListDetailExcel excel file
	 * @param sub
	 * @return
	 */
	File getCashCancelListDetailExcel(CashSub sub);
	
	/**
	 *  getChrgCashList 
	 * @param sub
	 * @return
	 */
	List<Cash> getChrgCashList(CashSub sub);
	
	String getMaxtDateChrgCash();
	
	/**
	 * @param paramMap
	 * @return
	 */
	int registCashBonus(Cash cash);
	
	/**
	 *  cashmanagehisList 
	 * @param sub
	 * @return
	 */
	List<Cash> cashmanagehisList(CashSub sub);

}
