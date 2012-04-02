package com.stockinvest.data.code.dao;

import java.sql.SQLException;
import java.util.List;

import com.stockinvest.data.code.info.CodeInfo;

public interface CodeDAO {
	public int selectCodeListCount(CodeInfo info)throws SQLException;
	public List<CodeInfo> selectCodeList(CodeInfo info)throws SQLException;
	public List selectCodeListExcel(CodeInfo info) throws SQLException;
	public CodeInfo selectCodeInfo(CodeInfo info) throws SQLException;
	public int updateCodeInfo(CodeInfo info) throws  SQLException;
	public int deleteCodeInfo(CodeInfo info) throws SQLException;
	public void insertCodeInfo(CodeInfo info) throws SQLException;
}
