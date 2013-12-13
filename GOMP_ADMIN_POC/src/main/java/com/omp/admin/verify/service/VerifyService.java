package com.omp.admin.verify.service;

import java.util.List;
import com.omp.admin.verify.model.Verify;

public interface VerifyService {
	
	public List<Verify> verifyCase(Verify verify);
	public List selectCategoryName(String vmType);
	public List<Verify> selectCategoryNameAll();
	public List<Verify> categorySaveSelect();
	public void saveCategory(Verify verify);
	public void insertCase(Verify verify);
}
