package com.omp.admin.device.scmng.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.omp.admin.device.scmng.model.CoreAppSearch;
import com.omp.admin.device.scmng.model.CoreAppSupport;
import com.omp.admin.device.scmng.model.DeviceSearch;
import com.omp.admin.device.scmng.service.ScVersionManageServiceImpl;
import com.omp.commons.action.BaseAction;
import com.omp.commons.exception.InvalidInputException;
import com.omp.commons.util.StringUtil;

@SuppressWarnings("serial")
public class ScVersionManageByDeviceAction
	extends BaseAction {
	private ScVersionManageServiceImpl	service;
	private DeviceSearch				sc;
	private CoreAppSearch				csc;
	private List<?>						deviceList;
	private List<?>						coreappList;
	private CoreAppSupport				support;
	
	public ScVersionManageByDeviceAction() {
		this.service	= new ScVersionManageServiceImpl();
	}
	
	public List<?> getDeviceList() {
		return deviceList;
	}

	public List<?> getCoreappList() {
		return coreappList;
	}

	public void setDeviceList(List<?> deviceList) {
		this.deviceList = deviceList;
	}
	
	
	@Override
	public void prepareRequest() throws Exception {
		this.sc			= new DeviceSearch();
		this.sc.setVmType("PD005606");
		this.sc.setSvcCd(new String[]{"US005202", "US005203"});
		this.csc		= new CoreAppSearch();
		this.support	= new CoreAppSupport();
	}
	
	public DeviceSearch getSc() {
		return sc;
	}

	public CoreAppSearch getCsc() {
		return csc;
	}
	
	public CoreAppSupport getSupport() {
		return this.support;
	}

	/**
	 * 단말기 검색
	 * @return
	 */
	public String scVersionManageByDevice() {
		this.deviceList	= this.service.searchCoreappManageDevices(this.sc);
		
		return SUCCESS;
	}
	
	/**
	 * 코어앱 검색
	 * @return
	 */
	public String ajaxScVersionListByDevice() {
		this.coreappList	= this.service.searchCoreappByDevice(this.csc);
		
		
		return SUCCESS;
	}
	
	/**
	 * 코어앱 상태 변경
	 * @return
	 */
	public String ajaxScVersionSetStatus() {
		// 벨리데이션
		if (StringUtils.isEmpty(this.support.getCoreappId())) {
			throw new InvalidInputException("설정 대상 코어앱이 지정되지 않았습니다.");
		}
		if (StringUtils.isEmpty(this.support.getPhoneModelCd())) {
			throw new InvalidInputException("설정 대상 단말기가 지정되지 않았습니다.");
		}
		if (StringUtils.isEmpty(this.support.getVerStatus())) {
			throw new InvalidInputException("설정 대상 코어앱 상태가 지정되지 않았습니다.");
		}
		
		this.service.updateSupportDeviceStatus(this.support);
		
		return SUCCESS;
	}
}