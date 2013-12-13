package com.omp.dev.contents.service;

import java.util.List;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;
import com.omp.dev.contents.model.TestPhoneSet;

public class TestPhoneSetServiceImpl 
			extends AbstractService implements TestPhoneSetService{
	
	@SuppressWarnings("unchecked")
	public List<TestPhoneSet> getTestPhoneList(String mbrNo){
		
		List<TestPhoneSet> phList = null;
		
		try{
			phList = this.commonDAO.queryForList("phoneSet.selectTestPhone", mbrNo);
		}catch(Exception e){
            throw new ServiceException("getTestPhoneList Error.", e);
		}
		
		return phList;
	}
	
	public void delTestPhone(TestPhoneSet testPhSet){
		
		daoManager.startTransaction();
		
		try{
			
			this.commonDAO.delete("phoneSet.deleteTestPhone", testPhSet);
			
			daoManager.commitTransaction();
			
		}catch(Exception e){
            throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}finally {
			daoManager.endTransaction();
		}
	}
	
	public void insertTestPhone(String[] seqArry,String[] macArry,String mbrNo){
			
		TestPhoneSet testPhSet = new TestPhoneSet();
		
		try{
			
			daoManager.startTransaction();
			
			for(int i=1;i<seqArry.length;i++) {
				if(seqArry[i].length()>6){
					
					if(seqArry[i].substring(0,7).equals("testcel")) {
						
						testPhSet.setMacAddr(macArry[i]);
						testPhSet.setMbrNo(mbrNo);
						
						this.commonDAO.insert("phoneSet.insertTestPhone", testPhSet);
					}
					
				}else{
					
					testPhSet.setTmpSeq(seqArry[i]);
					testPhSet.setMacAddr(macArry[i]);
					testPhSet.setMbrNo(mbrNo);
					
					this.commonDAO.insert("phoneSet.modifyTestPhone", testPhSet);
				}
			}
			
			daoManager.commitTransaction();
			
		}catch(Exception e){
            throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}finally {
			daoManager.endTransaction();
		}
	}
}
