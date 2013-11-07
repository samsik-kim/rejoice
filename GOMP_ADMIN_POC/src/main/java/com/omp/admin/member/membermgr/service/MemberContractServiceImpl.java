package com.omp.admin.member.membermgr.service;

import java.util.List;

import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;

import com.omp.admin.member.membermgr.model.MemberContract;

/**
 * Member Management - UserMember withdraw Management
 * 
 * @author HanJH
 * @version 0.1
 */
public class MemberContractServiceImpl extends AbstractService implements MemberContractService {

	/**
	 * User Member withdraw List
	 * 
	 * @param memberContract
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MemberContract> getUserMemberWithdrawList(MemberContract memberContract) throws ServiceException {
		try{
			
			return this.commonDAO.queryForPageList("MemberContract.getUserMemberWithdrawList", memberContract);

		} catch(Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}
	}

	/**
	 * Developer Member withdraw List
	 * 
	 * @param memberContract
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MemberContract> getDevMemberWithdrawList(MemberContract memberContract) throws ServiceException {
		try{
			
			return this.commonDAO.queryForPageList("MemberContract.getDevWithdrawList", memberContract);
			
		} catch(Exception e){
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}
	}

	/**
	 * Developer Member Change List
	 * 
	 * @param memberContract
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MemberContract> getDevChangeMemberList(MemberContract memberContract) throws ServiceException {
		// TODO Auto-generated method stub
		try{
			return this.commonDAO.queryForPageList("MemberContract.getDevChangeMemberList", memberContract);
		} catch(Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}
	}
}
