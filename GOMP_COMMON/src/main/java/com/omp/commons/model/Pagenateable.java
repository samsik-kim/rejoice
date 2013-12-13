package com.omp.commons.model;

/**
 * 페이지 나눔 정보를 포함하는 조건 객체가 구현해야 할 인터페이스
 * @author pat
 *
 */
public interface Pagenateable {

	/**
	 * 페이지 나눔 정보를 조회 합니다.
	 * @return
	 */
	public PagenateInfoModel	getPage();
}
