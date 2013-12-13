package com.omp.admin.settlement.service.remittance;

import java.io.File;
import java.util.List;

import com.omp.admin.settlement.model.Remittance;


public interface SettlementRemittanceAdmService {

	List<Remittance> remittanceRstList(Remittance remittance);
	void updateRemittanceRstBundleEnd(Remittance remittance);
	void updateRemittanceRstInfo(Remittance remmittance);
	Remittance remittanceRstInfo(Remittance remittance);
	List<Remittance> remittanceEndRstList(Remittance remittance);
	List<Remittance> remittanceEndRstInfoList(Remittance remittance);
	String remittanceStateInfo(Remittance remittance);
	void updateRemittanceStateInfo(Remittance remittance);
	File remittanceRstExcelList(Remittance remittance);
	File remittanceGiveUpExcelList(Remittance remittance);
	File remittanceEndRstExcelList(Remittance remittance);
	File remittanceEndRstInfoExcelList(Remittance remittance);
	Remittance popUpRemittanceEnd(Remittance remittance);
	Remittance popUpRemittanceEndRst(Remittance remittance);
	}
