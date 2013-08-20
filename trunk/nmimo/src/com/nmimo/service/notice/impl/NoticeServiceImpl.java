package com.nmimo.service.notice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nmimo.common.info.PageInfo;
import com.nmimo.data.notice.dao.NoticeDAO;
import com.nmimo.data.notice.info.NoticeInfo;
import com.nmimo.service.notice.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	NoticeDAO dao;

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param info
	 * @return
	 */
	@Override
	public PageInfo selectNoticeList(PageInfo pageInfo, NoticeInfo info) {
		int totalCnt = dao.selectTotalNotice(info);
		info.setTotalCount(totalCnt);
		List<NoticeInfo> list = dao.selectNoticeList(info);
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
	 * @param seq
	 * @return
	 */
	@Override
	public NoticeInfo selectNoticeDetail(int seq) {
		return dao.selectNoticeDetail(seq);
	}
}
