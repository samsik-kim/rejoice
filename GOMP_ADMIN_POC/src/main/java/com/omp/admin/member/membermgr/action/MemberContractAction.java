package com.omp.admin.member.membermgr.action;

import java.util.List;
import com.omp.admin.member.membermgr.model.MemberContract;
import com.omp.admin.member.membermgr.model.SearchConditionInfo;
import com.omp.admin.member.membermgr.service.MemberContractService;
import com.omp.admin.member.membermgr.service.MemberContractServiceImpl;
import com.omp.commons.action.BaseAction;

@SuppressWarnings("serial")
public class MemberContractAction extends BaseAction {
	
	private MemberContractService service;
	
	private MemberContract memberContract;
	
	private SearchConditionInfo sc;
	
	private List<MemberContract> resultList;
	
	private List<MemberContract> loginInfoList;
	
	private String searchYn;
	
	public MemberContractAction() {
		service = new MemberContractServiceImpl();
	}
	
	/**
	 * Member Management - UserMember withdraw List
	 * 
	 * @return
	 */
	public String getUserMemberWithdrawList() {
		if(memberContract == null) {
			memberContract = new MemberContract();
		}
		
		if(sc != null && !"".equals(sc.getWithdrawApprStartDt())){
			memberContract.setMbrClsCd(sc.getMbrclscd());
			memberContract.setStartDate(sc.getWithdrawApprStartDt());
			memberContract.setEndDate(sc.getWithdrawApprEndDt());
			memberContract.setSearchType(sc.getSearchType());
			memberContract.setSearchValue(sc.getSearchNm());
			memberContract.getPage().setNo(sc.getCurrentPageNo());

			resultList = service.getUserMemberWithdrawList(memberContract);
			
			searchYn = "Y";
		}else{
			sc = new SearchConditionInfo();
			
			searchYn = "N";
		}
		
		return SUCCESS;
	}
	
	/** 
	 * Member Management - DevMember withdraw List
	 * 
	 * @return
	 */
	public String getDevMemberWithdrawList() {
		if(memberContract == null){
			memberContract = new MemberContract();
		}
		
		if(sc != null && !"".equals(sc.getWithdrawReqStartDt()) && sc.getWithdrawApprStartDt() != null){
			memberContract.setMbrClsCd(sc.getMbrclscd());
			memberContract.setMbrCatCd(sc.getMbrcatcd());
			memberContract.setBizCatCd(sc.getBizcatcd());
			memberContract.setDevMbrStatCd(sc.getDevmbrstatcd());
			memberContract.setStartDate(sc.getWithdrawReqStartDt());
			memberContract.setEndDate(sc.getWithdrawReqEndDt());
			memberContract.setStartDate2(sc.getWithdrawApprStartDt());
			memberContract.setEndDate2(sc.getWithdrawApprEndDt());
			memberContract.setSearchType(sc.getSearchType());
			memberContract.setSearchValue(sc.getSearchNm());
			memberContract.getPage().setNo(sc.getCurrentPageNo());

			resultList = service.getDevMemberWithdrawList(memberContract);
			
			searchYn = "Y";
		}else{
			sc = new SearchConditionInfo();
			
			searchYn = "N";
		}
		
		return SUCCESS;
	}
	
	/**
	 * Member Management - DevMember Change List
	 * 
	 * @return
	 */
	public String getDevChangeMemberList() {
		if(memberContract == null){
			memberContract = new MemberContract();
		}
		
		if(sc != null && !"".equals(sc.getTransReqStartDt())){
			memberContract.setMbrClsCd(sc.getMbrclscd());
			memberContract.setMbrCatCd(sc.getMbrcatcd());
			memberContract.setBizCatCd(sc.getBizcatcd());
			memberContract.setDevMbrStatCd(sc.getDevmbrstatcd());
			memberContract.setStartDate(sc.getTransReqStartDt());
			memberContract.setEndDate(sc.getTransReqEndDt());
			memberContract.setSearchType(sc.getSearchType());
			memberContract.setSearchValue(sc.getSearchNm());
			memberContract.getPage().setNo(sc.getCurrentPageNo());

			resultList = service.getDevChangeMemberList(memberContract);
			
			searchYn = "Y";
		}else{
			sc = new SearchConditionInfo();
			
			searchYn = "N";
		}
		
		return SUCCESS;
	}
	
    public MemberContractService getService() {
        return service;
    }

    public void setService( MemberContractService service ) {
        this.service = service;
    }

    public MemberContract getMemberContract() {
		return memberContract;
	}

	public void setMemberContract(MemberContract memberContract) {
		this.memberContract = memberContract;
	}

	public List<MemberContract> getResultList() {
		return resultList;
	}

	public void setResultList(List<MemberContract> resultList) {
		this.resultList = resultList;
	}

	public List<MemberContract> getLoginInfoList() {
		return loginInfoList;
	}

	public void setLoginInfoList(List<MemberContract> loginInfoList) {
		this.loginInfoList = loginInfoList;
	}

	/**
	 * @return the sc
	 */
	public SearchConditionInfo getSc() {
		return sc;
	}

	/**
	 * @param sc the sc to set
	 */
	public void setSc(SearchConditionInfo sc) {
		this.sc = sc;
	}

	/**
	 * @return the searchYn
	 */
	public String getSearchYn() {
		return searchYn;
	}

	/**
	 * @param searchYn the searchYn to set
	 */
	public void setSearchYn(String searchYn) {
		this.searchYn = searchYn;
	}
	
	
}
