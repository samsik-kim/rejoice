package com.omp.admin.cash.service;

import java.io.File;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.omp.admin.cash.model.Cash;
import com.omp.admin.cash.model.CashAmt;
import com.omp.admin.cash.model.CashSub;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.model.ColumnInfoModel;
import com.omp.commons.service.AbstractService;
import com.omp.commons.util.GLogger;

public class CashServiceImpl extends AbstractService implements CashService {

	private GLogger logger = new GLogger(CashServiceImpl.class);

	private static final List<ColumnInfoModel> COLINFO_CONTENT_LIST	= new ArrayList<ColumnInfoModel>();

	static {
		COLINFO_CONTENT_LIST.add(new ColumnInfoModel("prchsDt", "月 / 日"));
		COLINFO_CONTENT_LIST.add(new ColumnInfoModel("prchsAmt", "販售金額(元)"));
		COLINFO_CONTENT_LIST.add(new ColumnInfoModel("cashAmt", "Whoopy幣交易額(P)"));
	}

	private static final List<ColumnInfoModel> COLINFO_CHARGE_LIST	= new ArrayList<ColumnInfoModel>();

	static {
		COLINFO_CHARGE_LIST.add(new ColumnInfoModel("rnum", "序號"));
		COLINFO_CHARGE_LIST.add(new ColumnInfoModel("mbrIdNm", "帳號(姓名)"));
		COLINFO_CHARGE_LIST.add(new ColumnInfoModel("atmAmt", "加值方式 WEBATM"));
		COLINFO_CHARGE_LIST.add(new ColumnInfoModel("adminAmt", "加值方式  管理者"));
		COLINFO_CHARGE_LIST.add(new ColumnInfoModel("cashAmt", "Whoopy幣交易額"));
		COLINFO_CHARGE_LIST.add(new ColumnInfoModel("prchsAmt", "販售金額"));
	}

	private static final List<ColumnInfoModel> COLINFO_CANCEL_LIST	= new ArrayList<ColumnInfoModel>();

	static {
		COLINFO_CANCEL_LIST.add(new ColumnInfoModel("prchsDt", "日期"));
		COLINFO_CANCEL_LIST.add(new ColumnInfoModel("cashAmt", "Whoopy幣取消額(P)"));
		COLINFO_CANCEL_LIST.add(new ColumnInfoModel("cancelCnt", "取消筆數"));
	}

	private static final List<ColumnInfoModel> COLINFO_CANCELDETAIL_LIST	= new ArrayList<ColumnInfoModel>();

	static {
		COLINFO_CANCELDETAIL_LIST.add(new ColumnInfoModel("rnum", "序號"));
		COLINFO_CANCELDETAIL_LIST.add(new ColumnInfoModel("mbrId", "帳號(姓名)"));
		COLINFO_CANCELDETAIL_LIST.add(new ColumnInfoModel("cashAmt", "取消之Whoopy幣"));
		COLINFO_CANCELDETAIL_LIST.add(new ColumnInfoModel("payCls", "加值方式"));
		COLINFO_CANCELDETAIL_LIST.add(new ColumnInfoModel("prchsAmt", "加值金額"));
		COLINFO_CANCELDETAIL_LIST.add(new ColumnInfoModel("prchsDt", "加值日期"));
	}

	@Override
	public List<Cash> getCashList(CashSub sub) {
		List<Cash> CashList = null;
		try {
			this.setStep("GetCashList");	
			CashList = super.commonDAO.queryForPageList("Cash.getCashList", sub);
		} catch (Exception e) {
			throw new ServiceException("회원별 캐시를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return CashList;
	}

	@Override
	public List<Cash> getCashChargeList(CashSub sub) {
		List<Cash> CashChargeList = null;
		try {
			this.setStep("GetCashChargeList");
			CashChargeList = super.commonDAO.queryForPageList("Cash.getCashChargeList", sub);
		} catch (Exception e) {
			throw new ServiceException("캐시충전리스트를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return CashChargeList;
	}

	/**
	 * 
	 */
	@Override
	public File getCashChargeListExcel(CashSub sub) {
		try {
			this.setStep("GetCashChargeList");
			return this.commonDAO.queryForExcel("Cash.getCashChargeList", sub, COLINFO_CONTENT_LIST);
		} catch (Exception e) {
			throw new ServiceException("캐시충전리스트 excel파일 작성중 에러가 발생 하였습니다.", e);
		}
	}

	@Override
	public List<Cash> getCashChargeListDetail(CashSub sub) {
		List<Cash> CashChargeDetailList = null;
		try {
			this.setStep("GetCashChargeListDetail");
			CashChargeDetailList = super.commonDAO.queryForPageList("Cash.getCashChargeListDetail", sub);
		} catch (Exception e) {
			throw new ServiceException("캐시충전상세리스트를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return CashChargeDetailList;
	}

	/**
	 * 
	 */
	@Override
	public File getCashChargeListDetailExcel(CashSub sub) {
		try {
			this.setStep("GetCashChargeListDetail");
			return this.commonDAO.queryForExcel("Cash.getCashChargeListDetail", sub, COLINFO_CHARGE_LIST);
		} catch (Exception e) {
			throw new ServiceException("캐시충전상세리스트 excel파일 작성중 에러가 발생 하였습니다.", e);
		}
	}

	@Override
	public List<Cash> getCashCancelList(CashSub sub) {
		List<Cash> CashCancelList = null;
		try {
			this.setStep("GetCashCancelList");
			CashCancelList = super.commonDAO.queryForPageList("Cash.getCashCancelList", sub);
		} catch (Exception e) {
			throw new ServiceException("캐시취소 리스트를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return CashCancelList;
	}

	/**
	 * 
	 */
	@Override
	public File getCashCancelListExcel(CashSub sub) {
		try {
			this.setStep("GetCashCancelList");
			return this.commonDAO.queryForExcel("Cash.getCashCancelList", sub, COLINFO_CANCEL_LIST);
		} catch (Exception e) {
			throw new ServiceException("캐시취소리스트 excel파일 작성중 에러가 발생 하였습니다.", e);
		}
	}

	@Override
	public List<Cash> getCashRefundUser(CashSub sub) {
		List<Cash> CashRefundUserList = null;
		try {
			this.setStep("GetCashRefundUser");
			CashRefundUserList = super.commonDAO.queryForPageList("Cash.getCashRefundUser", sub);
		} catch (Exception e) {
			throw new ServiceException("캐시삭제 회원 리스트를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return CashRefundUserList;
	}

	public int getTotalAvailAmtCash(Map paramMap) throws ServiceException {
		log.debug("MBR_NO ===> " + paramMap.get("MBR_NO"));
		Map resultMap = null;
		try {
			this.setStep("GetTotalAvailAmtCash");
			resultMap = (HashMap) super.commonDAO.queryForObject("Cash.getTotalAvailAmtCash", paramMap);
		} catch (SQLException e) {
			throw new ServiceException("사용가능 캐시를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		int totalAvailAmtCash = Integer.parseInt(((BigDecimal)resultMap.get("CASH_TOT_AMT")).toString());
		return totalAvailAmtCash;
	}

	public Map refund(Map paramMap) throws ServiceException {

		int prcAmt = Integer.parseInt((String)paramMap.get("PRC_AMT")); //사용할 금액
		String mbrNo = (String)paramMap.get("MBR_NO");
		String prchsId = (String)paramMap.get("PRCHS_ID");

		Map returnMap = new HashMap();

		String refundCashId = "";

		paramMap.put("AMT", prcAmt);
		
		try {
			this.setStep("InsertRefundCash");
			refundCashId = (String)super.commonDAO.insert("Cash.refundCash", paramMap);
		} catch (SQLException e1) {
			throw new ServiceException("캐시차감를 하는 동안 에러가 발생 하였습니다.", e1);
		}

		List<CashAmt> cashList = null;
		try {
			this.setStep("GetMbrNoCashAvailAmts");
			cashList = super.commonDAO.queryForList("Cash.getMbrNoCashAvailAmts", paramMap);
		} catch (SQLException e1) {
			throw new ServiceException("캐시리스트를 가지고 오는 동안 에러가 발생 하였습니다.", e1);
		}

		Cash cash = null;
		String cashId = "";
		int availAmt = 0;		
		int restAmt = 0; //차감 후 잔여금액
		paramMap.put("CASH_ID", refundCashId);

		try{

			for(int i = 0; i < cashList.size(); i++){

				cashId = cashList.get(i).getCashId();
				availAmt = cashList.get(i).getAvailAmt();

				restAmt = prcAmt - availAmt;

				if(restAmt > 0){
					//차감 availAmt
					paramMap.put("UPDATE_CASH_ID", cashId);
					paramMap.put("UPDATE_AVAIL_AMT", 0);
					
					this.setStep("UpdateminusOdCashHst");
					super.commonDAO.update("Cash.minusOdCashHst", paramMap);
					

					paramMap.put("BF_AVAIL_AMT", availAmt);
					paramMap.put("AF_AVAIL_AMT", 0);
					
					this.setStep("InsertOdCashHstDtl");
					super.commonDAO.insert("Cash.insertOdCashHstDtl", paramMap);
					
				}else if(restAmt == 0){
					//차감 availAmt
					paramMap.put("UPDATE_CASH_ID", cashId);
					paramMap.put("UPDATE_AVAIL_AMT", 0);
					
					this.setStep("UpdateminusOdCashHst");
					super.commonDAO.update("Cash.minusOdCashHst", paramMap);
					
					paramMap.put("BF_AVAIL_AMT", availAmt);
					paramMap.put("AF_AVAIL_AMT", 0);
					
					this.setStep("InsertOdCashHstDtl");
					super.commonDAO.insert("Cash.insertOdCashHstDtl", paramMap);
					
					break;
				}else if(restAmt < 0){
					//차감 절대값 restAmt
					paramMap.put("UPDATE_CASH_ID", cashId);
					paramMap.put("UPDATE_AVAIL_AMT", Math.abs(restAmt));

					this.setStep("UpdateMinusOdCashHst");
					super.commonDAO.update("Cash.minusOdCashHst", paramMap);
					
					paramMap.put("BF_AVAIL_AMT", availAmt);
					paramMap.put("AF_AVAIL_AMT", Math.abs(restAmt));
					
					this.setStep("InsertOdCashHstDtl");
					super.commonDAO.insert("Cash.insertOdCashHstDtl", paramMap);
					
					break;
				}

				prcAmt = restAmt;
			}
		}catch(Exception e){
			e.printStackTrace();
			returnMap.put("ERROR_CODE", "01");
		}

		returnMap.put("ERROR_CODE", "00");

		return returnMap;
	}

	public Map reserveChargeCancel(Map paramMap) throws ServiceException {
		int prcAmt = Integer.parseInt((String)paramMap.get("PRC_AMT")); //사용할 금액
		String mbrNo = (String)paramMap.get("MBR_NO");

		Map resultMap = null;
		try {
			this.setStep("GetTotalAvailAmt");
			resultMap = (HashMap)super.commonDAO.queryForObject("Cash.getTotalAvailAmt", paramMap);
		} catch (SQLException e) {
			throw new ServiceException("총가용캐시를 오는 동안 에러가 발생 하였습니다.", e);
		}
		int cashAvailAmt = Integer.parseInt(((BigDecimal)resultMap.get("CASH_TOT_AMT")).toString()); //사용할 수 있는 CASH

		Map returnMap = new HashMap();

		String cashId = "";
		int cashAmt = 0;

		if(prcAmt > cashAvailAmt){
			returnMap.put("ERROR_CODE", "01");
		}else{
			if(prcAmt==cashAvailAmt){
				//INSERT CASH(cashAvailAmt)
				paramMap.put("AMT", cashAvailAmt);
				try {
					this.setStep("InsertReserveChargeCancelCash");
					cashId = (String)super.commonDAO.insert("Cash.reserveChargeCancelCash", paramMap);
				} catch (SQLException e) {
					throw new ServiceException("cashId 동안 에러가 발생 하였습니다.", e);
				}
				cashAmt = cashAvailAmt;
			}else{
				//INSERT CASH(prcAmt)
				paramMap.put("AMT", prcAmt);
				try {
					this.setStep("InsertReserveChargeCancelCash");
					cashId = (String)super.commonDAO.insert("Cash.reserveChargeCancelCash", paramMap);
				} catch (SQLException e) {
					throw new ServiceException("cashId 동안 에러가 발생 하였습니다.", e);
				}
				cashAmt = prcAmt;
			}

			returnMap.put("CASH_ID", cashId);
			returnMap.put("CASH_AMT", cashAmt);
			returnMap.put("ERROR_CODE", "00");

		}
		return returnMap;
	}

	public Map cancel(Map paramMap) throws ServiceException {
		Map returnMap = new HashMap();
		Boolean rtnVal = false;

		if(paramMap.get("SVC_CD") == null){
			paramMap.put("SVC_CD", "OR003101");
		}

		if("POINT".equals((String)paramMap.get("TYPE"))){
			try {
				this.setStep("InsertCancelPoint");
				super.commonDAO.insert("Cash.cancelPoint", paramMap);
				rtnVal = true;
			} catch (SQLException e) {
				throw new ServiceException("cancelPoint 에러가 발생 하였습니다.", e);
			}
		}else if("CASH".equals((String)paramMap.get("TYPE"))){
			try {
				this.setStep("InsertCancelCash");
				super.commonDAO.insert("Cash.cancelCash", paramMap);
				rtnVal = true;
			} catch (SQLException e) {
				throw new ServiceException("cancelCash 에러가 발생 하였습니다.", e);
			} 
		}

		if(rtnVal){
			returnMap.put("ERROR_CODE", "00");
		}else{
			returnMap.put("ERROR_CODE", "01");
		}

		return returnMap;
	}

	public Map useChargeCancel(Map paramMap) throws ServiceException {

		Map returnMap = new HashMap();

		Boolean rtnVal = false;
		rtnVal = useCash(paramMap);

		if(rtnVal){
			returnMap.put("ERROR_CODE", "00");
		}else{
			returnMap.put("ERROR_CODE", "01");
		}

		return returnMap;
	}

	public Boolean useCash(Map paramMap) throws ServiceException {
		//int updateCount = cashDAO.reserveToUseOdCashHst(paramMap);
		
		int updateCount = 0;
		try {
			updateCount = super.commonDAO.update("Cash.reserveToUseOdCashHst", paramMap);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try{
			if(updateCount == 0){ //예약 데이터 없을 경우 모든 작업 SKIP
				return false;
			}else{
				this.setStep("GetCashIdOccrAmt");
				int prcAmt = ((Integer)super.commonDAO.queryForObject("Cash.getCashIdOccrAmt", paramMap)).intValue(); // 해당 CASH_ID 의 발생금액을 가지고 온다.
				
				this.setStep("GetMbrNoCashAvailAmts");
				List<CashAmt> cashList = super.commonDAO.queryForList("Cash.getMbrNoCashAvailAmts", paramMap); 

				//CashAmt cashAmt = null;
				String cashId = "";
				int availAmt = 0;

				int restAmt = 0; //차감 후 잔여금액

				for(int i = 0; i < cashList.size(); i++){

					cashId = cashList.get(i).getCashId();
					availAmt = cashList.get(i).getAvailAmt();
					restAmt = prcAmt - availAmt;

					if(restAmt > 0){
						//차감 availAmt
						paramMap.put("UPDATE_CASH_ID", cashId);
						paramMap.put("UPDATE_AVAIL_AMT", 0);
						
						this.setStep("UpdateMinusOdCashHst");
						super.commonDAO.update("Cash.minusOdCashHst", paramMap);
						
						paramMap.put("BF_AVAIL_AMT", availAmt);
						paramMap.put("AF_AVAIL_AMT", 0);
						
						this.setStep("InsertOdCashHstDtl");
						super.commonDAO.insert("Cash.insertOdCashHstDtl", paramMap);
						
					}else if(restAmt == 0){
						//차감 availAmt
						paramMap.put("UPDATE_CASH_ID", cashId);
						paramMap.put("UPDATE_AVAIL_AMT", 0);
						
						this.setStep("UpdateminusOdCashHst");
						super.commonDAO.update("Cash.minusOdCashHst", paramMap);	
						
						paramMap.put("BF_AVAIL_AMT", availAmt);
						paramMap.put("AF_AVAIL_AMT", 0);
						
						this.setStep("InsertOdCashHstDtl");
						super.commonDAO.insert("Cash.insertOdCashHstDtl", paramMap);

						break;
					}else if(restAmt < 0){
						//차감 절대값 restAmt
						paramMap.put("UPDATE_CASH_ID", cashId);
						paramMap.put("UPDATE_AVAIL_AMT", Math.abs(restAmt));
						
						this.setStep("UpdateminusOdCashHst");
						super.commonDAO.update("Cash.minusOdCashHst", paramMap);
						
						paramMap.put("BF_AVAIL_AMT", availAmt);
						paramMap.put("AF_AVAIL_AMT", Math.abs(restAmt));
						
						this.setStep("InsertOdCashHstDtl");
						super.commonDAO.insert("Cash.insertOdCashHstDtl", paramMap);
						
						break;
					}

					prcAmt = restAmt;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public Map cancelReserve(Map paramMap) throws ServiceException {
		Map returnMap = new HashMap();
		int rtnVal = 0;
		if("POINT".equals((String)paramMap.get("TYPE"))){
			try {
				this.setStep("DeleteCancelReservePoint");
				rtnVal = super.commonDAO.delete("Cash.cancelReservePoint", paramMap);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new ServiceException("cancelReservePoint 에러가 발생 하였습니다.", e);
			}
		}else if("CASH".equals((String)paramMap.get("TYPE"))){
			try {
				this.setStep("DeleteCancelReserveCash");
				rtnVal = super.commonDAO.delete("Cash.cancelReserveCash", paramMap);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new ServiceException("cancelReserveCash 에러가 발생 하였습니다.", e);
			} 
		}

		if(rtnVal != 0){
			returnMap.put("ERROR_CODE", "00");
		}else{
			returnMap.put("ERROR_CODE", "01");
		}

		return returnMap;
	}

	/**
	 * 
	 */
	@Override
	public List<Cash> getCashCancelListDetail(CashSub sub) {
		List<Cash> CashCancelDetailList = null;
		try {
			this.setStep("GetCashCancelListDetail");
			CashCancelDetailList = super.commonDAO.queryForPageList("Cash.getCashCancelListDetail", sub);
		} catch (Exception e) {
			throw new ServiceException("캐시취소 리스트를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return CashCancelDetailList;
	}

	/**
	 * 
	 */
	@Override
	public File getCashCancelListDetailExcel(CashSub sub) {
		try {
			this.setStep("GetCashCancelListDetail");
			return this.commonDAO.queryForExcel("Cash.getCashCancelListDetail", sub, COLINFO_CANCELDETAIL_LIST);
		} catch (Exception e) {
			throw new ServiceException("캐시취소리스트 excel파일 작성중 에러가 발생 하였습니다.", e);
		}
	}

	/**
	 * 
	 */
	@Override
	public List<Cash> getChrgCashList(CashSub sub) {
		List<Cash> CashChrgCashList = null;
		try {
			this.setStep("GetChrgCashList");
			CashChrgCashList = super.commonDAO.queryForPageList("Cash.getChrgCashList", sub);
		} catch (Exception e) {
			throw new ServiceException("캐시충전 리스트를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return CashChrgCashList;
	}

	/**
	 * 
	 */
	@Override
	public String getMaxtDateChrgCash() {
		String lastdate = "";
		try {
			this.setStep("GetMaxtDateChrgCash");
			lastdate = (String) super.commonDAO.queryForObject("Cash.getMaxtDateChrgCash");
		} catch (Exception e) {
			throw new ServiceException("최종수정일을 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return lastdate;
	}

	public int registCashBonus(Cash cash) {
		int insCnt = 0;    
		try {
			super.daoManager.startTransaction();
				
			//update CASH(TBL_CHRG_CASH)
			this.setStep("UpdateChrgCash");
			insCnt = super.commonDAO.update("Cash.updateChrgCash", cash);
			
			//INSERT CASH(TBL_CHRG_CASH_HST)
			this.setStep("InsertChrgCashHst");
			super.commonDAO.insert("Cash.insertChrgCashHst",cash);
			
			super.daoManager.commitTransaction();
		} catch (Exception e) {
			throw new ServiceException("보너스율 업데이트 하는 도중 에러가 발생하였습니다.", e);
		} finally {
			super.daoManager.endTransaction();
		}
		return insCnt;
	}
	
	
	public List<Cash> cashmanagehisList(CashSub sub) {
		List<Cash> CashmanagehisListt = null;
		try {
			this.setStep("GetCashmanagehisList");
			CashmanagehisListt = super.commonDAO.queryForPageList("Cash.cashmanagehisList", sub);
		} catch (Exception e) {
			throw new ServiceException("캐시보너스 리스트를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return CashmanagehisListt;
	}

}
