/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 *
 */
package com.omp.dev.settlement.action.productsale;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ibatis.dao.client.DaoException;
import com.omp.commons.action.BaseAction;
import com.omp.commons.model.PagenateList;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.SessionUtil;
import com.omp.dev.settlement.model.ProductSale;
import com.omp.dev.settlement.service.productsale.SettlementDevProductSaleService;
import com.omp.dev.settlement.service.productsale.SettlementDevProductSaleServiceImpl;
import com.omp.dev.user.model.Session;




/**
 * SettlementAdm Action
 *
 * @version 0.1
 */
@SuppressWarnings("serial")
public class SettlementDevProductSaleAction extends BaseAction {
	
	// service 
	private SettlementDevProductSaleService service = null;
	
	// param 
	private ProductSale productSale = null;
	
	// param 
	private ProductSale productSaleS = null;
	
	
	// DailySale list
	private List<ProductSale> productSaleList = null;
	
	

	// contents list totalCount
	private long totalCount;
	
	
	
	



	/**
	 * 
	 */
	public SettlementDevProductSaleAction() {
		service = new SettlementDevProductSaleServiceImpl();
	}

	


	
	
	/**
	 * 일별 판매현황 상세목록 조회
	 * 
	 * @param hm
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	public String productSaleList() {
		
		ArrayList list = new ArrayList(); //상품구분 처리에 사용
		if (productSaleS == null) {
			productSaleS = new ProductSale();
			
			// before 1 days default search
			//productSaleS.setSaleDt(DateUtil.getAddDay(-7, "yyyy-MM-dd"));
			productSaleS.setStartDate(DateUtil.getAddDay(-7, "yyyy-MM-dd"));
			productSaleS.setEndDate(DateUtil.getAddDay(0, "yyyy-MM-dd"));
			//상품구분 처리
			list.add("0");
			list.add("1");
			productSaleS.setPrdType(list);
		}else{
			//상품구분 처리
			String strPrdType = productSaleS.getStrPrdType();
			String arrStrPrdType[] = strPrdType.split(",");
			System.out.println("===============  strPrdType: " + strPrdType);
			System.out.println("===============  arrStrPrdType.length: " + arrStrPrdType.length);
			for (int i=0; i<arrStrPrdType.length; i++){
				list.add(arrStrPrdType[i].trim());
			}
		}
		
		//list.add("0");
		//list.add("1");
		productSaleS.setPrdType(list);
		
		Session session = (Session) SessionUtil.getMemberSession(this.req);
		productSaleS.setMbrNo(session.getMbrNo());

		productSaleList = service.productSaleList(productSaleS);
		totalCount = ((PagenateList) productSaleList).getTotalCount();
		
		return SUCCESS;
	}
	
	
	/**
	 * @return
	 */
	public String productSaleExcelList() {
		try {
			ArrayList list = new ArrayList(); //상품구분 처리에 사용
			if (productSaleS == null) {
				productSaleS = new ProductSale();
				
				// before 1 days default search
				//productSaleS.setSaleDt(DateUtil.getAddDay(-7, "yyyy-MM-dd"));
				productSaleS.setStartDate(DateUtil.getAddDay(-7, "yyyy-MM-dd"));
				productSaleS.setEndDate(DateUtil.getAddDay(0, "yyyy-MM-dd"));
				//상품구분 처리
				list.add("0");
				list.add("1");
			}else{
				//상품구분 처리
				String strPrdType = productSaleS.getStrPrdType();
				String arrStrPrdType[] = strPrdType.split(",");
				System.out.println("===============  strPrdType: " + strPrdType);
				System.out.println("===============  arrStrPrdType.length: " + arrStrPrdType.length);
				for (int i=0; i<arrStrPrdType.length; i++){
					list.add(arrStrPrdType[i].trim());
				}
			}
			
			productSaleS.setPrdType(list);
			
			Session session = (Session) SessionUtil.getMemberSession(this.req);
			productSaleS.setMbrNo(session.getMbrNo());
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String yyyymmdd = sdf.format(new Date());
			
			// TODO 조회기간 무력화, 페이징 조건 무력화는 commonDao에서 처리되도록 되어 있슴
            this.setDownloadFile(this.service.productSaleExcelList(this.productSaleS), "application/vnd.ms-excel; charset=UTF-8", "ProductSale-"+yyyymmdd+ ".xls");
            return SUCCESS;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}





	/**
	 * @return the productSale
	 */
	public ProductSale getProductSale() {
		return productSale;
	}





	/**
	 * @param productSale the productSale to set
	 */
	public void setProductSale(ProductSale productSale) {
		this.productSale = productSale;
	}





	/**
	 * @return the productSaleS
	 */
	public ProductSale getProductSaleS() {
		return productSaleS;
	}





	/**
	 * @param productSaleS the productSaleS to set
	 */
	public void setProductSaleS(ProductSale productSaleS) {
		this.productSaleS = productSaleS;
	}





	/**
	 * @return the productSaleList
	 */
	public List<ProductSale> getProductSaleList() {
		return productSaleList;
	}





	/**
	 * @param productSaleList the productSaleList to set
	 */
	public void setProductSaleList(List<ProductSale> productSaleList) {
		this.productSaleList = productSaleList;
	}
	
	
		
		
}