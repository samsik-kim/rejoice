package com.omp.dev.purchase.service;

import com.omp.commons.exception.ServiceException;
import com.omp.dev.purchase.model.Purchase;

public interface PurchaseService {
	 
	
	/**
     * 
     * TODO 상품 구매
     * <P/>
     * 상품 구매
     *
     * @param purchaseCancel
     * @return
     * @throws ServiceException
     */
	public Purchase purchase(Purchase purchase) throws ServiceException;
	
}
