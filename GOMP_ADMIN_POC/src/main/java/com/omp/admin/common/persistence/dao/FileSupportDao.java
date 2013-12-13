package com.omp.admin.common.persistence.dao;

import java.util.List;

import com.ibatis.dao.client.DaoException;
import com.omp.admin.common.model.FileSupport;

public interface FileSupportDao {
	
	/**
	 * 다운로드 가능한 파일목록 조회
	 * @param model
	 * @return
	 * @throws DaoException
	 */
	public List<FileSupport> selectFileDownloadList(FileSupport model) throws DaoException;
	
	/**
	 * 다운로드 가능한 파일상세 조회
	 * @param model
	 * @return
	 * @throws DaoException
	 */
	public FileSupport selectFileDownload(FileSupport model) throws DaoException;
	
	/**
	 * 업로드한 파일정보 등록
	 * @param model
	 * @throws DaoException
	 */
	public void insertFileDownload(FileSupport model) throws DaoException;
	
	/**
	 * 업로드한 파일그룹정보 등록
	 * @param model
	 * @throws DaoException
	 */
	public void insertFileDownloadGrp(FileSupport model) throws DaoException;
	
	/**
	 * 업로드한 파일그룹정보 수정
	 * @param model
	 * @throws DaoException
	 */
	public void updateFileDownloadGrp(FileSupport model) throws DaoException;
	
	/**
	 * 파일정보 시퀀스 조회
	 * @param model
	 * @return
	 * @throws DaoException
	 */
	public Long selectFileUploadSeq() throws DaoException;
	
	/**
	 * 파일그룹정보 시퀀스 조회
	 * @param model
	 * @return
	 * @throws DaoException
	 */
	public Long selectFileUploadGrpSeq() throws DaoException;
	
	/**
	 * 업로드한 파일정보 수정
	 * @param model
	 * @throws DaoException
	 */
	public void updateFileDownload(FileSupport model) throws DaoException;
}