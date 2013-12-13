package com.omp.admin.settlement.service.remittance;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.omp.admin.community.qna.model.QnA;
import com.omp.admin.settlement.model.Remittance;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.model.ColumnInfoModel;
import com.omp.commons.model.PagenateList;
import com.omp.commons.service.AbstractService;
import com.omp.commons.util.CipherAES;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.GLogger;


public class SettlementRemittanceAdmServiceImpl extends AbstractService implements SettlementRemittanceAdmService {

    
	protected GLogger	logger	= new GLogger(this.getClass());
	
	/* (non-Javadoc)송금결과 리스트 조회
	 * @see com.omp.admin.settlement.service.SettlementAdmService#exchangeRateList(com.omp.admin.settlement.model.ExchangeRate)
	 */
	@Override
	public List<Remittance> remittanceRstList(Remittance remittance) {
		
		List<Remittance> remittanceList = null;
		try {
			remittanceList = super.commonDAO.queryForPageList("Settlement.remittance.selectRemittanceRstList", remittance);
			
		} catch (Exception e) {
			throw new ServiceException("송금결과 리스트를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return remittanceList;
		
	}
	
	/**
	 * 송금결과 처리 엑셀 다운로드
	 * @param remittance
	 * @return
	 */
	public File remittanceRstExcelList(Remittance remittance) {
		try {
			List<ColumnInfoModel> COLINFO_DEV_QNA_LIST	= new ArrayList<ColumnInfoModel>();
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("mbrNo", "開發商(帳號)"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("saleYm", "交易月份"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("currencyUnit", "付款幣別"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("rmtAmt", "匯款金額"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("rmtEndCd", "處理狀態"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("rmtReqYyyymm", "匯款月份"));
			
			return this.commonDAO.queryForExcel("Settlement.remittance.selectRemittanceRstExcelList", remittance, COLINFO_DEV_QNA_LIST);
			
		} catch (Exception e) {
			throw new ServiceException("송금결과 excel파일 작성중 에러가 발생 하였습니다.", e);
		}
	}
	
	/**
	 * 송금포기 현황 엑셀 다운로드
	 * @param remittance
	 * @return
	 */
	public File remittanceGiveUpExcelList(Remittance remittance) {
		try {
			List<ColumnInfoModel> COLINFO_DEV_QNA_LIST	= new ArrayList<ColumnInfoModel>();
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("mbrNo", "開發商(帳號)"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("saleYm", "交易月份"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("currencyUnit", "付款幣別"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("rmtAmt", "匯款金額"));
			
			return this.commonDAO.queryForExcel("Settlement.remittance.selectRemittanceRstExcelList", remittance, COLINFO_DEV_QNA_LIST);
			
		} catch (Exception e) {
			throw new ServiceException("송금포기현황 excel파일 작성중 에러가 발생 하였습니다.", e);
		}
	}
	
	
	
	/* (non-Javadoc) 송금처리결과 상세내용을 조회
	 * @see com.omp.admin.settlement.service.remittance.SettlementRemittanceAdmService#selectRemittanceRstInfo(com.omp.admin.settlement.model.Remittance)
	 */
	@Override
	public Remittance remittanceRstInfo(Remittance remittance) {
		
		Remittance retObj = null;
		try {
			retObj = (Remittance)super.commonDAO.queryForObject("Settlement.remittance.selectRemittanceRstInfo", remittance);
			retObj.setAcctNo(CipherAES.decryption(retObj.getAcctNo()));//계좌번호 복호화
			
		} catch (Exception e) {
			throw new ServiceException("송금 처리 결과 내용을 가져오는 동안  에러가 발생 하였습니다.", e);
		}
		return retObj;
		
	}
	
	/* 송금마감처리 팝업
	 * (non-Javadoc)
	 * @see com.omp.admin.settlement.service.remittance.SettlementRemittanceAdmService#remittanceRstInfo(com.omp.admin.settlement.model.Remittance)
	 */
	@Override
	public Remittance popUpRemittanceEnd(Remittance remittance) {
		
		Remittance retObj = null;
		try {
			retObj = (Remittance)super.commonDAO.queryForObject("Settlement.remittance.selectRemittanceRstSummary", remittance);
			
		} catch (Exception e) {
			throw new ServiceException("송금 처리 결과 내용을 가져오는 동안  에러가 발생 하였습니다.", e);
		}
		return retObj;
		
	}
	
	
	/* 송금마감처리 결과팝업
	 * (non-Javadoc)
	 * @see com.omp.admin.settlement.service.remittance.SettlementRemittanceAdmService#popUpRemittanceEndRst(com.omp.admin.settlement.model.Remittance)
	 */
	@Override
	public Remittance popUpRemittanceEndRst(Remittance remittance) {
		
		Remittance retObj = null;
		try {
			retObj = (Remittance)super.commonDAO.queryForObject("Settlement.remittance.selectRemittanceRstSummary", remittance);
			
		} catch (Exception e) {
			throw new ServiceException("송금 처리 결과 내용을 가져오는 동안  에러가 발생 하였습니다.", e);
		}
		return retObj;
		
	}
	
	
	/* 특정월의 송금대기건을 일괄 송금완료하는 함수
	 * (non-Javadoc)
	 * @see com.omp.admin.settlement.service.remittance.SettlementRemittanceAdmService#updateRemittanceRstBundleEnd(com.omp.admin.settlement.model.Remittance)
	 */
	@Override
	public void updateRemittanceRstBundleEnd(Remittance remittance) {
		
		String imsiAdjStatCd = remittance.getAdjStatCd();
		
		super.daoManager.startTransaction();
		try {
			super.commonDAO.update("Settlement.remittance.updateRemittanceRstBundleEnd", remittance);//송금완료 일괄 처리
			String adjStatCd = "PD004106";//송금완료코드
			
			DateFormat df = new SimpleDateFormat("yyyy" + "年 " + "MM" + "月" + "dd" + "日");
			String curDay = df.format(new Date(System.currentTimeMillis()));
			remittance.setRntRstEndInsBy(remittance.getInsBy() +"於"+curDay+ "處理完筆. ");
			remittance.setAdjStatCd(adjStatCd);
			commonDAO.update("Settlement.remittance.updateAjdStateInfo", remittance);//월별정산처리상태 테이블 송금완료로 수정
			commonDAO.update("Settlement.remittance.updateAccountsRstBundleEnd", remittance);//정산년월집계테이블 송금완료로 변경
			super.daoManager.commitTransaction();
		} catch (Exception e) {
			throw new ServiceException("송금대기건을 일괄 송금완료 처리하는 동안 에러가 발생 하였습니다.", e);
		} finally {
			remittance.setAdjStatCd(imsiAdjStatCd); //기존값으로 환원
			super.daoManager.endTransaction();
		}	
		
	}
	
	/* 특정월의 개별건 송금처리 상태를 저장하는 함수
	 * (non-Javadoc)
	 * @see com.omp.admin.settlement.service.remittance.SettlementRemittanceAdmService#updateRemittanceRstInfo(com.omp.admin.settlement.model.Remittance)
	 */
	@Override
	public void updateRemittanceRstInfo(Remittance remittance) {
		
		int updCnt = 0;
		String imsiRmtStatCd = remittance.getRmtStatCd(); //페이지에서 넘어온 값 임시저장
		String imsiRmtAggtStatCd = remittance.getAggtStatCd(); //페이지에서 넘어온 값 임시저장
		
		if (remittance.getRmtEndCd().equals("PD004123")){ //송금포기시 포기처리 문구
			remittance.setRmtGiveupReason("※" + remittance.getInsBy() +"於 "+ DateUtil.getDateStr(new Date(System.currentTimeMillis()), "-") +"將狀態轉換為匯款失敗.");
			remittance.setRmtStatCd("PD004112");//송금상태코드 송금실패로 처리
			remittance.setAggtStatCd("PD004123");  
		}
		if (remittance.getRmtEndCd().equals("PD004122")){ //재송금시 송금상태코드 송금실패로 처리
			remittance.setRmtStatCd("PD004112");//송금상태코드 송금실패로 처리
			remittance.setAggtStatCd("PD004108"); //정산년월집계 테이블에 익월 송금-송금실패 상태로 저장
		}
		
		super.daoManager.startTransaction();
		try {
			super.commonDAO.update("Settlement.remittance.updateRemittanceRstInfo", remittance);//정산 송금 요청 결과 테이블 수정
			super.commonDAO.update("Settlement.remittance.updateAdjYmAggtStateInfo", remittance);//전산 년월 집계 테이블 수정
			
			super.daoManager.commitTransaction();
		} catch (Exception e) {
			throw new ServiceException("송금 처리 상태를 처리하는 동안 에러가 발생 하였습니다.", e);
		}finally {
			remittance.setRmtStatCd(imsiRmtStatCd);
			remittance.setAggtStatCd(imsiRmtAggtStatCd);
			super.daoManager.endTransaction();
		}	
		
	}
	
	
	/* (non-Javadoc)송금결과 현황리스트 조회
	 * @see com.omp.admin.settlement.service.SettlementAdmService#exchangeRateList(com.omp.admin.settlement.model.ExchangeRate)
	 */
	@Override
	public List<Remittance> remittanceEndRstList(Remittance remittance) {
		
		List<Remittance> remittanceList = null;
		try {
			remittanceList = super.commonDAO.queryForPageList("Settlement.remittance.selectRemittanceEndRstList", remittance);
			
		} catch (Exception e) {
			throw new ServiceException("송금결과현황 리스트를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return remittanceList;
		
	}
	
	/**
	 * 송금결과 현황 엑셀 다운로드
	 * @param remittance
	 * @return
	 */
	public File remittanceEndRstExcelList(Remittance remittance) {
		try {
			List<ColumnInfoModel> COLINFO_DEV_QNA_LIST	= new ArrayList<ColumnInfoModel>();
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("rmtReqYyyymm", "匯款月份"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("totlRmtPayAmt", "結算金額(TWD)"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("totlRmtUsPayAmt", "結算金額(USD)"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("totlRmtCnt", "匯款完畢"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("crovCnt", "次月處理"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("reRmtCnt", "再次匯款"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("giveupCnt", "匯款失敗"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("twAmt", "匯款金額(TWD)"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("usAmt", "匯款金額(USD)"));
			
			return this.commonDAO.queryForExcel("Settlement.remittance.selectRemittanceEndRstExcelList", remittance, COLINFO_DEV_QNA_LIST);
			
		} catch (Exception e) {
			throw new ServiceException("송금결과 현황리스트 excel파일 작성중 에러가 발생 하였습니다.", e);
		}
	}
	
	
	
	/* (non-Javadoc) 송금처리결과 현황 상세 리스트 조회
	 * @see com.omp.admin.settlement.service.remittance.SettlementRemittanceAdmService#selectRemittanceRstInfo(com.omp.admin.settlement.model.Remittance)
	 */
	@Override
	public List<Remittance> remittanceEndRstInfoList(Remittance remittanceS) {
	
		List<Remittance> remittanceList = null;
		try {
			remittanceList = super.commonDAO.queryForPageList("Settlement.remittance.selectRemittanceRstList", remittanceS);
			
		} catch (Exception e) {
			throw new ServiceException("송금결과 현황 상세 리스트를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return remittanceList;
		
	}
	
	/**
	 * 송금처리결과 현황 상세 엑셀 다운로드
	 * @param remittance
	 * @return
	 */
	public File remittanceEndRstInfoExcelList(Remittance remittance) {
		try {
			List<ColumnInfoModel> COLINFO_DEV_QNA_LIST	= new ArrayList<ColumnInfoModel>();
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("mbrNoNm", "開發商(帳號)"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("saleYm", "交易月份"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("currencyUnitName", "付款幣別"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("rmtAmt", "匯款金額"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("rmtEndName", "匯款狀態"));
			
			return this.commonDAO.queryForExcel("Settlement.remittance.selectRemittanceEndRstInfoExcelList", remittance, COLINFO_DEV_QNA_LIST);
			
		} catch (Exception e) {
			throw new ServiceException("송금처리결과 현황 상세 리스트 excel파일 작성중 에러가 발생 하였습니다.", e);
		}
	}
	
	
	/* (non-Javadoc) 월별 정산 상태 조회
	 * @see com.omp.admin.settlement.service.remittance.SettlementRemittanceAdmService#selectRemittanceRstInfo(com.omp.admin.settlement.model.Remittance)
	 */
	@Override
	public String remittanceStateInfo(Remittance remittanceS) {
	
		String adjStatCd = null;
		try {
			adjStatCd = (String)super.commonDAO.queryForObject("Settlement.remittance.selectRemittanceStateInfo", remittanceS);
			
		} catch (Exception e) {
			throw new ServiceException("월별 정산상태 정보를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return adjStatCd;
		
	}
	
	
	/* (non-Javadoc) 월별 정산 상태 수정
	 * @see com.omp.admin.settlement.service.remittance.SettlementRemittanceAdmService#selectRemittanceRstInfo(com.omp.admin.settlement.model.Remittance)
	 */
	@Override
	public void updateRemittanceStateInfo(Remittance remittanceS) {
	
		super.daoManager.startTransaction();
		try {
			super.commonDAO.update("Settlement.remittance.updateAdjYmAggtStateInfo", remittanceS);
			
		} catch (Exception e) {
			throw new ServiceException("월별 정산상태 정보를 수정하는 동안 에러가 발생 하였습니다.", e);
		} finally {
			super.daoManager.endTransaction();
		}	
		
		
	}
	
	
	
	

}
