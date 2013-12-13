/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 *
 */
package com.omp.admin.settlement.action.remittance;

import java.util.List;

import com.omp.admin.adminmgr.auth.model.AdMgr;
import com.omp.admin.adminmgr.auth.model.AdSession;
import com.omp.admin.common.Constants;
import com.omp.admin.settlement.model.Accounts;
import com.omp.admin.settlement.model.Remittance;
import com.omp.admin.settlement.service.remittance.SettlementRemittanceAdmService;
import com.omp.admin.settlement.service.remittance.SettlementRemittanceAdmServiceImpl;
import com.omp.commons.action.BaseAction;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.model.PagenateList;
import com.omp.commons.util.StringUtil;




/**
 * SettlementAdm Action
 *
 * @version 0.1
 */
@SuppressWarnings("serial")
public class SettlementRemittanceAdmAction extends BaseAction {
	
	// service 
	private SettlementRemittanceAdmService service = null;
	
	// param 
	private Remittance remittance = null;
	
	// param : 검색조건값 저장
	private Remittance remittanceS = null;
	
	
	// param : 검색조건값 저장
	private Remittance remittanceSS = null;
	
	
	// ExchangeRate list
	private List<Remittance> remittanceRstList = null;
	
	private String chkResult = "";
	
	// contents list totalCount
	private long totalCount;
	
	
	
	



	/**
	 * 
	 */
	public SettlementRemittanceAdmAction() {
		service = new SettlementRemittanceAdmServiceImpl();
	}

	


	
	/**
	 * <b>Action</b>
	 * Remmittance Management : 송금 처리 결과 리스트
	 * 
	 * @return
	 */
	public String remittanceRstList() {
		if (remittanceS == null) {//초기접근시 바로 리턴해준다.
			remittanceS = new Remittance();
			return SUCCESS;
		}
		
		String adjStatCd = ""; //해당월 정산상태코드
		if("P".equals(remittanceS.getSearchTimeBlock())){ //특정송금월만 지정을 하였을시에만 송금신청상태를 조회한다.
			adjStatCd = service.remittanceStateInfo(remittanceS);//송금신청상태조회
		}
		remittanceS.setAdjStatCd(adjStatCd);
		
		remittanceRstList = service.remittanceRstList(remittanceS);
		totalCount = ((PagenateList) remittanceRstList).getTotalCount();
		
		
		return SUCCESS;
	}
	
	
	/**
	 * <b>Action</b>
	 * Remmittance Management : 송금 처리 결과 엑셀 다운로드
	 * 
	 * @return
	 */
	public String remittanceRstExcelList() {
		if (remittanceS == null) {//초기접근시 바로 리턴해준다.
			remittanceS = new Remittance();
			return SUCCESS;
		}
		
		
		this.setDownloadFile(service.remittanceRstExcelList(remittanceS), "application/vnd.ms-excel; charset=UTF-8", "REMITTANCE_RESULT_LIST-"+""+".xls");
		
		return SUCCESS;
	}
	
	/**
	 * 송금처리 내용 조회
	 * @return
	 */
	public String remittanceRstInfo() {
		if (remittanceS == null) {
			remittanceS = new Remittance();
		}
		if (remittanceSS == null) {
			remittanceSS = new Remittance();
		}
		remittance = service.remittanceRstInfo(remittanceSS);
		
		
		return SUCCESS;
	}
	
	
	/**
	 * 송금마감처리 팝업
	 * @return
	 */
	public String popUpRemittanceEnd() {
		if (remittanceS == null) {
			remittanceS = new Remittance();
			return SUCCESS;
		}
		if (remittanceSS == null) {
			remittanceSS = new Remittance();
			return SUCCESS;
		}
		remittance = service.popUpRemittanceEnd(remittanceSS);
		
		
		return SUCCESS;
	}
	
	
	/**
	 * 송금마감처리결과 팝업
	 * @return
	 */
	public String popUpRemittanceEndRst() {
		if (remittanceS == null) {
			remittanceS = new Remittance();
			return SUCCESS;
		}
		if (remittanceSS == null) {
			remittanceSS = new Remittance();
			return SUCCESS;
		}
		remittance = service.popUpRemittanceEndRst(remittanceSS);
		
		return SUCCESS;
	}
	
	
	/**
	 * 송금 처리결과 수정 초기페이지
	 * @return
	 */
	public String editStartRemittanceRstInfo() {
		if (remittanceSS == null) {
			remittanceSS = new Remittance();
		}
		
		remittance = service.remittanceRstInfo(remittanceSS);
		
		return SUCCESS;
	}
	
	/**
	 * 송금 처리결과 수정 완료페이지
	 * @return
	 */
	public String editEndRemittanceRstInfo() {
		if (remittanceSS == null) {
			remittanceSS = new Remittance();
		}
		String loginId = com.omp.admin.common.util.CommonUtil.getLoginId(this.req.getSession());
		remittanceSS.setInsBy(loginId);
		remittanceSS.setUpdBy(loginId);
		service.updateRemittanceRstInfo(remittanceSS);
		
			
		return SUCCESS;
	}
	
	
	
	/**
	 * 송금대기 일괄처리 패스워드 확인
	 * @return
	 */
	public String popConfirmRemittance() {

		if (remittanceS == null) {
			remittanceS = new Remittance();
		}

		if ("".equals(StringUtil.nvlStr(remittanceS.getAdMgrPswdNo()))) {
			return NONE;
		}

		AdMgr adMgr = new AdMgr();
		String adMgrPswdNo = "";

		AdSession adSession = (AdSession) this.req.getSession(true).getAttribute(Constants.ADMIN_AUTH_SESSION_KEY);
		if (adSession != null) {
			adMgr = adSession.getAdMgr();
			adMgrPswdNo = adMgr.getPswdNo();
		} else {
			chkResult = ERROR;
			return ERROR;
		}

		if (!adMgrPswdNo.equals(StringUtil.nvlStr(remittanceS.getAdMgrPswdNo()))) {
			chkResult = ERROR;
			return ERROR;
		}

		chkResult = SUCCESS;
		return SUCCESS;
	}
	
	
	
	/**
	 * <b>Action</b>
	 * Remmittance Management : 송금대기건 일괄 송금완료 처리
	 * 
	 * @return
	 */
	public String popUpUpdateRemittanceRstBundleEnd() {
		if (remittanceS == null) {
			remittanceS = new Remittance();
		}
		
		String adjStatCd = ""; //정산상태코드
		if(remittanceS.getSearchTimeBlock().equals("P")){ //특정송금월만 지정을 하였을시에만 송금신청상태를 조회한다.
			adjStatCd = service.remittanceStateInfo(remittanceS);//송금신청상태조회
			
		}else{
			remittanceS.setProcessMessage("請選擇欲結束匯款作業的月份.");//송금 결과 마감을 처리할 해당월을 선택해 주세요.
			chkResult = ERROR;
			return SUCCESS;
		}
		remittanceS.setAdjStatCd(adjStatCd);
		
		
		if (!adjStatCd.equals("PD004105")){ //정산상태코드가 (송금처리중 상태)가 아니면 화면으로 리턴한다.
			remittanceS.setProcessMessage("現在無法結束匯款作業.");//송금 결과 마감을 처리할 상태가 아닙니다.
			chkResult = ERROR;
			return SUCCESS;
		}
		
		String loginId = com.omp.admin.common.util.CommonUtil.getLoginId(this.req.getSession());
		remittanceS.setInsBy(loginId);
		service.updateRemittanceRstBundleEnd(remittanceS);
		
		adjStatCd = "PD004106";//송금완료코드
		remittanceS.setAdjStatCd(adjStatCd);
		
		return SUCCESS;
	}
	
	
	/**
	 * <b>Action</b>
	 * Remmittance Management : 송금 포기 결과 리스트
	 * 
	 * @return
	 */
	public String remittanceGiveUpList() {
		if (remittanceS == null) { //초기접근시 바로 리턴
			remittanceS = new Remittance();
			return SUCCESS;
		}
		remittanceS.setRmtEndCd("PD004123"); //송금포기코드
		remittanceRstList = service.remittanceRstList(remittanceS);
		totalCount = ((PagenateList) remittanceRstList).getTotalCount();
		
		
		return SUCCESS;
	}
	
	
	/**
	 * <b>Action</b>
	 * Remmittance Management : 송금 포기 결과 엑셀 다운로드
	 * 
	 * @return
	 */
	public String remittanceGiveUpExcelList() {
		if (remittanceS == null) { //초기접근시 바로 리턴
			remittanceS = new Remittance();
			return SUCCESS;
		}
		remittanceS.setRmtEndCd("PD004123"); //송금포기코드
		this.setDownloadFile(service.remittanceGiveUpExcelList(remittanceS), "application/vnd.ms-excel; charset=UTF-8", "REMITTANCE_GIVE_UP_RESULT_LIST-"+""+".xls");
		
		
		
		return SUCCESS;
	}
	
	
	
	/**
	 * <b>Action</b>
	 * Remmittance Management : 송금 포기 결과 조회
	 * 
	 * @return
	 */
	public String remittanceGiveUpInfo() {
		if (remittanceSS == null) {
			remittanceSS = new Remittance();
		}
		remittance = (Remittance)service.remittanceRstInfo(remittanceSS);
		
		return SUCCESS;
	}
	
	
	/**
	 * <b>Action</b>
	 * Remmittance Management : 송금 포기 결과 수정
	 * 
	 * @return
	 */
	public String editRemittanceGiveUpInfo() {
		if (remittanceSS == null) {
			remittanceSS = new Remittance();
		}
		String loginId = com.omp.admin.common.util.CommonUtil.getLoginId(this.req.getSession());
		remittanceSS.setInsBy(loginId);
		remittanceSS.setUpdBy(loginId);
		service.updateRemittanceRstInfo(remittanceSS);
		
		return SUCCESS;
	}
	
	
	/**
	 * <b>Action</b>
	 * Remmittance Management : 송금  결과 현황 리스트
	 * 
	 * @return
	 */
	public String remittanceEndRstList() {
		if (remittance == null) {
			remittance = new Remittance();
		}
		if (remittanceS == null) { //초기접근시 바로 리턴해준다.
			remittanceS = new Remittance();
			return SUCCESS; 
		}
		
		remittanceRstList = service.remittanceEndRstList(remittanceS);
		totalCount = ((PagenateList) remittanceRstList).getTotalCount();
		
		
		return SUCCESS;
	}
	
	
	/**
	 * <b>Action</b>
	 * Remmittance Management : 송금  결과 현황 엑셀다운로드
	 * 
	 * @return
	 */
	public String remittanceEndRstExcelList() {
		if (remittance == null) {
			remittance = new Remittance();
		}
		if (remittanceS == null) { //초기접근시 바로 리턴해준다.
			remittanceS = new Remittance();
			return SUCCESS; 
		}
		
		this.setDownloadFile(service.remittanceEndRstExcelList(remittanceS), "application/vnd.ms-excel; charset=UTF-8", "REMITTANCE_END_RESULT_LIST-"+""+".xls");
		
		
		return SUCCESS;
	}
	
	
	/**
	 * <b>Action</b>
	 * Remmittance Management : 송금  결과 현황  상세리스트
	 * 
	 * @return
	 */
	public String remittanceEndRstInfoList() {
		
		if (remittanceS == null) { 
			remittanceS = new Remittance();
		}
		if (remittanceSS == null) { //초기접근시 바로 리턴해준다.
			remittanceSS = new Remittance();
			remittanceSS.setRmtReqYyyymm(remittanceS.getRmtReqYyyymm()); //리스트에서 넘어온 송금년월 셋팅
			return SUCCESS; 
		}
		
		remittanceRstList = service.remittanceEndRstInfoList(remittanceSS);
		totalCount = ((PagenateList) remittanceRstList).getTotalCount();
		
		
		return SUCCESS;
	
	}
	
	
	/**
	 * <b>Action</b>
	 * Remmittance Management : 송금  결과 현황  상세 엑셀 다운로드
	 * 
	 * @return
	 */
	public String remittanceEndRstInfoExcelList() {
		
		if (remittanceSS == null) { //초기접근시 바로 리턴해준다.
			remittanceSS = new Remittance();
			return SUCCESS; 
		}
		
		this.setDownloadFile(service.remittanceEndRstInfoExcelList(remittanceSS), "application/vnd.ms-excel; charset=UTF-8", "REMITTANCE_END_RESULT_INFO_LIST-"+""+".xls");
		
		
		return SUCCESS;
	}
	
	
	/**
	 * <b>Action</b>
	 * Remmittance Management : 송금결과 현황 송금결과 사유조회
	 * 
	 * @return
	 */
	public String popUpRmtReasonInfo() {
		if (remittanceSS == null) {
			remittanceSS = new Remittance();
		}
		remittance = (Remittance)service.remittanceRstInfo(remittanceSS);
		
		return SUCCESS;
	}
	
	
// ===================== SET/GET =============================================
	
	

	/**
	 * list total count
	 * @return long totalCount
	 */
	public long getTotalCount() {
		return totalCount;
	}





	/**
	 * @return the remittance
	 */
	public Remittance getRemittance() {
		return remittance;
	}





	/**
	 * @param remittance the remittance to set
	 */
	public void setRemittance(Remittance remittance) {
		this.remittance = remittance;
	}





	/**
	 * @return the remittanceRstList
	 */
	public List<Remittance> getRemittanceRstList() {
		return remittanceRstList;
	}





	/**
	 * @param remittanceRstList the remittanceRstList to set
	 */
	public void setRemittanceRstList(List<Remittance> remittanceRstList) {
		this.remittanceRstList = remittanceRstList;
	}





	/**
	 * @return the remittanceS
	 */
	public Remittance getRemittanceS() {
		return remittanceS;
	}





	/**
	 * @param remittanceS the remittanceS to set
	 */
	public void setRemittanceS(Remittance remittanceS) {
		this.remittanceS = remittanceS;
	}





	/**
	 * @return the remittanceSS
	 */
	public Remittance getRemittanceSS() {
		return remittanceSS;
	}





	/**
	 * @param remittanceSS the remittanceSS to set
	 */
	public void setRemittanceSS(Remittance remittanceSS) {
		this.remittanceSS = remittanceSS;
	}


    
	public String getChkResult() {
		return chkResult;
	}

	public void setChkResult(String chkResult) {
		this.chkResult = chkResult;
	}

	
	
		
}