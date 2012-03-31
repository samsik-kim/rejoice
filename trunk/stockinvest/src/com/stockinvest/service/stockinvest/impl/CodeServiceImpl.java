package com.stockinvest.service.stockinvest.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tframe.web.page.PageInfo;

import com.stockinvest.data.stockinvest.dao.CodeDAO;
import com.stockinvest.data.stockinvest.info.CodeInfo;
import com.stockinvest.service.stockinvest.CodeService;

@Service
@Transactional(readOnly=true, rollbackFor = Exception.class)
public class CodeServiceImpl implements CodeService {

	@Autowired
	CodeDAO dao;
	
	@Override
	public PageInfo selectCodeList(PageInfo pageInfo, CodeInfo info) throws SQLException {
		pageInfo.setTotalCount(dao.selectCodeListCount(info));
		if( pageInfo.getTotalCount() > 0 ) {
			info.setStartNum(pageInfo.getStartNum());
			info.setEndNum(pageInfo.getEndNum());
			pageInfo.setDataList(dao.selectCodeList(info));			
		}
		return pageInfo;
	}

}
