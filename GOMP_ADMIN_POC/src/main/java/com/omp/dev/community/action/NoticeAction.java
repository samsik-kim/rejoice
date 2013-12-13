package com.omp.dev.community.action;

import java.util.List;

import com.omp.commons.action.BaseAction;
import com.omp.commons.exception.NoticeException;
import com.omp.dev.common.Constants;
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
        
        if (notice == null) {
        	throw new NoticeException("대상 자료는 존재하지 않습니다.");
        }
        
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