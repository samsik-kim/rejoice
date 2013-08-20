package com.nmimo.service.review;

import com.nmimo.common.info.PageInfo;
import com.nmimo.data.mypage.info.MyWorkInfo;
import com.nmimo.data.review.info.BanrInfo;
import com.nmimo.data.review.info.BfacRegBasInfo;
import com.nmimo.data.review.info.ReviewInfo;

public interface ReviewService {

	//대기, 반려, 완료
	public PageInfo selectWorkList(PageInfo pageInfo, ReviewInfo info);
	public ReviewInfo selectWorkDetail(ReviewInfo info);
	
	//배너관리 
	public PageInfo selectBanrList(PageInfo pageInfo, BanrInfo info);
	public BanrInfo selectBanrDetail(int seq);
	
	//사전예약
	public PageInfo selectBfacRegList(PageInfo pageInfo, BfacRegBasInfo info);
	public BfacRegBasInfo selectBfacDetail(int seq);
}
