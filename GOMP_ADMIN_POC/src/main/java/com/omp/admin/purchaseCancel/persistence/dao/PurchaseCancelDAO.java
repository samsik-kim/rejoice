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

import com.omp.admin.purchaseCancel.model.PurchaseCancel;

/**
 * TODO 결제취소 DAO
 * <p/>
 * 결제취소 DAO
 *
 * @author  윤경철
 * @version 0.1
 */
public interface PurchaseCancelDAO {
    /**
     * 
     * TODO TID(거래번호) 조회
     * <P/>
     * TID(거래번호) 조회
     *
     * @param purchaseCancel
     * @return
     */
    public abstract String searchTid(PurchaseCancel purchaseCancel);
    
    
    /**
     * 
     * TODO 결제취소 내역 생성
     * <P/>
     * 결제취소 내역 생성
     *
     * @param purchaseCancel
     * @return
     */
    public abstract String createCancel(PurchaseCancel purchaseCancel);
    
    /**
     * 
     * TODO 환불 내역 생성
     * <P/>
     * 환불 내역 생성
     *
     * @param purchaseCancel
     * @return
     */
    public abstract String createRefundCancel( PurchaseCancel purchaseCancel );
}
