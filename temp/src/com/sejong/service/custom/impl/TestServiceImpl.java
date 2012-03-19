package com.sejong.service.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sejong.data.custom.dao.TestDao;
import com.sejong.service.custom.TestService;

@Service
@Transactional(readOnly=true, rollbackFor = Exception.class)
public class TestServiceImpl implements TestService {

	
	@Autowired
	private TestDao dao;
	
	@Override
	public int selectEmp(String empNo) throws Exception {
		return dao.selectEmp(empNo);
	}

}
