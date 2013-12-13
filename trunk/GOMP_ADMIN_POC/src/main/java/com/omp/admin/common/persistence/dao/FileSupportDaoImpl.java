package com.omp.admin.common.persistence.dao;

import java.util.List;

import com.ibatis.dao.client.DaoException;
import com.ibatis.dao.client.DaoManager;
import com.omp.commons.persistence.dao.CommonDAOImpl;
import com.omp.admin.common.model.FileSupport;

public class FileSupportDaoImpl extends CommonDAOImpl implements FileSupportDao {

	// private GLogger logger = new GLogger(FileSupportDaoImpl.class);

	public FileSupportDaoImpl(DaoManager daoManager) {
		super(daoManager);
	}

	@SuppressWarnings("unchecked")
	public List<FileSupport> selectFileDownloadList(FileSupport model) throws DaoException {
		return (List<FileSupport>) queryForList("files.selectFileDownloadList", model);
	}

	public FileSupport selectFileDownload(FileSupport model) throws DaoException {
		return (FileSupport) queryForObject("files.selectFileDownload", model);
	}

	public void insertFileDownload(FileSupport model) throws DaoException {
		insert("files.insertFileDownload", model);
	}

	public void insertFileDownloadGrp(FileSupport model) throws DaoException {
		insert("files.insertFileDownloadGrp", model);
	}

	public void updateFileDownloadGrp(FileSupport model) throws DaoException {
		update("files.updateFileDownloadGrp", model);
	}

	public Long selectFileUploadSeq() throws DaoException {
		return (Long) queryForObject("files.selectFileUploadSeq");
	}

	public Long selectFileUploadGrpSeq() throws DaoException {
		return (Long) queryForObject("files.selectFileUploadGrpSeq");
	}

	public void updateFileDownload(FileSupport model) throws DaoException {
		update("files.updateFileDownload", model);
	}

}