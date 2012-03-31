package com.stockinvest.service.stockinvest;

import java.sql.SQLException;

import tframe.web.page.PageInfo;

import com.stockinvest.data.stockinvest.info.CodeInfo;

public interface CodeService {
	public PageInfo selectCodeList(PageInfo pageInfo, CodeInfo info) throws SQLException ;
}
