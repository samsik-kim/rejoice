package com.omp.dev.community.service;

import java.sql.SQLException;
import java.util.List;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;
import com.omp.dev.community.model.Notice;

public class WebNoticeService extends AbstractService{
	@SuppressWarnings("unchecked")
	public List<Notice> selectNoticePagingList(Notice notice) {
		return this.commonDAO.queryForPageList("WebNotice.selectNoticePagingList", notice);
	}
	
	public Notice selectNotice(Notice notice) {
		Notice retNotice = null;
		Notice hitNotice = new Notice();
		hitNotice.setCtgrCd(notice.getCtgrCd());
		hitNotice.setNoticeId(notice.getNoticeId());
		try {
			this.commonDAO.update("WebNotice.updateNoticeHit", hitNotice);
			retNotice = (Notice) this.commonDAO.queryForObject("WebNotice.selectNotice", notice);
		} catch (SQLException e) {
			throw new ServiceException("공지 내용을 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return retNotice;
	}
	@SuppressWarnings("unchecked")
	public List<Notice> selectFileDownloadList(int gid) {
		List<Notice> noticeFile = null;
		try {
			noticeFile = this.commonDAO.queryForList("WebNotice.selectFileDownloadList", gid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return noticeFile;
	}
}
