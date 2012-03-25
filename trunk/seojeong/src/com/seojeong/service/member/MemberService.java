package com.seojeong.service.member;

import java.sql.SQLException;

import tframe.web.page.PageInfo;

import com.seojeong.data.member.info.MemberInfo;

public interface MemberService {

	public int selectEmp()throws SQLException ;
	
	public void insertMember(MemberInfo info)throws SQLException;
	
	public int updateMember(MemberInfo info)throws SQLException;
	
	public PageInfo selectMemberList(PageInfo pageInfo, MemberInfo info) throws SQLException;
	
	public String selectMdnCheck(String mdn)throws SQLException;
	
	public MemberInfo selectMemberInfo(MemberInfo info)throws SQLException;
}
