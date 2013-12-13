package com.omp.admin.contents.best.service;

import java.util.List;

import com.omp.admin.contents.adminrec.model.AdminRecommend;
import com.omp.admin.contents.adminrec.model.AdminRecommendParam;
import com.omp.admin.contents.adminrec.model.DpCategoryList;
import com.omp.admin.contents.best.model.BestParam;

public interface ChargeService {
	
	List<DpCategoryList> getDpCategoryList(BestParam param);
	
	List<AdminRecommend> selectChargeBestList(BestParam param);
	
	void updateChargeBestList(BestParam param, String[] upProdId, String[] expoYn, String[] expoPrior);
	
	void deleteChargeBestList(String selectedProdId, BestParam param);
	
	void insertChargeBestDefault(BestParam param);

}