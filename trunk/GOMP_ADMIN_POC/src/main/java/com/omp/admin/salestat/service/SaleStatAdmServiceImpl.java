package com.omp.admin.salestat.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.omp.admin.salestat.model.SaleStat;
import com.omp.admin.salestat.model.SaleStatSearch;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.model.ColumnInfoModel;
import com.omp.commons.service.AbstractService;
import com.omp.commons.text.LocalizeMessage;

public class SaleStatAdmServiceImpl extends AbstractService implements SaleStatAdmService {
	
	private static final List<ColumnInfoModel> COLINFO_CONTENT_LIST	= new ArrayList<ColumnInfoModel>();
	
	static {
		COLINFO_CONTENT_LIST.add(new ColumnInfoModel("saleDt", "年 / 月"));
		COLINFO_CONTENT_LIST.add(new ColumnInfoModel("saleAmt", "銷售金額"));
		COLINFO_CONTENT_LIST.add(new ColumnInfoModel("chargedDwnlCnt", "付費"));
		COLINFO_CONTENT_LIST.add(new ColumnInfoModel("freeDwnlCnt", "免費"));
		COLINFO_CONTENT_LIST.add(new ColumnInfoModel("cancelDwnlCnt", "取消筆數(金額)"));
		COLINFO_CONTENT_LIST.add(new ColumnInfoModel("cardDwnlCnt", "信用卡"));
		COLINFO_CONTENT_LIST.add(new ColumnInfoModel("phoneDwnlCnt", "手機"));
		COLINFO_CONTENT_LIST.add(new ColumnInfoModel("cashDwnlCnt", "Whoopy幣"));
	}
	
	@Override
	public List<SaleStat> selectSaleStatMonthlyMainList(SaleStatSearch sub) {
		List<SaleStat> SaleStatMonthlyList = null;
		try {
			SaleStatMonthlyList = super.commonDAO.queryForPageList("SaleStat.selectSaleStatMonthlyMainList", sub);
		} catch (Exception e) {
			throw new ServiceException("월별판매현황를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return SaleStatMonthlyList;
	}
	
	/**
	 * 
	 */
	@Override
	public 	File selectSaleStatMonthlyMainExcel(SaleStatSearch sub) {
		
		// 작성할 엑셀파일의 컬럼 정보
		List<ColumnInfoModel> excelColumnDefineList = new ArrayList<ColumnInfoModel>();
		
		excelColumnDefineList.add(new ColumnInfoModel("saleYm", "年 / 月"));
		excelColumnDefineList.add(new ColumnInfoModel("saleAmt", "銷售記錄 / 銷售金額"));
		excelColumnDefineList.add(new ColumnInfoModel("payCnt", "銷售記錄 / 付費"));
		excelColumnDefineList.add(new ColumnInfoModel("freeCnt", "銷售記錄 / 免費"));
		excelColumnDefineList.add(new ColumnInfoModel("cancelCnt", "取消筆數 / 筆數"));
		excelColumnDefineList.add(new ColumnInfoModel("cancelAmt", "取消筆數 / 金額"));
		excelColumnDefineList.add(new ColumnInfoModel("cardAmt", "付款方式 / 信用卡"));
		excelColumnDefineList.add(new ColumnInfoModel("phoneAmt", "付款方式 / 手機"));
		excelColumnDefineList.add(new ColumnInfoModel("cashAmt", "付款方式 / Whoopy幣"));
		excelColumnDefineList.add(new ColumnInfoModel("subAmt", "總計"));
		try {
			return this.commonDAO.queryForExcel("SaleStat.selectSaleStatMonthlyMainList", sub, excelColumnDefineList);
		} catch (Exception e) {
			throw new ServiceException("월별판매현황를 excel파일 작성중 에러가 발생 하였습니다.", e);
		}
	}
	
	@Override
	public List<SaleStat> selectSaleStatDailyMainList(SaleStatSearch sub) {
		List<SaleStat> SaleStatDailyList = null;
		try {
			SaleStatDailyList = super.commonDAO.queryForPageList("SaleStat.selectSaleStatDailyMainList", sub);
		} catch (Exception e) {
			throw new ServiceException("일별판매현황를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return SaleStatDailyList;
	}
	
	/**
	 * 
	 */
	@Override
	public File exportSaleStatDailyMainList(SaleStatSearch sub) {
		try {
			return this.commonDAO.queryForExcel("SaleStat.selectSaleStatDailyMainList", sub, COLINFO_CONTENT_LIST);
		} catch (Exception e) {
			throw new ServiceException("일별판매현황 excel파일 작성중 에러가 발생 하였습니다.", e);
		}
	}
	
	@Override
	public List<SaleStat> selectSaleStatDailyDetailList(SaleStatSearch sub) {
		List<SaleStat> SaleStatDailyDetailList = null;
		try {
			SaleStatDailyDetailList = super.commonDAO.queryForPageList("SaleStat.selectSaleStatDailyDetailList", sub);
		} catch (Exception e) {
			throw new ServiceException("일별판매현황를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return SaleStatDailyDetailList;
	}
	
	/**
	 * 
	 */
	@Override
	public File exportSaleStatDailyDetailList(SaleStatSearch sub) {
		// 작성할 엑셀파일의 컬럼 정보
		List<ColumnInfoModel> excelColumnDefineList = new ArrayList<ColumnInfoModel>();
		
		excelColumnDefineList.add(new ColumnInfoModel("rnum", "序號"));
		excelColumnDefineList.add(new ColumnInfoModel("memNameId", "開發商帳號(姓名)"));
		excelColumnDefineList.add(new ColumnInfoModel("prdName", "產品名稱"));
		excelColumnDefineList.add(new ColumnInfoModel("aid", "AID"));
		excelColumnDefineList.add(new ColumnInfoModel("saleAmt", "銷售筆數 / 銷售金額 "));
		excelColumnDefineList.add(new ColumnInfoModel("chargedDwnlCnt", "銷售筆數  / 付費"));
		excelColumnDefineList.add(new ColumnInfoModel("freeDwnlCnt", "銷售筆數  / 免費"));
		excelColumnDefineList.add(new ColumnInfoModel("cancelDwnlCnt", "取銷筆數 / 筆數"));
		excelColumnDefineList.add(new ColumnInfoModel("cancelAmt", "取銷筆數 / 金額"));
		excelColumnDefineList.add(new ColumnInfoModel("cardDwnlCnt", "付款方式 / 信用卡"));
		excelColumnDefineList.add(new ColumnInfoModel("phoneDwnlCnt", "付款方式 / 手機"));
		excelColumnDefineList.add(new ColumnInfoModel("cashDwnlCnt", "付款方式 / Whoopy幣"));
		excelColumnDefineList.add(new ColumnInfoModel("subAmt", "總計"));
		
		try {
			return this.commonDAO.queryForExcel("SaleStat.selectSaleStatDailyDetailList", sub, excelColumnDefineList);
		} catch (Exception e) {
			throw new ServiceException("일별판매상세 excel파일 작성중 에러가 발생 하였습니다.", e);
		}
	}
	
	@Override
	public List<SaleStat> selectSaleStatRefundMainList(SaleStatSearch sub) {
		List<SaleStat> SaleStatRefundMainList = null;
		try {
			SaleStatRefundMainList = super.commonDAO.queryForPageList("SaleStat.selectSaleStatRefundMainList", sub);
		} catch (Exception e) {
			throw new ServiceException("구매취소내역을 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return SaleStatRefundMainList;
	}
	
	/**
	 * 
	 */
	@Override
	public File exportSaleStatRefundMainList(SaleStatSearch sub) {
		// 작성할 엑셀파일의 컬럼 정보
		List<ColumnInfoModel> excelColumnDefineList = new ArrayList<ColumnInfoModel>();
		
		excelColumnDefineList.add(new ColumnInfoModel("rnum", "序號"));
		excelColumnDefineList.add(new ColumnInfoModel("dttm", "取消日期"));
		excelColumnDefineList.add(new ColumnInfoModel("saleDttm", "銷售日期"));
		excelColumnDefineList.add(new ColumnInfoModel("memNameId", "開發商帳號(姓名)"));
		excelColumnDefineList.add(new ColumnInfoModel("userMemNameId", "購買者帳號(姓名)"));
		excelColumnDefineList.add(new ColumnInfoModel("prdName", "產品名稱"));
		excelColumnDefineList.add(new ColumnInfoModel("aid", "AID"));
		excelColumnDefineList.add(new ColumnInfoModel("prodprc", "產品價格"));
		excelColumnDefineList.add(new ColumnInfoModel("paymethod", "付款方式"));
		excelColumnDefineList.add(new ColumnInfoModel("payAmt", "付款金額"));
		
		try {
			return this.commonDAO.queryForExcel("SaleStat.selectSaleStatRefundMainList", sub, excelColumnDefineList);
		} catch (Exception e) {
			throw new ServiceException("구매취소내역 excel파일 작성중 에러가 발생 하였습니다.", e);
		}
	}
	
	
}

