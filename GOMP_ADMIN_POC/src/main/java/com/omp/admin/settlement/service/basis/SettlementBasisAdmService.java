package com.omp.admin.settlement.service.basis;

import java.util.List;

import com.omp.admin.settlement.model.ExchangeRate;
import com.omp.admin.settlement.model.Receipt;


public interface SettlementBasisAdmService {

	List<ExchangeRate> exchangeRateList(ExchangeRate exChange);
	void insertExchangeRate(ExchangeRate exChange);
	List<Receipt> receiptList(Receipt receipt);
	void insertEndReceipt(Receipt receipt);
	String selectMaxReciptOccrNo(Receipt receipt);
	Receipt selectReceiptInfo(Receipt receipt);
	String selectReceiptExistYN(Receipt receipt);
	void insertReceiptInfo(Receipt receipt);
	void insertReceipt(Receipt receipt);
	void editEndReceipt(Receipt receipt);
	}
