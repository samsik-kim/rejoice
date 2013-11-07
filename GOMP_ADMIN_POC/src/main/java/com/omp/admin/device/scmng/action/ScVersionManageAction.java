package com.omp.admin.device.scmng.action;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import pat.web.util.WebUtil;

import com.omp.admin.common.util.CommonUtil;
import com.omp.admin.device.scmng.model.CoreApp;
import com.omp.admin.device.scmng.model.CoreAppGroup;
import com.omp.admin.device.scmng.model.CoreAppSearch;
import com.omp.admin.device.scmng.service.ScVersionManageServiceImpl;
import com.omp.commons.action.BaseAction;
import com.omp.commons.exception.InvalidInputException;
import com.omp.commons.exception.NoticeException;
import com.omp.commons.model.PagenateList;

@SuppressWarnings("serial")
public class ScVersionManageAction
	extends BaseAction {
	
	private ScVersionManageServiceImpl	service;
	private CoreApp						coreApp;
	private CoreAppSearch				sc;
	private PagenateList<?>				coreAppList;
	private Long						coreappId;
	private List<CoreAppGroup>			groupList;
	private Long[]						selectedCoreapps;
	private String						status;
	private String						lastVersion;
	
	public ScVersionManageAction() {
		this.service		= new ScVersionManageServiceImpl();
	}

	public Long[] getSelectedCoreapps() {
		return selectedCoreapps;
	}

	public void setSelectedCoreapps(Long[] selectedCoreapps) {
		this.selectedCoreapps = selectedCoreapps;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public CoreApp getCoreApp() {
		return coreApp;
	}

	public CoreAppSearch getSc() {
		return sc;
	}
	
	public String getScqs()
		throws UnsupportedEncodingException {
		return WebUtil.makeQueryString("sc.", this.sc, this.req.getCharacterEncoding(), true);
	}

	public List<?> getCoreAppList() {
		return coreAppList;
	}

	public List<CoreAppGroup> getGroupList() {
		return groupList;
	}
	
	public String getLastVersion() {
		return this.lastVersion;
	}

//	public List<?> getSupportDeviceList() {
//		return supportDeviceList;
//	}
	@Override
	public void prepareRequest() throws Exception {
		this.coreApp			= new CoreApp();
		this.sc					= new CoreAppSearch();
		this.coreAppList		= null;
		this.coreappId			= null;
//		this.supportDeviceList	= null;
	}
	
	public Long getCoreappId() {
		return coreappId;
	}

	public void setCoreappId(Long coreappId) {
		this.coreappId = coreappId;
	}
	
	
	/**
	 * 바이너리관리 초기화면
	 * @return
	 */
	public String scVersionInit() {
		this.coreAppList	= null;
		this.sc.setSearchTerm(60);
		this.sc.setAppTypes(new String[] {"DP002821", "DP002824"});
		this.sc.setStatus(new String[] {"DP005401", "DP005402", "DP005403"});
		return SUCCESS;
	}

	/**
	 * 검색 리스트
	 * @return
	 */
	public String scVersionList() {
		this.setStep("CallServiceGroupDelete");
		this.coreAppList		= this.service.searchCoreAppList(this.sc);
		this.setSearchedTotalCount(this.coreAppList);
		
		return SUCCESS;
	}
	

	/**
	 * 검색을 위한 그룹 리스트 조회
	 * @return
	 */
	public String ajaxScVersionGroupList4Search() {
		this.setStep("CallServiceGroupList");
		this.groupList	= this.service.searchCoreAppGroupList4Vermng(this.sc);
		return SUCCESS;
	}
	
	/**
	 * 수정편집화면
	 * @return
	 */
	public String scVersionEdit() {
		if (this.coreappId != null) {
			// 편집수정
			this.setStep("CallServiceGetCoreappInfo");
			this.setPrimaryKey("coreappId", this.coreappId);
			this.coreApp	= this.service.selectCoreAppInfo(this.coreappId);
			if (this.coreApp == null) {
				throw new NoticeException("대상 자료는 존재하지 않습니다.");
			}
		}
		
		return SUCCESS;
	}
	
	/**
	 * 수정 편집 화면을 위한 그룹 목록 조회
	 * @return
	 */
	public String ajaxScVersionGroupList4Edit() {
		
		this.setStep("CallServiceGroupList");
		this.groupList	= this.service.searchCoreAppGroupList4Vermng(this.sc);
		return SUCCESS;
	}

	/**
	 * 저장
	 * @return
	 */
	public String scVersionSave() {
		String	ver;
		File	appFile;
		boolean	isNew;
		
		//validate
		ver	= this.coreApp.getVer();
		if (StringUtils.isEmpty(ver)) {
			throw new InvalidInputException("버전이 입력되지 않았습니다.");
		}
		if (this.coreApp.getCoreappId() == null) {
			appFile	= this.coreApp.getAppFile(); 
			if (appFile == null) {
				throw new InvalidInputException("파일이 등록되지 않았습니다.");
			}
			isNew	= true;
		} else {
			isNew	= false;
		}
		this.setPrimaryKey("coreappId", this.coreApp.getCoreappId());
		
		// 작업자 설정
		this.coreApp.setUpdId(CommonUtil.getLoginId(this.req.getSession()));
		
		// DB 저장
		this.service.saveCoreApp(this.coreApp);
		
		// 추가시 검색 조건 초기화
		if (isNew) {
			this.sc	= new CoreAppSearch();
			this.sc.setSearchTerm(60);
			this.sc.setAppTypes(new String[] {"DP002821", "DP002824"});
			this.sc.setStatus(new String[] {"DP005401", "DP005402", "DP005403"});
		}
		
		return SUCCESS;
	}

	/**
	 * 상태변경
	 * @return
	 */
	public String scVersionChangeStatus() {
		this.setStep("CheckeValidateInput");
		if (this.selectedCoreapps == null || this.selectedCoreapps.length == 0) {
			throw new InvalidInputException("항목을 선택해 주세요.");
		}
		if (StringUtils.isEmpty(this.status)) {
			throw new InvalidInputException("변경 대상 상태가 전달되지 않았습니다.");
		}
		this.setPrimaryKey("coreappId", this.selectedCoreapps);
		
		this.setStep("CallServiceChangeStatus");
		this.service.changeStatus(this.selectedCoreapps, this.status, CommonUtil.getLoginId(this.req.getSession()));
		
		
		return "SUCCESS_" + this.status;
	}
	
	/**
	 * 그룹의 최신버전 얻기
	 * @return
	 */
	public String ajaxScLastVersionInGroup() {
		
		this.setStep("CheckeValidateInput");
		if (this.sc == null || this.sc.getGroupId() == null) {
			throw new NoticeException("조회 대상이 지정되지 않았습니다.");
		}
		this.setStep("CallServiceGetLastVersionInGroup");
		this.lastVersion	= this.service.getAppLastVersion(this.sc.getGroupId());
		if (this.lastVersion == null) {
			this.lastVersion	= "0.00";
		}
		return SUCCESS;
	}
}