package com.seojeong.data.member.dao;

import java.sql.SQLException;
import java.util.List;

import com.seojeong.data.member.info.MemberInfo;

public interface MemberDAO {
	
	/** Test Select EMP */
	public int selectEmp()throws SQLException ;
	
	public void insertMember(MemberInfo info)throws SQLException;
	
	public int updateMember(MemberInfo info)throws SQLException;
	
	public List<MemberInfo> selectMemberList(MemberInfo info)throws SQLException;
	
	public int selectMemberListCount(MemberInfo info)throws SQLException;
	
	public int selectMdnCheck(String mdn)throws SQLException;
	
	public MemberInfo selectMemberInfo(MemberInfo info)throws SQLException;
	
	public void deleteMember(MemberInfo info)throws SQLException;
	
	public List<MemberInfo> selectExcel(MemberInfo info)throws SQLException;
}
