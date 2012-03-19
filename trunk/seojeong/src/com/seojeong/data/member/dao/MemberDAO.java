package com.seojeong.data.member.dao;

import java.sql.SQLException;

import com.seojeong.data.member.info.MemberInfo;

public interface MemberDAO {
	
	/** Test Select EMP */
	public int selectEmp()throws SQLException ;
	
	public void insertMember(MemberInfo info)throws SQLException;
	
	public int updateMember(MemberInfo info)throws SQLException;
}
