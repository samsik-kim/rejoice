package com.nmimo.data.review.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.nmimo.data.mypage.info.MyWorkInfo;
import com.nmimo.data.review.dao.ReviewDAO;
import com.nmimo.data.review.info.BanrInfo;
import com.nmimo.data.review.info.BfacRegBasInfo;
import com.nmimo.data.review.info.ReviewInfo;

@Repository
public class ReviewDAOImpl implements ReviewDAO {

	private static final String NAME_SPACE = "review";
	
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;
	
	/**
	 * <pre>
	 *
	 * </pre>
	 * @param info
	 * @return
	 */
	@Override
	public int selectTotalCnt(ReviewInfo info) {
		return (Integer) sqlMapClientTemplate.queryForObject(NAME_SPACE + ".selectTotalCnt", info);
	}
	
	/**
	 * <pre>
	 *
	 * </pre>
	 * @param info
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ReviewInfo> selectWorkList(ReviewInfo info) {
		return (List<ReviewInfo>) sqlMapClientTemplate.queryForList(NAME_SPACE + ".selectGenlMsgWrkBasList", info);
	}
	
	/**
	 * <pre>
	 *
	 * </pre>
	 * @param info
	 * @return
	 */
	@Override
	public ReviewInfo selectWorkDetail(ReviewInfo info) {
		return (ReviewInfo) sqlMapClientTemplate.queryForObject(NAME_SPACE + ".selectWorkDetail", info);
	}	
	
	
	/**
	 * <pre>
	 *
	 * </pre>
	 * @param info
	 * @return
	 */
	@Override
	public int selectTotalBanrCnt(BanrInfo info) {
		return (Integer) sqlMapClientTemplate.queryForObject(NAME_SPACE + ".selectTotalBanr", info);
	}

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param info
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BanrInfo> selectBanrList(BanrInfo info) {
		return sqlMapClientTemplate.queryForList(NAME_SPACE + ".selectBanrList", info);
	}

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param info
	 * @return
	 */
	@Override
	public BanrInfo selectBanrDetail(int seq) {
		return (BanrInfo) sqlMapClientTemplate.queryForObject(NAME_SPACE + ".selectBanrDetail", seq);
	}

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param info
	 * @return
	 */
	@Override
	public int selectTotalBfac(BfacRegBasInfo info) {
		return (Integer) sqlMapClientTemplate.queryForObject(NAME_SPACE + ".selectTotalBfac", info);
	}

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param info
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BfacRegBasInfo> selectBfacRegList(BfacRegBasInfo info) {
		return sqlMapClientTemplate.queryForList(NAME_SPACE + ".selectBfacList", info);
	}

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param info
	 * @return
	 */
	@Override
	public BfacRegBasInfo selectBfacDetail(int seq) {
		return (BfacRegBasInfo) sqlMapClientTemplate.queryForObject(NAME_SPACE + ".selectBfacDetail", seq);
	}
	
}