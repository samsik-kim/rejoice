package com.omp.admin.contents.newupdate.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.omp.admin.common.util.CommonUtil;
import com.omp.admin.contents.adminrec.model.AdminRecommend;
import com.omp.admin.contents.adminrec.model.AdminRecommendParam;
import com.omp.admin.contents.adminrec.model.DpCategoryList;
import com.omp.admin.contents.adminrec.service.AdminRecommendService;
import com.omp.admin.contents.adminrec.service.AdminRecommendServiceImpl;
import com.omp.admin.contents.newupdate.service.NewUpdateService;
import com.omp.admin.contents.newupdate.service.NewUpdateServiceImpl;
import com.omp.commons.action.BaseAction;
import com.omp.commons.model.PagenateList;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.StringUtil;


@SuppressWarnings("serial")
public class NewUpdateAction extends BaseAction {

	private static final GLogger log = new GLogger(NewUpdateAction.class);
	
	private AdminRecommendService service;
	private NewUpdateService service1;

	public NewUpdateAction() {
		service = new AdminRecommendServiceImpl();
		service1 = new NewUpdateServiceImpl();
	}
	
	private AdminRecommendParam sub = null;
	
	private List<DpCategoryList> dpCategoryList = null;
	// AdminRecommend list
	private List<AdminRecommend> list = null;
	
	private AdminRecommend detail = null;
	
	// AdminRecommend list totalCount
	private long totalCount;


	public AdminRecommendParam getSub() {
		return sub;
	}


	public void setSub(AdminRecommendParam sub) {
		this.sub = sub;
	}

	public List<DpCategoryList> getDpCategoryList() {
		return dpCategoryList;
	}


	public void setDpCategoryList(List<DpCategoryList> dpCategoryList) {
		this.dpCategoryList = dpCategoryList;
	}


	public List<AdminRecommend> getList() {
		return list;
	}


	public void setList(List<AdminRecommend> list) {
		this.list = list;
	}


	public long getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}


	public AdminRecommend getDetail() {
		return detail;
	}


	public void setDetail(AdminRecommend detail) {
		this.detail = detail;
	}


	/**
	 * 메인 리스트 조회.
	 * @return
	 */
	public String selectNewUpdateList() {
		
		if (sub == null) {
			if (logger.isInfoEnabled())
				logger.info("init selectNewUpdateList");
			sub = new AdminRecommendParam();
		}

		sub.setDpUpCatNo(StringUtils.defaultIfEmpty(sub.getDpUpCatNo(), "DP01"));	// category main
		
		sub.setDpSelectDate(StringUtils.defaultIfEmpty(sub.getDpSelectDate(), DateUtil.getDateStr(DateUtil.getDate(),'-'))); // 날짜
		sub.setTimeType(StringUtils.defaultIfEmpty(sub.getTimeType(), "Y")); //최신데이터여부
		
		if(sub.getDpSelectDate().equals(DateUtil.getDateStr(DateUtil.getDate(),'-'))){
			sub.setTimeType("Y"); //최신데이터여부
		}else{
			sub.setTimeType("N"); //최신데이터여부
		}
		
		sub.setDpCatNo(StringUtils.defaultIfEmpty(sub.getDpCatNo(), "DP01"));	
		sub.setDpSelectDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getDpSelectDate()).trim()));
		this.setStep("GetDpCategoryList");
		dpCategoryList = service.getDpCategoryList(sub);
		this.setStep("SelectNewUpdateList");
		list = service1.selectNewUpdateList(sub);
		
		totalCount = ((PagenateList) list).getTotalCount();
	
		log.debug("############# NewUpdateAction.selectNewUpdateList End  ##################");

		return SUCCESS;
	}
	
	public String updateNewUpdateList() {
		
		if (sub == null) {
			if (logger.isInfoEnabled())
				logger.info("init update updateNewUpdateList");
			sub = new AdminRecommendParam();
		}
		
		String[] upProdId 	= this.req.getParameterValues("upProdId");
		String[] expoYn 	= this.req.getParameterValues("expoYn");
		String[] expoPrior 	= this.req.getParameterValues("expoPrior");

		sub.setUpdId(CommonUtil.getLoginId(this.req.getSession()));
		sub.setDpSelectDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getDpSelectDate()).trim()));
		this.setStep("UpdateNewUpdateList");		
		service1.updateNewUpdateList(sub, upProdId, expoYn, expoPrior);
	
		log.debug("############# NewUpdateAction.updateNewUpdateList End  ##################");

		return SUCCESS;
	}
	
	public String deleteNewUpdateList() {
		
		if (sub == null) {
			if (logger.isInfoEnabled())
				logger.info("init delete deleteNewUpdateList");
			sub = new AdminRecommendParam();
		}
		
		String selectedProdId = StringUtil.nvlStr(this.req.getParameter("selectedProdId"));
		sub.setDpSelectDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getDpSelectDate()).trim()));

		sub.setUpdId(CommonUtil.getLoginId(this.req.getSession()));
		this.setStep("DeleteNewUpdateList");		
		service1.deleteNewUpdateList(selectedProdId, sub);
	
		log.debug("############# NewUpdateAction.deleteNewUpdateList End  ##################");

		return SUCCESS;
	}
}