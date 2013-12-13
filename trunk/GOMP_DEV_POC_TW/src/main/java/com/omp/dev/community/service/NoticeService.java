package com.omp.dev.community.service;

import java.util.List;

import com.omp.dev.community.model.Notice;

public interface NoticeService {

	/**
	 * Notice List Search
	 * 
	 * @param sub
	 * @return
	 */
	public List<Notice> getNoticeList(Notice notice);


	/**
	 * Notice writing Read
	 * 
	 * @param sub
	 * @return
	 */
	public Notice getNotice(Notice notice);

}
