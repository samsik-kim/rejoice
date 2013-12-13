package com.omp.dev.settlement.service.productsale;

import java.io.File;
import java.util.List;

import com.omp.dev.settlement.model.ProductSale;

public interface SettlementDevProductSaleService {
	
	
	/**
	 * get saleStatDaily list
	 * @param sub
	 * @return
	 */
	List<ProductSale> productSaleList(ProductSale sub);
	File productSaleExcelList(ProductSale sub);
	
		
}
