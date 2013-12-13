package com.omp.admin.search.service;

import java.util.List;

import com.omp.admin.search.model.Search;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;
import com.omp.commons.util.GLogger;

public class SearchServiceImpl extends AbstractService implements SearchService{
	private GLogger	logger = new GLogger(this.getClass());
	
	public List<Search> getSearchList(Search search){
		this.logger.debug("getSearchList()");
		List<Search> searchList = null;
		try{
			searchList = this.commonDAO.queryForPageList("search.getSearchList", search);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException("getSearchList Error.", e);
		}
		return searchList;
	}
	
	public void removeSearch(int index){
		this.logger.debug("removeSearch()"); 
		daoManager.startTransaction();
		try{
			this.commonDAO.delete("search.removeSearch", index);
			daoManager.commitTransaction();
		}catch(Exception e){
			e.printStackTrace();
            throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}finally{
			daoManager.endTransaction();
		}
	}
	
	public void modifySearch(int index,String expo,String id){
		this.logger.debug("modifySearch()"); 
		daoManager.startTransaction();
		try{
			Search 			search = new Search();
			search.setSearchIdx(index+"");
			search.setExpoPrior(expo);
			search.setInsId(id);
			this.commonDAO.update("search.modifySearch",search);
			daoManager.commitTransaction();
		}catch(Exception e){
			e.printStackTrace();
            throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}finally{
			daoManager.endTransaction();
		}
	}
	
	public void insertSearch(String index,String nm,String id){
		this.logger.debug("insertSearch()"); 
		daoManager.startTransaction();
		try{
			Search 	search = new Search();
			if(index.equals("emp")){
				search.setSearchIdx("");
			}else{
				search.setSearchIdx(index+"");
			}
			search.setSearchNm(nm);
			search.setInsId(id);
			this.commonDAO.insert("search.insertSearch",search);
			daoManager.commitTransaction();
		}catch(Exception e){
			e.printStackTrace();
            throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}finally{
			daoManager.endTransaction();
		}
	}
}
