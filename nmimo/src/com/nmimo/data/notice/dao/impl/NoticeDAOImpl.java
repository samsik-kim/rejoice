package com.nmimo.data.notice.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.nmimo.data.notice.dao.NoticeDAO;
import com.nmimo.data.notice.info.NoticeInfo;

@Repository
public class NoticeDAOImpl implements NoticeDAO {

	private static final String NAME_SPACE = "notice";
	
	@Autowired
	 private SqlMapClientTemplate sqlMapClientTemplate;
	
	/**
	 * <pre>
	 *
	 * </pre>
	 * @param info
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<NoticeInfo> selectNoticeList(NoticeInfo info) {
		return sqlMapClientTemplate.queryForList(NAME_SPACE + ".selectNoticeList", info);
	}

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param info
	 * @return
	 */
	@Override
	public int selectTotalNotice(NoticeInfo info) {
		return (Integer) sqlMapClientTemplate.queryForObject(NAME_SPACE + ".selectTotalNotice", info);
	}

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param info
	 * @return
	 */
	@Override
	public NoticeInfo selectNoticeDetail(int seq) {
		return (NoticeInfo) sqlMapClientTemplate.queryForObject(NAME_SPACE + ".selectNoticeDetail", seq);
	}
}
