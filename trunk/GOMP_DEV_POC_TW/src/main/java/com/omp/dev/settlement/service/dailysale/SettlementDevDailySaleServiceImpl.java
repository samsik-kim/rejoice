package com.omp.dev.settlement.service.dailysale;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.omp.commons.exception.ServiceException;
import com.omp.commons.model.ColumnInfoModel;
import com.omp.commons.service.AbstractService;
import com.omp.dev.settlement.model.DailySale;
import com.omp.dev.settlement.model.ProductSale;

public class SettlementDevDailySaleServiceImpl extends AbstractService implements SettlementDevDailySaleService {
	
	

	@Override
	public List<DailySale> dailySaleList(DailySale sub) {
		List<DailySale> dailySaleList = null;
		try {
			dailySaleList = super.commonDAO.queryForPageList("Settlement.DailySale.selectDailySaleList", sub);

		} catch (Exception e) {
			throw new ServiceException("일별판매현황를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return dailySaleList;
	}
	
	public List<DailySale> dailySaleDetailList(DailySale sub) {
		List<DailySale> dailySaleDetailList = null;
		try {
			dailySaleDetailList = super.commonDAO.queryForPageList("Settlement.DailySale.selectDailySaleDetailList", sub);
		} catch (Exception e) {
			throw new ServiceException("판매상세내역을 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return dailySaleDetailList;
	}
	
	
	/* 판매기간, 개발자별 구매건수 , 취소건수
	 * (non-Javadoc)
	 * @see com.omp.dev.settlement.service.dailysale.SettlementDevDailySaleService#dailySaleDetailCount(com.omp.dev.settlement.model.DailySale)
	 */
	public DailySale dailySaleDetailCount(DailySale sub) {
		DailySale retObj = null;
		try {
			retObj = (DailySale)super.commonDAO.queryForObject("Settlement.DailySale.selectDailySaleDetailCount", sub);
		} catch (Exception e) {
			throw new ServiceException("구매완료, 취소건수를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return retObj;
	}
	
	public File dailySaleExcelList(DailySale sub) {
		// 작성할 엑셀파일의 컬럼 정보
		List<ColumnInfoModel> excelColumnDefineList = new ArrayList<ColumnInfoModel>();
		
		//excelColumnDefineList.add(new ColumnInfoModel("rnum", "번호"));
		excelColumnDefineList.add(new ColumnInfoModel("saleDt", "날짜"));
		excelColumnDefineList.add(new ColumnInfoModel("freeDwnlCnt", "무료건수"));
		excelColumnDefineList.add(new ColumnInfoModel("chargedDwnlCnt", "유료건수"));
		excelColumnDefineList.add(new ColumnInfoModel("saleAmt", "판매금액 "));
		excelColumnDefineList.add(new ColumnInfoModel("cardSaleAmt", "신용카드"));
		excelColumnDefineList.add(new ColumnInfoModel("phoneSaleAmt", "핸드폰"));
		excelColumnDefineList.add(new ColumnInfoModel("cashSaleAmt", "T Cash"));
		excelColumnDefineList.add(new ColumnInfoModel("cancelDwnlCnt", "구매취소건수"));
		excelColumnDefineList.add(new ColumnInfoModel("saleCancelAmt", "구매취소금액"));
		excelColumnDefineList.add(new ColumnInfoModel("subtotalAmt", "소계"));
			
		try {
			return this.commonDAO.queryForExcel("Settlement.DailySale.selectDailySaleList", sub, excelColumnDefineList);
		} catch (Exception e) {
			throw new ServiceException("일별 판매현황 excel파일 작성중 에러가 발생 하였습니다.", e);
		}
	}
	
	
	public File dailySaleDetailExcelList(DailySale sub) {
		// 작성할 엑셀파일의 컬럼 정보
		List<ColumnInfoModel> excelColumnDefineList = new ArrayList<ColumnInfoModel>();
		
		//excelColumnDefineList.add(new ColumnInfoModel("rnum", "번호"));
		excelColumnDefineList.add(new ColumnInfoModel("saleDt", "판매일"));
		excelColumnDefineList.add(new ColumnInfoModel("prodId", "상품명"));
		excelColumnDefineList.add(new ColumnInfoModel("prdName", "상품AID"));
		excelColumnDefineList.add(new ColumnInfoModel("prdSaleTypeName", "상품구분"));
		excelColumnDefineList.add(new ColumnInfoModel("saleAmt", "판매금액 "));
		excelColumnDefineList.add(new ColumnInfoModel("cardSaleAmt", "신용카드"));
		excelColumnDefineList.add(new ColumnInfoModel("phoneSaleAmt", "핸드폰"));
		excelColumnDefineList.add(new ColumnInfoModel("cashSaleAmt", "T Cash"));
		excelColumnDefineList.add(new ColumnInfoModel("saleYNName", "구매상태"));
			
		try {
			return this.commonDAO.queryForExcel("Settlement.DailySale.selectDailySaleDetailList", sub, excelColumnDefineList);
		} catch (Exception e) {
			throw new ServiceException("판매상세내역  excel파일 작성중 에러가 발생 하였습니다.", e);
		}
	}
	
		
}

