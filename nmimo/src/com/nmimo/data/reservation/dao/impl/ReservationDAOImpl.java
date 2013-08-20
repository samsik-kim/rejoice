package com.nmimo.data.reservation.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.nmimo.data.reservation.dao.ReservationDAO;
import com.nmimo.data.reservation.info.ReservationInfo;
import com.nmimo.data.review.info.BfacRegBasInfo;

@Repository
public class ReservationDAOImpl implements ReservationDAO {

	private static final String NAME_SPACE = "reservation";
	
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
	public ReservationInfo selectBfacDetail(int seq) {
		return (ReservationInfo) sqlMapClientTemplate.queryForObject(NAME_SPACE + ".selectBfacDetail", seq);
	}

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param info
	 * @return
	 */
	@Override
	public int selectTotalBfac(ReservationInfo info) {
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
	public List<ReservationInfo> selectBfacRegList(ReservationInfo info) {
		return sqlMapClientTemplate.queryForList(NAME_SPACE + ".selectBfacList", info);
	}
}
