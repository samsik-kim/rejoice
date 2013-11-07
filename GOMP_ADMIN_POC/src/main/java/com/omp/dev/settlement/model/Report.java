package com.omp.dev.settlement.model;

import com.omp.commons.model.CommonModel;
import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;
import com.omp.commons.model.TotalCountCarriable;


@SuppressWarnings("serial")
public class Report extends CommonModel implements Pagenateable, TotalCountCarriable
{
	private PagenateInfoModel	page		= new PagenateInfoModel();
	private Long    total_count = 0L;
	private String rnum;
	private int    rowCnt = 0;		//행번호.
	private String firstAccessYN; //페이지에 처음 접금여부
	private String searchType;	//화면검색조건 : 검색타입
	private String searchCont; //화면검색조건 : 검색내용
	private String baseSaleYm; /* 기준 정산_년월   */
	private String saleYm; /* 정산_년월   */
	private String mbrNo; /*  개발자번호  */
	private String mbrId; /*  개발자Id  */
	private double totlCnt; /*  총건수  */ 
	private double totlPayAmt; /*  총결제금액  */
	private double devBuDvAmtSum; /*  개발자_배분_금액_합계  */
	private double ompDvAmtSum; /*  OMP_배분_금액_합계  */
	private double beforeTaxAmt;  /*  세전금액  */
	private double txtAmt; /*  세금  */
	private String aggtStatCd; /*  정산_상태_코드  */
	private String currencyUnit;  /*  통화단위코드  */
	private String rmtReq_reason; /* 송금신청 에러 사유   */
	private String displayYn;  /*  표시여부  */
	private String insDttm; /*  등록일시  */
	private String insBy; /*  등록자  */
	private String updDttm;  /* 수정일시   */
	private String updBy; /*  수정자  */
	private String mbrNm;  /* 이름  */
	private String settYyyymm;/* 정산월(판매월)  */
	private String prodId; /* 상품ID  */
	private String prodNm; /* 상품명  */
	private double prodPrc; /* 상품가격  */
	private String settlRt; /* 정산율 */
	private double totlAmt; /* 판매대금 */
	private double devAmt; /* 개발자 정산금 */
	private String mbrNmId; /* 개발자명(개발자ID)  */ 
	private String rmtReqYyyymm;  //송금신청월
	private String adjStatCd;   //정산상태코드
	private String processMessage; //처리메세지 
	private String wholeAdjStatCd;   //특정월_전체_정산상태코드
	private String mbrClsCd;   /*  회원구분코드  */
	private String mbrGrCd;  /*  회원등급코드  */ 
	private String rmtReqReason; /*  송금신청 에러사유  */
	private String searchTimeBlock;  //검색기간 전체 부분 검색구분
	private String infoPageYN;  //상세페이지에서 이동여부
	private String acctNm; /* 예금주명 */
	private String backNm;  /*  은행명 */
	private String acctNo;  /*  계좌번호 */
	private String rmtReason; //송금포기사유
	private String adjSaleYm; /* 조정액 정산_년월   */
	private double adjAmt; /*  조정액  */
	private double adjDevBuDvAmtSum; /*  조정액 개발자_배분_금액_합계  */
	private String adjReason; /*  조정사유  */
	private String adjAggtStatCd; /* 조정액 정산_상태_코드  */
	private double adjTxtAmt; /*  조정액 세금  */
	private String adjRmtReqReason; /*  조정액 송금신청 에러사유  */
	private String rmtReqYN; //송금요청가능여부
	private String adjYn; //정산조정구분
	private double shareAmt; //배분금액
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * @return the sharAmt
	 */
	public double getShareAmt() {
		return shareAmt;
	}

	/**
	 * @param sharAmt the sharAmt to set
	 */
	public void setShareAmt(double shareAmt) {
		this.shareAmt = shareAmt;
	}

	/**
	 * @return the adjYn
	 */
	public String getAdjYn() {
		return adjYn;
	}

	/**
	 * @param adjYn the adjYn to set
	 */
	public void setAdjYn(String adjYn) {
		this.adjYn = adjYn;
	}

	/**
	 * @return the total_count
	 */
	public Long getTotal_count() {
		return total_count;
	}

	/**
	 * @param total_count the total_count to set
	 */
	public void setTotal_count(Long total_count) {
		this.total_count = total_count;
	}

	/**
	 * @return the totlCnt
	 */
	public double getTotlCnt() {
		return totlCnt;
	}

	/**
	 * @param totlCnt the totlCnt to set
	 */
	public void setTotlCnt(double totlCnt) {
		this.totlCnt = totlCnt;
	}

	/**
	 * @return the totlPayAmt
	 */
	public double getTotlPayAmt() {
		return totlPayAmt;
	}

	/**
	 * @param totlPayAmt the totlPayAmt to set
	 */
	public void setTotlPayAmt(double totlPayAmt) {
		this.totlPayAmt = totlPayAmt;
	}

	/**
	 * @return the devBuDvAmtSum
	 */
	public double getDevBuDvAmtSum() {
		return devBuDvAmtSum;
	}

	/**
	 * @param devBuDvAmtSum the devBuDvAmtSum to set
	 */
	public void setDevBuDvAmtSum(double devBuDvAmtSum) {
		this.devBuDvAmtSum = devBuDvAmtSum;
	}

	/**
	 * @return the ompDvAmtSum
	 */
	public double getOmpDvAmtSum() {
		return ompDvAmtSum;
	}

	/**
	 * @param ompDvAmtSum the ompDvAmtSum to set
	 */
	public void setOmpDvAmtSum(double ompDvAmtSum) {
		this.ompDvAmtSum = ompDvAmtSum;
	}

	/**
	 * @return the beforeTaxAmt
	 */
	public double getBeforeTaxAmt() {
		return beforeTaxAmt;
	}

	/**
	 * @param beforeTaxAmt the beforeTaxAmt to set
	 */
	public void setBeforeTaxAmt(double beforeTaxAmt) {
		this.beforeTaxAmt = beforeTaxAmt;
	}

	/**
	 * @return the txtAmt
	 */
	public double getTxtAmt() {
		return txtAmt;
	}

	/**
	 * @param txtAmt the txtAmt to set
	 */
	public void setTxtAmt(double txtAmt) {
		this.txtAmt = txtAmt;
	}

	/**
	 * @return the prodPrc
	 */
	public double getProdPrc() {
		return prodPrc;
	}

	/**
	 * @param prodPrc the prodPrc to set
	 */
	public void setProdPrc(double prodPrc) {
		this.prodPrc = prodPrc;
	}

	/**
	 * @return the totlAmt
	 */
	public double getTotlAmt() {
		return totlAmt;
	}

	/**
	 * @param totlAmt the totlAmt to set
	 */
	public void setTotlAmt(double totlAmt) {
		this.totlAmt = totlAmt;
	}

	/**
	 * @return the devAmt
	 */
	public double getDevAmt() {
		return devAmt;
	}

	/**
	 * @param devAmt the devAmt to set
	 */
	public void setDevAmt(double devAmt) {
		this.devAmt = devAmt;
	}

	/**
	 * @return the adjAmt
	 */
	public double getAdjAmt() {
		return adjAmt;
	}

	/**
	 * @param adjAmt the adjAmt to set
	 */
	public void setAdjAmt(double adjAmt) {
		this.adjAmt = adjAmt;
	}

	/**
	 * @return the adjTxtAmt
	 */
	public double getAdjTxtAmt() {
		return adjTxtAmt;
	}

	/**
	 * @param adjTxtAmt the adjTxtAmt to set
	 */
	public void setAdjTxtAmt(double adjTxtAmt) {
		this.adjTxtAmt = adjTxtAmt;
	}

	/**
	 * @return the rmtReqYN
	 */
	public String getRmtReqYN() {
		return rmtReqYN;
	}

	/**
	 * @param rmtReqYN the rmtReqYN to set
	 */
	public void setRmtReqYN(String rmtReqYN) {
		this.rmtReqYN = rmtReqYN;
	}

	/**
	 * @return the baseSaleYm
	 */
	public String getBaseSaleYm() {
		return baseSaleYm;
	}

	/**
	 * @param baseSaleYm the baseSaleYm to set
	 */
	public void setBaseSaleYm(String baseSaleYm) {
		this.baseSaleYm = baseSaleYm;
	}

	/**
	 * @return the adjAggtStatCd
	 */
	public String getAdjAggtStatCd() {
		return adjAggtStatCd;
	}

	/**
	 * @param adjAggtStatCd the adjAggtStatCd to set
	 */
	public void setAdjAggtStatCd(String adjAggtStatCd) {
		this.adjAggtStatCd = adjAggtStatCd;
	}

	/**
	 * @return the adjRmtReqReason
	 */
	public String getAdjRmtReqReason() {
		return adjRmtReqReason;
	}

	/**
	 * @param adjRmtReqReason the adjRmtReqReason to set
	 */
	public void setAdjRmtReqReason(String adjRmtReqReason) {
		this.adjRmtReqReason = adjRmtReqReason;
	}

	/**
	 * @return the adjSaleYm
	 */
	public String getAdjSaleYm() {
		return adjSaleYm;
	}

	/**
	 * @param adjSaleYm the adjSaleYm to set
	 */
	public void setAdjSaleYm(String adjSaleYm) {
		this.adjSaleYm = adjSaleYm;
	}

	/**
	 * @return the adjDevBuDvAmtSum
	 */
	public double getAdjDevBuDvAmtSum() {
		return adjDevBuDvAmtSum;
	}

	/**
	 * @param adjDevBuDvAmtSum the adjDevBuDvAmtSum to set
	 */
	public void setAdjDevBuDvAmtSum(double adjDevBuDvAmtSum) {
		this.adjDevBuDvAmtSum = adjDevBuDvAmtSum;
	}

	

	/**
	 * @return the rmtReason
	 */
	public String getRmtReason() {
		return rmtReason;
	}

	/**
	 * @param rmtReason the rmtReason to set
	 */
	public void setRmtReason(String rmtReason) {
		this.rmtReason = rmtReason;
	}

	/**
	 * @return the acctNm
	 */
	public String getAcctNm() {
		return acctNm;
	}

	/**
	 * @param acctNm the acctNm to set
	 */
	public void setAcctNm(String acctNm) {
		this.acctNm = acctNm;
	}

	/**
	 * @return the backNm
	 */
	public String getBackNm() {
		return backNm;
	}

	/**
	 * @param backNm the backNm to set
	 */
	public void setBackNm(String backNm) {
		this.backNm = backNm;
	}

	/**
	 * @return the acctNo
	 */
	public String getAcctNo() {
		return acctNo;
	}

	/**
	 * @param acctNo the acctNo to set
	 */
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	/**
	 * @return the infoPageYN
	 */
	public String getInfoPageYN() {
		return infoPageYN;
	}

	/**
	 * @param infoPageYN the infoPageYN to set
	 */
	public void setInfoPageYN(String infoPageYN) {
		this.infoPageYN = infoPageYN;
	}

	/**
	 * @return the searchTimeBlock
	 */
	public String getSearchTimeBlock() {
		return searchTimeBlock;
	}

	/**
	 * @param searchTimeBlock the searchTimeBlock to set
	 */
	public void setSearchTimeBlock(String searchTimeBlock) {
		this.searchTimeBlock = searchTimeBlock;
	}

	/**
	 * @return the rmtReqReason
	 */
	public String getRmtReqReason() {
		return rmtReqReason;
	}

	/**
	 * @param rmtReqReason the rmtReqReason to set
	 */
	public void setRmtReqReason(String rmtReqReason) {
		this.rmtReqReason = rmtReqReason;
	}

	/**
	 * @return the mbrClsCd
	 */
	public String getMbrClsCd() {
		return mbrClsCd;
	}

	/**
	 * @param mbrClsCd the mbrClsCd to set
	 */
	public void setMbrClsCd(String mbrClsCd) {
		this.mbrClsCd = mbrClsCd;
	}

	/**
	 * @return the mbrGrCd
	 */
	public String getMbrGrCd() {
		return mbrGrCd;
	}

	/**
	 * @param mbrGrCd the mbrGrCd to set
	 */
	public void setMbrGrCd(String mbrGrCd) {
		this.mbrGrCd = mbrGrCd;
	}

	/**
	 * @return the wholeAdjStatCd
	 */
	public String getWholeAdjStatCd() {
		return wholeAdjStatCd;
	}

	/**
	 * @param wholeAdjStatCd the wholeAdjStatCd to set
	 */
	public void setWholeAdjStatCd(String wholeAdjStatCd) {
		this.wholeAdjStatCd = wholeAdjStatCd;
	}

	/**
	 * @return the processMessage
	 */
	public String getProcessMessage() {
		return processMessage;
	}

	/**
	 * @param processMessage the processMessage to set
	 */
	public void setProcessMessage(String processMessage) {
		this.processMessage = processMessage;
	}

	/**
	 * @return the adjStatCd
	 */
	public String getAdjStatCd() {
		return adjStatCd;
	}

	/**
	 * @param adjStatCd the adjStatCd to set
	 */
	public void setAdjStatCd(String adjStatCd) {
		this.adjStatCd = adjStatCd;
	}

	/**
	 * @return the mbrId
	 */
	public String getMbrId() {
		return mbrId;
	}

	/**
	 * @param mbrId the mbrId to set
	 */
	public void setMbrId(String mbrId) {
		this.mbrId = mbrId;
	}

	/**
	 * @return the rmtReqYyyymm
	 */
	public String getRmtReqYyyymm() {
		return rmtReqYyyymm;
	}

	/**
	 * @param rmtReqYyyymm the rmtReqYyyymm to set
	 */
	public void setRmtReqYyyymm(String rmtReqYyyymm) {
		this.rmtReqYyyymm = rmtReqYyyymm;
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
	 * @return the searchCont
	 */
	public String getSearchCont() {
		return searchCont;
	}

	/**
	 * @param searchCont the searchCont to set
	 */
	public void setSearchCont(String searchCont) {
		this.searchCont = searchCont;
	}

	/**
	 * @return the settYyyymm
	 */
	public String getSettYyyymm() {
		return settYyyymm;
	}

	/**
	 * @param settYyyymm the settYyyymm to set
	 */
	public void setSettYyyymm(String settYyyymm) {
		this.settYyyymm = settYyyymm;
	}

	/**
	 * @return the prodId
	 */
	public String getProdId() {
		return prodId;
	}

	/**
	 * @param prodId the prodId to set
	 */
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	/**
	 * @return the prodNm
	 */
	public String getProdNm() {
		return prodNm;
	}

	/**
	 * @param prodNm the prodNm to set
	 */
	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
	}

	

	/**
	 * @return the settlRt
	 */
	public String getSettlRt() {
		return settlRt;
	}

	/**
	 * @param settlRt the settlRt to set
	 */
	public void setSettlRt(String settlRt) {
		this.settlRt = settlRt;
	}

	

	/**
	 * @return the mbrNmId
	 */
	public String getMbrNmId() {
		return mbrNmId;
	}

	/**
	 * @param mbrNmId the mbrNmId to set
	 */
	public void setMbrNmId(String mbrNmId) {
		this.mbrNmId = mbrNmId;
	}

	/**
	 * @return the mbrNm
	 */
	public String getMbrNm() {
		return mbrNm;
	}

	/**
	 * @param mbrNm the mbrNm to set
	 */
	public void setMbrNm(String mbrNm) {
		this.mbrNm = mbrNm;
	}

	/**
	 * @return the saleYm
	 */
	public String getSaleYm() {
		return saleYm;
	}

	/**
	 * @param saleYm the saleYm to set
	 */
	public void setSaleYm(String saleYm) {
		this.saleYm = saleYm;
	}

	/**
	 * @return the mbrNo
	 */
	public String getMbrNo() {
		return mbrNo;
	}

	/**
	 * @param mbrNo the mbrNo to set
	 */
	public void setMbrNo(String mbrNo) {
		this.mbrNo = mbrNo;
	}

	
	/**
	 * @return the aggtStatCd
	 */
	public String getAggtStatCd() {
		return aggtStatCd;
	}

	/**
	 * @param aggtStatCd the aggtStatCd to set
	 */
	public void setAggtStatCd(String aggtStatCd) {
		this.aggtStatCd = aggtStatCd;
	}

	/**
	 * @return the currencyUnit
	 */
	public String getCurrencyUnit() {
		return currencyUnit;
	}

	/**
	 * @param currencyUnit the currencyUnit to set
	 */
	public void setCurrencyUnit(String currencyUnit) {
		this.currencyUnit = currencyUnit;
	}

	/**
	 * @return the adjReason
	 */
	public String getAdjReason() {
		return adjReason;
	}

	/**
	 * @param adjReason the adjReason to set
	 */
	public void setAdjReason(String adjReason) {
		this.adjReason = adjReason;
	}

	/**
	 * @return the rmtReq_reason
	 */
	public String getRmtReq_reason() {
		return rmtReq_reason;
	}

	/**
	 * @param rmtReq_reason the rmtReq_reason to set
	 */
	public void setRmtReq_reason(String rmtReq_reason) {
		this.rmtReq_reason = rmtReq_reason;
	}

	/**
	 * @return the displayYn
	 */
	public String getDisplayYn() {
		return displayYn;
	}

	/**
	 * @param displayYn the displayYn to set
	 */
	public void setDisplayYn(String displayYn) {
		this.displayYn = displayYn;
	}

	/**
	 * @return the insDttm
	 */
	public String getInsDttm() {
		return insDttm;
	}

	/**
	 * @param insDttm the insDttm to set
	 */
	public void setInsDttm(String insDttm) {
		this.insDttm = insDttm;
	}

	/**
	 * @return the insBy
	 */
	public String getInsBy() {
		return insBy;
	}

	/**
	 * @param insBy the insBy to set
	 */
	public void setInsBy(String insBy) {
		this.insBy = insBy;
	}

	/**
	 * @return the updDttm
	 */
	public String getUpdDttm() {
		return updDttm;
	}

	/**
	 * @param updDttm the updDttm to set
	 */
	public void setUpdDttm(String updDttm) {
		this.updDttm = updDttm;
	}

	/**
	 * @return the updBy
	 */
	public String getUpdBy() {
		return updBy;
	}

	/**
	 * @param updBy the updBy to set
	 */
	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}

	/**
	 * @return the firstAccessYN
	 */
	public String getFirstAccessYN() {
		return firstAccessYN;
	}

	/**
	 * @param firstAccessYN the firstAccessYN to set
	 */
	public void setFirstAccessYN(String firstAccessYN) {
		this.firstAccessYN = firstAccessYN;
	}

	
	/**
	 * @return the page
	 */
	public PagenateInfoModel getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(PagenateInfoModel page) {
		this.page = page;
	}

	/**
	 * @return the rnum
	 */
	public String getRnum() {
		return rnum;
	}

	/**
	 * @param rnum the rnum to set
	 */
	public void setRnum(String rnum) {
		this.rnum = rnum;
	}

	/**
	 * @return the rowCnt
	 */
	public int getRowCnt() {
		return rowCnt;
	}

	/**
	 * @param rowCnt the rowCnt to set
	 */
	public void setRowCnt(int rowCnt) {
		this.rowCnt = rowCnt;
	}

	
	@Override
	public Long getTotalCount() {
		return this.total_count;
	}

	@Override
	public void setTotalCount(Long totalCount) {
		this.total_count	= totalCount;
	}
	
	
	
}
