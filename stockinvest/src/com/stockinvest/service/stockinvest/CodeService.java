package com.stockinvest.service.stockinvest;

import java.sql.SQLException;

import tframe.web.page.PageInfo;

import com.stockinvest.data.stockinvest.info.CodeInfo;

public interface CodeService {
	public PageInfo selectCodeList(PageInfo pageInfo, CodeInfo info) throws SQLException;
	public CodeInfo selectCodeInfo(CodeInfo info) throws SQLException;
	public int updateCodeInfo(CodeInfo info) throws  SQLException;
	public int deleteCodeInfo(CodeInfo info) throws  SQLException;
	public void insertCodeInfo(CodeInfo info) throws SQLException;
}