package com.omp.commons.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import com.omp.commons.exception.InvalidInputException;

/**
 * 검색에 필요한 공통 요소들을 가진 검색 조건 객체의 슈퍼 클래스
 * @author pat
 *
 */
@SuppressWarnings("serial")
public abstract class SearchCondition
	extends DataValueObject
	implements Pagenateable {
	/**
	 * 날자표현 기본포멧
	 */
	protected static final SimpleDateFormat DEFAULT_DATE_FORMAT		= new SimpleDateFormat("yyyyMMdd");
	/**
	 * 숫자 제외한 모든 케릭터 제거용 정규식 패턴
	 */
	protected static final Pattern			REPLACE_PTN_ONLY_NUMBER	= Pattern.compile("[^0-9]", Pattern.DOTALL + Pattern.MULTILINE); 
	

	/**
	 * 페이징 정보
	 */
	protected PagenateInfoModel	page;
	/**
	 * 검색어
	 */
	protected String	keyword;
	/**
	 * 검색형식
	 */
	protected String	searchType;
	/**
	 * 검색기간 부터~
	 */
	protected Date		fromDate;
	/**
	 * 검색기간 ~까지
	 */
	protected Date		toDate;
	/**
	 * 정렬 기준
	 */
	protected String	orderBy;
	/**
	 * 정렬 방향
	 */
	protected String	orderDirection;
	
	/**
	 * 기본 생성자.
	 * 검색기간은 현재일 기준으로 7일 설정, 페이지 검색조건은 페이지당 20건이 기본으로 설정됩니다.
	 */
	public SearchCondition() {
		this.page	= new PagenateInfoModel();
	}

	public void setSearchTerm(int dayTerm) {
		this.setSearchTerm(new Date(), dayTerm);
	}
	
	public void setSearchTerm(Date toDate, int dayTerm) {
		Calendar	fromCal;
		
		fromCal	= Calendar.getInstance();
		fromCal.setTime(toDate);
		fromCal.add(Calendar.DAY_OF_YEAR, dayTerm * -1);
		
		this.toDate		= toDate;
		this.fromDate	= fromCal.getTime(); 
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	
	public String getFromDate() {
		return this.fromDate == null ? null : DEFAULT_DATE_FORMAT.format(this.fromDate);
	}

	public void setFromDate(String fromDate) {
		if (fromDate == null) {
			this.fromDate	= null;
		}
		try {
			this.fromDate = DEFAULT_DATE_FORMAT.parse(REPLACE_PTN_ONLY_NUMBER.matcher(fromDate).replaceAll(""));
		} catch (ParseException e) {
			throw new InvalidInputException("검색기간 from 날짜 형식이 올바르지 않습니다", e);
		}
	}
	
	public Date getFromDateObject() {
		return this.fromDate;
	}

	public String getToDate() {
		return this.toDate == null ? null : DEFAULT_DATE_FORMAT.format(this.toDate);
	}

	public void setToDate(String toDate) {
		if (toDate == null) {
			this.toDate	= null;
		}
		try {
			this.toDate = DEFAULT_DATE_FORMAT.parse(REPLACE_PTN_ONLY_NUMBER.matcher(toDate).replaceAll(""));
		} catch (ParseException e) {
			throw new InvalidInputException("검색기간 to 날짜 형식이 올바르지 않습니다", e);
		}
	}
	
	public Date getFromToDate() {
		return this.toDate;
	}

	@Override
	public PagenateInfoModel getPage() {
		return this.page;
	}
	
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}

}
