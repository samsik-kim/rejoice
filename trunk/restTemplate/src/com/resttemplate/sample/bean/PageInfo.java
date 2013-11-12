package com.resttemplate.sample.bean;

import java.io.Serializable;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

@SuppressWarnings("serial")
public class PageInfo implements Serializable{
	public static final String PAGE_KEY = "PAGE_KEY";
	private String searchPrefix = "search";
	private Map searchParams;
	private int currentPage;
	private int totalCount;
	private int pageGroupSize = 10;

	private int pageSize = 10;
	private int totalPage;
	private int beginUnitPage;
	private int endUnitPage;
	private Collection dataList;
	private int startNum;
	private int endNum;

	public String getSearchPrefix() {
		return this.searchPrefix;
	}

	public void setSearchPrefix(String searchPrefix) {
		this.searchPrefix = searchPrefix;
	}

	public Map getSearchParams() {
		return this.searchParams;
	}

	public void setSearchParams(Map searchParams) {
		this.searchParams = searchParams;
	}

	public int getCurrentPage() {
		return this.currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		setPageInfo();
	}

	public int getPageGroupSize() {
		return this.pageGroupSize;
	}

	public void setPageGroupSize(int pageGroupSize) {
		this.pageGroupSize = pageGroupSize;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return this.totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getBeginUnitPage() {
		return this.beginUnitPage;
	}

	public void setBeginUnitPage(int beginUnitPage) {
		this.beginUnitPage = beginUnitPage;
	}

	public int getEndUnitPage() {
		return this.endUnitPage;
	}

	public void setEndUnitPage(int endUnitPage) {
		this.endUnitPage = endUnitPage;
	}

	public Collection getDataList() {
		return this.dataList;
	}

	public void setDataList(Collection dataList) {
		this.dataList = dataList;
	}

	public int getStartNum() {
		return this.startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public int getEndNum() {
		return this.endNum;
	}

	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}

	private PageInfo() {
	}

	public PageInfo(HttpServletRequest request, int currentPage, int pageunit,
			int pagesize) {
		paramMap(request);
		this.currentPage = (currentPage < 1) ? 1 : currentPage;
		this.pageGroupSize = pageunit;
		this.pageSize = pagesize;

		this.startNum = (this.currentPage - 1) * (this.pageSize + 1);
		this.endNum = this.currentPage * this.pageSize;
	}

	public PageInfo(HttpServletRequest request, int currentPage) {
		paramMap(request);
		this.currentPage = currentPage;
	}

	public void setPageInfo() {
		this.totalPage = this.pageSize == 0 ? this.totalCount : (this.totalCount - 1) / (this.pageSize + 1);
		this.currentPage = this.currentPage > this.totalPage ? this.totalPage
				: this.currentPage;
		this.beginUnitPage = (this.currentPage - 1) / this.pageGroupSize
				* this.pageGroupSize + 1;
		this.endUnitPage = this.beginUnitPage + this.pageGroupSize - 1;
	}

	public void paramMap(HttpServletRequest request) {
		Enumeration paramNames = request.getParameterNames();
		this.searchParams = new HashMap();
		try {
			while (paramNames.hasMoreElements()) {
				String paramKey = (String) paramNames.nextElement();
				if (paramKey.startsWith(this.searchPrefix))
					this.searchParams.put(paramKey, URLEncoder.encode(
							StringUtils.defaultString(request.getParameter(paramKey),
									""), "UTF-8"));
			}
		} catch (Exception localException) {
			new Throwable(localException);
		}
	}

	public String getParamToString() {
		String result = "";
		StringBuffer sb = new StringBuffer();
		Iterator paramKey = this.searchParams.keySet().iterator();

		int i = 0;
		while (paramKey.hasNext()) {
			String key = (String) paramKey.next();
			if (i != 0) {
				sb.append("&");
			}
			sb.append(key);
			sb.append("=");
			sb.append(this.searchParams.get(key));
			++i;
		}
		try {
			result = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean hasPreviousPageUnit() {
		return this.currentPage < this.pageGroupSize + 1;
	}

	public int getPageOfPreviousPageUnit() {
		return this.currentPage - this.pageGroupSize > 1 ? this.currentPage
				- this.pageGroupSize : 1;
	}

	public boolean hasPreviousPage() {
		return this.currentPage <= 1;
	}

	public int getPreviousPage() {
		return this.currentPage - 1;
	}

	public boolean isEmptyPage() {
		return getTotalCount() < 1;
	}

	public int getEndListPage() {
		return this.endUnitPage > this.totalPage ? this.totalPage
				: this.endUnitPage;
	}

	public boolean hasNextPage() {
		return this.currentPage >= this.totalPage;
	}

	public int getNextPage() {
		return this.currentPage + 1;
	}

	public boolean hasNextPageUnit() {
		return this.endUnitPage >= this.totalPage;
	}

	public int getPageOfNextPageUnit() {
		return this.currentPage + this.pageGroupSize < this.totalPage ? this.currentPage
				+ this.pageGroupSize
				: this.totalPage;
	}
}