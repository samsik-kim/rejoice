package com.omp.admin.community.notice.service;

import java.sql.SQLException;
import java.util.List;

import com.omp.admin.common.model.FileSupport;
import com.omp.admin.community.notice.model.Notice;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.StringUtil;

/**
 * 공지사항 비즈니스 로직 클래스
 * @author soohee
 * 
 */
public class NoticeService extends AbstractService {

	private GLogger log = new GLogger(NoticeService.class);

	public long getNoticeIdSeq() {
		long result = 0;
		try {
			this.setStep("Community.selectSeqNoticeId");
			result = (Long) this.commonDAO.queryForObject("Community.selectSeqNoticeId");
		} catch (SQLException e) {
			throw new ServiceException("공지사항 SEQ를 가져오기를 실패 하였습니다 : " + e);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Notice> selectNoticePagingList(Notice notice) {
		this.setStep("Community.selectNoticePagingList");
		return this.commonDAO.queryForPageList("Community.selectNoticePagingList", notice);
	}

	@SuppressWarnings("unchecked")
	public List<Notice> selectNoticeList(Notice notice) {
		List<Notice> retNoticeList = null;
		try {
			this.setStep("Community.selectNoticeList");
			retNoticeList = this.commonDAO.queryForList("Community.selectNoticeList", notice);
		} catch (SQLException e) {
			throw new ServiceException("공지 목록을 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return retNoticeList;
	}

	public Notice selectNotice(Notice notice) {
		Notice retNotice = null;
		try {
			this.setStep("Community.selectNotice");
			retNotice = (Notice) this.commonDAO.queryForObject("Community.selectNotice", notice);
		} catch (SQLException e) {
			throw new ServiceException("공지 내용을 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return retNotice;
	}

	public Notice insertNotice(Notice notice, List<FileSupport> fileSupportList) {

		Notice retNotice = null;
		FileSupport fileSupport = null;

		try {

			this.setStep("startTransaction");
			this.daoManager.startTransaction();

			this.setStep("Community.insertNotice");
			retNotice = (Notice) this.commonDAO.insert("Community.insertNotice", notice);

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
			throw new ServiceException("공지 내용을 저장하는 동안 에러가 발생 하였습니다.", e);
		} finally {
			this.setStep("endTransaction");
			this.daoManager.endTransaction();
		}

		return retNotice;
	}

	public int updateNotice(Notice notice, List<FileSupport> fileSupportList, String sDelUploadedFid) {

		int nResult = 0;
		FileSupport fileSupport = null;

		try {

			this.setStep("startTransaction");
			this.daoManager.startTransaction();

			if (notice.getSelectedNoticeId().indexOf(',') > 0) {

				String[] arrSelectedNoticeId = notice.getSelectedNoticeId().split(",");
				for (int i = 0; i < arrSelectedNoticeId.length; i++) {

					notice.setNoticeId(Long.parseLong(arrSelectedNoticeId[i]));

					if (log.isDebugEnabled())
						log.debug("notice.getNoticeId() : " + notice.getNoticeId());

					this.setStep("Community.updateNotice");
					nResult = nResult + this.commonDAO.update("Community.updateNotice", notice);
				}

			} else {

				notice.setNoticeId(Long.parseLong(notice.getSelectedNoticeId()));
				this.setStep("Community.updateNotice");
				nResult = this.commonDAO.update("Community.updateNotice", notice);

				if (!"".equals(StringUtil.nvlStr(sDelUploadedFid))) {
					FileSupport updateFileSupport = new FileSupport();
					String[] aDelUploadedFid = sDelUploadedFid.split(",");
					for (int i = 0; i < aDelUploadedFid.length; i++) {
						if (log.isDebugEnabled())
							log.debug("UseYN = N, FID : " + aDelUploadedFid[i]);
						updateFileSupport.setUseYn("N");
						updateFileSupport.setGid(notice.getGid());
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
			}

			this.setStep("commitTransaction");
			this.daoManager.commitTransaction();

		} catch (Exception e) {
			throw new ServiceException("공지 내용을 변경하는 동안 에러가 발생 하였습니다.", e);
		} finally {
			this.setStep("endTransaction");
			this.daoManager.endTransaction();
		}

		return nResult;
	}

	public int deleteNotice(Notice notice) {
		int cnt = 0;
		try {
			this.setStep("Community.deleteNotice");
			cnt = this.commonDAO.delete("Community.deleteNotice", notice);
		} catch (SQLException e) {
			throw new ServiceException("공지 내용을 삭제하는 동안 에러가 발생 하였습니다.", e);
		}
		return cnt;
	}

}
