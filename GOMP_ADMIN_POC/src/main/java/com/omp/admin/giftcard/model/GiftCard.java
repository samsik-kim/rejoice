package com.omp.admin.giftcard.model;

import java.util.List;

import com.omp.commons.model.TotalCountCarriable;

public class GiftCard implements TotalCountCarriable {
	
	//getPresentCashList
	private String gcGroupGeq; 
	private String cardName; 
	private String cardType; 
	private String issueYn; 
	private String issueReqId;
	private String issueReqDt;
	private String issueReqTm;
	private String cardCount;
	private String cardRealAmt;
	
	//registReqissuecash
	private String issueType;
	private String mbrId;
	private String mbrNo;
	private String mbrGrade;
	private String groupSeq;		// 기프트 카드 그룹 관리 Key
	private String cardNm;			// 기프트 카드 명
	private String cardAmt;			// 캐쉬 포인트 
	private String cardCnt;			// 카드 발급 수
	private String regDt;			// 등록일
	private String validity;		// 유효 기간(개월)
	private String issueDt;			// 발행 일
	private Integer rowNum;			// 리스트 번호
	private String regCnt;			// 등록된 기프트 카드수 
	private String presentCashAmt;
	private List<String> failedMdnList;
	private String comp_file_name;
	
	private String mbrNm;		// 회원 이름
	private String mbrHpNum;	// 회원 휴대폰 번호
	private String searchType;
	private String startDate;
	private String endDate;
	private String searchKeyword;
	private String mbrCatCd;
	private String gcgroupseq; 
	private String serialNo;
	private String occrDt;
		
	public String getOccrDt() {
		return occrDt;
	}

	public void setOccrDt(String occrDt) {
		this.occrDt = occrDt;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getGcgroupseq() {
		return gcgroupseq;
	}

	public void setGcgroupseq(String gcgroupseq) {
		this.gcgroupseq = gcgroupseq;
	}

	public String getMbrCatCd() {
		return mbrCatCd;
	}

	public void setMbrCatCd(String mbrCatCd) {
		this.mbrCatCd = mbrCatCd;
	}

	public String getMbrNm() {
		return mbrNm;
	}

	public void setMbrNm(String mbrNm) {
		this.mbrNm = mbrNm;
	}

	public String getMbrHpNum() {
		return mbrHpNum;
	}

	public void setMbrHpNum(String mbrHpNum) {
		this.mbrHpNum = mbrHpNum;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public String getGroupSeq() {
		return groupSeq;
	}

	public void setGroupSeq(String groupSeq) {
		this.groupSeq = groupSeq;
	}

	public String getCardNm() {
		return cardNm;
	}

	public void setCardNm(String cardNm) {
		this.cardNm = cardNm;
	}

	public String getCardAmt() {
		return cardAmt;
	}

	public void setCardAmt(String cardAmt) {
		this.cardAmt = cardAmt;
	}

	public String getCardCnt() {
		return cardCnt;
	}

	public void setCardCnt(String cardCnt) {
		this.cardCnt = cardCnt;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public String getIssueDt() {
		return issueDt;
	}

	public void setIssueDt(String issueDt) {
		this.issueDt = issueDt;
	}

	public Integer getRowNum() {
		return rowNum;
	}

	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}

	public String getRegCnt() {
		return regCnt;
	}

	public void setRegCnt(String regCnt) {
		this.regCnt = regCnt;
	}

	public String getPresentCashAmt() {
		return presentCashAmt;
	}

	public void setPresentCashAmt(String presentCashAmt) {
		this.presentCashAmt = presentCashAmt;
	}

	public List<String> getFailedMdnList() {
		return failedMdnList;
	}

	public void setFailedMdnList(List<String> failedMdnList) {
		this.failedMdnList = failedMdnList;
	}

	public String getComp_file_name() {
		return comp_file_name;
	}

	public void setComp_file_name(String comp_file_name) {
		this.comp_file_name = comp_file_name;
	}

	public String getMbrId() {
		return mbrId;
	}

	public void setMbrId(String mbrId) {
		this.mbrId = mbrId;
	}

	public String getMbrNo() {
		return mbrNo;
	}

	public void setMbrNo(String mbrNo) {
		this.mbrNo = mbrNo;
	}

	public String getMbrGrade() {
		return mbrGrade;
	}

	public void setMbrGrade(String mbrGrade) {
		this.mbrGrade = mbrGrade;
	}


	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public String getGcGroupGeq() {
		return gcGroupGeq;
	}

	public void setGcGroupGeq(String gcGroupGeq) {
		this.gcGroupGeq = gcGroupGeq;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getIssueYn() {
		return issueYn;
	}

	public void setIssueYn(String issueYn) {
		this.issueYn = issueYn;
	}

	public String getIssueReqId() {
		return issueReqId;
	}

	public void setIssueReqId(String issueReqId) {
		this.issueReqId = issueReqId;
	}

	public String getIssueReqDt() {
		return issueReqDt;
	}

	public void setIssueReqDt(String issueReqDt) {
		this.issueReqDt = issueReqDt;
	}

	public String getIssueReqTm() {
		return issueReqTm;
	}

	public void setIssueReqTm(String issueReqTm) {
		this.issueReqTm = issueReqTm;
	}

	public String getCardCount() {
		return cardCount;
	}

	public void setCardCount(String cardCount) {
		this.cardCount = cardCount;
	}

	public String getCardRealAmt() {
		return cardRealAmt;
	}

	public void setCardRealAmt(String cardRealAmt) {
		this.cardRealAmt = cardRealAmt;
	}

	/*
	 * PAGING REFER
	 */
	private int rnum;
	// paging
	private Long total_count;
	
	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	
	public Long getTotal_count() {
		return total_count;
	}

	public void setTotal_count(Long total_count) {
		this.total_count = total_count;
	}
	
	// baseInfo 

	@Override
	public Long getTotalCount() {
		return this.total_count;
	}

	@Override
	public void setTotalCount(Long totalCount) {
		this.total_count = totalCount;
	}
	
}