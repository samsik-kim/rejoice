/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 * redaano    2009. 3. 30.    Description
 *
 */
package com.omp.dev.community.action;

import java.util.List;

import com.omp.commons.action.BaseAction;
import com.omp.commons.util.GLogger;
import com.omp.dev.common.Constants;
import com.omp.dev.common.util.CommonUtil;
import com.omp.dev.community.model.Notice;
import com.omp.dev.community.service.NoticeService;
import com.omp.dev.community.service.NoticeServiceImpl;

/**
 * Notice Action
 * 
 * @author redaano
 * @version 0.1
 */
@SuppressWarnings("serial")
public class NoticeAction extends BaseAction {
    private static GLogger logger = new GLogger(NoticeAction.class);

    private Notice notice = null;
    
    private List<Notice> list = null;
    
    private NoticeService service = null;
    
	public NoticeAction() {
        service = new NoticeServiceImpl();
    }

    /**
     * NOtice List Search
     * 
     * @return
     */
    public String listNotice() {
    	if(notice == null) {
    		notice = new Notice();
    	}
	        
    	if("".equals(notice.getSearchWord()) || notice.getSearchWord() != null){
    		notice.setSearchWord( CommonUtil.removeSpecial(notice.getSearchWord()) );
    	}
      
      	notice.setCtgrCd(Constants.CTGR_NTC_DEV); // Developer PoC Notice
        
      	list = service.getNoticeList(notice);
       
      	return SUCCESS;
    }

    /**
     * Noice View
     * 
     * @return
     */
    public String viewNotice() {
    	if(notice == null){
    		notice = new Notice();
    	}
    	
    	int no = notice.getPage().getNo();
    	
    	notice.setCtgrCd(Constants.CTGR_NTC_DEV); // Developer PoC Notice
        
        notice = service.getNotice(notice);
        
        notice.getPage().setNo(no);
        
        return SUCCESS;
    }

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public List<Notice> getList() {
        return list;
    }

    public void setList(List<Notice> list) {
        this.list = list;
    }

    public NoticeService getService() {
        return service;
    }

    public void setService(NoticeService service) {
        this.service = service;
    }
}