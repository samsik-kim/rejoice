package com.omp.admin.mobileBanner.service;

import java.util.List;
import com.omp.admin.mobileBanner.model.MobileBanner;

public interface MobileBannerService {
	public List<MobileBanner> getMobileBannerList(MobileBanner mobileBanner);
	public void removeBanner(String delIdx);
	public void modifyBanner(int index,String expo,String yn,String id);
	public MobileBanner getBannerDetail(int index);
	public void insertBanner(MobileBanner mobileBanner);
	public int getMaxOpenOrder();
}
