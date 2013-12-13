package com.omp.commons.paging;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({ "unchecked", "serial" })
public class PageResultList extends ArrayList {
	
	private int totalSize;
	
	public PageResultList(){
		
	}
	
	public PageResultList(List list){
		addAll(list);
	}
	

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	
	public int getSize() {
		return this.size();
	}
}
