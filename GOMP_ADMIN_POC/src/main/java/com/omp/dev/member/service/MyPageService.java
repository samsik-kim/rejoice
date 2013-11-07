package com.omp.dev.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import com.omp.dev.member.model.Bank;
import com.omp.dev.member.model.Member;
import com.omp.dev.member.model.MemberTransHist;
import com.omp.dev.member.model.MemberWithdrawInfo;
import com.omp.dev.purchase.model.Purchase;

public interface MyPageService {

	public String certificationPassword(HashMap map) throws Exception;
	
	public Member getMemberProfile(String mbrNo) throws Exception;
	
	public void updateProfile(Member profileInfo) throws Exception;
	
	public MemberTransHist getCalculateInfo(String mbrNo) throws Exception;
	
	public void insertCalculateInfo(MemberTransHist calculateInfo, Properties prop) throws Exception;
	
	public Purchase transferPaidMember(Purchase purchase) throws Exception;
	
	public Member insertTransferInfo(HttpServletRequest req, MemberTransHist transferInfo, Properties prop) throws Exception;
	
	public void transferBusinessMember(HttpServletRequest req, String mbrNo) throws Exception;
	
	public void mypageWithdrawExcute(HttpServletRequest req, MemberWithdrawInfo withdrawInfo) throws Exception;
	
	public String isPersonalMember(String mbrNo) throws Exception;
	
	public MemberTransHist getLastTransHistStat(HashMap map) throws Exception;
	
	public HashMap isValidPrcStat(HashMap map) throws Exception;
	
	public List<Bank> getBankList(String bankNm) throws Exception;
	
	public void updateMemberEmail(Member member)throws Exception;
	
	public Integer getEmailCheck(String email)throws Exception;
}
