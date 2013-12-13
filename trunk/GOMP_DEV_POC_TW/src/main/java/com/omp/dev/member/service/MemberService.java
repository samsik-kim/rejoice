package com.omp.dev.member.service;

import java.sql.SQLException;
import java.util.HashMap;

import com.omp.dev.member.model.Member;

public interface MemberService {
	
	/** [CHECK] psRegNo, mbrNm */
	public Integer registCheck(Member member);
	
	/** [CHECK] email */
	public Integer duplicateEmailCheck(String email);
	
	/** [CHECK] ID 
	 * @throws SQLException */
	public Integer duplicateIdCheck(String mbrId) throws SQLException;
	
	/** [INSERT] member */
	public HashMap<Object, String> insertMember(Member member);
	
	/** [SELECT] emailCert */
	public Member getEmailCert(String mbrNo) throws Exception;
	
	/** [SELECT] MBR_NO */
	public String getMemberNo(String mbrId) throws Exception;
	
	/** [UPDATE] JOIN */
	public void updateMemberJoin(String mbrNo)throws Exception;

	/** [SELECT] TWID */
	public String selectTwID() throws Exception;
}
