package com.nmimo.service.mypage;

import com.nmimo.common.info.PageInfo;
import com.nmimo.data.mypage.info.MyWorkInfo;

public interface MyWorkService {

	public PageInfo selectWorkList(PageInfo pageInfo, MyWorkInfo info);
	
	public MyWorkInfo selectWorkDetail(MyWorkInfo info);
}
