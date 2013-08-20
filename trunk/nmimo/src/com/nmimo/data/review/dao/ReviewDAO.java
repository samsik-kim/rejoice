package com.nmimo.data.review.dao;

import java.util.List;

import com.nmimo.data.mypage.info.MyWorkInfo;
import com.nmimo.data.review.info.BanrInfo;
import com.nmimo.data.review.info.BfacRegBasInfo;
import com.nmimo.data.review.info.ReviewInfo;

public interface ReviewDAO {

	public int selectTotalCnt(ReviewInfo info);

	public List<ReviewInfo> selectWorkList(ReviewInfo info);
	
	public ReviewInfo selectWorkDetail(ReviewInfo info);
	
	
	public int selectTotalBanrCnt(BanrInfo info);

	public List<BanrInfo> selectBanrList(BanrInfo info);
	
	public BanrInfo selectBanrDetail(int seq);

	
	public int selectTotalBfac(BfacRegBasInfo info);
	
	public List<BfacRegBasInfo> selectBfacRegList(BfacRegBasInfo info);
	
	public BfacRegBasInfo selectBfacDetail(int seq);
}
