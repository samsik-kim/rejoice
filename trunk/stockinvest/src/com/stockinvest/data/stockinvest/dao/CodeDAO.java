package com.stockinvest.data.stockinvest.dao;

import java.sql.SQLException;
import java.util.List;

import com.stockinvest.data.stockinvest.info.CodeInfo;

public interface CodeDAO {
	
	public int selectCodeListCount(CodeInfo info)throws SQLException;	
	public List<CodeInfo> selectCodeList(CodeInfo info)throws SQLException ;
	
}
