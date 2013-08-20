package com.nmimo.common.info;


public class SocketHeaderInfo {
	
	//	CHAR(4B)	
	private String orgType = "WEB";
	//	CHAR(4B)
	private String commandId="";
	//	LONG(8B)
	private String sequenceNo = "1";
	//	INT(4B)
	private String commandLen="";
	
	/**
	 * @return the orgType
	 */
	public String getOrgType() {
		return orgType;
	}
	/**
	 * @param orgType the orgType to set
	 */
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	/**
	 * @return the commandId
	 */
	public String getCommandId() {
		return commandId;
	}
	/**
	 * @param commandId the commandId to set
	 */
	public void setCommandId(String commandId) {
		this.commandId = commandId;
	}
	/**
	 * @return the sequenceNo
	 */
	public String getSequenceNo() {
		return sequenceNo;
	}
	/**
	 * @param sequenceNo the sequenceNo to set
	 */
	public void setSequenceNo(String sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
	/**
	 * @return the commandLen
	 */
	public String getCommandLen() {
		return commandLen;
	}
	/**
	 * @param commandLen the commandLen to set
	 */
	public void setCommandLen(String commandLen) {
		this.commandLen = commandLen;
	}

	public byte [] getToByte(){
		String str = getOrgType() + getCommandId() + getSequenceNo() + getCommandLen();
		return str.getBytes();
	}
}