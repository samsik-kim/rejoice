package com.nmimo.service.review.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nmimo.common.info.PageInfo;
import com.nmimo.data.mypage.info.MyWorkInfo;
import com.nmimo.data.review.dao.ReviewDAO;
import com.nmimo.data.review.info.BanrInfo;
import com.nmimo.data.review.info.BfacRegBasInfo;
import com.nmimo.data.review.info.ReviewInfo;
import com.nmimo.service.review.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewDAO dao;
	
	/**
	 * <pre>
	 *
	 * </pre>
	 * @param info
	 * @return
	 */
	@Override
	public PageInfo selectWorkList(PageInfo pageInfo, ReviewInfo info) {
		int totalCnt = dao.selectTotalCnt(info);
		info.setTotalCount(totalCnt);
		List<ReviewInfo> list = dao.selectWorkList(info) ;
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
	public ReviewInfo selectWorkDetail(ReviewInfo info) {
		return dao.selectWorkDetail(info);
	}
	
	
	/**
	 * <pre>
	 *
	 * </pre>
	 * @param pageInfo
	 * @param info
	 * @return
	 */
	@Override
	public PageInfo selectBanrList(PageInfo pageInfo, BanrInfo info) {
		int totalCnt = dao.selectTotalBanrCnt(info);
		info.setTotalCount(totalCnt);
		List<BanrInfo> list = dao.selectBanrList(info) ;
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
	public BanrInfo selectBanrDetail(int seq) {
		return dao.selectBanrDetail(seq);
	}

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param pageInfo
	 * @param info
	 * @return
	 */
	@Override
	public PageInfo selectBfacRegList(PageInfo pageInfo, BfacRegBasInfo info) {
		int totalCnt = dao.selectTotalBfac(info);
		info.setTotalCount(totalCnt);
		List<BfacRegBasInfo> list = dao.selectBfacRegList(info);
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
	public BfacRegBasInfo selectBfacDetail(int seq) {
		return dao.selectBfacDetail(seq);
	}
}