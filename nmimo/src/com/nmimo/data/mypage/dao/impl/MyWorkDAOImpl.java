package com.nmimo.data.mypage.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.nmimo.data.mypage.dao.MyWorkDAO;
import com.nmimo.data.mypage.info.MyWorkInfo;

@Repository
public class MyWorkDAOImpl implements MyWorkDAO {

	private static final String NAME_SPACE = "mywork";
	
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
	public List<MyWorkInfo> selectWorkList(MyWorkInfo info) {
		return (List<MyWorkInfo>) sqlMapClientTemplate.queryForList(NAME_SPACE + ".selectGenlMsgWrkBasList", info);
	}

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param info
	 * @return
	 */
	@Override
	public int selectTotalCnt(MyWorkInfo info) {
		return (Integer) sqlMapClientTemplate.queryForObject(NAME_SPACE + ".selectTotalCnt", info);
	}

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param info
	 * @return
	 */
	@Override
	public MyWorkInfo selectWorkDetail(MyWorkInfo info) {
		return (MyWorkInfo) sqlMapClientTemplate.queryForObject(NAME_SPACE + ".selectWorkDetail", info);
	}
}
