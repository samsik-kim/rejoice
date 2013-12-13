package com.omp.admin.adminmgr.code.service;

import java.sql.SQLException;
import java.util.List;

import com.omp.admin.adminmgr.code.model.CommCd;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;

public class CommonCodeService extends AbstractService {

	@SuppressWarnings("unchecked")
	public List<CommCd> selectCommCdList(CommCd commCd) {
		this.setStep("AdminMgr.selectCommCdPagingList");
		return this.commonDAO.queryForPageList("AdminMgr.selectCommCdPagingList", commCd);
	}

	public int selectGroupInfoCnt(CommCd commCd) {
		int retCnt = 0;
		try {
			retCnt = (Integer) this.commonDAO.queryForObject("AdminMgr.selectGroupCdCount", commCd);
		} catch (SQLException e) {
			throw new ServiceException("공통코드를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return retCnt;
	}  
	
	public CommCd selectCommCd(CommCd commCd) {
		CommCd retCommCd = null;
		try {
			this.setStep("AdminMgr.selectCommCd");
			retCommCd = (CommCd) this.commonDAO.queryForObject("AdminMgr.selectCommCd", commCd);
		} catch (SQLException e) {
			throw new ServiceException("공통코드를 가져오는 동안 에러가 발생 하였습니다.", e);
		} catch (Exception e) {
			throw new ServiceException("공통코드를 가져오는 동안 에러가 발생 하였습니다..", e);
		}
		return retCommCd;
	}

	public CommCd insertCommCd(CommCd commCd) {
		CommCd retCommCd = null;
		try {
			this.setStep("AdminMgr.insertCommCd");
			retCommCd = (CommCd) this.commonDAO.insert("AdminMgr.insertCommCd", commCd);
		} catch (SQLException e) {
			throw new ServiceException("공통코드를 저장하는 동안 에러가 발생 하였습니다.", e);
		}
		return retCommCd;
	}

	public int updateCommCd(CommCd commCd) {
		int cnt = 0;
		try {
			this.setStep("AdminMgr.updateCommCd");
			cnt = this.commonDAO.update("AdminMgr.updateCommCd", commCd);
		} catch (SQLException e) {
			throw new ServiceException("공통코드를 변경하는 동안 에러가 발생 하였습니다.", e);
		}
		return cnt;
	}

	public int updateCommCdUseYn(CommCd commCd) {

		int cnt = 0;

		try {

			this.setStep("startTransaction");
			this.daoManager.startTransaction();

			if (commCd.getSelectedGrpCd().indexOf(',') > 0) {

				String[] arrSelectedGrpCd = commCd.getSelectedGrpCd().split(",");
				String[] arrSelectedDtlCd = commCd.getSelectedDtlCd().split(",");
				for (int i = 0; i < arrSelectedGrpCd.length; i++) {
					commCd.setGrpCd(arrSelectedGrpCd[i]);
					commCd.setDtlCd(arrSelectedDtlCd[i]);

					this.setStep("AdminMgr.updateCommCdUseYn");
					cnt = cnt + this.commonDAO.update("AdminMgr.updateCommCdUseYn", commCd);
				}

			} else {
				commCd.setGrpCd(commCd.getSelectedDtlCd());
				commCd.setDtlCd(commCd.getSelectedDtlCd());

				this.setStep("AdminMgr.updateCommCdUseYn");
				cnt = this.commonDAO.update("AdminMgr.updateCommCdUseYn", commCd);
			}

			this.setStep("commitTransaction");
			this.daoManager.commitTransaction();

		} catch (SQLException e) {
			throw new ServiceException("공통코드의 사용여부를 변경하는 동안 에러가 발생 하였습니다.", e);
		} finally {
			this.setStep("endTransaction");
			this.daoManager.endTransaction();
		}
		return cnt;
	}

	public int deleteCommCd(CommCd commCd) {
		int cnt = 0;
		try {
			this.setStep("AdminMgr.deleteCommCd");
			cnt = this.commonDAO.delete("AdminMgr.deleteCommCd", commCd);
		} catch (SQLException e) {
			throw new ServiceException("공통코드를 삭제하는 동안 에러가 발생 하였습니다.", e);
		}
		return cnt;
	}

}
