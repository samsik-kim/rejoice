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
package com.omp.admin.purchaseCancel.service;

import com.omp.commons.exception.ServiceException;
import com.omp.admin.purchaseCancel.model.PurchaseCancel;

/**
 * TODO 클래스 설명을 입력해 주세요.
 * <p/>
 * 상세한 설명을 입력해 주세요.
 *
 * @author  윤경철
 * @version 0.1
 */
public interface PurchaseCancelService {
    
    /**
     * 
     * TODO 거래번호 조회
     * <P/>
     * 거래번호 조회
     *
     * @param purchaseCancel
     * @return
     * @throws ServiceException
     */
    public boolean purchaseCancel(PurchaseCancel purchaseCancel) throws ServiceException;
    
    /**
     * 
     * TODO 복수건 구매취소
     * <P/>
     * 복수 구매취소 처리를 위한 Service
     *
     * @param direct : 취소 모듈 절대경로, purchaseCancel : 구매취소모델객체
     * @return
     * @throws ServiceException
     */
    public boolean purchaseCancelMulti(PurchaseCancel purchaseCancel) throws ServiceException;
    
    /**
     * 
     * TODO Whoopy Cash 환불
     * <P/>
     * Whoopy Cash 환불
     *
     * @param purchaseCancel
     * @return
     * @throws ServiceException
     */
    public boolean purchaseRefund(PurchaseCancel purchaseCancel) throws ServiceException;
    
    /**
     * 
     * TODO 신용카드 결제취소요청
     * <P/>
     * 신용카드 결제취소요청
     *
     * @param purchaseCancel
     * @return
     * @throws ServiceException
     */
    public boolean cardCancel(PurchaseCancel purchaseCancel) throws ServiceException;
    
    /**
     * 
     * TODO 핸드폰 결제취소요청
     * <P/>
     * 핸드폰 결제취소요청
     *
     * @param purchaseCancel
     * @return
     * @throws ServiceException
     */
    public boolean phoneCancel(PurchaseCancel purchaseCancel) throws ServiceException;

}
