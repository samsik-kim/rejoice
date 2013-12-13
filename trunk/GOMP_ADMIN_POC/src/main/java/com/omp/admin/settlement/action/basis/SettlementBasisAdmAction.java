/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 *
 */
package com.omp.admin.settlement.action.basis;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

import com.omp.admin.settlement.model.ExchangeRate;
import com.omp.admin.settlement.model.Receipt;
import com.omp.admin.settlement.service.basis.SettlementBasisAdmService;
import com.omp.admin.settlement.service.basis.SettlementBasisAdmServiceImpl;
import com.omp.commons.action.BaseAction;
import com.omp.commons.model.PagenateList;




/**
 * SettlementAdm Action
 *
 * @version 0.1
 */
@SuppressWarnings("serial")
public class SettlementBasisAdmAction extends BaseAction {
	
	// service 
	private SettlementBasisAdmService service = null;
	
	// param 
	private ExchangeRate exRate = null;
	
	// param 
	private ExchangeRate exRateS = null;
	
	// param 
	private Receipt receipt = null;
	
	
	// param :검색조건 데이타 저장용
	private Receipt receiptS = null;
	
	
	// param 
	private Receipt receiptSS = null;
	
	// ExchangeRate list
	private List<ExchangeRate> exchangeRateList = null;
	
	
	// Receipt list
	private List<Receipt> receiptList = null;
	
	

	// contents list totalCount
	private long totalCount;
	
	
	
	



	/**
	 * 
	 */
	public SettlementBasisAdmAction() {
		if (logger.isInfoEnabled())
			logger.debug("init SettlementAdmAction");
		service = new SettlementBasisAdmServiceImpl();
	}

	


	// 해당 월의 마지막 날을 구한다 yyyymm or yyyymmdd
	public String getLastDay(String yyyymm) {
		if(yyyymm==null || yyyymm.length()<6) return "";
		String yyyy = yyyymm.substring(0,4);
		String mm = yyyymm.substring(4,6);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(yyyy));
		cal.set(Calendar.MONTH, Integer.parseInt(mm)-1);
		cal.set(Calendar.DATE, 1);
		return ""+cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * <b>Action</b>
	 * Product Management : exchage rate list
	 * 
	 * @return
	 */
	public String exchangeRateList() {
		if (exRateS == null) {
			exRateS = new ExchangeRate();
			
		}
		
		exchangeRateList = service.exchangeRateList(exRateS);
		totalCount = ((PagenateList) exchangeRateList).getTotalCount();
		
		return SUCCESS;
	}
	
	
	/**
	 * <b>Action</b>
	 * ExchangeRate Management : exchage rate insert
	 * 
	 * @return
	 */
	public String insertExchangeRate() {
		
		String loginId = com.omp.admin.common.util.CommonUtil.getLoginId(this.req.getSession());
		exRateS.setInsBy(loginId);
		service.insertExchangeRate(exRateS);
			
		return SUCCESS;
	}
	
	
	
	
	/**
	 * @param receipt
	 * @return
	 */
	public String receiptList() {
		if (receiptS == null) { //초기접근시 바로 리턴해준다.
			receiptS = new Receipt();
			return SUCCESS;
		}
		try{
		receiptList = service.receiptList(receiptS);
		totalCount = ((PagenateList) receiptList).getTotalCount();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	
	
	/**
	 * <b>Action</b>
	 * Receipt Management : insertStartReceipt
	 * @return
	 */
	public String insertStartReceipt() {
		if (receiptS == null) {
			receiptS = new Receipt();
		}
		return SUCCESS;
	}
	
	
	/**
	 * <b>Action</b>
	 * Receipt Management : insertEndReceipt
	 * @return
	 */
	public String insertEndReceipt() {
		if (receiptSS == null) {
			receiptSS = new Receipt();
		}
		
		String loginId = com.omp.admin.common.util.CommonUtil.getLoginId(this.req.getSession());
		receiptSS.setRegId(loginId);
		service.insertEndReceipt(receiptSS);
		receiptS.setProcessMessage("處理完畢.");//정산적으로 처리되었습니다.
		
		return SUCCESS;
		
	}
	
	
	/**
	 * <b>Action</b>
	 * Receipt Management : editStartReceipt
	 * @return
	 */
	public String editStartReceipt() {
		
		try {
			receipt = service.selectReceiptInfo(receiptSS);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return SUCCESS;
	}
	
	
	/**
	 * <b>Action</b>
	 * Receipt Management : editEndReceipt
	 * @return
	 */
	public String editEndReceipt() {
		
		try {
			String loginId = com.omp.admin.common.util.CommonUtil.getLoginId(this.req.getSession());
			receiptSS.setRegId(loginId);
			service.editEndReceipt(receiptSS);
			receiptS.setProcessMessage("處理完畢.");//정산적으로 처리되었습니다.
		} catch (Exception e) {
			
		} 
		
		return SUCCESS;
		
	}
	
	
	/**
	 * <b>Action</b>
	 * Receipt Management : checkExistReceipt
	 */
	public void ajaxCheckExistReceipt() {
		
		JSONObject jsonObject = new JSONObject();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter writer = null;
		
		try {
			String receiptExistYN = service.selectReceiptExistYN(receiptSS);
			if (receiptExistYN.equals("N")){
				jsonObject.put("data", "N");
			}else{ //데이타 중복으로 저장할 수 없을시
				jsonObject.put("data", "Y");
			}
			writer = response.getWriter();
			writer.write(jsonObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally { if(writer != null) { writer.close(); } }
		
	}
	
	
	/**
	 * <b>Action</b>
	 * Receipt Management : ajaxCheckUsedReceipt
	 */
	public void ajaxCheckUsedReceipt() {
		
		JSONObject jsonObject = new JSONObject();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter writer = null;
		
		Receipt retReceipt = null;

		
		try {

			retReceipt = service.selectReceiptInfo(receiptSS);
			if (retReceipt.getRtPossbilYn().equals("N")){ //사용한 영수증이 존재시
				jsonObject.put("data", "X");
				
			}else{ //사용한 영수증이  미  존재시 
				String receiptExistYN = service.selectReceiptExistYN(receiptSS);
				if (receiptExistYN.equals("N")){ //저장가능할시
					jsonObject.put("data", "N");
				}else{ //데이타 중복으로 저장할 수 없을시
					jsonObject.put("data", "Y");
				}
			}
			
			writer = response.getWriter();
			writer.write(jsonObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally { if(writer != null) { writer.close(); } }
		
	}
	

	
// ===================== SET/GET =============================================
	
	/**
	 * @return the exRate
	 */
	public ExchangeRate getExRate() {
		return exRate;
	}

	/**
	 * @param exRate the exRate to set
	 */
	public void setExRate(ExchangeRate exRate) {
		this.exRate = exRate;
	}

	/**
	 * list total count
	 * @return long totalCount
	 */
	public long getTotalCount() {
		return totalCount;
	}
	
	
	/**
	 * @return the list
	 */
	public List<ExchangeRate> getExchangeRateList() {
		return exchangeRateList;
	}

	/**
	 * @param list the list to set
	 */
	public void setExchangeRateList(List<ExchangeRate> exchangeRatelist) {
		this.exchangeRateList = exchangeRatelist;
	}

	/**
	 * @return the receipt
	 */
	public Receipt getReceipt() {
		return receipt;
	}

	/**
	 * @param receipt the receipt to set
	 */
	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

	/**
	 * @return the receiptList
	 */
	public List<Receipt> getReceiptList() {
		return receiptList;
	}

	/**
	 * @param receiptList the receiptList to set
	 */
	public void setReceiptList(List<Receipt> receiptList) {
		this.receiptList = receiptList;
	}




	/**
	 * @return the receiptS
	 */
	public Receipt getReceiptS() {
		return receiptS;
	}




	/**
	 * @param receiptS the receiptS to set
	 */
	public void setReceiptS(Receipt receiptS) {
		this.receiptS = receiptS;
	}




	/**
	 * @return the receiptSS
	 */
	public Receipt getReceiptSS() {
		return receiptSS;
	}




	/**
	 * @param receiptSS the receiptSS to set
	 */
	public void setReceiptSS(Receipt receiptSS) {
		this.receiptSS = receiptSS;
	}




	/**
	 * @return the exRateS
	 */
	public ExchangeRate getExRateS() {
		return exRateS;
	}




	/**
	 * @param exRateS the exRateS to set
	 */
	public void setExRateS(ExchangeRate exRateS) {
		this.exRateS = exRateS;
	}

	
		
}