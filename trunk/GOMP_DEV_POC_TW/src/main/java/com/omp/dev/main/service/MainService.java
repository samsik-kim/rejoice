package com.omp.dev.main.service;

import java.util.List;

import com.omp.dev.community.model.Notice;
import com.omp.dev.main.model.MainDownloadBest;
import com.omp.dev.member.model.Member;

public interface MainService {
	
	public Member getMemberProfile(String mbrNo);

	public List<MainDownloadBest> getMainPayDownloadBest();
	
	public List<MainDownloadBest> getMainFreeDownloadBest();

	public List<Notice> selectMainNoticeList(String ctgrcd);
}
