package com.stockinvest.service.code.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tframe.web.page.PageInfo;

import com.stockinvest.data.code.dao.CodeDAO;
import com.stockinvest.data.code.info.CodeInfo;
import com.stockinvest.service.code.CodeService;

@Service
@Transactional(readOnly=true, rollbackFor = Exception.class)
public class CodeServiceImpl implements CodeService {

	@Autowired
	CodeDAO dao;
	
	@Override
	public PageInfo selectCodeList(PageInfo pageInfo, CodeInfo info) throws SQLException {
		pageInfo.setTotalCount(dao.selectCodeListCount(info));
		if( pageInfo.getTotalCount() > 0 ) {
			info.setCurrentPage(String.valueOf(pageInfo.getCurrentPage()));
			info.setStartNum(pageInfo.getStartNum());
			info.setEndNum(pageInfo.getEndNum());
			pageInfo.setDataList(dao.selectCodeList(info));			
		}
		return pageInfo;
	}

	@Override
	public CodeInfo selectCodeInfo(CodeInfo info) throws SQLException {
		return dao.selectCodeInfo(info);
	}

	@Override
	public int updateCodeInfo(CodeInfo info) throws SQLException {
		return dao.updateCodeInfo(info);
	}

	@Override
	public int deleteCodeInfo(CodeInfo info) throws SQLException {
		return dao.deleteCodeInfo(info);
	}

	@Override
	public void insertCodeInfo(CodeInfo info) throws SQLException {
		dao.insertCodeInfo(info);
	}

	@Override
	public List selectCodeListExcel(CodeInfo info) throws SQLException {
		return dao.selectCodeListExcel(info);
	}

}
