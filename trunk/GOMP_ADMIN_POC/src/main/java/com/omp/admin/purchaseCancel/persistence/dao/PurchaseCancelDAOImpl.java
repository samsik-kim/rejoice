/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 * 윤경철            2009. 5. 6.    Description
 *
 */
package com.omp.admin.purchaseCancel.persistence.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.template.SqlMapDaoTemplate;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.util.GLogger;
import com.omp.admin.purchaseCancel.model.PurchaseCancel;

/**
 * TODO 클래스 설명을 입력해 주세요.
 * <p/>
 * 상세한 설명을 입력해 주세요.
 *
 * @author  윤경철
 * @version 0.1
 */
public class PurchaseCancelDAOImpl extends SqlMapDaoTemplate implements PurchaseCancelDAO {

	private static final GLogger log = new GLogger(PurchaseCancelDAOImpl.class);
    
    public PurchaseCancelDAOImpl(DaoManager daoManager) {
        super(daoManager);
    }
    
    public String searchTid( PurchaseCancel purchaseCancel) {
        // TODO Auto-generated method stub
        PurchaseCancel payMethod = null;
        try{
            //결제방식 조회
        	payMethod = (PurchaseCancel) queryForObject("PurchaseCancel.selectPayMethodMulti", purchaseCancel);
           
            if(payMethod == null) {
                log.info("거래내역 존재하지 않습니다[결제방법 조회 실패]. 구매 후 이루어질수 있는 작업입니다");
                return "NOK";
            }

            purchaseCancel.setHandsetNo(payMethod.getHandsetNo());
            purchaseCancel.setMbrNo(payMethod.getMbrNo());
            purchaseCancel.setPayMethod(payMethod.getPayMethod());
            purchaseCancel.setPrchsId(payMethod.getPrchsId());
            purchaseCancel.setProdId(payMethod.getProdId());
            purchaseCancel.setPrchsCls(payMethod.getPrchsCls());
            purchaseCancel.setPrcAmt(payMethod.getPrcAmt()); 		//캐쉬량
            
            purchaseCancel.purchaseCancelListRemove();
            
            if(purchaseCancel.getPayMethod().equals("OR000698")) {			
            	//테스트폰 결제취소(pg결제 이루어지지 않음)
            	purchaseCancel.setPrchsId(payMethod.getPrchsId());
                purchaseCancel.setPayMethod(payMethod.getPayMethod());
                purchaseCancel.setPrchsCls(payMethod.getPrchsCls());
                purchaseCancel.setHandsetNo(payMethod.getHandsetNo());
                purchaseCancel.setProdId(payMethod.getProdId());
                purchaseCancel.setPurchaseCancelList(purchaseCancel); //결제방식별 List 저장 		
            } else {	
            	//정상결제취소(pg사 연동) PG + 쿠폰 + Cash
                //거래번호 조회
                List list = null;
                list = queryForList("PurchaseCancel.selectTid", purchaseCancel);
                
                for(int i=0; i<list.size(); i++){
                    purchaseCancel.setPurchaseCancelList(((PurchaseCancel)list.get(i))); //결제방식별 List 저장
                }
                if(list.size() == 0) {
                    log.info("거래내역 존재하지 않습니다. 구매 후 이루어질수 있는 작업입니다");
                    return "NOK";
                }
            }
        }catch(Exception e){
            throw new ServiceException("searchTid fail.",e); // DB 데이터를 조회하는 도중 에러가 발생하였습니다.
        }
        return "OK";
    }
    
    public String createCancel( PurchaseCancel purchaseCancel ) {
        // TODO Auto-generated method stub
        int cnt = 0;
        try{
            //구매정보 테이블 구매상태(구매취소), 구매취소일자 update 
            cnt = update("PurchaseCancel.updatePurchaseCncl", purchaseCancel);
            if(cnt == 0) {//발생하면 안된다
                log.info("[Fatal]결제취소는 이루어졌으나 구매정보의 결제 정보가 존재하지 않습니다. (거래번호 : {0})", new Object[] {purchaseCancel.getTid()});
                return "NOK";
            }
            log.info("구매정보취소 cnt : " + cnt);
            
            if(!purchaseCancel.getPayMethod().equals("OR000698")){
                //결제결과내역 구매취소일자 update
                cnt=0;
                cnt = update("PurchaseCancel.updateAccountCncl", purchaseCancel);
                if(cnt == 0) {//발생하면 안된다
                    log.info("[Fatal]결제취소는 이루어졌으나 결제결과내역의 결제 정보가 존재하지 않습니다. (거래번호 : {0})", new Object[] {purchaseCancel.getTid()});
                    return "NOK";
                }else {
                	//신용카드인경우 영수증 발급 및 취소  핸드폰결제  및 캐쉬 인 경우는 영수증 발급 및 취소 없음
                	if(purchaseCancel.getPayMethod().equals("OR000601")) {
	                	//영수증 발급 정보 update 
	                	update("PurchaseCancel.updateReceiptCncl", purchaseCancel);
	                	//영수증 관리 정보 카운트 update
	                	update("PurchaseCancel.updateReceiptInfoCncl", purchaseCancel);
                	}	
                }
                
                log.info("결제내역취소 cnt : {0}", new Object[] {cnt});    
            }
            
        }catch(Exception e){
            throw new ServiceException("createCancel fail.",e); // DB 데이터를 조회하는 도중 에러가 발생하였습니다.
        }
        return "OK";
    }
    
    public String createRefundCancel( PurchaseCancel purchaseCancel ) {
        // TODO Auto-generated method stub
        int cnt = 0;
        try{
            //구매정보 테이블 구매상태(구매취소), 구매취소일자 update 
            cnt = update("PurchaseCancel.updatePurchaseCncl", purchaseCancel);
            if(cnt == 0) {//발생하면 안된다
                log.info("[Fatal]결제취소는 이루어졌으나 구매정보의 결제 정보가 존재하지 않습니다. (거래번호 : {0})", new Object[] {purchaseCancel.getTid()});
                return "NOK";
            }
            log.info("구매정보취소 cnt : " + cnt);
            
            if(!purchaseCancel.getPayMethod().equals("OR000698")){
                //결제결과내역 구매취소일자 update
                cnt=0;
                cnt = update("PurchaseCancel.updateAccountCncl", purchaseCancel);
                if(cnt == 0) {//발생하면 안된다
                    log.info("[Fatal]결제취소는 이루어졌으나 결제결과내역의 결제 정보가 존재하지 않습니다. (거래번호 : {0})", new Object[] {purchaseCancel.getTid()});
                    return "NOK";
                }
                log.info("결제내역취소 cnt : {0}", new Object[] {cnt});    
            }
        }catch(Exception e){
            throw new ServiceException("createRefundCancel fail." ,e); // DB 데이터를 조회하는 도중 에러가 발생하였습니다.
        }
        return "OK";
    }
}
