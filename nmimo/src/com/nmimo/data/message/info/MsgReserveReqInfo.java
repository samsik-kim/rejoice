package com.nmimo.data.message.info;

import java.io.Serializable;
import java.util.HashMap;

import com.nmimo.common.info.SocketHeaderInfo;
import com.nmimo.common.util.SocketString;

/**
 * <pre>
 *
 * </pre>
 * @file MsgReserveReqInfo.java
 * @since 2013. 8. 19.
 * @author Rejoice
 */
@SuppressWarnings("serial")
public class MsgReserveReqInfo extends SocketHeaderInfo implements Serializable{

	/** nMIMO 웹사이트 메시지 등록자 (KT사원번호) */
	private String employeeAccount;

	
	/**
	 * <pre>
	 * Default
	 * </pre>
	 */
	public MsgReserveReqInfo() {
		super();
	}

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param employeeAccount
	 */
	public MsgReserveReqInfo(String employeeAccount) {
		super();
		this.employeeAccount = employeeAccount;
	}

	/**
	 * @return the employeeAccount
	 */
	public String getEmployeeAccount() {
		return employeeAccount;
	}

	/**
	 * @param employeeAccount the employeeAccount to set
	 */
	public void setEmployeeAccount(String employeeAccount) {
		this.employeeAccount = employeeAccount;
	}

	/**
	 * <pre>
	 * 
	 * </pre>
	 * @return
	 */
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("X-nMimo-Employee-Account: " + getEmployeeAccount() + "<CRLF>");
		return sb.toString();
	}
	
	//Test
	public static void main(String[] args) throws Exception {
		MsgReserveReqInfo msgReserveReqInfo = new MsgReserveReqInfo();
		msgReserveReqInfo.setCommandLen("123");
		msgReserveReqInfo.setCommandId("aaa");
//		System.out.println(msgReserveReqInfo.getToByte());
		SocketString sds = new SocketString();
		HashMap<String, String> map = sds.getSocketMsg(msgReserveReqInfo.getToByte(), msgReserveReqInfo.toString());
		String returnStr = map.get("X-nMimo-Employee-Account");
		System.out.println(returnStr);
	}
}