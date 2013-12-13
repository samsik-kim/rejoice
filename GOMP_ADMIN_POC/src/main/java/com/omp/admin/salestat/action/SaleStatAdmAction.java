package com.omp.admin.salestat.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ibatis.dao.client.DaoException;
import com.omp.admin.salestat.model.SaleStat;
import com.omp.admin.salestat.model.SaleStatSearch;
import com.omp.admin.salestat.service.SaleStatAdmService;
import com.omp.admin.salestat.service.SaleStatAdmServiceImpl;
import com.omp.commons.action.BaseAction;
import com.omp.commons.model.PagenateList;
import com.omp.commons.util.DateUtil;

@SuppressWarnings("serial")
public class SaleStatAdmAction  extends BaseAction {
	
	private SaleStatAdmService service;
	
	// contents list totalCount
	private long totalCount;
	
	private int diffMonth;
	
	// SaleStatDaily domain
	private SaleStat saleStatDaily = null;
	
	public SaleStat getSaleStatDaily() {
		return saleStatDaily;
	}

	public void setSaleStatDaily(SaleStat saleStatDaily) {
		this.saleStatDaily = saleStatDaily;
	}

	// param 
	private SaleStatSearch sub = null;
	
	// saleStatDaily list
	private List<SaleStat> list = null;
	
	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public SaleStatSearch getSub() {
		return sub;
	}

	public void setSub(SaleStatSearch sub) {
		this.sub = sub;
	}

	public List<SaleStat> getList() {
		return list;
	}

	public void setList(List<SaleStat> list) {
		this.list = list;
	}
	
	
	public int getDiffMonth() {
		return diffMonth;
	}

	public void setDiffMonth(int diffMonth) {
		this.diffMonth = diffMonth;
	}

	/**
	 * default Constructor<br/>
	 * ContentsManagementService Binding
	 */
	public SaleStatAdmAction() {
		service = new SaleStatAdmServiceImpl();
	}
	
	/**
	 * 월별 판매현황 목록 조회
	 * 
	 * @param hm
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	public String selectSaleStatMonthlyMainList() {
		if (sub == null) {
			if (logger.isInfoEnabled())
				logger.info("init serarch");
			sub = new SaleStatSearch();
			
			// before 12 Monthly default search
			String fromYm = DateUtil.getYYYYMMBeforeMonth(DateUtil.getSysDate("yyyyMM"), -12);
			String toYm = DateUtil.getYYYYMM();

			sub.setFromYm(fromYm);		
			sub.setToYm(toYm);
		}
		
		// search start / end date change db query format
		sub.setStartDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getFromYm()).trim()));
		sub.setEndDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getToYm()).trim()));
		
		diffMonth = DateUtil.getDiffMonths(sub.getFromYm() + "01", sub.getToYm() + "01");
		
		list = service.selectSaleStatMonthlyMainList(sub);
		
		totalCount = ((PagenateList) list).getTotalCount();
		
		return SUCCESS;
	}
	
	public String exportSaleStatMonthlyMainList() {
		try {
			if (sub == null) {
				if (logger.isInfoEnabled())
					logger.info("init serarch");
				sub = new SaleStatSearch();
				
				// before 12 Monthly default search
				String fromYm = DateUtil.getYYYYMMBeforeMonth(DateUtil.getSysDate("yyyyMM"), -12);
				String toYm = DateUtil.getYYYYMM();

				sub.setFromYm(fromYm);		
				sub.setToYm(toYm);
			}
			
			// search start / end date change db query format
			sub.setStartDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getFromYm()).trim()));
			sub.setEndDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getToYm()).trim()));
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String yyyymmdd = sdf.format(new Date());

			// TODO 조회기간 무력화, 페이징 조건 무력화는 commonDao에서 처리되도록 되어 있슴
            this.setDownloadFile(this.service.selectSaleStatMonthlyMainExcel(this.sub), "application/vnd.ms-excel; charset=UTF-8", "每月販售現狀-"+yyyymmdd+ ".xls");
            return SUCCESS;
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
		 
	}
	
	/**
	 * 일별 판매현황 목록 조회
	 * 
	 * @param hm
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	public String selectSaleStatDailyMainList() {
		if (sub == null) {
			if (logger.isInfoEnabled())
				logger.info("init serarch");
			sub = new SaleStatSearch();

			// before 7 days default search
			sub.setSearchWeek("Y");
			sub.setEndDate(DateUtil.getSysDate("yyyy-MM-dd"));
			sub.setStartDate(DateUtil.getAddDay(-7, "yyyy-MM-dd"));
		}
		
		if (sub.getSaleYm() != null) {
			String saleym = sub.getSaleYm().replaceAll("-", "");
			String startsaleym = DateUtil.toDisplayFormat(saleym +"01", "-" );
			String endsaleym = DateUtil.toDisplayFormat( saleym + DateUtil.getlastDayOfMonth(saleym), "-" );
			sub.setStartDate(startsaleym);
			sub.setEndDate(endsaleym);
		}
		
		// search start / end date change db query format
		sub.setStartDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getStartDate()).trim()));
		sub.setEndDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getEndDate()).trim()));

		list = service.selectSaleStatDailyMainList(sub);
		
		totalCount = ((PagenateList) list).getTotalCount();
		
		return SUCCESS;
	}
	
	public String exportSaleStatDailyMainList() {
		try {
			if (sub == null) {
				if (logger.isInfoEnabled())
					logger.info("init serarch");
				sub = new SaleStatSearch();

				// before 7 days default search
				sub.setSearchWeek("Y");
				sub.setEndDate(DateUtil.getSysDate("yyyy-MM-dd"));
				sub.setStartDate(DateUtil.getAddDay(-7, "yyyy-MM-dd"));
			}
			
			// search start / end date change db query format
			sub.setStartDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getStartDate()).trim()));
			sub.setEndDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getEndDate()).trim()));
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String yyyymmdd = sdf.format(new Date());

			// TODO 조회기간 무력화, 페이징 조건 무력화는 commonDao에서 처리되도록 되어 있슴
            this.setDownloadFile(this.service.exportSaleStatDailyMainList(this.sub), "application/vnd.ms-excel; charset=UTF-8", "每日販售現狀-"+yyyymmdd+ ".xls");
            return SUCCESS;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
		 
	}
	
	/**
	 * 일별 판매현황 상세목록 조회
	 * 
	 * @param hm
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	public String selectSaleStatDailyDetailList() {
		
		if (sub == null) {
			if (logger.isInfoEnabled())
				logger.info("init serarch");
			sub = new SaleStatSearch();
			
			// before 1 days default search
			sub.setSaleDt(DateUtil.getAddDay(-1, "yyyy-MM-dd"));
		}
		
		sub.setSaleDt(StringUtils.defaultString(sub.getSaleDt()));
		sub.setSearchType(StringUtils.defaultString(sub.getSearchType()));
		sub.setSearchText(StringUtils.defaultString(sub.getSearchText()));
		
		list = service.selectSaleStatDailyDetailList(sub);
		
		totalCount = ((PagenateList) list).getTotalCount();
		
		return SUCCESS;
	}
	
	public String exportSaleStatDailyDetailList() {
		try {
			if (sub == null) {
				if (logger.isInfoEnabled())
					logger.info("init serarch");
				sub = new SaleStatSearch();
				
				sub.setSaleDt(DateUtil.getAddDay(-1, "yyyy-MM-dd"));
			}
			
			sub.setSaleDt(StringUtils.defaultString(sub.getSaleDt()));
			sub.setSearchType(StringUtils.defaultString(sub.getSearchType()));
			sub.setSearchText(StringUtils.defaultString(sub.getSearchText()));
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String yyyymmdd = sdf.format(new Date());
			
			// TODO 조회기간 무력화, 페이징 조건 무력화는 commonDao에서 처리되도록 되어 있슴
            this.setDownloadFile(this.service.exportSaleStatDailyDetailList(this.sub), "application/vnd.ms-excel; charset=UTF-8", "詳細銷售現狀-"+yyyymmdd+ ".xls");
            return SUCCESS;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
		 
	}
	
	/**
	 * 구매 취소 내역
	 * 
	 * @param hm
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	public String selectSaleStatRefundMainList() {
		if (sub == null) {
			if (logger.isInfoEnabled())
				logger.info("init serarch");
			sub = new SaleStatSearch();
			
			// before -1 Monthly default search
			String fromYm = DateUtil.getYYYYMMBeforeMonth(DateUtil.getSysDate("yyyyMM"), -1);
			String toYm = DateUtil.getYYYYMM();

			sub.setFromYm(fromYm);		
			sub.setToYm(toYm);
		}
		
		sub.setFromYm(sub.getFromYm()+"01");
		sub.setToYm(sub.getToYm()+"31");
		
		// search start / end date change db query format
		sub.setStartDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getFromYm()).trim()));
		sub.setEndDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getToYm()).trim()));
		sub.setSearchType(StringUtils.defaultString(sub.getSearchType()));
		sub.setSearchText(StringUtils.defaultString(sub.getSearchText()));
		
		list = service.selectSaleStatRefundMainList(sub);
		
		totalCount = ((PagenateList) list).getTotalCount();
		
		return SUCCESS;
	}
	
	public String exportSaleStatRefundMainList() {
		try {
			if (sub == null) {
				if (logger.isInfoEnabled())
					logger.info("init serarch");
				sub = new SaleStatSearch();
				
				// before -1 Monthly default search
				String fromYm = DateUtil.getYYYYMMBeforeMonth(DateUtil.getSysDate("yyyyMM"), -1);
				String toYm = DateUtil.getYYYYMM();

				sub.setFromYm(fromYm);		
				sub.setToYm(toYm);
			}
			
			sub.setFromYm(sub.getFromYm()+"01");
			sub.setToYm(sub.getToYm()+"31");
			
			// search start / end date change db query format
			sub.setStartDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getFromYm()).trim()));
			sub.setEndDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getToYm()).trim()));
			sub.setSearchType(StringUtils.defaultString(sub.getSearchType()));
			sub.setSearchText(StringUtils.defaultString(sub.getSearchText()));
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String yyyymmdd = sdf.format(new Date());
			
			// TODO 조회기간 무력화, 페이징 조건 무력화는 commonDao에서 처리되도록 되어 있슴
            this.setDownloadFile(this.service.exportSaleStatRefundMainList(this.sub), "application/vnd.ms-excel; charset=UTF-8", "取消購買之記錄-"+yyyymmdd+ ".xls");
            return SUCCESS;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
		 
	}
	
	
	
}
