package com.omp.admin.member.membermgr.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.omp.admin.adminmgr.auth.model.AdSession;
import com.omp.admin.common.Constants;
import com.omp.admin.member.membermgr.model.SearchConditionInfo;
import com.omp.admin.member.membermgr.model.UserMemberMgr;
import com.omp.admin.member.membermgr.service.DevMemberMgrService;
import com.omp.admin.member.membermgr.service.DevMemberMgrServiceImpl;
import com.omp.commons.action.BaseAction;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.util.CipherAES;
import com.omp.commons.util.GLogger;

@SuppressWarnings("serial")
public class DevMemberMgrAction extends BaseAction {

	private GLogger log = new GLogger(this.getClass());
	
	private UserMemberMgr devMember;
	
	private SearchConditionInfo sc;
	
	private DevMemberMgrService devMemberService;
	
	private List<UserMemberMgr> list = null;
	
	private List<HashMap<String, String>> phoneList = null;
	
	private String searchYn;
	
    public DevMemberMgrAction(){
    	devMemberService = new DevMemberMgrServiceImpl();
	}
    
    /**
	 * DEV MEMBER SEARCH LIST
	 * @return
	 */
	public String findDevMemberMgrList(){

		if(devMember == null){
			devMember = new UserMemberMgr();
		}
		
		if(sc != null && !"".equals(sc.getStartDate()) && sc.getPayTransStartDate() != null){
			devMember.setMbrgrcd(sc.getMbrgrcd());
			devMember.setMbrclscd(sc.getMbrclscd());
			devMember.setMbrcatcd(sc.getMbrcatcd());
			devMember.setBizcatcd(sc.getBizcatcd());
			devMember.setStartDate(sc.getStartDate());
			devMember.setEndDate(sc.getEndDate());
			devMember.setPayStartDt(sc.getPayTransStartDate());
			devMember.setPayEndDt(sc.getPayTransEndDate());
			devMember.setDevmbrstatcd(sc.getDevmbrstatcd());
			devMember.setSearchType(sc.getSearchType());
			devMember.setSearchValue(sc.getSearchNm());
			devMember.getPage().setNo(sc.getCurrentPageNo());
			
			list = devMemberService.findDevMemberMgrList(devMember);
			
			searchYn = "Y";
		}else{
			sc = new SearchConditionInfo();
			
			searchYn = "N";
		}
		
		return SUCCESS;
	}
    
	/**
	 * DEV MEMBER DETAIL PAGE
	 * @return
	 */
	public String findDevMemberDetail(){

		if(devMember == null){
			devMember = new UserMemberMgr();
		}
		
		if(sc == null){
			sc = new SearchConditionInfo();
		}
		
		int currentPage = devMember.getPage().getNo();
		
		phoneList = devMemberService.testPhoneInfoList(devMember.getMbrno());
		
		devMember = devMemberService.findDevMemberDetail(devMember.getMbrno());
		
		if(devMember != null){
			devMember.getPage().setNo(currentPage);
			
			if(devMember.getMbrclscd().equals(Constants.MEM_CLS_PRIVATE)){
				try {
					devMember.setPsregno(CipherAES.decryption(devMember.getPsregno()));
				} catch (Exception e) {
					throw new ServiceException("findDevMemberDetail - PS_REG_NO Decryption Error");
				}
			}
			
			if(devMember.getDevmbrstatcd().equals(Constants.MEM_DEV_STATUS_LEAVE_FINISH)){
				HttpServletRequest req = this.req;
				
				AdSession adSession = (AdSession) req.getSession(true).getAttribute(Constants.ADMIN_AUTH_SESSION_KEY);
				
				devMember.setRegid(adSession.getAdMgr().getMgrId());
			}
		}
		
		return SUCCESS;
	}
    
	/**
	 * Ajax State Update Excute
	 * @return
	 */
	public void ajaxStateUpdateExcute(){
		HttpServletResponse res = this.res;
		res.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = null;

		try {
			
			if(devMember == null){
				devMember = new UserMemberMgr();
			}
			
			JSONObject jsonObject = new JSONObject();

			jsonObject = devMemberService.ajaxStateUpdateExcute(devMember, this.req);

			out = res.getWriter();
			
			out.write(jsonObject.toString());
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	/**
	 * Pop-Up Trans Application Form View
	 * @return
	 */
	public String popTransApplcationFormView(){

		if(devMember == null){
			devMember = new UserMemberMgr();
		}

		devMember = devMemberService.transApplicationFormView(devMember);
		
		if(devMember.getMbrclscd().equals(Constants.MEM_CLS_PRIVATE) && !devMember.getDevmbrstatcd().equals(Constants.MEM_DEV_STATUS_TURN_MOTION_REQ)){
			try {
				devMember.setPsregno(CipherAES.decryption(devMember.getPsregno()));
			} catch (Exception e) {
				throw new ServiceException("popTransApplcationFormView - PS_REG_NO Decryption Error");
			}
		}

		return SUCCESS;
	}
	
	/**
	 * Pop-Up Trans History View
	 * @return
	 */
	public String popTransHistoryView(){
		
		if(devMember == null){
			devMember = new UserMemberMgr();
		}

		list = devMemberService.popTransHistoryView(devMember);
		
//		devMember = devMemberService.findDevMemberDetail(devMember.getMbrno());
		
		return SUCCESS;
	}
	
	/**
	 * Pop-Up Trans Reject Register
	 * @return
	 */
	public String popTransRejectRegister(){
		return SUCCESS;
	}
	
	/**
	 * Pop-Up Reject View
	 * @return
	 */
	public String popRejectView(){

		if(devMember == null){
			devMember = new UserMemberMgr();
		}

		String rejreason = devMemberService.popRejectView(devMember);
		
		devMember.setRejreason(rejreason);
		
		return SUCCESS;
	}
	
	/**
	 * Pop-Up Withdraw View
	 * @return
	 */
	public String popWithdrawView(){

		if(devMember == null){
			devMember = new UserMemberMgr();
		}
		
		if(sc == null){
			sc = new SearchConditionInfo();
		}

		devMember = devMemberService.popWithdrawView(devMember);
		
		return SUCCESS;
	}
	
	public UserMemberMgr getDevMember() {
		return devMember;
	}

	public void setDevMember(UserMemberMgr devMember) {
		this.devMember = devMember;
	}

	public List<UserMemberMgr> getList() {
		return list;
	}

	public void setList(List<UserMemberMgr> list) {
		this.list = list;
	}

	public List<HashMap<String, String>> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<HashMap<String, String>> phoneList) {
		this.phoneList = phoneList;
	}

	/**
	 * @return the devMemberService
	 */
	public DevMemberMgrService getDevMemberService() {
		return devMemberService;
	}

	/**
	 * @param devMemberService the devMemberService to set
	 */
	public void setDevMemberService(DevMemberMgrService devMemberService) {
		this.devMemberService = devMemberService;
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
