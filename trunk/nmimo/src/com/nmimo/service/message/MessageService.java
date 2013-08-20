package com.nmimo.service.message;

import java.sql.SQLException;

import com.nmimo.data.message.info.MessageInfo;

public interface MessageService {

	public int insertMsgInfo(MessageInfo dbParams) throws SQLException;
	
	public int insertAutoMsgInfo(MessageInfo dbParams) throws SQLException;
}
