package com.omp.dev.community.persistence.dao;

import java.util.List;

import com.ibatis.dao.client.DaoException;
import com.ibatis.dao.client.DaoManager;
import com.omp.commons.persistence.dao.CommonDAOImpl;
import com.omp.commons.util.GLogger;
import com.omp.dev.community.model.Notice;

public class NoticeDAOImpl extends CommonDAOImpl implements NoticeDAO {
	@SuppressWarnings("unused")
	private static GLogger logger = new GLogger(NoticeDAOImpl.class);


	public NoticeDAOImpl(DaoManager daoManager) {
		super(daoManager);
	}


	/**
	 * Notice List Search
	 * 
	 * @param notice
	 * @return
	 * @throws DaoExceptionotice	 */
	@SuppressWarnings("unchecked")
	public List<Notice> selectNoticeList(Notice notice) throws DaoException {
		return queryForList("Community_Notice.selectNoticeList", notice);
	}


	/**
	 * Notice writing Hits Increase
	 * 
	 * @param notice
	 * @return
	 * @throws DaoException
	 */
	public boolean updateNoticeHit(Notice notice) throws DaoException {
		int affect = update("Community_Notice.updateNoticeHit", notice);
		return (affect == 1);
	}


	/**
	 * Notice writing Read
	 * 
	 * @param notice
	 * @return
	 * @throws DaoException
	 */
	public Notice selectNotice(Notice notice) throws DaoException {
		return (Notice)queryForObject("Community_Notice.selectNotice", notice);
	}

}
