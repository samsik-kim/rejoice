/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 * redaano    2009. 4. 13.    Description
 *
 */
package com.omp.dev.common.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

import com.omp.commons.action.BaseAction;
//import com.omp.commons.com.model.DpCat;
//import com.omp.commons.com.service.ComService;
//import com.omp.commons.com.service.ComServiceImpl;
import com.omp.commons.commcode.CacheCommCode;
import com.omp.commons.commcode.model.CommCode;
import com.omp.commons.util.GLogger;
import com.omp.dev.common.Constants;
//import com.omp.dev.product.devinfo.service.ProductDevInfoSettingService;
//import com.omp.dev.product.devinfo.service.ProductDevInfoSettingServiceImpl;

/**
 * CommonAction
 * 
 * @author redaano
 * @version 0.1
 */

@SuppressWarnings("serial")
public class CommonAction extends BaseAction {
	private static GLogger logger = new GLogger(CommonAction.class);

//	private ComService service = null;
//	private String cd = "";
//
//
//	public CommonAction() {
//		service = new ComServiceImpl();
//	}


	/**
	 * 단말기명 조회(ajax)
	 */
	public void getAjaxPhoneName() {
//		HttpServletResponse res = ServletActionContext.this.res;
//		res.setContentType("text/plain; charset=UTF-8");
//		PrintWriter out = null;
//
//		List<PhoneInfo> list = service.getPhoneNameList(cd);
//
//		try {
//			JSONArray jsonArray = new JSONArray(list.toArray(), false);
//			JSONObject jsonObject = new JSONObject();
//
//			jsonObject.put("result", jsonArray);
//			out = res.getWriter();
//			out.write(jsonObject.toString());
//
//			// debug
//			if(logger.isDebugEnabled()) {
//				logger.debug(jsonObject.toString());
//			}
//
//		} catch(Exception ex) {
//			logger.error(ex, ex);
//		} finally {
//			if(out != null) {
//				out.close();
//			}
//		}
	}


	/**
	 * 상품전시카테고리 조회(ajax)
	 */
//	public void getAjaxDpCatList() {
//		HttpServletResponse res = ServletActionContext.this.res;
//		res.setContentType("text/plain; charset=UTF-8");
//		PrintWriter out = null;
//
//		List<DpCat> list = service.getDpCatList(cd);
//
//		try {
//			JSONArray jsonArray = new JSONArray(list.toArray(), false);
//			JSONObject jsonObject = new JSONObject();
//
//			jsonObject.put("result", jsonArray);
//			out = res.getWriter();
//			out.write(jsonObject.toString());
//
//			// debug
//			if(logger.isDebugEnabled()) {
//				logger.debug(jsonObject.toString());
//			}
//		} catch(Exception ex) {
//			logger.error(ex, ex);
//		} finally {
//			if(out != null) {
//				out.close();
//			}
//		}
//	}


	/**
	 * 상품 scid 생성
	 * */
//	public void getAjaxScid() {
//		
//		HttpServletResponse res = ServletActionContext.this.res;
//		res.setContentType("text/plain; charset=UTF-8");
//		PrintWriter out = null;
//
//		ProductDevInfoSettingService svc = new ProductDevInfoSettingServiceImpl();
//		String scid = svc.makeScid();
//		logger.debug("#scid : " + scid);
//
//		try {
//			out = res.getWriter();
//			out.write(scid);
//
//		} catch(Exception ex) {
//			logger.error(ex, ex);
//		} finally {
//			if(out != null) {
//				out.close();
//			}
//		}
//		
//	}

//	public static List<CommCode> getVmTypeList() {
//		List<CommCode> vmTypeList =  CacheCommCode.getCommCode("PD0056");	
//		List<CommCode> resultList = new ArrayList<CommCode>();
//		
//		if (vmTypeList != null) {
//			for (int  i=0; i < vmTypeList.size(); i++) {
//				if (!Constants.CONTENT_PLATFORM_MULTIMEDIA.equals(vmTypeList.get(i).getDtlFullCd())) {
//					resultList.add(vmTypeList.get(i));
//				}
//			}
//		}
//		
//		return resultList;
//	}

//	public ComService getService() {
//		return service;
//	}
//
//
//	public void setService(ComService service) {
//		this.service = service;
//	}


//	public String getCd() {
//		return cd;
//	}
//
//
//	public void setCd(String cd) {
//		this.cd = cd;
//	}

}