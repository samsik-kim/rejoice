package com.seojeong.service.member.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tframe.web.page.PageInfo;

import com.seojeong.data.member.dao.MemberDAO;
import com.seojeong.data.member.info.MemberInfo;
import com.seojeong.service.member.MemberService;

@Service
@Transactional(readOnly=true, rollbackFor = Exception.class)
public class MemberServiceImpl implements MemberService {

	
	@Autowired
	MemberDAO dao;
	
	@Override
	public int selectEmp() throws SQLException {
		return dao.selectEmp();
	}

	/* (non-Javadoc)
	 * @see com.seojeong.service.member.MemberService#insertMember(com.seojeong.data.member.info.MemberInfo)
	 */
	@Override
	public void insertMember(MemberInfo info) throws SQLException {
		dao.insertMember(info);
	}

	/* (non-Javadoc)
	 * @see com.seojeong.service.member.MemberService#updateMember(com.seojeong.data.member.info.MemberInfo)
	 */
	@Override
	public int updateMember(MemberInfo info) throws SQLException {
		return dao.updateMember(info);
	}

	/* (non-Javadoc)
	 * @see com.seojeong.service.member.MemberService#selectMemberList(tframe.web.page.PageInfo, com.seojeong.data.member.info.MemberInfo)
	 */
	@Override
	public PageInfo selectMemberList(PageInfo pageInfo, MemberInfo info) throws SQLException {
		pageInfo.setTotalCount(dao.selectMemberListCount(info));
		if(pageInfo.getTotalCount() > 0){
			info.setStartNum(pageInfo.getStartNum());
			info.setEndNum(pageInfo.getEndNum());
			pageInfo.setDataList(dao.selectMemberList(info));
		}
		return pageInfo;
	}
}
