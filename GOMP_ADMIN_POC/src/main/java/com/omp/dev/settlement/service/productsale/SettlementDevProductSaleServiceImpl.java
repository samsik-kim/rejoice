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
		excelColumnDefineList.add(new ColumnInfoModel("prdName", "產品名稱"));
		excelColumnDefineList.add(new ColumnInfoModel("aid", "產品AID"));
		excelColumnDefineList.add(new ColumnInfoModel("corpProdNo", "提供產品管理編號 "));
		excelColumnDefineList.add(new ColumnInfoModel("prdSaleTypeName", "產品區分"));
		excelColumnDefineList.add(new ColumnInfoModel("prodprc", "產品價格 "));
		excelColumnDefineList.add(new ColumnInfoModel("chargedDwnlCnt", "銷售記錄(筆數)"));
		excelColumnDefineList.add(new ColumnInfoModel("saleAmt", "銷售記錄(金額)"));
		//excelColumnDefineList.add(new ColumnInfoModel("freeDwnlCnt", "판매건수 / 무료"));
		excelColumnDefineList.add(new ColumnInfoModel("cardSaleAmt", "付款方式(信用卡)"));
		excelColumnDefineList.add(new ColumnInfoModel("phoneSaleAmt", "付款方式(手機)"));
		excelColumnDefineList.add(new ColumnInfoModel("cashSaleAmt", "付款方式(T商店幣)"));
		excelColumnDefineList.add(new ColumnInfoModel("cancelDwnlCnt", "取消購買之記錄(筆數)"));
		excelColumnDefineList.add(new ColumnInfoModel("cancelAmt", "取消購買之記錄(金額)"));
		//excelColumnDefineList.add(new ColumnInfoModel("subAmt", "소계"));
		
		try {
			return this.commonDAO.queryForExcel("Settlement.ProductSale.selectProductSaleList", sub, excelColumnDefineList);
		} catch (Exception e) {
			throw new ServiceException("상품별 판매현황 excel파일 작성중 에러가 발생 하였습니다.", e);
		}
	}
	
		
}

