package com.omp.admin.device.scmng.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.xmlpull.v1.XmlPullParserException;


import com.omp.admin.common.Constants;
import com.omp.admin.device.scmng.model.CoreApp;
import com.omp.admin.device.scmng.model.CoreAppDevice;
import com.omp.admin.device.scmng.model.CoreAppGroup;
import com.omp.admin.device.scmng.model.CoreAppGroupDevice;
import com.omp.admin.device.scmng.model.CoreAppGroupDeviceSearch;
import com.omp.admin.device.scmng.model.CoreAppGroupEdit;
import com.omp.admin.device.scmng.model.CoreAppGroupSearch;
import com.omp.admin.device.scmng.model.CoreAppHistorySearch;
import com.omp.admin.device.scmng.model.CoreAppList;
import com.omp.admin.device.scmng.model.CoreAppSearch;
import com.omp.admin.device.scmng.model.CoreAppSupport;
import com.omp.admin.device.scmng.model.Device;
import com.omp.admin.device.scmng.model.DeviceSearch;
import com.omp.commons.commcode.CacheCommCode;
import com.omp.commons.commcode.model.CommCode;
import com.omp.commons.exception.NoticeException;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.model.PagenateList;
import com.omp.commons.service.AbstractService;
import com.omp.commons.util.APKInfomation;
import com.omp.commons.util.APKUtil;
import com.omp.commons.util.FileUtil;
import com.omp.commons.util.config.ConfigProperties;

public class ScVersionManageServiceImpl
	extends AbstractService {

	private SimpleDateFormat	SDF	= new SimpleDateFormat("yyyyMMdd");
	
	@SuppressWarnings("unchecked")
	public PagenateList<CoreAppGroup>	searchCoreAppGroupList(CoreAppGroupSearch sc) {
		try {
			return (PagenateList<CoreAppGroup>)this.commonDAO.queryForPageList("Device.searchCoreAppGroupList", sc);
		} catch (Exception e) {
			throw new ServiceException("SC 관리그룹을 검색 하는데 실패 했습니다.", e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<CoreAppGroupDevice>	getCoreAppGroupDeviceList(CoreAppGroupDeviceSearch sc) {
		try {
			return (List<CoreAppGroupDevice>)this.commonDAO.queryForList("Device.getCoreAppGroupDeviceList", sc);
		} catch (Exception e) {
			throw new ServiceException("SC 관리그룹에 대한 단말기 정보를 조회 하는데 실패 했습니다.", e);
		}
	}
	
	public void saveCoreAppGroup(CoreAppGroupEdit edit) {
		try {
			String[]	pmcds;
			Long		groupId;

			this.setStep("StartTransaction");
			this.daoManager.startTransaction();
			// 그룹명 중복검사
			this.setStep("CheckDupGroupName");
			if (this.commonDAO.queryForObject("Device.checkDuplicateGroupName", edit) != null) {
				throw new NoticeException("이미 등록된 그룹 명입니다. 다른 그룹 명을 입력하세요");
			}
			// 그룹추가 혹은 수정
			if (edit.getCoreappGroupId() == null) {
				this.setStep("InsertGroup");
				groupId	= (Long)this.commonDAO.insert("Device.insertCoreAppGroup", edit);
				edit.setCoreappGroupId(groupId);
			} else {
				this.setStep("UpdateGroup");
				this.commonDAO.insert("Device.updateCoreAppGroup", edit);
			}
			groupId	= edit.getCoreappGroupId(); 
			pmcds	= edit.getPhoneModelCd();
			// 대상 단말기들 기존정보 삭제
			this.setStep("ClearGroupDevices");
			this.commonDAO.delete("Device.clearGroupDeviceList", groupId);
			if (pmcds != null && pmcds.length > 0) {
				// 타 그룹 단말기 상태 해제
				this.commonDAO.delete("Device.withdrawOtherGroupDeviceList", edit);
			}
			
			// 단말기 추가
			if (pmcds != null && pmcds.length > 0) {
				this.setStep("InsertGroupDevices");
				CoreAppGroupDevice	cagd	= new CoreAppGroupDevice();
				cagd.setCoreappGroupId(groupId);
				for (String pmcd : pmcds) {
					cagd.setPhoneModelCd(pmcd);
					this.commonDAO.insert("Device.insertCoreAppGroupDevice", cagd);
				}
			}
			this.setStep("CommitTransaction");
			this.daoManager.commitTransaction();
		} catch (NoticeException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException("SC 관리그룹을 저장하는데 실패 했습니다.", e);
		} finally {
			this.setStep("EndTransaction");
			this.daoManager.endTransaction();
		}
	}
	
	public CoreAppGroupEdit selectCoreAppGroupEdit(Long groupId) {
		try {
			return (CoreAppGroupEdit)this.commonDAO.queryForObject("Device.selectCoreAppGroupEdit", groupId);
		} catch (Exception e) {
			throw new ServiceException("SC 관리그룹을 조회하는데 실패 했습니다.", e);
		}
	}
	
	
	public int deleteCoreAppGroup(Long[] groupIds) {
		int	rv;
		
		rv	= 0;
		try {
			int	res;
			
			this.setStep("StartTransaction");
			this.daoManager.startTransaction();
			
			this.setStep("DeleteGroups");
			for (Long groupId: groupIds) {
				res	= this.commonDAO.delete("Device.deleteCoreAppGroup", groupId);
				if (res == 0) {
					throw new NoticeException("선택하신 그룹은 \"바이너리 관리\"에서 사용중이므로 삭제하실 수 없습니다.");
				}
				rv++;
				this.commonDAO.delete("Device.deleteCoreAppGroupHp", groupId);
			}
			this.setStep("CommitTransaction");
			this.daoManager.commitTransaction();
		} catch (NoticeException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException("SC 관리그룹을 삭제 하는데 실패 했습니다.", e);
		} finally {
			this.setStep("EndTransaction");
			this.daoManager.endTransaction();
		}
		return rv;
	}
	

	@SuppressWarnings("unchecked")
	public PagenateList<CoreAppList> searchCoreAppList(CoreAppSearch sc) {
		try {
			return (PagenateList<CoreAppList>)this.commonDAO.queryForPageList("Device.searchCoreAppList", sc);
		} catch (Exception e) {
			throw new ServiceException("SC 바이너리를 검색 하는데 실패 했습니다.", e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<CoreAppGroup>	searchCoreAppGroupList4Vermng(CoreAppSearch sc) {
		try {
			String[]	appTypes;
			
			appTypes	= sc.getAppTypes();
			if (appTypes == null || appTypes.length == 0) {
				return new ArrayList<CoreAppGroup>();
			} else {
				return (List<CoreAppGroup>)this.commonDAO.queryForPageList("Device.searchCoreAppGroupList4Vermng", sc);
			}
		} catch (Exception e) {
			throw new ServiceException("SC 바이너리 검색을 위한 관리그룹 목록을 조회 하는데 실패 했습니다.", e);
		}
	}
	
	
	
	
	
	public CoreApp	selectCoreAppInfo(Long coreappId) {
		try {
			return (CoreApp)this.commonDAO.queryForObject("Device.selectCoreAppInfo", coreappId);
		} catch (Exception e) {
			throw new ServiceException("SC 버전정보를 가져오는데 실패 했습니다.", e);
		}
	}
	
//	@SuppressWarnings("unchecked")
//	public List<CoreAppSupportDeviceInfo>	selectSupportDeviceList(Long coreappId) {
//		try {
//			return this.commonDAO.queryForList("Device.selectSupportDeviceList", coreappId);
//		} catch (Exception e) {
//			throw new ServiceException("SC 단말기 지원정보를 가져오는데 실패 했습니다.", e);
//		}
//	}
	
	public String getAppLastVersion(Long coreappGroupId) {
		try {
			return (String)this.commonDAO.queryForObject("Device.getAppLastVersion", coreappGroupId);
		} catch (Exception e) {
			throw new ServiceException("SC 단말기 지원정보를 가져오는데 실패 했습니다.", e);
		}
	}
	
	public Long saveCoreApp(CoreApp coreApp) {
		Long				rv;
		String				appPath;
		File				appFile;
		ConfigProperties	conf;
		String				coreappStoreBase;
		boolean				isChangeVersion;
		boolean				ischangeGroup;
		File				oldFile;
		File				newFile;
		String				maxVersion;
		
		rv					= coreApp.getCoreappId();
		conf				= new ConfigProperties();
		coreappStoreBase	= conf.getString("omp.common.path.http-share.coreapp");
		
		// 바이너리 검증
		if (coreApp.getAppFile() != null) {
			this.setStep("CheckBinary");
			try {
				this.verifyBinary(coreApp.getAppFile());
			} catch (NoticeException e) {
				throw e;
			} catch (Exception e) {
				throw new ServiceException("SC 바이너리를 저장하는데 실패했습니다.", e);
			}
		}
		
		this.setStep("StartTransaction");
		this.daoManager.startTransaction();
		try {
			// 버전 검증
			this.setStep("CheckDupVersion");
			
			CoreApp	verCoreapp = ((CoreApp)this.commonDAO.queryForObject("Device.selectCoreAppInfoByVer", coreApp));
			if (verCoreapp != null && !verCoreapp.getCoreappId().equals(rv)) {
				throw new NoticeException("입력하신 버전은 이미 존재하는 버전입니다. 확인 후 다시 입력하세요.");
			}
			if (rv == null) {
				maxVersion	= (String)this.commonDAO.queryForObject("Device.getAppLastVersion", coreApp.getCoreappGroupId());
				if (maxVersion != null && maxVersion.compareTo(coreApp.getVer()) >= 0) {
					throw new NoticeException("해당 그룹의 최신 버전은 {0}입니다. 최신 버전보다 높은 버전 번호를 입력하십시오.", new Object[] {maxVersion});
				}
			}
			
			// 기존 버전 정보 얻기
			this.setStep("GetOldVersion");
			CoreApp	oldCoreApp;
			if (rv == null) {
				oldCoreApp = null;
			} else {
				oldCoreApp	= (CoreApp)this.commonDAO.queryForObject("Device.selectCoreAppInfo", rv);
				if (oldCoreApp == null) {
					throw new NoticeException("수정 대상 자료가 존재하지 않습니다.");
				}
			}
			
			this.setStep("CheckUpdateDate");
			// 버전번호 변경 여부
			isChangeVersion	= oldCoreApp != null && !oldCoreApp.getVer().equals(coreApp.getVer());
			// 그룹 변경 여부
			ischangeGroup	= oldCoreApp != null && !oldCoreApp.getCoreappGroupId().equals(coreApp.getCoreappGroupId());
			if (ischangeGroup && !"DP005401".equals(oldCoreApp.getStatus())) {
				// 대기 상태가 아닌 바이너리는 그룹 변경 불가.
				throw new NoticeException("상용 혹은 테스트 상태의 바이너리는 그룹 변경이 불가합니다.");
			}
			
			// 업로드 파일 처리
			this.setStep("SaveUploadedFile");
			appPath	= "G" + coreApp.getCoreappGroupId() + ("DP002821".equals(coreApp.getAppType()) ? "_Client" : "_ARM")
				+ "_v" + coreApp.getVer() + "_"+ SDF.format(new Date()) + ".apk";
			appFile	= coreApp.getAppFile();
			oldFile	= null;
			newFile	= null;
			if (appFile == null) { // 업로드 파일 없을때
				// 버전 혹은 그룹 변경시 기존 파일 복사
				// 파일명 변경 하지 않는 이유는 변경 후에 일어나는 프로세스 실패시 파일명 롤백이 어렵기 때문임.
				if (isChangeVersion || ischangeGroup) {
					oldFile	= new File(coreappStoreBase, oldCoreApp.getAppPath());
					newFile	= new File(coreappStoreBase, appPath);
				}
			} else { // 업로드 파일 있을때
				oldFile	= appFile;
				newFile	= new File(coreappStoreBase, appPath);
			}
			if (oldFile != null) {
				try {
					newFile.getParentFile().mkdirs();
					FileUtil.copy(oldFile, newFile, true);
					if (this.log.isDebugEnabled()) {
						this.log.debug("코어앱 파일 {0}을 {1}에 저장.", new Object[] {oldFile, newFile});
					}
				} catch (IOException e) {
					throw new ServiceException("코어앱 파일 저장소 저장 실패", e);
				}
				coreApp.setAppPath(appPath);
			}
			
			try {
				if (rv == null) {	// coreapp 추가
					this.setStep("InsertVersion");
					rv	= (Long)this.commonDAO.insert("Device.insertCoreApp", coreApp);
				} else {			// coreapp 수정
					this.setStep("UpdateVersion");
					rv	= coreApp.getCoreappId();
					if (this.commonDAO.update("Device.updateCoreApp", coreApp) == 0) {
						throw new NoticeException("수정 대상 자료가 존재하지 않습니다.");
					}
				}
				this.setStep("CommitTransaction");
				this.daoManager.commitTransaction();
			} finally {
				if (oldFile != null) {
					// 모든 프로세스 종료시 이전 파일 삭제
					oldFile.delete();
				}
			}
		} catch (NoticeException e) {
			throw e;
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException("SC 바이너리를 저장하는데 실패했습니다.", e);
		} finally {
			this.setStep("EndTransaction");
			this.daoManager.endTransaction();
		}
		return rv;
	}
	
	
	public void changeStatus(Long[] coreappIds, String status, String oper) {
		
		try {
			CoreApp	ca	= new CoreApp();
			
			this.setStep("StartTransaction");
			this.daoManager.startTransaction();
			
			this.setStep("PrepareData");
			ca.setStatus(status);
			ca.setUpdId(oper);
			this.setStep("UpdateStatus");
			for (Long coreappId : coreappIds) {
				ca.setCoreappId(coreappId);
				this.commonDAO.update("Device.clearCoreappStatus", ca);
				if (this.commonDAO.update("Device.updateCoreappStatus", ca) == 0) {
					throw new NoticeException("변경 대상 자료가 존재하지 않습니다.");
				}
			}
			this.setStep("CommitTransaction");
			this.daoManager.commitTransaction();
		} catch (NoticeException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException("SC 바이너리의 상태를 변경하는데 실패했습니다.", e);
		} finally {
			this.setStep("EndTransaction");
			this.daoManager.endTransaction();
		}
	}
	
	private void verifyBinary(File apkFile)
		throws NoticeException, IOException, XmlPullParserException {
		APKInfomation apkInf;
		
		
		apkInf	= APKUtil.getAPKInfomation(apkFile);
		

		// 버전코드가 존재하지 않을 때
		String	versionCode;
		
		versionCode	= apkInf.getVersionCode(); 
		if (versionCode == null ) {
			throw new NoticeException("jsp.content.contentAndroidDevInfo.msg.subContent15");
		}
		
		// 버전코드값이 Integer값이 아닐 때 
		try {
			Integer.parseInt(versionCode);
		} catch (NumberFormatException ne) {
			throw new NoticeException("jsp.content.contentAndroidDevInfo.msg.subContent24");
		} 
		
		// 버전네임version name 이 존재하지 않을 때
		String	versionName;
		
		versionName	= apkInf.getVersionName();
		if (versionName == null) {
			throw new NoticeException("jsp.content.contentAndroidDevInfo.msg.subContent26");
		}
		
		// Is Signning Check 사이닝 되지 않았을 떄
		if (!apkInf.isSigned()) {
			throw new NoticeException("jsp.content.contentAndroidDevInfo.msg.subContent13");
		}
		
		// Is Debuggingg Check 디버그 모드일 때
		if (apkInf.isDebugSign()) {
			throw new NoticeException("jsp.content.contentAndroidDevInfo.msg.subContent13");
		}
		
		// 유효일 체크가 10,000 일 미만일 떄	
		ConfigProperties conf = new ConfigProperties();
		String limitDay = conf.getString("omp.admin.product.contents.prodBinary.limitDay", "10000");
		int intLimitDay = Integer.parseInt(limitDay);
		
		if (apkInf.getSignKeyValidityDays() < intLimitDay-1) {
			throw new NoticeException("jsp.content.contentAndroidDevInfo.msg.subContent09");
		}
		
		// 지원 SDK 버전 정보 수집
		List<CommCode>	sdkList;
		Set<String>		sdkVerSet;
		Set<String>		supportSdkVerSet;
		
		sdkList				= CacheCommCode.getCommCode(Constants.ANDROID_OS);
		sdkVerSet			= new HashSet<String>();
		supportSdkVerSet	= new HashSet<String>();
		for (CommCode cc: sdkList) {
			String	verCd;
			
			verCd	= cc.getAddField1();
			sdkVerSet.add(verCd);
			if (cc.isAvailable("support")) {
				supportSdkVerSet.add(verCd);
			}
		}
		
		
		// 지원하지않는 Min Os Version 일 때
		String		minVer;
		
		minVer	= apkInf.getMinSDKVersion();
		if (minVer == null || minVer.length() == 0) {
			minVer	= "1";
		}
		if(!supportSdkVerSet.contains(minVer)) {
			throw new NoticeException("jsp.content.contentAndroidDevInfo.msg.subContent12");
		}
		
		// 지원하지않는 Max Os Version 일 때
		String	maxVer;
		
		maxVer	= apkInf.getMaxSDKVersion();
		if(maxVer != null
				&& !supportSdkVerSet.contains(maxVer)) {
			throw new NoticeException("jsp.content.contentAndroidDevInfo.msg.subContent11");
		}
		
		// MaxSDKVersion 존재하고 MaxSdkVersion이 minSdkVersion 보다 작을 경우
		if (maxVer != null 
				&& Integer.parseInt(minVer) > Integer.parseInt(maxVer)) {
			throw new NoticeException("jsp.content.contentAndroidDevInfo.msg.subContent16");
		}
		
		// Target SDK Version값이 존재하고, Min SDK Version 보다 작을 경우
		String	targetVer;
		
		targetVer	= apkInf.getTargetSDKVersion();
		if(targetVer != null 
				&& Integer.parseInt(minVer) > Integer.parseInt(targetVer)) {
			throw new NoticeException("jsp.content.contentAndroidDevInfo.msg.subContent19");
		}
		
		// Target SDK Version값이 존재하고, MaxSDKVersion 존재하고, Max SDK Version 보다 클 경우
		if(targetVer != null  && maxVer != null 
				&& Integer.parseInt(targetVer) > Integer.parseInt(maxVer)) {
			throw new NoticeException("jsp.content.contentAndroidDevInfo.msg.subContent19");
		}


		// * Android OS 유효성 Check START
		// minSdkVersion 체크
		if (minVer != null && !sdkVerSet.contains(minVer)) {
			// Spec에 정의 되지 않은 값
			throw new NoticeException("jsp.content.contentAndroidDevInfo.msg.subContent20");
		}
		
		// maxSdkVersion 체크
		if (maxVer != null && !sdkVerSet.contains(maxVer)) {
			// Spec에 정의 되지 않은 값
			throw new NoticeException("jsp.content.contentAndroidDevInfo.msg.subContent21");
		}
		
		// targetSdkVersion 체크
		if (targetVer != null && !sdkVerSet.contains(targetVer)) {
			throw new NoticeException("jsp.content.contentAndroidDevInfo.msg.subContent22");
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<CoreApp>	searchCoreAppHistory(CoreAppHistorySearch sc) {
		try {
			return this.commonDAO.queryForList("Device.searchCoreappHistory", sc);
		} catch (Exception e) {
			throw new ServiceException("SC 바이너리 이력을 조회하는데 실패 했습니다.", e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<CoreAppGroupDevice>	getGroupDeviceList(Long groupId) {
		try {
			return (List<CoreAppGroupDevice>)this.commonDAO.queryForList("Device.getGroupDeviceList", groupId);
		} catch (Exception e) {
			throw new ServiceException("SC 관리 그룹의 단말기 목록을 조회하는데 실패 했습니다.", e);
		}
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Device> searchCoreappManageDevices(DeviceSearch sc) {
		try {
			return this.commonDAO.queryForList("Device.searchCoreappManageDevices", sc);
		} catch (Exception e) {
			throw new ServiceException("코어앱 관리 대상  단말기를 검색 하는데 실패 했습니다.", e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<CoreAppDevice> searchCoreappByDevice(CoreAppSearch sc) {
		try {
			return this.commonDAO.queryForList("Device.searchCoreappByDevice", sc);
		} catch (Exception e) {
			throw new ServiceException("코어앱 관리 대상 단말기를 검색 하는데 실패 했습니다.", e);
		}
		
	}
	
	public void updateSupportDeviceStatus(CoreAppSupport supportStatus) {
		this.setStep("StartTransaction");
		this.daoManager.startTransaction();
		try {
			this.setStep("ClearSupportDeviceStatus");
			this.commonDAO.update("Device.cleanSupportDeviceStatus", supportStatus);
			this.setStep("UpdateSupportDeviceStatus");
			if (this.commonDAO.update("Device.updateSupportDeviceStatus", supportStatus) == 0) {
				throw new NoticeException("설정 대상 코어앱이 존재하지 않습니다.");
			}
			this.setStep("CommitTransaction");
			this.daoManager.commitTransaction();
		} catch (NoticeException e) {
			throw e;
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException("코어어플 지원 단말 상태 과정 중 문제가 발생 하였습니다.", e);
		} finally {
			this.setStep("EndTransaction");
			this.daoManager.endTransaction();
		}
	}
}
