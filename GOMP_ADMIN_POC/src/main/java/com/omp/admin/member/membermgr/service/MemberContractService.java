package com.omp.admin.member.membermgr.service;

import java.util.List;
import com.omp.admin.member.membermgr.model.MemberContract;
import com.omp.commons.exception.ServiceException;

/**
 * Member Management - UserMember withdraw Management
 * 
 * @author HanJH
 * @version 0.1
 */
public interface MemberContractService {
	
	/**
	 * User Memeber withdraw List
	 * 
	 * @param memberContract
	 * @return
	 */
	public List<MemberContract> getUserMemberWithdrawList(MemberContract memberContract)throws ServiceException;
	
	/**
	 * Developer Member withdraw List
	 * 
	 * @param memberContract
	 * @return
	 */
	public List<MemberContract> getDevMemberWithdrawList(MemberContract memberContract)throws ServiceException;
	
	/**
	 * Developer Member withdraw List
	 * 
	 * @param memberContract
	 * @return
	 */
	public List<MemberContract> getDevChangeMemberList(MemberContract memberContract)throws ServiceException;
}
