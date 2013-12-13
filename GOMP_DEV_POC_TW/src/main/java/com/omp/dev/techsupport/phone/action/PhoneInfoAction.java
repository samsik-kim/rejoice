package com.omp.dev.techsupport.phone.action;

import java.util.List;

import com.omp.commons.action.BaseAction;
import com.omp.commons.util.GLogger;
import com.omp.dev.techsupport.phone.model.PhoneInfo;
import com.omp.dev.techsupport.phone.service.PhoneInfoService;
import com.omp.dev.techsupport.phone.service.PhoneInfoServiceImpl;

@SuppressWarnings("serial")
public class PhoneInfoAction extends BaseAction {
	private static GLogger logger = new GLogger(PhoneInfoAction.class);
	
	private List<PhoneInfo> resultList = null;
	private PhoneInfo phoneInfo = null;
	private String listImgPos;
	
	private PhoneInfoService service = null;
	
	public PhoneInfoAction(){
		service = new PhoneInfoServiceImpl();
	}
	
	public String getPhoneInfoList() throws Exception {
		if(phoneInfo == null) {
			phoneInfo = new PhoneInfo();
		}
		
		try {
			phoneInfo.getPage().setRows(10);
			
			resultList = service.getPhoneInfoList(phoneInfo);
			
			logger.debug(resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public List<PhoneInfo> getResultList() {
		return resultList;
	}
	public void setResultList(List<PhoneInfo> resultList) {
		this.resultList = resultList;
	}
	public PhoneInfo getPhoneInfo() {
		return phoneInfo;
	}
	public void setPhoneInfo(PhoneInfo phoneInfo) {
		this.phoneInfo = phoneInfo;
	}

	public String getListImgPos() {
		return listImgPos;
	}

	public void setListImgPos(String listImgPos) {
		this.listImgPos = listImgPos;
	}
}
