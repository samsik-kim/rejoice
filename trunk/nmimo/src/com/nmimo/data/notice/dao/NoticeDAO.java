package com.nmimo.data.notice.dao;

import java.util.List;

import com.nmimo.data.notice.info.NoticeInfo;

public interface NoticeDAO {

	public List<NoticeInfo> selectNoticeList(NoticeInfo info);
	
	public int selectTotalNotice(NoticeInfo info);
	
	public NoticeInfo selectNoticeDetail(int seq);
}
