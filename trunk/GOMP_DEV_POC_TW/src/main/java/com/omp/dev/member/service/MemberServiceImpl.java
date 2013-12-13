package com.omp.dev.member.service;

import java.sql.SQLException;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.ibatis.dao.client.DaoManager;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.persistence.dao.DaoConfig;
import com.omp.commons.service.AbstractService;
import com.omp.dev.common.Constants;
import com.omp.dev.member.model.Member;
import com.omp.dev.member.persistence.dao.MemberDAO;

/**
 * @author P007974
 *
 */
public class MemberServiceImpl
	extends AbstractService implements MemberService {
	
	/**
	 * Logger
	 */
	protected Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * MemberDAO 
	 */
	MemberDAO memberDAO;
	
	DaoManager manager;
	
	public MemberServiceImpl(){
		manager = DaoConfig.getDaoManager();
		memberDAO = (MemberDAO)manager.getDao(MemberDAO.class);
	}
	
	/* 
	 * [CHECK] psRegNo, mbrNm
	 * @see com.omp.dev.member.service.MemberService#registCheck(com.omp.dev.member.model.Member)
	 */
	public Integer registCheck(Member member) {
		return memberDAO.registCheck(member);
	}

	/* 
	 * [CHECK] email
	 * @see com.omp.dev.member.service.MemberService#duplicateEmailCheck(java.lang.String)
	 */
	public Integer duplicateEmailCheck(String email) {
		return memberDAO.duplicateEmailCheck(email);
	}

	/* 
	 * [CHECK] ID
	 * @see com.omp.dev.member.service.MemberService#duplicateIdCheck(java.lang.String)
	 */
	public Integer duplicateIdCheck(String mbrId) throws SQLException {
		int cnt = 0;
		String mbrNo = "";
		Member member = new Member();
		//[CHECK - ID]
		cnt = (Integer) commonDAO.queryForObject("member.idCheck", mbrId);
		
		if(cnt > 0){
			//[MBR_NO]
			mbrNo =  (String) commonDAO.queryForObject("member.getMbrNo", mbrId);
			member = (Member)commonDAO.queryForObject("main.getmember", mbrNo);
			if(Constants.MEM_STATUS_LEAVE.equals(member.getMbrStatCd())){
				//[CHECK -ID]
				cnt = (Integer) commonDAO.queryForObject("member.secedeIdCheck", mbrNo);
			}
		}
		return cnt;
	}

	/*
	 * [INSERT] member
	 * (non-Javadoc)
	 * @see com.omp.dev.member.service.MemberService#insertMember(com.omp.dev.member.model.Member)
	 */
	public HashMap<Object, String> insertMember(Member member) {
		HashMap<Object, String> map = new HashMap<Object, String>();
		try {
			daoManager.startTransaction();

			memberDAO.insertMember(member);
			
			map.put("resultMsg", "SUCCESS");
			map.put("mbrId", member.getMbrId());
			map.put("emailAddr", member.getEmailAddr());
			member.setMbrNo((String) commonDAO.queryForObject("member.getMbrNo", member.getMbrId()));
			
			//MEMBER_HIST
			commonDAO.insert("member.insertMemberHst", member.getMbrNo());
			
			daoManager.commitTransaction();
			
        } catch (Exception e) {
            e.printStackTrace();
            map.put("resultMsg", "FAIL");
            throw new ServiceException("개발자 가입 실패", e);
        } finally {
            daoManager.endTransaction();
        }
		
		return map;
	}

	/* (non-Javadoc)
	 * @see com.omp.dev.member.service.MemberService#getEmailCert(java.lang.String)
	 */
	@Override
	public Member getEmailCert(String mbrNo) throws Exception {
		return (Member) commonDAO.queryForObject("member.getEmailCert", mbrNo);
	}

	/* (non-Javadoc)
	 * @see com.omp.dev.member.service.MemberService#getMemberNo(java.lang.String)
	 */
	@Override
	public String getMemberNo(String mbrId) throws Exception {
		return (String) commonDAO.queryForObject("member.getMbrNo", mbrId);
	}

	/* (non-Javadoc)
	 * @see com.omp.dev.member.service.MemberService#updateMemberJoin(java.lang.String)
	 */
	@Override
	public void updateMemberJoin(String mbrNo) throws Exception {
		commonDAO.update("member.updateMemberJoin", mbrNo);
	}

	@Override
	public String selectTwID() throws Exception {
		return (String)this.commonDAO.queryForObject("TwID.selectTwID");
	}
	
}
