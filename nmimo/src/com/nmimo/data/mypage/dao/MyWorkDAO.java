package com.nmimo.data.mypage.dao;

import java.util.List;

import com.nmimo.data.mypage.info.MyWorkInfo;

public interface MyWorkDAO {

	public int selectTotalCnt(MyWorkInfo info);

	public List<MyWorkInfo> selectWorkList(MyWorkInfo info);
	
	public MyWorkInfo selectWorkDetail(MyWorkInfo info);
}
