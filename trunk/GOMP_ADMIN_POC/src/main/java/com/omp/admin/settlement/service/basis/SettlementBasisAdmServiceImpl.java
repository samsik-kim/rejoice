package com.omp.admin.settlement.service.basis;

import java.util.List;

import com.omp.admin.settlement.model.ExchangeRate;
import com.omp.admin.settlement.model.Receipt;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;
import com.omp.commons.util.GLogger;


public class SettlementBasisAdmServiceImpl extends AbstractService implements SettlementBasisAdmService {

    
	protected GLogger	logger	= new GLogger(this.getClass());
	
	/* (non-Javadoc)정산환율 리스트 조회
	 * @see com.omp.admin.settlement.service.SettlementAdmService#exchangeRateList(com.omp.admin.settlement.model.ExchangeRate)
	 */
	@Override
	public List<ExchangeRate> exchangeRateList(ExchangeRate exchangeRate) {
		
		List<ExchangeRate> exchangeRateList = null;
		try {
			exchangeRateList = super.commonDAO.queryForPageList("Settlement.basis.selectExchangeRateList", exchangeRate);
			
		} catch (Exception e) {
			throw new ServiceException("환율리스트를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return exchangeRateList;
		
	}
	
	
	/* (non-Javadoc)정산환율 등록
	 * @see com.omp.admin.settlement.service.SettlementAdmService#insertExchangeRate(com.omp.admin.settlement.model.ExchangeRate)
	 */
	@Override
	public void insertExchangeRate(ExchangeRate exchangeRate) {
		
		super.daoManager.startTransaction();
		try {
			
			super.commonDAO.update("Settlement.basis.mergeExchangeRate", exchangeRate);
			
			super.daoManager.commitTransaction();
		} catch (Exception e) {
			throw new ServiceException("환율 저장시 에러가 발생 하였습니다.", e);
		} finally {
			super.daoManager.endTransaction();
		}	
		return ;
	}
	
	
	/* (non-Javadoc)영수증 발행 내역 리스트 조회
	 * @see com.omp.admin.settlement.service.SettlementAdmService#receiptList(com.omp.admin.settlement.model.Receipt)
	 */
	@Override	
	public List<Receipt> receiptList(Receipt receipt) {
		List<Receipt> receiptList = null;
		try {
			
			receiptList = super.commonDAO.queryForPageList("Settlement.basis.selectReceiptList", receipt);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("영수증관리 리스트를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return receiptList;
		
	}
	
	
	/* (non-Javadoc) 영수증 발행등록
	 * @see com.omp.admin.settlement.service.basis.SettlementBasisAdmService#receiptList(com.omp.admin.settlement.model.Receipt)
	 */
	@Override	
	public void insertEndReceipt(Receipt receipt) {
		
		long sNum = Long.parseLong(receipt.getRtStartNo());
		long eNum = Long.parseLong(receipt.getRtEndNo());
		
		
		super.daoManager.startTransaction();
		try {
			
			String occrNo = (String)super.commonDAO.queryForObject("Settlement.basis.selectMaxReciptOccrNo", receipt);
			receipt.setOccrNo(occrNo);
			receipt.setRtTotlCnt(Long.toString(eNum-sNum+1));
			super.commonDAO.insert("Settlement.basis.insertReceiptInfo", receipt);

			receipt.setRtStartNo(String.valueOf(sNum));
			receipt.setRtEndNo(String.valueOf(eNum));
			
			super.commonDAO.insert("Settlement.basis.insertReceipt", receipt);
			
			/*
			int intLength;
			String zeroString="";
			
			super.commonDAO.startBatch();

			
			for(long i=sNum ; i<=eNum ; i++){ //새로운 영수증 등록
				intLength = String.valueOf(i).length();
				zeroString="";
				for(int j=intLength; j<8;j++){
					zeroString += "0";
				}
				receipt.setRtNo(zeroString+String.valueOf(i));
				super.commonDAO.insert("Settlement.basis.insertReceipt", receipt);
			}
			
			super.commonDAO.executeBatch();
			*/
			super.daoManager.commitTransaction();
			
		} catch (Exception e) {
			throw new ServiceException("영수증 발행등록 동안 에러가 발생 하였습니다.", e);
		}finally {
			super.daoManager.endTransaction();
		}
		
	}
	
	
	/* (non-Javadoc) 영수증 발행 수정
	 * @see com.omp.admin.settlement.service.basis.SettlementBasisAdmService#insertEndReceipt(com.omp.admin.settlement.model.Receipt)
	 */
	public void editEndReceipt(Receipt receipt) {
		
		long sNum = Long.parseLong(receipt.getRtStartNo());
		long eNum = Long.parseLong(receipt.getRtEndNo());
		
		super.daoManager.startTransaction();
		try {
			
			receipt.setRtTotlCnt(Long.toString(eNum-sNum+1));
			super.commonDAO.update("Settlement.basis.updateReceiptInfo", receipt); //기본정보 수정
			super.commonDAO.delete("Settlement.basis.deleteReceipt", receipt); //발행영수증삭제
			
			receipt.setRtStartNo(String.valueOf(sNum));
			receipt.setRtEndNo(String.valueOf(eNum));
			
			super.commonDAO.insert("Settlement.basis.insertReceipt", receipt);
			
			super.daoManager.commitTransaction();
		} catch (Exception e) {
			throw new ServiceException("영수증 발행 수정 처리중에  에러가 발생 하였습니다.", e);
		}finally {
			super.daoManager.endTransaction();
		}
		
	}
	
	
	/**
	 * 영수증 발행 기본정보 입력
	 * @param receipt
	 */
	public void insertReceiptInfo(Receipt receipt) {
		
		super.daoManager.startTransaction();
		try {
			super.commonDAO.insert("Settlement.basis.insertReceiptInfo", receipt);
			super.daoManager.commitTransaction();
		} catch (Exception e) {
			throw new ServiceException("영수증 발행 기본정보 등록 동안 에러가 발생 하였습니다.", e);
		} finally {
			super.daoManager.endTransaction();
		}
		
	}
	
	
	
	/**
	 * 영수증 발행 상세정보 입력
	 * @param receipt
	 */
	public void insertReceipt(Receipt receipt) {
		
		super.daoManager.startTransaction();
		try {
			super.commonDAO.insert("Settlement.basis.insertReceipt", receipt);
			super.daoManager.commitTransaction();
		} catch (Exception e) {
			throw new ServiceException("영수증 발행 상세정보 등록 동안 에러가 발생 하였습니다.", e);
		} finally {
			super.daoManager.endTransaction();
		}
		
	}


	
	/* (non-Javadoc) 특정 발행기간 영수증 최대 발행회차 번호조회
	 * @see com.omp.admin.settlement.service.basis.SettlementBasisAdmService#selectMaxReciptOccrNo(com.omp.admin.settlement.model.Receipt)
	 */
	public String selectMaxReciptOccrNo(Receipt receipt) {
		
		String cnt = null;
		try {
			cnt = (String)super.commonDAO.queryForObject("Settlement.basis.selectMaxReciptOccrNo", receipt);
		} catch (Exception e) {
			throw new ServiceException("최대 영수증 회차를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		
		return cnt;
	}
	
	
	/* (non-Javadoc) 발행영수증 기본정보 내역 조회
	 * @see com.omp.admin.settlement.service.basis.SettlementBasisAdmService#selectReceiptInfo(com.omp.admin.settlement.model.Receipt)
	 */
	public Receipt selectReceiptInfo(Receipt receipt) {
		
		Receipt retObj = null;
		try {
			retObj = (Receipt)super.commonDAO.queryForObject("Settlement.basis.selectReceiptInfo", receipt);
		} catch (Exception e) {
			throw new ServiceException("발행 영수증 기본정보를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		
		return retObj;
	}
	
	
	/* (non-Javadoc) 영수증 발행 내역 존재 여부 조회
	 * @see com.omp.admin.settlement.service.basis.SettlementBasisAdmService#selectReceiptExistYN(com.omp.admin.settlement.model.Receipt)
	 */
	public String selectReceiptExistYN(Receipt receipt) {
		
		String receiptExistYN = "N";
		try {
			receiptExistYN = (String)super.commonDAO.queryForObject("Settlement.basis.selectReceiptExistYN", receipt);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("영수증 발행 내역 존재 여부 조회 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		
		return receiptExistYN;
	}
	
	
	

}
