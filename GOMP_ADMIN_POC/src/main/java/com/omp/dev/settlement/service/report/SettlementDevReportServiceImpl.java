package com.omp.dev.settlement.service.report;

import java.util.HashMap;
import java.util.List;

import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;
import com.omp.commons.util.CipherAES;
import com.omp.dev.settlement.model.Report;

public class SettlementDevReportServiceImpl extends AbstractService implements SettlementDevReportService {
	
	

	@Override
	public List<Report> settlementReport(Report sub) {
		List<Report> retList = null;
		try {
			retList = super.commonDAO.queryForPageList("Settlement.report.selectSettlementReport", sub);

		} catch (Exception e) {
			throw new ServiceException("정산리포트를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return retList;
	}
	
	/**
	 * 계좌정보조회
	 * @param sub
	 * @return
	 */
	public Report bankInfo(Report sub) {
		Report retObj = null;
		HashMap map = null;
		try {
			
			retObj = (Report)super.commonDAO.queryForObject("Settlement.report.selectBankInfo", sub);
			retObj.setAcctNo(CipherAES.decryption(retObj.getAcctNo()));//계좌번호 복호화
			
			//- US000103 : 외국인
		    if ((retObj.getMbrClsCd()).equals("US000103")){ //외국인경우
		    	map = (HashMap)super.commonDAO.queryForObject("Settlement.report.selectTransferAmt", sub);
		    	retObj.setDevBuDvAmtSum(Double.valueOf((String)map.get("DEVBUDVAMTSUM")));
		    	//retObj.setDevBuAdjAmtSum((String)map.get("9.99"));
		    }

		} catch (Exception e) {
			throw new ServiceException("계좌정보를 조회하는 동안 에러가 발생 하였습니다.", e);
		}
		return retObj;
	}
	
	
	/* 송금신청 가능월 여부 조회
	 * (non-Javadoc)
	 * @see com.omp.dev.settlement.service.report.SettlementDevReportService#rmtReqYN()
	 */
	public String rmtReqYN() {
		String retStr;
		try {
			
			retStr = (String)super.commonDAO.queryForObject("Settlement.report.selectRmtReqYN");
		    

		} catch (Exception e) {
			throw new ServiceException("계좌정보를 조회하는 동안 에러가 발생 하였습니다.", e);
		}
		return retStr;
	}
	
	/* 
	 * 정산 송금요청
	 * (non-Javadoc)
	 * @see com.omp.dev.settlement.service.report.SettlementDevReportService#settlementRequest(com.omp.dev.settlement.model.Report)
	 */
	public void settlementRequest(Report sub) {
		super.daoManager.startTransaction();
		try {
			super.commonDAO.update("Settlement.report.updateSettlementRequest", sub);
			super.daoManager.commitTransaction();
		} catch (Exception e) {
			throw new ServiceException("송금요청을 처리하는 동안 에러가 발생 하였습니다.", e);
		} finally {
			super.daoManager.endTransaction();
		}
		
	}
	
	
	/* 조정 송금요청
	 * (non-Javadoc)
	 * @see com.omp.dev.settlement.service.report.SettlementDevReportService#adjustMoneyRequest(com.omp.dev.settlement.model.Report)
	 */
	public void adjustMoneyRequest(Report sub) {
		super.daoManager.startTransaction();
		try {
			super.commonDAO.update("Settlement.report.updateAdjustMoneyRequest", sub);
			super.daoManager.commitTransaction();
		} catch (Exception e) {
			throw new ServiceException("송금요청을 처리하는 동안 에러가 발생 하였습니다.", e);
		} finally {
			super.daoManager.endTransaction();
		}
		
	}
	
	
	/*
	 * 외국인 정산 송금요청
	 *  (non-Javadoc)
	 * @see com.omp.dev.settlement.service.report.SettlementDevReportService#settlementRequest(com.omp.dev.settlement.model.Report)
	 */
	public void foreignSettlementRequest(Report sub) {
		super.daoManager.startTransaction();
		try {
			super.commonDAO.update("Settlement.report.updateForeignSettlementRequest", sub);
			super.daoManager.commitTransaction();
		} catch (Exception e) {
			throw new ServiceException("송금요청을 처리하는 동안 에러가 발생 하였습니다.", e);
		} finally {
			super.daoManager.endTransaction();
		}	
		
	}
	
	
	public Report selectSettlementContInfo(Report sub) {
		Report retObj = null;
		try {
			retObj = (Report)super.commonDAO.queryForObject("Settlement.report.selectSettlementContInfo", sub);
			
		} catch (Exception e) {
			throw new ServiceException("송금결과정보를 조회하는 동안 에러가 발생 하였습니다.", e);
		}
		return retObj;
	}
	
	
	/* 송금포기 내용조회
	 * (non-Javadoc)
	 * @see com.omp.dev.settlement.service.report.SettlementDevReportService#selectSettlementMoneyInfo(com.omp.dev.settlement.model.Report)
	 */
	public Report selectSettlementMoneyInfo(Report sub) {
		Report retObj = null;
		try {
			retObj = (Report)super.commonDAO.queryForObject("Settlement.report.selectSettlementMoneyInfo", sub);
			
		} catch (Exception e) {
			throw new ServiceException("송금결과정보를 조회하는 동안 에러가 발생 하였습니다.", e);
		}
		return retObj;
	}
	
			
}

