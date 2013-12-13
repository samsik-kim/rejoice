package com.omp.admin.search.action;

import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import com.omp.admin.adminmgr.auth.model.AdMgr;
import com.omp.admin.adminmgr.auth.model.AdSession;
import com.omp.admin.common.Constants;
import com.omp.admin.community.qna.model.QnA;
import com.omp.admin.search.model.Search;
import com.omp.admin.search.service.SearchService;
import com.omp.admin.search.service.SearchServiceImpl;
import com.omp.commons.action.BaseAction;
import com.omp.commons.model.PagenateList;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.StringUtil;

public class SearchManagementAction extends BaseAction{
	private GLogger			logger	= new GLogger(this.getClass());
	private Search 			search = null;
	private Search 			searchSub = null;
	private SearchService 	searchService = null;
	private List<Search>	searchList = null;
	private String			delIdx = "";
	private String			modIdx = "";
	private String			modval = "";
	private String			insIdx = "";
	private String			insVal = "";
	private String			chk = "";
	private String 			searcheck="";
	private long			searchCnt = (long) 0;
	
	/**
	 * Constructor
	 */
    public SearchManagementAction() {
    	searchSub = new Search();
    	if(this.searchService == null) {
    		this.searchService = new SearchServiceImpl();
    	}
    }
	
	/**
     * Search Management LIST.
     * @param qna
     * @return
     */
	public String SearchList() throws Throwable {
		this.logger.debug("SearchList()");
		if(search == null){
			search = new Search();
			searcheck="請選擇搜尋日期後點選\"搜尋\"鍵。";
		}else{
			if(searchSub!=null&&chk.equals("Y")){
				search = searchSub;
				chk="";
			}
			if (search.getStartDate() == null) {
				Date nowDate = new Date();
				String dateStr = DateUtil.getYYYYMMDD(nowDate);

				search.setStartDate(DateUtil.getDayAfterWithOutSlash(dateStr, -7));
				search.setEndDate(dateStr);
			}
			String sPageNo = ("".equals(StringUtil.nvlStr(this.req.getParameter("pageNo")))) ? "" + search.getPage().getNo() : this.req
					.getParameter("pageNo");
			sPageNo = StringUtil.nvlStr(sPageNo, "1");
			search.getPage().setNo(Integer.parseInt(sPageNo));
			searchList=searchService.getSearchList(search);
			searchCnt = ((PagenateList) searchList).getTotalCount().longValue();
			if(!(searchCnt>0)){
				searcheck="無資料。";
			}
		}
		return "success";
	}
	
	/**
	 * Search Delete
	 * @param qna
	 */
	public void removeSearch(){
		this.logger.debug("removeSearch()");
		
		StringTokenizer tokenizer = new StringTokenizer(this.delIdx, ",");
		int index = 0;
		while (tokenizer.hasMoreTokens()) {
			index = Integer.parseInt(tokenizer.nextToken());
		    if(index >= 0) {
		    	this.searchService.removeSearch(index);
		    }
		}
	}
	
	/**
	 * Search Modify
	 * @param qna
	 */
	public void modifySearch(){
		this.logger.debug("modifySearch()");
		if(searchSub==null){
			searchSub = new Search();
		}
		searchSub = search;
		HttpServletRequest request = this.req;
		AdSession adSession = (AdSession) request.getSession(true).getAttribute(Constants.ADMIN_AUTH_SESSION_KEY);
		AdMgr adMgr = adSession.getAdMgr();
		
		String[] tempIdx =this.modIdx.split("#");
		String[] tempVal =this.modval.split("#");
		for(int i=1;i<tempIdx.length;i++){
			this.searchService.modifySearch(Integer.parseInt(tempIdx[i]),tempVal[i],adMgr.getMgrId());
		}
		   
	}
	
	public String popSearch(){
		this.logger.debug("popSearch()");
		String sPageNo = ("".equals(StringUtil.nvlStr(this.req.getParameter("pageNo")))) ? "" + search.getPage().getNo() : this.req
				.getParameter("pageNo");
		search.getPage().setNo(Integer.parseInt(sPageNo));
		return "success";
	}
	
	public void insertSearch(){
		this.logger.debug("insertSearch()");
		if(searchSub==null){
			searchSub = new Search();
		}
		searchSub = search;
		HttpServletRequest request = this.req;
		AdSession adSession = (AdSession) request.getSession(true).getAttribute(Constants.ADMIN_AUTH_SESSION_KEY);
		AdMgr adMgr = adSession.getAdMgr();
		if(insIdx.equals("")){
			insIdx = "emp";
			this.searchService.insertSearch(insIdx,insVal,adMgr.getMgrId());
		}else{
			this.searchService.insertSearch(insIdx,insVal,adMgr.getMgrId());
		}
	}

	public Search getSearch() {
		return search;
	}

	public void setSearch(Search search) {
		this.search = search;
	}

	public SearchService getSearchService() {
		return searchService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	public List<Search> getSearchList() {
		return searchList;
	}

	public void setSearchList(List<Search> searchList) {
		this.searchList = searchList;
	}

	public String getDelIdx() {
		return delIdx;
	}

	public void setDelIdx(String delIdx) {
		this.delIdx = delIdx;
	}

	public long getSearchCnt() {
		return searchCnt;
	}

	public void setSearchCnt(long searchCnt) {
		this.searchCnt = searchCnt;
	}

	public String getModIdx() {
		return modIdx;
	}

	public void setModIdx(String modIdx) {
		this.modIdx = modIdx;
	}

	public String getModval() {
		return modval;
	}

	public void setModval(String modval) {
		this.modval = modval;
	}

	public String getInsIdx() {
		return insIdx;
	}

	public void setInsIdx(String insIdx) {
		this.insIdx = insIdx;
	}

	public String getInsVal() {
		return insVal;
	}

	public void setInsVal(String insVal) {
		this.insVal = insVal;
	}

	public Search getSearchSub() {
		return searchSub;
	}

	public void setSearchSub(Search searchSub) {
		this.searchSub = searchSub;
	}

	public String getChk() {
		return chk;
	}

	public void setChk(String chk) {
		this.chk = chk;
	}

	public String getSearcheck() {
		return searcheck;
	}

	public void setSearcheck(String searcheck) {
		this.searcheck = searcheck;
	}

}
