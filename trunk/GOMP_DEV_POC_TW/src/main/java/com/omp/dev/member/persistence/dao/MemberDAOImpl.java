package com.omp.dev.member.persistence.dao;

import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.template.SqlMapDaoTemplate;
import com.omp.dev.member.model.Member;

public class MemberDAOImpl extends SqlMapDaoTemplate implements MemberDAO{
	
	public MemberDAOImpl(DaoManager daoManager){
		super(daoManager);
	}

	/* (non-Javadoc)
	 * @see com.omp.dev.member.persistence.dao.MemberDAO#check(com.omp.dev.member.MemberInfo)
	 */
	public Integer registCheck(Member member) {
		return (Integer)queryForObject("member.registCheck", member);
	}

	/* (non-Javadoc)
	 * @see com.omp.dev.member.persistence.dao.MemberDAO#emailCheck(java.lang.String)
	 */
	public Integer duplicateEmailCheck(String email) {
		return (Integer)queryForObject("member.emailCheck", email);
	}

	/* (non-Javadoc)
	 * @see com.omp.dev.member.persistence.dao.MemberDAO#duplicateIdCheck(java.lang.String)
	 */
	public Integer duplicateIdCheck(String mbrId) {
		return (Integer)queryForObject("member.idCheck", mbrId);
	}

	/* (non-Javadoc)
	 * @see com.omp.dev.member.persistence.dao.MemberDAO#insertMember(com.omp.dev.member.model.Member)
	 */
	public void insertMember(Member member) {
		insert("member.insertMember", member);
	}
	
}
