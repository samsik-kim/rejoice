package com.omp.dev.main.service;

import java.util.ArrayList;
import java.util.List;

import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;
import com.omp.commons.util.GLogger;
import com.omp.dev.community.model.Notice;
import com.omp.dev.main.model.MainDownloadBest;
import com.omp.dev.member.model.Member;

public class MainServiceImpl extends AbstractService implements MainService {
	
	/**
	 * Logger
	 */
	protected GLogger logger = new GLogger(this.getClass());

	/* (non-Javadoc)
	 * @see com.omp.dev.main.service.MainService#getMemberProfile(java.lang.String)
	 */
	@Override
	public Member getMemberProfile(String mbrNo) {
		Member member = new Member();
		try {
			member = (Member) commonDAO.queryForObject("main.getmember", mbrNo);
		} catch (Exception e) {
			new ServiceException("getMemberProfile() fail", e);
		}
		return member;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MainDownloadBest> getMainPayDownloadBest() {
		List<MainDownloadBest> list = new ArrayList<MainDownloadBest>();
		try {
			list = this.commonDAO.queryForList("main.payDownloadBest");
		} catch (Exception e) {
			new ServiceException("getMainPayDownloadBest() fail.", e);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MainDownloadBest> getMainFreeDownloadBest() {
		List<MainDownloadBest> list = new ArrayList<MainDownloadBest>();
		try {
			list = this.commonDAO.queryForList("main.freeDownloadBest");
		} catch (Exception e) {
			new ServiceException("getMainFreeDownloadBest() fail.", e);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notice> selectMainNoticeList(String ctgrcd) {
		List<Notice> list = new ArrayList<Notice>();
		try {
			list = this.commonDAO.queryForList("main.selectMainNotice", ctgrcd);
		} catch (Exception e) {
			new ServiceException("selectMainNoticeList() fail.", e);
		}
		return list;
	}
	
}
