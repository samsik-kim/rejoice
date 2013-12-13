package com.omp.dev.community.persistence.dao;

import java.util.List;

import com.ibatis.dao.client.DaoException;
import com.omp.dev.community.model.Notice;

public interface NoticeDAO {

	/**
	 * Notice List Search
	 * 
	 * @param notice
	 * @return
	 * @throws DaoException
	 */
	public List<Notice> selectNoticeList(Notice notice) throws DaoException;


	/**
	 * Notice writing Hits Increase
	 * 
	 * @param notice
	 * @return
	 * @throws DaoException
	 */
	public boolean updateNoticeHit(Notice notice) throws DaoException;


	/**
	 * Notice writing Read
	 * 
	 * @param notice
	 * @return
	 * @throws DaoException
	 */
	public Notice selectNotice(Notice notice) throws DaoException;

}
