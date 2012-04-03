package com.stockinvest.service.code.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tframe.common.util.StringUtils;
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
			
			//데이터가 검색된 상태에서 페이지네비게이션의 페이지 이동을 하고 검색을 다시 누른경우
			//현재 전달받은페이지가 토탈페이지를 넘어버리면 1로 초기화시켜버림.
			int getCurrentPage = Integer.parseInt(StringUtils.nvlStr(info.getCurrentPage(),"1"));  
			
			if ( getCurrentPage > pageInfo.getTotalPage() ) {
				info.setCurrentPage("1");
				info.setStartNum(1);
				info.setEndNum(20);
				pageInfo.setCurrentPage(1);
			} else {
				info.setCurrentPage(String.valueOf(pageInfo.getCurrentPage()));
				info.setStartNum(pageInfo.getStartNum());
				info.setEndNum(pageInfo.getEndNum());
			}
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
