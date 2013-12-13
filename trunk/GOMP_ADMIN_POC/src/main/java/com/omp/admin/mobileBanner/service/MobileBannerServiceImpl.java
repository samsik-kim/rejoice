package com.omp.admin.mobileBanner.service;

import java.sql.SQLException;
import java.util.List;
import java.util.StringTokenizer;
import com.omp.admin.mobileBanner.model.MobileBanner;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;

public class MobileBannerServiceImpl extends AbstractService implements MobileBannerService{
	
	public List<MobileBanner> getMobileBannerList(MobileBanner mobileBanner){
		List<MobileBanner> mobileBannerList = null;
		try{
			mobileBannerList = this.commonDAO.queryForPageList("mobileBanner.getMobileBannerList", mobileBanner);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException("getMobileBannerList Error.", e);
		}
		return mobileBannerList;
	}
	
	public void removeBanner(String delIdx){
		try{
			this.daoManager.startTransaction();
			StringTokenizer tokenizer = new StringTokenizer(delIdx, ",");
			int index = 0;
			try{
				while (tokenizer.hasMoreTokens()) {
					index = Integer.parseInt(tokenizer.nextToken());
					if (index >= 0) {
						this.commonDAO.delete("mobileBanner.removeBanner", index);
					}
				}
				this.daoManager.commitTransaction();
			}finally {
				this.daoManager.endTransaction();
			}
		}catch(Exception e){
			e.printStackTrace();
            throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}
	}
	
	public void modifyBanner(int index,String expo,String yn,String id){
		try{
			this.daoManager.startTransaction();
			MobileBanner mobileBanner = new MobileBanner();
			mobileBanner.setBnrNo(index+"");
			if(Integer.parseInt(expo)>1000000000){
				mobileBanner.setOpenOrder(999999999);
			}else{
				mobileBanner.setOpenOrder(Integer.parseInt(expo));
			}
			mobileBanner.setInsId(id);
			mobileBanner.setOpenYn(yn);
			this.commonDAO.update("mobileBanner.modifyBanner",mobileBanner);
			this.daoManager.commitTransaction();
		}catch(Exception e){
			e.printStackTrace();
            throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		} finally {
			this.daoManager.endTransaction();
		}
	}
	
	public int getMaxOpenOrder(){
		try {
			return (Integer) this.commonDAO.queryForObject("mobileBanner.getMaxOpenOrder");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("getMaxOpenOrder Error.", e);
		}
	}
	
	public MobileBanner getBannerDetail(int index){
		MobileBanner mobileBanner = new MobileBanner();
		try{
			mobileBanner = (MobileBanner)this.commonDAO.queryForObject("mobileBanner.getBannerDetail",index);
			mobileBanner.setStartDate(mobileBanner.getBnrStartDt());
			mobileBanner.setEndDate(mobileBanner.getBnrEndDt());
		}catch(Exception e){
			e.printStackTrace();
            throw new ServiceException("getBannerDetail Error.", e);
		}
		return mobileBanner;
	}
	
	public void insertBanner(MobileBanner mobileBanner){
		try{
			this.daoManager.startTransaction();
			if(mobileBanner.getBnrNo().equals("")){
				this.commonDAO.insert("mobileBanner.insertBanner", mobileBanner);
			}else{
				this.commonDAO.update("mobileBanner.updateBanner", mobileBanner);
			}
			this.daoManager.commitTransaction();
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		} finally {
			this.daoManager.endTransaction();
		}
	}
}
