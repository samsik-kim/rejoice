package com.nmimo.data.message.dao;

import java.sql.SQLException;

import com.nmimo.data.message.info.MessageInfo;

public interface MessageDAO {

	public int insertMsgInfo(MessageInfo dbParams) throws SQLException;
	
	public int insertAutoMsgInfo(MessageInfo dbParams) throws SQLException;
}
