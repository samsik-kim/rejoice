package com.omp.admin.member.membermgr.model;

import com.omp.admin.common.util.CommonUtil;
/**
 * Admin POC Member Management Search Condition Model
 */

public class SearchConditionInfo {
	
	private String mbrclscd;								// 회원구분(개인, 사업자, 외국인)
	private String mbrcatcd;							// 회원분류(사용자, 무료, 유료)
	private String devmbrstatcd;						// 개발자회원 상태 코드(가입신청, 가입완료, 전환신청, 전환완료, 전환거절, 탈퇴신청, 탈퇴완료, 정산정보승인대기, 정산정보승인완료, 정산정보승인거절, 연회비결제완료, 연회비결제대기)
	private String mbrstatcd;							// 회원상태(정상, 징계, 탈퇴)
	private String mbrgrcd;								// 회원 등급(Normal, Trust, Special)
	private String bizcatcd;								// 사업자 종류 코드(소규모, 일반)
	private String searchType;							// 검색어 조건
	private String searchNm;							// 검색명
	private String startDate;							// 시작일
	private String endDate;								// 종료일
	private String payTransStartDate;				// 유료 전환 시작일
	private String payTransEndDate;				// 유료 전환 종료일
	private String transReqStartDt;					// 전환 신청 시작일
	private String transReqEndDt;					// 전환 신청 종료일
	private String withdrawReqStartDt;			// 탈퇴 신청 시작일
	private String withdrawReqEndDt;				// 탈퇴 신청 종료일
	private String withdrawApprStartDt;			// 탈퇴 승인 시작일
	private String withdrawApprEndDt;			// 탈퇴 승인 종료일
	private String purchaseStartDt;					// 구매 시작일
	private String purchaseEndDt;					// 구매 종료일
	private int currentPageNo;						// 현재 페이지번호
	
	private String payCls;								// 결제수단
	private String prchsStat;							// 구매상태
	
	
	public SearchConditionInfo() {
		this.currentPageNo = 1;
	}
	
	/**
	 * @return the mbrclscd
	 */
	public String getMbrclscd() {
		return mbrclscd;
	}
	/**
	 * @param mbrclscd the mbrclscd to set
	 */
	public void setMbrclscd(String mbrclscd) {
		this.mbrclscd = mbrclscd;
	}
	/**
	 * @return the mbrcatcd
	 */
	public String getMbrcatcd() {
		return mbrcatcd;
	}
	/**
	 * @param mbrcatcd the mbrcatcd to set
	 */
	public void setMbrcatcd(String mbrcatcd) {
		this.mbrcatcd = mbrcatcd;
	}
	/**
	 * @return the devmbrstatcd
	 */
	public String getDevmbrstatcd() {
		return devmbrstatcd;
	}
	/**
	 * @param devmbrstatcd the devmbrstatcd to set
	 */
	public void setDevmbrstatcd(String devmbrstatcd) {
		this.devmbrstatcd = devmbrstatcd;
	}
	/**
	 * @return the mbrstatcd
	 */
	public String getMbrstatcd() {
		return mbrstatcd;
	}
	/**
	 * @param mbrstatcd the mbrstatcd to set
	 */
	public void setMbrstatcd(String mbrstatcd) {
		this.mbrstatcd = mbrstatcd;
	}
	/**
	 * @return the mbrgrcd
	 */
	public String getMbrgrcd() {
		return mbrgrcd;
	}
	/**
	 * @param mbrgrcd the mbrgrcd to set
	 */
	public void setMbrgrcd(String mbrgrcd) {
		this.mbrgrcd = mbrgrcd;
	}
	/**
	 * @return the bizcatcd
	 */
	public String getBizcatcd() {
		return bizcatcd;
	}
	/**
	 * @param bizcatcd the bizcatcd to set
	 */
	public void setBizcatcd(String bizcatcd) {
		this.bizcatcd = bizcatcd;
	}
	/**
	 * @return the searchType
	 */
	public String getSearchType() {
		return searchType;
	}
	/**
	 * @param searchType the searchType to set
	 */
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	/**
	 * @return the searchNm
	 */
	public String getSearchNm() {
		return searchNm;
	}
	/**
	 * @param searchNm the searchNm to set
	 */
	public void setSearchNm(String searchNm) {
		this.searchNm = searchNm;
	}
	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		startDate = CommonUtil.removeSpecial(startDate);
		startDate = startDate.replaceAll("/", "");
		startDate = startDate.replaceAll("-", "");
			
		if(startDate.length() == 8){
			startDate += "000000";
		}
		
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		endDate = CommonUtil.removeSpecial(endDate);
		endDate = endDate.replaceAll("/", "");
		endDate = endDate.replaceAll("-", "");
			
		if(endDate.length() == 8){
			endDate += "235959";
		}
		
		this.endDate = endDate;
	}
	/**
	 * @return the payTransStartDate
	 */
	public String getPayTransStartDate() {
		return payTransStartDate;
	}
	/**
	 * @param payTransStartDate the payTransStartDate to set
	 */
	public void setPayTransStartDate(String payTransStartDate) {
		payTransStartDate = CommonUtil.removeSpecial(payTransStartDate);
		payTransStartDate = payTransStartDate.replaceAll("/", "");
		payTransStartDate = payTransStartDate.replaceAll("-", "");
		
		if(payTransStartDate.length() == 8){
			payTransStartDate += "000000";
		}
		
		this.payTransStartDate = payTransStartDate;
	}
	/**
	 * @return the payTransEndDate
	 */
	public String getPayTransEndDate() {
		return payTransEndDate;
	}
	/**
	 * @param payTransEndDate the payTransEndDate to set
	 */
	public void setPayTransEndDate(String payTransEndDate) {
		payTransEndDate = CommonUtil.removeSpecial(payTransEndDate);
		payTransEndDate = payTransEndDate.replaceAll("/", "");
		payTransEndDate = payTransEndDate.replaceAll("-", "");
		
		if(payTransEndDate.length() == 8){
			payTransEndDate += "235959";
		}
		
		this.payTransEndDate = payTransEndDate;
	}
	/**
	 * @return the transReqStartDt
	 */
	public String getTransReqStartDt() {
		return transReqStartDt;
	}
	/**
	 * @param transReqStartDt the transReqStartDt to set
	 */
	public void setTransReqStartDt(String transReqStartDt) {
		transReqStartDt = CommonUtil.removeSpecial(transReqStartDt);
		transReqStartDt = transReqStartDt.replaceAll("/", "");
		transReqStartDt = transReqStartDt.replaceAll("-", "");
		
		if(transReqStartDt.length() == 8){
			transReqStartDt += "000000";
		}
		
		this.transReqStartDt = transReqStartDt;
	}
	/**
	 * @return the transReqEndDt
	 */
	public String getTransReqEndDt() {
		return transReqEndDt;
	}
	/**
	 * @param transReqEndDt the transReqEndDt to set
	 */
	public void setTransReqEndDt(String transReqEndDt) {
		transReqEndDt = CommonUtil.removeSpecial(transReqEndDt);
		transReqEndDt = transReqEndDt.replaceAll("/", "");
		transReqEndDt = transReqEndDt.replaceAll("-", "");
		
		if(transReqEndDt.length() == 8){
			transReqEndDt += "235959";
		}
		
		this.transReqEndDt = transReqEndDt;
	}
	/**
	 * @return the withdrawReqStartDt
	 */
	public String getWithdrawReqStartDt() {
		return withdrawReqStartDt;
	}
	/**
	 * @param withdrawReqStartDt the withdrawReqStartDt to set
	 */
	public void setWithdrawReqStartDt(String withdrawReqStartDt) {
		withdrawReqStartDt = CommonUtil.removeSpecial(withdrawReqStartDt);
		withdrawReqStartDt = withdrawReqStartDt.replaceAll("/", "");
		withdrawReqStartDt = withdrawReqStartDt.replaceAll("-", "");
		
		if(withdrawReqStartDt.length() == 8){
			withdrawReqStartDt += "000000";
		}
		
		this.withdrawReqStartDt = withdrawReqStartDt;
	}
	/**
	 * @return the withdrawReqEndDt
	 */
	public String getWithdrawReqEndDt() {
		return withdrawReqEndDt;
	}
	/**
	 * @param withdrawReqEndDt the withdrawReqEndDt to set
	 */
	public void setWithdrawReqEndDt(String withdrawReqEndDt) {
		withdrawReqEndDt = CommonUtil.removeSpecial(withdrawReqEndDt);
		withdrawReqEndDt = withdrawReqEndDt.replaceAll("/", "");
		withdrawReqEndDt = withdrawReqEndDt.replaceAll("-", "");
		
		if(withdrawReqEndDt.length() == 8){
			withdrawReqEndDt += "235959";
		}
		
		this.withdrawReqEndDt = withdrawReqEndDt;
	}
	/**
	 * @return the withdrawApprStartDt
	 */
	public String getWithdrawApprStartDt() {
		return withdrawApprStartDt;
	}
	/**
	 * @param withdrawApprStartDt the withdrawApprStartDt to set
	 */
	public void setWithdrawApprStartDt(String withdrawApprStartDt) {
		withdrawApprStartDt = CommonUtil.removeSpecial(withdrawApprStartDt);
		withdrawApprStartDt = withdrawApprStartDt.replaceAll("/", "");
		withdrawApprStartDt = withdrawApprStartDt.replaceAll("-", "");
		
		if(withdrawApprStartDt.length() == 8){
			withdrawApprStartDt += "000000";
		}
		
		this.withdrawApprStartDt = withdrawApprStartDt;
	}
	/**
	 * @return the withdrawApprEndDt
	 */
	public String getWithdrawApprEndDt() {
		return withdrawApprEndDt;
	}
	/**
	 * @param withdrawApprEndDt the withdrawApprEndDt to set
	 */
	public void setWithdrawApprEndDt(String withdrawApprEndDt) {
		withdrawApprEndDt = CommonUtil.removeSpecial(withdrawApprEndDt);
		withdrawApprEndDt = withdrawApprEndDt.replaceAll("/", "");
		withdrawApprEndDt = withdrawApprEndDt.replaceAll("-", "");
		
		if(withdrawApprEndDt.length() == 8){
			withdrawApprEndDt += "235959";
		}
		
		this.withdrawApprEndDt = withdrawApprEndDt;
	}
	/**
	 * @return the purchaseStartDt
	 */
	public String getPurchaseStartDt() {
		return purchaseStartDt;
	}
	/**
	 * @param purchaseStartDt the purchaseStartDt to set
	 */
	public void setPurchaseStartDt(String purchaseStartDt) {
		purchaseStartDt = CommonUtil.removeSpecial(purchaseStartDt);
		purchaseStartDt = purchaseStartDt.replaceAll("/", "");
		purchaseStartDt = purchaseStartDt.replaceAll("-", "");
		
//		if(purchaseStartDt.length() == 8){
//			purchaseStartDt += "000000";
//		}
		
		this.purchaseStartDt = purchaseStartDt;
	}
	/**
	 * @return the purchaseEndDt
	 */
	public String getPurchaseEndDt() {
		return purchaseEndDt;
	}
	/**
	 * @param purchaseEndDt the purchaseEndDt to set
	 */
	public void setPurchaseEndDt(String purchaseEndDt) {
		purchaseEndDt = CommonUtil.removeSpecial(purchaseEndDt);
		purchaseEndDt = purchaseEndDt.replaceAll("/", "");
		purchaseEndDt = purchaseEndDt.replaceAll("-", "");
		
//		if(purchaseEndDt.length() == 8){
//			purchaseEndDt += "235959";
//		}
		
		this.purchaseEndDt = purchaseEndDt;
	}
	/**
	 * @return the currentPageNo
	 */
	public int getCurrentPageNo() {
		return currentPageNo;
	}
	/**
	 * @param currentPageNo the currentPageNo to set
	 */
	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	/**
	 * @return the payCls
	 */
	public String getPayCls() {
		return payCls;
	}

	/**
	 * @param payCls the payCls to set
	 */
	public void setPayCls(String payCls) {
		this.payCls = payCls;
	}

	/**
	 * @return the prchsStat
	 */
	public String getPrchsStat() {
		return prchsStat;
	}

	/**
	 * @param prchsStat the prchsStat to set
	 */
	public void setPrchsStat(String prchsStat) {
		this.prchsStat = prchsStat;
	}
	
}
