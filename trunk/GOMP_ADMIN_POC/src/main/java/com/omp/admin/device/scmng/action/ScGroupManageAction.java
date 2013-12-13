package com.omp.admin.device.scmng.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import pat.web.util.WebUtil;

import com.omp.admin.common.util.CommonUtil;
import com.omp.admin.device.scmng.model.CoreApp;
import com.omp.admin.device.scmng.model.CoreAppGroup;
import com.omp.admin.device.scmng.model.CoreAppGroupDevice;
import com.omp.admin.device.scmng.model.CoreAppGroupDeviceSearch;
import com.omp.admin.device.scmng.model.CoreAppGroupEdit;
import com.omp.admin.device.scmng.model.CoreAppGroupSearch;
import com.omp.admin.device.scmng.model.CoreAppHistorySearch;
import com.omp.admin.device.scmng.service.ScVersionManageServiceImpl;
import com.omp.commons.action.BaseAction;
import com.omp.commons.exception.InvalidInputException;
import com.omp.commons.exception.NoticeException;
import com.omp.commons.model.PagenateList;

@SuppressWarnings("serial")
public class ScGroupManageAction
	extends BaseAction {

	private Long						groupId;
	private ScVersionManageServiceImpl	service;
	private CoreAppGroupSearch			sc;
	private PagenateList<CoreAppGroup>	groupList;
	private CoreAppGroupEdit			edit;
	private CoreAppGroupDeviceSearch	deviceSc;
	private List<CoreAppGroupDevice>	deviceList;
	private Long[]						selectedGroups;
	private CoreAppHistorySearch		historySc;
	private List<CoreApp>				coreAppHistory;
	
	
	public ScGroupManageAction() {
		this.service	= new ScVersionManageServiceImpl();
	}
	
	public List<CoreApp> getCoreAppHistory() {
		return coreAppHistory;
	}
	
	public CoreAppHistorySearch getHistorySc() {
		return historySc;
	}

	public List<CoreAppGroupDevice> getDeviceList() {
		return deviceList;
	}

	public Long[] getSelectedGroups() {
		return selectedGroups;
	}

	public void setSelectedGroups(Long[] selectedGroups) {
		this.selectedGroups = selectedGroups;
	}

	public CoreAppGroupDeviceSearch getDeviceSc() {
		return deviceSc;
	}

	public Long getGroupId() {
		return groupId;
	}

	public CoreAppGroupEdit getEdit() {
		return edit;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public CoreAppGroupSearch getSc() {
		return this.sc;
	}
	
	public PagenateList<CoreAppGroup> getGroupList() {
		return groupList;
	}
	
	public String getScqs()
		throws UnsupportedEncodingException {
		return WebUtil.makeQueryString("sc.", this.sc, this.req.getCharacterEncoding(), true);
	}
		
	@Override
	public void prepareRequest() throws Exception {
		this.sc			= new CoreAppGroupSearch();
		this.deviceSc	= new CoreAppGroupDeviceSearch();
		this.edit		= new CoreAppGroupEdit();
		this.historySc	= new CoreAppHistorySearch();
	}
	
	
	/**
	 * 관리 초기화면
	 */
	public String scGroupInit() {
		this.groupList	= null;
		this.sc.setSearchTerm(60);
		this.sc.setAppTypes(new String[] {"DP002821", "DP002824"});
		this.sc.setSearchType("groupName");
		return SUCCESS;
	}
	
	/**
	 * 검색 화면
	 */
	public String scGroupList() {
		this.setStep("CallServiceGroupList");
		this.groupList	= this.service.searchCoreAppGroupList(sc);
		
		return SUCCESS;
	}

	/**
	 * 수정편집화면
	 * @return
	 */
	public String scGroupEdit() {
		if (this.groupId != null) {
			this.setPrimaryKey("coreappGroupId", groupId);
			this.edit	= this.service.selectCoreAppGroupEdit(this.groupId);
			if (this.edit == null) {
				throw new NoticeException("대상 자료는 존재하지 않습니다.");
			}
		}
		
		return SUCCESS;
	}
	
	/**
	 * 수정편집 화면용 단말기 리스트 조회
	 * @return
	 */
	public String ajaxScGroupDeviceList() {
		
		this.setStep("CallServiceGroupDeviceList");
		this.deviceList	= this.service.getCoreAppGroupDeviceList(this.deviceSc);
		
		return SUCCESS;
	}
	
	/**
	 * 저장
	 * @return
	 */
	public String scGroupSave() {
		Long	groupId;
		boolean	isNew;
		
		
		this.setStep("CheckData");
		groupId	= this.edit.getCoreappGroupId();
		this.setPrimaryKey("coreappGroupId", groupId);
		isNew	= groupId == null;
		
		
		// 작업자 설정
		this.setStep("SetOper");
		this.edit.setUpdId(CommonUtil.getLoginId(this.req.getSession()));
		
		this.setStep("CallServiceSaveGroup");
		this.service.saveCoreAppGroup(this.edit);
		
		// 추가시 검색 조건 초기화
		if (isNew) {
			this.setStep("SearchConditionSetting");
			this.sc	= new CoreAppGroupSearch();
			this.sc.setSearchTerm(60);
			this.sc.setAppTypes(new String[] {"DP002821", "DP002824"});
			this.sc.setSearchType("groupName");
		}
		
		return SUCCESS;
	}
	
	/**
	 * 삭제
	 */
	public String scGroupDelete() {
		this.setStep("CheckValidateInput");
		if (this.selectedGroups == null || this.selectedGroups.length == 0) {
			throw new InvalidInputException("항목을 선택해 주세요.");
		}
		this.setPrimaryKey("coreappGroupId", this.selectedGroups);
		this.setStep("CallServiceGroupDelete");
		this.service.deleteCoreAppGroup(this.selectedGroups);
		
		return SUCCESS;
	}
	
	/**
	 * 단말기 바이너리 이력 팝업
	 * @return
	 */
	public String popupScPhoneBinaryHistory(){
		this.setStep("CheckValidateInput");
		if (StringUtils.isEmpty(this.historySc.getPhoneModelCd())
			|| StringUtils.isEmpty(this.historySc.getAppType())) {
			throw new NoticeException("조회 대상이 지정되지 않았습니다.");
		}
		this.setStep("CallServiceSCHistory");
		this.coreAppHistory	= this.service.searchCoreAppHistory(this.historySc);
		
		return SUCCESS;
	}

	/**
	 * 그룹에 포함된 단말기 목록 팝업
	 * @return
	 */
	public String popupScGroupDeviceList() {
		this.setStep("CallServiceGroupDeviceList");
		this.deviceList	= this.service.getGroupDeviceList(this.groupId);
		
		return SUCCESS;
	}

}
