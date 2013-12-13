package com.omp.commons.model;

import java.io.Serializable;

/**
 * 자료의 페이지 나눔 정보를 가지는 모델
 * 페이지 번호는 1부터 시작합니다.
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class PagenateInfoModel
	implements Serializable {
	
	private Integer	no;
	private Integer	rows;
	private long	totalCount;
	private int		topPage;
	private long	dataFrom;
	private long	dataTo;
	
	/**
	 * 기본 생성자
	 * 기본 페이지당 건수는 20
	 */
	public PagenateInfoModel() {
		this(20);
	}

	/**
	 * 생성자
	 * @param rows 기본 페이지당 건수 설정 
	 */
	public PagenateInfoModel(int rows) {
		this.no		= 1;
		this.rows	= rows;
	}
	
	/**
	 * 페이지 번호를 얻습니다.
	 * 0부터 시작합니다.
	 * @return
	 */
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	
	/**
	 * 페이지당 row수를 얻습니다.
	 * @return
	 */
	public Integer getRows() {
		return rows;
	}
	
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
	/**
	 * 설정된 페이지 번호와 페이지당 로우수를 기준으로 시작 로우번호를 반환합니다.
	 * @return
	 */
	public Integer getStartRow() {
		if (this.no == null || this.rows == null) {
			return null;
		} else {
			return Integer.valueOf((this.no.intValue() - 1) * this.rows.intValue()) + 1;
		}
	}

	/**
	 * 설정도니 페이지 번호와 페이지당 로우수를 기준으로 끝 로우번호를 반환합니다.
	 * @return
	 */
	public Integer getEndRow() {
		if (this.no == null || this.rows == null) {
			return null;
		} else {
			return this.no * rows;
		}
	}
	
	/**
	 * 페이지 조회 범위를 최대로 변경합니다.
	 */
	public void setPageAll() {
		this.no		= Integer.valueOf(1);
		this.rows	= Integer.valueOf(Integer.MAX_VALUE); 
	}

	/**
	 * 검색에 사용된 이후에 검색 결과수 얻을수 있음.
	 * @return
	 */
	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		if (totalCount == null) {
			this.totalCount	= 0;
		} else {
			this.totalCount = totalCount;
		}
		if (this.totalCount == 0) {
			this.topPage	= 0;
			this.dataFrom	= 0;
			this.dataTo		= 0;
		} else {
			this.topPage	= (int)(totalCount/this.rows + (totalCount % this.rows == 0 ? 0 : 1));
			this.dataFrom	= this.getStartRow();
			this.dataTo		= Math.min(this.getEndRow(), totalCount);
		}
	}

	/**
	 * 제일 끝 페이비 번호 반환
	 * @return
	 */
	public int getTopPage() {
		return topPage;
	}

	/**
	 * 데이터 시작 인덱스
	 * @return
	 */
	public long getDataFrom() {
		return dataFrom;
	}

	/**
	 * 테이터 종료 인덱스
	 * @return
	 */
	public long getDataTo() {
		return dataTo;
	}
}