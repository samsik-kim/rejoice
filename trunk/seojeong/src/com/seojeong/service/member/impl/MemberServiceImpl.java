package com.seojeong.service.member.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seojeong.data.member.dao.MemberDAO;
import com.seojeong.service.member.MemberService;

@Service
@Transactional(readOnly=true, rollbackFor = Exception.class)
public class MemberServiceImpl implements MemberService {

	
	@Autowired
	MemberDAO dao;
	
	@Override
	public int selectEmp() throws SQLException {
		return dao.selectEmp();
	}

}
