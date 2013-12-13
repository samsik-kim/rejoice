package com.omp.commons.model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 페이징 리스트 객체.
 * 페이징 처리하여 얻은 리스트를 표현합니다.
 * @author pat
 *
 * @param <E>
 */
@SuppressWarnings("serial")
public class PagenateList<E>
	extends ArrayList<E> {
	
	private PagenateInfoModel	page;

	public PagenateList() {
		super();
	}

	public PagenateList(int arg0) {
		super(arg0);
	}

	public PagenateList(Collection<? extends E> arg0) {
		super(arg0);
	}

	public PagenateInfoModel getPage() {
		return page;
	}

	public void setPage(PagenateInfoModel page) {
		this.page = page;
	}

	public Long getTotalCount() {
		return this.page.getTotalCount();
	}
}