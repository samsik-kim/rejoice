package com.omp.admin.settlement.model;

import com.omp.commons.model.CommonModel;
import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;
import com.omp.commons.model.TotalCountCarriable;


@SuppressWarnings("serial")
public class Receipt extends CommonModel implements Pagenateable, TotalCountCarriable
{
	private PagenateInfoModel	page		= new PagenateInfoModel();
	private Long    total_count = 0L;
	private String rnum;
	private int    rowCnt = 0;		//행번호.
	private String rtYearterm;		//영수증 년도/회차
	private String rtStartYearterm;		//시작 영수증 년도/회차 : 검색조건에서 사용
	private String rtEndYearterm;		//끝    영수증 년도/회차   :검색조건에서 사용: 
	private String rtUseTime;		//사용기간
	private String occrNo;			//일련번호
	private String maxOccrNo;		//영수증 년도/회차 별 최대 일련번호
	private String rtStartNo;		//시작번호
	private String rtEndNo;			//끝번호
	private String rtTotlCnt;		//전체건수
	private String rtUseCnt;		//사용건수
	private String rtUnuseCnt;		//남은건수
	private String rtNo;			//영수증번호
	private String rtPossbilYn;		//수정가능여부
	private String regId;			//등록자아이디
	private String regDt;			//등록일자
	private String receiptFromYear; //검색시작년도
	private String receiptFromMonth; //검색시작월
	private String receiptEndYear;	//검색종료년도
	private String receiptEndMonth;	//검색종료월
	private String rtPrfix;		//영수증앞변호
	private String firstAccessYN; //페이지에 처음 접금여부
	private String processMessage; //처리완료메세지
	
	
	
	
	
	
	
	
	
	
	
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
	 * @return the rtPrfix
	 */
	public String getRtPrfix() {
		return rtPrfix;
	}

	/**
	 * @param rtPrfix the rtPrfix to set
	 */
	public void setRtPrfix(String rtPrfix) {
		this.rtPrfix = rtPrfix;
	}

	/**
	 * @return the receiptFromYear
	 */
	public String getReceiptFromYear() {
		return receiptFromYear;
	}

	/**
	 * @param receiptFromYear the receiptFromYear to set
	 */
	public void setReceiptFromYear(String receiptFromYear) {
		this.receiptFromYear = receiptFromYear;
	}

	/**
	 * @return the receiptFromMonth
	 */
	public String getReceiptFromMonth() {
		return receiptFromMonth;
	}

	/**
	 * @param receiptFromMonth the receiptFromMonth to set
	 */
	public void setReceiptFromMonth(String receiptFromMonth) {
		this.receiptFromMonth = receiptFromMonth;
	}

	/**
	 * @return the receiptEndYear
	 */
	public String getReceiptEndYear() {
		return receiptEndYear;
	}

	/**
	 * @param receiptEndYear the receiptEndYear to set
	 */
	public void setReceiptEndYear(String receiptEndYear) {
		this.receiptEndYear = receiptEndYear;
	}

	/**
	 * @return the receiptEndMonth
	 */
	public String getReceiptEndMonth() {
		return receiptEndMonth;
	}

	/**
	 * @param receiptEndMonth the receiptEndMonth to set
	 */
	public void setReceiptEndMonth(String receiptEndMonth) {
		this.receiptEndMonth = receiptEndMonth;
	}

	/**
	 * @return the maxOccrNo
	 */
	public String getMaxOccrNo() {
		return maxOccrNo;
	}

	/**
	 * @param maxOccrNo the maxOccrNo to set
	 */
	public void setMaxOccrNo(String maxOccrNo) {
		this.maxOccrNo = maxOccrNo;
	}
	
	
	/**
	 * @return the rtNo
	 */
	public String getRtNo() {
		return rtNo;
	}

	/**
	 * @param rtNo the rtNo to set
	 */
	public void setRtNo(String rtNo) {
		this.rtNo = rtNo;
	}

	/**
	 * @return the rtStartYearterm
	 */
	public String getRtStartYearterm() {
		return rtStartYearterm;
	}

	/**
	 * @param rtStartYearterm the rtStartYearterm to set
	 */
	public void setRtStartYearterm(String rtStartYearterm) {
		this.rtStartYearterm = rtStartYearterm;
	}

	/**
	 * @return the rtEndYearterm
	 */
	public String getRtEndYearterm() {
		return rtEndYearterm;
	}

	/**
	 * @param rtEndYearterm the rtEndYearterm to set
	 */
	public void setRtEndYearterm(String rtEndYearterm) {
		this.rtEndYearterm = rtEndYearterm;
	}

	
	
	/**
	 * @return the rtUseTime
	 */
	public String getRtUseTime() {
		return rtUseTime;
	}

	/**
	 * @param rtUseTime the rtUseTime to set
	 */
	public void setRtUseTime(String rtUseTime) {
		this.rtUseTime = rtUseTime;
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

	/**
	 * @return the rtYearterm
	 */
	public String getRtYearterm() {
		return rtYearterm;
	}

	/**
	 * @param rtYearterm the rtYearterm to set
	 */
	public void setRtYearterm(String rtYearterm) {
		this.rtYearterm = rtYearterm;
	}

	/**
	 * @return the occrNo
	 */
	public String getOccrNo() {
		return occrNo;
	}

	/**
	 * @param occrNo the occrNo to set
	 */
	public void setOccrNo(String occrNo) {
		this.occrNo = occrNo;
	}

	/**
	 * @return the rtStartNo
	 */
	public String getRtStartNo() {
		return rtStartNo;
	}

	/**
	 * @param rtStartNo the rtStartNo to set
	 */
	public void setRtStartNo(String rtStartNo) {
		this.rtStartNo = rtStartNo;
	}

	/**
	 * @return the rtEndNo
	 */
	public String getRtEndNo() {
		return rtEndNo;
	}

	/**
	 * @param rtEndNo the rtEndNo to set
	 */
	public void setRtEndNo(String rtEndNo) {
		this.rtEndNo = rtEndNo;
	}

	/**
	 * @return the rtTotlCnt
	 */
	public String getRtTotlCnt() {
		return rtTotlCnt;
	}

	/**
	 * @param rtTotlCnt the rtTotlCnt to set
	 */
	public void setRtTotlCnt(String rtTotlCnt) {
		this.rtTotlCnt = rtTotlCnt;
	}

	/**
	 * @return the rtUseCnt
	 */
	public String getRtUseCnt() {
		return rtUseCnt;
	}

	/**
	 * @param rtUseCnt the rtUseCnt to set
	 */
	public void setRtUseCnt(String rtUseCnt) {
		this.rtUseCnt = rtUseCnt;
	}

	/**
	 * @return the rtUnuseCnt
	 */
	public String getRtUnuseCnt() {
		return rtUnuseCnt;
	}

	/**
	 * @param rtUnuseCnt the rtUnuseCnt to set
	 */
	public void setRtUnuseCnt(String rtUnuseCnt) {
		this.rtUnuseCnt = rtUnuseCnt;
	}

	/**
	 * @return the rtPossbilYn
	 */
	public String getRtPossbilYn() {
		return rtPossbilYn;
	}

	/**
	 * @param rtPossbilYn the rtPossbilYn to set
	 */
	public void setRtPossbilYn(String rtPossbilYn) {
		this.rtPossbilYn = rtPossbilYn;
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
	 * @return the regDt
	 */
	public String getRegDt() {
		return regDt;
	}

	/**
	 * @param regDt the regDt to set
	 */
	public void setRegDt(String regDt) {
		this.regDt = regDt;
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
