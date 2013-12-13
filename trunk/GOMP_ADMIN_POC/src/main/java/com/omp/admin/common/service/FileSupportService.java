package com.omp.admin.common.service;

import java.util.List;

import com.ibatis.dao.client.DaoException;
import com.ibatis.dao.client.DaoManager;
import com.omp.admin.common.model.FileSupport;
import com.omp.admin.common.persistence.dao.FileSupportDao;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.persistence.dao.DaoConfig;
import com.omp.commons.util.GLogger;

public class FileSupportService {

	private static final GLogger logger = new GLogger(FileSupportService.class);

	private FileSupportDao dao;
	DaoManager manager = null;

	public FileSupportService() {
		manager = DaoConfig.getDaoManager();
		this.dao = (FileSupportDao) ((DaoConfig.getDaoManager()).getDao(FileSupportDao.class));
	}

	public List<FileSupport> selectFileDownloadList(FileSupport vo) throws Exception {
		logger.debug("FileSupportServiceImpl selectFileDownloadList");

		List<FileSupport> result = null;
		try {
			result = dao.selectFileDownloadList(vo);
		} catch (Exception e) {
			throw new ServiceException("다운로드가능한 파일목록 가져오기 실패.", e);
		}
		return result;
	}

	public FileSupport selectFileDownload(FileSupport vo) throws Exception {
		logger.debug("FileSupportServiceImpl selectFileDownload");

		FileSupport result = null;
		try {
			result = dao.selectFileDownload(vo);
		} catch (Exception e) {
			throw new ServiceException("다운로드가능한 파일 상세 가죠오기 실패.", e);
		}
		return result;
	}

	public FileSupport insertFileDownload(FileSupport vo) throws Exception {
		logger.debug("FileSupportServiceImpl insertFileDownload");

		try {
			/////////////////////////////////////
			manager.startTransaction();
			/////////////////////////////////////

			vo.setFid(dao.selectFileUploadSeq() + "");
			vo.setGid(dao.selectFileUploadGrpSeq() + "");

			// 파일그룹정보 등록
			dao.insertFileDownloadGrp(vo);
			// 파일정보 등록
			dao.insertFileDownload(vo);

			/////////////////////////////////////
			manager.commitTransaction();
			/////////////////////////////////////			
		} catch (Exception e) {
			throw new ServiceException("다운로드가능한 파일 수정 실패.", e);
		} finally {
			/////////////////////////////////////			
			manager.endTransaction();
			/////////////////////////////////////			
		}
		return vo;
	}

	public void updateFileDownloadGrp(FileSupport vo) throws Exception {
		logger.debug("FileSupportServiceImpl updateFileDownloadGrp");

		try {
			dao.updateFileDownloadGrp(vo);
		} catch (Exception e) {
			throw new ServiceException("업로드한 파일그룹정보 수정 실패.", e);
		}
	}

	/**
	 * 파일정보 시퀀스 조회
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public Long selectFileUploadSeq() throws Exception {
		logger.debug("FileSupportServiceImpl selectFileUploadSeq");
		long rtnValue;

		try {
			rtnValue = dao.selectFileUploadSeq();
		} catch (Exception e) {
			throw new ServiceException("파일정보 시퀀스 조회 실패.", e);
		}

		return rtnValue;
	}

	/**
	 * 파일그룹정보 시퀀스 조회
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public Long selectFileUploadGrpSeq() throws Exception {
		logger.debug("FileSupportServiceImpl selectFileUploadSeq");
		long rtnValue;

		try {
			rtnValue = dao.selectFileUploadGrpSeq();
		} catch (Exception e) {
			throw new ServiceException("파일그룹정보 시퀀스 조회 실패.", e);
		}

		return rtnValue;
	}

	/**
	 * 파일업로드 Grp 등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public FileSupport insertFileUploadGrp(FileSupport vo) throws Exception {
		logger.debug("FileSupportServiceImpl insertFileDownload");

		try {

			// 파일그룹정보 등록
			dao.insertFileDownloadGrp(vo);
		} catch (Exception e) {
			throw new ServiceException("업로드한 파일정보 수정 실패.", e);
		}
		return vo;
	}

	/**
	 * 파일업로드 등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public FileSupport insertFileUpload(FileSupport vo) throws Exception {
		logger.debug("FileSupportServiceImpl insertFileDownload");

		try {
			// 파일정보 등록
			dao.insertFileDownload(vo);

		} catch (Exception e) {
			throw new ServiceException("업로드한 파일정보 수정 실패.", e);
		}
		return vo;
	}

	/**
	 * 파일업로드 상태 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public void updateFileDownload(FileSupport vo) throws Exception {
		logger.debug("FileSupportServiceImpl updateFileDownload");

		try {
			dao.updateFileDownload(vo);
		} catch (Exception e) {
			throw new ServiceException("업로드한 파일정보 수정 실패.", e);
		}
	}
}