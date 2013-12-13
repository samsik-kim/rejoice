package com.omp.admin.contents.announce.service;

import java.sql.SQLException;
import java.util.List;

import com.omp.admin.contents.announce.model.DpAnoc;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;

/**
 * Announce 비즈니스 로직 클래스
 * @author soohee
 * 
 */
public class AnnounceService extends AbstractService {

	//private GLogger log = new GLogger(AnnounceService.class);

	@SuppressWarnings("unchecked")
	public List<DpAnoc> selectAnnounceList(DpAnoc dpAnoc) {
		return this.commonDAO.queryForPageList("Contents.selectAnnounceList", dpAnoc);
	}

	public DpAnoc selectAnnounce(DpAnoc dpAnoc) {
		DpAnoc retDpAnoc = null;
		try {
			retDpAnoc = (DpAnoc) this.commonDAO.queryForObject("Contents.selectAnnounce", dpAnoc);
		} catch (SQLException e) {
			throw new ServiceException("서비스 점검 내용을 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return retDpAnoc;
	}

	public DpAnoc insertAnnounce(DpAnoc dpAnoc) {
		DpAnoc retDpAnoc = null;
		try {
			retDpAnoc = (DpAnoc) this.commonDAO.insert("Contents.insertAnnounce", dpAnoc);
		} catch (SQLException e) {
			throw new ServiceException("서비스 점검 내용을 저장하는 동안 에러가 발생 하였습니다.", e);
		}
		return retDpAnoc;
	}

	public int updateAnnounce(DpAnoc dpAnoc) {
		int cnt = 0;
		try {
			cnt = this.commonDAO.update("Contents.updateAnnounce", dpAnoc);
		} catch (SQLException e) {
			throw new ServiceException("서비스 점검 내용을 변경하는 동안 에러가 발생 하였습니다.", e);
		}
		return cnt;
	}

	public int deleteAnnounce(DpAnoc dpAnoc, String selectedAnocSeq) {

		int cnt = 0;

		try {

			this.daoManager.startTransaction();

			if (selectedAnocSeq.indexOf(',') > 0) {

				String[] arrSelectedAnocSeq = selectedAnocSeq.split(",");
				for (int i = 0; i < arrSelectedAnocSeq.length; i++) {

					dpAnoc.setAnocSeq(Integer.parseInt(arrSelectedAnocSeq[i]));

					if (log.isDebugEnabled())
						log.debug("dpAnoc.getAnocSeq() : " + dpAnoc.getAnocSeq());

					cnt = cnt + this.commonDAO.delete("Contents.deleteAnnounce", dpAnoc);

				}

			} else {

				dpAnoc.setAnocSeq(Integer.parseInt(selectedAnocSeq));
				cnt = this.commonDAO.delete("Contents.deleteAnnounce", dpAnoc);

			}

			this.daoManager.commitTransaction();

		} catch (SQLException e) {
			throw new ServiceException("서비스 점검 내용을 삭제하는 동안 에러가 발생 하였습니다.", e);
		} finally {
			this.daoManager.endTransaction();
		}
		return cnt;
	}

}
