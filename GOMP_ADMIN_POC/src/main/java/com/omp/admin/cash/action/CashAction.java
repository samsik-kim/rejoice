package com.omp.admin.cash.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ibatis.dao.client.DaoException;
import com.omp.admin.cash.model.Cash;
import com.omp.admin.cash.model.CashSub;
import com.omp.admin.cash.service.CashService;
import com.omp.admin.cash.service.CashServiceImpl;
import com.omp.commons.action.BaseAction;
import com.omp.commons.exception.NoticeException;
import com.omp.commons.model.PagenateList;
import com.omp.commons.util.DateUtil;

public class CashAction extends BaseAction {
	
	private CashService service;
	
	// param 
	private CashSub sub = null;
	
	// Cash domain
	private Cash cash = null;
	
	// Cash list
	private List<Cash> list = null;
	
	private String prchsDt;
	
	private String lastedDate="";
	
	private String[] opTypes;
	
	public String[] getOpTypes() {
		return opTypes;
	}

	public void setOpTypes(String[] opTypes) {
		this.opTypes = opTypes;
	}

	/**
	 * default Constructor<br/>
	 * CashService Binding
	 */
	public CashAction() {
		service = new CashServiceImpl();
	}
	
	// contents list totalCount
	private long totalCount;
	
	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	
	public Cash getCash() {
		return cash;
	}

	public void setCash(Cash cash) {
		this.cash = cash;
	}
	
	public CashSub getSub() {
		return sub;
	}

	public void setSub(CashSub sub) {
		this.sub = sub;
	}
	
	public List<Cash> getList() {
		return list;
	}

	public void setList(List<Cash> list) {
		this.list = list;
	}
	
	public String getPrchsDt() {
		return prchsDt;
	}

	public void setPrchsDt(String prchsDt) {
		this.prchsDt = prchsDt;
	}
	
	public String getLastedDate() {
		return lastedDate;
	}

	public void setLastedDate(String lastedDate) {
		this.lastedDate = lastedDate;
	}
	
	/**
	 * 회원별 캐쉬 현황
	 * 
	 * @param hm
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	public String getCashList() {
        
		this.setStep("SearchConditionSetting");
		
		if (sub == null) {
			if (logger.isInfoEnabled())
				logger.info("init serarch");
			sub = new CashSub();

			// before 7 days default search
			sub.setSearchWeek("Y");
			sub.setEndDate(DateUtil.getSysDate("yyyy-MM-dd"));
			sub.setStartDate(DateUtil.getAddDay(-7, "yyyy-MM-dd"));
		}
		
		// search start / end date change db query format
		sub.setStartDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getStartDate()).trim()));
		sub.setEndDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getEndDate()).trim()));
		
		if (null != opTypes) {
			logger.debug(opTypes.length);
			if (opTypes.length == 1) {
				logger.debug(opTypes[0]);
				if ( null == opTypes[0] || opTypes[0] =="") {
					opTypes = null;
				}
			}
			sub.setOpTypes(opTypes);
		}
		
		this.setStep("CallServiceGetCashList");
		
		list = service.getCashList(sub);
		
		totalCount = ((PagenateList) list).getTotalCount();
		
		return SUCCESS;
	}
	
	/**
	 * 캐시 충전 현황
	 * 
	 * @param hm
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	public String getCashChargeList() {
		
		this.setStep("SearchConditionSetting");
		
		if (sub == null) {
			if (logger.isInfoEnabled())
				logger.info("init serarch");
			sub = new CashSub();

			// before 7 days default search
			sub.setSearchWeek("Y");
			sub.setEndDate(DateUtil.getSysDate("yyyy-MM-dd"));
			sub.setStartDate(DateUtil.getAddDay(-7, "yyyy-MM-dd"));
		}
		
		// search start / end date change db query format
		sub.setStartDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getStartDate()).trim()));
		sub.setEndDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getEndDate()).trim()));
		
		this.setStep("CallServiceGetCashChargeList");
		
		list = service.getCashChargeList(sub);
		
		totalCount = ((PagenateList) list).getTotalCount();
		
		return SUCCESS;
	}
	
	public String getCashChargeListExcel() {
		
		try {
			
			this.setStep("SearchConditionSetting");
			
			if (sub == null) {
				if (logger.isInfoEnabled())
					logger.info("init serarch");
				sub = new CashSub();

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
			
			this.setStep("CallServiceGetCashChargeListExcel");
			
			this.setDownloadFile(this.service.getCashChargeListExcel(this.sub), "application/vnd.ms-excel; charset=UTF-8", "加值紀錄_" + yyyymmdd + ".xls");
			
			return SUCCESS;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
		 
	}
	
	/**
	 * <b>Action</b>
	 * Cash Management : getCashChargeListDetail
	 * 
	 * @return
	 */
	public String getCashChargeListDetail() {
		
		this.setStep("CallServiceGetCashChargeListDetail");
		
		list = service.getCashChargeListDetail(sub);
		totalCount = ((PagenateList) list).getTotalCount();
		return SUCCESS;
	}
	
	public String getCashChargeListDetailExcel() {
		try {
			
			this.setStep("SearchConditionSetting");
			
			if (sub == null) {
				throw new NoticeException("조회 수행 후 다운로드 할 수 있습니다.");
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String yyyymmdd = sdf.format(new Date());
			
			this.setStep("CallServiceGetCashChargeListDetailExcel");
			
			this.setDownloadFile(this.service.getCashChargeListDetailExcel(this.sub), "application/vnd.ms-excel; charset=UTF-8", "加值紀錄_" + yyyymmdd + ".xls");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
		 
	}
	
	/**
	 * 캐시 취소 내역
	 * 
	 * @param hm
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	public String getCashCancelList() {
		
		this.setStep("SearchConditionSetting");
		
		if (sub == null) {
			if (logger.isInfoEnabled())
				logger.info("init serarch");
			sub = new CashSub();

			// before 7 days default search
			sub.setSearchWeek("Y");
			sub.setEndDate(DateUtil.getSysDate("yyyy-MM-dd"));
			sub.setStartDate(DateUtil.getAddDay(-7, "yyyy-MM-dd"));
		}
		
		// search start / end date change db query format
		sub.setStartDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getStartDate()).trim()));
		sub.setEndDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getEndDate()).trim()));
		
		this.setStep("CallServiceGetCashCancelList");
		
		list = service.getCashCancelList(sub);
		
		totalCount = ((PagenateList) list).getTotalCount();
		
		return SUCCESS;
	}
	
	public String getCashCancelListExcel() {
		try {
			this.setStep("SearchConditionSetting");
			if (sub == null) {
				if (logger.isInfoEnabled())
					logger.info("init serarch");
				sub = new CashSub();

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
			
			this.setStep("CallServiceGetCashCancelListExcel");
			
			this.setDownloadFile(this.service.getCashCancelListExcel(this.sub), "application/vnd.ms-excel; charset=UTF-8", "取消紀錄 _" + yyyymmdd	+ ".xls");
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
		 
	}
	
	public String getCashRefundUser() {
		
		this.setStep("SearchConditionSetting");
		
		if (sub == null) {
			if (logger.isInfoEnabled())
				logger.info("init serarch");
			sub = new CashSub();
			sub.setFirstCheck("Y");
		}
		
		this.setStep("CallServiceGetCashRefundUser");
		
		list = service.getCashRefundUser(sub); 
		totalCount = ((PagenateList) list).getTotalCount();
		return SUCCESS;
	}
	
	
	/**
	 * 캐시 취소 내역 상세 현황
	 * 
	 * @param hm
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	public String getCashCancelListDetail() {
		
		this.setStep("SearchConditionSetting");
		
		if (sub == null) {
			if (logger.isInfoEnabled())
				logger.info("init serarch");
			sub = new CashSub();

			// before 7 days default search
			sub.setSearchWeek("Y");
			sub.setEndDate(DateUtil.getSysDate("yyyy-MM-dd"));
			sub.setStartDate(DateUtil.getAddDay(-7, "yyyy-MM-dd"));
		}
		
		// search start / end date change db query format
		sub.setStartDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getStartDate()).trim()));
		sub.setEndDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getEndDate()).trim()));
		
		this.setStep("CallServiceGetCashCancelListDetail");
		list = service.getCashCancelListDetail(sub);
		
		totalCount = ((PagenateList) list).getTotalCount();
		
		return SUCCESS;
		
	}
	
	public String getCashCancelListDetailExcel() {
		try {
			
			this.setStep("SearchConditionSetting");
			if (sub == null) {
				if (logger.isInfoEnabled())
					logger.info("init serarch");
				sub = new CashSub();

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
			
			this.setStep("CallServiceGetCashCancelListDetailExcel");
			this.setDownloadFile(this.service.getCashCancelListDetailExcel(this.sub), "application/vnd.ms-excel; charset=UTF-8", "取消紀錄Detail_" + yyyymmdd + ".xls");
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
		 
	}
	
	/**
	 * 캐쉬 관리 정보
	 * @return
	 * @throws Exception
	 */
	
	public String getCashManage() {
		
		this.setStep("SearchConditionSetting");
		
		if (sub == null) {
			if (logger.isInfoEnabled())
				logger.info("init serarch");
			sub = new CashSub();
		}
		
		lastedDate =  service.getMaxtDateChrgCash();
		
		this.setStep("CallServiceGetChrgCashList");
		list = service.getChrgCashList(sub);
		
		totalCount = ((PagenateList) list).getTotalCount();
		
		return SUCCESS;
	}
	
	/**
	 * 캐시 보너스율 조정
	 * @return
	 * @throws Exception
	 */
	
	public String registCashBonus() {
		
		this.setStep("SearchConditionSetting");
		if (sub == null) {
			if (logger.isInfoEnabled())
				logger.info("init serarch");
			sub = new CashSub();
		}
		
		Cash cash = new Cash();
		String adminId = com.omp.admin.common.util.CommonUtil.getLoginId(this.req.getSession());
		cash.setUpdtId(adminId);		
		
		try {
			for (int i = 0; i < sub.getChrgCashId().length; i++) {
				
				cash.setChrgCashId(sub.getChrgCashId()[i].toString());
				cash.setBonusRatio(sub.getBonusRatio()[i].toString());
				cash.setPrebonusRatio(sub.getPrebonusRatio()[i].toString());
				
				//logger.debug("------------------" + cash.getPrebonusRatio() + ":::::" + cash.getBonusRatio());
				
				if (!cash.getPrebonusRatio().equals(cash.getBonusRatio())) {
					this.setStep("CallServiceRegistCashBonus");
					service.registCashBonus(cash);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	
	/**
	 * 캐시 보너스율 조정
	 * @return
	 * @throws Exception
	 */
	
	public String getCashManageHisList() {
		
		this.setStep("SearchConditionSetting");
		
		if (sub == null) {
			if (logger.isInfoEnabled())
				logger.info("init serarch");
			sub = new CashSub();
			
			sub.setSearchMonth("Y");
			sub.setEndDate(DateUtil.getSysDate("yyyy-MM-dd"));
			sub.setStartDate(DateUtil.getAddDay(-31, "yyyy-MM-dd"));
		}
		
		// search start / end date change db query format
		sub.setStartDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getStartDate()).trim()));
		sub.setEndDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getEndDate()).trim()));
		
		this.setStep("CallServiceCashmanagehisList");
		list = service.cashmanagehisList(sub); 
		totalCount = ((PagenateList) list).getTotalCount();
		return SUCCESS;
	}

}
