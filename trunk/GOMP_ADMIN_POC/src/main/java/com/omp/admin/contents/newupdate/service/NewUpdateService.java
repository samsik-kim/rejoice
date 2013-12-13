package com.omp.admin.contents.newupdate.service;

import java.util.List;

import com.omp.admin.contents.adminrec.model.AdminRecommend;
import com.omp.admin.contents.adminrec.model.AdminRecommendParam;

public interface NewUpdateService {
	
	List<AdminRecommend> selectNewUpdateList(AdminRecommendParam param);
	
	void updateNewUpdateList(AdminRecommendParam param, String[] upProdId, String[] expoYn, String[] expoPrior);
	
	void deleteNewUpdateList(String selectedProdId, AdminRecommendParam param);

}