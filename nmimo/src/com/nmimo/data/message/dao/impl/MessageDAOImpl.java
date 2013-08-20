package com.nmimo.data.message.dao.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.nmimo.data.message.dao.MessageDAO;
import com.nmimo.data.message.info.MessageInfo;

@Repository
public class MessageDAOImpl implements MessageDAO {

	@Autowired
	 private SqlMapClientTemplate sqlMapClientTemplate;

	private static final String NAME_SPACE = "message";
	
	@Override
	public int insertMsgInfo(MessageInfo dbParams) throws SQLException {
		return sqlMapClientTemplate.update(NAME_SPACE + ".insertMsgInfo", dbParams);
	}
	
	@Override
	public int insertAutoMsgInfo(MessageInfo dbParams) throws SQLException {
		return sqlMapClientTemplate.update(NAME_SPACE + ".insertAutoMsgInfo", dbParams);
	}
	
}
