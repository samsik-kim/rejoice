package com.omp.admin.verify.service;

import java.util.List;

import com.omp.admin.verify.model.VerifyLicense;

public interface VerifyLicenseService {
	public void insertLicense(VerifyLicense verifyLicense);
	public List<VerifyLicense> licenseList(VerifyLicense verifyLicense);
	public VerifyLicense detailLicense(String seqOta);
	public String getCodeName(String cd);
}
