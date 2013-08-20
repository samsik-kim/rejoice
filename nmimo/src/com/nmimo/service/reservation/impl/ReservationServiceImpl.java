package com.nmimo.service.reservation.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nmimo.common.info.PageInfo;
import com.nmimo.data.reservation.dao.ReservationDAO;
import com.nmimo.data.reservation.info.ReservationInfo;
import com.nmimo.data.review.info.BfacRegBasInfo;
import com.nmimo.service.reservation.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationDAO dao;
	
	/**
	 * <pre>
	 *
	 * </pre>
	 * @param pageInfo
	 * @param info
	 * @return
	 */
	@Override
	public PageInfo selectBfacRegList(PageInfo pageInfo, ReservationInfo info) {
		int totalCnt = dao.selectTotalBfac(info);
		info.setTotalCount(totalCnt);
		List<ReservationInfo> list = dao.selectBfacRegList(info);
		// 게시물 목록 사이즈 조회 및 설정
		if (list != null && list.size() > 0) {
			// 총 갯수 설정
			pageInfo.setTotalCount(totalCnt);
			// 데이타 리스트 설정
			pageInfo.setDataList(list);
		} 
		return pageInfo;
	}

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param info
	 * @return
	 */
	@Override
	public ReservationInfo selectBfacDetail(int seq) {
		return dao.selectBfacDetail(seq);
	}
}
