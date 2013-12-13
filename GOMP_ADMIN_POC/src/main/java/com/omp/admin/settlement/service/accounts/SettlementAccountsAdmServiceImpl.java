package com.omp.admin.settlement.service.accounts;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.omp.admin.settlement.model.Accounts;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.model.ColumnInfoModel;
import com.omp.commons.service.AbstractService;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.GLogger;


/**
 * @author Administrator
 *
 */
public class SettlementAccountsAdmServiceImpl extends AbstractService implements SettlementAccountsAdmService {

    
	protected GLogger	logger	= new GLogger(this.getClass());
	

	/* 당월정산현황 리스트
	 * (non-Javadoc)
	 * @see com.omp.admin.settlement.service.accounts.SettlementAccountsAdmService#monthAccountsList(com.omp.admin.settlement.model.Accounts)
	 */
	@Override
	public List<Accounts> monthAccountsList(Accounts accounts) {
		
		List<Accounts> accountsList = null;
		HashMap map;
		
		try {
			
			//판매마감상태인 정산코드존재여부 확인하여 판매마감 상태인  해당월의 월별정산내역조회
			accounts.setAdjStatCd("PD004101");//정산상태코드(판매마감인 상태)
			map = (HashMap)super.commonDAO.queryForObject("Settlement.accounts.selectAccountsStateInfo",accounts); //월별정산처리상태정보
			if (map ==null){
				accounts.setAdjStatCd("");
			}else{
				accounts.setSettYyyymm((String)map.get("SALEYM"));//판매마감 상태인 정산월 셋팅
				accountsList = super.commonDAO.queryForPageList("Settlement.accounts.selectMonthAccountsList",accounts);
				accounts.setSaleYm("");//판매마감 상태인 정산월 초기화로 재셋팅
			}
		} catch (Exception e) {
			throw new ServiceException("당월정산현황  리스트를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return accountsList;
		
	}
	
	
	/**
	 * 당월정산현황  엑셀 다운로드
	 * @param remittance
	 * @return
	 */
	public File monthAccountsExcelList(Accounts accounts) {
		
		HashMap map;
		try {
			

			//판매마감상태인 정산코드존재여부 확인하여 판매마감 상태인  해당월의 월별정산내역조회
			accounts.setAdjStatCd("PD004101");//정산상태코드(판매마감인 상태)
			map = (HashMap)super.commonDAO.queryForObject("Settlement.accounts.selectAccountsStateInfo",accounts); //월별정산처리상태정보
				
			accounts.setSettYyyymm((String)map.get("SALEYM"));//판매마감 상태인 정산월 셋팅
			//accounts.setSaleYm("");//판매마감 상태인 정산월 초기화로 재셋팅
				
				
			List<ColumnInfoModel> COLINFO_DEV_QNA_LIST	= new ArrayList<ColumnInfoModel>();
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("mbrNmId", "開發商(帳號)"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("aid", "AID"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("prodNm", "產品名稱"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("settYyyymm", "交易月份"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("prodPrc", "產品價格"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("settlRt", "拆分比"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("totlAmt", "銷售金額"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("devAmt", "拆帳總金額"));
				
				
			return this.commonDAO.queryForExcel("Settlement.accounts.selectMonthAccountsExcelList", accounts, COLINFO_DEV_QNA_LIST);
		
			
		} catch (Exception e) {
			throw new ServiceException("당월정산현황  excel파일 작성중 에러가 발생 하였습니다.", e);
		}
	}
	
	
	/**
	 * 정산마감 일괄처리
	 * @param accounts
	 * @return
	 */
	public Accounts monthAccountsBundleEnd(Accounts accounts) {
		
		Accounts retAccounts = null;
		HashMap map;
		
		super.daoManager.startTransaction();
		try {
			
			//판매마감상태인 정산코드존재여부 확인하여 판매마감 상태인  해당월의 월별정산내역조회
			accounts.setAdjStatCd("PD004101");//정산상태코드(판매마감인 상태)
			map = (HashMap)super.commonDAO.queryForObject("Settlement.accounts.selectAccountsStateInfo",accounts); //월별정산상태정보
			if (map ==null){
				accounts.setAdjStatCd("");
				accounts.setProcessMessage("需結束處理之資料不存在.");//정산마감을 처리할 데이타가 존재하지 않습니다.
			}else{
				accounts.setSaleYm((String)map.get("SALEYM"));//판매마감 상태인 정산월 셋팅
				accounts.setAdjStatCd("PD004102");//(정산마감 _ settlement Report 제공 상태)로셋팅
				super.commonDAO.update("Settlement.accounts.updateAccountsStateInfo",accounts);//월별정산상태수정
				
				accounts.setAggtStatCd("PD004102");//(정산마감 _ settlement Report 제공 상태)로셋팅
				super.commonDAO.update("Settlement.accounts.updateMonthAccounts",accounts);//정산_년월_집계_수정
				accounts.setSaleYm("");//판매마감 상태인 정산월 초기화로 재셋팅
				accounts.setProcessMessage("結算結束完畢."); //정산마감을 처리완료하였습니다.
				
				super.daoManager.commitTransaction();
			}
			
			
		} catch (Exception e) {
			throw new ServiceException("당월정산 마감을 처리하는 동안 에러가 발생 하였습니다.", e);
		} finally {
			super.daoManager.endTransaction();
		}	
		return retAccounts;
		
	}
	
	
	
	/* (non-Javadoc) 월별 정산 상태 조회
	 * @see com.omp.admin.settlement.service.remittance.SettlementRemittanceAdmService#selectRemittanceRstInfo(com.omp.admin.settlement.model.Remittance)
	 */
	@Override
	public HashMap accountsStateInfo(Accounts accounts) {
	
		HashMap map = null;
		try {
			map = (HashMap)super.commonDAO.queryForObject("Settlement.accounts.selectAccountsStateInfo", accounts);
			
		} catch (Exception e) {
			throw new ServiceException("월별 정산상태 정보를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return map;
		
	}
	
	
	/* 영수증 처리 리스트
	 * (non-Javadoc)
	 * @see com.omp.admin.settlement.service.accounts.SettlementAccountsAdmService#monthAccountsList(com.omp.admin.settlement.model.Accounts)
	 */
	@Override
	public List<Accounts> receiptProcessList(Accounts accounts) {
		
		List<Accounts> accountsList = null;
		HashMap map;
		
		try {
			
				accountsList = super.commonDAO.queryForPageList("Settlement.accounts.selectReceiptProcessList",accounts);
			
		} catch (Exception e) {
			throw new ServiceException("영수증 처리  리스트를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return accountsList;
		
	}
	
	/**
	 * Local, National 엑셀 다운로드
	 * @param remittance
	 * @return
	 */
	public File receiptProcessLoNaExcelList(Accounts accounts) {
		
		HashMap map;
		try {
			
			List<ColumnInfoModel> COLINFO_DEV_QNA_LIST	= new ArrayList<ColumnInfoModel>();
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("mbrNm", "개발자이름"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("mbrId", "개발자ID"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("currencyUnitName", "지불화폐"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("devBuAdjAmtSum", "송금금액"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("rmtReqYyyymm", "신청일"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("aggtStatCdName", "신청여부"));
				
				
			return this.commonDAO.queryForExcel("Settlement.accounts.selectReceiptProcessLoNaExcelList", accounts, COLINFO_DEV_QNA_LIST);
		
			
		} catch (Exception e) {
			throw new ServiceException("영수증  excel파일 작성중 에러가 발생 하였습니다.", e);
		}
	}
	
	
	
	/* 영수증 Local, National 카운드
	 * (non-Javadoc)
	 * @see com.omp.admin.settlement.service.accounts.SettlementAccountsAdmService#receiptLoNaCount(com.omp.admin.settlement.model.Accounts)
	 */
	@Override
	public Accounts receiptLoNaCount(Accounts accounts) {
		
		Accounts retObj = null;
		
		try {
			
			retObj = (Accounts)super.commonDAO.queryForObject("Settlement.accounts.selectReceiptLoNaCount",accounts);
			
		} catch (Exception e) {
			throw new ServiceException("영수증 건수를  가져오는 동안 에러가 발생하였습니다. ", e);
		}
		return retObj;
		
	}
	
	
	
	
	/* 영수증 처리 상세 정보 조회
	 * (non-Javadoc)
	 * @see com.omp.admin.settlement.service.accounts.SettlementAccountsAdmService#receiptProcessList(com.omp.admin.settlement.model.Accounts)
	 */
	@Override
	public Accounts receiptProcessInfo(Accounts accounts) {
		
		Accounts retAccounts = null;
		
		try {
			
			retAccounts = (Accounts)super.commonDAO.queryForObject("Settlement.accounts.selectReceiptProcessInfo",accounts); 
			
		} catch (Exception e) {
			throw new ServiceException("영수증 처리 상세 정보를  가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return retAccounts;
		
	}
	
	
	/**
	 * 영수증 처리 수정  초기 조회
	 * @param accounts
	 * @return
	 */
	@Override
	public Accounts editStartReceiptProcessInfo(Accounts accounts) {
		
		Accounts retAccounts = null;
		
		try {
			
			retAccounts = (Accounts)super.commonDAO.queryForObject("Settlement.accounts.selectReceiptProcessInfo",accounts); 
			
		} catch (Exception e) {
			throw new ServiceException("영수증 처리 수정 상세 정보를  가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return retAccounts;
		
	}
	
	
	/**
	 * 영수증 처리 수정 완료
	 * @param accounts
	 * @return
	 */
	@Override
	public void editEndReceiptProcessInfo(Accounts accounts) {
		
		super.daoManager.startTransaction();
		try {
			
			if (accounts.getAdjAmtEditYN().equals("Y")){ //조정액 수정시 포기처리 문구
				accounts.setAdjReasonInsBy(accounts.getUpdBy() +"已於 " + DateUtil.getDateStr(new Date(System.currentTimeMillis()), "-") +"輸入調帳金額");
			}
			super.commonDAO.update("Settlement.accounts.editEndReceiptProcessInfo",accounts); 
			 
			if(accounts.getAdjAmtEditYN().equals("Y") && accounts.getAdjAmt()>0){//조정액이 '0'보다 클때만 조정액처리
				Accounts accts = (Accounts)super.commonDAO.queryForObject("Settlement.accounts.selectAjustmentMoney",accounts);
				super.commonDAO.update("Settlement.accounts.mergeaAjustmentMoney",accts);
			}else if(accounts.getAdjAmtEditYN().equals("Y") && accounts.getAdjAmt()==0){ //조정액이 '0'이면 기존의 조쟁액 삭제
				super.commonDAO.update("Settlement.accounts.deleteAjustmentMoney",accounts);
			}
			
			super.daoManager.commitTransaction();
			
		} catch (Exception e) {
			throw new ServiceException("영수증 처리 수정을 처리하는  동안 에러가 발생 하였습니다.", e);
		} finally {
			super.daoManager.endTransaction();
		}	
		
	}
	
	
	/**
	 * 영수증(정산율) 처리 수정  초기 조회
	 * @param accounts
	 * @return
	 */
	@Override
	public Accounts editStartAdjustmentMoneyInfo(Accounts accounts) {
		
		Accounts retAccounts = null;
		
		try {
			
			retAccounts = (Accounts)super.commonDAO.queryForObject("Settlement.accounts.selectReceiptProcessInfo",accounts); 
			
		} catch (Exception e) {
			throw new ServiceException("영수증 처리 수정 상세 정보를  가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return retAccounts;
		
	}
	
	
	/**
	 * 영수증(정산율) 처리 수정 완료
	 * @param accounts
	 * @return
	 */
	@Override
	public void editEndAdjustmentMoneyInfo(Accounts accounts) {
		
		super.daoManager.startTransaction();
		try {
			
			super.commonDAO.update("Settlement.accounts.editEndAdjustmentMoneyInfo",accounts); 
			
		} catch (Exception e) {
			throw new ServiceException("영수증 처리 수정을 처리하는  동안 에러가 발생 하였습니다.", e);
		} finally {
			super.daoManager.endTransaction();
		}	
		
	}
	
	/**
	 * 영수증 처리 마감
	 * @param accounts
	 * @return
	 */
	@Override
	public void updateReceiptProcessBundleEnd(Accounts accounts) {
		
		String imsi = accounts.getAdjStatCd(); //기존 상태값저장
		super.daoManager.startTransaction();
		try {
			accounts.setAdjStatCd("PD004104");//송금신청 완료_영수증 처리 마감
			super.commonDAO.update("Settlement.accounts.updateAccountsStateInfo",accounts); 
			
			super.daoManager.commitTransaction();
			
		} catch (Exception e) {
			throw new ServiceException("영수증 처리 마감을 처리하는  동안 에러가 발생 하였습니다.", e);
		} finally {
			accounts.setAdjStatCd(imsi);//검색조건에서 넘어온 값으로 다시 셋팅
			super.daoManager.endTransaction();
		}	
		
		
	}
	
	
}
