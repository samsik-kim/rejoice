package com.nmimo.service.crm;

import java.sql.SQLException;

import com.nmimo.data.crm.info.CrmInfo;

public interface CrmService {
	
	public CrmInfo selectEtcUserInfo(CrmInfo dbParams) throws SQLException;

}
