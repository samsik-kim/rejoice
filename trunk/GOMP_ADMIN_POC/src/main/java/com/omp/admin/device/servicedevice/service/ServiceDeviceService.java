package com.omp.admin.device.servicedevice.service;

import java.sql.SQLException;
import java.util.List;

import com.omp.admin.device.servicedevice.model.PhoneInfo;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;

/**
 * Announce 비즈니스 로직 클래스
 * @author soohee
 * 
 */
public class ServiceDeviceService extends AbstractService {

	//private GLogger log = new GLogger(AnnounceService.class);

	@SuppressWarnings("unchecked")
	public List<PhoneInfo> selectPhoneInfoPagingList(PhoneInfo phoneInfo) {
		return this.commonDAO.queryForPageList("Device.selectPhoneInfoPagingList", phoneInfo);
	}
	
	@SuppressWarnings("unchecked")
	public List<PhoneInfo> selectPopPhoneInfoPagingList(PhoneInfo phoneInfo) {
		return this.commonDAO.queryForPageList("Device.selectPopPhoneInfoPagingList", phoneInfo);
	}	

	@SuppressWarnings("unchecked")
	public List<PhoneInfo> selectPhoneInfoList(PhoneInfo phoneInfo) {
		List<PhoneInfo> retPhoneInfoList = null;
		try {
			retPhoneInfoList = this.commonDAO.queryForList("Device.selectPhoneInfoList", phoneInfo);
		} catch (SQLException e) {
			throw new ServiceException("서비스 단말 리스트를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return retPhoneInfoList;
	}

	public PhoneInfo selectPhoneInfo(PhoneInfo phoneInfo) {
		PhoneInfo retPhoneInfo = null;
		try {
			retPhoneInfo = (PhoneInfo) this.commonDAO.queryForObject("Device.selectPhoneInfo", phoneInfo);
		} catch (SQLException e) {
			throw new ServiceException("서비스 단말 정보를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return retPhoneInfo;
	}

	public int selectPhoneInfoCnt(PhoneInfo phoneInfo) {
		int retCnt = 0;
		try {
			retCnt = (Integer) this.commonDAO.queryForObject("Device.selectPhoneInfoCnt", phoneInfo);
		} catch (SQLException e) {
			throw new ServiceException("서비스 단말 정보를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return retCnt;
	}

	public PhoneInfo insertPhoneInfo(PhoneInfo phoneInfo) {
		PhoneInfo retPhoneInfo = null;
		try {
			retPhoneInfo = (PhoneInfo) this.commonDAO.insert("Device.insertPhoneInfo", phoneInfo);
		} catch (SQLException e) {
			throw new ServiceException("서비스 단말 정보를 저장하는 동안 에러가 발생 하였습니다.", e);
		}
		return retPhoneInfo;
	}

	public int updatePhoneInfo(PhoneInfo phoneInfo) {
		int cnt = 0;
		try {
			cnt = this.commonDAO.update("Device.updatePhoneInfo", phoneInfo);
		} catch (SQLException e) {
			throw new ServiceException("서비스 단말 정보를 변경하는 동안 에러가 발생 하였습니다.", e);
		}
		return cnt;
	}

	public int updatePhoneInfoDelYn(PhoneInfo phoneInfo) {

		int retCnt = 0;

		try {

			this.daoManager.startTransaction();

			if (phoneInfo.getPhoneModelCd().indexOf(',') > 0) {

				String[] arrSelectedPhoneModelCd = phoneInfo.getPhoneModelCd().split(",");
				for (int i = 0; i < arrSelectedPhoneModelCd.length; i++) {

					phoneInfo.setPhoneModelCd(arrSelectedPhoneModelCd[i]);

					if (log.isDebugEnabled())
						log.debug("phoneInfo.getPhoneModelCd() : " + phoneInfo.getPhoneModelCd());

					retCnt = this.commonDAO.update("Device.updatePhoneInfoDelYn", phoneInfo);

				}

			} else {

				retCnt = this.commonDAO.update("Device.updatePhoneInfoDelYn", phoneInfo);

			}

			this.daoManager.commitTransaction();

		} catch (SQLException e) {
			throw new ServiceException("서비스 단말 정보를 삭제하는 동안 에러가 발생 하였습니다.", e);
		} finally {
			this.daoManager.endTransaction();
		}

		return retCnt;
	}

}
