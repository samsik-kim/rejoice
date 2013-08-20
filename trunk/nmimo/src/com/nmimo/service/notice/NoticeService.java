package com.nmimo.service.notice;

import com.nmimo.common.info.PageInfo;
import com.nmimo.data.notice.info.NoticeInfo;

public interface NoticeService {

	public PageInfo selectNoticeList(PageInfo pageInfo, NoticeInfo info);
	
	public NoticeInfo selectNoticeDetail(int seq);
}
