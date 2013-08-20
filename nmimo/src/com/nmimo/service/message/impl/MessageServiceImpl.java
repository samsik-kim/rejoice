package com.nmimo.service.message.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nmimo.data.message.dao.MessageDAO;
import com.nmimo.data.message.info.MessageInfo;
import com.nmimo.service.message.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	MessageDAO messageDAO;
	
	@Override
	public int insertMsgInfo(MessageInfo dbParams)throws SQLException{
		return messageDAO.insertMsgInfo(dbParams);
	}

	@Override
	public int insertAutoMsgInfo(MessageInfo dbParams)throws SQLException{
		return messageDAO.insertAutoMsgInfo(dbParams);
	}

}
