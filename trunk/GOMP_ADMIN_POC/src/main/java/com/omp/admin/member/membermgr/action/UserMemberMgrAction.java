package com.omp.admin.member.membermgr.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.omp.admin.common.Constants;
import com.omp.admin.member.membermgr.model.SearchConditionInfo;
import com.omp.admin.member.membermgr.model.UserMemberMgr;
import com.omp.admin.member.membermgr.service.UserMemberMgrService;
import com.omp.admin.member.membermgr.service.UserMemberMgrServiceImpl;
import com.omp.commons.action.BaseAction;
import com.omp.commons.util.CipherAES;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.GLogger;

@SuppressWarnings("serial")
public class UserMemberMgrAction extends BaseAction {

	private GLogger log = new GLogger(this.getClass());
	
	private UserMemberMgr userMember;
	
	private SearchConditionInfo sc;
	
	private List<UserMemberMgr> userMemberList = null;
	
	private List<HashMap<String, String>> phoneList = null;
	
	private UserMemberMgrService userMemberService;
	
	private int searchCnt = 0;
	
	private String searchYn;

	public UserMemberMgrAction(){
		userMemberService = new UserMemberMgrServiceImpl();
	}
	
	/**
	 * USER MEMBER SEARCH LIST
	 * @return
	 */
	public String findUserMemberMgrList() {
		
		if(userMember == null){
			userMember = new UserMemberMgr();
		}
		
		if(sc != null){
			userMember.setMbrclscd(sc.getMbrclscd());
			userMember.setMbrstatcd(sc.getMbrstatcd());
			userMember.setStartDate(sc.getStartDate());
			userMember.setEndDate(sc.getEndDate());
			userMember.setSearchType(sc.getSearchType());
			userMember.setSearchValue(sc.getSearchNm());
			userMember.getPage().setNo(sc.getCurrentPageNo());
			
			userMemberList = userMemberService.findUserMemberMgrList(userMember);
			
			searchYn = "Y";
		}else{
			sc = new SearchConditionInfo();
			
			searchYn = "N";
		}
		
		return SUCCESS;
	}
	
	/**
	 * USER MEMBER DATAIL	PAGE
	 * @return
	 */
	public String findUserMemberDetail() {
		
		if(userMember == null){
			userMember = new UserMemberMgr();
		}
		
		if(sc == null){
			sc = new SearchConditionInfo();
		}
		
		int currentPage = userMember.getPage().getNo();

		phoneList = userMemberService.phoneInfoList(userMember.getMbrno());

		userMember = userMemberService.findUserMemberDetail(userMember.getMbrno());
		
		if(userMember != null){
			userMember.getPage().setNo(currentPage);

			if(userMember.getMbrclscd().equals(Constants.MEM_CLS_PRIVATE)){
				try {
					userMember.setPsregno(CipherAES.decryption(userMember.getPsregno()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		if(phoneList != null && phoneList.size() > 0) searchCnt = phoneList.size();
		
		return SUCCESS;
	}
	
	/**
	 * Temp Password E-mail Send Pop-Up Page
	 * @return
	 */
	public String popTempEmailSend() {
		
		if(userMember == null){
			userMember = new UserMemberMgr();
		}
		
		userMember = userMemberService.findUserMemberDetail(userMember.getMbrno());
		
		return SUCCESS;
	}
	
	/**
	 * User Member Login Info History Pop-Up Page
	 * @return
	 */
	public String popLoginInfoHistory() {
		
		if(userMember == null){
			userMember = new UserMemberMgr();
		}
		
		if(sc != null){
			userMember.setStartDate(sc.getStartDate());
			userMember.setEndDate(sc.getEndDate());
			userMember.getPage().setNo(sc.getCurrentPageNo());
			userMember.setSearchType(sc.getSearchType());
			userMember.setSearchValue(sc.getSearchNm());
		}else{
			sc = new SearchConditionInfo();
			
			userMember.setStartDate(DateUtil.getDayAfterWithOutSlash(DateUtil.getSysDate(), -7) + "000000");
			userMember.setEndDate(DateUtil.getSysDate() + "235959");
		}

		userMemberList = userMemberService.loginInfoHistory(userMember);
		
		return SUCCESS;
	}

	/**
	 * Ajax Temp Password Email Send To User
	 * @return
	 */
	public void ajaxEmailSendExcute() {
		
		HttpServletResponse res = this.res;
		res.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = null;
		
		try {
			
			if(userMember == null){
				userMember = new UserMemberMgr();
			}
			
			JSONObject jsonObject = new JSONObject();
			
			jsonObject = userMemberService.ajaxEmailSendExcute(userMember);

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
	 * Ajax Phone Info Delete
	 * @return
	 */
	public void ajaxPhoneDeleteExcute() {
		HttpServletResponse res = this.res;
		res.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = null;

		try {
			if(userMember == null){
				userMember = new UserMemberMgr();
			}
			
			JSONObject jsonObject = new JSONObject();
			
			jsonObject = userMemberService.userPhoneInfoDelete(userMember);

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
	
	public int getSearchCnt() {
		return searchCnt;
	}

	public void setSearchCnt(int searchCnt) {
		this.searchCnt = searchCnt;
	}

	public List<UserMemberMgr> getUserMemberList() {
		return userMemberList;
	}

	public void setUserMemberList(List<UserMemberMgr> userMemberList) {
		this.userMemberList = userMemberList;
	}

	public List<HashMap<String, String>> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<HashMap<String, String>> phoneList) {
		this.phoneList = phoneList;
	}

	public UserMemberMgr getUserMember() {
		return userMember;
	}

	public void setUserMember(UserMemberMgr userMember) {
		this.userMember = userMember;
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
