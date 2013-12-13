package com.omp.dev.member.persistence.dao;

import com.omp.dev.member.model.Member;

public interface MemberDAO {
	
	/** [CHECK] psRegNo, mbrNm */
	public Integer registCheck(Member member);
	
	/** [CHECK] email */
	public Integer duplicateEmailCheck(String email);
	
	/** [CHECK] ID */
	public Integer duplicateIdCheck(String mbrId);
	
	/** [INSERT] member */
	public void insertMember(Member member);
}
