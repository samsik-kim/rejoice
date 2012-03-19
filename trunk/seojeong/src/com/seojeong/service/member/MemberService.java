package com.seojeong.service.member;

import java.sql.SQLException;

import com.seojeong.data.member.info.MemberInfo;

public interface MemberService {

	public int selectEmp()throws SQLException ;
	
	public void insertMember(MemberInfo info)throws SQLException;
	
	public int updateMember(MemberInfo info)throws SQLException;
}
