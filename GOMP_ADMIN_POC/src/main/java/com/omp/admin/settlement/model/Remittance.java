package com.omp.admin.settlement.model;

import com.omp.commons.model.CommonModel;
import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;
import com.omp.commons.model.TotalCountCarriable;


@SuppressWarnings("serial")
public class Remittance extends CommonModel implements Pagenateable, TotalCountCarriable
{
	private PagenateInfoModel	page		= new PagenateInfoModel();
	private Long    total_count = 0L;
	private String rnum;
	private int    rowCnt = 0;		//행번호.
	private String searchTimeBlock;  //검색기간 전체 부분 검색구분
	private String saleYm;	//정산_년월
	private String mbrNo;	//개발자번호
	private String rmtReqYyyymm;	//송금신청년월
	private String rmtReqDt;	//송금_요청_일자
	private double rmtAmt;	//송금_금액
	private String paySchDt;	//지급_예정_일자
	private String payReqDt;	//지급_요청_일자
	private String payEndDt;	//지급_완료_일자
	private String rmtStatCd;	//송금_상태_코드
	private String rmtEndCd;	//송금_완료_코드
	private String rmtEndName;	//송금_완료_명
	private String mbrNm;	//개발자명
	private String hpNo;	//개발자전화
	private String acctNo;	//계좌번호
	private String backNm;	//은행명
	private String backCd;	//은행코드
	private String backBranchNm;	//은행지점명
	private String backBranchCd;	//은행지점코드
	private String currencyUnit;	//통화단위코드
	private String currencyUnitName;	//통화단위코드명
	private String backGlCd;	//Swift Code/IBAN
	private String acctNm;	//예금주명
	private String insDttm;	//등록일시
	private String insBy;	//등록자
	private String updDttm;	//수정일시
	private String updBy;	//수정자
	private String searchType;	//화면검색조건 : 검색타입
	private String searchCont; //화면검색조건 : 검색내용
	//private String searchSaleYm;	//화면검색조건 : 정산_년월
	//private String searchMbrNo;	//화면검색조건 : 개발자번호
	//private String searchRmtReqYyyymm;	//화면검색조건 : 송금신청년월
	//private String searchRmtEndCd;     //화면검색조건 : 송금_완료_코드
	private String rmtReason; //송금결과 사유
	private String rmtGiveupReason; //송금포기 사유
	private String mbrClsCd; //회원구분코드
	private String mbrGrCd; //회원등급코드
	private String regId;
	private double totlRmtPayAmt; /*  총송금금액   */
	private double totlRmtPayUsAmt; /*  총송금금액   */
	private long waitRmtCnt; /*  송금대기건수   */
	private long totlRmtCnt; /*  송금완료건수   */ 
	private long crovCnt; /*   이월처리건수  */
	private long reRmtCnt; /*  재송금건수   */
	private long giveupCnt; /*   송금포기건수   */ 
	private double adjCnt; /*  조정액   */
	private double twAmt; /*  대만달러 송금액   */
	private double usAmt; /*  미달러 송금액   */
	private String searchStartYm; //검색시작년월
	private String searchEndYm; //검색종료년월
	private String firstAccessYN; //페이지에 처음 접금여부
	private String adjStatCd;  /*  정산_상태_코드  */
	private String mbrNoNm;	//개발자번호_명 결합명
	private String processMessage; //처리메세지
	private String mbrId; //개발자ID
	private String aggtStatCd; //정산 상태 코드
	private String adjYn; //정산조정구분
	private String rntRstEndInsBy; //송금결과마감등록자
	private String AdMgrPswdNo;
	
	
	
	
	
	
	
	
	
	
	
    
	
	/**
	 * @return the adMgrPswdNo
	 */
	public String getAdMgrPswdNo() {
		return AdMgrPswdNo;
	}

	/**
	 * @param adMgrPswdNo the adMgrPswdNo to set
	 */
	public void setAdMgrPswdNo(String adMgrPswdNo) {
		AdMgrPswdNo = adMgrPswdNo;
	}

	/**
	 * @return the totlRmtPayUsAmt
	 */
	public double getTotlRmtPayUsAmt() {
		return totlRmtPayUsAmt;
	}

	/**
	 * @param totlRmtPayUsAmt the totlRmtPayUsAmt to set
	 */
	public void setTotlRmtPayUsAmt(double totlRmtPayUsAmt) {
		this.totlRmtPayUsAmt = totlRmtPayUsAmt;
	}

	/**
	 * @return the rntRstEndInsBy
	 */
	public String getRntRstEndInsBy() {
		return rntRstEndInsBy;
	}

	/**
	 * @param rntRstEndInsBy the rntRstEndInsBy to set
	 */
	public void setRntRstEndInsBy(String rntRstEndInsBy) {
		this.rntRstEndInsBy = rntRstEndInsBy;
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
	 * @return the waitRmtCnt
	 */
	public long getWaitRmtCnt() {
		return waitRmtCnt;
	}

	/**
	 * @param waitRmtCnt the waitRmtCnt to set
	 */
	public void setWaitRmtCnt(long waitRmtCnt) {
		this.waitRmtCnt = waitRmtCnt;
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
	 * @return the mbrNoNm
	 */
	public String getMbrNoNm() {
		return mbrNoNm;
	}

	/**
	 * @param mbrNoNm the mbrNoNm to set
	 */
	public void setMbrNoNm(String mbrNoNm) {
		this.mbrNoNm = mbrNoNm;
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
	 * @return the searchStartYm
	 */
	public String getSearchStartYm() {
		return searchStartYm;
	}

	/**
	 * @param searchStartYm the searchStartYm to set
	 */
	public void setSearchStartYm(String searchStartYm) {
		this.searchStartYm = searchStartYm;
	}

	/**
	 * @return the searchEndYm
	 */
	public String getSearchEndYm() {
		return searchEndYm;
	}

	/**
	 * @param searchEndYm the searchEndYm to set
	 */
	public void setSearchEndYm(String searchEndYm) {
		this.searchEndYm = searchEndYm;
	}

	/**
	 * @return the totlRmtPayAmt
	 */
	public double getTotlRmtPayAmt() {
		return totlRmtPayAmt;
	}

	/**
	 * @param totlRmtPayAmt the totlRmtPayAmt to set
	 */
	public void setTotlRmtPayAmt(double totlRmtPayAmt) {
		this.totlRmtPayAmt = totlRmtPayAmt;
	}

	/**
	 * @return the totlRmtCnt
	 */
	public long getTotlRmtCnt() {
		return totlRmtCnt;
	}

	/**
	 * @param totlRmtCnt the totlRmtCnt to set
	 */
	public void setTotlRmtCnt(long totlRmtCnt) {
		this.totlRmtCnt = totlRmtCnt;
	}

	/**
	 * @return the crovCnt
	 */
	public long getCrovCnt() {
		return crovCnt;
	}

	/**
	 * @param crovCnt the crovCnt to set
	 */
	public void setCrovCnt(long crovCnt) {
		this.crovCnt = crovCnt;
	}

	/**
	 * @return the reRmtCnt
	 */
	public long getReRmtCnt() {
		return reRmtCnt;
	}

	/**
	 * @param reRmtCnt the reRmtCnt to set
	 */
	public void setReRmtCnt(long reRmtCnt) {
		this.reRmtCnt = reRmtCnt;
	}

	/**
	 * @return the giveupCnt
	 */
	public long getGiveupCnt() {
		return giveupCnt;
	}

	/**
	 * @param giveupCnt the giveupCnt to set
	 */
	public void setGiveupCnt(long giveupCnt) {
		this.giveupCnt = giveupCnt;
	}

	/**
	 * @return the adjCnt
	 */
	public double getAdjCnt() {
		return adjCnt;
	}

	/**
	 * @param adjCnt the adjCnt to set
	 */
	public void setAdjCnt(double adjCnt) {
		this.adjCnt = adjCnt;
	}

	/**
	 * @return the twAmt
	 */
	public double getTwAmt() {
		return twAmt;
	}

	/**
	 * @param twAmt the twAmt to set
	 */
	public void setTwAmt(double twAmt) {
		this.twAmt = twAmt;
	}

	/**
	 * @return the usAmt
	 */
	public double getUsAmt() {
		return usAmt;
	}

	/**
	 * @param usAmt the usAmt to set
	 */
	public void setUsAmt(double usAmt) {
		this.usAmt = usAmt;
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
	 * @return the regId
	 */
	public String getRegId() {
		return regId;
	}

	/**
	 * @param regId the regId to set
	 */
	public void setRegId(String regId) {
		this.regId = regId;
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
	 * @return the rmtGiveupReason
	 */
	public String getRmtGiveupReason() {
		return rmtGiveupReason;
	}

	/**
	 * @param rmtGiveupReason the rmtGiveupReason to set
	 */
	public void setRmtGiveupReason(String rmtGiveupReason) {
		this.rmtGiveupReason = rmtGiveupReason;
	}

	/**
	 * @return the currencyUnitName
	 */
	public String getCurrencyUnitName() {
		return currencyUnitName;
	}

	/**
	 * @param currencyUnitName the currencyUnitName to set
	 */
	public void setCurrencyUnitName(String currencyUnitName) {
		this.currencyUnitName = currencyUnitName;
	}

	/**
	 * @return the rmtEndName
	 */
	public String getRmtEndName() {
		return rmtEndName;
	}

	/**
	 * @param rmtEndName the rmtEndName to set
	 */
	public void setRmtEndName(String rmtEndName) {
		this.rmtEndName = rmtEndName;
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
	 * @return the rmtReqDt
	 */
	public String getRmtReqDt() {
		return rmtReqDt;
	}

	/**
	 * @param rmtReqDt the rmtReqDt to set
	 */
	public void setRmtReqDt(String rmtReqDt) {
		this.rmtReqDt = rmtReqDt;
	}

	/**
	 * @return the rmtAmt
	 */
	public double getRmtAmt() {
		return rmtAmt;
	}

	/**
	 * @param rmtAmt the rmtAmt to set
	 */
	public void setRmtAmt(double rmtAmt) {
		this.rmtAmt = rmtAmt;
	}

	/**
	 * @return the paySchDt
	 */
	public String getPaySchDt() {
		return paySchDt;
	}

	/**
	 * @param paySchDt the paySchDt to set
	 */
	public void setPaySchDt(String paySchDt) {
		this.paySchDt = paySchDt;
	}

	/**
	 * @return the payReqDt
	 */
	public String getPayReqDt() {
		return payReqDt;
	}

	/**
	 * @param payReqDt the payReqDt to set
	 */
	public void setPayReqDt(String payReqDt) {
		this.payReqDt = payReqDt;
	}

	/**
	 * @return the payEndDt
	 */
	public String getPayEndDt() {
		return payEndDt;
	}

	/**
	 * @param payEndDt the payEndDt to set
	 */
	public void setPayEndDt(String payEndDt) {
		this.payEndDt = payEndDt;
	}

	/**
	 * @return the rmtStatCd
	 */
	public String getRmtStatCd() {
		return rmtStatCd;
	}

	/**
	 * @param rmtStatCd the rmtStatCd to set
	 */
	public void setRmtStatCd(String rmtStatCd) {
		this.rmtStatCd = rmtStatCd;
	}

	/**
	 * @return the rmtEndCd
	 */
	public String getRmtEndCd() {
		return rmtEndCd;
	}

	/**
	 * @param rmtEndCd the rmtEndCd to set
	 */
	public void setRmtEndCd(String rmtEndCd) {
		this.rmtEndCd = rmtEndCd;
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
	 * @return the hpNo
	 */
	public String getHpNo() {
		return hpNo;
	}

	/**
	 * @param hpNo the hpNo to set
	 */
	public void setHpNo(String hpNo) {
		this.hpNo = hpNo;
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
	 * @return the backCd
	 */
	public String getBackCd() {
		return backCd;
	}

	/**
	 * @param backCd the backCd to set
	 */
	public void setBackCd(String backCd) {
		this.backCd = backCd;
	}

	/**
	 * @return the backBranchNm
	 */
	public String getBackBranchNm() {
		return backBranchNm;
	}

	/**
	 * @param backBranchNm the backBranchNm to set
	 */
	public void setBackBranchNm(String backBranchNm) {
		this.backBranchNm = backBranchNm;
	}

	/**
	 * @return the backBranchCd
	 */
	public String getBackBranchCd() {
		return backBranchCd;
	}

	/**
	 * @param backBranchCd the backBranchCd to set
	 */
	public void setBackBranchCd(String backBranchCd) {
		this.backBranchCd = backBranchCd;
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
	 * @return the backGlCd
	 */
	public String getBackGlCd() {
		return backGlCd;
	}

	/**
	 * @param backGlCd the backGlCd to set
	 */
	public void setBackGlCd(String backGlCd) {
		this.backGlCd = backGlCd;
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
