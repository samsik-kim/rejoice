package com.omp.admin.contents.best.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.omp.admin.common.util.CommonUtil;
import com.omp.admin.contents.adminrec.model.AdminRecommend;
import com.omp.admin.contents.adminrec.model.AdminRecommendParam;
import com.omp.admin.contents.adminrec.model.DpCategoryList;
import com.omp.admin.contents.adminrec.service.AdminRecommendService;
import com.omp.admin.contents.adminrec.service.AdminRecommendServiceImpl;
import com.omp.admin.contents.best.model.BestParam;
import com.omp.admin.contents.best.service.ChargeService;
import com.omp.admin.contents.best.service.ChargeServiceImpl;
import com.omp.commons.action.BaseAction;
import com.omp.commons.model.PagenateList;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.StringUtil;


@SuppressWarnings("serial")
public class ChargeAction extends BaseAction {

	private static final GLogger log = new GLogger(ChargeAction.class);
	
	private AdminRecommendService service;
	private ChargeService service1;

	public ChargeAction() {
		service = new AdminRecommendServiceImpl();
		service1 = new ChargeServiceImpl();
	}
	
	private BestParam sub = null;
	
	private List<DpCategoryList> dpCategoryList = null;
	// AdminRecommend list
	private List<AdminRecommend> list = null;
	
	private AdminRecommend detail = null;
	
	// AdminRecommend list totalCount
	private long totalCount;


	public BestParam getSub() {
		return sub;
	}


	public void setSub(BestParam sub) {
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
	public String selectChargeBestList() {
		
		if (sub == null) {
			if (logger.isInfoEnabled())
				logger.info("init selectChargeBestList");
			sub = new BestParam();
		}

		sub.setDpUpCatNo(StringUtils.defaultIfEmpty(sub.getDpUpCatNo(), "DP01"));	// category main
		
		sub.setDpSelectDate(StringUtils.defaultIfEmpty(sub.getDpSelectDate(), DateUtil.getAddDay(-1, "yyyy-MM-dd"))); // 날짜
		sub.setTimeType(StringUtils.defaultIfEmpty(sub.getTimeType(), "Y")); //최신데이터여부
		
		if(sub.getDpSelectDate().equals(DateUtil.getAddDay(-1, "yyyy-MM-dd"))){
			sub.setTimeType("Y"); //최신데이터여부
		}else{
			sub.setTimeType("N"); //최신데이터여부
		}
		
		sub.setDpCatNo(StringUtils.defaultIfEmpty(sub.getDpCatNo(), "DP01"));	
		sub.setDpSelectDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getDpSelectDate()).trim()));
		this.setStep("GetDpCategoryList");
		dpCategoryList = service1.getDpCategoryList(sub);
		this.setStep("SelectChargeBestList");
		list = service1.selectChargeBestList(sub);
		
		totalCount = ((PagenateList) list).getTotalCount();
	
		log.debug("############# ChargeAction.selectChargeBestList End  ##################");

		return SUCCESS;
	}
	
	public String updateChargeBestList() {
		
		if (sub == null) {
			if (logger.isInfoEnabled())
				logger.info("init update updateChargeBestList");
			sub = new BestParam();
		}
		
		String[] upProdId 	= this.req.getParameterValues("upProdId");
		String[] expoYn 	= this.req.getParameterValues("expoYn");
		String[] expoPrior 	= this.req.getParameterValues("expoPrior");

		sub.setUpdId(CommonUtil.getLoginId(this.req.getSession()));
		sub.setDpSelectDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getDpSelectDate()).trim()));
		this.setStep("UpdateChargeBestList");		
		service1.updateChargeBestList(sub, upProdId, expoYn, expoPrior);
	
		log.debug("############# ChargeAction.updateChargeBestList End  ##################");

		return SUCCESS;
	}
	
	public String deleteChargeBestList() {
		
		if (sub == null) {
			if (logger.isInfoEnabled())
				logger.info("init delete deleteChargeBestList");
			sub = new BestParam();
		}
		
		String selectedProdId = StringUtil.nvlStr(this.req.getParameter("selectedProdId"));
		sub.setDpSelectDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getDpSelectDate()).trim()));

		sub.setUpdId(CommonUtil.getLoginId(this.req.getSession()));
		this.setStep("DeleteChargeBestList");		
		service1.deleteChargeBestList(selectedProdId, sub);
	
		log.debug("############# ChargeAction.deleteChargeBestList End  ##################");

		return SUCCESS;
	}
	
	public String insertChargeBestDefault() {
		
		if (sub == null) {
			if (logger.isInfoEnabled())
				logger.info("init insert insertChargeBestDefault");
			sub = new BestParam();
		}

		sub.setRegId(CommonUtil.getLoginId(this.req.getSession()));
		sub.setUpdId(CommonUtil.getLoginId(this.req.getSession()));
		sub.setDpSelectDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getDpSelectDate()).trim()));
		this.setStep("insertChargeBestDefault");		
		service1.insertChargeBestDefault(sub);
	
		log.debug("############# ChargeAction.insertChargeBestDefault End  ##################");

		return SUCCESS;
	}
}