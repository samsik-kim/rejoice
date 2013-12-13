package com.omp.admin.settlement.model;

import com.omp.commons.model.CommonModel;
import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;
import com.omp.commons.model.TotalCountCarriable;


@SuppressWarnings("serial")
public class ExchangeRate extends CommonModel implements Pagenateable, TotalCountCarriable
{
	private PagenateInfoModel	page		= new PagenateInfoModel();
	private Long    total_count = 0L;
	private String rnum;
	private int    rowCnt = 0;		//행번호.
	private String saleYm;  /* 정산_년월 */
	private String currencyUnit; /* 통화단*/
	private double currency; /* 환율 */     
	private String insDttm; /* 등록일시 */ 
	private String insBy; /* 등록자 */     
	private String updDttm; /* 수정일시 */ 
	private String updBy; /* 수정자 */
	private String cnt; //현재 등록된 카운트:mearge문 사용하기 위해 임시로 사용
	
	
	
	
	/**
	 * @return the cnt
	 */
	public String getCnt() {
		return cnt;
	}

	/**
	 * @param cnt the cnt to set
	 */
	public void setCnt(String cnt) {
		this.cnt = cnt;
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
	 * @return the currency
	 */
	public double getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(double currency) {
		this.currency = currency;
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

	@Override
	public Long getTotalCount() {
		return this.total_count;
	}

	@Override
	public void setTotalCount(Long totalCount) {
		this.total_count	= totalCount;
	}
	
	@Override
    public PagenateInfoModel getPage() {
        return this.page;
    }
	public String getRnum() {
		return rnum;
	}

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
	
		
	
}
