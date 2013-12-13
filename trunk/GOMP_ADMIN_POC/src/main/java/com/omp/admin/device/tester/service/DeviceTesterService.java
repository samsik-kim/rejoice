package com.omp.admin.device.tester.service;

import java.util.List;

import com.omp.admin.device.tester.model.PhoneTester;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;

public class DeviceTesterService extends AbstractService {

	//private GLogger log = new GLogger(AnnounceService.class);

	@SuppressWarnings("unchecked")
	public List<PhoneTester> selectPhoneTesterPagingList(PhoneTester phoneTester) {
		return this.commonDAO.queryForPageList("Device.selectPhoneTesterPagingList", phoneTester);
	}

	public PhoneTester insertPhoneTester(PhoneTester phoneTester) {

		PhoneTester retPhoneTester = null;

		try {

			daoManager.startTransaction();

			this.commonDAO.delete("Device.deletePhoneTester", phoneTester);
			retPhoneTester = (PhoneTester) this.commonDAO.insert("Device.insertPhoneTester", phoneTester);

			daoManager.commitTransaction();

		} catch (Exception ex) {
			throw new ServiceException("테스트 아이디 등록 실패.", ex);
		} finally {
			daoManager.endTransaction();
		}

		return retPhoneTester;
	}

	public int deletePhoneTester(PhoneTester phoneTester) {

		int retCnt = 0;

		try {

			daoManager.startTransaction();

			if (phoneTester.getTesterId().indexOf(',') > 0) {

				String[] arrSelectedTesterId = phoneTester.getTesterId().split(",");
				for (int i = 0; i < arrSelectedTesterId.length; i++) {

					phoneTester.setTesterId(arrSelectedTesterId[i]);

					if (log.isDebugEnabled())
						log.debug("phoneTester.getTesterId() : " + phoneTester.getTesterId());

					retCnt = retCnt + this.commonDAO.delete("Device.deletePhoneTester", phoneTester);
				}

			} else {

				retCnt = this.commonDAO.delete("Device.deletePhoneTester", phoneTester);

			}

			daoManager.commitTransaction();

		} catch (Exception ex) {
			throw new ServiceException("테스트 아이디 삭제 실패.", ex);
		} finally {
			daoManager.endTransaction();
		}

		return retCnt;
	}
}
