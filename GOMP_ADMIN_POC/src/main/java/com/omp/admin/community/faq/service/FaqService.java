package com.omp.admin.community.faq.service;

import java.sql.SQLException;
import java.util.List;

import com.omp.admin.common.model.FileSupport;
import com.omp.admin.community.faq.model.Ctgr;
import com.omp.admin.community.faq.model.Faq;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.StringUtil;

/**
 * 공지사항 비즈니스 로직 클래스
 * @author soohee
 * 
 */
public class FaqService extends AbstractService {

	private GLogger log = new GLogger(FaqService.class);

	public long getFaqIdSeq() {
		long result = 0;
		try {
			this.setStep("Community.selectSeqFaqId");
			result = (Long) this.commonDAO.queryForObject("Community.selectSeqFaqId");
		} catch (SQLException e) {
			throw new ServiceException("FAQ 카테고리 SEQ를 가져오기를 실패 하였습니다.", e);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Ctgr> selectCtgrList(Ctgr ctgr) {

		List<Ctgr> retCtgrList = null;
		try {
			this.setStep("Community.selectCtgrList");
			retCtgrList = this.commonDAO.queryForList("Community.selectCtgrList", ctgr);
		} catch (SQLException e) {
			throw new ServiceException("FAQ 카테고리 리스트를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return retCtgrList;
	}

	public int processCtgr(List<Ctgr> ctgrList) {

		int nResult = 0;

		try {

			if (ctgrList.size() < 1) {
				return nResult;
			}

			this.setStep("startTransaction");
			daoManager.startTransaction();

			Ctgr ctgr = null;
			for (int i = 0; i < ctgrList.size(); i++) {
				ctgr = new Ctgr();
				ctgr = (Ctgr) ctgrList.get(i);
				if ("INS".equalsIgnoreCase(ctgr.getGubun())) {
					this.setStep("Community.insertCtgr");
					this.commonDAO.insert("Community.insertCtgr", ctgr);
				} else if ("UPD".equalsIgnoreCase(ctgr.getGubun())) {
					this.setStep("Community.updateCtgr");
					this.commonDAO.update("Community.updateCtgr", ctgr);
				} else if ("DEL".equalsIgnoreCase(ctgr.getGubun())) {
					this.setStep("Community.updateCtgrDelYn");
					this.commonDAO.update("Community.updateCtgrDelYn", ctgr);
				} else {
					log.info("Not Process Category : {0}", new Object[] { ctgr.getCtgrNm() });
				}
				nResult++;
			}

			this.setStep("commitTransaction");
			daoManager.commitTransaction();

		} catch (SQLException e) {
			throw new ServiceException("FAQ 카테고리를 추가,변경,삭제하는 동안 에러가 발생 하였습니다.", e);
		} finally {
			this.setStep("endTransaction");
			daoManager.endTransaction();
		}

		return nResult;
	}

	@SuppressWarnings("unchecked")
	public List<Faq> selectFaqList(Faq faq) {
		this.setStep("Community.selectFaqPagingList");
		return this.commonDAO.queryForPageList("Community.selectFaqPagingList", faq);
	}

	public Faq selectFaq(Faq faq) {

		Faq retFaq = null;

		try {
			this.setStep("Community.selectFaq");
			retFaq = (Faq) this.commonDAO.queryForObject("Community.selectFaq", faq);
		} catch (SQLException e) {
			throw new ServiceException("FAQ 내용을 가져오는 동안 에러가 발생 하였습니다.", e);
		}

		return retFaq;
	}

	public Faq insertFaq(Faq faq, List<FileSupport> fileSupportList) {

		Faq retFaq = null;
		FileSupport fileSupport = null;

		try {

			this.setStep("startTransaction");
			this.daoManager.startTransaction();

			this.setStep("Community.insertFaq");
			retFaq = (Faq) this.commonDAO.insert("Community.insertFaq", faq);

			if (fileSupportList != null) {
				for (int i = 0; i < fileSupportList.size(); i++) {
					fileSupport = fileSupportList.get(i);
					this.setStep("files.insertFileDownloadGrp");
					this.commonDAO.insert("files.insertFileDownloadGrp", fileSupport);
					this.setStep("files.insertFileDownload");
					this.commonDAO.insert("files.insertFileDownload", fileSupport);
				}
			}

			this.setStep("commitTransaction");
			this.daoManager.commitTransaction();

		} catch (SQLException e) {
			throw new ServiceException("FAQ 내용을 저장하는 동안 에러가 발생 하였습니다.", e);
		} finally {
			this.setStep("endTransaction");
			this.daoManager.endTransaction();
		}

		return retFaq;
	}

	public int updateFaq(Faq faq, List<FileSupport> fileSupportList, String sDelUploadedFid) {

		int nResult = 0;
		FileSupport fileSupport = null;

		try {

			this.setStep("startTransaction");
			this.daoManager.startTransaction();

			this.setStep("Community.updateFaq");
			nResult = this.commonDAO.update("Community.updateFaq", faq);

			if (!"".equals(StringUtil.nvlStr(sDelUploadedFid))) {
				FileSupport updateFileSupport = new FileSupport();
				String[] aDelUploadedFid = sDelUploadedFid.split(",");
				for (int i = 0; i < aDelUploadedFid.length; i++) {
					if (log.isDebugEnabled())
						log.debug("UseYN = N, FID : " + aDelUploadedFid[i]);
					updateFileSupport.setUseYn("N");
					updateFileSupport.setGid(faq.getGid());
					updateFileSupport.setFid(aDelUploadedFid[i]);
					this.setStep("files.updateFileDownloadGrp");
					this.commonDAO.update("files.updateFileDownloadGrp", updateFileSupport);
				}
			}

			if (fileSupportList != null) {
				for (int i = 0; i < fileSupportList.size(); i++) {
					fileSupport = fileSupportList.get(i);
					this.setStep("files.insertFileDownloadGrp");
					this.commonDAO.insert("files.insertFileDownloadGrp", fileSupport);
					this.setStep("files.insertFileDownload");
					this.commonDAO.insert("files.insertFileDownload", fileSupport);
				}
			}

			this.setStep("commitTransaction");
			this.daoManager.commitTransaction();

		} catch (SQLException e) {
			throw new ServiceException("FAQ 내용을 변경하는 동안 에러가 발생 하였습니다.", e);
		} finally {
			this.setStep("endTransaction");
			this.daoManager.endTransaction();
		}

		return nResult;
	}

	public int updateFaqDelYn(Faq faq) {

		int nResult = 0;

		try {

			this.setStep("startTransaction");
			this.daoManager.startTransaction();

			if (faq.getSelectedFaqId().indexOf(",") > 0) {

				String[] arrSelectedFaqId = faq.getSelectedFaqId().split(",");
				for (int i = 0; i < arrSelectedFaqId.length; i++) {

					faq.setFaqId(Long.parseLong(arrSelectedFaqId[i]));

					if (log.isDebugEnabled())
						log.debug("faq.getFaqId() : " + faq.getFaqId());

					this.setStep("Community.updateFaqDelYn");
					nResult = nResult + this.commonDAO.update("Community.updateFaqDelYn", faq);
				}

			} else {

				faq.setFaqId(Long.parseLong(faq.getSelectedFaqId()));
				this.setStep("Community.updateFaqDelYn");
				nResult = this.commonDAO.update("Community.updateFaqDelYn", faq);

			}

			this.setStep("commitTransaction");
			this.daoManager.commitTransaction();

		} catch (SQLException e) {
			throw new ServiceException("FAQ 내용을 삭제하는 동안 에러가 발생 하였습니다.", e);
		} finally {
			this.setStep("endTransaction");
			this.daoManager.endTransaction();
		}

		return nResult;
	}

	public int updateFaqOpenYn(Faq faq) {

		int nResult = 0;

		try {

			this.setStep("startTransaction");
			this.daoManager.startTransaction();

			if (faq.getSelectedFaqId().indexOf(",") > 0) {

				String[] arrSelectedFaqId = faq.getSelectedFaqId().split(",");
				for (int i = 0; i < arrSelectedFaqId.length; i++) {

					faq.setFaqId(Long.parseLong(arrSelectedFaqId[i]));

					if (log.isDebugEnabled())
						log.debug("faq.getFaqId() : " + faq.getFaqId());

					this.setStep("Community.updateFaqOpenYn");
					nResult = nResult + this.commonDAO.update("Community.updateFaqOpenYn", faq);
				}

			} else {

				faq.setFaqId(Long.parseLong(faq.getSelectedFaqId()));
				this.setStep("Community.updateFaqOpenYn");
				nResult = this.commonDAO.update("Community.updateFaqOpenYn", faq);

			}

			this.setStep("commitTransaction");
			this.daoManager.commitTransaction();

		} catch (SQLException e) {
			throw new ServiceException("FAQ 내용을 노출/숨김하는 동안 에러가 발생 하였습니다.", e);
		} finally {
			this.setStep("endTransaction");
			daoManager.endTransaction();
		}

		return nResult;
	}

	public int updateFaqSortList(Faq faq, String[] aSortFaqId, String[] aSort) {

		int nResult = 0;

		try {

			this.setStep("startTransaction");
			this.daoManager.startTransaction();

			for (int i = 0; i < aSortFaqId.length; i++) {

				faq.setFaqId(Long.parseLong(aSortFaqId[i].trim()));
				faq.setSort(aSort[i].trim());

				if (log.isDebugEnabled())
					log.debug("faq.getFaqId() : " + faq.getFaqId() + ", faq.getSort() : " + faq.getSort());

				this.setStep("Community.updateFaqSort");
				nResult = nResult + this.commonDAO.update("Community.updateFaqSort", faq);

			}

			this.setStep("commitTransaction");
			this.daoManager.commitTransaction();

		} catch (SQLException e) {
			throw new ServiceException("FAQ 내용의 순서를 변경하는 동안 에러가 발생 하였습니다.", e);
		} finally {
			this.setStep("endTransaction");
			daoManager.endTransaction();
		}

		return nResult;
	}

}
