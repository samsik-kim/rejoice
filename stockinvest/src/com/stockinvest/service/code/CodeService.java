package com.stockinvest.service.code;

import java.sql.SQLException;
import java.util.List;

import tframe.web.page.PageInfo;

import com.stockinvest.data.code.info.CodeInfo;

public interface CodeService {
	public PageInfo selectCodeList(PageInfo pageInfo, CodeInfo info) throws SQLException;
	public List selectCodeListExcel(CodeInfo info) throws SQLException;
	public CodeInfo selectCodeInfo(CodeInfo info) throws SQLException;
	public int updateCodeInfo(CodeInfo info) throws  SQLException;
	public int deleteCodeInfo(CodeInfo info) throws  SQLException;
	public void insertCodeInfo(CodeInfo info) throws SQLException;
}
