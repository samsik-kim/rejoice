package com.omp.admin.search.service;

import java.util.List;
import com.omp.admin.search.model.Search;


public interface SearchService {
	public List<Search> getSearchList(Search search);
	public void removeSearch(int index);
	public void modifySearch(int index,String expo,String id);
	public void insertSearch(String index,String nm,String id);
}
