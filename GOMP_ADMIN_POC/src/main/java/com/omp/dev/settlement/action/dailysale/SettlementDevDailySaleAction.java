/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 *
 */
package com.omp.dev.settlement.action.dailysale;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.omp.commons.action.BaseAction;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.model.PagenateList;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.SessionUtil;
import com.omp.dev.settlement.model.DailySale;
import com.omp.dev.settlement.model.ProductSale;
import com.omp.dev.settlement.service.dailysale.SettlementDevDailySaleService;
import com.omp.dev.settlement.service.dailysale.SettlementDevDailySaleServiceImpl;
import com.omp.dev.user.model.Session;




/**
 * SettlementAdm Action
 *
 * @version 0.1
 */
@SuppressWarnings("serial")
public class SettlementDevDailySaleAction extends BaseAction {
	
	// service 
	private SettlementDevDailySaleService service = null;
	
	// param 
	private DailySale dailySale = null;
	
	// param 
	private DailySale dailySaleS = null;
	
	
	// DailySale list
	private List<DailySale> dailySaleList = null;
	
	

	// contents list totalCount
	private long totalCount;
	
	
	
	



	/**
	 * 
	 */
	public SettlementDevDailySaleAction() {
		service = new SettlementDevDailySaleServiceImpl();
	}

	


	
	/**
	 * <b>Action</b>
	 * Settlement Management : 
	 * 
	 * @return
	 */
	public String dailySaleList() {
		if (dailySaleS == null) {
			dailySaleS = new DailySale();
			
			// before 1 days default search
			dailySaleS.setStartDate(DateUtil.getAddDay(-7, "yyyy-MM-dd"));
			dailySaleS.setEndDate(DateUtil.getAddDay(0, "yyyy-MM-dd"));
			
		}
		
		Session session = (Session) SessionUtil.getMemberSession(this.req);
		dailySaleS.setMbrNo(session.getMbrNo());
		dailySaleList = service.dailySaleList(dailySaleS);
		totalCount = ((PagenateList) dailySaleList).getTotalCount();
		
		return SUCCESS;
	}
	
	
	public String dailySaleDetailList() {
		if (dailySaleS == null) {
			dailySaleS = new DailySale();
		}
		
		dailySaleS.getPage().setRows(10);
		Session session = (Session) SessionUtil.getMemberSession(this.req);
		dailySaleS.setMbrNo(session.getMbrNo());
		dailySaleList = service.dailySaleDetailList(dailySaleS);
		dailySale = service.dailySaleDetailCount(dailySaleS);
		totalCount = ((PagenateList) dailySaleList).getTotalCount();
		
		return SUCCESS;
	}
	
	
	public String dailySaleExcelList() {
		try {
			if (dailySaleS == null) {
				dailySaleS = new DailySale();
				
				// before 1 days default search
				dailySaleS.setStartDate(DateUtil.getAddDay(-7, "yyyy-MM-dd"));
				dailySaleS.setEndDate(DateUtil.getAddDay(0, "yyyy-MM-dd"));
				
			}
			
			Session session = (Session) SessionUtil.getMemberSession(this.req);
			dailySaleS.setMbrNo(session.getMbrNo());
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String yyyymmdd = sdf.format(new Date());
			
			// TODO 조회기간 무력화, 페이징 조건 무력화는 commonDao에서 처리되도록 되어 있슴
            this.setDownloadFile(this.service.dailySaleExcelList(this.dailySaleS), "application/vnd.ms-excel; charset=UTF-8", "DailySale-"+yyyymmdd+ ".xls");
            return SUCCESS;
			
		} catch (Exception e) {
			throw new ServiceException("dailySaleExcelList fail.", e);
		}
		 
	}
	
	
	public String dailySaleDetailExcelList() {
		try {
			if (dailySaleS == null) {
				dailySaleS = new DailySale();
				return SUCCESS;
			}
			
			Session session = (Session) SessionUtil.getMemberSession(this.req);
			dailySaleS.setMbrNo(session.getMbrNo());
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String yyyymmdd = sdf.format(new Date());
			
			// TODO 조회기간 무력화, 페이징 조건 무력화는 commonDao에서 처리되도록 되어 있슴
            this.setDownloadFile(this.service.dailySaleDetailExcelList(this.dailySaleS), "application/vnd.ms-excel; charset=UTF-8", "DailySaleDetail-"+yyyymmdd+ ".xls");
            return SUCCESS;
			
		} catch (Exception e) {
			throw new ServiceException("dailySaleDetailExcelList fail.", e);
		}
	}
	
	
	
// ===================== SET/GET =============================================
	
	
	/**
	 * list total count
	 * @return long totalCount
	 */
	public long getTotalCount() {
		return totalCount;
	}





	/**
	 * @return the dailySale
	 */
	public DailySale getDailySale() {
		return dailySale;
	}





	/**
	 * @param dailySale the dailySale to set
	 */
	public void setDailySale(DailySale dailySale) {
		this.dailySale = dailySale;
	}





	/**
	 * @return the dailySaleList
	 */
	public List<DailySale> getDailySaleList() {
		return dailySaleList;
	}





	/**
	 * @param dailySaleList the dailySaleList to set
	 */
	public void setDailySaleList(List<DailySale> dailySaleList) {
		this.dailySaleList = dailySaleList;
	}

	



	/**
	 * @return the dailySaleS
	 */
	public DailySale getDailySaleS() {
		return dailySaleS;
	}





	/**
	 * @param dailySaleS the dailySaleS to set
	 */
	public void setDailySaleS(DailySale dailySaleS) {
		this.dailySaleS = dailySaleS;
	}





	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	
	
		
		
}