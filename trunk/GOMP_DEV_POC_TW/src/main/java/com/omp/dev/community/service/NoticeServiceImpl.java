package com.omp.dev.community.service;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.persistence.dao.DaoConfig;
import com.omp.commons.service.AbstractService;
import com.omp.commons.util.GLogger;
import com.omp.dev.community.model.Notice;
import com.omp.dev.community.persistence.dao.NoticeDAO;

public class NoticeServiceImpl extends AbstractService implements NoticeService {
	
    private static GLogger logger = new GLogger(NoticeServiceImpl.class);
    
    DaoManager manager = null;
    
    NoticeDAO dao = null;

    public NoticeServiceImpl() {
        manager = DaoConfig.getDaoManager();
        dao = (NoticeDAO)manager.getDao(NoticeDAO.class);
    }


    /**
     * Notice List Search
     * 
     * @param notice
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Notice> getNoticeList(Notice notice) {
        List<Notice> result = null;
        try {
        	result = this.commonDAO.queryForPageList("Community_Notice.selectNoticeList", notice);
    	}catch(Exception e){
    		e.printStackTrace();
    		throw new ServiceException("getNoticeList Error.", e);
        }                                                                                                                                                             
        return result;
    }


    /**
     * Notice writing Read
     * 
     * @param notice
     * @return
     */
    public Notice getNotice(Notice notice) {
        Notice result = null;
        try {
        	// Hits Increase
        	this.commonDAO.update("Community_Notice.updateNoticeHit", notice);
        	
            // writing Search
            result = (Notice) this.commonDAO.queryForObject("Community_Notice.selectNotice", notice);
        }catch(Exception e){
    		e.printStackTrace();
    		throw new ServiceException("getNotice Error.", e);
        }
        return result;
    }

}
