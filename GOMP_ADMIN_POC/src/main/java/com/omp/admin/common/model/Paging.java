/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 * redaano    2009. 4. 13.    Description
 *
 */
package com.omp.admin.common.model;

/**
 * 페이징 Model
 * 
 * @author redaano
 * @version 0.1
 */
public class Paging {
	private int page = 1;
	private int scale = 10;
	private int startRow = 1;
	private int endRow = 20;
	private int blockScale = 10;
	private long totalCount;
	private long rNum;


	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}


	public int getScale() {
		return scale;
	}


	public void setScale(int scale) {
		this.scale = scale;
	}


	public int getStartRow() {
		return startRow;
	}


	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}


	public int getEndRow() {
		return endRow;
	}


	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}


	public int getBlockScale() {
		return blockScale;
	}


	public void setBlockScale(int blockScale) {
		this.blockScale = blockScale;
	}


	public long getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}


	public long getRNum() {
		return rNum;
	}


	public void setRNum(long num) {
		rNum = num;
	}

}