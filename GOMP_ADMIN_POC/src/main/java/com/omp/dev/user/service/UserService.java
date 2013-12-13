package com.omp.dev.user.service;

import com.omp.dev.user.model.Session;

public interface UserService {
	
	/**
	 * get Developer Member Id(twId, Uniform Number, Passport Number)
	 * @param name
	 * @param psReqNo
	 * @param flag
	 * @return
	 */
	public Session getDevMemberById(String name, String psReqNo, String mbrClsCd, String flag);
	
	/**
	 * get Developer Member Id(email)
	 * @param name
	 * @param flag
	 * @return
	 */
	public Session getDevMemberById(String email, String flag);
	
	/**
	 * get Developer Member Password
	 * @param userId
	 * @return
	 */
	public Session getDevMemberByPw(String userId) throws Exception;
	
	public Session getSession(Session session);
	
	/**
	 * again Email Certification
	 * @param radioFlag
	 * @param session
	 */
	public String reEmailCertification(String radioFlag, Session session);
}
