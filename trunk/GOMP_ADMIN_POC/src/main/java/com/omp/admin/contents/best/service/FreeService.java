package com.omp.admin.contents.best.service;

import java.util.List;

import com.omp.admin.contents.adminrec.model.AdminRecommend;
import com.omp.admin.contents.best.model.BestParam;

public interface FreeService {
	
	List<AdminRecommend> selectFreeBestList(BestParam param);
	
	void updateFreeBestList(BestParam param, String[] upProdId, String[] expoYn, String[] expoPrior);
	
	void deleteFreeBestList(String selectedProdId, BestParam param);
	
	void insertFreeBestDefault(BestParam param);

}