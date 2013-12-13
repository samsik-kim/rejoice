package com.omp.dev.settlement.service.productsale;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.omp.commons.exception.ServiceException;
import com.omp.commons.model.ColumnInfoModel;
import com.omp.commons.service.AbstractService;
import com.omp.dev.settlement.model.ProductSale;

public class SettlementDevProductSaleServiceImpl extends AbstractService implements SettlementDevProductSaleService {
	
	

	@Override
	public List<ProductSale> productSaleList(ProductSale sub) {
		List<ProductSale> SaleStatDailyDetailList = null;
		try {
			SaleStatDailyDetailList = super.commonDAO.queryForPageList("Settlement.ProductSale.selectProductSaleList", sub);
		} catch (Exception e) {
			throw new ServiceException("일별판매현황를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return SaleStatDailyDetailList;
	}
	
	
	public File productSaleExcelList(ProductSale sub) {
		// 작성할 엑셀파일의 컬럼 정보
		List<ColumnInfoModel> excelColumnDefineList = new ArrayList<ColumnInfoModel>();
		
		//excelColumnDefineList.add(new ColumnInfoModel("rnum", "번호"));
		excelColumnDefineList.add(new ColumnInfoModel("prdName", "상품명"));
		excelColumnDefineList.add(new ColumnInfoModel("prodId", "상품AID"));
		excelColumnDefineList.add(new ColumnInfoModel("prdSaleTypeName", "상품구분"));
		excelColumnDefineList.add(new ColumnInfoModel("prodprc", "상품가격 "));
		excelColumnDefineList.add(new ColumnInfoModel("chargedDwnlCnt", "판매건수"));
		excelColumnDefineList.add(new ColumnInfoModel("saleAmt", "판매금액"));
		excelColumnDefineList.add(new ColumnInfoModel("freeDwnlCnt", "판매건수 / 무료"));
		excelColumnDefineList.add(new ColumnInfoModel("cardSaleAmt", "신용카드"));
		excelColumnDefineList.add(new ColumnInfoModel("phoneSaleAmt", "핸드폰"));
		excelColumnDefineList.add(new ColumnInfoModel("cashSaleAmt", "TCash"));
		excelColumnDefineList.add(new ColumnInfoModel("cancelDwnlCnt", "취소건수"));
		excelColumnDefineList.add(new ColumnInfoModel("cancelAmt", "취소금액"));
		excelColumnDefineList.add(new ColumnInfoModel("subAmt", "소계"));
		
		try {
			return this.commonDAO.queryForExcel("Settlement.ProductSale.selectProductSaleList", sub, excelColumnDefineList);
		} catch (Exception e) {
			throw new ServiceException("상품별 판매현황 excel파일 작성중 에러가 발생 하였습니다.", e);
		}
	}
	
		
}

