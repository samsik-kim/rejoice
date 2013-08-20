package com.nmimo.service.mypage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nmimo.common.info.PageInfo;
import com.nmimo.data.mypage.dao.MyWorkDAO;
import com.nmimo.data.mypage.info.MyWorkInfo;
import com.nmimo.service.mypage.MyWorkService;

/**
 * <pre>
 *
 * </pre>
 * @file MyWorkServiceImpl.java
 * @since 2013. 8. 8.
 * @author Rejoice
 */
@Service
//@Transactional(rollbackFor = Exception.class)
public class MyWorkServiceImpl implements MyWorkService {

	@Autowired
	public MyWorkDAO dao;
	
	/**
	 * <pre>
	 *
	 * </pre>
	 * @param info
	 * @return
	 */
	@Override
	public PageInfo selectWorkList(PageInfo pageInfo, MyWorkInfo info) {
		int totalCnt = dao.selectTotalCnt(info);
		info.setTotalCount(totalCnt);
		List<MyWorkInfo> list = dao.selectWorkList(info) ;
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
	public MyWorkInfo selectWorkDetail(MyWorkInfo info) {
		return dao.selectWorkDetail(info);
	}
}