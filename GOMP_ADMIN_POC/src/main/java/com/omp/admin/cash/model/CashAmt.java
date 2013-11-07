package com.omp.admin.cash.model;

public class CashAmt {
	private String cashId = "";
	private int availAmt = 0;
	
	public String getCashId() {
		return cashId;
	}
	public void setCashId(String cashId) {
		this.cashId = cashId;
	}
	public int getAvailAmt() {
		return availAmt;
	}
	public void setAvailAmt(int availAmt) {
		this.availAmt = availAmt;
	}
}
